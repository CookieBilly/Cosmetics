

package ws.billy.CookieGadgets.nms.v1_16_R2.pets.pathfinders;

import net.minecraft.server.v1_16_R2.EntityLiving;
import net.minecraft.server.v1_16_R2.WorldServer;
import net.minecraft.server.v1_16_R2.Vec3D;
import net.minecraft.server.v1_16_R2.RandomPositionGenerator;
import java.util.EnumSet;
import net.minecraft.server.v1_16_R2.EntityCreature;
import net.minecraft.server.v1_16_R2.PathfinderGoal;

public class PathfinderGoalPanic extends PathfinderGoal
{
    private EntityCreature creature;
    protected double speed;
    public double c;
    public double d;
    public double e;
    
    public PathfinderGoalPanic(final EntityCreature creature, final double speed) {
        this.creature = creature;
        this.speed = speed;
        this.a((EnumSet)EnumSet.of(PathfinderGoal.Type.MOVE));
    }
    
    public boolean a() {
        final Vec3D a = RandomPositionGenerator.a(this.creature, 5, 4);
        if (a == null) {
            return false;
        }
        this.c = a.x;
        this.d = a.y;
        this.e = a.z;
        return true;
    }
    
    public void c() {
        final Vec3D a = RandomPositionGenerator.a(this.creature, 5, 4);
        if (a == null) {
            return;
        }
        this.creature.getNavigation().a(a.x, a.y, a.z, this.speed);
    }
    
    public boolean b() {
        if (this.creature.ticksLived - this.creature.hurtTimestamp > 100) {
            this.creature.a((WorldServer)this.creature.getWorld(), (EntityLiving)null);
            return false;
        }
        return !this.creature.getNavigation().m();
    }
}
