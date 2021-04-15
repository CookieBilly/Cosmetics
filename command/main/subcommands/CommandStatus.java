

package ws.billy.CookieGadgets.command.main.subcommands;

import org.bukkit.OfflinePlayer;
import ws.billy.CookieGadgets.cosmetics.suits.SuitEquipmentType;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandStatus extends SubCommand
{
    public CommandStatus() {
        super("/gmenu status <player>", "Check player's data.", null, "CookieGadgets.commands.status", new String[] { "status", "check" }, true);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length > 3) {
            CommandManager.printMessage((CommandSender)player, this);
            return;
        }
        Player player2 = player;
        if (array.length == 3 && array[2].equalsIgnoreCase("--delete")) {
            player2 = player.getServer().getPlayer(array[1]);
            if (player2 != null) {
                final PlayerManager playerManager = CookieGadgets.getPlayerManager(player2);
                CookieGadgets.getDatabaseManager().getDatabaseUtils().deletePlayerData(player2.getUniqueId(), playerManager.getUID());
                playerManager.resetPlayerData();
                CookieGadgets.getDatabaseManager().getDatabaseUtils().initPlayerStats(playerManager);
                playerManager.initData();
                player.sendMessage(ChatUtil.format("&aSuccessful remove " + player2.getName() + "'s data."));
                return;
            }
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.offlinePlayerCommand((CommandSender)player, array));
        }
        if (array.length == 2) {
            player2 = player.getServer().getPlayer(array[1]);
            if (player2 == null || !player2.isOnline()) {
                player.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
        }
        this.onCommand((CommandSender)player, player2, array);
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        if (array.length > 3) {
            CommandManager.printMessage(commandSender, this);
            return;
        }
        Player player = null;
        if (array.length == 2) {
            player = commandSender.getServer().getPlayer(array[1]);
            if (player == null || !player.isOnline()) {
                commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
        }
        this.onCommand(commandSender, player, array);
    }
    
    private void onCommand(final CommandSender commandSender, final Player player, final String[] array) {
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (playerManager == null) {
            return;
        }
        commandSender.sendMessage(ChatUtil.format("&e---------------&fAbout: CookieGadgets&e---------------"));
        commandSender.sendMessage(ChatUtil.format("&7Player Name: &b" + player.getName()));
        commandSender.sendMessage(ChatUtil.format("&7UUID: &b" + playerManager.getUUID().toString()));
        commandSender.sendMessage(ChatUtil.format("&7UID: &b" + (playerManager.isLoaded() ? Integer.valueOf(playerManager.getUID()) : MessageType.LOADING.getFormatMessage())));
        commandSender.sendMessage(ChatUtil.format("&7Mystery Dust: &b" + (playerManager.isLoaded() ? Integer.valueOf(playerManager.getMysteryDust()) : MessageType.LOADING.getFormatMessage())));
        commandSender.sendMessage(ChatUtil.format("&7Mystery Boxes: &b" + (playerManager.isLoaded() ? Integer.valueOf(playerManager.getMysteryBoxes()) : MessageType.LOADING.getFormatMessage())));
        commandSender.sendMessage(ChatUtil.format("&7Gift Packs: &b" + (playerManager.isLoaded() ? Integer.valueOf(playerManager.getGiftPacks()) : MessageType.LOADING.getFormatMessage())));
        commandSender.sendMessage(ChatUtil.format("&7Gifts sent: &b" + (playerManager.isLoaded() ? Integer.valueOf(playerManager.getGiftSent()) : MessageType.LOADING.getFormatMessage())));
        commandSender.sendMessage(ChatUtil.format("&7Gifts received: &b" + (playerManager.isLoaded() ? Integer.valueOf(playerManager.getGiftReceived()) : MessageType.LOADING.getFormatMessage())));
        String s = String.valueOf((playerManager.getEquippedHat() != null) ? new StringBuilder(String.valueOf(playerManager.getEquippedHat().getDisplayName())).append("&b, ").toString() : "") + ((playerManager.getEquippedAnimatedHat() != null) ? (String.valueOf(playerManager.getEquippedAnimatedHat().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedParticle() != null) ? (String.valueOf(playerManager.getEquippedParticle().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedSuitEquipment().get(EnumArmorType.HELMET) != null) ? (String.valueOf(playerManager.getEquippedSuitEquipment().get(EnumArmorType.HELMET).getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE) != null) ? (String.valueOf(playerManager.getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE).getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS) != null) ? (String.valueOf(playerManager.getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS).getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedSuitEquipment().get(EnumArmorType.BOOTS) != null) ? (String.valueOf(playerManager.getEquippedSuitEquipment().get(EnumArmorType.BOOTS).getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedGadget() != null) ? (String.valueOf(playerManager.getEquippedGadget().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedPet() != null) ? (String.valueOf(playerManager.getEquippedPet().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedMiniature() != null) ? (String.valueOf(playerManager.getEquippedMiniature().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedMorph() != null) ? (String.valueOf(playerManager.getEquippedMorph().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedBanner() != null) ? (String.valueOf(playerManager.getEquippedBanner().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedEmote() != null) ? (String.valueOf(playerManager.getEquippedEmote().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedCloak() != null) ? (String.valueOf(playerManager.getEquippedCloak().getDisplayName()) + "&b, ") : "");
        if (s.endsWith("&b, ")) {
            s = s.substring(0, s.length() - 4);
        }
        if (s.equals("")) {
            s = MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage();
        }
        commandSender.sendMessage(ChatUtil.format("&7Equipped Cosmetics: &b" + (playerManager.isLoaded() ? s : MessageType.LOADING.getFormatMessage())));
        commandSender.sendMessage(ChatUtil.format("&7Unlocked Cosmetics Items: &b" + (playerManager.isLoaded() ? Integer.valueOf(playerManager.getUnlockedTotalCosmeticItems()) : MessageType.LOADING.getFormatMessage())));
    }
    
    private boolean offlinePlayerCommand(final CommandSender commandSender, final String[] array) {
        final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(array[1]);
        if (!CookieGadgets.getDatabaseManager().getDatabaseUtils().isExist(offlinePlayer)) {
            commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
            return false;
        }
        CookieGadgets.getDatabaseManager().getDatabaseUtils().deletePlayerData(offlinePlayer.getUniqueId(), CookieGadgets.getDatabaseManager().getDatabaseUtils().getUID(offlinePlayer.getUniqueId()));
        commandSender.sendMessage(ChatUtil.format("&aSuccessful remove " + offlinePlayer.getName() + "'s data."));
        return true;
    }
}
