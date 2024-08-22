import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;


public class EnchantItem implements Listener {
    public final Map<Player, Map<String, EnchantList>> playerEnchantListMap = new HashMap<>();
    public final Map<Player, Map<String, String[]>> playerHideMap = new HashMap<>();

    public boolean IsItemLoreContain(ItemStack itemStack, String str) {
        List<String> itemLore = Objects.requireNonNullElse(itemStack.getItemMeta().getLore(), new ArrayList<String>());
        return itemLore.contains(str);
    }

    public String RomanNumber(int number) {
        if (number == 1) {
            return "I";
        } else if (number == 2) {
            return "II";
        } else if (number == 3) {
            return "III";
        } else if (number == 4) {
            return "IV";
        } else if (number == 5) {
            return "V";
        } else if (number == 6) {
            return "VI";
        } else if (number == 7) {
            return "VII";
        } else if (number == 8) {
            return "VIII";
        } else if (number == 9) {
            return "IX";
        } else if (number == 10) {
            return "X";
        } else if (number == 11) {
            return "XI";
        } else if (number == 12) {
            return "XII";
        } else if (number == 13) {
            return "XIII";
        } else if (number == 14) {
            return "XIV";
        } else if (number == 15) {
            return "XV";
        } else if (number == 16) {
            return "XVI";
        } else if (number == 17) {
            return "XVII";
        } else if (number == 18) {
            return "XVIII";
        } else if (number == 19) {
            return "XIX";
        } else if (number == 20) {
            return "XX";
        }
        return "Overflow";
    }

    public boolean IsWeapon(ItemStack itemStack) {
        List<String> strings = List.of(
                "§c§l『短剑』", "§c§l『战锤』", "§c§l『镰刀』",
                "§c§l『弓』", "§c§l『弩』", "§c§l『吹箭筒』",
                "§c§l『法杖』", "§c§l『提灯』", "§c§l『法典』");
        for (String str : strings) {
            if (IsItemLoreContain(itemStack, str)) {
                return true;
            }
        }
        return false;
    }

    public boolean IsArmor(ItemStack itemStack) {
        List<String> strings = List.of(
                "§c§l『护甲』");
        for (String str : strings) {
            if (IsItemLoreContain(itemStack, str)) {
                return true;
            }
        }
        return false;
    }

