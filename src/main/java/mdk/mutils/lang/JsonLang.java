package mdk.mutils.lang;

import com.google.gson.JsonObject;
import mdk.mutils.Static;

import java.io.Reader;
public class JsonLang extends AbstractLang {
    public JsonLang() {
        super();
    }
    @Override
    public ILang load(Reader reader) {
        JsonObject object = Static.GSON.fromJson(reader, JsonObject.class);
        for (String id : object.keySet()) {
            put(id, object.get(id).getAsString());
        }
        return this;
    }
}
