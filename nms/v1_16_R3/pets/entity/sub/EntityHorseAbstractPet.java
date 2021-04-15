

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.sub;

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
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IHorseAbstractPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.EntityAgeablePet;

public abstract class EntityHorseAbstractPet extends EntityAgeablePet implements IHorseAbstractPet
{
    protected static final DataWatcherObject<Byte> VISUAL;
    private static final DataWatcherObject<Optional<UUID>> OWNER;
    
    static {
        VISUAL = DataWatcher.a((Class)EntityHorseAbstractPet.class, (DataWatcherSerializer)DataWatcherWrapper.BYTE);
        OWNER = DataWatcher.a((Class)EntityHorseAbstractPet.class, (DataWatcherSerializer)DataWatcherWrapper.UUID);
    }
    
    public EntityHorseAbstractPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntityHorseAbstractPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityHorseAbstractPet.VISUAL, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityHorseAbstractPet.OWNER, (Object)Optional.empty());
    }
    
    @Override
    public boolean isCollidable() {
        return false;
    }
    
    @Override
    public boolean hasSaddle() {
        return this.getHorseVisual(4);
    }
    
    @Override
    public void setSaddle(final boolean b) {
        this.setHorseVisual(4, b);
    }
    
    public boolean getHorseVisual(final int n) {
        return ((byte)this.datawatcher.get((DataWatcherObject)EntityHorseAbstractPet.VISUAL) & n) != 0x0;
    }
    
    public void setHorseVisual(final int n, final boolean b) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntityHorseAbstractPet.VISUAL);
        if (b) {
            this.datawatcher.set((DataWatcherObject)EntityHorseAbstractPet.VISUAL, (Object)(byte)(byteValue | n));
        }
        else {
            this.datawatcher.set((DataWatcherObject)EntityHorseAbstractPet.VISUAL, (Object)(byte)(byteValue & ~n));
        }
    }
}
