

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityUnleashEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.GameMode;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Chicken;
import java.util.ArrayList;

public class GadgetParachute extends Gadget
{
    private boolean activated;
    private ArrayList<Chicken> chickens;
    private boolean run;
    private int step;
    private ArmorStand armorstand;
    private boolean enableFlyByDefault;
    
    public GadgetParachute(final UUID uuid) {
        super(uuid, GadgetType.PARACHUTE);
        this.activated = false;
        this.chickens = new ArrayList<Chicken>();
        this.run = true;
        this.step = 0;
        this.enableFlyByDefault = false;
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        if (!this.getPlayer().isOnGround()) {
            this.getPlayer().sendMessage(MessageType.NOT_ON_GROUND.getFormatMessage());
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.activated = true;
        ParticleEffect.EXPLOSION_HUGE.display(this.getPlayer().getLocation(), 0.5f, 3);
        SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(this.getPlayer().getLocation());
        if (this.getPlayer().isFlying()) {
            this.getPlayer().setFlying(false);
            this.enableFlyByDefault = true;
        }
        final boolean allowFlight = this.getPlayer().getAllowFlight();
        if (!allowFlight) {
            this.getPlayer().setAllowFlight(true);
        }
        CookieGadgets.setBypassCreatureSpawn(true);
        final ArmorStand armorstand = (ArmorStand)this.getPlayer().getWorld().spawn(this.getPlayer().getLocation(), (Class)ArmorStand.class);
        armorstand.setVisible(false);
        armorstand.setGravity(true);
        armorstand.setSmall(true);
        armorstand.setBasePlate(false);
        armorstand.setPassenger((Entity)this.getPlayer());
        (this.armorstand = armorstand).teleport(this.armorstand.getLocation().add(0.0, 1.0, 0.0));
        this.armorstand.setVelocity(new Vector(0, 5, 0));
        CookieGadgets.setBypassCreatureSpawn(false);
        CookieGadgets.getPlayerManager(this.getPlayer()).disableFallDamage();
        if (!allowFlight) {
            this.getPlayer().setAllowFlight(false);
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.activated) {
            if (!this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType()) {
                this.onClear();
                return;
            }
            ++this.step;
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                if (this.getPlayer().getGameMode() != GameMode.SPECTATOR && this.getPlayer().isFlying()) {
                    this.getPlayer().setFlying(false);
                    this.enableFlyByDefault = true;
                }
                return;
            });
            if (this.getPlayer().getLocation().add(0.0, 1.0, 0.0).getBlock().getType() != Material.AIR || (this.step >= 30 && !this.getPlayer().isOnGround() && !this.armorstand.isOnGround())) {
                if (this.run) {
                    if (this.armorstand.getVelocity().getY() < -0.2) {
                        MathUtil.applyVelocity((Entity)this.armorstand, this.armorstand.getVelocity().add(new Vector(0.0, 0.3, 0.0)));
                    }
                    this.run = false;
                    new BukkitRunnable() {
                        public void run() {
                            if (GadgetParachute.this.getPlayer().isOnGround() || GadgetParachute.this.armorstand == null || GadgetParachute.this.armorstand.isOnGround() || GadgetParachute.this.armorstand.getPassenger() == null) {
                                GadgetParachute.this.clearAll();
                                this.cancel();
                                return;
                            }
                            for (int i = 1; i <= 10; ++i) {
                                CookieGadgets.setBypassCreatureSpawn(true);
                                final Chicken e = (Chicken)GadgetParachute.this.getPlayer().getWorld().spawnEntity(GadgetParachute.this.getPlayer().getLocation().add(MathUtil.randomDouble(0.5, 1.0), 3.0, MathUtil.randomDouble(0.5, 1.0)), EntityType.CHICKEN);
                                GadgetParachute.this.chickens.add(e);
                                e.setLeashHolder((Entity)GadgetParachute.this.getPlayer());
                                e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                                CookieGadgets.setBypassCreatureSpawn(false);
                            }
                        }
                    }.runTaskLater((Plugin)CookieGadgets.getInstance(), 20L);
                }
                if (!this.armorstand.isOnGround()) {
                    if (this.armorstand.getVelocity().getY() < -0.33) {
                        MathUtil.applyVelocity((Entity)this.armorstand, this.armorstand.getVelocity().add(new Vector(0.0, 0.2, 0.0)));
                    }
                    for (final Chicken chicken : this.chickens) {
                        if (chicken.getVelocity().getY() < -0.1) {
                            MathUtil.applyVelocity((Entity)chicken, chicken.getVelocity().add(new Vector(0.0, 0.1, 0.0)));
                        }
                    }
                }
                else {
                    try {
                        MathUtil.applyVelocity((Entity)this.armorstand, new Vector(0.0, 0.2, 0.0));
                        this.clearAll();
                    }
                    catch (Exception ex) {
                        MathUtil.applyVelocity((Entity)this.armorstand, new Vector(0.0, 0.2, 0.0));
                        this.clearAll();
                    }
                }
            }
            if (this.step > 5 && (this.getPlayer().isSneaking() || this.step >= 500 || this.getPlayer().isOnGround() || (this.armorstand != null && this.armorstand.isOnGround()))) {
                this.clearAll();
            }
            if (this.run && this.step >= 45) {
                this.clearAll();
            }
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        for (final Chicken chicken : this.chickens) {
            chicken.setLeashHolder((Entity)null);
            chicken.remove();
        }
        this.chickens.clear();
        if (this.armorstand != null) {
            this.armorstand.remove();
        }
        this.armorstand = null;
        if (this.getPlayer().isOnGround()) {
            CookieGadgets.getPlayerManager(this.getPlayer()).enableFallDamage();
        }
        if (this.enableFlyByDefault) {
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> this.getPlayer().setAllowFlight(true));
        }
        this.step = 0;
        this.run = true;
        this.activated = false;
        this.enableFlyByDefault = false;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeashBreak(final EntityUnleashEvent entityUnleashEvent) {
        if (this.chickens.contains(entityUnleashEvent.getEntity())) {
            for (final Entity entity : entityUnleashEvent.getEntity().getNearbyEntities(1.0, 1.0, 1.0)) {
                if (entity instanceof Item && ((Item)entity).getItemStack().getType().equals((Object)EnumMaterial.LEAD.getType())) {
                    entity.remove();
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeashEntity(final PlayerLeashEntityEvent playerLeashEntityEvent) {
        if (playerLeashEntityEvent.getPlayer().getItemInHand() != null && playerLeashEntityEvent.getPlayer().getItemInHand().hasItemMeta() && CookieGadgets.getNMSManager().hasNBTTag(playerLeashEntityEvent.getPlayer().getItemInHand(), "Cosmetics")) {
            playerLeashEntityEvent.setCancelled(true);
            playerLeashEntityEvent.getPlayer().updateInventory();
        }
    }
}
