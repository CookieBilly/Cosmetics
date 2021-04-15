

package ws.billy.CookieGadgets.nms.v1_8_R1.pets.pathfinders;

import java.lang.reflect.Field;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import net.minecraft.server.v1_8_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R1.PathfinderGoal;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R1.EntityHuman;
import org.bukkit.craftbukkit.v1_8_R1.util.UnsafeList;
import net.minecraft.server.v1_8_R1.PathfinderGoalSelector;
import ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.EntityPet;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Pathfinders
{
    public static void handlePathfinders(final Player player, final Entity entity, final double n) {
        final net.minecraft.server.v1_8_R1.Entity handle = ((CraftEntity)entity).getHandle();
        try {
            if (handle instanceof EntityPet) {
                final EntityPet entityPet = (EntityPet)handle;
                final Field declaredField = PathfinderGoalSelector.class.getDeclaredField("b");
                declaredField.setAccessible(true);
                final Field declaredField2 = PathfinderGoalSelector.class.getDeclaredField("c");
                declaredField2.setAccessible(true);
                declaredField.set(entityPet.goalSelector, new UnsafeList());
                declaredField.set(entityPet.targetSelector, new UnsafeList());
                declaredField2.set(entityPet.goalSelector, new UnsafeList());
                declaredField2.set(entityPet.targetSelector, new UnsafeList());
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
        final net.minecraft.server.v1_8_R1.Entity handle = ((CraftEntity)entity).getHandle();
        try {
            if (handle instanceof EntityInsentient) {
                final EntityInsentient entityInsentient = (EntityInsentient)handle;
                final Field declaredField = PathfinderGoalSelector.class.getDeclaredField("b");
                declaredField.setAccessible(true);
                final Field declaredField2 = PathfinderGoalSelector.class.getDeclaredField("c");
                declaredField2.setAccessible(true);
                declaredField.set(entityInsentient.goalSelector, new UnsafeList());
                declaredField.set(entityInsentient.targetSelector, new UnsafeList());
                declaredField2.set(entityInsentient.goalSelector, new UnsafeList());
                declaredField2.set(entityInsentient.targetSelector, new UnsafeList());
                entityInsentient.goalSelector.a(0, (PathfinderGoal)new PathfinderGoalFloat(entityInsentient));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
