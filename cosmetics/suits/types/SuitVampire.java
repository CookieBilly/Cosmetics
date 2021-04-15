

package ws.billy.CookieGadgets.cosmetics.suits.types;

import ws.billy.CookieGadgets.utils.cosmetics.morphs.GDisguiseType;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;

public class SuitVampire extends Suit
{
    private boolean morphed;
    
    public SuitVampire(final UUID uuid) {
        super(uuid, SuitType.VAMPIRE);
        this.morphed = false;
    }
    
    public void onUpdate() {
        if (!CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() && !CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) {
            return;
        }
        if (this.getPlayer().isFlying() && !this.morphed) {
            if (CookieGadgets.getPlayerManager(this.getPlayer()).getEquippedMorph() != null) {
                CookieGadgets.getPlayerManager(this.getPlayer()).unequipMorph();
            }
            ParticleEffect.SMOKE_NORMAL.display(this.getPlayer().getLocation(), 0.5f, 0.5f, 0.5f, 12);
            CookieGadgets.getGDisguise().disguise(this.getPlayer(), GDisguiseType.BAT);
            this.morphed = true;
        }
        else if (!this.getPlayer().isFlying() && this.morphed) {
            CookieGadgets.getGDisguise().undisguise(this.getPlayer());
            this.morphed = false;
        }
    }
    
    @Override
    public void onClear() {
        this.morphed = false;
        if ((CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() || CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) && CookieGadgets.getGDisguise().isDisguised(this.getPlayer()) && CookieGadgets.getGDisguise().getDisguise(this.getPlayer()).equals(GDisguiseType.BAT)) {
            CookieGadgets.getGDisguise().undisguise(this.getPlayer());
        }
    }
}
