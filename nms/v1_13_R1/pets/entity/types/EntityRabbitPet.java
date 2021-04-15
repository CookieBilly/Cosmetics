

package ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.types;

import net.minecraft.server.v1_13_R1.EntityInsentient;
import net.minecraft.server.v1_13_R1.ControllerJump;
import net.minecraft.server.v1_13_R1.Vec3D;
import net.minecraft.server.v1_13_R1.PathEntity;
import net.minecraft.server.v1_13_R1.Entity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GRabbitType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.World;
import net.minecraft.server.v1_13_R1.DataWatcher;
import net.minecraft.server.v1_13_R1.DataWatcherRegistry;
import net.minecraft.server.v1_13_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityRabbitPet;
import ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.EntityAgeablePet;

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
        super((EntityTypes<?>)EntityTypes.RABBIT, world);
        this.onGroundLastTick = false;
        this.delay = 0;
    }
    
    public EntityRabbitPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.RABBIT, world, pet);
        this.onGroundLastTick = false;
        this.delay = 0;
        this.h = new ControllerJumpRabbit(this);
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
                this.o(false);
                this.reset();
            }
            final ControllerJumpRabbit controllerJumpRabbit = (ControllerJumpRabbit)this.h;
            if (!controllerJumpRabbit.c()) {
                if (this.delay == 0) {
                    final PathEntity m = this.getNavigation().m();
                    if (m != null && m.e() < m.d()) {
                        final Vec3D a = m.a((Entity)this);
                        this.a(new double[] { a.x, a.z });
                        this.de();
                    }
                }
            }
            else if (!controllerJumpRabbit.d()) {
                ((ControllerJumpRabbit)this.h).a(true);
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
    
    protected void cH() {
        super.cH();
        if (this.moveController.c() > 0.0 && this.motX * this.motX + this.motZ * this.motZ < 0.010000000000000002) {
            this.a(0.0f, 0.0f, 1.0f, 0.1f);
        }
        if (!this.world.isClientSide) {
            this.world.broadcastEntityEffect((Entity)this, (byte)1);
        }
    }
    
    private void reset() {
        this.resetDelay();
        ((ControllerJumpRabbit)this.h).a(false);
    }
    
    private void resetDelay() {
        if (this.moveController.c() < 2.2) {
            this.delay = 5;
        }
        else {
            this.delay = 1;
        }
    }
    
    public void reseter() {
        this.o(true);
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
                this.rabbitPet.reseter();
                this.a = false;
            }
        }
    }
}
