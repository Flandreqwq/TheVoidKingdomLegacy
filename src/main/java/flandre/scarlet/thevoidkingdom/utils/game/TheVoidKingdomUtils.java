package flandre.scarlet.thevoidkingdom.utils.game;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.*;

public class TheVoidKingdomUtils {
    public static Map<String, String> FONT_PLACEHOLDER = new HashMap<>();

    static {
        FONT_PLACEHOLDER.put("%img_line%", "\uE396");
        FONT_PLACEHOLDER.put("%img_short_line_1%", "\uE3A0");
        FONT_PLACEHOLDER.put("%img_short_line_2%", "\uE39F");
        FONT_PLACEHOLDER.put("%img_short_line_3%", "\uE39E");
        FONT_PLACEHOLDER.put("%img_long_line_1%", "\uE398");
        FONT_PLACEHOLDER.put("%img_long_line_2%", "\uE399");
        FONT_PLACEHOLDER.put("%img_long_line_3%", "\uE39A");
        FONT_PLACEHOLDER.put("%img_enchant_stat_idea%", "\uE3D4");
        FONT_PLACEHOLDER.put("%img_enchant_stat_inspiration%", "\uE3D5");
        FONT_PLACEHOLDER.put("%img_enchant_stat_rank%", "\uE3D6");
        FONT_PLACEHOLDER.put("%img_info%", "\uE397");
        FONT_PLACEHOLDER.put("%img_slot_background_1%", "\uE3C8");
        FONT_PLACEHOLDER.put("%img_slot_background_2%", "\uE3C9");
        FONT_PLACEHOLDER.put("%img_slot_background_3%", "\uE3CA");
        FONT_PLACEHOLDER.put("%img_slot_background_4%", "\uE3CB");
        FONT_PLACEHOLDER.put("%img_slot_background_5%", "\uE3CC");
        FONT_PLACEHOLDER.put("%img_slot_background_6%", "\uE3CD");
    }

    public static List<Material> VANILLA_ARMORS = List.of(
            Material.LEATHER_HELMET,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_LEGGINGS,
            Material.LEATHER_BOOTS,
            Material.GOLDEN_HELMET,
            Material.GOLDEN_CHESTPLATE,
            Material.GOLDEN_LEGGINGS,
            Material.GOLDEN_BOOTS,
            Material.CHAINMAIL_HELMET,
            Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS,
            Material.CHAINMAIL_BOOTS,
            Material.IRON_HELMET,
            Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS,
            Material.IRON_BOOTS,
            Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS,
            Material.NETHERITE_HELMET,
            Material.NETHERITE_CHESTPLATE,
            Material.NETHERITE_LEGGINGS,
            Material.NETHERITE_BOOTS,
            Material.TURTLE_HELMET
    );

    public static List<Material> VANILLA_TOOL = List.of(
            Material.WOODEN_PICKAXE,
            Material.WOODEN_AXE,
            Material.WOODEN_HOE,
            Material.WOODEN_SHOVEL,
            Material.STONE_PICKAXE,
            Material.STONE_AXE,
            Material.STONE_HOE,
            Material.STONE_SHOVEL,
            Material.IRON_PICKAXE,
            Material.IRON_AXE,
            Material.IRON_HOE,
            Material.IRON_SHOVEL,
            Material.GOLDEN_PICKAXE,
            Material.GOLDEN_AXE,
            Material.GOLDEN_HOE,
            Material.GOLDEN_SHOVEL,
            Material.DIAMOND_PICKAXE,
            Material.DIAMOND_AXE,
            Material.DIAMOND_HOE,
            Material.DIAMOND_SHOVEL,
            Material.NETHERITE_PICKAXE,
            Material.NETHERITE_AXE,
            Material.NETHERITE_HOE,
            Material.NETHERITE_SHOVEL
    );

    public static List<Material> VANILLA_SWORD = List.of(
            Material.WOODEN_SWORD,
            Material.STONE_SWORD,
            Material.IRON_SWORD,
            Material.GOLDEN_SWORD,
            Material.DIAMOND_SWORD,
            Material.NETHERITE_SWORD,
            Material.TRIDENT
    );

    public static void sendMessageToPlayer(Player player, String message, boolean debug) {
        if (debug) {
            player.sendMessage(TheVoidKingdomUtils.deserializeMiniMessage("<dark_gray>[<light_purple><bold>虚空之国</bold><dark_gray>|<green><bold>Debug</bold><dark_gray>] <reset>" + message, false));
        } else {
            player.sendMessage(TheVoidKingdomUtils.deserializeMiniMessage("<dark_gray>[<light_purple><bold>虚空之国</bold><dark_gray>] <reset>" + message, false));
        }
    }

