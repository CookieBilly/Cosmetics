

package ws.billy.CookieGadgets.command.main.subcommands;

import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.cosmetics.particles.ParticleType;
import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHatType;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import ws.billy.CookieGadgets.cosmetics.emotes.EmoteType;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import ws.billy.CookieGadgets.cosmetics.hats.standard.HatType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import ws.billy.CookieGadgets.cosmetics.banners.BannerType;
import java.sql.Timestamp;
import ws.billy.CookieGadgets.cosmetics.suits.SuitEquipmentType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandAddPermission extends SubCommand
{
    public CommandAddPermission() {
        super("/gmenu addperm <cosmetic> <type> <player>", "Give player's permission to access cosmetic items.", null, "CookieGadgets.commands.addpermission", new String[] { "addpermission", "addperm", "ap" }, true);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length != 4) {
            CommandManager.printMessage((CommandSender)player, this);
            return;
        }
        Player player2 = player;
        if (array.length == 4) {
            player2 = player.getServer().getPlayer(array[3]);
            if (player2 == null || !player2.isOnline()) {
                player.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
        }
        this.onCommand((CommandSender)player, player2, array);
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        if (array.length != 4) {
            CommandManager.printMessage(commandSender, this);
            return;
        }
        final Player player = commandSender.getServer().getPlayer(array[3]);
        if (player == null || !player.isOnline()) {
            commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
            return;
        }
        this.onCommand(commandSender, player, array);
    }
    
    private void onCommand(final CommandSender commandSender, final Player player, final String[] array) {
        final String[] array2 = { "hat", "animated_hat", "particle", "suitHelmet", "suitChestplate", "suitLeggings", "suitBoots", "gadget", "pet", "miniature", "morph", "banner", "emote", "cloak" };
        String s = "none";
        String[] array3;
        for (int length = (array3 = array2).length, i = 0; i < length; ++i) {
            final String anotherString = array3[i];
            if (array[1].equalsIgnoreCase(anotherString)) {
                s = anotherString;
            }
        }
        if (s.equals("none")) {
            CommandManager.printMessage(commandSender, this);
            commandSender.sendMessage(ChatUtil.format("&bCosmetics&e: &c&lHat, Animated_Hat, Particle, SuitHelmet, SuitChestplate, SuitLeggings, SuitBoots, Gadget, Pet, Miniature, Morph, Banner, Emote, Cloak"));
            return;
        }
        final String s2 = array[2];
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (playerManager == null) {
            return;
        }
        final String s3;
        switch (s3 = s) {
            case "suitBoots": {
                if (!Category.SUITS.isEnabled()) {
                    commandSender.sendMessage(MessageType.SUITS_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final SuitType value = SuitType.valueOf(s2.replace("_Boots", ""));
                if (value == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "suit"));
                    final StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < SuitType.enabled().size(); ++j) {
                        sb.append(String.valueOf(SuitType.enabled().get(j).toString()) + "_Boots" + ((j != SuitType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bSuit Boots Types&e: &c" + sb.toString()));
                    return;
                }
                if (!value.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "suit"));
                    return;
                }
                final SuitEquipmentType value2 = SuitEquipmentType.valueOf(value + " Boots");
                if (playerManager.hasPermission(value2.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.SUITS, value2.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_SUIT.getFormatMessage().replace("{SUIT}", value2.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_SUIT_FOR_PLAYER.getFormatMessage().replace("{SUIT}", value2.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "suitHelmet": {
                if (!Category.SUITS.isEnabled()) {
                    commandSender.sendMessage(MessageType.SUITS_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final SuitType value3 = SuitType.valueOf(s2.replace("_Helmet", ""));
                if (value3 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "suit"));
                    final StringBuilder sb2 = new StringBuilder();
                    for (int k = 0; k < SuitType.enabled().size(); ++k) {
                        sb2.append(String.valueOf(SuitType.enabled().get(k).toString()) + "_Helmet" + ((k != SuitType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bSuit Helmet Types&e: &c" + sb2.toString()));
                    return;
                }
                if (!value3.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "suit"));
                    return;
                }
                final SuitEquipmentType value4 = SuitEquipmentType.valueOf(value3 + " Helmet");
                if (playerManager.hasPermission(value4.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.SUITS, value4.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_SUIT.getFormatMessage().replace("{SUIT}", value4.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_SUIT.getFormatMessage().replace("{SUIT}", value4.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "banner": {
                if (!Category.BANNERS.isEnabled()) {
                    commandSender.sendMessage(MessageType.BANNERS_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final BannerType value5 = BannerType.valueOf(s2.replace("_", " "));
                if (value5 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "banner"));
                    final StringBuilder sb3 = new StringBuilder();
                    for (int l = 0; l < BannerType.enabled().size(); ++l) {
                        sb3.append(String.valueOf(BannerType.enabled().get(l).toString().replace(" ", "_")) + ((l != BannerType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bBanner Types&e: &c" + sb3.toString()));
                    return;
                }
                if (!value5.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "banner"));
                    return;
                }
                if (playerManager.hasPermission(value5.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.BANNERS, value5.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_BANNER.getFormatMessage().replace("{BANNER}", value5.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_BANNER_FOR_PLAYER.getFormatMessage().replace("{BANNER}", value5.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "gadget": {
                if (!Category.GADGETS.isEnabled()) {
                    commandSender.sendMessage(MessageType.GADGETS_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final GadgetType value6 = GadgetType.valueOf(s2.replace("_", " "));
                if (value6 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "gadget"));
                    final StringBuilder sb4 = new StringBuilder();
                    for (int n = 0; n < GadgetType.enabled().size(); ++n) {
                        sb4.append(String.valueOf(GadgetType.enabled().get(n).toString().replace(" ", "_")) + ((n != GadgetType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bGadget Types&e: &c" + sb4.toString()));
                    return;
                }
                if (!value6.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "gadget"));
                    return;
                }
                if (playerManager.hasPermission(value6.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.GADGETS, value6.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_GADGET.getFormatMessage().replace("{GADGET}", value6.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_GADGET_FOR_PLAYER.getFormatMessage().replace("{GADGET}", value6.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "suitChestplate": {
                if (!Category.SUITS.isEnabled()) {
                    commandSender.sendMessage(MessageType.SUITS_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final SuitType value7 = SuitType.valueOf(s2.replace("_Chestplate", ""));
                if (value7 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "suit"));
                    final StringBuilder sb5 = new StringBuilder();
                    for (int n2 = 0; n2 < SuitType.enabled().size(); ++n2) {
                        sb5.append(String.valueOf(SuitType.enabled().get(n2).toString()) + "_Chestplate" + ((n2 != SuitType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bSuit Chestplate Types&e: &c" + sb5.toString()));
                    return;
                }
                if (!value7.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "suit"));
                    return;
                }
                final SuitEquipmentType value8 = SuitEquipmentType.valueOf(value7 + " Chestplate");
                if (playerManager.hasPermission(value8.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.SUITS, value8.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_SUIT.getFormatMessage().replace("{SUIT}", value8.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_SUIT_FOR_PLAYER.getFormatMessage().replace("{SUIT}", value8.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "hat": {
                if (!Category.HATS.isEnabled()) {
                    commandSender.sendMessage(MessageType.HATS_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final HatType value9 = HatType.valueOf(s2.replace("_", " "));
                if (value9 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "hat"));
                    final StringBuilder sb6 = new StringBuilder();
                    for (int n3 = 0; n3 < HatType.enabled().size(); ++n3) {
                        sb6.append(String.valueOf(HatType.enabled().get(n3).toString().replace(" ", "_")) + ((n3 != HatType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bHat Types&e: &c" + sb6.toString()));
                    return;
                }
                if (!value9.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "hat"));
                    return;
                }
                if (playerManager.hasPermission(value9.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.HATS, value9.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_HAT.getFormatMessage().replace("{HAT}", value9.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_HAT_FOR_PLAYER.getFormatMessage().replace("{HAT}", value9.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "pet": {
                if (!Category.PETS.isEnabled()) {
                    commandSender.sendMessage(MessageType.PETS_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final PetType value10 = PetType.valueOf(s2.replace("_", " "));
                if (value10 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "pet"));
                    final StringBuilder sb7 = new StringBuilder();
                    for (int n4 = 0; n4 < PetType.enabled().size(); ++n4) {
                        sb7.append(String.valueOf(PetType.enabled().get(n4).toString().replace(" ", "_")) + ((n4 != PetType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bPet Types&e: &c" + sb7.toString()));
                    return;
                }
                if (!value10.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "pet"));
                    return;
                }
                if (playerManager.hasPermission(value10.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.PETS, value10.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_PET.getFormatMessage().replace("{PET}", value10.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_PET_FOR_PLAYER.getFormatMessage().replace("{PET}", value10.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "cloak": {
                if (!Category.CLOAKS.isEnabled()) {
                    commandSender.sendMessage(MessageType.CLOAKS_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final CloakType value11 = CloakType.valueOf(s2.replace("_", " "));
                if (value11 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "cloak"));
                    final StringBuilder sb8 = new StringBuilder();
                    for (int n5 = 0; n5 < CloakType.enabled().size(); ++n5) {
                        sb8.append(String.valueOf(CloakType.enabled().get(n5).toString().replace(" ", "_")) + ((n5 != CloakType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bCloak Types&e: &c" + sb8.toString()));
                    return;
                }
                if (!value11.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "cloak"));
                    return;
                }
                if (playerManager.hasPermission(value11.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.CLOAKS, value11.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_CLOAK.getFormatMessage().replace("{CLOAK}", value11.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_CLOAK_FOR_PLAYER.getFormatMessage().replace("{CLOAK}", value11.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "emote": {
                if (!Category.EMOTES.isEnabled()) {
                    commandSender.sendMessage(MessageType.EMOTES_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final EmoteType value12 = EmoteType.valueOf(s2.replace("_", " "));
                if (value12 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "emote"));
                    final StringBuilder sb9 = new StringBuilder();
                    for (int n6 = 0; n6 < EmoteType.enabled().size(); ++n6) {
                        sb9.append(String.valueOf(EmoteType.enabled().get(n6).toString().replace(" ", "_")) + ((n6 != EmoteType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bEmote Types&e: &c" + sb9.toString()));
                    return;
                }
                if (!value12.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "emote"));
                    return;
                }
                if (playerManager.hasPermission(value12.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.EMOTES, value12.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_EMOTE.getFormatMessage().replace("{EMOTE}", value12.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_EMOTE_FOR_PLAYER.getFormatMessage().replace("{EMOTE}", value12.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "morph": {
                if (!Category.MORPHS.isEnabled()) {
                    commandSender.sendMessage(MessageType.MORPHS_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final MorphType value13 = MorphType.valueOf(s2.replace("_", " "));
                if (value13 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "morph"));
                    final StringBuilder sb10 = new StringBuilder();
                    for (int n7 = 0; n7 < MorphType.enabled().size(); ++n7) {
                        sb10.append(String.valueOf(MorphType.enabled().get(n7).toString().replace(" ", "_")) + ((n7 != MorphType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bMorph Types&e: &c" + sb10.toString()));
                    return;
                }
                if (!value13.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "morph"));
                    return;
                }
                if (playerManager.hasPermission(value13.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.MORPHS, value13.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_MORPH.getFormatMessage().replace("{MORPH}", value13.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_MORPH_FOR_PLAYER.getFormatMessage().replace("{MORPH}", value13.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "miniature": {
                if (!Category.MINIATURES.isEnabled()) {
                    commandSender.sendMessage(MessageType.MINIATURES_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final MiniatureType value14 = MiniatureType.valueOf(s2.replace("_", " "));
                if (value14 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "miniature"));
                    final StringBuilder sb11 = new StringBuilder();
                    for (int n8 = 0; n8 < MiniatureType.enabled().size(); ++n8) {
                        sb11.append(String.valueOf(MiniatureType.enabled().get(n8).toString().replace(" ", "_")) + ((n8 != MiniatureType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bMiniature Types&e: &c" + sb11.toString()));
                    return;
                }
                if (!value14.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "miniature"));
                    return;
                }
                if (playerManager.hasPermission(value14.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.MINIATURES, value14.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_MINIATURE.getFormatMessage().replace("{MINIATURE}", value14.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_MINIATURE_FOR_PLAYER.getFormatMessage().replace("{MINIATURE}", value14.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "animated_hat": {
                if (!Category.ANIMATED_HATS.isEnabled()) {
                    commandSender.sendMessage(MessageType.ANIMATED_HATS_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final AnimatedHatType value15 = AnimatedHatType.valueOf(s2.replace("_", " "));
                if (value15 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "animated hat"));
                    final StringBuilder sb12 = new StringBuilder();
                    for (int n9 = 0; n9 < HatType.enabled().size(); ++n9) {
                        sb12.append(String.valueOf(HatType.enabled().get(n9).toString().replace(" ", "_")) + ((n9 != HatType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bAnimated Hat Types&e: &c" + sb12.toString()));
                    return;
                }
                if (!value15.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "animated hat"));
                    return;
                }
                if (playerManager.hasPermission(value15.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.ANIMATED_HATS, value15.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_ANIMATED_HAT.getFormatMessage().replace("{ANIMATED_HAT}", value15.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_ANIMATED_HAT_FOR_PLAYER.getFormatMessage().replace("{ANIMATED_HAT}", value15.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "particle": {
                if (!Category.PARTICLES.isEnabled()) {
                    commandSender.sendMessage(MessageType.PARTICLES_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final ParticleType value16 = ParticleType.valueOf(s2.replace("_", " "));
                if (value16 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "particle"));
                    final StringBuilder sb13 = new StringBuilder();
                    for (int n10 = 0; n10 < ParticleType.enabled().size(); ++n10) {
                        sb13.append(String.valueOf(ParticleType.enabled().get(n10).toString().replace(" ", "_")) + ((n10 != ParticleType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bParticle Types&e: &c" + sb13.toString()));
                    return;
                }
                if (!value16.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "particle"));
                    return;
                }
                if (playerManager.hasPermission(value16.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.PARTICLES, value16.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_PARTICLE.getFormatMessage().replace("{PARTICLE}", value16.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_PARTICLE_FOR_PLAYER.getFormatMessage().replace("{PARTICLE}", value16.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            case "suitLeggings": {
                if (!Category.SUITS.isEnabled()) {
                    commandSender.sendMessage(MessageType.SUITS_ARE_DISABLED.getFormatMessage());
                    return;
                }
                final SuitType value17 = SuitType.valueOf(s2.replace("_Leggings", ""));
                if (value17 == null) {
                    commandSender.sendMessage(MessageType.INVALID_TYPE.getFormatMessage().replace("{TYPE}", "suit"));
                    final StringBuilder sb14 = new StringBuilder();
                    for (int n11 = 0; n11 < SuitType.enabled().size(); ++n11) {
                        sb14.append(String.valueOf(SuitType.enabled().get(n11).toString()) + "_Leggings" + ((n11 != SuitType.enabled().size() - 1) ? "&f, &c" : ""));
                    }
                    commandSender.sendMessage(ChatUtil.format("&bSuit Leggings Types&e: &c" + sb14.toString()));
                    return;
                }
                if (!value17.isEnabled()) {
                    commandSender.sendMessage(MessageType.TYPE_IS_NOT_ENABLED.getFormatMessage().replace("{TYPE}", "suit"));
                    return;
                }
                final SuitEquipmentType value18 = SuitEquipmentType.valueOf(value17 + " Leggings");
                if (playerManager.hasPermission(value18.getPermission())) {
                    commandSender.sendMessage(MessageType.ALREADY_HAS_PERMISSION.getFormatMessage().replace("{PLAYER}", player.getName()));
                    return;
                }
                playerManager.addUnlockedCosmeticItem(Category.SUITS, value18.getName(), null);
                player.sendMessage(MessageType.GRANTED_ACCESS_TO_SUIT.getFormatMessage().replace("{SUIT}", value18.getDisplayNameStripColor()));
                commandSender.sendMessage(MessageType.GRANTED_ACCESS_TO_SUIT_FOR_PLAYER.getFormatMessage().replace("{SUIT}", value18.getDisplayNameStripColor()).replace("{PLAYER}", player.getName()));
                break;
            }
            default:
                break;
        }
    }
}
