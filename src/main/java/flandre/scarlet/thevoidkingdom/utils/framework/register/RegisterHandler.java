package flandre.scarlet.thevoidkingdom.utils.framework.register;

import com.molean.isletopia.framework.annotations.AnnotationHandler;
import com.molean.isletopia.framework.annotations.IAnnotationHandler;

@AnnotationHandler(Register.class)
public class RegisterHandler implements IAnnotationHandler<Register> {
    @Override
    public void handle(Class<?> clazz, Register register) {
        String name = clazz.getName();
        try {
            Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
