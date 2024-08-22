package flandre.scarlet.thevoidkingdom.functions.skill;

import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.core.skills.SkillExecutor;
import io.lumine.mythic.core.skills.SkillMechanic;
import io.lumine.mythic.core.utils.annotations.MythicMechanic;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;

@MythicMechanic(
        author = "__FlandreScarlet",
        name = "vktest",
        description = "qwq"
)
public class TestSkill extends SkillMechanic implements ITargetedEntitySkill {
    protected final String message;


    public TestSkill(SkillExecutor manager, String file, MythicLineConfig mlc) {
        this(manager, new File(file), mlc.getLine(), mlc);
    }

    public TestSkill(SkillExecutor manager, File file, String line, MythicLineConfig mlc) {
        super(manager, file, line, mlc);
        this.message = mlc.getString(new String[]{"message", "m"}, "默认文本");
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata skillMetadata, AbstractEntity abstractEntity) {
        Player player = Bukkit.getPlayerExact("__FlandreScarlet");
        if (player != null && player.isOnline()) {
            TheVoidKingdomUtils.sendMessageToPlayer(player, abstractEntity.getName().replace("§", "") + ":" + message, true);
            return SkillResult.SUCCESS;
        }
        return SkillResult.CONDITION_FAILED;
    }
}
