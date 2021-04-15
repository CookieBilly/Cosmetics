

package ws.billy.CookieGadgets.utils.cosmetics.banners;

import java.util.Iterator;
import java.util.ArrayList;
import org.bukkit.block.banner.Pattern;
import java.util.List;
import ws.billy.CookieGadgets.utils.GDyeColor;

public class GPatterns
{
    private GDyeColor baseColor;
    private List<Pattern> patterns;
    private List<GPattern> gPatterns;
    
    public GPatterns(final GDyeColor baseColor, final List<GPattern> gPatterns) {
        this.patterns = new ArrayList<Pattern>();
        this.baseColor = baseColor;
        this.gPatterns = gPatterns;
        final Iterator<GPattern> iterator = gPatterns.iterator();
        while (iterator.hasNext()) {
            this.patterns.add(iterator.next().getPattern());
        }
    }
    
    public GDyeColor getBaseColor() {
        return this.baseColor;
    }
    
    public List<GPattern> getGPatterns() {
        return this.gPatterns;
    }
    
    public List<Pattern> getPatterns() {
        return this.patterns;
    }
}
