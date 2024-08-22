package flandre.scarlet.thevoidkingdom.functions.block.direction;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterExtraTask;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import java.util.List;

@Register(registerName = "6方向方块")
public record Direction6(Direction origin, String north, String south, String east, String west, String up,
                         String down) implements DirectionalBlock {

    private static final RegisterManager<Direction6> registerManager = RegisterManager.getManager(Direction6.class);

    private String getBlock(BlockFace blockFace) {
        return switch (blockFace) {
            case UP -> up;
            case EAST -> east;
            case NORTH -> north;
            case WEST -> west;
            case DOWN -> down;
            case SOUTH -> south;
            default -> "AIR";
        };
    }

    @Override
    public void place(float pitch, float yaw, BlockFace blockFace, Location location) {
        switch (blockFace) {
            case DOWN, UP -> {
                if (pitch < 45 && pitch > -45) {
                    if (yaw < 45 && yaw > -45) {
                        ItemsAdderUtils.placeBlock(north, location);
                    } else if (yaw < -45 && yaw > -135) {
                        ItemsAdderUtils.placeBlock(west, location);
                    } else if (yaw > 45 && yaw < 135) {
                        ItemsAdderUtils.placeBlock(east, location);
                    } else {
                        ItemsAdderUtils.placeBlock(south, location);
                    }
                } else {
                    ItemsAdderUtils.placeBlock(getBlock(blockFace), location);
                }
            }
            case NORTH, WEST, SOUTH, EAST -> {
                if (pitch < 45 && pitch > -45) {
                    ItemsAdderUtils.placeBlock(getBlock(blockFace), location);
                } else if (pitch >= 45) {
                    ItemsAdderUtils.placeBlock(up, location);
                } else {
                    ItemsAdderUtils.placeBlock(down, location);
                }
            }
        }
    }


    @Override
    public String getOrigin() {
        return switch (origin) {
            case SOUTH -> south;
            case NORTH -> north;
            case WEST -> west;
            case UP -> up;
            case DOWN -> down;
            default -> east;
        };
    }


    @Override
    public boolean isValid() {
        if (!ItemsAdderUtils.isItemNamespaceIdExist(north)) {
            TheVoidKingdom.LOGGER.warning("注册6方向方块失败,原因: north向方块 " + north + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(south)) {
            TheVoidKingdom.LOGGER.warning("注册6方向方块失败,原因: south向方块 " + south + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(east)) {
            TheVoidKingdom.LOGGER.warning("注册6方向方块失败,原因: east向方块 " + east + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(west)) {
            TheVoidKingdom.LOGGER.warning("注册6方向方块失败,原因: west向方块 " + west + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(up)) {
            TheVoidKingdom.LOGGER.warning("注册6方向方块失败,原因: up向方块 " + up + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(down)) {
            TheVoidKingdom.LOGGER.warning("注册6方向方块失败,原因: down向方块 " + down + " 不存在");
            return false;
        }
        return true;
    }


    @RegisterExtraTask
    private static final Runnable extraTask = () -> {
        DirectionalBlockMap directionalBlockMap = DirectionalBlock.DIRECTIONAL_BLOCK_MAP;
        directionalBlockMap.clear();
        List<Direction6> validList6 = RegisterManager.getManager(Direction6.class).getValidList();
        validList6.forEach(direction6 -> {
            directionalBlockMap.addKey(direction6.getOrigin(), direction6);
            directionalBlockMap.addOrigin(direction6.north(), direction6.getOrigin());
            directionalBlockMap.addOrigin(direction6.south(), direction6.getOrigin());
            directionalBlockMap.addOrigin(direction6.west(), direction6.getOrigin());
            directionalBlockMap.addOrigin(direction6.east(), direction6.getOrigin());
            directionalBlockMap.addOrigin(direction6.up(), direction6.getOrigin());
            directionalBlockMap.addOrigin(direction6.down(), direction6.getOrigin());
        });
    };
}
