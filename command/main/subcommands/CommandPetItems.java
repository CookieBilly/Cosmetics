

package ws.billy.CookieGadgets.command.main.subcommands;

import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandPetItems extends SubCommand
{
    public CommandPetItems() {
        super("/gmenu petitems", "Manage player's pet items.", null, "CookieGadgets.commands.petitems", new String[] { "petitems", "petitem", "pi" }, true);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        this.onCommand((CommandSender)player, array);
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        this.onCommand(commandSender, array);
    }
    
    private void onCommand(final CommandSender commandSender, final String[] array) {
        if (array.length < 2) {
            printMessages(commandSender);
            return;
        }
        if (array[1].equalsIgnoreCase("check")) {
            if (array.length < 3) {
                CommandManager.printMessage(commandSender, "/gmenu petitems check <player>", "Shows a list of player's pet items.", null, true);
                return;
            }
            final Player player = commandSender.getServer().getPlayer(array[2]);
            if (player == null || !player.isOnline()) {
                commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
            if (playerManager == null) {
                commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
            commandSender.sendMessage(ChatUtil.format("&e---------------&b" + player.getName() + "'s Pet Items&e---------------"));
            PetItems[] values;
            for (int length = (values = PetItems.values()).length, i = 0; i < length; ++i) {
                final PetItems petItems = values[i];
                final int petItem = playerManager.getPetItem(petItems);
                if (petItem != 0) {
                    commandSender.sendMessage(ChatUtil.format(String.valueOf(petItems.getDisplayName()) + "&b: &e" + petItem));
                }
            }
        }
        else {
            if (array.length < 5) {
                printMessages(commandSender);
                return;
            }
            if (!array[1].equalsIgnoreCase("add") && !array[1].equalsIgnoreCase("remove") && !array[1].equalsIgnoreCase("set")) {
                printMessages(commandSender);
                return;
            }
            final PetItems byName = PetItems.getByName(array[2].replace("_", " "));
            if (byName == null) {
                commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "item"));
                final StringBuilder sb = new StringBuilder();
                PetItems[] values2;
                for (int length2 = (values2 = PetItems.values()).length, j = 0; j < length2; ++j) {
                    sb.append(String.valueOf(values2[j].getSQLIndex().toString()) + "&f, &c");
                }
                sb.delete(sb.length() - 6, sb.length());
                commandSender.sendMessage(ChatUtil.format("&bPet Items&e: &c" + sb.toString()));
                return;
            }
            final Player player2 = commandSender.getServer().getPlayer(array[3]);
            if (player2 == null || !player2.isOnline()) {
                commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
            final PlayerManager playerManager2 = CookieGadgets.getPlayerManager(player2);
            if (playerManager2 == null) {
                commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
            try {
                Integer.parseInt(array[4]);
            }
            catch (NumberFormatException ex) {
                commandSender.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
                return;
            }
            final int int1 = Integer.parseInt(array[4]);
            if (int1 <= 0) {
                commandSender.sendMessage(MessageType.REQUIRED_POSITIVE_NUMBER.getFormatMessage());
                return;
            }
            if (array[1].equalsIgnoreCase("add")) {
                playerManager2.addPetItems(byName, int1);
                commandSender.sendMessage(MessageType.PLAYER_ADDED_PET_ITEM.getFormatMessage().replace("{AMOUNT}", String.valueOf(int1)).replace("{PET_ITEM}", String.valueOf(byName.getDisplayNameStripColor())).replace("{PLAYER}", playerManager2.getPlayer().getName()));
                playerManager2.getPlayer().sendMessage(MessageType.PLAYER_RECEIVED_PET_ITEM.getFormatMessage().replace("{AMOUNT}", String.valueOf(int1)).replace("{PET_ITEM}", String.valueOf(byName.getDisplayNameStripColor())).replace("{PLAYER}", commandSender.getName()));
                return;
            }
            if (array[1].equalsIgnoreCase("remove")) {
                playerManager2.removePetItems(byName, int1);
                commandSender.sendMessage(MessageType.PLAYER_REMOVED_PET_ITEM.getFormatMessage().replace("{AMOUNT}", String.valueOf(int1)).replace("{PET_ITEM}", String.valueOf(byName.getDisplayNameStripColor())).replace("{PLAYER}", playerManager2.getPlayer().getName()));
                playerManager2.getPlayer().sendMessage(MessageType.REMOVED_PET_ITEM_FROM_PLAYER.getFormatMessage().replace("{AMOUNT}", String.valueOf(int1)).replace("{PET_ITEM}", String.valueOf(byName.getDisplayNameStripColor())));
                return;
            }
            if (array[1].equalsIgnoreCase("set")) {
                playerManager2.setPetItems(byName, int1);
                commandSender.sendMessage(MessageType.PLAYER_SET_PET_ITEM.getFormatMessage().replace("{AMOUNT}", String.valueOf(int1)).replace("{PET_ITEM}", String.valueOf(byName.getDisplayNameStripColor())).replace("{PLAYER}", playerManager2.getPlayer().getName()));
                playerManager2.getPlayer().sendMessage(MessageType.SET_PLAYER_PET_ITEM.getFormatMessage().replace("{AMOUNT}", String.valueOf(int1)).replace("{PET_ITEM}", String.valueOf(byName.getDisplayNameStripColor())));
                return;
            }
            printMessages(commandSender);
        }
    }
    
    private static void printMessages(final CommandSender commandSender) {
        CommandManager.printMessage(commandSender, "/gmenu petitems", "Manage player's pet items.", null, true);
        CommandManager.printMessage(commandSender, "/gmenu petitems add <item> <player> <amount>", "Add the amount of pet items.", null, false);
        CommandManager.printMessage(commandSender, "/gmenu petitems check <player>", "Shows a list of player's pet items.", null, false);
        CommandManager.printMessage(commandSender, "/gmenu petitems remove <item> <player> <amount>", "Remove certain pet items from a player.", null, false);
        CommandManager.printMessage(commandSender, "/gmenu petitems set <item> <player> <amount>", "Set the amount of pet items.", null, false);
    }
}
