

package ws.billy.CookieGadgets.command.main.subcommands;

import org.bukkit.OfflinePlayer;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.main.CommandManager;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.Category;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandNamePet extends SubCommand
{
    public CommandNamePet() {
        super("/gmenu namepet <name>", "Name your pet.", null, "CookieGadgets.commands.namepet", new String[] { "renamepet", "namepet" }, true);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (!Category.PETS.isEnabled()) {
            player.sendMessage(MessageType.PETS_ARE_DISABLED.getFormatMessage());
            return;
        }
        if (CookieGadgets.getPlayerManager(player).getEquippedPet() == null) {
            player.sendMessage(MessageType.NO_PET_SPAWNED.getFormatMessage());
            return;
        }
        String s = "";
        for (int i = 1; i < array.length; ++i) {
            s = String.valueOf(s) + (String.valueOf(array[i]) + " ");
        }
        if (s == "") {
            CommandManager.printMessage((CommandSender)player, new CommandNamePet());
            return;
        }
        if (s.endsWith(" ")) {
            s = ChatUtil.format(s.substring(0, s.length() - 1));
        }
        final int maximumPetNameCharacters = CookieGadgets.getCookieGadgetsData().getMaximumPetNameCharacters();
        if (s.length() > maximumPetNameCharacters) {
            player.sendMessage(MessageType.CHARACTERS_TOO_LONG.getFormatMessage().replace("{CHARACTERS}", String.valueOf(maximumPetNameCharacters)));
            return;
        }
        if (CookieGadgets.getPetData().isPetNameBlacklistEnabled()) {
            final Iterator<String> iterator = CookieGadgets.getPetData().getPetNameBlacklist().iterator();
            while (iterator.hasNext()) {
                if (ChatUtil.stripColor(s).contains(iterator.next())) {
                    player.sendMessage(MessageType.PET_NAME_IN_BLACKLIST.getFormatMessage());
                    return;
                }
            }
        }
        final PetType equippedPet = CookieGadgets.getPlayerManager(player).getEquippedPet();
        CookieGadgets.getPlayerManager(player).setPetName(equippedPet, s);
        player.sendMessage(ChatUtil.format(MessageType.RENAME_PET.getFormatMessage().replace("{NAME}", s).replace("{TYPE}", equippedPet.getDisplayNameStripColor())));
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        if (!Category.PETS.isEnabled()) {
            commandSender.sendMessage(MessageType.PETS_ARE_DISABLED.getFormatMessage());
            return;
        }
        if (array.length < 4) {
            CommandManager.printMessage(commandSender, "/gmenu namepet <player> <type> <name>", "Name player's pet.", null, true);
            return;
        }
        if (commandSender.getServer().getPlayer(array[1]) == null) {
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.offlinePlayerCommand(commandSender, array));
        }
        else {
            final Player player = commandSender.getServer().getPlayer(array[1]);
            final PetType value = PetType.valueOf(array[2].replace("_", " "));
            if (value == null) {
                commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "pet"));
                final StringBuilder sb = new StringBuilder();
                for (int i = 0; i < PetType.enabled().size(); ++i) {
                    sb.append(String.valueOf(PetType.enabled().get(i).toString().replace(" ", "_")) + ((i != PetType.enabled().size() - 1) ? "&f, &c" : ""));
                }
                commandSender.sendMessage(ChatUtil.format("&bPet Types&e: &c" + sb.toString()));
                return;
            }
            if (!value.isEnabled()) {
                commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "pet"));
                return;
            }
            if (CookieGadgets.getPlayerManager(player).getPetData(value) == null) {
                commandSender.sendMessage(ChatUtil.format(MessageType.UNLOCK_PET_BEFORE_RENAME.getFormatMessage().replace("{TYPE}", value.getDisplayNameStripColor()).replace("{PLAYER}", player.getName())));
                return;
            }
            String s = "";
            for (int j = 3; j < array.length; ++j) {
                s = String.valueOf(s) + (String.valueOf(array[j]) + " ");
            }
            if (s == "") {
                CommandManager.printMessage(commandSender, "/gmenu namepet [player] <type> <name>", "Name player's pet.", null, true);
                return;
            }
            if (s.endsWith(" ")) {
                s = ChatUtil.format(s.substring(0, s.length() - 1));
            }
            final int maximumPetNameCharacters = CookieGadgets.getCookieGadgetsData().getMaximumPetNameCharacters();
            if (s.length() > maximumPetNameCharacters) {
                commandSender.sendMessage(MessageType.CHARACTERS_TOO_LONG.getFormatMessage().replace("{CHARACTERS}", String.valueOf(maximumPetNameCharacters)));
                return;
            }
            if (CookieGadgets.getPetData().isPetNameBlacklistEnabled()) {
                final Iterator<String> iterator = CookieGadgets.getPetData().getPetNameBlacklist().iterator();
                while (iterator.hasNext()) {
                    if (ChatUtil.stripColor(s).contains(iterator.next())) {
                        commandSender.sendMessage(MessageType.PET_NAME_IN_BLACKLIST.getFormatMessage());
                        return;
                    }
                }
            }
            CookieGadgets.getPlayerManager(player).setPetName(value, s);
            commandSender.sendMessage(ChatUtil.format(MessageType.RENAME_PET_FOR_PLAYER.getFormatMessage().replace("{NAME}", s).replace("{TYPE}", value.getDisplayNameStripColor()).replace("{PLAYER}", player.getName())));
        }
    }
    
    private boolean offlinePlayerCommand(final CommandSender commandSender, final String[] array) {
        OfflinePlayerManager offlinePlayerManager = null;
        if (commandSender.getServer().getPlayer(array[1]) == null) {
            final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(array[1]);
            if (!CookieGadgets.getDatabaseManager().getDatabaseUtils().isExist(offlinePlayer)) {
                commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return false;
            }
            offlinePlayerManager = new OfflinePlayerManager(offlinePlayer.getUniqueId());
        }
        final PetType value = PetType.valueOf(array[2].replace("_", " "));
        if (value == null) {
            commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "pet"));
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < PetType.enabled().size(); ++i) {
                sb.append(String.valueOf(PetType.enabled().get(i).toString().replace(" ", "_")) + ((i != PetType.enabled().size() - 1) ? "&f, &c" : ""));
            }
            commandSender.sendMessage(ChatUtil.format("&bPet Types&e: &c" + sb.toString()));
            return false;
        }
        if (!value.isEnabled()) {
            commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "pet"));
            return false;
        }
        if (offlinePlayerManager.getPetData(value) == null) {
            commandSender.sendMessage(ChatUtil.format(MessageType.UNLOCK_PET_BEFORE_RENAME.getFormatMessage().replace("{TYPE}", value.getDisplayNameStripColor()).replace("{PLAYER}", offlinePlayerManager.getName())));
            return false;
        }
        String s = "";
        for (int j = 3; j < array.length; ++j) {
            s = String.valueOf(s) + (String.valueOf(array[j]) + " ");
        }
        if (s == "") {
            CommandManager.printMessage(commandSender, "/gmenu namepet <player> <type> <name>", "Name player's pet.", null, true);
            return false;
        }
        if (s.endsWith(" ")) {
            s = ChatUtil.format(s.substring(0, s.length() - 1));
        }
        final int maximumPetNameCharacters = CookieGadgets.getCookieGadgetsData().getMaximumPetNameCharacters();
        if (s.length() > maximumPetNameCharacters) {
            commandSender.sendMessage(MessageType.CHARACTERS_TOO_LONG.getFormatMessage().replace("{CHARACTERS}", String.valueOf(maximumPetNameCharacters)));
            return false;
        }
        if (CookieGadgets.getPetData().isPetNameBlacklistEnabled()) {
            final Iterator<String> iterator = CookieGadgets.getPetData().getPetNameBlacklist().iterator();
            while (iterator.hasNext()) {
                if (ChatUtil.stripColor(s).contains(iterator.next())) {
                    commandSender.sendMessage(MessageType.PET_NAME_IN_BLACKLIST.getFormatMessage());
                    return false;
                }
            }
        }
        offlinePlayerManager.setPetName(value, s);
        commandSender.sendMessage(ChatUtil.format(MessageType.RENAME_PET_FOR_PLAYER.getFormatMessage().replace("{NAME}", s).replace("{TYPE}", value.getDisplayNameStripColor()).replace("{PLAYER}", offlinePlayerManager.getName())));
        return true;
    }
}
