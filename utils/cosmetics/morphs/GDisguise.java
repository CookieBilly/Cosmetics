

package ws.billy.CookieGadgets.utils.cosmetics.morphs;

import org.bukkit.entity.Player;

public interface GDisguise
{
    boolean isDisguised(final Player p0);
    
    GDisguiseType getDisguise(final Player p0);
    
    boolean disguise(final Player p0, final GDisguiseType p1);
    
    default void setViewDisguiseToggled(final Player player, final boolean toggle) {
    }
    
    boolean disguiseCreeper(final Player p0);
    
    boolean disguiseSheep(final Player p0);
    
    boolean undisguise(final Player p0);
}
