package moe.taswell.rebirthutils.nms.api.events;

import moe.taswell.rebirthutils.nms.api.buffer.PackagedFriendlyByteBuf;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerInGamePacketSendEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private final int packetId;
    private final PackagedFriendlyByteBuf packetBuffer;
    private final Player targetPlayer;

    public PlayerInGamePacketSendEvent(int packetId, PackagedFriendlyByteBuf packetBuffer, Player targetPlayer) {
        super(true);
        this.packetId = packetId;
        this.packetBuffer = packetBuffer;
        this.targetPlayer = targetPlayer;
    }

    public Player getTargetPlayer() {
        return this.targetPlayer;
    }

    public int getPacketId() {
        return this.packetId;
    }

    public PackagedFriendlyByteBuf getPacketBuffer() {
        return this.packetBuffer;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}