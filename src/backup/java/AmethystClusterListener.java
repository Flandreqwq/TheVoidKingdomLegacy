package flandre.scarlet.thevoidkingdom.functions.block.vanilla;


import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.AmethystCluster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Bean
public class AmethystClusterListener implements Listener {
    private final Map<UUID, Long> map = new HashMap<>();

    public void change(Block block, Material material) {
        AmethystCluster amethystCluster = (AmethystCluster) block.getBlockData();
        BlockFace blockFace = amethystCluster.getFacing();
        block.setType(material);
        AmethystCluster newAmethystCluster = (AmethystCluster) block.getBlockData();
        newAmethystCluster.setFacing(blockFace);
        block.setBlockData(newAmethystCluster);
        block.getWorld().playSound(block.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 2);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(PlayerInteractEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!event.getAction().isRightClick()) {
            return;
        }
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }


        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (player.getWorld().getName().equals("world_spawn")) {
            return;
        }
        if (System.currentTimeMillis() - map.getOrDefault(uuid, 0L) < 200) {
            return;
        }
        map.put(uuid, System.currentTimeMillis());


        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getType().isAir()) {
            return;
        }
        CustomStack customStack = CustomStack.byItemStack(itemStack);
        if (customStack == null) {
            return;
        }
        if (!customStack.getNamespacedID().equals("vkmaterials:shuijingcuishengfen")) {
            return;
        }


        switch (block.getType()) {
            case AMETHYST_CLUSTER:
                CustomStack stack = CustomStack.getInstance("vkmaterials:mojingsuipian");
                if (stack != null) {
                    itemStack.setAmount(itemStack.getAmount() - 1);
                    block.setType(Material.AIR);
                    Location location = block.getLocation();
                    block.getWorld().playSound(location, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 1);
                    stack.drop(location);
                } else {
                    return;
                }
                break;
            case LARGE_AMETHYST_BUD:
                itemStack.setAmount(itemStack.getAmount() - 1);
                change(block, Material.AMETHYST_CLUSTER);
                break;
            case MEDIUM_AMETHYST_BUD:
                itemStack.setAmount(itemStack.getAmount() - 1);
                change(block, Material.LARGE_AMETHYST_BUD);
                break;
            case SMALL_AMETHYST_BUD:
                itemStack.setAmount(itemStack.getAmount() - 1);
                change(block, Material.MEDIUM_AMETHYST_BUD);
                break;
        }
    }
}
