

package ws.billy.CookieGadgets.utils.cosmetics.miniatures;

import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.GMaterial;

public class MiniatureArmorType
{
    private EnumMiniatureEquipments miniatureEquipments;
    private GMaterial material;
    private String texture;
    private boolean isHead;
    
    public MiniatureArmorType(final EnumMiniatureEquipments miniatureEquipments, final String texture) {
        this.isHead = false;
        this.miniatureEquipments = miniatureEquipments;
        this.material = new GMaterial(EnumMaterial.PLAYER_HEAD);
        this.texture = texture;
        this.isHead = true;
    }
    
    public MiniatureArmorType(final EnumMiniatureEquipments miniatureEquipments, final GMaterial material) {
        this.isHead = false;
        this.miniatureEquipments = miniatureEquipments;
        this.material = material;
        this.texture = null;
    }
    
    public EnumMiniatureEquipments getMiniatureEquipments() {
        return this.miniatureEquipments;
    }
    
    public GMaterial getGMaterial() {
        return this.material;
    }
    
    public boolean isSkullHead() {
        return this.isHead;
    }
    
    public ItemStack getItemStack() {
        if (this.isHead) {
            return ItemUtils.itemSkull(CookieGadgets.getInstance().getPluginName(), this.texture);
        }
        return ItemUtils.item(CookieGadgets.getInstance().getPluginName(), this.material);
    }
    
    public String getTexture() {
        return this.texture;
    }
}
