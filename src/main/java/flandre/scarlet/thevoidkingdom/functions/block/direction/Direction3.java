package flandre.scarlet.thevoidkingdom.functions.block.direction;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterExtraTask;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import java.util.List;

@Register(registerName = "3方向方块")
public record Direction3(Direction origin, String x, String y, String z) implements DirectionalBlock {
    private static final RegisterManager<Direction3> registerManager = RegisterManager.getManager(Direction3.class);

    static {
        registerManager.create(new Direction3(Direction.Y, "vkblocks:depthrock_pillar_x", "vkblocks:depthrock_pillar", "vkblocks:depthrock_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:blue_glazed_terracotta_pillar_x", "vkblocks:blue_glazed_terracotta_pillar", "vkblocks:blue_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:brown_glazed_terracotta_pillar_x", "vkblocks:brown_glazed_terracotta_pillar", "vkblocks:brown_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:cyan_glazed_terracotta_pillar_x", "vkblocks:cyan_glazed_terracotta_pillar", "vkblocks:cyan_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:gray_glazed_terracotta_pillar_x", "vkblocks:gray_glazed_terracotta_pillar", "vkblocks:gray_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:green_glazed_terracotta_pillar_x", "vkblocks:green_glazed_terracotta_pillar", "vkblocks:green_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:light_blue_glazed_terracotta_pillar_x", "vkblocks:light_blue_glazed_terracotta_pillar", "vkblocks:light_blue_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:light_gray_glazed_terracotta_pillar_x", "vkblocks:light_gray_glazed_terracotta_pillar", "vkblocks:light_gray_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:lime_glazed_terracotta_pillar_x", "vkblocks:lime_glazed_terracotta_pillar", "vkblocks:lime_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:magenta_glazed_terracotta_pillar_x", "vkblocks:magenta_glazed_terracotta_pillar", "vkblocks:magenta_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:orange_glazed_terracotta_pillar_x", "vkblocks:orange_glazed_terracotta_pillar", "vkblocks:orange_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:pink_glazed_terracotta_pillar_x", "vkblocks:pink_glazed_terracotta_pillar", "vkblocks:pink_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:red_glazed_terracotta_pillar_x", "vkblocks:red_glazed_terracotta_pillar", "vkblocks:red_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:white_glazed_terracotta_pillar_x", "vkblocks:white_glazed_terracotta_pillar", "vkblocks:white_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:yellow_glazed_terracotta_pillar_x", "vkblocks:yellow_glazed_terracotta_pillar", "vkblocks:yellow_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:purple_glazed_terracotta_pillar_x", "vkblocks:purple_glazed_terracotta_pillar", "vkblocks:purple_glazed_terracotta_pillar_z"));
        registerManager.create(new Direction3(Direction.Y, "vkblocks:black_glazed_terracotta_pillar_x", "vkblocks:black_glazed_terracotta_pillar", "vkblocks:black_glazed_terracotta_pillar_z"));
    }

    @Override
    public void place(float pitch, float yaw, BlockFace blockFace, Location location) {
        if (blockFace == BlockFace.NORTH || blockFace == BlockFace.SOUTH) {
            ItemsAdderUtils.placeBlock(z, location);
        } else if (blockFace == BlockFace.EAST || blockFace == BlockFace.WEST) {
            ItemsAdderUtils.placeBlock(x, location);
        } else {
            ItemsAdderUtils.placeBlock(y, location);
        }
    }

    @Override
    public String getOrigin() {
        return switch (origin) {
            case X -> x;
            case Z -> z;
            default -> y;
        };
    }


    @Override
    public boolean isValid() {
        if (!ItemsAdderUtils.isItemNamespaceIdExist(x)) {
            TheVoidKingdom.LOGGER.warning("注册3方向方块失败,原因: x向方块 " + x + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(y)) {
            TheVoidKingdom.LOGGER.warning("注册3方向方块失败,原因: y向方块 " + y + " 不存在");
            return false;
        }
        if (!ItemsAdderUtils.isItemNamespaceIdExist(z)) {
            TheVoidKingdom.LOGGER.warning("注册3方向方块失败,原因: z向方块 " + z + " 不存在");
            return false;
        }
        return true;
    }


    @RegisterExtraTask
    private static final Runnable extraTask = () -> {
        DirectionalBlockMap directionalBlockMap = DirectionalBlock.DIRECTIONAL_BLOCK_MAP;
        directionalBlockMap.clear();
        List<Direction3> validList3 = RegisterManager.getManager(Direction3.class).getValidList();
        validList3.forEach(direction3 -> {
            directionalBlockMap.addKey(direction3.getOrigin(), direction3);
            directionalBlockMap.addOrigin(direction3.x(), direction3.getOrigin());
            directionalBlockMap.addOrigin(direction3.y(), direction3.getOrigin());
            directionalBlockMap.addOrigin(direction3.z(), direction3.getOrigin());
        });
    };
}
