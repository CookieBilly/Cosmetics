

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import java.util.Iterator;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.util.Vector;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.Item;
import java.util.ArrayList;

public class GadgetKookieFountain extends Gadget
{
    private boolean activated;
    private ArrayList<Item> items;
    
    public GadgetKookieFountain(final UUID uuid) {
        super(uuid, GadgetType.KOOKIE_FOUNTAIN);
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
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!GadgetKookieFountain.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetKookieFountain.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetKookieFountain.this.getPlayer()).getCurrentGadget().getType() != GadgetKookieFountain.this.getType()) {
                    this.step = 60;
                    GadgetKookieFountain.this.onClear();
                    this.cancel();
                    return;
                }
                if (this.step <= 60) {
                    final Item dropItem = GadgetKookieFountain.this.getPlayer().getWorld().dropItem(GadgetKookieFountain.this.getPlayer().getEyeLocation(), ItemUtils.item(UUID.randomUUID().toString(), EnumMaterial.COOKIE, 0));
                    dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    dropItem.setPickupDelay(Integer.MAX_VALUE);
                    dropItem.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) * 0.4, 0.4, (CookieGadgets.random().nextDouble() - 0.5) * 0.4));
                    SoundEffect.ENTITY_CHICKEN_EGG.playSound(GadgetKookieFountain.this.getPlayer().getLocation());
                    GadgetKookieFountain.this.items.add(dropItem);
                }
                else {
                    GadgetKookieFountain.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 4L);
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
        final Iterator<Item> iterator = this.items.iterator();
        while (iterator.hasNext()) {
            iterator.next().remove();
        }
        this.items.clear();
        this.activated = false;
    }
}
