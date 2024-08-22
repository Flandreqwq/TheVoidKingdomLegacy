package flandre.scarlet.thevoidkingdom.utils.game.menu;

import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuIcon {
    public static final Set<MenuIcon> MENU_ICONS = new HashSet<>();
    private final Material material;
    private final int customModelData;
    private final int amount;
    private final String displayName;
    private final List<String> lores;

    private final ItemStack itemStack;


    private ItemStack generateItem() {
        ItemStack icon = new ItemStack(material);
        icon.setAmount(amount);
        ItemMeta itemMeta = icon.getItemMeta();
        itemMeta.setCustomModelData(customModelData);
        itemMeta.displayName(TheVoidKingdomUtils.deserializeMiniMessage(displayName, false));
        if (lores != null) {
            itemMeta.lore(TheVoidKingdomUtils.deserializeList(lores, true));
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        icon.setItemMeta(itemMeta);
        return icon;
    }

    public MenuIcon(Material material, int customModelData, int amount, String displayName, List<String> lores) {
        this.material = material;
        this.customModelData = customModelData;
        this.amount = amount;
        this.displayName = displayName;
        this.lores = lores;
        this.itemStack = generateItem();
    }

    public static MenuIcon create(Material material, int customModelData, int amount, String displayName, List<String> lores) {
        MenuIcon menuIcon = new MenuIcon(material, customModelData, amount, displayName, lores);
        MENU_ICONS.add(menuIcon);
        return menuIcon;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
