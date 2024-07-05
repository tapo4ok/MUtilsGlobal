package mdk.mutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mdk.mutils.brigadier.Message;
import mdk.mutils.json.GsonSerializerAdapterFactory;
import mdk.mutils.json.IdentifierAdapter;
import mdk.mutils.json.TextAdapter;

public class Static {
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Message.class, new TextAdapter())
            .registerTypeAdapter(Identifier.class, new IdentifierAdapter())
            .registerTypeAdapterFactory(new GsonSerializerAdapterFactory())
            .create();
}
