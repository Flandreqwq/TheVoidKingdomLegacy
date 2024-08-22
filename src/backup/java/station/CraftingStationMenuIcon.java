package flandre.scarlet.thevoidkingdom.functions.station;

import de.tr7zw.nbtapi.NBTItem;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.MenuIcon;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftingStationMenuIcon {
    public static final MenuIcon VOID_CRAFTING_TABLE_LAST = MenuIcon.create(Material.SHEARS, 4950287, 1, "<!italic><bold><gold>< 上一页", null);
    public static final MenuIcon VOID_CRAFTING_TABLE_NEXT = MenuIcon.create(Material.SHEARS, 4950288, 1, "<!italic><bold><gold>下一页 >", null);


    public static ItemStack getTypeIcon(String namespaceId, String displayName, String stationId) {
        ItemStack itemStack = ItemsAdderUtils.getItemStack(namespaceId);
        ItemStack icon = new ItemStack(itemStack.getType());
        ItemMeta itemMeta = icon.getItemMeta();
        itemMeta.displayName(TheVoidKingdomUtils.deserializeMiniMessage(displayName, false));
        itemMeta.setCustomModelData(itemStack.getItemMeta().hasCustomModelData() ? itemStack.getItemMeta().getCustomModelData() : null);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        icon.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(icon);
        nbtItem.setString("stationId", stationId);
        return nbtItem.getItem();
    }
}
