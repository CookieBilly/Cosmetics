

package ws.billy.CookieGadgets.cosmetics.suits.types;

import ws.billy.CookieGadgets.utils.MessageType;
import java.io.File;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;

public class SuitBumblebee extends Suit
{
    private boolean activated;
    private boolean clicked;
    private boolean flyToActivate;
    private PositionSongPlayer positionSongPlayer;
    
    public SuitBumblebee(final UUID uuid) {
        super(uuid, SuitType.BUMBLEBEE);
        this.activated = false;
        this.clicked = false;
        this.flyToActivate = false;
    }
    
    public void onUpdate() {
        if (this.clicked && !this.activated && !this.isBeingCooldown()) {
            this.activated = true;
            this.clicked = false;
            this.playSong(this.getPlayer(), 20);
            this.addCooldownTimer();
        }
        if (this.getPlayer().isFlying() && !this.activated && !this.isBeingCooldown()) {
            this.activated = true;
            this.flyToActivate = true;
            this.playSong(this.getPlayer(), 20);
            this.addCooldownTimer();
        }
        if (!this.getPlayer().isFlying() && this.flyToActivate) {
            this.clearAll();
        }
        if (this.positionSongPlayer != null && this.activated) {
            this.positionSongPlayer.setTargetLocation(this.getPlayer().getEyeLocation().add(-0.5, -0.5, -0.5));
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        if (this.positionSongPlayer != null && this.positionSongPlayer.isPlaying()) {
            this.positionSongPlayer.setPlaying(false);
        }
        this.positionSongPlayer = null;
        this.clicked = false;
        this.activated = false;
        this.flyToActivate = false;
    }
    
    private void playSong(final Player player, final int n) {
        final File file = new File(String.valueOf(CookieGadgets.getInstance().getDataFolder().getPath()) + "/songs/Bumblebee.nbs");
        if (!file.exists()) {
            CookieGadgets.getInstance().saveResource("songs/Bumblebee.nbs", true);
        }
        (this.positionSongPlayer = new PositionSongPlayer(NBSDecoder.parse(file))).setTargetLocation(player.getEyeLocation().add(-0.5, -0.5, -0.5));
        this.positionSongPlayer.setPlaying(true);
        this.positionSongPlayer.addPlayer(player);
        this.positionSongPlayer.setVolume((byte)100);
        this.positionSongPlayer.getFadeIn().setFadeStart((byte)25);
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                SuitBumblebee.this.clearAll();
            }
        }, n * 20L);
    }
    
    @EventHandler
    public void onPlayerClick(final PlayerInteractEvent playerInteractEvent) {
        if ((playerInteractEvent.getAction() != Action.LEFT_CLICK_AIR && playerInteractEvent.getAction() != Action.LEFT_CLICK_BLOCK) || playerInteractEvent.getPlayer() != this.getPlayer() || this.clicked) {
            return;
        }
        if (playerInteractEvent.getPlayer().getItemInHand().getType() != Material.AIR) {
            return;
        }
        if (this.isBeingCooldown()) {
            playerInteractEvent.setCancelled(true);
            return;
        }
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.TURN_OFF_BUMBLEBEE_SONG.getFormatMessage());
            this.clearAll();
            playerInteractEvent.setCancelled(true);
            return;
        }
        playerInteractEvent.setCancelled(this.clicked = true);
    }
}
