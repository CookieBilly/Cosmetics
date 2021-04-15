

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.Location;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
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
import ws.billy.CookieGadgets.configuration.FileManager;
import org.bukkit.entity.Item;
import java.util.ArrayList;

public class GadgetPartyPopper extends Gadget
{
    private boolean activated;
    private ArrayList<Item> items;
    private static boolean isAffectPlayers;
    
    static {
        GadgetPartyPopper.isAffectPlayers = FileManager.getGadgetsFile().getBoolean("Gadgets.Visual.Types.Party Popper.Affect-Players");
    }
    
    public GadgetPartyPopper(final UUID uuid) {
        super(uuid, GadgetType.PARTY_POPPER);
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
                if (!GadgetPartyPopper.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetPartyPopper.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetPartyPopper.this.getPlayer()).getCurrentGadget().getType() != GadgetPartyPopper.this.getType()) {
                    this.step = 100;
                    GadgetPartyPopper.this.onClear();
                    this.cancel();
                    return;
                }
                for (final Item item : GadgetPartyPopper.this.items) {
                    if (item.getTicksLived() > 10) {
                        item.remove();
                    }
                }
                if (this.step <= 100) {
                    final Location add = GadgetPartyPopper.this.getPlayer().getLocation().add(0.0, 2.0, 0.0);
                    final EnumMaterial value = EnumMaterial.valueOf(35, CookieGadgets.random().nextInt(15));
                    final Item dropItem = GadgetPartyPopper.this.getPlayer().getWorld().dropItem(add, ItemUtils.item(UUID.randomUUID().toString(), value, value.getData()));
                    dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    dropItem.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) / 1.7, 0.4, (CookieGadgets.random().nextDouble() - 0.5) / 1.7));
                    dropItem.setPickupDelay(Integer.MAX_VALUE);
                    GadgetPartyPopper.this.items.add(dropItem);
                    SoundEffect.ENTITY_CHICKEN_EGG.playSound((Entity)dropItem, GadgetPartyPopper.this.getPlayer().getLocation());
                    for (final Entity entity : GadgetPartyPopper.this.getPlayer().getNearbyEntities(1.5, 2.5, 1.5)) {
                        if (entity instanceof Player && GadgetPartyPopper.isAffectPlayers) {
                            MathUtil.applyVelocity(entity, new Vector(0.0, 0.5, 0.0).add(MathUtil.getRandomCircleVector().multiply(0.1)));
                        }
                    }
                }
                else {
                    GadgetPartyPopper.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 2L);
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
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
