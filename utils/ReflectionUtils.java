

package ws.billy.CookieGadgets.utils;

import org.bukkit.Bukkit;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.entity.Player;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public final class ReflectionUtils
{
    private ReflectionUtils() {
    }
    
    public static Constructor<?> getConstructor(final Class<?> clazz, final Class<?>... array) {
        final Class<?>[] primitive = DataType.getPrimitive(array);
        Constructor[] constructors;
        for (int length = (constructors = clazz.getConstructors()).length, i = 0; i < length; ++i) {
            final Constructor constructor = constructors[i];
            if (DataType.compare(DataType.getPrimitive(constructor.getParameterTypes()), primitive)) {
                return (Constructor<?>)constructor;
            }
        }
        throw new NoSuchMethodException("There is no such constructor in this class with the specified parameter types");
    }
    
    public static Constructor<?> getConstructor(final String s, final PackageType packageType, final Class<?>... array) {
        return getConstructor(packageType.getClass(s), array);
    }
    
    public static Object instantiateObject(final Class<?> clazz, final Object... initargs) {
        return getConstructor(clazz, DataType.getPrimitive(initargs)).newInstance(initargs);
    }
    
    public static Object instantiateObject(final String s, final PackageType packageType, final Object... array) {
        return instantiateObject(packageType.getClass(s), array);
    }
    
    public static Method getMethod(final Class<?> clazz, final String s, final Class<?>... array) {
        final Class<?>[] primitive = DataType.getPrimitive(array);
        Method[] methods;
        for (int length = (methods = clazz.getMethods()).length, i = 0; i < length; ++i) {
            final Method method = methods[i];
            if (method.getName().equals(s) && DataType.compare(DataType.getPrimitive(method.getParameterTypes()), primitive)) {
                return method;
            }
        }
        Method[] declaredMethods;
        for (int length2 = (declaredMethods = clazz.getDeclaredMethods()).length, j = 0; j < length2; ++j) {
            final Method method2 = declaredMethods[j];
            if (method2.getName().equals(s) && DataType.compare(DataType.getPrimitive(method2.getParameterTypes()), primitive)) {
                method2.setAccessible(true);
                return method2;
            }
        }
        throw new NoSuchMethodException("There is no such method in this class with the specified name and parameter types");
    }
    
    public static Method getMethod(final String s, final PackageType packageType, final String s2, final Class<?>... array) {
        return getMethod(packageType.getClass(s), s2, array);
    }
    
    public static Object invokeMethod(final Object obj, final String s, final Object... args) {
        return getMethod(obj.getClass(), s, DataType.getPrimitive(args)).invoke(obj, args);
    }
    
    public static Object invokeMethod(final Object obj, final Class<?> clazz, final String s, final Object... args) {
        return getMethod(clazz, s, DataType.getPrimitive(args)).invoke(obj, args);
    }
    
    public static Object invokeMethod(final Object o, final String s, final PackageType packageType, final String s2, final Object... array) {
        return invokeMethod(o, packageType.getClass(s), s2, array);
    }
    
    public static Field getField(final Class<?> clazz, final boolean b, final String s) {
        final Field field = b ? clazz.getDeclaredField(s) : clazz.getField(s);
        field.setAccessible(true);
        return field;
    }
    
    public static Field getField(final String s, final PackageType packageType, final boolean b, final String s2) {
        return getField(packageType.getClass(s), b, s2);
    }
    
    public static void setPrivateField(final Class<?> clazz, final Object obj, final String name, final Object value) {
        final Field declaredField = clazz.getDeclaredField(name);
        declaredField.setAccessible(true);
        declaredField.set(obj, value);
    }
    
    public static Object getValue(final Object obj, final Class<?> clazz, final boolean b, final String s) {
        return getField(clazz, b, s).get(obj);
    }
    
    public static Object getValue(final Object o, final String s, final PackageType packageType, final boolean b, final String s2) {
        return getValue(o, packageType.getClass(s), b, s2);
    }
    
    public static Object getValue(final Object o, final boolean b, final String s) {
        return getValue(o, o.getClass(), b, s);
    }
    
    public static void setValue(final Object obj, final Class<?> clazz, final boolean b, final String s, final Object value) {
        getField(clazz, b, s).set(obj, value);
    }
    
    public static void setValue(final Object o, final String s, final PackageType packageType, final boolean b, final String s2, final Object o2) {
        setValue(o, packageType.getClass(s), b, s2, o2);
    }
    
    public static void setValue(final Object o, final boolean b, final String s, final Object o2) {
        setValue(o, o.getClass(), b, s, o2);
    }
    
    public static Object getConnection(final Player obj) {
        final Object invoke = obj.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(obj, new Object[0]);
        return invoke.getClass().getField("playerConnection").get(invoke);
    }
    
    public static void sendPacket(final Player obj, final Object o) {
        try {
            final Object invoke = obj.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(obj, new Object[0]);
            final Object value = invoke.getClass().getField("playerConnection").get(invoke);
            value.getClass().getMethod("sendPacket", Class.forName(String.valueOf(PackageType.MINECRAFT_SERVER.getPath()) + ".Packet")).invoke(value, o);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void setData(final Object obj, final byte b) {
        if (!VersionManager.is1_13OrAbove()) {
            try {
                obj.getClass().getMethod("setData", Byte.TYPE).invoke(obj, b);
            }
            catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            catch (IllegalArgumentException ex2) {
                ex2.printStackTrace();
            }
            catch (InvocationTargetException ex3) {
                ex3.printStackTrace();
            }
            catch (NoSuchMethodException ex4) {
                ex4.printStackTrace();
            }
            catch (SecurityException ex5) {
                ex5.printStackTrace();
            }
        }
    }
    
    public enum DataType
    {
        BYTE((Class<?>)Byte.TYPE, (Class<?>)Byte.class), 
        SHORT((Class<?>)Short.TYPE, (Class<?>)Short.class), 
        INTEGER((Class<?>)Integer.TYPE, (Class<?>)Integer.class), 
        LONG((Class<?>)Long.TYPE, (Class<?>)Long.class), 
        CHARACTER((Class<?>)Character.TYPE, (Class<?>)Character.class), 
        FLOAT((Class<?>)Float.TYPE, (Class<?>)Float.class), 
        DOUBLE((Class<?>)Double.TYPE, (Class<?>)Double.class), 
        BOOLEAN((Class<?>)Boolean.TYPE, (Class<?>)Boolean.class);
        
        private static final Map<Class<?>, DataType> CLASS_MAP;
        private final Class<?> primitive;
        private final Class<?> reference;
        
        static {
            CLASS_MAP = new HashMap<Class<?>, DataType>();
            DataType[] values;
            for (int length = (values = values()).length, i = 0; i < length; ++i) {
                final DataType dataType = values[i];
                DataType.CLASS_MAP.put(dataType.primitive, dataType);
                DataType.CLASS_MAP.put(dataType.reference, dataType);
            }
        }
        
        private DataType(final Class<?> primitive, final Class<?> reference) {
            this.primitive = primitive;
            this.reference = reference;
        }
        
        public Class<?> getPrimitive() {
            return this.primitive;
        }
        
        public Class<?> getReference() {
            return this.reference;
        }
        
        public static DataType fromClass(final Class<?> clazz) {
            return DataType.CLASS_MAP.get(clazz);
        }
        
        public static Class<?> getPrimitive(final Class<?> clazz) {
            final DataType fromClass = fromClass(clazz);
            return (fromClass == null) ? clazz : fromClass.getPrimitive();
        }
        
        public static Class<?> getReference(final Class<?> clazz) {
            final DataType fromClass = fromClass(clazz);
            return (fromClass == null) ? clazz : fromClass.getReference();
        }
        
        public static Class<?>[] getPrimitive(final Class<?>[] array) {
            final int n = (array == null) ? 0 : array.length;
            final Class[] array2 = new Class[n];
            for (int i = 0; i < n; ++i) {
                array2[i] = getPrimitive(array[i]);
            }
            return (Class<?>[])array2;
        }
        
        public static Class<?>[] getReference(final Class<?>[] array) {
            final int n = (array == null) ? 0 : array.length;
            final Class[] array2 = new Class[n];
            for (int i = 0; i < n; ++i) {
                array2[i] = getReference(array[i]);
            }
            return (Class<?>[])array2;
        }
        
        public static Class<?>[] getPrimitive(final Object[] array) {
            final int n = (array == null) ? 0 : array.length;
            final Class[] array2 = new Class[n];
            for (int i = 0; i < n; ++i) {
                array2[i] = getPrimitive(array[i].getClass());
            }
            return (Class<?>[])array2;
        }
        
        public static Class<?>[] getReference(final Object[] array) {
            final int n = (array == null) ? 0 : array.length;
            final Class[] array2 = new Class[n];
            for (int i = 0; i < n; ++i) {
                array2[i] = getReference(array[i].getClass());
            }
            return (Class<?>[])array2;
        }
        
        public static boolean compare(final Class<?>[] array, final Class<?>[] array2) {
            if (array == null || array2 == null || array.length != array2.length) {
                return false;
            }
            for (int i = 0; i < array.length; ++i) {
                final Class<?> clazz = array[i];
                final Class<?> obj = array2[i];
                if (!clazz.equals(obj) && !clazz.isAssignableFrom(obj)) {
                    return false;
                }
            }
            return true;
        }
    }
    
    public enum PackageType
    {
        MINECRAFT_SERVER("MINECRAFT_SERVER", 0, "net.minecraft.server." + getServerVersion()), 
        CRAFTBUKKIT("CRAFTBUKKIT", 1, "org.bukkit.craftbukkit." + getServerVersion()), 
        CRAFTBUKKIT_BLOCK("CRAFTBUKKIT_BLOCK", 2, PackageType.CRAFTBUKKIT, "block"), 
        CRAFTBUKKIT_CHUNKIO("CRAFTBUKKIT_CHUNKIO", 3, PackageType.CRAFTBUKKIT, "chunkio"), 
        CRAFTBUKKIT_COMMAND("CRAFTBUKKIT_COMMAND", 4, PackageType.CRAFTBUKKIT, "command"), 
        CRAFTBUKKIT_CONVERSATIONS("CRAFTBUKKIT_CONVERSATIONS", 5, PackageType.CRAFTBUKKIT, "conversations"), 
        CRAFTBUKKIT_ENCHANTMENS("CRAFTBUKKIT_ENCHANTMENS", 6, PackageType.CRAFTBUKKIT, "enchantments"), 
        CRAFTBUKKIT_ENTITY("CRAFTBUKKIT_ENTITY", 7, PackageType.CRAFTBUKKIT, "entity"), 
        CRAFTBUKKIT_EVENT("CRAFTBUKKIT_EVENT", 8, PackageType.CRAFTBUKKIT, "event"), 
        CRAFTBUKKIT_GENERATOR("CRAFTBUKKIT_GENERATOR", 9, PackageType.CRAFTBUKKIT, "generator"), 
        CRAFTBUKKIT_HELP("CRAFTBUKKIT_HELP", 10, PackageType.CRAFTBUKKIT, "help"), 
        CRAFTBUKKIT_INVENTORY("CRAFTBUKKIT_INVENTORY", 11, PackageType.CRAFTBUKKIT, "inventory"), 
        CRAFTBUKKIT_MAP("CRAFTBUKKIT_MAP", 12, PackageType.CRAFTBUKKIT, "map"), 
        CRAFTBUKKIT_METADATA("CRAFTBUKKIT_METADATA", 13, PackageType.CRAFTBUKKIT, "metadata"), 
        CRAFTBUKKIT_POTION("CRAFTBUKKIT_POTION", 14, PackageType.CRAFTBUKKIT, "potion"), 
        CRAFTBUKKIT_PROJECTILES("CRAFTBUKKIT_PROJECTILES", 15, PackageType.CRAFTBUKKIT, "projectiles"), 
        CRAFTBUKKIT_SCHEDULER("CRAFTBUKKIT_SCHEDULER", 16, PackageType.CRAFTBUKKIT, "scheduler"), 
        CRAFTBUKKIT_SCOREBOARD("CRAFTBUKKIT_SCOREBOARD", 17, PackageType.CRAFTBUKKIT, "scoreboard"), 
        CRAFTBUKKIT_UPDATER("CRAFTBUKKIT_UPDATER", 18, PackageType.CRAFTBUKKIT, "updater"), 
        CRAFTBUKKIT_UTIL("CRAFTBUKKIT_UTIL", 19, PackageType.CRAFTBUKKIT, "util");
        
        private final String path;
        
        private PackageType(final String name, final int ordinal, final String path) {
            this.path = path;
        }
        
        private PackageType(final String s, final int n, final PackageType obj, final String str) {
            this(s, n, obj + "." + str);
        }
        
        public String getPath() {
            return this.path;
        }
        
        public Class<?> getClass(final String str) {
            return Class.forName(this + "." + str);
        }
        
        @Override
        public String toString() {
            return this.path;
        }
        
        public static String getServerVersion() {
            return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        }
    }
}
