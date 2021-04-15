

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureSnowglobe extends Miniature
{
    private float y;
    
    public MiniatureSnowglobe(final UUID uuid) {
        super(uuid, MiniatureType.SNOWGLOBE);
        this.y = 0.0f;
    }
    
    @Override
    public void onUpdate() {
        if (super.getMiniature() != null) {
            if (this.y >= Float.MAX_VALUE) {
                this.y = 0.0f;
            }
            super.getMiniature().setHeadPoseYNMS(this.y);
            this.y += 6.0f;
        }
    }
}
