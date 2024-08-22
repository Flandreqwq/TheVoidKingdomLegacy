package flandre.scarlet.thevoidkingdom.functions.item.consume;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterExtraTask;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Registrable;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Register(registerName = "具有额外效果的消耗品")
public record Consumable(String namespaceId, ResultAction[] resultActions) implements Registrable {
    private static final RegisterManager<Consumable> registerManager = RegisterManager.getManager(Consumable.class);

    static {
        registerManager.create(new Consumable("vkmaterials:milk_bottle", new ResultAction[]{
                new RemoveRandomPotionEffectAction()
        }));
    }

    @Override
    public boolean isValid() {
        if (!ItemsAdderUtils.isItemNamespaceIdExist(namespaceId)) {
            TheVoidKingdom.LOGGER.warning("注册具有额外效果的消耗品失败,原因: 物品 " + namespaceId + " 不存在");
            return false;
        }
        for (ResultAction resultAction : resultActions) {
            if (!resultAction.isValid()) {
                TheVoidKingdom.LOGGER.warning("注册具有额外效果的消耗品失败,原因: 存在无效的ResultAction");
                return false;
            }
        }
        return true;
    }

    public static Map<String, Consumable> CONSUMABLE_KEY_MAP = new HashMap<>();

    @RegisterExtraTask
    private static final Runnable extraTask = () -> {
        CONSUMABLE_KEY_MAP.clear();
        List<Consumable> validList = RegisterManager.getManager(Consumable.class).getValidList();
        validList.forEach(consumable -> {
            CONSUMABLE_KEY_MAP.put(consumable.namespaceId, consumable);
        });
    };
}
