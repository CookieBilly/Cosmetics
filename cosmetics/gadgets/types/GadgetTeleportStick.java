

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.event.player.PlayerTeleportEvent;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import java.util.Set;
import org.bukkit.block.Block;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.Location;

public class GadgetTeleportStick extends Gadget
{
    private Location targetLocation;
    private boolean activated;
    private static int range;
    
    static {
        GadgetTeleportStick.range = 30;
    }
    
    public GadgetTeleportStick(final UUID uuid) {
        super(uuid, GadgetType.TELEPORT_STICK);
        this.activated = false;
        if (FileManager.getGadgetsFile().get(String.valueOf(this.getType().getFilePath()) + ".Range") != null) {
            GadgetTeleportStick.range = FileManager.getGadgetsFile().getInt(String.valueOf(this.getType().getFilePath()) + ".Range");
        }
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            return false;
        }
        final Block targetBlock = BlockUtil.getTargetBlock(this.getPlayer(), GadgetTeleportStick.range);
        if (targetBlock == null) {
            this.getPlayer().sendMessage(MessageType.TARGET_A_BLOCK.getFormatMessage());
            return false;
        }
        final Location location = targetBlock.getLocation();
        if (location == null || (location != null && location.getBlock().isEmpty())) {
            this.getPlayer().sendMessage(MessageType.TARGET_A_BLOCK.getFormatMessage());
            return false;
        }
        final Location location2 = this.getPlayer().getLastTwoTargetBlocks((Set)null, GadgetTeleportStick.range).get(0).getLocation();
        if (WorldGuardUtils.isInBlacklistedRegion(location2, BlacklistedRegion.GADGETS)) {
            this.getPlayer().sendMessage(MessageType.NOT_ALLOWED_TELEPORT_TO_THAT_LOCATION.getFormatMessage());
            return false;
        }
        if (!location.clone().add(0.0, 1.0, 0.0).getBlock().isEmpty() || (!location.clone().add(0.0, 2.0, 0.0).getBlock().isEmpty() && location2.clone().getBlock().isEmpty() && location2.clone().add(0.0, 1.0, 0.0).getBlock().isEmpty() && location2.clone().add(0.0, 2.0, 0.0).getBlock().isEmpty() && location2.clone().add(0.0, 3.0, 0.0).getBlock().isEmpty())) {
            if (BlockUtil.isOutsideOfBorder(location2)) {
                this.getPlayer().sendMessage(MessageType.NOT_ALLOWED_TELEPORT_TO_THAT_LOCATION.getFormatMessage());
                return false;
            }
            this.targetLocation = location2;
            return true;
        }
        else {
            if (BlockUtil.isOutsideOfBorder(location)) {
                this.getPlayer().sendMessage(MessageType.NOT_ALLOWED_TELEPORT_TO_THAT_LOCATION.getFormatMessage());
                return false;
            }
            if (!location.add(0.0, 1.0, 0.0).getBlock().isEmpty() || !location.add(0.0, 2.0, 0.0).getBlock().isEmpty()) {
                this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
                return false;
            }
            this.targetLocation = location;
            return super.checkRequirements();
        }
    }
    
    @Override
    public void onClick() {
        this.activated = true;
        this.targetLocation.setDirection(this.getPlayer().getLocation().getDirection());
        this.targetLocation.add(0.0, 1.25, 0.0);
        this.getPlayer().teleport(this.targetLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
        ParticleEffect.SMOKE_LARGE.display(this.targetLocation, 0.5f, 0.5f, 0.5f, 15);
        ParticleEffect.ENCHANTMENT_TABLE.display(this.targetLocation, 0.5f, 0.5f, 0.5f, 20);
        SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(this.targetLocation);
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                CookieGadgets.getPlayerManager(GadgetTeleportStick.this.getPlayer()).disableFallDamage();
                GadgetTeleportStick.access$0(GadgetTeleportStick.this, false);
            }
        }, 4L);
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        HandlerList.unregisterAll((Listener)this);
    }
    
    static /* synthetic */ void access$0(final GadgetTeleportStick gadgetTeleportStick, final boolean activated) {
        gadgetTeleportStick.activated = activated;
    }
}
