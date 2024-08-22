import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import io.lumine.mythic.lib.api.player.MMOPlayerData;
import io.lumine.mythic.lib.api.stat.modifier.StatModifier;
import io.lumine.mythic.lib.player.modifier.ModifierType;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class EnchantStats implements Listener {
    private final Map<UUID, ItemStack> playerHeldItemMap = new HashMap<>();

    public boolean IsStringContains(String str, List<String> list) {
        for (String string : list) {
            if (str.contains(string)) return true;
        }
        return false;
    }

    public boolean IsItemLoreContain(ItemStack itemStack, String str) {
        if (itemStack.getType().isAir()) return false;
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> itemLore = Objects.requireNonNullElse(itemMeta.getLore(), new ArrayList<>());
        return itemLore.contains(str);
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

    public boolean IsWarrior(Player player) {
        PlayerData playerData = PlayerData.get(player);
        List<String> jobs = List.of("见习战士", "神圣骑士", "幻影刺客");
        for (String job : jobs) {
            if (playerData.getProfess().getName().equals(job)) {
                return true;
            }
        }
        return false;
    }

    public boolean IsArcher(Player player) {
        PlayerData playerData = PlayerData.get(player);
        List<String> jobs = List.of("见习射手", "虚空游侠", "虚空猎人");
        for (String job : jobs) {
            if (playerData.getProfess().getName().equals(job)) {
                return true;
            }
        }
        return false;
    }

    public boolean IsMage(Player player) {
        PlayerData playerData = PlayerData.get(player);
        List<String> jobs = List.of("魔法使", "烈焰法师", "寒霜法师", "奥术法师");
        for (String job : jobs) {
            if (playerData.getProfess().getName().equals(job)) {
                return true;
            }
        }
        return false;
    }

    public void EnchantWeaponModifier(Player player, String statName, double value) {
        MMOPlayerData playerData = MMOPlayerData.get(player);
        StatModifier Modifier = new StatModifier("WeaponEnchant", statName, value, ModifierType.FLAT);
        Modifier.register(playerData);
//        player.sendMessage(statName + "现在增幅是" + value);
    }

    public void EnchantArmorModifier(Player player, String statName, double value, int armorType) {
        MMOPlayerData playerData = MMOPlayerData.get(player);
        StatModifier Modifier = new StatModifier("ArmorEnchant" + armorType, statName, value, ModifierType.FLAT);
        Modifier.register(playerData);
//        player.sendMessage(armorType + "号护甲的" + statName + "现在增幅是" + value);
    }

    public void GiveWeaponEnchantValue(double[] values, String statName, int mode, Player player, String lore, int cutIndex) {
        String romanLevel = lore.substring(cutIndex);
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i] + (i + 1) * mode;
        }
        switch (romanLevel) {
            case "I":
                EnchantWeaponModifier(player, statName, values[0]);
                break;
            case "II":
                EnchantWeaponModifier(player, statName, values[1]);
                break;
            case "III":
                EnchantWeaponModifier(player, statName, values[2]);
                break;
            case "IV":
                EnchantWeaponModifier(player, statName, values[3]);
                break;
            case "V":
                EnchantWeaponModifier(player, statName, values[4]);
                break;
            case "VI":
                EnchantWeaponModifier(player, statName, values[5]);
                break;
            case "VII":
                EnchantWeaponModifier(player, statName, values[6]);
                break;
            case "VIII":
                EnchantWeaponModifier(player, statName, values[7]);
                break;
            case "IX":
                EnchantWeaponModifier(player, statName, values[8]);
                break;
            case "X":
                EnchantWeaponModifier(player, statName, values[9]);
                break;
        }

    }

    public void GiveArmorEnchantValue(double[] values, String statName, int mode, int armorType, Player player, String lore, int cutIndex) {
        String romanLevel = lore.substring(cutIndex);
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i] + i * mode;
        }
        switch (romanLevel) {
            case "I":
                EnchantArmorModifier(player, statName, values[0], armorType);
                break;
            case "II":
                EnchantArmorModifier(player, statName, values[1], armorType);
                break;
            case "III":
                EnchantArmorModifier(player, statName, values[2], armorType);
                break;
            case "IV":
                EnchantArmorModifier(player, statName, values[3], armorType);
                break;
            case "V":
                EnchantArmorModifier(player, statName, values[4], armorType);
                break;
            case "VI":
                EnchantArmorModifier(player, statName, values[5], armorType);
                break;
            case "VII":
                EnchantArmorModifier(player, statName, values[6], armorType);
                break;
            case "VIII":
                EnchantArmorModifier(player, statName, values[7], armorType);
                break;
            case "IX":
                EnchantArmorModifier(player, statName, values[8], armorType);
                break;
            case "X":
                EnchantArmorModifier(player, statName, values[9], armorType);
                break;
        }

    }

    public boolean GiveEnchantValues_Damage(Player player, String lore, int mode) {
        if (IsStringContains(lore, List.of("沉重", "锋利", "利刃", "魔光"))) {
            GiveWeaponEnchantValue(new double[]{3, 6, 10, 14, 18, 23, 28, 33, 38, 44}, "ATTACK_DAMAGE", mode, player, lore, 9);
            return true;
        } else if (IsStringContains(lore, List.of("力量", "魔涌", "穿透"))) {
            GiveWeaponEnchantValue(new double[]{4, 8, 12, 17, 22, 27, 32, 38, 44, 50}, "ATTACK_DAMAGE", mode, player, lore, 9);
            return true;
        } else if (IsStringContains(lore, List.of("锋矢", "咒术"))) {
            GiveWeaponEnchantValue(new double[]{2, 3, 5, 7, 9, 11, 14, 17, 20, 23}, "ATTACK_DAMAGE", mode, player, lore, 9);
            return true;
        } else {
            return false;
        }
    }

    public void EnchantDamage(Player player, List<String> lores) {
        boolean flag = false;
        for (String lore : lores) {
            if (lore.contains("§7● §b")) {
                flag = GiveEnchantValues_Damage(player, lore, 0);
            } else if (lore.contains("§7● §7")) {
                flag = GiveEnchantValues_Damage(player, lore, -1);
            } else if (lore.contains("§7● §c")) {
                flag = GiveEnchantValues_Damage(player, lore, 1);
            }
            if (flag) break;
        }
        if (!flag) {
            EnchantWeaponModifier(player, "ATTACK_DAMAGE", 0);
        }
    }

    public void EnchantWeaponCriticalChance(Player player, List<String> lores) {
        for (String lore : lores) {
            if (lore.contains("§7● §b")) {
                if (!lore.contains("幸运")) continue;
                GiveWeaponEnchantValue(new double[]{2.5, 5, 8}, "CRITICAL_STRIKE_CHANCE", 0, player, lore, 9);
                return;
            } else if (lore.contains("§7● §7")) {
                if (!lore.contains("幸运")) continue;
                GiveWeaponEnchantValue(new double[]{2.5, 5, 8}, "CRITICAL_STRIKE_CHANCE", -1, player, lore, 9);
                return;
            } else if (lore.contains("§7● §c")) {
                if (!lore.contains("幸运")) continue;
                GiveWeaponEnchantValue(new double[]{2.5, 5, 8}, "CRITICAL_STRIKE_CHANCE", 1, player, lore, 9);
                return;
            }
        }
        EnchantWeaponModifier(player, "CRITICAL_STRIKE_CHANCE", 0);
    }

    public void EnchantArmorCriticalChance(Player player, List<String> lores, int armorType) {
        for (String lore : lores) {
            if (lore.contains("§7● §b")) {
                if (!lore.contains("幸运")) continue;
                GiveArmorEnchantValue(new double[]{2.5, 5, 8}, "CRITICAL_STRIKE_CHANCE", 0, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §7")) {
                if (!lore.contains("幸运")) continue;
                GiveArmorEnchantValue(new double[]{2.5, 5, 8}, "CRITICAL_STRIKE_CHANCE", -1, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §c")) {
                if (!lore.contains("幸运")) continue;
                GiveArmorEnchantValue(new double[]{2.5, 5, 8}, "CRITICAL_STRIKE_CHANCE", 1, armorType, player, lore, 9);
                return;
            }
        }
        EnchantArmorModifier(player, "CRITICAL_STRIKE_CHANCE", 0, armorType);
    }

    public void EnchantWeaponCriticalPower(Player player, List<String> lores) {
        for (String lore : lores) {
            if (lore.contains("§7● §b")) {
                if (!lore.contains("会心")) continue;
                GiveWeaponEnchantValue(new double[]{5, 8, 12}, "CRITICAL_STRIKE_POWER", 0, player, lore, 9);
                return;
            } else if (lore.contains("§7● §7")) {
                if (!lore.contains("会心")) continue;
                GiveWeaponEnchantValue(new double[]{5, 8, 12}, "CRITICAL_STRIKE_POWER", -1, player, lore, 9);
                return;
            } else if (lore.contains("§7● §c")) {
                if (!lore.contains("会心")) continue;
                GiveWeaponEnchantValue(new double[]{5, 8, 12}, "CRITICAL_STRIKE_POWER", 1, player, lore, 9);
                return;
            }
        }
        EnchantWeaponModifier(player, "CRITICAL_STRIKE_POWER", 0);
    }

    public void EnchantArmorCriticalPower(Player player, List<String> lores, int armorType) {
        for (String lore : lores) {
            if (lore.contains("§7● §b")) {
                if (!lore.contains("会心")) continue;
                GiveArmorEnchantValue(new double[]{5, 8, 12}, "CRITICAL_STRIKE_POWER", 0, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §7")) {
                if (!lore.contains("会心")) continue;
                GiveArmorEnchantValue(new double[]{5, 8, 12}, "CRITICAL_STRIKE_POWER", -1, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §c")) {
                if (!lore.contains("会心")) continue;
                GiveArmorEnchantValue(new double[]{5, 8, 12}, "CRITICAL_STRIKE_POWER", 1, armorType, player, lore, 9);
                return;
            }
        }
        EnchantArmorModifier(player, "CRITICAL_STRIKE_POWER", 0, armorType);
    }

    public void EnchantDefense(Player player, List<String> lores, int armorType) {
        for (String lore : lores) {
            if (lore.contains("§7● §b")) {
                if (!lore.contains("保护")) continue;
                GiveArmorEnchantValue(new double[]{1.5, 2.5, 3.5, 4.5, 5.5, 7, 8.5, 10, 11.5, 13}, "DEFENSE", 0, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §7")) {
                if (!lore.contains("保护")) continue;
                GiveArmorEnchantValue(new double[]{1.5, 2.5, 3.5, 4.5, 5.5, 7, 8.5, 10, 11.5, 13}, "DEFENSE", -1, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §c")) {
                if (!lore.contains("保护")) continue;
                GiveArmorEnchantValue(new double[]{1.5, 2.5, 3.5, 4.5, 5.5, 7, 8.5, 10, 11.5, 13}, "DEFENSE", 1, armorType, player, lore, 9);
                return;
            }
        }
        EnchantArmorModifier(player, "DEFENSE", 0, armorType);
    }

    public void EnchantDodgeRating(Player player, List<String> lores, int armorType) {
        if (!IsArcher(player)) return;
        for (String lore : lores) {
            if (lore.contains("§7● §b")) {
                if (!lore.contains("轻盈")) continue;
                GiveArmorEnchantValue(new double[]{2, 4, 7}, "DODGE_RATING", 0, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §7")) {
                if (!lore.contains("轻盈")) continue;
                GiveArmorEnchantValue(new double[]{2, 4, 7}, "DODGE_RATING", -1, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §c")) {
                if (!lore.contains("轻盈")) continue;
                GiveArmorEnchantValue(new double[]{2, 4, 7}, "DODGE_RATING", 1, armorType, player, lore, 9);
                return;
            }
        }
        EnchantArmorModifier(player, "DODGE_RATING", 0, armorType);
    }

    public void EnchantAnger(Player player, List<String> lores, int armorType) {
        if (!IsWarrior(player)) return;
        for (String lore : lores) {
            if (lore.contains("§7● §b")) {
                if (!lore.contains("生命")) continue;
                GiveArmorEnchantValue(new double[]{10, 20, 30}, "MAX_HEALTH", 0, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §7")) {
                if (!lore.contains("生命")) continue;
                GiveArmorEnchantValue(new double[]{10, 20, 30}, "MAX_HEALTH", -5, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §c")) {
                if (!lore.contains("生命")) continue;
                GiveArmorEnchantValue(new double[]{10, 20, 30}, "MAX_HEALTH", 5, armorType, player, lore, 9);
                return;
            }
        }
        EnchantArmorModifier(player, "MAX_MANA", 0, armorType);
    }

    public void EnchantMana(Player player, List<String> lores, int armorType) {
        if (!IsMage(player)) return;
        for (String lore : lores) {
            if (lore.contains("§7● §b")) {
                if (!lore.contains("充沛")) continue;
                GiveArmorEnchantValue(new double[]{30, 70, 120}, "MAX_MANA", 0, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §7")) {
                if (!lore.contains("充沛")) continue;
                GiveArmorEnchantValue(new double[]{30, 70, 120}, "MAX_MANA", -15, armorType, player, lore, 9);
                return;
            } else if (lore.contains("§7● §c")) {
                if (!lore.contains("充沛")) continue;
                GiveArmorEnchantValue(new double[]{30, 70, 120}, "MAX_MANA", 15, armorType, player, lore, 9);
                return;
            }
        }
        EnchantArmorModifier(player, "MAX_MANA", 0, armorType);
    }

    public void ClearWeaponModifiers(Player player) {
        EnchantWeaponModifier(player, "ATTACK_DAMAGE", 0);
        EnchantWeaponModifier(player, "CRITICAL_STRIKE_CHANCE", 0);
        EnchantWeaponModifier(player, "CRITICAL_STRIKE_POWER", 0);
    }

    public void ClearArmorModifiers(Player player, int armorType) {
        EnchantArmorModifier(player, "CRITICAL_STRIKE_CHANCE", 0, armorType);
        EnchantArmorModifier(player, "CRITICAL_STRIKE_POWER", 0, armorType);
        EnchantArmorModifier(player, "DEFENSE", 0, armorType);
        EnchantArmorModifier(player, "MAX_MANA", 0, armorType);
        EnchantArmorModifier(player, "DODGE_RATING", 0, armorType);
    }


    @EventHandler
    public void OnServerTickStartEvent(ServerTickStartEvent event) {
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (Player player : players) {
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            UUID uuid = player.getUniqueId();
            if (Objects.equals(playerHeldItemMap.get(uuid), itemStack)) {
                return;
            }
            playerHeldItemMap.put(uuid, itemStack);
            if (itemStack.getType().isAir()) {
                ClearWeaponModifiers(player);
                return;
            }
            if (!IsWeapon(itemStack)) {
                ClearWeaponModifiers(player);
                return;
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            List<String> lores = itemMeta.getLore();
            assert lores != null;
            EnchantDamage(player, lores);
            EnchantWeaponCriticalChance(player, lores);
            EnchantWeaponCriticalPower(player, lores);
        }

    }

    @EventHandler
    public void OnPlayerArmorChangeEvent(PlayerArmorChangeEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getNewItem();
        PlayerArmorChangeEvent.SlotType slotType = event.getSlotType();
        int armorType = 0;
        if (slotType.equals(PlayerArmorChangeEvent.SlotType.HEAD)) {
            armorType = 1;
        } else if (slotType.equals(PlayerArmorChangeEvent.SlotType.CHEST)) {
            armorType = 2;
        } else if (slotType.equals(PlayerArmorChangeEvent.SlotType.LEGS)) {
            armorType = 3;
        } else if (slotType.equals(PlayerArmorChangeEvent.SlotType.FEET)) {
            armorType = 4;
        }
        if (itemStack == null) {
            ClearArmorModifiers(player, armorType);
            return;
        }
        if (!IsArmor(itemStack)) {
            ClearArmorModifiers(player, armorType);
            return;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lores = itemMeta.getLore();
        assert lores != null;
        EnchantDefense(player, lores, armorType);
        EnchantArmorCriticalChance(player, lores, armorType);
        EnchantArmorCriticalPower(player, lores, armorType);
        EnchantDodgeRating(player, lores, armorType);
        EnchantAnger(player, lores, armorType);
        EnchantMana(player, lores, armorType);
    }
}
