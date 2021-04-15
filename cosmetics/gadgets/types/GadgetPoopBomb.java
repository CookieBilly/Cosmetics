

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import java.util.Iterator;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import java.util.Random;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.Item;
import java.util.ArrayList;

public class GadgetPoopBomb extends Gadget
{
    private boolean activated;
    private ArrayList<Item> items;
    
    public GadgetPoopBomb(final UUID uuid) {
        super(uuid, GadgetType.POOP_BOMB);
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
        final Item dropItem = this.getPlayer().getWorld().dropItem(this.getPlayer().getLocation(), new ItemStack(EnumMaterial.COCOA_BEANS.getType(), 2, (short)3));
        dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        dropItem.setPickupDelay(Integer.MAX_VALUE);
        this.items.add(dropItem);
        this.activated = true;
        SoundEffect.ENTITY_VILLAGER_HURT.playSound((Entity)dropItem);
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                final Random random = new Random();
                ++this.step;
                if (!GadgetPoopBomb.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetPoopBomb.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetPoopBomb.this.getPlayer()).getCurrentGadget().getType() != GadgetPoopBomb.this.getType()) {
                    this.step = 61;
                    GadgetPoopBomb.this.clearAll();
                    return;
                }
                if (this.step <= 60) {
                    if (random.nextInt(100) + 1 <= 70) {
                        for (int i = 0; i < 2; ++i) {
                            final Item dropItem = dropItem.getWorld().dropItem(GadgetPoopBomb.this.items.get(random.nextInt(GadgetPoopBomb.this.items.size())).getLocation(), ItemUtils.item(UUID.randomUUID().toString(), EnumMaterial.COCOA_BEANS, 3));
                            dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            dropItem.setVelocity(new Vector((random.nextDouble() - 0.5) / 2.0, 0.2, (random.nextDouble() - 0.5) / 2.0));
                            dropItem.setPickupDelay(Integer.MAX_VALUE);
                            ParticleEffect.ENCHANTMENT_TABLE.display(dropItem.getLocation(), 1.0f, 0.3f, 1.0f, 1.0f, 3);
                            SoundEffect.ENTITY_ITEM_PICKUP.playSound((Entity)dropItem);
                            GadgetPoopBomb.this.items.add(dropItem);
                        }
                    }
                }
                else {
                    GadgetPoopBomb.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 10L, 3L);
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
        for (final Item item : this.items) {
            ParticleEffect.SMOKE_LARGE.display(item.getLocation(), 0.2f, 4);
            SoundEffect.ENTITY_GENERIC_EXPLODE.playSound((Entity)item, 0.5f, 1.0f);
            item.remove();
        }
        this.items.clear();
        this.activated = false;
    }
}
