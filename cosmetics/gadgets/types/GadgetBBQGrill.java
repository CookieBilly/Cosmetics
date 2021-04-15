// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import java.util.Iterator;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.util.EulerAngle;
import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import java.util.ArrayList;

public class GadgetBBQGrill extends Gadget
{
    private boolean activated;
    private ArrayList<ArmorStand> armorstands;
    private Location centerLocation;
    
    public GadgetBBQGrill(final UUID uuid) {
        super(uuid, GadgetType.BBQ_GRILL);
        this.activated = false;
        this.armorstands = new ArrayList<ArmorStand>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        if (!this.getPlayer().isOnGround()) {
            this.getPlayer().sendMessage(MessageType.NOT_ON_GROUND.getFormatMessage());
            return false;
        }
        if (!new CuboID(this.getPlayer().getLocation().clone().add(-1.0, 0.0, 0.0), this.getPlayer().getLocation().clone().add(2.0, 2.0, 1.0)).isEmpty()) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        (this.centerLocation = this.getPlayer().getLocation().getBlock().getLocation().clone()).setYaw(-90.0f);
        this.centerLocation.add(1.2, -0.1, 0.5);
        final ArmorStand e = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.centerLocation, EntityType.ARMOR_STAND);
        e.setVisible(true);
        e.setGravity(false);
        e.setSmall(true);
        e.setArms(false);
        e.setBasePlate(false);
        e.setLeftLegPose(new EulerAngle(0.0, 0.0, 5.56760031));
        e.setRightLegPose(new EulerAngle(0.0, 0.0, 0.698131701));
        try {
            if (VersionManager.is1_9OrAbove()) {
                e.setSilent(true);
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        e.setHelmet(new ItemStack(Material.OBSIDIAN));
        e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.armorstands.add(e);
        final ArmorStand e2 = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.centerLocation.clone().add(0.0, 0.0, 0.435), EntityType.ARMOR_STAND);
        e2.setVisible(false);
        e2.setGravity(false);
        e2.setSmall(true);
        e2.setArms(false);
        e2.setBasePlate(false);
        try {
            if (VersionManager.is1_9OrAbove()) {
                e2.setSilent(true);
            }
        }
        catch (NoSuchMethodError noSuchMethodError2) {}
        e2.setHelmet(new ItemStack(Material.OBSIDIAN));
        e2.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.armorstands.add(e2);
        final ArmorStand e3 = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.centerLocation.clone().add(0.0, 0.0, -0.435), EntityType.ARMOR_STAND);
        e3.setVisible(false);
        e3.setGravity(false);
        e3.setSmall(true);
        e3.setArms(false);
        e3.setBasePlate(false);
        if (VersionManager.is1_9OrAbove()) {
            e3.setSilent(true);
        }
        e3.setHelmet(new ItemStack(Material.OBSIDIAN));
        e3.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.armorstands.add(e3);
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
        final Iterator<ArmorStand> iterator = this.armorstands.iterator();
        while (iterator.hasNext()) {
            iterator.next().remove();
        }
        this.armorstands.clear();
        this.centerLocation = null;
        this.activated = false;
    }
}
