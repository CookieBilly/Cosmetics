

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;

public class MorphCreeper extends Morph
{
    private boolean activated;
    
    public MorphCreeper(final UUID uuid) {
        super(uuid, MorphType.CREEPER);
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
    
    @Override
    void onClick() {
        this.activated = true;
        SoundEffect.ENTITY_CREEPER_PRIMED.playSound(this.getPlayer());
        CookieGadgets.getGDisguise().disguiseCreeper(this.getPlayer());
        new BukkitRunnable() {
            public void run() {
                MorphCreeper.this.clearAll();
            }
        }.runTaskLater((Plugin)CookieGadgets.getInstance(), 30L);
    }
    
    @Override
    void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        this.activated = false;
    }
}
