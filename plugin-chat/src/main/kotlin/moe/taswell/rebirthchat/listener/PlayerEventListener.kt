package moe.taswell.rebirthchat.listener

import io.papermc.paper.event.player.AsyncChatEvent
import moe.taswell.rebirthchat.PluginBootstrap
import moe.taswell.rebirthchat.Resources
import moe.taswell.rebirthutils.nms.shared.APIEntryPoint
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.concurrent.ConcurrentHashMap

object PlayerEventListener : Listener {
    private val playerLastChatTimes: MutableMap<Player,Long> = ConcurrentHashMap()

    @EventHandler
    fun onPlayerChat(playerChatEvent: AsyncChatEvent){
        val player = playerChatEvent.player

        if (!this.checkChatSpeedRate(player)){
            PluginBootstrap.pluginInstance.logger.info("Player ${player.name} is chatting too fast!")
            player.sendMessage(Component.text(Resources.getChatToMessageNotice()))
            playerChatEvent.isCancelled = true
        }
    }

    fun checkChatSpeedRate(player: Player): Boolean{
        if (!this.playerLastChatTimes.containsKey(player)){
            this.playerLastChatTimes[player] = System.currentTimeMillis()
            return true
        }

        val currentRateLimit = 1000 / Resources.getChatSpeedLimitPerSec().toLong()
        val currentTimeAbs = System.currentTimeMillis() - this.playerLastChatTimes[player]!!

        this.playerLastChatTimes[player] = System.currentTimeMillis()

        return currentTimeAbs > currentRateLimit
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerJoin(playerJoinEvent: PlayerJoinEvent){
        val player = playerJoinEvent.player

        this.processJoin(player)
        this.showTitleToPlayers(player)
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerQuit(playerQuitEvent: PlayerQuitEvent){
        val player = playerQuitEvent.player

        this.processQuit(player)
    }

    fun processQuit(player: Player){
        this.playerLastChatTimes.remove(player)
    }

    fun processJoin(player: Player){
        this.playerLastChatTimes[player] = System.currentTimeMillis()
    }

    fun showTitleToPlayers(player: Player){
        val messages = Resources.getTitleMessagesAndDelay()

        APIEntryPoint.getSchedulerService().runAsyncTaskNow({
            for (singleMessage in messages.first){
                val split = singleMessage.split("|")

                if (split.size == 2){
                    val mainTitle = split[0]
                    val subTitle = split[1]

                    val wrappedTitle = Title.title(Component.text(mainTitle),Component.text(subTitle))
                    player.showTitle(wrappedTitle)
                }else{
                    PluginBootstrap.pluginInstance.logger.warning("Skipping invalid title $singleMessage !")
                }

                Thread.sleep(messages.second)
            }
        },PluginBootstrap.pluginInstance)
    }

}