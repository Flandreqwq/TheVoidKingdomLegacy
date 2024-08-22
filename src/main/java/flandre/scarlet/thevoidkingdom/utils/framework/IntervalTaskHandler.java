package flandre.scarlet.thevoidkingdom.utils.framework;

import com.molean.isletopia.framework.ClassResolver;
import com.molean.isletopia.framework.annotations.BeanHandler;
import com.molean.isletopia.framework.annotations.IBeanHandler;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.Bukkit;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

@BeanHandler
public class IntervalTaskHandler implements IBeanHandler {
    @Override
    public void handle(Object object) {
        outer:
        for (Method declareMethod : object.getClass().getDeclaredMethods()) {
            if (declareMethod.isAnnotationPresent(IntervalTask.class)) {
                IntervalTask annotation = declareMethod.getAnnotation(IntervalTask.class);
                int period = annotation.value();
                int delay = annotation.delay();
                boolean isAsynchronously = annotation.isAsynchronously();
                List<Object> parameters = new ArrayList<>();
                for (Parameter parameter : declareMethod.getParameters()) {
                    Object parameterObject = ClassResolver.INSTANCE.getObject(parameter);
                    if (parameterObject == null) {
                        continue outer;
                    }
                    parameters.add(parameterObject);
                }


                if (isAsynchronously) {
                    Bukkit.getScheduler().runTaskTimerAsynchronously(TheVoidKingdom.PLUGIN, bukkitTask -> {
                        execute(object, declareMethod, parameters);
                    }, delay, period);
                } else {
                    Bukkit.getScheduler().runTaskTimer(TheVoidKingdom.PLUGIN, bukkitTask -> {
                        execute(object, declareMethod, parameters);
                    }, delay, period);
                }
            }
        }
    }

    public void execute(Object object, Method method, List<Object> parameters) {
        try {
            method.setAccessible(true);
            method.invoke(object, parameters.toArray());
        } catch (Exception e) {
            TheVoidKingdom.LOGGER.severe("Error while run interval task " + method.getName() + " for " + object.getClass());
            e.printStackTrace();
        }
    }
}
