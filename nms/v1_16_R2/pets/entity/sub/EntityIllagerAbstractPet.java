

package ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.sub;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R2.World;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.DataWatcher;
import net.minecraft.server.v1_16_R2.DataWatcherRegistry;
import net.minecraft.server.v1_16_R2.DataWatcherObject;
import ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.EntityPet;

public abstract class EntityIllagerAbstractPet extends EntityPet
{
    protected static final DataWatcherObject<Byte> a;
    
    static {
        a = DataWatcher.a((Class)EntityIllagerAbstractPet.class, DataWatcherRegistry.a);
    }
    
    public EntityIllagerAbstractPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntityIllagerAbstractPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityIllagerAbstractPet.a, (Object)0);
    }
    
    protected void a(final int n, final boolean b) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntityIllagerAbstractPet.a);
        byte b2;
        if (b) {
            b2 = (byte)(byteValue | n);
        }
        else {
            b2 = (byte)(byteValue & ~n);
        }
        this.datawatcher.set((DataWatcherObject)EntityIllagerAbstractPet.a, (Object)(byte)(b2 & 0xFF));
    }
}
