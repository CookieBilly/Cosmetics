

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

public class GadgetDiamondShower extends Gadget
{
    private static EnumMaterial[] materials;
    private boolean activated;
    private ArrayList<Item> items;
    
    static {
        GadgetDiamondShower.materials = new EnumMaterial[] { EnumMaterial.DIAMOND, EnumMaterial.DIAMOND_BLOCK, EnumMaterial.DIAMOND_CHESTPLATE, EnumMaterial.DIAMOND_PICKAXE, EnumMaterial.DIAMOND_ORE, EnumMaterial.DIAMOND_SWORD };
    }
    
    public GadgetDiamondShower(final UUID uuid) {
        super(uuid, GadgetType.DIAMOND_SHOWER);
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
        SoundEffect.ENTITY_HORSE_ARMOR.playSound(this.getPlayer());
        this.activated = true;
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!GadgetDiamondShower.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetDiamondShower.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetDiamondShower.this.getPlayer()).getCurrentGadget().getType() != GadgetDiamondShower.this.getType()) {
                    this.step = 50;
                    GadgetDiamondShower.this.onClear();
                    this.cancel();
                    return;
                }
                for (final Item item : GadgetDiamondShower.this.items) {
                    if (item.getTicksLived() > 10) {
                        item.remove();
                    }
                }
                if (this.step <= 50) {
                    final Item dropItem = GadgetDiamondShower.this.getPlayer().getWorld().dropItem(GadgetDiamondShower.this.getPlayer().getEyeLocation(), ItemUtils.item(UUID.randomUUID().toString(), GadgetDiamondShower.materials[CookieGadgets.random().nextInt(GadgetDiamondShower.materials.length)], 0));
                    dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    dropItem.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) / 5.0, 0.3, (CookieGadgets.random().nextDouble() - 0.5) / 5.0));
                    dropItem.setPickupDelay(Integer.MAX_VALUE);
                    GadgetDiamondShower.this.items.add(dropItem);
                }
                else {
                    GadgetDiamondShower.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 3L);
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
