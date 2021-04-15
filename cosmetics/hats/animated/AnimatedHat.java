

package ws.billy.CookieGadgets.cosmetics.hats.animated;

import org.bukkit.plugin.Plugin;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import ws.billy.CookieGadgets.cosmetics.Category;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.UUID;

public class AnimatedHat
{
    private UUID uuid;
    private AnimatedHatType type;
    private Player player;
    
    public AnimatedHat(final UUID uuid, final AnimatedHatType type) {
        this.type = type;
        if (!type.isEnabled()) {
            return;
        }
        if (uuid != null) {
            this.uuid = uuid;
            this.player = Bukkit.getPlayer(uuid);
            if (PermissionUtils.noPermission(this.getPlayer(), type.getPermission(), EnumPermission.ANIMATED_HATS.getPermission(), true)) {
                CookieGadgets.getPlayerManager(this.getPlayer()).unequipAnimatedHat();
                return;
            }
            if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentAnimatedHat() != null) {
                CookieGadgets.getPlayerManager(this.getPlayer()).unequipAnimatedHat();
            }
            try {
                new BukkitRunnable() {
                    int step = -1;
                    
                    public void run() {
                        if (Bukkit.getPlayer(AnimatedHat.this.getPlayerUUID()) != null && CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).getCurrentAnimatedHat() != null && CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).getCurrentAnimatedHat().getType() == AnimatedHat.this.getType()) {
                            if (!Category.ANIMATED_HATS.isEnabled() || !WorldUtils.isWorldEnabled(AnimatedHat.this.getPlayer(), true)) {
                                CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).unequipAnimatedHat();
                                AnimatedHat.this.getPlayer().updateInventory();
                                return;
                            }
                            if (PermissionUtils.noPermission(AnimatedHat.this.getPlayer(), type.getPermission(), EnumPermission.ANIMATED_HATS.getPermission(), true)) {
                                CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).unequipAnimatedHat();
                                AnimatedHat.this.getPlayer().updateInventory();
                                return;
                            }
                            if (AnimatedHat.this.getPlayer().getInventory().getHelmet() != null && !CookieGadgets.getNMSManager().isNBTTagEqual(AnimatedHat.this.getPlayer().getInventory().getHelmet(), "Category", "Animated_Hat")) {
                                if (CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).getEquippedHat() != null) {
                                    CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).unequipHat();
                                }
                                else if (!CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).getEquippedSuitEquipment().isEmpty() && CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).getEquippedSuitEquipment().containsKey(EnumArmorType.HELMET)) {
                                    CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).unequipSuitEquipment(EnumArmorType.HELMET);
                                }
                                else if (CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).getEquippedBanner() != null) {
                                    CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).unequipBanner();
                                }
                                else if (CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).getEquippedEmote() != null) {
                                    CookieGadgets.getPlayerManager(AnimatedHat.this.getPlayer()).unequipEmote();
                                }
                                else {
                                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                                        AnimatedHat.this.getPlayer().getWorld().dropItemNaturally(AnimatedHat.this.getPlayer().getLocation(), AnimatedHat.this.getPlayer().getInventory().getHelmet().clone());
                                        AnimatedHat.this.getPlayer().getInventory().setHelmet((ItemStack)null);
                                        AnimatedHat.this.getPlayer().updateInventory();
                                        return;
                                    });
                                }
                            }
                            ++this.step;
                            try {
                                if (this.step >= AnimatedHat.this.getType().getFrames().size()) {
                                    this.step = 0;
                                }
                                if (this.step <= AnimatedHat.this.getType().getFrames().size() - 1) {
                                    AnimatedHat.this.player.getInventory().setHelmet((ItemStack)AnimatedHat.this.getType().getFrames().get(this.step));
                                }
                            }
                            catch (Exception ex) {
                                AnimatedHat.this.clearAll();
                                this.cancel();
                            }
                        }
                        else {
                            this.cancel();
                        }
                    }
                }.runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), 1L, (long)this.getType().getTicksPerFrame());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentAnimatedHat(this);
        }
    }
    
    public UUID getPlayerUUID() {
        return this.uuid;
    }
    
    public AnimatedHatType getType() {
        return this.type;
    }
    
    protected Player getPlayer() {
        return this.player;
    }
    
    public void clear() {
        if (this.uuid == null) {
            return;
        }
        if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentAnimatedHat() != null) {
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentAnimatedHat(null);
        }
        this.uuid = null;
    }
    
    private void clearAll() {
        this.player.getInventory().setHelmet((ItemStack)null);
    }
}
