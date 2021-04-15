

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import org.bukkit.util.Vector;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.Item;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.EnumMaterial;

public class GadgetGoldFountain extends Gadget
{
    private static EnumMaterial[] materials;
    private boolean activated;
    private ArrayList<Item> items;
    
    static {
        GadgetGoldFountain.materials = new EnumMaterial[] { EnumMaterial.GOLD_INGOT, EnumMaterial.GOLD_BLOCK, EnumMaterial.GOLDEN_CHESTPLATE, EnumMaterial.GOLDEN_PICKAXE, EnumMaterial.GOLD_ORE, EnumMaterial.GOLDEN_APPLE, EnumMaterial.GOLDEN_SWORD };
    }
    
    public GadgetGoldFountain(final UUID uuid) {
        super(uuid, GadgetType.GOLD_FOUNTAIN);
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
        SoundEffect.ENTITY_HORSE_ARMOR.playSound(this.getPlayer().getLocation());
        this.activated = true;
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!GadgetGoldFountain.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetGoldFountain.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetGoldFountain.this.getPlayer()).getCurrentGadget().getType() != GadgetGoldFountain.this.getType()) {
                    this.step = 50;
                    GadgetGoldFountain.this.onClear();
                    this.cancel();
                    return;
                }
                for (final Item item : GadgetGoldFountain.this.items) {
                    if (item.getTicksLived() > 10) {
                        item.remove();
                    }
                }
                if (this.step <= 50) {
                    final Item dropItem = GadgetGoldFountain.this.getPlayer().getWorld().dropItem(GadgetGoldFountain.this.getPlayer().getEyeLocation(), ItemUtils.item(UUID.randomUUID().toString(), GadgetGoldFountain.materials[CookieGadgets.random().nextInt(GadgetGoldFountain.materials.length)], 0));
                    dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    dropItem.setPickupDelay(Integer.MAX_VALUE);
                    dropItem.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) / 5.0, 0.3, (CookieGadgets.random().nextDouble() - 0.5) / 5.0));
                    GadgetGoldFountain.this.items.add(dropItem);
                }
                else {
                    GadgetGoldFountain.this.clearAll();
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
