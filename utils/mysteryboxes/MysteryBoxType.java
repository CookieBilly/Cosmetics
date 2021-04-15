

package ws.billy.CookieGadgets.utils.mysteryboxes;

import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.List;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Quality;
import ws.billy.CookieGadgets.utils.GMaterial;

public enum MysteryBoxType
{
    NORMAL_MYSTERY_BOX_1("Normal Mystery Box #1", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Material")), "CookieGadgets.mysteryboxes.open.1", Quality.ONE_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Normal-Mystery-Box.Lore.Quality.One-Star"), MessageType.NO_PERMISSION_TO_OPEN_ONE_STAR_MYSTERY_BOX.getFormatMessage()),
    NORMAL_MYSTERY_BOX_2("Normal Mystery Box #2", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Material")), "CookieGadgets.mysteryboxes.open.2", Quality.TWO_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Normal-Mystery-Box.Lore.Quality.Two-Star"), MessageType.NO_PERMISSION_TO_OPEN_TWO_STAR_MYSTERY_BOX.getFormatMessage()),
    NORMAL_MYSTERY_BOX_3("Normal Mystery Box #3", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Material")), "CookieGadgets.mysteryboxes.open.3", Quality.THREE_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Normal-Mystery-Box.Lore.Quality.Three-Star"), MessageType.NO_PERMISSION_TO_OPEN_THREE_STAR_MYSTERY_BOX.getFormatMessage()),
    NORMAL_MYSTERY_BOX_4("Normal Mystery Box #4", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Material")), "CookieGadgets.mysteryboxes.open.4", Quality.FOUR_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Normal-Mystery-Box.Lore.Quality.Four-Star"), MessageType.NO_PERMISSION_TO_OPEN_FOUR_STAR_MYSTERY_BOX.getFormatMessage()),
    NORMAL_MYSTERY_BOX_5("Normal Mystery Box #5", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Normal-Mystery-Box.Material")), "CookieGadgets.mysteryboxes.open.5", Quality.FIVE_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Normal-Mystery-Box.Lore.Quality.Five-Star"), MessageType.NO_PERMISSION_TO_OPEN_FIVE_STAR_MYSTERY_BOX.getFormatMessage()),
    GIFTED_MYSTERY_BOX_1("Gifted Mystery Box #1", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Gifted-Mystery-Box.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Gifted-Mystery-Box.Material")), "CookieGadgets.mysteryboxes.open.1", Quality.ONE_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Gifted-Mystery-Box.Lore.One-Star")),
    GIFTED_MYSTERY_BOX_2("Gifted Mystery Box #2", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Gifted-Mystery-Box.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Gifted-Mystery-Box.Material")), "CookieGadgets.mysteryboxes.open.2", Quality.TWO_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Gifted-Mystery-Box.Lore.Two-Star")),
    GIFTED_MYSTERY_BOX_3("Gifted Mystery Box #3", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Gifted-Mystery-Box.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Gifted-Mystery-Box.Material")), "CookieGadgets.mysteryboxes.open.3", Quality.THREE_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Gifted-Mystery-Box.Lore.Three-Star")),
    GIFTED_MYSTERY_BOX_4("Gifted Mystery Box #4", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Gifted-Mystery-Box.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Gifted-Mystery-Box.Material")), "CookieGadgets.mysteryboxes.open.4", Quality.FOUR_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Gifted-Mystery-Box.Lore.Four-Star")),
    GIFTED_MYSTERY_BOX_5("Gifted Mystery Box #5", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Gifted-Mystery-Box.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Gifted-Mystery-Box.Material")), "CookieGadgets.mysteryboxes.open.5", Quality.FIVE_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Gifted-Mystery-Box.Lore.Five-Star")),
    CRAFTED_MYSTERY_BOX_1("Crafted Mystery Box #1", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Crafted-Mystery-Box.One-Star.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Crafted-Mystery-Box.One-Star.Material")), "CookieGadgets.mysteryboxes.open.1", Quality.ONE_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Crafted-Mystery-Box.One-Star.Lore")),
    CRAFTED_MYSTERY_BOX_2("Crafted Mystery Box #2", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Crafted-Mystery-Box.Two-Star.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Crafted-Mystery-Box.Two-Star.Material")), "CookieGadgets.mysteryboxes.open.2", Quality.TWO_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Crafted-Mystery-Box.Two-Star.Lore")),
    CRAFTED_MYSTERY_BOX_3("Crafted Mystery Box #3", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Crafted-Mystery-Box.Three-Star.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Crafted-Mystery-Box.Three-Star.Material")), "CookieGadgets.mysteryboxes.open.3", Quality.THREE_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Crafted-Mystery-Box.Three-Star.Lore")),
    CRAFTED_MYSTERY_BOX_4("Crafted Mystery Box #4", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Crafted-Mystery-Box.Four-Star.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Crafted-Mystery-Box.Four-Star.Material")), "CookieGadgets.mysteryboxes.open.4", Quality.FOUR_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Crafted-Mystery-Box.Four-Star.Lore")),
    CRAFTED_MYSTERY_BOX_5("Crafted Mystery Box #5", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Crafted-Mystery-Box.Five-Star.Name"), new GMaterial(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Types.Crafted-Mystery-Box.Five-Star.Material")), "CookieGadgets.mysteryboxes.open.5", Quality.FIVE_STAR, FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Types.Crafted-Mystery-Box.Five-Star.Lore"));
    
    private String name;
    private String displayName;
    private GMaterial material;
    private String permission;
    private Quality quality;
    private List<String> lore;
    private String noPermMessage;
    private boolean isNormalMysteryBox;
    private boolean isGiftedMysteryBox;
    private boolean isCraftedMysteryBox;
    
    private MysteryBoxType(final String name2, final String displayName, final GMaterial material, final String permission, final Quality quality, final List<String> lore) {
        this.isNormalMysteryBox = false;
        this.isGiftedMysteryBox = false;
        this.isCraftedMysteryBox = false;
        this.name = name2;
        this.displayName = displayName;
        this.material = material;
        this.quality = quality;
        this.permission = permission;
        this.lore = lore;
        if (name2.contains("Normal")) {
            this.isNormalMysteryBox = true;
        }
        if (name2.contains("Gifted")) {
            this.isGiftedMysteryBox = true;
        }
        if (name2.contains("Crafted")) {
            this.isCraftedMysteryBox = true;
        }
    }
    
    private MysteryBoxType(final String name2, final String displayName, final GMaterial material, final String permission, final Quality quality, final List<String> lore, final String noPermMessage) {
        this.isNormalMysteryBox = false;
        this.isGiftedMysteryBox = false;
        this.isCraftedMysteryBox = false;
        this.name = name2;
        this.displayName = displayName;
        this.material = material;
        this.quality = quality;
        this.permission = permission;
        this.lore = lore;
        this.noPermMessage = noPermMessage;
        if (name2.contains("Normal")) {
            this.isNormalMysteryBox = true;
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDisplayName() {
        return ChatUtil.format(this.displayName);
    }
    
    public GMaterial getMaterial() {
        return this.material;
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public Quality getQuality() {
        return this.quality;
    }
    
    public List<String> getLore() {
        return this.lore;
    }
    
    public String getNoPermissionMessage() {
        return this.noPermMessage;
    }
    
    public boolean isNormalMysteryBox() {
        return this.isNormalMysteryBox;
    }
    
    public boolean isGiftedMysteryBox() {
        return this.isGiftedMysteryBox;
    }
    
    public boolean isCraftedMysteryBox() {
        return this.isCraftedMysteryBox;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static MysteryBoxType valueOfByName(final String anotherString) {
        MysteryBoxType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final MysteryBoxType mysteryBoxType = values[i];
            if (mysteryBoxType.getName().equalsIgnoreCase(anotherString)) {
                return mysteryBoxType;
            }
        }
        return null;
    }
}
