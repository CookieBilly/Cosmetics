

package ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.World;
import net.minecraft.server.v1_13_R1.DataWatcher;
import net.minecraft.server.v1_13_R1.DataWatcherRegistry;
import net.minecraft.server.v1_13_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySlimePet;
import ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 0.6f)
public class EntitySlimePet extends EntityPet implements IEntitySlimePet
{
    private static final DataWatcherObject<Integer> SIZE;
    private static int jumpDelay;
    
    static {
        SIZE = DataWatcher.a((Class)EntitySlimePet.class, DataWatcherRegistry.b);
    }
    
    public EntitySlimePet(final World world) {
        super((EntityTypes<?>)EntityTypes.SLIME, world);
    }
    
    public EntitySlimePet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.SLIME, world, pet);
        EntitySlimePet.jumpDelay = this.random.nextInt(4) + 4;
    }
    
    public EntitySlimePet(final EntityTypes<?> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntitySlimePet(final EntityTypes<?> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
        EntitySlimePet.jumpDelay = this.random.nextInt(4) + 4;
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
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
            EntitySlimePet.jumpDelay = this.random.nextInt(4) + 4;
            this.getControllerJump().a();
            final SoundEffect ambientSound = this.getPet().getType().getEntityPet().getAmbientSound();
            if (ambientSound != null) {
                ambientSound.playSound(this.getEntity());
            }
        }
    }
}
