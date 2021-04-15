

package ws.billy.CookieGadgets.cosmetics.type;

import java.util.Iterator;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.ChatUtil;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.cosmetics.Category;
import java.util.List;

public class CosmeticType
{
    private static final List<CosmeticType> COSMETICS;
    private Category category;
    private String name;
    private String displayName;
    private String permission;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    
    static {
        COSMETICS = new ArrayList<CosmeticType>();
    }
    
    public CosmeticType(final Category category, final String name, final String displayName, final String permission, final int mysteryDust, final Rarity rarity, final List<String> lockedLore, final List<String> unlockedLore) {
        this.category = category;
        this.name = name;
        this.displayName = displayName;
        this.permission = permission;
        this.mysteryDust = mysteryDust;
        this.rarity = rarity;
        this.lockedLore = lockedLore;
        this.unlockedLore = unlockedLore;
        if (!CosmeticType.COSMETICS.contains(this)) {
            CosmeticType.COSMETICS.add(this);
        }
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDisplayName() {
        return ChatUtil.format(this.displayName);
    }
    
    public String getDisplayNameStripColor() {
        return ChatUtil.stripColor(this.displayName);
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public int getMysteryDust() {
        return this.mysteryDust;
    }
    
    public Rarity getRarity() {
        return this.rarity;
    }
    
    public List<String> getLockedLore() {
        return this.lockedLore;
    }
    
    public List<String> getUnlockedLore() {
        return this.unlockedLore;
    }
    
    public boolean isEnabled() {
        return true;
    }
    
    public boolean canBeFound() {
        return true;
    }
    
    public boolean isPurchasable() {
        return true;
    }
    
    public String getFilePath() {
        return "";
    }
    
    public void equip(final PlayerManager playerManager) {
    }
    
    public void unequip(final PlayerManager playerManager) {
    }
    
    public boolean checkRequirement(final PlayerManager playerManager) {
        return true;
    }
    
    public static List<CosmeticType> cosmetics() {
        return CosmeticType.COSMETICS;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static CosmeticType valueOf(final String anotherString, final Category category) {
        for (final CosmeticType cosmeticType : cosmetics()) {
            if (cosmeticType.getName().equalsIgnoreCase(anotherString) && cosmeticType.getCategory() == category) {
                return cosmeticType;
            }
        }
        return null;
    }
}
