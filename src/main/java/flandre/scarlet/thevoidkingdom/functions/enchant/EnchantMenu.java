package flandre.scarlet.thevoidkingdom.functions.enchant;

import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import flandre.scarlet.thevoidkingdom.utils.game.menu.BlockMenu;
import flandre.scarlet.thevoidkingdom.utils.game.menu.CloseReason;
import flandre.scarlet.thevoidkingdom.utils.game.menu.MenuProtect;
import flandre.scarlet.thevoidkingdom.utils.game.menu.OpenReason;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnchantMenu extends BlockMenu {
    public class EnchantMenuHolder extends BlockMenuHolder {
        @Override
        public @NotNull EnchantMenu getMenu() {
            return EnchantMenu.this;
        }
    }
    private ItemStack itemStack;
    private Boolean lock = false;
    private Boolean stop = false;
    private Boolean flip = true;
    private int firstCardIndex;
    private int secondCardIndex;
    private CardGame cardGame;
    private final Map<VKEnchantment, Integer> enchantLevelMap = new HashMap<>();
    public static final List<Integer> CARD_SLOTS = List.of(
            3, 4, 5, 6, 7, 8,
            12, 13, 14, 15, 16, 17,
            21, 22, 23, 24, 25, 26,
            30, 31, 32, 33, 34, 35,
            39, 40, 41, 42, 43, 44,
            48, 49, 50, 51, 52, 53
    );


    public EnchantMenu(Player owner, Location blockLocation, ItemStack itemStack, CardGame cardGame) {
        super(owner, blockLocation);
        this.itemStack = itemStack;
        this.cardGame = cardGame;
        this.inventory = Bukkit.createInventory(new EnchantMenuHolder(), 54, Component.text(":offset_-22::gui_enchant:"));
        for (int i = 0; i < 54; i++) {
            if (EnchantMenu.CARD_SLOTS.contains(i)) {
                inventory.setItem(i, EnchantMenuIcon.CARD.getItemStack());
            }
        }
        setPointAndIcon(cardGame.getPoint(), cardGame.getCardGameInfo());
        setItemStack(itemStack);
        inventory.setItem(36, EnchantMenuIcon.CLOSE.getItemStack());
        inventory.setItem(37, EnchantMenuIcon.CLOSE.getItemStack());
        inventory.setItem(45, EnchantMenuIcon.FINISH.getItemStack());
        inventory.setItem(46, EnchantMenuIcon.FINISH.getItemStack());
    }


    public CardGame getCardGame() {
        return cardGame;
    }

    public Boolean isLock() {
        return lock;
    }

    public void setPointAndIcon(int point, CardGameInfo cardGameInfo) {
        this.cardGame.setPoint(point);
        ItemStack pointIcon;
        if (point <= 0) {
            pointIcon = EnchantMenuIcon.NO_POINT.getItemStack();
        } else {
            pointIcon = EnchantMenuIcon.POINT.getItemStack();
            pointIcon.lore(TheVoidKingdomUtils.deserializeList(List.of(
                    "<!italic><white>%img_short_line_3%",
                    "<!italic><gray>  剩余的思绪点数量",
                    "<!italic><gray>  思索未知时消耗",
                    "<!italic><white>%img_short_line_3%",
                    "<!italic><white> %img_enchant_stat_idea% <green>思绪: <white>" + String.format("%.2f", cardGameInfo.getTotalIdeaAmount()),
                    "<!italic><white> %img_enchant_stat_inspiration% <#FACC1F>灵感: <white>" + String.format("%.2f", cardGameInfo.getTotalInspirationAmount()),
                    "<!italic><white> %img_enchant_stat_rank% <aqua>位阶: <white>" + String.format("%.2f", cardGameInfo.getTotalRankAmount()),
                    "<!italic><white>%img_short_line_3%"
            ), true));
            pointIcon.setAmount(point);
        }
        inventory.setItem(0, pointIcon);
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public Boolean isStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    public Boolean getFlip() {
        return flip;
    }

    public void setFlip(Boolean flip) {
        this.flip = flip;
    }

    public int getFirstCardIndex() {
        return firstCardIndex;
    }

    public void setFirstCardIndex(int firstCardIndex) {
        this.firstCardIndex = firstCardIndex;
    }

    public int getSecondCardIndex() {
        return secondCardIndex;
    }

    public void setSecondCardIndex(int secondCardIndex) {
        this.secondCardIndex = secondCardIndex;
    }

    @Override
    public void open(OpenReason openReason) {
        if (openReason == OpenReason.OPEN_NEW) {
            owner.openInventory(inventory);
            EnchantLock.addLock(blockLocation, owner);
            MenuProtect.addDenyAll(owner, this);
        }
    }

    @Override
    public void close(CloseReason closeReason) {
        switch (closeReason) {
            case BLOCK_BREAK, DAMAGED, TOO_FAR -> {
                TheVoidKingdomUtils.sendMessageToPlayer(owner, "<red>你的思绪被意外中断了……", false);
            }
            case FINISH_ENCHANT -> {
                TheVoidKingdomUtils.sendMessageToPlayer(owner, "<yellow>魔咒的低吟声在你的耳边回响……", false);
            }
            case FINISH_EMPTY_ENCHANT -> {
                TheVoidKingdomUtils.sendMessageToPlayer(owner, "<red>你没有思索出任何有价值的东西……", false);
            }
            case CANCEL_ENCHANT, DEFAULT, OPEN_OTHER -> {
                TheVoidKingdomUtils.sendMessageToPlayer(owner, "<red>你停止了思索……", false);
            }
            default -> {
                return;
            }
        }
        this.inventory.close();
        EnchantLock.removeLock(owner);
        MenuProtect.removeDenyAll(owner);
    }

    public void setCardGame(CardGame cardGame) {
        this.cardGame = cardGame;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        inventory.setItem(18, itemStack);
    }

    public Map<VKEnchantment, Integer> getEnchantLevelMap() {
        return enchantLevelMap;
    }
}
