package flandre.scarlet.thevoidkingdom.utils.framework.register;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.Events.ItemsAdderLoadDataEvent;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

@Bean
public class RegisterListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(ItemsAdderLoadDataEvent event) {
        TheVoidKingdom.LOGGER.info("创建虚空之国相关注册内容中……");
        CreatorHandler.CREATOR_SET.forEach(creator -> {
            creator.clear();
            creator.initialize();
        });
        TheVoidKingdom.LOGGER.info("创建完成!");
        TheVoidKingdom.LOGGER.info("注册虚空之国相关内容中……");
        RegisterManager.MANAGER_MAP.values().forEach(registerManager -> {
            registerManager.registerAll();
            registerManager.runExtraTask();
        });
        TheVoidKingdom.LOGGER.info("注册完成! " + RegisterManager.MANAGER_MAP.values().size() + " 个RegisterManager已启用");
    }
}
