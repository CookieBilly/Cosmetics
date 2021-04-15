

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureEnderman extends Miniature
{
    public MiniatureEnderman(final UUID uuid) {
        super(uuid, MiniatureType.ENDERMAN);
    }
    
    @Override
    public void onUpdate() {
    }
}
