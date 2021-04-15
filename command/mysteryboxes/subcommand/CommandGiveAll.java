

package ws.billy.CookieGadgets.command.mysteryboxes.subcommand;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.mysteryboxes.MysteryBoxType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import java.util.HashMap;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.command.mysteryboxes.CommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.mysteryboxes.SubCommand;

public class CommandGiveAll extends SubCommand
{
    public CommandGiveAll() {
        super("/gmysteryboxes giveall <amount> [quality] [ex=7h/d/m/false]", "Give all online players mystery boxes.", "Give all online players mystery boxes.\n\n&71) Use 'ex=7h/7d/7m' if you want to change the expiry date\n&7   of the mystery boxes. (Default is 7 days)\n&72) Use 'msg=false' if you do not want to send message\n&7   to the player when they received mystery boxes.\n&73) Use 'reqperm=false' player doesn't require permission to open\n&7   mystery boxes.\n&74) Use 'c:(1:40,2:30,3:25,4:15,5:10)' if you want to customize\n&7   the chances between different quality of mystery boxes.", "CookieGadgets.mysteryboxes.giveall", new String[] { "giveall", "ga" }, true);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        this.onCommand((CommandSender)player, array);
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        this.onCommand(commandSender, array);
    }
    
    private void onCommand(final CommandSender commandSender, final String[] array) {
        if (array.length < 2 || array.length > 6) {
            CommandManager.printMessage(commandSender, this);
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(array[1]);
        }
        catch (NumberFormatException ex) {
            commandSender.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
            return;
        }
        if (int1 <= 0) {
            commandSender.sendMessage(MessageType.REQUIRED_POSITIVE_NUMBER.getFormatMessage());
            return;
        }
        if (array.length >= 3) {
            int int2 = 0;
            Long n = 604800000L;
            boolean b = true;
            boolean b2 = true;
            final HashMap<Integer, Float> hashMap = new HashMap<Integer, Float>();
            for (int i = 2; i <= array.length - 1; ++i) {
                final String s = array[i];
                if (s.startsWith("msg=")) {
                    if (s.equalsIgnoreCase("msg=false")) {
                        b = false;
                    }
                }
                else if (s.startsWith("ex=")) {
                    final String replace = s.replace("ex=", "");
                    if (replace.equalsIgnoreCase("false")) {
                        n = null;
                    }
                    else if (replace.contains("m")) {
                        final String replace2 = replace.replace("m", "");
                        try {
                            Integer.parseInt(replace2);
                        }
                        catch (NumberFormatException ex2) {
                            commandSender.sendMessage(MessageType.REQUIRED_TIME_FORMAT.getFormatMessage());
                            return;
                        }
                        n = Integer.valueOf(replace2) * 2592000 * 1000L;
                    }
                    else if (replace.contains("d")) {
                        final String replace3 = replace.replace("d", "");
                        try {
                            Integer.parseInt(replace3);
                        }
                        catch (NumberFormatException ex3) {
                            commandSender.sendMessage(MessageType.REQUIRED_TIME_FORMAT.getFormatMessage());
                            return;
                        }
                        n = Integer.valueOf(replace3) * 86400 * 1000L;
                    }
                    else {
                        if (!replace.contains("h")) {
                            commandSender.sendMessage(MessageType.REQUIRED_TIME_FORMAT.getFormatMessage());
                            return;
                        }
                        final String replace4 = replace.replace("h", "");
                        try {
                            Integer.parseInt(replace4);
                        }
                        catch (NumberFormatException ex4) {
                            commandSender.sendMessage(MessageType.REQUIRED_TIME_FORMAT.getFormatMessage());
                            return;
                        }
                        n = Integer.valueOf(replace4) * 3600 * 1000L;
                    }
                }
                else if (s.startsWith("reqperm=")) {
                    final String replace5 = s.replace("reqperm=", "");
                    b2 = (replace5.equalsIgnoreCase("true") || !replace5.equalsIgnoreCase("false"));
                }
                else {
                    if (s.startsWith("c:(") && s.endsWith(")")) {
                        try {
                            int index = s.indexOf("(");
                            for (int j = 1; j <= 5; ++j) {
                                final int index2 = s.indexOf((j == 5) ? ")" : ",", (j == 1) ? index : (index + 1));
                                final String substring = s.substring(index + 1, index2);
                                index = index2;
                                final int intValue = Integer.valueOf(substring.substring(0, 1));
                                hashMap.put(intValue, Float.valueOf(substring.replace(String.valueOf(intValue) + ":", "")));
                            }
                            continue;
                        }
                        catch (StringIndexOutOfBoundsException ex5) {
                            commandSender.sendMessage(MessageType.REQUIRED_FULL_DATA.getFormatMessage());
                            return;
                        }
                    }
                    try {
                        int2 = Integer.parseInt(array[2]);
                    }
                    catch (NumberFormatException ex6) {
                        commandSender.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
                        return;
                    }
                    if (int2 <= 0) {
                        commandSender.sendMessage(MessageType.REQUIRED_POSITIVE_NUMBER.getFormatMessage());
                        return;
                    }
                    if (int2 > 5) {
                        commandSender.sendMessage(MessageType.VALUE_BETWEEN_ONE_TO_FIVE.getFormatMessage());
                        return;
                    }
                }
            }
            int k = 0;
            for (final Player player : Bukkit.getOnlinePlayers()) {
                if (!player.isOnline()) {
                    continue;
                }
                if (int2 == 0) {
                    if (hashMap.isEmpty() || hashMap.size() != 5) {
                        CookieGadgets.getPlayerManager(player).giveMysteryBoxes((n == null) ? null : Long.valueOf(System.currentTimeMillis() + n), b2, null, int1);
                    }
                    else {
                        float n2 = 0.0f;
                        for (int l = 1; l <= 5; ++l) {
                            n2 += hashMap.get(l);
                        }
                        CookieGadgets.getPlayerManager(player).giveMysteryBoxes((n == null) ? null : Long.valueOf(System.currentTimeMillis() + n), b2, null, int1, n2, hashMap.get(1), hashMap.get(2), hashMap.get(3), hashMap.get(4), hashMap.get(5));
                    }
                }
                else {
                    CookieGadgets.getPlayerManager(player).giveMysteryBoxes(MysteryBoxType.valueOfByName("Normal Mystery Box #" + int2), (n == null) ? null : Long.valueOf(System.currentTimeMillis() + n), b2, null, int1);
                }
                ++k;
                if (!b) {
                    continue;
                }
                player.sendMessage(MessageType.PLAYER_RECEIVED_MYSTERY_BOXES.getFormatMessage().replace("{MYSTERY_BOXES}", String.valueOf(int1)).replace("{PLAYER}", commandSender.getName()));
            }
            commandSender.sendMessage(MessageType.PLAYER_GAVE_MYSTERY_BOXES_TO_ALL_PLAYERS.getFormatMessage().replace("{MYSTERY_BOXES}", String.valueOf(int1)).replace("{ONLINE}", String.valueOf(k)));
        }
        int m = 0;
        if (array.length == 2) {
            for (final Player player2 : Bukkit.getOnlinePlayers()) {
                if (!player2.isOnline()) {
                    continue;
                }
                CookieGadgets.getPlayerManager(player2).giveMysteryBoxes(System.currentTimeMillis() + 604800000L, true, null, int1);
                player2.sendMessage(MessageType.PLAYER_RECEIVED_MYSTERY_BOXES.getFormatMessage().replace("{MYSTERY_BOXES}", String.valueOf(int1)).replace("{PLAYER}", commandSender.getName()));
                ++m;
            }
            commandSender.sendMessage(MessageType.PLAYER_GAVE_MYSTERY_BOXES_TO_ALL_PLAYERS.getFormatMessage().replace("{MYSTERY_BOXES}", String.valueOf(int1)).replace("{ONLINE}", String.valueOf(m)));
        }
    }
}
