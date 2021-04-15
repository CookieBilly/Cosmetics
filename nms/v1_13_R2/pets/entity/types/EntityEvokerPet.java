

package ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.World;
import net.minecraft.server.v1_13_R2.DataWatcher;
import net.minecraft.server.v1_13_R2.DataWatcherRegistry;
import net.minecraft.server.v1_13_R2.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityEvokerPet;
import ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 1.95f)
public class EntityEvokerPet extends EntityPet implements IEntityEvokerPet
{
    private static final DataWatcherObject<Byte> SPELL;
    
    static {
        SPELL = DataWatcher.a((Class)EntityEvokerPet.class, DataWatcherRegistry.a);
    }
    
    public EntityEvokerPet(final World world) {
        super((EntityTypes<?>)EntityTypes.EVOKER, world);
    }
    
    public EntityEvokerPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.EVOKER, world, pet);
    }
    
    public EntityEvokerPet(final EntityTypes<?> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntityEvokerPet(final EntityTypes<?> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityEvokerPet.SPELL, (Object)0);
    }
}
