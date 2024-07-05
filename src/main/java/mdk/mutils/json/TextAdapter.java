package mdk.mutils.json;

import com.google.gson.*;
import mdk.mutils.brigadier.Message;

import java.lang.reflect.Type;

public class TextAdapter implements JsonSerializer<Message> {
    @Override
    public JsonElement serialize(Message src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getString());
    }
}
