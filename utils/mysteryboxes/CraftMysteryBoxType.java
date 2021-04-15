

package ws.billy.CookieGadgets.utils.mysteryboxes;

import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.List;
import ws.billy.CookieGadgets.utils.GMaterial;

public enum CraftMysteryBoxType
{
    CRAFT_MYSTERY_BOX_1("1", FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Material")), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Price"), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Slot")), 
    CRAFT_MYSTERY_BOX_2("2", FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Material")), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Price"), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Slot")), 
    CRAFT_MYSTERY_BOX_3("3", FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Material")), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Price"), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Slot")), 
    CRAFT_MYSTERY_BOX_4("4", FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Material")), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Price"), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Slot")), 
    CRAFT_MYSTERY_BOX_5("5", FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Material")), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Price"), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Slot"));
    
    private String name;
    private String displayName;
    private GMaterial material;
    private int price;
    private final List<String> lore;
    private int slot;
    
    private CraftMysteryBoxType(final String name2, final String displayName, final GMaterial material, final int price, final List<String> lore, final int slot) {
        this.name = name2;
        this.displayName = displayName;
        this.material = material;
        this.price = price;
        this.lore = lore;
        this.slot = slot;
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
    
    public int getPrice() {
        return this.price;
    }
    
    public List<String> getLore() {
        return this.lore;
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static CraftMysteryBoxType valueOf(final int i) {
        CraftMysteryBoxType[] values;
        for (int length = (values = values()).length, j = 0; j < length; ++j) {
            final CraftMysteryBoxType craftMysteryBoxType = values[j];
            if (craftMysteryBoxType.getName().equalsIgnoreCase(String.valueOf(i))) {
                return craftMysteryBoxType;
            }
        }
        return null;
    }
}
