

package ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.GDyeColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_10_R1.World;
import net.minecraft.server.v1_10_R1.DataWatcher;
import net.minecraft.server.v1_10_R1.DataWatcherRegistry;
import net.minecraft.server.v1_10_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySheepPet;
import ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.9f, height = 1.3f)
public class EntitySheepPet extends EntityAgeablePet implements IEntitySheepPet
{
    private static final DataWatcherObject<Byte> COLOR_SHEARED;
    private boolean sheared;
    private boolean rainbow;
    private int tick;
    
    static {
        COLOR_SHEARED = DataWatcher.a((Class)EntitySheepPet.class, DataWatcherRegistry.a);
    }
    
    public EntitySheepPet(final World world) {
        super(world);
        this.sheared = false;
        this.rainbow = false;
        this.tick = 0;
    }
    
    public EntitySheepPet(final World world, final IPet pet) {
        super(world, pet);
        this.sheared = false;
        this.rainbow = false;
        this.tick = 0;
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntitySheepPet.COLOR_SHEARED, (Object)0);
        this.setSheared(false);
    }
    
    @Override
    public GDyeColor getColor() {
        return GDyeColor.getByWoolData((byte)this.datawatcher.get((DataWatcherObject)EntitySheepPet.COLOR_SHEARED));
    }
    
    @Override
    public void setColor(final GDyeColor gDyeColor) {
        this.datawatcher.set((DataWatcherObject)EntitySheepPet.COLOR_SHEARED, (Object)gDyeColor.getWoolData());
    }
    
    @Override
    public boolean isSheared() {
        return this.sheared;
    }
    
    @Override
    public void setSheared(final boolean sheared) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntitySheepPet.COLOR_SHEARED);
        if (sheared) {
            this.datawatcher.set((DataWatcherObject)EntitySheepPet.COLOR_SHEARED, (Object)(byte)(byteValue | 0x10));
        }
        else {
            this.datawatcher.set((DataWatcherObject)EntitySheepPet.COLOR_SHEARED, (Object)(byte)(byteValue & 0xFFFFFFEF));
        }
        this.sheared = sheared;
    }
    
    @Override
    public boolean isRainbow() {
        return this.rainbow;
    }
    
    @Override
    public void setRainbow(final boolean rainbow) {
        this.rainbow = rainbow;
    }
    
    @Override
    public void repeatTask() {
        super.repeatTask();
        if (this.rainbow) {
            if (this.tick == 4) {
                this.setColor(GDyeColor.getNextColor(this.getColor()));
                this.tick = 0;
            }
            ++this.tick;
        }
    }
}
