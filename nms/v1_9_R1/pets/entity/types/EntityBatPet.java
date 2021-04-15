

package ws.billy.CookieGadgets.nms.v1_9_R1.pets.entity.types;

import net.minecraft.server.v1_9_R1.MathHelper;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_9_R1.World;
import net.minecraft.server.v1_9_R1.DataWatcher;
import net.minecraft.server.v1_9_R1.DataWatcherRegistry;
import net.minecraft.server.v1_9_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IFlyablePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityBatPet;
import ws.billy.CookieGadgets.nms.v1_9_R1.pets.entity.EntityPet;

@EntitySize(width = 0.5f, height = 0.9f)
public class EntityBatPet extends EntityPet implements IEntityBatPet, IFlyablePet
{
    private static final DataWatcherObject<Byte> HANGING;
    
    static {
        HANGING = DataWatcher.a((Class)EntityBatPet.class, DataWatcherRegistry.a);
    }
    
    public EntityBatPet(final World world) {
        super(world);
    }
    
    public EntityBatPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityBatPet.HANGING, (Object)0);
    }
    
    @Override
    public boolean isHanging() {
        return ((byte)this.datawatcher.get((DataWatcherObject)EntityBatPet.HANGING) & 0x1) != 0x0;
    }
    
    @Override
    public void setHanging(final boolean b) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntityBatPet.HANGING);
        if (b) {
            this.datawatcher.set((DataWatcherObject)EntityBatPet.HANGING, (Object)(byte)(byteValue | 0x1));
        }
        else {
            this.datawatcher.set((DataWatcherObject)EntityBatPet.HANGING, (Object)(byte)(byteValue & 0xFFFFFFFE));
        }
    }
    
    @Override
    public void repeatTask() {
        super.repeatTask();
        if (this.isHanging()) {
            final double motX = 0.0;
            this.motZ = motX;
            this.motY = motX;
            this.motX = motX;
            this.locY = MathHelper.floor(this.locY) + 1.0 - this.length;
        }
        else {
            this.motY *= 0.6000004238418579;
        }
    }
}
