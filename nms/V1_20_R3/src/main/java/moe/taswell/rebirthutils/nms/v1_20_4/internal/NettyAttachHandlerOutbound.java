package moe.taswell.rebirthutils.nms.v1_20_4.internal;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import moe.taswell.rebirthutils.nms.api.events.PlayerInGamePacketSendEvent;
import moe.taswell.rebirthutils.nms.v1_20_4.buffer.PackagedFriendlyByteBufImpl;
import net.minecraft.network.FriendlyByteBuf;
import org.bukkit.entity.Player;

public class NettyAttachHandlerOutbound extends ChannelDuplexHandler {
    private final Player player;

    public NettyAttachHandlerOutbound(Player player) {
        this.player = player;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise){
        if (msg instanceof ByteBuf byteBuf){
            final FriendlyByteBuf wrapped = new FriendlyByteBuf(byteBuf);

            byteBuf.markReaderIndex();
            final int packetId = wrapped.readVarInt();

            if (!new PlayerInGamePacketSendEvent(packetId,new PackagedFriendlyByteBufImpl(wrapped),this.player).callEvent()){
                wrapped.skipBytes(byteBuf.readableBytes());
                return;
            }

            byteBuf.resetReaderIndex();
        }

        ctx.write(msg, promise);
    }
}