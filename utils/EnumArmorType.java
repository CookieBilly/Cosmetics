

package ws.billy.CookieGadgets.utils;

public enum EnumArmorType
{
    HELMET("HELMET", 0, "Helmet", MessageType.RESET_SUIT_HELMET), 
    CHESTPLATE("CHESTPLATE", 1, "Chestplate", MessageType.RESET_SUIT_CHESTPLATE), 
    LEGGINGS("LEGGINGS", 2, "Leggings", MessageType.RESET_SUIT_LEGGINGS), 
    BOOTS("BOOTS", 3, "Boots", MessageType.RESET_SUIT_BOOTS);
    
    private String type;
    private MessageType resetMessage;
    
    private EnumArmorType(final String name, final int ordinal, final String type, final MessageType resetMessage) {
        this.type = type;
        this.resetMessage = resetMessage;
    }
    
    public String getType() {
        return this.type;
    }
    
    public MessageType getResetSuitMessage() {
        return this.resetMessage;
    }
    
    public static boolean isLeatherArmor(final EnumMaterial enumMaterial) {
        return enumMaterial == EnumMaterial.LEATHER_HELMET || enumMaterial == EnumMaterial.LEATHER_CHESTPLATE || enumMaterial == EnumMaterial.LEATHER_LEGGINGS || enumMaterial == EnumMaterial.LEATHER_BOOTS;
    }
}
