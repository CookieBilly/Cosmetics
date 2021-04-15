// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.sub;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R1.World;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.DataWatcher;
import net.minecraft.server.v1_13_R1.DataWatcherRegistry;
import net.minecraft.server.v1_13_R1.DataWatcherObject;
import ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.EntityPet;

public abstract class EntityIllagerAbstractPet extends EntityPet
{
    protected static final DataWatcherObject<Byte> a;
    
    static {
        a = DataWatcher.a((Class)EntityIllagerAbstractPet.class, DataWatcherRegistry.a);
    }
    
    public EntityIllagerAbstractPet(final EntityTypes<?> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntityIllagerAbstractPet(final EntityTypes<?> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
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
