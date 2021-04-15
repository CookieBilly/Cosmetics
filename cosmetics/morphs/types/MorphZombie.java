

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.cosmetics.morphs.GDisguiseType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;
import org.bukkit.entity.Entity;
import java.util.ArrayList;

public class MorphZombie extends Morph
{
    private boolean activated;
    private ArrayList<Entity> players;
    
    public MorphZombie(final UUID uuid) {
        super(uuid, MorphType.ZOMBIE);
        this.activated = false;
        this.players = new ArrayList<Entity>();
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
        SoundEffect.ENTITY_ZOMBIE_AMBIENT.playSound(this.getPlayer().getLocation());
        if (this.getPlayer().getNearbyEntities(5.0, 3.0, 5.0).size() <= 0) {
            this.getPlayer().sendMessage(MessageType.NO_PLAYER_NEARBY.getFormatMessage());
            this.clearAll();
        }
        else {
            for (final Entity e : this.getPlayer().getNearbyEntities(5.0, 3.0, 5.0)) {
                if (!(e instanceof Player) || e.hasMetadata("NPC")) {
                    this.getPlayer().sendMessage(MessageType.NO_PLAYER_NEARBY.getFormatMessage());
                    this.clearAll();
                    return;
                }
                if (CookieGadgets.getGDisguise().isDisguised((Player)e)) {
                    continue;
                }
                CookieGadgets.getGDisguise().disguise((Player)e, GDisguiseType.ZOMBIE);
                this.players.add(e);
            }
            new BukkitRunnable() {
                public void run() {
                    SoundEffect.ENTITY_ZOMBIE_DEATH.playSound(MorphZombie.this.getPlayer().getLocation());
                    MorphZombie.this.clearAll();
                }
            }.runTaskLater((Plugin)CookieGadgets.getInstance(), 80L);
        }
    }
    
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
    }
    
    private void clearAll() {
        for (final Entity entity : this.players) {
            if (entity instanceof Player && CookieGadgets.getGDisguise().isDisguised((Player)entity)) {
                CookieGadgets.getGDisguise().undisguise((Player)entity);
            }
        }
        this.players.clear();
        this.activated = false;
    }
}
