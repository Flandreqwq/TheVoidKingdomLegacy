package flandre.scarlet.thevoidkingdom.functions.repair;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.utils.game.CoolDownMap;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import io.lumine.mythic.lib.api.item.NBTItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Bean
public class AnvilInventoryListener implements Listener {
    private static final CoolDownMap<UUID> coolDownMap = new CoolDownMap<>(1000);

    @EventHandler(priority = EventPriority.HIGH)
    public void on(PrepareAnvilEvent event) {
        AnvilInventory anvilInventory = event.getInventory();
        List<HumanEntity> list = anvilInventory.getViewers();
        if (list.size() > 1) {
            return;
        }
        Player player = (Player) list.get(0);
        ItemStack leftItemStack = anvilInventory.getItem(0);
        ItemStack rightItemStack = anvilInventory.getItem(1);
        ItemStack resultItemStack = anvilInventory.getResult();
        String renameText = anvilInventory.getRenameText();
        if (resultItemStack == null) {
            return;
        }
        if (leftItemStack != null && rightItemStack != null) {
            if (ItemsAdderUtils.isCustomItemStack(leftItemStack) || ItemsAdderUtils.isCustomItemStack(rightItemStack)) {
                event.setResult(null);
                return;
            }
        }


        if (renameText == null) {
            return;
        }
        renameText = renameText.replace("§", "&");
        Pattern patternOffset = Pattern.compile("(:offset_.+?:)");
        Matcher matcherOffset = patternOffset.matcher(renameText);
        renameText = matcherOffset.replaceAll("");
        Pattern pattern = Pattern.compile("[\ue000-\uf8ff]");
        Matcher matcher = pattern.matcher(renameText);
        if (matcher.find()) {
            UUID uuid = player.getUniqueId();
            if (!coolDownMap.isCoolDown(uuid)) {
                TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>请以双冒号格式输入表情，否则取出物品时无法生效", false);
                TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>格式示例 :emoji: :touhou_flandre:", false);
                coolDownMap.add(uuid);
            }
        }
        String newName = matcher.replaceAll("");
        if (newName.equals("")) {
            NBTItem nbtItem = NBTItem.get(resultItemStack);
            if (nbtItem.hasType()) {
                newName = nbtItem.getString("MMOITEMS_NAME");
                if (MMOItemsUtils.hasEnchantment(resultItemStack)) {
                    newName = "§b" + newName;
                } else {
                    newName = "§f" + newName;
                }
                ItemMeta itemMeta = resultItemStack.getItemMeta();
                itemMeta.displayName(LegacyComponentSerializer.legacySection().deserialize(newName).decoration(TextDecoration.ITALIC, false));
                resultItemStack.setItemMeta(itemMeta);
                event.setResult(resultItemStack);
            } else {
                CustomStack customStack = CustomStack.byItemStack(resultItemStack);
                if (customStack != null) {
                    newName = CustomStack.getInstance(customStack.getNamespacedID()).getDisplayName();
                    ItemMeta itemMeta = resultItemStack.getItemMeta();
                    itemMeta.displayName(LegacyComponentSerializer.legacySection().deserialize(newName).decoration(TextDecoration.ITALIC, false));
                    resultItemStack.setItemMeta(itemMeta);
                    event.setResult(resultItemStack);
                } else {
                    ItemStack itemStack = new ItemStack(resultItemStack.getType(), resultItemStack.getAmount());
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (resultItemStack.getItemMeta() instanceof Damageable damageable) {
                        Damageable damageableMeta = (Damageable) itemMeta;
                        damageableMeta.setDamage(damageable.getDamage());
                        itemStack.setItemMeta(damageableMeta);
                    }
                    TheVoidKingdomUtils.checkClearVanillaStat(itemStack);
                    event.setResult(itemStack);
                }
            }
        } else {
            if (ItemsAdderUtils.isCustomItemStack(resultItemStack)) {
                ItemMeta itemMeta = resultItemStack.getItemMeta();
                itemMeta.displayName(Component.text(newName));
                resultItemStack.setItemMeta(itemMeta);
                event.setResult(resultItemStack);
            } else {
                ItemMeta itemMeta = resultItemStack.getItemMeta();
                itemMeta.displayName(Component.text(newName));
                resultItemStack.setItemMeta(itemMeta);
                TheVoidKingdomUtils.checkClearVanillaStat(resultItemStack);
                event.setResult(resultItemStack);
            }
        }
    }
}
