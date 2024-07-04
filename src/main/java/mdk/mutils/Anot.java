package mdk.mutils;

import mdk.mutils.annotations.Chat;
import mdk.mutils.chat.AbstractChat;
import mdk.mutils.chat.ChatFormater;
import mdk.mutils.chat.IChat;
import mdk.mutils.config.SimpleConfig;
import mdk.mutils.lang.LangUtls;
import mdk.mutils.registry.Registries;
import org.bukkit.permissions.Permission;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;

public class Anot {
    public static void init(Object obj) {
        init(obj, Context.newWith(obj));
    }
    public static void init(Object obj, Context context) {
        SimpleConfig.init(obj, context);
        LangUtls.init(obj, context);
        chat_annotation(obj, context);
    }


    public static void chat_annotation(Object obj, Context context) {
        Iterator<Field> iterator = Arrays.stream(obj.getClass().getFields()).iterator();
        while (iterator.hasNext()) {
            Field field = iterator.next();
            if (field.isAnnotationPresent(Chat.class)) {
                Chat instance = field.getAnnotation(Chat.class);
                try {
                    Identifier identifier = new Identifier(instance.id().value());
                    IChat chat;
                    if (Registries.CHAT_REGISTRY.containsId(identifier)) {
                        chat = Registries.CHAT_REGISTRY.get(identifier);
                    }
                    else {
                        chat = new AbstractChat(new Permission(instance.permision()), ChatFormater.DEFULD, identifier) {};
                    }

                    if (!Modifier.isStatic(Modifier.fieldModifiers())) {
                        field.set(obj, chat);
                    }
                    else {
                        field.set(null, chat);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
