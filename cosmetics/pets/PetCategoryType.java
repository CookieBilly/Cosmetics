

package ws.billy.CookieGadgets.cosmetics.pets;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;

public class PetCategoryType
{
    private static final List<PetCategoryType> ENABLED;
    private static final List<PetCategoryType> VALUES;
    public static final PetCategoryType SILVERFISH;
    public static final PetCategoryType CHICKEN;
    public static final PetCategoryType WOLF;
    public static final PetCategoryType CAT;
    public static final PetCategoryType ZOMBIE;
    public static final PetCategoryType BAT;
    public static final PetCategoryType SPIDER;
    public static final PetCategoryType SNOWMAN;
    public static final PetCategoryType RABBIT;
    public static final PetCategoryType VILLAGER;
    public static final PetCategoryType GOLEM;
    public static final PetCategoryType ENDERMAN;
    public static final PetCategoryType BLAZE;
    public static final PetCategoryType ENDERMITE;
    public static final PetCategoryType COW;
    public static final PetCategoryType CREEPER;
    public static final PetCategoryType HORSE;
    public static final PetCategoryType PIG;
    public static final PetCategoryType SHEEP;
    public static final PetCategoryType SLIME;
    public static final PetCategoryType SKELETON;
    public static final PetCategoryType SQUID;
    public static final PetCategoryType POLAR_BEAR;
    public static final PetCategoryType LLAMA;
    public static final PetCategoryType PANDA;
    private String name;
    private String displayName;
    private GMaterial material;
    private String GUIName;
    private List<String> permission;
    private List<String> lore;
    private boolean isEnable;
    
