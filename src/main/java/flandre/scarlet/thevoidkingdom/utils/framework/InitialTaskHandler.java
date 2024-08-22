package flandre.scarlet.thevoidkingdom.utils.framework;

import com.molean.isletopia.framework.ClassResolver;
import com.molean.isletopia.framework.annotations.BeanHandler;
import com.molean.isletopia.framework.annotations.IBeanHandler;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@BeanHandler(-1000)
public class InitialTaskHandler implements IBeanHandler {
    public void execute(Object object, Method method, List<Object> parameters) {
        try {
            method.setAccessible(true);
            method.invoke(object, parameters.toArray());
        } catch (Exception e) {
            TheVoidKingdom.LOGGER.severe("Error while execute initial task " + method.getName() + " for " + object.getClass().getName());
            e.printStackTrace();
        }
    }

    @Override
    public void handle(Object object) {
        outer:
        for (Method declaredMethod : object.getClass().getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(InitialTask.class)) {
                InitialTask annotation = declaredMethod.getAnnotation(InitialTask.class);
                boolean async = annotation.async();
                List<Object> parameters = new ArrayList<>();
                for (Parameter parameter : declaredMethod.getParameters()) {
                    Object parameterObject = ClassResolver.INSTANCE.getObject(parameter);
                    if (parameterObject == null) {
                        continue outer;
                    }
                    parameters.add(parameterObject);
                }
                if (async) {
                    CompletableFuture.runAsync(() -> {
                        execute(object, declaredMethod, parameters);
                    });
                } else {
                    execute(object, declaredMethod, parameters);
                }
            }
        }
    }
}
