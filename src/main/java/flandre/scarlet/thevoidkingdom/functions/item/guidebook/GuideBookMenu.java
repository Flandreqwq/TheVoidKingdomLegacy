package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import flandre.scarlet.thevoidkingdom.utils.game.menu.CloseReason;
import flandre.scarlet.thevoidkingdom.utils.game.menu.Menu;
import flandre.scarlet.thevoidkingdom.utils.game.menu.MenuProtect;
import flandre.scarlet.thevoidkingdom.utils.game.menu.OpenReason;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class GuideBookMenu extends Menu {

    public abstract class GuideBookMenuHolder extends MenuHolder {
        @Override
        public @NotNull GuideBookMenu getMenu() {
            return GuideBookMenu.this;
        }
    }

    public static final Map<UUID, List<GuideBookMenu>> PLAYER_MENU_LIST = new HashMap<>();

    public GuideBookMenu(Player owner) {
        super(owner);
    }

    @Override
    public void open(OpenReason openReason) {
        if (inventory == null) {
            return;
        }
        switch (openReason) {
            case OPEN_NEW -> {
                List<GuideBookMenu> list = PLAYER_MENU_LIST.computeIfAbsent(owner.getUniqueId(), k -> new ArrayList<>());
                if (list.size() > 50) {
                    TheVoidKingdomUtils.sendMessageToPlayer(owner, "<red>禁止套娃！", false);
                    return;
                }
                list.add(this);
            }

            case LAST_PAGE, NEXT_PAGE, BACK_LAST_MENU -> {
                Inventory beforeInventory = owner.getOpenInventory().getTopInventory();
                if (beforeInventory.getHolder() instanceof SwitchableMenuHolder) {
                    SwitchedItemManager.stop(beforeInventory);
                }
            }
        }
        owner.openInventory(inventory);
        MenuProtect.addDenyAll(owner, this);
    }

    @Override
    public void close(CloseReason closeReason) {
        if (closeReason == CloseReason.DEFAULT) {
            inventory.close();
            List<GuideBookMenu> list = PLAYER_MENU_LIST.get(owner.getUniqueId());
            list.forEach(guideBookMenu -> {
                SwitchedItemManager.stop(guideBookMenu.inventory);
            });
            list.clear();
            MenuProtect.removeDenyAll(owner);
        }
    }


    public void backToLastMenu() {
        UUID uuid = owner.getUniqueId();
        List<GuideBookMenu> list = PLAYER_MENU_LIST.get(uuid);
        list.remove(list.size() - 1);
        GuideBookMenu guideBookMenu = list.get(list.size() - 1);
        guideBookMenu.open(OpenReason.BACK_LAST_MENU);
    }
}
