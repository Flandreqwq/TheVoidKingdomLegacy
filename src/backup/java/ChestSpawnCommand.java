package flandre.scarlet.thevoidkingdom.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import flandre.scarlet.thevoidkingdom.functions.structure.chest.ChestInWorld;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@CommandAlias("vk|flandre")
@Subcommand("debug chest spawn")
@CommandPermission("thevoidkingdom.command.debug.chest.spawn")
@Description("生成某个世界的结构箱子")
public class ChestSpawnCommand extends BaseCommand {
    @Default
    public void onDefault(Player player, String worldName, Location location) {
        switch (worldName) {
            case "world":
                ChestInWorld.spawnChest(location);
                break;
            default:
                player.sendMessage("§8[§d§l虚空之国§8|§a§lDebug§8]§c 未知的世界名");
                return;
        }
        player.sendMessage("§8[§d§l虚空之国§8|§a§lDebug§8]§e 箱子已生成");
    }
}
