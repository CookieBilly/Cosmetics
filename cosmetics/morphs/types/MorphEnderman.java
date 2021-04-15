

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.event.player.PlayerTeleportEvent;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;

public class MorphEnderman extends Morph
{
    private int maxRadius;
    
    public MorphEnderman(final UUID uuid) {
        super(uuid, MorphType.ENDERMAN);
        this.maxRadius = 8;
    }
    
    public void onClick() {
        final Location clone = this.getPlayer().getLocation().clone();
        clone.setX(this.getPlayer().getLocation().getX() + Math.random() * this.maxRadius * 2.0 - this.maxRadius);
        clone.setZ(this.getPlayer().getLocation().getZ() + Math.random() * this.maxRadius * 2.0 - this.maxRadius);
        clone.setY((double)this.getPlayer().getLocation().getWorld().getHighestBlockAt(clone.getBlockX(), clone.getBlockZ()).getY());
        if (WorldGuardUtils.isInBlacklistedRegion(clone, BlacklistedRegion.MORPHS) || BlockUtil.isOutsideOfBorder(clone)) {
            SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(this.getPlayer(), 1.0f, 0.5f);
            this.getPlayer().sendMessage(MessageType.NOT_ALLOWED_TELEPORT_TO_THAT_LOCATION.getFormatMessage());
            return;
        }
        this.getPlayer().teleport(clone, PlayerTeleportEvent.TeleportCause.PLUGIN);
        SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(this.getPlayer().getLocation());
        ParticleEffect.FLAME.display(this.getPlayer().getLocation().add(0.0, 1.5, 0.0), 0.5f, 0.5f, 0.5f, 0.0f, 8);
    }
    
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        HandlerList.unregisterAll((Listener)this);
    }
}
