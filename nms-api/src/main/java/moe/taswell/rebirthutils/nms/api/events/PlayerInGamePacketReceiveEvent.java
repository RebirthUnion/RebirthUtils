package moe.taswell.rebirthutils.nms.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerInGamePacketReceiveEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private final int packetId;
    private final byte[] packetBuffer;
    private final Player sender;

    public PlayerInGamePacketReceiveEvent(int packetId, byte[] packetBuffer, Player sender) {
        super(true);
        this.packetId = packetId;
        this.packetBuffer = packetBuffer;
        this.sender = sender;
    }

    public Player getSender() {
        return this.sender;
    }

    public int getPacketId() {
        return this.packetId;
    }

    public byte[] getPacketBuffer() {
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