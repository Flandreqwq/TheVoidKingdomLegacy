package flandre.scarlet.thevoidkingdom.functions.rpg.stat.handler;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.rpg.player.RPGPlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;

public class AttributeStatHandler implements StatHandler {
    public static final String VK_ATTRIBUTE_NAME = "vkattribute";
    private final Attribute attribute;
    private final double scale;


    public AttributeStatHandler(Attribute attribute, double scale) {
        this.attribute = attribute;
        this.scale = scale;
    }

    @Override
    public void handle(RPGPlayer rpgPlayer, double totalVKStatValue) {
        Player player = rpgPlayer.getPlayer();
        AttributeInstance attributeInstance = player.getAttribute(attribute);
        if (attributeInstance != null) {
            attributeInstance.getModifiers().removeIf(attributeModifier -> attributeModifier.getName().equals(VK_ATTRIBUTE_NAME));
            double vanillaBase = attributeInstance.getBaseValue();
            double vkStatValue = totalVKStatValue * scale;
            if (totalVKStatValue != vanillaBase) {
                attributeInstance.addModifier(new AttributeModifier(VK_ATTRIBUTE_NAME, vkStatValue - vanillaBase, AttributeModifier.Operation.ADD_NUMBER));
            }
        } else {
            TheVoidKingdom.LOGGER.warning("玩家无对应属性: " + attribute.name());
        }

    }
}
