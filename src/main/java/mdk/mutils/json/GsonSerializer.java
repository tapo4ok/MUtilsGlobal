package mdk.mutils.json;

import com.google.gson.JsonElement;

public interface GsonSerializer<T> {
    T deserialize(JsonElement obj);
    JsonElement serialize();
}
