package flandre.scarlet.thevoidkingdom.utils.framework.register;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterManager<T extends Registrable> {
    private final Class<T> clazz;
    private final List<T> validList = new ArrayList<>();
    private final List<T> rawList = new ArrayList<>();
    public static final Map<Class<?>, RegisterManager<?>> MANAGER_MAP = new HashMap<>();

    public RegisterManager(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Registrable> RegisterManager<T> getManager(Class<T> clazz) {
        return (RegisterManager<T>) MANAGER_MAP.computeIfAbsent(clazz, (clazz1) -> new RegisterManager<>(clazz));
    }

    public void registerAll() {
        int successAmount = 0;
        int failureAmount = 0;
        validList.clear();
        for (T t : rawList) {
            if (t.isValid()) {
                validList.add(t);
                successAmount++;
            } else {
                failureAmount++;
            }
        }
        TheVoidKingdom.LOGGER.info("注册了 " + successAmount + " 个[" + getRegisterName() + "(" + clazz.getSimpleName() + ".class)]，失败 " + failureAmount + " 个");
    }

    public String getRegisterName() {
        Register register = clazz.getAnnotation(Register.class);
        return register == null ? "未设置注册名" : register.registerName();
    }

    public void create(T t) {
        rawList.add(t);
    }


    public void runExtraTask() {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(RegisterExtraTask.class)) {
                field.setAccessible(true);
                if (!Modifier.isStatic(field.getModifiers()) || !field.getType().equals(Runnable.class)) {
                    TheVoidKingdom.LOGGER.warning("额外注册任务必须是静态Runnable");
                    break;
                }
                try {
                    Runnable extraTask = (Runnable) field.get(clazz);
                    extraTask.run();
                    TheVoidKingdom.LOGGER.info("执行了[" + getRegisterName() +"("+ clazz.getSimpleName() +".class)]的额外注册任务" );
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<T> getValidList() {
        return validList;
    }

    public List<T> getRawList() {
        return rawList;
    }
}
