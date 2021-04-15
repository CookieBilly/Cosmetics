

package ws.billy.CookieGadgets.utils.mysteryboxes.utils;

import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.List;

public enum MysteryBoxJSONMessages
{
    FOUND_ONE_STAR_BOX(1, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.One-Star.Message")), 
    FOUND_TWO_STAR_BOX(2, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Two-Star.Message")), 
    FOUND_THREE_STAR_BOX(3, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Three-Star.Message")), 
    FOUND_FOUR_STAR_BOX(4, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Four-Star.Message")), 
    FOUND_FIVE_STAR_BOX(5, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Five-Star.Message")), 
    FOUND_COMMON_LOOT(Rarity.COMMON, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Loot.Common.Message")), 
    FOUND_RARE_LOOT(Rarity.RARE, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Loot.Rare.Message")), 
    FOUND_EPIC_LOOT(Rarity.EPIC, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Loot.Epic.Message")), 
    FOUND_LEGENDARY_LOOT(Rarity.LEGENDARY, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Loot.Legendary.Message")), 
    MYSTERY_DUST(FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Mystery-Dust.Message")), 
    PET_ITEMS(FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Pet-Items.Message"));
    
    private Rarity rarity;
    private int quality;
    private List<String> message;
    
    private MysteryBoxJSONMessages(final List<String> message) {
        this.message = message;
    }
    
    private MysteryBoxJSONMessages(final Rarity rarity, final List<String> message) {
        this.rarity = rarity;
        this.quality = 0;
        this.message = message;
    }
    
    private MysteryBoxJSONMessages(final int quality, final List<String> message) {
        this.rarity = null;
        this.quality = quality;
        this.message = message;
    }
    
    public Rarity getRarity() {
        return this.rarity;
    }
    
    public int getQuality() {
        return this.quality;
    }
    
    public List<String> getMessage() {
        return ChatUtil.format(this.message);
    }
    
    public static MysteryBoxJSONMessages valueOf(final Rarity rarity) {
        MysteryBoxJSONMessages[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final MysteryBoxJSONMessages mysteryBoxJSONMessages = values[i];
            if (mysteryBoxJSONMessages.getRarity() != null) {
                if (mysteryBoxJSONMessages.getRarity() == rarity) {
                    return mysteryBoxJSONMessages;
                }
            }
        }
        return null;
    }
    
    public static MysteryBoxJSONMessages valueOfBoxFound(final int n) {
        MysteryBoxJSONMessages[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final MysteryBoxJSONMessages mysteryBoxJSONMessages = values[i];
            if (mysteryBoxJSONMessages.getQuality() != 0) {
                if (mysteryBoxJSONMessages.getQuality() == n) {
                    return mysteryBoxJSONMessages;
                }
            }
        }
        return null;
    }
}
