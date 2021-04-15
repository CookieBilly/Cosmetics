

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.types;

import net.minecraft.server.v1_16_R3.EnumColor;
import ws.billy.CookieGadgets.utils.GDyeColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GLlamaColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.DataWatcherSerializer;
import net.minecraft.server.v1_16_R3.DataWatcher;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils.DataWatcherWrapper;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityLlamaPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.sub.EntityHorseChestedAbstractPet;

@EntitySize(width = 0.9f, height = 1.87f)
public class EntityLlamaPet extends EntityHorseChestedAbstractPet implements IEntityLlamaPet
{
    private static final DataWatcherObject<Integer> STRENGTH;
    private static final DataWatcherObject<Integer> COLOR;
    private static final DataWatcherObject<Integer> VARIANT;
    
    static {
        STRENGTH = DataWatcher.a((Class)EntityLlamaPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
        COLOR = DataWatcher.a((Class)EntityLlamaPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
        VARIANT = DataWatcher.a((Class)EntityLlamaPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
    }
    
    public EntityLlamaPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.LLAMA, world);
    }
    
    public EntityLlamaPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.LLAMA, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityLlamaPet.STRENGTH, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityLlamaPet.COLOR, (Object)(-1));
        this.datawatcher.register((DataWatcherObject)EntityLlamaPet.VARIANT, (Object)0);
    }
    
    @Override
    public void setLlamaColor(final GLlamaColor gLlamaColor) {
        this.datawatcher.set((DataWatcherObject)EntityLlamaPet.VARIANT, (Object)gLlamaColor.getId());
    }
    
    @Override
    public GLlamaColor getLlamaColor() {
        return GLlamaColor.getById((int)this.datawatcher.get((DataWatcherObject)EntityLlamaPet.VARIANT));
    }
    
    @Override
    public void setColor(final GDyeColor gDyeColor) {
        this.datawatcher.set((DataWatcherObject)EntityLlamaPet.COLOR, (Object)EnumColor.fromColorIndex((int)gDyeColor.getWoolData()).getColorIndex());
    }
    
    @Override
    public GDyeColor getColor() {
        return GDyeColor.getByWoolData(((Integer)this.datawatcher.get((DataWatcherObject)EntityLlamaPet.COLOR)).byteValue());
    }
}
