

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.GDyeColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GCatType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.DataWatcherSerializer;
import net.minecraft.server.v1_16_R3.DataWatcher;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils.DataWatcherWrapper;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityCatPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.EntityTameablePet;

@EntitySize(width = 0.6f, height = 0.7f)
public class EntityCatPet extends EntityTameablePet implements IEntityCatPet
{
    private static final DataWatcherObject<Integer> TYPE;
    private static final DataWatcherObject<Boolean> LYING_ON_GROUND;
    private static final DataWatcherObject<Boolean> LOOK_UP;
    private static final DataWatcherObject<Integer> COLLAR_COLOR;
    
    static {
        TYPE = DataWatcher.a((Class)EntityCatPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
        LYING_ON_GROUND = DataWatcher.a((Class)EntityCatPet.class, (DataWatcherSerializer)DataWatcherWrapper.BOOLEAN);
        LOOK_UP = DataWatcher.a((Class)EntityCatPet.class, (DataWatcherSerializer)DataWatcherWrapper.BOOLEAN);
        COLLAR_COLOR = DataWatcher.a((Class)EntityCatPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
    }
    
    public EntityCatPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.CAT, world);
    }
    
    public EntityCatPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.CAT, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityCatPet.TYPE, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityCatPet.LYING_ON_GROUND, (Object)false);
        this.datawatcher.register((DataWatcherObject)EntityCatPet.LOOK_UP, (Object)false);
        this.datawatcher.register((DataWatcherObject)EntityCatPet.COLLAR_COLOR, (Object)0);
    }
    
    @Override
    public GCatType getCatType() {
        return GCatType.getById((int)this.datawatcher.get((DataWatcherObject)EntityCatPet.TYPE));
    }
    
    @Override
    public void setCatType(final GCatType gCatType) {
        this.datawatcher.set((DataWatcherObject)EntityCatPet.TYPE, (Object)gCatType.getId());
    }
    
    @Override
    public boolean isLying() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityCatPet.LYING_ON_GROUND);
    }
    
    @Override
    public void setLying(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityCatPet.LYING_ON_GROUND, (Object)b);
    }
    
    @Override
    public boolean isLookUp() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityCatPet.LOOK_UP);
    }
    
    @Override
    public void setLookUp(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityCatPet.LOOK_UP, (Object)b);
    }
    
    @Override
    public GDyeColor getCollarColor() {
        return GDyeColor.getByWoolData(((Integer)this.datawatcher.get((DataWatcherObject)EntityCatPet.COLLAR_COLOR)).byteValue());
    }
    
    @Override
    public void setCollarColor(final GDyeColor gDyeColor) {
        this.datawatcher.set((DataWatcherObject)EntityCatPet.COLLAR_COLOR, (Object)(int)gDyeColor.getWoolData());
    }
}
