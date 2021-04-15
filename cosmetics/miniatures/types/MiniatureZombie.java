

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureZombie extends Miniature
{
    public MiniatureZombie(final UUID uuid) {
        super(uuid, MiniatureType.ZOMBIE);
    }
    
    @Override
    public void onUpdate() {
    }
}
