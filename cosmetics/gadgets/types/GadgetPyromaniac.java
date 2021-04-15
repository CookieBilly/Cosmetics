

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;

public class GadgetPyromaniac extends Gadget
{
    private boolean activated;
    
    public GadgetPyromaniac(final UUID uuid) {
        super(uuid, GadgetType.PYROMANIAC);
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
        this.getPlayer().setFireTicks(200);
        CookieGadgets.getPlayerManager(this.getPlayer()).disableFireDamage();
        this.activated = true;
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                if (!GadgetPyromaniac.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetPyromaniac.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetPyromaniac.this.getPlayer()).getCurrentGadget().getType() != GadgetPyromaniac.this.getType()) {
                    return;
                }
                GadgetPyromaniac.this.clearAll();
            }
        }, 200L);
    }
    
    @Override
    public void onUpdate() {
        if (this.activated) {
            if (this.getPlayer().getFireTicks() <= 0) {
                this.getPlayer().setFireTicks(200);
            }
            ParticleEffect.LAVA.display(this.getPlayer().getLocation(), 0.3f, 0.2f, 0.3f, 0.0f, 1);
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        this.getPlayer().setFireTicks(0);
        CookieGadgets.getPlayerManager(this.getPlayer()).enableFireDamage();
        this.activated = false;
    }
}
