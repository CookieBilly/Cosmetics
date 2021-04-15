

package ws.billy.CookieGadgets.nms.v1_15_R1.pets.pathfinders;

import java.lang.reflect.Field;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import net.minecraft.server.v1_15_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_15_R1.PathfinderGoal;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_15_R1.EntityHuman;
import java.util.Set;
import java.util.Map;
import net.minecraft.server.v1_15_R1.PathfinderGoalSelector;
import ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity.EntityPet;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Pathfinders
{
    public static void handlePathfinders(final Player player, final Entity entity, final double n) {
        final net.minecraft.server.v1_15_R1.Entity handle = ((CraftEntity)entity).getHandle();
        try {
            if (handle instanceof EntityPet) {
                final EntityPet entityPet = (EntityPet)handle;
                final Field declaredField = PathfinderGoalSelector.class.getDeclaredField("c");
                declaredField.setAccessible(true);
                final Field declaredField2 = PathfinderGoalSelector.class.getDeclaredField("d");
                declaredField2.setAccessible(true);
                ((Map)declaredField.get(entityPet.goalSelector)).clear();
                ((Map)declaredField.get(entityPet.targetSelector)).clear();
                ((Set)declaredField2.get(entityPet.goalSelector)).clear();
                ((Set)declaredField2.get(entityPet.targetSelector)).clear();
                entityPet.goalSelector.a(0, (PathfinderGoal)new PathfinderGoalLookAtPlayer((EntityInsentient)entityPet, (Class)EntityHuman.class, 6.0f));
                entityPet.goalSelector.a(1, (PathfinderGoal)new PathfinderGoalFloat((EntityInsentient)entityPet));
                entityPet.goalSelector.a(2, (PathfinderGoal)new PathFinderGoalFollowOwner(player, entityPet, n));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void clearPathfinders(final Entity entity) {
        final net.minecraft.server.v1_15_R1.Entity handle = ((CraftEntity)entity).getHandle();
        try {
            if (handle instanceof EntityInsentient) {
                final EntityInsentient entityInsentient = (EntityInsentient)handle;
                final Field declaredField = PathfinderGoalSelector.class.getDeclaredField("c");
                declaredField.setAccessible(true);
                final Field declaredField2 = PathfinderGoalSelector.class.getDeclaredField("d");
                declaredField2.setAccessible(true);
                ((Map)declaredField.get(entityInsentient.goalSelector)).clear();
                ((Map)declaredField.get(entityInsentient.targetSelector)).clear();
                ((Set)declaredField2.get(entityInsentient.goalSelector)).clear();
                ((Set)declaredField2.get(entityInsentient.targetSelector)).clear();
                entityInsentient.goalSelector.a(0, (PathfinderGoal)new PathfinderGoalFloat(entityInsentient));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
