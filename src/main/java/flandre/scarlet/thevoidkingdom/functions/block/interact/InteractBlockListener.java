package flandre.scarlet.thevoidkingdom.functions.block.interact;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.InteractBlockRecipe;
import flandre.scarlet.thevoidkingdom.utils.game.CoolDownMap;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import flandre.scarlet.thevoidkingdom.utils.game.PlayerOffHandInteract;
import org.bukkit.Material;
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

import java.util.List;
import java.util.UUID;

@Bean
public class InteractBlockListener implements Listener {

    private final CoolDownMap<UUID> coolDownMap = new CoolDownMap<>(200);

    private static final List<String> worldBlackList = List.of("world_spawn");

    public String getInputKey(ItemStack itemStack) {
        Material material = itemStack.getType();
        return switch (material) {
            case SHEARS -> "SHEARS";
            case BRUSH -> "BRUSH";
            case FLINT_AND_STEEL -> "FLINT_AND_STEEL";
            case WOODEN_PICKAXE, STONE_PICKAXE, IRON_PICKAXE, GOLDEN_PICKAXE, DIAMOND_PICKAXE, NETHERITE_PICKAXE ->
                    "PICKAXE";
            case WOODEN_AXE, STONE_AXE, IRON_AXE, GOLDEN_AXE, DIAMOND_AXE, NETHERITE_AXE -> "AXE";
            case WOODEN_HOE, STONE_HOE, IRON_HOE, GOLDEN_HOE, DIAMOND_HOE, NETHERITE_HOE -> "HOE";
            case WOODEN_SHOVEL, STONE_SHOVEL, IRON_SHOVEL, GOLDEN_SHOVEL, DIAMOND_SHOVEL, NETHERITE_SHOVEL -> "SHOVEL";
            default -> ItemsAdderUtils.getItemNamespaceId(itemStack);
        };
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(PlayerInteractEvent event) {
        if (event.useInteractedBlock().equals(Event.Result.DENY) || event.useItemInHand().equals(Event.Result.DENY)) {
            return;
        }
        EquipmentSlot equipmentSlot = event.getHand();
        if (equipmentSlot != EquipmentSlot.HAND && equipmentSlot != EquipmentSlot.OFF_HAND) {
            return;
        }


        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        if (worldBlackList.contains(block.getWorld().getName())) {
            return;
        }


        String blockNamespaceId = ItemsAdderUtils.getBlockNamespaceId(block);
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        ItemStack inputItemStack = event.getItem();
        if (inputItemStack == null) {
            return;
        }
        InteractBlockRecipe interactBlockRecipe = InteractBlockRecipe.INTERACT_BLOCK_MAP.get(getInputKey(inputItemStack) + blockNamespaceId);
        if (interactBlockRecipe == null) {
            return;
        }


        switch (interactBlockRecipe.interactAction()) {
            case ONLY_RIGHT_CLICK, ONLY_LEFT_CLICK -> {
                if (player.isSneaking()) {
                    return;
                }
            }
            case SHIFT_RIGHT_CLICK, SHIFT_LEFT_CLICK -> {
                if (!player.isSneaking()) {
                    return;
                }
            }
        }
        switch (interactBlockRecipe.interactAction()) {
            case ONLY_RIGHT_CLICK, RIGHT_CLICK, SHIFT_RIGHT_CLICK -> {
                if (!event.getAction().isRightClick()) {
                    return;
                }
            }
            case ONLY_LEFT_CLICK, LEFT_CLICK, SHIFT_LEFT_CLICK -> {
                if (!event.getAction().isLeftClick()) {
                    return;
                }
            }
        }


        InputChoice inputChoice = interactBlockRecipe.input();
        int requireAmount = interactBlockRecipe.inputAmount();
        PlayerInventory inventory = player.getInventory();
        int slot = (equipmentSlot == EquipmentSlot.HAND ? inventory.getHeldItemSlot() : 40);
        if (inputChoice instanceof ItemInput) {
            int amount = inputItemStack.getAmount();
            if (amount < requireAmount) {
                return;
            }
            //检查通过
            if (event.getHand() == EquipmentSlot.HAND) {
                PlayerOffHandInteract.cancel(player);
            }
            event.setCancelled(true);
            if (coolDownMap.isCoolDown(uuid)) {
                return;
            }
            coolDownMap.add(uuid);
            inputItemStack.setAmount(amount - requireAmount);
        } else if (inputChoice instanceof ToolInput toolInput) {
            if (toolInput.isForceDecreaseDurability()) {
                //检查通过
                if (event.getHand() == EquipmentSlot.HAND) {
                    PlayerOffHandInteract.cancel(player);
                }
                event.setCancelled(true);
                if (coolDownMap.isCoolDown(uuid)) {
                    return;
                }
                coolDownMap.add(uuid);
                inventory.setItem(slot, MMOItemsUtils.decreaseDurabilityNaturally(inputItemStack, player, requireAmount));
            }
        } else {
            if (event.getHand() == EquipmentSlot.HAND) {
                PlayerOffHandInteract.cancel(player);
            }
            event.setCancelled(true);
            return;
        }


        ResultAction[] resultActions = interactBlockRecipe.resultActions();
        for (ResultAction resultAction : resultActions) {
            resultAction.execute(block.getLocation());
        }
    }
}
