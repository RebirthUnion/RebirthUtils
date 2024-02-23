package moe.taswell.pluginlibrary;

import moe.taswell.pluginlibrary.events.PlayerEventListener;
import moe.taswell.rebirthutils.nms.shared.APIEntryPoint;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RebirthLibraries extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("Loading API components");
        APIEntryPoint.init();
        this.getLogger().info("Registering listeners");
        this.registerEventListeners();
    }

    private void registerEventListeners(){
        Bukkit.getPluginManager().registerEvents(new PlayerEventListener(),this);
    }

    @Override
    public void onDisable() { }
}
