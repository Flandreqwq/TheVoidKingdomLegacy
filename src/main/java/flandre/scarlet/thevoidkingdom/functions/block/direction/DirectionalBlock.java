package flandre.scarlet.thevoidkingdom.functions.block.direction;

import flandre.scarlet.thevoidkingdom.utils.framework.register.Registrable;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

public interface DirectionalBlock extends Registrable {
    public static final DirectionalBlockMap DIRECTIONAL_BLOCK_MAP = new DirectionalBlockMap();

    void place(float pitch, float yaw, BlockFace blockFace, Location location);

    String getOrigin();
}
