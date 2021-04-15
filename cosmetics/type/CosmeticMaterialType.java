

package ws.billy.CookieGadgets.cosmetics.type;

import java.util.List;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.GMaterial;

public class CosmeticMaterialType extends CosmeticType
{
    private GMaterial material;
    
    public CosmeticMaterialType(final Category category, final String s, final String s2, final GMaterial material, final String s3, final int n, final Rarity rarity, final List<String> list, final List<String> list2) {
        super(category, s, s2, s3, n, rarity, list, list2);
        this.material = material;
    }
    
    public GMaterial getMaterial() {
        return this.material;
    }
}
