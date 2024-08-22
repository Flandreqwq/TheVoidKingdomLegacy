package flandre.scarlet.thevoidkingdom.utils.events;

import flandre.scarlet.thevoidkingdom.functions.mob.boss.BossTotem;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class PlayerActiveTotemEvent extends PlayerEvent {
    public static final HandlerList handles = new HandlerList();

    private final BossTotem bossTotem;

    public PlayerActiveTotemEvent(@NotNull Player who, BossTotem bossTotem) {
        super(who, false);
        this.bossTotem = bossTotem;
    }

    @Override
    @Nonnull
    public HandlerList getHandlers() {
        return handles;
    }

    public static HandlerList getHandlerList() {
        return handles;
    }

    public BossTotem getTotem() {
        return bossTotem;
    }
}
