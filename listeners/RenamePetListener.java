

package ws.billy.CookieGadgets.listeners;

import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import java.util.Iterator;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.command.main.SubCommand;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.main.CommandManager;
import ws.billy.CookieGadgets.command.main.subcommands.CommandNamePet;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.Listener;

public class RenamePetListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerRenamePet(final AsyncPlayerChatEvent asyncPlayerChatEvent) {
        final Player player = asyncPlayerChatEvent.getPlayer();
        if (!WorldUtils.isWorldEnabled(player.getWorld())) {
            return;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (!playerManager.isRenamingPet()) {
            return;
        }
        if (playerManager.getEquippedPet() == null) {
            player.sendMessage(MessageType.NO_PET_SPAWNED.getFormatMessage());
            return;
        }
        if (asyncPlayerChatEvent.getMessage().equalsIgnoreCase("cancel")) {
            player.sendMessage(MessageType.CANCEL_RENAME_PET.getFormatMessage());
            playerManager.setRenamingPet(false);
            asyncPlayerChatEvent.setCancelled(true);
            return;
        }
        if (asyncPlayerChatEvent.getMessage().equals("") || asyncPlayerChatEvent.getMessage().equalsIgnoreCase("cancel")) {
            return;
        }
        String replacement = asyncPlayerChatEvent.getMessage();
        if (replacement == "") {
            CommandManager.printMessage((CommandSender)player, new CommandNamePet());
            asyncPlayerChatEvent.setCancelled(true);
            return;
        }
        if (replacement.endsWith(" ")) {
            replacement = ChatUtil.format(replacement.substring(0, replacement.length() - 1));
        }
        final int maximumPetNameCharacters = CookieGadgets.getCookieGadgetsData().getMaximumPetNameCharacters();
        if (replacement.length() > maximumPetNameCharacters) {
            player.sendMessage(MessageType.CHARACTERS_TOO_LONG.getFormatMessage().replace("{CHARACTERS}", String.valueOf(maximumPetNameCharacters)));
            asyncPlayerChatEvent.setCancelled(true);
            return;
        }
        if (CookieGadgets.getPetData().isPetNameBlacklistEnabled()) {
            final Iterator<String> iterator = CookieGadgets.getPetData().getPetNameBlacklist().iterator();
            while (iterator.hasNext()) {
                if (ChatUtil.stripColor(replacement).contains(iterator.next())) {
                    player.sendMessage(MessageType.PET_NAME_IN_BLACKLIST.getFormatMessage());
                    asyncPlayerChatEvent.setCancelled(true);
                    return;
                }
            }
        }
        final PetType equippedPet = playerManager.getEquippedPet();
        Bukkit.getServer().getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> playerManager.setPetName(equippedPet, replacement));
        player.sendMessage(ChatUtil.format(MessageType.RENAME_PET.getFormatMessage().replace("{NAME}", replacement).replace("{TYPE}", equippedPet.getDisplayNameStripColor())));
        playerManager.setRenamingPet(false);
        asyncPlayerChatEvent.setCancelled(true);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRenamePet(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        if (!CookieGadgets.getPetData().isPetNameCommandEnabled() || !WorldUtils.isWorldEnabled(playerCommandPreprocessEvent.getPlayer().getWorld())) {
            return;
        }
        final String lowerCase = playerCommandPreprocessEvent.getMessage().replace("/", "").toLowerCase();
        if (!lowerCase.startsWith("pet") || !lowerCase.split(" ")[0].equals("pet")) {
            return;
        }
        final Player player = playerCommandPreprocessEvent.getPlayer();
        if (!lowerCase.startsWith("pet name ")) {
            CommandManager.printMessage((CommandSender)player, "/pet name <name>", CookieGadgets.getPetData().getPetNameCommandDescription(), null, false);
            playerCommandPreprocessEvent.setCancelled(true);
            return;
        }
        if (PermissionUtils.noPermission(player, EnumPermission.RENAME_PET.getPermission(), EnumPermission.ALL_COMMANDS.getPermission(), true)) {
            playerCommandPreprocessEvent.setCancelled(true);
            return;
        }
        if (!Category.PETS.isEnabled()) {
            player.sendMessage(MessageType.PETS_ARE_DISABLED.getFormatMessage());
            playerCommandPreprocessEvent.setCancelled(true);
            return;
        }
        if (CookieGadgets.getPlayerManager(player).getEquippedPet() == null) {
            player.sendMessage(MessageType.NO_PET_SPAWNED.getFormatMessage());
            playerCommandPreprocessEvent.setCancelled(true);
            return;
        }
        String replacement = playerCommandPreprocessEvent.getMessage().replace("/pet name ", "");
        if (replacement == "") {
            CommandManager.printMessage((CommandSender)player, "/pet name <name>", CookieGadgets.getPetData().getPetNameCommandDescription(), null, false);
            playerCommandPreprocessEvent.setCancelled(true);
            return;
        }
        if (replacement.endsWith(" ")) {
            replacement = ChatUtil.format(replacement.substring(0, replacement.length() - 1));
        }
        final int maximumPetNameCharacters = CookieGadgets.getCookieGadgetsData().getMaximumPetNameCharacters();
        if (replacement.length() > maximumPetNameCharacters) {
            player.sendMessage(MessageType.CHARACTERS_TOO_LONG.getFormatMessage().replace("{CHARACTERS}", String.valueOf(maximumPetNameCharacters)));
            playerCommandPreprocessEvent.setCancelled(true);
            return;
        }
        if (CookieGadgets.getPetData().isPetNameBlacklistEnabled()) {
            final Iterator<String> iterator = CookieGadgets.getPetData().getPetNameBlacklist().iterator();
            while (iterator.hasNext()) {
                if (ChatUtil.stripColor(replacement).contains(iterator.next())) {
                    player.sendMessage(MessageType.PET_NAME_IN_BLACKLIST.getFormatMessage());
                    playerCommandPreprocessEvent.setCancelled(true);
                    return;
                }
            }
        }
        final PetType equippedPet = CookieGadgets.getPlayerManager(player).getEquippedPet();
        CookieGadgets.getPlayerManager(player).setPetName(equippedPet, replacement);
        player.sendMessage(ChatUtil.format(MessageType.RENAME_PET.getFormatMessage().replace("{NAME}", replacement).replace("{TYPE}", equippedPet.getDisplayNameStripColor())));
        playerCommandPreprocessEvent.setCancelled(true);
    }
}
