package flandre.scarlet.thevoidkingdom.utils.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IntervalTask {
    int value() default 1;
    int delay() default 0;
    boolean isAsynchronously() default true;
}
