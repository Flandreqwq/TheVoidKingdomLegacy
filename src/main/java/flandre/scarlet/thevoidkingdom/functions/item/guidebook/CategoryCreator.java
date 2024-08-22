package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import com.molean.isletopia.framework.annotations.Bean;
import de.tr7zw.nbtapi.NBTItem;
import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Creator;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Bean
public class CategoryCreator implements Creator {
    private static final RegisterManager<Category> registerManager = RegisterManager.getManager(Category.class);

    @Override
    public void initialize() {
        File itemsAdder = Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemsAdder")).getDataFolder();
        File contents = new File(itemsAdder, "contents");
        File[] files = contents.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            File configs = new File(file, "configs");
            if (!configs.exists()) {
                continue;
            }
            File categories = new File(configs, "categories.yml");
            if (!categories.exists()) {
                continue;
            }

            YamlConfiguration yml = new YamlConfiguration();
            try {
                yml.load(categories);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }


            ConfigurationSection configurationSection = yml.getConfigurationSection("categories");
            if (configurationSection == null) {
                return;
            }
            Set<String> categoryNames = configurationSection.getKeys(false);
            for (String categoryName : categoryNames) {
                if (!yml.getBoolean("categories." + categoryName + ".enabled")) {
                    continue;
                }
                String categoryDisplayName = yml.getString("categories." + categoryName + ".name");
                String iconNamespaceId = yml.getString("categories." + categoryName + ".icon");
                String permission = yml.getString("categories." + categoryName + ".permission");
                LinkedHashSet<String> nameSpaceIds = new LinkedHashSet<>(yml.getStringList("categories." + categoryName + ".items"));
                ItemStack icon;
                CustomStack customStack = CustomStack.getInstance(iconNamespaceId);
                if (customStack == null) {
                    TheVoidKingdom.LOGGER.warning("不存在的ia物品：" + iconNamespaceId + " ,无法作为 " + categoryName + " 分类的图标");
                    icon = ItemsAdderUtils.getLostTextureIcon(categoryDisplayName);
                } else {
                    ItemStack itemStack = customStack.getItemStack();
                    icon = new ItemStack(itemStack.getType());
                    ItemMeta itemMeta = icon.getItemMeta();
                    itemMeta.setCustomModelData(itemStack.getItemMeta().getCustomModelData());
                    itemMeta.displayName(LegacyComponentSerializer.legacyAmpersand().deserialize(categoryDisplayName).decoration(TextDecoration.ITALIC, false));
                    itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    icon.setItemMeta(itemMeta);
                }
                NBTItem nbtItem = new NBTItem(icon);
                nbtItem.setString("category", categoryName);
                registerManager.create(new Category(categoryName, categoryDisplayName, nbtItem.getItem(), permission, new ArrayList<>(nameSpaceIds)));
            }
        }
    }

    @Override
    public void clear() {
        registerManager.getRawList().clear();
    }
}
