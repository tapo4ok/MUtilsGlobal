package mdk.mutils.json;

import com.google.gson.*;
import mdk.mutils.Identifier;

import java.lang.reflect.Type;

public class IdentifierAdapter implements JsonDeserializer<Identifier>, JsonSerializer<Identifier> {
    @Override
    public Identifier deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Identifier(json.getAsString());
    }

    @Override
    public JsonElement serialize(Identifier src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }
}
