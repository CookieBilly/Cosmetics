

package ws.billy.CookieGadgets.nms.v1_9_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.SoundEffect;
import net.minecraft.server.v1_9_R1.Entity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GRabbitType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_9_R1.World;
import net.minecraft.server.v1_9_R1.DataWatcher;
import net.minecraft.server.v1_9_R1.DataWatcherRegistry;
import net.minecraft.server.v1_9_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityRabbitPet;
import ws.billy.CookieGadgets.nms.v1_9_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.6f, height = 0.7f)
public class EntityRabbitPet extends EntityAgeablePet implements IEntityRabbitPet
{
    private static final DataWatcherObject<Integer> TYPE;
    private int jumpDelay;
    
    static {
        TYPE = DataWatcher.a((Class)EntityRabbitPet.class, DataWatcherRegistry.b);
    }
    
    public EntityRabbitPet(final World world) {
        super(world);
    }
    
    public EntityRabbitPet(final World world, final IPet pet) {
        super(world, pet);
        this.jumpDelay = this.random.nextInt(15) + 10;
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityRabbitPet.TYPE, (Object)0);
    }
    
    @Override
    public GRabbitType getRabbitType() {
        return GRabbitType.getById((int)this.datawatcher.get((DataWatcherObject)EntityRabbitPet.TYPE));
    }
    
    @Override
    public void setRabbitType(final GRabbitType gRabbitType) {
        this.datawatcher.set((DataWatcherObject)EntityRabbitPet.TYPE, (Object)gRabbitType.getId());
    }
    
    @Override
    public void repeatTask() {
        super.repeatTask();
        if (this.onGround && this.jumpDelay-- <= 0 && this.passengers.size() == 0 && !this.getEntity().isInsideVehicle()) {
            this.jumpDelay = this.random.nextInt(15) + 10;
            this.getControllerJump().a();
            final SoundEffect ambientSound = this.getPet().getType().getEntityPet().getAmbientSound();
            if (ambientSound != null) {
                ambientSound.playSound(this.getEntity());
            }
            this.world.broadcastEntityEffect((Entity)this, (byte)1);
        }
    }
}
