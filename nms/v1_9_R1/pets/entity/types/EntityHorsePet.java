

package ws.billy.CookieGadgets.nms.v1_9_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseVariant;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseMarking;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseArmor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseColor;
import net.minecraft.server.v1_9_R1.EnumHorseArmor;
import net.minecraft.server.v1_9_R1.EnumHorseType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_9_R1.World;
import net.minecraft.server.v1_9_R1.DataWatcher;
import net.minecraft.server.v1_9_R1.DataWatcherRegistry;
import java.util.UUID;
import com.google.common.base.Optional;
import net.minecraft.server.v1_9_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IChestedAbstractPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHorsePet;
import ws.billy.CookieGadgets.nms.v1_9_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 1.4f, height = 1.6f)
public class EntityHorsePet extends EntityAgeablePet implements IEntityHorsePet, IChestedAbstractPet
{
    private static final DataWatcherObject<Byte> VISUAL;
    private static final DataWatcherObject<Integer> VARIANT;
    private static final DataWatcherObject<Integer> MAKRING;
    private static final DataWatcherObject<Optional<UUID>> OWNER;
    private static final DataWatcherObject<Integer> ARMOR;
    protected float jumpPower;
    protected boolean bA;
    
    static {
        VISUAL = DataWatcher.a((Class)EntityHorsePet.class, DataWatcherRegistry.a);
        VARIANT = DataWatcher.a((Class)EntityHorsePet.class, DataWatcherRegistry.b);
        MAKRING = DataWatcher.a((Class)EntityHorsePet.class, DataWatcherRegistry.b);
        OWNER = DataWatcher.a((Class)EntityHorsePet.class, DataWatcherRegistry.m);
        ARMOR = DataWatcher.a((Class)EntityHorsePet.class, DataWatcherRegistry.b);
    }
    
    public EntityHorsePet(final World world) {
        super(world);
        this.jumpPower = 0.0f;
    }
    
    public EntityHorsePet(final World world, final IPet pet) {
        super(world, pet);
        this.jumpPower = 0.0f;
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityHorsePet.VISUAL, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityHorsePet.VARIANT, (Object)EnumHorseType.MULE.k());
        this.datawatcher.register((DataWatcherObject)EntityHorsePet.MAKRING, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityHorsePet.OWNER, (Object)Optional.absent());
        this.datawatcher.register((DataWatcherObject)EntityHorsePet.ARMOR, (Object)EnumHorseArmor.NONE.a());
    }
    
    public int getVariant() {
        return (int)this.datawatcher.get((DataWatcherObject)EntityHorsePet.MAKRING);
    }
    
    public void setVariant(final GHorseColor gHorseColor) {
    }
    
    @Override
    public GHorseArmor getArmor() {
        return GHorseArmor.getById((int)this.datawatcher.get((DataWatcherObject)EntityHorsePet.ARMOR));
    }
    
    @Override
    public void setArmor(final GHorseArmor gHorseArmor) {
        this.datawatcher.set((DataWatcherObject)EntityHorsePet.ARMOR, (Object)EnumHorseArmor.values()[gHorseArmor.ordinal()].a());
    }
    
    @Override
    public GHorseColor getColor() {
        return GHorseColor.values()[this.getVariant() & 0xFF];
    }
    
    @Override
    public void setColor(final GHorseColor gHorseColor) {
        this.datawatcher.set((DataWatcherObject)EntityHorsePet.MAKRING, (Object)((gHorseColor.ordinal() & 0xFF) | this.getMarking().ordinal() << 8));
    }
    
    @Override
    public GHorseMarking getMarking() {
        return GHorseMarking.values()[this.getVariant() >>> 8];
    }
    
    @Override
    public void setMarking(final GHorseMarking gHorseMarking) {
        this.datawatcher.set((DataWatcherObject)EntityHorsePet.MAKRING, (Object)((this.getColor().ordinal() & 0xFF) | gHorseMarking.ordinal() << 8));
    }
    
    @Override
    public GHorseVariant getType() {
        return GHorseVariant.getById((int)this.datawatcher.get((DataWatcherObject)EntityHorsePet.VARIANT));
    }
    
    @Override
    public void setType(final GHorseVariant gHorseVariant) {
        this.datawatcher.set((DataWatcherObject)EntityHorsePet.VARIANT, (Object)gHorseVariant.getId());
    }
    
    @Override
    public boolean isCollidable() {
        return false;
    }
    
    @Override
    public boolean hasChest() {
        return this.getHorseVisual(8);
    }
    
    @Override
    public void setHasChest(final boolean b) {
        this.setHorseVisual(8, b);
    }
    
    @Override
    public boolean hasSaddle() {
        return this.getHorseVisual(4);
    }
    
    @Override
    public void setSaddle(final boolean b) {
        this.setHorseVisual(4, b);
    }
    
    public boolean getHorseVisual(final int n) {
        return ((byte)this.datawatcher.get((DataWatcherObject)EntityHorsePet.VISUAL) & n) != 0x0;
    }
    
    public void setHorseVisual(final int n, final boolean b) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntityHorsePet.VISUAL);
        if (b) {
            this.datawatcher.set((DataWatcherObject)EntityHorsePet.VISUAL, (Object)(byte)(byteValue | n));
        }
        else {
            this.datawatcher.set((DataWatcherObject)EntityHorsePet.VISUAL, (Object)(byte)(byteValue & ~n));
        }
    }
}
