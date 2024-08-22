package flandre.scarlet.thevoidkingdom.functions.mob.boss;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Registrable;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Location;
import org.bukkit.Sound;

import java.util.List;

@Register(registerName = "Boss图腾")
public class BossTotem implements Registrable {
    private static final RegisterManager<BossTotem> registerManager = RegisterManager.getManager(BossTotem.class);

    //TotemLayer
    private static final TotemLayer deepRiteLayerBase = new TotemLayer(new String[][]{
            new String[]{"vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks"},
            new String[]{"vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks"},
            new String[]{"vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks"},
            new String[]{"vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks"},
            new String[]{"vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks"},
            new String[]{"vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks"},
            new String[]{"vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks", "vkblocks:depthrock_bricks"},
    });
    private static final TotemLayer deepRiteLayerPillar = new TotemLayer(new String[][]{
            new String[]{"vkblocks:depthrock_pillar", "AIR", "AIR", "AIR", "AIR", "AIR", "vkblocks:depthrock_pillar"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"vkblocks:depthrock_pillar", "AIR", "AIR", "AIR", "AIR", "AIR", "vkblocks:depthrock_pillar"},
    });
    private static final TotemLayer deepRiteLayerCorePillar = new TotemLayer(new String[][]{
            new String[]{"vkblocks:depthrock_pillar", "AIR", "AIR", "AIR", "AIR", "AIR", "vkblocks:depthrock_pillar"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "vkblocks:depth_core", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"vkblocks:depthrock_pillar", "AIR", "AIR", "AIR", "AIR", "AIR", "vkblocks:depthrock_pillar"},
    });
    private static final TotemLayer deepRiteLayerCampfire = new TotemLayer(new String[][]{
            new String[]{"CAMPFIRE", "AIR", "AIR", "AIR", "AIR", "AIR", "CAMPFIRE"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"CAMPFIRE", "AIR", "AIR", "AIR", "AIR", "AIR", "CAMPFIRE"},
    });
    private static final TotemLayer deepRiteLayerGeliya = new TotemLayer(new String[][]{
            new String[]{"vkblocks:geliya_totem_pillar", "AIR", "AIR", "AIR", "AIR", "AIR", "vkblocks:geliya_totem_pillar"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"vkblocks:geliya_totem_pillar", "AIR", "AIR", "AIR", "AIR", "AIR", "vkblocks:geliya_totem_pillar"},
    });
    private static final TotemLayer deepRiteLayerKarong = new TotemLayer(new String[][]{
            new String[]{"vkblocks:karong_totem_pillar", "AIR", "AIR", "AIR", "AIR", "AIR", "vkblocks:karong_totem_pillar"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"vkblocks:karong_totem_pillar", "AIR", "AIR", "AIR", "AIR", "AIR", "vkblocks:karong_totem_pillar"},
    });

