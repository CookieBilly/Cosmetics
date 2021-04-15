

package ws.billy.CookieGadgets.player;

import java.util.UUID;
import org.bukkit.entity.Player;

public interface IPlayer
{
    Player getPlayer();
    
    String getName();
    
    UUID getUUID();
    
    default boolean isOfflinePlayer() {
        return true;
    }
    
    void initData();
    
    int getMysteryDust();
    
    void addMysteryDust(final int p0);
    
    void setMysteryDust(final int p0);
    
    void removeMysteryDust(final int p0);
    
    void goBackToMainMenu();
}
