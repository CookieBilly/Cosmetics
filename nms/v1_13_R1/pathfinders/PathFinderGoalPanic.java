

package ws.billy.CookieGadgets.nms.v1_13_R1.pathfinders;

import net.minecraft.server.v1_13_R1.EntityLiving;
import net.minecraft.server.v1_13_R1.Vec3D;
import net.minecraft.server.v1_13_R1.RandomPositionGenerator;
import net.minecraft.server.v1_13_R1.EntityCreature;
import net.minecraft.server.v1_13_R1.PathfinderGoal;

public class PathFinderGoalPanic extends PathfinderGoal
{
    private EntityCreature b;
    protected double a;
    public double c;
    public double d;
    public double e;
    
    public PathFinderGoalPanic(final EntityCreature b, final double a) {
        this.b = b;
        this.a = a;
        this.a(1);
    }
    
    public boolean a() {
        final Vec3D a = RandomPositionGenerator.a(this.b, 5, 4);
        if (a == null) {
            return false;
        }
        this.c = a.x;
        this.d = a.y;
        this.e = a.z;
        return true;
    }
    
    public void c() {
        final Vec3D a = RandomPositionGenerator.a(this.b, 5, 4);
        if (a == null) {
            return;
        }
        this.b.getNavigation().a(a.x, a.y, a.z, 1.5);
    }
    
    public boolean b() {
        if (this.b.ticksLived - this.b.hurtTimestamp > 100) {
            this.b.b((EntityLiving)null);
            return false;
        }
        return !this.b.getNavigation().p();
    }
}
