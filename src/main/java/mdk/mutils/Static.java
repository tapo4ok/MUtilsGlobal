package mdk.mutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mdk.mutils.json.GsonSerializerAdapterFactory;
import mdk.mutils.json.IdentifierAdapter;

public class Static {
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Identifier.class, new IdentifierAdapter())
            .registerTypeAdapterFactory(new GsonSerializerAdapterFactory())
            .create();
}
