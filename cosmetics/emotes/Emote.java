

package ws.billy.CookieGadgets.cosmetics.emotes;

import org.bukkit.event.EventHandler;
import java.util.Iterator;
import ws.billy.CookieGadgets.nms.interfaces.ArmorStandSlot;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import ws.billy.CookieGadgets.cosmetics.Category;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSArmorStand;
import org.bukkit.entity.Player;
import java.util.UUID;
import org.bukkit.event.Listener;

public class Emote implements Listener
{
    private UUID uuid;
    private EmoteType type;
    private Player player;
    private Listener listener;
    public boolean activated;
    private NMSArmorStand hologram;
    
    public Emote(final UUID uuid, final EmoteType type) {
        this.activated = false;
        this.type = type;
        if (!type.isEnabled()) {
            return;
        }
        if (uuid != null) {
            this.uuid = uuid;
            this.player = Bukkit.getPlayer(uuid);
            if (PermissionUtils.noPermission(this.getPlayer(), type.getPermission(), EnumPermission.EMOTES.getPermission(), true)) {
                CookieGadgets.getPlayerManager(this.getPlayer()).unequipEmote();
                return;
            }
            if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentEmote() != null) {
                CookieGadgets.getPlayerManager(this.getPlayer()).removeEmote();
            }
            try {
                new BukkitRunnable() {
                    public void run() {
                        if (Bukkit.getPlayer(Emote.this.getPlayerUUID()) != null && CookieGadgets.getPlayerManager(Emote.this.getPlayer()).getCurrentEmote() != null && CookieGadgets.getPlayerManager(Emote.this.getPlayer()).getCurrentEmote().getType() == Emote.this.getType()) {
                            if (CookieGadgets.getCookieGadgetsData().isCooldownInActionBar() && CookieGadgets.getPlayerManager(Emote.this.player).emoteCooldown().containsKey(Emote.this.getType())) {
                                if (CookieGadgets.getPlayerManager(Emote.this.player).emoteCooldown().get(Emote.this.getType()) - System.currentTimeMillis() > 0L && !CookieGadgets.getPlayerManager(Emote.this.player).isBypassCooldown()) {
                                    CookieGadgets.getPlayerManager(Emote.this.getPlayer()).sendCooldownBar(type.getDisplayName(), CookieGadgets.getPlayerManager(Emote.this.player).emoteCooldown().get(Emote.this.getType()), type.getCooldown());
                                }
                                else {
                                    CookieGadgets.getPlayerManager(Emote.this.getPlayer()).resetCooldownBar();
                                    CookieGadgets.getPlayerManager(Emote.this.getPlayer()).emoteCooldown().remove(Emote.this.getType());
                                }
                            }
                            if (Emote.this.activated && Emote.this.getType() == EmoteType.RAGE) {
                                ParticleEffect.VILLAGER_ANGRY.display(Emote.this.getPlayer().getLocation().add(0.0, 2.4, 0.0), 0.0f, 1);
                            }
                        }
                        else {
                            this.cancel();
                        }
                    }
                }.runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), 0L, 1L);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.listener = (Listener)new EmoteListener(this);
            CookieGadgets.getInstance().registerListener(this.listener);
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentEmote(this);
        }
    }
    
    public UUID getPlayerUUID() {
        return this.uuid;
    }
    
    public EmoteType getType() {
        return this.type;
    }
    
    protected Player getPlayer() {
        return this.player;
    }
    
    public void removeListener() {
        HandlerList.unregisterAll((Listener)this);
    }
    
    public void unregisterListeners() {
        try {
            HandlerList.unregisterAll((Listener)this);
            HandlerList.unregisterAll(this.listener);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void clear() {
        if (this.uuid == null) {
            return;
        }
        if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentEmote() != null) {
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentEmote(null);
        }
        this.unregisterListeners();
        this.removeHologram();
        this.uuid = null;
    }
    
    public void removeHologram() {
        if (this.hologram != null && !this.hologram.isDeadNMS()) {
            this.hologram.killEntityNMS();
        }
        this.hologram = null;
    }
    
    private void clearAll() {
        this.player.getInventory().setHelmet((ItemStack)null);
        this.removeHologram();
        this.activated = false;
    }
    
    static /* synthetic */ void access$3(final Emote emote, final NMSArmorStand hologram) {
        emote.hologram = hologram;
    }
    
    public class EmoteListener implements Listener
    {
        private Emote emote;
        
        public EmoteListener(final Emote emote) {
            this.emote = emote;
        }
        
        @EventHandler
        protected void onPlayerActivateEmote(final PlayerInteractEvent playerInteractEvent) {
            final Player player = playerInteractEvent.getPlayer();
            final UUID uniqueId = player.getUniqueId();
            if (playerInteractEvent.getAction() != Action.RIGHT_CLICK_AIR && playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK) {
                return;
            }
            if (!player.getItemInHand().hasItemMeta() || !player.getItemInHand().getItemMeta().hasDisplayName()) {
                return;
            }
            if (!uniqueId.equals(this.emote.uuid)) {
                return;
            }
            for (final EmoteType emoteType : EmoteType.values()) {
                if (player.getItemInHand().getItemMeta().getDisplayName().equals(emoteType.getDisplayName())) {
                    if (!Category.EMOTES.isEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
                        CookieGadgets.getPlayerManager(Emote.this.getPlayer()).unequipEmote();
                        player.updateInventory();
                        playerInteractEvent.setCancelled(true);
                        return;
                    }
                    if (CookieGadgets.getPlayerManager(player).emoteCooldown().containsKey(Emote.this.getType()) && !CookieGadgets.getPlayerManager(player).isBypassCooldown()) {
                        if (CookieGadgets.getPlayerManager(player).emoteCooldown().get(Emote.this.getType()) - System.currentTimeMillis() > 0L) {
                            player.sendMessage(MessageType.COOLDOWN.getFormatMessage().replace("{COOLDOWN}", String.valueOf((CookieGadgets.getPlayerManager(player).emoteCooldown().get(Emote.this.getType()) - System.currentTimeMillis()) / 1000L + 1L)));
                            player.updateInventory();
                            playerInteractEvent.setCancelled(true);
                            return;
                        }
                        CookieGadgets.getPlayerManager(player).emoteCooldown().remove(Emote.this.getType());
                    }
                    if (this.emote.activated) {
                        player.sendMessage(MessageType.EMOTE_IS_ACTIVATED.getFormatMessage());
                        player.updateInventory();
                        playerInteractEvent.setCancelled(true);
                        return;
                    }
                    if (PermissionUtils.noPermission(Emote.this.getPlayer(), Emote.this.type.getPermission(), EnumPermission.EMOTES.getPermission(), true)) {
                        CookieGadgets.getPlayerManager(player).unequipEmote();
                        player.updateInventory();
                        playerInteractEvent.setCancelled(true);
                        return;
                    }
                    if (player.getInventory().getHelmet() != null) {
                        if (CookieGadgets.getPlayerManager(player).getEquippedHat() != null) {
                            CookieGadgets.getPlayerManager(player).unequipHat();
                        }
                        else if (CookieGadgets.getPlayerManager(player).getEquippedAnimatedHat() != null) {
                            CookieGadgets.getPlayerManager(player).unequipAnimatedHat();
                        }
                        else if (!CookieGadgets.getPlayerManager(player).getEquippedSuitEquipment().isEmpty() && CookieGadgets.getPlayerManager(player).getEquippedSuitEquipment().containsKey(EnumArmorType.HELMET)) {
                            CookieGadgets.getPlayerManager(player).unequipSuitEquipment(EnumArmorType.HELMET);
                        }
                        else if (CookieGadgets.getPlayerManager(player).getEquippedBanner() != null) {
                            CookieGadgets.getPlayerManager(player).unequipBanner();
                        }
                        else {
                            player.getWorld().dropItemNaturally(player.getLocation(), player.getInventory().getHelmet().clone());
                            player.getInventory().setHelmet((ItemStack)null);
                            player.updateInventory();
                        }
                    }
                    this.emote.activated = true;
                    if (emoteType.isHologramEnabled()) {
                        final double n;
                        final EmoteType emoteType2;
                        Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                            if (Emote.this.hologram != null) {
                                Emote.this.removeHologram();
                            }
                            n = (VersionManager.is1_9OrAbove() ? (VersionManager.is1_11OrAbove() ? 2.2 : 2.13) : 1.25);
                            Emote.access$3(Emote.this, CookieGadgets.getNMSManager().spawnNMSArmorStandFollower(Emote.this.getPlayer().getWorld(), Emote.this.getPlayer().getLocation().getX(), Emote.this.getPlayer().getLocation().getY() + n, Emote.this.getPlayer().getLocation().getZ(), Emote.this.getPlayer(), n, ChatUtil.format(emoteType2.getHologram()), null, null));
                            return;
                        });
                    }
                    if (this.emote.activated) {
                        new BukkitRunnable() {
                            int step = -1;
                            
                            public void run() {
                                if (!player.isOnline() || !Emote.this.activated || CookieGadgets.getPlayerManager(player).getCurrentEmote() == null || CookieGadgets.getPlayerManager(player).getCurrentEmote().getType() != Emote.this.getType()) {
                                    this.step = Emote.this.getType().getFrames().size();
                                    Emote.this.removeHologram();
                                    Emote.this.activated = false;
                                    this.cancel();
                                    return;
                                }
                                ++this.step;
                                if (Bukkit.getPlayer(Emote.this.getPlayerUUID()) != null && CookieGadgets.getPlayerManager(player).getCurrentEmote() != null && CookieGadgets.getPlayerManager(player).getCurrentEmote().getType() == Emote.this.getType()) {
                                    try {
                                        if (this.step <= Emote.this.getType().getFrames().size() - 1) {
                                            player.getInventory().setHelmet((ItemStack)Emote.this.getType().getFrames().get(this.step));
                                        }
                                        else {
                                            Emote.this.clearAll();
                                            this.cancel();
                                        }
                                    }
                                    catch (Exception ex) {
                                        Emote.this.clearAll();
                                        this.cancel();
                                    }
                                }
                                else {
                                    Emote.this.clearAll();
                                    this.cancel();
                                }
                            }
                        }.runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), 1L, (long)Emote.this.getType().getTicksPerFrame());
                    }
                    if (!CookieGadgets.getPlayerManager(player).isBypassCooldown()) {
                        CookieGadgets.getPlayerManager(player).emoteCooldown().put(Emote.this.getType(), System.currentTimeMillis() + Emote.this.getType().getCooldown() * 1000L);
                    }
                    player.updateInventory();
                    playerInteractEvent.setCancelled(true);
                }
            }
        }
    }
}
