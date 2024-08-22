package flandre.scarlet.thevoidkingdom.functions.repair;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Registrable;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@Register(registerName = "可修复物品")
public record RepairItemType(String miType, String miName, Map<String, Integer> ingredients) implements Registrable {

    private static final RegisterManager<RepairItemType> registerManager = RegisterManager.getManager(RepairItemType.class);

    static {
        registerManager.create(new RepairItemType("HAMMER", "QINGTONG_HAMMER", Map.of("vkmaterials:bronze_ingot", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("HAMMER", "GANG_HAMMER", Map.of("vkmaterials:steel_ingot", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("HAMMER", "MOGANG_HAMMER", Map.of("vkmaterials:blood_steel_ingot", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("SWORD", "QINGTONG_SWORD", Map.of("vkmaterials:bronze_ingot", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("SWORD", "GANG_SWORD", Map.of("vkmaterials:steel_ingot", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("SWORD", "MOGANG_SWORD", Map.of("vkmaterials:blood_steel_ingot", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("SHIELD", "QINGTONG_SHIELD", Map.of("vkmaterials:bronze_ingot", 10, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("SHIELD", "GANG_SHIELD", Map.of("vkmaterials:steel_ingot", 10, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("SHIELD", "MOGANG_SHIELD", Map.of("vkmaterials:blood_steel_ingot", 10, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("BOW", "CUZHI_BOW", Map.of("vkmaterials:crude_leather", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("BOW", "KUANGYE_BOW", Map.of("vkmaterials:beast_leather", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("BOW", "SHOULIE_BOW", Map.of("vkmaterials:delicate_leather", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("BOW", "KARONG_BOW", Map.of("vkmaterials:delicate_leather", 8, "vkmaterials:void_crystal", 20, "GOLD_INGOT", 15)));
        registerManager.create(new RepairItemType("CROSS", "CUZHI_CROSS", Map.of("vkmaterials:crude_leather", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("CROSS", "KUANGYE_CROSS", Map.of("vkmaterials:beast_leather", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("CROSS", "SHOULIE_CROSS", Map.of("vkmaterials:delicate_leather", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("SHOT", "CUZHI_SHOT", Map.of("vkmaterials:crude_leather", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("SHOT", "KUANGYE_SHOT", Map.of("vkmaterials:beast_leather", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("SHOT", "SHOULIE_SHOT", Map.of("vkmaterials:delicate_leather", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("WAND", "ZIJING_WAND", Map.of("vkmaterials:amethyst_cloth", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("WAND", "MOJING_WAND", Map.of("vkmaterials:magic_crystal_cloth", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("WAND", "MONENG_WAND", Map.of("vkmaterials:magic_energy_cloth", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("LANTERN", "ZIJING_LANTERN", Map.of("vkmaterials:amethyst_cloth", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("LANTERN", "MOJING_LANTERN", Map.of("vkmaterials:magic_crystal_cloth", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("LANTERN", "MONENG_LANTERN", Map.of("vkmaterials:magic_energy_cloth", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("BOOK", "ZIJING_BOOK", Map.of("vkmaterials:amethyst_cloth", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("BOOK", "MOJING_BOOK", Map.of("vkmaterials:magic_crystal_cloth", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("BOOK", "MONENG_BOOK", Map.of("vkmaterials:magic_energy_cloth", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "QINGTONG_HELMET", Map.of("vkmaterials:bronze_ingot", 5, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "GANG_HELMET", Map.of("vkmaterials:steel_ingot", 5, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "MOGANG_HELMET", Map.of("vkmaterials:blood_steel_ingot", 5, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "QINGTONG_CHESTPLATE", Map.of("vkmaterials:bronze_ingot", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "GANG_CHESTPLATE", Map.of("vkmaterials:steel_ingot", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "MOGANG_CHESTPLATE", Map.of("vkmaterials:blood_steel_ingot", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "QINGTONG_LEGGINGS", Map.of("vkmaterials:bronze_ingot", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "GANG_LEGGINGS", Map.of("vkmaterials:steel_ingot", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "MOGANG_LEGGINGS", Map.of("vkmaterials:blood_steel_ingot", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "QINGTONG_BOOTS", Map.of("vkmaterials:bronze_ingot", 5, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "GANG_BOOTS", Map.of("vkmaterials:steel_ingot", 5, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_WARRIOR", "MOGANG_BOOTS", Map.of("vkmaterials:blood_steel_ingot", 5, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "CUZHI_HELMET", Map.of("vkmaterials:crude_leather", 5, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "KUANGYE_HELMET", Map.of("vkmaterials:beast_leather", 5, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "SHOULIE_HELMET", Map.of("vkmaterials:delicate_leather", 5, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "CUZHI_CHESTPLATE", Map.of("vkmaterials:crude_leather", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "KUANGYE_CHESTPLATE", Map.of("vkmaterials:beast_leather", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "SHOULIE_CHESTPLATE", Map.of("vkmaterials:delicate_leather", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "CUZHI_LEGGINGS", Map.of("vkmaterials:crude_leather", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "KUANGYE_LEGGINGS", Map.of("vkmaterials:beast_leather", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "SHOULIE_LEGGINGS", Map.of("vkmaterials:delicate_leather", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "CUZHI_BOOTS", Map.of("vkmaterials:crude_leather", 5, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "KUANGYE_BOOTS", Map.of("vkmaterials:beast_leather", 5, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_ARCHER", "SHOULIE_BOOTS", Map.of("vkmaterials:delicate_leather", 5, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "ZIJING_HELMET", Map.of("vkmaterials:amethyst_cloth", 5, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "MOJING_HELMET", Map.of("vkmaterials:magic_crystal_cloth", 5, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "MONENG_HELMET", Map.of("vkmaterials:magic_energy_cloth", 5, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "ZIJING_CHESTPLATE", Map.of("vkmaterials:amethyst_cloth", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "MOJING_CHESTPLATE", Map.of("vkmaterials:magic_crystal_cloth", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "MONENG_CHESTPLATE", Map.of("vkmaterials:magic_energy_cloth", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "ZIJING_LEGGINGS", Map.of("vkmaterials:amethyst_cloth", 8, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "MOJING_LEGGINGS", Map.of("vkmaterials:magic_crystal_cloth", 8, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "MONENG_LEGGINGS", Map.of("vkmaterials:magic_energy_cloth", 8, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "ZIJING_BOOTS", Map.of("vkmaterials:amethyst_cloth", 5, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "MOJING_BOOTS", Map.of("vkmaterials:magic_crystal_cloth", 5, "vkmaterials:void_crystal", 10)));
        registerManager.create(new RepairItemType("ARMOR_MAGE", "MONENG_BOOTS", Map.of("vkmaterials:magic_energy_cloth", 5, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("PICKAXE", "PICKAXE_XXX_QINGTONG", Map.of("vkmaterials:bronze_ingot", 3, "vkmaterials:void_crystal", 3)));
        registerManager.create(new RepairItemType("PICKAXE", "PICKAXE_XXXX_GANG", Map.of("vkmaterials:steel_ingot", 3, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("PICKAXE", "PICKAXE_XXXXX_MOGANG", Map.of("vkmaterials:blood_steel_ingot", 3, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("AXE", "AXE_XXX_QINGTONG", Map.of("vkmaterials:bronze_ingot", 3, "vkmaterials:void_crystal", 3)));
        registerManager.create(new RepairItemType("AXE", "AXE_XXXX_GANG", Map.of("vkmaterials:steel_ingot", 3, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("AXE", "AXE_XXXXX_MOGANG", Map.of("vkmaterials:blood_steel_ingot", 3, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("HOE", "HOE_XXX_QINGTONG", Map.of("vkmaterials:bronze_ingot", 2, "vkmaterials:void_crystal", 3)));
        registerManager.create(new RepairItemType("HOE", "HOE_XXXX_GANG", Map.of("vkmaterials:steel_ingot", 2, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("HOE", "HOE_XXXXX_MOGANG", Map.of("vkmaterials:blood_steel_ingot", 2, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("SHOVEL", "SHOVEL_XXX_QINGTONG", Map.of("vkmaterials:bronze_ingot", 1, "vkmaterials:void_crystal", 3)));
        registerManager.create(new RepairItemType("SHOVEL", "SHOVEL_XXXX_GANG", Map.of("vkmaterials:steel_ingot", 1, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("SHOVEL", "SHOVEL_XXXXX_MOGANG", Map.of("vkmaterials:blood_steel_ingot", 1, "vkmaterials:void_crystal", 20)));
        registerManager.create(new RepairItemType("DAGGER", "CHUDAO", Map.of("IRON_INGOT", 1, "vkmaterials:void_crystal", 3)));
        registerManager.create(new RepairItemType("DAGGER", "QINGTONG_DAGGER", Map.of("vkmaterials:bronze_ingot", 2, "vkmaterials:void_crystal", 3)));
        registerManager.create(new RepairItemType("DAGGER", "GANG_DAGGER", Map.of("vkmaterials:steel_ingot", 2, "vkmaterials:void_crystal", 5)));
        registerManager.create(new RepairItemType("DAGGER", "MOGANG_DAGGER", Map.of("vkmaterials:blood_steel_ingot", 2, "vkmaterials:void_crystal", 20)));
    }


    public static RepairItemType getType(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType().isAir()) {
            return null;
        }
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType()) {
            return null;
        }
        String miType = nbtItem.getType();
        String miName = nbtItem.getString("MMOITEMS_ITEM_ID");
        for (RepairItemType repairItemType : registerManager.getValidList()) {
            if (repairItemType.miName.equals(miName) && repairItemType.miType().equals(miType)) {
                return repairItemType;
            }
        }
        return null;
    }

    @Override
    public boolean isValid() {
        for (String namespaceId : ingredients.keySet()) {
            if (!ItemsAdderUtils.isItemNamespaceIdExist(namespaceId)) {
                TheVoidKingdom.LOGGER.warning("注册可修复物品失败，原因：物品" + namespaceId + "不存在");
                return false;
            }
        }
        for (Integer amount : ingredients.values()) {
            if (amount < 1 || amount > 64) {
                TheVoidKingdom.LOGGER.warning("注册可修复物品失败，原因：数量 " + amount + " 不合法");
                return false;
            }
        }
        return true;
    }
}
