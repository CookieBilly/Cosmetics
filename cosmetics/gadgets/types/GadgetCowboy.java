

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.PlayerUtils;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;

public class GadgetCowboy extends Gadget
{
    public GadgetCowboy(final UUID uuid) {
        super(uuid, GadgetType.COWBOY);
    }
    
    @Override
    public void onClick() {
        for (final Player player : PlayerUtils.getNearbyPlayers(this.getPlayer().getLocation(), 15.0)) {
            if (player != this.getPlayer() && player.getPassenger() == null && player.getVehicle() != this.getPlayer() && this.getPlayer().getVehicle() != player && !player.hasMetadata("NPC") && player.getGameMode() != GameMode.SPECTATOR && this.getPlayer().canSee(player) && !WorldGuardUtils.isInBlacklistedRegion(player.getLocation(), BlacklistedRegion.GADGETS)) {
                if (BlockUtil.isOutsideOfBorder(player.getLocation())) {
                    continue;
                }
                CookieGadgets.getNMSManager().setPassenger(player, this.getPlayer());
                return;
            }
        }
        this.getPlayer().sendMessage(MessageType.NO_PLAYER_NEARBY.getFormatMessage());
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        HandlerList.unregisterAll((Listener)this);
    }
}
