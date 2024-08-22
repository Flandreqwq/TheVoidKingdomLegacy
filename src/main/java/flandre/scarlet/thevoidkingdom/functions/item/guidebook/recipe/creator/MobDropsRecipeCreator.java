package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.creator;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.MobDropsRecipe;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.utils.collections.WeightedCollection;
import io.lumine.mythic.core.drops.Drop;
import io.lumine.mythic.core.drops.DropTable;
import io.lumine.mythic.core.drops.droppables.CustomDrop;
import io.lumine.mythic.core.drops.droppables.DropTableDrop;
import io.lumine.mythic.core.drops.droppables.ExperienceDrop;
import io.lumine.mythic.core.drops.droppables.ItemDrop;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Bean
public class MobDropsRecipeCreator implements RecipeCreator {
    private static final RegisterManager<MobDropsRecipe> registerManager = RegisterManager.getManager(MobDropsRecipe.class);

    private static final List<String> mobNames = List.of(
            "武装僵尸-1",
            "武装僵尸-2",
            "武装僵尸-3",
            "武装僵尸村民-1",
            "武装僵尸村民-2",
            "武装僵尸村民-3",
            "派对苦力怕-1",
            "派对苦力怕-2",
            "派对苦力怕-3",
            "织网毒蛛-1",
            "织网毒蛛-2",
            "织网毒蛛-3",
            "敏捷骷髅-1",
            "敏捷骷髅-2",
            "敏捷骷髅-3",
            "敏捷流浪者-1",
            "敏捷流浪者-2",
            "敏捷流浪者-3",
            "潮涌溺尸-1",
            "潮涌溺尸-2",
            "潮涌溺尸-3",
            "流沙尸壳-1",
            "流沙尸壳-2",
            "流沙尸壳-3",
            "歌利亚",
            "喀戎",
            "薇尔斯"
    );

