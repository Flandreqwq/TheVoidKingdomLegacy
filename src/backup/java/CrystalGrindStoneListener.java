package flandre.scarlet.thevoidkingdom.functions.enchant;

import com.molean.isletopia.framework.annotations.Bean;
import de.tr7zw.nbtapi.NBTItem;
import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.game.CoolDownMap;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import flandre.scarlet.thevoidkingdom.utils.game.PlayerOffHandInteract;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Random;
import java.util.UUID;

@Bean
public class CrystalGrindStoneListener implements Listener {
    private final CoolDownMap<UUID> coolDownMap = new CoolDownMap<>(200);


    @EventHandler(priority = EventPriority.LOW)
    public void on(PlayerInteractEvent event) {
        if (event.useItemInHand() == Event.Result.DENY || event.useInteractedBlock() == Event.Result.DENY) {
            return;
        }
        if (!event.getAction().isRightClick()) {
            return;
        }


        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        if (!block.getType().equals(Material.GRINDSTONE)) {
            return;
        }


        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack inputItemStack = event.getItem();
        if (inputItemStack == null || player.isSneaking()) {
            return;
        }


        if (event.getHand() == EquipmentSlot.HAND) {
            PlayerOffHandInteract.cancel(player);
        }
        event.setCancelled(true);
        UUID uuid = player.getUniqueId();
        if (coolDownMap.isCoolDown(uuid)) {
            return;
        }
        coolDownMap.add(uuid);


        int slot = event.getHand() == EquipmentSlot.HAND ? player.getInventory().getHeldItemSlot() : 40;
        NBTItem nbtItem = new NBTItem(inputItemStack);
        if (!nbtItem.getString("MMOITEMS_ITEM_TYPE").equals("MATERIAL_ENCHANT")) {
            return;
        }
        String extraProductNamespaceId;
        switch (nbtItem.getString("MMOITEMS_ITEM_ID")) {
            case "YUNMOQINGJINSHICU" -> extraProductNamespaceId = "vkmaterials:yunmoqingjinshisuipian";
            case "ZHUSHA" -> extraProductNamespaceId = "vkmaterials:zhushasuipian";
            default -> {
                return;
            }
        }


        Location location = block.getLocation();
        World world = block.getWorld();
        world.playSound(location, Sound.BLOCK_GRINDSTONE_USE, 1, 1);
        world.spawnParticle(Particle.ITEM_CRACK, location.clone().add(0.5, 0.8, 0.5), 10, 0.2, 0.2, 0.2, 0.1, inputItemStack);
        double size = nbtItem.getDouble("MMOITEMS_CUSTOM_SIZE");
        double polish = nbtItem.getDouble("MMOITEMS_CUSTOM_POLISH");
        double purity = nbtItem.getDouble("MMOITEMS_CUSTOM_PURITY");
        Random random = new Random();
        if (random.nextDouble() * 100 > size) {
            //磨碎了
            player.spawnParticle(Particle.ITEM_CRACK, player.getLocation().add(0.0, 1.0, 0.0), 20, 0.2, 0.2, 0.2, 0.1, inputItemStack);
            player.playSound(location, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1F, 0.7F);
            CustomStack customStack = CustomStack.getInstance(extraProductNamespaceId);
            if (customStack == null) {
                TheVoidKingdom.LOGGER.warning("不存在的ia物品: " + extraProductNamespaceId);
            } else {
                ItemStack extraProduct = customStack.getItemStack();
                extraProduct.setAmount((int) (size / 10));
                inventory.setItem(slot, extraProduct);
            }
            return;
        }
        double newSize = size - (random.nextDouble() * 9 + 1);
        if (newSize <= 0) {
            //新尺寸小于0碎了
            world.spawnParticle(Particle.ITEM_CRACK, player.getLocation().add(0.0, 1.0, 0.0), 20, 0.2, 0.2, 0.2, 0.1, inputItemStack);
            inputItemStack.setAmount(0);
            world.playSound(location, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1F, 0.7F);
            return;
        }
        double newPolish = Math.min(polish + (random.nextDouble() * 5 * purity), 100.0);
        nbtItem.setDouble("MMOITEMS_CUSTOM_SIZE", newSize);
        nbtItem.setDouble("MMOITEMS_CUSTOM_POLISH", newPolish);
        ItemStack newItemStack = nbtItem.getItem();
        newItemStack.lore(MMOItemsUtils.buildMILore(newItemStack));
        inventory.setItem(slot, newItemStack);
    }
}
