// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.DyeColor;
import ws.billy.CookieGadgets.utils.SoundEffect;
import java.text.DecimalFormat;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.configuration.FileManager;
import org.bukkit.entity.Sheep;

public class GadgetExplodingSheep extends Gadget
{
    private boolean activated;
    private Sheep sheep;
    private static String remainTime;
    
    static {
        GadgetExplodingSheep.remainTime = FileManager.getGadgetsFile().getString("Gadgets.Mobs And NPCs.Types.Exploding Sheep.Remain-Time");
    }
    
    public GadgetExplodingSheep(final UUID uuid) {
        super(uuid, GadgetType.EXPLODING_SHEEP);
        this.activated = false;
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
        final Location add = this.getPlayer().getLocation().add(this.getPlayer().getLocation().getDirection().multiply(0.5));
        add.setY(this.getPlayer().getLocation().getY() + 1.0);
        CookieGadgets.setBypassCreatureSpawn(true);
        final Sheep sheep = (Sheep)this.getPlayer().getWorld().spawn(add, (Class)Sheep.class);
        sheep.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        sheep.setAgeLock(true);
        sheep.setCustomNameVisible(true);
        sheep.setCustomName(ChatUtil.format(GadgetExplodingSheep.remainTime.replace("{REMAIN_TIME}", "3.0")));
        CookieGadgets.getNMSManager().clearPathfinders((Entity)sheep);
        this.sheep = sheep;
        CookieGadgets.setBypassCreatureSpawn(false);
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!GadgetExplodingSheep.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetExplodingSheep.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetExplodingSheep.this.getPlayer()).getCurrentGadget().getType() != GadgetExplodingSheep.this.getType() || GadgetExplodingSheep.this.sheep == null) {
                    this.step = 15;
                    GadgetExplodingSheep.this.onClear();
                    return;
                }
                GadgetExplodingSheep.this.sheep.setCustomName(ChatUtil.format(GadgetExplodingSheep.remainTime.replace("{REMAIN_TIME}", new DecimalFormat("0.0").format(3.0 - this.step * 4.0 / 20.0))));
                if (this.step <= 14) {
                    SoundEffect.UI_BUTTON_CLICK.playSound((Entity)sheep);
                    if (sheep.getColor() == DyeColor.RED) {
                        sheep.setColor(DyeColor.WHITE);
                    }
                    else {
                        sheep.setColor(DyeColor.RED);
                    }
                }
                else {
                    ParticleEffect.EXPLOSION_LARGE.display(sheep.getLocation().add(0.0, 1.0, 0.0), 0.5f, 5);
                    SoundEffect.ENTITY_GENERIC_EXPLODE.playSound((Entity)sheep);
                    GadgetExplodingSheep.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 4L);
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        if (this.sheep != null) {
            this.sheep.remove();
        }
        this.sheep = null;
        this.activated = false;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerShearSheep(final PlayerShearEntityEvent playerShearEntityEvent) {
        if (CookieGadgets.getNMSManager().hasNBTTag(playerShearEntityEvent.getPlayer().getItemInHand(), "Cosmetics")) {
            playerShearEntityEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerShearBlock(final BlockBreakEvent blockBreakEvent) {
        if (CookieGadgets.getNMSManager().hasNBTTag(blockBreakEvent.getPlayer().getItemInHand(), "Cosmetics")) {
            blockBreakEvent.setCancelled(true);
        }
    }
}
