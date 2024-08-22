package flandre.scarlet.thevoidkingdom.functions.block.vanilla;


import com.molean.isletopia.framework.annotations.Bean;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Bean
public class FletchingTableListener implements Listener {
    public final Map<UUID, Long> map = new HashMap<>();
    public final List<String> list = List.of("_LOG", "_WOOD", "_STEM", "_HYPHAE");
    public final ItemStack arrows = new ItemStack(Material.ARROW,4);

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


        if (!block.getType().equals(Material.FLETCHING_TABLE)) {
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
        if (!itemStack.getType().equals(Material.FLINT)) {
            return;
        }
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (nbtItem.hasType()) {
            return;
        }
        Location location = block.getLocation();
        Block upBlock = location.add(0, 1, 0).getBlock();
        String type = upBlock.getType().toString();
        boolean flag = false;
        for (String str : list) {
            if (type.contains(str)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return;
        }

        event.setCancelled(true);
        itemStack.setAmount(itemStack.getAmount() - 1);
        upBlock.setType(Material.AIR);
        player.playSound(location, Sound.ENTITY_VILLAGER_WORK_FLETCHER, 1, 1);
        World world = player.getWorld();
        world.dropItem(location.add(0.5, 0, 0.5), arrows);
    }
}