    public boolean IsShield(ItemStack itemStack) {
        List<String> strings = List.of(
                "§c§l『壁垒』");
        for (String str : strings) {
            if (IsItemLoreContain(itemStack, str)) {
                return true;
            }
        }
        return false;
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

    public int GetDamageEnchantMaxLevel(ItemStack itemStack) {
        String displayName = itemStack.getItemMeta().getDisplayName();
        if (displayName.contains("§a青铜")) {
            return 3;
        } else if (displayName.contains("§a钢")) {
            return 4;
        } else if (displayName.contains("§b魔钢")) {
            return 5;
        } else if (displayName.contains("§b源质钢")) {
            return 6;
        } else if (displayName.contains("§a皮革")) {
            return 3;
        } else if (displayName.contains("§a狂野")) {
            return 4;
        } else if (displayName.contains("§b狩猎")) {
            return 5;
        } else if (displayName.contains("§b华丽")) {
            return 6;
        } else if (displayName.contains("§a紫晶")) {
            return 3;
        } else if (displayName.contains("§a魔晶")) {
            return 4;
        } else if (displayName.contains("§b魔能")) {
            return 5;
        } else if (displayName.contains("§b天界")) {
            return 6;
        }
        return 0;
    }

    public String GetDamageEnchantName(ItemStack itemStack) {
        if (IsItemLoreContain(itemStack, "§c§l『战锤』")) {
            return "沉重";
        } else if (IsItemLoreContain(itemStack, "§c§l『短剑』")) {
            return "锋利";
        } else if (IsItemLoreContain(itemStack, "§c§l『镰刀』")) {
            return "利刃";
        } else if (IsItemLoreContain(itemStack, "§c§l『弩』")) {
            return "穿透";
        } else if (IsItemLoreContain(itemStack, "§c§l『吹箭筒』")) {
            return "锋矢";
        } else if (IsItemLoreContain(itemStack, "§c§l『弓』")) {
            return "力量";
        } else if (IsItemLoreContain(itemStack, "§c§l『法杖』")) {
            return "魔涌";
        } else if (IsItemLoreContain(itemStack, "§c§l『提灯』")) {
            return "魔光";
        } else if (IsItemLoreContain(itemStack, "§c§l『法典』")) {
            return "咒术";
        }
        return "UNKNOWN";
    }

    public String GetSpecialEnchantName(ItemStack itemStack) {
        String displayName = itemStack.getItemMeta().getDisplayName();
        if (displayName.contains("§a青铜")) {
            return "生命";
        } else if (displayName.contains("§a钢")) {
            return "生命";
        } else if (displayName.contains("§b魔钢")) {
            return "生命";
        } else if (displayName.contains("§b源质钢")) {
            return "生命";
        } else if (displayName.contains("§a皮革")) {
            return "轻盈";
        } else if (displayName.contains("§a狂野")) {
            return "轻盈";
        } else if (displayName.contains("§b狩猎")) {
            return "轻盈";
        } else if (displayName.contains("§b华丽")) {
            return "轻盈";
        } else if (displayName.contains("§a紫晶")) {
            return "充沛";
        } else if (displayName.contains("§a魔晶")) {
            return "充沛";
        } else if (displayName.contains("§b魔能")) {
            return "充沛";
        } else if (displayName.contains("§b天界")) {
            return "充沛";
        }
        return "UNKNOWN";
    }

    public int GetRandomLevelByProbability(double[] probability) {
        Random random = new Random();
        double rand = random.nextDouble();
        double sum = 0.0;
        for (int i = 0; i < probability.length; i++) {
            sum += probability[i];
            if (rand < sum) {
                return i;
            }
        }
        return 0;
    }

    public int GetRandomLevelByWeight(int maxLevel, int[] weights) {
        Random random = new Random();
        for (int i = maxLevel + 1; i < weights.length; i++) {
            weights[i] = 0;
        }
        int sum = 0;
        for (int weight : weights) {
            sum = sum + weight;
        }
        int rand = random.nextInt(sum);
        int sumWeight = 0;
        for (int i = 0; sumWeight < sum; i++) {
            sumWeight += weights[i];
            if (rand < sumWeight) {
                return i;
            }
        }
        return 0;
    }

    public int[] GetRandomChoiceLowForWeapon() {
        int durabilityLevel = GetRandomLevelByProbability(new double[]{0.5, 0.4, 0.2, 0.0});
        int damageLevel = GetRandomLevelByWeight(2, new int[]{5, 4, 1});
        if (durabilityLevel == 0 && damageLevel == 0) {
            Random random = new Random();
            if (random.nextBoolean()) {
                durabilityLevel = 1;
            } else {
                damageLevel = 1;
            }
        }
        return new int[]{durabilityLevel, damageLevel, 0, 0};
    }

    public int[] GetRandomChoiceMiddleForWeapon(ItemStack itemStack) {
        int durabilityLevel = GetRandomLevelByProbability(new double[]{0.2, 0.4, 0.3, 0.1});
        int damageLevel = GetRandomLevelByWeight(Math.max(2, GetDamageEnchantMaxLevel(itemStack) - 2), new int[]{500, 500, 500, 200, 100, 50, 10, 1});
        Random random = new Random();
        int criticalChanceLevel;
        int criticalDamageLevel;
        if (random.nextBoolean()) {
            criticalChanceLevel = 0;
            criticalDamageLevel = GetRandomLevelByProbability(new double[]{0.5, 0.3, 0.15, 0.05});
        } else {
            criticalChanceLevel = GetRandomLevelByProbability(new double[]{0.5, 0.3, 0.15, 0.05});
            criticalDamageLevel = 0;
        }
        if (durabilityLevel == 0 && damageLevel == 0 && criticalChanceLevel == 0 && criticalDamageLevel == 0) {
            if (random.nextBoolean()) {
                durabilityLevel = 1;
            } else {
                damageLevel = 1;
            }
        }
        return new int[]{durabilityLevel, damageLevel, criticalChanceLevel, criticalDamageLevel};
    }

    public int[] GetRandomChoiceHighForWeapon(ItemStack itemStack) {
        int durabilityLevel = GetRandomLevelByProbability(new double[]{0.2, 0.3, 0.35, 0.15});
        int damageLevel = GetRandomLevelByWeight(GetDamageEnchantMaxLevel(itemStack), new int[]{500, 500, 500, 200, 100, 50, 10, 1});
        Random random = new Random();
        int criticalChanceLevel;
        int criticalDamageLevel;
        if (random.nextBoolean()) {
            criticalChanceLevel = 0;
            criticalDamageLevel = GetRandomLevelByProbability(new double[]{0.3, 0.3, 0.3, 0.1});
        } else {
            criticalChanceLevel = GetRandomLevelByProbability(new double[]{0.3, 0.3, 0.3, 0.1});
            criticalDamageLevel = 0;
        }
        if (durabilityLevel == 0 && damageLevel == 0 && criticalChanceLevel == 0 && criticalDamageLevel == 0) {
            if (random.nextBoolean()) {
                durabilityLevel = 1;
            } else {
                damageLevel = 1;
            }
        }
        return new int[]{durabilityLevel, damageLevel, criticalChanceLevel, criticalDamageLevel};
    }

    public String HideEnchantForWeapon(int[] choice, String damageEnchantName) {
        Random random = new Random();
        int amount = 0;
        for (int num : choice) {
            if (num != 0) {
                amount++;
            }
        }
        int select = random.nextInt(amount) + 1;
        int index = -1;
        for (int num : choice) {
            index++;
            if (num == 0) continue;
            select--;
            if (select == 0) break;
        }
        if (index == 0) {
            return "§f耐久 " + RomanNumber(choice[0]) + " §f...";
        } else if (index == 1) {
            return "§f" + damageEnchantName + " " + RomanNumber(choice[1]) + " §f...";
        } else if (index == 2) {
            return "§f幸运 " + RomanNumber(choice[2]) + " §f...";
        } else if (index == 3) {
            return "§f会心 " + RomanNumber(choice[3]) + " §f...";
        }
        return "§cError";
    }

    public EnchantList GetEnchantResultForWeapon(Player player, ItemStack itemStack) {
        String name = itemStack.getItemMeta().getDisplayName();
        playerEnchantListMap.computeIfAbsent(player, k -> new HashMap<>());
        if (playerEnchantListMap.get(player).get(name) == null) {
            playerEnchantListMap.get(player).put(name, new EnchantList(GetRandomChoiceLowForWeapon(),
                    GetRandomChoiceMiddleForWeapon(itemStack), GetRandomChoiceHighForWeapon(itemStack)));
        }
        return playerEnchantListMap.get(player).get(name);
    }

    public String[] GetEnchantHideResultForWeapon(Player player, ItemStack itemStack, EnchantList enchantList) {
        String name = itemStack.getItemMeta().getDisplayName();
        playerHideMap.computeIfAbsent(player, k -> new HashMap<>());
        if (playerHideMap.get(player).get(name) == null) {
            String damageEnchantName = GetDamageEnchantName(itemStack);
            playerHideMap.get(player).put(name, new String[]{HideEnchantForWeapon(enchantList.getLevelLow(), damageEnchantName),
                    HideEnchantForWeapon(enchantList.getLevelMiddle(), damageEnchantName),
                    HideEnchantForWeapon(enchantList.getLevelHigh(), damageEnchantName)});
        }
        return playerHideMap.get(player).get(name);
    }

    public int[] GetRandomChoiceLowForArmor() {
        int durabilityLevel = GetRandomLevelByProbability(new double[]{0.5, 0.4, 0.2, 0.0});
        int armorLevel = GetRandomLevelByWeight(2, new int[]{5, 4, 1});
        if (durabilityLevel == 0 && armorLevel == 0) {
            Random random = new Random();
            if (random.nextBoolean()) {
                durabilityLevel = 1;
            } else {
                armorLevel = 1;
            }
        }
        return new int[]{durabilityLevel, armorLevel, 0, 0, 0};
    }

    public int[] GetRandomChoiceMiddleForArmor(ItemStack itemStack) {
        int durabilityLevel = GetRandomLevelByProbability(new double[]{0.2, 0.4, 0.3, 0.1});
        int armorLevel = GetRandomLevelByWeight(Math.max(2, GetDamageEnchantMaxLevel(itemStack) - 2), new int[]{500, 500, 500, 200, 100, 50, 10, 1});
        Random random = new Random();
        int criticalChanceLevel;
        int criticalDamageLevel;
        if (random.nextBoolean()) {
            criticalChanceLevel = 0;
            criticalDamageLevel = GetRandomLevelByProbability(new double[]{0.5, 0.34, 0.15, 0.01});
        } else {
            criticalChanceLevel = GetRandomLevelByProbability(new double[]{0.5, 0.34, 0.15, 0.01});
            criticalDamageLevel = 0;
        }
        int specialLevel = GetRandomLevelByProbability(new double[]{0.5, 0.4, 0.1, 0});
        if (durabilityLevel == 0 && armorLevel == 0 && criticalChanceLevel == 0 && criticalDamageLevel == 0 && specialLevel == 0) {
            if (random.nextBoolean()) {
                durabilityLevel = 1;
            } else {
                armorLevel = 1;
            }
        }
        return new int[]{durabilityLevel, armorLevel, criticalChanceLevel, criticalDamageLevel, specialLevel};
    }

    public int[] GetRandomChoiceHighForArmor(ItemStack itemStack) {
        int durabilityLevel = GetRandomLevelByProbability(new double[]{0.2, 0.3, 0.35, 0.15});
        int armorLevel = GetRandomLevelByWeight(GetDamageEnchantMaxLevel(itemStack), new int[]{500, 500, 500, 200, 100, 50, 10, 1});
        Random random = new Random();
        int criticalChanceLevel;
        int criticalDamageLevel;
        if (random.nextBoolean()) {
            criticalChanceLevel = 0;
            criticalDamageLevel = GetRandomLevelByProbability(new double[]{0.3, 0.3, 0.3, 0.1});
        } else {
            criticalChanceLevel = GetRandomLevelByProbability(new double[]{0.3, 0.3, 0.3, 0.1});
            criticalDamageLevel = 0;
        }
        int specialLevel = GetRandomLevelByProbability(new double[]{0.4, 0.4, 0.15, 0.05});
        if (durabilityLevel == 0 && armorLevel == 0 && criticalChanceLevel == 0 && criticalDamageLevel == 0 && specialLevel == 0) {
            if (random.nextBoolean()) {
                durabilityLevel = 1;
            } else {
                armorLevel = 1;
            }
        }
        return new int[]{durabilityLevel, armorLevel, criticalChanceLevel, criticalDamageLevel, specialLevel};
    }

    public String HideEnchantForArmor(int[] choice, String specialEnchantName) {
        Random random = new Random();
        int amount = 0;
        for (int num : choice) {
            if (num != 0) {
                amount++;
            }
        }
        int select = random.nextInt(amount) + 1;
        int index = -1;
        for (int num : choice) {
            index++;
            if (num == 0) continue;
            select--;
            if (select == 0) break;
        }
        if (index == 0) {
            return "§f耐久 " + RomanNumber(choice[0]) + " §f...";
        } else if (index == 1) {
            return "§f保护 " + RomanNumber(choice[1]) + " §f...";
        } else if (index == 2) {
            return "§f幸运 " + RomanNumber(choice[2]) + " §f...";
        } else if (index == 3) {
            return "§f会心 " + RomanNumber(choice[3]) + " §f...";
        } else if (index == 4) {
            return "§f" + specialEnchantName + " " + RomanNumber(choice[4]) + " §f...";
        }
        return "§cError";
    }

    public EnchantList GetEnchantResultForArmor(Player player, ItemStack itemStack) {
        String name = itemStack.getItemMeta().getDisplayName();
        playerEnchantListMap.computeIfAbsent(player, k -> new HashMap<>());
        if (playerEnchantListMap.get(player).get(name) == null) {
            playerEnchantListMap.get(player).put(name, new EnchantList(GetRandomChoiceLowForArmor(),
                    GetRandomChoiceMiddleForArmor(itemStack), GetRandomChoiceHighForArmor(itemStack)));
        }
        return playerEnchantListMap.get(player).get(name);
    }

    public String[] GetEnchantHideResultForArmor(Player player, ItemStack itemStack, EnchantList enchantList) {
        String name = itemStack.getItemMeta().getDisplayName();
        playerHideMap.computeIfAbsent(player, k -> new HashMap<>());
        if (playerHideMap.get(player).get(name) == null) {
            String specialEnchantName = GetSpecialEnchantName(itemStack);
            playerHideMap.get(player).put(name, new String[]{HideEnchantForArmor(enchantList.getLevelLow(), specialEnchantName),
                    HideEnchantForArmor(enchantList.getLevelMiddle(), specialEnchantName),
                    HideEnchantForArmor(enchantList.getLevelHigh(), specialEnchantName)});
        }
        return playerHideMap.get(player).get(name);
    }

    public int[] GetRandomChoiceLowForTool() {
        int durabilityLevel = GetRandomLevelByProbability(new double[]{0.5, 0.4, 0.2, 0.0});
        int efficiencyLevel = GetRandomLevelByProbability(new double[]{0.25, 0.25, 0.25, 0.25, 0.0, 0.0});
        if (durabilityLevel == 0 && efficiencyLevel == 0) {
            Random random = new Random();
            if (random.nextBoolean()) {
                durabilityLevel = 1;
            } else {
                efficiencyLevel = 1;
            }
        }
        return new int[]{durabilityLevel, efficiencyLevel, 0};
    }

    public int[] GetRandomChoiceMiddleForTool() {
        int durabilityLevel = GetRandomLevelByProbability(new double[]{0.5, 0.4, 0.2, 0.0});
        int efficiencyLevel = GetRandomLevelByProbability(new double[]{0.25, 0.25, 0.25, 0.25, 0.0, 0.0});
        int hasteLevel = GetRandomLevelByProbability(new double[]{0.5, 0.34, 0.15, 0.01});
        Random random = new Random();
        if (durabilityLevel == 0 && efficiencyLevel == 0 && hasteLevel == 0) {
            if (random.nextBoolean()) {
                durabilityLevel = 1;
            } else {
                efficiencyLevel = 1;
            }
        }
        return new int[]{durabilityLevel, efficiencyLevel, hasteLevel};
    }

    public int[] GetRandomChoiceHighForTool() {
        int durabilityLevel = GetRandomLevelByProbability(new double[]{0.2, 0.3, 0.35, 0.15});
        int efficiencyLevel = GetRandomLevelByProbability(new double[]{0.1, 0.1, 0.2, 0.3, 0.2, 0.1});
        int hasteLevel = GetRandomLevelByProbability(new double[]{0.4, 0.3, 0.25, 0.05});
        Random random = new Random();
        if (durabilityLevel == 0 && efficiencyLevel == 0 && hasteLevel == 0) {
            if (random.nextBoolean()) {
                durabilityLevel = 1;
            } else {
                efficiencyLevel = 1;
            }
        }
        return new int[]{durabilityLevel, efficiencyLevel, hasteLevel};
    }

    public String HideEnchantForTool(int[] choice) {
        Random random = new Random();
        int amount = 0;
        for (int num : choice) {
            if (num != 0) {
                amount++;
            }
        }
        int select = random.nextInt(amount) + 1;
        int index = -1;
        for (int num : choice) {
            index++;
            if (num == 0) continue;
            select--;
            if (select == 0) break;
        }
        if (index == 0) {
            return "§f耐久 " + RomanNumber(choice[0]) + " §f...";
        } else if (index == 1) {
            return "§f效率 " + RomanNumber(choice[1]) + " §f...";
        } else if (index == 2) {
            return "§f振奋 " + RomanNumber(choice[2]) + " §f...";
        }
        return "§cError";
    }

    public EnchantList GetEnchantResultForTool(Player player, ItemStack itemStack) {
        String name = itemStack.getItemMeta().getDisplayName();
        playerEnchantListMap.computeIfAbsent(player, k -> new HashMap<>());
        if (playerEnchantListMap.get(player).get(name) == null) {
            playerEnchantListMap.get(player).put(name, new EnchantList(GetRandomChoiceLowForTool(),
                    GetRandomChoiceMiddleForTool(), GetRandomChoiceHighForTool()));
        }
        return playerEnchantListMap.get(player).get(name);
    }

    public String[] GetEnchantHideResultForTool(Player player, ItemStack itemStack, EnchantList enchantList) {
        String name = itemStack.getItemMeta().getDisplayName();
        playerHideMap.computeIfAbsent(player, k -> new HashMap<>());
        if (playerHideMap.get(player).get(name) == null) {
            String specialEnchantName = GetSpecialEnchantName(itemStack);
            playerHideMap.get(player).put(name, new String[]{HideEnchantForTool(enchantList.getLevelLow()),
                    HideEnchantForTool(enchantList.getLevelMiddle()),
                    HideEnchantForTool(enchantList.getLevelHigh())});
        }
        return playerHideMap.get(player).get(name);
    }

    public String GetConsumableName(ItemStack itemStack) {
        String displayName = itemStack.getItemMeta().getDisplayName();
        if (displayName.contains("§a青铜")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§a钢")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§b魔钢")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§b源质钢")) {
            return "§b虚空精晶";
        } else if (displayName.contains("§a皮革")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§a狂野")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§b狩猎")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§b华丽")) {
            return "§b虚空精晶";
        } else if (displayName.contains("§a紫晶")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§a魔晶")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§b魔能")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§b天界")) {
            return "§b虚空精晶";
        } else if (displayName.contains("§f圆石")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§f复合石")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§b破坏者")) {
            return "§a虚空晶体";
        } else if (displayName.contains("§b摧毁者")) {
            return "§a虚空晶体";
        }
        return "UNKNOWN";
    }

    public int[] GetConsumableAmount(ItemStack itemStack) {
        String displayName = itemStack.getItemMeta().getDisplayName();
        if (displayName.contains("§a青铜")) {
            return new int[]{1, 2, 3};
        } else if (displayName.contains("§a钢")) {
            return new int[]{2, 3, 4};
        } else if (displayName.contains("§b魔钢")) {
            return new int[]{2, 4, 6};
        } else if (displayName.contains("§b源质钢")) {
            return new int[]{3, 6, 9};
        } else if (displayName.contains("§a皮革")) {
            return new int[]{1, 2, 3};
        } else if (displayName.contains("§a狂野")) {
            return new int[]{2, 3, 4};
        } else if (displayName.contains("§b狩猎")) {
            return new int[]{2, 4, 6};
        } else if (displayName.contains("§b华丽")) {
            return new int[]{3, 6, 9};
        } else if (displayName.contains("§a紫晶")) {
            return new int[]{1, 2, 3};
        } else if (displayName.contains("§a魔晶")) {
            return new int[]{2, 3, 4};
        } else if (displayName.contains("§b魔能")) {
            return new int[]{2, 4, 6};
        } else if (displayName.contains("§b天界")) {
            return new int[]{3, 6, 9};
        } else if (displayName.contains("§f圆石")) {
            return new int[]{1, 2, 3};
        } else if (displayName.contains("§f复合石")) {
            return new int[]{1, 2, 3};
        } else if (displayName.contains("§b破坏者")) {
            return new int[]{2, 4, 6};
        } else if (displayName.contains("§b摧毁者")) {
            return new int[]{2, 4, 6};
        }
        return new int[]{495, 495, 495};
    }

    public int[] GetNeedLevel(ItemStack itemStack) {
        String displayName = itemStack.getItemMeta().getDisplayName();
        if (displayName.contains("§a青铜")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§a钢")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§b魔钢")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§b源质钢")) {
            return new int[]{20, 40, 60};
        } else if (displayName.contains("§a皮革")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§a狂野")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§b狩猎")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§b华丽")) {
            return new int[]{20, 40, 60};
        } else if (displayName.contains("§a紫晶")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§a魔晶")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§b魔能")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§b天界")) {
            return new int[]{20, 40, 60};
        } else if (displayName.contains("§f圆石")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§f复合石")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§b破坏者")) {
            return new int[]{10, 20, 30};
        } else if (displayName.contains("§b摧毁者")) {
            return new int[]{10, 20, 30};
        }
        return new int[]{495, 495, 495};
    }

    public boolean CheckConditions(Player player, int needLevel, String consumableName, int amount) {
        ItemStack itemStack = player.getOpenInventory().getItem(28);
        if (itemStack == null) {
            player.sendMessage("§8[§d§l虚空之国§8] §e该物品需要 [" + consumableName + "§e] 作为附魔介质");
            return false;
        }
        if (!itemStack.getItemMeta().getDisplayName().equals(consumableName)) {
            player.sendMessage("§8[§d§l虚空之国§8] §e该物品需要 [" + consumableName + "§e] 作为附魔介质");
            return false;
        }
        if (itemStack.getAmount() < amount) {
            player.sendMessage("§8[§d§l虚空之国§8] §c附魔介质不足");
            return false;
        }
        if (player.getLevel() < needLevel) {
            player.sendMessage("§8[§d§l虚空之国§8] §c附魔经验不足");
            return false;
        }
        return true;
    }

    public void TakeConditionsAndEnchantItem(Player player, int needLevel, int amount, int[] enchantResult, String EnchantName) {
        ItemStack itemStack = player.getOpenInventory().getItem(10);
        ItemStack consumable = player.getOpenInventory().getItem(28);
        assert consumable != null;
        consumable.setAmount(consumable.getAmount() - amount);
        player.setLevel(player.getLevel() - needLevel);
        if (IsWeapon(itemStack)) {
            EnchantWeapon(player, enchantResult, EnchantName);
        } else if (IsArmor(itemStack)) {
            EnchantArmor(player, enchantResult, EnchantName);
        } else if (IsTool(itemStack)) {
            EnchantTool(player, enchantResult);
        } else {

        }
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
    }

    public String GetRandomEnchantColor() {
        Random random = new Random();
        double rand = random.nextDouble();
        if (rand < 0.3) {
            return "§7";
        } else if (rand < 0.8) {
            return "§b";
        } else {
            return "§c";
        }
    }

    public void EnchantWeapon(Player player, int[] enchantResult, String damageEnchantName) {
        InventoryView inventoryView = player.getOpenInventory();
        if (!inventoryView.getTitle().equals(EnchantMenu.TITLE)) {
            return;
        }
        ItemStack itemStack = inventoryView.getItem(10);
        assert itemStack != null;
        List<String> lores = itemStack.getItemMeta().getLore();
        String line;
        int count = 0;
        int lineCount = 0;
        assert lores != null;
        for (String lore : lores) {
            count++;
            if (lore.contains("=")) {
                line = lore;
                lineCount++;
                if (lineCount == 2) {
                    int enchantLoreCount = 0;
                    for (int i = 0; i < 4; i++) {
                        String enchantName;
                        if (i == 0) {
                            enchantName = "耐久";
                        } else if (i == 1) {
                            enchantName = damageEnchantName;
                        } else if (i == 2) {
                            enchantName = "幸运";
                        } else {
                            enchantName = "会心";
                        }
                        if (enchantResult[i] != 0) {
                            String enchantLore = "§7● " + GetRandomEnchantColor() + enchantName + " " + RomanNumber(enchantResult[i]);
                            lores.add(count + enchantLoreCount, enchantLore);
                            enchantLoreCount++;
                        }
                        if (i == 3) {
                            lores.add(count + enchantLoreCount, line);
                        }
                    }
                    break;
                }
            }
        }
        itemStack.setLore(lores);
        itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, enchantResult[0]);
        playerEnchantListMap.get(player).clear();
        if (playerHideMap.get(player) != null) {
            playerHideMap.get(player).clear();
        }
    }

    public void EnchantArmor(Player player, int[] enchantResult, String specialEnchantName) {
        InventoryView inventoryView = player.getOpenInventory();
        if (!inventoryView.getTitle().equals(EnchantMenu.TITLE)) {
            return;
        }
        ItemStack itemStack = inventoryView.getItem(10);
        assert itemStack != null;
        List<String> lores = itemStack.getItemMeta().getLore();
        String line;
        int count = 0;
        int lineCount = 0;
        assert lores != null;
        for (String lore : lores) {
            count++;
            if (lore.contains("=")) {
                line = lore;
                lineCount++;
                if (lineCount == 2) {
                    int enchantLoreCount = 0;
                    for (int i = 0; i < 5; i++) {
                        String enchantName;
                        if (i == 0) {
                            enchantName = "耐久";
                        } else if (i == 1) {
                            enchantName = "保护";
                        } else if (i == 2) {
                            enchantName = "幸运";
                        } else if (i == 3) {
                            enchantName = "会心";
                        } else {
                            enchantName = specialEnchantName;
                        }
                        if (enchantResult[i] != 0) {
                            String enchantLore = "§7● " + GetRandomEnchantColor() + enchantName + " " + RomanNumber(enchantResult[i]);
                            lores.add(count + enchantLoreCount, enchantLore);
                            enchantLoreCount++;
                        }
                        if (i == 4) {
                            lores.add(count + enchantLoreCount, line);
                        }
                    }
                    break;
                }
            }
        }
        itemStack.setLore(lores);
        itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, enchantResult[0]);
        playerEnchantListMap.get(player).clear();
        if (playerHideMap.get(player) != null) {
            playerHideMap.get(player).clear();
        }
    }

    public void EnchantTool(Player player, int[] enchantResult) {
        InventoryView inventoryView = player.getOpenInventory();
        if (!inventoryView.getTitle().equals(EnchantMenu.TITLE)) {
            return;
        }
        ItemStack itemStack = inventoryView.getItem(10);
        assert itemStack != null;
        List<String> lores = itemStack.getItemMeta().getLore();
        String line;
        int count = 0;
        int lineCount = 0;
        assert lores != null;
        for (String lore : lores) {
            count++;
            if (lore.contains("=")) {
                line = lore;
                lineCount++;
                if (lineCount == 2) {
                    int enchantLoreCount = 0;
                    for (int i = 0; i < 3; i++) {
                        String enchantName;
                        if (i == 0) {
                            enchantName = "耐久";
                        } else if (i == 1) {
                            enchantName = "效率";
                        } else {
                            enchantName = "振奋";
                        }
                        if (enchantResult[i] != 0) {
                            String enchantLore = "§7● " + GetRandomEnchantColor() + enchantName + " " + RomanNumber(enchantResult[i]);
                            lores.add(count + enchantLoreCount, enchantLore);
                            enchantLoreCount++;
                        }
                        if (i == 2) {
                            lores.add(count + enchantLoreCount, line);
                        }
                    }
                    break;
                }
            }
        }
        itemStack.setLore(lores);
        itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, enchantResult[0]);
        itemStack.addUnsafeEnchantment(Enchantment.DIG_SPEED, enchantResult[1]);
        playerEnchantListMap.get(player).clear();
        if (playerHideMap.get(player) != null) {
            playerHideMap.get(player).clear();
        }
    }

    public ItemStack GetEnchantMenuButton(String name, List<String> lore, int amount) {
        ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public void ChangeEnchantList(Player player) {
        InventoryView inventoryView = player.getOpenInventory();
        if (!inventoryView.getTitle().equals(EnchantMenu.TITLE)) {
            return;
        }
        ItemStack itemStack = inventoryView.getItem(10);
        if (itemStack == null) {
            ItemStack noEnchant = new ItemStack(Material.BOOK);
            ItemMeta noEnchantMeta = noEnchant.getItemMeta();
            noEnchantMeta.setDisplayName("§7未置入待附魔物品");
            noEnchant.setItemMeta(noEnchantMeta);
            inventoryView.setItem(21, noEnchant);
            inventoryView.setItem(23, noEnchant.add(1));
            inventoryView.setItem(25, noEnchant.add(1));
            return;
        }
        if (!itemStack.getEnchantments().isEmpty()) {
            ItemStack noEnchant = new ItemStack(Material.BOOK);
            ItemMeta noEnchantMeta = noEnchant.getItemMeta();
            noEnchantMeta.setDisplayName("§c该物品无可用魔咒");
            noEnchant.setItemMeta(noEnchantMeta);
            inventoryView.setItem(21, noEnchant);
            inventoryView.setItem(23, noEnchant.add(1));
            inventoryView.setItem(25, noEnchant.add(1));
            return;
        }
        if (IsWeapon(itemStack)) {
            EnchantList enchantList = GetEnchantResultForWeapon(player, itemStack);
            String[] choices = GetEnchantHideResultForWeapon(player, itemStack, enchantList);
            String consumableName = GetConsumableName(itemStack);
            int[] amounts = GetConsumableAmount(itemStack);
            int[] levels = GetNeedLevel(itemStack);
            inventoryView.setItem(21, GetEnchantMenuButton(choices[0],
                    List.of("", "§e消耗:", "§e[" + consumableName + "§e] × " + amounts[0], "§e[经验] × " + levels[0] + "级"), 1));
            inventoryView.setItem(23, GetEnchantMenuButton(choices[1],
                    List.of("", "§e消耗:", "§e[" + consumableName + "§e] × " + amounts[1], "§e[经验] × " + levels[1] + "级"), 2));
            inventoryView.setItem(25, GetEnchantMenuButton(choices[2],
                    List.of("", "§e消耗:", "§e[" + consumableName + "§e] × " + amounts[2], "§e[经验] × " + levels[2] + "级"), 3));
        } else if (IsArmor(itemStack)) {
            EnchantList enchantList = GetEnchantResultForArmor(player, itemStack);
            String[] choices = GetEnchantHideResultForArmor(player, itemStack, enchantList);
            String consumableName = GetConsumableName(itemStack);
            int[] amounts = GetConsumableAmount(itemStack);
            int[] levels = GetNeedLevel(itemStack);
            inventoryView.setItem(21, GetEnchantMenuButton(choices[0],
                    List.of("", "§e消耗:", "§e[" + consumableName + "§e] × " + amounts[0], "§e[经验] × " + levels[0] + "级"), 1));
            inventoryView.setItem(23, GetEnchantMenuButton(choices[1],
                    List.of("", "§e消耗:", "§e[" + consumableName + "§e] × " + amounts[1], "§e[经验] × " + levels[1] + "级"), 2));
            inventoryView.setItem(25, GetEnchantMenuButton(choices[2],
                    List.of("", "§e消耗:", "§e[" + consumableName + "§e] × " + amounts[2], "§e[经验] × " + levels[2] + "级"), 3));
        } else if (IsTool(itemStack)) {
            EnchantList enchantList = GetEnchantResultForTool(player, itemStack);
            String[] choices = GetEnchantHideResultForTool(player, itemStack, enchantList);
            String consumableName = GetConsumableName(itemStack);
            int[] amounts = GetConsumableAmount(itemStack);
            int[] levels = GetNeedLevel(itemStack);
            inventoryView.setItem(21, GetEnchantMenuButton(choices[0],
                    List.of("", "§e消耗:", "§e[" + consumableName + "§e] × " + amounts[0], "§e[经验] × " + levels[0] + "级"), 1));
            inventoryView.setItem(23, GetEnchantMenuButton(choices[1],
                    List.of("", "§e消耗:", "§e[" + consumableName + "§e] × " + amounts[1], "§e[经验] × " + levels[1] + "级"), 2));
            inventoryView.setItem(25, GetEnchantMenuButton(choices[2],
                    List.of("", "§e消耗:", "§e[" + consumableName + "§e] × " + amounts[2], "§e[经验] × " + levels[2] + "级"), 3));
        } else if (IsShield(itemStack)) {

        } else {
            ItemStack noEnchant = new ItemStack(Material.BOOK);
            ItemMeta noEnchantMeta = noEnchant.getItemMeta();
            noEnchantMeta.setDisplayName("§c该物品无可用魔咒");
            noEnchant.setItemMeta(noEnchantMeta);
            inventoryView.setItem(21, noEnchant);
            inventoryView.setItem(23, noEnchant.add(1));
            inventoryView.setItem(25, noEnchant.add(1));
            return;
        }
    }

    @EventHandler
    public void OnClickMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryView inv = player.getOpenInventory();
        if (inv.getTitle().equals(EnchantMenu.TITLE)) {
            int clickSlot = event.getRawSlot();
            if (clickSlot >= 0 && clickSlot < 45 && clickSlot != 10 && clickSlot != 28) {
                event.setCancelled(true);
            }
            Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(TheVoidKingdom.class), () -> {
                ChangeEnchantList(player);
                ItemStack itemStack = inv.getItem(10);
                if (itemStack == null) {
                    return;
                } else if (!itemStack.getEnchantments().isEmpty()) {
                    return;
                } else if (!IsArmor(itemStack) && !IsWeapon(itemStack) && !IsTool(itemStack) && !IsShield(itemStack)) {
                    return;
                }
                String consumableName = GetConsumableName(itemStack);
                int[] amounts = GetConsumableAmount(itemStack);
                int[] levels = GetNeedLevel(itemStack);
                if (IsWeapon(itemStack)) {
                    if (clickSlot == 21) {
                        if (CheckConditions(player, levels[0], consumableName, amounts[0])) {
                            int[] enchantResult = GetEnchantResultForWeapon(player, itemStack).getLevelLow();
                            TakeConditionsAndEnchantItem(player, levels[0], amounts[0], enchantResult, GetDamageEnchantName(itemStack));
                        }
                    } else if (clickSlot == 23) {
                        if (CheckConditions(player, levels[1], consumableName, amounts[1])) {
                            int[] enchantResult = GetEnchantResultForWeapon(player, itemStack).getLevelMiddle();
                            TakeConditionsAndEnchantItem(player, levels[1], amounts[1], enchantResult, GetDamageEnchantName(itemStack));
                        }
                    } else if (clickSlot == 25) {
                        if (CheckConditions(player, levels[2], consumableName, amounts[2])) {
                            int[] enchantResult = GetEnchantResultForWeapon(player, itemStack).getLevelHigh();
                            TakeConditionsAndEnchantItem(player, levels[2], amounts[2], enchantResult, GetDamageEnchantName(itemStack));
                        }
                    }
                    ChangeEnchantList(player);
                } else if (IsArmor(itemStack)) {
                    if (clickSlot == 21) {
                        if (CheckConditions(player, levels[0], consumableName, amounts[0])) {
                            int[] enchantResult = GetEnchantResultForArmor(player, itemStack).getLevelLow();
                            TakeConditionsAndEnchantItem(player, levels[0], amounts[0], enchantResult, GetSpecialEnchantName(itemStack));
                        }
                    } else if (clickSlot == 23) {
                        if (CheckConditions(player, levels[1], consumableName, amounts[1])) {
                            int[] enchantResult = GetEnchantResultForArmor(player, itemStack).getLevelMiddle();
                            TakeConditionsAndEnchantItem(player, levels[1], amounts[1], enchantResult, GetSpecialEnchantName(itemStack));
                        }
                    } else if (clickSlot == 25) {
                        if (CheckConditions(player, levels[2], consumableName, amounts[2])) {
                            int[] enchantResult = GetEnchantResultForArmor(player, itemStack).getLevelHigh();
                            TakeConditionsAndEnchantItem(player, levels[2], amounts[2], enchantResult, GetSpecialEnchantName(itemStack));
                        }
                    }
                    ChangeEnchantList(player);
                } else if (IsTool(itemStack)) {
                    if (clickSlot == 21) {
                        if (CheckConditions(player, levels[0], consumableName, amounts[0])) {
                            int[] enchantResult = GetEnchantResultForTool(player, itemStack).getLevelLow();
                            TakeConditionsAndEnchantItem(player, levels[0], amounts[0], enchantResult, "");
                        }
                    } else if (clickSlot == 23) {
                        if (CheckConditions(player, levels[1], consumableName, amounts[1])) {
                            int[] enchantResult = GetEnchantResultForTool(player, itemStack).getLevelMiddle();
                            TakeConditionsAndEnchantItem(player, levels[1], amounts[1], enchantResult, "");
                        }
                    } else if (clickSlot == 25) {
                        if (CheckConditions(player, levels[2], consumableName, amounts[2])) {
                            int[] enchantResult = GetEnchantResultForTool(player, itemStack).getLevelHigh();
                            TakeConditionsAndEnchantItem(player, levels[2], amounts[2], enchantResult, "");
                        }
                    }
                    ChangeEnchantList(player);
                } else {

                }
                //denyClickPlayerSet.remove(uuid);
            }, 1);
        }
    }

    @EventHandler
    public void OnRightClickEnchantTable(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        Player player = event.getPlayer();
        if (event.getAction().isLeftClick()) {
            return;
        }
        if (clickedBlock == null) {
            return;
        }
        if (clickedBlock.getType() != Material.ENCHANTING_TABLE) {
            return;
        }
        event.setCancelled(true);
        new EnchantMenu(player).open();
    }

    @EventHandler
    public void OnCloseMenu(InventoryCloseEvent event) {
        InventoryView inv = event.getView();
        if (!inv.getTitle().equals(EnchantMenu.TITLE)) {
            return;
        }
        Player player = (Player) event.getPlayer();
        Inventory playerInventory = player.getInventory();
        ItemStack enchantItem = inv.getItem(10);
        ItemStack consumable = inv.getItem(28);
        if (enchantItem != null) {
            int emptySlot = playerInventory.firstEmpty();
            if (emptySlot != -1) {
                playerInventory.setItem(emptySlot, enchantItem);
            } else {
                player.getWorld().dropItem(player.getLocation(), enchantItem);
            }
        }
        if (consumable != null) {
            int emptySlot = playerInventory.firstEmpty();
            if (emptySlot != -1) {
                playerInventory.setItem(emptySlot, consumable);
            } else {
                player.getWorld().dropItem(player.getLocation(), consumable);
            }
        }
    }
}
