package mdk.mutils.json;

import mdk.mutils.Static;
import org.bukkit.inventory.ItemStack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonDeserializationContext;


public class ItemStackAdapter implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {
    @Override
    public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
        return Static.GSON.toJsonTree(src.serialize());
    }

    @Override
    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Map<String, Object> map = Static.GSON.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
        return ItemStack.deserialize(map);
    }
}
