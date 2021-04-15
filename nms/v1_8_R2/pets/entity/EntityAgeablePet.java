

package ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IAgeablePet;

public abstract class EntityAgeablePet extends EntityPet implements IAgeablePet
{
    private boolean ageLocked;
    
    public EntityAgeablePet(final World world) {
        super(world);
        this.ageLocked = true;
    }
    
    public EntityAgeablePet(final World world, final IPet pet) {
        super(world, pet);
        this.ageLocked = true;
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(12, (Object)0);
    }
    
    @Override
    public boolean isBaby() {
        return this.datawatcher.getByte(12) < 0;
    }
    
    @Override
    public void setBaby(final boolean b) {
        if (b) {
            this.datawatcher.watch(12, (Object)(-1));
        }
        else {
            this.datawatcher.watch(12, (Object)0);
        }
        this.resetEntitySize(b);
    }
    
    public int getAge() {
        return this.datawatcher.getByte(12);
    }
    
    public void setAge(final int n) {
        this.datawatcher.watch(12, (Object)(byte)((n < 0) ? -1 : ((n >= 6000) ? 1 : 0)));
    }
    
    public boolean isAgeLocked() {
        return this.ageLocked;
    }
    
    public void setAgeLocked(final boolean ageLocked) {
        this.ageLocked = ageLocked;
    }
    
    public void m() {
        super.m();
        if (!this.world.isClientSide && !this.ageLocked) {
            int age = this.getAge();
            if (age < 0) {
                ++age;
                this.setAge(age);
            }
            else if (age > 0) {
                --age;
                this.setAge(age);
            }
        }
    }
}
