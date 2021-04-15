

package ws.billy.CookieGadgets.utils.mysteryboxes;

import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.CookieGadgets;
import java.util.Iterator;
import ws.billy.CookieGadgets.mysteryboxes.customloot.CustomMysteryBoxesLoot;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticType;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxesLoot;
import java.util.ArrayList;

public class CosmeticItems
{
    private static ArrayList<MysteryBoxesLoot> common;
    private static ArrayList<MysteryBoxesLoot> rare;
    private static ArrayList<MysteryBoxesLoot> epic;
    private static ArrayList<MysteryBoxesLoot> legendary;
    private Rarity rarity;
    
    static {
        CosmeticItems.common = new ArrayList<MysteryBoxesLoot>();
        CosmeticItems.rare = new ArrayList<MysteryBoxesLoot>();
        CosmeticItems.epic = new ArrayList<MysteryBoxesLoot>();
        CosmeticItems.legendary = new ArrayList<MysteryBoxesLoot>();
    }
    
    public CosmeticItems(final Rarity rarity) {
        this.rarity = rarity;
        if (this.rarity == null) {
            throw new IllegalArgumentException("The rarity cannot be null!");
        }
    }
    
    public static void loadItems() {
        for (final CosmeticType cosmeticType : CosmeticType.cosmetics()) {
            if (cosmeticType.getCategory().isEnabled() && cosmeticType.getCategory().canBeFound() && cosmeticType.isEnabled() && cosmeticType.canBeFound()) {
                if (cosmeticType.getRarity() == Rarity.COMMON) {
                    CosmeticItems.common.add(new MysteryBoxesLoot(Rarity.COMMON, cosmeticType.getCategory(), cosmeticType.getName()));
                }
                else if (cosmeticType.getRarity() == Rarity.RARE) {
                    CosmeticItems.rare.add(new MysteryBoxesLoot(Rarity.RARE, cosmeticType.getCategory(), cosmeticType.getName()));
                }
                else if (cosmeticType.getRarity() == Rarity.EPIC) {
                    CosmeticItems.epic.add(new MysteryBoxesLoot(Rarity.EPIC, cosmeticType.getCategory(), cosmeticType.getName()));
                }
                else {
                    if (cosmeticType.getRarity() != Rarity.LEGENDARY) {
                        continue;
                    }
                    CosmeticItems.legendary.add(new MysteryBoxesLoot(Rarity.LEGENDARY, cosmeticType.getCategory(), cosmeticType.getName()));
                }
            }
        }
        for (final CustomMysteryBoxesLoot customMysteryBoxesLoot : CustomMysteryBoxesLoot.values()) {
            if (!customMysteryBoxesLoot.canBeFound()) {
                continue;
            }
            if (customMysteryBoxesLoot.getRarity() == Rarity.COMMON) {
                CosmeticItems.common.add(new MysteryBoxesLoot(customMysteryBoxesLoot));
            }
            else if (customMysteryBoxesLoot.getRarity() == Rarity.RARE) {
                CosmeticItems.rare.add(new MysteryBoxesLoot(customMysteryBoxesLoot));
            }
            else if (customMysteryBoxesLoot.getRarity() == Rarity.EPIC) {
                CosmeticItems.epic.add(new MysteryBoxesLoot(customMysteryBoxesLoot));
            }
            else {
                if (customMysteryBoxesLoot.getRarity() != Rarity.LEGENDARY) {
                    continue;
                }
                CosmeticItems.legendary.add(new MysteryBoxesLoot(customMysteryBoxesLoot));
            }
        }
    }
    
    public MysteryBoxesLoot getRandomItem() {
        if (this.rarity == Rarity.COMMON) {
            final MysteryBoxesLoot mysteryBoxesLoot = CosmeticItems.common.get(CookieGadgets.random().nextInt(CosmeticItems.common.size()));
            if (mysteryBoxesLoot == null) {
                return new MysteryBoxesLoot(Rarity.COMMON, null, null);
            }
            return mysteryBoxesLoot;
        }
        else if (this.rarity == Rarity.RARE) {
            final MysteryBoxesLoot mysteryBoxesLoot2 = CosmeticItems.rare.get(CookieGadgets.random().nextInt(CosmeticItems.rare.size()));
            if (mysteryBoxesLoot2 == null) {
                return new MysteryBoxesLoot(Rarity.RARE, null, null);
            }
            return mysteryBoxesLoot2;
        }
        else if (this.rarity == Rarity.EPIC) {
            final MysteryBoxesLoot mysteryBoxesLoot3 = CosmeticItems.epic.get(CookieGadgets.random().nextInt(CosmeticItems.epic.size()));
            if (mysteryBoxesLoot3 == null) {
                return new MysteryBoxesLoot(Rarity.EPIC, null, null);
            }
            return mysteryBoxesLoot3;
        }
        else {
            if (this.rarity != Rarity.LEGENDARY) {
                return new MysteryBoxesLoot(this.rarity, null, null);
            }
            final MysteryBoxesLoot mysteryBoxesLoot4 = CosmeticItems.legendary.get(CookieGadgets.random().nextInt(CosmeticItems.legendary.size()));
            if (mysteryBoxesLoot4 == null) {
                return new MysteryBoxesLoot(Rarity.LEGENDARY, null, null);
            }
            return mysteryBoxesLoot4;
        }
    }
}
