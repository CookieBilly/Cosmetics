

package ws.billy.CookieGadgets.utils.mysteryvault.animations.types;

import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.EntityType;
import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.GMiniBlock;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Quality;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.entity.ArmorStand;

public class NoneAnimation extends Animation
{
    private ArmorStand armorStand;
    
    public NoneAnimation(final PlayerManager playerManager, final MysteryVault mysteryVault, final Quality quality, final long n) {
        super(playerManager, mysteryVault, AnimationType.NONE, quality, n);
    }
    
    @Override
    public void onStart() {
        final Location location = this.getMysteryVault().getLocation();
        final ArmorStand armorStand = (ArmorStand)this.getPlayer().getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + 0.5, location.getY() + GMiniBlock.additionY, location.getZ() + 0.5, GMiniBlock.getYawByBlockFace(this.getMysteryVault().getBlockFace()), 0.0f).clone(), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setSmall(true);
        try {
            armorStand.setMarker(false);
            armorStand.setCollidable(false);
            armorStand.setSilent(true);
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        armorStand.setHelmet(this.getAnimation().getMiniBlock().getItemStack());
        armorStand.setBasePlate(false);
        armorStand.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.armorStand = armorStand;
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onUpdateParticleEffect() {
        if (this.steps == 0) {
            SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(this.armorStand.getLocation().clone());
        }
        else if (this.steps == 15) {
            ParticleEffect.LAVA.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.0f, 45);
            ParticleEffect.FLAME.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.45f, 40);
        }
        else if (this.steps == 20) {
            ParticleEffect.CLOUD.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.7f, 0.7f, 0.7f);
            SoundEffect.ENTITY_EXPERIENCE_ORB_PICKUP.playSound(this.armorStand.getLocation());
        }
        else if (this.steps > 20 && this.steps < 30) {
            ParticleEffect.CLOUD.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.7f, 0.7f, 0.7f);
        }
        else if (this.steps >= 30) {
            this.onClear();
            this.isContinue = false;
        }
    }
    
    @Override
    public void onUpdateSound() {
    }
    
    @Override
    public void onClear() {
        if (this.armorStand != null && this.armorStand.isValid()) {
            this.armorStand.remove();
            this.armorStand = null;
        }
        this.getMysteryVault().setActivate(false);
        this.getPlayerManager().setOpeningMysteryBox(false);
    }
}
