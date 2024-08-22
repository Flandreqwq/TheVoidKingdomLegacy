package flandre.scarlet.thevoidkingdom.functions.repair;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomBlock;
import flandre.scarlet.thevoidkingdom.utils.game.CoolDownMap;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import flandre.scarlet.thevoidkingdom.utils.game.PlayerOffHandInteract;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import flandre.scarlet.thevoidkingdom.utils.game.menu.CloseReason;
import flandre.scarlet.thevoidkingdom.utils.game.menu.OpenReason;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

@Bean
public class RepairMenuListener implements Listener {
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
        String namespaceId = customBlock.getNamespacedID();
        if (!namespaceId.equals("vkblocks:repair_table")) {
            return;
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


        if (inputItemStack == null) {
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>手持需要修复的物品右键修复桌进行修复", false);
            return;
        }
        RepairItemType repairItemType = RepairItemType.getType(inputItemStack);
        if (repairItemType == null) {
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>这个物品无法通过修复桌进行修复", false);
            return;
        }
        double durability = MMOItemsUtils.getDurability(inputItemStack);
        double maxDurability = MMOItemsUtils.getMaxDurability(inputItemStack);
        if (durability == maxDurability) {
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>这个物品无需修复", false);
            return;
        }
        ItemStack itemStack = inputItemStack.clone();
        inputItemStack.setAmount(0);
        new RepairMenu(player, block.getLocation(), itemStack, repairItemType, durability, maxDurability).open(OpenReason.OPEN_NEW);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        if (!(inventory.getHolder() instanceof RepairMenu.RepairMenuHolder)) {
            return;
        }
        int slot = event.getRawSlot();
        RepairMenu repairMenu = (RepairMenu) ((RepairMenu.RepairMenuHolder) inventory.getHolder()).getMenu();
        //取消修复
        if (slot == 33 || slot == 34) {
            repairMenu.close(CloseReason.CANCEL_REPAIR);
        }
        //确认修复
        else if (slot == 42 || slot == 43) {
            Map<String, Integer> ingredients = repairMenu.getIngredients();
            //材料足够
            if (TheVoidKingdomUtils.checkIngredients(player, ingredients)) {
                TheVoidKingdomUtils.takeIngredients(player, ingredients);
                repairMenu.close(CloseReason.FINISH_REPAIR);
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 0.5F);
            }
            //材料不足
            else {
                repairMenu.close(CloseReason.REPAIR_MATERIAL_NOT_ENOUGH);
            }
        }
    }
}
