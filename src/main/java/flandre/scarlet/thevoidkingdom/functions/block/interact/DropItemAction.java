package flandre.scarlet.thevoidkingdom.functions.block.interact;

import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;

public class DropItemAction implements ResultAction {
    private final String itemNamespaceId;
    private final int minAmount;
    private final int maxAmount;

    private final double possibility;

    public DropItemAction(String itemNamespaceId, int minAmount, int maxAmount, double possibility) {
        this.itemNamespaceId = itemNamespaceId;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.possibility = possibility;
    }

    @Override
    public String getResultNamespaceId() {
        return itemNamespaceId;
    }

    @Override
    public void execute(Location location) {
        ItemStack itemStack;
        if (itemNamespaceId.contains(":")) {
            CustomStack customStack = CustomStack.getInstance(itemNamespaceId);
            itemStack = customStack.getItemStack();
        } else {
            itemStack = new ItemStack(Objects.requireNonNull(Material.getMaterial(itemNamespaceId)));
        }
        Random random = new Random();
        if (random.nextDouble() > possibility) {
            return;
        }
        itemStack.setAmount(random.nextInt(maxAmount - minAmount + 1) + minAmount);
        location.getWorld().dropItem(location.clone().add(0.5, 0.5, 0.5), itemStack);
    }

    @Override
    public boolean isValid() {
        return minAmount <= maxAmount && ItemsAdderUtils.isItemNamespaceIdExist(itemNamespaceId);
    }

    public String getItemNamespaceId() {
        return itemNamespaceId;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public double getPossibility() {
        return possibility;
    }
}
