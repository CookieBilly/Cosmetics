

package ws.billy.CookieGadgets.mysteryboxes;

import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.ChatUtil;
import java.util.List;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.ExpiryDateCounter;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.mysteryboxes.CosmeticItems;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.mysteryboxes.customloot.CustomMysteryBoxesLoot;
import ws.billy.CookieGadgets.cosmetics.Category;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Quality;
import ws.billy.CookieGadgets.utils.mysteryboxes.MysteryBoxType;

public class MysteryBoxes
{
    private MysteryBoxType mysteryBoxType;
    private Quality quality;
    private boolean expirable;
    private Long expiryDate;
    private boolean requirePerm;
    private String details;
    private String decodedLoots;
    private String encodedString;
    private String decodedString;
    private ArrayList<MysteryBoxesLoot> loots;
    private ArrayList<MysteryBoxesLoot> commonLoots;
    private ArrayList<MysteryBoxesLoot> rareLoots;
    private ArrayList<MysteryBoxesLoot> epicLoots;
    private ArrayList<MysteryBoxesLoot> legendaryLoots;
    
    public MysteryBoxes(final String encodedString) {
        this.loots = new ArrayList<MysteryBoxesLoot>();
        this.commonLoots = new ArrayList<MysteryBoxesLoot>();
        this.rareLoots = new ArrayList<MysteryBoxesLoot>();
        this.epicLoots = new ArrayList<MysteryBoxesLoot>();
        this.legendaryLoots = new ArrayList<MysteryBoxesLoot>();
        this.encodedString = encodedString;
        this.decodedString = Base64Coder.decodeString(encodedString);
        this.mysteryBoxType = MysteryBoxType.valueOfByName(this.decodedString.split("\\ %% ")[0]);
        this.quality = this.mysteryBoxType.getQuality();
        this.expiryDate = (this.decodedString.split("\\ %% ")[1].equals("null") ? null : Long.valueOf(this.decodedString.split("\\ %% ")[1]));
        this.expirable = (this.expiryDate != null);
        this.requirePerm = Boolean.valueOf(this.decodedString.split("\\ %% ")[2]);
        this.details = (this.decodedString.split("\\ %% ")[3].equals("null") ? null : this.decodedString.split("\\ %% ")[3]);
        if (this.expiryDate == null) {
            this.decodedLoots = this.decodedString.replace(String.valueOf(this.mysteryBoxType.getName()) + " %% " + (Object)null + " %% " + Boolean.valueOf(this.requirePerm).toString() + " %% " + this.details + " %% ", "");
        }
        else {
            this.decodedLoots = this.decodedString.replace(String.valueOf(this.mysteryBoxType.getName()) + " %% " + this.expiryDate.toString() + " %% " + Boolean.valueOf(this.requirePerm).toString() + " %% " + this.details + " %% ", "");
        }
        for (int i = 0; i < 7; ++i) {
            final int[] array = { 0, 3, 6, 9, 12, 15, 18 };
            final int[] array2 = { 1, 4, 7, 10, 13, 16, 19 };
            final int[] array3 = { 2, 5, 8, 11, 14, 17, 20 };
            final Category valueOfByName = Category.valueOfByName(this.decodedLoots.split("\\ %% ")[array2[i]]);
            if (valueOfByName == null) {
                final CustomMysteryBoxesLoot value = CustomMysteryBoxesLoot.valueOf(this.decodedLoots.split("\\ %% ")[array3[i]].toString());
                MysteryBoxesLoot randomItem;
                if (value == null) {
                    randomItem = new CosmeticItems(Rarity.getName(this.decodedLoots.split("\\ %% ")[array[i]])).getRandomItem();
                }
                else {
                    randomItem = new MysteryBoxesLoot(value);
                }
                this.loots.add(randomItem);
            }
            else {
                this.loots.add(new MysteryBoxesLoot(Rarity.getName(this.decodedLoots.split("\\ %% ")[array[i]]), valueOfByName, this.decodedLoots.split("\\ %% ")[array3[i]].toString()));
            }
        }
        for (final MysteryBoxesLoot mysteryBoxesLoot : this.loots) {
            if (mysteryBoxesLoot.getRarity() == Rarity.COMMON) {
                if (this.commonLoots.contains(mysteryBoxesLoot)) {
                    continue;
                }
                this.commonLoots.add(mysteryBoxesLoot);
            }
            else if (mysteryBoxesLoot.getRarity() == Rarity.RARE) {
                if (this.rareLoots.contains(mysteryBoxesLoot)) {
                    continue;
                }
                this.rareLoots.add(mysteryBoxesLoot);
            }
            else if (mysteryBoxesLoot.getRarity() == Rarity.EPIC) {
                if (this.epicLoots.contains(mysteryBoxesLoot)) {
                    continue;
                }
                this.epicLoots.add(mysteryBoxesLoot);
            }
            else {
                if (mysteryBoxesLoot.getRarity() != Rarity.LEGENDARY || this.legendaryLoots.contains(mysteryBoxesLoot)) {
                    continue;
                }
                this.legendaryLoots.add(mysteryBoxesLoot);
            }
        }
    }
    
