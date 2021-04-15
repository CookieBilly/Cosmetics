

package ws.billy.CookieGadgets.nms.v1_14_R1.pets.entity.types;

import net.minecraft.server.v1_14_R1.ControllerJump;
import net.minecraft.server.v1_14_R1.Vec3D;
import net.minecraft.server.v1_14_R1.PathEntity;
import org.bukkit.Location;
import net.minecraft.server.v1_14_R1.Entity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GRabbitType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.World;
import net.minecraft.server.v1_14_R1.DataWatcher;
import net.minecraft.server.v1_14_R1.DataWatcherRegistry;
import net.minecraft.server.v1_14_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityRabbitPet;
import ws.billy.CookieGadgets.nms.v1_14_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.6f, height = 0.7f)
public class EntityRabbitPet extends EntityAgeablePet implements IEntityRabbitPet
{
    private static final DataWatcherObject<Integer> TYPE;
    private boolean onGroundLastTick;
    private int delay;
    
    static {
        TYPE = DataWatcher.a((Class)EntityRabbitPet.class, DataWatcherRegistry.b);
    }
    
    public EntityRabbitPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.RABBIT, world);
        this.onGroundLastTick = false;
        this.delay = 0;
    }
    
    public EntityRabbitPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.RABBIT, world, pet);
        this.onGroundLastTick = false;
        this.delay = 0;
        this.bt = new ControllerJumpRabbit(this);
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
        if (this.onGround) {
            if (!this.onGroundLastTick) {
                this.setJumping(false);
                this.reset();
            }
            final ControllerJumpRabbit controllerJumpRabbit = (ControllerJumpRabbit)this.bt;
            if (!controllerJumpRabbit.c()) {
                if (this.delay == 0) {
                    final Location location = this.getPet().getOwner().getLocation();
                    final PathEntity a = this.getNavigation().a(location.getX() + 2.0, location.getY(), location.getZ() + 2.0, 0);
                    if (a != null && a.f() < a.e()) {
                        final Vec3D a2 = a.a((Entity)this);
                        this.a(new double[] { a2.x, a2.z });
                        this.dl();
                    }
                }
            }
            else if (!controllerJumpRabbit.d()) {
                ((ControllerJumpRabbit)this.bt).a(true);
            }
        }
        this.onGroundLastTick = this.onGround;
    }
    
    public void mobTick() {
        super.mobTick();
        if (this.delay > 0) {
            --this.delay;
        }
    }
    
    protected void jump() {
        super.jump();
        if (this.moveController.c() > 0.0) {
            final Vec3D mot = this.getMot();
            if (mot.x * mot.x + mot.z * mot.x < 0.010000000000000002) {
                this.a(new float[] { 0.0f, 0.0f, 1.0f, 0.1f });
            }
        }
        if (!this.world.isClientSide) {
            this.world.broadcastEntityEffect((Entity)this, (byte)1);
        }
    }
    
    private void reset() {
        this.resetDelay();
        ((ControllerJumpRabbit)this.bt).a(false);
    }
    
    private void resetDelay() {
        if (this.moveController.c() < 2.2) {
            this.delay = 5;
        }
        else {
            this.delay = 1;
        }
    }
    
    public void dV() {
        this.setJumping(true);
    }
    
    public class ControllerJumpRabbit extends ControllerJump
    {
        private EntityRabbitPet rabbitPet;
        private boolean d;
        
        public ControllerJumpRabbit(final EntityRabbitPet rabbitPet) {
            super((EntityInsentient)rabbitPet);
            this.d = false;
            this.rabbitPet = rabbitPet;
        }
        
        public boolean c() {
            return this.a;
        }
        
        public boolean d() {
            return this.d;
        }
        
        public void a(final boolean d) {
            this.d = d;
        }
        
        public void b() {
            if (this.a) {
                this.rabbitPet.dV();
                this.a = false;
            }
        }
    }
}
