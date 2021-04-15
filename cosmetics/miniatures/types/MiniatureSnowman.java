

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureSnowman extends Miniature
{
    public MiniatureSnowman(final UUID uuid) {
        super(uuid, MiniatureType.SNOWMAN);
    }
    
    @Override
    public void onUpdate() {
        if (super.getMiniature() != null) {
            ParticleEffect.SNOWBALL.display(super.getMiniature().getLocationNMS().add(0.0, 0.1, 0.0), this.getPlayer(), 0.1f, 0.0f, 0.1f, 5.0f, 2);
        }
    }
}
