

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.Location;
import ws.billy.CookieGadgets.nms.interfaces.ArmorStandSlot;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.PlayerUtils;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.configuration.FileManager;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSArmorStand;
import java.util.HashMap;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class GadgetFlowerGiver extends Gadget
{
    public static ArrayList<Player> pendingPlayers;
    public static HashMap<Player, NMSArmorStand> acceptedFlowerPlayers;
    public static ArrayList<Player> declinedFlowerPlayers;
    private ArrayList<Player> privateTargetPlayers;
    private Player targetPlayer;
    private static String targetAPlayerMessage;
    private static String alreadyHadAFlowerMessage;
    private static String sentAFlowerMessage;
    private static String alreadySentARequestMessage;
    private static String receivedFlowerMessage;
    private static String acceptMessage;
    private static String declineMessage;
    private static String acceptJsonMessage;
    private static String declineJsonMessage;
    public static String noPendingInviteMessage;
    private static String acceptedFlowerMessage;
    private static String declinedFlowerMessage;
    private static String rightClickToRemoveFlowerMessage;
    private static ItemStack[] flowers;
    
    static {
        GadgetFlowerGiver.pendingPlayers = new ArrayList<Player>();
        GadgetFlowerGiver.acceptedFlowerPlayers = new HashMap<Player, NMSArmorStand>();
        GadgetFlowerGiver.declinedFlowerPlayers = new ArrayList<Player>();
        GadgetFlowerGiver.targetAPlayerMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Target-A-Player");
        GadgetFlowerGiver.alreadyHadAFlowerMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Already-Had-A-Flower");
        GadgetFlowerGiver.sentAFlowerMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Sent-A-Flower");
        GadgetFlowerGiver.alreadySentARequestMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Already-Sent-A-Request");
        GadgetFlowerGiver.receivedFlowerMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Received-Flower");
        GadgetFlowerGiver.acceptMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Accept");
        GadgetFlowerGiver.declineMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Decline");
        GadgetFlowerGiver.acceptJsonMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Accept-Json-message");
        GadgetFlowerGiver.declineJsonMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Decline-Json-message");
        GadgetFlowerGiver.noPendingInviteMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.No-Pending-Invite");
        GadgetFlowerGiver.acceptedFlowerMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Accepted-Flower");
        GadgetFlowerGiver.declinedFlowerMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Declined-Flower");
        GadgetFlowerGiver.rightClickToRemoveFlowerMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Flower Giver.Messages.Right-Click-To-Remove-Flower");
        GadgetFlowerGiver.flowers = new ItemStack[] { new ItemStack(EnumMaterial.DANDELION.getType()), new ItemStack(EnumMaterial.POPPY.getType(), 0), new ItemStack(EnumMaterial.BLUE_ORCHID.getType(), 1), new ItemStack(EnumMaterial.ALLIUM.getType(), 2), new ItemStack(EnumMaterial.AZURE_BLUET.getType(), 3), new ItemStack(EnumMaterial.RED_TULIP.getType(), 4), new ItemStack(EnumMaterial.ORANGE_TULIP.getType(), 5), new ItemStack(EnumMaterial.WHITE_TULIP.getType(), 6), new ItemStack(EnumMaterial.PINK_TULIP.getType(), 7), new ItemStack(EnumMaterial.OXEYE_DAISY.getType(), 8) };
    }
    
    public GadgetFlowerGiver(final UUID uuid) {
        super(uuid, GadgetType.FLOWER_GIVER);
        this.privateTargetPlayers = new ArrayList<Player>();
        this.targetPlayer = null;
    }
    
    @Override
    protected boolean checkRequirements() {
        final Entity entityPlayerLooking = PlayerUtils.getEntityPlayerLookingAt(this.getPlayer(), 10);
        if (entityPlayerLooking == null) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetFlowerGiver.targetAPlayerMessage));
            return false;
        }
        if (!(entityPlayerLooking instanceof Player)) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetFlowerGiver.targetAPlayerMessage));
            return false;
        }
        if (!this.getPlayer().canSee((Player)entityPlayerLooking)) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetFlowerGiver.targetAPlayerMessage));
            return false;
        }
        if (this.targetPlayer != null && entityPlayerLooking == this.targetPlayer) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetFlowerGiver.alreadySentARequestMessage).replace("{PLAYER}", this.targetPlayer.getName()));
            return false;
        }
        if (GadgetFlowerGiver.acceptedFlowerPlayers.containsKey(entityPlayerLooking)) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetFlowerGiver.alreadyHadAFlowerMessage));
            return false;
        }
        if (GadgetFlowerGiver.pendingPlayers.contains(entityPlayerLooking)) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetFlowerGiver.alreadyHadAFlowerMessage));
            return false;
        }
        this.targetPlayer = (Player)entityPlayerLooking;
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        if (this.targetPlayer != null && this.targetPlayer.isOnline()) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetFlowerGiver.sentAFlowerMessage).replace("{PLAYER}", this.targetPlayer.getName()));
            this.targetPlayer.sendMessage(ChatUtil.format(GadgetFlowerGiver.receivedFlowerMessage).replace("{PLAYER}", this.getPlayer().getName()));
            CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(GadgetFlowerGiver.acceptMessage)).showText(ChatUtil.format(GadgetFlowerGiver.acceptJsonMessage)).runCommand("/flowergiver accept {PLAYER}".replace("{PLAYER}", this.getPlayer().getName())).then(" ").then(ChatUtil.format(GadgetFlowerGiver.declineMessage)).showText(ChatUtil.format(GadgetFlowerGiver.declineJsonMessage)).runCommand("/flowergiver decline {PLAYER}".replace("{PLAYER}", this.getPlayer().getName())).send(this.targetPlayer);
            if (!GadgetFlowerGiver.pendingPlayers.contains(this.targetPlayer)) {
                GadgetFlowerGiver.pendingPlayers.add(this.targetPlayer);
            }
            if (!this.privateTargetPlayers.contains(this.targetPlayer)) {
                this.privateTargetPlayers.add(this.targetPlayer);
            }
            return;
        }
        this.getPlayer().sendMessage(ChatUtil.format(GadgetFlowerGiver.targetAPlayerMessage));
    }
    
    @Override
    public void onUpdate() {
        for (final Player o : this.privateTargetPlayers) {
            if (GadgetFlowerGiver.declinedFlowerPlayers.contains(o)) {
                if (GadgetFlowerGiver.pendingPlayers.contains(o)) {
                    GadgetFlowerGiver.pendingPlayers.remove(o);
                }
                if (this.targetPlayer == o) {
                    this.targetPlayer = null;
                }
                GadgetFlowerGiver.declinedFlowerPlayers.remove(o);
                this.privateTargetPlayers.remove(o);
                break;
            }
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        for (final Player o : this.privateTargetPlayers) {
            if (GadgetFlowerGiver.pendingPlayers.contains(o)) {
                GadgetFlowerGiver.pendingPlayers.remove(o);
            }
            if (GadgetFlowerGiver.acceptedFlowerPlayers.containsKey(o)) {
                final NMSArmorStand nmsArmorStand = GadgetFlowerGiver.acceptedFlowerPlayers.get(o);
                if (!nmsArmorStand.isDeadNMS()) {
                    nmsArmorStand.killEntityNMS();
                }
                GadgetFlowerGiver.acceptedFlowerPlayers.remove(o);
            }
            if (GadgetFlowerGiver.declinedFlowerPlayers.contains(o)) {
                GadgetFlowerGiver.declinedFlowerPlayers.remove(o);
            }
        }
        this.privateTargetPlayers.clear();
        this.targetPlayer = null;
    }
    
    public static void removeFlower(final Player key) {
        if (key == null) {
            return;
        }
        if (GadgetFlowerGiver.pendingPlayers.contains(key)) {
            GadgetFlowerGiver.pendingPlayers.remove(key);
        }
        if (GadgetFlowerGiver.acceptedFlowerPlayers.containsKey(key)) {
            final NMSArmorStand nmsArmorStand = GadgetFlowerGiver.acceptedFlowerPlayers.get(key);
            if (!nmsArmorStand.isDeadNMS()) {
                nmsArmorStand.killEntityNMS();
            }
            GadgetFlowerGiver.acceptedFlowerPlayers.remove(key);
        }
    }
    
    @EventHandler
    public void onPlayerExecuteFlowerGiverCommand(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        final Player player = playerCommandPreprocessEvent.getPlayer();
        if (player == this.targetPlayer && GadgetFlowerGiver.pendingPlayers.contains(player) && playerCommandPreprocessEvent.getMessage().startsWith("/flowergiver")) {
            if (playerCommandPreprocessEvent.getMessage().contains("accept")) {
                final Player player2 = Bukkit.getPlayer(playerCommandPreprocessEvent.getMessage().replace("/flowergiver accept ", ""));
                if (player2 != null && player2.isOnline()) {
                    player2.sendMessage(ChatUtil.format(GadgetFlowerGiver.acceptedFlowerMessage).replace("{PLAYER}", player.getName()));
                    player.sendMessage(ChatUtil.format(GadgetFlowerGiver.rightClickToRemoveFlowerMessage));
                    final Location location = player.getLocation();
                    final double n = VersionManager.is1_9OrAbove() ? (VersionManager.is1_11OrAbove() ? 1.8 : 1.73) : 2.0;
                    final NMSArmorStand spawnNMSArmorStandFollower = CookieGadgets.getNMSManager().spawnNMSArmorStandFollower(location.getWorld(), location.getX(), location.getY() + n, location.getZ(), player, n, null, ArmorStandSlot.HEAD, GadgetFlowerGiver.flowers[CookieGadgets.random().nextInt(GadgetFlowerGiver.flowers.length)]);
                    if (!GadgetFlowerGiver.acceptedFlowerPlayers.containsKey(player)) {
                        GadgetFlowerGiver.acceptedFlowerPlayers.put(player, spawnNMSArmorStandFollower);
                    }
                }
            }
            else if (playerCommandPreprocessEvent.getMessage().contains("decline")) {
                final Player player3 = Bukkit.getPlayer(playerCommandPreprocessEvent.getMessage().replace("/flowergiver decline ", ""));
                if (player3 != null && player3.isOnline()) {
                    player3.sendMessage(ChatUtil.format(GadgetFlowerGiver.declinedFlowerMessage).replace("{PLAYER}", player.getName()));
                    if (!GadgetFlowerGiver.declinedFlowerPlayers.contains(player)) {
                        GadgetFlowerGiver.declinedFlowerPlayers.add(player);
                    }
                }
            }
            GadgetFlowerGiver.pendingPlayers.remove(player);
            playerCommandPreprocessEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlayerClick(final PlayerInteractEvent playerInteractEvent) {
        if (GadgetFlowerGiver.acceptedFlowerPlayers.isEmpty()) {
            return;
        }
        if ((playerInteractEvent.getAction() == Action.RIGHT_CLICK_AIR || playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK) && GadgetFlowerGiver.acceptedFlowerPlayers.containsKey(playerInteractEvent.getPlayer())) {
            if (playerInteractEvent.getPlayer().getItemInHand().getType() != Material.AIR) {
                return;
            }
            removeFlower(playerInteractEvent.getPlayer());
            playerInteractEvent.setCancelled(true);
        }
    }
}
