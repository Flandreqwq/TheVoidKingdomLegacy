package flandre.scarlet.thevoidkingdom.functions.enchant;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomBlock;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.framework.IntervalTask;
import flandre.scarlet.thevoidkingdom.utils.game.*;
import flandre.scarlet.thevoidkingdom.utils.game.menu.CloseReason;
import flandre.scarlet.thevoidkingdom.utils.game.menu.OpenReason;
import io.lumine.mythic.lib.api.item.NBTItem;
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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;
import org.jetbrains.annotations.Nullable;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Bean
public class EnchantTableListener implements Listener {
    private final CoolDownMap<Location> coolDownMap = new CoolDownMap<>(200);

    public static void finishEnchant(Player player, ItemStack itemStack) {
        TheVoidKingdom.LOGGER.info("玩家 " + player.getName() + " 附魔得到了物品:");
        TheVoidKingdom.LOGGER.info(itemStack.toString());
        Location location = Objects.requireNonNull(EnchantLock.getByPlayer(player)).getLocation();
        ItemDisplay itemDisplay = searchEnchantItemDisplay(location);
        if (itemDisplay == null) {
            TheVoidKingdom.LOGGER.warning("带有附魔物品的展示实体因未知错误而找不到");
            return;
        }
        World world = player.getWorld();
        itemDisplay.setItemStack(itemStack);
        world.playSound(location, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 0);
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, location.clone().add(0.5, 2.9, 0.5), 500, 0.1, 0.1, 0.1, 3);
    }

    public static ItemDisplay searchEnchantItemDisplay(Location blockLocation) {
        Location centerLocation = blockLocation.clone().add(0.5, 0.0, 0.5);
        Collection<ItemDisplay> collection = centerLocation.getNearbyEntitiesByType(ItemDisplay.class, 0.2);
        for (ItemDisplay itemDisplay : collection) {
            PersistentDataContainer persistentDataContainer = itemDisplay.getPersistentDataContainer();
            PersistentDataContainer dataContainer = persistentDataContainer.get(TheVoidKingdom.DATA_NAMESPACED, PersistentDataType.TAG_CONTAINER);
            if (dataContainer == null) {
                continue;
            }
            if (!Objects.equals(dataContainer.get(TYPE, PersistentDataType.STRING), "EnchantTable")) {
                continue;
            }
            String locationStr = dataContainer.get(LOCATION, PersistentDataType.STRING);
            if (Objects.equals(locationStr, centerLocation.toString()) && itemDisplay.getItemStack() != null) {
                return itemDisplay;
            }
        }
        return null;
    }

    public static ItemDisplay spawnEnchantItemDisplay(Location centerLocation, ItemStack itemStack) {
        ItemDisplay itemDisplay = (ItemDisplay) centerLocation.getWorld().spawnEntity(centerLocation, EntityType.ITEM_DISPLAY);
        itemDisplay.setItemStack(itemStack);
        itemDisplay.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.FIXED);
        itemDisplay.setBillboard(Display.Billboard.FIXED);
        itemDisplay.setShadowRadius(0);
        itemDisplay.setTransformation(new Transformation(new Vector3f(0F, 1.6F, 0F), new AxisAngle4f(0, 0, 1, 0), new Vector3f(0.6F, 0.6F, 0.6F), new AxisAngle4f(0, 0, 1, 0)));
        PersistentDataContainer persistentDataContainer = itemDisplay.getPersistentDataContainer();
        PersistentDataContainer dataContainer = persistentDataContainer.getAdapterContext().newPersistentDataContainer();
        dataContainer.set(TYPE, PersistentDataType.STRING, "EnchantTable");
        dataContainer.set(ROTATION_TYPE, PersistentDataType.STRING, "Y");
        dataContainer.set(LOCATION, PersistentDataType.STRING, centerLocation.toString());
        persistentDataContainer.set(TheVoidKingdom.DATA_NAMESPACED, PersistentDataType.TAG_CONTAINER, dataContainer);
        return itemDisplay;
    }


    public static @Nullable EnchantItemType getEnchantItemType(@Nullable ItemStack itemStack) {
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType()) {
            return null;
        }
        return EnchantItemType.ENCHANT_ITEM_TYPE_MAP.getOrDefault(nbtItem.getType(), null);
    }

    public enum EnchantConsumableType {
        LAPIS_LAZULI,
        MAGICAL_LAPIS_LAZULI
    }

    public static @Nullable EnchantConsumableType getEnchantConsumableType(@Nullable ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }
        return switch (ItemsAdderUtils.getItemNamespaceId(itemStack)) {
            case "LAPIS_LAZULI" -> EnchantConsumableType.LAPIS_LAZULI;
            case "vkmaterials:yunmoqingjinshi" -> EnchantConsumableType.MAGICAL_LAPIS_LAZULI;
            default -> null;
        };
    }


    private static final List<Integer> list1 = List.of(0, 1, 2, 3, 4, 0, 4, 0, 4, 0, 4, 0, 1, 2, 3, 4);
    private static final List<Integer> list2 = List.of(0, 0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 4);

    public static CardGameInfo getCardGameInfo(Location tableLocation, EnchantConsumableType enchantConsumableType) {
        double totalIdeaAmount = 0;
        double totalInspirationAmount = 0;
        double totalRankAmount = 0;
        int totalExpDiscount = 0;
        double totalStopPossible = 0;
        Location location = tableLocation.clone().add(-2, 0, -2);
        Block originBlock = location.getBlock();
        for (int j = 0; j <= 1; j++) {
            for (int i = 0; i < 16; i++) {
                Block block = originBlock.getRelative(list1.get(i), j, list2.get(i));
                CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
                if (customBlock == null) {
                    if (block.getType().equals(Material.BOOKSHELF)) {
                        totalIdeaAmount += 1;
                    }
                } else {
                    for (BookShelf bookShelf : BookShelf.BOOK_SHELVES) {
                        if (bookShelf.namespaceID().equals(customBlock.getNamespacedID())) {
                            totalIdeaAmount += bookShelf.ideaAmount();
                            totalInspirationAmount += bookShelf.inspirationAmount();
                            totalRankAmount += bookShelf.rankAmount();
                            totalExpDiscount += bookShelf.expDiscount();
                            totalStopPossible += bookShelf.stopPossible();
                            break;
                        }
                    }
                }
            }
        }
        switch (enchantConsumableType) {
            case LAPIS_LAZULI -> {
                totalIdeaAmount += 3;
                totalRankAmount += 1;
            }
            case MAGICAL_LAPIS_LAZULI -> {
                totalIdeaAmount += 6;
                totalRankAmount += 2;
            }
        }
        return new CardGameInfo(totalIdeaAmount, totalInspirationAmount, totalRankAmount, totalExpDiscount, totalStopPossible);
    }


    @EventHandler(priority = EventPriority.LOW)
    public void on(PlayerInteractEvent event) {
        if (event.useInteractedBlock() == Event.Result.DENY || event.useItemInHand() == Event.Result.DENY) {
            return;
        }
        if (!event.getAction().isRightClick()) {
            return;
        }


        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        Location location = block.getLocation();
        if (block.getType() != Material.ENCHANTING_TABLE) {
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
        if (coolDownMap.isCoolDown(location)) {
            return;
        }
        coolDownMap.add(location);


        Location centerLocation = location.clone().add(0.5, 0.0, 0.5);
        ItemDisplay enchantItemDisplay = searchEnchantItemDisplay(location);
        if (enchantItemDisplay == null) {
            //附魔台上无附魔物品
            if (inputItemStack == null) {
                //玩家空手
                TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>附魔台中魔咒的低吟声吸引着你的思绪……试着放些什么上去", false);
                return;
            }
            if (getEnchantConsumableType(inputItemStack) != null) {
                //玩家手持任意附魔耗材
                TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>附魔台上空无一物……你无法开始思索", false);
                return;
            }
            if (EnchantTableListener.getEnchantItemType(inputItemStack) != null) {
                //玩家手持一个可附魔物品
                if (MMOItemsUtils.hasEnchantment(inputItemStack)) {
                    //手持物品已有附魔
                    TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>附魔台对这个物品没有任何反应……", false);
                } else {
                    ItemStack itemStack = inputItemStack.clone();
                    inputItemStack.setAmount(0);
                    spawnEnchantItemDisplay(centerLocation, itemStack);
                }
            } else {
                //玩家手持非附魔耗材、非可附魔物品
                TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>附魔台对这个物品没有任何反应……", false);
            }
        } else {
            //附魔台上有附魔物品
            if (EnchantLock.hasLocation(location)) {
                TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>" + Objects.requireNonNull(EnchantLock.getByLocation(location)).getPlayer().getName() + " 正在用这个附魔台思考中，请勿打扰", false);
                return;
            }
            ItemStack enchantItem = enchantItemDisplay.getItemStack();
            assert enchantItem != null;
            if (inputItemStack != null && enchantItem.getEnchantments().isEmpty()) {
                EnchantConsumableType enchantConsumableType = getEnchantConsumableType(inputItemStack);
                if (enchantConsumableType != null) {
                    //玩家手持任意附魔耗材
                    EnchantItemType enchantItemType = getEnchantItemType(enchantItem);
                    if (enchantItemType == null) {
                        //附魔物品有问题（理论上不会触发）
                        TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>非法操作: 无法附魔的物品！请将错误汇报给管理员！", true);
                        TheVoidKingdom.LOGGER.warning(event.getPlayer().getName() + " 与放置了非法物品的附魔台发生了交互");
                    } else {
                        CardGameInfo cardGameInfo = getCardGameInfo(location, enchantConsumableType);
                        int requireExp = cardGameInfo.getRequireExp();
                        if (player.getLevel() < requireExp) {
                            //玩家经验不足
                            TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>附魔经验等级不足 <green>" + requireExp + "L" + "<yellow> ……你无法开始思索", false);
                        } else {
                            //启动附魔
                            EnchantMenu enchantMenu = new EnchantMenu(player, location, enchantItem, new CardGame(enchantItemType, cardGameInfo));
                            CardGameListener.uuidEnchantMenuMap.put(player.getUniqueId(), enchantMenu);
                            World world = player.getWorld();
                            world.spawnParticle(Particle.ITEM_CRACK, player.getLocation().add(0.0, 1.0, 0.0), 20, 0.2, 0.2, 0.2, 0.1, inputItemStack);
                            world.playSound(location, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1F, 0.7F);
                            player.setLevel(player.getLevel() - requireExp);
                            inputItemStack.setAmount(inputItemStack.getAmount() - 1);
                            enchantMenu.open(OpenReason.OPEN_NEW);
                        }
                    }
                    return;
                }
            }
            //玩家空手 手持非附魔耗材 附魔台上物品已有附魔
            enchantItemDisplay.remove();
            TheVoidKingdomUtils.giveItemStackToPlayer(player, enchantItem);
            player.playSound(centerLocation, Sound.ENTITY_ITEM_PICKUP, 1, 1);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Block block = event.getBlock();
        if (block.getType() != Material.ENCHANTING_TABLE) {
            return;
        }
        Location location = block.getLocation();
        ItemDisplay itemDisplay = searchEnchantItemDisplay(location);
        if (itemDisplay != null) {
            ItemStack itemStack = itemDisplay.getItemStack();
            itemDisplay.remove();
            assert itemStack != null;
            block.getWorld().dropItem(location.clone().add(0.5, 0.5, 0.5), itemStack);
        }
        if (EnchantLock.hasLocation(location)) {
            EnchantMenu enchantMenu = CardGameListener.uuidEnchantMenuMap.get(Objects.requireNonNull(EnchantLock.getByLocation(location)).getPlayer().getUniqueId());
            enchantMenu.close(CloseReason.BLOCK_BREAK);
        }
    }

    public static final NamespacedKey ROTATION_TYPE = new NamespacedKey(TheVoidKingdom.PLUGIN, "rotation_type");
    public static final NamespacedKey LAST_DEGREE = new NamespacedKey(TheVoidKingdom.PLUGIN, "last_degree");
    public static final NamespacedKey LOCATION = new NamespacedKey(TheVoidKingdom.PLUGIN, "location");
    public static final NamespacedKey TYPE = new NamespacedKey(TheVoidKingdom.PLUGIN, "type");


    @IntervalTask(value = 20, delay = 20, isAsynchronously = false)
    public void on() {
        for (World world : Bukkit.getWorlds()) {
            Collection<ItemDisplay> collection = world.getEntitiesByClass(ItemDisplay.class);
            for (ItemDisplay itemDisplay : collection) {
                PersistentDataContainer persistentDataContainer = itemDisplay.getPersistentDataContainer();
                PersistentDataContainer dataContainer = persistentDataContainer.get(TheVoidKingdom.DATA_NAMESPACED, PersistentDataType.TAG_CONTAINER);
                if (dataContainer == null) {
                    continue;
                }
                if (!Objects.equals(dataContainer.get(TYPE, PersistentDataType.STRING), "EnchantTable")) {
                    continue;
                }
                String rotationType = dataContainer.get(ROTATION_TYPE, PersistentDataType.STRING);
                if (rotationType == null) {
                    continue;
                }
                if (!rotationType.equals("Y")) {
                    continue;
                }
                int lastDegree = dataContainer.getOrDefault(LAST_DEGREE, PersistentDataType.INTEGER, 0);
                Transformation transformation = itemDisplay.getTransformation();
                int newDegree = lastDegree <= -9999990 ? 0 : lastDegree - 45;
                itemDisplay.setTransformation(new Transformation(
                        transformation.getTranslation(),
                        new AxisAngle4f((float) (newDegree / 180.0 * Math.PI), 0F, 1F, 0F),
                        transformation.getScale(),
                        new AxisAngle4f()
                ));
                itemDisplay.setInterpolationDuration(20);
                itemDisplay.setInterpolationDelay(0);
                dataContainer.set(LAST_DEGREE, PersistentDataType.INTEGER, newDegree);
                persistentDataContainer.set(TheVoidKingdom.DATA_NAMESPACED, PersistentDataType.TAG_CONTAINER, dataContainer);
            }
        }
    }
}
