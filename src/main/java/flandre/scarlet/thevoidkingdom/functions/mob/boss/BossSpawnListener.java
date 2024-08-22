package flandre.scarlet.thevoidkingdom.functions.mob.boss;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomBlock;
import flandre.scarlet.thevoidkingdom.utils.events.PlayerActiveTotemEvent;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.CoolDownMap;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.PlayerOffHandInteract;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Bean
public class BossSpawnListener implements Listener {

    private final CoolDownMap<UUID> coolDownMap = new CoolDownMap<>(200);

    @EventHandler(priority = EventPriority.HIGH)
    public void on(PlayerInteractEvent event) {
        if (event.useItemInHand() == Event.Result.DENY || event.useInteractedBlock() == Event.Result.DENY) {
            return;
        }
        if (!event.getAction().isRightClick()) {
            return;
        }


        Player player = event.getPlayer();
        ItemStack inputItemStack = event.getItem();
        if (inputItemStack == null) {
            return;
        }

        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        if (customBlock == null) {
            return;
        }
        Location location = block.getLocation();
        BossTotem bossTotem = checkBossTotem(location, inputItemStack, customBlock.getNamespacedID());
        if (bossTotem == null) {
            return;
        }


        if (event.getHand() == EquipmentSlot.HAND) {
            PlayerOffHandInteract.cancel(player);
        }
        event.setCancelled(true);
        UUID uuid = player.getUniqueId();
        if (coolDownMap.isCoolDown(uuid)) {
            return;
        }
        coolDownMap.add(uuid);


        String denyPermission = bossTotem.getDenyPermission();
        String cooldown = bossTotem.getDefaultCoolDown();
        if (!player.hasPermission(denyPermission) || player.hasPermission("vk.denyspawn.bypass")) {
            //玩家不处于仪式冷却 玩家拥有绕过权限
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission settemp " + denyPermission + " true " + cooldown);
            CustomBlock.byAlreadyPlaced(block).remove();
            inputItemStack.setAmount(inputItemStack.getAmount() - 1);
            bossTotem.spawnBoss(location);
            Sound sound = bossTotem.getSuccessSound();
            if (sound != null) {
                block.getWorld().playSound(location, sound, 10, 0);
            }
            PlayerActiveTotemEvent playerActiveTotemEvent = new PlayerActiveTotemEvent(player, bossTotem);
            Bukkit.getPluginManager().callEvent(playerActiveTotemEvent);
        } else {
            //玩家处于仪式冷却
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>一股神秘的力量阻止了你进行" + bossTotem.getRiteName() + "，似乎是由于仪式进行的过于频繁了，或许应该晚些时候再来尝试", false);
            Sound sound = bossTotem.getFailSound();
            if (sound != null) {
                block.getWorld().playSound(location, sound, 1, 0);
            }
        }
    }

    private BossTotem checkBossTotem(Location blockLocation, ItemStack itemStack, String coreNamespaceId) {
        for (BossTotem bossTotem : RegisterManager.getManager(BossTotem.class).getValidList()) {
            if (!ItemsAdderUtils.checkNamespaceId(itemStack, bossTotem.getSpawnItemNamespaceId())) {
                continue;
            }
            if (!bossTotem.getCoreNamespaceId().equals(coreNamespaceId)) {
                continue;
            }
            if (!bossTotem.isConditionMeet(blockLocation)) {
                continue;
            }
            if (!bossTotem.isLayersMeet(blockLocation)) {
                continue;
            }
            return bossTotem;
        }
        return null;
    }
}
