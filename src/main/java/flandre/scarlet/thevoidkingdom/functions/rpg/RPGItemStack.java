package flandre.scarlet.thevoidkingdom.functions.rpg;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import flandre.scarlet.thevoidkingdom.functions.rpg.stat.VKStat;
import flandre.scarlet.thevoidkingdom.functions.rpg.stat.VKStatType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RPGItemStack {
    private final ItemStack itemStack;
    private final Map<VKStatType, VKStat> statMap = new HashMap<>();

    public RPGItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;

        NBTItem nbtItem = new NBTItem(itemStack);
        NBTCompound nbtCompound = nbtItem.getCompound("vkstats");
        if (nbtCompound != null) {
            for (String key : nbtCompound.getKeys()) {
                switch (key){
                    case "base_damage"->{
//                        statMap.put(VKStatType.BASE_DAMAGE,)
                    }
                }
            }
        }
    }

    public VKStat getStat(VKStatType statEnum) {
        return statMap.getOrDefault(statEnum, null);
    }


}