    public MysteryBoxes(final MysteryBoxType mysteryBoxType, final boolean expirable, final Long expiryDate, final boolean requirePerm, final String details, final String decodedLoots) {
        this.loots = new ArrayList<MysteryBoxesLoot>();
        this.commonLoots = new ArrayList<MysteryBoxesLoot>();
        this.rareLoots = new ArrayList<MysteryBoxesLoot>();
        this.epicLoots = new ArrayList<MysteryBoxesLoot>();
        this.legendaryLoots = new ArrayList<MysteryBoxesLoot>();
        this.mysteryBoxType = mysteryBoxType;
        this.quality = this.mysteryBoxType.getQuality();
        this.expirable = expirable;
        this.expiryDate = expiryDate;
        this.requirePerm = requirePerm;
        this.details = details;
        this.decodedLoots = decodedLoots;
        this.decodedString = String.valueOf(this.mysteryBoxType.getName()) + " %% " + this.getExpiryDate() + " %% " + this.requirePerm + " %% " + this.details + " %% " + this.decodedLoots;
        this.encodedString = Base64Coder.encodeString(String.valueOf(this.mysteryBoxType.getName()) + " %% " + this.getExpiryDate() + " %% " + this.requirePerm + " %% " + this.details + " %% " + this.decodedLoots);
        for (int i = 0; i < 7; ++i) {
            final int[] array = { 0, 3, 6, 9, 12, 15, 18 };
            final int[] array2 = { 1, 4, 7, 10, 13, 16, 19 };
            final int[] array3 = { 2, 5, 8, 11, 14, 17, 20 };
            final Category valueOfByName = Category.valueOfByName(this.decodedLoots.split("\\ %% ")[array2[i]]);
            if (valueOfByName == null) {
                final CustomMysteryBoxesLoot value = CustomMysteryBoxesLoot.valueOf(this.decodedLoots.split("\\ %% ")[array3[i]].toString());
                MysteryBoxesLoot randomItem;
                if (value == null) {
                    randomItem = new CosmeticItems(Rarity.getName(this.decodedLoots.split("\\ %% ")[array[i]])).getRandomItem();
                }
                else {
                    randomItem = new MysteryBoxesLoot(value);
                }
                this.loots.add(randomItem);
            }
            else {
                this.loots.add(new MysteryBoxesLoot(Rarity.getName(this.decodedLoots.split("\\ %% ")[array[i]]), valueOfByName, this.decodedLoots.split("\\ %% ")[array3[i]].toString()));
            }
        }
        for (final MysteryBoxesLoot mysteryBoxesLoot : this.loots) {
            if (mysteryBoxesLoot.getRarity() == Rarity.COMMON) {
                if (this.commonLoots.contains(mysteryBoxesLoot)) {
                    continue;
                }
                this.commonLoots.add(mysteryBoxesLoot);
            }
            else if (mysteryBoxesLoot.getRarity() == Rarity.RARE) {
                if (this.rareLoots.contains(mysteryBoxesLoot)) {
                    continue;
                }
                this.rareLoots.add(mysteryBoxesLoot);
            }
            else if (mysteryBoxesLoot.getRarity() == Rarity.EPIC) {
                if (this.epicLoots.contains(mysteryBoxesLoot)) {
                    continue;
                }
                this.epicLoots.add(mysteryBoxesLoot);
            }
            else {
                if (mysteryBoxesLoot.getRarity() != Rarity.LEGENDARY || this.legendaryLoots.contains(mysteryBoxesLoot)) {
                    continue;
                }
                this.legendaryLoots.add(mysteryBoxesLoot);
            }
        }
    }
    
