package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
@Register(registerName = "符卡包抽卡配方")
public record SpellcardPackRecipe(String cardBagNamespaceId,
                                  Set<String> dropItemNamespaceIds) implements VKRecipe {
    private static final List<Integer> slots = List.of(13, 14, 15, 16, 22, 23, 24, 25, 31, 32, 33, 34);

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_card_bag:";
    }

    @Override
    public String getMenuDisplayName() {
        return "符卡包抽卡";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        inventory.setItem(19, ItemsAdderUtils.getItemStackNoCheck(this.cardBagNamespaceId()));
        Iterator<Integer> iterator = slots.iterator();
        for (String namespaceId : this.dropItemNamespaceIds()) {
            if (iterator.hasNext()) {
                inventory.setItem(iterator.next(), ItemsAdderUtils.getItemStackNoCheck(namespaceId));
            } else {
                TheVoidKingdom.LOGGER.warning("符卡包 " + this.cardBagNamespaceId() + " 包含的符卡过多");
                break;
            }
        }
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        return cardBagNamespaceId.equals(namespaceId);
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return dropItemNamespaceIds.contains(namespaceId);
    }


    @Override
    public boolean isValid() {
        String cardBagNamespaceId = this.cardBagNamespaceId();
        if (!ItemsAdderUtils.isItemNamespaceIdExist(cardBagNamespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册符卡包抽卡配方失败,原因: 符卡包物品 " + cardBagNamespaceId + " 不存在");
            return false;
        }
        for (String dropItemNamespaceId : this.dropItemNamespaceIds()) {
            if (!dropItemNamespaceId.contains("EXP:") && !ItemsAdderUtils.isItemNamespaceIdExist(dropItemNamespaceId)) {
                TheVoidKingdom.LOGGER.warning("注册符卡包抽卡配方失败,原因: 掉落物品 " + dropItemNamespaceId + " 不存在");
                return false;
            }
        }
        return true;
    }
}
