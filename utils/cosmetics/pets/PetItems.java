

package ws.billy.CookieGadgets.utils.cosmetics.pets;

import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.List;
import ws.billy.CookieGadgets.utils.GMaterial;

public enum PetItems
{
    APPLE("Apple", "&bApple", new GMaterial(EnumMaterial.APPLE), PetItemType.FOOD, 30, 35, 40, 50, 0, Arrays.asList("&7A delicious fruit!")), 
    MELON("Melon", "&bMelon", new GMaterial(EnumMaterial.MELON), PetItemType.FOOD, 30, 35, 40, 50, 1, Arrays.asList("&7Contains seeds.")), 
    PUMPKIN_PIE("Pumpkin Pie", "&bPumpkin Pie", new GMaterial(EnumMaterial.PUMPKIN_PIE), PetItemType.FOOD, 30, 35, 40, 50, 2, Arrays.asList("&7Serving size: All of it.")), 
    CARROT("Carrot", "&bCarrot", new GMaterial(EnumMaterial.CARROT), PetItemType.FOOD, 30, 35, 40, 50, 3, Arrays.asList("&7Good for your eyesight!")), 
    BAKED_POTATO("Baked Potato", "&bBaked Potato", new GMaterial(EnumMaterial.BAKED_POTATO), PetItemType.FOOD, 30, 35, 40, 50, 4, Arrays.asList("&7Po-Tae-Toe Poe-Tah-Toe.")), 
    MUSHROOM_SOUP("Mushroom Soup", "&bMushroom Soup", new GMaterial(EnumMaterial.MUSHROOM_STEW), PetItemType.FOOD, 30, 35, 40, 50, 5, Arrays.asList("&7This is NOT a Mooshroom", "&7Stew!")), 
    FLOWER("Flower", "&bFlower", new GMaterial(EnumMaterial.RED_TULIP), PetItemType.FOOD, 30, 35, 40, 50, 6, Arrays.asList("&7Tastes as good as it", "&7smells!")), 
    HAY("Hay", "&bHay", new GMaterial(EnumMaterial.HAY_BLOCK), PetItemType.FOOD, 30, 35, 40, 50, 7, Arrays.asList("&7Haaaay there how's it", "&7goin'...")), 
    WHEAT("Wheat", "&bWheat", new GMaterial(EnumMaterial.WHEAT), PetItemType.FOOD, 30, 35, 40, 50, 8, Arrays.asList("&7Not yet processed.")), 
    BREAD("Bread", "&bBread", new GMaterial(EnumMaterial.BREAD), PetItemType.FOOD, 30, 35, 40, 50, 9, Arrays.asList("&7Freshly baked.")), 
    COOKIE("Cookie", "&bCookie", new GMaterial(EnumMaterial.COOKIE), PetItemType.FOOD, 30, 35, 40, 50, 10, Arrays.asList("&7With chocolate chips!")), 
    CAKE("Cake", "&bCake", new GMaterial(EnumMaterial.CAKE), PetItemType.FOOD, 30, 35, 40, 50, 11, Arrays.asList("&7This one is not a lie.")), 
    RAW_FISH("Raw Fish", "&bRaw Fish", new GMaterial(EnumMaterial.COD), PetItemType.FOOD, 30, 35, 40, 50, 12, Arrays.asList("&7Caught by the local", "&7fisherman.")), 
    RAW_PORKCHOP("Raw Porkchop", "&bRaw Porkchop", new GMaterial(EnumMaterial.PORKCHOP), PetItemType.FOOD, 30, 35, 40, 50, 13, Arrays.asList("&7For those who like raw", "&7meat!")), 
    ANGUS_STEAK("Angus Steak", "&bAngus Steak", new GMaterial(EnumMaterial.COOKED_BEEF), PetItemType.FOOD, 30, 35, 40, 50, 14, Arrays.asList("&7Well-prepared and seasoned.")), 
    BONE("Bone", "&bBone", new GMaterial(EnumMaterial.BONE), PetItemType.FOOD, 30, 35, 40, 50, 15, Arrays.asList("&7Dug out of the ground.")), 
    ROTTEN_FLESH("Rotten Flesh", "&bRotten Flesh", new GMaterial(EnumMaterial.ROTTEN_FLESH), PetItemType.FOOD, 30, 35, 40, 50, 16, Arrays.asList("&7Eeewwww!")), 
    MAGMA_CREAM("Magma Cream", "&bMagma Cream", new GMaterial(EnumMaterial.MAGMA_CREAM), PetItemType.FOOD, 30, 35, 40, 50, 17, Arrays.asList("&7WARNING: Intense heat!")), 
    WATER("Water", "&bWater", new GMaterial(EnumMaterial.WATER_BUCKET), PetItemType.DRINK, 30, 35, 40, 50, 27, Arrays.asList("&7Purified.")), 
    MILK("Milk", "&bMilk", new GMaterial(EnumMaterial.MILK_BUCKET), PetItemType.DRINK, 30, 35, 40, 50, 28, Arrays.asList("&7Whole milk.")), 
    LAVA("Lava", "&bLava", new GMaterial(EnumMaterial.LAVA_BUCKET), PetItemType.DRINK, 30, 35, 40, 50, 29, Arrays.asList("&7Very hot!")), 
    STICK("Stick", "&bStick", new GMaterial(EnumMaterial.STICK), PetItemType.TOY, 30, 35, 40, 50, 33, Arrays.asList("&7Wooden Stick.")), 
    BALL("Ball", "&bBall", new GMaterial(EnumMaterial.SLIME_BALL), PetItemType.TOY, 30, 35, 40, 50, 34, Arrays.asList("&7Bounces and rolls!")), 
    LEASH("Leash", "&bLeash", new GMaterial(EnumMaterial.LEAD), PetItemType.TOY, 30, 35, 40, 50, 35, Arrays.asList("&7Let's go for a walk!")), 
    FEATHER("Feather", "&bFeather", new GMaterial(EnumMaterial.FEATHER), PetItemType.TOY, 30, 35, 40, 50, 42, Arrays.asList("&7Tickle tickle!")), 
    FRISBEE("Frisbee", "&bFrisbee", new GMaterial(EnumMaterial.MUSIC_DISC_13), PetItemType.TOY, 30, 35, 40, 50, 43, Arrays.asList("&7FETCH!")), 
    SPARRING_SWORD("Sparring Sword", "&bSparring Sword", new GMaterial(EnumMaterial.WOODEN_SWORD), PetItemType.TOY, 30, 35, 40, 50, 44, Arrays.asList("&7En garde!"));
    
