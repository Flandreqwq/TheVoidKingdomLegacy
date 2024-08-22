package flandre.scarlet.thevoidkingdom.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.framework.PaperCommand;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

@Bean
@CommandAlias("vk|flandre")
@Subcommand("test")
@CommandPermission("vk.admin.test")
@Description("测试指令")
public class TestCommand extends BaseCommand implements PaperCommand {
    @Default
    public void onDefault(Player player, float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        Location centerLocation = player.getLocation();
        ItemDisplay itemDisplay = (ItemDisplay) centerLocation.getWorld().spawnEntity(centerLocation, EntityType.ITEM_DISPLAY);
        itemDisplay.setItemStack(itemStack);
        itemDisplay.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.FIXED);
        itemDisplay.setBillboard(Display.Billboard.FIXED);
        itemDisplay.setShadowRadius(0);

        itemDisplay.setTransformation(new Transformation(new Vector3f(0F, 0.08F, 0F), new AxisAngle4f(arg1, arg2, arg3, arg4), new Vector3f(0.5F, 0.5F, 0.5F), new AxisAngle4f(arg5, arg6, arg7, arg8)));

        TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>运行了测试命令", true);
    }
}
