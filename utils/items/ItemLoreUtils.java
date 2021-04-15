

package ws.billy.CookieGadgets.utils.items;

import java.util.Iterator;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import ws.billy.CookieGadgets.utils.ChatUtil;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;

public class ItemLoreUtils
{
    public static ItemStack addLore(final ItemStack itemStack, final String s) {
        final ItemStack itemStack2 = new ItemStack(itemStack);
        final ItemMeta itemMeta = itemStack2.getItemMeta();
        if (s != null) {
            List<String> lore = (List<String>)itemMeta.getLore();
            if (lore == null) {
                lore = new ArrayList<String>();
            }
            lore.add(ChatUtil.format(s));
            itemMeta.setLore((List)lore);
        }
        itemStack2.setItemMeta(itemMeta);
        return itemStack2;
    }
    
    public static ItemStack addLore(final ItemStack itemStack, final List<String> list) {
        final ItemStack itemStack2 = new ItemStack(itemStack);
        final ItemMeta itemMeta = itemStack2.getItemMeta();
        if (list != null) {
            List<String> lore = (List<String>)itemMeta.getLore();
            if (lore == null) {
                lore = new ArrayList<String>();
            }
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                lore.add(ChatUtil.format(iterator.next()));
            }
            itemMeta.setLore((List)lore);
        }
        itemStack2.setItemMeta(itemMeta);
        return itemStack2;
    }
    
    public static List<String> combineLore(final String e, final List<String> list) {
        final ArrayList<String> list2 = new ArrayList<String>();
        if (e != null) {
            list2.add(e);
        }
        if (list != null) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                list2.add(ChatUtil.format(iterator.next()));
            }
        }
        return list2;
    }
    
    public static List<String> combineLore(final List<String> list, final String e) {
        final ArrayList<String> list2 = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                list2.add(ChatUtil.format(iterator.next()));
            }
        }
        if (e != null) {
            list2.add(e);
        }
        return list2;
    }
    
    public static List<String> combineLore(final List<String> list, final List<String> list2) {
        final ArrayList<String> list3 = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                list3.add(ChatUtil.format(iterator.next()));
            }
        }
        if (list2 != null) {
            final Iterator<String> iterator2 = list2.iterator();
            while (iterator2.hasNext()) {
                list3.add(ChatUtil.format(iterator2.next()));
            }
        }
        return list3;
    }
    
    public static List<String> combineLore(final List<String> list, final String... array) {
        final ArrayList<String> list2 = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                list2.add(ChatUtil.format(iterator.next()));
            }
        }
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                list2.add(ChatUtil.format(array[i]));
            }
        }
        return list2;
    }
}