    public MysteryBoxType getMysteryBoxType() {
        return this.mysteryBoxType;
    }
    
    public Quality getQuality() {
        return this.quality;
    }
    
    public Long getExpiryDate() {
        return this.expiryDate;
    }
    
    public String getExpiryDateDistance() {
        return (this.expiryDate == null) ? null : new ExpiryDateCounter((this.expiryDate - System.currentTimeMillis()) / 1000L).getFormat();
    }
    
    public boolean isExpired() {
        return this.expiryDate - System.currentTimeMillis() < 1L;
    }
    
    public boolean isExpirable() {
        return this.expirable;
    }
    
    public boolean isRequiredPermission() {
        return this.requirePerm;
    }
    
    public String getDetails() {
        return this.details;
    }
    
    public String getDecodedLoots() {
        return this.decodedLoots;
    }
    
    public ArrayList<MysteryBoxesLoot> getLoots() {
        return this.loots;
    }
    
    public ArrayList<MysteryBoxesLoot> getCommonLoots() {
        return this.commonLoots;
    }
    
    public ArrayList<MysteryBoxesLoot> getRareLoots() {
        return this.rareLoots;
    }
    
    public ArrayList<MysteryBoxesLoot> getEpicLoots() {
        return this.epicLoots;
    }
    
    public ArrayList<MysteryBoxesLoot> getLegendaryLoots() {
        return this.legendaryLoots;
    }
    
