package flandre.scarlet.thevoidkingdom.utils.events;

import flandre.scarlet.thevoidkingdom.functions.enchant.EnchantMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class PlayerPlayCardGameEvent extends PlayerEvent {
    public static final HandlerList handles = new HandlerList();

    private final EnchantMenu enchantMenu;

    public PlayerPlayCardGameEvent(@NotNull Player who, EnchantMenu enchantMenu) {
        super(who, false);
        this.enchantMenu = enchantMenu;
    }

    @Override
    @Nonnull
    public HandlerList getHandlers() {
        return handles;
    }

    public static HandlerList getHandlerList() {
        return handles;
    }

    public EnchantMenu getEnchantMenu() {
        return enchantMenu;
    }
}
