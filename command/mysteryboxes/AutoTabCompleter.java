

package ws.billy.CookieGadgets.command.mysteryboxes;

import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class AutoTabCompleter implements TabCompleter
{
    public List<String> onTabComplete(final CommandSender commandSender, final Command command, final String s, final String[] array) {
        if (array.length == 1) {
            final ArrayList<String> list = (ArrayList<String>)new ArrayList<Comparable>();
            final ArrayList<String> list2 = new ArrayList<String>();
            list2.add("check");
            list2.add("gift");
            list2.add("give");
            list2.add("giveall");
            list2.add("mode");
            if (!array[0].equals("")) {
                for (final String e : list2) {
                    if (e.toLowerCase().startsWith(array[0].toLowerCase())) {
                        list.add(e);
                    }
                }
            }
            else {
                final Iterator<String> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    list.add(iterator2.next());
                }
            }
            Collections.sort((List<Comparable>)list);
            return list;
        }
        if (array.length == 2 && (array[0].equalsIgnoreCase("mode") || array[0].equalsIgnoreCase("edit") || array[0].equalsIgnoreCase("setup"))) {
            final ArrayList<String> list3 = (ArrayList<String>)new ArrayList<Comparable>();
            final ArrayList<String> list4 = new ArrayList<String>();
            list4.add("add-vault");
            list4.add("info");
            list4.add("list");
            list4.add("near");
            list4.add("redefine");
            list4.add("remove-vault");
            list4.add("teleport");
            if (!array[1].equals("")) {
                for (final String e2 : list4) {
                    if (e2.toLowerCase().startsWith(array[1].toLowerCase())) {
                        list3.add(e2);
                    }
                }
            }
            else {
                final Iterator<String> iterator4 = list4.iterator();
                while (iterator4.hasNext()) {
                    list3.add(iterator4.next());
                }
            }
            Collections.sort((List<Comparable>)list3);
            return list3;
        }
        return null;
    }
}
