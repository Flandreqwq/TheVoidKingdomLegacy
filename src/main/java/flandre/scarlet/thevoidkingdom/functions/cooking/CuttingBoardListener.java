package flandre.scarlet.thevoidkingdom.functions.cooking;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.CuttingBoardRecipe;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.CoolDownMap;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.PlayerOffHandInteract;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import net.Indyuce.mmoitems.api.interaction.util.DurabilityItem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import java.util.Collection;
import java.util.Objects;

@Bean
public class CuttingBoardListener implements Listener {
    private final CoolDownMap<Location> coolDownMap = new CoolDownMap<>(200);

    private enum CuttingBoardFace {
        EAST,
        WEST,
        NORTH,
        SOUTH
    }

    private CuttingBoardFace getCuttingBoardFace(String nameSpaceId) {
        return switch (nameSpaceId) {
            case "vkblocks:cutting_board" -> CuttingBoardFace.EAST;
            case "vkblocks:cutting_board_w" -> CuttingBoardFace.WEST;
            case "vkblocks:cutting_board_n" -> CuttingBoardFace.NORTH;
            case "vkblocks:cutting_board_s" -> CuttingBoardFace.SOUTH;
            default -> null;
        };
    }

    public static final NamespacedKey LOCATION = new NamespacedKey(TheVoidKingdom.PLUGIN, "location");
    public static final NamespacedKey TYPE = new NamespacedKey(TheVoidKingdom.PLUGIN, "type");

    public static ItemDisplay searchCuttingBoardItemDisplay(Location blockLocation) {
        Location centerLocation = blockLocation.clone().add(0.5, 0.0, 0.5);
        Collection<ItemDisplay> collection = centerLocation.getNearbyEntitiesByType(ItemDisplay.class, 0.2);
        for (ItemDisplay itemDisplay : collection) {
            PersistentDataContainer persistentDataContainer = itemDisplay.getPersistentDataContainer();
            PersistentDataContainer dataContainer = persistentDataContainer.get(TheVoidKingdom.DATA_NAMESPACED, PersistentDataType.TAG_CONTAINER);
            if (dataContainer == null) {
                continue;
            }
            if (!Objects.equals(dataContainer.get(TYPE, PersistentDataType.STRING), "CuttingBoard")) {
                continue;
            }
            String locationStr = dataContainer.get(LOCATION, PersistentDataType.STRING);
            if (Objects.equals(locationStr, centerLocation.toString()) && itemDisplay.getItemStack() != null) {
                return itemDisplay;
            }
        }
        return null;
    }

