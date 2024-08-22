package flandre.scarlet.thevoidkingdom.functions.crafting;

import com.molean.isletopia.framework.annotations.Bean;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import dev.lone.itemsadder.api.CustomBlock;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.VoidCraftingTableRecipe;
import flandre.scarlet.thevoidkingdom.utils.game.*;
import flandre.scarlet.thevoidkingdom.utils.game.menu.OpenReason;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Bean
public class VoidCraftingTableListener implements Listener {
    private final CoolDownMap<UUID> coolDownMap = new CoolDownMap<>(200);

    @EventHandler(priority = EventPriority.LOW)
    public void on(PlayerInteractEvent event) {
        if (event.useItemInHand() == Event.Result.DENY || event.useInteractedBlock() == Event.Result.DENY) {
            return;
        }
        if (!event.getAction().isRightClick()) {
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
        if (!customBlock.getNamespacedID().equals("vkblocks:void_crafting_table")) {
            return;
        }


        Player player = event.getPlayer();
        ItemStack inputItemStack = event.getItem();
        if (inputItemStack != null && player.isSneaking()) {
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


        VoidCraftingTableMenu voidCraftingTableMenu = new VoidCraftingTableMenu(player, block.getLocation(), null, 1);
        voidCraftingTableMenu.open(OpenReason.OPEN_NEW);
        voidCraftingTableMenu.update(null, 1);
    }

    private static final List<ClickType> clickTypes = List.of(
            ClickType.LEFT,
            ClickType.RIGHT,
            ClickType.SHIFT_LEFT,
            ClickType.SHIFT_RIGHT
    );

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        if (!(inventory.getHolder() instanceof VoidCraftingTableMenu.VoidCraftingTableMenuHolder)) {
            return;
        }
        int clickSlot = event.getRawSlot();
        if (clickSlot < 0 || clickSlot > inventory.getSize()) {
            return;
        }
        ItemStack clickIcon = inventory.getItem(clickSlot);
        if (clickIcon == null) {
            return;
        }
        ClickType clickType = event.getClick();
        if (!clickTypes.contains(clickType)) {
            return;
        }
        VoidCraftingTableMenu voidCraftingTableMenu = ((VoidCraftingTableMenu.VoidCraftingTableMenuHolder) inventory.getHolder()).getMenu();
        switch (clickSlot) {
            //点击上一页
            case 48 -> {
                voidCraftingTableMenu.lastPage();
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
            }
            //点击下一页
            case 52 -> {
                voidCraftingTableMenu.nextPage();
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
            }
            //点击分类
            case 0 -> {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                if (voidCraftingTableMenu.getRecipeCategory() != null) {
                    voidCraftingTableMenu.update(null, 1);
                }
            }
            case 9 -> {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                if (voidCraftingTableMenu.getRecipeCategory() != VoidCraftingTableRecipe.RecipeCategory.BLOCK) {
                    voidCraftingTableMenu.update(VoidCraftingTableRecipe.RecipeCategory.BLOCK, 1);
                }
            }
            case 18 -> {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                if (voidCraftingTableMenu.getRecipeCategory() != VoidCraftingTableRecipe.RecipeCategory.MATERIAL) {
                    voidCraftingTableMenu.update(VoidCraftingTableRecipe.RecipeCategory.MATERIAL, 1);
                }
            }
            case 27 -> {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                if (voidCraftingTableMenu.getRecipeCategory() != VoidCraftingTableRecipe.RecipeCategory.TOOL_ADD_ACCESSORY) {
                    voidCraftingTableMenu.update(VoidCraftingTableRecipe.RecipeCategory.TOOL_ADD_ACCESSORY, 1);
                }
            }
            case 36 -> {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                if (voidCraftingTableMenu.getRecipeCategory() != VoidCraftingTableRecipe.RecipeCategory.WEAPON) {
                    voidCraftingTableMenu.update(VoidCraftingTableRecipe.RecipeCategory.WEAPON, 1);
                }
            }
            case 45 -> {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                if (voidCraftingTableMenu.getRecipeCategory() != VoidCraftingTableRecipe.RecipeCategory.ARMOR) {
                    voidCraftingTableMenu.update(VoidCraftingTableRecipe.RecipeCategory.ARMOR, 1);
                }
            }
            //点击配方
            case 12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 30, 31, 32, 33, 34, 39, 40, 41, 42, 43 -> {
                NBTItem nbtItem = new NBTItem(clickIcon);
                String key = nbtItem.getString("recipeKey");
                VoidCraftingTableRecipe voidCraftingTableRecipe = VoidCraftingTableRecipe.RECIPE_KEY_MAP.get(key);
                if (voidCraftingTableRecipe == null) {
                    return;
                }
                switch (clickType) {
                    case RIGHT, SHIFT_RIGHT -> {

                    }
                    case LEFT -> {
                        Map<String, Integer> ingredients = voidCraftingTableRecipe.ingredients();
                        if (TheVoidKingdomUtils.checkIngredients(player, ingredients)) {
                            String resultString = voidCraftingTableRecipe.resultString();
                            if (resultString.contains("#")) {
                                //结果物品是mi物品（武器、护甲、饰品、符卡）
                                String[] strings = resultString.split("#");
                                String miType = strings[0].toUpperCase();
                                String miName = strings[1].toUpperCase();
                                MMOItemTemplate mmoItemTemplate = MMOItems.plugin.getTemplates().getTemplate(MMOItems.plugin.getTypes().get(miType), miName);
                                if (mmoItemTemplate == null) {
                                    TheVoidKingdom.LOGGER.warning("MMOItemTemplate " + resultString + " 不存在");
                                    TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>非法操作: 无法合成该物品！请将错误汇报给管理员！", true);
                                    return;
                                }
                                TheVoidKingdomUtils.takeIngredients(player, ingredients);
                                for (int i = voidCraftingTableRecipe.resultAmount(); i > 0; i--) {
                                    ItemStack resultItemStack = mmoItemTemplate.newBuilder().build().newBuilder().build();
                                    if (resultItemStack == null) {
                                        TheVoidKingdom.LOGGER.warning("物品 " + resultString + " build失败");
                                        TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>非法操作: 无法合成该物品！请将错误汇报给管理员！", true);
                                        return;
                                    }
                                    switch (miType) {
                                        case "PICKAXE", "AXE", "SHOVEL", "HOE", "DAGGER" -> {
                                            NBTItem nbtItem1 = new NBTItem(resultItemStack);
                                            NBTCompound nbtCompound = nbtItem1.addCompound("itemsadder");
                                            nbtCompound.setString("namespace", MMOItemsUtils.getIANamespace(miType));
                                            nbtCompound.setString("id", miName.toLowerCase());
                                            resultItemStack = nbtItem1.getItem();
                                        }
                                    }
                                    TheVoidKingdomUtils.giveItemStackToPlayer(player, resultItemStack);
                                }
                            } else {
                                //结果物品是ia物品（方块、材料）
                                TheVoidKingdomUtils.takeIngredients(player, ingredients);
                                ItemStack resultItemStack = ItemsAdderUtils.getItemStackNoCheck(resultString);
                                resultItemStack.setAmount(voidCraftingTableRecipe.resultAmount());
                                TheVoidKingdomUtils.giveItemStackToPlayer(player, resultItemStack);
                            }
                            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                            voidCraftingTableMenu.update();
                        } else {
                            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>你没有足够的材料合成该物品……", false);
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                        }
                    }
                    case SHIFT_LEFT -> {
                        //计算背包中的物品可以进行该配方几次
                        Map<String, Integer> ingredients = voidCraftingTableRecipe.ingredients();
                        Map<String, Integer> playerIngredients = new HashMap<>();
                        for (ItemStack itemStack : player.getInventory()) {
                            if (itemStack == null) {
                                continue;
                            }
                            for (String namespaceId : ingredients.keySet()) {
                                if (ItemsAdderUtils.getItemNamespaceId(itemStack).equals(namespaceId)) {
                                    playerIngredients.put(namespaceId, playerIngredients.getOrDefault(namespaceId, 0) + itemStack.getAmount());
                                }
                            }
                        }
                        int totalMultiple = 10000;
                        for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
                            int multiple = playerIngredients.getOrDefault(entry.getKey(), 0) / entry.getValue();
                            totalMultiple = Math.min(totalMultiple, multiple);
                        }
                        if (totalMultiple == 0) {
                            //为0说明无法合成
                            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>你没有足够的材料合成该物品……", false);
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                        } else {
                            String resultString = voidCraftingTableRecipe.resultString();
                            if (resultString.contains("#")) {
                                //结果物品是mi物品（武器、护甲、饰品、符卡）
                                String[] strings = resultString.split("#");
                                String miType = strings[0].toUpperCase();
                                String miName = strings[1].toUpperCase();
                                MMOItemTemplate mmoItemTemplate = MMOItems.plugin.getTemplates().getTemplate(MMOItems.plugin.getTypes().get(miType), miName);
                                if (mmoItemTemplate == null) {
                                    TheVoidKingdom.LOGGER.warning("MMOItemTemplate " + resultString + " 不存在");
                                    TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>非法操作: 无法合成该物品！请将错误汇报给管理员！", true);
                                    return;
                                }
                                Map<String, Integer> map = new HashMap<>();
                                for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
                                    map.put(entry.getKey(), entry.getValue() * totalMultiple);
                                }
                                TheVoidKingdomUtils.takeIngredients(player, map);
                                for (int i = totalMultiple * voidCraftingTableRecipe.resultAmount(); i > 0; i--) {
                                    ItemStack resultItemStack = mmoItemTemplate.newBuilder().build().newBuilder().build();
                                    if (resultItemStack == null) {
                                        TheVoidKingdom.LOGGER.warning("物品 " + resultString + " build失败");
                                        TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>非法操作: 无法合成该物品！请将错误汇报给管理员！", true);
                                        return;
                                    }
                                    switch (miType) {
                                        case "PICKAXE", "AXE", "SHOVEL", "HOE", "DAGGER" -> {
                                            NBTItem nbtItem1 = new NBTItem(resultItemStack);
                                            NBTCompound nbtCompound = nbtItem1.addCompound("itemsadder");
                                            nbtCompound.setString("namespace", MMOItemsUtils.getIANamespace(miType));
                                            nbtCompound.setString("id", miName.toLowerCase());
                                            resultItemStack = nbtItem1.getItem();
                                        }
                                    }
                                    TheVoidKingdomUtils.giveItemStackToPlayer(player, resultItemStack);
                                }
                            } else {
                                //结果物品是ia物品（方块、材料）
                                Map<String, Integer> map = new HashMap<>();
                                for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
                                    map.put(entry.getKey(), entry.getValue() * totalMultiple);
                                }
                                TheVoidKingdomUtils.takeIngredients(player, map);
                                ItemStack resultItemStack = ItemsAdderUtils.getItemStackNoCheck(resultString);
                                resultItemStack.setAmount(voidCraftingTableRecipe.resultAmount() * totalMultiple);
                                TheVoidKingdomUtils.giveItemStackToPlayer(player, resultItemStack);
                            }
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            voidCraftingTableMenu.update();
                        }
                    }
                }
            }
        }
    }



}
