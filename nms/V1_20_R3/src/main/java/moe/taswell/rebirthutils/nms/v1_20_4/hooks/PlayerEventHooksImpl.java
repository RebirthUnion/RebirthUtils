package moe.taswell.rebirthutils.nms.v1_20_4.hooks;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import moe.taswell.rebirthutils.nms.api.events.PlayerConnectionAttachedEvent;
import moe.taswell.rebirthutils.nms.api.hooks.PlayerEventHooks;
import moe.taswell.rebirthutils.nms.v1_20_4.internal.NettyAttachHandlerInbound;
import moe.taswell.rebirthutils.nms.v1_20_4.internal.NettyAttachHandlerOutbound;
import net.minecraft.network.Connection;
import net.minecraft.server.MinecraftServer;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerEventHooksImpl implements PlayerEventHooks {
    @Override
    public void onPlayerJoin(Player player) {
        final Connection targetPlayerConnection = ((CraftPlayer) player).getHandle().connection.connection;
        final Channel targetPlayerChannel = targetPlayerConnection.channel;
        final ChannelPipeline channelPipeline = targetPlayerChannel.pipeline();

        if (!channelPipeline.names().contains("packet_attach_handler_in")){
            final NettyAttachHandlerInbound attachHandlerInbound = new NettyAttachHandlerInbound(player);
            final NettyAttachHandlerOutbound attachHandlerOutbound = new NettyAttachHandlerOutbound(player);

            if (MinecraftServer.getServer().getCompressionThreshold() >= 0 && !targetPlayerConnection.isMemoryConnection()){
                channelPipeline.addAfter("decompress","attach_inbound",attachHandlerInbound);
                channelPipeline.addBefore("compress","attach_outbound",attachHandlerOutbound);
            }else{
                channelPipeline.addBefore("decoder","attach_inbound",attachHandlerInbound);
                channelPipeline.addAfter("encoder","attach_outbound",attachHandlerOutbound);
            }

            new PlayerConnectionAttachedEvent(player).callEvent();
        }
    }

    @Override
    public void onPlayerLeft(Player player) {

    }
}