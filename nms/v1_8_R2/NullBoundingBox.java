

package ws.billy.CookieGadgets.nms.v1_8_R2;

import net.minecraft.server.v1_8_R2.MovingObjectPosition;
import net.minecraft.server.v1_8_R2.Vec3D;
import net.minecraft.server.v1_8_R2.AxisAlignedBB;

public class NullBoundingBox extends AxisAlignedBB
{
    public NullBoundingBox() {
        super(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }
    
    public double a() {
        return 0.0;
    }
    
    public double a(final AxisAlignedBB axisAlignedBB, final double n) {
        return 0.0;
    }
    
    public AxisAlignedBB a(final AxisAlignedBB axisAlignedBB) {
        return this;
    }
    
    public AxisAlignedBB a(final double n, final double n2, final double n3) {
        return this;
    }
    
    public MovingObjectPosition a(final Vec3D vec3D, final Vec3D vec3D2) {
        return super.a(vec3D, vec3D2);
    }
    
    public boolean a(final Vec3D vec3D) {
        return false;
    }
    
    public double b(final AxisAlignedBB axisAlignedBB, final double n) {
        return 0.0;
    }
    
    public boolean b(final AxisAlignedBB axisAlignedBB) {
        return false;
    }
    
    public double c(final AxisAlignedBB axisAlignedBB, final double n) {
        return 0.0;
    }
    
    public AxisAlignedBB c(final double n, final double n2, final double n3) {
        return this;
    }
    
    public AxisAlignedBB grow(final double n, final double n2, final double n3) {
        return this;
    }
    
    public AxisAlignedBB shrink(final double n, final double n2, final double n3) {
        return this;
    }
}
