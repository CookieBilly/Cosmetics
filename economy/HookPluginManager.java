

package ws.billy.CookieGadgets.economy;

import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.log.LoggerManager;
import ws.billy.CookieGadgets.CookieGadgets;

public class HookPluginManager
{
    private CookieGadgets CookieGadgets;
    
    public HookPluginManager(final CookieGadgets CookieGadgets) {
        this.CookieGadgets = CookieGadgets;
    }
    
    public void hookPlugins(final GStorage gStorage) {
        if (gStorage.getPluginName() == null) {
            LoggerManager.info("[MysteryDust] " + gStorage.getName() + " hooked.");
            return;
        }
        if (Bukkit.getPluginManager().isPluginEnabled(gStorage.getPluginName())) {
            try {
                if (!CookieGadgets.getGEconomyProvider().hook()) {
                    LoggerManager.consoleMessage(ChatUtil.format("&cFailed to hook " + gStorage.getName() + "!"));
                    this.CookieGadgets.getServer().getPluginManager().disablePlugin((Plugin)this.CookieGadgets);
                    return;
                }
                LoggerManager.info("[MysteryDust] " + gStorage.getName() + " hooked.");
                return;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                LoggerManager.consoleMessage(ChatUtil.format("&cFailed to hook " + gStorage.getName() + "!"));
                this.CookieGadgets.getServer().getPluginManager().disablePlugin((Plugin)this.CookieGadgets);
                return;
            }
        }
        LoggerManager.consoleMessage(ChatUtil.format("&cDoes not found " + gStorage.getName() + " plugin. &cFailed to hook " + gStorage.getName() + "!"));
        this.CookieGadgets.getServer().getPluginManager().disablePlugin((Plugin)this.CookieGadgets);
    }
}
