

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureMrSmiley extends Miniature
{
    public MiniatureMrSmiley(final UUID uuid) {
        super(uuid, MiniatureType.MR_SMILEY);
    }
    
    @Override
    public void onUpdate() {
    }
}
