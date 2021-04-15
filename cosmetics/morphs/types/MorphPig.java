

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import java.util.Iterator;
import org.bukkit.util.Vector;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;
import org.bukkit.entity.Item;
import java.util.ArrayList;

public class MorphPig extends Morph
{
    private boolean activated;
    private ArrayList<Item> items;
    
    public MorphPig(final UUID uuid) {
        super(uuid, MorphType.PIG);
        this.activated = false;
        this.items = new ArrayList<Item>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.MORPH_SKILL_IS_ACTIVATED.getFormatMessage().replace("{MORPH}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        return true;
    }
    
    public void onClick() {
        this.activated = true;
        SoundEffect.ENTITY_PIG_AMBIENT.playSound(this.getPlayer());
        int i = 0;
        final Item e;
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> {
            while (i <= 13) {
                this.getPlayer().getWorld().dropItem(this.getPlayer().getLocation(), ItemUtils.item(UUID.randomUUID().toString(), EnumMaterial.COOKED_PORKCHOP));
                e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                e.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) / 1.7, 0.3, (CookieGadgets.random().nextDouble() - 0.5) / 1.7));
                e.setPickupDelay(Integer.MAX_VALUE);
                this.items.add(e);
                ++i;
            }
            SoundEffect.ENTITY_CHICKEN_EGG.playSound(this.getPlayer().getLocation());
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> this.clearAll(), 80L);
        }, 20L);
    }
    
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
