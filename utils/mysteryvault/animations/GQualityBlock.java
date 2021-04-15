

package ws.billy.CookieGadgets.utils.mysteryvault.animations;

import org.bukkit.util.EulerAngle;
import java.util.List;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.GMaterial;

public class GQualityBlock
{
    private GMaterial material;
    private int rotationalAngleX;
    private int rotationalAngleY;
    private int rotationalAngleZ;
    private ItemStack itemStack;
    private boolean hasRotate;
    
    public GQualityBlock(final GMaterial material) {
        this.material = material;
        this.rotationalAngleX = 0;
        this.rotationalAngleY = 0;
        this.rotationalAngleZ = 0;
        this.hasRotate = false;
        if (this.material.isSkullHead()) {
            this.itemStack = ItemUtils.itemSkull("quality block", null, this.material.getTexture());
        }
        else {
            this.itemStack = ItemUtils.item("quality block", this.material.getEnumMaterial(), this.material.getData());
        }
    }
    
    public GQualityBlock(final GMaterial material, final int rotationalAngleX, final int rotationalAngleY, final int rotationalAngleZ) {
        this.material = material;
        this.rotationalAngleX = rotationalAngleX;
        this.rotationalAngleY = rotationalAngleY;
        this.rotationalAngleZ = rotationalAngleZ;
        this.hasRotate = true;
        if (this.material.isSkullHead()) {
            this.itemStack = ItemUtils.itemSkull("quality block", null, this.material.getTexture());
        }
        else {
            this.itemStack = ItemUtils.item("quality block", this.material.getEnumMaterial(), this.material.getData());
        }
    }
    
    public GMaterial getMaterial() {
        return this.material;
    }
    
    public boolean hasRotate() {
        return this.hasRotate;
    }
    
    public int getRotationalAngleX() {
        return this.rotationalAngleX;
    }
    
    public int getRotationalAngleY() {
        return this.rotationalAngleY;
    }
    
    public int getRotationalAngleZ() {
        return this.rotationalAngleZ;
    }
    
    public EulerAngle getEulerAngle() {
        return new EulerAngle(degreeToRadians(this.rotationalAngleX), degreeToRadians(this.rotationalAngleY), degreeToRadians(this.rotationalAngleZ));
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    @Override
    public String toString() {
        return this.itemStack.toString();
    }
    
    private static double degreeToRadians(final int n) {
        return n / 180.0 * 3.141592653589793;
    }
}
