

package ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R2.World;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.DataWatcher;
import net.minecraft.server.v1_16_R2.DataWatcherRegistry;
import net.minecraft.server.v1_16_R2.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySlimePet;
import ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 0.6f)
public class EntitySlimePet extends EntityPet implements IEntitySlimePet
{
    private static final DataWatcherObject<Integer> SIZE;
    private static int jumpDelay;
    
    static {
        SIZE = DataWatcher.a((Class)EntitySlimePet.class, DataWatcherRegistry.b);
    }
    
    public EntitySlimePet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntitySlimePet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
        EntitySlimePet.jumpDelay = this.random.nextInt(40) + 20;
    }
    
    public EntitySlimePet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.SLIME, world);
    }
    
    public EntitySlimePet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.SLIME, world, pet);
        EntitySlimePet.jumpDelay = this.random.nextInt(40) + 20;
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntitySlimePet.SIZE, (Object)2);
    }
    
    public int getSize() {
        return (int)this.datawatcher.get((DataWatcherObject)EntitySlimePet.SIZE);
    }
    
    public void setSize(final int i) {
        this.datawatcher.set((DataWatcherObject)EntitySlimePet.SIZE, (Object)i);
    }
    
    public boolean isSmall() {
        return this.getSize() == 0 | this.getSize() == 1;
    }
    
    @Override
    public void repeatTask() {
        super.repeatTask();
        if (this.onGround && EntitySlimePet.jumpDelay-- <= 0 && this.passengers.size() == 0 && !this.getEntity().isInsideVehicle()) {
            EntitySlimePet.jumpDelay = this.random.nextInt(40) + 20;
            this.getControllerJump().jump();
            final SoundEffect ambientSound = this.getPet().getType().getEntityPet().getAmbientSound();
            if (ambientSound != null) {
                ambientSound.playSound(this.getEntity());
            }
        }
    }
}
