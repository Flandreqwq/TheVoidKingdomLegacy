package flandre.scarlet.thevoidkingdom.functions.skill;

import com.molean.isletopia.framework.annotations.Bean;
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

@Bean
public class MythicMechanicLoadListener implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void on(MythicMechanicLoadEvent event) {
        switch (event.getMechanicName().toLowerCase()) {
            case "vktest" ->
                    event.register(new TestSkill(event.getContainer().getManager(), event.getMechanicName(), event.getConfig()));
            case "mmotempstatmodifier","tempstatmodifier","tempstat" ->
                    event.register(new MMOTempStatModifierSkill(event.getContainer().getManager(), event.getMechanicName(), event.getConfig()));
        }
    }
}
