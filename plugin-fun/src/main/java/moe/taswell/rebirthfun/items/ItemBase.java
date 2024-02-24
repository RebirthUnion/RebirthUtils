package moe.taswell.rebirthfun.items;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

public interface ItemBase {
    String getItemName();

    ShapedRecipe getRecipe(ItemStack finalItem);

    ItemStack getItemWithoutNtag();

    boolean onUseItem(Player user, @Nullable Block onBlock, Action action);

    boolean onAttackEntity(Entity user, Entity attacked);

    void onAsyncTicked();
}
