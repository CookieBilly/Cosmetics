

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureMiner extends Miniature
{
    public MiniatureMiner(final UUID uuid) {
        super(uuid, MiniatureType.MINER);
    }
    
    @Override
    public void onUpdate() {
    }
}
