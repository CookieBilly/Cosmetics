

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.configuration.FileManager;
import org.bukkit.util.Vector;
import org.bukkit.entity.Player;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Bat;
import java.util.ArrayList;

public class GadgetBatLauncher extends Gadget
{
    private boolean activated;
    private ArrayList<Bat> bats;
    private Location eyeLocation;
    
    public GadgetBatLauncher(final UUID uuid) {
        super(uuid, GadgetType.BAT_LAUNCHER);
        this.activated = false;
        this.bats = new ArrayList<Bat>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.activated = true;
        this.eyeLocation = this.getPlayer().getEyeLocation();
        for (int i = 0; i < 16; ++i) {
            CookieGadgets.setBypassCreatureSpawn(true);
            final Bat e = (Bat)this.getPlayer().getWorld().spawnEntity(this.getPlayer().getEyeLocation(), EntityType.BAT);
            this.bats.add(e);
            e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
            CookieGadgets.setBypassCreatureSpawn(false);
        }
        SoundEffect.ENTITY_FIREWORK_ROCKET_LAUNCH.playSound(this.getPlayer());
        new BukkitRunnable() {
            int steps = 0;
            
            public void run() {
                try {
                    ++this.steps;
                    if (this.steps > 60) {
                        GadgetBatLauncher.this.clearAll();
                        this.cancel();
                        return;
                    }
                    if (!GadgetBatLauncher.this.activated || !GadgetBatLauncher.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetBatLauncher.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetBatLauncher.this.getPlayer()).getCurrentGadget().getType() != GadgetBatLauncher.this.getType()) {
                        GadgetBatLauncher.this.onClear();
                        this.cancel();
                        return;
                    }
                    GadgetBatLauncher.this.onHit(GadgetBatLauncher.this.getPlayer());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    GadgetBatLauncher.this.clearAll();
                }
            }
        }.runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), 0L, 1L);
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        for (final Bat bat : this.bats) {
            if (bat.isValid()) {
                ParticleEffect.SMOKE_LARGE.display(bat.getLocation(), 0.1f, 0.1f, 0.1f, 3);
                bat.remove();
            }
        }
        this.bats.clear();
        this.eyeLocation = null;
        this.activated = false;
    }
    
    private void onHit(final Player obj) {
        for (final Bat bat : this.bats) {
            if (!bat.isValid()) {
                this.onClear();
                break;
            }
            bat.setVelocity(this.eyeLocation.getDirection().clone().multiply(0.5).add(new Vector((Math.random() - 0.5) / 3.0, (Math.random() - 0.5) / 3.0, (Math.random() - 0.5) / 3.0)));
            if (!FileManager.getGadgetsFile().getBoolean(String.valueOf(this.getType().getFilePath()) + ".Affect-Players")) {
                continue;
            }
            for (final Player player : Bukkit.getOnlinePlayers()) {
                if (player.getWorld() == bat.getWorld()) {
                    if (!player.isOnline()) {
                        continue;
                    }
                    if (player.equals(obj) || !this.hitPlayer(bat.getLocation(), player)) {
                        continue;
                    }
                    final Vector direction = bat.getLocation().getDirection();
                    direction.normalize();
                    direction.multiply(0.4);
                    direction.setY(direction.getY() + 0.2);
                    if (direction.getY() > 7.5) {
                        direction.setY(7.5);
                    }
                    if (player.isOnGround()) {
                        direction.setY(direction.getY() + 0.2);
                    }
                    player.setFallDistance(0.0f);
                    MathUtil.applyVelocity((Entity)player, bat.getLocation().getDirection().add(new Vector(0.0f, 0.4f, 0.0f)));
                    SoundEffect.ENTITY_BAT_HURT.playSound((Entity)bat);
                    ParticleEffect.SMOKE_LARGE.display(bat.getLocation(), 0.1f, 0.1f, 0.1f, 3);
                    bat.remove();
                }
            }
        }
    }
    
    private boolean hitPlayer(final Location location, final Player player) {
        return location.add(0.0, -location.getY(), 0.0).toVector().subtract(player.getLocation().add(0.0, -player.getLocation().getY(), 0.0).toVector()).length() < 0.8 || (location.add(0.0, -location.getY(), 0.0).toVector().subtract(player.getLocation().add(0.0, -player.getLocation().getY(), 0.0).toVector()).length() < 1.2 && location.getY() > player.getLocation().getY() && location.getY() < player.getEyeLocation().getY());
    }
}
