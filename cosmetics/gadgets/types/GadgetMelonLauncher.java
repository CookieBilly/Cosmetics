

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import org.bukkit.util.Vector;
import org.bukkit.Effect;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.Item;
import java.util.ArrayList;

public class GadgetMelonLauncher extends Gadget
{
    private ArrayList<Item> melons;
    private ArrayList<Item> melonBlocks;
    
    public GadgetMelonLauncher(final UUID uuid) {
        super(uuid, GadgetType.MELON_LAUNCHER);
        this.melons = new ArrayList<Item>();
        this.melonBlocks = new ArrayList<Item>();
    }
    
    @Override
    public void onClick() {
        SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(this.getPlayer());
        final Item dropItem = this.getPlayer().getWorld().dropItem(this.getPlayer().getEyeLocation(), ItemUtils.item(UUID.randomUUID().toString(), EnumMaterial.MELON, 0));
        dropItem.setPickupDelay(Integer.MAX_VALUE);
        dropItem.setVelocity(this.getPlayer().getEyeLocation().getDirection().multiply(1.3));
        dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.melonBlocks.add(dropItem);
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!GadgetMelonLauncher.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetMelonLauncher.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetMelonLauncher.this.getPlayer()).getCurrentGadget().getType() != GadgetMelonLauncher.this.getType() || this.step >= 24) {
                    this.step = 24;
                    this.cancel();
                    return;
                }
                final Iterator<Item> iterator;
                Item item;
                int i = 0;
                final Item e;
                Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                    GadgetMelonLauncher.this.melonBlocks.iterator();
                    while (iterator.hasNext()) {
                        item = iterator.next();
                        if (item.isOnGround()) {
                            item.getWorld().playEffect(item.getLocation(), Effect.STEP_SOUND, 103);
                            while (i <= 8) {
                                GadgetMelonLauncher.this.getPlayer().getWorld().dropItem(item.getLocation(), ItemUtils.item(UUID.randomUUID().toString(), EnumMaterial.MELON_SLICE, 0));
                                e.setVelocity(new Vector(CookieGadgets.random().nextDouble() - 0.5, CookieGadgets.random().nextDouble() / 2.0, CookieGadgets.random().nextDouble() - 0.5).multiply(0.75));
                                e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                                GadgetMelonLauncher.this.melons.add(e);
                                ++i;
                            }
                            item.remove();
                            iterator.remove();
                        }
                    }
                });
            }
        }.runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), 0L, 5L);
    }
    
    @Override
    public void onUpdate() {
        try {
            for (final Item item : this.melons) {
                if (item.isValid() && item.getTicksLived() > 100) {
                    item.remove();
                }
            }
            for (final Item item2 : this.melonBlocks) {
                if (item2.isValid() && item2.getTicksLived() > 120) {
                    item2.remove();
                }
            }
        }
        catch (ConcurrentModificationException ex) {}
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        for (final Item item : this.melons) {
            if (item.isValid()) {
                item.remove();
            }
        }
        this.melons.clear();
        for (final Item item2 : this.melonBlocks) {
            if (item2.isValid()) {
                item2.remove();
            }
        }
        this.melonBlocks.clear();
    }
    
    @EventHandler
    public void onPickUpMelon(final PlayerPickupItemEvent playerPickupItemEvent) {
        final Player player = playerPickupItemEvent.getPlayer();
        if (playerPickupItemEvent.getItem().hasMetadata(CookieGadgets.getInstance().getPluginName()) && playerPickupItemEvent.getItem().getItemStack().getType() == EnumMaterial.MELON_SLICE.getType()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
            SoundEffect.ENTITY_GENERIC_EAT.playSound(player);
            playerPickupItemEvent.getItem().remove();
            playerPickupItemEvent.setCancelled(true);
            this.melons.remove(playerPickupItemEvent.getItem());
        }
        if (this.melonBlocks.contains(playerPickupItemEvent.getItem())) {
            playerPickupItemEvent.setCancelled(true);
        }
    }
}
