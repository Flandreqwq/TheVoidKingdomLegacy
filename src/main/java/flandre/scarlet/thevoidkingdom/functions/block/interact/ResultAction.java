package flandre.scarlet.thevoidkingdom.functions.block.interact;

import org.bukkit.Location;

public interface ResultAction {
    String getResultNamespaceId();
    void execute(Location location);

    boolean isValid();
}
