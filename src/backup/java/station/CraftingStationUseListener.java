package flandre.scarlet.thevoidkingdom.functions.station;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.crafting.recipe.CraftingRecipe;
import net.Indyuce.mmoitems.api.crafting.recipe.Recipe;
import net.Indyuce.mmoitems.api.event.PlayerUseCraftingStationEvent;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

//@Bean
public class CraftingStationUseListener implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void on(PlayerUseCraftingStationEvent event) {
        if (event.hasResult()) {
            return;
        }
        Recipe recipe = event.getRecipe();
        if (recipe instanceof CraftingRecipe craftingRecipe) {
            Player player = event.getPlayer();
            if (!craftingRecipe.whenClaimed().isEmpty()) {
                return;
            }
            ItemStack resultItemStack = craftingRecipe.getOutputItemStack(null);
            io.lumine.mythic.lib.api.item.NBTItem nbtItem = io.lumine.mythic.lib.api.item.NBTItem.get(resultItemStack);
            String miType = nbtItem.getType();
            String miName = nbtItem.getString("MMOITEMS_ITEM_ID");
            String namespace = MMOItemsUtils.getIANamespace(miType);
            ItemStack outputItemStack;
            if (namespace.equals("vktools")) {
                MMOItem mmoItem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(miType), miName);
                if (mmoItem == null) {
                    TheVoidKingdom.LOGGER.warning("没有找到Type为 " + miType + " Name为 " + miName + " 的mi物品");
                    TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>无法制作该物品！这大概率是一个bug，请联系管理员！", false);
                    return;
                }
                ItemStack itemStack = mmoItem.newBuilder().build();
                assert itemStack != null;
                NBTItem newNbtItem = new NBTItem(itemStack);
                NBTCompound nbtCompound = newNbtItem.addCompound("itemsadder");
                nbtCompound.setString("namespace", namespace);
                nbtCompound.setString("id", miName.toLowerCase());
                outputItemStack = newNbtItem.getItem();
            } else {
                String namespaceId = namespace + ":" + miName.toLowerCase();
                CustomStack customStack = CustomStack.getInstance(namespaceId);
                if (customStack == null) {
                    TheVoidKingdom.LOGGER.warning("不存在的ia物品：" + namespaceId);
                    TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>无法制作该物品！这大概率是一个bug，请联系管理员！", false);
                    return;
                }
                outputItemStack = customStack.getItemStack();
            }
            outputItemStack.setAmount(craftingRecipe.getOutputAmount());
            TheVoidKingdomUtils.giveItemStackToPlayer(player, outputItemStack);
        }
    }
}
