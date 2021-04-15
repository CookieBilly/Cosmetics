

package ws.billy.CookieGadgets.utils.discount;

import java.util.Iterator;
import ws.billy.CookieGadgets.configuration.CustomConfiguration;
import java.util.List;
import ws.billy.CookieGadgets.configuration.FileManager;

public class ItemCostDiscountManager
{
    public static void loadDiscountGroups() {
        final CustomConfiguration configFile = FileManager.getConfigFile();
        if (configFile.get("Item-Cost-Discount.Discount-Rates") != null) {
            for (final String str : configFile.getConfigurationSection("Item-Cost-Discount.Discount-Rates").getKeys(false)) {
                new ItemCostDiscount(str, configFile.getInt("Item-Cost-Discount.Discount-Rates." + str + ".Priority"), configFile.getString("Item-Cost-Discount.Discount-Rates." + str + ".Permission"), configFile.getInt("Item-Cost-Discount.Discount-Rates." + str + ".Rate"), configFile.getStringList("Item-Cost-Discount.Discount-Rates." + str + ".Lore.Enough-Mystery-Dust"), configFile.getStringList("Item-Cost-Discount.Discount-Rates." + str + ".Lore.Not-Enough-Mystery-Dust"));
            }
        }
        ItemCostDiscount.arrangeDiscountGroups();
    }
}
