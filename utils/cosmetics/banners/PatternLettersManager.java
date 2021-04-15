

package ws.billy.CookieGadgets.utils.cosmetics.banners;

import java.util.Arrays;
import ws.billy.CookieGadgets.utils.GDyeColor;

public class PatternLettersManager
{
    public static GPatterns bannerLetterB() {
        return new GPatterns(GDyeColor.BLACK, Arrays.asList(new GPattern(GDyeColor.YELLOW, GPatternType.STRIPE_RIGHT), new GPattern(GDyeColor.YELLOW, GPatternType.STRIPE_BOTTOM), new GPattern(GDyeColor.YELLOW, GPatternType.STRIPE_MIDDLE), new GPattern(GDyeColor.YELLOW, GPatternType.STRIPE_TOP), new GPattern(GDyeColor.BLACK, GPatternType.CURLY_BORDER), new GPattern(GDyeColor.YELLOW, GPatternType.SQUARE_TOP_LEFT), new GPattern(GDyeColor.YELLOW, GPatternType.SQUARE_BOTTOM_LEFT), new GPattern(GDyeColor.YELLOW, GPatternType.STRIPE_LEFT), new GPattern(GDyeColor.BLACK, GPatternType.BORDER)));
    }
}
