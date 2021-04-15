

package ws.billy.CookieGadgets.utils.cosmetics.banners;

import org.bukkit.block.banner.Pattern;
import org.bukkit.DyeColor;
import ws.billy.CookieGadgets.utils.GDyeColor;

public class GPattern
{
    private GDyeColor color;
    private String patternIdentifier;
    private GPatternType patternType;
    
    public GPattern(final GDyeColor color, final String patternIdentifier) {
        this.color = color;
        this.patternIdentifier = patternIdentifier;
        this.patternType = null;
    }
    
    public GPattern(final GDyeColor color, final GPatternType patternType) {
        this.color = color;
        this.patternIdentifier = null;
        this.patternType = patternType;
    }
    
    public DyeColor getColor() {
        return this.color.getColor();
    }
    
    public String getPatternIdentifier() {
        return this.patternIdentifier;
    }
    
    public GPatternType getPatternType() {
        return this.patternType;
    }
    
    public Pattern getPattern() {
        if (this.patternIdentifier != null) {
            return new Pattern(this.color.getColor(), GPatternType.getByIdentifier(this.patternIdentifier));
        }
        return new Pattern(this.color.getColor(), this.patternType.getPatternType());
    }
}
