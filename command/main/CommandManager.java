

package ws.billy.CookieGadgets.command.main;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.WorldUtils;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.command.Command;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import java.util.List;
import java.util.Arrays;
import org.bukkit.plugin.Plugin;
import java.util.ArrayList;
import org.bukkit.command.CommandExecutor;

public class CommandManager implements CommandExecutor
{
    public static ArrayList<SubCommand> subCmds;
    public static ArrayList<SubCommand> consoleSubCmds;
    
    static {
        CommandManager.subCmds = new ArrayList<SubCommand>();
        CommandManager.consoleSubCmds = new ArrayList<SubCommand>();
    }
    
    public CommandManager(final Plugin plugin) {
        plugin.getServer().getPluginCommand("CookieGadgets").setExecutor((CommandExecutor)this);
        plugin.getServer().getPluginCommand("CookieGadgets").setAliases((List)Arrays.asList("gmenu"));
    }
    
    public void registerCommand(final SubCommand subCommand) {
        if (!CommandManager.subCmds.contains(subCommand)) {
            CommandManager.subCmds.add(subCommand);
        }
        if (subCommand.canConsoleUse() && !CommandManager.consoleSubCmds.contains(subCommand)) {
            CommandManager.consoleSubCmds.add(subCommand);
        }
    }
    
    public static void printMessage(final CommandSender commandSender, final String str, final String s, String str2, final boolean b) {
        if (commandSender instanceof Player) {
            if (str2 == null) {
                str2 = s;
            }
            CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(" &e" + str + " &b- " + s)).suggestCommand(str).showText(ChatUtil.format("&b" + str + "\n&7" + str2)).send((Player)commandSender);
        }
        else {
            commandSender.sendMessage(ChatUtil.format(" &e" + str + " &b- " + s));
        }
    }
    
    public static void printMessage(final CommandSender commandSender, final SubCommand subCommand) {
        if (commandSender instanceof Player) {
            CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(" &e" + subCommand.getUsage() + " &b- " + subCommand.getDescription())).suggestCommand(subCommand.getUsage()).showText(ChatUtil.format("&b" + subCommand.getUsage() + "\n&7" + subCommand.getHoverText())).send((Player)commandSender);
        }
        else {
            commandSender.sendMessage(ChatUtil.format(" &e" + subCommand.getUsage() + " &b- " + subCommand.getDescription()));
        }
    }
    
    public static void printHelpMessage(final CommandSender commandSender, int n) {
        final int maxPagesAmount = GInventory.getMaxPagesAmount(8, CommandManager.subCmds.size());
        final int maxPagesAmount2 = GInventory.getMaxPagesAmount(8, CommandManager.consoleSubCmds.size());
        if (n > ((commandSender instanceof Player) ? maxPagesAmount : maxPagesAmount2)) {
            n = 1;
        }
        final int n2 = (commandSender instanceof Player) ? CommandManager.subCmds.size() : CommandManager.consoleSubCmds.size();
        int n3 = 1;
        if (n > 1) {
            n3 = 8 * (n - 1) + 1;
        }
        int n4 = 8;
        if (n2 < 8) {
            n4 = n2;
        }
        if (n > 1) {
            if (n2 >= 8 * n) {
                n4 = 8 * n;
            }
            else {
                n4 = n2;
            }
        }
        if (commandSender instanceof Player) {
            int n5 = 0;
            for (int n6 = n3; n6 <= n4 && n6 <= CommandManager.subCmds.size(); ++n6) {
                final SubCommand subCommand = CommandManager.subCmds.get(n6 - 1);
                if (subCommand.getPermission() == null || commandSender.hasPermission(subCommand.getPermission())) {
                    CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(" &e" + subCommand.getUsage() + " &b- " + subCommand.getDescription())).suggestCommand(subCommand.getUsage()).showText(ChatUtil.format("&b" + subCommand.getUsage() + "\n&7" + subCommand.getHoverText())).send((Player)commandSender);
                    ++n5;
                }
            }
            if (n >= maxPagesAmount) {
                if (n > 1 && maxPagesAmount > 1) {
                    CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format("&3Use &b/gmenu help " + (maxPagesAmount - 1) + " &3to display page " + (maxPagesAmount - 1) + "!")).runCommand("/gmenu help " + (maxPagesAmount - 1)).send((Player)commandSender);
                }
            }
            else if (n5 >= 8) {
                CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format("&3Use &b/gmenu help " + (n + 1) + " &3to display page " + (n + 1) + "!")).runCommand("/gmenu help " + (n + 1)).send((Player)commandSender);
            }
        }
        else {
                for (int index = n3; index <= n4 && index <= CommandManager.consoleSubCmds.size(); ++index) {
                final SubCommand subCommand2 = CommandManager.consoleSubCmds.get(index - 1);
                if (!subCommand2.canConsoleUse()) {
                    final SubCommand subCommand3 = CommandManager.consoleSubCmds.get(index);
                    ++n4;
                }
                else {
                    commandSender.sendMessage(ChatUtil.format(" &e" + subCommand2.getUsage() + " &b- " + subCommand2.getDescription()));
                }
            }
            if (n >= maxPagesAmount2) {
                if (n > 1 && maxPagesAmount2 > 1) {
                    commandSender.sendMessage(ChatUtil.format("&3Use &b/gmenu help " + (maxPagesAmount2 - 1) + " &3to display page " + (maxPagesAmount2 - 1) + "!"));
                }
            }
            else {
                commandSender.sendMessage(ChatUtil.format("&3Use &b/gmenu help " + (n + 1) + " &3to display page " + (n + 1) + "!"));
            }
        }
    }
    
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] array) {
        if (!(commandSender instanceof Player) && !(commandSender instanceof CommandSender)) {
            commandSender.sendMessage(MessageType.NOT_ALLOWED_FROM_COMMAND_SENDER.getFormatMessage());
            return true;
        }
        if (array == null || array.length == 0) {
            if (commandSender instanceof Player) {
                if (!WorldUtils.isWorldEnabled((Player)commandSender, true)) {
                    return true;
                }
                printHelpMessage(commandSender, 1);
            }
            else {
                printHelpMessage(commandSender, 1);
            }
            return true;
        }
        for (final SubCommand subCommand : CommandManager.subCmds) {
            if (subCommand.contains(array[0])) {
                if (commandSender instanceof Player && !WorldUtils.isWorldEnabled((Player)commandSender, true)) {
                    return true;
                }
                if (subCommand.getPermission() != null && !commandSender.hasPermission(subCommand.getPermission())) {
                    commandSender.sendMessage(MessageType.NO_PERMISSION.getFormatMessage().replace("{PERMISSION}", subCommand.getPermission()));
                    return true;
                }
                if (commandSender instanceof Player) {
                    subCommand.onCommandPlayer((Player)commandSender, array);
                }
                else if (commandSender instanceof CommandSender) {
                    subCommand.onOtherCommandSender(commandSender, array);
                }
                return true;
            }
        }
        if (commandSender instanceof Player) {
            if (!WorldUtils.isWorldEnabled((Player)commandSender, true)) {
                return true;
            }
            printHelpMessage(commandSender, 1);
        }
        else {
            printHelpMessage(commandSender, 1);
        }
        return true;
    }
}