    public List<String> getLore() {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.mysteryBoxType.isNormalMysteryBox()) {
            final Iterator<String> iterator = this.mysteryBoxType.getLore().iterator();
            while (iterator.hasNext()) {
                list.add(ChatUtil.format(iterator.next().replace("{ITEM_ONE}", this.getDisplayName(this, 1)).replace("{ITEM_TWO}", this.getDisplayName(this, 2)).replace("{ITEM_THREE}", this.getDisplayName(this, 3)).replace("{ITEM_FOUR}", this.getDisplayName(this, 4)).replace("{ITEM_FIVE}", this.getDisplayName(this, 5)).replace("{ITEM_SIX}", this.getDisplayName(this, 6)).replace("{ITEM_SEVEN}", this.getDisplayName(this, 7)).replace("{EXPIRY_DATE}", this.isExpirable() ? MysteryBoxesMessages.expiryDateFormat.replace("{EXPIRY_DATE}", this.getExpiryDateDistance()) : MysteryBoxesMessages.neverExpiryFormat)));
            }
        }
        else if (this.mysteryBoxType.isGiftedMysteryBox()) {
            final Iterator<String> iterator2 = this.mysteryBoxType.getLore().iterator();
            while (iterator2.hasNext()) {
                list.add(ChatUtil.format(iterator2.next().replace("{ITEM_ONE}", this.getDisplayName(this, 1)).replace("{ITEM_TWO}", this.getDisplayName(this, 2)).replace("{ITEM_THREE}", this.getDisplayName(this, 3)).replace("{ITEM_FOUR}", this.getDisplayName(this, 4)).replace("{ITEM_FIVE}", this.getDisplayName(this, 5)).replace("{ITEM_SIX}", this.getDisplayName(this, 6)).replace("{ITEM_SEVEN}", this.getDisplayName(this, 7)).replace("{SENDER}", this.getDetails())));
            }
        }
        else if (this.mysteryBoxType.isCraftedMysteryBox()) {
            final Iterator<String> iterator3 = this.mysteryBoxType.getLore().iterator();
            while (iterator3.hasNext()) {
                list.add(ChatUtil.format(iterator3.next().replace("{ITEM_ONE}", this.getDisplayName(this, 1)).replace("{ITEM_TWO}", this.getDisplayName(this, 2)).replace("{ITEM_THREE}", this.getDisplayName(this, 3)).replace("{ITEM_FOUR}", this.getDisplayName(this, 4)).replace("{ITEM_FIVE}", this.getDisplayName(this, 5)).replace("{ITEM_SIX}", this.getDisplayName(this, 6)).replace("{ITEM_SEVEN}", this.getDisplayName(this, 7)).replace("{DATE}", this.getDetails())));
            }
        }
        return list;
    }
    
    private String getDisplayName(final MysteryBoxes mysteryBoxes, final int n) {
        return mysteryBoxes.getLoots().get(n - 1).getDisplayNameStripColor();
    }
    
    public MysteryBoxesLoot open() {
        Rarity rarity = this.giveLoots();
        if (this.getCommonLoots().size() == 0) {
            rarity = this.giveLoots2();
        }
        if (rarity == Rarity.COMMON) {
            if (this.getCommonLoots().size() == 0) {
                return this.getLoots().get(CookieGadgets.random().nextInt(this.getLoots().size()));
            }
            return this.getCommonLoots().get(CookieGadgets.random().nextInt(this.getCommonLoots().size()));
        }
        else if (rarity == Rarity.RARE) {
            if (this.getRareLoots().size() == 0) {
                return this.getLoots().get(CookieGadgets.random().nextInt(this.getLoots().size()));
            }
            return this.getRareLoots().get(CookieGadgets.random().nextInt(this.getRareLoots().size()));
        }
        else if (rarity == Rarity.EPIC) {
            if (this.getEpicLoots().size() == 0) {
                return this.getLoots().get(CookieGadgets.random().nextInt(this.getLoots().size()));
            }
            return this.getEpicLoots().get(CookieGadgets.random().nextInt(this.getEpicLoots().size()));
        }
        else {
            if (rarity != Rarity.LEGENDARY) {
                return this.getLoots().get(CookieGadgets.random().nextInt(this.getLoots().size()));
            }
            if (this.getLegendaryLoots().size() == 0) {
                return this.getLoots().get(CookieGadgets.random().nextInt(this.getLoots().size()));
            }
            return this.getLegendaryLoots().get(CookieGadgets.random().nextInt(this.getLegendaryLoots().size()));
        }
    }
    
    private Rarity giveLoots() {
        final int chance = Rarity.COMMON.getChance(this.quality);
        final int chance2 = Rarity.RARE.getChance(this.quality);
        final int chance3 = Rarity.EPIC.getChance(this.quality);
        final int chance4 = Rarity.LEGENDARY.getChance(this.quality);
        final int nextInt = CookieGadgets.random().nextInt(chance + chance2 + chance3 + chance4);
        if (nextInt < chance) {
            return Rarity.COMMON;
        }
        if (nextInt >= chance && nextInt < chance + chance2) {
            return Rarity.RARE;
        }
        if (nextInt >= chance + chance2 && nextInt < chance + chance2 + chance3) {
            return Rarity.EPIC;
        }
        if (nextInt >= chance + chance2 + chance3 && nextInt <= chance + chance2 + chance3 + chance4) {
            return Rarity.LEGENDARY;
        }
        return null;
    }
    
    private Rarity giveLoots2() {
        final int chance = Rarity.RARE.getChance(this.quality);
        final int chance2 = Rarity.EPIC.getChance(this.quality);
        final int chance3 = Rarity.LEGENDARY.getChance(this.quality);
        final int nextInt = CookieGadgets.random().nextInt(chance + chance2 + chance3);
        if (nextInt < chance) {
            return Rarity.RARE;
        }
        if (nextInt >= chance && nextInt < chance + chance2) {
            return Rarity.EPIC;
        }
        if (nextInt >= chance + chance2 && nextInt <= chance + chance2 + chance3) {
            return Rarity.LEGENDARY;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.decodedString;
    }
    
    public String toEncodedString() {
        return this.encodedString;
    }
}
