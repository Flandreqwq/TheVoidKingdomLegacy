package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterExtraTask;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Register(registerName = "虚界工作台配方")
public record VoidCraftingTableRecipe(String key, Map<String, Integer> ingredients, String resultString,
                                      int resultAmount,
                                      String permission, RecipeCategory recipeCategory) implements VKRecipe {
    private static final RegisterManager<VoidCraftingTableRecipe> registerManager = RegisterManager.getManager(VoidCraftingTableRecipe.class);

    //方块配方
    static {
        registerManager.create(new VoidCraftingTableRecipe(
                "粗锡块", Map.of("vkmaterials:raw_tin", 9),
                "vkblocks:raw_tin_block", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "蕴魔青金石块", Map.of("vkmaterials:enchanted_lapis_lazuli", 9),
                "vkblocks:enchanted_lapis_block", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "锡块", Map.of("vkmaterials:tin_ingot", 9),
                "vkblocks:tin_block", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜块", Map.of("vkmaterials:bronze_ingot", 9),
                "vkblocks:bronze_block", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢块", Map.of("vkmaterials:steel_ingot", 9),
                "vkblocks:steel_block", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢块", Map.of("vkmaterials:blood_steel_ingot", 9),
                "vkblocks:blood_steel_block", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "蜂蜜书架", Map.of("BOOKSHELF", 1, "HONEY_BOTTLE", 4, "HONEYCOMB", 4),
                "vkblocks:honey_bookshelf", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "西瓜书架", Map.of("BOOKSHELF", 1, "MELON", 4, "MELON_SLICE", 4),
                "vkblocks:melon_bookshelf", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "海洋书架", Map.of("BOOKSHELF", 1, "PRISMARINE_BRICKS", 8, "PUFFERFISH_BUCKET", 1),
                "vkblocks:sea_bookshelf", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "锻心海洋书架", Map.of("vkblocks:sea_bookshelf", 1, "PRISMARINE_SHARD", 8, "HEART_OF_THE_SEA", 1),
                "vkblocks:sea_heart_bookshelf", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "澄澈海洋书架", Map.of("vkblocks:sea_bookshelf", 1, "PRISMARINE_CRYSTALS", 8, "SEA_LANTERN", 1),
                "vkblocks:sea_crystal_bookshelf", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "校准海洋书架", Map.of("vkblocks:sea_bookshelf", 1, "HONEY_BOTTLE", 4, "AMETHYST_CLUSTER", 4),
                "vkblocks:sea_calibration_bookshelf", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "深暗书架", Map.of("BOOKSHELF", 1, "CRACKED_DEEPSLATE_TILES", 4, "vkmaterials:deep_dark_dust", 4),
                "vkblocks:deep_bookshelf", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "回响深暗书架", Map.of("vkblocks:deep_bookshelf", 1, "ECHO_SHARD", 4, "CANDLE", 4, "vkmaterials:deep_dark_dust", 4),
                "vkblocks:echo_deep_bookshelf", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "灵魂深暗书架", Map.of("vkblocks:deep_bookshelf", 1, "SOUL_LANTERN", 4, "vkmaterials:deep_dark_dust", 4),
                "vkblocks:soul_deep_bookshelf", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "回响幽匿书架", Map.of("vkblocks:echo_deep_bookshelf", 1, "SCULK", 4, "SCULK_CATALYST", 4, "vkmaterials:deep_dark_dust", 4),
                "vkblocks:echo_sculk_bookshelf", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "灵魂幽匿书架", Map.of("vkblocks:soul_deep_bookshelf", 1, "SCULK", 4, "SCULK_CATALYST", 4, "vkmaterials:deep_dark_dust", 4),
                "vkblocks:soul_sculk_bookshelf", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "深渊石", Map.of("vkmaterials:depth_pebble", 4),
                "vkblocks:depthrock", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "深渊石砖", Map.of("vkblocks:depthrock", 1),
                "vkblocks:depthrock_bricks", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "深渊石柱", Map.of("vkblocks:depthrock", 1),
                "vkblocks:depthrock_pillar", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "巨物图腾柱", Map.of("vkblocks:depthrock_pillar", 1, "vkmaterials:geliya_totem_shard", 4, "vkmaterials:deep_dark_dust", 4),
                "vkblocks:geliya_totem_pillar", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "亡魂图腾柱", Map.of("vkblocks:depthrock_pillar", 1, "vkmaterials:karong_totem_shard", 4, "vkmaterials:deep_dark_dust", 4),
                "vkblocks:karong_totem_pillar", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "紫晶图腾柱", Map.of("vkblocks:depthrock_pillar", 1, "vkmaterials:weiersi_totem_shard", 4, "vkmaterials:deep_dark_dust", 4),
                "vkblocks:weiersi_totem_pillar", 1,
                null, RecipeCategory.BLOCK)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "深渊核心", Map.of("GOLD_BLOCK", 1, "vkmaterials:depth_pebble", 4, "vkmaterials:deep_dark_dust", 4),
                "vkblocks:depth_core", 1,
                null, RecipeCategory.BLOCK)
        );
    }

    //材料配方
    static {
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢锭", Map.of("vkmaterials:steel_ingot", 1, "vkmaterials:blood_of_the_giant", 4, "vkmaterials:void_crystal", 4),
                "vkmaterials:blood_steel_ingot", 1,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狂野兽皮", Map.of("vkmaterials:crude_leather", 1, "vkmaterials:beast_blood", 3),
                "vkmaterials:beast_leather", 1,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "精致皮革", Map.of("vkmaterials:beast_leather", 1, "vkmaterials:soul_of_the_undead", 4, "vkmaterials:void_crystal", 4),
                "vkmaterials:delicate_leather", 1,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔晶布匹", Map.of("vkmaterials:amethyst_cloth", 1, "vkmaterials:magic_crystal_dust", 4),
                "vkmaterials:magic_crystal_cloth", 1,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔能布匹", Map.of("vkmaterials:magic_crystal_cloth", 1, "vkmaterials:tear_of_the_lich", 4, "vkmaterials:void_crystal", 4),
                "vkmaterials:magic_energy_cloth", 1,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔晶粉", Map.of("vkmaterials:magic_crystal_shard", 1),
                "vkmaterials:magic_crystal_dust", 2,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "水晶催生粉", Map.of("vkmaterials:amethyst_dust", 4, "vkmaterials:magic_crystal_dust", 4),
                "vkmaterials:crystal_growth_dust", 8,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "深渊石子", Map.of("vkmaterials:pebble", 1, "vkmaterials:deep_dark_dust", 1),
                "vkmaterials:depth_pebble", 1,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "附魔之瓶", Map.of("vkmaterials:enchanted_lapis_lazuli_nugget", 1, "GLASS_BOTTLE", 1),
                "EXPERIENCE_BOTTLE", 1,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "蕴魔青金石", Map.of("vkmaterials:enchanted_lapis_lazuli_nugget", 9),
                "vkmaterials:enchanted_lapis_lazuli", 1,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "蕴魔青金石", Map.of("vkblocks:enchanted_lapis_block", 1),
                "vkmaterials:enchanted_lapis_lazuli", 9,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "黑暗料理桶", Map.of("vkmaterials:steel_ingot", 4, "vkmaterials:deep_dark_dust", 8, "ROTTEN_FLESH", 8, "BONE", 8),
                "vkmaterials:geliya_sacrifice", 1,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "伪造的黄金勋章", Map.of("vkmaterials:beast_leather", 4, "vkmaterials:deep_dark_dust", 8, "YELLOW_DYE", 8, "IRON_INGOT", 1),
                "vkmaterials:karong_sacrifice", 1,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "幽雅紫晶花", Map.of("vkmaterials:magic_crystal_cloth", 4, "vkmaterials:deep_dark_dust", 8, "POPPY", 1, "AMETHYST_BLOCK", 8),
                "vkmaterials:weiersi_sacrifice", 1,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "粗锡9", Map.of("vkblocks:raw_tin_block", 1),
                "vkmaterials:raw_tin", 9,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "锡锭9", Map.of("vkblocks:tin_block", 1),
                "vkmaterials:tin_ingot", 9,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜锭9", Map.of("vkblocks:bronze_block", 1),
                "vkmaterials:bronze_ingot", 9,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢锭9", Map.of("vkblocks:steel_block", 1),
                "vkmaterials:steel_ingot", 9,
                null, RecipeCategory.MATERIAL)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢锭9", Map.of("vkblocks:blood_steel_block", 1),
                "vkmaterials:blood_steel_ingot", 9,
                null, RecipeCategory.MATERIAL)
        );
    }

    //工具饰品配方
    static {
        registerManager.create(new VoidCraftingTableRecipe(
                "厨刀", Map.of("IRON_INGOT", 1, "STICK", 1),
                "DAGGER#CHUDAO", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "饰品盒", Map.of("vkmaterials:void_crystal", 4, "CHEST", 1, "GOLD_INGOT", 1, "RED_DYE", 4),
                "vkmaterials:accessory_box", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜镐", Map.of("vkmaterials:bronze_ingot", 3, "STICK", 2),
                "PICKAXE#PICKAXE_XXX_QINGTONG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜斧", Map.of("vkmaterials:bronze_ingot", 3, "STICK", 2),
                "AXE#AXE_XXX_QINGTONG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜锹", Map.of("vkmaterials:bronze_ingot", 1, "STICK", 2),
                "SHOVEL#SHOVEL_XXX_QINGTONG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜锄", Map.of("vkmaterials:bronze_ingot", 2, "STICK", 2),
                "HOE#HOE_XXX_QINGTONG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜匕首", Map.of("vkmaterials:bronze_ingot", 2, "STICK", 1),
                "DAGGER#QINGTONG_DAGGER", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢镐", Map.of("vkmaterials:steel_ingot", 3, "STICK", 2),
                "PICKAXE#PICKAXE_XXXX_GANG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢斧", Map.of("vkmaterials:steel_ingot", 3, "STICK", 2),
                "AXE#AXE_XXXX_GANG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢锹", Map.of("vkmaterials:steel_ingot", 1, "STICK", 2),
                "SHOVEL#SHOVEL_XXXX_GANG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢锄", Map.of("vkmaterials:steel_ingot", 2, "STICK", 2),
                "HOE#HOE_XXXX_GANG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢匕首", Map.of("vkmaterials:steel_ingot", 2, "STICK", 1),
                "DAGGER#GANG_DAGGER", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "玻璃镐", Map.of("GLASS", 3, "STICK", 2),
                "PICKAXE#PICKAXE_XXXXX_BOLI", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "玻璃斧", Map.of("GLASS", 3, "STICK", 2),
                "AXE#AXE_XXXXX_BOLI", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "玻璃锹", Map.of("GLASS", 1, "STICK", 2),
                "SHOVEL#SHOVEL_XXXXX_BOLI", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "玻璃锄", Map.of("GLASS", 2, "STICK", 2),
                "HOE#HOE_XXXXX_BOLI", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "玻璃匕首", Map.of("GLASS", 2, "STICK", 1),
                "DAGGER#BOLI_DAGGER", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢镐", Map.of("vkmaterials:blood_steel_ingot", 3, "STICK", 2),
                "PICKAXE#PICKAXE_XXXXX_MOGANG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢斧", Map.of("vkmaterials:blood_steel_ingot", 3, "STICK", 2),
                "AXE#AXE_XXXXX_MOGANG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢锹", Map.of("vkmaterials:blood_steel_ingot", 1, "STICK", 2),
                "SHOVEL#SHOVEL_XXXXX_MOGANG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢锄", Map.of("vkmaterials:blood_steel_ingot", 2, "STICK", 2),
                "HOE#HOE_XXXXX_MOGANG", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢匕首", Map.of("vkmaterials:blood_steel_ingot", 2, "STICK", 1),
                "DAGGER#MOGANG_DAGGER", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "金制护符", Map.of("GOLD_BLOCK", 1, "vkmaterials:magic_crystal_shard", 4, "vkmaterials:void_crystal", 1),
                "ACCESSORY#JINZHIHUFU", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "虚界护符", Map.of("vkaccessories:jinzhihufu", 1, "vkmaterials:void_delicate_crystal", 4),
                "ACCESSORY#XVJIEHUFU", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "幸运兔脚", Map.of("RABBIT_FOOT", 1, "vkmaterials:lucky_clover", 1, "vkmaterials:void_crystal", 1),
                "ACCESSORY#XINGYUNTUJIAO", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "紫水晶透镜", Map.of("AMETHYST_SHARD", 16, "vkmaterials:void_crystal", 1),
                "ACCESSORY#ZISHUIJINGTOUJING", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "闪避布", Map.of("FEATHER", 4, "vkmaterials:cloth", 4, "vkmaterials:void_crystal", 1),
                "ACCESSORY#SHANBIBU", 1,
                null, RecipeCategory.TOOL_ADD_ACCESSORY)
        );
    }

    //武器配方
    static {
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜战锤", Map.of("vkmaterials:bronze_ingot", 14, "STICK", 2),
                "HAMMER#QINGTONG_HAMMER", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢战锤", Map.of("vkmaterials:steel_ingot", 14, "STICK", 2),
                "HAMMER#GANG_HAMMER", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢战锤", Map.of("vkmaterials:blood_steel_ingot", 14),
                "HAMMER#MOGANG_HAMMER", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜长剑", Map.of("vkmaterials:bronze_ingot", 14, "STICK", 2),
                "SWORD#QINGTONG_SWORD", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢长剑", Map.of("vkmaterials:steel_ingot", 14, "STICK", 2),
                "SWORD#GANG_SWORD", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢长剑", Map.of("vkmaterials:blood_steel_ingot", 14),
                "SWORD#MOGANG_SWORD", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜圆盾", Map.of("vkmaterials:bronze_ingot", 20),
                "SHIELD#QINGTONG_SHIELD", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢盾牌", Map.of("vkmaterials:steel_ingot", 20),
                "SHIELD#GANG_SHIELD", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢盾牌", Map.of("vkmaterials:blood_steel_ingot", 20),
                "SHIELD#MOGANG_SHIELD", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "粗制长弓", Map.of("vkmaterials:bronze_ingot", 6, "vkmaterials:crude_leather", 8),
                "BOW#CUZHI_BOW", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狂野长弓", Map.of("vkmaterials:steel_ingot", 6, "vkmaterials:beast_leather", 8),
                "BOW#KUANGYE_BOW", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狩猎长弓", Map.of("vkmaterials:blood_steel_ingot", 6, "vkmaterials:delicate_leather", 8),
                "BOW#SHOULIE_BOW", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "粗制吹箭筒", Map.of("vkmaterials:bronze_ingot", 6, "vkmaterials:crude_leather", 8),
                "SHOT#CUZHI_SHOT", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狂野吹箭筒", Map.of("vkmaterials:steel_ingot", 6, "vkmaterials:beast_leather", 8),
                "SHOT#KUANGYE_SHOT", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狩猎吹箭筒", Map.of("vkmaterials:blood_steel_ingot", 6, "vkmaterials:delicate_leather", 8),
                "SHOT#SHOULIE_SHOT", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "粗制连弩", Map.of("vkmaterials:bronze_ingot", 6, "vkmaterials:crude_leather", 8),
                "CROSS#CUZHI_CROSS", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狂野连弩", Map.of("vkmaterials:steel_ingot", 6, "vkmaterials:beast_leather", 8),
                "CROSS#KUANGYE_CROSS", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狩猎连弩", Map.of("vkmaterials:blood_steel_ingot", 6, "vkmaterials:delicate_leather", 8),
                "CROSS#SHOULIE_CROSS", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "紫晶法杖", Map.of("vkmaterials:bronze_ingot", 6, "vkmaterials:amethyst_cloth", 8),
                "WAND#ZIJING_WAND", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔晶法杖", Map.of("vkmaterials:steel_ingot", 6, "vkmaterials:magic_crystal_cloth", 8),
                "WAND#MOJING_WAND", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔能法杖", Map.of("vkmaterials:blood_steel_ingot", 6, "vkmaterials:magic_energy_cloth", 8),
                "WAND#MONENG_WAND", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "紫晶提灯", Map.of("vkmaterials:bronze_ingot", 6, "vkmaterials:amethyst_cloth", 8),
                "LANTERN#ZIJING_LANTERN", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔晶提灯", Map.of("vkmaterials:steel_ingot", 6, "vkmaterials:magic_crystal_cloth", 8),
                "LANTERN#MOJING_LANTERN", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔能提灯", Map.of("vkmaterials:blood_steel_ingot", 6, "vkmaterials:magic_energy_cloth", 8),
                "LANTERN#MONENG_LANTERN", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "紫晶法典", Map.of("vkmaterials:crude_leather", 6, "vkmaterials:amethyst_cloth", 8),
                "BOOK#ZIJING_BOOK", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔晶法典", Map.of("vkmaterials:beast_leather", 6, "vkmaterials:magic_crystal_cloth", 8),
                "BOOK#MOJING_BOOK", 1,
                null, RecipeCategory.WEAPON)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔能法典", Map.of("vkmaterials:delicate_leather", 6, "vkmaterials:magic_energy_cloth", 8),
                "BOOK#MONENG_BOOK", 1,
                null, RecipeCategory.WEAPON)
        );
    }

    //护甲配方
    static {
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜战盔", Map.of("vkmaterials:bronze_ingot", 10),
                "ARMOR_WARRIOR#QINGTONG_HELMET", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜战甲", Map.of("vkmaterials:bronze_ingot", 16),
                "ARMOR_WARRIOR#QINGTONG_CHESTPLATE", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜腿甲", Map.of("vkmaterials:bronze_ingot", 16),
                "ARMOR_WARRIOR#QINGTONG_LEGGINGS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "青铜战靴", Map.of("vkmaterials:bronze_ingot", 10),
                "ARMOR_WARRIOR#QINGTONG_BOOTS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢战盔", Map.of("vkmaterials:steel_ingot", 10),
                "ARMOR_WARRIOR#GANG_HELMET", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢战甲", Map.of("vkmaterials:steel_ingot", 16),
                "ARMOR_WARRIOR#GANG_CHESTPLATE", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢腿甲", Map.of("vkmaterials:steel_ingot", 16),
                "ARMOR_WARRIOR#GANG_LEGGINGS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "钢战靴", Map.of("vkmaterials:steel_ingot", 10),
                "ARMOR_WARRIOR#GANG_BOOTS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢战盔", Map.of("vkmaterials:blood_steel_ingot", 10),
                "ARMOR_WARRIOR#MOGANG_HELMET", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢战甲", Map.of("vkmaterials:blood_steel_ingot", 16),
                "ARMOR_WARRIOR#MOGANG_CHESTPLATE", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢腿甲", Map.of("vkmaterials:blood_steel_ingot", 16),
                "ARMOR_WARRIOR#MOGANG_LEGGINGS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔钢战靴", Map.of("vkmaterials:blood_steel_ingot", 10),
                "ARMOR_WARRIOR#MOGANG_BOOTS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "粗制头饰", Map.of("vkmaterials:crude_leather", 10),
                "ARMOR_ARCHER#CUZHI_HELMET", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "粗制护胸", Map.of("vkmaterials:crude_leather", 16),
                "ARMOR_ARCHER#CUZHI_CHESTPLATE", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "粗制护腿", Map.of("vkmaterials:crude_leather", 16),
                "ARMOR_ARCHER#CUZHI_LEGGINGS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "粗制便靴", Map.of("vkmaterials:crude_leather", 10),
                "ARMOR_ARCHER#CUZHI_BOOTS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狂野头饰", Map.of("vkmaterials:beast_leather", 10),
                "ARMOR_ARCHER#KUANGYE_HELMET", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狂野护胸", Map.of("vkmaterials:beast_leather", 16),
                "ARMOR_ARCHER#KUANGYE_CHESTPLATE", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狂野护腿", Map.of("vkmaterials:beast_leather", 16),
                "ARMOR_ARCHER#KUANGYE_LEGGINGS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狂野便靴", Map.of("vkmaterials:beast_leather", 10),
                "ARMOR_ARCHER#KUANGYE_BOOTS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狩猎头饰", Map.of("vkmaterials:delicate_leather", 10),
                "ARMOR_ARCHER#SHOULIE_HELMET", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狩猎护胸", Map.of("vkmaterials:delicate_leather", 16),
                "ARMOR_ARCHER#SHOULIE_CHESTPLATE", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狩猎护腿", Map.of("vkmaterials:delicate_leather", 16),
                "ARMOR_ARCHER#SHOULIE_LEGGINGS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "狩猎便靴", Map.of("vkmaterials:delicate_leather", 10),
                "ARMOR_ARCHER#SHOULIE_BOOTS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "紫晶兜帽", Map.of("vkmaterials:amethyst_cloth", 10),
                "ARMOR_MAGE#ZIJING_HELMET", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "紫晶法袍", Map.of("vkmaterials:amethyst_cloth", 16),
                "ARMOR_MAGE#ZIJING_CHESTPLATE", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "紫晶长裤", Map.of("vkmaterials:amethyst_cloth", 16),
                "ARMOR_MAGE#ZIJING_LEGGINGS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "紫晶软鞋", Map.of("vkmaterials:amethyst_cloth", 10),
                "ARMOR_MAGE#ZIJING_BOOTS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔晶兜帽", Map.of("vkmaterials:magic_crystal_cloth", 10),
                "ARMOR_MAGE#MOJING_HELMET", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔晶法袍", Map.of("vkmaterials:magic_crystal_cloth", 16),
                "ARMOR_MAGE#MOJING_CHESTPLATE", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔晶长裤", Map.of("vkmaterials:magic_crystal_cloth", 16),
                "ARMOR_MAGE#MOJING_LEGGINGS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔晶软鞋", Map.of("vkmaterials:magic_crystal_cloth", 10),
                "ARMOR_MAGE#MOJING_BOOTS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔能兜帽", Map.of("vkmaterials:magic_energy_cloth", 10),
                "ARMOR_MAGE#MONENG_HELMET", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔能法袍", Map.of("vkmaterials:magic_energy_cloth", 16),
                "ARMOR_MAGE#MONENG_CHESTPLATE", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔能长裤", Map.of("vkmaterials:magic_energy_cloth", 16),
                "ARMOR_MAGE#MONENG_LEGGINGS", 1,
                null, RecipeCategory.ARMOR)
        );
        registerManager.create(new VoidCraftingTableRecipe(
                "魔能软鞋", Map.of("vkmaterials:magic_energy_cloth", 10),
                "ARMOR_MAGE#MONENG_BOOTS", 1,
                null, RecipeCategory.ARMOR)
        );
    }

    public enum RecipeCategory {
        BLOCK,
        MATERIAL,
        TOOL_ADD_ACCESSORY,
        WEAPON,
        ARMOR
    }

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_void_crafting_table:";
    }

    @Override
    public String getMenuDisplayName() {
        return "虚界工作台";
    }

    private static final List<Integer> SLOTS = List.of(10, 11, 12, 13, 19, 20, 21, 22, 28, 29, 30, 31);

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        Iterator<Integer> iterator = SLOTS.iterator();
        ingredients.forEach((namespaceId, amount) -> {
            ItemStack itemStack = ItemsAdderUtils.getItemStackNoCheck(namespaceId);
            while (amount > 0) {
                if (amount > 64) {
                    itemStack.setAmount(64);
                    amount -= 64;
                } else {
                    itemStack.setAmount(amount);
                    amount -= amount;
                }
                if (iterator.hasNext()) {
                    inventory.setItem(iterator.next(), itemStack);
                } else {
                    TheVoidKingdom.LOGGER.warning("工作站配方所需物品过多，无法完全显示");
                    break;
                }
            }
        });
        ItemStack resultItemStack;
        if (resultString.contains("#")) {
            String[] strings = resultString.split("#");
            resultItemStack = ItemsAdderUtils.getItemStackNotNull(strings[0], strings[1]);
        } else {
            resultItemStack = ItemsAdderUtils.getItemStackNoCheck(resultString);
        }
        resultItemStack.setAmount(resultAmount);
        inventory.setItem(25, resultItemStack);
        return inventory;
    }


    @Override
    public boolean checkInput(String namespaceId) {
        return ingredients.containsKey(namespaceId);
    }

    @Override
    public boolean checkResult(String namespaceId) {
        if (resultString.contains("#")) {
            String[] strings = resultString.split("#");
            String miType = strings[0];
            String miName = strings[1];
            return (MMOItemsUtils.getIANamespace(miType) + ":" + miName.toLowerCase()).equals(namespaceId);
        } else {
            return resultString.equals(namespaceId);
        }
    }

    @Override
    public boolean isValid() {
        for (String namespaceId : ingredients.keySet()) {
            if (!ItemsAdderUtils.isItemNamespaceIdExist(namespaceId)) {
                TheVoidKingdom.LOGGER.warning("注册虚界工作台配方失败,原因: 材料物品 " + namespaceId + " 不存在");
                return false;
            }
        }
        for (Integer amount : ingredients.values()) {
            if (amount < 1) {
                TheVoidKingdom.LOGGER.warning("注册虚界工作台配方失败,原因: 材料物品数量不合法");
                return false;
            }
        }
        if (resultString.contains("#")) {
            String[] strings = resultString.split("#");
            String miType = strings[0];
            String miName = strings[1];
            MMOItemTemplate mmoItemTemplate = MMOItems.plugin.getTemplates().getTemplate(MMOItems.plugin.getTypes().get(miType), miName);
            if (mmoItemTemplate == null) {
                TheVoidKingdom.LOGGER.warning("注册虚界工作台配方失败,原因: ItemTemplate " + resultString + " 不存在");
                return false;
            }
        } else {
            if (!ItemsAdderUtils.isItemNamespaceIdExist(resultString)) {
                TheVoidKingdom.LOGGER.warning("注册虚界工作台配方失败,原因: 材料物品 " + resultString + " 不存在");
                return false;
            }
        }
        if (resultAmount > 64 || resultAmount < 1) {
            TheVoidKingdom.LOGGER.warning("注册虚界工作台配方失败,原因: 结果物品数量不合法");
            return false;
        }
        return true;
    }


    public static final Map<VoidCraftingTableRecipe.RecipeCategory, List<VoidCraftingTableRecipe>> RECIPE_CATEGORY_MAP = new HashMap<>();
    public static final Map<String, VoidCraftingTableRecipe> RECIPE_KEY_MAP = new HashMap<>();

    @RegisterExtraTask
    private static final Runnable extraTask = () -> {
        RECIPE_CATEGORY_MAP.clear();
        RECIPE_KEY_MAP.clear();
        List<VoidCraftingTableRecipe> validList = RegisterManager.getManager(VoidCraftingTableRecipe.class).getValidList();
        validList.forEach(voidCraftingTableRecipe -> {
            RECIPE_KEY_MAP.put(voidCraftingTableRecipe.key(), voidCraftingTableRecipe);
            List<VoidCraftingTableRecipe> list = RECIPE_CATEGORY_MAP.computeIfAbsent(voidCraftingTableRecipe.recipeCategory(), recipeCategory -> new ArrayList<>());
            list.add(voidCraftingTableRecipe);
        });
    };
}
