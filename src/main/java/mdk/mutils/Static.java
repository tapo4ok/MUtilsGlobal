package mdk.mutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Static {
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();
}
