package flandre.scarlet.thevoidkingdom.commands.player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.framework.PaperCommand;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@Bean
@CommandAlias("vk|flandre")
@Subcommand("player getIAItem")
@CommandPermission("thevoidkingdom.command.player.getiaitem")
@Description("获取ia物品")
public class GetIAItemCommand extends BaseCommand implements PaperCommand {
    @Default
    public void onDefault(Player player, String namespaceId, int amount) {
        CustomStack customStack = CustomStack.getInstance(namespaceId);
        if (customStack == null) {
            TheVoidKingdom.LOGGER.warning("不存在的ia物品：" + namespaceId);
            return;
        }
        Inventory inventory = player.getInventory();
        ItemStack itemStack = customStack.getItemStack();
        itemStack.setAmount(amount);
        Map<Integer, ItemStack> map = inventory.addItem(itemStack);
        World world = player.getWorld();
        Location location = player.getLocation();
        map.forEach((integer, itemStack1) -> {
            world.dropItem(location, itemStack1);
        });
    }
}
