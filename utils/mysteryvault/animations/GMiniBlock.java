

package ws.billy.CookieGadgets.utils.mysteryvault.animations;

import org.bukkit.block.BlockFace;
import java.util.List;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.GMaterial;

public class GMiniBlock
{
    private GMaterial material;
    private ItemStack itemStack;
    public static double additionY;
    
    static {
        GMiniBlock.additionY = 2.6;
    }
    
    public GMiniBlock(final GMaterial material) {
        this.material = material;
        if (this.material.isSkullHead()) {
            this.itemStack = ItemUtils.itemSkull("mini block", null, this.material.getTexture());
        }
        else {
            this.itemStack = ItemUtils.item("mini block", this.material.getEnumMaterial(), this.material.getData());
        }
    }
    
    public GMaterial getMaterial() {
        return this.material;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    @Override
    public String toString() {
        return this.itemStack.toString();
    }
    
    public static float getYawByBlockFace(final BlockFace blockFace) {
        if (blockFace == BlockFace.EAST) {
            return -90.0f;
        }
        if (blockFace == BlockFace.SOUTH) {
            return 0.0f;
        }
        if (blockFace == BlockFace.WEST) {
            return 90.0f;
        }
        if (blockFace == BlockFace.NORTH) {
            return 180.0f;
        }
        return 0.0f;
    }
}
