

package ws.billy.CookieGadgets.nms.v1_16_R1.pets.entity.types;

import net.minecraft.server.v1_16_R1.ControllerMove;
import net.minecraft.server.v1_16_R1.ControllerJump;
import net.minecraft.server.v1_16_R1.MathHelper;
import net.minecraft.server.v1_16_R1.Vec3D;
import net.minecraft.server.v1_16_R1.PathEntity;
import net.minecraft.server.v1_16_R1.Entity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GRabbitType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R1.EntityInsentient;
import net.minecraft.server.v1_16_R1.EntityTypes;
import net.minecraft.server.v1_16_R1.World;
import net.minecraft.server.v1_16_R1.DataWatcher;
import net.minecraft.server.v1_16_R1.DataWatcherRegistry;
import net.minecraft.server.v1_16_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityRabbitPet;
import ws.billy.CookieGadgets.nms.v1_16_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.4f, height = 0.5f)
public class EntityRabbitPet extends EntityAgeablePet implements IEntityRabbitPet
{
    private static final DataWatcherObject<Integer> TYPE;
    private int bx;
    private int by;
    private boolean bz;
    private int bA;
    private int bB;
    
    static {
        TYPE = DataWatcher.a((Class)EntityRabbitPet.class, DataWatcherRegistry.b);
    }
    
    public EntityRabbitPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.RABBIT, world);
    }
    
    public EntityRabbitPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.RABBIT, world, pet);
        this.bp = new ControllerJumpRabbit(this);
        this.moveController = new ControllerMoveRabbit(this);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
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
    
    protected float dI() {
        if (this.positionChanged || (this.moveController.b() && this.moveController.e() > this.locY() + 0.5)) {
            return 0.5f;
        }
        final PathEntity k = this.navigation.k();
        if (k != null && k.f() < k.e() && k.a((Entity)this).y > this.locY() + 0.5) {
            return 0.5f;
        }
        return (this.moveController.c() <= 0.6) ? 0.2f : 0.3f;
    }
    
    protected void jump() {
        super.jump();
        if (this.moveController.c() > 0.0 && Entity.b(this.getMot()) < 0.01) {
            this.a(0.1f, new Vec3D(0.0, 0.0, 1.0));
        }
        if (!this.world.isClientSide) {
            this.world.broadcastEntityEffect((Entity)this, (byte)1);
        }
    }
    
    public void mobTick() {
        if (this.passengers.size() != 0) {
            return;
        }
        if (this.bA > 0) {
            --this.bA;
        }
        if (this.bB > 0) {
            this.bB -= this.random.nextInt(3);
            if (this.bB < 0) {
                this.bB = 0;
            }
        }
        if (this.onGround) {
            if (!this.bz) {
                this.setJumping(false);
                this.reset();
            }
            final ControllerJumpRabbit controllerJumpRabbit = (ControllerJumpRabbit)this.bp;
            if (!controllerJumpRabbit.c()) {
                if (this.moveController.b() && this.bA == 0) {
                    final PathEntity k = this.navigation.k();
                    Vec3D a = new Vec3D(this.moveController.d(), this.moveController.e(), this.moveController.f());
                    if (k != null && k.f() < k.e()) {
                        a = k.a((Entity)this);
                    }
                    this.b(a.x, a.z);
                    this.eL();
                }
            }
            else if (!controllerJumpRabbit.d()) {
                ((ControllerJumpRabbit)this.bp).a(true);
            }
        }
        this.bz = this.onGround;
    }
    
    public void movementTick() {
        super.movementTick();
        if (this.bx != this.by) {
            ++this.bx;
        }
        else if (this.by != 0) {
            this.bx = 0;
            this.by = 0;
            this.setJumping(false);
        }
    }
    
    public void setJumping(final boolean jumping) {
        super.setJumping(jumping);
    }
    
    private void reset() {
        if (this.moveController.c() < 2.2) {
            this.bA = 10;
        }
        else {
            this.bA = 1;
        }
        ((ControllerJumpRabbit)this.bp).a(false);
    }
    
    private void i(final double n) {
        this.getNavigation().a(n);
        this.moveController.a(this.moveController.d(), this.moveController.e(), this.moveController.f(), n);
    }
    
    private void eL() {
        this.setJumping(true);
        this.by = 10;
        this.bx = 0;
    }
    
    private void b(final double n, final double n2) {
        this.yaw = (float)(MathHelper.d(n2 - this.locZ(), n - this.locX()) * 57.2957763671875) - 90.0f;
    }
    
    private static class ControllerMoveRabbit extends ControllerMove
    {
        private final EntityRabbitPet i;
        private double j;
        
        public ControllerMoveRabbit(final EntityRabbitPet i) {
            super((EntityInsentient)i);
            this.i = i;
        }
        
        public void a() {
            if (this.i.onGround && !this.i.jumping && !((ControllerJumpRabbit)this.i.bp).c()) {
                this.i.i(0.0);
            }
            else if (this.b()) {
                this.i.i(this.j);
            }
            super.a();
        }
        
        public void a(final double n, final double n2, final double n3, double j) {
            if (this.i.isInWater()) {
                j = 1.5;
            }
            super.a(n, n2, n3, j);
            if (j > 0.0) {
                this.j = j;
            }
        }
    }
    
    public class ControllerJumpRabbit extends ControllerJump
    {
        private final EntityRabbitPet c;
        private boolean d;
        
        public ControllerJumpRabbit(final EntityRabbitPet c) {
            super((EntityInsentient)c);
            this.c = c;
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
                this.c.eL();
                this.a = false;
            }
        }
    }
}
