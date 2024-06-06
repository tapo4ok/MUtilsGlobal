package mdk.mutils;

import lombok.Getter;

import java.io.File;
import java.util.logging.Logger;

@Getter
public class Context {
    private final Logger logger;
    private final File datadir;
    private final Object obj;

    public Context(Logger logger, File data, Object obj) {
        this.datadir = data;
        this.logger = logger;
        this.obj = obj;
    }

    public ClassLoader getClassLoader() {
        return obj.getClass().getClassLoader();
    }

    public static Context newWith(Object obj) {
        Class<?> cls = obj.getClass();
        try {
            return new Context((Logger) cls.getMethod("getLogger").invoke(obj), (File) cls.getMethod("getDataFolder").invoke(obj), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
