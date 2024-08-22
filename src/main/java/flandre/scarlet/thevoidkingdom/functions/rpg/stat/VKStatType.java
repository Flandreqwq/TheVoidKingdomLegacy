package flandre.scarlet.thevoidkingdom.functions.rpg.stat;

import flandre.scarlet.thevoidkingdom.functions.rpg.stat.handler.AttributeStatHandler;
import flandre.scarlet.thevoidkingdom.functions.rpg.stat.handler.StatHandler;
import org.bukkit.attribute.Attribute;

public enum VKStatType {
    BASE_DAMAGE(0, ),
    ATTACK_SPEED(4, new AttributeStatHandler(Attribute.GENERIC_ATTACK_SPEED, 1)),
    MOVEMENT_SPEED(0.1, new AttributeStatHandler(Attribute.GENERIC_MOVEMENT_SPEED, 1)),
    MAX_HEALTH(20, new AttributeStatHandler(Attribute.GENERIC_MAX_HEALTH, 1)),
    KNOCKBACK_RESISTANCE(0, new AttributeStatHandler(Attribute.GENERIC_KNOCKBACK_RESISTANCE, 1)),
    LUCK(0, new AttributeStatHandler(Attribute.GENERIC_LUCK, 1));
    private final double baseValue;
    private final StatHandler statHandler;

    VKStatType(double baseValue, StatHandler statHandler) {
        this.baseValue = baseValue;
        this.statHandler = statHandler;
    }


    public double getBaseValue() {
        return baseValue;
    }

    public StatHandler getStatHandler() {
        return statHandler;
    }
}
