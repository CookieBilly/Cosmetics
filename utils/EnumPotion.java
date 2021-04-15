

package ws.billy.CookieGadgets.utils;

import org.bukkit.potion.PotionType;

public enum EnumPotion
{
    WATER("WATER", 0, 0), 
    AWKWARD("AWKWARD", 1, 16), 
    THICK("THICK", 2, 32), 
    MUNDANE("MUNDANE", 3, 64), 
    REGEN("REGEN", 4, 8193), 
    REGEN_II("REGEN_II", 5, 8197, false, true), 
    REGEN_III("REGEN_III", 6, 8225, true, false), 
    SPEED("SPEED", 7, 8194), 
    SPEED_II("SPEED_II", 8, 8258, false, true), 
    SPEED_III("SPEED_III", 9, 8226, true, false), 
    FIRE_RESISTANCE("FIRE_RESISTANCE", 10, 8195), 
    INSTANT_HEAL("INSTANT_HEAL", 11, 8197), 
    INSTANT_HEAL_II("INSTANT_HEAL_II", 12, 8229, true, false), 
    NIGHT_VISION("NIGHT_VISION", 13, 8198), 
    NIGHT_VISION_II("NIGHT_VISION_II", 14, 8262, false, true), 
    STRENGTH("STRENGTH", 15, 8201), 
    STRENGTH_II("STRENGTH_II", 16, 8265, false, true), 
    STRENGTH_III("STRENGTH_III", 17, 8233, true, false), 
    JUMP("JUMP", 18, 8203), 
    JUMP_II("JUMP_II", 19, 8267, false, true), 
    JUMP_III("JUMP_III", 20, 8235, true, false), 
    WATER_BREATHING("WATER_BREATHING", 21, 8205, true, false), 
    WATER_BREATHING_II("WATER_BREATHING_II", 22, 8269, false, true), 
    INVISIBILITY("INVISIBILITY", 23, 8206), 
    INVISIBILITY_II("INVISIBILITY_II", 24, 8270, false, true), 
    LUCK("LUCK", 25, 8207), 
    POISON("POISON", 26, 8196), 
    POISON_II("POISON_II", 27, 8260, false, true), 
    POISON_III("POISON_III", 28, 8228, true, false), 
    WEAKNESS("WEAKNESS", 29, 8200), 
    WEAKNESS_II("WEAKNESS_II", 30, 8264, false, true), 
    SLOWNESS("SLOWNESS", 31, 8202), 
    SLOWNESS_II("SLOWNESS_II", 32, 8266, false, true), 
    INSTANT_DAMAGE("INSTANT_DAMAGE", 33, 8204), 
    INSTANT_DAMAGE_II("INSTANT_DAMAGE_II", 34, 8236, true, false);
    
    private int data;
    private PotionType potionType;
    private boolean strong;
    private boolean extend;
    
    private EnumPotion(final String name, final int ordinal, final int data) {
        this.data = data;
        try {
            this.potionType = PotionType.valueOf(this.toString().replace("_II", "").replace("_III", ""));
        }
        catch (NoSuchFieldError | IllegalArgumentException noSuchFieldError) {
            this.potionType = PotionType.REGEN;
        }
        this.strong = false;
        this.extend = false;
    }
    
    private EnumPotion(final String name, final int ordinal, final int data, final boolean strong, final boolean extend) {
        this.data = data;
        try {
            this.potionType = PotionType.valueOf(this.toString().replace("_II", "").replace("_III", ""));
        }
        catch (NoSuchFieldError | IllegalArgumentException noSuchFieldError) {
            this.potionType = PotionType.REGEN;
        }
        this.strong = strong;
        this.extend = extend;
    }
    
    public int getData() {
        return this.data;
    }
    
    public PotionType getPotionType() {
        return this.potionType;
    }
    
    public boolean isStrong() {
        return this.strong;
    }
    
    public boolean isExtend() {
        return this.extend;
    }
    
    public static EnumPotion valueOfMaterialData(final int n) {
        EnumPotion[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final EnumPotion enumPotion = values[i];
            if (enumPotion.getData() == n) {
                return enumPotion;
            }
        }
        return EnumPotion.WATER;
    }
    
    public static boolean isPotion(final EnumMaterial enumMaterial) {
        return enumMaterial.getType().toString().toLowerCase().contains("potion");
    }
    
    public static boolean isSplashPotion(final EnumMaterial enumMaterial) {
        return enumMaterial == EnumMaterial.SPLASH_POTION;
    }
    
    public static boolean isLingeringPotion(final EnumMaterial enumMaterial) {
        return enumMaterial == EnumMaterial.LINGERING_POTION;
    }
}
