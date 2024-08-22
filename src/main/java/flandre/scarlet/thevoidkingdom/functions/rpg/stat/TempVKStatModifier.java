package flandre.scarlet.thevoidkingdom.functions.rpg.stat;

import java.util.UUID;

public class TempVKStatModifier extends VKStatModifier {
    private Long overTime;

    public TempVKStatModifier(UUID uuid, String key, VKStatType vkStatType, VKStatModifier.ModifierType modifierType, double value, Long overTime) {
        super(uuid, key, vkStatType, modifierType, value);
        this.overTime = overTime;
    }

    public TempVKStatModifier(String key, VKStatType vkStatType, VKStatModifier.ModifierType modifierType, double value, Long overTime) {
        super(key, vkStatType, modifierType, value);
        this.overTime = overTime;
    }

    public Long getOverTime() {
        return overTime;
    }

    public void addOverTime(int tick) {
        this.overTime += tick * 50L;
    }
}
