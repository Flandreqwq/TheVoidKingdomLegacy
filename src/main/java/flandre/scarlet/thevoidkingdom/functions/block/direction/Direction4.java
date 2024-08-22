package flandre.scarlet.thevoidkingdom.functions.block.direction;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterExtraTask;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import java.util.List;

@Register(registerName = "4方向方块")
public record Direction4(Direction origin, String north, String south, String east,
                         String west) implements DirectionalBlock {

    private static final RegisterManager<Direction4> registerManager = RegisterManager.getManager(Direction4.class);

    static {
        registerManager.create(new Direction4(Direction.EAST, "vkblocks:cutting_board_n", "vkblocks:cutting_board_s", "vkblocks:cutting_board", "vkblocks:cutting_board_w"));
    }

    @Override
    public void place(float pitch, float yaw, BlockFace blockFace, Location location) {
        if (yaw < 45 && yaw > -45) {
            ItemsAdderUtils.placeBlock(south, location);
        } else if (yaw < -45 && yaw > -135) {
            ItemsAdderUtils.placeBlock(east, location);
        } else if (yaw > 45 && yaw < 135) {
            ItemsAdderUtils.placeBlock(west, location);
        } else {
            ItemsAdderUtils.placeBlock(north, location);
        }
    }

    @Override
    public String getOrigin() {
        return switch (origin) {
            case SOUTH -> south;
            case NORTH -> north;
            case WEST -> west;
            default -> east;
        };
    }

    @Override
    public boolean isValid() {
        if (!ItemsAdderUtils.isItemNamespaceIdExist(north)) {
            TheVoidKingdom.LOGGER.warning("注册4方向方块失败,原因: north向方块 " + north + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(south)) {
            TheVoidKingdom.LOGGER.warning("注册4方向方块失败,原因: south向方块 " + south + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(east)) {
            TheVoidKingdom.LOGGER.warning("注册4方向方块失败,原因: east向方块 " + east + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(west)) {
            TheVoidKingdom.LOGGER.warning("注册4方向方块失败,原因: west向方块 " + west + " 不存在");
            return false;
        }
        return true;
    }

    @RegisterExtraTask
    private static final Runnable extraTask = () -> {
        DirectionalBlockMap directionalBlockMap = DirectionalBlock.DIRECTIONAL_BLOCK_MAP;
        directionalBlockMap.clear();
        List<Direction4> validList4 = RegisterManager.getManager(Direction4.class).getValidList();
        validList4.forEach(direction4 -> {
            directionalBlockMap.addKey(direction4.getOrigin(), direction4);
            directionalBlockMap.addOrigin(direction4.north(), direction4.getOrigin());
            directionalBlockMap.addOrigin(direction4.south(), direction4.getOrigin());
            directionalBlockMap.addOrigin(direction4.west(), direction4.getOrigin());
            directionalBlockMap.addOrigin(direction4.east(), direction4.getOrigin());
        });
    };
}
