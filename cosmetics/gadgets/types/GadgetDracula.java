

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.Bat;
import java.util.ArrayList;

public class GadgetDracula extends Gadget
{
    private boolean activated;
    private ArrayList<Bat> bats;
    
    public GadgetDracula(final UUID uuid) {
        super(uuid, GadgetType.DRACULA);
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
        final Location add = this.getPlayer().getLocation().add(0.0, 1.0, 0.0);
        for (int i = 0; i < 5; ++i) {
            final Bat e = (Bat)add.getWorld().spawn(add, (Class)Bat.class);
            e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
            this.bats.add(e);
        }
        this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0));
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!GadgetDracula.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetDracula.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetDracula.this.getPlayer()).getCurrentGadget().getType() != GadgetDracula.this.getType()) {
                    this.step = 10;
                    GadgetDracula.this.onClear();
                    this.cancel();
                }
                if (this.step >= 10) {
                    GadgetDracula.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 20L);
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
            }
            bat.remove();
        }
        this.bats.clear();
        if (this.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            this.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
        }
        this.activated = false;
    }
}
