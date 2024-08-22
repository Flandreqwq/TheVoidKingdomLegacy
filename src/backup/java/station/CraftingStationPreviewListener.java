package flandre.scarlet.thevoidkingdom.functions.station;

import com.molean.isletopia.framework.annotations.Bean;
import de.tr7zw.nbtapi.NBTItem;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.GuideBookMenuIcon;
import net.Indyuce.mmoitems.gui.CraftingStationPreview;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Bean
public class CraftingStationPreviewListener implements Listener {
    private static final List<Integer> SLOTS = List.of(1, 2, 3, 4, 10, 11, 12, 13, 19, 20, 21, 22);

    @EventHandler(priority = EventPriority.LOW)
    public void on(InventoryOpenEvent event) throws NoSuchFieldException, IllegalAccessException {
        Inventory inventory = event.getInventory();
        if (inventory.getHolder() instanceof CraftingStationPreview craftingStationPreview) {
            ItemStack resultItem = Objects.requireNonNull(inventory.getItem(16)).clone();
            inventory.clear();
            Class clazz = craftingStationPreview.getClass();
            Field field = clazz.getDeclaredField("ingredients");
            field.setAccessible(true);
            List<ItemStack> ingredients = (List<ItemStack>) field.get(craftingStationPreview);
            Iterator<Integer> iterator = SLOTS.iterator();
            for (ItemStack itemStack : ingredients) {
                if (iterator.hasNext()) {
                    inventory.setItem(iterator.next(), itemStack);
                } else {
                    TheVoidKingdom.LOGGER.warning("工作站配方所需物品种类过多");
                }
            }
            inventory.setItem(16, resultItem);
            ItemStack backIcon = GuideBookMenuIcon.CLOSE.getItemStack().clone();
            NBTItem nbtItem = new NBTItem(backIcon);
            nbtItem.setString("ItemId", "BACK");
            inventory.setItem(40, nbtItem.getItem());
        }
    }
}
