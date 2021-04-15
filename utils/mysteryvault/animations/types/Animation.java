

package ws.billy.CookieGadgets.utils.mysteryvault.animations.types;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Quality;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.player.PlayerManager;

public abstract class Animation
{
    private PlayerManager pManager;
    private MysteryVault mysteryVault;
    private AnimationType animation;
    private Quality quality;
    private long delay;
    protected int steps;
    protected boolean isContinue;
    
    public Animation(final PlayerManager pManager, final MysteryVault mysteryVault, final AnimationType animation, final Quality quality, final long delay) {
        this.steps = 0;
        this.isContinue = true;
        this.mysteryVault = mysteryVault;
        this.animation = animation;
        this.quality = quality;
        this.delay = delay;
        if (!Bukkit.getServer().getWorlds().contains(this.mysteryVault.getLocation().getWorld())) {
            return;
        }
        if (pManager != null) {
            this.pManager = pManager;
            if (PermissionUtils.noPermission(pManager.getPlayer(), animation.getPermission(), EnumPermission.ALL_MYSTERY_VAULT_ANIMATIONS.getPermission(), true)) {
                mysteryVault.setActivate(false);
                pManager.setOpeningMysteryBox(false);
                return;
            }
            this.onStart();
            this.playSound();
            new BukkitRunnable() {
                public void run() {
                    if (Animation.this.getPlayer() == null || !Animation.this.getPlayer().isOnline()) {
                        Animation.this.clear();
                        this.cancel();
                        return;
                    }
                    if (!Animation.this.isContinue) {
                        mysteryVault.setActivate(false);
                        this.cancel();
                        return;
                    }
                    try {
                        Animation.this.onUpdate();
                        Animation.this.onUpdateParticleEffect();
                        Animation.this.onUpdateSound();
                        final Animation this$0 = Animation.this;
                        ++this$0.steps;
                    }
                    catch (NullPointerException ex) {
                        ex.printStackTrace();
                        Animation.this.clear();
                        this.cancel();
                    }
                }
            }.runTaskTimer((Plugin)CookieGadgets.getInstance(), delay, animation.getRepeatDelay());
        }
    }
    
    protected PlayerManager getPlayerManager() {
        return this.pManager;
    }
    
    protected Player getPlayer() {
        return this.pManager.getPlayer();
    }
    
    public MysteryVault getMysteryVault() {
        return this.mysteryVault;
    }
    
    public Quality getQuality() {
        return this.quality;
    }
    
    public AnimationType getAnimation() {
        return this.animation;
    }
    
    public long getDelay() {
        return this.delay;
    }
    
    public abstract void onStart();
    
    public abstract void onUpdate();
    
    protected void playSound() {
    }
    
    public abstract void onUpdateParticleEffect();
    
    public abstract void onUpdateSound();
    
    public abstract void onClear();
    
    public void clear() {
        if (this.pManager == null) {
            return;
        }
        this.mysteryVault.setActivate(false);
        this.pManager.setOpeningMysteryBox(false);
        this.isContinue = false;
    }
}
