

package ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.GDyeColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySheepPet;
import ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.9f, height = 1.3f)
public class EntitySheepPet extends EntityAgeablePet implements IEntitySheepPet
{
    private boolean rainbow;
    private int tick;
    
    public EntitySheepPet(final World world) {
        super(world);
        this.rainbow = false;
        this.tick = 0;
    }
    
    public EntitySheepPet(final World world, final IPet pet) {
        super(world, pet);
        this.rainbow = false;
        this.tick = 0;
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(16, (Object)0);
        this.setSheared(false);
    }
    
    @Override
    public GDyeColor getColor() {
        return GDyeColor.getByWoolData((byte)(this.datawatcher.getByte(16) & 0xF));
    }
    
    @Override
    public void setColor(final GDyeColor gDyeColor) {
        this.datawatcher.watch(16, (Object)(byte)((this.datawatcher.getByte(16) & 0xF0) | (gDyeColor.getWoolData() & 0xF)));
    }
    
    @Override
    public boolean isSheared() {
        return (this.datawatcher.getByte(16) & 0x10) != 0x0;
    }
    
    @Override
    public void setSheared(final boolean b) {
        final byte byte1 = this.datawatcher.getByte(16);
        if (b) {
            this.datawatcher.watch(16, (Object)(byte)(byte1 | 0x10));
        }
        else {
            this.datawatcher.watch(16, (Object)(byte)(byte1 & 0xFFFFFFEF));
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
