package mdk.mutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mdk.mutils.annotations.StaticInstance;
import mdk.mutils.json.GsonSerializerAdapterFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;

public class Static {
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapterFactory(new GsonSerializerAdapterFactory())
            .create();

    public static void init(Object obj, Context context) {
        Iterator<Field> iterator = Arrays.stream(obj.getClass().getFields()).iterator();
        while (iterator.hasNext()) {
            Field field = iterator.next();
            if (field.isAnnotationPresent(StaticInstance.class)) {
                Object lang = null;
                try {
                    Type type = field.getGenericType();
                    switch (type.getTypeName()) {
                        case "com.google.gson.Gson": {
                            lang = GSON;
                            break;
                        }
                        case "java.util.logging.Logger": {
                            lang = context.getLogger();
                            break;
                        }
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
