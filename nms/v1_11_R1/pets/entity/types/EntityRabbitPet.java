

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types;

import net.minecraft.server.v1_11_R1.EntityInsentient;
import net.minecraft.server.v1_11_R1.ControllerJump;
import net.minecraft.server.v1_11_R1.Vec3D;
import net.minecraft.server.v1_11_R1.PathEntity;
import net.minecraft.server.v1_11_R1.Entity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GRabbitType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_11_R1.World;
import net.minecraft.server.v1_11_R1.DataWatcher;
import net.minecraft.server.v1_11_R1.DataWatcherRegistry;
import net.minecraft.server.v1_11_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityRabbitPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.EntityAgeablePet;

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
        super(world);
        this.onGroundLastTick = false;
        this.delay = 0;
    }
    
    public EntityRabbitPet(final World world, final IPet pet) {
        super(world, pet);
        this.onGroundLastTick = false;
        this.delay = 0;
        this.g = new ControllerJumpRabbit(this);
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
                this.l(false);
                this.reset();
            }
            final ControllerJumpRabbit controllerJumpRabbit = (ControllerJumpRabbit)this.g;
            if (!controllerJumpRabbit.c()) {
                if (this.delay == 0) {
                    final PathEntity k = this.getNavigation().k();
                    if (k != null && k.e() < k.d()) {
                        final Vec3D a = k.a((Entity)this);
                        this.a(new double[] { a.x, a.z });
                        this.de();
                    }
                }
            }
            else if (!controllerJumpRabbit.d()) {
                ((ControllerJumpRabbit)this.g).a(true);
            }
        }
        this.onGroundLastTick = this.onGround;
    }
    
    public void M() {
        super.M();
        if (this.delay > 0) {
            --this.delay;
        }
    }
    
    protected void cm() {
        super.cm();
        this.world.broadcastEntityEffect((Entity)this, (byte)1);
    }
    
    private void reset() {
        this.resetDelay();
        ((ControllerJumpRabbit)this.g).a(false);
    }
    
    private void resetDelay() {
        if (this.moveController.b() < 2.2) {
            this.delay = 5;
        }
        else {
            this.delay = 1;
        }
    }
    
    public void de() {
        this.l(true);
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
                this.rabbitPet.de();
                this.a = false;
            }
        }
    }
}
