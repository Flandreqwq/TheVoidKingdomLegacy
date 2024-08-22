package flandre.scarlet.thevoidkingdom;

import co.aikar.commands.PaperCommandManager;
import com.molean.isletopia.framework.ClassResolver;
import flandre.scarlet.thevoidkingdom.utils.framework.BukkitClassScanner;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.logging.Logger;

public final class TheVoidKingdom extends JavaPlugin {
    public static JavaPlugin PLUGIN;
    public static Logger LOGGER;
    public static MiniMessage MINI_MESSAGE;
    public static NamespacedKey DATA_NAMESPACED;

    @Override
    public void onEnable() {
        PLUGIN = this;
        LOGGER = this.getLogger();
        MINI_MESSAGE = MiniMessage.miniMessage();
        DATA_NAMESPACED = new NamespacedKey(this, "vkdata");
        BukkitClassScanner bukkitClassScanner = new BukkitClassScanner(this);
        PaperCommandManager paperCommandManager = new PaperCommandManager(this);
        paperCommandManager.enableUnstableAPI("help");
        paperCommandManager.getLocales().setDefaultLocale(Locale.CHINA);
        try {
            ClassResolver.INSTANCE.loadClass(bukkitClassScanner, "flandre.scarlet.thevoidkingdom");
            ClassResolver.INSTANCE.addBean(this);
            ClassResolver.INSTANCE.addBean(bukkitClassScanner);
            ClassResolver.INSTANCE.addBean(paperCommandManager);
            ClassResolver.INSTANCE.resolveBean();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        System.out.println(" ");
        System.out.println(" ");
        System.out.println("[虚空之国] 插件加载成功");
        System.out.println("[虚空之国] 插件作者: __FlandreScarlet");
        System.out.println(" ");
        System.out.println(" ");
    }


    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }
}
