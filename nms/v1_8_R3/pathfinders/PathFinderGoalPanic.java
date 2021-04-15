

package ws.billy.CookieGadgets.nms.v1_8_R3.pathfinders;

import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.Vec3D;
import net.minecraft.server.v1_8_R3.RandomPositionGenerator;
import net.minecraft.server.v1_8_R3.EntityCreature;
import net.minecraft.server.v1_8_R3.PathfinderGoal;

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
        this.c = a.a;
        this.d = a.b;
        this.e = a.c;
        return true;
    }
    
    public void c() {
        final Vec3D a = RandomPositionGenerator.a(this.b, 5, 4);
        if (a == null) {
            return;
        }
        this.b.getNavigation().a(a.a, a.b, a.c, 1.5);
    }
    
    public boolean b() {
        if (this.b.ticksLived - this.b.hurtTimestamp > 100) {
            this.b.b((EntityLiving)null);
            return false;
        }
        return !this.b.getNavigation().m();
    }
}
