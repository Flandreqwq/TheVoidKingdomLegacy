package flandre.scarlet.thevoidkingdom.commands.player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.framework.PaperCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

@Bean
@CommandAlias("vk|flandre")
@Subcommand("player teleport")
@CommandPermission("thevoidkingdom.command.player.teleport")
@Description("传送")
public class TeleportCommand extends BaseCommand implements PaperCommand {
    @Default
    public void onDefault(Player player, double x, double y, double z, @Default("this") String world) {
        World destination;
        if (Objects.equals(world, "this")) {
            destination = player.getWorld();
        } else {
            destination = Bukkit.getWorld(world);
        }
        player.teleport(new Location(destination, x, y, z));
    }
}
