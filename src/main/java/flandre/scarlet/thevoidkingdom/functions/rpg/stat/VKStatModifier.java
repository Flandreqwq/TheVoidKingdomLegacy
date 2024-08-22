package flandre.scarlet.thevoidkingdom.functions.rpg.stat;

import java.util.UUID;

public class VKStatModifier {
    private final UUID uuid;
    private final String key;
    private final VKStatType vkStatType;
    private final ModifierType modifierType;
    private final double value;

    public VKStatModifier(UUID uuid, String key, VKStatType vkStatType, ModifierType modifierType, double value) {
        this.uuid = uuid;
        this.key = key;
        this.vkStatType = vkStatType;
        this.modifierType = modifierType;
        this.value = value;
    }

    public VKStatModifier(String key, VKStatType vkStatType, ModifierType modifierType, double value) {
        this(UUID.randomUUID(), key, vkStatType, modifierType, value);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getKey() {
        return key;
    }

    public VKStatType getVkStatType() {
        return vkStatType;
    }

    public ModifierType getModifierType() {
        return modifierType;
    }

    public double getValue() {
        return value;
    }

    public enum ModifierType {
        FLAT,
        RELATIVE
    }
}
