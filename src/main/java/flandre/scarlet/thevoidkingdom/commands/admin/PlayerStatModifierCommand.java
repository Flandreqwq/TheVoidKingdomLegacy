package flandre.scarlet.thevoidkingdom.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.framework.PaperCommand;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import io.lumine.mythic.lib.api.player.MMOPlayerData;
import io.lumine.mythic.lib.api.stat.modifier.StatModifier;
import io.lumine.mythic.lib.player.modifier.ModifierType;
import org.bukkit.entity.Player;


@Bean
@CommandAlias("vk|flandre")
@Subcommand("player statmodifier")
@CommandPermission("vk.admin.player.statmodifier")
@Description("为玩家添加额外rpg属性")
public class PlayerStatModifierCommand extends BaseCommand implements PaperCommand {
    @Default
    public void onDefault(Player player, String key, String statName, double value) {
        MMOPlayerData playerData = MMOPlayerData.get(player);
        StatModifier damageModifier = new StatModifier(key, statName, value, ModifierType.FLAT);
        damageModifier.register(playerData);
        TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>已为你添加 " + value + " 点 " + statName + " ,key为: " + key, true);
    }
}
