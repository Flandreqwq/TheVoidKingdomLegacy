package flandre.scarlet.thevoidkingdom.functions.block.custom;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomBlock;
import flandre.scarlet.thevoidkingdom.utils.game.CoolDownMap;
import flandre.scarlet.thevoidkingdom.utils.game.PlayerOffHandInteract;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.crafting.CraftingStation;
import net.Indyuce.mmoitems.gui.CraftingStationView;
import org.bukkit.block.Block;
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
public class TableOpenGuiListener implements Listener {
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
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        if (customBlock == null) {
            return;
        }
        CraftingStation craftingStation;
        switch (customBlock.getNamespacedID()) {
            case "vkblocks:void_crafting_table" -> {
                craftingStation = MMOItems.plugin.getCrafting().getStation("void-crafting-table");
            }
            case "vkblocks:spellcard_table_1" -> {
                craftingStation = MMOItems.plugin.getCrafting().getStation("spellcard-table-1");
            }
            default -> {
                return;
            }
        }


        Player player = event.getPlayer();
        ItemStack inputItemStack = event.getItem();
        if (inputItemStack != null && player.isSneaking()) {
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


        new CraftingStationView(player, craftingStation, 1).open();
    }
}
