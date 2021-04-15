

package ws.billy.CookieGadgets.nms.interfaces;

import net.minecraft.server.v1_9_R1.EnumItemSlot;

public enum ArmorStandSlot
{
    MAIN_HAND("MAIN_HAND", 0, "MainHand", 0), 
    OFF_HAND("OFF_HAND", 1, "OffHand", 0), 
    HEAD("HEAD", 2, "Head", 4), 
    CHEST("CHEST", 3, "Chest", 3), 
    LEGS("LEGS", 4, "Legs", 2), 
    FEET("FEET", 5, "Feet", 1);
    
    private String name;
    private int slot;
    
    private ArmorStandSlot(final String name, final int ordinal, final String name2, final int slot) {
        this.name = name2;
        this.slot = slot;
    }
    
    public int to_1_8_Format() {
        return this.slot;
    }
    
    public EnumItemSlot to_1_9_R1_Format() {
        return EnumItemSlot.valueOf(this.name.toUpperCase());
    }
    
    public net.minecraft.server.v1_9_R2.EnumItemSlot to_1_9_R2_Format() {
        return net.minecraft.server.v1_9_R2.EnumItemSlot.valueOf(this.name.toUpperCase());
    }
    
    public net.minecraft.server.v1_10_R1.EnumItemSlot to_1_10_R1_Format() {
        return net.minecraft.server.v1_10_R1.EnumItemSlot.valueOf(this.name.toUpperCase());
    }
    
    public net.minecraft.server.v1_11_R1.EnumItemSlot to_1_11_R1_Format() {
        return net.minecraft.server.v1_11_R1.EnumItemSlot.valueOf(this.name.toUpperCase());
    }
    
    public net.minecraft.server.v1_12_R1.EnumItemSlot to_1_12_R1_Format() {
        return net.minecraft.server.v1_12_R1.EnumItemSlot.valueOf(this.name.toUpperCase());
    }
    
    public net.minecraft.server.v1_13_R1.EnumItemSlot to_1_13_R1_Format() {
        return net.minecraft.server.v1_13_R1.EnumItemSlot.valueOf(this.name.toUpperCase());
    }
    
    public net.minecraft.server.v1_13_R2.EnumItemSlot to_1_13_R2_Format() {
        return net.minecraft.server.v1_13_R2.EnumItemSlot.valueOf(this.name.toUpperCase());
    }
    
    public net.minecraft.server.v1_14_R1.EnumItemSlot to_1_14_R1_Format() {
        return net.minecraft.server.v1_14_R1.EnumItemSlot.valueOf(this.name.toUpperCase());
    }
    
    public net.minecraft.server.v1_15_R1.EnumItemSlot to_1_15_R1_Format() {
        return net.minecraft.server.v1_15_R1.EnumItemSlot.valueOf(this.name.toUpperCase());
    }
    
    public net.minecraft.server.v1_16_R1.EnumItemSlot to_1_16_R1_Format() {
        return net.minecraft.server.v1_16_R1.EnumItemSlot.valueOf(this.name.toUpperCase());
    }
    
    public net.minecraft.server.v1_16_R2.EnumItemSlot to_1_16_R2_Format() {
        return net.minecraft.server.v1_16_R2.EnumItemSlot.valueOf(this.name.toUpperCase());
    }
    
    public net.minecraft.server.v1_16_R3.EnumItemSlot to_1_16_R3_Format() {
        return net.minecraft.server.v1_16_R3.EnumItemSlot.valueOf(this.name.toUpperCase());
    }
}
