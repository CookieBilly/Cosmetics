

package ws.billy.CookieGadgets.utils;

import java.lang.reflect.InvocationTargetException;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import java.util.List;
import java.util.Iterator;
import org.bukkit.entity.Entity;
import java.util.ArrayList;
import org.bukkit.Location;

public class EntityUtils
{
    public static ArrayList<Entity> getNearbyEntities(final Location location, final double n) {
        final ArrayList<Entity> list = new ArrayList<Entity>();
        for (final Entity e : location.getWorld().getEntities()) {
            if (e.getLocation().distance(location) <= n) {
                list.add(e);
            }
        }
        return list;
    }
    
    public static List<LivingEntity> getNearbyLivingEntities(final Location location, final double n) {
        final ArrayList<LivingEntity> list = new ArrayList<LivingEntity>();
        for (final Entity entity : location.getWorld().getEntities()) {
            if (entity instanceof LivingEntity && !(entity instanceof ArmorStand) && entity.getLocation().distance(location) <= n) {
                list.add((LivingEntity)entity);
            }
        }
        return list;
    }
    
    public static List<LivingEntity> getNearbyArmorStands(final Location location, final double n) {
        final ArrayList<LivingEntity> list = new ArrayList<LivingEntity>();
        for (final Entity entity : location.getWorld().getEntities()) {
            if (entity instanceof ArmorStand && entity.getLocation().distance(location) <= n) {
                list.add((LivingEntity)entity);
            }
        }
        return list;
    }
    
    public static Object getEntityHandle(final Entity entity) {
        try {
            return ReflectionUtils.invokeMethod(entity, ReflectionUtils.getMethod(ReflectionUtils.PackageType.CRAFTBUKKIT.getClass("entity.CraftEntity"), "getHandle", (Class<?>[])new Class[0]).getName(), new Object[0]);
        }
        catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            final Throwable t;
            t.printStackTrace();
            return null;
        }
    }
}
