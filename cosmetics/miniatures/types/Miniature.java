

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSEntityMiniature;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public abstract class Miniature
{
    private UUID uuid;
    private MiniatureType type;
    private Player player;
    private NMSEntityMiniature miniature;
    
    public Miniature(final UUID uuid, final MiniatureType type) {
        this.type = type;
        if (!type.isEnabled()) {
            return;
        }
        if (uuid != null) {
            this.uuid = uuid;
            this.player = Bukkit.getPlayer(uuid);
            if (PermissionUtils.noPermission(this.getPlayer(), type.getPermission(), EnumPermission.MINIATURES.getPermission(), true)) {
                CookieGadgets.getPlayerManager(this.getPlayer()).unequipMiniature();
                return;
            }
            if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentMiniature() != null) {
                CookieGadgets.getPlayerManager(this.getPlayer()).removeMiniature();
            }
            final float n;
            boolean b = false;
            boolean b2 = false;
            final Location location;
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                if (Bukkit.getPlayer(this.getPlayerUUID()) != null) {
                    this.player.getLocation();
                    this.player.getEyeLocation().getYaw();
                    if (n >= 0.0f && n <= 90.0f) {
                        b = false;
                    }
                    else if (n > 90.0f && n <= 180.0f) {
                        b2 = true;
                    }
                    else if (n > 180.0f && n <= 270.0f) {
                        b2 = true;
                    }
                    else if (n > 270.0f) {}
                    this.miniature = CookieGadgets.getNMSManager().spawnNMSMiniature(location.getWorld(), location.getBlockX() + (b ? (b2 ? -1.5 : 1.5) : 0.0), location.getBlockY() + 0.55, location.getBlockZ() + (b ? 0.0 : (b2 ? -1.5 : 1.5)), type.isInvisible(), this.player, type.getDisplayName(), type.getEquipments());
                }
                return;
            });
            new BukkitRunnable() {
                public void run() {
                    try {
                        if (Bukkit.getPlayer(Miniature.this.getPlayerUUID()) != null && CookieGadgets.getPlayerManager(Miniature.this.getPlayer()).getCurrentMiniature() != null && CookieGadgets.getPlayerManager(Miniature.this.getPlayer()).getCurrentMiniature().getType() == type) {
                            Miniature.this.onUpdate();
                        }
                        else {
                            this.cancel();
                        }
                    }
                    catch (NullPointerException ex) {
                        ex.printStackTrace();
                        Miniature.this.clear();
                        this.cancel();
                    }
                }
            }.runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), 0L, type.getRepeatDelay());
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentMiniature(this);
        }
    }
    
    public UUID getPlayerUUID() {
        return this.uuid;
    }
    
    public MiniatureType getType() {
        return this.type;
    }
    
    protected NMSEntityMiniature getMiniature() {
        return this.miniature;
    }
    
    public abstract void onUpdate();
    
    protected Player getPlayer() {
        return this.player;
    }
    
    public void clear() {
        if (this.uuid == null) {
            return;
        }
        if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentMiniature() != null) {
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentMiniature(null);
        }
        this.removeMiniature();
        this.uuid = null;
        this.type = null;
        this.player = null;
    }
    
    public void removeMiniature() {
        if (this.miniature != null && !this.miniature.isDeadNMS()) {
            this.miniature.killEntityNMS();
        }
        this.miniature = null;
    }
}
