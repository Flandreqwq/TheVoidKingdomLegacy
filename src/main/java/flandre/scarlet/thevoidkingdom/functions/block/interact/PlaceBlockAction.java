package flandre.scarlet.thevoidkingdom.functions.block.interact;

import dev.lone.itemsadder.api.CustomBlock;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class PlaceBlockAction implements ResultAction {
    private final String namespaceId;

    public PlaceBlockAction(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    @Override
    public void execute(Location location) {
        CustomBlock.remove(location);
        if (namespaceId.contains(":")) {
            CustomBlock.place(namespaceId, location);
        } else {
            Block block = location.getBlock();
            block.setType(Material.getMaterial(namespaceId));
        }
    }

    @Override
    public boolean isValid() {
        return ItemsAdderUtils.isBlockNamespaceIdExist(namespaceId);
    }

    @Override
    public String getResultNamespaceId() {
        return namespaceId;
    }

    public String getNamespaceId() {
        return namespaceId;
    }
}
