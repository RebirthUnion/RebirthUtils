package moe.taswell.rebirthchat

import moe.taswell.rebirthchat.listener.PlayerEventListener
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

object PluginBootstrap {
    lateinit var pluginInstance: Plugin

    fun onEnable(pluginInstance: Plugin){
        this.pluginInstance = pluginInstance

        pluginInstance.logger.info("Loading config file")
        pluginInstance.saveDefaultConfig()

        pluginInstance.logger.info("Register event listeners")
        Bukkit.getPluginManager().registerEvents(PlayerEventListener,pluginInstance)
    }

    fun onDisable(plugin: Plugin){

    }

}