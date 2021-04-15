

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.List;

public class GadgetFortuneCookie extends Gadget
{
    private boolean activated;
    private static List<String> playerMessages;
    private static List<String> messages;
    
    static {
        GadgetFortuneCookie.playerMessages = FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Fortune Cookie.Messages.Player");
        GadgetFortuneCookie.messages = FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Fortune Cookie.Messages.Fortune Cookie");
    }
    
    public GadgetFortuneCookie(final UUID uuid) {
        super(uuid, GadgetType.FORTUNE_COOKIE);
        this.activated = false;
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.activated = true;
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                if (!GadgetFortuneCookie.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetFortuneCookie.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetFortuneCookie.this.getPlayer()).getCurrentGadget().getType() != GadgetFortuneCookie.this.getType() || !GadgetFortuneCookie.this.activated) {
                    GadgetFortuneCookie.this.onClear();
                    this.cancel();
                    return;
                }
                if (this.step < GadgetFortuneCookie.playerMessages.size()) {
                    GadgetFortuneCookie.this.getPlayer().sendMessage(ChatUtil.format(GadgetFortuneCookie.playerMessages.get(this.step).replace("{PLAYER}", GadgetFortuneCookie.this.getPlayer().getName())));
                }
                else {
                    GadgetFortuneCookie.this.getPlayer().sendMessage(ChatUtil.format(GadgetFortuneCookie.messages.get(CookieGadgets.random().nextInt(GadgetFortuneCookie.messages.size()))));
                    GadgetFortuneCookie.this.clearAll();
                    this.cancel();
                }
                ++this.step;
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 55L);
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        this.activated = false;
    }
}
