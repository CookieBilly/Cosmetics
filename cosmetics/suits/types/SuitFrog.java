

package ws.billy.CookieGadgets.cosmetics.suits.types;

import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;

public class SuitFrog extends Suit
{
    private boolean activated;
    private int tick;
    
    public SuitFrog(final UUID uuid) {
        super(uuid, SuitType.FROG);
        this.activated = false;
        this.tick = 0;
    }
    
    public void onUpdate() {
        if (!this.activated || this.tick > 100) {
            this.tick = 0;
            if (this.getPlayer().hasPotionEffect(PotionEffectType.JUMP)) {
                this.getPlayer().removePotionEffect(PotionEffectType.JUMP);
            }
            this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 5));
        }
        if (!CookieGadgets.getPlayerManager(this.getPlayer()).isFallDamageDisabled()) {
            CookieGadgets.getPlayerManager(this.getPlayer()).disableFallDamage();
        }
        this.activated = true;
        ++this.tick;
    }
    
    @Override
    public void onClear() {
        this.activated = false;
        this.tick = 0;
        if (this.getPlayer().hasPotionEffect(PotionEffectType.JUMP)) {
            this.getPlayer().removePotionEffect(PotionEffectType.JUMP);
        }
        CookieGadgets.getPlayerManager(this.getPlayer()).enableFallDamage();
    }
}
