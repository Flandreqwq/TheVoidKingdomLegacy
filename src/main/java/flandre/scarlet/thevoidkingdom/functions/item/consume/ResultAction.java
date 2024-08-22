package flandre.scarlet.thevoidkingdom.functions.item.consume;

import org.bukkit.entity.Player;

public interface ResultAction {
    void execute(Player player);

    boolean isValid();
}
