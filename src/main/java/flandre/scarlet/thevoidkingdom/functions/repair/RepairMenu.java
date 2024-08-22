package flandre.scarlet.thevoidkingdom.functions.repair;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.events.PlayerRepairItemEvent;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import flandre.scarlet.thevoidkingdom.utils.game.menu.BlockMenu;
import flandre.scarlet.thevoidkingdom.utils.game.menu.CloseReason;
import flandre.scarlet.thevoidkingdom.utils.game.menu.MenuProtect;
import flandre.scarlet.thevoidkingdom.utils.game.menu.OpenReason;
import net.Indyuce.mmoitems.api.interaction.util.DurabilityItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RepairMenu extends BlockMenu {

    public class RepairMenuHolder extends BlockMenuHolder {
        @Override
        public @NotNull RepairMenu getMenu() {
            return RepairMenu.this;
        }
    }

    private final ItemStack repairItemStack;
    private Map<String, Integer> ingredients = new HashMap<>();
    private static final List<Integer> slots = List.of(28, 29, 30, 37, 38, 39);

    public RepairMenu(Player owner, Location location, ItemStack repairItemStack, RepairItemType repairItemType, double durability, double maxDurability) {
        super(owner, location);
        this.repairItemStack = repairItemStack;
        this.inventory = Bukkit.createInventory(new RepairMenuHolder(), 45, Component.text(":offset_-8::gui_repair:"));
        inventory.setItem(14, repairItemStack);
        double var = 1 - durability / maxDurability;
        repairItemType.ingredients().forEach((namespaceId, amount) -> {
            ingredients.put(namespaceId, ((int) Math.ceil(amount * var)));
        });
        int totalEnchantLevel = MMOItemsUtils.getTotalEnchantmentLevel(repairItemStack);
        if (totalEnchantLevel > 0) {
            ingredients.put("EXPERIENCE_BOTTLE", Math.min(totalEnchantLevel * 2, 64));
        }
        Iterator<Integer> iterator = slots.iterator();
        ingredients.forEach((namespaceId, amount) -> {
            if (iterator.hasNext()) {
                ItemStack itemStack = ItemsAdderUtils.getItemStackNoCheck(namespaceId);
                itemStack.setAmount(amount);
                inventory.setItem(iterator.next(), itemStack);
            }
        });
        inventory.setItem(33, RepairMenuIcon.CLOSE.getItemStack());
        inventory.setItem(34, RepairMenuIcon.CLOSE.getItemStack());
        inventory.setItem(42, RepairMenuIcon.FINISH.getItemStack());
        inventory.setItem(43, RepairMenuIcon.FINISH.getItemStack());
    }


    @Override
    public void open(OpenReason openReason) {
        if (openReason == OpenReason.OPEN_NEW) {
            owner.openInventory(inventory);
            MenuProtect.addDenyAll(owner, this);
        }
    }

    @Override
    public void close(CloseReason closeReason) {
        switch (closeReason) {
            case BLOCK_BREAK, TOO_FAR, CANCEL_REPAIR, DEFAULT, OPEN_OTHER -> {
                this.inventory.close();
                MenuProtect.removeDenyAll(owner);
                TheVoidKingdomUtils.sendMessageToPlayer(owner, "<yellow>你取消了修复", false);
                TheVoidKingdomUtils.giveItemStackToPlayer(owner, repairItemStack);
            }
            case FINISH_REPAIR -> {
                this.inventory.close();
                MenuProtect.removeDenyAll(owner);
                TheVoidKingdomUtils.sendMessageToPlayer(owner, "<yellow>修复成功！", false);
                TheVoidKingdom.LOGGER.info("玩家 " + owner.getName() + " 修复了物品:");
                TheVoidKingdom.LOGGER.info(repairItemStack.toString());
                DurabilityItem durabilityItem = new DurabilityItem(owner, repairItemStack);
                ItemStack resultItemStack = durabilityItem.addDurability(durabilityItem.getMaxDurability()).toItem();
                PlayerRepairItemEvent playerRepairItemEvent = new PlayerRepairItemEvent(owner, resultItemStack);
                Bukkit.getPluginManager().callEvent(playerRepairItemEvent);
                TheVoidKingdomUtils.giveItemStackToPlayer(owner, resultItemStack);
            }
            case REPAIR_MATERIAL_NOT_ENOUGH -> {
                this.inventory.close();
                MenuProtect.removeDenyAll(owner);
                TheVoidKingdomUtils.sendMessageToPlayer(owner, "<red>你没有足够的材料修复该物品……", false);
                TheVoidKingdomUtils.giveItemStackToPlayer(owner, repairItemStack);
            }
        }
    }


    public ItemStack getRepairItemStack() {
        return repairItemStack;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }
}
