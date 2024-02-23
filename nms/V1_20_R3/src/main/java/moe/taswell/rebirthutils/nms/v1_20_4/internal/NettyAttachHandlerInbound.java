package moe.taswell.rebirthutils.nms.v1_20_4.internal;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import moe.taswell.rebirthutils.nms.api.events.PlayerInGamePacketReceiveEvent;
import net.minecraft.network.FriendlyByteBuf;
import org.bukkit.entity.Player;

public class NettyAttachHandlerInbound extends SimpleChannelInboundHandler<Object> {
    protected final Player player;

    public NettyAttachHandlerInbound(Player player) {
        super(false);
        this.player = player;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        if ((msg instanceof ByteBuf byteBuf)){
            final ByteBuf copy = byteBuf.copy();
            final FriendlyByteBuf wrapped = new FriendlyByteBuf(copy);

            final int packetId = wrapped.readVarInt();
            final byte[] remainingBytes = new byte[wrapped.readableBytes()];
            wrapped.readBytes(remainingBytes);

            if (!new PlayerInGamePacketReceiveEvent(packetId,remainingBytes,this.player).callEvent()){
                return;
            }
        }

        ctx.fireChannelRead(msg);
    }
}