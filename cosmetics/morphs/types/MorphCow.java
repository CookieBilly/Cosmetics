

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;
import org.bukkit.entity.Cow;
import java.util.ArrayList;

public class MorphCow extends Morph
{
    private ArrayList<Cow> cows;
    private boolean activated;
    
    public MorphCow(final UUID uuid) {
        super(uuid, MorphType.COW);
        this.cows = new ArrayList<Cow>();
        this.activated = false;
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
        SoundEffect.ENTITY_COW_AMBIENT.playSound(this.getPlayer());
        for (int i = 0; i < 8; ++i) {
            CookieGadgets.setBypassCreatureSpawn(true);
            final Cow e = (Cow)this.getPlayer().getWorld().spawn(this.getPlayer().getLocation(), (Class)Cow.class);
            e.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) / 1.7, 0.3, (CookieGadgets.random().nextDouble() - 0.5) / 1.7));
            e.setBaby();
            e.setAgeLock(true);
            e.setNoDamageTicks(120);
            e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
            this.cows.add(e);
            CookieGadgets.setBypassCreatureSpawn(false);
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    MorphCow.this.clearAll();
                }
            }, 110L);
        }
    }
    
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
    }
    
    private void clearAll() {
        for (final Cow cow : this.cows) {
            if (cow != null) {
                ParticleEffect.LAVA.display(cow.getLocation(), 5);
            }
            cow.remove();
        }
        this.cows.clear();
        this.activated = false;
    }
}
