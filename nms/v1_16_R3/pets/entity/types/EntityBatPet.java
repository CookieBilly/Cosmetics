

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.types;

import net.minecraft.server.v1_16_R3.MathHelper;
import net.minecraft.server.v1_16_R3.Vec3D;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.DataWatcherSerializer;
import net.minecraft.server.v1_16_R3.DataWatcher;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils.DataWatcherWrapper;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityBatPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.EntityPet;

@EntitySize(width = 0.5f, height = 0.9f)
public class EntityBatPet extends EntityPet implements IEntityBatPet
{
    private static final DataWatcherObject<Byte> HANGING;
    
    static {
        HANGING = DataWatcher.a((Class)EntityBatPet.class, (DataWatcherSerializer)DataWatcherWrapper.BYTE);
    }
    
    public EntityBatPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.BAT, world);
    }
    
    public EntityBatPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.BAT, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
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
            this.setMot(Vec3D.ORIGIN);
            this.setPositionRaw(this.locX(), MathHelper.floor(this.locY()) + 1.0 - this.getHeight(), this.locZ());
        }
        else {
            this.setMot(this.getMot().d(1.0, 0.6, 1.0));
        }
    }
}
