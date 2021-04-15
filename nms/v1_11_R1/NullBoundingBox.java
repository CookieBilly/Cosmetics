

package ws.billy.CookieGadgets.nms.v1_11_R1;

import net.minecraft.server.v1_11_R1.BlockPosition;
import net.minecraft.server.v1_11_R1.MovingObjectPosition;
import net.minecraft.server.v1_11_R1.Vec3D;
import net.minecraft.server.v1_11_R1.AxisAlignedBB;

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
    
    public MovingObjectPosition b(final Vec3D vec3D, final Vec3D vec3D2) {
        return null;
    }
    
    public double b(final AxisAlignedBB axisAlignedBB, final double n) {
        return 0.0;
    }
    
    public double c(final AxisAlignedBB axisAlignedBB, final double n) {
        return 0.0;
    }
    
    public AxisAlignedBB grow(final double n, final double n2, final double n3) {
        return this;
    }
    
    public AxisAlignedBB shrink(final double n) {
        return this;
    }
    
    public AxisAlignedBB a(final BlockPosition blockPosition) {
        return this;
    }
    
    public boolean a(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        return false;
    }
    
    public boolean b(final Vec3D vec3D) {
        return false;
    }
    
    public boolean c(final Vec3D vec3D) {
        return false;
    }
    
    public boolean d(final Vec3D vec3D) {
        return false;
    }
    
    public AxisAlignedBB e(final double n) {
        return this;
    }
    
    public AxisAlignedBB g(final double n) {
        return this;
    }
    
    public AxisAlignedBB a(final Vec3D vec3D) {
        return this;
    }
    
    public AxisAlignedBB b(final AxisAlignedBB axisAlignedBB) {
        return this;
    }
    
    public AxisAlignedBB b(final double n, final double n2, final double n3) {
        return this;
    }
    
    public boolean c(final AxisAlignedBB axisAlignedBB) {
        return false;
    }
    
    public AxisAlignedBB d(final double n, final double n2, final double n3) {
        return this;
    }
    
    public boolean e(final Vec3D vec3D) {
        return false;
    }
}
