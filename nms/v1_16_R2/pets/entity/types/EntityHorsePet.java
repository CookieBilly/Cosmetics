

package ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseMarking;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseArmor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.World;
import net.minecraft.server.v1_16_R2.DataWatcher;
import net.minecraft.server.v1_16_R2.DataWatcherRegistry;
import net.minecraft.server.v1_16_R2.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHorsePet;
import ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.sub.EntityHorseAbstractPet;

@EntitySize(width = 1.4f, height = 1.6f)
public class EntityHorsePet extends EntityHorseAbstractPet implements IEntityHorsePet
{
    private static final DataWatcherObject<Integer> MAKRING;
    
    static {
        MAKRING = DataWatcher.a((Class)EntityHorsePet.class, DataWatcherRegistry.b);
    }
    
    public EntityHorsePet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.HORSE, world);
    }
    
    public EntityHorsePet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.HORSE, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityHorsePet.MAKRING, (Object)0);
    }
    
    public int getVariant() {
        return (int)this.datawatcher.get((DataWatcherObject)EntityHorsePet.MAKRING);
    }
    
    public void setVariant(final GHorseColor gHorseColor) {
    }
    
    @Override
    public GHorseArmor getArmor() {
        return null;
    }
    
    @Override
    public void setArmor(final GHorseArmor gHorseArmor) {
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
