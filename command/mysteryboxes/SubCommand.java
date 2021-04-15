

package ws.billy.CookieGadgets.command.mysteryboxes;

import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;

public abstract class SubCommand
{
    private String usage;
    private String description;
    private String hoverText;
    private String permission;
    private String[] aliases;
    private boolean canConsoleUse;
    
    public SubCommand(final String usage, final String description, final String hoverText, final String permission, final String[] aliases, final boolean canConsoleUse) {
        this.usage = usage;
        this.description = description;
        this.hoverText = hoverText;
        this.permission = permission;
        this.aliases = aliases;
        this.canConsoleUse = canConsoleUse;
    }
    
    public String getUsage() {
        return this.usage;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getHoverText() {
        if (this.hoverText == null) {
            return this.description;
        }
        return this.hoverText;
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public String[] getAliases() {
        return this.aliases;
    }
    
    public boolean canConsoleUse() {
        return this.canConsoleUse;
    }
    
    public boolean contains(final String s) {
        return Arrays.asList(this.aliases) != null && Arrays.asList(this.aliases).contains(s.toLowerCase());
    }
    
    protected abstract void onCommandPlayer(final Player p0, final String[] p1);
    
    protected abstract void onOtherCommandSender(final CommandSender p0, final String[] p1);
    
    protected void notAllowed(final CommandSender commandSender) {
        commandSender.sendMessage(MessageType.NOT_ALLOWED_FROM_CONSOLE.getFormatMessage());
    }
}
