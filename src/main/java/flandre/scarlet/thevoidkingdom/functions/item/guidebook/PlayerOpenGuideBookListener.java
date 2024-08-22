package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.CoolDownMap;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.PlayerOffHandInteract;
import flandre.scarlet.thevoidkingdom.utils.game.menu.OpenReason;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Bean
public class PlayerOpenGuideBookListener implements Listener {
    private static final CoolDownMap<UUID> coolDownMap = new CoolDownMap<>(200);

    @EventHandler(priority = EventPriority.MONITOR)
    public void on(PlayerInteractEvent event) {
        if (event.useItemInHand().equals(Event.Result.DENY)) {
            return;
        }
        if (!event.getAction().isRightClick()) {
            return;
        }



        Player player = event.getPlayer();
        ItemStack inputItemStack = event.getItem();
        if (!ItemsAdderUtils.getItemNamespaceId(inputItemStack).contains("vkmaterials:guidebook")) {
            return;
        }


        Block block = event.getClickedBlock();
        if (block != null) {
            if (block.getType().equals(Material.CHISELED_BOOKSHELF) && !player.isSneaking()) {
                Directional directional = (Directional) block.getBlockData();
                if (directional.getFacing().equals(event.getBlockFace())) {
                    return;
                }
            }
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


        new CategoryMainMenu(player).open(OpenReason.OPEN_NEW);
    }
}