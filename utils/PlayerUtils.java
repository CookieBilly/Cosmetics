// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.utils;

import org.bukkit.World;
import org.bukkit.util.Vector;
import org.bukkit.entity.LivingEntity;
import java.util.Iterator;
import org.bukkit.entity.Entity;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import java.util.List;
import org.bukkit.Location;

public class PlayerUtils
{
    public static List<Player> getNearbyPlayers(final Location location, final double n) {
        final ArrayList<Player> list = new ArrayList<Player>();
        for (final Entity entity : location.getWorld().getEntities()) {
            if (entity instanceof Player && entity.getLocation().distance(location) <= n) {
                list.add((Player)entity);
            }
        }
        return list;
    }
    
    public static Player getNearestPlayer(final Player player, final List<Player> list) {
        Player player2 = null;
        for (final Player player3 : list) {
            if ((player2 == null || player3.getLocation().distance(player.getLocation()) < player2.getLocation().distance(player.getLocation())) && player3 != player) {
                player2 = player3;
            }
        }
        return player2;
    }
    
    public static boolean isPlayerLookingAtEntity(final Player player, final LivingEntity livingEntity) {
        final Location eyeLocation = player.getEyeLocation();
        return livingEntity.getEyeLocation().toVector().subtract(eyeLocation.toVector()).normalize().dot(eyeLocation.getDirection()) > 0.99;
    }
    
    public static Entity getEntityPlayerLookingAt(final Player player, final int n) {
        if (player == null) {
            return null;
        }
        Entity entity = null;
        final double n2 = 1.0;
        for (final Entity entity2 : player.getNearbyEntities((double)n, (double)n, (double)n)) {
            final Vector subtract = entity2.getLocation().toVector().subtract(player.getLocation().toVector());
            if (player.getLocation().getDirection().normalize().crossProduct(subtract).lengthSquared() < n2 && subtract.normalize().dot(player.getLocation().getDirection().normalize()) >= 0.0 && (entity == null || entity.getLocation().distanceSquared(player.getLocation()) > entity2.getLocation().distanceSquared(player.getLocation()))) {
                entity = entity2;
            }
        }
        return entity;
    }
    
    public static List<Entity> getEntitiesPlayerLookingAt(final Player player, final int n) {
        final ArrayList<LivingEntity> list = (ArrayList<LivingEntity>)new ArrayList<Entity>();
        for (final Entity entity : player.getNearbyEntities((double)n, (double)n, (double)n)) {
            if (entity instanceof LivingEntity && isPlayerLookingAtEntity(player, (LivingEntity)entity)) {
                list.add(entity);
            }
        }
        return (List<Entity>)list;
    }
    
    public static List<Player> getPlayers(final World world) {
        final ArrayList<Player> list = new ArrayList<Player>();
        for (final Entity entity : world.getEntities()) {
            if (entity instanceof Player) {
                list.add((Player)entity);
            }
        }
        return list;
    }
}
