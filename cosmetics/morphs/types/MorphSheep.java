

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;

public class MorphSheep extends Morph
{
    private boolean activated;
    
    public MorphSheep(final UUID uuid) {
        super(uuid, MorphType.SHEEP);
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
        CookieGadgets.getGDisguise().disguiseSheep(this.getPlayer());
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!MorphSheep.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(MorphSheep.this.getPlayer()).getCurrentMorph() == null || CookieGadgets.getPlayerManager(MorphSheep.this.getPlayer()).getCurrentMorph().getType() != MorphSheep.this.getType()) {
                    MorphSheep.this.onClear();
                    this.step = 33;
                    this.cancel();
                    return;
                }
                if (this.step > 32) {
                    MorphSheep.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 1L, 5L);
    }
    
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
    }
    
    private void clearAll() {
        this.activated = false;
    }
}
