package flandre.scarlet.thevoidkingdom.utils.game;


import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.enchant.StatEnchantment;
import flandre.scarlet.thevoidkingdom.functions.enchant.VKEnchantment;
import flandre.scarlet.thevoidkingdom.functions.enchant.VanillaEnchantment;
import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import net.Indyuce.mmoitems.api.interaction.util.DurabilityItem;
import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MMOItemsUtils {
    public static final Map<String, String> CHANGEABLE_STAT_TRANSLATE_MAP = new HashMap<>();
    public static final List<String> ROMA_NUMBER = List.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
    public static final String ENCHANT_ICON = PlaceholderAPI.setPlaceholders(null, "%img_enchant%");

    static {
        CHANGEABLE_STAT_TRANSLATE_MAP.put("基础伤害", "ATTACK_DAMAGE");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("基础防御", "DEFENSE");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("暴击概率", "CRITICAL_STRIKE_CHANCE");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("暴击伤害", "CRITICAL_STRIKE_POWER");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("符卡伤害", "SKILL_DAMAGE");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("符暴概率", "SKILL_CRITICAL_STRIKE_CHANCE");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("符暴伤害", "SKILL_CRITICAL_STRIKE_POWER");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("符卡冷却", "COOLDOWN_REDUCTION");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("生命上限", "MAX_HEALTH");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("生命回复", "HEALTH_REGENERATION");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("物理伤害", "PHYSICAL_DAMAGE");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("射弹伤害", "PROJECTILE_DAMAGE");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("魔法伤害", "MAGIC_DAMAGE");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("物理抗性", "PHYSICAL_DAMAGE_REDUCTION");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("射弹抗性", "PROJECTILE_DAMAGE_REDUCTION");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("魔法抗性", "MAGIC_DAMAGE_REDUCTION");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("锤击范围", "BLUNT_POWER");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("锤伤比例", "BLUNT_RATING");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("箭矢速度", "ARROW_VELOCITY");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("魔力上限", "MAX_MANA");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("魔力回复", "MANA_REGENERATION");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("魔力消耗", "MANA_COST");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("格挡比例", "BLOCK_POWER");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("格挡概率", "BLOCK_RATING");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("格挡冷却", "BLOCK_COOLDOWN_REDUCTION");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("闪避概率", "DODGE_RATING");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("闪避冷却", "DODGE_COOLDOWN_REDUCTION");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("晶体尺寸", "CUSTOM_SIZE");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("晶体抛光", "CUSTOM_POLISH");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("纯度等级", "CUSTOM_PURITY");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("振奋概率", "CUSTOM_HASTE_START_POSSIBILITY");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("激励概率", "CUSTOM_HASTE_UPGRADE_POSSIBILITY");
        CHANGEABLE_STAT_TRANSLATE_MAP.put("持久加时", "CUSTOM_HASTE_EXTRA_TIME");
    }

    //这个静态方法不是就地修改物品！需要set到inventory里面
    public static ItemStack changeDurability(ItemStack inputItemStack, int durability, int maxDurability) {
        if (durability < 0) {
            TheVoidKingdom.LOGGER.warning("耐久值不应该小于0");
            return inputItemStack;
        }
        if (maxDurability < 1) {
            TheVoidKingdom.LOGGER.warning("最大耐久不应该小于1");
            return inputItemStack;
        }
        if (durability > maxDurability) {
            TheVoidKingdom.LOGGER.warning("耐久值不应该大于最大耐久");
            return inputItemStack;
        }
        NBTItem nbtItem = NBTItem.get(inputItemStack);
        if (!nbtItem.hasType()) {
            return inputItemStack;
        }


        de.tr7zw.nbtapi.NBTItem nbti = new de.tr7zw.nbtapi.NBTItem(inputItemStack);
        if (!nbti.hasKey("MMOITEMS_MAX_DURABILITY")) {
            TheVoidKingdom.LOGGER.warning("该物品无耐久值");
            return inputItemStack;
        }
        nbti.setInteger("MMOITEMS_DURABILITY", durability);
        nbti.setInteger("MMOITEMS_MAX_DURABILITY", maxDurability);
        ItemStack newItem = nbti.getItem();
        newItem.lore(buildMILore(newItem));
        //修改原版耐久条
        ItemMeta newItemMeta = newItem.getItemMeta();
        Damageable damageable = (Damageable) newItemMeta;
        damageable.setDamage((int) Math.ceil((maxDurability - durability) / (double) maxDurability * newItem.getType().getMaxDurability()));
        newItem.setItemMeta(damageable);
        return newItem;
    }


    public static @Nullable ItemStack decreaseDurabilityNaturally(@Nullable ItemStack itemStack, Player player, int loss) {
        if (itemStack == null || itemStack.getType().isAir()) {
            return null;
        }
        if (loss < 0) {
            TheVoidKingdom.LOGGER.warning("损失的耐久应不小于0");
            return itemStack;
        } else if (loss == 0) {
            return itemStack;
        }
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (nbtItem.hasType()) {
            DurabilityItem durabilityItem = new DurabilityItem(player, nbtItem);
            durabilityItem.decreaseDurability(loss);
            return durabilityItem.toItem();
        } else {
            double durabilityLevel = itemStack.getEnchantmentLevel(Enchantment.DURABILITY);
            int requireDurability = loss;
            if (durabilityLevel > 0.0) {
                Random random = new Random();
                for (int i = 1; i <= loss; i++) {
                    if (random.nextDouble() > 1.0 / (durabilityLevel + 1.0)) {
                        requireDurability--;
                    }
                }
            }
            if (requireDurability < 1) {
                return itemStack;
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            Damageable damageable = (Damageable) itemMeta;
            int maxDurability = itemStack.getType().getMaxDurability();
            int damage = damageable.getDamage();
            int newDamage = damage + requireDurability;
            if (newDamage < maxDurability) {
                damageable.setDamage(damageable.getDamage() + requireDurability);
                itemStack.setItemMeta(damageable);
                return itemStack;
            } else {
                player.getWorld().spawnParticle(Particle.ITEM_CRACK, player.getLocation().add(0.0, 1.0, 0.0), 10, 0.1, 0.1, 0.1, 0.0, itemStack);
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
                return null;
            }
        }
    }

    public static ItemStack changeStat(ItemStack inputItemStack, String stat, double value) {
        if (value < 0) {
            TheVoidKingdom.LOGGER.warning("stat值不应该小于0");
            return inputItemStack;
        }
        if (!CHANGEABLE_STAT_TRANSLATE_MAP.containsValue(stat)) {
            if (CHANGEABLE_STAT_TRANSLATE_MAP.containsKey(stat)) {
                stat = CHANGEABLE_STAT_TRANSLATE_MAP.get(stat);
            } else {
                TheVoidKingdom.LOGGER.warning("不存在的stat: " + stat);
                return inputItemStack;
            }
        }
        de.tr7zw.nbtapi.NBTItem nbti = new de.tr7zw.nbtapi.NBTItem(inputItemStack);
        nbti.setDouble("MMOITEMS_" + stat, value);
        ItemStack newItem = nbti.getItem();
        newItem.lore(buildMILore(newItem));
        return newItem;
    }

    public static ItemStack changeEnchantLevel(ItemStack inputItemStack, VKEnchantment vkEnchantment, int newLevel) {
        if (newLevel < 0 || newLevel > 10) {
            TheVoidKingdom.LOGGER.warning("附魔等级应在1-10之间");
            return inputItemStack;
        }
        de.tr7zw.nbtapi.NBTItem nbti = new de.tr7zw.nbtapi.NBTItem(inputItemStack);
        String enchantName = vkEnchantment.getEnchantName();
        int beforeLevel = 0;
        boolean isEnchantmentExist = false;
        //附魔槽中已有该附魔，修改现有槽附魔等级
        for (int i = 1; i < 10; i++) {
            String str = nbti.getString("MMOITEMS_CUSTOM_ENCHANT" + i);
            if (str.contains(enchantName)) {
                isEnchantmentExist = true;
                String levelRoma = str.substring(9);
                beforeLevel = ROMA_NUMBER.contains(levelRoma) ? ROMA_NUMBER.indexOf(levelRoma) + 1 : 0;
                if (newLevel == 0) {
                    nbti.removeKey("MMOITEMS_CUSTOM_ENCHANT" + i);
                } else {
                    nbti.setString("MMOITEMS_CUSTOM_ENCHANT" + i, "§b " + ENCHANT_ICON + " " + enchantName + " " + ROMA_NUMBER.get(newLevel - 1));
                }
                break;
            }
        }
        //附魔槽中无该附魔，找一个空槽
        if (!isEnchantmentExist) {
            if (newLevel == 0) {
                return inputItemStack;
            }
            boolean hasEmptySlot = false;
            for (int i = 1; i < 10; i++) {
                String str = nbti.getString("MMOITEMS_CUSTOM_ENCHANT" + i);
                if (str.isEmpty()) {
                    hasEmptySlot = true;
                    nbti.setString("MMOITEMS_CUSTOM_ENCHANT" + i, "§b " + ENCHANT_ICON + " " + enchantName + " " + ROMA_NUMBER.get(newLevel - 1));
                    break;
                }
            }
            if (!hasEmptySlot) {
                TheVoidKingdom.LOGGER.warning("该物品附魔nbt槽位已满");
                return inputItemStack;
            }
        }
        //修改stat属性或原版附魔
        ItemStack resultItemStack = null;
        if (vkEnchantment instanceof StatEnchantment statEnchantment) {
            switch (statEnchantment.getOperation()) {
                case DURABILITY -> {
                    double beforeValue = nbti.getDouble("MMOITEMS_MAX_DURABILITY");
                    List<Double> statValues = statEnchantment.getStatValues();
                    ItemStack temp = nbti.getItem();
                    resultItemStack = MMOItemsUtils.changeDurability(temp, MMOItemsUtils.getDurability(temp), (int) (beforeValue / (beforeLevel == 0 ? 1 : statValues.get(beforeLevel - 1)) * (newLevel == 0 ? 1 : statValues.get(newLevel - 1))));
                }
                case MULTIPLY_STAT -> {
                    String key = "MMOITEMS_" + statEnchantment.getStatName();
                    double beforeValue = nbti.getDouble(key);
                    List<Double> statValues = statEnchantment.getStatValues();
                    nbti.setDouble(key, beforeValue / (beforeLevel == 0 ? 1 : statValues.get(beforeLevel - 1)) * (newLevel == 0 ? 1 : statValues.get(newLevel - 1)));
                    resultItemStack = nbti.getItem();
                    resultItemStack.lore(buildMILore(resultItemStack));
                }
                case ADD_STAT -> {
                    String key = "MMOITEMS_" + statEnchantment.getStatName();
                    double beforeValue = nbti.getDouble(key);
                    List<Double> statValues = statEnchantment.getStatValues();
                    nbti.setDouble(key, beforeValue - (beforeLevel == 0 ? 0 : statValues.get(beforeLevel - 1)) + (newLevel == 0 ? 0 : statValues.get(newLevel - 1)));
                    resultItemStack = nbti.getItem();
                    resultItemStack.lore(buildMILore(resultItemStack));
                }
            }
        } else if (vkEnchantment instanceof VanillaEnchantment vanillaEnchantment) {
            ItemStack itemStack = nbti.getItem();
            if (newLevel == 0) {
                itemStack.removeEnchantment(vanillaEnchantment.getEnchantment());
            } else {
                itemStack.addUnsafeEnchantment(vanillaEnchantment.getEnchantment(), vanillaEnchantment.getLevelValues().get(newLevel - 1));
            }
            resultItemStack = nbti.getItem();
            resultItemStack.lore(buildMILore(resultItemStack));
        }
        assert resultItemStack != null;
        //最后安排附魔光效
        if (hasEnchantment(resultItemStack)) {
            resultItemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 0);
        } else {
            resultItemStack.removeEnchantment(Enchantment.DURABILITY);
        }
        return resultItemStack;
    }

    //获取物品的mi耐久，若物品无mi耐久，返回-1
    public static int getDurability(de.tr7zw.nbtapi.NBTItem nbtItem) {
        if (!nbtItem.hasKey("MMOITEMS_MAX_DURABILITY")) {
            return -1;
        }
        return nbtItem.hasKey("MMOITEMS_DURABILITY") ? nbtItem.getInteger("MMOITEMS_DURABILITY") : nbtItem.getInteger("MMOITEMS_MAX_DURABILITY");
    }

    public static int getDurability(ItemStack itemStack) {
        return getDurability(new de.tr7zw.nbtapi.NBTItem(itemStack));
    }

    //获取物品的mi最大耐久，若物品无mi耐久，返回-1
    public static int getMaxDurability(de.tr7zw.nbtapi.NBTItem nbtItem) {
        return nbtItem.hasKey("MMOITEMS_MAX_DURABILITY") ? nbtItem.getInteger("MMOITEMS_MAX_DURABILITY") : -1;
    }

    public static int getMaxDurability(ItemStack itemStack) {
        return getMaxDurability(new de.tr7zw.nbtapi.NBTItem(itemStack));
    }

    //获取物品的mi stat属性值，可用中文映射名，若物品无对应stat属性，返回-1
    public static double getStat(ItemStack itemStack, String str) {
        de.tr7zw.nbtapi.NBTItem nbti = new de.tr7zw.nbtapi.NBTItem(itemStack);
        if (CHANGEABLE_STAT_TRANSLATE_MAP.containsValue(str)) {
            return nbti.getDouble(str);
        }
        if (CHANGEABLE_STAT_TRANSLATE_MAP.containsKey(str)) {
            return nbti.getDouble(CHANGEABLE_STAT_TRANSLATE_MAP.get(str));
        }
        return -1;
    }

    //检测物品是否具有vk附魔
    public static boolean hasEnchantment(ItemStack itemStack) {
        de.tr7zw.nbtapi.NBTItem nbti = new de.tr7zw.nbtapi.NBTItem(itemStack);
        for (int i = 1; i < 10; i++) {
            String str = nbti.getString("MMOITEMS_CUSTOM_ENCHANT" + i);
            if (!str.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    //获取物品特定vk附魔的等级，若物品无对应附魔/附魔等级不合法，返回-1
    public static int getEnchantmentLevel(ItemStack itemStack, VKEnchantment vkEnchantment) {
        de.tr7zw.nbtapi.NBTItem nbti = new de.tr7zw.nbtapi.NBTItem(itemStack);
        String enchantName = vkEnchantment.getEnchantName();
        for (int i = 1; i < 10; i++) {
            String str = nbti.getString("MMOITEMS_CUSTOM_ENCHANT" + i);
            if (str.contains(enchantName)) {
                String levelRoma = str.substring(9);
                return ROMA_NUMBER.contains(levelRoma) ? ROMA_NUMBER.indexOf(levelRoma) + 1 : -1;
            }
        }
        return -1;
    }

    //获取物品vk附魔等级之和，等级不合法的附魔会被忽略
    public static int getTotalEnchantmentLevel(ItemStack itemStack) {
        de.tr7zw.nbtapi.NBTItem nbti = new de.tr7zw.nbtapi.NBTItem(itemStack);
        int count = 0;
        for (int i = 1; i < 10; i++) {
            String str = nbti.getString("MMOITEMS_CUSTOM_ENCHANT" + i);
            if (str != null && str.length() != 0) {
                String levelRoma = str.substring(9);
                count = count + (ROMA_NUMBER.contains(levelRoma) ? ROMA_NUMBER.indexOf(levelRoma) + 1 : 0);
            }
        }
        return count;
    }

    public static String getIANamespace(String miType) {
        return switch (miType.toLowerCase()) {
            case "accessory" -> "vkaccessories";
            case "armor", "armor_warrior", "armor_archer", "armor_mage" -> "vkarmors";
            case "pickaxe", "axe", "shovel", "hoe" -> "vktools";
            case "sword", "hammer", "shield", "bow", "cross", "shot", "wand", "lantern", "book", "dagger" ->
                    "vkweapons";
            case "spellcard", "spellcard_consumable" -> "vkspellcards";
            default -> null;
        };
    }


    public static List<Component> buildMILore(ItemStack itemStack) {
        MMOItem mmoitem = new LiveMMOItem(itemStack);
        ItemStack newItemStack = mmoitem.newBuilder().build();
        return newItemStack == null ? null : newItemStack.lore();
    }
}
