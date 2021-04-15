

package ws.billy.CookieGadgets.utils.discount;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ItemCostDiscount
{
    private static ArrayList<ItemCostDiscount> UNARRANGED_GROUPS;
    private static ArrayList<ItemCostDiscount> ARRANGED_GROUPS;
    private String name;
    private int priority;
    private String permission;
    private int rate;
    private List<String> enoughMysteryDustLore;
    private List<String> notEnoughMysteryDustLore;
    
    static {
        ItemCostDiscount.UNARRANGED_GROUPS = new ArrayList<ItemCostDiscount>();
        ItemCostDiscount.ARRANGED_GROUPS = new ArrayList<ItemCostDiscount>();
    }
    
    public ItemCostDiscount(final String name, final int priority, final String permission, int abs, final List<String> enoughMysteryDustLore, final List<String> notEnoughMysteryDustLore) {
        this.name = name;
        this.priority = priority;
        this.permission = permission;
        abs = Math.abs(abs);
        if (abs > 100) {
            abs = 100;
        }
        this.rate = abs;
        this.enoughMysteryDustLore = enoughMysteryDustLore;
        this.notEnoughMysteryDustLore = notEnoughMysteryDustLore;
        if (!ItemCostDiscount.UNARRANGED_GROUPS.contains(this)) {
            ItemCostDiscount.UNARRANGED_GROUPS.add(this);
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public int getDiscountRate() {
        return this.rate;
    }
    
    public int getPriceAfterDiscount(final int n) {
        return (int)Math.ceil(n * (double)(100 - this.rate) / 100.0);
    }
    
    public List<String> getEnoughMysteryDustLore() {
        return this.enoughMysteryDustLore;
    }
    
    public List<String> getNotEnoughMysteryDustLore() {
        return this.notEnoughMysteryDustLore;
    }
    
    public static ArrayList<ItemCostDiscount> getUnarrangedGroups() {
        return ItemCostDiscount.UNARRANGED_GROUPS;
    }
    
    public static ArrayList<ItemCostDiscount> getArrangedGroups() {
        if (!ItemCostDiscount.UNARRANGED_GROUPS.isEmpty() && ItemCostDiscount.ARRANGED_GROUPS.isEmpty()) {
            arrangeDiscountGroups();
        }
        return ItemCostDiscount.ARRANGED_GROUPS;
    }
    
    public static void arrangeDiscountGroups() {
        ArrayList<ItemCostDiscount> arrange = new ArrayList<ItemCostDiscount>();
        final Iterator<ItemCostDiscount> iterator = ItemCostDiscount.UNARRANGED_GROUPS.iterator();
        while (iterator.hasNext()) {
            arrange = arrange(arrange, iterator.next());
        }
        ItemCostDiscount.ARRANGED_GROUPS = arrange;
    }
    
    private static ArrayList<ItemCostDiscount> arrange(final ArrayList<ItemCostDiscount> list, final ItemCostDiscount e) {
        if (list.isEmpty()) {
            list.add(e);
            return list;
        }
        final ArrayList<ItemCostDiscount> list2 = new ArrayList<ItemCostDiscount>();
        int n = 1;
        for (final ItemCostDiscount itemCostDiscount : list) {
            if (n == 0 || itemCostDiscount.getPriority() <= e.getPriority()) {
                list2.add(itemCostDiscount);
            }
            else {
                list2.add(e);
                list2.add(itemCostDiscount);
                n = 0;
            }
        }
        if (n != 0) {
            list2.add(e);
        }
        return list2;
    }
}
