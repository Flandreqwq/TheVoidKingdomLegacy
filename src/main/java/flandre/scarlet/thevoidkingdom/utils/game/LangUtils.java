package flandre.scarlet.thevoidkingdom.utils.game;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class LangUtils {
    private static JsonObject defaultJsonObject = null;

    private static final Map<Locale, JsonObject> jsonObjectMap = new HashMap<>();
    private static final Map<Locale, Map<String, String>> langMap = new HashMap<>();
    private static final Map<Locale, Map<String, Set<String>>> langReverseMap = new HashMap<>();

    static {
        InputStream inputStream = LangUtils.class.getClassLoader().getResourceAsStream("lang/zh_cn.json");
        if (inputStream != null) {
            @SuppressWarnings("all")
            JsonElement parse = new JsonParser().parse(new InputStreamReader(inputStream));
            defaultJsonObject = parse.getAsJsonObject();
        }
    }

    @Nullable
    private static JsonObject get(Locale locale) {
        if (jsonObjectMap.containsKey(locale)) {
            JsonObject jsonObject = jsonObjectMap.get(locale);
            if (jsonObject == null) {
                return defaultJsonObject;
            } else {
                return jsonObject;

            }
        }
        InputStream inputStream = LangUtils.class.getClassLoader().getResourceAsStream("lang/" + locale.toString().toLowerCase(Locale.ROOT) + ".json");
        if (inputStream != null) {
            @SuppressWarnings("all")
            JsonElement parse = new JsonParser().parse(new InputStreamReader(inputStream));
            JsonObject asJsonObject = parse.getAsJsonObject();
            jsonObjectMap.put(locale, asJsonObject);
            return asJsonObject;
        } else {
            return defaultJsonObject;
        }
    }

    @NotNull
    public static Map<String, Set<String>> getReverseNodeMap(Locale locale) {
        return langReverseMap.computeIfAbsent(locale, loc -> {
            HashMap<String, Set<String>> nodeMap = new HashMap<>();
            JsonObject jsonObject = get(locale);
            if (jsonObject != null) {
                try {
                    for (String s : jsonObject.keySet()) {
                        nodeMap.computeIfAbsent(jsonObject.get(s).getAsString(), t -> new HashSet<>());
                        nodeMap.get(jsonObject.get(s).getAsString()).add(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return nodeMap;
        });
    }

    @Nullable
    public static Set<String> getReverseKey(Locale locale, String data) {
        return getReverseNodeMap(locale).get(data);
    }

    @Nullable
    public static Set<String> getReverseKey(String data) {
        return getReverseNodeMap(Locale.SIMPLIFIED_CHINESE).get(data);
    }


    @Nullable
    public static String getInternalNameOf(Locale locale, String prefix, String entityDisplay) {
        Set<String> keys = getReverseNodeMap(locale).getOrDefault(entityDisplay, Set.of());
        if (keys.isEmpty()) {
            return null;
        }
        for (String key : keys) {
            if (key.startsWith(prefix)) {
                String[] split = key.split("\\.");
                if (split.length > 0) {
                    return split[split.length - 1];
                }
            }
        }
        return null;
    }

    @NotNull
    public static Map<String, String> getNodeMap(Locale locale) {
        return langMap.computeIfAbsent(locale, loc -> {
            HashMap<String, String> nodeMap = new HashMap<>();
            JsonObject jsonObject = get(locale);
            if (jsonObject != null) {
                try {
                    for (String s : jsonObject.keySet()) {
                        nodeMap.put(s, jsonObject.get(s).getAsString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return nodeMap;
        });
    }

    @NotNull
    public static String get(Locale locale, String key) {
        return getNodeMap(locale).getOrDefault(key, key);
    }

    @NotNull
    public static String getOr(Locale locale, String key, String def) {
        return getNodeMap(locale).getOrDefault(key, def);
    }

    @NotNull
    public static String get(String key) {
        return get(Locale.SIMPLIFIED_CHINESE, key);
    }

    @NotNull
    public static String getOr(String key, String def) {
        return getOr(Locale.SIMPLIFIED_CHINESE, key, def);
    }

    @NotNull
    public static boolean contains(Locale locale, String key) {
        return !get(locale, key).equals(key);
    }

    @NotNull
    public static boolean contains(String key) {
        return !get(Locale.SIMPLIFIED_CHINESE, key).equals(key);
    }


    @NotNull
    public static String getItem(Locale locale, String internal) {
        return getOr(locale, "item.minecraft." + internal, internal);
    }

    @NotNull
    public static String getItemOrBlock(Locale locale, String internal) {
        return getOr(locale, "item.minecraft." + internal, getOr(locale, "block.minecraft." + internal, internal));
    }

    @NotNull
    public static String getItemOrBlockOr(Locale locale, String internal, String def) {
        return getOr(locale, "item.minecraft." + internal, getOr(locale, "block.minecraft." + internal, def));
    }


    @NotNull
    public static String getItemOrBlock(String internal) {
        return getItemOrBlock(Locale.CHINA, internal);
    }

    @NotNull
    public static String getItemOrBlockOr(String internal, String def) {
        return getItemOrBlockOr(Locale.CHINA, internal, def);
    }

    @NotNull
    public static String getItem(String internal) {
        return getItem(Locale.CHINA, internal);
    }


    @NotNull
    public static String getStat(Locale locale, String internal) {
        return getOr(locale, "stat.minecraft." + internal, internal);
    }

    @NotNull
    public static String getStat(String internal) {
        return getStat(Locale.CHINA, internal);
    }

    @NotNull
    public static String getEntity(Locale locale, String internal) {
        return getOr(locale, "entity.minecraft." + internal, internal);
    }

    @NotNull
    public static String getEntity(String internal) {
        return getEntity(Locale.CHINA, internal);
    }

    @NotNull
    public static String getBlock(Locale locale, String internal) {
        return getOr(locale, "block.minecraft." + internal, internal);
    }

    @NotNull
    public static String getBlock(String internal) {
        return getBlock(Locale.CHINA, internal);
    }

    @NotNull
    public static String getAdvancementTitle(Locale locale, String internal) {
        return getOr(locale, "advancements." + internal + ".title", internal);
    }

    @NotNull
    public static String getAdvancementTitle(String internal) {
        return getAdvancementTitle(Locale.CHINA, internal);
    }

    @NotNull
    public static String getAdvancementDesc(Locale locale, String internal) {
        return getOr(locale, "advancements." + internal + ".description", internal);
    }

    @NotNull
    public static String getAdvancementDesc(String internal) {
        return getAdvancementDesc(Locale.CHINA, internal);
    }

    @NotNull
    public static String getBiome(Locale locale, String internal) {
        return getOr(locale, "biome.minecraft." + internal, internal);
    }

    @NotNull
    public static String getBiome(String internal) {
        return getBiome(Locale.CHINA, internal);
    }

}