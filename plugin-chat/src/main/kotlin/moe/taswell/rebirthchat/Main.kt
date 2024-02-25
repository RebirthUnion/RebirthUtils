package moe.taswell.rebirthchat

import org.bukkit.enchantments.Enchantment
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    override fun onEnable() {
        PluginBootstrap.onEnable(this)
    }

    override fun onDisable() {
        PluginBootstrap.onDisable(this)
    }

}