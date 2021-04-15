

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureIronGolem extends Miniature
{
    public MiniatureIronGolem(final UUID uuid) {
        super(uuid, MiniatureType.IRON_GOLEM);
    }
    
    @Override
    public void onUpdate() {
    }
}
