package mdk.mutils.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class GsonSerializerAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!GsonSerializer.class.isAssignableFrom(type.getRawType())) {
            return null;
        }

        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                elementAdapter.write(out, gson.toJsonTree(((GsonSerializer<T>) value).serialize()));
            }

            @Override
            public T read(JsonReader in) throws IOException {
                try {
                    Class<?> cls = type.getRawType();
                    GsonSerializer<T> obj = (GsonSerializer<T>) cls.newInstance();
                    return (T) obj.deserialize(elementAdapter.read(in));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
