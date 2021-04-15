

package ws.billy.CookieGadgets.nms.v1_13_R2;

import net.minecraft.server.v1_13_R2.EnumDirection;
import net.minecraft.server.v1_13_R2.BlockPosition;
import net.minecraft.server.v1_13_R2.MovingObjectPosition;
import net.minecraft.server.v1_13_R2.Vec3D;
import net.minecraft.server.v1_13_R2.AxisAlignedBB;

public class NullBoundingBox extends AxisAlignedBB
{
    public NullBoundingBox() {
        super(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }
    
    public double a() {
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
    
    public double a(final EnumDirection.EnumAxis enumAxis) {
        return 0.0;
    }
    
    public MovingObjectPosition a(final Vec3D vec3D, final Vec3D vec3D2, final BlockPosition blockPosition) {
        return null;
    }
    
    public double b(final EnumDirection.EnumAxis enumAxis) {
        return 0.0;
    }
    
    public boolean e(final double n, final double n2, final double n3) {
        return false;
    }
    
    public AxisAlignedBB f(final double n, final double n2, final double n3) {
        return this;
    }
}
