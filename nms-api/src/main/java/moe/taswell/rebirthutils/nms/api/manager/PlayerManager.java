package moe.taswell.rebirthutils.nms.api.manager;

import io.netty.channel.Channel;
import org.bukkit.entity.Player;

public interface PlayerManager {
    Channel getChannelOfPlayer(Player player);
}