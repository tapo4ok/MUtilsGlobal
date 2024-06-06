package mdk.mutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mdk.mutils.json.GsonSerializerAdapterFactory;

public class Static {
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapterFactory(new GsonSerializerAdapterFactory())
            .create();
}
