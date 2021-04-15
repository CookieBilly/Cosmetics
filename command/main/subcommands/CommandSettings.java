

package ws.billy.CookieGadgets.command.main.subcommands;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.BooleanUtil;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandSettings extends SubCommand
{
    private String[] settings;
    
    public CommandSettings() {
        super("/gmenu settings <setting> <value>", "Modify personal settings.", null, "CookieGadgets.commands.settings", new String[] { "settings", "setting" }, false);
        this.settings = new String[] { "bypasscooldown", "selfmorphview" };
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length != 2 && array.length != 3) {
            CommandManager.printMessage((CommandSender)player, new CommandSettings());
            return;
        }
        String s = "bypasscooldown";
        if (array.length >= 2) {
            String[] settings;
            for (int length = (settings = this.settings).length, i = 0; i < length; ++i) {
                final String s2 = settings[i];
                if (s2.equalsIgnoreCase(array[1])) {
                    s = s2;
                }
            }
            if (s.equalsIgnoreCase("bypasscooldown") && !array[1].equalsIgnoreCase("bypasscooldown")) {
                CommandManager.printMessage((CommandSender)player, new CommandSettings());
                final StringBuilder sb = new StringBuilder();
                for (int j = 0; j < this.settings.length; ++j) {
                    sb.append(String.valueOf(this.settings[j].toString().replace(" ", "_")) + ((j != this.settings.length - 1) ? "&f, &c&l" : ""));
                }
                player.sendMessage(ChatUtil.format("&bSettings&e: &c&l" + sb.toString()));
                return;
            }
        }
        if (array.length != 2) {
            boolean booleanValue = true;
            if (array.length == 3) {
                if (!BooleanUtil.isTrue(array[2]) && !BooleanUtil.isFalse(array[2])) {
                    player.sendMessage(MessageType.ONLY_BOOLEAN_VALUE_CAN_BE_ACCEPTED.getFormatMessage());
                    return;
                }
                booleanValue = BooleanUtil.getBooleanValue(array[2]);
            }
            if (s.equalsIgnoreCase("bypasscooldown")) {
                if (PermissionUtils.noPermission(player, EnumPermission.BYPASS_COOLDOWN.getPermission(), true)) {
                    return;
                }
                CookieGadgets.getPlayerManager(player).setBypassCooldown(booleanValue);
            }
            else if (s.equalsIgnoreCase("selfmorphview")) {
                CookieGadgets.getPlayerManager(player).setSeeSelfMorph(booleanValue);
            }
            return;
        }
        if (s.equalsIgnoreCase("bypasscooldown")) {
            final Iterator<String> iterator = EnumItem.SETTINGS_STATUS.getLore().iterator();
            while (iterator.hasNext()) {
                player.sendMessage(ChatUtil.format(iterator.next()).replace("{SETTING}", MessageType.COOLDOWN_BYPASS.getFormatMessage()).replace("{STATUS}", CookieGadgets.getPlayerManager(player).isBypassCooldown() ? MessageType.ENABLED.getFormatMessage() : MessageType.DISABLED.getFormatMessage()));
            }
            return;
        }
        if (s.equalsIgnoreCase("selfmorphview")) {
            final Iterator<String> iterator2 = EnumItem.SETTINGS_STATUS.getLore().iterator();
            while (iterator2.hasNext()) {
                player.sendMessage(ChatUtil.format(iterator2.next()).replace("{SETTING}", MessageType.SELF_MORPH_VIEW.getFormatMessage()).replace("{STATUS}", CookieGadgets.getPlayerManager(player).canSeeSelfMorph() ? MessageType.ENABLED.getFormatMessage() : MessageType.DISABLED.getFormatMessage()));
            }
        }
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        this.notAllowed(commandSender);
    }
}
