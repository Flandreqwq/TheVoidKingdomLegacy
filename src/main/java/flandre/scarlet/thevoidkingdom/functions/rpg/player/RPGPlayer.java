package flandre.scarlet.thevoidkingdom.functions.rpg.player;

import flandre.scarlet.thevoidkingdom.functions.rpg.stat.VKStatInstance;
import flandre.scarlet.thevoidkingdom.functions.rpg.stat.VKStatModifier;
import flandre.scarlet.thevoidkingdom.functions.rpg.stat.VKStatType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RPGPlayer {
    private final Player player;
    private final Map<VKStatType, VKStatInstance> playerVKStatMap = new HashMap<>();

    public RPGPlayer(Player player) {
        this.player = player;
    }

    public void updatePlayerVKStats(String key, VKStatModifier... vkStatModifiers) {
        Set<VKStatType> tempSet = new HashSet<>();
        //通过key移除旧StatModifier
        for (VKStatInstance vkStatInstance : playerVKStatMap.values()) {
            if (vkStatInstance.removeAllModifiers(key)) {
                tempSet.add(vkStatInstance.getVkStatType());
            }
        }
        //添加所有新的StatModifier
        for (VKStatModifier vkStatModifier : vkStatModifiers) {
            VKStatType targetVKStatType = vkStatModifier.getVkStatType();
            VKStatInstance vkStatInstance = playerVKStatMap.computeIfAbsent(targetVKStatType,
                    vkStatType -> new VKStatInstance(vkStatType, getPlayerVKStatBaseValue(vkStatType)));
            if (vkStatInstance.addModifier(vkStatModifier)) {
                tempSet.add(targetVKStatType);
            }
        }
        //所有被修改的属性都重新计算终值
        for (VKStatType vkStatType : tempSet) {
            playerVKStatMap.get(vkStatType).calculate();
        }
        //处理每个属性的Handler
        for (VKStatInstance vkStatInstance : playerVKStatMap.values()) {
            vkStatInstance.handle(this);
        }
    }


    public double getPlayerVKStatBaseValue(VKStatType vkStatType) {
        //TODO 加入职业基值支持
        return vkStatType.getBaseValue();
    }

    public Player getPlayer() {
        return player;
    }


}
