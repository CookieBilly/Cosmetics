

package ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.World;
import net.minecraft.server.v1_16_R2.DataWatcher;
import net.minecraft.server.v1_16_R2.DataWatcherRegistry;
import net.minecraft.server.v1_16_R2.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityBlazePet;
import ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 1.7f)
public class EntityBlazePet extends EntityPet implements IEntityBlazePet
{
    private static final DataWatcherObject<Byte> BURNING;
    
    static {
        BURNING = DataWatcher.a((Class)EntityBlazePet.class, DataWatcherRegistry.a);
    }
    
    public EntityBlazePet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.BLAZE, world);
    }
    
    public EntityBlazePet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.BLAZE, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityBlazePet.BURNING, (Object)0);
    }
    
    @Override
    public boolean isBurning() {
        return ((byte)this.datawatcher.get((DataWatcherObject)EntityBlazePet.BURNING) & 0x1) != 0x0;
    }
    
    @Override
    public void setBurning(final boolean b) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntityBlazePet.BURNING);
        byte b2;
        if (b) {
            b2 = (byte)(byteValue | 0x1);
        }
        else {
            b2 = (byte)(byteValue & 0xFFFFFFFE);
        }
        this.datawatcher.set((DataWatcherObject)EntityBlazePet.BURNING, (Object)b2);
    }
}
