package flandre.scarlet.thevoidkingdom.functions.advencement;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.enchant.CardGame;
import flandre.scarlet.thevoidkingdom.functions.enchant.EnchantMenu;
import flandre.scarlet.thevoidkingdom.utils.events.PlayerActiveTotemEvent;
import flandre.scarlet.thevoidkingdom.utils.events.PlayerFinishEnchantEvent;
import flandre.scarlet.thevoidkingdom.utils.events.PlayerPlayCardGameEvent;
import flandre.scarlet.thevoidkingdom.utils.events.PlayerRepairItemEvent;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.lib.api.event.PlayerKillEntityEvent;
import net.Indyuce.mmocore.api.event.PlayerChangeClassEvent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

@Bean
public class SimpleEventListener implements Listener {

    private static final AdvancementCriteria universalRoot = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/root"), "进入虚空主界");
    private static final AdvancementCriteria warriorRoot = new AdvancementCriteria(new NamespacedKey("vkadvancements", "warrior/root"), "成为战士");
    private static final AdvancementCriteria archerRoot = new AdvancementCriteria(new NamespacedKey("vkadvancements", "archer/root"), "成为游侠");
    private static final AdvancementCriteria mageRoot = new AdvancementCriteria(new NamespacedKey("vkadvancements", "mage/root"), "成为魔法使");
    private static final AdvancementCriteria enchantItem = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/enchant_item"), "附魔任意物品");
    private static final AdvancementCriteria repairItem = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/repair_item"), "修复任意物品");
    private static final AdvancementCriteria enchantFirstCardStop = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/enchant_first_card_stop"), "精神涣散");
    private static final AdvancementCriteria enchantAllCardsOpen = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/enchant_all_cards_open"), "翻开所有卡牌");
    private static final AdvancementCriteria deepDarkRite = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/deep_dark_rite"), "深渊仪式");
    private static final AdvancementCriteria killGeliya = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_geliya"), "深渊巨物——歌利亚");
    private static final AdvancementCriteria killKarong = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_karong"), "不灭亡骸——喀戎");
    private static final AdvancementCriteria killWeiersi = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_weiersi"), "紫晶巫妖——薇尔斯");
    private static final AdvancementCriteria killAllVoidMobsArmoredZombie = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "武装僵尸");
    private static final AdvancementCriteria killAllVoidMobsArmoredZombieVillager = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "武装僵尸村民");
    private static final AdvancementCriteria killAllVoidMobsConduitDrowned = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "潮涌溺尸");
    private static final AdvancementCriteria killAllVoidMobsQuicksandHusk = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "流沙尸壳");
    private static final AdvancementCriteria killAllVoidMobsQuickSkeleton = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "敏捷骷髅");
    private static final AdvancementCriteria killAllVoidMobsQuickStary = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "敏捷流浪者");
    private static final AdvancementCriteria killAllVoidMobsPartyCreeper = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "派对苦力怕");
    private static final AdvancementCriteria killAllVoidMobsWebSpider = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "织网毒蛛");
    private static final AdvancementCriteria killAllVoidMobsGeliya = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "深渊巨物——歌利亚");
    private static final AdvancementCriteria killAllVoidMobsKarong = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "不灭亡骸——喀戎");
    private static final AdvancementCriteria killAllVoidMobsWeiersi = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "紫晶巫妖——薇尔斯");
    private static final AdvancementCriteria killAllVoidMobsSkeleton = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "骷髅");
    private static final AdvancementCriteria killAllVoidMobsCreeper = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "minecraft:creeper");
    private static final AdvancementCriteria killAllVoidMobsHusk = new AdvancementCriteria(new NamespacedKey("vkadvancements", "universal/kill_all_mobs"), "minecraft:husk");

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(PlayerPlayCardGameEvent event) {
        EnchantMenu enchantMenu = event.getEnchantMenu();
        Player player = event.getPlayer();
        CardGame cardGame = enchantMenu.getCardGame();
        if (enchantMenu.isStop() && cardGame.getOpenCardAmount() == 1) {
            awardAdvancementCriteria(player, enchantFirstCardStop);
        } else if (cardGame.getClosedCardAmount() == 0) {
            awardAdvancementCriteria(player, enchantAllCardsOpen);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(PlayerFinishEnchantEvent event) {
        Player player = event.getPlayer();
        awardAdvancementCriteria(player, enchantItem);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(PlayerRepairItemEvent event) {
        Player player = event.getPlayer();
        awardAdvancementCriteria(player, repairItem);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().equals("world")) {
            awardAdvancementCriteria(player, universalRoot);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(PlayerChangeClassEvent event) {
        Player player = event.getPlayer();
        switch (event.getNewClass().getName()) {
            case "战士" -> {
                awardAdvancementCriteria(player, warriorRoot);
            }
            case "游侠" -> {
                awardAdvancementCriteria(player, archerRoot);
            }
            case "魔法使" -> {
                awardAdvancementCriteria(player, mageRoot);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(PlayerActiveTotemEvent event) {
        Player player = event.getPlayer();
        switch (event.getTotem().getRiteName()) {
            case "深渊仪式" -> {
                awardAdvancementCriteria(player, deepDarkRite);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(PlayerKillEntityEvent event) {
        LivingEntity livingEntity = event.getTarget();
        Player player = event.getPlayer();
        ActiveMob mythicMob = MythicBukkit.inst().getMobManager().getActiveMob(livingEntity.getUniqueId()).orElse(null);
        if (mythicMob != null) {
            return;
        }
        switch (livingEntity.getType()) {
            case SKELETON -> {
                awardAdvancementCriteria(player, killAllVoidMobsSkeleton);
            }
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(MythicMobDeathEvent event) {
        LivingEntity livingEntity = event.getKiller();
        if (livingEntity == null || !livingEntity.getType().equals(EntityType.PLAYER)) {
            return;
        }
        Player player = (Player) livingEntity;
        switch (event.getMob().getMobType()) {
            case "歌利亚" -> {
                awardAdvancementCriteria(player, killGeliya);
                awardAdvancementCriteria(player, killAllVoidMobsGeliya);
            }
            case "喀戎" -> {
                awardAdvancementCriteria(player, killKarong);
                awardAdvancementCriteria(player, killAllVoidMobsKarong);
            }
            case "薇尔斯" -> {
                awardAdvancementCriteria(player, killWeiersi);
                awardAdvancementCriteria(player, killAllVoidMobsWeiersi);
            }
            case "武装僵尸-1", "武装僵尸-2", "武装僵尸-3" -> {
                awardAdvancementCriteria(player, killAllVoidMobsArmoredZombie);
            }
            case "武装僵尸村民-1", "武装僵尸村民-2", "武装僵尸村民-3" -> {
                awardAdvancementCriteria(player, killAllVoidMobsArmoredZombieVillager);
            }
            case "潮涌溺尸-1", "潮涌溺尸-2", "潮涌溺尸-3" -> {
                awardAdvancementCriteria(player, killAllVoidMobsConduitDrowned);
            }
            case "流沙尸壳-1", "流沙尸壳-2", "流沙尸壳-3" -> {
                awardAdvancementCriteria(player, killAllVoidMobsQuicksandHusk);
                awardAdvancementCriteria(player, killAllVoidMobsHusk);
            }
            case "敏捷骷髅-1", "敏捷骷髅-2", "敏捷骷髅-3" -> {
                awardAdvancementCriteria(player, killAllVoidMobsQuickSkeleton);
                awardAdvancementCriteria(player, killAllVoidMobsSkeleton);
            }
            case "敏捷流浪者-1", "敏捷流浪者-2", "敏捷流浪者-3" -> {
                awardAdvancementCriteria(player, killAllVoidMobsQuickStary);
            }
            case "派对苦力怕-1", "派对苦力怕-2", "派对苦力怕-3" -> {
                awardAdvancementCriteria(player, killAllVoidMobsPartyCreeper);
                awardAdvancementCriteria(player, killAllVoidMobsCreeper);
            }
            case "织网毒蛛-1", "织网毒蛛-2", "织网毒蛛-3" -> {
                awardAdvancementCriteria(player, killAllVoidMobsWebSpider);
            }
        }
    }

    private void awardAdvancementCriteria(Player player, AdvancementCriteria advancementCriteria) {
        if (advancementCriteria.isIgnoredPlayer(player)) {
            return;
        }
        Advancement advancement = Bukkit.getAdvancement(advancementCriteria.getAdvancementKey());
        if (advancement == null) {
            TheVoidKingdom.LOGGER.warning("不存在的进度: " + advancementCriteria.getAdvancementKey().toString());
            return;
        }
        AdvancementProgress advancementProgress = player.getAdvancementProgress(advancement);
        String criteria = advancementCriteria.getCriteria();
        if (!advancementProgress.getRemainingCriteria().contains(criteria)) {
            advancementCriteria.addIgnoredPlayer(player);
            return;
        }
        advancementProgress.awardCriteria(criteria);
    }
}
