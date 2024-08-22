import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class EnchantMenu {
    public Inventory components;
    public Player owner;
    public static final String TITLE = "§8[§b§l介质附魔§8]";


    public EnchantMenu(Player player) {
        components = Bukkit.createInventory(player, 45, TITLE);
        owner = player;

        ItemStack nothing = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE);
        ItemMeta nothingMeta = nothing.getItemMeta();
        nothingMeta.setDisplayName(" ");
        nothing.setItemMeta(nothingMeta);

        ItemStack info = new ItemStack(Material.PAPER);
        ItemMeta infoMeta = info.getItemMeta();
        infoMeta.setDisplayName("        §b§l>>>> 介质附魔 §r§b§l<<<<");
        infoMeta.setLore(List.of("§e§m===========[§e ❊ §e§m]===========",
                "§8▪ §7将想要附魔的物品置入上方空格",
                "§8▪ §7将附魔介质置入下方空格",
                "§8▪ §7选择右侧的魔咒进行附魔",
                "§8▪ §7从左至右依次为初级/中级/高级魔咒",
                "§e§m===========[§e ❊ §e§m]==========="));
        info.setItemMeta(infoMeta);



        for (int i = 0; i < 45; i++) {
            components.setItem(i, nothing);
        }
        components.setItem(10, new ItemStack(Material.AIR));
        components.setItem(19, info);
        components.setItem(28, new ItemStack(Material.AIR));

        ItemStack noEnchant = new ItemStack(Material.BOOK);
        ItemMeta noEnchantMeta = noEnchant.getItemMeta();
        noEnchantMeta.setDisplayName("§7未置入待附魔物品");
        noEnchant.setItemMeta(noEnchantMeta);
        components.setItem(21, noEnchant);
        components.setItem(23, noEnchant.add(1));
        components.setItem(25, noEnchant.add(1));
    }

    public void open() {
        owner.openInventory(components);
    }

}