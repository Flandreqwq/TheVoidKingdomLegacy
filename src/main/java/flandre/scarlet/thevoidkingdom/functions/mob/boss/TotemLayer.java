package flandre.scarlet.thevoidkingdom.functions.mob.boss;

import dev.lone.itemsadder.api.CustomBlock;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class TotemLayer {
    String[][] layerBlocks;

    public TotemLayer(String[][] layerBlocks) {
        this.layerBlocks = layerBlocks;
    }

    public boolean isLayerBlocksMeet(Block originBlock) {
        for (int i = 0; i < layerBlocks.length; i++) {
            for (int j = 0; j < layerBlocks[i].length; j++) {
                Block block = originBlock.getRelative(i, 0, j);
                if (layerBlocks[i][j].contains(":")) {
                    CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
                    if (customBlock == null) {
                        return false;
                    } else if (!customBlock.getNamespacedID().equals(layerBlocks[i][j])) {
                        return false;
                    }
                } else {
                    if (layerBlocks[i][j] == null) {
                        continue;
                    }
                    Material material = Material.getMaterial(layerBlocks[i][j]);
                    if (material == null) {
                        TheVoidKingdom.LOGGER.warning("祭坛方块Material不存在");
                        return false;
                    } else if (!block.getType().equals(material)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
