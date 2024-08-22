package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.block.interact.*;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.GuideBookMenuIcon;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterExtraTask;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Register(registerName = "方块交互配方")
public record InteractBlockRecipe(InputChoice input, int inputAmount, String blockNamespaceId,
                                  InteractBlockAction interactAction,
                                  ResultAction[] resultActions) implements VKRecipe {
    public static final RegisterManager<InteractBlockRecipe> registerManager = RegisterManager.getManager(InteractBlockRecipe.class);


    //工作台
    static {
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:void_crystal"), 1, "CRAFTING_TABLE",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("vkblocks:void_crafting_table"),
                        new PlaySoundAction(Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 0)
                }));
    }

    //匣子
    static {
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:iron_key"), 1, "vkblocks:locked_golden_box",
                InteractBlockRecipe.InteractBlockAction.RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("vkblocks:golden_box"),
                        new PlaySoundAction(Sound.BLOCK_WOODEN_DOOR_OPEN, 1, 1.5F)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:golden_key"), 1, "vkblocks:locked_void_box",
                InteractBlockRecipe.InteractBlockAction.RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("vkblocks:void_box"),
                        new PlaySoundAction(Sound.BLOCK_WOODEN_DOOR_OPEN, 1, 1.5F)
                }));
    }

    //紫水晶
    static {
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:crystal_growth_dust"), 1, "AMETHYST_CLUSTER",
                InteractBlockRecipe.InteractBlockAction.RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("AIR"),
                        new DropItemAction("vkmaterials:magic_crystal_shard", 1, 1, 1),
                        new PlaySoundAction(Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 2)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:crystal_growth_dust"), 1, "LARGE_AMETHYST_BUD",
                InteractBlockRecipe.InteractBlockAction.RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.AMETHYST_CLUSTER),
                        new PlaySoundAction(Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 2)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:crystal_growth_dust"), 1, "MEDIUM_AMETHYST_BUD",
                InteractBlockRecipe.InteractBlockAction.RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.LARGE_AMETHYST_BUD),
                        new PlaySoundAction(Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 2)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:crystal_growth_dust"), 1, "SMALL_AMETHYST_BUD",
                InteractBlockRecipe.InteractBlockAction.RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.MEDIUM_AMETHYST_BUD),
                        new PlaySoundAction(Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 2)
                }));
    }

    //苔藓丛
    static {
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:moss_clump"), 1, "COBBLESTONE",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("MOSSY_COBBLESTONE"),
                        new PlaySoundAction(Sound.BLOCK_MOSS_PLACE, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:moss_clump"), 1, "COBBLESTONE_STAIRS",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.MOSSY_COBBLESTONE_STAIRS),
                        new PlaySoundAction(Sound.BLOCK_MOSS_PLACE, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:moss_clump"), 1, "COBBLESTONE_SLAB",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.MOSSY_COBBLESTONE_SLAB),
                        new PlaySoundAction(Sound.BLOCK_MOSS_PLACE, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:moss_clump"), 1, "COBBLESTONE_WALL",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.MOSSY_COBBLESTONE_WALL),
                        new PlaySoundAction(Sound.BLOCK_MOSS_PLACE, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:moss_clump"), 1, "STONE_BRICKS",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("MOSSY_STONE_BRICKS"),
                        new PlaySoundAction(Sound.BLOCK_MOSS_PLACE, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:moss_clump"), 1, "STONE_BRICK_STAIRS",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.MOSSY_STONE_BRICK_STAIRS),
                        new PlaySoundAction(Sound.BLOCK_MOSS_PLACE, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:moss_clump"), 1, "STONE_BRICK_SLAB",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.MOSSY_STONE_BRICK_SLAB),
                        new PlaySoundAction(Sound.BLOCK_MOSS_PLACE, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:moss_clump"), 1, "STONE_BRICK_WALL",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.MOSSY_STONE_BRICK_WALL),
                        new PlaySoundAction(Sound.BLOCK_MOSS_PLACE, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.SHEARS, true), 1, "MOSSY_COBBLESTONE",
                InteractBlockRecipe.InteractBlockAction.RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("COBBLESTONE"),
                        new DropItemAction("vkmaterials:moss_clump", 1, 1, 1),
                        new PlaySoundAction(Sound.BLOCK_MOSS_BREAK, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.SHEARS, true), 1, "MOSSY_STONE_BRICKS",
                InteractBlockRecipe.InteractBlockAction.RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("STONE_BRICKS"),
                        new DropItemAction("vkmaterials:moss_clump", 1, 1, 1),
                        new PlaySoundAction(Sound.BLOCK_MOSS_BREAK, 1, 1)
                }));
    }

    //杜鹃花
    static {
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.SHEARS, true), 1, "FLOWERING_AZALEA",
                InteractBlockRecipe.InteractBlockAction.RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("AZALEA"),
                        new DropItemAction("vkmaterials:azalea_flower", 1, 1, 1),
                        new PlaySoundAction(Sound.BLOCK_AZALEA_BREAK, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.SHEARS, true), 1, "FLOWERING_AZALEA_LEAVES",
                InteractBlockRecipe.InteractBlockAction.RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.AZALEA_LEAVES),
                        new DropItemAction("vkmaterials:azalea_flower", 1, 1, 1),
                        new PlaySoundAction(Sound.BLOCK_AZALEA_LEAVES_BREAK, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:azalea_flower"), 1, "AZALEA",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("FLOWERING_AZALEA"),
                        new PlaySoundAction(Sound.BLOCK_AZALEA_PLACE, 1, 1)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:azalea_flower"), 1, "AZALEA_LEAVES",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.FLOWERING_AZALEA_LEAVES),
                        new PlaySoundAction(Sound.BLOCK_AZALEA_PLACE, 1, 1)
                }));
    }

    //火烧苔藓
    static {
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.FLINT_AND_STEEL, true), 1, "MOSSY_COBBLESTONE",
                InteractBlockRecipe.InteractBlockAction.ONLY_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("COBBLESTONE"),
                        new PlaySoundAction(Sound.ITEM_FIRECHARGE_USE, 1, 1),
                        new SpawnParticleAction(Particle.FLAME, 30, 0.35, 0.35, 0.35, 0.03, null)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.FLINT_AND_STEEL, true), 1, "MOSSY_COBBLESTONE_STAIRS",
                InteractBlockRecipe.InteractBlockAction.ONLY_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.COBBLESTONE_STAIRS),
                        new PlaySoundAction(Sound.ITEM_FIRECHARGE_USE, 1, 1),
                        new SpawnParticleAction(Particle.FLAME, 30, 0.35, 0.35, 0.35, 0.03, null)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.FLINT_AND_STEEL, true), 1, "MOSSY_COBBLESTONE_SLAB",
                InteractBlockRecipe.InteractBlockAction.ONLY_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.COBBLESTONE_SLAB),
                        new PlaySoundAction(Sound.ITEM_FIRECHARGE_USE, 1, 1),
                        new SpawnParticleAction(Particle.FLAME, 30, 0.35, 0.35, 0.35, 0.03, null)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.FLINT_AND_STEEL, true), 1, "MOSSY_COBBLESTONE_WALL",
                InteractBlockRecipe.InteractBlockAction.ONLY_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.COBBLESTONE_WALL),
                        new PlaySoundAction(Sound.ITEM_FIRECHARGE_USE, 1, 1),
                        new SpawnParticleAction(Particle.FLAME, 30, 0.35, 0.35, 0.35, 0.03, null)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.FLINT_AND_STEEL, true), 1, "MOSSY_STONE_BRICKS",
                InteractBlockRecipe.InteractBlockAction.ONLY_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceBlockAction("STONE_BRICKS"),
                        new PlaySoundAction(Sound.ITEM_FIRECHARGE_USE, 1, 1),
                        new SpawnParticleAction(Particle.FLAME, 30, 0.35, 0.35, 0.35, 0.03, null)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.FLINT_AND_STEEL, true), 1, "MOSSY_STONE_BRICK_STAIRS",
                InteractBlockRecipe.InteractBlockAction.ONLY_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.STONE_BRICK_STAIRS),
                        new PlaySoundAction(Sound.ITEM_FIRECHARGE_USE, 1, 1),
                        new SpawnParticleAction(Particle.FLAME, 30, 0.35, 0.35, 0.35, 0.03, null)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.FLINT_AND_STEEL, true), 1, "MOSSY_STONE_BRICK_SLAB",
                InteractBlockRecipe.InteractBlockAction.ONLY_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.STONE_BRICK_SLAB),
                        new PlaySoundAction(Sound.ITEM_FIRECHARGE_USE, 1, 1),
                        new SpawnParticleAction(Particle.FLAME, 30, 0.35, 0.35, 0.35, 0.03, null)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ToolInput(ToolInput.ToolType.FLINT_AND_STEEL, true), 1, "MOSSY_STONE_BRICK_WALL",
                InteractBlockRecipe.InteractBlockAction.ONLY_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaceSameDataBlockAction(Material.STONE_BRICK_WALL),
                        new PlaySoundAction(Sound.ITEM_FIRECHARGE_USE, 1, 1),
                        new SpawnParticleAction(Particle.FLAME, 30, 0.35, 0.35, 0.35, 0.03, null)
                }));
    }

    //树皮
    static {
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:oak_bark"), 1, "STRIPPED_OAK_LOG",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaySoundAction(Sound.BLOCK_WOOD_PLACE, 1, 0.8F),
                        new PlaceSameDataBlockAction(Material.OAK_LOG)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:spruce_bark"), 1, "STRIPPED_SPRUCE_LOG",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaySoundAction(Sound.BLOCK_WOOD_PLACE, 1, 0.8F),
                        new PlaceSameDataBlockAction(Material.SPRUCE_LOG)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:birch_bark"), 1, "STRIPPED_BIRCH_LOG",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaySoundAction(Sound.BLOCK_WOOD_PLACE, 1, 0.8F),
                        new PlaceSameDataBlockAction(Material.BIRCH_LOG)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:jungle_bark"), 1, "STRIPPED_JUNGLE_LOG",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaySoundAction(Sound.BLOCK_WOOD_PLACE, 1, 0.8F),
                        new PlaceSameDataBlockAction(Material.JUNGLE_LOG)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:acacia_bark"), 1, "STRIPPED_ACACIA_LOG",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaySoundAction(Sound.BLOCK_WOOD_PLACE, 1, 0.8F),
                        new PlaceSameDataBlockAction(Material.ACACIA_LOG)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:dark_oak_bark"), 1, "STRIPPED_DARK_OAK_LOG",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaySoundAction(Sound.BLOCK_WOOD_PLACE, 1, 0.8F),
                        new PlaceSameDataBlockAction(Material.DARK_OAK_LOG)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:mangrove_bark"), 1, "STRIPPED_MANGROVE_LOG",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaySoundAction(Sound.BLOCK_WOOD_PLACE, 1, 0.8F),
                        new PlaceSameDataBlockAction(Material.MANGROVE_LOG)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:crimson_bark"), 1, "STRIPPED_CRIMSON_STEM",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaySoundAction(Sound.BLOCK_STEM_PLACE, 1, 0.8F),
                        new PlaceSameDataBlockAction(Material.CRIMSON_STEM)
                }));
        registerManager.create(new InteractBlockRecipe(
                new ItemInput("vkmaterials:warped_bark"), 1, "STRIPPED_WARPED_STEM",
                InteractBlockRecipe.InteractBlockAction.SHIFT_RIGHT_CLICK,
                new ResultAction[]{
                        new PlaySoundAction(Sound.BLOCK_STEM_PLACE, 1, 0.8F),
                        new PlaceSameDataBlockAction(Material.WARPED_STEM)
                }));
    }


    public String getResultNamespaceId() {
        String last = null;
        for (ResultAction resultAction : resultActions) {
            String str = resultAction.getResultNamespaceId();
            if (str != null) {
                last = str;
            }
        }
        return last;
    }


    @Override
    public boolean isValid() {
        if (inputAmount > 64 || inputAmount < 1) {
            TheVoidKingdom.LOGGER.warning("注册方块交互配方失败,原因: 输入物品数量 " + inputAmount + " 不合法");
            return false;
        }
        if (!ItemsAdderUtils.isBlockNamespaceIdExist(blockNamespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册方块交互配方失败,原因: 方块 " + blockNamespaceId + " 不存在");
            return false;
        }
        String inputNamespaceId = input.getInputNamespaceId();
        if (!ItemsAdderUtils.isItemNamespaceIdExist(inputNamespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册方块交互配方失败,原因: 输入物品 " + inputNamespaceId + " 不存在");
            return false;
        }
        String resultNamespaceId = null;
        for (ResultAction resultAction : resultActions) {
            if (!resultAction.isValid()) {
                if (!resultAction.isValid()) {
                    TheVoidKingdom.LOGGER.warning("注册方块交互配方失败,原因: 存在无效的ResultAction");
                    return false;
                }
            }
            String nowResultNamespaceId = resultAction.getResultNamespaceId();
            if (nowResultNamespaceId != null) {
                resultNamespaceId = nowResultNamespaceId;
            }
        }
        if (resultNamespaceId == null) {
            TheVoidKingdom.LOGGER.warning("注册方块交互配方失败,原因: ResultAction有误,Action没有结果");
            return false;
        }
        return true;
    }

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_interact_block:";
    }

    @Override
    public String getMenuDisplayName() {
        return "与方块交互";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        inventory.setItem(19, ItemsAdderUtils.getItemStackNoCheck(this.input().getInputNamespaceId()));
        inventory.setItem(21, ItemsAdderUtils.getItemStackNoCheck(this.blockNamespaceId()));
        ItemStack actionIcon = switch (this.interactAction()) {
            case RIGHT_CLICK, ONLY_RIGHT_CLICK -> GuideBookMenuIcon.RIGHT_CLICK.getItemStack();
            case LEFT_CLICK, ONLY_LEFT_CLICK -> GuideBookMenuIcon.LEFT_CLICK.getItemStack();
            case SHIFT_RIGHT_CLICK -> GuideBookMenuIcon.SHIFT_RIGHT_CLICK.getItemStack();
            case SHIFT_LEFT_CLICK -> GuideBookMenuIcon.SHIFT_LEFT_CLICK.getItemStack();
        };
        inventory.setItem(14, actionIcon);
        ItemStack resultItemStack = ItemsAdderUtils.getItemStackNoCheck(this.getResultNamespaceId());
        inventory.setItem(25, resultItemStack);
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        if (blockNamespaceId.equals(namespaceId)) {
            return true;
        }
        if (input instanceof ItemInput itemInput) {
            return itemInput.getInputNamespaceId().equals(namespaceId);
        } else if (input instanceof ToolInput toolInput) {
            if (namespaceId.contains(":")) {
                CustomStack customStack = CustomStack.getInstance(namespaceId);
                if (customStack != null) {
                    return toolInput.isRightTool(customStack.getItemStack().getType());
                }
            } else {
                Material material = Material.getMaterial(namespaceId);
                if (material != null) {
                    return toolInput.isRightTool(material);
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return getResultNamespaceId().equals(namespaceId);
    }

    public static final Map<String, InteractBlockRecipe> INTERACT_BLOCK_MAP = new HashMap<>();

    @RegisterExtraTask
    private static final Runnable extraTask = () -> {
        INTERACT_BLOCK_MAP.clear();
        List<InteractBlockRecipe> validList = RegisterManager.getManager(InteractBlockRecipe.class).getValidList();
        validList.forEach(interactBlockRecipe -> {
            INTERACT_BLOCK_MAP.put(interactBlockRecipe.input().getInputKey() + interactBlockRecipe.blockNamespaceId(), interactBlockRecipe);
        });
    };

    public enum InteractBlockAction {
        RIGHT_CLICK,
        LEFT_CLICK,
        ONLY_RIGHT_CLICK,
        ONLY_LEFT_CLICK,
        SHIFT_RIGHT_CLICK,
        SHIFT_LEFT_CLICK
    }

}
