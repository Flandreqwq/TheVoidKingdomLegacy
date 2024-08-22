package flandre.scarlet.thevoidkingdom.functions.item.tool;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ToolEffects implements Listener {
    public boolean IsItemLoreContain(ItemStack itemStack, String str) {
        List<String> itemLore = Objects.requireNonNullElse(itemStack.getItemMeta().getLore(), new ArrayList<String>());
        return itemLore.contains(str);
    }

    public boolean IsTool(ItemStack itemStack) {
        List<String> strings = List.of(
                "§c§l『镐』", "§c§l『斧』", "§c§l『锹』", "§c§l『锄』");
        for (String str : strings) {
            if (IsItemLoreContain(itemStack, str)) {
                return true;
            }
        }
        return false;
    }

    public void GiveHasteEffect(Player player, int tick, int level) {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.FAST_DIGGING, tick, level - 1);
        potionEffect.apply(player);
    }

    public void EnchantHaste(Player player, List<String> lores) {
        for (String lore : lores) {
            if (lore.contains("§7● §b")) {
                if (!lore.contains("振奋")) continue;
                String romanLevel = lore.substring(9);
                int seconds = 0;
                switch (romanLevel) {
                    case "I":
                        seconds = 1;
                        break;
                    case "II":
                        seconds = 3;
                        break;
                    case "III":
                        seconds = 6;
                        break;
                }
                GiveHasteEffect(player, 20 * seconds, 2);
                return;
            } else if (lore.contains("§7● §7")) {
                if (!lore.contains("振奋")) continue;
                String romanLevel = lore.substring(9);
                int seconds = 0;
                switch (romanLevel) {
                    case "I":
                        seconds = 1;
                        break;
                    case "II":
                        seconds = 3;
                        break;
                    case "III":
                        seconds = 6;
                        break;
                }
                GiveHasteEffect(player, 20 * seconds, 1);
                return;
            } else if (lore.contains("§7● §c")) {
                if (!lore.contains("振奋")) continue;
                String romanLevel = lore.substring(9);
                int seconds = 0;
                switch (romanLevel) {
                    case "I":
                        seconds = 1;
                        break;
                    case "II":
                        seconds = 3;
                        break;
                    case "III":
                        seconds = 6;
                        break;
                }
                GiveHasteEffect(player, 20 * seconds, 3);
                return;
            }
        }
    }

    @EventHandler
    public void OnBlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getType().equals(Material.AIR)) return;
        if (!IsTool(itemStack)) return;
        List<String> lores = itemStack.getItemMeta().getLore();
        assert lores != null;
        Random random = new Random();
        if (random.nextDouble() < 0.1) {
            EnchantHaste(player, lores);
        }
    }
}
