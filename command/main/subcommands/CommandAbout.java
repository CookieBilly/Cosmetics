

package ws.billy.CookieGadgets.command.main.subcommands;

import ws.billy.CookieGadgets.command.main.CommandManager;
import java.io.IOException;
import ws.billy.CookieGadgets.utils.debug.PastebinPoster;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.debug.ContentBuilder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandAbout extends SubCommand
{
    public CommandAbout() {
        super("/gmenu about", "Display information about the plugin.", null, "CookieGadgets.commands.about", new String[] { "about", "info", "information", "version" }, true);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length > 1) {
            array[1].equalsIgnoreCase("-test");
        }
        this.onCommand((CommandSender)player, array);
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        this.onCommand(commandSender, array);
    }
    
    private void onCommand(final CommandSender commandSender, final String[] array) {
        if (array[1].equalsIgnoreCase("-storage")) {
            commandSender.sendMessage(ChatUtil.format("&7Data Storage: &b" + CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName()));
            commandSender.sendMessage(ChatUtil.format("&7Mystery Dust Storage: &b" + String.valueOf((CookieGadgets.getCookieGadgetsData().getMysteryDustStorage() == null) ? "&cError" : CookieGadgets.getCookieGadgetsData().getMysteryDustStorage().getName())));
            return;
        }
        if (array[1].equalsIgnoreCase("-diagnose") || array[1].equalsIgnoreCase("-diagnoses")) {
            final ContentBuilder contentBuilder = new ContentBuilder();
            contentBuilder.appendLine("Server Version: " + Bukkit.getVersion());
            contentBuilder.appendLine("Plugin Version: " + CookieGadgets.getInstance().getDescription().getVersion());
            contentBuilder.appendLine("Config Version: " + FileManager.getConfigFile().getString("Config-Version"));
            contentBuilder.appendLine("Morphs: " + (CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() ? "Lib's Disguises" : (CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled() ? "iDisguise" : "Disable")));
            contentBuilder.appendLine("ProtocolLib: " + ((Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) ? "Not Found" : Bukkit.getPluginManager().getPlugin("ProtocolLib").getDescription().getVersion()));
            contentBuilder.appendLine("Data Storage: " + CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName());
            contentBuilder.appendLine("Mystery Dust Storage: " + String.valueOf((CookieGadgets.getCookieGadgetsData().getMysteryDustStorage() == null) ? "Error" : CookieGadgets.getCookieGadgetsData().getMysteryDustStorage().getName()));
            contentBuilder.appendLine("Purchase Command Status: " + CookieGadgets.getCookieGadgetsData().isCosmeticItemPurchaseCommandEnabled());
            contentBuilder.appendLine("Purchase Command: " + CookieGadgets.getCookieGadgetsData().getCosmeticItemPurchaseCommand());
            contentBuilder.appendLine("Found Loot Command Status: " + CookieGadgets.getCookieGadgetsData().isFoundMysteryBoxLootCommandEnabled());
            contentBuilder.appendLine("Found Loot Command: " + CookieGadgets.getCookieGadgetsData().getFoundMysteryBoxLootCommand());
            return;
        }
        CommandManager.printMessage(commandSender, new CommandAbout());
    }
}