    public void createVanillaMobDropRecipes() {
        registerManager.create(new MobDropsRecipe("鸡", "vkguis:icon_chicken", Set.of("CHICKEN", "FEATHER", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("牛", "vkguis:icon_cow", Set.of("BEEF", "LEATHER", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("猪", "vkguis:icon_pig", Set.of("PORKCHOP", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("绵羊", "vkguis:icon_sheep", Set.of("MUTTON", "WHITE_WOOL", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("兔子", "vkguis:icon_rabbit", Set.of("RABBIT_HIDE", "RABBIT", "RABBIT_FOOT", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("哞菇", "vkguis:icon_mooshroom", Set.of("BEEF", "LEATHER", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("马", "vkguis:icon_horse", Set.of("LEATHER", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("驴", "vkguis:icon_donkey", Set.of("LEATHER", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("骡", "vkguis:icon_mule", Set.of("LEATHER", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("羊驼", "vkguis:icon_llama", Set.of("LEATHER", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("骆驼", "vkguis:icon_camel", Set.of("vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("狼", "vkguis:icon_wolf", Set.of("vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("山羊", "vkguis:icon_goat", Set.of("vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("豹猫", "vkguis:icon_ocelot", Set.of("vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("狐狸", "vkguis:icon_fox", Set.of("vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("北极熊", "vkguis:icon_polar_bear", Set.of("COD", "SALMON", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("熊猫", "vkguis:icon_panda", Set.of("BAMBOO", "vkmaterials:beast_blood")));
        registerManager.create(new MobDropsRecipe("鹦鹉", "vkguis:icon_parrot", Set.of("FEATHER")));
        registerManager.create(new MobDropsRecipe("猫", "vkguis:icon_cat", Set.of("STRING")));
        registerManager.create(new MobDropsRecipe("海豚", "vkguis:icon_dolphin", Set.of("COD")));
        registerManager.create(new MobDropsRecipe("鳕鱼", "vkguis:icon_cod", Set.of("COD", "BONE_MEAL")));
        registerManager.create(new MobDropsRecipe("鲑鱼", "vkguis:icon_salmon", Set.of("SALMON", "BONE_MEAL")));
        registerManager.create(new MobDropsRecipe("热带鱼", "vkguis:icon_tropical_fish", Set.of("TROPICAL_FISH", "BONE_MEAL")));
        registerManager.create(new MobDropsRecipe("河豚", "vkguis:icon_puffer_fish", Set.of("PUFFERFISH", "BONE_MEAL")));
        registerManager.create(new MobDropsRecipe("海龟", "vkguis:icon_turtle", Set.of("SEAGRASS", "BOWL")));
        registerManager.create(new MobDropsRecipe("鱿鱼", "vkguis:icon_squid", Set.of("INK_SAC")));
        registerManager.create(new MobDropsRecipe("发光鱿鱼", "vkguis:icon_glow_squid", Set.of("GLOW_INK_SAC")));
        registerManager.create(new MobDropsRecipe("雪傀儡", "vkguis:icon_snow_golem", Set.of("SNOWBALL")));
        registerManager.create(new MobDropsRecipe("铁傀儡", "vkguis:icon_iron_golem", Set.of("IRON_INGOT", "POPPY")));
        registerManager.create(new MobDropsRecipe("炽足兽", "vkguis:icon_strider", Set.of("STRING")));
        registerManager.create(new MobDropsRecipe("僵尸", "vkguis:icon_zombie", Set.of("ROTTEN_FLESH", "IRON_INGOT", "CARROT", "POTATO")));
        registerManager.create(new MobDropsRecipe("僵尸村民", "vkguis:icon_zombie_villager", Set.of("ROTTEN_FLESH", "IRON_INGOT", "CARROT", "POTATO")));
        registerManager.create(new MobDropsRecipe("尸壳", "vkguis:icon_husk", Set.of("ROTTEN_FLESH", "IRON_INGOT", "CARROT", "POTATO")));
        registerManager.create(new MobDropsRecipe("溺尸", "vkguis:icon_drowned", Set.of("ROTTEN_FLESH", "COPPER_INGOT")));
        registerManager.create(new MobDropsRecipe("骷髅", "vkguis:icon_skeleton", Set.of("BONE", "ARROW")));
        registerManager.create(new MobDropsRecipe("流浪者", "vkguis:icon_stray", Set.of("BONE", "ARROW")));
        registerManager.create(new MobDropsRecipe("骷髅马", "vkguis:icon_skeleton_horse", Set.of("BONE")));
        registerManager.create(new MobDropsRecipe("蜘蛛", "vkguis:icon_spider", Set.of("STRING", "SPIDER_EYE")));
        registerManager.create(new MobDropsRecipe("洞穴蜘蛛", "vkguis:icon_cave_spider", Set.of("STRING", "SPIDER_EYE")));
        registerManager.create(new MobDropsRecipe("苦力怕", "vkguis:icon_creeper", Set.of("GUNPOWDER")));
        registerManager.create(new MobDropsRecipe("女巫", "vkguis:icon_witch", Set.of("GLASS_BOTTLE", "GLOWSTONE_DUST", "GUNPOWDER", "REDSTONE", "SPIDER_EYE", "SUGAR", "STICK")));
        registerManager.create(new MobDropsRecipe("末影人", "vkguis:icon_enderman", Set.of("ENDER_PEARL")));
        registerManager.create(new MobDropsRecipe("史莱姆", "vkguis:icon_slime", Set.of("SLIME_BALL")));
        registerManager.create(new MobDropsRecipe("幻翼", "vkguis:icon_phantom", Set.of("PHANTOM_MEMBRANE")));
        registerManager.create(new MobDropsRecipe("卫道士", "vkguis:icon_vindicator", Set.of("EMERALD")));
        registerManager.create(new MobDropsRecipe("唤魔者", "vkguis:icon_evoker", Set.of("EMERALD", "TOTEM_OF_UNDYING")));
        registerManager.create(new MobDropsRecipe("劫掠兽", "vkguis:icon_ravager", Set.of("SADDLE")));
        registerManager.create(new MobDropsRecipe("守卫者", "vkguis:icon_guardian", Set.of("PRISMARINE_SHARD", "PRISMARINE_CRYSTALS", "COD")));
        registerManager.create(new MobDropsRecipe("远古守卫者", "vkguis:icon_elder_guardian", Set.of("PRISMARINE_SHARD", "PRISMARINE_CRYSTALS", "COD", "WET_SPONGE", "TIDE_ARMOR_TRIM_SMITHING_TEMPLATE")));
        registerManager.create(new MobDropsRecipe("监守者", "vkguis:icon_warden", Set.of("SCULK_CATALYST")));
        registerManager.create(new MobDropsRecipe("僵尸猪灵", "vkguis:icon_zombified_piglin", Set.of("ROTTEN_FLESH", "GOLD_NUGGET")));
        registerManager.create(new MobDropsRecipe("烈焰人", "vkguis:icon_blaze", Set.of("BLAZE_ROD")));
        registerManager.create(new MobDropsRecipe("岩浆怪", "vkguis:icon_magma_cube", Set.of("MAGMA_CREAM")));
        registerManager.create(new MobDropsRecipe("恶魂", "vkguis:icon_ghast", Set.of("GHAST_TEAR", "GUNPOWDER")));
        registerManager.create(new MobDropsRecipe("疣猪兽", "vkguis:icon_hoglin", Set.of("PORKCHOP", "LEATHER")));
        registerManager.create(new MobDropsRecipe("僵尸疣猪兽", "vkguis:icon_zoglin", Set.of("ROTTEN_FLESH")));
        registerManager.create(new MobDropsRecipe("凋灵骷髅", "vkguis:icon_wither_skeleton", Set.of("BONE", "COAL", "WITHER_SKELETON_SKULL")));
        registerManager.create(new MobDropsRecipe("凋灵", "vkguis:icon_wither", Set.of("NETHER_STAR")));
        registerManager.create(new MobDropsRecipe("潜影贝", "vkguis:icon_shulker", Set.of("SHULKER_SHELL")));
    }

    @Override
    public void initialize() {
        createVanillaMobDropRecipes();
        for (String mobName : mobNames) {
            MythicMob mythicMob = MythicBukkit.inst().getMobManager().getMythicMob(mobName).orElse(null);
            if (mythicMob == null) {
                TheVoidKingdom.LOGGER.warning("不存在的MythicMobs怪物: " + mobName);
                continue;
            }
            DropTable dropTable = mythicMob.getDropTable();
            List<Drop> dropList = getSimpleDrops(dropTable);
            Set<String> dropItemNamespaceIds = new HashSet<>();
            for (Drop drop : dropList) {
                String namespaceId = getDropNamespaceId(drop);
                if (namespaceId != null) {
                    dropItemNamespaceIds.add(namespaceId);
                }
            }
            registerManager.create(new MobDropsRecipe(mythicMob.getDisplayName().get(), getIconItemNamespaceId(mythicMob.getInternalName()), dropItemNamespaceIds));
        }
    }

    @Override
    public void clear() {
        registerManager.getRawList().clear();
    }

    @SuppressWarnings("unchecked")
    public static List<Drop> getDropTableDrops(DropTable dropTable) {
        Class<?> clazz = dropTable.getClass();
        List<Drop> dropList = new ArrayList<>();
        try {
            Field field = clazz.getDeclaredField("drops");
            field.setAccessible(true);
            WeightedCollection<Drop> dropWeightedCollection = (WeightedCollection<Drop>) field.get(dropTable);
            Class<?> clazz2 = dropWeightedCollection.getClass();
            Field field2 = clazz2.getDeclaredField("items");
            field2.setAccessible(true);
            dropList = (List<Drop>) field2.get(dropWeightedCollection);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return dropList;
    }

    public static List<Drop> getSimpleDrops(DropTable dropTable) {
        List<Drop> resultDropList = new ArrayList<>();
        List<Drop> dropList = getDropTableDrops(dropTable);
        for (Drop drop : dropList) {
            if (drop instanceof DropTableDrop dropTableDrop) {
                try {
                    Class<?> clazz = dropTableDrop.getClass();
                    Field field = clazz.getDeclaredField("dropTable");
                    field.setAccessible(true);
                    DropTable dropTable1 = (DropTable) field.get(dropTableDrop);
                    resultDropList.addAll(getSimpleDrops(dropTable1));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                resultDropList.add(drop);
            }
        }
        return resultDropList;
    }

    public static String getDropNamespaceId(Drop drop) {
        if (drop instanceof ItemDrop) {
            return drop.getLine().split(" ")[0].toUpperCase();
        } else if (drop instanceof CustomDrop) {
            String configLine = drop.getLine();
            if (configLine.contains("mmoitem")) {
                String str = configLine.substring(configLine.indexOf("{") + 1, configLine.indexOf("}"));
                String[] args = str.split(";");
                String miType = "";
                String miName = "";
                for (String arg : args) {
                    if (arg.contains("type=")) {
                        miType = StringUtils.substringAfter(arg, "type=");
                    } else if (arg.contains("id=")) {
                        miName = StringUtils.substringAfter(arg, "id=");
                    }
                }
                return MMOItemsUtils.getIANamespace(miType) + ":" + miName.toLowerCase();
            } else {
                return drop.getLine().split(" ")[0];
            }
        } else if (drop instanceof ExperienceDrop) {
            return null;
//            return "EXP:" + drop.getLine().split(" ")[1];
        } else {
            TheVoidKingdom.LOGGER.warning("未知的掉落物类型: " + drop.getLine());
            return null;
        }
    }

    public String getIconItemNamespaceId(String mobName) {
        return switch (mobName) {
//            case "武装僵尸-1", "武装僵尸-2", "武装僵尸-3" -> "vkguis:icon_armed_zombie";
//            case "武装僵尸村民-1", "武装僵尸村民-2", "武装僵尸村民-3" -> "vkguis:icon_armed_zombie_villager";
//            case "派对苦力怕-1", "派对苦力怕-2", "派对苦力怕-3" -> "vkguis:icon_party_creeper";
//            case "织网毒蛛-1", "织网毒蛛-2", "织网毒蛛-3" -> "vkguis:icon_web_spider";
//            case "敏捷骷髅-1", "敏捷骷髅-2", "敏捷骷髅-3" -> "vkguis:icon_dodge_skeleton";
//            case "敏捷流浪者-1", "敏捷流浪者-2", "敏捷流浪者-3" -> "vkguis:icon_dodge_stray";
//            case "潮涌溺尸-1", "潮涌溺尸-2", "潮涌溺尸-3" -> "vkguis:icon_conduit_drown";
//            case "流沙尸壳-1", "流沙尸壳-2", "流沙尸壳-3" -> "vkguis:icon_quicksand_husk";
            default -> "null";
        };
    }
}
