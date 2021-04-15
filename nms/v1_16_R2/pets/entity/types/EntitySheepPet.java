

package ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.World;
import net.minecraft.server.v1_16_R2.DataWatcher;
import net.minecraft.server.v1_16_R2.DataWatcherRegistry;
import ws.billy.CookieGadgets.utils.GDyeColor;
import net.minecraft.server.v1_16_R2.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySheepPet;
import ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.9f, height = 1.3f)
public class EntitySheepPet extends EntityAgeablePet implements IEntitySheepPet
{
    private static final DataWatcherObject<Byte> COLOR_SHEARED;
    private boolean rainbow;
    private GDyeColor color;
    private int tick;
    
    static {
        COLOR_SHEARED = DataWatcher.a((Class)EntitySheepPet.class, DataWatcherRegistry.a);
    }
    
    public EntitySheepPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.SHEEP, world);
        this.rainbow = false;
        this.color = GDyeColor.WHITE;
        this.tick = 0;
    }
    
    public EntitySheepPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.SHEEP, world, pet);
        this.rainbow = false;
        this.color = GDyeColor.WHITE;
        this.tick = 0;
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntitySheepPet.COLOR_SHEARED, (Object)0);
        this.setSheared(false);
    }
    
    @Override
    public GDyeColor getColor() {
        this.setSheared(false);
        return this.color;
    }
    
    @Override
    public void setColor(final GDyeColor color) {
        this.color = color;
        this.setSheared(false);
        this.datawatcher.set((DataWatcherObject)EntitySheepPet.COLOR_SHEARED, (Object)color.getWoolData());
    }
    
    @Override
    public boolean isSheared() {
        return ((byte)this.datawatcher.get((DataWatcherObject)EntitySheepPet.COLOR_SHEARED) & 0x10) != 0x0;
    }
    
    @Override
    public void setSheared(final boolean b) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntitySheepPet.COLOR_SHEARED);
        if (b) {
            this.datawatcher.set((DataWatcherObject)EntitySheepPet.COLOR_SHEARED, (Object)(byte)(byteValue | 0x10));
        }
        else {
            this.datawatcher.set((DataWatcherObject)EntitySheepPet.COLOR_SHEARED, (Object)(byte)(byteValue & 0xFFFFFFEF));
        }
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
