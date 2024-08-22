package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryMenu extends GuideBookMenu {

    public class CategoryMenuHolder extends GuideBookMenuHolder {
        @Override
        public @NotNull CategoryMenu getMenu() {
            return CategoryMenu.this;
        }
    }

    private final Category category;
    private final int page;

    public CategoryMenu(Player owner, Category category, int page) {
        super(owner);
        this.category = category;
        this.inventory = Bukkit.createInventory(new CategoryMenuHolder(), 54,
                LegacyComponentSerializer.legacySection().deserialize(":offset_-16::gui_guide_blank::offset_-176:" + category.categoryDisplayName().replace("&", "§")));
        int maxPage = (int) Math.ceil(category.nameSpaceIds().size() / 45.0);
        if (page > maxPage) {
            page = maxPage;
        } else if (page < 1) {
            page = 1;
        }
        this.page = page;
        List<String> allNameSpaceIds = category.nameSpaceIds();
        List<String> showNameSpaceIds = allNameSpaceIds.subList((page - 1) * 45, Math.min(page * 45, allNameSpaceIds.size()));
        int slot = 0;
        for (String namespaceId : showNameSpaceIds) {
            inventory.setItem(slot, ItemsAdderUtils.getItemStackNotNull(namespaceId));
            slot++;
        }
        inventory.setItem(49, GuideBookMenuIcon.CLOSE.getItemStack());
        if (page > 1) {
            inventory.setItem(46, GuideBookMenuIcon.LAST.getItemStack());
        }
        if (page < maxPage) {
            inventory.setItem(52, GuideBookMenuIcon.NEXT.getItemStack());
        }
    }


    public Category getCategory() {
        return category;
    }

    public int getPage() {
        return page;
    }

}
