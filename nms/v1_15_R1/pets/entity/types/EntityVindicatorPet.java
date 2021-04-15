

package ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity.types;

import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityVindicatorPet;
import ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity.sub.EntityIllagerAbstractPet;

@EntitySize(width = 0.6f, height = 1.95f)
public class EntityVindicatorPet extends EntityIllagerAbstractPet implements IEntityVindicatorPet
{
    boolean johnny;
    
    public EntityVindicatorPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.VINDICATOR, world);
        this.johnny = false;
    }
    
    public EntityVindicatorPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.VINDICATOR, world, pet);
        this.johnny = false;
    }
    
    @Override
    public boolean isJohnny() {
        return this.johnny;
    }
    
    @Override
    public void setJohnny(final boolean johnny) {
        ((LivingEntity)this.getEntity()).getEquipment().setItemInMainHand(new ItemStack(johnny ? Material.IRON_AXE : Material.AIR));
        this.a(1, this.johnny = johnny);
    }
}
