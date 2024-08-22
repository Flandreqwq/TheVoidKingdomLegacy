import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepairEnchantLores implements Listener {
    public boolean IsItemLoreContain(ItemStack itemStack, String str) {
        if (itemStack.getType().isAir()) return false;
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> itemLore = Objects.requireNonNullElse(itemMeta.getLore(), new ArrayList<>());
        return itemLore.contains(str);
    }

    public boolean IsGems(ItemStack itemStack) {
        List<String> strings = List.of(
                "§c§l『宝石』", "§c§l『符石』", "§c§l『符卡』", "§c§l『符文』");
        for (String str : strings) {
            if (IsItemLoreContain(itemStack, str)) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void OnInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryView inv = player.getOpenInventory();
        ItemStack cursor = event.getCursor();
        int slot = event.getRawSlot();
        if (cursor == null) return;
        if (!IsGems(cursor)) return;
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) return;
        if (itemStack.getEnchantments().isEmpty()) return;
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lores = itemMeta.getLore();
        if (lores == null) return;
        List<String> enchantLores = new ArrayList<>();
        for (String lore : lores) {
            if (lore.contains("§7●")) {
                enchantLores.add(lore);
            }
        }
        if (enchantLores.isEmpty()) return;
        String name = itemMeta.getDisplayName();
        Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(TheVoidKingdom.class), () -> {
            ItemStack itemStack1 = inv.getItem(slot);
            assert itemStack1 != null;
            ItemMeta itemMeta1 = itemStack1.getItemMeta();
            if (!itemMeta1.getDisplayName().equals(name)) return;
            List<String> newLores = itemMeta1.getLore();
            assert newLores != null;
            for (String lore : newLores) {
                if (lore.contains("§7●")) return;
            }
            String line;
            int count = 0;
            int lineCount = 0;
            for (String lore : newLores) {
                count++;
                if (lore.contains("=")) {
                    line = lore;
                    lineCount++;
                    if (lineCount == 2) {
                        for (String enchantLore : enchantLores) {
                            newLores.add(count, enchantLore);
                            count++;
                        }
                        newLores.add(count, line);
                        break;
                    }
                }
            }
            itemStack1.setLore(newLores);
        }, 1);
    }
}
