

package ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.SoundEffect;
import net.minecraft.server.v1_8_R2.Entity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GRabbitType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityRabbitPet;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.6f, height = 0.7f)
public class EntityRabbitPet extends EntityAgeablePet implements IEntityRabbitPet
{
    private int jumpDelay;
    
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
        this.datawatcher.a(18, (Object)0);
    }
    
    @Override
    public GRabbitType getRabbitType() {
        return GRabbitType.getById(this.datawatcher.getByte(18));
    }
    
    @Override
    public void setRabbitType(final GRabbitType gRabbitType) {
        this.datawatcher.watch(18, (Object)(byte)gRabbitType.getId());
    }
    
    @Override
    public void repeatTask() {
        super.repeatTask();
        if (this.onGround && this.jumpDelay-- <= 0 && this.passenger == null && !this.getEntity().isInsideVehicle()) {
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
