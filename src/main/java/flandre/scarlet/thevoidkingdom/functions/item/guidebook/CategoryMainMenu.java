package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class CategoryMainMenu extends GuideBookMenu {

    public class CategoryMainMenuHolder extends GuideBookMenuHolder {
        @Override
        public @NotNull Inventory getInventory() {
            return CategoryMainMenu.this.inventory;
        }
    }

    public static final String[] CATEGORY_SEQUENCE = {
            "function", "nature", "building", "color", "furniture", "material", "prop", "farming", "cooking", "meal",
            "tool", "dagger", "universal",
            "hammer", "sword", "shield", "bow", "cross", "shot", "wand", "lantern", "book",
            "warrior", "archer", "mage", "talisman", "spellcard", "hat"
    };

    public CategoryMainMenu(Player owner) {
        super(owner);
        this.inventory = Bukkit.createInventory(new CategoryMainMenuHolder(), 54, TheVoidKingdomUtils.deserializeMiniMessage(":offset_-16::gui_guide_blank::offset_-176:<gold>❊ <bold>物品图鉴", false));
        int slot = 0;
        for (String categoryName : CATEGORY_SEQUENCE) {
            Category category = Category.CATEGORY_KEY_MAP.get(categoryName);
            if (category == null) {
                TheVoidKingdom.LOGGER.warning("未知的分类: " + categoryName);
                continue;
            }
            if (owner.hasPermission(category.permission())) {
                inventory.setItem(slot, category.icon());
                slot++;
            }
        }
        inventory.setItem(49, GuideBookMenuIcon.SEARCH.getItemStack());
    }
}
