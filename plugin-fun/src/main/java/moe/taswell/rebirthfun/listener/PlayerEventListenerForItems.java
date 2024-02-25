package moe.taswell.rebirthfun.listener;

import moe.taswell.rebirthfun.items.CustomItemRegistryManager;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerEventListenerForItems implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerUseItem(PlayerInteractEvent event){
        final Action action = event.getAction();
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItem();
        final Block actuallyClickedOn = event.getClickedBlock();

        final Block toUse = action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_AIR ? null : actuallyClickedOn;

        CustomItemRegistryManager.onPlayerUseItem(player,itemStack,toUse,action);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityAttackedEntity(EntityDamageByEntityEvent event){
        final ItemStack usedItem = event.getDamager() instanceof LivingEntity livingEntity ? livingEntity.getEquipment() == null ? null : livingEntity.getEquipment().getItemInMainHand() : null;
        CustomItemRegistryManager.onEntityAttackedEntity(event.getDamager(),event.getEntity(),usedItem);
    }
}
