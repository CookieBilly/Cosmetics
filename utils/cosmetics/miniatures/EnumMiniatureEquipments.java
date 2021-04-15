

package ws.billy.CookieGadgets.utils.cosmetics.miniatures;

import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.nms.interfaces.ArmorStandSlot;

public enum EnumMiniatureEquipments
{
    HELMET("HELMET", 0, "Helmet", ArmorStandSlot.HEAD), 
    CHESTPLATE("CHESTPLATE", 1, "Chestplate", ArmorStandSlot.CHEST), 
    LEGGINGS("LEGGINGS", 2, "Leggings", ArmorStandSlot.LEGS), 
    BOOTS("BOOTS", 3, "Boots", ArmorStandSlot.FEET), 
    HAND("HAND", 4, "Hand", ArmorStandSlot.MAIN_HAND);
    
    private String type;
    private ArmorStandSlot armorStandSlot;
    
    private EnumMiniatureEquipments(final String name, final int ordinal, final String type, final ArmorStandSlot armorStandSlot) {
        this.type = type;
        this.armorStandSlot = armorStandSlot;
    }
    
    public String getType() {
        return this.type;
    }
    
    public ArmorStandSlot getArmorStandSlot() {
        return this.armorStandSlot;
    }
    
    public static boolean isLeatherArmor(final EnumMaterial enumMaterial) {
        return enumMaterial == EnumMaterial.LEATHER_HELMET || enumMaterial == EnumMaterial.LEATHER_CHESTPLATE || enumMaterial == EnumMaterial.LEATHER_LEGGINGS || enumMaterial == EnumMaterial.LEATHER_BOOTS;
    }
}
