

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureGhost extends Miniature
{
    public MiniatureGhost(final UUID uuid) {
        super(uuid, MiniatureType.GHOST);
    }
    
    @Override
    public void onUpdate() {
        if (super.getMiniature() != null) {
            ParticleEffect.SNOWBALL.display(super.getMiniature().getLocationNMS(), this.getPlayer(), 0.05f, 0.05f, 0.05f, 5.0f, 2);
        }
    }
}
