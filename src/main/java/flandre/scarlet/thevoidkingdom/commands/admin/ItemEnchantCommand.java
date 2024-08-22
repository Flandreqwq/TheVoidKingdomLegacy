package flandre.scarlet.thevoidkingdom.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.LangUtils;
import flandre.scarlet.thevoidkingdom.utils.framework.PaperCommand;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Bean
@CommandAlias("vk|flandre")
@Subcommand("item enchant")
@CommandPermission("vk.admin.item.enchant")
@Description("对手中的物品进行指定原版附魔")
public class ItemEnchantCommand extends BaseCommand implements PaperCommand {
    @Default
    public void onDefault(Player player, String enchantment, @Default("1") int level) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        boolean isEnchant=false;
        for (String str : enchantmentMap.keySet()) {
            if (Objects.equals(enchantment, str)) {
                isEnchant=true;
                itemStack.addUnsafeEnchantment(enchantmentMap.get(str), level);
                TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>已为物品添加原版附魔: " + enchantment, true);
                break;
            }
        }
        if(!isEnchant){
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>不存在的原版附魔: " + enchantment, true);
        }
    }

    private final Map<String, Enchantment> enchantmentMap = new HashMap<>();

    public ItemEnchantCommand() {
        init();
    }

    public void init() {
        for (Enchantment value : Enchantment.values()) {
            enchantmentMap.put(LangUtils.get(value.translationKey()), value);
        }
    }
}


