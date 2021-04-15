

package ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_10_R1.World;
import net.minecraft.server.v1_10_R1.DataWatcher;
import net.minecraft.server.v1_10_R1.DataWatcherRegistry;
import java.util.UUID;
import com.google.common.base.Optional;
import net.minecraft.server.v1_10_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.ITameable;

public class EntityTameablePet extends EntityAgeablePet implements ITameable
{
    protected static final DataWatcherObject<Byte> TAMED;
    protected static final DataWatcherObject<Optional<UUID>> OWNER;
    
    static {
        TAMED = DataWatcher.a((Class)EntityTameablePet.class, DataWatcherRegistry.a);
        OWNER = DataWatcher.a((Class)EntityTameablePet.class, DataWatcherRegistry.m);
    }
    
    public EntityTameablePet(final World world) {
        super(world);
    }
    
    public EntityTameablePet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityTameablePet.TAMED, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityTameablePet.OWNER, (Object)Optional.absent());
    }
    
    @Override
    public boolean isTamed() {
        return ((byte)this.datawatcher.get((DataWatcherObject)EntityTameablePet.TAMED) & 0x4) != 0x0;
    }
    
    @Override
    public void setTamed(final boolean b) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntityTameablePet.TAMED);
        if (b) {
            this.datawatcher.set((DataWatcherObject)EntityTameablePet.TAMED, (Object)(byte)(byteValue | 0x4));
        }
        else {
            this.datawatcher.set((DataWatcherObject)EntityTameablePet.TAMED, (Object)(byte)(byteValue & 0xFFFFFFFB));
        }
    }
    
    @Override
    public boolean isSitting() {
        return ((byte)this.datawatcher.get((DataWatcherObject)EntityTameablePet.TAMED) & 0x1) != 0x0;
    }
    
    @Override
    public void setSitting(final boolean b) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntityTameablePet.TAMED);
        if (b) {
            this.datawatcher.set((DataWatcherObject)EntityTameablePet.TAMED, (Object)(byte)(byteValue | 0x1));
        }
        else {
            this.datawatcher.set((DataWatcherObject)EntityTameablePet.TAMED, (Object)(byte)(byteValue & 0xFFFFFFFE));
        }
    }
    
    public UUID getOwnerUUID() {
        return (UUID)((Optional)this.datawatcher.get((DataWatcherObject)EntityTameablePet.OWNER)).orNull();
    }
    
    public void setOwnerUUID(final UUID uuid) {
        this.datawatcher.set((DataWatcherObject)EntityTameablePet.OWNER, (Object)Optional.fromNullable((Object)uuid));
    }
}
