package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.SwitchedItem;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.SwitchedItemManager;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.Set;
@Register(registerName = "匣子战利品配方")
public record OpenBoxRecipe(String boxNamespaceId, Set<String> dropItemNamespaceIds) implements VKRecipe {

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_open_box:";
    }

    @Override
    public String getMenuDisplayName() {
        return "匣子战利品";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        inventory.setItem(20, ItemsAdderUtils.getItemStackNoCheck(boxNamespaceId));
        SwitchedItemManager.create(inventory, new SwitchedItem(dropItemNamespaceIds, SwitchedItem.SwitchMode.NORMAL, 24));
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        return boxNamespaceId.equals(namespaceId);
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return dropItemNamespaceIds.contains(namespaceId);
    }


    @Override
    public boolean isValid() {
        if (!ItemsAdderUtils.isItemNamespaceIdExist(boxNamespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册匣子战利品配方失败,原因: 匣子物品 " + boxNamespaceId + " 不存在");
            return false;
        }
        for (String dropItemNamespaceId : dropItemNamespaceIds) {
            if (!dropItemNamespaceId.contains("EXP:") && !ItemsAdderUtils.isItemNamespaceIdExist(dropItemNamespaceId)) {
                TheVoidKingdom.LOGGER.warning("注册匣子战利品配方失败,原因: 掉落物品 " + dropItemNamespaceId + " 不存在");
                return false;
            }
        }
        return true;
    }
}
