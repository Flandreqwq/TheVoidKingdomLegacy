package flandre.scarlet.thevoidkingdom.utils.framework;

import com.molean.isletopia.framework.ClassScanner;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.lang.reflect.Method;
import java.util.jar.JarFile;

public class BukkitClassScanner implements ClassScanner {

    private final Plugin plugin;

    public BukkitClassScanner(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Class<?> loadClass(String path) throws Exception {
        if (findClassMethodCache == null) {
            Class<?> aClass = Class.forName("org.bukkit.plugin.java.PluginClassLoader");
            findClassMethodCache = aClass.getDeclaredMethod("findClass", String.class);
            findClassMethodCache.setAccessible(true);
        }
        return (Class<?>) findClassMethodCache.invoke(getClassLoader(), path);
    }

    @Override
    public JarFile getJarFile() throws Exception {
        String path = new File(getClass().getProtectionDomain().getCodeSource().getLocation()
                .toURI()).getPath();
        return new JarFile(path);
    }

    private static Method findClassMethodCache = null;

    private static ClassLoader classLoader = null;

    public ClassLoader getClassLoader() {
        if (classLoader == null) {
            classLoader = plugin.getClass().getClassLoader();
        }
        return classLoader;
    }


}
