

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.DataWatcherSerializer;
import net.minecraft.server.v1_16_R3.DataWatcher;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils.DataWatcherWrapper;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IAgeablePet;

public abstract class EntityAgeablePet extends EntityPet implements IAgeablePet
{
    private static final DataWatcherObject<Boolean> BABY;
    protected int age;
    private boolean ageLocked;
    
    static {
        BABY = DataWatcher.a((Class)EntityAgeablePet.class, (DataWatcherSerializer)DataWatcherWrapper.BOOLEAN);
    }
    
    public EntityAgeablePet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super(entityTypes, world);
        this.ageLocked = true;
    }
    
    public EntityAgeablePet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
        this.ageLocked = true;
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
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
    
    public void inactiveTick() {
        super.inactiveTick();
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
