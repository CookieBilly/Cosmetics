

package ws.billy.CookieGadgets.command.main.subcommands;

import java.util.Iterator;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandReset extends SubCommand
{
    public CommandReset() {
        super("/gmenu reset <all|cosmetic> <all|player>", "Reset active cosmetics.", null, "CookieGadgets.commands.reset", new String[] { "reset", "remove", "unequip" }, true);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length < 2 || array.length > 3) {
            CommandManager.printMessage((CommandSender)player, this);
            return;
        }
        Player player2 = player;
        if (array.length == 3) {
            player2 = player.getServer().getPlayer(array[2]);
            if (array[2].equalsIgnoreCase("all")) {
                player2 = null;
            }
            else if (player2 == null || !player2.isOnline()) {
                player.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
        }
        if (player2 != null) {
            this.resetCosmetic((CommandSender)player, player2, array[1]);
        }
        else {
            this.resetCosmeticForAllPlayers((CommandSender)player, array[1]);
        }
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        if (array.length != 3) {
            CommandManager.printMessage(commandSender, this);
            commandSender.sendMessage(ChatUtil.format("&bCosmetics&e: &c&lAll, Hat, Animated_Hat, Particle, Suit, Gadget, Pet, Miniature, Morph, Banner, Emote, Cloak"));
            return;
        }
        Player player = commandSender.getServer().getPlayer(array[2]);
        if (array[2].equalsIgnoreCase("all")) {
            player = null;
        }
        else if (player == null || !player.isOnline()) {
            commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
            return;
        }
        if (player != null) {
            this.resetCosmetic(commandSender, player, array[1]);
        }
        else {
            this.resetCosmeticForAllPlayers(commandSender, array[1]);
        }
    }
    
    private void resetCosmetic(final CommandSender commandSender, final Player player, final String s) {
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (s.equalsIgnoreCase("all")) {
            player.sendMessage(MessageType.RESET_COSMETICS.getFormatMessage());
            playerManager.unequipActiveCosmetics();
        }
        else if (s.equalsIgnoreCase("hat") || s.equalsIgnoreCase("hats")) {
            player.sendMessage(MessageType.RESET_HAT.getFormatMessage());
            playerManager.unequipHat();
        }
        else if (s.equalsIgnoreCase("animatedhat") || s.equalsIgnoreCase("animated_hat") || s.equalsIgnoreCase("animatedhats") || s.equalsIgnoreCase("animated_hats")) {
            player.sendMessage(MessageType.RESET_ANIMATED_HAT.getFormatMessage());
            playerManager.unequipAnimatedHat();
        }
        else if (s.equalsIgnoreCase("particle") || s.equalsIgnoreCase("particles")) {
            player.sendMessage(MessageType.RESET_PARTICLE.getFormatMessage());
            playerManager.unequipParticle();
        }
        else if (s.equalsIgnoreCase("suit") || s.equalsIgnoreCase("suits")) {
            player.sendMessage(MessageType.RESET_SUIT.getFormatMessage());
            playerManager.unequipSuit();
        }
        else if (s.equalsIgnoreCase("gadget") || s.equalsIgnoreCase("gadgets")) {
            player.sendMessage(MessageType.RESET_GADGET.getFormatMessage());
            playerManager.unequipGadget();
        }
        else if (s.equalsIgnoreCase("pet") || s.equalsIgnoreCase("pets")) {
            player.sendMessage(MessageType.RESET_PET.getFormatMessage());
            playerManager.unequipPet();
        }
        else if (s.equalsIgnoreCase("miniature") || s.equalsIgnoreCase("miniatures")) {
            player.sendMessage(MessageType.RESET_MINIATURE.getFormatMessage());
            playerManager.unequipMiniature();
        }
        else if (s.equalsIgnoreCase("morph") || s.equalsIgnoreCase("morphs")) {
            player.sendMessage(MessageType.RESET_MORPH.getFormatMessage());
            playerManager.unequipMorph();
        }
        else if (s.equalsIgnoreCase("banner") || s.equalsIgnoreCase("banners")) {
            player.sendMessage(MessageType.RESET_BANNER.getFormatMessage());
            playerManager.unequipBanner();
        }
        else if (s.equalsIgnoreCase("emote") || s.equalsIgnoreCase("emotes")) {
            player.sendMessage(MessageType.RESET_EMOTE.getFormatMessage());
            playerManager.unequipEmote();
        }
        else {
            if (!s.equalsIgnoreCase("cloak") && !s.equalsIgnoreCase("cloaks")) {
                CommandManager.printMessage(commandSender, new CommandReset());
                commandSender.sendMessage(ChatUtil.format("&bCosmetics&e: &c&lAll, Hat, Animated_Hat, Particle, Suit, Gadget, Pet, Miniature, Morph, Banner, Emote, Cloak"));
                return;
            }
            player.sendMessage(MessageType.RESET_CLOAK.getFormatMessage());
            playerManager.unequipCloak();
        }
        commandSender.sendMessage(MessageType.RESET_COMPLETED.getFormatMessage());
    }
    
    private void resetCosmeticForAllPlayers(final CommandSender commandSender, final String anotherString) {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
            if (anotherString.equalsIgnoreCase("all")) {
                player.sendMessage(MessageType.RESET_COSMETICS.getFormatMessage());
                playerManager.unequipActiveCosmetics();
            }
            else if (anotherString.equalsIgnoreCase("hat") || anotherString.equalsIgnoreCase("hats")) {
                player.sendMessage(MessageType.RESET_HAT.getFormatMessage());
                playerManager.unequipHat();
            }
            else if (anotherString.equalsIgnoreCase("animatedhat") || anotherString.equalsIgnoreCase("animated_hat") || anotherString.equalsIgnoreCase("animatedhats") || anotherString.equalsIgnoreCase("animated_hats")) {
                player.sendMessage(MessageType.RESET_ANIMATED_HAT.getFormatMessage());
                playerManager.unequipAnimatedHat();
            }
            else if (anotherString.equalsIgnoreCase("particle") || anotherString.equalsIgnoreCase("particles")) {
                player.sendMessage(MessageType.RESET_PARTICLE.getFormatMessage());
                playerManager.unequipParticle();
            }
            else if (anotherString.equalsIgnoreCase("suit") || anotherString.equalsIgnoreCase("suits")) {
                player.sendMessage(MessageType.RESET_SUIT.getFormatMessage());
                playerManager.unequipSuit();
            }
            else if (anotherString.equalsIgnoreCase("gadget") || anotherString.equalsIgnoreCase("gadgets")) {
                player.sendMessage(MessageType.RESET_GADGET.getFormatMessage());
                playerManager.unequipGadget();
            }
            else if (anotherString.equalsIgnoreCase("pet") || anotherString.equalsIgnoreCase("pets")) {
                player.sendMessage(MessageType.RESET_PET.getFormatMessage());
                playerManager.unequipPet();
            }
            else if (anotherString.equalsIgnoreCase("miniature") || anotherString.equalsIgnoreCase("miniatures")) {
                player.sendMessage(MessageType.RESET_MINIATURE.getFormatMessage());
                playerManager.unequipMiniature();
            }
            else if (anotherString.equalsIgnoreCase("morph") || anotherString.equalsIgnoreCase("morphs")) {
                player.sendMessage(MessageType.RESET_MORPH.getFormatMessage());
                playerManager.unequipMorph();
            }
            else if (anotherString.equalsIgnoreCase("banner") || anotherString.equalsIgnoreCase("banners")) {
                player.sendMessage(MessageType.RESET_BANNER.getFormatMessage());
                playerManager.unequipBanner();
            }
            else if (anotherString.equalsIgnoreCase("emote") || anotherString.equalsIgnoreCase("emotes")) {
                player.sendMessage(MessageType.RESET_EMOTE.getFormatMessage());
                playerManager.unequipEmote();
            }
            else {
                if (!anotherString.equalsIgnoreCase("cloak") && !anotherString.equalsIgnoreCase("cloaks")) {
                    continue;
                }
                player.sendMessage(MessageType.RESET_CLOAK.getFormatMessage());
                playerManager.unequipCloak();
            }
        }
        String[] array;
        for (int length = (array = new String[] { "all", "hat", "hats", "animatedhat", "animatedhats", "animated_hat", "animated_hats", "particle", "particles", "suit", "suits", "gadget", "gadgets", "pet", "pets", "miniature", "miniatures", "morph", "morphs", "banner", "banners", "emote", "emotes", "cloak", "cloaks" }).length, i = 0; i < length; ++i) {
            if (array[i].equalsIgnoreCase(anotherString)) {
                commandSender.sendMessage(MessageType.RESET_COMPLETED.getFormatMessage());
                return;
            }
        }
        CommandManager.printMessage(commandSender, this);
        commandSender.sendMessage(ChatUtil.format("&bCosmetics&e: &c&lAll, Hat, Animated_Hat, Particle, Suit, Gadget, Pet, Miniature, Morph, Banner, Emote, Cloak"));
    }
}