    private String name;
    private String displayName;
    private GMaterial material;
    private PetItemType itemType;
    private int dislikeIncrement;
    private int likeIncrement;
    private int reallyLikeIncrement;
    private int favouriteIncrement;
    private int slot;
    private List<String> lore;
    
    private PetItems(final String name2, final String displayName, final GMaterial material, final PetItemType itemType, final int n, final int n2, final int n3, final int n4, final int slot, final List<String> lore) {
        this.name = name2;
        final String replace = this.name.replace(" ", "-");
        if (FileManager.getPetSystemFile().get("Pet-Items." + replace + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getPetSystemFile().getString("Pet-Items." + replace + ".Name");
        }
        if (FileManager.getPetSystemFile().get("Pet-Items." + replace + ".Material") == null) {
            this.material = material;
            FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Material", this.material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getPetSystemFile().getString("Pet-Items." + replace + ".Material"));
        }
        this.itemType = itemType;
        if (FileManager.getPetSystemFile().get("Pet-Items." + replace + ".Increment") == null) {
            this.dislikeIncrement = n;
            this.likeIncrement = n2;
            this.reallyLikeIncrement = n3;
            this.favouriteIncrement = n4;
            FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Increment." + PetItemInterest.DISLIKE.getName(), this.dislikeIncrement);
            FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Increment." + PetItemInterest.LIKE.getName(), this.likeIncrement);
            FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Increment." + PetItemInterest.REALLY_LIKE.getName().replace(" ", "-"), this.reallyLikeIncrement);
            FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Increment." + PetItemInterest.FAVOURITE.getName(), this.favouriteIncrement);
        }
        else {
            PetItemInterest[] values;
            for (int length = (values = PetItemInterest.values()).length, i = 0; i < length; ++i) {
                final PetItemInterest petItemInterest = values[i];
                final String replace2 = petItemInterest.getName().replace(" ", "-");
                if (FileManager.getPetSystemFile().get("Pet-Items." + replace + ".Increment." + replace2) != null) {
                    if (petItemInterest == PetItemInterest.DISLIKE) {
                        this.dislikeIncrement = FileManager.getPetSystemFile().getInt("Pet-Items." + replace + ".Increment." + replace2);
                    }
                    if (petItemInterest == PetItemInterest.LIKE) {
                        this.likeIncrement = FileManager.getPetSystemFile().getInt("Pet-Items." + replace + ".Increment." + replace2);
                    }
                    if (petItemInterest == PetItemInterest.REALLY_LIKE) {
                        this.reallyLikeIncrement = FileManager.getPetSystemFile().getInt("Pet-Items." + replace + ".Increment." + replace2);
                    }
                    if (petItemInterest == PetItemInterest.FAVOURITE) {
                        this.favouriteIncrement = FileManager.getPetSystemFile().getInt("Pet-Items." + replace + ".Increment." + replace2);
                    }
                }
                else {
                    if (petItemInterest == PetItemInterest.DISLIKE) {
                        this.dislikeIncrement = n;
                        FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Increment." + replace2, this.dislikeIncrement);
                    }
                    if (petItemInterest == PetItemInterest.LIKE) {
                        this.likeIncrement = n2;
                        FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Increment." + replace2, this.likeIncrement);
                    }
                    if (petItemInterest == PetItemInterest.REALLY_LIKE) {
                        this.reallyLikeIncrement = n3;
                        FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Increment." + replace2, this.reallyLikeIncrement);
                    }
                    if (petItemInterest == PetItemInterest.FAVOURITE) {
                        this.favouriteIncrement = n4;
                        FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Increment." + replace2, this.favouriteIncrement);
                    }
                }
            }
        }
        if (FileManager.getPetSystemFile().get("Pet-Items." + replace + ".Slot") == null) {
            this.slot = slot;
            FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Slot", this.slot);
        }
        else {
            this.slot = FileManager.getPetSystemFile().getInt("Pet-Items." + replace + ".Slot");
        }
        if (FileManager.getPetSystemFile().get("Pet-Items." + replace + ".Lore") == null) {
            if ((this.lore = lore) == null) {
                FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Lore", "");
            }
            else {
                FileManager.getPetSystemFile().set("Pet-Items." + replace + ".Lore", this.lore);
            }
        }
        else {
            this.lore = FileManager.getPetSystemFile().getStringList("Pet-Items." + replace + ".Lore");
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSQLIndex() {
        return this.name.replace(" ", "_");
    }
    
    public String getDisplayName() {
        return ChatUtil.format(this.displayName);
    }
    
    public String getDisplayNameStripColor() {
        return ChatUtil.stripColor(this.displayName);
    }
    
    public GMaterial getMaterial() {
        return this.material;
    }
    
    public int getDislikeIncrement() {
        return this.dislikeIncrement;
    }
    
    public int getLikeIncrement() {
        return this.likeIncrement;
    }
    
    public int getReallyLikeIncrement() {
        return this.reallyLikeIncrement;
    }
    
    public int getFavouriteIncrement() {
        return this.favouriteIncrement;
    }
    
    public int getIncrementByInterest(final PetItemInterest petItemInterest) {
        if (petItemInterest == PetItemInterest.DISLIKE) {
            return this.dislikeIncrement;
        }
        if (petItemInterest == PetItemInterest.LIKE) {
            return this.likeIncrement;
        }
        if (petItemInterest == PetItemInterest.REALLY_LIKE) {
            return this.reallyLikeIncrement;
        }
        if (petItemInterest == PetItemInterest.FAVOURITE) {
            return this.favouriteIncrement;
        }
        return this.likeIncrement;
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    public List<String> getLore() {
        return this.lore;
    }
    
    public PetItemType getItemType() {
        return this.itemType;
    }
    
    public static PetItems getByName(final String s) {
        try {
            PetItems[] values;
            for (int length = (values = values()).length, i = 0; i < length; ++i) {
                final PetItems petItems = values[i];
                if (petItems.getName().toLowerCase().equals(s.toLowerCase())) {
                    return petItems;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static PetItems getRandomPetItems() {
        return values()[CookieGadgets.random().nextInt(values().length)];
    }
    
    public enum PetItemType
    {
        FOOD("Hunger", "Hunger", Arrays.asList("", "&7Increases a pet's Hunger", "&7rating."), Arrays.asList("", "&7Remaining: &f{REMAIN}", "&eClick to give to pet!")), 
        DRINK("Thirst", "Thirst", Arrays.asList("", "&7Increases a pet's Thirst", "&7rating."), Arrays.asList("", "&7Remaining: &f{REMAIN}", "&eClick to give to pet!")), 
        TOY("Exercise", "Exercise", Arrays.asList("", "&7Increases a pet's Exercise", "&7rating."), Arrays.asList("", "&7Remaining: &f{REMAIN}", "&eClick to give to pet!"));
        
        private String name;
        private String displayName;
        private List<String> petItemLore;
        private List<String> itemRemainLore;
        private boolean isPlaySoundEnabled;
        private SoundEffect clickSound;
        private SoundEffect feedSound;
        private SoundEffect fulledSound;
        
        private PetItemType(final String name2, final String displayName, final List<String> petItemLore, final List<String> itemRemainLore) {
            this.name = name2;
            if (FileManager.getPetSystemFile().get("Pet-System.Stats." + this.name + ".Name") == null) {
                this.displayName = displayName;
                FileManager.getPetSystemFile().set("Pet-System.Stats." + this.name + ".Name", this.displayName);
            }
            else {
                this.displayName = FileManager.getPetSystemFile().getString("Pet-System.Stats." + this.name + ".Name");
            }
            if (FileManager.getPetSystemFile().get("Pet-System.Stats." + this.name + ".Play-Sound") == null || FileManager.getPetSystemFile().get("Pet-System.Stats." + this.name + ".Play-Sound.Enabled") == null) {
                this.isPlaySoundEnabled = true;
                FileManager.getPetSystemFile().set("Pet-System.Stats." + this.name + ".Play-Sound.Enabled", true);
            }
            else {
                this.isPlaySoundEnabled = FileManager.getPetSystemFile().getBoolean("Pet-System.Stats." + this.name + ".Play-Sound.Enabled");
            }
            if (FileManager.getPetSystemFile().get("Pet-System.Stats." + this.name + ".Play-Sound") == null || FileManager.getPetSystemFile().get("Pet-System.Stats." + this.name + ".Play-Sound.Click-Sound") == null) {
                this.clickSound = SoundEffect.UI_BUTTON_CLICK;
                FileManager.getPetSystemFile().set("Pet-System.Stats." + this.name + ".Play-Sound.Click-Sound", this.clickSound.getName());
            }
            else {
                this.clickSound = SoundEffect.valueOfSound(FileManager.getPetSystemFile().getString("Pet-System.Stats." + this.name + ".Play-Sound.Click-Sound"));
            }
            if (FileManager.getPetSystemFile().get("Pet-System.Stats." + this.name + ".Play-Sound") == null || FileManager.getPetSystemFile().get("Pet-System.Stats." + this.name + ".Play-Sound.Feed-Sound") == null) {
                this.feedSound = SoundEffect.ENTITY_VILLAGER_AMBIENT;
                FileManager.getPetSystemFile().set("Pet-System.Stats." + this.name + ".Play-Sound.Feed-Sound", this.feedSound.getName());
            }
            else {
                this.feedSound = SoundEffect.valueOfSound(FileManager.getPetSystemFile().getString("Pet-System.Stats." + this.name + ".Play-Sound.Feed-Sound"));
            }
            if (FileManager.getPetSystemFile().get("Pet-System.Stats." + this.name + ".Play-Sound") == null || FileManager.getPetSystemFile().get("Pet-System.Stats." + this.name + ".Play-Sound.Fulled-Sound") == null) {
                this.fulledSound = SoundEffect.BLOCK_NOTE_BLOCK_BELL;
                FileManager.getPetSystemFile().set("Pet-System.Stats." + this.name + ".Play-Sound.Fulled-Sound", this.feedSound.getName());
            }
            else {
                this.fulledSound = SoundEffect.valueOfSound(FileManager.getPetSystemFile().getString("Pet-System.Stats." + this.name + ".Play-Sound.Fulled-Sound"));
            }
            if (FileManager.getPetSystemFile().get("Pet-System.Stats." + this.name + ".Lore.Pet-Item") == null) {
                this.petItemLore = petItemLore;
                FileManager.getPetSystemFile().set("Pet-System.Stats." + this.name + ".Lore.Pet-Item", this.petItemLore);
            }
            else {
                this.petItemLore = FileManager.getPetSystemFile().getStringList("Pet-System.Stats." + this.name + ".Lore.Pet-Item");
            }
            if (FileManager.getPetSystemFile().get("Pet-System.Stats." + this.name + ".Lore.Item-Remain") == null) {
                this.itemRemainLore = itemRemainLore;
                FileManager.getPetSystemFile().set("Pet-System.Stats." + this.name + ".Lore.Item-Remain", this.itemRemainLore);
            }
            else {
                this.itemRemainLore = FileManager.getPetSystemFile().getStringList("Pet-System.Stats." + this.name + ".Lore.Item-Remain");
            }
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
        
        public boolean isPlaySoundEnabled() {
            return this.isPlaySoundEnabled;
        }
        
        public SoundEffect getClickSound() {
            return this.clickSound;
        }
        
        public SoundEffect getFeedSound() {
            return this.feedSound;
        }
        
        public SoundEffect getFulledSound() {
            return this.fulledSound;
        }
        
        public List<String> getPetItemLore() {
            return this.petItemLore;
        }
        
        public List<String> getItemRemainLore() {
            return this.itemRemainLore;
        }
    }
}
