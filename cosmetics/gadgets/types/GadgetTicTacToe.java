

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.StringUtils;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.PlayerUtils;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.EnumItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class GadgetTicTacToe extends Gadget
{
    public static ArrayList<Player> pendingPlayers;
    public static ArrayList<Player> acceptedPlayers;
    public static ArrayList<Player> declinedPlayers;
    private boolean activated;
    private Player targetPlayer;
    private boolean challengerTurn;
    private ItemStack[] symbols;
    private static int[] slots;
    private static ItemStack c;
    private static ItemStack o;
    private static String TicTacToeGUIName;
    private static int[][] winMethods;
    private static String targetAPlayerMessage;
    private static String targetOtherPlayerMessage;
    private static String sendChallengeMessage;
    private static String alreadySentARequestMessage;
    private static String receivedChallengeMessage;
    private static String acceptMessage;
    private static String declineMessage;
    private static String acceptJsonMessage;
    private static String declineJsonMessage;
    public static String noPendingChallengeMessage;
    private static String acceptedChallengeMessage;
    private static String declinedChallengeMessage;
    private static String drawMessage;
    private static String winMessage;
    private static String lossMessage;
    private static String playerLeftMessage;
    private static String opponentLeftMessage;
    
    static {
        GadgetTicTacToe.pendingPlayers = new ArrayList<Player>();
        GadgetTicTacToe.acceptedPlayers = new ArrayList<Player>();
        GadgetTicTacToe.declinedPlayers = new ArrayList<Player>();
        GadgetTicTacToe.slots = new int[] { 3, 4, 5, 12, 13, 14, 21, 22, 23 };
        GadgetTicTacToe.c = EnumItem.TIC_TAC_TOE_CHALLENGER_SYMBOL.getItemStack();
        GadgetTicTacToe.o = EnumItem.TIC_TAC_TOE_OPPONENT_SYMBOL.getItemStack();
        GadgetTicTacToe.TicTacToeGUIName = ChatUtil.format(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.GUI-Name"));
        GadgetTicTacToe.winMethods = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };
        GadgetTicTacToe.targetAPlayerMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Target-A-Player");
        GadgetTicTacToe.targetOtherPlayerMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Target-Other-Player");
        GadgetTicTacToe.sendChallengeMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Challenge");
        GadgetTicTacToe.alreadySentARequestMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Already-Sent-A-Request");
        GadgetTicTacToe.receivedChallengeMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Received-Challenge");
        GadgetTicTacToe.acceptMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Accept");
        GadgetTicTacToe.declineMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Decline");
        GadgetTicTacToe.acceptJsonMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Accept-Json-message");
        GadgetTicTacToe.declineJsonMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Decline-Json-message");
        GadgetTicTacToe.noPendingChallengeMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.No-Pending-Challenge");
        GadgetTicTacToe.acceptedChallengeMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Accepted-Challenge");
        GadgetTicTacToe.declinedChallengeMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Declined-Challenge");
        GadgetTicTacToe.drawMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Draw");
        GadgetTicTacToe.winMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Win");
        GadgetTicTacToe.lossMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Loss");
        GadgetTicTacToe.playerLeftMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Player-Left-Game");
        GadgetTicTacToe.opponentLeftMessage = FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Opponent-Left-Game");
    }
    
    public GadgetTicTacToe(final UUID uuid) {
        super(uuid, GadgetType.TIC_TAC_TOE);
        this.activated = false;
        this.challengerTurn = true;
        this.symbols = new ItemStack[9];
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        final Entity entityPlayerLooking = PlayerUtils.getEntityPlayerLookingAt(this.getPlayer(), 10);
        if (entityPlayerLooking == null) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.targetAPlayerMessage));
            return false;
        }
        if (!(entityPlayerLooking instanceof Player)) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.targetAPlayerMessage));
            return false;
        }
        if (!this.getPlayer().canSee((Player)entityPlayerLooking)) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.targetAPlayerMessage));
            return false;
        }
        if (this.targetPlayer != null && entityPlayerLooking == this.targetPlayer) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.alreadySentARequestMessage).replace("{PLAYER}", this.targetPlayer.getName()));
            return false;
        }
        if (GadgetTicTacToe.acceptedPlayers.contains(entityPlayerLooking)) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.targetOtherPlayerMessage));
            return false;
        }
        if (GadgetTicTacToe.pendingPlayers.contains(entityPlayerLooking)) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.targetOtherPlayerMessage));
            return false;
        }
        if (this.targetPlayer != null && GadgetTicTacToe.pendingPlayers.contains(this.targetPlayer)) {
            GadgetTicTacToe.pendingPlayers.remove(this.targetPlayer);
        }
        this.targetPlayer = (Player)entityPlayerLooking;
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        if (this.targetPlayer != null && this.targetPlayer.isOnline()) {
            this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.sendChallengeMessage).replace("{PLAYER}", this.targetPlayer.getName()));
            this.targetPlayer.sendMessage(ChatUtil.format(GadgetTicTacToe.receivedChallengeMessage).replace("{PLAYER}", this.getPlayer().getName()));
            CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(GadgetTicTacToe.acceptMessage)).showText(ChatUtil.format(GadgetTicTacToe.acceptJsonMessage)).runCommand("/tictactoe accept {PLAYER}".replace("{PLAYER}", this.getPlayer().getName())).then(" ").then(ChatUtil.format(GadgetTicTacToe.declineMessage)).showText(ChatUtil.format(GadgetTicTacToe.declineJsonMessage)).runCommand("/tictactoe decline {PLAYER}".replace("{PLAYER}", this.getPlayer().getName())).send(this.targetPlayer);
            if (!GadgetTicTacToe.pendingPlayers.contains(this.targetPlayer)) {
                GadgetTicTacToe.pendingPlayers.add(this.targetPlayer);
            }
            return;
        }
        this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.targetAPlayerMessage));
    }
    
    @Override
    public void onUpdate() {
        if (GadgetTicTacToe.declinedPlayers.contains(this.getPlayer())) {
            this.clearAll();
        }
        if (this.activated) {
            if (this.getPlayer() == null || (this.getPlayer() != null && !this.getPlayer().isOnline())) {
                this.targetPlayer.sendMessage(ChatUtil.format(GadgetTicTacToe.opponentLeftMessage));
                this.clearAll();
                return;
            }
            if (this.targetPlayer == null || (this.targetPlayer != null && !this.targetPlayer.isOnline())) {
                this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.opponentLeftMessage));
                this.clearAll();
                return;
            }
            boolean b = true;
            boolean b2 = true;
            int[][] winMethods;
            for (int length = (winMethods = GadgetTicTacToe.winMethods).length, i = 0; i < length; ++i) {
                final int[] array = winMethods[i];
                b = true;
                b2 = true;
                int[] array2;
                for (int length2 = (array2 = array).length, j = 0; j < length2; ++j) {
                    final ItemStack itemStack = this.symbols[array2[j]];
                    if (itemStack == null) {
                        b = false;
                        b2 = false;
                        break;
                    }
                    if (itemStack != EnumItem.TIC_TAC_TOE_CHALLENGER_SYMBOL.getItemStack()) {
                        b = false;
                    }
                    if (itemStack != EnumItem.TIC_TAC_TOE_OPPONENT_SYMBOL.getItemStack()) {
                        b2 = false;
                    }
                }
                if (b) {
                    break;
                }
                if (b2) {
                    break;
                }
            }
            if (!b && !b2) {
                boolean b3 = true;
                for (int k = 0; k <= 8; ++k) {
                    if (this.symbols[k] == null) {
                        b3 = false;
                        break;
                    }
                }
                if (b3) {
                    this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.drawMessage.replace("{PLAYER}", this.targetPlayer.getName())));
                    if (this.targetPlayer != null) {
                        this.targetPlayer.sendMessage(ChatUtil.format(GadgetTicTacToe.drawMessage.replace("{PLAYER}", this.getPlayer().getName())));
                    }
                    this.clearAll();
                    return;
                }
            }
            if (b) {
                this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.winMessage.replace("{PLAYER}", this.targetPlayer.getName())));
                this.targetPlayer.sendMessage(ChatUtil.format(GadgetTicTacToe.lossMessage.replace("{PLAYER}", this.getPlayer().getName())));
                SoundEffect.ENTITY_PLAYER_LEVELUP.playSound(this.getPlayer(), 1.0f, 1.0f);
                SoundEffect.ENTITY_ENDERMAN_DEATH.playSound(this.targetPlayer, 1.0f, 1.0f);
                this.clearAll();
                return;
            }
            if (b2) {
                this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.lossMessage.replace("{PLAYER}", this.targetPlayer.getName())));
                this.targetPlayer.sendMessage(ChatUtil.format(GadgetTicTacToe.winMessage.replace("{PLAYER}", this.getPlayer().getName())));
                SoundEffect.ENTITY_PLAYER_LEVELUP.playSound(this.targetPlayer, 1.0f, 1.0f);
                SoundEffect.ENTITY_ENDERMAN_DEATH.playSound(this.getPlayer(), 1.0f, 1.0f);
                this.clearAll();
                return;
            }
            if (this.getPlayer().getOpenInventory() == null || (this.getPlayer().getOpenInventory() != null && !this.getPlayer().getOpenInventory().getTitle().startsWith(ChatUtil.format(GadgetTicTacToe.TicTacToeGUIName.replace("{OPPONENT}", this.targetPlayer.getName()))))) {
                this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.playerLeftMessage));
                this.targetPlayer.sendMessage(ChatUtil.format(GadgetTicTacToe.opponentLeftMessage));
                this.clearAll();
                return;
            }
            if (this.targetPlayer.getOpenInventory() == null || (this.targetPlayer.getOpenInventory() != null && !this.targetPlayer.getOpenInventory().getTitle().startsWith(ChatUtil.format(GadgetTicTacToe.TicTacToeGUIName.replace("{OPPONENT}", this.getPlayer().getName()))))) {
                this.targetPlayer.sendMessage(ChatUtil.format(GadgetTicTacToe.playerLeftMessage));
                this.getPlayer().sendMessage(ChatUtil.format(GadgetTicTacToe.opponentLeftMessage));
                this.clearAll();
            }
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        if (!CookieGadgets.getInstance().isEnabled()) {
            return;
        }
        int i = 0;
        Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
            if (this.targetPlayer != null) {
                if (GadgetTicTacToe.pendingPlayers.contains(this.targetPlayer)) {
                    GadgetTicTacToe.pendingPlayers.remove(this.targetPlayer);
                }
                if (GadgetTicTacToe.acceptedPlayers.contains(this.targetPlayer)) {
                    if (this.targetPlayer.getOpenInventory() != null && this.activated) {
                        this.targetPlayer.closeInventory();
                    }
                    GadgetTicTacToe.acceptedPlayers.remove(this.targetPlayer);
                }
            }
            if (this.getPlayer().getOpenInventory() != null && this.activated) {
                this.getPlayer().closeInventory();
            }
            if (GadgetTicTacToe.acceptedPlayers.contains(this.getPlayer())) {
                GadgetTicTacToe.acceptedPlayers.remove(this.getPlayer());
            }
            if (GadgetTicTacToe.declinedPlayers.contains(this.getPlayer())) {
                GadgetTicTacToe.declinedPlayers.remove(this.getPlayer());
            }
            this.targetPlayer = null;
            this.challengerTurn = true;
            while (i <= 8) {
                this.symbols[i] = null;
                ++i;
            }
            this.activated = false;
        });
    }
    
    public void openTicTacToeMenu(final Player player, final Player player2) {
        if (player != this.getPlayer() && player != this.targetPlayer) {
            return;
        }
        if (player2 != this.getPlayer() && player2 != this.targetPlayer) {
            return;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 27, ChatUtil.format(GadgetTicTacToe.TicTacToeGUIName.replace("{OPPONENT}", player2.getName())));
        int n = 0;
        if (player == this.getPlayer()) {
            if (this.challengerTurn) {
                ItemStack[] symbols;
                for (int length = (symbols = this.symbols).length, i = 0; i < length; ++i) {
                    final ItemStack itemStack = symbols[i];
                    InventoryUtils.inventory(inventory, (itemStack == null) ? EnumItem.TIC_TAC_TOE_PLACE_SYMBOL.getItemStack() : itemStack, GadgetTicTacToe.slots[n++]);
                }
            }
            else {
                ItemStack[] symbols2;
                for (int length2 = (symbols2 = this.symbols).length, j = 0; j < length2; ++j) {
                    final ItemStack itemStack2 = symbols2[j];
                    InventoryUtils.inventory(inventory, (itemStack2 == null) ? EnumItem.TIC_TAC_TOE_NOT_YOUR_TURN.getItemStack() : itemStack2, GadgetTicTacToe.slots[n++]);
                }
            }
        }
        else if (!this.challengerTurn) {
            ItemStack[] symbols3;
            for (int length3 = (symbols3 = this.symbols).length, k = 0; k < length3; ++k) {
                final ItemStack itemStack3 = symbols3[k];
                InventoryUtils.inventory(inventory, (itemStack3 == null) ? EnumItem.TIC_TAC_TOE_PLACE_SYMBOL.getItemStack() : itemStack3, GadgetTicTacToe.slots[n++]);
            }
        }
        else {
            ItemStack[] symbols4;
            for (int length4 = (symbols4 = this.symbols).length, l = 0; l < length4; ++l) {
                final ItemStack itemStack4 = symbols4[l];
                InventoryUtils.inventory(inventory, (itemStack4 == null) ? EnumItem.TIC_TAC_TOE_NOT_YOUR_TURN.getItemStack() : itemStack4, GadgetTicTacToe.slots[n++]);
            }
        }
        InventoryUtils.inventory(inventory, StringUtils.addPlaceholder(EnumItem.TIC_TAC_TOE_CHALLENGER.getDisplayName(), "{PLAYER}", (player == this.getPlayer()) ? player.getName() : player2.getName()), EnumItem.TIC_TAC_TOE_CHALLENGER.getMaterial(), StringUtils.addPlaceholder(EnumItem.TIC_TAC_TOE_CHALLENGER.getLore(), "{PLAYER}", (player == this.getPlayer()) ? player.getName() : player2.getName()), (player == this.getPlayer()) ? EnumItem.TIC_TAC_TOE_CHALLENGER.getSlot() : EnumItem.TIC_TAC_TOE_OPPONENT.getSlot());
        InventoryUtils.inventory(inventory, StringUtils.addPlaceholder(EnumItem.TIC_TAC_TOE_OPPONENT.getDisplayName(), "{PLAYER}", (player == this.getPlayer()) ? player2.getName() : player.getName()), EnumItem.TIC_TAC_TOE_OPPONENT.getMaterial(), StringUtils.addPlaceholder(EnumItem.TIC_TAC_TOE_OPPONENT.getLore(), "{PLAYER}", (player == this.getPlayer()) ? player2.getName() : player.getName()), (player == this.getPlayer()) ? EnumItem.TIC_TAC_TOE_OPPONENT.getSlot() : EnumItem.TIC_TAC_TOE_CHALLENGER.getSlot());
        player.openInventory(inventory);
    }
    
    private void placeSymbol(final Player player, final int n) {
        if (player != this.getPlayer() && player != this.targetPlayer) {
            return;
        }
        if (this.targetPlayer != null && this.targetPlayer.isOnline()) {
            if (player == this.getPlayer()) {
                if (this.challengerTurn) {
                    this.symbols[n] = GadgetTicTacToe.c;
                    this.challengerTurn = false;
                }
            }
            else if (player == this.targetPlayer && !this.challengerTurn) {
                this.symbols[n] = GadgetTicTacToe.o;
                this.challengerTurn = true;
            }
            Player player2 = this.targetPlayer;
            if (player == this.getPlayer()) {
                player2 = this.targetPlayer;
            }
            else if (player == this.targetPlayer) {
                player2 = this.getPlayer();
            }
            this.openTicTacToeMenu(player, player2);
            this.openTicTacToeMenu(player2, player);
        }
        else {
            this.clearAll();
        }
    }
    
    @EventHandler
    private void onInvClickTicTacToe(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (this.targetPlayer == null || (player != this.getPlayer() && player != this.targetPlayer) || !inventoryClickEvent.getView().getTitle().startsWith(GadgetTicTacToe.TicTacToeGUIName.replace("{OPPONENT}", ""))) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 27 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(player, true)) {
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (inventoryClickEvent.getCurrentItem() == null || inventoryClickEvent.getCurrentItem().getItemMeta() == null || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName() == null) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (inventoryClickEvent.getClick() == ClickType.SHIFT_LEFT || inventoryClickEvent.getClick() == ClickType.SHIFT_RIGHT || inventoryClickEvent.getClick() == ClickType.NUMBER_KEY || inventoryClickEvent.getClick() == ClickType.UNKNOWN) {
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        int n = 0;
        int[] slots;
        for (int length = (slots = GadgetTicTacToe.slots).length, i = 0; i < length; ++i) {
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.TIC_TAC_TOE_PLACE_SYMBOL.getItemStack(), slots[i])) {
                this.placeSymbol(player, n);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            ++n;
        }
        inventoryClickEvent.setCancelled(true);
    }
    
    @EventHandler
    public void onPlayerExecuteTicTacToeCommand(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        final Player player = playerCommandPreprocessEvent.getPlayer();
        if (player == this.targetPlayer && playerCommandPreprocessEvent.getMessage().startsWith("/tictactoe") && GadgetTicTacToe.pendingPlayers.contains(player)) {
            if (playerCommandPreprocessEvent.getMessage().contains("accept")) {
                final Player player2 = Bukkit.getPlayer(playerCommandPreprocessEvent.getMessage().replace("/tictactoe accept ", ""));
                if (player2 != null && player2.isOnline()) {
                    player2.sendMessage(ChatUtil.format(GadgetTicTacToe.acceptedChallengeMessage).replace("{PLAYER}", player.getName()));
                    if (!GadgetTicTacToe.acceptedPlayers.contains(player2)) {
                        GadgetTicTacToe.acceptedPlayers.add(player2);
                    }
                    if (!GadgetTicTacToe.acceptedPlayers.contains(player)) {
                        GadgetTicTacToe.acceptedPlayers.add(player);
                    }
                    this.openTicTacToeMenu(player, player2);
                    this.openTicTacToeMenu(player2, player);
                    this.activated = true;
                }
            }
            else if (playerCommandPreprocessEvent.getMessage().contains("decline")) {
                final Player player3 = Bukkit.getPlayer(playerCommandPreprocessEvent.getMessage().replace("/tictactoe decline ", ""));
                if (player3 != null && player3.isOnline()) {
                    player3.sendMessage(ChatUtil.format(GadgetTicTacToe.declinedChallengeMessage).replace("{PLAYER}", player.getName()));
                    if (!GadgetTicTacToe.declinedPlayers.contains(player3)) {
                        GadgetTicTacToe.declinedPlayers.add(player3);
                    }
                }
            }
            GadgetTicTacToe.pendingPlayers.remove(player);
            playerCommandPreprocessEvent.setCancelled(true);
        }
    }
}
