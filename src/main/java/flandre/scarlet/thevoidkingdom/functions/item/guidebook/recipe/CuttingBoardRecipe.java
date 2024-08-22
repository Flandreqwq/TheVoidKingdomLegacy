package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.GuideBookMenuIcon;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Register(registerName = "砧板配方")
public record CuttingBoardRecipe(String inputNamespaceId,
                                 String resultNamespaceId, int resultAmount,
                                 String extraResultNamespaceId, int extraAmount) implements VKRecipe {
    private static final RegisterManager<CuttingBoardRecipe> registerManager = RegisterManager.getManager(CuttingBoardRecipe.class);

    static {
        registerManager.create(new CuttingBoardRecipe("vkmaterials:cabbage", "vkmaterials:cabbage_leaf", 2, "AIR", 0));
        registerManager.create(new CuttingBoardRecipe("PUMPKIN", "vkmaterials:pumpkin_slice", 4, "AIR", 0));
        registerManager.create(new CuttingBoardRecipe("BEEF", "vkmaterials:minced_beef", 2, "BONE_MEAL", 1));
        registerManager.create(new CuttingBoardRecipe("CHICKEN", "vkmaterials:raw_chicken_cuts", 2, "BONE_MEAL", 1));
        registerManager.create(new CuttingBoardRecipe("PORKCHOP", "vkmaterials:raw_bacon", 2, "BONE_MEAL", 1));
        registerManager.create(new CuttingBoardRecipe("MUTTON", "vkmaterials:raw_mutton_chops", 2, "BONE_MEAL", 1));
        registerManager.create(new CuttingBoardRecipe("COD", "vkmaterials:raw_cod_slice", 2, "BONE_MEAL", 1));
        registerManager.create(new CuttingBoardRecipe("SALMON", "vkmaterials:raw_salmon_slice", 2, "BONE_MEAL", 1));
    }

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_cutting_board:";
    }

    @Override
    public String getMenuDisplayName() {
        return "砧板";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        inventory.setItem(11, GuideBookMenuIcon.CUTTING_BOARD.getItemStack());
        inventory.setItem(20, ItemsAdderUtils.getItemStackNoCheck(this.inputNamespaceId));
        ItemStack resultItemStack = ItemsAdderUtils.getItemStackNoCheck(this.resultNamespaceId);
        resultItemStack.setAmount(resultAmount);
        inventory.setItem(24, resultItemStack);
        if (!extraResultNamespaceId.equals("AIR")) {
            ItemStack extraResultItemStack = ItemsAdderUtils.getItemStackNoCheck(this.extraResultNamespaceId);
            extraResultItemStack.setAmount(extraAmount);
            inventory.setItem(25, extraResultItemStack);
        }
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        return inputNamespaceId.equals(namespaceId);
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return resultNamespaceId.equals(namespaceId) || (!extraResultNamespaceId.equals("AIR") && extraResultNamespaceId.equals(namespaceId));
    }


    @Override
    public boolean isValid() {
        if (!ItemsAdderUtils.isItemNamespaceIdExist(inputNamespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册砧板配方失败,原因: 输入物品 " + inputNamespaceId + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(resultNamespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册砧板配方失败,原因: 结果物品 " + resultNamespaceId + " 不存在");
            return false;
        }
        if (resultAmount > 64 || resultAmount < 1) {
            TheVoidKingdom.LOGGER.warning("注册砧板配方失败,原因: 结果物品数量不合法");
            return false;
        }
        if (!extraResultNamespaceId.equals("AIR")) {
            if (!ItemsAdderUtils.isItemNamespaceIdExist(extraResultNamespaceId)) {
                TheVoidKingdom.LOGGER.warning("注册砧板配方失败,原因: 额外结果物品 " + extraResultNamespaceId + " 不存在");
                return false;
            }
            if (extraAmount > 64 || extraAmount < 1) {
                TheVoidKingdom.LOGGER.warning("注册砧板配方失败,原因: 额外结果物品数量不合法");
                return false;
            }
        }
        return true;
    }
}