    static {
        ENABLED = new ArrayList<PetCategoryType>();
        VALUES = new ArrayList<PetCategoryType>();
        SILVERFISH = new PetCategoryType("Silverfish", "&aSilverfish", new GMaterial(EnumMaterial.INFESTED_STONE), Arrays.asList("CookieGadgets.pets.silverfish"), Arrays.asList("&7Click here to see all pets", "&7in the Silverfish category!"));
        CHICKEN = new PetCategoryType("Chicken", "&9Chicken", new GMaterial(EnumMaterial.EGG), Arrays.asList("CookieGadgets.pets.chicken", "CookieGadgets.pets.babychicken"), Arrays.asList("&7Click here to see all pets", "&7in the Chicken category!"));
        WOLF = new PetCategoryType("Wolf", "&9Wolf", new GMaterial(EnumMaterial.BONE), Arrays.asList("CookieGadgets.pets.wolf", "CookieGadgets.pets.babywolf"), Arrays.asList("&7Click here to see all pets", "&7in the Wolf category!"));
        CAT = new PetCategoryType("Cat", "&5Cat", new GMaterial(EnumMaterial.COD), Arrays.asList("CookieGadgets.pets.blackcat", "CookieGadgets.pets.babyblackcat", "CookieGadgets.pets.redcat", "CookieGadgets.pets.babyredcat", "CookieGadgets.pets.siamesecat", "CookieGadgets.pets.babysiamesecat", "CookieGadgets.pets.wildocelot", "CookieGadgets.pets.babywildocelot"), Arrays.asList("&7Click here to see all pets", "&7in the Cat category!"));
        ZOMBIE = new PetCategoryType("Zombie", "&5Zombie", new GMaterial(EnumMaterial.ZOMBIE_HEAD), Arrays.asList("CookieGadgets.pets.zombie", "CookieGadgets.pets.babyzombie", "CookieGadgets.pets.husk", "CookieGadgets.pets.babyhusk", "CookieGadgets.pets.redlittlehelper", "CookieGadgets.pets.greenlittlehelper"), Arrays.asList("&7Click here to see all pets", "&7in the Zombie category!"));
        BAT = new PetCategoryType("Bat", "&5Bat", new GMaterial(EnumMaterial.COAL), Arrays.asList("CookieGadgets.pets.bat"), Arrays.asList("&7Click here to see all pets", "&7in the Bat category!"));
        SPIDER = new PetCategoryType("Spider", "&5Spider", new GMaterial(EnumMaterial.SPIDER_EYE), Arrays.asList("CookieGadgets.pets.spider", "CookieGadgets.pets.cavespider"), Arrays.asList("&7Click here to see all pets", "&7in the Spider category!"));
        SNOWMAN = new PetCategoryType("Snowman", "&5Snowman", new GMaterial(EnumMaterial.SNOWBALL), Arrays.asList("CookieGadgets.pets.snowman"), Arrays.asList("&7Click here to see all pets", "&7in the Snowman category!"));
        RABBIT = new PetCategoryType("Rabbit", "&6Rabbit", new GMaterial(EnumMaterial.CARROT), Arrays.asList("CookieGadgets.pets.blackrabbit", "CookieGadgets.pets.babyblackrabbit", "CookieGadgets.pets.blackandwhiterabbit", "CookieGadgets.pets.babyblackandwhiterabbit", "CookieGadgets.pets.brownrabbit", "CookieGadgets.pets.babybrownrabbit", "CookieGadgets.pets.goldrabbit", "CookieGadgets.pets.babygoldrabbit", "CookieGadgets.pets.saltandpepperrabbit", "CookieGadgets.pets.babysaltandpepperrabbit", "CookieGadgets.pets.whiterabbit", "CookieGadgets.pets.babywhiterabbit"), Arrays.asList("&7Click here to see all pets", "&7in the Rabbit category!"));
        VILLAGER = new PetCategoryType("Villager", "&6Villager", new GMaterial(EnumMaterial.EMERALD), Arrays.asList("CookieGadgets.pets.blacksmithvillager", "CookieGadgets.pets.babyblacksmithvillager", "CookieGadgets.pets.butchervillager", "CookieGadgets.pets.babybutchervillager", "CookieGadgets.pets.farmervillager", "CookieGadgets.pets.babyfarmervillager", "CookieGadgets.pets.librarianvillager", "CookieGadgets.pets.babylibrarianvillager", "CookieGadgets.pets.priestvillager", "CookieGadgets.pets.babypriestvillager", "CookieGadgets.pets.zombievillager", "CookieGadgets.pets.babyzombievillager", "CookieGadgets.pets.witch", "CookieGadgets.pets.evoker", "CookieGadgets.pets.vindicator", "CookieGadgets.pets.illusioner"), Arrays.asList("&7Click here to see all pets", "&7in the Villager category!"));
        GOLEM = new PetCategoryType("Golem", "&6Golem", new GMaterial("head:89091d79ea0f59ef7ef94d7bba6e5f17f2f7d4572c44f90f76c4819a714"), Arrays.asList("CookieGadgets.pets.golem"), Arrays.asList("&7Click here to see all pets", "&7in the Golem category!"));
        ENDERMAN = new PetCategoryType("Enderman", "&6Enderman", new GMaterial("head:7a59bb0a7a32965b3d90d8eafa899d1835f424509eadd4e6b709ada50b9cf"), Arrays.asList("CookieGadgets.pets.enderman"), Arrays.asList("&7Click here to see all pets", "&7in the Enderman category!"));
        BLAZE = new PetCategoryType("Blaze", "&6Blaze", new GMaterial(EnumMaterial.BLAZE_ROD), Arrays.asList("CookieGadgets.pets.blaze"), Arrays.asList("&7Click here to see all pets", "&7in the Blaze category!"));
        ENDERMITE = new PetCategoryType("Endermite", "&6Endermite", new GMaterial(EnumMaterial.ENDERMITE_SPAWN_EGG), Arrays.asList("CookieGadgets.pets.endermite"), Arrays.asList("&7Click here to see all pets", "&7in the Endermite category!"));
        COW = new PetCategoryType("Cow", "&6Cow", new GMaterial(EnumMaterial.LEATHER), Arrays.asList("CookieGadgets.pets.cow", "CookieGadgets.pets.babycow", "CookieGadgets.pets.mushroomcow", "CookieGadgets.pets.babymushroomcow"), Arrays.asList("&7Click here to see all pets", "&7in the Cow category!"));
        CREEPER = new PetCategoryType("Creeper", "&6Creeper", new GMaterial(EnumMaterial.CREEPER_HEAD), Arrays.asList("CookieGadgets.pets.creeper", "CookieGadgets.pets.poweredcreeper"), Arrays.asList("&7Click here to see all pets", "&7in the Creeper category!"));
        HORSE = new PetCategoryType("Horse", "&6Horse", new GMaterial(EnumMaterial.SADDLE), Arrays.asList("CookieGadgets.pets.blackhorse", "CookieGadgets.pets.babyblackhorse", "CookieGadgets.pets.brownhorse", "CookieGadgets.pets.babybrownhorse", "CookieGadgets.pets.chestnuthorse", "CookieGadgets.pets.babychestnuthorse", "CookieGadgets.pets.creamyhorse", "CookieGadgets.pets.babycreamyhorse", "CookieGadgets.pets.darkbrownhorse", "CookieGadgets.pets.babydarkbrownhorse", "CookieGadgets.pets.grayhorse", "CookieGadgets.pets.babygrayhorse", "CookieGadgets.pets.whitehorse", "CookieGadgets.pets.babywhitehorse", "CookieGadgets.pets.donkey", "CookieGadgets.pets.babydonkey", "CookieGadgets.pets.mule", "CookieGadgets.pets.babymule", "CookieGadgets.pets.skeletonhorse", "CookieGadgets.pets.babyskeletonhorse", "CookieGadgets.pets.undeadhorse", "CookieGadgets.pets.babyundeadhorse"), Arrays.asList("&7Click here to see all pets", "&7in the Horse category!"));
        PIG = new PetCategoryType("Pig", "&6Pig", new GMaterial(EnumMaterial.PORKCHOP), Arrays.asList("CookieGadgets.pets.pig", "CookieGadgets.pets.babypig", "CookieGadgets.pets.pigzombie", "CookieGadgets.pets.babypigzombie"), Arrays.asList("&7Click here to see all pets", "&7in the Pig category!"));
        SHEEP = new PetCategoryType("Sheep", "&6Sheep", new GMaterial(EnumMaterial.WHITE_WOOL), Arrays.asList("CookieGadgets.pets.blacksheep", "CookieGadgets.pets.babyblacksheep", "CookieGadgets.pets.bluesheep", "CookieGadgets.pets.babybluesheep", "CookieGadgets.pets.brownsheep", "CookieGadgets.pets.babybrownsheep", "CookieGadgets.pets.cyansheep", "CookieGadgets.pets.babycyansheep", "CookieGadgets.pets.graysheep", "CookieGadgets.pets.babygraysheep", "CookieGadgets.pets.greensheep", "CookieGadgets.pets.babygreensheep", "CookieGadgets.pets.lightbluesheep", "CookieGadgets.pets.babylightbluesheep", "CookieGadgets.pets.limesheep", "CookieGadgets.pets.babylimesheep", "CookieGadgets.pets.magentasheep", "CookieGadgets.pets.babymagentasheep", "CookieGadgets.pets.orangesheep", "CookieGadgets.pets.babyorangesheep", "CookieGadgets.pets.pinksheep", "CookieGadgets.pets.babypinksheep", "CookieGadgets.pets.purplesheep", "CookieGadgets.pets.babypurplesheep", "CookieGadgets.pets.redsheep", "CookieGadgets.pets.babyredsheep", "CookieGadgets.pets.silversheep", "CookieGadgets.pets.babysilversheep", "CookieGadgets.pets.whitesheep", "CookieGadgets.pets.babywhitesheep", "CookieGadgets.pets.yellowsheep", "CookieGadgets.pets.babyyellowsheep", "CookieGadgets.pets.rainbowsheep"), Arrays.asList("&7Click here to see all pets", "&7in the Sheep category!"));
        SLIME = new PetCategoryType("Slime", "&6Slime", new GMaterial(EnumMaterial.SLIME_BLOCK), Arrays.asList("CookieGadgets.pets.bigslime", "CookieGadgets.pets.smallslime", "CookieGadgets.pets.tinyslime", "CookieGadgets.pets.bigmagmacube", "CookieGadgets.pets.smallmagmacube", "CookieGadgets.pets.tinymagmacube"), Arrays.asList("&7Click here to see all pets", "&7in the Slime category!"));
        SKELETON = new PetCategoryType("Skeleton", "&6Skeleton", new GMaterial(EnumMaterial.BOW), Arrays.asList("CookieGadgets.pets.skeleton", "CookieGadgets.pets.witherskeleton", "CookieGadgets.pets.strayskeleton"), Arrays.asList("&7Click here to see all pets", "&7in the Skeleton category!"));
        SQUID = new PetCategoryType("Squid", "&6Squid", new GMaterial(EnumMaterial.INK_SAC), Arrays.asList("CookieGadgets.pets.squid"), Arrays.asList("&7Click here to see all pets", "&7in the Squid category!"));
        POLAR_BEAR = new PetCategoryType("Polar Bear", "&6Polar Bear", new GMaterial("head:bab178f5cdd750f0e356860aa5539153eb2abec1e146ca57c65d25a5df8fdfe"), Arrays.asList("CookieGadgets.pets.polarbear", "CookieGadgets.pets.babypolarbear"), Arrays.asList("&7Click here to see all pets", "&7in the Polar Bear category!"));
        LLAMA = new PetCategoryType("Llama", "&6Llama", new GMaterial("head:83d9b5915912ffc2b85761d6adcb428a812f9b83ff634e331162ce46c99e9"), Arrays.asList("CookieGadgets.pets.brownllama", "CookieGadgets.pets.babybrownllama", "CookieGadgets.pets.creamyllama", "CookieGadgets.pets.babycreamyllama", "CookieGadgets.pets.grayllama", "CookieGadgets.pets.babygrayllama", "CookieGadgets.pets.whitellama", "CookieGadgets.pets.babywhitellama"), Arrays.asList("&7Click here to see all pets", "&7in the Llama category!"));
        PANDA = new PetCategoryType("Panda", "&6Panda", new GMaterial("head:dca096eea506301bea6d4b17ee1605625a6f5082c71f74a639cc940439f47166"), Arrays.asList("CookieGadgets.pets.normalpanda", "CookieGadgets.pets.babynormalpanda", "CookieGadgets.pets.lazypanda", "CookieGadgets.pets.babylazypanda", "CookieGadgets.pets.worriedpanda", "CookieGadgets.pets.babyworriedpanda", "CookieGadgets.pets.playfulpanda", "CookieGadgets.pets.babyplayfulpanda", "CookieGadgets.pets.brownpanda", "CookieGadgets.pets.babybrownpanda", "CookieGadgets.pets.weakpanda", "CookieGadgets.pets.babyweakpanda", "CookieGadgets.pets.aggressivepanda", "CookieGadgets.pets.babyaggressivepanda"), Arrays.asList("&7Click here to see all pets", "&7in the Panda category!"));
    }
    
