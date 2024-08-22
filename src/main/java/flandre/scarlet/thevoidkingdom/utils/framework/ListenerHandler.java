package flandre.scarlet.thevoidkingdom.utils.framework;

import com.molean.isletopia.framework.annotations.AutoInject;
import com.molean.isletopia.framework.annotations.BeanHandler;
import com.molean.isletopia.framework.annotations.IBeanHandler;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

@BeanHandler
public class ListenerHandler implements IBeanHandler {


    @AutoInject
    private TheVoidKingdom plugin;
    @Override
    public void handle(Object o) {
        if (o instanceof Listener listener) {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        }
    }
}