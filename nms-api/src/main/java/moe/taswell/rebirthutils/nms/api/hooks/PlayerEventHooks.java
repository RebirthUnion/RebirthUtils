package moe.taswell.rebirthutils.nms.api.hooks;

import org.bukkit.entity.Player;

public interface PlayerEventHooks {
    void onPlayerJoin(Player player);

    void onPlayerLeft(Player player);
}