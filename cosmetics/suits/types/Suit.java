

package ws.billy.CookieGadgets.cosmetics.suits.types;

import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.cosmetics.suits.SuitEquipmentType;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;
import org.bukkit.event.Listener;

public abstract class Suit implements Listener
{
    private UUID uuid;
    private SuitType type;
    private Player player;
    private PlayerManager pManager;
    
    public Suit(final UUID uuid, final SuitType type) {
        this.type = type;
        if (uuid != null) {
            this.uuid = uuid;
            this.player = Bukkit.getPlayer(uuid);
            this.pManager = CookieGadgets.getPlayerManager(this.player);
            if (this.getPlayer().getInventory().getHelmet() == null || this.getPlayer().getInventory().getChestplate() == null || this.getPlayer().getInventory().getLeggings() == null || this.getPlayer().getInventory().getBoots() == null) {
                return;
            }
            if (this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.HELMET) == null || this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE) == null || this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS) == null || this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.BOOTS) == null) {
                return;
            }
            if (SuitEquipmentType.valueOfDisplayName(this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.HELMET).getDisplayName()).getSuitCategory() != type || SuitEquipmentType.valueOfDisplayName(this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE).getDisplayName()).getSuitCategory() != type || SuitEquipmentType.valueOfDisplayName(this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS).getDisplayName()).getSuitCategory() != type || SuitEquipmentType.valueOfDisplayName(this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.BOOTS).getDisplayName()).getSuitCategory() != type) {
                return;
            }
            if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentSuit() != null) {
                CookieGadgets.getPlayerManager(this.getPlayer()).removeSuit();
            }
            try {
                new BukkitRunnable() {
                    public void run() {
                        if (Suit.this.getPlayer().getInventory().getHelmet() == null || Suit.this.getPlayer().getInventory().getChestplate() == null || Suit.this.getPlayer().getInventory().getLeggings() == null || Suit.this.getPlayer().getInventory().getBoots() == null) {
                            this.cancel();
                            return;
                        }
                        if (Suit.this.getPlayerManager().getCurrentSuit() == null || Suit.this.getPlayerManager().getCurrentSuit().getType() != Suit.this.getType()) {
                            this.cancel();
                            return;
                        }
                        if (Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.HELMET) == null || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE) == null || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS) == null || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.BOOTS) == null) {
                            this.cancel();
                            return;
                        }
                        if (!type.isAbilityEnabled()) {
                            this.cancel();
                            return;
                        }
                        if (Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.HELMET).getSuitCategory() != type || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE).getSuitCategory() != type || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS).getSuitCategory() != type || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.BOOTS).getSuitCategory() != type) {
                            this.cancel();
                            return;
                        }
                        Suit.this.onUpdate();
                    }
                }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, type.getRepeatDelay());
                new BukkitRunnable() {
                    public void run() {
                        if (Suit.this.getPlayer().getInventory().getHelmet() == null || Suit.this.getPlayer().getInventory().getChestplate() == null || Suit.this.getPlayer().getInventory().getLeggings() == null || Suit.this.getPlayer().getInventory().getBoots() == null) {
                            return;
                        }
                        if (Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.HELMET) == null || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE) == null || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS) == null || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.BOOTS) == null) {
                            return;
                        }
                        if (Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.HELMET).getSuitCategory() != type || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE).getSuitCategory() != type || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS).getSuitCategory() != type || Suit.this.getPlayerManager().getEquippedSuitEquipment().get(EnumArmorType.BOOTS).getSuitCategory() != type) {
                            return;
                        }
                        if (Bukkit.getPlayer(Suit.this.getPlayerUUID()) == null || CookieGadgets.getPlayerManager(Suit.this.getPlayer()).getCurrentSuit() == null) {
                            this.cancel();
                            return;
                        }
                        if (Suit.this.getPlayerManager().getCurrentSuit().getType() == Suit.this.getType()) {
                            if (CookieGadgets.getCookieGadgetsData().isCooldownInActionBar() && Suit.this.getPlayerManager().suitCooldown().containsKey(Suit.this.getType())) {
                                if (Suit.this.getPlayerManager().suitCooldown().get(Suit.this.getType()) - System.currentTimeMillis() > 0L && !Suit.this.getPlayerManager().isBypassCooldown()) {
                                    Suit.this.getPlayerManager().sendCooldownBar(type.getDisplayName(), Suit.this.getPlayerManager().suitCooldown().get(Suit.this.getType()), type.getCooldown());
                                    return;
                                }
                                Suit.this.getPlayerManager().resetCooldownBar();
                                Suit.this.getPlayerManager().suitCooldown().remove(Suit.this.getType());
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
        }
        CookieGadgets.getInstance().registerListener((Listener)this);
        CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentSuit(this);
    }
    
    protected UUID getPlayerUUID() {
        return this.uuid;
    }
    
    public SuitType getType() {
        return this.type;
    }
    
    protected Player getPlayer() {
        return this.player;
    }
    
    protected final PlayerManager getPlayerManager() {
        return this.pManager;
    }
    
    public boolean isBeingCooldown() {
        if (!this.getPlayerManager().suitCooldown().containsKey(this.getType()) || this.getPlayerManager().isBypassCooldown()) {
            return false;
        }
        if (this.getPlayerManager().suitCooldown().get(this.getType()) - System.currentTimeMillis() <= 0L) {
            this.getPlayerManager().suitCooldown().remove(this.getType());
            return false;
        }
        this.player.sendMessage(MessageType.COOLDOWN.getFormatMessage().replace("{COOLDOWN}", String.valueOf((this.getPlayerManager().suitCooldown().get(this.getType()) - System.currentTimeMillis()) / 1000L + 1L)));
        this.player.updateInventory();
        return true;
    }
    
    public void addCooldownTimer() {
        if (!this.getPlayerManager().isBypassCooldown() && this.getType().getCooldown() > 0) {
            this.getPlayerManager().suitCooldown().put(this.getType(), System.currentTimeMillis() + this.getType().getCooldown() * 1000L);
        }
    }
    
    abstract void onUpdate();
    
    public abstract void onClear();
    
    public void clear() {
        if (this.uuid == null) {
            return;
        }
        if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentSuit() != null) {
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentSuit(null);
        }
        this.uuid = null;
    }
}
