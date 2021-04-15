

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.types;

import net.minecraft.server.v1_16_R3.Vec3D;
import net.minecraft.server.v1_16_R3.ParticleParam;
import net.minecraft.server.v1_16_R3.MathHelper;
import net.minecraft.server.v1_16_R3.Particles;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GPandaGene;
import net.minecraft.server.v1_16_R3.SoundEffects;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.DataWatcherSerializer;
import net.minecraft.server.v1_16_R3.DataWatcher;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils.DataWatcherWrapper;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityPandaPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.EntityAgeablePet;

@EntitySize(width = 1.3f, height = 1.25f)
public class EntityPandaPet extends EntityAgeablePet implements IEntityPandaPet
{
    private static final DataWatcherObject<Integer> ASK_FOR_BAMBOO_TICKS;
    private static final DataWatcherObject<Integer> SNEEZE_PROGRESS;
    private static final DataWatcherObject<Integer> EATING_TICKS;
    private static final DataWatcherObject<Byte> MAIN_GENE;
    private static final DataWatcherObject<Byte> HIDDEN_GENE;
    private static final DataWatcherObject<Byte> PANDA_FLAGS;
    
    static {
        ASK_FOR_BAMBOO_TICKS = DataWatcher.a((Class)EntityPandaPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
        SNEEZE_PROGRESS = DataWatcher.a((Class)EntityPandaPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
        EATING_TICKS = DataWatcher.a((Class)EntityPandaPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
        MAIN_GENE = DataWatcher.a((Class)EntityPandaPet.class, (DataWatcherSerializer)DataWatcherWrapper.BYTE);
        HIDDEN_GENE = DataWatcher.a((Class)EntityPandaPet.class, (DataWatcherSerializer)DataWatcherWrapper.BYTE);
        PANDA_FLAGS = DataWatcher.a((Class)EntityPandaPet.class, (DataWatcherSerializer)DataWatcherWrapper.BYTE);
    }
    
    public EntityPandaPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.PANDA, world);
    }
    
    public EntityPandaPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.PANDA, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityPandaPet.ASK_FOR_BAMBOO_TICKS, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityPandaPet.SNEEZE_PROGRESS, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityPandaPet.MAIN_GENE, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityPandaPet.HIDDEN_GENE, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityPandaPet.PANDA_FLAGS, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityPandaPet.EATING_TICKS, (Object)0);
    }
    
    @Override
    public void repeatTask() {
        super.repeatTask();
        if (this.isSneezing()) {
            final int sneezeProgress = this.getSneezeProgress();
            this.setSneezeProgress(sneezeProgress + 1);
            if (sneezeProgress > 20) {
                this.setSneezing(false);
                this.handleSneeze();
            }
            else if (sneezeProgress == 1) {
                this.playSound(SoundEffects.ENTITY_PANDA_PRE_SNEEZE, 1.0f, 1.0f);
            }
        }
    }
    
    @Override
    public void setSneezing(final boolean b) {
        this.setFlag(2, b);
        if (!b) {
            this.setSneezeProgress(0);
        }
    }
    
    public int getSneezeProgress() {
        return (int)this.datawatcher.get((DataWatcherObject)EntityPandaPet.SNEEZE_PROGRESS);
    }
    
    public void setSneezeProgress(final int i) {
        this.datawatcher.set((DataWatcherObject)EntityPandaPet.SNEEZE_PROGRESS, (Object)i);
    }
    
    @Override
    public boolean isSitting() {
        return (int)this.datawatcher.get((DataWatcherObject)EntityPandaPet.EATING_TICKS) > 0;
    }
    
    @Override
    public void setSitting(final boolean i) {
        this.datawatcher.set((DataWatcherObject)EntityPandaPet.EATING_TICKS, (Object)(int)(i ? 1 : 0));
    }
    
    @Override
    public GPandaGene getGene() {
        return GPandaGene.valueOfById((byte)this.datawatcher.get((DataWatcherObject)EntityPandaPet.MAIN_GENE));
    }
    
    @Override
    public void setGene(final GPandaGene gPandaGene) {
        this.datawatcher.set((DataWatcherObject)EntityPandaPet.MAIN_GENE, (Object)(byte)gPandaGene.ordinal());
        final GPandaGene valueOfById = GPandaGene.valueOfById((byte)this.datawatcher.get((DataWatcherObject)EntityPandaPet.HIDDEN_GENE));
        if (gPandaGene.isRecessive()) {
            this.datawatcher.set((DataWatcherObject)EntityPandaPet.HIDDEN_GENE, (Object)(byte)gPandaGene.getId());
        }
        else if (valueOfById != GPandaGene.NORMAL) {
            this.datawatcher.set((DataWatcherObject)EntityPandaPet.HIDDEN_GENE, (Object)0);
        }
    }
    
    @Override
    public boolean getFlag(final int n) {
        return ((byte)this.datawatcher.get((DataWatcherObject)EntityPandaPet.PANDA_FLAGS) & n) != 0x0;
    }
    
    @Override
    public void setFlag(final int n, final boolean b) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntityPandaPet.PANDA_FLAGS);
        if (b) {
            this.datawatcher.set((DataWatcherObject)EntityPandaPet.PANDA_FLAGS, (Object)(byte)(byteValue | n));
        }
        else {
            this.datawatcher.set((DataWatcherObject)EntityPandaPet.PANDA_FLAGS, (Object)(byte)(byteValue & ~n));
        }
    }
    
    private void handleSneeze() {
        final Vec3D mot = this.getMot();
        this.world.addParticle((ParticleParam)Particles.SNEEZE, this.locX() - (this.getWidth() + 1.0f) * 0.5 * MathHelper.sin(this.aK * 0.017453292f), this.locY() + this.getHeadHeight() - 0.10000000149011612, this.locZ() + (this.getWidth() + 1.0f) * 0.5 * MathHelper.cos(this.aK * 0.017453292f), mot.x, 0.0, mot.z);
        this.playSound(SoundEffects.ENTITY_PANDA_SNEEZE, 1.0f, 1.0f);
        this.world.a((Class)EntityPandaPet.class, this.getBoundingBox().g(10.0)).forEach(entityPandaPet -> {
            if (!entityPandaPet.isBaby() && entityPandaPet.onGround && !entityPandaPet.isInWater() && entityPandaPet.isSpookedBySneeze()) {
                entityPandaPet.jump();
            }
        });
    }
    
    private boolean isSpookedBySneeze() {
        return !this.isLyingOnBack() && this.getGene() != GPandaGene.WORRIED && !this.isSitting() && !this.isPlaying() && !this.isScared();
    }
}
