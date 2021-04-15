

package ws.billy.CookieGadgets.utils.cosmetics.banners;

import org.bukkit.block.banner.Pattern;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.PatternType;

public enum GPatternType
{
    BASE("BASE", 0, "b", PatternType.BASE), 
    BORDER("BORDER", 1, "bo", PatternType.BORDER), 
    BRICKS("BRICKS", 2, "bri", PatternType.BRICKS), 
    CIRCLE_MIDDLE("CIRCLE_MIDDLE", 3, "mc", PatternType.CIRCLE_MIDDLE), 
    CREEPER("CREEPER", 4, "cre", PatternType.CREEPER), 
    CROSS("CROSS", 5, "cr", PatternType.CROSS), 
    CURLY_BORDER("CURLY_BORDER", 6, "cbo", PatternType.CURLY_BORDER), 
    DIAGONAL_LEFT("DIAGONAL_LEFT", 7, "ld", PatternType.DIAGONAL_LEFT), 
    DIAGONAL_LEFT_MIRROR("DIAGONAL_LEFT_MIRROR", 8, "lud", PatternType.DIAGONAL_LEFT_MIRROR), 
    DIAGONAL_RIGHT("DIAGONAL_RIGHT", 9, "rud", PatternType.DIAGONAL_RIGHT), 
    DIAGONAL_RIGHT_MIRROR("DIAGONAL_RIGHT_MIRROR", 10, "rd", PatternType.DIAGONAL_RIGHT_MIRROR), 
    FLOWER("FLOWER", 11, "flo", PatternType.FLOWER), 
    GRADIENT("GRADIENT", 12, "gra", PatternType.GRADIENT), 
    GRADIENT_UP("GRADIENT_UP", 13, "gru", PatternType.GRADIENT_UP), 
    HALF_HORIZONTAL("HALF_HORIZONTAL", 14, "hh", PatternType.HALF_HORIZONTAL), 
    HALF_HORIZONTAL_MIRROR("HALF_HORIZONTAL_MIRROR", 15, "hhb", PatternType.HALF_HORIZONTAL_MIRROR), 
    HALF_VERTICAL("HALF_VERTICAL", 16, "vh", PatternType.HALF_VERTICAL), 
    HALF_VERTICAL_MIRROR("HALF_VERTICAL_MIRROR", 17, "vhr", PatternType.HALF_VERTICAL_MIRROR), 
    MOJANG("MOJANG", 18, "moj", PatternType.MOJANG), 
    RHOMBUS_MIDDLE("RHOMBUS_MIDDLE", 19, "mr", PatternType.RHOMBUS_MIDDLE), 
    SKULL("SKULL", 20, "sku", PatternType.SKULL), 
    SQUARE_BOTTOM_LEFT("SQUARE_BOTTOM_LEFT", 21, "bl", PatternType.SQUARE_BOTTOM_LEFT), 
    SQUARE_BOTTOM_RIGHT("SQUARE_BOTTOM_RIGHT", 22, "br", PatternType.SQUARE_BOTTOM_RIGHT), 
    SQUARE_TOP_LEFT("SQUARE_TOP_LEFT", 23, "tl", PatternType.SQUARE_TOP_LEFT), 
    SQUARE_TOP_RIGHT("SQUARE_TOP_RIGHT", 24, "tr", PatternType.SQUARE_TOP_RIGHT), 
    STRAIGHT_CROSS("STRAIGHT_CROSS", 25, "sc", PatternType.STRAIGHT_CROSS), 
    STRIPE_BOTTOM("STRIPE_BOTTOM", 26, "bs", PatternType.STRIPE_BOTTOM), 
    STRIPE_CENTER("STRIPE_CENTER", 27, "cs", PatternType.STRIPE_CENTER), 
    STRIPE_DOWNLEFT("STRIPE_DOWNLEFT", 28, "dls", PatternType.STRIPE_DOWNLEFT), 
    STRIPE_DOWNRIGHT("STRIPE_DOWNRIGHT", 29, "drs", PatternType.STRIPE_DOWNRIGHT), 
    STRIPE_LEFT("STRIPE_LEFT", 30, "ls", PatternType.STRIPE_LEFT), 
    STRIPE_MIDDLE("STRIPE_MIDDLE", 31, "ms", PatternType.STRIPE_MIDDLE), 
    STRIPE_RIGHT("STRIPE_RIGHT", 32, "rs", PatternType.STRIPE_RIGHT), 
    STRIPE_SMALL("STRIPE_SMALL", 33, "ss", PatternType.STRIPE_SMALL), 
    STRIPE_TOP("STRIPE_TOP", 34, "ts", PatternType.STRIPE_TOP), 
    TRIANGLE_BOTTOM("TRIANGLE_BOTTOM", 35, "bt", PatternType.TRIANGLE_BOTTOM), 
    TRIANGLE_TOP("TRIANGLE_TOP", 36, "tt", PatternType.TRIANGLE_TOP), 
    TRIANGLES_BOTTOM("TRIANGLES_BOTTOM", 37, "bts", PatternType.TRIANGLES_BOTTOM), 
    TRIANGLES_TOP("TRIANGLES_TOP", 38, "tts", PatternType.TRIANGLES_TOP);
    
    private String identifier;
    private PatternType patternType;
    
    private GPatternType(final String name, final int ordinal, final String identifier, final PatternType patternType) {
        this.identifier = identifier;
        this.patternType = patternType;
    }
    
    public String getIdentifier() {
        return this.identifier;
    }
    
    public PatternType getPatternType() {
        return this.patternType;
    }
    
    public Pattern toColor(final DyeColor dyeColor) {
        return new Pattern(dyeColor, this.patternType);
    }
    
    public static PatternType getByIdentifier(final String anotherString) {
        GPatternType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GPatternType gPatternType = values[i];
            if (gPatternType.getIdentifier().equalsIgnoreCase(anotherString)) {
                return gPatternType.getPatternType();
            }
        }
        return null;
    }
}
