package flandre.scarlet.thevoidkingdom.functions.skill;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.api.skills.placeholders.PlaceholderDouble;
import io.lumine.mythic.api.skills.placeholders.PlaceholderInt;
import io.lumine.mythic.core.skills.SkillExecutor;
import io.lumine.mythic.core.skills.SkillMechanic;
import io.lumine.mythic.core.utils.annotations.MythicMechanic;
import io.lumine.mythic.lib.api.player.EquipmentSlot;
import io.lumine.mythic.lib.api.stat.modifier.TemporaryStatModifier;
import io.lumine.mythic.lib.player.modifier.ModifierSource;
import io.lumine.mythic.lib.player.modifier.ModifierType;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.stat.type.ItemStat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

@MythicMechanic(
        author = "__FlandreScarlet",
        name = "mmotempstatmodifier",
        aliases = {"tempstat", "tempstatmodifier"},
        description = "给予玩家mmo临时stat"
)
public class MMOTempStatModifierSkill extends SkillMechanic implements ITargetedEntitySkill {
    protected final String key;
    protected final String stat;
    protected final PlaceholderDouble amount;
    protected final PlaceholderInt duration;
    protected final ModifierType modifierType;
    protected final EquipmentSlot equipmentSlot;
    protected final ModifierSource modifierSource;

    public MMOTempStatModifierSkill(SkillExecutor manager, String file, MythicLineConfig mlc) {
        this(manager, new File(file), mlc.getLine(), mlc);
    }

    public MMOTempStatModifierSkill(SkillExecutor manager, File file, String line, MythicLineConfig mlc) {
        super(manager, file, line, mlc);
        this.key = mlc.getString(new String[]{"key", "k"}, UUID.randomUUID().toString());
        this.stat = mlc.getString(new String[]{"stat"}, null);
        this.amount = PlaceholderDouble.of(mlc.getString(new String[]{"amount", "a"}, "0"));
        this.duration = PlaceholderInt.of(mlc.getString(new String[]{"duration", "d"}, "20"));
        this.modifierType = ModifierType.valueOf(mlc.getString(new String[]{"modifiertype", "type", "t"}, "FLAT").toUpperCase());
        this.equipmentSlot = EquipmentSlot.valueOf(mlc.getString(new String[]{"equipmentslot", "slot"}, "OTHER").toUpperCase());
        this.modifierSource = ModifierSource.valueOf(mlc.getString(new String[]{"modifiersource", "source"}, "OTHER").toUpperCase());
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata skillMetadata, AbstractEntity abstractEntity) {
        if (stat == null || !checkStat(stat)) {
            return SkillResult.INVALID_CONFIG;
        }
        Entity entity = abstractEntity.getBukkitEntity();
        if (!entity.getType().equals(EntityType.PLAYER)) {
            return SkillResult.INVALID_TARGET;
        }
        Player player = (Player) entity;
        TemporaryStatModifier temporaryStatModifier = new TemporaryStatModifier(key, stat, amount.get(skillMetadata, abstractEntity), modifierType, equipmentSlot, modifierSource);
        temporaryStatModifier.register(MMOItems.plugin.getPlayerDataManager().get(player.getUniqueId()).getMMOPlayerData(), duration.get(skillMetadata, abstractEntity));
//        System.out.println("玩家 " + player.getName() + " 的 " + stat + " 属性变化 " + amount.get(skillMetadata, abstractEntity) + (modifierType == ModifierType.FLAT ? "" : "%") + " 持续 " + duration.get(skillMetadata, abstractEntity) + "ticks");
        return SkillResult.SUCCESS;
    }

    private boolean checkStat(String stat) {
        for (ItemStat<?, ?> itemStat : MMOItems.plugin.getStats().getAll()) {
            if (itemStat.getId().equals(stat)) {
                return true;
            }
        }
        return false;
    }
}