    private static final AttributeModifier attributeModifier = new AttributeModifier(UUID.randomUUID(), "vkDecoy", 0, AttributeModifier.Operation.ADD_NUMBER);
    private static final AttributeModifier woodenSwordDamage = new AttributeModifier(UUID.randomUUID(), "vkDecoy", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
    private static final AttributeModifier stoneSwordDamage = new AttributeModifier(UUID.randomUUID(), "vkDecoy", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
    private static final AttributeModifier woodenSwordSpeed = new AttributeModifier(UUID.randomUUID(), "vkDecoy", -2.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
    private static final AttributeModifier stoneSwordSpeed = new AttributeModifier(UUID.randomUUID(), "vkDecoy", -2.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

    public static void removeVanillaEnchantment(ItemStack itemStack) {
        Map<Enchantment, Integer> enchantments = itemStack.getEnchantments();
        enchantments.forEach(((enchantment, integer) -> itemStack.removeEnchantment(enchantment)));
    }

    public static ItemStack checkClearVanillaStat(ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }
        Material material = itemStack.getType();
        if (material.isAir()) {
            return itemStack;
        }
        if (ItemsAdderUtils.isCustomItemStack(itemStack)) {
            return itemStack;
        }
        removeVanillaEnchantment(itemStack);
        if (VANILLA_ARMORS.contains(material)) {
            ItemMeta itemMete = itemStack.getItemMeta();
            if (itemMete.getAttributeModifiers() == null) {
//                itemMete.setAttributeModifiers(emptyMultimap);
                itemMete.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attributeModifier);
                itemMete.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                if (itemMete instanceof LeatherArmorMeta leatherArmorMeta) {
                    leatherArmorMeta.setColor(null);
                    itemStack.setItemMeta(leatherArmorMeta);
                } else {
                    itemStack.setItemMeta(itemMete);
                }
            }
            return itemStack;
        } else if (TheVoidKingdomUtils.VANILLA_TOOL.contains(material)) {
            ItemMeta itemMete = itemStack.getItemMeta();
            if (itemMete.getAttributeModifiers() == null) {
//                itemMete.setAttributeModifiers(emptyMultimap);
                itemMete.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attributeModifier);
                itemMete.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                itemStack.setItemMeta(itemMete);
            }
            return itemStack;
        } else if (TheVoidKingdomUtils.VANILLA_SWORD.contains(material)) {
            ItemMeta itemMete = itemStack.getItemMeta();
//            itemMete.setAttributeModifiers(emptyMultimap);
            if (itemMete.getAttributeModifiers() == null) {
                if (material.equals(Material.WOODEN_SWORD)) {
                    itemMete.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, woodenSwordDamage);
                    itemMete.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, woodenSwordSpeed);
                } else if (material.equals(Material.STONE_SWORD)) {
                    itemMete.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, stoneSwordDamage);
                    itemMete.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, stoneSwordSpeed);
                } else {
                    itemMete.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attributeModifier);
                }
                itemMete.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                itemStack.setItemMeta(itemMete);
            }
            return itemStack;
        } else {
            return itemStack;
        }
    }

    public static List<Component> deserializeList(List<String> list, boolean setPlaceholder) {
        List<Component> list1 = new ArrayList<>();
        list.forEach(str -> {
            list1.add(deserializeMiniMessage(str, setPlaceholder));
        });
        return list1;
    }

    public static Component deserializeMiniMessage(String str, boolean setPlaceholder) {
        return TheVoidKingdom.MINI_MESSAGE.deserialize(setPlaceholder ? setFontPlaceHolder(str) : str);
    }

    public static String setFontPlaceHolder(String string) {
        String str = string;
        for (String key : FONT_PLACEHOLDER.keySet()) {
            str = str.replace(key, FONT_PLACEHOLDER.get(key));
        }
        return str;
    }

    public static void giveItemStackToPlayer(Player player, ItemStack... itemStacks) {
        List<ItemStack> giveItemStacks = new ArrayList<>();
        for (ItemStack itemStack : itemStacks) {
            int amount = itemStack.getAmount();
            int maxStackSize = itemStack.getType().getMaxStackSize();
            while (amount > maxStackSize) {
                ItemStack itemStackClone = itemStack.clone();
                itemStackClone.setAmount(maxStackSize);
                giveItemStacks.add(itemStackClone);
                amount -= maxStackSize;
            }
            ItemStack itemStackClone = itemStack.clone();
            itemStackClone.setAmount(amount);
            giveItemStacks.add(itemStackClone);
        }
        Inventory inventory = player.getInventory();
        Map<Integer, ItemStack> returnItemStackMap = inventory.addItem(giveItemStacks.toArray(new ItemStack[0]));
        World world = player.getWorld();
        Location location = player.getLocation();
        returnItemStackMap.forEach((integer, itemStack1) -> {
            world.dropItem(location, itemStack1);
        });
    }


    public static boolean checkIngredients(Player player, Map<String, Integer> ingredients) {
        Inventory inventory = player.getInventory();
        Map<String, Integer> mapClone = new HashMap<>(ingredients);
        for (ItemStack itemStack : inventory) {
            if (itemStack == null) {
                continue;
            }
            for (String namespaceId : mapClone.keySet()) {
                if (ItemsAdderUtils.getItemNamespaceId(itemStack).equals(namespaceId)) {
                    if (itemStack.getAmount() >= mapClone.get(namespaceId)) {
                        mapClone.remove(namespaceId);
                    } else {
                        mapClone.put(namespaceId, mapClone.get(namespaceId) - itemStack.getAmount());
                    }
                    break;
                }
            }
        }
        return mapClone.isEmpty();
    }

    public static void takeIngredients(Player player, Map<String, Integer> ingredients) {
        Inventory inventory = player.getInventory();
        Map<String, Integer> mapClone = new HashMap<>(ingredients);
        for (ItemStack itemStack : inventory) {
            if (itemStack == null) {
                continue;
            }
            for (String namespaceId : mapClone.keySet()) {
                if (ItemsAdderUtils.getItemNamespaceId(itemStack).equals(namespaceId)) {
                    int var1 = itemStack.getAmount();
                    int var2 = mapClone.get(namespaceId);
                    if (var1 >= var2) {
                        itemStack.setAmount(var1 - var2);
                        mapClone.remove(namespaceId);
                    } else {
                        itemStack.setAmount(0);
                        mapClone.put(namespaceId, var2 - var1);
                    }
                    break;
                }
            }
        }
        if (!mapClone.isEmpty()) {
            throw new RuntimeException("材料检查通过，但玩家背包中的材料不足！");
        }
    }
}
