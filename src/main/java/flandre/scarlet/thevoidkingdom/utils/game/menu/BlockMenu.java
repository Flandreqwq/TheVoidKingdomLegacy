package flandre.scarlet.thevoidkingdom.utils.game.menu;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class BlockMenu extends Menu {
    public final Location blockLocation;

    public abstract class BlockMenuHolder extends Menu.MenuHolder {
        public @NotNull BlockMenu getMenu() {
            return BlockMenu.this;
        }
    }

    public BlockMenu(Player owner, Location blockLocation) {
        super(owner);
        this.blockLocation = blockLocation;
    }

    public Location getBlockLocation() {
        return blockLocation;
    }

}
