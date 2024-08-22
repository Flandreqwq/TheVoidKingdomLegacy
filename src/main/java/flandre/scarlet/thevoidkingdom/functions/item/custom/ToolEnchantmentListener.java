package flandre.scarlet.thevoidkingdom.functions.item.custom;

import dev.lone.itemsadder.api.CustomBlock;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

//@Bean
public class ToolEnchantmentListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void on(BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }
        PlayerInventory inventory = player.getInventory();
        ItemStack itemStack = inventory.getItemInMainHand();
        if (itemStack.getType().isAir()) {
            return;
        }
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType()) {
            return;
        }
        String miType = nbtItem.getType();
        Block block = event.getBlock();
        if (CustomBlock.byAlreadyPlaced(block) != null) {
            return;
        }
        Material material = block.getType();
        switch (miType) {
            case "PICKAXE" -> {
                if (!Tag.MINEABLE_PICKAXE.isTagged(material)) {
                    return;
                }
            }
            case "AXE" -> {
                if (!Tag.MINEABLE_AXE.isTagged(material)) {
                    return;
                }
            }
            case "HOE" -> {
                if (!Tag.MINEABLE_HOE.isTagged(material)) {
                    return;
                }
            }
            case "SHOVEL" -> {
                if (!Tag.MINEABLE_SHOVEL.isTagged(material)) {
                    return;
                }
            }
            default -> {
                return;
            }
        }
        double hasteTime = nbtItem.getDouble("MMOITEMS_CUSTOM_HASTE_EXTRA_TIME") + 10;
        PotionEffect playerHastePotionEffect = player.getPotionEffect(PotionEffectType.FAST_DIGGING);
        if (playerHastePotionEffect == null) {
            double startPossibility = nbtItem.getDouble("MMOITEMS_CUSTOM_HASTE_START_POSSIBILITY");
            if (startPossibility > 0) {
                Random random = new Random();
                if (random.nextDouble() < startPossibility / 100) {
                    PotionEffect haste = new PotionEffect(PotionEffectType.FAST_DIGGING, (int) (hasteTime * 20), 0, false);
                    haste.apply(player);
                }
            }
        } else {
            int playerHasteAmplifier = playerHastePotionEffect.getAmplifier();
            double upgradePossibility = nbtItem.getDouble("MMOITEMS_CUSTOM_HASTE_UPGRADE_POSSIBILITY");
            if (upgradePossibility > 0) {
                Random random = new Random();
                if (random.nextDouble() < upgradePossibility / 100) {
                    PotionEffect haste = new PotionEffect(PotionEffectType.FAST_DIGGING, (int) (hasteTime * 20), Math.min(playerHasteAmplifier + 1, 3), false);
                    haste.apply(player);
                }
            }
        }
    }
}