    public static ItemDisplay spawnCuttingBoardItemDisplay(Location centerLocation, ItemStack itemStack, CuttingBoardFace cuttingBoardFace) {
        ItemDisplay itemDisplay = (ItemDisplay) centerLocation.getWorld().spawnEntity(centerLocation, EntityType.ITEM_DISPLAY);
        itemDisplay.setItemStack(itemStack);
        itemDisplay.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.FIXED);
        itemDisplay.setBillboard(Display.Billboard.FIXED);
        itemDisplay.setShadowRadius(0);
        switch (cuttingBoardFace) {
            case SOUTH -> {
                itemDisplay.setTransformation(new Transformation(new Vector3f(0F, 0.08F, 0F), new AxisAngle4f(1.570796F, -1, 0, 0), new Vector3f(0.5F, 0.5F, 0.5F), new AxisAngle4f(1.570796F, 0, 0, 1)));
            }
            case NORTH -> {
                itemDisplay.setTransformation(new Transformation(new Vector3f(0F, 0.08F, 0F), new AxisAngle4f(1.570796F, -1, 0, 0), new Vector3f(0.5F, 0.5F, 0.5F), new AxisAngle4f(-1.570796F, 0, 0, 1)));
            }
            case EAST -> {
                itemDisplay.setTransformation(new Transformation(new Vector3f(0F, 0.08F, 0F), new AxisAngle4f(1.570796F, -1, 0, 0), new Vector3f(0.5F, 0.5F, 0.5F), new AxisAngle4f(3.141593F, 0, 0, 1)));
            }
            case WEST -> {
                itemDisplay.setTransformation(new Transformation(new Vector3f(0F, 0.08F, 0F), new AxisAngle4f(1.570796F, -1, 0, 0), new Vector3f(0.5F, 0.5F, 0.5F), new AxisAngle4f(0, 0, 0, 1)));
            }
        }
        PersistentDataContainer persistentDataContainer = itemDisplay.getPersistentDataContainer();
        PersistentDataContainer dataContainer = persistentDataContainer.getAdapterContext().newPersistentDataContainer();
        dataContainer.set(TYPE, PersistentDataType.STRING, "CuttingBoard");
        dataContainer.set(LOCATION, PersistentDataType.STRING, centerLocation.toString());
        persistentDataContainer.set(TheVoidKingdom.DATA_NAMESPACED, PersistentDataType.TAG_CONTAINER, dataContainer);
        return itemDisplay;
    }

    @EventHandler(priority = EventPriority.HIGH)
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
        CuttingBoardFace cuttingBoardFace = getCuttingBoardFace(customBlock.getNamespacedID());
        if (cuttingBoardFace == null) {
            return;
        }


        Player player = event.getPlayer();
        World world = player.getWorld();
        PlayerInventory inventory = player.getInventory();
        ItemStack inputItemStack = event.getItem();
        int slot = event.getHand() == EquipmentSlot.HAND ? inventory.getHeldItemSlot() : 40;
        if (inputItemStack != null && player.isSneaking()) {
            return;
        }


        if (inputItemStack == null) {
            return;
        }
        String namespaceId = ItemsAdderUtils.getItemNamespaceId(inputItemStack);
        CuttingBoardRecipe recipe = getCuttingBoardRecipe(namespaceId);
        Location blockLocation = block.getLocation();
        Location centerLocation = blockLocation.clone().add(0.5, 0.0, 0.5);
        ItemDisplay itemDisplay = searchCuttingBoardItemDisplay(blockLocation);
        boolean isCuttingBoardEmpty = itemDisplay == null;
        if (isCuttingBoardEmpty) {
            //砧板上无物品
            if (recipe == null) {
                //玩家手持的是无法放到砧板上的物品
                return;
            }
        }


        if (event.getHand() == EquipmentSlot.HAND) {
            PlayerOffHandInteract.cancel(player);
        }
        event.setCancelled(true);


        if (coolDownMap.isCoolDown(blockLocation)) {
            return;
        }
        coolDownMap.add(blockLocation);


        if (isCuttingBoardEmpty) {
            //砧板上无物品
            ItemStack itemStack = inputItemStack.clone();
            itemStack.setAmount(1);
            inputItemStack.setAmount(inputItemStack.getAmount() - 1);
            spawnCuttingBoardItemDisplay(centerLocation, itemStack, cuttingBoardFace);
        } else {
            //砧板上有物品
            ItemStack foodItemStack = itemDisplay.getItemStack();
            assert foodItemStack != null;
            if (ItemsAdderUtils.checkNamespaceId(inputItemStack, "vkweapons:chudao")) {
                //玩家手持厨刀
                String foodNamespaceId = ItemsAdderUtils.getItemNamespaceId(foodItemStack);
                CuttingBoardRecipe cuttingBoardRecipe = getCuttingBoardRecipe(foodNamespaceId);
                if (cuttingBoardRecipe == null) {
                    //砧板上面的物品有问题（理论上不会触发）
                    TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>非法操作: 无法加工的物品！请将错误汇报给管理员！", true);
                    TheVoidKingdom.LOGGER.warning(event.getPlayer().getName() + " 与放置了非法物品的砧板发生了交互");
                    return;
                }

                itemDisplay.remove();

                DurabilityItem durabilityItem = new DurabilityItem(player, inputItemStack);
                ItemStack knifeItemStack = durabilityItem.decreaseDurability(1).toItem();
                inventory.setItem(slot, knifeItemStack);

                ItemStack resultItemStack = ItemsAdderUtils.getItemStackNoCheck(cuttingBoardRecipe.resultNamespaceId());
                resultItemStack.setAmount(cuttingBoardRecipe.resultAmount());
                world.dropItem(centerLocation.clone().add(0, 0.1, 0), resultItemStack);
                String extraResultNamespaceId = cuttingBoardRecipe.extraResultNamespaceId();
                if (!extraResultNamespaceId.equals("AIR")) {
                    ItemStack extraResultItemStack = ItemsAdderUtils.getItemStackNoCheck(extraResultNamespaceId);
                    extraResultItemStack.setAmount(cuttingBoardRecipe.extraAmount());
                    world.dropItem(centerLocation.clone().add(0, 0.1, 0), extraResultItemStack);
                }

                world.playSound(centerLocation, "vkblocks:block.cutting_board.knife", SoundCategory.BLOCKS, 1, 1);
                world.spawnParticle(Particle.ITEM_CRACK, centerLocation.clone().add(0, 0.1, 0), 10, 0.15, 0.15, 0.15, 0, foodItemStack);
                return;
            }
            //玩家手持非厨刀
            itemDisplay.remove();
            TheVoidKingdomUtils.giveItemStackToPlayer(player, foodItemStack);
            player.playSound(centerLocation, Sound.ENTITY_ITEM_PICKUP, 1, 1);
        }
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void on(CustomBlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (getCuttingBoardFace(event.getNamespacedID()) == null) {
            return;
        }
        Block block = event.getBlock();
        Location location = block.getLocation();
        ItemDisplay itemDisplay = searchCuttingBoardItemDisplay(location);
        if (itemDisplay != null) {
            ItemStack itemStack = itemDisplay.getItemStack();
            itemDisplay.remove();
            assert itemStack != null;
            block.getWorld().dropItem(location.clone().add(0.5, 0.1, 0.5), itemStack);
        }
    }


    private CuttingBoardRecipe getCuttingBoardRecipe(String inputNamespaceId) {
        for (CuttingBoardRecipe cuttingBoardRecipe : RegisterManager.getManager(CuttingBoardRecipe.class).getValidList()) {
            if (cuttingBoardRecipe.checkInput(inputNamespaceId)) {
                return cuttingBoardRecipe;
            }
        }
        return null;
    }
}
