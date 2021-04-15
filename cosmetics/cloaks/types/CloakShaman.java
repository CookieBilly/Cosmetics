

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;

public class CloakShaman extends Cloak
{
    private double i;
    
    public CloakShaman(final UUID uuid) {
        super(uuid, CloakType.SHAMAN);
        this.i = 0.0;
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation());
    }
    
    private void active(final Location location) {
        if (this.i >= Double.MAX_VALUE) {
            this.i = 0.0;
        }
        final double n = 0.07853981633974483;
        final double n2 = 0.07853981633974483;
        final double n3 = this.i * n;
        final double n4 = this.i * n2 + 3.0;
        final Vector vector = new Vector();
        final Vector vector2 = new Vector();
        vector.setX(Math.cos(n3) * 0.6);
        vector.setZ(Math.sin(n3) * 0.6);
        vector2.setX(Math.cos(n4) * 0.6);
        vector2.setZ(Math.sin(n4) * 0.6);
        if (this.showCloakEffectToEveryone) {
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector).add(0.0, 2.0, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 1);
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector2).add(0.0, 2.0, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 1);
        }
        else {
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector).add(0.0, 2.0, 0.0), this.getPlayer(), 0.0f, 1);
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector2).add(0.0, 2.0, 0.0), this.getPlayer(), 0.0f, 1);
        }
        final double n5 = 0.07853981633974483;
        final double n6 = 0.07853981633974483;
        final double n7 = this.i * n5 + 3.0;
        final double n8 = this.i * n6 + 6.0;
        final Vector vector3 = new Vector();
        final Vector vector4 = new Vector();
        vector3.setX(Math.cos(n7) * 0.4);
        vector3.setZ(Math.sin(n7) * 0.4);
        vector4.setX(Math.cos(n8) * 0.4);
        vector4.setZ(Math.sin(n8) * 0.4);
        if (this.showCloakEffectToEveryone) {
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector3).add(0.0, 1.25, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 1);
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector4).add(0.0, 1.25, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 1);
        }
        else {
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector3).add(0.0, 1.25, 0.0), this.getPlayer(), 0.0f, 1);
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector4).add(0.0, 1.25, 0.0), this.getPlayer(), 0.0f, 1);
        }
        final double n9 = 0.07853981633974483;
        final double n10 = 0.07853981633974483;
        final double n11 = this.i * n9;
        final double n12 = this.i * n10 + 3.0;
        final Vector vector5 = new Vector();
        final Vector vector6 = new Vector();
        vector5.setX(Math.cos(n11) * 0.25);
        vector5.setZ(Math.sin(n11) * 0.25);
        vector6.setX(Math.cos(n12) * 0.25);
        vector6.setZ(Math.sin(n12) * 0.25);
        if (this.showCloakEffectToEveryone) {
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector5).add(0.0, 0.65, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 1);
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector6).add(0.0, 0.65, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 1);
        }
        else {
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector5).add(0.0, 0.65, 0.0), this.getPlayer(), 0.0f, 1);
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(vector6).add(0.0, 0.65, 0.0), this.getPlayer(), 0.0f, 1);
        }
        this.i += 4.0;
    }
}
