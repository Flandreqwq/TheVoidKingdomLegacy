package flandre.scarlet.thevoidkingdom.functions.enchant;


import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.events.PlayerFinishEnchantEvent;
import flandre.scarlet.thevoidkingdom.utils.events.PlayerPlayCardGameEvent;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import flandre.scarlet.thevoidkingdom.utils.game.menu.CloseReason;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Bean
public class CardGameListener implements Listener {
    public static Map<UUID, EnchantMenu> uuidEnchantMenuMap = new HashMap<>();

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        if (!(inventory.getHolder() instanceof EnchantMenu.EnchantMenuHolder)) {
            return;
        }
        EnchantMenu enchantMenu = uuidEnchantMenuMap.get(player.getUniqueId());
        int slot = event.getRawSlot();
        ItemStack enchantItemStack = enchantMenu.getItemStack();
        if (enchantMenu.isLock()) {
            if (enchantMenu.isStop()) {
                //精神涣散时
                if (slot == 36 || slot == 37) {
                    //点击停止思考
                    enchantMenu.close(CloseReason.CANCEL_ENCHANT);
                } else if (slot == 45 || slot == 46) {
                    //点击完成附魔
                    if (enchantMenu.getEnchantLevelMap().isEmpty()) {
                        enchantMenu.close(CloseReason.FINISH_EMPTY_ENCHANT);
                    } else {
                        EnchantTableListener.finishEnchant(player, enchantItemStack);
                        PlayerFinishEnchantEvent playerFinishEnchantEvent = new PlayerFinishEnchantEvent(player, enchantItemStack);
                        Bukkit.getPluginManager().callEvent(playerFinishEnchantEvent);
                        enchantMenu.close(CloseReason.FINISH_ENCHANT);
                    }
                }
            }
            return;
        }
        CardGame cardGame = enchantMenu.getCardGame();
        CardGameInfo cardGameInfo = cardGame.getCardGameInfo();
        int point = cardGame.getPoint();
        Map<VKEnchantment, Integer> enchantLevelMap = enchantMenu.getEnchantLevelMap();
        List<Boolean> cardStateList = cardGame.getCardStateList();
        List<Card> cardResultList = cardGame.getCardResultList();
        if (EnchantMenu.CARD_SLOTS.contains(slot)) {
            Location location = player.getLocation();
            int index = EnchantMenu.CARD_SLOTS.indexOf(slot);
            if (cardStateList.get(index)) {
                return;
            }
            if (point <= 0) {
                return;
            }
            point--;
            enchantMenu.setLock(true);
            enchantMenu.setPointAndIcon(point, cardGameInfo);
            cardStateList.set(index, true);


            Card card = cardResultList.get(index);
            World world = player.getWorld();
            Boolean b = enchantMenu.getFlip();
            if (b) {
                enchantMenu.setFirstCardIndex(index);
            } else {
                enchantMenu.setSecondCardIndex(index);
            }
            enchantMenu.setFlip(!b);
            inventory.setItem(slot, card.getMenuIcon().getItemStack());
            player.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1F, 1);

            if (card.equals(Card.STOP)) {
                enchantMenu.setPointAndIcon(0, cardGameInfo);
                enchantMenu.setStop(true);
                world.playSound(location, Sound.ENTITY_WITHER_SPAWN, 1F, 2F);
                PlayerPlayCardGameEvent playerPlayCardGameEvent = new PlayerPlayCardGameEvent(player, enchantMenu);
                Bukkit.getPluginManager().callEvent(playerPlayCardGameEvent);
                return;
            }

