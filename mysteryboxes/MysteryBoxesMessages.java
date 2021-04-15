

package ws.billy.CookieGadgets.mysteryboxes;

import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;

public class MysteryBoxesMessages
{
    public static String hologramLineOne;
    public static String hologramLineTwo;
    public static String expiryDateFormat;
    public static String neverExpiryFormat;
    private static boolean isOpeningMysteryBoxBoardcastEnabled;
    private static String openingMysteryBoxBoardcastMessage;
    private static boolean isRecentLootContainRarity;
    
    public static void initMessages() {
        MysteryBoxesMessages.hologramLineOne = ((FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Holograms.Mystery-Vault.Line-1") == null) ? "&bMystery Vault" : FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Holograms.Mystery-Vault.Line-1"));
        MysteryBoxesMessages.hologramLineTwo = ((FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Holograms.Mystery-Vault.Line-2") == null) ? "&e&lRight Click" : FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Holograms.Mystery-Vault.Line-2"));
        MysteryBoxesMessages.expiryDateFormat = FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Lore.Expiry-Date");
        MysteryBoxesMessages.neverExpiryFormat = FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Lore.Never-Expired");
        MysteryBoxesMessages.isOpeningMysteryBoxBoardcastEnabled = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Broadcast.Opening-Mystery-Box.Enabled");
        MysteryBoxesMessages.openingMysteryBoxBoardcastMessage = ChatUtil.format(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Broadcast.Opening-Mystery-Box.Message"));
        MysteryBoxesMessages.isRecentLootContainRarity = FileManager.getMessagesFile().getBoolean("GUI-Menus.Mystery-Vault-Menu.Items.Mystery-Box-Information.Loot-Contain-Rarity");
    }
    
    public static boolean isOpeningMysteryBoxBoardcastEnabled() {
        return MysteryBoxesMessages.isOpeningMysteryBoxBoardcastEnabled;
    }
    
    public static String getOpeningMysteryBoxBoardcastMessage() {
        return MysteryBoxesMessages.openingMysteryBoxBoardcastMessage;
    }
    
    public static boolean isRecentLootContainRarity() {
        return MysteryBoxesMessages.isRecentLootContainRarity;
    }
}
