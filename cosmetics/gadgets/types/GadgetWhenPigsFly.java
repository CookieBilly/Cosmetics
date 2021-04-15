

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import java.util.Iterator;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.FireworkUtils;
import java.util.Arrays;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Item;
import java.util.ArrayList;

public class GadgetWhenPigsFly extends Gadget
{
    private boolean activated;
    private ArrayList<Item> items;
    private Pig pig;
    private Bat bat;
    
    public GadgetWhenPigsFly(final UUID uuid) {
        super(uuid, GadgetType.WHEN_PIGS_FLY);
        this.activated = false;
        this.items = new ArrayList<Item>();
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
        final Location location = this.getPlayer().getLocation();
        CookieGadgets.setBypassCreatureSpawn(true);
        (this.pig = (Pig)this.getPlayer().getWorld().spawn(location, (Class)Pig.class)).setSaddle(true);
        this.pig.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.pig.setPassenger((Entity)this.getPlayer());
        (this.bat = (Bat)this.getPlayer().getWorld().spawn(location, (Class)Bat.class)).setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 250, 0));
        this.bat.setPassenger((Entity)this.pig);
        if (VersionManager.is1_9OrAbove()) {
            this.bat.setSilent(true);
        }
        CookieGadgets.setBypassCreatureSpawn(false);
        CookieGadgets.getPlayerManager(this.getPlayer()).disableFallDamage();
        new BukkitRunnable() {
            public void run() {
                if (!GadgetWhenPigsFly.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetWhenPigsFly.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetWhenPigsFly.this.getPlayer()).getCurrentGadget().getType() != GadgetWhenPigsFly.this.getType() || !GadgetWhenPigsFly.this.activated) {
                    GadgetWhenPigsFly.this.onClear();
                    this.cancel();
                    return;
                }
                FireworkUtils.displayFirework(GadgetWhenPigsFly.this.pig.getLocation(), FireworkEffect.Type.BURST, false, false, Arrays.asList(Color.RED), Arrays.asList(Color.RED));
                final EnumMaterial[] array = { EnumMaterial.BONE, EnumMaterial.GUNPOWDER, EnumMaterial.PORKCHOP };
                for (int i = 0; i <= 25; ++i) {
                    final Item dropItem = GadgetWhenPigsFly.this.getPlayer().getWorld().dropItem(GadgetWhenPigsFly.this.pig.getLocation(), ItemUtils.item(UUID.randomUUID().toString(), array[CookieGadgets.random().nextInt(array.length)], 0));
                    dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    dropItem.setPickupDelay(Integer.MAX_VALUE);
                    dropItem.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) / 1.7, 0.4, (CookieGadgets.random().nextDouble() - 0.5) / 1.7));
                    GadgetWhenPigsFly.this.items.add(dropItem);
                }
                SoundEffect.BLOCK_STONE_STEP.playSound(GadgetWhenPigsFly.this.pig.getLocation());
                if (GadgetWhenPigsFly.this.pig != null) {
                    GadgetWhenPigsFly.this.pig.remove();
                }
                if (GadgetWhenPigsFly.this.bat != null) {
                    GadgetWhenPigsFly.this.bat.remove();
                }
                if (GadgetWhenPigsFly.this.getPlayer().isOnGround()) {
                    CookieGadgets.getPlayerManager(GadgetWhenPigsFly.this.getPlayer()).enableFallDamage();
                }
                new BukkitRunnable() {
                    public void run() {
                        GadgetWhenPigsFly.this.clearAll();
                    }
                }.runTaskLater((Plugin)CookieGadgets.getInstance(), 60L);
            }
        }.runTaskLater((Plugin)CookieGadgets.getInstance(), 200L);
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
        if (!this.items.isEmpty()) {
            final Iterator<Item> iterator = this.items.iterator();
            while (iterator.hasNext()) {
                iterator.next().remove();
            }
            this.items.clear();
        }
        if (this.pig != null) {
            this.pig.remove();
        }
        this.pig = null;
        if (this.bat != null) {
            this.bat.remove();
        }
        this.bat = null;
        if (this.getPlayer().isOnGround()) {
            CookieGadgets.getPlayerManager(this.getPlayer()).enableFallDamage();
        }
        this.activated = false;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerToggleSneak(final PlayerToggleSneakEvent playerToggleSneakEvent) {
        if (playerToggleSneakEvent.getPlayer() == this.getPlayer() && this.activated) {
            this.clearAll();
        }
    }
}
