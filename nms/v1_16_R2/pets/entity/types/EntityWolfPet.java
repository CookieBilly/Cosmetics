

package ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.GDyeColor;
import net.minecraft.server.v1_16_R2.EnumColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.World;
import net.minecraft.server.v1_16_R2.DataWatcher;
import net.minecraft.server.v1_16_R2.DataWatcherRegistry;
import net.minecraft.server.v1_16_R2.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWolfPet;
import ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.EntityTameablePet;

@EntitySize(width = 0.6f, height = 0.8f)
public class EntityWolfPet extends EntityTameablePet implements IEntityWolfPet
{
    private static final DataWatcherObject<Boolean> bA;
    private static final DataWatcherObject<Integer> COLLAR_COLOR;
    
    static {
        bA = DataWatcher.a((Class)EntityWolfPet.class, DataWatcherRegistry.i);
        COLLAR_COLOR = DataWatcher.a((Class)EntityWolfPet.class, DataWatcherRegistry.b);
    }
    
    public EntityWolfPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.WOLF, world);
    }
    
    public EntityWolfPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.WOLF, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityWolfPet.bA, (Object)false);
        this.datawatcher.register((DataWatcherObject)EntityWolfPet.COLLAR_COLOR, (Object)EnumColor.RED.getColorIndex());
    }
    
    @Override
    public void setTamed(final boolean tamed) {
        if (this.isAngry() && tamed) {
            this.setAngry(false);
        }
        super.setTamed(tamed);
    }
    
    @Override
    public boolean isAngry() {
        return ((byte)this.datawatcher.get((DataWatcherObject)EntityWolfPet.TAMED) & 0x2) != 0x0;
    }
    
    @Override
    public void setAngry(final boolean b) {
        if (this.isTamed() && b) {
            this.setTamed(false);
        }
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntityWolfPet.TAMED);
        if (b) {
            this.datawatcher.set((DataWatcherObject)EntityWolfPet.TAMED, (Object)(byte)(byteValue | 0x2));
        }
        else {
            this.datawatcher.set((DataWatcherObject)EntityWolfPet.TAMED, (Object)(byte)(byteValue & 0xFFFFFFFD));
        }
    }
    
    @Override
    public GDyeColor getColor() {
        return GDyeColor.getByDyeData((byte)(int)this.getDataWatcher().get((DataWatcherObject)EntityWolfPet.COLLAR_COLOR));
    }
    
    @Override
    public void setColor(final GDyeColor gDyeColor) {
        if (this.isTamed()) {
            this.datawatcher.set((DataWatcherObject)EntityWolfPet.COLLAR_COLOR, (Object)(int)gDyeColor.getDyeData());
        }
    }
}
