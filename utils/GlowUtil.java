

package ws.billy.CookieGadgets.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class GlowUtil
{
    public static ItemStack addGlow(final ItemStack itemStack) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, false);
        itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    @Deprecated
    public static ItemStack addGlow(final ItemStack itemStack, final boolean b) {
        try {
            final Class<?> class1 = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ItemStack");
            final Class<?> class2 = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagCompound");
            final Class<?> class3 = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagList");
            final Class<?> class4 = ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack");
            final Class<?> class5 = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTBase");
            final Method declaredMethod = class4.getDeclaredMethod("asNMSCopy", ItemStack.class);
            final Method declaredMethod2 = class4.getDeclaredMethod("asCraftMirror", class1);
            final Method declaredMethod3 = class1.getDeclaredMethod("hasTag", (Class<?>[])new Class[0]);
            final Method declaredMethod4 = class1.getDeclaredMethod("setTag", class2);
            final Method declaredMethod5 = class1.getDeclaredMethod("getTag", (Class<?>[])new Class[0]);
            final Method declaredMethod6 = class2.getDeclaredMethod("set", String.class, class5);
            declaredMethod.setAccessible(true);
            declaredMethod2.setAccessible(true);
            declaredMethod3.setAccessible(true);
            declaredMethod4.setAccessible(true);
            declaredMethod5.setAccessible(true);
            declaredMethod6.setAccessible(true);
            final Constructor<?> constructor = class2.getConstructor((Class<?>[])new Class[0]);
            final Constructor<?> constructor2 = class3.getConstructor((Class<?>[])new Class[0]);
            constructor.setAccessible(true);
            constructor2.setAccessible(true);
            final Object invoke = declaredMethod.invoke(null, itemStack);
            Object obj;
            if (declaredMethod3.invoke(invoke, new Object[0])) {
                obj = declaredMethod5.invoke(invoke, new Object[0]);
            }
            else {
                obj = constructor.newInstance(new Object[0]);
            }
            if (b) {
                declaredMethod6.invoke(obj, "ench", constructor2.newInstance(new Object[0]));
            }
            declaredMethod4.invoke(invoke, obj);
            return (ItemStack)declaredMethod2.invoke(null, invoke);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return itemStack;
        }
    }
}
