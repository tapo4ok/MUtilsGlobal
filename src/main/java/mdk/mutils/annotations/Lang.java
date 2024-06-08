package mdk.mutils.annotations;

import mdk.mutils.lang.ILang;
import mdk.mutils.lang.SimpleLang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lang {
    String[] value() default {};
    Class<? extends ILang> type() default SimpleLang.class;
}
