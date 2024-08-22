package flandre.scarlet.thevoidkingdom.utils.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class PlayerRepairItemEvent extends PlayerEvent {
    public static final HandlerList handles = new HandlerList();

    private final ItemStack itemStack;

    public PlayerRepairItemEvent(@NotNull Player who, ItemStack itemStack) {
        super(who, false);
        this.itemStack = itemStack;
    }

    @Override
    @Nonnull
    public HandlerList getHandlers() {
        return handles;
    }

    public static HandlerList getHandlerList() {
        return handles;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
