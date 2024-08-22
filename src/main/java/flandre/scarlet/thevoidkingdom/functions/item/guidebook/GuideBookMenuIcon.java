package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import flandre.scarlet.thevoidkingdom.utils.game.menu.MenuIcon;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuideBookMenuIcon {
    public static final MenuIcon LAST = MenuIcon.create(Material.SHEARS, 4950193, 1, "<!italic><bold><gold>< 上一页", null);
    public static final MenuIcon NEXT = MenuIcon.create(Material.SHEARS, 4950194, 1, "<!italic><bold><gold>下一页 >", null);
    public static final MenuIcon CLOSE = MenuIcon.create(Material.SHEARS, 4950192, 1, "<!italic><bold><gold>返回", null);
    public static final MenuIcon SEARCH = MenuIcon.create(Material.SHEARS, 4950191, 1, "<!italic><bold><gold>搜索", null);
    public static final MenuIcon COOK_INFO = MenuIcon.create(Material.SHEARS, 4950136, 1, "<!italic><bold><gold>烧制信息", null);
    public static final MenuIcon MINING_INFO = MenuIcon.create(Material.SHEARS, 4950136, 1, "<!italic><bold><gold>掉落物信息", null);
    public static final MenuIcon CUTTING_BOARD = MenuIcon.create(Material.SHEARS, 4950403, 1, " ", null);
    public static final MenuIcon RIGHT_CLICK = MenuIcon.create(Material.SHEARS, 4950195, 1, "<!italic><bold><gold>对方块使用物品", null);
    public static final MenuIcon LEFT_CLICK = MenuIcon.create(Material.SHEARS, 4950196, 1, "<!italic><bold><gold>用物品攻击方块", null);
    public static final MenuIcon SHIFT_RIGHT_CLICK = MenuIcon.create(Material.SHEARS, 4950198, 1, "<!italic><bold><gold>潜行时对方块使用物品", null);
    public static final MenuIcon SHIFT_LEFT_CLICK = MenuIcon.create(Material.SHEARS, 4950199, 1, "<!italic><bold><gold>潜行时用物品攻击方块", null);

    public static ItemStack getCoalFuelIcon() {
        ItemStack itemStack = new ItemStack(Material.COAL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(TheVoidKingdomUtils.deserializeMiniMessage("<italic><white>任意燃料", false));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
