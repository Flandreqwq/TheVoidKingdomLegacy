package flandre.scarlet.thevoidkingdom.utils;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigUtil {

    private static final Map<String, String> map = new ConcurrentHashMap<>();

    public static void reloadAll() {
        map.clear();
    }

    public static YamlConfiguration getConfig(String type) {
        String s1 = map.computeIfAbsent(type, (s) -> {
            try {
                File dataFolder = TheVoidKingdom.PLUGIN.getDataFolder();
                if (!dataFolder.exists()) {
                    boolean mkdirs = dataFolder.mkdirs();
                    assert mkdirs;
                }
                File file = new File(dataFolder, type + ".yml");
                if (!file.exists()) {
                    InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(type + ".yml");
                    byte[] bytes;
                    assert inputStream != null;
                    bytes = inputStream.readAllBytes();
                    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                        fileOutputStream.write(bytes);
                    }
                }
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    return new String(fileInputStream.readAllBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return YamlConfiguration.loadConfiguration(new StringReader(s1));
    }
}
