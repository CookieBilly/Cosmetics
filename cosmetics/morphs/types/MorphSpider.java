

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import java.util.Iterator;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;
import org.bukkit.entity.Item;
import java.util.ArrayList;

public class MorphSpider extends Morph
{
    private boolean activated;
    private ArrayList<Item> items;
    
    public MorphSpider(final UUID uuid) {
        super(uuid, MorphType.SPIDER);
        this.activated = false;
        this.items = new ArrayList<Item>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.MORPH_SKILL_IS_ACTIVATED.getFormatMessage().replace("{MORPH}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        return true;
    }
    
    @Override
    void onClick() {
        this.activated = true;
        SoundEffect.ENTITY_SPIDER_AMBIENT.playSound(this.getPlayer());
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 13; ++i) {
                    final Item dropItem = MorphSpider.this.getPlayer().getWorld().dropItem(MorphSpider.this.getPlayer().getLocation(), ItemUtils.item(UUID.randomUUID().toString(), EnumMaterial.COBWEB));
                    dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    dropItem.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) / 1.7, 0.3, (CookieGadgets.random().nextDouble() - 0.5) / 1.7));
                    dropItem.setPickupDelay(Integer.MAX_VALUE);
                    MorphSpider.this.items.add(dropItem);
                }
                SoundEffect.ENTITY_SPIDER_STEP.playSound(MorphSpider.this.getPlayer().getLocation());
                Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        MorphSpider.this.clearAll();
                    }
                }, 80L);
            }
        }, 20L);
    }
    
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
