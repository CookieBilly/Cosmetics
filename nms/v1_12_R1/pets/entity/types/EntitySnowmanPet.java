

package ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_12_R1.World;
import net.minecraft.server.v1_12_R1.DataWatcher;
import net.minecraft.server.v1_12_R1.DataWatcherRegistry;
import net.minecraft.server.v1_12_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySnowmanPet;
import ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.EntityPet;

@EntitySize(width = 0.4f, height = 1.8f)
public class EntitySnowmanPet extends EntityPet implements IEntitySnowmanPet
{
    private static final DataWatcherObject<Byte> PUMPKIN;
    
    static {
        PUMPKIN = DataWatcher.a((Class)EntitySnowmanPet.class, DataWatcherRegistry.a);
    }
    
    public EntitySnowmanPet(final World world) {
        super(world);
    }
    
    public EntitySnowmanPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntitySnowmanPet.PUMPKIN, (Object)0);
    }
    
    @Override
    public boolean hasPumpkin() {
        return ((byte)this.datawatcher.get((DataWatcherObject)EntitySnowmanPet.PUMPKIN) & 0x10) != 0x0;
    }
    
    @Override
    public void setPumpkin(final boolean b) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntitySnowmanPet.PUMPKIN);
        if (b) {
            this.datawatcher.set((DataWatcherObject)EntitySnowmanPet.PUMPKIN, (Object)(byte)(byteValue | 0x10));
        }
        else {
            this.datawatcher.set((DataWatcherObject)EntitySnowmanPet.PUMPKIN, (Object)(byte)(byteValue & 0xFFFFFFEF));
        }
    }
}
