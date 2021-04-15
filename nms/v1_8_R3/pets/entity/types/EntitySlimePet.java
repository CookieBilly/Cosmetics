

package ws.billy.CookieGadgets.nms.v1_8_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R3.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySlimePet;
import ws.billy.CookieGadgets.nms.v1_8_R3.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 0.6f)
public class EntitySlimePet extends EntityPet implements IEntitySlimePet
{
    private static int jumpDelay;
    
    public EntitySlimePet(final World world) {
        super(world);
    }
    
    public EntitySlimePet(final World world, final IPet pet) {
        super(world, pet);
        EntitySlimePet.jumpDelay = this.random.nextInt(4) + 4;
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(16, (Object)2);
    }
    
    public int getSize() {
        return this.datawatcher.getByte(16);
    }
    
    public void setSize(final int n) {
        this.datawatcher.watch(16, (Object)(byte)n);
    }
    
    public boolean isSmall() {
        return this.getSize() == 0 | this.getSize() == 1;
    }
    
    @Override
    public void repeatTask() {
        super.repeatTask();
        if (this.onGround && EntitySlimePet.jumpDelay-- <= 0 && this.passenger == null && !this.getEntity().isInsideVehicle()) {
            EntitySlimePet.jumpDelay = this.random.nextInt(4) + 4;
            this.getControllerJump().a();
            final SoundEffect ambientSound = this.getPet().getType().getEntityPet().getAmbientSound();
            if (ambientSound != null) {
                ambientSound.playSound(this.getEntity());
            }
        }
    }
}
