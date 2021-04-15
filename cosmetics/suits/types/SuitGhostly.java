

package ws.billy.CookieGadgets.cosmetics.suits.types;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;

public class SuitGhostly extends Suit
{
    private boolean activated;
    private int tick;
    
    public SuitGhostly(final UUID uuid) {
        super(uuid, SuitType.GHOSTLY);
        this.activated = false;
        this.tick = 0;
    }
    
    public void onUpdate() {
        if (!this.activated || this.tick > 100) {
            this.tick = 0;
            if (this.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                this.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
            }
            this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 300, 1));
        }
        this.activated = true;
        ++this.tick;
    }
    
    @Override
    public void onClear() {
        if (this.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            this.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
        }
        this.activated = false;
        this.tick = 0;
    }
}
