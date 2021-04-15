

package ws.billy.CookieGadgets.utils.mysteryvault;

import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.ChatUtil;
import java.util.Iterator;
import org.bukkit.block.BlockFace;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.ArrayList;

public class MysteryVaultManager
{
    private static ArrayList<MysteryVault> vaults;
    private static boolean isIndividualHologramsEnable;
    private static ArrayList<String> availableMysteryBoxesHolograms;
    private static String zeroMysteryBoxesHologram;
    
    static {
        MysteryVaultManager.vaults = new ArrayList<MysteryVault>();
        MysteryVaultManager.isIndividualHologramsEnable = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Holograms.Individual-Holograms.Enabled");
        MysteryVaultManager.availableMysteryBoxesHolograms = new ArrayList<String>();
        MysteryVaultManager.zeroMysteryBoxesHologram = null;
    }
    
    public static void loadMysteryVaults() {
        if (FileManager.getMysteryVaultFile().get("Mystery-Vaults") != null) {
            for (final String s : MysteryVaultUtils.getKeys(FileManager.getMysteryVaultFile(), "Mystery-Vaults")) {
                MysteryVaultManager.vaults.add(new MysteryVault(s, BlockFace.valueOf(FileManager.getMysteryVaultFile().getString("Mystery-Vaults." + s + ".BlockFace")), MysteryVaultUtils.getLocation(FileManager.getMysteryVaultFile(), "Mystery-Vaults." + s + ".Location")));
            }
        }
    }
    
    public static boolean isIndividualHologramsEnabled() {
        return MysteryVaultManager.isIndividualHologramsEnable;
    }
    
    public static ArrayList<String> getAvailableMysteryBoxesHolograms() {
        if (MysteryVaultManager.availableMysteryBoxesHolograms.isEmpty()) {
            final Iterator<String> iterator = FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Holograms.Individual-Holograms.Available-Mystery-Boxes").iterator();
            while (iterator.hasNext()) {
                MysteryVaultManager.availableMysteryBoxesHolograms.add(ChatUtil.format(iterator.next()));
            }
            return MysteryVaultManager.availableMysteryBoxesHolograms;
        }
        return MysteryVaultManager.availableMysteryBoxesHolograms;
    }
    
    public static String getZeroMysteryBoxAvailableHologram() {
        if (MysteryVaultManager.zeroMysteryBoxesHologram == null) {
            return MysteryVaultManager.zeroMysteryBoxesHologram = ChatUtil.format(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Holograms.Individual-Holograms.Zero-Mystery-Box-Available"));
        }
        if (MysteryVaultManager.zeroMysteryBoxesHologram.equals("")) {
            return MysteryVaultManager.zeroMysteryBoxesHologram = ChatUtil.format("&c");
        }
        return MysteryVaultManager.zeroMysteryBoxesHologram;
    }
    
    public static void forceRemoveHolograms() {
        MysteryVaultManager.vaults.forEach(mysteryVault -> mysteryVault.removeHolograms());
    }
    
    public static void forceRemoveAvailableMysteryBoxesHologram() {
        MysteryVaultManager.vaults.forEach(mysteryVault -> mysteryVault.removeAvailableMysteryBoxesHologram());
    }
    
    public static void forceRemoveLootHologram() {
        MysteryVaultManager.vaults.forEach(mysteryVault -> mysteryVault.removeLootHologram());
    }
    
    public static MysteryVault getVaultByName(final String anotherString) {
        for (final MysteryVault mysteryVault : MysteryVaultManager.vaults) {
            if (mysteryVault.getName().equalsIgnoreCase(anotherString)) {
                return mysteryVault;
            }
        }
        return null;
    }
    
    public static MysteryVault getVaultByLocation(final Location location) {
        for (final MysteryVault mysteryVault : MysteryVaultManager.vaults) {
            if (mysteryVault.getLocation().equals((Object)location)) {
                return mysteryVault;
            }
        }
        return null;
    }
    
    public static ArrayList<MysteryVault> vaults() {
        return MysteryVaultManager.vaults;
    }
}
