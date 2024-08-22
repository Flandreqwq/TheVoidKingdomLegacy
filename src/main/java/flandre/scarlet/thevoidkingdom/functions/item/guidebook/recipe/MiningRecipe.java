package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import com.destroystokyo.paper.MaterialTags;
import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.GuideBookMenuIcon;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Register(registerName = "采集配方")
public record MiningRecipe(String result, int minAmount, int maxAmount, double possibility, ToolType toolType,
                           int toolLevel,
                           String blockNamespaceId) implements VKRecipe {

    private static final RegisterManager<MiningRecipe> registerManager = RegisterManager.getManager(MiningRecipe.class);

    static {
        registerManager.create(new MiningRecipe("COAL", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 1, "COAL_ORE"));
        registerManager.create(new MiningRecipe("COAL", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 1, "DEEPSLATE_COAL_ORE"));
        registerManager.create(new MiningRecipe("RAW_IRON", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 3, "IRON_ORE"));
        registerManager.create(new MiningRecipe("RAW_IRON", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 3, "DEEPSLATE_IRON_ORE"));
        registerManager.create(new MiningRecipe("RAW_GOLD", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 4, "GOLD_ORE"));
        registerManager.create(new MiningRecipe("RAW_GOLD", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 4, "DEEPSLATE_GOLD_ORE"));
        registerManager.create(new MiningRecipe("RAW_COPPER", 2, 5, 1, MiningRecipe.ToolType.PICKAXE, 2, "COPPER_ORE"));
        registerManager.create(new MiningRecipe("RAW_COPPER", 2, 5, 1, MiningRecipe.ToolType.PICKAXE, 2, "DEEPSLATE_COPPER_ORE"));
        registerManager.create(new MiningRecipe("LAPIS_LAZULI", 4, 9, 1, MiningRecipe.ToolType.PICKAXE, 2, "LAPIS_ORE"));
        registerManager.create(new MiningRecipe("LAPIS_LAZULI", 4, 9, 1, MiningRecipe.ToolType.PICKAXE, 2, "DEEPSLATE_LAPIS_ORE"));
        registerManager.create(new MiningRecipe("REDSTONE", 4, 5, 1, MiningRecipe.ToolType.PICKAXE, 4, "REDSTONE_ORE"));
        registerManager.create(new MiningRecipe("REDSTONE", 4, 5, 1, MiningRecipe.ToolType.PICKAXE, 4, "DEEPSLATE_REDSTONE_ORE"));
        registerManager.create(new MiningRecipe("EMERALD", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 4, "EMERALD_ORE"));
        registerManager.create(new MiningRecipe("EMERALD", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 4, "DEEPSLATE_EMERALD_ORE"));
        registerManager.create(new MiningRecipe("DIAMOND", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 4, "DIAMOND_ORE"));
        registerManager.create(new MiningRecipe("DIAMOND", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 4, "DEEPSLATE_DIAMOND_ORE"));
        registerManager.create(new MiningRecipe("vkmaterials:raw_tin", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 2, "vkblocks:tin_ore"));
        registerManager.create(new MiningRecipe("vkmaterials:raw_tin", 1, 1, 1, MiningRecipe.ToolType.PICKAXE, 2, "vkblocks:deepslate_tin_ore"));
        registerManager.create(new MiningRecipe("vkmaterials:magic_crystal_shard", 1, 3, 1, MiningRecipe.ToolType.PICKAXE, 3, "vkblocks:magic_crystal_ore"));
        registerManager.create(new MiningRecipe("vkmaterials:deep_dark_dust", 4, 8, 1, MiningRecipe.ToolType.HAND, 0, "SPAWNER"));
        registerManager.create(new MiningRecipe("vkmaterials:deep_dark_dust", 0, 1, 0.08, MiningRecipe.ToolType.PICKAXE, 1, "DEEPSLATE"));
        registerManager.create(new MiningRecipe("vkmaterials:depth_pebble", 0, 1, 0.02, MiningRecipe.ToolType.PICKAXE, 1, "DEEPSLATE"));
        registerManager.create(new MiningRecipe("vkmaterials:depth_pebble", 0, 1, 0.5, MiningRecipe.ToolType.PICKAXE, 1, "DEEPSLATE_COAL_ORE"));
        registerManager.create(new MiningRecipe("vkmaterials:depth_pebble", 0, 1, 0.05, MiningRecipe.ToolType.PICKAXE, 3, "DEEPSLATE_IRON_ORE"));
        registerManager.create(new MiningRecipe("vkmaterials:depth_pebble", 0, 1, 0.1, MiningRecipe.ToolType.PICKAXE, 4, "DEEPSLATE_GOLD_ORE"));
        registerManager.create(new MiningRecipe("vkmaterials:depth_pebble", 0, 1, 0.05, MiningRecipe.ToolType.PICKAXE, 2, "DEEPSLATE_COPPER_ORE"));
        registerManager.create(new MiningRecipe("vkmaterials:depth_pebble", 0, 1, 0.2, MiningRecipe.ToolType.PICKAXE, 1, "DEEPSLATE_LAPIS_ORE"));
        registerManager.create(new MiningRecipe("vkmaterials:depth_pebble", 0, 1, 0.1, MiningRecipe.ToolType.PICKAXE, 4, "DEEPSLATE_REDSTONE_ORE"));
        registerManager.create(new MiningRecipe("vkmaterials:depth_pebble", 0, 1, 0.2, MiningRecipe.ToolType.PICKAXE, 4, "DEEPSLATE_DIAMOND_ORE"));
        registerManager.create(new MiningRecipe("vkmaterials:depth_pebble", 0, 1, 0.5, MiningRecipe.ToolType.PICKAXE, 4, "DEEPSLATE_EMERALD_ORE"));
        registerManager.create(new MiningRecipe("vkmaterials:depth_pebble", 0, 1, 0.1, MiningRecipe.ToolType.PICKAXE, 2, "vkblocks:deepslate_tin_ore"));
        registerManager.create(new MiningRecipe("vkmaterials:pebble", 1, 3, 1, MiningRecipe.ToolType.HAND, 0, "vkblocks:pebbles_1"));
        registerManager.create(new MiningRecipe("vkmaterials:pebble", 1, 5, 1, MiningRecipe.ToolType.HAND, 0, "vkblocks:pebbles_2"));
        registerManager.create(new MiningRecipe("vkmaterials:pebble", 1, 3, 1, MiningRecipe.ToolType.HAND, 0, "vkblocks:pebbles_3"));
        registerManager.create(new MiningRecipe("vkmaterials:lucky_clover", 1, 1, 0.01, MiningRecipe.ToolType.HAND, 0, "vkblocks:clover_bushes"));
        registerManager.create(new MiningRecipe("vkmaterials:enchanted_lapis_lazuli_nugget", 1, 3, 0.75, MiningRecipe.ToolType.PICKAXE, 2, "LAPIS_ORE"));
        registerManager.create(new MiningRecipe("vkmaterials:enchanted_lapis_lazuli_nugget", 1, 3, 0.75, MiningRecipe.ToolType.PICKAXE, 2, "DEEPSLATE_LAPIS_ORE"));
    }


    @Override
    public boolean isValid() {
        if (minAmount > maxAmount) {
            TheVoidKingdom.LOGGER.warning("注册采集配方失败,原因: 最小数量不能大于最大数量");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(blockNamespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册采集配方失败,原因: 方块 " + blockNamespaceId + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(result)) {
            TheVoidKingdom.LOGGER.warning("注册采集配方失败,原因: 结果物品 " + result + " 不存在");
            return false;
        }
        return true;
    }

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_mining:";
    }

    @Override
    public String getMenuDisplayName() {
        return "采集自然资源";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        inventory.setItem(10, ItemsAdderUtils.getItemStackNotNull("vkguis:icon_" + this.toolType().toString().toLowerCase() + "_" + this.toolLevel()));
        inventory.setItem(21, ItemsAdderUtils.getItemStackNoCheck(this.blockNamespaceId()));
        inventory.setItem(25, ItemsAdderUtils.getItemStackNoCheck(this.result()));
        int minAmount = this.minAmount();
        int maxAmount = this.maxAmount();
        String amountStr;
        if (minAmount == maxAmount) {
            amountStr = String.valueOf(minAmount);
        } else if (minAmount == 0) {
            if (maxAmount == 1) {
                amountStr = "1";
            } else {
                amountStr = "1～" + maxAmount;
            }
        } else {
            amountStr = minAmount + "～" + maxAmount;
        }
        ItemStack itemStack = GuideBookMenuIcon.MINING_INFO.getItemStack().clone();
        itemStack.lore(TheVoidKingdomUtils.deserializeList(List.of(
                "<!italic><white>%img_short_line_3%",
                "<!italic><gold> %img_info% 掉落数量: <white>" + amountStr,
                "<!italic><gold> %img_info% 掉落概率: <white>" + String.format("%.1f", this.possibility() * 100) + "%",
                "<!italic><white>%img_short_line_3%"
        ), true));
        inventory.setItem(14, itemStack);
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        return blockNamespaceId.equals(namespaceId);
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return result.equals(namespaceId);
    }

    public static int getToolLevel(String namespaceId) {
        if (namespaceId.contains(":")) {
            CustomStack customStack = CustomStack.getInstance(namespaceId);
            if (customStack == null) {
                return 0;
            }
            if (customStack.getNamespace().equals("vktools")) {
                String id = customStack.getId().toLowerCase();
                String[] strings = id.split("_");
                return strings[1].length();
            } else {
                return 0;
            }
        } else {
            Material material = Material.getMaterial(namespaceId);
            if (material == null) {
                return 0;
            } else {
                if (MaterialTags.WOODEN_TOOLS.isTagged(material)) {
                    return 1;
                } else if (MaterialTags.STONE_TOOLS.isTagged(material)) {
                    return 2;
                } else {
                    return 0;
                }
            }
        }
    }


    public enum ToolType {
        HAND,
        PICKAXE,
        AXE,
        HOE,
        SHOVEL
    }
}
