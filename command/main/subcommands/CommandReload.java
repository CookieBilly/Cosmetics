

package ws.billy.CookieGadgets.command.main.subcommands;

import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.utils.PluginUtils;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.economy.GStorage;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandReload extends SubCommand
{
    public CommandReload() {
        super("/gmenu reload", "Reload the plugin.", null, "CookieGadgets.commands.reload", new String[] { "reload", "rl" }, true);
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
        if (array.length > 1) {
            CommandManager.printMessage(commandSender, new CommandReload());
            return;
        }
        Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
            if (GStorage.isCustomStorage(CookieGadgets.getCookieGadgetsData().getMysteryDustStorage())) {
                commandSender.sendMessage(ChatUtil.format("&cYou're not allowed to reload the plugin as you're using custom economy storage!"));
            }
            else {
                commandSender.sendMessage(ChatUtil.format("&cUse this command in your own risk! It might cause bugs and memory leaks."));
                commandSender.sendMessage(MessageType.RELOADING_PLUGIN.getFormatMessage());
                try {
                    PluginUtils.reload(Bukkit.getServer().getPluginManager().getPlugin("CookieGadgets"));
                    commandSender.sendMessage(MessageType.RELOADED_PLUGIN.getFormatMessage());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
