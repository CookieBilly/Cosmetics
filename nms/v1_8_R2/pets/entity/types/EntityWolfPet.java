

package ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types;

import net.minecraft.server.v1_8_R2.EnumParticle;
import net.minecraft.server.v1_8_R2.MathHelper;
import ws.billy.CookieGadgets.utils.GDyeColor;
import net.minecraft.server.v1_8_R2.EnumColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWolfPet;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.EntityTameablePet;

@EntitySize(width = 0.6f, height = 0.8f)
public class EntityWolfPet extends EntityTameablePet implements IEntityWolfPet
{
    private boolean wet;
    private boolean shaking;
    private float shakeCount;
    
    public EntityWolfPet(final World world) {
        super(world);
    }
    
    public EntityWolfPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(18, (Object)this.getHealth());
        this.datawatcher.a(19, (Object)0);
        this.datawatcher.a(20, (Object)(byte)EnumColor.RED.getColorIndex());
    }
    
    @Override
    public void setTamed(final boolean tamed) {
        if (this.isAngry() && tamed) {
            this.setAngry(false);
        }
        super.setTamed(tamed);
    }
    
    @Override
    public boolean isAngry() {
        return (this.datawatcher.getByte(16) & 0x2) != 0x0;
    }
    
    @Override
    public void setAngry(final boolean b) {
        if (this.isTamed() && b) {
            this.setTamed(false);
        }
        final byte byte1 = this.datawatcher.getByte(16);
        if (b) {
            this.datawatcher.watch(16, (Object)(byte)(byte1 | 0x2));
        }
        else {
            this.datawatcher.watch(16, (Object)(byte)(byte1 & 0xFFFFFFFD));
        }
    }
    
    @Override
    public GDyeColor getColor() {
        return GDyeColor.getByDyeData(this.getDataWatcher().getByte(20));
    }
    
    @Override
    public void setColor(final GDyeColor gDyeColor) {
        if (this.isTamed()) {
            this.datawatcher.watch(20, (Object)gDyeColor.getDyeData());
        }
    }
    
    @Override
    public void repeatTask() {
        super.repeatTask();
        if (this.inWater) {
            this.wet = true;
            this.shaking = false;
            this.shakeCount = 0.0f;
        }
        else if ((this.wet || this.shaking) && this.shaking) {
            this.shakeCount += 0.05f;
            if (this.shakeCount - 0.05f >= 2.0f) {
                this.wet = false;
                this.shaking = false;
                this.shakeCount = 0.0f;
            }
            if (this.shakeCount > 0.4f) {
                final float n = (float)this.getBoundingBox().b;
                for (int n2 = (int)(MathHelper.sin((this.shakeCount - 0.4f) * 3.1415927f) * 7.0f), i = 0; i < n2; ++i) {
                    this.world.addParticle(EnumParticle.WATER_SPLASH, this.locX + (this.random.nextFloat() * 2.0f - 1.0f) * this.width * 0.5f, (double)(n + 0.8f), this.locZ + (this.random.nextFloat() * 2.0f - 1.0f) * this.width * 0.5f, this.motX, this.motY, this.motZ, new int[0]);
                }
            }
        }
    }
}
