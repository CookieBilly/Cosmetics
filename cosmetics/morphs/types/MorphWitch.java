

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.util.Vector;
import org.bukkit.entity.ThrownPotion;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;

public class MorphWitch extends Morph
{
    private int[] potion1;
    private int[] potion2;
    
    public MorphWitch(final UUID uuid) {
        super(uuid, MorphType.WITCH);
        this.potion1 = new int[] { 0, 8193, 8194, 8195, 8197, 8198, 8201, 8203, 8205, 8206, 8207, 8196, 8200, 8202, 8204 };
        this.potion2 = new int[] { 16385, 16386, 16419, 16388, 16453, 16422, 16424, 16393, 16426, 16395, 16460, 16429, 16430 };
    }
    
    public void onClick() {
        final ThrownPotion thrownPotion = (ThrownPotion)this.getPlayer().launchProjectile((Class)ThrownPotion.class, this.getPlayer().getLocation().getDirection().multiply(1.25).add(new Vector(0.0, 0.1, 0.0)));
        if (VersionManager.is1_9OrAbove()) {
            thrownPotion.setItem(CookieGadgets.getNMSManager().getPotionFromId(new ItemStack(EnumMaterial.SPLASH_POTION.getType(), 1), EnumMaterial.SPLASH_POTION, this.potion1[CookieGadgets.random().nextInt(this.potion1.length)]));
        }
        else {
            thrownPotion.setItem(new ItemStack(EnumMaterial.POTION.getType(), 1, (short)(byte)this.potion2[CookieGadgets.random().nextInt(this.potion2.length)]));
        }
        thrownPotion.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        SoundEffect.ENTITY_SPLASH_POTION_THROW.playSound(thrownPotion.getLocation());
    }
    
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        HandlerList.unregisterAll((Listener)this);
    }
}
