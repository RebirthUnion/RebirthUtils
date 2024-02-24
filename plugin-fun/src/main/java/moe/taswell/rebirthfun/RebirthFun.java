package moe.taswell.rebirthfun;

import moe.taswell.rebirthfun.commands.GiveCustomItemCommand;
import moe.taswell.rebirthfun.items.RegistryManager;
import moe.taswell.rebirthfun.listener.PlayerEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RebirthFun extends JavaPlugin {
    public static RebirthFun pluginInstance;


    @Override
    public void onEnable() {
        pluginInstance = this;
        this.getLogger().info("Loading items");
        RegistryManager.init();
        this.getLogger().info("Register event listener");
        Bukkit.getPluginManager().registerEvents(new PlayerEventListener(),this);
        this.getLogger().info("Register commands");
        Bukkit.getPluginCommand("gcitem").setExecutor(new GiveCustomItemCommand());
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Stopping tick tasks");
        RegistryManager.stopTicking();
        this.getLogger().info("Unloading registries");
        RegistryManager.unloadAllRegistry();
    }
}
