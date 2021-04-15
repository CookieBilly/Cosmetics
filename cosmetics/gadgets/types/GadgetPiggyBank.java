

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.util.Vector;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.Item;
import java.util.ArrayList;
import org.bukkit.entity.Pig;

public class GadgetPiggyBank extends Gadget
{
    private boolean activated;
    private Pig pig;
    private ArrayList<Item> items;
    
    public GadgetPiggyBank(final UUID uuid) {
        super(uuid, GadgetType.PIGGY_BANK);
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
        CookieGadgets.setBypassCreatureSpawn(true);
        final Pig pig = (Pig)this.getPlayer().getLocation().getWorld().spawn(this.getPlayer().getLocation(), (Class)Pig.class);
        pig.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        try {
            if (VersionManager.is1_9OrAbove()) {
                pig.setSilent(true);
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        this.pig = pig;
        CookieGadgets.setBypassCreatureSpawn(false);
        this.activated = true;
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!GadgetPiggyBank.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetPiggyBank.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetPiggyBank.this.getPlayer()).getCurrentGadget().getType() != GadgetPiggyBank.this.getType()) {
                    this.step = 4;
                    GadgetPiggyBank.this.onClear();
                    this.cancel();
                    return;
                }
                if (this.step <= 3) {
                    pig.setVelocity(new Vector(0.0, 0.7, 0.0));
                }
                else {
                    final EnumMaterial[] array = { EnumMaterial.GOLD_BLOCK, EnumMaterial.GOLD_INGOT, EnumMaterial.GOLDEN_HELMET, EnumMaterial.GOLDEN_CHESTPLATE, EnumMaterial.GOLDEN_LEGGINGS, EnumMaterial.GOLDEN_BOOTS, EnumMaterial.GOLDEN_SWORD, EnumMaterial.GOLDEN_PICKAXE, EnumMaterial.GOLDEN_SHOVEL };
                    for (int i = 0; i <= 50; ++i) {
                        final Item dropItem = GadgetPiggyBank.this.getPlayer().getWorld().dropItem(pig.getLocation(), ItemUtils.item(UUID.randomUUID().toString(), array[CookieGadgets.random().nextInt(array.length)], 0));
                        dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        dropItem.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) / 4.0, 0.4, (CookieGadgets.random().nextDouble() - 0.5) / 4.0));
                        dropItem.setPickupDelay(Integer.MAX_VALUE);
                        GadgetPiggyBank.this.items.add(dropItem);
                    }
                    SoundEffect.ENTITY_PLAYER_LEVELUP.playSound(pig.getLocation());
                    if (GadgetPiggyBank.this.pig != null) {
                        GadgetPiggyBank.this.pig.remove();
                    }
                    this.cancel();
                    new BukkitRunnable() {
                        public void run() {
                            GadgetPiggyBank.this.clearAll();
                        }
                    }.runTaskLater((Plugin)CookieGadgets.getInstance(), 70L);
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 5L);
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
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
        this.activated = false;
    }
}
