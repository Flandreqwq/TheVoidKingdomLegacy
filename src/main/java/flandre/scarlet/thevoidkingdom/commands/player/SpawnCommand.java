package flandre.scarlet.thevoidkingdom.commands.player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.annotation.*;
import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.framework.PaperCommand;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Bean
@CommandAlias("vk|flandre")
@Subcommand("player spawn")
@CommandPermission("thevoidkingdom.command.player.spawn")
@Description("在玩家位置生成mm实体")
public class SpawnCommand extends BaseCommand implements PaperCommand {
    @Default
    public void onDefault(CommandIssuer commandIssuer, String playerName, String spawnMobName) {
        Player player = Bukkit.getPlayerExact(playerName);
        if (player == null) {
            TheVoidKingdom.LOGGER.warning("玩家 " + playerName + " 不在线");
            return;
        }
        Location location = player.getLocation();
        MythicMob spawnMob = MythicBukkit.inst().getMobManager().getMythicMob(spawnMobName).orElse(null);
        if (spawnMob != null) {
            spawnMob.spawn(BukkitAdapter.adapt(location), 1);
        } else {
            TheVoidKingdom.LOGGER.warning("没有找到名为 " + spawnMobName + " 的怪物");
        }
    }
}
