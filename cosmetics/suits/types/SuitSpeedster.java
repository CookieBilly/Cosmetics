

package ws.billy.CookieGadgets.cosmetics.suits.types;

import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;

public class SuitSpeedster extends Suit
{
    private boolean activated;
    private int tick;
    
    public SuitSpeedster(final UUID uuid) {
        super(uuid, SuitType.SPEEDSTER);
        this.activated = false;
        this.tick = 0;
    }
    
    public void onUpdate() {
        if (!this.activated || this.tick > 100) {
            this.tick = 0;
            if (this.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) {
                this.getPlayer().removePotionEffect(PotionEffectType.SPEED);
            }
            this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 4));
        }
        ParticleEffect.CLOUD.display(this.getPlayer().getLocation().add(0.0, 0.2, 0.0), 0.2f, 0.2f, 0.2f, 0.0f, 8);
        this.activated = true;
        ++this.tick;
    }
    
    @Override
    public void onClear() {
        if (this.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) {
            this.getPlayer().removePotionEffect(PotionEffectType.SPEED);
        }
        this.activated = false;
        this.tick = 0;
    }
}
