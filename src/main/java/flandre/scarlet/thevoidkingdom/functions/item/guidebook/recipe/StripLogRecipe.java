package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
@Register(registerName = "去皮配方")
public record StripLogRecipe(String logNamespaceId, String stripLogNamespaceId,
                             String barkNamespaceId) implements VKRecipe {
    private static final RegisterManager<StripLogRecipe> registerManager = RegisterManager.getManager(StripLogRecipe.class);
//    private String logNamespaceId;
//    private String stripLogNamespaceId;
//    private String barkNamespaceId;


//    public StripLogRecipe() {
//
//    }
//
//    @InitialTask
//    public void init() {
//        System.out.println("~~~~~StripLogRecipe");
//        YamlConfiguration stripLogRecipe = ConfigUtil.getConfig("strip_log_recipe");
//        Set<String> keys = stripLogRecipe.getKeys(false);
//        for (String key : keys) {
//            registerManager.create(new StripLogRecipe(key, stripLogRecipe.getString(key + ".strip"), stripLogRecipe.getString(key + ".bark")));
//        }
//    }

//    public StripLogRecipe(String logNamespaceId, String stripLogNamespaceId, String barkNamespaceId) {
//        this.logNamespaceId = logNamespaceId;
//        this.stripLogNamespaceId = stripLogNamespaceId;
//        this.barkNamespaceId = barkNamespaceId;
//    }

    static {
        registerManager.create(new StripLogRecipe("OAK_LOG", "STRIPPED_OAK_LOG", "vkmaterials:oak_bark"));
        registerManager.create(new StripLogRecipe("SPRUCE_LOG", "STRIPPED_SPRUCE_LOG", "vkmaterials:spruce_bark"));
        registerManager.create(new StripLogRecipe("BIRCH_LOG", "STRIPPED_BIRCH_LOG", "vkmaterials:birch_bark"));
        registerManager.create(new StripLogRecipe("JUNGLE_LOG", "STRIPPED_JUNGLE_LOG", "vkmaterials:jungle_bark"));
        registerManager.create(new StripLogRecipe("ACACIA_LOG", "STRIPPED_ACACIA_LOG", "vkmaterials:acacia_bark"));
        registerManager.create(new StripLogRecipe("DARK_OAK_LOG", "STRIPPED_DARK_OAK_LOG", "vkmaterials:dark_oak_bark"));
        registerManager.create(new StripLogRecipe("MANGROVE_LOG", "STRIPPED_MANGROVE_LOG", "vkmaterials:mangrove_bark"));
        registerManager.create(new StripLogRecipe("CRIMSON_STEM", "STRIPPED_CRIMSON_STEM", "vkmaterials:crimson_bark"));
        registerManager.create(new StripLogRecipe("WARPED_STEM", "STRIPPED_WARPED_STEM", "vkmaterials:warped_bark"));
//        YamlConfiguration stripLogRecipe = ConfigUtil.getConfig("strip_log_recipe");
//        Set<String> keys = stripLogRecipe.getKeys(false);
//        for (String key : keys) {
//            registerManager.create(new StripLogRecipe(key, stripLogRecipe.getString(key + ".strip"), stripLogRecipe.getString(key + ".bark")));
//        }

//        try {
//            Class<StripLogRecipe> stripLogRecipeClass = StripLogRecipe.class;
//            for (Field declaredField : stripLogRecipeClass.getDeclaredFields()) {
//                int modifiers = declaredField.getModifiers();
//                if (declaredField.getName().startsWith("_")) {
//                    continue;
//                }
//                if (!Modifier.isStatic(modifiers)) {
//                    YamlConfiguration stripLogRecipe = ConfigUtil.getConfig("strip_log_recipe");
//                    Set<String> keys = stripLogRecipe.getKeys(false);
//                    for (String key : keys) {
//                        StripLogRecipe stripLogRecipe1 = stripLogRecipeClass.newInstance();
//                        declaredField.set(stripLogRecipe1, stripLogRecipe.getString(key + "." + declaredField.getName()));
//                        registerManager.create(stripLogRecipe1);
//                    }
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_strip_log:";
    }

    @Override
    public String getMenuDisplayName() {
        return "去皮";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        inventory.setItem(19, ItemsAdderUtils.getItemStackNoCheck(logNamespaceId));
        inventory.setItem(23, ItemsAdderUtils.getItemStackNoCheck(stripLogNamespaceId));
        inventory.setItem(25, ItemsAdderUtils.getItemStackNoCheck(barkNamespaceId));
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        return logNamespaceId.equals(namespaceId);
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return stripLogNamespaceId.equals(namespaceId) || barkNamespaceId.equals(namespaceId);
    }


    @Override
    public boolean isValid() {
        if (!ItemsAdderUtils.isItemNamespaceIdExist(logNamespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册去皮配方失败,原因: 原木物品 " + logNamespaceId + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(stripLogNamespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册去皮配方失败,原因: 去皮原木物品 " + stripLogNamespaceId + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(barkNamespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册去皮配方失败,原因: 树皮物品 " + barkNamespaceId + " 不存在");
            return false;
        }
        return true;
    }
}
