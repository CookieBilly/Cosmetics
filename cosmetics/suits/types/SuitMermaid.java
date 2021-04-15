

package ws.billy.CookieGadgets.cosmetics.suits.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.cosmetics.morphs.GDisguiseType;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;

public class SuitMermaid extends Suit
{
    private boolean morphed;
    
    public SuitMermaid(final UUID uuid) {
        super(uuid, SuitType.MERMAID);
        this.morphed = false;
    }
    
    public void onUpdate() {
        if (!CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() && !CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) {
            return;
        }
        if (this.getPlayer().getLocation().getBlock().isLiquid() && !this.morphed) {
            if (CookieGadgets.getPlayerManager(this.getPlayer()).getEquippedMorph() != null) {
                CookieGadgets.getPlayerManager(this.getPlayer()).unequipMorph();
            }
            ParticleEffect.CLOUD.display(this.getPlayer().getLocation(), 0.5f, 0.5f, 0.5f, 12);
            CookieGadgets.getGDisguise().disguise(this.getPlayer(), GDisguiseType.SQUID);
            this.morphed = true;
        }
        else if (!this.getPlayer().getLocation().getBlock().isLiquid()) {
            CookieGadgets.getGDisguise().undisguise(this.getPlayer());
            this.morphed = false;
        }
    }
    
    @Override
    public void onClear() {
        this.morphed = false;
        if ((CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() || CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) && CookieGadgets.getGDisguise().isDisguised(this.getPlayer()) && CookieGadgets.getGDisguise().getDisguise(this.getPlayer()).equals(GDisguiseType.SQUID)) {
            CookieGadgets.getGDisguise().undisguise(this.getPlayer());
        }
        HandlerList.unregisterAll((Listener)this);
    }
}
