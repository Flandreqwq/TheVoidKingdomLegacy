package flandre.scarlet.thevoidkingdom.functions.block.interact;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Orientable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.block.data.type.Wall;

public class PlaceSameDataBlockAction implements ResultAction {
    private final Material material;

    public PlaceSameDataBlockAction(Material material) {
        this.material = material;
    }

    @Override
    public String getResultNamespaceId() {
        return material.toString();
    }

    @Override
    public void execute(Location location) {
        Block block = location.getBlock();
        BlockData oldBlockData = block.getBlockData().clone();
        block.setType(material);
        BlockData blockData = block.getBlockData();
        if (blockData instanceof Directional directional) {
            directional.setFacing(((Directional) oldBlockData).getFacing());
        }
        if (blockData instanceof Orientable orientable) {
            orientable.setAxis(((Orientable) oldBlockData).getAxis());
        }
        if (blockData instanceof Waterlogged waterlogged) {
            waterlogged.setWaterlogged(((Waterlogged) oldBlockData).isWaterlogged());
        }
        if (blockData instanceof Slab slab) {
            slab.setType(((Slab) oldBlockData).getType());
        }
        if (blockData instanceof Stairs stairs) {
            stairs.setShape(((Stairs) oldBlockData).getShape());
        }
        if (blockData instanceof Wall wall) {
            wall.setHeight(BlockFace.EAST, ((Wall) oldBlockData).getHeight(BlockFace.EAST));
            wall.setHeight(BlockFace.WEST, ((Wall) oldBlockData).getHeight(BlockFace.WEST));
            wall.setHeight(BlockFace.SOUTH, ((Wall) oldBlockData).getHeight(BlockFace.SOUTH));
            wall.setHeight(BlockFace.NORTH, ((Wall) oldBlockData).getHeight(BlockFace.NORTH));
            wall.setUp(((Wall) oldBlockData).isUp());
        }
        if (blockData instanceof Leaves leaves) {
            leaves.setDistance(((Leaves) oldBlockData).getDistance());
            leaves.setPersistent(((Leaves) oldBlockData).isPersistent());
        }
        block.setBlockData(blockData);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public Material getMaterial() {
        return material;
    }
}
