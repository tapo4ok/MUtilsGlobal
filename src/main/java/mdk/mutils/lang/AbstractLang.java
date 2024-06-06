package mdk.mutils.lang;

import java.io.*;
import java.util.HashMap;

public abstract class AbstractLang extends HashMap<String, String> implements ILang {
    public AbstractLang() {
        super();
    }
    @Override
    public String format(String str, Object... obs) {
        return String.format(get(str), obs);
    }

    @Override
    public ILang load(String code) {
        return load(new StringReader(code));
    }

    @Override
    public ILang load(File file) {
        try {
            return load(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
            return this;
        }
    }

    @Override
    public ILang load(DataInputStream stream) {
        return load(new InputStreamReader(stream));
    }
    @Override
    public ILang load(InputStream stream) {
        return load(new InputStreamReader(stream));
    }
}
