package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import com.molean.isletopia.framework.annotations.Bean;
import de.tr7zw.nbtapi.NBTItem;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.menu.OpenReason;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Bean
public class CategoryMenuListener implements Listener {


    private static final List<ClickType> clickTypes = List.of(
            ClickType.LEFT,
            ClickType.RIGHT,
            ClickType.SHIFT_LEFT,
            ClickType.SHIFT_RIGHT
    );

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        InventoryHolder inventoryHolder = inventory.getHolder();
        if (inventoryHolder instanceof CategoryMainMenu.CategoryMainMenuHolder) {
            int clickSlot = event.getRawSlot();
            if (clickSlot < 0 || clickSlot > inventory.getSize()) {
                return;
            }
            ItemStack clickIcon = inventory.getItem(clickSlot);
            if (clickIcon == null) {
                return;
            }
            ClickType click = event.getClick();
            if (!clickTypes.contains(click)) {
                return;
            }
            if (clickIcon.equals(GuideBookMenuIcon.SEARCH.getItemStack())) {
                System.out.println("搜索");
            } else {
                NBTItem nbtItem = new NBTItem(clickIcon);
                if (!nbtItem.hasKey("category")) {
                    return;
                }
                String categoryName = nbtItem.getString("category");
                Category category = Category.CATEGORY_KEY_MAP.get(categoryName);
                new CategoryMenu(player, category, 1).open(OpenReason.OPEN_NEW);
            }
        } else if (inventoryHolder instanceof CategoryMenu.CategoryMenuHolder categoryMenuHolder) {
            int clickSlot = event.getRawSlot();
            if (clickSlot < 0 || clickSlot > inventory.getSize()) {
                return;
            }
            ItemStack clickIcon = inventory.getItem(clickSlot);
            if (clickIcon == null) {
                return;
            }
            ClickType click = event.getClick();
            if (!clickTypes.contains(click)) {
                return;
            }
            CategoryMenu categoryMenu = categoryMenuHolder.getMenu();
            if (clickIcon.equals(GuideBookMenuIcon.CLOSE.getItemStack())) {
                categoryMenu.backToLastMenu();
            } else if (clickIcon.equals(GuideBookMenuIcon.NEXT.getItemStack())) {
                new CategoryMenu(player, categoryMenu.getCategory(), categoryMenu.getPage() + 1).open(OpenReason.NEXT_PAGE);
            } else if (clickIcon.equals(GuideBookMenuIcon.LAST.getItemStack())) {
                new CategoryMenu(player, categoryMenu.getCategory(), categoryMenu.getPage() - 1).open(OpenReason.LAST_PAGE);
            } else {
                new RecipeListMenu(player, ItemsAdderUtils.getItemNamespaceId(clickIcon), !click.isShiftClick(), 1).open(OpenReason.OPEN_NEW);
            }
        }
    }

}
