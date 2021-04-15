

package ws.billy.CookieGadgets.nms.v1_14_R1;

import net.minecraft.server.v1_14_R1.EnumDirection;
import net.minecraft.server.v1_14_R1.Vec3D;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.AxisAlignedBB;

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
    
    public double b(final EnumDirection.EnumAxis enumAxis) {
        return 0.0;
    }
    
    public boolean e(final double n, final double n2, final double n3) {
        return false;
    }
    
    public double b() {
        return 0.0;
    }
    
    public double c() {
        return 0.0;
    }
    
    public double d() {
        return 0.0;
    }
    
    public boolean c(final Vec3D vec3D) {
        return false;
    }
    
    public Vec3D f() {
        return Vec3D.a;
    }
}
