

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureDoge extends Miniature
{
    public MiniatureDoge(final UUID uuid) {
        super(uuid, MiniatureType.DOGE);
    }
    
    @Override
    public void onUpdate() {
    }
}
