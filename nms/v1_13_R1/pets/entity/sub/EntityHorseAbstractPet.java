

package ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.sub;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R1.World;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.DataWatcher;
import net.minecraft.server.v1_13_R1.DataWatcherRegistry;
import java.util.UUID;
import java.util.Optional;
import net.minecraft.server.v1_13_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IHorseAbstractPet;
import ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.EntityAgeablePet;

public abstract class EntityHorseAbstractPet extends EntityAgeablePet implements IHorseAbstractPet
{
    private static final DataWatcherObject<Byte> VISUAL;
    private static final DataWatcherObject<Optional<UUID>> OWNER;
    protected float jumpPower;
    protected boolean bA;
    
    static {
        VISUAL = DataWatcher.a((Class)EntityHorseAbstractPet.class, DataWatcherRegistry.a);
        OWNER = DataWatcher.a((Class)EntityHorseAbstractPet.class, DataWatcherRegistry.o);
    }
    
    public EntityHorseAbstractPet(final EntityTypes<?> entityTypes, final World world) {
        super(entityTypes, world);
        this.jumpPower = 0.0f;
    }
    
    public EntityHorseAbstractPet(final EntityTypes<?> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
        this.jumpPower = 0.0f;
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
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
