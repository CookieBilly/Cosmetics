

package ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.ITameable;

public class EntityTameablePet extends EntityAgeablePet implements ITameable
{
    public EntityTameablePet(final World world) {
        super(world);
    }
    
    public EntityTameablePet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(16, (Object)0);
        this.datawatcher.a(17, (Object)"");
    }
    
    @Override
    public boolean isTamed() {
        return (this.datawatcher.getByte(16) & 0x4) != 0x0;
    }
    
    @Override
    public void setTamed(final boolean b) {
        final byte byte1 = this.datawatcher.getByte(16);
        if (b) {
            this.datawatcher.watch(16, (Object)(byte)(byte1 | 0x4));
        }
        else {
            this.datawatcher.watch(16, (Object)(byte)(byte1 & 0xFFFFFFFB));
        }
    }
    
    @Override
    public boolean isSitting() {
        return (this.datawatcher.getByte(16) & 0x1) != 0x0;
    }
    
    @Override
    public void setSitting(final boolean b) {
        final byte byte1 = this.datawatcher.getByte(16);
        if (b) {
            this.datawatcher.watch(16, (Object)(byte)(byte1 | 0x1));
        }
        else {
            this.datawatcher.watch(16, (Object)(byte)(byte1 & 0xFFFFFFFE));
        }
    }
}
