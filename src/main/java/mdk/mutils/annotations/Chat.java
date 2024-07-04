package mdk.mutils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Chat {
    Identifier id();
    String permision();

    @Retention(RetentionPolicy.RUNTIME)
    @interface Identifier {
        String value();
    }
}
