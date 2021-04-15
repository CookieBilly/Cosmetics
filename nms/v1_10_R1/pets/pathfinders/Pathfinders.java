

package ws.billy.CookieGadgets.nms.v1_10_R1.pets.pathfinders;

import java.lang.reflect.Field;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import net.minecraft.server.v1_10_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_10_R1.PathfinderGoal;
import net.minecraft.server.v1_10_R1.EntityInsentient;
import net.minecraft.server.v1_10_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_10_R1.EntityHuman;
import com.google.common.collect.Sets;
import net.minecraft.server.v1_10_R1.PathfinderGoalSelector;
import ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity.EntityPet;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Pathfinders
{
    public static void handlePathfinders(final Player player, final Entity entity, final double n) {
        final net.minecraft.server.v1_10_R1.Entity handle = ((CraftEntity)entity).getHandle();
        try {
            if (handle instanceof EntityPet) {
                final EntityPet entityPet = (EntityPet)handle;
                final Field declaredField = PathfinderGoalSelector.class.getDeclaredField("b");
                declaredField.setAccessible(true);
                final Field declaredField2 = PathfinderGoalSelector.class.getDeclaredField("c");
                declaredField2.setAccessible(true);
                declaredField.set(entityPet.goalSelector, Sets.newLinkedHashSet());
                declaredField.set(entityPet.targetSelector, Sets.newLinkedHashSet());
                declaredField2.set(entityPet.goalSelector, Sets.newLinkedHashSet());
                declaredField2.set(entityPet.targetSelector, Sets.newLinkedHashSet());
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
        final net.minecraft.server.v1_10_R1.Entity handle = ((CraftEntity)entity).getHandle();
        try {
            if (handle instanceof EntityInsentient) {
                final EntityInsentient entityInsentient = (EntityInsentient)handle;
                final Field declaredField = PathfinderGoalSelector.class.getDeclaredField("b");
                declaredField.setAccessible(true);
                final Field declaredField2 = PathfinderGoalSelector.class.getDeclaredField("c");
                declaredField2.setAccessible(true);
                declaredField.set(entityInsentient.goalSelector, Sets.newLinkedHashSet());
                declaredField.set(entityInsentient.targetSelector, Sets.newLinkedHashSet());
                declaredField2.set(entityInsentient.goalSelector, Sets.newLinkedHashSet());
                declaredField2.set(entityInsentient.targetSelector, Sets.newLinkedHashSet());
                entityInsentient.goalSelector.a(0, (PathfinderGoal)new PathfinderGoalFloat(entityInsentient));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
