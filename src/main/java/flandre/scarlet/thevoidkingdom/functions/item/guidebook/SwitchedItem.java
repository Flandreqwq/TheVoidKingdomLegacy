package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SwitchedItem {
    private final List<ItemStack> itemStacks;
    private final SwitchMode switchMode;
    private final int slot;
    private int last = 0;

    public SwitchedItem(List<ItemStack> itemStacks, SwitchMode switchMode, int slot) {
        this.itemStacks = itemStacks;
        this.switchMode = switchMode;
        this.slot = slot;
    }

    public SwitchedItem(Collection<String> namespaceIds, SwitchMode switchMode, int slot) {
        List<ItemStack> list=new ArrayList<>();
        namespaceIds.forEach(namespaceId -> {
            list.add(ItemsAdderUtils.getItemStackNotNull(namespaceId));
        });
        this.itemStacks = list;
        this.switchMode = switchMode;
        this.slot = slot;
    }

    public enum SwitchMode{
        NORMAL,
        RANDOM
    }

    public List<ItemStack> getItemStacks() {
        return itemStacks;
    }

    public SwitchMode getSwitchMode() {
        return switchMode;
    }

    public int getSlot() {
        return slot;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }
}