    private static final TotemLayer deepRiteLayerWeiersi = new TotemLayer(new String[][]{
            new String[]{"vkblocks:weiersi_totem_pillar", "AIR", "AIR", "AIR", "AIR", "AIR", "vkblocks:weiersi_totem_pillar"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR"},
            new String[]{"vkblocks:weiersi_totem_pillar", "AIR", "AIR", "AIR", "AIR", "AIR", "vkblocks:weiersi_totem_pillar"},
    });

    //BossTotem

    private static final BossTotem geliyaTotem = new BossTotem(
            "深渊仪式",
            List.of(
                    new WorldCondition("World", "boss_world"),
                    new HeightCondition(120, 50)
            ),
            "歌利亚-登场盔甲架",
            "vkblocks:depth_core",
            "vkmaterials:geliya_sacrifice",
            "vk.denyspawn.geliya",
            "60m",
            new int[]{3, 1, 3},
            List.of(deepRiteLayerBase, deepRiteLayerCorePillar, deepRiteLayerGeliya, deepRiteLayerPillar, deepRiteLayerCampfire),
            Sound.ENTITY_ZOMBIE_VILLAGER_CURE,
            null);

    private static final BossTotem karongTotem = new BossTotem(
            "深渊仪式",
            List.of(
                    new WorldCondition("World", "boss_world"),
                    new HeightCondition(120, 50)
            ),
            "喀戎-登场盔甲架",
            "vkblocks:depth_core",
            "vkmaterials:karong_sacrifice",
            "vk.denyspawn.karong",
            "60m",
            new int[]{3, 1, 3},
            List.of(deepRiteLayerBase, deepRiteLayerCorePillar, deepRiteLayerKarong, deepRiteLayerPillar, deepRiteLayerCampfire),
            Sound.ENTITY_ZOMBIE_VILLAGER_CURE,
            null);

    private static final BossTotem weiersiTotem = new BossTotem(
            "深渊仪式",
            List.of(
                    new WorldCondition("World", "boss_world"),
                    new HeightCondition(120, 50)
            ),
            "薇尔斯-登场盔甲架",
            "vkblocks:depth_core",
            "vkmaterials:weiersi_sacrifice",
            "vk.denyspawn.weiersi",
            "60m",
            new int[]{3, 1, 3},
            List.of(deepRiteLayerBase, deepRiteLayerCorePillar, deepRiteLayerWeiersi, deepRiteLayerPillar, deepRiteLayerCampfire),
            Sound.ENTITY_ZOMBIE_VILLAGER_CURE,
            null);

    static {
        registerManager.create(geliyaTotem);
        registerManager.create(karongTotem);
        registerManager.create(weiersiTotem);
    }

    private final String riteName;
    private final List<TotemCondition> totemConditions;
    private final String bossName;
    private final String coreNamespaceId;
    private final String spawnItemNamespaceId;
    private final String denyPermission;
    private final String defaultCoolDown;
    private final int[] corePosition;
    private final List<TotemLayer> layers;
    private final Sound successSound;
    private final Sound failSound;

    public BossTotem(String riteName, List<TotemCondition> totemConditions, String bossName, String coreNamespaceId, String spawnItemNamespaceId, String denyPermission, String defaultCoolDown, int[] corePosition, List<TotemLayer> layers, Sound successSound, Sound failSound) {
        this.riteName = riteName;
        this.totemConditions = totemConditions;
        this.bossName = bossName;
        this.coreNamespaceId = coreNamespaceId;
        this.spawnItemNamespaceId = spawnItemNamespaceId;
        this.denyPermission = denyPermission;
        this.defaultCoolDown = defaultCoolDown;
        this.corePosition = corePosition;
        this.layers = layers;
        this.successSound = successSound;
        this.failSound = failSound;
    }

    public boolean isConditionMeet(Location blockLocation) {
        for (TotemCondition totemCondition : totemConditions) {
            if (!totemCondition.isMeet(blockLocation)) {
                return false;
            }
        }
        return true;
    }

    public boolean isLayersMeet(Location blockLocation) {
        Location originLocation = blockLocation.clone().add(-1 * corePosition[0], -1 * corePosition[1], -1 * corePosition[2]);
        for (TotemLayer layer : layers) {
            if (!layer.isLayerBlocksMeet(originLocation.getBlock())) {
                return false;
            }
            originLocation.add(0, 1, 0);
        }
        return true;
    }

    public void spawnBoss(Location blockLocation) {
        MythicMob spawnMob = MythicBukkit.inst().getMobManager().getMythicMob(bossName).orElse(null);
        if (spawnMob != null) {
            spawnMob.spawn(BukkitAdapter.adapt(blockLocation.clone().add(0.5, 0, 0.5)), 1);
        } else {
            TheVoidKingdom.LOGGER.warning("没有找到名为 " + bossName + " 的怪物");
        }
    }


    public String getCoreNamespaceId() {
        return coreNamespaceId;
    }

    public String getSpawnItemNamespaceId() {
        return spawnItemNamespaceId;
    }

    public String getRiteName() {
        return riteName;
    }

    public String getDenyPermission() {
        return denyPermission;
    }

    public String getDefaultCoolDown() {
        return defaultCoolDown;
    }

    public Sound getSuccessSound() {
        return successSound;
    }

    public Sound getFailSound() {
        return failSound;
    }

    public List<TotemCondition> getTotemConditions() {
        return totemConditions;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
