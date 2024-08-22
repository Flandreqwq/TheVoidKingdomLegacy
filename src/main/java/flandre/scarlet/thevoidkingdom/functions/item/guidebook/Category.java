package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterExtraTask;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Registrable;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Register(registerName = "手册物品分类")
public record Category(String categoryName, String categoryDisplayName, ItemStack icon, String permission,
                       List<String> nameSpaceIds) implements Registrable {

    @Override
    public boolean isValid() {
        return true;
    }

    public static final Map<String, Category> CATEGORY_KEY_MAP = new HashMap<>();

    @RegisterExtraTask
    private static final Runnable extraTask = () -> {
        CATEGORY_KEY_MAP.clear();
        List<Category> validList = RegisterManager.getManager(Category.class).getValidList();
        validList.forEach(category -> {
            CATEGORY_KEY_MAP.put(category.categoryName, category);
        });
    };
}
