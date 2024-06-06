package mdk.mutils.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Map;

public class ItemStackTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!ItemStack.class.isAssignableFrom(type.getRawType())) {
            return null;
        }

        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                elementAdapter.write(out, gson.toJsonTree(((ItemStack) value).serialize()).getAsJsonObject());
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonObject jsonObject = elementAdapter.read(in).getAsJsonObject();
                return (T) ItemStack.deserialize(gson.fromJson(jsonObject, new TypeToken<Map<String, Object>>() {}.getType()));
            }
        };
    }
}
