

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerTeleportEvent;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.CookieGadgets;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.configuration.FileManager;
import org.bukkit.entity.Player;

public class GadgetTeleporter extends Gadget
{
    private boolean activated;
    private boolean stopCounting;
    private int steps;
    private Player targetPlayer;
    private static String initiatingMessage;
    private static String scanningMessage;
    private static String targetAcquiredMessage;
    private static String teleportInitiatingMessage;
    private static String teleportCompleteMessage;
    private static String processFailedMessage;
    private static String abortedMessage;
    
    static {
        GadgetTeleporter.initiatingMessage = FileManager.getGadgetsFile().getString("Gadgets.Movement.Types.Teleporter.Messages.Initiating-Teleport-Sequence");
        GadgetTeleporter.scanningMessage = FileManager.getGadgetsFile().getString("Gadgets.Movement.Types.Teleporter.Messages.Scanning-Players");
        GadgetTeleporter.targetAcquiredMessage = FileManager.getGadgetsFile().getString("Gadgets.Movement.Types.Teleporter.Messages.Target-Acquired");
        GadgetTeleporter.teleportInitiatingMessage = FileManager.getGadgetsFile().getString("Gadgets.Movement.Types.Teleporter.Messages.Teleport-Initiating");
        GadgetTeleporter.teleportCompleteMessage = FileManager.getGadgetsFile().getString("Gadgets.Movement.Types.Teleporter.Messages.Teleport-Complete");
        GadgetTeleporter.processFailedMessage = FileManager.getGadgetsFile().getString("Gadgets.Movement.Types.Teleporter.Messages.Process-Failed");
        GadgetTeleporter.abortedMessage = FileManager.getGadgetsFile().getString("Gadgets.Movement.Types.Teleporter.Messages.Aborted-Teleport-Sequence");
    }
    
    public GadgetTeleporter(final UUID uuid) {
        super(uuid, GadgetType.TELEPORTER);
        this.activated = false;
        this.stopCounting = false;
        this.steps = 0;
        this.targetPlayer = null;
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.activated = true;
    }
    
    @Override
    public void onUpdate() {
        if (this.activated) {
            if (this.steps % 40 == 0 && !this.stopCounting) {
                if (Bukkit.getOnlinePlayers().size() <= 1 && this.steps / 40 == 2) {
                    this.sendFailedMessage();
                    return;
                }
                if (this.steps / 40 > 2) {
                    if (this.targetPlayer == null) {
                        this.sendFailedMessage();
                        return;
                    }
                    if (!this.targetPlayer.isOnline()) {
                        this.sendFailedMessage();
                        return;
                    }
                }
                if (this.steps == 0) {
                    this.getPlayer().sendMessage(ChatUtil.format(GadgetTeleporter.initiatingMessage));
                }
                else if (this.steps / 40 == 1) {
                    this.getPlayer().sendMessage(ChatUtil.format(GadgetTeleporter.scanningMessage));
                    SoundEffect.ENTITY_ENDER_DRAGON_HURT.playSound(this.getPlayer());
                }
                else if (this.steps / 40 == 2) {
                    this.getPlayer().sendMessage(ChatUtil.format(GadgetTeleporter.targetAcquiredMessage));
                    SoundEffect.ENTITY_ENDER_DRAGON_HURT.playSound(this.getPlayer());
                    final ArrayList<Player> list = new ArrayList<Player>();
                    for (final Player e : Bukkit.getOnlinePlayers()) {
                        if (e != this.getPlayer()) {
                            if (!e.isOnline()) {
                                continue;
                            }
                            list.add(e);
                        }
                    }
                    this.targetPlayer = list.get(CookieGadgets.random().nextInt(list.size()));
                }
                else if (this.steps / 40 == 3) {
                    this.getPlayer().sendMessage(ChatUtil.format(GadgetTeleporter.teleportInitiatingMessage));
                    SoundEffect.ENTITY_ENDER_DRAGON_HURT.playSound(this.getPlayer());
                }
                else if (this.steps / 40 == 4) {
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        if (this.targetPlayer == null) {
                            this.sendFailedMessage();
                            return;
                        }
                        else if (!this.targetPlayer.isOnline()) {
                            this.sendFailedMessage();
                            return;
                        }
                        else if (WorldGuardUtils.isInBlacklistedRegion(this.targetPlayer.getLocation(), BlacklistedRegion.GADGETS) || BlockUtil.isOutsideOfBorder(this.targetPlayer.getLocation())) {
                            this.sendFailedMessage();
                            return;
                        }
                        else {
                            this.getPlayer().teleport((Entity)this.targetPlayer, PlayerTeleportEvent.TeleportCause.PLUGIN);
                            this.getPlayer().sendMessage(ChatUtil.format(GadgetTeleporter.teleportCompleteMessage));
                            SoundEffect.ENTITY_ENDER_DRAGON_FLAP.playSound(this.getPlayer());
                            this.clearAll();
                            return;
                        }
                    });
                }
            }
            ++this.steps;
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        this.stopCounting = false;
        this.targetPlayer = null;
        this.steps = 0;
        this.activated = false;
    }
    
    private void sendFailedMessage() {
        this.getPlayer().sendMessage(ChatUtil.format(GadgetTeleporter.processFailedMessage));
        SoundEffect.ENTITY_ENDERMAN_DEATH.playSound(this.getPlayer());
        this.stopCounting = true;
        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            if (!this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType() || !this.activated) {
                this.onClear();
            }
            else {
                this.getPlayer().sendMessage(ChatUtil.format(GadgetTeleporter.abortedMessage));
                this.clearAll();
            }
        }, 20L);
    }
}
