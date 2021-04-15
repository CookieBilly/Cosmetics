

package ws.billy.CookieGadgets.command.mysteryboxes.subcommand;

import java.util.Iterator;
import org.bukkit.block.BlockFace;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerTeleportEvent;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVaultAPI;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVaultManager;
import ws.billy.CookieGadgets.utils.ChatUtil;
import java.util.regex.Pattern;
import org.bukkit.block.Block;
import java.util.Set;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.Material;
import ws.billy.CookieGadgets.utils.BlockUtil;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.mysteryboxes.CommandManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.mysteryboxes.SubCommand;

public class CommandMode extends SubCommand
{
    public CommandMode() {
        super("/gmysteryboxes mode", "Shows the commands to manipulate mystery vault.", null, "CookieGadgets.mysteryboxes.mode", new String[] { "mode", "setup", "edit" }, false);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length < 2) {
            printMessages(player);
            return;
        }
        if (array[1].equalsIgnoreCase("add-vault") || array[1].equalsIgnoreCase("create")) {
            if (array.length != 3) {
                CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode add-vault <vaultName>", "Create a mystery vault.", null, true);
                return;
            }
            final Location location = BlockUtil.getTargetBlock(player, 5).getLocation();
            if (location.getBlock().getType() == Material.AIR) {
                player.sendMessage(MessageType.TARGET_A_BLOCK.getFormatMessage());
                return;
            }
            final List lastTwoTargetBlocks = player.getLastTwoTargetBlocks((Set)null, 5);
            final BlockFace blockFace = getBlockFace(lastTwoTargetBlocks.get(0), lastTwoTargetBlocks.get(1));
            final String replacement = array[2];
            if (Pattern.compile("[^A-Za-z0-9 && [^_]]").matcher(replacement).find()) {
                player.sendMessage(ChatUtil.format(MessageType.DOES_NOT_SUPPORT_CHARACTERS.getFormatMessage()));
                return;
            }
            for (final MysteryVault mysteryVault : MysteryVaultManager.vaults()) {
                if (mysteryVault.getName().equalsIgnoreCase(replacement)) {
                    player.sendMessage(MessageType.MYSTERY_VAULT_IS_EXISTS.getFormatMessage());
                    return;
                }
                if (mysteryVault.getLocation() == null) {
                    continue;
                }
                if (mysteryVault.getLocation().equals((Object)location)) {
                    player.sendMessage(MessageType.MYSTERY_VAULT_SAME_LOCATION.getFormatMessage());
                    return;
                }
            }
            final MysteryVault mysteryVault2 = MysteryVaultAPI.mysteryVault(replacement, blockFace, location);
            mysteryVault2.saveToDatabase();
            mysteryVault2.createHolograms();
            player.sendMessage(MessageType.ADDED_A_MYSTERY_VAULT.getFormatMessage().replace("{NAME}", replacement));
        }
        else if (array[1].equalsIgnoreCase("info") || array[1].equalsIgnoreCase("infomation")) {
            if (array.length != 3) {
                CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode info <vaultName>", "Show the info of the mystery vault.", null, true);
                return;
            }
            final String replacement2 = array[2];
            if (MysteryVaultManager.vaults().size() == 0) {
                player.sendMessage(MessageType.DO_NOT_HAVE_ANY_MYSTERY_VAULT.getFormatMessage());
                return;
            }
            final MysteryVault vaultByName = MysteryVaultManager.getVaultByName(replacement2);
            if (vaultByName == null) {
                player.sendMessage(MessageType.MYSTERY_VAULT_NOT_FOUND_WITH_NAME.getFormatMessage().replace("{NAME}", replacement2));
                return;
            }
            final Location location2 = vaultByName.getLocation();
            player.sendMessage(ChatUtil.format("&e---------------&fMystery Vault Info&e---------------"));
            player.sendMessage(ChatUtil.format("&7- &r&l" + vaultByName.getName() + " &7at world: \"" + location2.getWorld().getName() + "\" x: " + location2.getX() + ", y: " + location2.getY() + ", z: " + location2.getZ() + " (BlockFace: " + vaultByName.getBlockFace().toString() + ")"));
        }
        else if (array[1].equalsIgnoreCase("list")) {
            if (array.length != 2) {
                CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode list", "List all the mystery vault.", null, true);
                return;
            }
            if (MysteryVaultManager.vaults().size() == 0) {
                player.sendMessage(MessageType.DO_NOT_HAVE_ANY_MYSTERY_VAULT.getFormatMessage());
                return;
            }
            player.sendMessage(ChatUtil.format("&e---------------&fMystery Vault List&e---------------"));
            for (final MysteryVault mysteryVault3 : MysteryVaultManager.vaults()) {
                final Location location3 = mysteryVault3.getLocation();
                if (location3 != null) {
                    if (location3.getWorld() == null) {
                        continue;
                    }
                    player.sendMessage(ChatUtil.format("&7- &r&l" + mysteryVault3.getName() + " &7at world: \"" + location3.getWorld().getName() + "\" x: " + location3.getX() + ", y: " + location3.getY() + ", z: " + location3.getZ() + " (BlockFace: " + mysteryVault3.getBlockFace().toString() + ")"));
                }
            }
        }
        else if (array[1].equalsIgnoreCase("near")) {
            if (array.length != 3) {
                CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode near <radius>", "Get a list of nearby mystery vault.", null, true);
                return;
            }
            if (MysteryVaultManager.vaults().size() == 0) {
                player.sendMessage(MessageType.DO_NOT_HAVE_ANY_MYSTERY_VAULT.getFormatMessage());
                return;
            }
            try {
                Integer.parseInt(array[2]);
            }
            catch (NumberFormatException ex) {
                player.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
                return;
            }
            final int int1 = Integer.parseInt(array[2]);
            if (int1 <= 0) {
                player.sendMessage(MessageType.REQUIRED_POSITIVE_NUMBER.getFormatMessage());
                return;
            }
            final int n = int1 * int1;
            int n2 = 0;
            player.sendMessage(ChatUtil.format("&e---------------&fNearby Mystery Vault&e---------------"));
            for (final MysteryVault mysteryVault4 : MysteryVaultManager.vaults()) {
                final Location location4 = mysteryVault4.getLocation();
                if (location4 == null) {
                    continue;
                }
                if (!location4.getWorld().equals(player.getLocation().getWorld()) || location4.distanceSquared(player.getLocation()) > n) {
                    continue;
                }
                player.sendMessage(ChatUtil.format("&7- &r&l" + mysteryVault4.getName() + "&7 x: " + location4.getX() + ", y: " + location4.getY() + ", z: " + location4.getZ() + " (BlockFace: " + mysteryVault4.getBlockFace().toString() + ")"));
                if (n2 != 0) {
                    continue;
                }
                n2 = 1;
            }
            if (n2 == 0) {
                player.sendMessage(MessageType.NO_MYSTERY_VAULT_NEARBY.getFormatMessage());
            }
        }
        else if (array[1].equalsIgnoreCase("redefine") || array[1].equalsIgnoreCase("relocate")) {
            if (array.length != 3) {
                CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode redefine <vaultName>", "Redefine mystery vault location.", null, true);
                return;
            }
            final Location location5 = BlockUtil.getTargetBlock(player, 5).getLocation();
            if (location5.getBlock().getType() == Material.AIR) {
                player.sendMessage(MessageType.TARGET_A_BLOCK.getFormatMessage());
                return;
            }
            final List lastTwoTargetBlocks2 = player.getLastTwoTargetBlocks((Set)null, 5);
            final BlockFace blockFace2 = getBlockFace(lastTwoTargetBlocks2.get(0), lastTwoTargetBlocks2.get(1));
            final String replacement3 = array[2];
            if (MysteryVaultManager.vaults().size() == 0) {
                player.sendMessage(MessageType.DO_NOT_HAVE_ANY_MYSTERY_VAULT.getFormatMessage());
                return;
            }
            final MysteryVault vaultByName2 = MysteryVaultManager.getVaultByName(replacement3);
            if (vaultByName2 == null) {
                player.sendMessage(MessageType.MYSTERY_VAULT_NOT_FOUND_WITH_NAME.getFormatMessage().replace("{NAME}", replacement3));
                return;
            }
            for (final MysteryVault mysteryVault5 : MysteryVaultManager.vaults()) {
                if (mysteryVault5.getLocation() == null) {
                    continue;
                }
                if (!mysteryVault5.getName().equalsIgnoreCase(replacement3) && mysteryVault5.getLocation().equals((Object)location5)) {
                    player.sendMessage(MessageType.MYSTERY_VAULT_SAME_LOCATION.getFormatMessage());
                    return;
                }
            }
            vaultByName2.updateLocation(blockFace2, location5);
            vaultByName2.removeHolograms();
            vaultByName2.removeAvailableMysteryBoxesHologram();
            vaultByName2.createHolograms();
            vaultByName2.createAvailableMysteryBoxesHologram();
            player.sendMessage(MessageType.REDEFINED_A_MYSTERY_VAULT.getFormatMessage().replace("{NAME}", replacement3));
        }
        else if (array[1].equalsIgnoreCase("remove-vault") || array[1].equalsIgnoreCase("remove")) {
            if (array.length == 2) {
                final Location location6 = BlockUtil.getTargetBlock(player, 5).getLocation();
                if (MysteryVaultManager.vaults().size() == 0) {
                    player.sendMessage(MessageType.DO_NOT_HAVE_ANY_MYSTERY_VAULT.getFormatMessage());
                    return;
                }
                final MysteryVault vaultByLocation = MysteryVaultManager.getVaultByLocation(location6);
                if (vaultByLocation != null) {
                    vaultByLocation.delete();
                    player.sendMessage(MessageType.REMOVED_MYSTERY_VAULT.getFormatMessage().replace("{NAME}", vaultByLocation.getName()));
                    return;
                }
                player.sendMessage(MessageType.MYSTERY_VAULT_NOT_FOUND.getFormatMessage());
            }
            else if (array.length == 3) {
                final String s = array[2];
                if (MysteryVaultManager.vaults().size() == 0) {
                    player.sendMessage(MessageType.DO_NOT_HAVE_ANY_MYSTERY_VAULT.getFormatMessage());
                    return;
                }
                if (s.startsWith("r:")) {
                    try {
                        Integer.parseInt(s.replace("r:", ""));
                    }
                    catch (NumberFormatException ex2) {
                        player.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
                        return;
                    }
                    final int intValue = Integer.valueOf(s.replace("r:", ""));
                    if (intValue <= 0) {
                        player.sendMessage(MessageType.REQUIRED_POSITIVE_NUMBER.getFormatMessage());
                        return;
                    }
                    final int n3 = intValue * intValue;
                    int n4 = 0;
                    for (final MysteryVault mysteryVault6 : MysteryVaultManager.vaults()) {
                        final Location location7 = mysteryVault6.getLocation();
                        if (location7 != null) {
                            if (location7.getWorld() == null) {
                                continue;
                            }
                            if (!location7.getWorld().equals(player.getLocation().getWorld()) || location7.distanceSquared(player.getLocation()) > n3 || mysteryVault6 == null) {
                                continue;
                            }
                            mysteryVault6.delete();
                            player.sendMessage(MessageType.REMOVED_MYSTERY_VAULT.getFormatMessage().replace("{NAME}", mysteryVault6.getName()));
                            if (n4 == 0) {
                                n4 = 1;
                                break;
                            }
                            break;
                        }
                    }
                    if (n4 == 0) {
                        player.sendMessage(MessageType.NO_MYSTERY_VAULT_NEARBY.getFormatMessage());
                    }
                }
                else {
                    final MysteryVault vaultByName3 = MysteryVaultManager.getVaultByName(s);
                    if (vaultByName3 == null) {
                        player.sendMessage(MessageType.MYSTERY_VAULT_NOT_FOUND_WITH_NAME.getFormatMessage().replace("{NAME}", s));
                        return;
                    }
                    vaultByName3.delete();
                    player.sendMessage(MessageType.REMOVED_MYSTERY_VAULT.getFormatMessage().replace("{NAME}", s));
                }
            }
            else {
                CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode remove-vault [vaultName|r:{radius}]", "Remove a mystery vault.", "Remove a mystery vault.\n\n&7End with the command 'r:{radius}', will remove\n&7mystery vault in given radius.", true);
            }
        }
        else if (array[1].equalsIgnoreCase("tp") || array[1].equalsIgnoreCase("teleport")) {
            if (array.length != 3) {
                CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode teleport <vaultName>", "Teleport you to given mystery vault.", null, true);
                return;
            }
            final String replacement4 = array[2];
            if (MysteryVaultManager.vaults().size() == 0) {
                player.sendMessage(MessageType.DO_NOT_HAVE_ANY_MYSTERY_VAULT.getFormatMessage());
                return;
            }
            final MysteryVault vaultByName4 = MysteryVaultManager.getVaultByName(replacement4);
            if (vaultByName4 == null) {
                player.sendMessage(MessageType.MYSTERY_VAULT_NOT_FOUND_WITH_NAME.getFormatMessage().replace("{NAME}", replacement4));
                return;
            }
            player.teleport(vaultByName4.getLocation().clone().add(0.5, 1.2, 0.5), PlayerTeleportEvent.TeleportCause.PLUGIN);
            player.sendMessage(MessageType.TELEPORTED_TO_MYSTERY_VAULT.getFormatMessage().replace("{NAME}", vaultByName4.getName()));
        }
        else {
            printMessages(player);
        }
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        this.notAllowed(commandSender);
    }
    
    private static void printMessages(final Player player) {
        CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode", "Shows the commands to manipulate mystery vault.", null, true);
        CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode add-vault <vaultName>", "Create a mystery vault.", null, false);
        CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode info <vaultName>", "Show the info of the mystery vault.", null, false);
        CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode list", "List all the mystery vault.", null, false);
        CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode near <radius>", "Get a list of nearby mystery vault.", null, false);
        CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode redefine <vaultName>", "Redefine mystery vault location.", null, false);
        CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode remove-vault [vaultName|r:{radius}]", "Remove a mystery vault.", "Remove a mystery vault.\n\n&7End with the command 'r:{radius}', will remove\n&7mystery vault in given radius.", false);
        CommandManager.printMessage((CommandSender)player, "/gmysteryboxes mode teleport <vaultName>", "Teleport you to given mystery vault.", null, false);
    }
    
    private static BlockFace getBlockFace(final Block block, final Block block2) {
        final BlockFace[] values;
        final int length = (values = BlockFace.values()).length;
        int i = 0;
        while (i < length) {
            final BlockFace blockFace = values[i];
            if (block.getRelative(blockFace).getLocation().equals((Object)block2.getLocation())) {
                if (blockFace.getOppositeFace() != BlockFace.EAST && blockFace.getOppositeFace() != BlockFace.SOUTH && blockFace.getOppositeFace() != BlockFace.WEST && blockFace.getOppositeFace() != BlockFace.NORTH) {
                    return BlockFace.EAST;
                }
                return blockFace.getOppositeFace();
            }
            else {
                ++i;
            }
        }
        return null;
    }
}
