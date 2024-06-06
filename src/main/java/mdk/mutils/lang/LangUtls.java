package mdk.mutils.lang;

import mdk.mutils.Context;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;

public class LangUtls {
    public static void init(Object obj, Context context) {
        Iterator<Field> iterator = Arrays.stream(obj.getClass().getFields()).iterator();
        while (iterator.hasNext()) {
            Field field = iterator.next();
            if (field.isAnnotationPresent(Lang.class)) {
                Lang instance = field.getAnnotation(Lang.class);
                try {
                    ILang lang = instance.type().getConstructor().newInstance();

                    if (instance.value().equalsIgnoreCase("false")) {
                        lang.load(context.getClassLoader().getResourceAsStream(instance.value()));
                    }
                    field.set(obj, lang);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
