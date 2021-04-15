

package ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseMarking;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseArmor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseColor;
import net.minecraft.server.v1_12_R1.EnumHorseArmor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_12_R1.World;
import net.minecraft.server.v1_12_R1.DataWatcher;
import net.minecraft.server.v1_12_R1.DataWatcherRegistry;
import net.minecraft.server.v1_12_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHorsePet;
import ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.sub.EntityHorseAbstractPet;

@EntitySize(width = 1.4f, height = 1.6f)
public class EntityHorsePet extends EntityHorseAbstractPet implements IEntityHorsePet
{
    private static final DataWatcherObject<Integer> MAKRING;
    private static final DataWatcherObject<Integer> ARMOR;
    
    static {
        MAKRING = DataWatcher.a((Class)EntityHorsePet.class, DataWatcherRegistry.b);
        ARMOR = DataWatcher.a((Class)EntityHorsePet.class, DataWatcherRegistry.b);
    }
    
    public EntityHorsePet(final World world) {
        super(world);
    }
    
    public EntityHorsePet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityHorsePet.MAKRING, (Object)0);
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
}
