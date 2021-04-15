

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.types;

import net.minecraft.server.v1_16_R3.ControllerMove;
import net.minecraft.server.v1_16_R3.ControllerJump;
import net.minecraft.server.v1_16_R3.MathHelper;
import net.minecraft.server.v1_16_R3.Vec3D;
import net.minecraft.server.v1_16_R3.PathEntity;
import net.minecraft.server.v1_16_R3.Entity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GRabbitType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.DataWatcherSerializer;
import net.minecraft.server.v1_16_R3.DataWatcher;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils.DataWatcherWrapper;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityRabbitPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.4f, height = 0.5f)
public class EntityRabbitPet extends EntityAgeablePet implements IEntityRabbitPet
{
    private static final DataWatcherObject<Integer> TYPE;
    private int bq;
    private int br;
    private boolean bs;
    private int bt;
    private int bu;
    
    static {
        TYPE = DataWatcher.a((Class)EntityRabbitPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
    }
    
    public EntityRabbitPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.RABBIT, world);
    }
    
    public EntityRabbitPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.RABBIT, world, pet);
        this.bi = new ControllerJumpRabbit(this);
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
    
    protected float dJ() {
        if (this.positionChanged || (this.moveController.b() && this.moveController.e() > this.locY() + 0.5)) {
            return 0.5f;
        }
        final PathEntity k = this.navigation.k();
        if (k != null && !k.c() && k.a((Entity)this).y > this.locY() + 0.5) {
            return 0.5f;
        }
        return (this.moveController.c() <= 0.6) ? 0.2f : 0.3f;
    }
    
    protected void jump() {
        super.jump();
        if (this.moveController.c() > 0.0 && Entity.c(this.getMot()) < 0.01) {
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
        if (this.bt > 0) {
            --this.bt;
        }
        if (this.bu > 0) {
            this.bu -= this.random.nextInt(3);
            if (this.bu < 0) {
                this.bu = 0;
            }
        }
        if (this.onGround) {
            if (!this.bs) {
                this.setJumping(false);
                this.reset();
            }
            final ControllerJumpRabbit controllerJumpRabbit = (ControllerJumpRabbit)this.bi;
            if (!controllerJumpRabbit.c()) {
                if (this.moveController.b() && this.bt == 0) {
                    final PathEntity k = this.navigation.k();
                    Vec3D a = new Vec3D(this.moveController.d(), this.moveController.e(), this.moveController.f());
                    if (k != null && !k.c()) {
                        a = k.a((Entity)this);
                    }
                    this.b(a.x, a.z);
                    this.playJumpAnimation();
                }
            }
            else if (!controllerJumpRabbit.d()) {
                ((ControllerJumpRabbit)this.bi).a(true);
            }
        }
        this.bs = this.onGround;
    }
    
    public void movementTick() {
        super.movementTick();
        if (this.bq != this.br) {
            ++this.bq;
        }
        else if (this.br != 0) {
            this.bq = 0;
            this.br = 0;
            this.setJumping(false);
        }
    }
    
    public void setJumping(final boolean jumping) {
        super.setJumping(jumping);
    }
    
    private void reset() {
        if (this.moveController.c() < 2.2) {
            this.bt = 10;
        }
        else {
            this.bt = 1;
        }
        ((ControllerJumpRabbit)this.bi).a(false);
    }
    
    private void i(final double n) {
        this.getNavigation().a(n);
        this.moveController.a(this.moveController.d(), this.moveController.e(), this.moveController.f(), n);
    }
    
    private void playJumpAnimation() {
        this.setJumping(true);
        this.br = 10;
        this.bq = 0;
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
            if (this.i.onGround && !this.i.jumping && !((ControllerJumpRabbit)this.i.bi).c()) {
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
                this.c.playJumpAnimation();
                this.a = false;
            }
        }
    }
}
