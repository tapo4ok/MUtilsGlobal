package mdk.mutils.config;

import lombok.Getter;
import lombok.ToString;
import mdk.mutils.Context;
import mdk.mutils.Static;

import java.io.*;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@ToString
public class SimpleConfig<T> {
    private String fileName;
    private String packageName;
    private T config;
    private final Class<T> cls;
    private File configFile;

    public SimpleConfig(Class<T> cls, Context context) {
        this.cls = cls;
        Config fs = cls.getAnnotation(Config.class);
        String name;
        if (fs == null) return;
        if (fs.value().equalsIgnoreCase("")) name = cls.getSimpleName().toLowerCase();
        else name = fs.value();
        name = name.toLowerCase();
        this.fileName = name;

        Config.Package f2 = cls.getAnnotation(Config.Package.class);
        String packag;
        if (f2 == null) {
            packageName = null;
            func_0c(context);
            return;
        }
        if (f2.value().equalsIgnoreCase(""))
            packag = cls.getPackage().getName();
        else packag = fs.value();
        packag = packag
                .toLowerCase()
                .replace('.', '\\')
                .replace('\\', '/');
        this.packageName = packag;

        func_0c(context);
    }

    private void func_0c(Context context) {
        File dataFolder = context.getDatadir();
        dataFolder = dataFolder.getParentFile();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        File configDir = new File(dataFolder, "configs");
        File pkgDir;
        if (packageName != null) {
            pkgDir = new File(configDir, packageName);
        } else {
            pkgDir = new File(configDir.toURI());
        }

        if (!pkgDir.exists()) {
            pkgDir.mkdirs();
        }

        configFile = new File(pkgDir, fileName + ".json");
        if (!configFile.exists()) {
            try {
                Config fs = cls.getAnnotation(Config.class);
                String f = fs.FromResource();
                if (f.equalsIgnoreCase("false")) {
                    Save();
                } else {
                    saveResource(f, context.getLogger());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            Load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void Load() throws IOException {
        this.config = Static.GSON.fromJson(new FileReader(configFile), cls);
    }

    public void Save() throws IOException, java.lang.InstantiationException, java.lang.IllegalAccessException {
        if (config == null) {
            config = cls.newInstance();
        }

        FileWriter fileWriter = new FileWriter(configFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(Static.GSON.toJson(config));
        bufferedWriter.close();
    }

    public T getConfig() {
        return config;
    }

    public void saveResource(String resourcePath, Logger logger) {
        if (resourcePath == null || resourcePath.equals("")) {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }

        resourcePath = resourcePath.replace('\\', '/');
        InputStream in = getResource(resourcePath, cls);
        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found in " + configFile);
        }

        File outDir = configFile.getParentFile();

        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        try {
            if (!configFile.exists()) {
                OutputStream out = new FileOutputStream(configFile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
            } else {
                logger.log(Level.WARNING, "Could not save " + configFile.getName() + " to " + configFile + " because " + configFile.getName() + " already exists.");
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Could not save " + configFile.getName() + " to " + configFile, ex);
        }
    }

    public InputStream getResource(String filename, Class<T> cls) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        try {
            URL url = cls.getClassLoader().getResource(filename);

            if (url == null) {
                return null;
            }

            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException ex) {
            return null;
        }
    }

    public static void init(Object obj) {
        Class<?> cls = obj.getClass();
        try {
            init(obj, new Context((Logger) cls.getMethod("getLogger").invoke(obj), (File) cls.getMethod("getDataFolder").invoke(obj)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init(Object obj, Context context) {
        Iterator<Field> iterator = Arrays.stream(obj.getClass().getFields()).iterator();
        while (iterator.hasNext()) {
            Field field = iterator.next();
            if (field.isAnnotationPresent(Config.Instance.class)) {
                Config.Instance instance = field.getAnnotation(Config.Instance.class);
                try {
                    @SuppressWarnings("unchecked")
                    Class<?> c;
                    if (instance.value().equalsIgnoreCase("reflection")) {
                        Type genericFieldType = field.getGenericType();
                        if (genericFieldType instanceof ParameterizedType) {
                            c = (Class<?>) ((ParameterizedType) genericFieldType).getActualTypeArguments()[0];
                        }
                        else {
                            c = null;
                        }
                    }
                    else {
                        c = Class.forName(instance.value());
                    }

                    field.set(obj, SimpleConfig.class.getConstructor(Class.class, Context.class).newInstance(c, context));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}