package flandre.scarlet.thevoidkingdom.functions.block.custom;

import com.destroystokyo.paper.MaterialTags;
import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.MiningRecipe;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;


@Bean
public class BoxDestroyListener implements Listener {
    private void spawnMythicMob(Location location, String spawnMobName) {
        MythicMob spawnMob = MythicBukkit.inst().getMobManager().getMythicMob(spawnMobName).orElse(null);
        if (spawnMob != null) {
            spawnMob.spawn(BukkitAdapter.adapt(location), 1);
        } else {
            TheVoidKingdom.LOGGER.warning("没有找到名为 " + spawnMobName + " 的怪物");
        }
    }

    private boolean checkTool(ItemStack itemStack, int requireToolLevel) {
        if (requireToolLevel == 0) {
            return true;
        }
        int toolLevel = MiningRecipe.getToolLevel(ItemsAdderUtils.getItemNamespaceId(itemStack));
        return MaterialTags.PICKAXES.isTagged(itemStack) && requireToolLevel <= toolLevel;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(CustomBlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
            return;
        }
        String namespaceId = event.getNamespacedID();
        Location location = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
        switch (namespaceId) {
            case "vkblocks:wooden_box" -> {
                if (checkTool(itemStack, 0)) {
                    spawnMythicMob(location, "木匣");
                }
            }
            case "vkblocks:bronze_box" -> {
                if (checkTool(itemStack, 2)) {
                    spawnMythicMob(location, "青铜匣");
                }
            }
            case "vkblocks:golden_box" -> {
                if (checkTool(itemStack, 4)) {
                    spawnMythicMob(location, "金匣");
                }
            }
            case "vkblocks:void_box" -> {
                if (checkTool(itemStack, 4)) {
                    spawnMythicMob(location, "虚界宝匣");
                }
            }
        }
    }
}