            int firstCardIndex = enchantMenu.getFirstCardIndex();
            int secondCardIndex = enchantMenu.getSecondCardIndex();
            Card card1 = cardResultList.get(firstCardIndex);
            Card card2 = cardResultList.get(secondCardIndex);
            if (!b) {
                if (Objects.equals(card1, card2)) {
                    if (card1.equals(Card.REALLOC)) {
                        CardGameInfo newContext = new CardGameInfo(cardGameInfo.getTotalIdeaAmount(), cardGameInfo.getTotalInspirationAmount(),
                                cardGameInfo.getTotalRankAmount(), cardGame.getPoint(), cardGameInfo.getMaxLevel(), cardGameInfo.getEnchantmentAmount(),
                                0, cardGameInfo.getStopAmount(),
                                cardGameInfo.getMorePointAmount1(), cardGameInfo.getMorePointAmount2(), cardGameInfo.getMorePointAmount3());
                        CardGame newGame = new CardGame(cardGame.getEnchantItem(), newContext);
                        Bukkit.getScheduler().runTaskLater(TheVoidKingdom.PLUGIN, () -> {
                            enchantMenu.setCardGame(newGame);
                            for (int i = 0; i < 54; i++) {
                                if (EnchantMenu.CARD_SLOTS.contains(i)) {
                                    inventory.setItem(i, EnchantMenuIcon.CARD.getItemStack());
                                }
                            }
                            PlayerPlayCardGameEvent playerPlayCardGameEvent = new PlayerPlayCardGameEvent(player, enchantMenu);
                            Bukkit.getPluginManager().callEvent(playerPlayCardGameEvent);
                            enchantMenu.setLock(false);
                            if (player.getOpenInventory().getTopInventory().getHolder() instanceof EnchantMenu.EnchantMenuHolder) {
                                world.playSound(location, Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                            }
                        }, 30);
                        return;
                    } else if (card1.equals(Card.MORE_POINT_1)) {
                        enchantMenu.setPointAndIcon(point + 3, cardGameInfo);
                        world.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 0.1F, 1.8F);
                    } else if (card1.equals(Card.MORE_POINT_2)) {
                        enchantMenu.setPointAndIcon(point + 5, cardGameInfo);
                        world.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 0.1F, 0.8F);
                    } else if (card1.equals(Card.MORE_POINT_3)) {
                        enchantMenu.setPointAndIcon(point + 8, cardGameInfo);
                        world.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 0.1F, 0F);
                    } else {
                        int level = enchantLevelMap.getOrDefault(card1.getVkEnchantment(), 0) + 1;
                        int rankMaxLevelLimit = cardGameInfo.getMaxLevel();
                        if (level > rankMaxLevelLimit) {
                            world.playSound(location, Sound.BLOCK_FIRE_EXTINGUISH, 1, 2);
                            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>位阶产生的枷锁限制了你的思绪……", false);
                        } else if (level > getItemMaxLevelLimit(enchantItemStack)) {
                            world.playSound(location, Sound.BLOCK_FIRE_EXTINGUISH, 1, 2);
                            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>此物品无法承载如此高等级的附魔……", false);
                        } else {
                            VKEnchantment enchantment = card1.getVkEnchantment();
                            enchantLevelMap.put(enchantment, level);
                            enchantMenu.setItemStack(MMOItemsUtils.changeEnchantLevel(enchantItemStack, enchantment, level));
                            world.playSound(location, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
                            world.spawnParticle(Particle.ENCHANTMENT_TABLE, enchantMenu.getBlockLocation().clone().add(0.5, 2.0, 0.5), 20, 0, 0, 0, 1);
                        }
                    }
                    Bukkit.getScheduler().runTaskLater(TheVoidKingdom.PLUGIN, () -> {
                        PlayerPlayCardGameEvent playerPlayCardGameEvent = new PlayerPlayCardGameEvent(player, enchantMenu);
                        Bukkit.getPluginManager().callEvent(playerPlayCardGameEvent);
                        enchantMenu.setLock(false);
                    }, 4);
                } else {
                    Bukkit.getScheduler().runTaskLater(TheVoidKingdom.PLUGIN, () -> {
                        if (player.getOpenInventory().getTopInventory().getHolder() instanceof EnchantMenu.EnchantMenuHolder) {
                            world.playSound(location, Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                        }
                        cardStateList.set(firstCardIndex, false);
                        cardStateList.set(secondCardIndex, false);
                        inventory.setItem(EnchantMenu.CARD_SLOTS.get(firstCardIndex), EnchantMenuIcon.CARD.getItemStack());
                        inventory.setItem(EnchantMenu.CARD_SLOTS.get(secondCardIndex), EnchantMenuIcon.CARD.getItemStack());
                        PlayerPlayCardGameEvent playerPlayCardGameEvent = new PlayerPlayCardGameEvent(player, enchantMenu);
                        Bukkit.getPluginManager().callEvent(playerPlayCardGameEvent);
                        enchantMenu.setLock(false);
                    }, 20);
                }
            } else {
                Bukkit.getScheduler().runTaskLater(TheVoidKingdom.PLUGIN, () -> {
                    PlayerPlayCardGameEvent playerPlayCardGameEvent = new PlayerPlayCardGameEvent(player, enchantMenu);
                    Bukkit.getPluginManager().callEvent(playerPlayCardGameEvent);
                    enchantMenu.setLock(false);
                }, 4);
            }
        } else if (slot == 36 || slot == 37) {
            //点击停止思考
            enchantMenu.close(CloseReason.CANCEL_ENCHANT);
        } else if (slot == 45 || slot == 46) {
            //点击完成附魔
            if (enchantMenu.getEnchantLevelMap().isEmpty()) {
                enchantMenu.close(CloseReason.FINISH_EMPTY_ENCHANT);
            } else {
                EnchantTableListener.finishEnchant(player, enchantItemStack);
                PlayerFinishEnchantEvent playerFinishEnchantEvent = new PlayerFinishEnchantEvent(player, enchantItemStack);
                Bukkit.getPluginManager().callEvent(playerFinishEnchantEvent);
                enchantMenu.close(CloseReason.FINISH_ENCHANT);
            }
        }
    }

    private int getItemMaxLevelLimit(ItemStack itemStack) {
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType()) {
            return 0;
        }
        return (int) nbtItem.getDouble("MMOITEMS_CUSTOM_ENCHANT_LEVEL_LIMIT");
    }
}
