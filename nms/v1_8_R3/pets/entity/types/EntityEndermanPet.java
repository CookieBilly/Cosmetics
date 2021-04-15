

package ws.billy.CookieGadgets.nms.v1_8_R3.pets.entity.types;

import net.minecraft.server.v1_8_R3.Block;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R3.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityEndermanPet;
import ws.billy.CookieGadgets.nms.v1_8_R3.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 2.9f)
public class EntityEndermanPet extends EntityPet implements IEntityEndermanPet
{
    public EntityEndermanPet(final World world) {
        super(world);
    }
    
    public EntityEndermanPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(16, (Object)0);
        this.datawatcher.a(17, (Object)0);
        this.datawatcher.a(18, (Object)0);
    }
    
    @Override
    public boolean isScreaming() {
        return this.datawatcher.getByte(18) > 0;
    }
    
    @Override
    public void setScreaming(final boolean b) {
        this.datawatcher.watch(18, (Object)(byte)(b ? 1 : 0));
    }
    
    public Block getCarried() {
        return Block.getById((int)this.datawatcher.getShort(16));
    }
    
    public void setCarried(final Block block) {
        this.datawatcher.watch(16, (Object)(short)(Block.getId(block) & 0xFF));
    }
}
