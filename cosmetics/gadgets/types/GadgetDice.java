

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.util.EulerAngle;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public class GadgetDice extends Gadget
{
    private boolean activated;
    private ArmorStand armorStand;
    private Location centerLocation;
    private double pose;
    
    public GadgetDice(final UUID uuid) {
        super(uuid, GadgetType.BBQ_GRILL);
        this.activated = false;
        this.pose = 0.0;
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
        (this.centerLocation = this.getPlayer().getLocation().getBlock().getLocation().clone()).setYaw(-90.0f);
        this.centerLocation.add(1.2, -0.1, 0.5);
        final ArmorStand armorStand = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.centerLocation, EntityType.ARMOR_STAND);
        armorStand.setVisible(true);
        armorStand.setGravity(false);
        armorStand.setSmall(false);
        armorStand.setArms(true);
        armorStand.setBasePlate(false);
        try {
            if (VersionManager.is1_9OrAbove()) {
                armorStand.setSilent(true);
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        armorStand.setVelocity(new Vector(armorStand.getVelocity().getX(), 0.8, armorStand.getVelocity().getZ()));
        armorStand.setVelocity(this.getPlayer().getLocation().getDirection().multiply(1.5));
        armorStand.setItemInHand(ItemUtils.itemSkull("Dice", "915f7c313bca9c2f958e68ab14ab393867d67503affff8f20cb13fbe917fd31"));
        armorStand.setRightArmPose(new EulerAngle(0.0, 0.0, 4.71238898));
        armorStand.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.armorStand = armorStand;
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> {
            if (!this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType() || !this.activated) {
                return;
            }
            else {
                this.clearAll();
                return;
            }
        }, 600L);
        this.activated = true;
    }
    
    @Override
    public void onUpdate() {
        if (this.activated) {
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                if (this.getPlayer().isOnline() && CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() != null && CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() == this.getType() && this.activated) {
                    if (this.armorStand != null) {
                        this.armorStand.setRightArmPose(new EulerAngle(0.0, this.pose + 0.174532925, 0.0));
                    }
                    this.pose += 0.174532925;
                    ParticleEffect.SMOKE_NORMAL.display(this.centerLocation.clone().add(0.0, 1.3, 0.0), 0.15f, 0.0f, 0.15f, 0.0f, 3);
                    ParticleEffect.FLAME.display(this.centerLocation.clone().add(0.0, 1.1, 0.0), 0.1f, 0.0f, 0.1f, 0.0f, 1);
                }
            });
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        if (this.armorStand != null) {
            this.armorStand.remove();
        }
        this.armorStand = null;
        this.centerLocation = null;
        this.activated = false;
    }
}
