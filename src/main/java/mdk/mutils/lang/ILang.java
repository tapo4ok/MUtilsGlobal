package mdk.mutils.lang;


import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

public interface ILang extends Map<String, String> {
    String format(String str, Object... obs);
    ILang load(Reader reader);
    ILang load(String code);
    ILang load(File file);
    ILang load(DataInputStream stream);
    ILang load(InputStream stream);
}
