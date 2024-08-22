package flandre.scarlet.thevoidkingdom.utils.game.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class Menu {
    public Inventory inventory;
    public final Player owner;

    public abstract class MenuHolder implements InventoryHolder {
        @Override
        public @NotNull Inventory getInventory() {
            return getMenu().inventory;
        }

        public @NotNull Menu getMenu() {
            return Menu.this;
        }
    }

    public Menu(Player owner) {
        this.owner = owner;
    }

    public void open(OpenReason openReason) {
    }

    public void close(CloseReason closeReason) {
    }

    public void setTitle(Component component) {
        InventoryView inventoryView = owner.getOpenInventory();
        if (Objects.equals(inventoryView.getTopInventory().getHolder(), inventory.getHolder())) {
            inventoryView.setTitle(LegacyComponentSerializer.legacySection().serialize(component));
        }
    }
}
