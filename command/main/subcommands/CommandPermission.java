

package ws.billy.CookieGadgets.command.main.subcommands;

import ws.billy.CookieGadgets.cosmetics.hats.standard.HatType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitEquipmentType;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import ws.billy.CookieGadgets.cosmetics.banners.BannerType;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import ws.billy.CookieGadgets.cosmetics.emotes.EmoteType;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import ws.billy.CookieGadgets.cosmetics.particles.ParticleType;
import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHatType;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandPermission extends SubCommand
{
    public CommandPermission() {
        super("/gmenu permission <cosmetic|commands> [page]", "Gives a list of all permissions.", null, "CookieGadgets.commands.permission", new String[] { "permission", "permissions", "perm" }, true);
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
        if (array.length > 3) {
            CommandManager.printMessage(commandSender, new CommandPermission());
            return;
        }
        String s = Types.COMMANDS.getName();
        if (array.length >= 2) {
            Types[] values;
            for (int length = (values = Types.values()).length, i = 0; i < length; ++i) {
                final Types types = values[i];
                if (types.getName().equalsIgnoreCase(array[1])) {
                    s = types.getName();
                }
            }
            if (s == Types.COMMANDS.getName() && !array[1].equalsIgnoreCase("commands")) {
                CommandManager.printMessage(commandSender, new CommandPermission());
                commandSender.sendMessage(ChatUtil.format("&bCosmetics&e: &c&lCommands, Hats, Animated_Hats, Particles, Suits, Gadgets, Pets, Miniatures, Morphs, Banners, Emotes, Cloaks"));
                return;
            }
        }
        final int size = this.getSize(s);
        final int maxPagesAmount = GInventory.getMaxPagesAmount(8, size);
        int int1 = 1;
        if (array.length == 3) {
            try {
                int1 = Integer.parseInt(array[2]);
            }
            catch (NumberFormatException ex2) {
                commandSender.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
                return;
            }
            if (Integer.valueOf(array[2]) > maxPagesAmount) {
                int1 = 1;
            }
        }
        int n = 1;
        if (int1 > 1) {
            n = 8 * (int1 - 1) + 1;
            if (s == Types.COMMANDS.getName()) {
                n = 8 * (int1 - 1) + 3;
            }
        }
        int n2 = 8;
        if (size < 8) {
            n2 = size;
        }
        if (int1 > 1) {
            if (size >= 8 * int1) {
                n2 = 8 * int1;
                if (s == Types.COMMANDS.getName()) {
                    n2 = 8 * int1 + 2;
                }
            }
            else {
                n2 = size;
                if (s == Types.COMMANDS.getName()) {
                    n2 = size + 2;
                }
            }
        }
        int n3 = 1;
        if (int1 > 1) {
            n3 = 8 * (int1 - 1) + 1;
        }
        commandSender.sendMessage(ChatUtil.format("&e---------------&fPermission: " + s.toLowerCase().substring(0, 1).toUpperCase() + s.toLowerCase().substring(1) + "&e---------------" + "&b[" + int1 + "/" + maxPagesAmount + "]"));
        for (int j = n; j <= n2; ++j) {
            try {
                if (this.getPermission(s, j) == null) {
                    ++n2;
                }
                else {
                    commandSender.sendMessage(ChatUtil.format(String.valueOf(n3++) + ". " + this.hasPermission(commandSender, this.getPermission(s, j)) + this.getPermission(s, j)));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (int1 >= maxPagesAmount) {
            if (int1 > 1 && maxPagesAmount > 1) {
                commandSender.sendMessage(ChatUtil.format("&3Use &b/menu permission " + s.toLowerCase() + " " + (maxPagesAmount - 1) + " &3to display page " + (maxPagesAmount - 1) + "!"));
            }
        }
        else {
            commandSender.sendMessage(ChatUtil.format("&3Use &b/menu permission " + s.toLowerCase() + " " + (int1 + 1) + " &3to display page " + (int1 + 1) + "!"));
        }
        commandSender.sendMessage(ChatUtil.format("&7More Permissions: &bhttp://bit.ly/CookieGadgets-Permissions"));
    }
    
    private String hasPermission(final CommandSender commandSender, final String s) {
        if (commandSender.hasPermission(s)) {
            return "&e";
        }
        return "&c";
    }
    
    private String getPermission(final String s, final int n) {
        switch (s.hashCode()) {
            case -1847009708: {
                if (!s.equals("animated_hats")) {
                    return null;
                }
                return AnimatedHatType.values().get(n - 1).getPermission();
            }
            case -1800314195: {
                if (!s.equals("particles")) {
                    return null;
                }
                return ParticleType.values().get(n - 1).getPermission();
            }
            case -1357537629: {
                if (!s.equals("cloaks")) {
                    return null;
                }
                return CloakType.values().get(n - 1).getPermission();
            }
            case -1299337733: {
                if (!s.equals("emotes")) {
                    return null;
                }
                return EmoteType.values().get(n - 1).getPermission();
            }
            case -1068371861: {
                if (!s.equals("morphs")) {
                    return null;
                }
                return MorphType.values().get(n - 1).getPermission();
            }
            case -727324203: {
                if (!s.equals("miniatures")) {
                    return null;
                }
                return MiniatureType.values().get(n - 1).getPermission();
            }
            case -602535288: {
                if (!s.equals("commands")) {
                    return null;
                }
                final int size = CommandManager.subCmds.size();
                if (n <= size) {
                    return CommandManager.subCmds.get(n - 1).getPermission();
                }
                if (n > size && n <= size + ws.billy.CookieGadgets.command.mysterydust.CommandManager.subCmds.size()) {
                    return ws.billy.CookieGadgets.command.mysterydust.CommandManager.subCmds.get(n - 1 - size).getPermission();
                }
                if (n > size + ws.billy.CookieGadgets.command.mysterydust.CommandManager.subCmds.size()) {
                    return ws.billy.CookieGadgets.command.mysteryboxes.CommandManager.subCmds.get(n - 1 - size - ws.billy.CookieGadgets.command.mysterydust.CommandManager.subCmds.size()).getPermission();
                }
                break;
            }
            case -336959801: {
                if (!s.equals("banners")) {
                    return null;
                }
                return BannerType.values().get(n - 1).getPermission();
            }
            case -203852377: {
                if (!s.equals("gadgets")) {
                    return null;
                }
                return GadgetType.values().get(n - 1).getPermission();
            }
            case 3195192: {
                if (!s.equals("hats")) {
                    return null;
                }
                break;
            }
            case 3437364: {
                if (!s.equals("pets")) {
                    return null;
                }
                return PetType.values().get(n - 1).getPermission();
            }
            case 109795078: {
                if (!s.equals("suits")) {
                    return null;
                }
                return SuitEquipmentType.values().get(n - 1).getPermission();
            }
        }
        return HatType.values().get(n - 1).getPermission();
    }
    
    private int getSize(final String s) {
        int n = 0;
        switch (s) {
            case "animated_hats": {
                n = AnimatedHatType.values().size();
                break;
            }
            case "particles": {
                n = ParticleType.values().size();
                break;
            }
            case "cloaks": {
                n = CloakType.values().size();
                break;
            }
            case "emotes": {
                n = EmoteType.values().size();
                break;
            }
            case "morphs": {
                n = MorphType.values().size();
                break;
            }
            case "miniatures": {
                n = MiniatureType.values().size();
                break;
            }
            case "commands": {
                int size = CommandManager.subCmds.size();
                for (int i = 1; i <= CommandManager.subCmds.size(); ++i) {
                    if (CommandManager.subCmds.get(i - 1).getPermission() == null) {
                        --size;
                    }
                }
                n = size + ws.billy.CookieGadgets.command.mysterydust.CommandManager.subCmds.size() + ws.billy.CookieGadgets.command.mysteryboxes.CommandManager.subCmds.size();
                break;
            }
            case "banners": {
                n = BannerType.values().size();
                break;
            }
            case "gadgets": {
                n = GadgetType.values().size();
                break;
            }
            case "hats": {
                n = HatType.values().size();
                break;
            }
            case "pets": {
                n = PetType.values().size();
                break;
            }
            case "suits": {
                n = SuitEquipmentType.values().size();
                break;
            }
            default:
                break;
        }
        return n;
    }
    
    private enum Types
    {
        COMMANDS("COMMANDS", 0, "commands"), 
        HATS("HATS", 1, "hats"), 
        ANIMATED_HATS("ANIMATED_HATS", 2, "animated_hats"), 
        PARTICLES("PARTICLES", 3, "particles"), 
        SUITS("SUITS", 4, "suits"), 
        GADGETS("GADGETS", 5, "gadgets"), 
        PETS("PETS", 6, "pets"), 
        MINIATURES("MINIATURES", 7, "miniatures"), 
        MORPHS("MORPHS", 8, "morphs"), 
        BANNERS("BANNERS", 9, "banners"), 
        EMOTES("EMOTES", 10, "emotes"), 
        CLOAKS("CLOAKS", 11, "cloaks");
        
        private String name;
        
        private Types(final String name, final int ordinal, final String name2) {
            this.name = name2;
        }
        
        public String getName() {
            return this.name;
        }
    }
}
