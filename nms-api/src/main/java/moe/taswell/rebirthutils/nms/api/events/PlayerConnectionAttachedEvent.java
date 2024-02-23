package moe.taswell.rebirthutils.nms.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerConnectionAttachedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player targetPlayer;

    public PlayerConnectionAttachedEvent(Player targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public Player getTargetPlayer(){
        return this.targetPlayer;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}