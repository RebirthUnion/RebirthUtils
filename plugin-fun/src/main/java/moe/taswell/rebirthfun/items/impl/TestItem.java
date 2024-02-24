package moe.taswell.rebirthfun.items.impl;

import moe.taswell.rebirthfun.items.ItemBase;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestItem implements ItemBase {
    private static final ItemStack wrapped;

    static {
        final ItemStack testWrapped = new ItemStack(Material.BLAZE_ROD);

        testWrapped.editMeta(meta -> {
            meta.displayName(Component.text("小棒♂棒♂").color(TextColor.color(255,192,203)));
            meta.addEnchant(Enchantment.FIRE_ASPECT,255,true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

            meta.lore(List.of(Component.text("♂♂♂♂♂♂♂♂♂").color(TextColor.color(255,192,203))));
        });

        wrapped = testWrapped;
    }

    @Override
    public String getItemName() {
        return "oioo:testitem";
    }

    @Override
    public ShapedRecipe getRecipe(ItemStack finalItem) {
        return null;
    }

    @Override
    public ItemStack getItemWithoutNtag() {
        return wrapped;
    }

    @Override
    public boolean onUseItem(Player user, @Nullable Block onBlock, Action action) {
        if (onBlock == null){
            user.spawnParticle(Particle.HEART,user.getLocation(),20);
        }

        return true;
    }

    @Override
    public boolean onAttackEntity(Entity user, Entity attacked) {
        attacked.getWorld().spawnParticle(Particle.HEART,attacked.getLocation(),20);

        return true;
    }

    @Override
    public void onAsyncTicked() {

    }
}
