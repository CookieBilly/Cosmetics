

package ws.billy.CookieGadgets.utils;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Quality;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import java.util.Random;

public class MathUtil
{
    private static Random random;
    
    static {
        MathUtil.random = new Random();
    }
    
    public static Vector rotateAroundAxisX(final Vector vector, final double n) {
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        return vector.setY(vector.getY() * cos - vector.getZ() * sin).setZ(vector.getY() * sin + vector.getZ() * cos);
    }
    
    public static Vector rotateAroundAxisY(final Vector vector, final double n) {
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        return vector.setX(vector.getX() * cos + vector.getZ() * sin).setZ(vector.getX() * -sin + vector.getZ() * cos);
    }
    
    public static Vector rotateAroundAxisZ(final Vector vector, final double n) {
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        return vector.setX(vector.getX() * cos - vector.getY() * sin).setY(vector.getX() * sin + vector.getY() * cos);
    }
    
    public static Vector rotateVector(final Vector vector, final double n, final double n2, final double n3) {
        rotateAroundAxisX(vector, n);
        rotateAroundAxisY(vector, n2);
        rotateAroundAxisZ(vector, n3);
        return vector;
    }
    
    public static double angleToXAxis(final Vector vector) {
        return Math.atan2(vector.getX(), vector.getY());
    }
    
    public static Vector getRandomVector() {
        return new Vector(MathUtil.random.nextDouble() * 2.0 - 1.0, MathUtil.random.nextDouble() * 2.0 - 1.0, MathUtil.random.nextDouble() * 2.0 - 1.0).normalize();
    }
    
    public static Vector getBackVector(final Location location) {
        return new Vector((float)(location.getX() + 0.75 * Math.cos(Math.toRadians(location.getYaw() + 90.0f))) - location.getX(), 0.0, (float)(location.getZ() + 0.75 * Math.sin(Math.toRadians(location.getYaw() + 90.0f))) - location.getZ());
    }
    
    public static Vector rotateVectorYX(final Vector vector, final float n, final float n2) {
        final double radians = Math.toRadians(-1.0f * n);
        final double n3 = n2;
        final double cos = Math.cos(radians);
        final double cos2 = Math.cos(n3);
        final double sin = Math.sin(radians);
        final double sin2 = Math.sin(n3);
        final double y = vector.getY();
        final double z = vector.getZ();
        final double n4 = y * sin2 - z * cos2;
        final double n5 = y * cos2 + z * sin2;
        final double n6 = n4;
        final double x = vector.getX();
        return new Vector(n6 * sin + x * cos, n5, n6 * cos - x * sin);
    }
    
    public static void applyVelocity(final Entity entity, final Vector velocity) {
        if (entity.hasMetadata("NPC")) {
            return;
        }
        entity.setVelocity(velocity);
        if (entity instanceof Player) {
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    CookieGadgets.getPlayerManager((Player)entity).disableFallDamage();
                }
            }, 4L);
        }
    }
    
    public static void applyVelocity(final Entity entity, final Vector velocity, final boolean b) {
        if (entity.hasMetadata("NPC")) {
            return;
        }
        entity.setVelocity(velocity);
        if (entity instanceof Player && !b) {
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    CookieGadgets.getPlayerManager((Player)entity).disableFallDamage();
                }
            }, 4L);
        }
    }
    
    public static Vector getRandomCircleVector() {
        final double n = MathUtil.random.nextDouble() * 2.0 * 3.141592653589793;
        return new Vector(Math.cos(n), 0.0, Math.sin(n));
    }
    
    public static double randomDouble(final double n, final double n2) {
        return (Math.random() < 0.5) ? ((1.0 - Math.random()) * (n2 - n) + n) : (Math.random() * (n2 - n) + n);
    }
    
    public static float randomFloat(final float n, final float n2) {
        return (float)((Math.random() < 0.5) ? ((1.0 - Math.random()) * (n2 - n) + n) : (Math.random() * (n2 - n) + n));
    }
    
    public static int randomInt(final int n, final int n2) {
        return (int)((Math.random() < 0.5) ? ((1.0 - Math.random()) * (n2 - n + 1) + n) : (Math.random() * (n2 - n + 1) + n));
    }
    
    public static void main(final String[] array) {
        System.out.println(((Quality)randomChance(new Object[] { Quality.ONE_STAR, Quality.TWO_STAR, Quality.THREE_STAR, Quality.FOUR_STAR, Quality.FIVE_STAR }, new int[] { 15, 40, 30, 8, 7 })).getName());
    }
    
    public static Object randomChance(final Object[] array, final int[] array2) {
        if (array.length != array2.length) {
            return null;
        }
        final ArrayList<Object> list = new ArrayList<Object>();
        int n = 0;
        for (final Object e : array) {
            for (int j = 0; j < array2[n]; ++j) {
                list.add(e);
            }
            ++n;
        }
        Collections.shuffle(list);
        if (list.size() == 0) {
            return null;
        }
        return list.get(MathUtil.random.nextInt(list.size()));
    }
    
    public static List<Integer> splitNumber(final int n, final int n2) {
        if (n <= 0 || n2 <= 0) {
            return null;
        }
        int n3 = n;
        final ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            int n4 = n3;
            if (n4 > n2) {
                n4 = n2;
            }
            final int randomInt = randomInt(0, n4);
            if (randomInt != 0) {
                list.add(randomInt);
                n3 -= randomInt;
                if (n3 <= 0) {
                    break;
                }
            }
        }
        return list;
    }
    
    public static double offset(final Entity entity, final Entity entity2) {
        return offset(entity.getLocation().toVector(), entity2.getLocation().toVector());
    }
    
    public static double offset(final Location location, final Location location2) {
        return offset(location.toVector(), location2.toVector());
    }
    
    public static double offset(final Vector vector, final Vector vector2) {
        return vector.subtract(vector2).length();
    }
    
    public static double square(final double n) {
        return n * n;
    }
    
    public static int floor(final double n) {
        final int n2 = (int)n;
        return (n2 == n) ? n2 : (n2 - (int)(Double.doubleToRawLongBits(n) >>> 63));
    }
}
