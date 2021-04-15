

package ws.billy.CookieGadgets.command.mysterydust;

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
            list2.add("add");
            list2.add("check");
            list2.add("pay");
            list2.add("remove");
            list2.add("set");
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
        return null;
    }
}
