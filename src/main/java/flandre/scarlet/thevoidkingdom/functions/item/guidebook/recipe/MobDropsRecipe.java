package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.SwitchedItem;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.SwitchedItemManager;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;
import java.util.Set;
@Register(registerName = "怪物掉落物配方")
public record MobDropsRecipe(String mobDisplayName, String iconItemNamespaceId,
                             Set<String> dropItemNamespaceIds) implements VKRecipe {
    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_mob_drops:";
    }

    @Override
    public String getMenuDisplayName() {
        return "生物掉落物";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        SwitchedItemManager.create(inventory, new SwitchedItem(dropItemNamespaceIds, SwitchedItem.SwitchMode.NORMAL, 24));
        ItemStack icon;
        if (iconItemNamespaceId.equals("null")) {
            icon = new ItemStack(Material.SPAWNER);
            ItemMeta itemMeta = icon.getItemMeta();
            itemMeta.displayName(LegacyComponentSerializer.legacyAmpersand().deserialize(this.mobDisplayName()).decoration(TextDecoration.ITALIC, false));
            itemMeta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
            icon.setItemMeta(itemMeta);
        } else {
            ItemStack itemStack = ItemsAdderUtils.getItemStackNoCheck(iconItemNamespaceId);
            icon = new ItemStack(itemStack.getType());
            ItemMeta itemMeta = icon.getItemMeta();
            itemMeta.displayName(LegacyComponentSerializer.legacyAmpersand().deserialize(this.mobDisplayName()).decoration(TextDecoration.ITALIC, false));
            itemMeta.setCustomModelData(itemStack.getItemMeta().getCustomModelData());
            itemMeta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
            icon.setItemMeta(itemMeta);
        }
        inventory.setItem(20, icon);
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        return false;
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return dropItemNamespaceIds.contains(namespaceId);
    }

    @Override
    public boolean isValid() {
        String iconItemNamespaceId = this.iconItemNamespaceId;
        if (!Objects.equals(iconItemNamespaceId, "null") && !ItemsAdderUtils.isItemNamespaceIdExist(iconItemNamespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册生物掉落物配方失败,原因: 图标物品 " + iconItemNamespaceId + " 不存在");
            return false;
        }
        for (String dropItemNamespaceId : this.dropItemNamespaceIds()) {
            if (!dropItemNamespaceId.contains("EXP:") && !ItemsAdderUtils.isItemNamespaceIdExist(dropItemNamespaceId)) {
                TheVoidKingdom.LOGGER.warning("注册生物掉落物配方失败,原因: 掉落物品 " + dropItemNamespaceId + " 不存在");
                return false;
            }
        }
        return true;
    }
}
