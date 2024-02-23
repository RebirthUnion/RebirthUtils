package moe.taswell.pluginlibrary.events;

import moe.taswell.rebirthutils.nms.api.hooks.PlayerEventHooks;
import moe.taswell.rebirthutils.nms.shared.APIEntryPoint;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerEventListener implements Listener {
    private final PlayerEventHooks apiEventHooks = APIEntryPoint.getPlayerEventHooks();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(@NotNull PlayerJoinEvent event){
        this.apiEventHooks.onPlayerJoin(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLeft(@NotNull PlayerQuitEvent event){
        this.apiEventHooks.onPlayerLeft(event.getPlayer());
    }
}