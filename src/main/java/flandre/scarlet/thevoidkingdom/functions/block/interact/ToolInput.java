package flandre.scarlet.thevoidkingdom.functions.block.interact;

import com.destroystokyo.paper.MaterialTags;
import org.bukkit.Material;

public class ToolInput implements InputChoice {
    public enum ToolType {
        PICKAXE,
        AXE,
        HOE,
        SHOVEL,
        SHEARS,
        BRUSH,
        FLINT_AND_STEEL
    }

    private final ToolType toolType;
    private final boolean forceDecreaseDurability;

    public ToolInput(ToolType toolType, boolean forceDecreaseDurability) {
        this.toolType = toolType;
        this.forceDecreaseDurability = forceDecreaseDurability;
    }

    @Override
    public String getInputNamespaceId() {
        return switch (toolType) {
            case PICKAXE -> "vkguis:icon_any_pickaxe";
            case AXE -> "vkguis:icon_any_axe";
            case HOE -> "vkguis:icon_any_hoe";
            case SHOVEL -> "vkguis:icon_any_shovel";
            case SHEARS -> "vkguis:icon_any_shears";
            case BRUSH -> "vkguis:icon_any_brush";
            case FLINT_AND_STEEL -> "vkguis:icon_any_flint_and_steel";
        };
    }

    @Override
    public String getInputKey() {
        return toolType.name();
    }

    public boolean isRightTool(Material material) {
        return switch (toolType) {
            case PICKAXE -> MaterialTags.PICKAXES.isTagged(material);
            case AXE -> MaterialTags.AXES.isTagged(material);
            case HOE -> MaterialTags.HOES.isTagged(material);
            case SHOVEL -> MaterialTags.SHOVELS.isTagged(material);
            case SHEARS -> material.equals(Material.SHEARS);
            case BRUSH -> material.equals(Material.BRUSH);
            case FLINT_AND_STEEL -> material.equals(Material.FLINT_AND_STEEL);
        };
    }

    public ToolType getToolType() {
        return toolType;
    }

    public boolean isForceDecreaseDurability() {
        return forceDecreaseDurability;
    }
}
