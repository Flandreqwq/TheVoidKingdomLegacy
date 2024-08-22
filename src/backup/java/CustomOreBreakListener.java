package flandre.scarlet.thevoidkingdom.functions.block.custom;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.experience.EXPSource;
import net.Indyuce.mmocore.experience.PlayerProfessions;
import net.Indyuce.mmocore.experience.Profession;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

@Bean
public class CustomOreBreakListener implements Listener {
    private final Profession profession = MMOCore.plugin.professionManager.get("mining");

    private void giveExp(Player player, Block block, int least, int most) {
        Random random = new Random();
        double amount = (random.nextInt((most - least) * 10 + 1) + least * 10) / 10.0;
        PlayerData playerData = PlayerData.get(player.getUniqueId());
        PlayerProfessions playerProfessions = playerData.getCollectionSkills();
        playerProfessions.giveExperience(profession, amount, EXPSource.SOURCE, block.getLocation(), false);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(CustomBlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
            return;
        }
        String namespaceId = event.getNamespacedID();
        Block block = event.getBlock();
        switch (namespaceId) {
            //锡矿石：3-4
            case "vkblocks:tin_ore" -> {
                giveExp(player, block, 3, 4);
            }

            //深层锡矿石：5-7
            case "vkblocks:deepslate_tin_ore" -> {
                giveExp(player, block, 5, 7);
            }

            //魔晶矿石：6-8
            case "vkblocks:magic_crystal_ore" -> {
                giveExp(player, block, 6, 8);
            }
        }
    }
}
