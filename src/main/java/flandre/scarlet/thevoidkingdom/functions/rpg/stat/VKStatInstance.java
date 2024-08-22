package flandre.scarlet.thevoidkingdom.functions.rpg.stat;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.rpg.player.RPGPlayer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class VKStatInstance {
    private final VKStatType vkStatType;
    private final Set<VKStatModifier> vkStatModifiers = new HashSet<>();
    private final double baseValue;
    private double totalAddValue = 0;
    private double totalMultiplyValue = 1;
    private double totalValue;

    public VKStatInstance(VKStatType vkStatType, double baseValue) {
        this.vkStatType = vkStatType;
        this.baseValue = baseValue;
    }

    public void calculate() {
        double add = 0;
        double multiply = 1;
        Iterator<VKStatModifier> iterator = vkStatModifiers.iterator();
        while (iterator.hasNext()) {
            VKStatModifier vkStatModifier = iterator.next();
            if (vkStatModifier instanceof TempVKStatModifier tempVKStatModifier) {
                if (tempVKStatModifier.getOverTime() < System.currentTimeMillis()) {
                    iterator.remove();
                    continue;
                }
            }
            switch (vkStatModifier.getModifierType()) {
                case FLAT -> add += vkStatModifier.getValue();
                case RELATIVE -> multiply += vkStatModifier.getValue();
            }
        }
        totalAddValue = add;
        totalMultiplyValue = multiply;
        totalValue = (baseValue + totalAddValue) * totalMultiplyValue;
        System.out.println("更新后的" + vkStatType + ": " + totalValue);
    }

    public boolean addModifier(VKStatModifier vkStatModifier) {
        if (vkStatModifier.getVkStatType().equals(vkStatType)) {
            vkStatModifiers.add(vkStatModifier);
            return true;
        } else {
            TheVoidKingdom.LOGGER.warning("无法将 " + vkStatModifier.getVkStatType().name() + " 的VKStatModifier添加到 " + vkStatType.name() + " 的VKStatInstance中");
            return false;
        }
    }

    public boolean removeAllModifiers(String key) {
        return vkStatModifiers.removeIf(vkStatModifier -> vkStatModifier.getKey().equals(key));
    }

    public void handle(RPGPlayer rpgPlayer) {
        vkStatType.getStatHandler().handle(rpgPlayer, totalValue);
    }

    public Set<VKStatModifier> getVkStatModifiers() {
        return vkStatModifiers;
    }

    public VKStatType getVkStatType() {
        return vkStatType;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public double getTotalAddValue() {
        return totalAddValue;
    }

    public double getTotalMultiplyValue() {
        return totalMultiplyValue;
    }
}
