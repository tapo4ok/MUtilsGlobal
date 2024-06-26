package mdk.mutils.lang;

import mdk.mutils.Context;
import mdk.mutils.annotations.Lang;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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

                    for (String str : instance.value()) {
                        lang.load(context.getClassLoader().getResourceAsStream(str));
                    }
                    if (!Modifier.isStatic(Modifier.fieldModifiers())) {
                        field.set(obj, lang);
                    }
                    else {
                        field.set(null, lang);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
