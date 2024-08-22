package flandre.scarlet.thevoidkingdom.utils.game;

import de.tr7zw.nbtapi.NBTCompound;
import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemsAdderUtils {
    public static boolean isItemNamespaceIdExist(@Nullable String namespaceId) {
        if (namespaceId == null) {
            return false;
        }
        if (namespaceId.contains(":")) {
            CustomStack customStack = CustomStack.getInstance(namespaceId);
            if (customStack == null) {
                TheVoidKingdom.LOGGER.warning("不存在的ia物品：" + namespaceId);
                return false;
            } else {
                return true;
            }
        } else {
            Material material = Material.getMaterial(namespaceId);
            if (material == null) {
                TheVoidKingdom.LOGGER.warning("不存在的material：" + namespaceId);
                return false;
            } else {
                return true;
            }
        }
    }

    public static boolean isBlockNamespaceIdExist(@Nullable String namespaceId) {
        if (namespaceId == null) {
            return false;
        }
        if (namespaceId.contains(":")) {
            CustomBlock customBlock = CustomBlock.getInstance(namespaceId);
            if (customBlock == null) {
                TheVoidKingdom.LOGGER.warning("不存在的ia方块：" + namespaceId);
                return false;
            } else {
                return true;
            }
        } else {
            Material material = Material.getMaterial(namespaceId);
            if (material == null || !material.isBlock()) {
                TheVoidKingdom.LOGGER.warning("不存在的方块material：" + namespaceId);
                return false;
            } else {
                return true;
            }
        }
    }

    public static @NotNull String getItemNamespaceId(@Nullable ItemStack itemStack) {
        if (itemStack == null) {
            return "AIR";
        }
        CustomStack customStack = CustomStack.byItemStack(itemStack);
        if (customStack == null) {
            NBTItem nbtItem = NBTItem.get(itemStack);
            if (nbtItem.hasType()) {
                String miType = nbtItem.getType();
                String miName = nbtItem.getString("MMOITEMS_ITEM_ID");
                return MMOItemsUtils.getIANamespace(miType) + ":" + miName.toLowerCase();
            } else {
                return itemStack.getType().toString();
            }
        } else {
            return customStack.getNamespacedID();
        }
    }

    public static @NotNull String getBlockNamespaceId(@Nullable Block block) {
        if (block == null) {
            return "AIR";
        }
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        if (customBlock == null) {
            return block.getType().toString();
        } else {
            return customBlock.getNamespacedID();
        }
    }

    //支持获取ia mi 原版物品
    public static @NotNull ItemStack getItemStackNotNull(@Nullable String namespaceId) {
        if (namespaceId == null) {
            return getLostTextureIcon("<red><italic>Error: 传入参数为null");
        }
        if (namespaceId.contains(":")) {
            CustomStack customStack = CustomStack.getInstance(namespaceId);
            if (customStack == null) {
                TheVoidKingdom.LOGGER.warning("不存在的ia物品：" + namespaceId);
                return getLostTextureIcon("<red><italic>Error: 物品不存在");
            } else {
                return customStack.getItemStack();
            }
        } else if (namespaceId.contains("#")) {
            String[] strings = namespaceId.split("#");
            String miType = strings[0].toUpperCase();
            String miName = strings[1].toUpperCase();
            MMOItemTemplate mmoItemTemplate = MMOItems.plugin.getTemplates().getTemplate(MMOItems.plugin.getTypes().get(miType), miName);
            if (mmoItemTemplate == null) {
                TheVoidKingdom.LOGGER.warning("ItemTemplate " + namespaceId + " 不存在");
                return getLostTextureIcon("<red><italic>Error: 物品不存在");
            }
            ItemStack itemStack = mmoItemTemplate.newBuilder().build().newBuilder().build();
            if (itemStack == null) {
                TheVoidKingdom.LOGGER.warning("物品 " + namespaceId + " build失败");
                return getLostTextureIcon("<red><italic>Error: 物品不存在");
            }
            switch (miType) {
                case "PICKAXE", "AXE", "SHOVEL", "HOE", "DAGGER" -> {
                    de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(itemStack);
                    NBTCompound nbtCompound = nbtItem.addCompound("itemsadder");
                    nbtCompound.setString("namespace", MMOItemsUtils.getIANamespace(miType));
                    nbtCompound.setString("id", miName.toLowerCase());
                    return nbtItem.getItem();
                }
                default -> {
                    return itemStack;
                }
            }
        } else {
            Material material = Material.getMaterial(namespaceId);
            if (material == null) {
                TheVoidKingdom.LOGGER.warning("不存在的material：" + namespaceId);
                return getLostTextureIcon("<red><italic>Error: 物品不存在");
            } else {
                return TheVoidKingdomUtils.checkClearVanillaStat(new ItemStack(material));
            }
        }
    }


    //支持获取ia mi 原版物品
    public static @Nullable ItemStack getItemStackNullable(@Nullable String namespaceId) {
        if (namespaceId == null) {
            return null;
        }
        if (namespaceId.contains(":")) {
            CustomStack customStack = CustomStack.getInstance(namespaceId);
            if (customStack == null) {
                TheVoidKingdom.LOGGER.warning("不存在的ia物品：" + namespaceId);
                return null;
            } else {
                return customStack.getItemStack();
            }
        } else if (namespaceId.contains("#")) {
            String[] strings = namespaceId.split("#");
            String miType = strings[0].toUpperCase();
            String miName = strings[1].toUpperCase();
            MMOItemTemplate mmoItemTemplate = MMOItems.plugin.getTemplates().getTemplate(MMOItems.plugin.getTypes().get(miType), miName);
            if (mmoItemTemplate == null) {
                TheVoidKingdom.LOGGER.warning("ItemTemplate " + namespaceId + " 不存在");
                return null;
            }
            ItemStack itemStack = mmoItemTemplate.newBuilder().build().newBuilder().build();
            if (itemStack == null) {
                TheVoidKingdom.LOGGER.warning("物品 " + namespaceId + " build失败");
                return null;
            }
            switch (miType) {
                case "PICKAXE", "AXE", "SHOVEL", "HOE", "DAGGER" -> {
                    de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(itemStack);
                    NBTCompound nbtCompound = nbtItem.addCompound("itemsadder");
                    nbtCompound.setString("namespace", MMOItemsUtils.getIANamespace(miType));
                    nbtCompound.setString("id", miName.toLowerCase());
                    return nbtItem.getItem();
                }
                default -> {
                    return itemStack;
                }
            }
        } else {
            Material material = Material.getMaterial(namespaceId);
            if (material == null) {
                TheVoidKingdom.LOGGER.warning("不存在的material：" + namespaceId);
                return null;
            } else {
                return TheVoidKingdomUtils.checkClearVanillaStat(new ItemStack(material));
            }
        }
    }

    public static @NotNull ItemStack getItemStackNotNull(@Nullable String miType, @Nullable String miName) {
        if (miType == null || miName == null) {
            return getLostTextureIcon("<red><italic>Error: 传入参数为null");
        }
        return ItemsAdderUtils.getItemStackNotNull(MMOItemsUtils.getIANamespace(miType) + ":" + miName.toLowerCase());
    }

    //不支持获取mi物品，如有需要请使用#getItemStackNotNull(@Nullable String miType, @Nullable String miName)
    //因为mi物品不安全，必须进行检查
    public static @NotNull ItemStack getItemStackNoCheck(@NotNull String namespaceId) {
        if (namespaceId.contains(":")) {
            return CustomStack.getInstance(namespaceId).getItemStack();
        } else {
            Material material = Material.getMaterial(namespaceId);
            assert material != null;
            return TheVoidKingdomUtils.checkClearVanillaStat(new ItemStack(material));
        }
    }

    public static @NotNull ItemStack getLostTextureIcon(@Nullable String displayName) {
        ItemStack itemStack = new ItemStack(Material.WARPED_FUNGUS_ON_A_STICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(TheVoidKingdomUtils.deserializeMiniMessage(displayName == null ? "<red><italic>Error: 物品丢失" : displayName, false));
        itemMeta.lore(TheVoidKingdomUtils.deserializeList(List.of(
                "<red><italic>物品因bug而无法显示",
                "<red><italic>请通知管理员修复"
        ), false));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static @NotNull ItemStack getLostTextureIcon() {
        return getLostTextureIcon("<red><italic>Error: 物品丢失");
    }

    public static boolean checkNamespaceId(@Nullable ItemStack itemStack, String... namespaceIds) {
        String namespaceId = getItemNamespaceId(itemStack);
        for (String str : namespaceIds) {
            if (namespaceId.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public enum CustomItemType {
        IA_ITEM,
        MI_ITEM,
        VANILLA_ITEM
    }

    public static @Nullable CustomItemType getCustomItemType(@Nullable ItemStack itemStack) {
        if (itemStack == null || itemStack.getType().isAir() || itemStack.getAmount() == 0) {
            return null;
        }
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (nbtItem.hasType()) {
            return CustomItemType.MI_ITEM;
        } else {
            CustomStack customStack = CustomStack.byItemStack(itemStack);
            if (customStack != null) {
                return CustomItemType.IA_ITEM;
            } else {
                return CustomItemType.VANILLA_ITEM;
            }
        }
    }

    public static boolean isCustomItemStack(@Nullable ItemStack itemStack) {
        CustomItemType customItemType = getCustomItemType(itemStack);
        return customItemType == CustomItemType.IA_ITEM || customItemType == CustomItemType.MI_ITEM;
    }

    public static void placeBlock(@Nullable String blockNamespaceId, Location location) {
        if (!isBlockNamespaceIdExist(blockNamespaceId)) {
            return;
        }
        if (blockNamespaceId.contains(":")) {
            CustomBlock.place(blockNamespaceId, location);
        } else {
            location.getBlock().setType(Material.valueOf(blockNamespaceId));
        }
    }
}