    private PetCategoryType(final String name, final String displayName, final GMaterial material, final List<String> permission, final List<String> lore) {
        this.name = name;
        if (FileManager.getPetsFile().get("Pets." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getPetsFile().set("Pets." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getPetsFile().getString("Pets." + this.name + ".Name");
        }
        if (FileManager.getPetsFile().get("Pets." + this.name + ".Material") == null) {
            this.material = material;
            FileManager.getPetsFile().set("Pets." + this.name + ".Material", material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getPetsFile().getString("Pets." + this.name + ".Material"));
        }
        if (FileManager.getPetsFile().get("Pets." + this.name + ".GUI-Name") == null) {
            this.GUIName = ChatUtil.stripColor(this.displayName);
            FileManager.getPetsFile().set("Pets." + this.name + ".GUI-Name", this.GUIName);
        }
        else {
            this.GUIName = FileManager.getPetsFile().getString("Pets." + this.name + ".GUI-Name");
        }
        this.permission = permission;
        if (FileManager.getPetsFile().get("Pets." + this.name + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getPetsFile().set("Pets." + this.name + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getPetsFile().getBoolean("Pets." + this.name + ".Enabled");
        }
        if (FileManager.getPetsFile().get("Pets." + this.name + ".Lore") == null) {
            if ((this.lore = lore) == null) {
                FileManager.getPetsFile().set("Pets." + this.name + ".Lore", "");
            }
            else {
                FileManager.getPetsFile().set("Pets." + this.name + ".Lore", this.lore);
            }
        }
        else {
            this.lore = FileManager.getPetsFile().getStringList("Pets." + this.name + ".Lore");
        }
        if (!PetCategoryType.VALUES.contains(this)) {
            PetCategoryType.VALUES.add(this);
        }
        if (this.name == "Polar Bear" && !VersionManager.is1_10OrAbove() && PetCategoryType.VALUES.contains(this)) {
            PetCategoryType.VALUES.remove(this);
        }
        if (this.name == "Llama" && !VersionManager.is1_11OrAbove() && PetCategoryType.VALUES.contains(this)) {
            PetCategoryType.VALUES.remove(this);
        }
        if (this.name == "Panda" && !VersionManager.is1_14OrAbove() && PetCategoryType.VALUES.contains(this)) {
            PetCategoryType.VALUES.remove(this);
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
    
    public GMaterial getMaterial() {
        return this.material;
    }
    
    public String getGUIName() {
        return ChatUtil.format(this.GUIName);
    }
    
    public List<String> getPermission() {
        return this.permission;
    }
    
    public List<String> getLore() {
        return this.lore;
    }
    
    public boolean isEnabled() {
        return this.isEnable;
    }
    
    public static List<PetCategoryType> enabled() {
        return PetCategoryType.ENABLED;
    }
    
    public static List<PetCategoryType> values() {
        return PetCategoryType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final PetCategoryType petCategoryType : values()) {
            if (petCategoryType.isEnabled() && !PetCategoryType.ENABLED.contains(petCategoryType)) {
                PetCategoryType.ENABLED.add(petCategoryType);
            }
        }
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static PetCategoryType valueOf(final String anotherString) {
        for (final PetCategoryType petCategoryType : values()) {
            if (petCategoryType.getName().equalsIgnoreCase(anotherString)) {
                return petCategoryType;
            }
        }
        return null;
    }
}
