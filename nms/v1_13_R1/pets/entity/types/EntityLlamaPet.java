

package ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.types;

import net.minecraft.server.v1_13_R1.EnumColor;
import ws.billy.CookieGadgets.utils.GDyeColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GLlamaColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.World;
import net.minecraft.server.v1_13_R1.DataWatcher;
import net.minecraft.server.v1_13_R1.DataWatcherRegistry;
import net.minecraft.server.v1_13_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityLlamaPet;
import ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.sub.EntityHorseChestedAbstractPet;

@EntitySize(width = 0.9f, height = 1.87f)
public class EntityLlamaPet extends EntityHorseChestedAbstractPet implements IEntityLlamaPet
{
    private static final DataWatcherObject<Integer> STRENGTH;
    private static final DataWatcherObject<Integer> COLOR;
    private static final DataWatcherObject<Integer> VARIANT;
    
    static {
        STRENGTH = DataWatcher.a((Class)EntityLlamaPet.class, DataWatcherRegistry.b);
        COLOR = DataWatcher.a((Class)EntityLlamaPet.class, DataWatcherRegistry.b);
        VARIANT = DataWatcher.a((Class)EntityLlamaPet.class, DataWatcherRegistry.b);
    }
    
    public EntityLlamaPet(final World world) {
        super((EntityTypes<?>)EntityTypes.LLAMA, world);
    }
    
    public EntityLlamaPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.LLAMA, world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
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
