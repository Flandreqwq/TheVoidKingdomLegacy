package flandre.scarlet.thevoidkingdom.listeners;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetHavenMagicStone implements Listener {
    //天堂魔法石获得
    private final List<Location> skyMagicStoneBlocks = new ArrayList<Location>();

    @EventHandler
    public void havenMagicStoneGet(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (event.getAction().isLeftClick()) {
            return;
        }
        if (clickedBlock == null) {
            return;
        }
        ItemStack itemStack = event.getItem();
        if (itemStack == null) {
            return;
        }
        if (clickedBlock.getType() != Material.CALCITE) {
            return;
        }
        if (!itemStack.getItemMeta().getDisplayName().contains("§a魔晶粉")) {
            return;
        }
        Location location = clickedBlock.getLocation();
        if (location.getBlockY() < 300) {
            return;
        }
        if (skyMagicStoneBlocks.contains(location)) {
            return;
        }
        skyMagicStoneBlocks.add(location);
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        itemInHand.setAmount(itemInHand.getAmount() - 1);

        Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(TheVoidKingdom.class), () -> {
            World world = clickedBlock.getWorld();
            Random random = new Random();
            clickedBlock.setType(Material.AIR);
            if (random.nextDouble() >= 0.33) {
                world.playSound(player.getLocation(), Sound.BLOCK_CALCITE_BREAK, 1, 1);
                skyMagicStoneBlocks.remove(location);
            } else {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mi drop MATERIAL TIANTANGMOFASHI " + world.getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + " 1 1-1 0");
                world.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 1, 0);
                skyMagicStoneBlocks.remove(location);
            }
        }, 3);
    }
}
