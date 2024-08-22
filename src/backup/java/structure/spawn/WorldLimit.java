package flandre.scarlet.thevoidkingdom.functions.structure.spawn;

import com.magmaguy.betterstructures.api.BuildPlaceEvent;
import com.magmaguy.betterstructures.buildingfitter.FitAnything;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.List;

public final class WorldLimit implements Listener {
    private boolean IsNameNotContain(String name, List<String> list) {
        for (String str : list) {
            if (name.contains(str)) {
                return false;
            }
        }
        return true;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void OnBuildPlaceEvent(BuildPlaceEvent event) {
        FitAnything fitAnything = event.getFitAnything();
        World world = fitAnything.getLocation().getWorld();
        String name = fitAnything.getSchematicContainer().getSchematicConfigField().getFilename();
        switch (world.getName()) {
            case "world":
                if(IsNameNotContain(name, List.of(
                        "airballoon",
                        "boat",
                        "cistern",
                        "dungeon",
                        "watertemplesmall",
                        "well",
                        "graveyard",
                        "skyisland",
                        "mine_connector",
                        "mine_",
                        "mine_storage",
                        "mine_surfacebore",
                        "mine_tailingdump",
                        "raft",
                        "tomb",
                        "waypoint"
                ))){
                    event.setCancelled(true);
                }
                break;
            case "world_nightmare":
                if(IsNameNotContain(name, List.of(
                        "airballoon",
                        "boat",
                        "cistern",
                        "dungeon",
                        "bridgecave",
                        "flagship",
                        "floatingfortress",
                        "temple",
                        "tower",
                        "undergroundtemple",
                        "watertemplesmall",
                        "well",
                        "graveyard",
                        "skyisland",
                        "mine_connector",
                        "mine_",
                        "mine_storage",
                        "mine_surfacebore",
                        "mine_tailingdump",
                        "raft",
                        "tomb",
                        "waypoint"
                ))){
                    event.setCancelled(true);
                }
                break;
        }
//        System.out.println(name+" "+world+" "+event.isCancelled());
    }
}
