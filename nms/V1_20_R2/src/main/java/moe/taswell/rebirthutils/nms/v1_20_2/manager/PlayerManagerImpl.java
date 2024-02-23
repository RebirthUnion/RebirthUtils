package moe.taswell.rebirthutils.nms.v1_20_2.manager;

import io.netty.channel.Channel;
import moe.taswell.rebirthutils.nms.api.manager.PlayerManager;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerManagerImpl implements PlayerManager {
    @Override
    public Channel getChannelOfPlayer(Player player) {
        final CraftPlayer craftEntity = ((CraftPlayer) player);
        final ServerPlayer handle = craftEntity.getHandle();

        return handle.connection.connection.channel;
    }
}