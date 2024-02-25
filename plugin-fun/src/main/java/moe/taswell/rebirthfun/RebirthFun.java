package moe.taswell.rebirthfun;

import moe.taswell.rebirthfun.commands.GiveCustomItemCommand;
import moe.taswell.rebirthfun.items.CustomItemRegistryManager;
import moe.taswell.rebirthfun.listener.PlayerEventListenerForItems;
import org.bukkit.Bukkit;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public final class RebirthFun extends JavaPlugin {
    public static RebirthFun pluginInstance;

    @Override
    public void onEnable() {
        pluginInstance = this;
        this.getLogger().info("Loading items");
        CustomItemRegistryManager.init();
        this.getLogger().info("Register event listener");
        Bukkit.getPluginManager().registerEvents(new PlayerEventListenerForItems(),this);
        this.getLogger().info("Register commands");
        Bukkit.getPluginCommand("gcitem").setExecutor(new GiveCustomItemCommand());
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Stopping tick tasks");
        CustomItemRegistryManager.stopTicking();
        this.getLogger().info("Unloading registries");
        CustomItemRegistryManager.unloadAllRegistry();
    }
}
