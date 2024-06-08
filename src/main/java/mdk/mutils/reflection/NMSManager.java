package mdk.mutils.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Deprecated
public class NMSManager {
    private String version;
    private static final Logger logger = Logger.getLogger(NMSManager.class.getName());
    private static NMSManager Inc;
    private Map<String, Class<?>> cachedNMSClasses = new HashMap<>();
    private Map<String, Class<?>> cachedCraftClasses = new HashMap<>();
    public static NMSManager getInstance() {
        if (Inc == null) {
            Inc = new NMSManager();
            Inc.initialize();
        }
        return Inc;
    }
    private NMSManager() {

    }
    private void initialize() {
        try {
            this.version = getServerVersion();
            logger.log(Level.INFO, "Detected server version: " + version);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getServerVersion() throws ClassNotFoundException {
        String packageName = "org.bukkit.craftbukkit";
        Class<?> serverClass = Class.forName(packageName + ".CraftServer");
        String name = serverClass.getPackage().getName();
        return name.substring(name.lastIndexOf('.') + 1);
    }

    public Class<?> getNMSClass(String className) {
        if (cachedNMSClasses.containsKey(className)) {
            return cachedNMSClasses.get(className);
        }
        try {
            Class<?> clazz = Class.forName("net.minecraft.server." + version + "." + className);
            cachedNMSClasses.put(className, clazz);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Class<?> getCraftClass(String className) {
        if (cachedCraftClasses.containsKey(className)) {
            return cachedCraftClasses.get(className);
        }
        try {
            Class<?> clazz = Class.forName("org.bukkit.craftbukkit." + version + "." + className);
            cachedCraftClasses.put(className, clazz);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getHandle(Object craftObject) {
        return invokeMethod(craftObject, "getHandle");
    }

    public Object getField(Object instance, String fieldName) {
        try {
            Field field = instance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(instance);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setField(Object instance, String fieldName, Object value) {
        try {
            Field field = instance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object invokeMethod(Object instance, String methodName, Class<?>[] parameterTypes, Object... args) {
        try {
            Method method = instance.getClass().getMethod(methodName, parameterTypes);
            return method.invoke(instance, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object invokeMethod(Object instance, String methodName) {
        try {
            Method method = instance.getClass().getMethod(methodName);
            return method.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getNBTTagCompound(Object nmsEntity) {
        try {
            Method getNBTTagMethod = nmsEntity.getClass().getMethod("save", getNMSClass("NBTTagCompound"));
            return getNBTTagMethod.invoke(nmsEntity, getNMSClass("NBTTagCompound").newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setNBTTagCompound(Object nmsEntity, Object nbtTagCompound) {
        try {
            Method setNBTTagMethod = nmsEntity.getClass().getMethod("load", getNMSClass("NBTTagCompound"));
            setNBTTagMethod.invoke(nmsEntity, nbtTagCompound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerCommand(String commandName, Object commandInstance) {
        try {
            Class<?> minecraftServerClass = Class.forName("net.minecraft.server.MinecraftServer");
            Method getServerMethod = minecraftServerClass.getMethod("getServer");
            Object minecraftServer = getServerMethod.invoke(null);
            Field commandDispatcherField = minecraftServerClass.getDeclaredField("commandDispatcher");
            commandDispatcherField.setAccessible(true);
            Object commandDispatcher = commandDispatcherField.get(minecraftServer);
            Method getRootMethod = commandDispatcher.getClass().getMethod("a");
            Object rootCommandNode = getRootMethod.invoke(commandDispatcher);
            Object literalCommandNode = createLiteralCommandNode(commandName, commandInstance);
            Method registerMethod = rootCommandNode.getClass().getMethod("addChild", Class.forName("com.mojang.brigadier.tree.CommandNode"));
            registerMethod.invoke(rootCommandNode, literalCommandNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object createLiteralCommandNode(String commandName, Object commandInstance) {
        try {
            Class<?> literalArgumentBuilderClass = Class.forName("com.mojang.brigadier.builder.LiteralArgumentBuilder");
            Method literalMethod = literalArgumentBuilderClass.getMethod("literal", String.class);
            Object literalCommandBuilder = literalMethod.invoke(null, commandName);
            Method thenMethod = literalArgumentBuilderClass.getMethod("then", Class.forName("com.mojang.brigadier.builder.ArgumentBuilder"));
            thenMethod.invoke(literalCommandBuilder, commandInstance);
            return literalCommandBuilder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getServerInstance() {
        try {
            return getCraftClass("CraftServer").getMethod("getServer").invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}