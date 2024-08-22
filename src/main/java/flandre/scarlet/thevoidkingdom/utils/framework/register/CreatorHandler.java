package flandre.scarlet.thevoidkingdom.utils.framework.register;

import com.molean.isletopia.framework.annotations.BeanHandler;
import com.molean.isletopia.framework.annotations.IBeanHandler;

import java.util.HashSet;
import java.util.Set;

@BeanHandler
public class CreatorHandler implements IBeanHandler {

    public static final Set<Creator> CREATOR_SET = new HashSet<>();

    @Override
    public void handle(Object o) {
        if (o instanceof Creator creator) {
            CREATOR_SET.add(creator);
        }
    }
}