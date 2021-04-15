

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureReindeer extends Miniature
{
    public MiniatureReindeer(final UUID uuid) {
        super(uuid, MiniatureType.REINDEER);
    }
    
    @Override
    public void onUpdate() {
    }
}
