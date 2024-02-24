package moe.taswell.rebirthfun.items;

import moe.taswell.rebirthfun.RebirthFun;
import moe.taswell.rebirthfun.items.impl.TestItem;
import moe.taswell.rebirthutils.nms.api.nbt.PackagedCompoundTag;
import moe.taswell.rebirthutils.nms.api.scheduler.ScheduledTask;
import moe.taswell.rebirthutils.nms.shared.APIEntryPoint;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class RegistryManager {
    private static final Map<String,ItemBase> registedItems = new ConcurrentHashMap<>();
    private static final Map<String, CompletableFuture<Void>> allAsyncTickTasks = new ConcurrentHashMap<>();
    private static final Map<String,ItemStack> registedItemstacks = new ConcurrentHashMap<>();
    private static ScheduledTask tickTask = null;

    public static void stopTicking(){
        tickTask.cancel();

        for (CompletableFuture<Void> singleTickTask : allAsyncTickTasks.values()){
            singleTickTask.cancel(true);
        }
        allAsyncTickTasks.clear();

        tickTask = null;
    }

    public static ItemStack getItem(String registry){
        return registedItemstacks.get(registry);
    }

    public static ItemBase getItemBase(String registry){
        return registedItems.get(registry);
    }

    public static void unloadAllRegistry(){
        for (String itemRegistryString : registedItems.keySet()){
            final NamespacedKey itemRegistry = NamespacedKey.fromString(itemRegistryString);

            if (itemRegistry == null){
                Bukkit.getLogger().warning("Skipping removing invalid recipe " + itemRegistryString + "!");
                continue;
            }

            Bukkit.getServer().removeRecipe(itemRegistry);
        }

        registedItems.clear();
    }

    public static void loadForSingleItem(@NotNull ItemBase itemBase){
        final String itemRegistryString = itemBase.getItemName();

        final ItemStack wrappedItemStack = itemBase.getItemWithoutNtag().clone();
        final PackagedCompoundTag itemNbt = APIEntryPoint.getNbtManager().getTagOfItem(wrappedItemStack);

        if (itemNbt.contains("nname")){
            throw new IllegalStateException("The item which is going to be registed already contained nTag!");
        }

        itemNbt.putString("nname",itemRegistryString);

        registedItemstacks.put(itemRegistryString,wrappedItemStack);

        final ShapedRecipe recipe = itemBase.getRecipe(wrappedItemStack);

        if (recipe == null){
            return;
        }

        Bukkit.getServer().addRecipe(recipe,true);
    }

    public static void registerAllItems(){
       final TestItem testItem = new TestItem();
        registedItems.put(testItem.getItemName(),testItem);
    }

    public static void loadAllItems(){
        for (ItemBase singleItem: registedItems.values()){
            try {
                loadForSingleItem(singleItem);
            }catch (Exception e){
                Bukkit.getLogger().warning("Failed to register custom item!" + e.getLocalizedMessage());
            }
        }
    }

    public static void init(){
        registerAllItems();
        loadAllItems();
        startTicking();
    }

    public static boolean onPlayerUseItem(Player player, ItemStack usedItem, @Nullable Block clickedOn, Action action){
        final PackagedCompoundTag itemNbt = APIEntryPoint.getNbtManager().getTagOfItem(usedItem);

        if (!itemNbt.contains("nname")){
            return true;
        }

        final String itemRegistry = itemNbt.getString("nname");

        if (!registedItems.containsKey(itemRegistry)){
            Bukkit.getLogger().warning("The registry of used item " + itemRegistry + "has not found!Skipping event being processed");
            return true;
        }

        final ItemBase targetItemBase = registedItems.get(itemRegistry);

        return targetItemBase.onUseItem(player,clickedOn,action);
    }

    public static boolean onEntityAttackedEntity(Entity user,Entity attackedEntity,@Nullable ItemStack usedItem){
        if (usedItem == null){
            return true;
        }

        final PackagedCompoundTag itemNbt = APIEntryPoint.getNbtManager().getTagOfItem(usedItem);

        if (!itemNbt.contains("nname")){
            return true;
        }

        final String itemRegistry = itemNbt.getString("nname");

        if (!registedItems.containsKey(itemRegistry)){
            Bukkit.getLogger().warning("The registry of used item " + itemRegistry + "has not found!Skipping event being processed");
            return true;
        }

        final ItemBase targetItemBase = registedItems.get(itemRegistry);

        return targetItemBase.onAttackEntity(user,attackedEntity);
    }

    public static void startTicking(){
        APIEntryPoint.getSchedulerService().runAsyncTaskTimer(()->{
            for (Map.Entry<String,ItemBase> itemBaseEntry : registedItems.entrySet()){
                final String registryString = itemBaseEntry.getKey();
                final ItemBase itemBase = itemBaseEntry.getValue();

                if (!allAsyncTickTasks.containsKey(registryString)){
                    allAsyncTickTasks.put(registryString,CompletableFuture.runAsync(itemBase::onAsyncTicked,task -> APIEntryPoint.getSchedulerService().runAsyncTaskNow(task,RebirthFun.pluginInstance)));
                    continue;
                }

                if (!allAsyncTickTasks.get(registryString).isDone()){
                    continue;
                }

                allAsyncTickTasks.replace(registryString,CompletableFuture.runAsync(itemBase::onAsyncTicked,task -> APIEntryPoint.getSchedulerService().runAsyncTaskNow(task,RebirthFun.pluginInstance)));
            }
        },20,1, RebirthFun.pluginInstance);
    }
}
