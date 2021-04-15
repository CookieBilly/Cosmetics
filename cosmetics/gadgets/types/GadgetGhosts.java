

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import java.util.Iterator;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bat;
import java.util.HashMap;

public class GadgetGhosts extends Gadget
{
    private boolean activated;
    private HashMap<Bat, ArmorStand> ghosts;
    
    public GadgetGhosts(final UUID uuid) {
        super(uuid, GadgetType.GHOSTS);
        this.activated = false;
        this.ghosts = new HashMap<Bat, ArmorStand>();
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
        for (int i = 0; i < 8; ++i) {
            CookieGadgets.setBypassCreatureSpawn(true);
            final Bat key = (Bat)this.getPlayer().getWorld().spawn(this.getPlayer().getLocation().add(0.0, 1.0, 0.0), (Class)Bat.class);
            final ArmorStand armorStand = (ArmorStand)key.getWorld().spawn(key.getLocation(), (Class)ArmorStand.class);
            armorStand.setSmall(true);
            armorStand.setGravity(false);
            armorStand.setVisible(false);
            armorStand.setHelmet(new ItemStack(EnumMaterial.WITHER_SKELETON_SKULL.getType(), 1, (short)1));
            armorStand.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
            key.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
            key.setPassenger((Entity)armorStand);
            key.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 240, 1));
            this.ghosts.put(key, armorStand);
            CookieGadgets.setBypassCreatureSpawn(false);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)CookieGadgets.getInstance(), () -> {
            if (!this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType()) {
                this.onClear();
            }
            else {
                this.clearAll();
            }
        }, 220L);
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
        final Iterator<Bat> iterator = this.ghosts.keySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().remove();
        }
        final Iterator<ArmorStand> iterator2 = this.ghosts.values().iterator();
        while (iterator2.hasNext()) {
            iterator2.next().remove();
        }
        this.ghosts.clear();
        this.activated = false;
    }
}
