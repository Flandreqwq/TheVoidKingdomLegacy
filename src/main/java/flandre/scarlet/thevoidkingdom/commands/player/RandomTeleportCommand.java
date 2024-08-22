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
import java.util.Random;

@Bean
@CommandAlias("vk|flandre")
@Subcommand("player randomTeleport")
@CommandPermission("thevoidkingdom.command.player.randomteleport")
@Description("随机传送")
public class RandomTeleportCommand extends BaseCommand implements PaperCommand {
    @Default
    public void onDefault(Player player, @Default("10000") int radius, @Default("this") String world) {
        World destination;
        if (Objects.equals(world, "this")) {
            destination = player.getWorld();
        } else {
            destination = Bukkit.getWorld(world);
        }
        Random random = new Random();
        double randX = random.nextInt(radius) - radius / 2.0;
        double randZ = random.nextInt(radius) - radius / 2.0;
        Location location = new Location(destination, randX, 0, randZ).toHighestLocation();
        player.teleport(location);
    }
}
