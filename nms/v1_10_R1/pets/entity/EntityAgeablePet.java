

package ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_10_R1.World;
import net.minecraft.server.v1_10_R1.DataWatcher;
import net.minecraft.server.v1_10_R1.DataWatcherRegistry;
import net.minecraft.server.v1_10_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IAgeablePet;

public abstract class EntityAgeablePet extends EntityPet implements IAgeablePet
{
    private static final DataWatcherObject<Boolean> BABY;
    protected int age;
    private boolean ageLocked;
    
    static {
        BABY = DataWatcher.a((Class)EntityAgeablePet.class, DataWatcherRegistry.h);
    }
    
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
        this.datawatcher.register((DataWatcherObject)EntityAgeablePet.BABY, (Object)false);
    }
    
    @Override
    public boolean isBaby() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityAgeablePet.BABY);
    }
    
    @Override
    public void setBaby(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityAgeablePet.BABY, (Object)b);
        this.resetEntitySize(b);
    }
    
    public int getAge() {
        return this.datawatcher.get((DataWatcherObject)EntityAgeablePet.BABY) ? -1 : this.age;
    }
    
    public void setAge(final int n) {
        this.setAge(n, false);
    }
    
    public void setAge(final int n, final boolean b) {
        int ageRaw = this.getAge() + n * 20;
        if (ageRaw > 0) {
            ageRaw = 0;
        }
        this.setAgeRaw(ageRaw);
    }
    
    public void setAgeRaw(final int age) {
        this.datawatcher.set((DataWatcherObject)EntityAgeablePet.BABY, (Object)(age < 0));
        this.age = age;
    }
    
    public boolean isAgeLocked() {
        return this.ageLocked;
    }
    
    public void setAgeLocked(final boolean ageLocked) {
        this.ageLocked = ageLocked;
    }
    
    public void U() {
        super.U();
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
