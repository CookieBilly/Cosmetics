

package ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.types;

import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.MathHelper;
import ws.billy.CookieGadgets.utils.GDyeColor;
import net.minecraft.server.v1_12_R1.EnumColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_12_R1.World;
import net.minecraft.server.v1_12_R1.DataWatcher;
import net.minecraft.server.v1_12_R1.DataWatcherRegistry;
import net.minecraft.server.v1_12_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWolfPet;
import ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.EntityTameablePet;

@EntitySize(width = 0.6f, height = 0.8f)
public class EntityWolfPet extends EntityTameablePet implements IEntityWolfPet
{
    private static final DataWatcherObject<Float> DATA_HEALTH;
    private static final DataWatcherObject<Boolean> bA;
    private static final DataWatcherObject<Integer> COLLAR_COLOR;
    private boolean wet;
    private boolean shaking;
    private float shakeCount;
    
    static {
        DATA_HEALTH = DataWatcher.a((Class)EntityWolfPet.class, DataWatcherRegistry.c);
        bA = DataWatcher.a((Class)EntityWolfPet.class, DataWatcherRegistry.h);
        COLLAR_COLOR = DataWatcher.a((Class)EntityWolfPet.class, DataWatcherRegistry.b);
    }
    
    public EntityWolfPet(final World world) {
        super(world);
    }
    
    public EntityWolfPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityWolfPet.DATA_HEALTH, (Object)this.getHealth());
        this.datawatcher.register((DataWatcherObject)EntityWolfPet.bA, (Object)false);
        this.datawatcher.register((DataWatcherObject)EntityWolfPet.COLLAR_COLOR, (Object)EnumColor.RED.getInvColorIndex());
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
        return ((byte)this.datawatcher.get((DataWatcherObject)EntityWolfPet.TAMED) & 0x2) != 0x0;
    }
    
    @Override
    public void setAngry(final boolean b) {
        if (this.isTamed() && b) {
            this.setTamed(false);
        }
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntityWolfPet.TAMED);
        if (b) {
            this.datawatcher.set((DataWatcherObject)EntityWolfPet.TAMED, (Object)(byte)(byteValue | 0x2));
        }
        else {
            this.datawatcher.set((DataWatcherObject)EntityWolfPet.TAMED, (Object)(byte)(byteValue & 0xFFFFFFFD));
        }
    }
    
    @Override
    public GDyeColor getColor() {
        return GDyeColor.getByDyeData((byte)(int)this.getDataWatcher().get((DataWatcherObject)EntityWolfPet.COLLAR_COLOR));
    }
    
    @Override
    public void setColor(final GDyeColor gDyeColor) {
        if (this.isTamed()) {
            this.datawatcher.set((DataWatcherObject)EntityWolfPet.COLLAR_COLOR, (Object)(int)gDyeColor.getDyeData());
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
