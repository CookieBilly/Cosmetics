

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.DataWatcherSerializer;
import net.minecraft.server.v1_16_R3.DataWatcher;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils.DataWatcherWrapper;
import java.util.UUID;
import java.util.Optional;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.ITameable;

public class EntityTameablePet extends EntityAgeablePet implements ITameable
{
    protected static final DataWatcherObject<Byte> TAMED;
    protected static final DataWatcherObject<Optional<UUID>> OWNER;
    
    static {
        TAMED = DataWatcher.a((Class)EntityTameablePet.class, (DataWatcherSerializer)DataWatcherWrapper.BYTE);
        OWNER = DataWatcher.a((Class)EntityTameablePet.class, (DataWatcherSerializer)DataWatcherWrapper.UUID);
    }
    
    public EntityTameablePet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntityTameablePet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityTameablePet.TAMED, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityTameablePet.OWNER, (Object)Optional.empty());
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
        return ((Optional)this.datawatcher.get((DataWatcherObject)EntityTameablePet.OWNER)).orElse(null);
    }
    
    public void setOwnerUUID(final UUID value) {
        this.datawatcher.set((DataWatcherObject)EntityTameablePet.OWNER, (Object)Optional.ofNullable(value));
    }
}
