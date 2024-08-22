package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.framework.IntervalTask;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Bean
public class SwitchedItemManager {
    private static final HashMap<Inventory, List<SwitchedItem>> map = new HashMap<>();

    public static void create(Inventory inventory, SwitchedItem switchedItem) {
        if (!(inventory.getHolder() instanceof SwitchableMenuHolder)) {
            return;
        }
        List<ItemStack> list = switchedItem.getItemStacks();
        if (list.isEmpty()) {
            return;
        }
        inventory.setItem(switchedItem.getSlot(), list.get(0));
        if (list.size() == 1) {
            return;
        }
        List<SwitchedItem> list1 = map.computeIfAbsent(inventory, inventory1 -> new ArrayList<>());
        list1.add(switchedItem);
    }

    public static void stop(Inventory inventory) {
        map.remove(inventory);
    }

    @IntervalTask(value = 30, isAsynchronously = false)
    public void on() {
        for (Map.Entry<Inventory, List<SwitchedItem>> entry : map.entrySet()) {
            Inventory inventory = entry.getKey();
            if (inventory.getViewers().isEmpty()) {
                continue;
            }
            for (SwitchedItem switchedItem : entry.getValue()) {
                int index = 0;
                List<ItemStack> list = switchedItem.getItemStacks();
                switch (switchedItem.getSwitchMode()) {
                    case NORMAL -> {
                        index = Math.floorMod(switchedItem.getLast() + 1, list.size());
                        switchedItem.setLast(index);
                    }
                    case RANDOM -> {
                        index = new Random().nextInt(list.size());
                    }
                }
                inventory.setItem(switchedItem.getSlot(), list.get(index));
            }
        }
//        System.out.println("inv number: " + map.keySet().size());
    }
}
