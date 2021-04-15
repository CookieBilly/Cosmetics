

package ws.billy.CookieGadgets.cosmetics.pets;

import java.util.Collections;
import java.util.function.Function;
import java.util.Comparator;
import ws.billy.CookieGadgets.utils.EnumInventorySort;
import java.util.Set;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItemInterest;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import org.bukkit.Color;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntityType;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticMaterialType;

public class PetType extends CosmeticMaterialType
{
    private static final List<PetType> ENABLED;
    private static final List<PetType> VALUES;
    private static final HashMap<PetCategoryType, ArrayList<PetType>> PET_CATEGORIES;
    public static final PetType SILVERFISH;
    public static final PetType CHICKEN;
    public static final PetType BABY_CHICKEN;
    public static final PetType WOLF;
    public static final PetType BABY_WOLF;
    public static final PetType BLACK_CAT;
    public static final PetType BABY_BLACK_CAT;
    public static final PetType RED_CAT;
    public static final PetType BABY_RED_CAT;
    public static final PetType SIAMESE_CAT;
    public static final PetType BABY_SIAMESE_CAT;
    public static final PetType WILD_OCELOT;
    public static final PetType BABY_WILD_OCELOT;
    public static final PetType ZOMBIE;
    public static final PetType BABY_ZOMBIE;
    public static final PetType HUSK;
    public static final PetType BABY_HUSK;
    public static final PetType RED_LITTLE_HELPER;
    public static final PetType GREEN_LITTLE_HELPER;
    public static final PetType BAT;
    public static final PetType SPIDER;
    public static final PetType CAVE_SPIDER;
    public static final PetType SNOWMAN;
    public static final PetType BLACK_RABBIT;
    public static final PetType BABY_BLACK_RABBIT;
    public static final PetType BLACK_AND_WHITE_RABBIT;
    public static final PetType BABY_BLACK_AND_WHITE_RABBIT;
    public static final PetType BROWN_RABBIT;
    public static final PetType BABY_BROWN_RABBIT;
    public static final PetType GOLD_RABBIT;
    public static final PetType BABY_GOLD_RABBIT;
    public static final PetType SALT_AND_PEPPER_RABBIT;
    public static final PetType BABY_SALT_AND_PEPPER_RABBIT;
    public static final PetType WHITE_RABBIT;
    public static final PetType BABY_WHITE_RABBIT;
    public static final PetType BLACKSMITH_VILLAGER;
    public static final PetType BABY_BLACKSMITH_VILLAGER;
    public static final PetType BUTCHER_VILLAGER;
    public static final PetType BABY_BUTCHER_VILLAGER;
    public static final PetType FARMER_VILLAGER;
    public static final PetType BABY_FARMER_VILLAGER;
    public static final PetType LIBRARIAN_VILLAGER;
    public static final PetType BABY_LIBRARIAN_VILLAGER;
    public static final PetType PRIEST_VILLAGER;
    public static final PetType BABY_PRIEST_VILLAGER;
    public static final PetType ZOMBIE_VILLAGER;
    public static final PetType BABY_ZOMBIE_VILLAGER;
    public static final PetType WITCH;
    public static final PetType EVOKER;
    public static final PetType VINDICATOR;
    public static final PetType ILLUSIONER;
    public static final PetType GOLEM;
    public static final PetType ENDERMAN;
    public static final PetType BLAZE;
    public static final PetType ENDERMITE;
    public static final PetType COW;
    public static final PetType BABY_COW;
    public static final PetType MUSHROOM_COW;
    public static final PetType BABY_MUSHROOM_COW;
    public static final PetType CREEPER;
    public static final PetType POWERED_CREEPER;
    public static final PetType BLACK_HORSE;
    public static final PetType BABY_BLACK_HORSE;
    public static final PetType BROWN_HORSE;
    public static final PetType BABY_BROWN_HORSE;
    public static final PetType CHESTNUT_HORSE;
    public static final PetType BABY_CHESTNUT_HORSE;
    public static final PetType CREAMY_HORSE;
    public static final PetType BABY_CREAMY_HORSE;
    public static final PetType DARK_BROWN_HORSE;
    public static final PetType BABY_DARK_BROWN_HORSE;
    public static final PetType GRAY_HORSE;
    public static final PetType BABY_GRAY_HORSE;
    public static final PetType WHITE_HORSE;
    public static final PetType BABY_WHITE_HORSE;
    public static final PetType DONKEY;
    public static final PetType BABY_DONKEY;
    public static final PetType MULE;
    public static final PetType BABY_MULE;
    public static final PetType SKELETON_HORSE;
    public static final PetType BABY_SKELETON_HORSE;
    public static final PetType UNDEAD_HORSE;
    public static final PetType BABY_UNDEAD_HORSE;
    public static final PetType PIG;
    public static final PetType BABY_PIG;
    public static final PetType PIG_ZOMBIE;
    public static final PetType BABY_PIG_ZOMBIE;
    public static final PetType BLACK_SHEEP;
    public static final PetType BABY_BLACK_SHEEP;
    public static final PetType BLUE_SHEEP;
    public static final PetType BABY_BLUE_SHEEP;
    public static final PetType BROWN_SHEEP;
    public static final PetType BABY_BROWN_SHEEP;
    public static final PetType CYAN_SHEEP;
    public static final PetType BABY_CYAN_SHEEP;
    public static final PetType GRAY_SHEEP;
    public static final PetType BABY_GRAY_SHEEP;
    public static final PetType GREEN_SHEEP;
    public static final PetType BABY_GREEN_SHEEP;
    public static final PetType LIGHT_BLUE_SHEEP;
    public static final PetType BABY_LIGHT_BLUE_SHEEP;
    public static final PetType LIME_SHEEP;
    public static final PetType BABY_LIME_SHEEP;
    public static final PetType MAGENTA_SHEEP;
    public static final PetType BABY_MAGENTA_SHEEP;
    public static final PetType ORANGE_SHEEP;
    public static final PetType BABY_ORANGE_SHEEP;
    public static final PetType PINK_SHEEP;
    public static final PetType BABY_PINK_SHEEP;
    public static final PetType PURPLE_SHEEP;
    public static final PetType BABY_PURPLE_SHEEP;
    public static final PetType RED_SHEEP;
    public static final PetType BABY_RED_SHEEP;
    public static final PetType SILVER_SHEEP;
    public static final PetType BABY_SILVER_SHEEP;
    public static final PetType WHITE_SHEEP;
    public static final PetType BABY_WHITE_SHEEP;
    public static final PetType YELLOW_SHEEP;
    public static final PetType BABY_YELLOW_SHEEP;
    public static final PetType RAINBOW_SHEEP;
    public static final PetType SLIME_BIG;
    public static final PetType SLIME_SMALL;
    public static final PetType SLIME_TINY;
    public static final PetType MAGMA_CUBE_BIG;
    public static final PetType MAGMA_CUBE_SMALL;
    public static final PetType MAGMA_CUBE_TINY;
    public static final PetType SKELETON;
    public static final PetType WITHER_SKELETON;
    public static final PetType STRAY_SKELETON;
    public static final PetType SQUID;
    public static final PetType POLAR_BEAR;
    public static final PetType BABY_POLAR_BEAR;
    public static final PetType BROWN_LLAMA;
    public static final PetType BABY_BROWN_LLAMA;
    public static final PetType CREAMY_LLAMA;
    public static final PetType BABY_CREAMY_LLAMA;
    public static final PetType GRAY_LLAMA;
    public static final PetType BABY_GRAY_LLAMA;
    public static final PetType WHITE_LLAMA;
    public static final PetType BABY_WHITE_LLAMA;
    public static final PetType NORMAL_PANDA;
    public static final PetType BABY_NORMAL_PANDA;
    public static final PetType LAZY_PANDA;
    public static final PetType BABY_LAZY_PANDA;
    public static final PetType WORRIED_PANDA;
    public static final PetType BABY_WORRIED_PANDA;
    public static final PetType PLAYFUL_PANDA;
    public static final PetType BABY_PLAYFUL_PANDA;
    public static final PetType BROWN_PANDA;
    public static final PetType BABY_BROWN_PANDA;
    public static final PetType WEAK_PANDA;
    public static final PetType BABY_WEAK_PANDA;
    public static final PetType AGGRESSIVE_PANDA;
    public static final PetType BABY_AGGRESSIVE_PANDA;
    private PetCategoryType category;
    private String displayName;
    private GMaterial material;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private GEntityType entityType;
    private EntityPet clazz;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    private String defaultPetName;
    private List<String> dislike;
    private List<String> reallyLike;
    private List<String> favourite;
    
    static {
        ENABLED = new ArrayList<PetType>();
        VALUES = new ArrayList<PetType>();
        PET_CATEGORIES = new HashMap<PetCategoryType, ArrayList<PetType>>();
        SILVERFISH = new PetType(PetCategoryType.SILVERFISH, "Silverfish", "&aSilverfish", new GMaterial(EnumMaterial.INFESTED_STONE), "CookieGadgets.pets.silverfish", 12, Rarity.COMMON, null, GEntityType.SILVERFISH, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        CHICKEN = new PetType(PetCategoryType.CHICKEN, "Chicken", "&aChicken", new GMaterial(EnumMaterial.FEATHER), "CookieGadgets.pets.chicken", 10, Rarity.COMMON, null, GEntityType.CHICKEN, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MELON.getName(), PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.FLOWER.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_CHICKEN = new PetType(PetCategoryType.CHICKEN, "Baby Chicken", "&9Chicken (Baby)", new GMaterial(EnumMaterial.WHEAT_SEEDS), "CookieGadgets.pets.babychicken", 30, Rarity.RARE, null, GEntityType.BABY_CHICKEN, Arrays.asList(PetItems.FLOWER.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MELON.getName(), PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()));
        WOLF = new PetType(PetCategoryType.WOLF, "Wolf", "&aWolf", new GMaterial(EnumMaterial.BONE), "CookieGadgets.pets.wolf", 10, Rarity.COMMON, null, GEntityType.WOLF, Arrays.asList(PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.LAVA.getName(), PetItems.FRISBEE.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_WOLF = new PetType(PetCategoryType.WOLF, "Baby Wolf", "&9Wolf (Baby)", new GMaterial(EnumMaterial.BONE_MEAL), "CookieGadgets.pets.babywolf", 30, Rarity.RARE, null, GEntityType.BABY_WOLF, Arrays.asList(PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.BREAD.getName(), PetItems.CAKE.getName(), PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.FRISBEE.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.RAW_PORKCHOP.getName(), PetItems.BONE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        BLACK_CAT = new PetType(PetCategoryType.CAT, "Black Cat", "&aCat: Black", new GMaterial(EnumMaterial.COOKED_COD), "CookieGadgets.pets.blackcat", 12, Rarity.COMMON, null, GEntityType.BLACK_CAT, Arrays.asList(PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.COOKIE.getName(), PetItems.CAKE.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()));
        BABY_BLACK_CAT = new PetType(PetCategoryType.CAT, "Baby Black Cat", "&9Cat: Black (Baby)", new GMaterial(EnumMaterial.COOKED_COD), "CookieGadgets.pets.babyblackcat", 30, Rarity.RARE, null, GEntityType.BABY_BLACK_CAT, Arrays.asList(PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.COOKIE.getName(), PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        RED_CAT = new PetType(PetCategoryType.CAT, "Red Cat", "&aCat: Red", new GMaterial(EnumMaterial.COOKED_SALMON), "CookieGadgets.pets.redcat", 12, Rarity.COMMON, null, GEntityType.RED_CAT, Arrays.asList(PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.COOKIE.getName(), PetItems.CAKE.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()));
        BABY_RED_CAT = new PetType(PetCategoryType.CAT, "Baby Red Cat", "&9Cat: Red (Baby)", new GMaterial(EnumMaterial.COOKED_SALMON), "CookieGadgets.pets.babyredcat", 30, Rarity.RARE, null, GEntityType.BABY_RED_CAT, Arrays.asList(PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.COOKIE.getName(), PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        SIAMESE_CAT = new PetType(PetCategoryType.CAT, "Siamese Cat", "&aCat: Siamese", new GMaterial(EnumMaterial.TROPICAL_FISH), "CookieGadgets.pets.siamesecat", 12, Rarity.COMMON, null, GEntityType.SIAMESE_CAT, Arrays.asList(PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.COOKIE.getName(), PetItems.CAKE.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()));
        BABY_SIAMESE_CAT = new PetType(PetCategoryType.CAT, "Baby Siamese Cat", "&9Cat: Siamese (Baby)", new GMaterial(EnumMaterial.TROPICAL_FISH), "CookieGadgets.pets.babysiamesecat", 30, Rarity.RARE, null, GEntityType.BABY_SIAMESE_CAT, Arrays.asList(PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.COOKIE.getName(), PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        WILD_OCELOT = new PetType(PetCategoryType.CAT, "Wild Ocelot", "&9Wild Ocelot", new GMaterial(EnumMaterial.COD), "CookieGadgets.pets.wildocelot", 15, Rarity.RARE, null, GEntityType.WILD_OCELOT, Arrays.asList(PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.COOKIE.getName(), PetItems.CAKE.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()));
        BABY_WILD_OCELOT = new PetType(PetCategoryType.CAT, "Baby Wild Ocelot", "&5Wild Ocelot (Baby)", new GMaterial(EnumMaterial.COD), "CookieGadgets.pets.babywildocelot", 35, Rarity.EPIC, null, GEntityType.BABY_WILD_OCELOT, Arrays.asList(PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.COOKIE.getName(), PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        ZOMBIE = new PetType(PetCategoryType.ZOMBIE, "Zombie", "&aZombie", new GMaterial(EnumMaterial.ROTTEN_FLESH), "CookieGadgets.pets.zombie", 12, Rarity.COMMON, null, GEntityType.ZOMBIE, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.RAW_PORKCHOP.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()));
        BABY_ZOMBIE = new PetType(PetCategoryType.ZOMBIE, "Baby Zombie", "&9Zombie (Baby)", new GMaterial(EnumMaterial.POTATO), "CookieGadgets.pets.babyzombie", 50, Rarity.RARE, null, GEntityType.BABY_ZOMBIE, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.LAVA.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MILK.getName(), PetItems.SPARRING_SWORD.getName()));
        HUSK = new PetType(PetCategoryType.ZOMBIE, "Husk", "&9Zombie Husk", new GMaterial("head:d674c63c8db5f4ca628d69a3b1f8a36e29d8fd775e1a6bdb6cabb4be4db121"), "CookieGadgets.pets.husk", 45, Rarity.RARE, null, GEntityType.HUSK, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.RAW_PORKCHOP.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()));
        BABY_HUSK = new PetType(PetCategoryType.ZOMBIE, "Baby Husk", "&5Zombie Husk (Baby)", new GMaterial("head:d674c63c8db5f4ca628d69a3b1f8a36e29d8fd775e1a6bdb6cabb4be4db121"), "CookieGadgets.pets.babyhusk", 60, Rarity.EPIC, null, GEntityType.BABY_HUSK, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        RED_LITTLE_HELPER = new PetType(PetCategoryType.ZOMBIE, "Red Little Helper", "&5Red Little Helper", new GMaterial(EnumMaterial.LEATHER_HELMET, Color.RED), "CookieGadgets.pets.redlittlehelper", 50, Rarity.EPIC, null, GEntityType.RED_LITTLE_HELPER, Arrays.asList(PetItems.FLOWER.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.LAVA.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        GREEN_LITTLE_HELPER = new PetType(PetCategoryType.ZOMBIE, "Green Little Helper", "&5Green Little Helper", new GMaterial(EnumMaterial.LEATHER_HELMET, Color.LIME), "CookieGadgets.pets.greenlittlehelper", 30, Rarity.EPIC, null, GEntityType.GREEN_LITTLE_HELPER, Arrays.asList(PetItems.FLOWER.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.LAVA.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        BAT = new PetType(PetCategoryType.BAT, "Bat", "&5Bat", new GMaterial(EnumMaterial.COAL), "CookieGadgets.pets.bat", 37, Rarity.EPIC, null, GEntityType.BAT, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        SPIDER = new PetType(PetCategoryType.SPIDER, "Spider", "&5Spider", new GMaterial(EnumMaterial.SPIDER_EYE), "CookieGadgets.pets.spider", 42, Rarity.EPIC, null, GEntityType.SPIDER, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.MILK.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        CAVE_SPIDER = new PetType(PetCategoryType.SPIDER, "Cave Spider", "&9Cave Spider", new GMaterial(EnumMaterial.STRING), "CookieGadgets.pets.cavespider", 27, Rarity.RARE, null, GEntityType.CAVE_SPIDER, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.WATER.getName(), PetItems.SPARRING_SWORD.getName()));
        SNOWMAN = new PetType(PetCategoryType.SNOWMAN, "Snowman", "&5Snowman", new GMaterial(EnumMaterial.SNOWBALL), "CookieGadgets.pets.snowman", 52, Rarity.EPIC, null, GEntityType.SNOWMAN, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.PUMPKIN_PIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        BLACK_RABBIT = new PetType(PetCategoryType.RABBIT, "Black Rabbit", "&9Rabbit: Black", new GMaterial("head:72c58116a147d1a9a26269224a8be184fe8e5f3f3df9b61751369ad87382ec9"), "CookieGadgets.pets.blackrabbit", 22, Rarity.RARE, null, GEntityType.BLACK_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_BLACK_RABBIT = new PetType(PetCategoryType.RABBIT, "Baby Black Rabbit", "&5Rabbit: Black (Baby)", new GMaterial(EnumMaterial.CARROT), "CookieGadgets.pets.babyblackrabbit", 28, Rarity.EPIC, null, GEntityType.BABY_BLACK_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        BLACK_AND_WHITE_RABBIT = new PetType(PetCategoryType.RABBIT, "Black & White Rabbit", "&5Rabbit: Black & White", new GMaterial("head:cb8cff4b15b8ca37e25750f345718f289cb22c5b3ad22627a71223faccc"), "CookieGadgets.pets.blackandwhiterabbit", 45, Rarity.EPIC, null, GEntityType.BLACK_AND_WHITE_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_BLACK_AND_WHITE_RABBIT = new PetType(PetCategoryType.RABBIT, "Baby Black & White Rabbit", "&6Rabbit: Black & White (Baby)", new GMaterial(EnumMaterial.CARROT), "CookieGadgets.pets.babyblackandwhiterabbit", 51, Rarity.LEGENDARY, null, GEntityType.BABY_BLACK_AND_WHITE_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        BROWN_RABBIT = new PetType(PetCategoryType.RABBIT, "Brown Rabbit", "&9Rabbit: Brown", new GMaterial("head:7d1169b2694a6aba826360992365bcda5a10c89a3aa2b48c438531dd8685c3a7"), "CookieGadgets.pets.brownrabbit", 22, Rarity.RARE, null, GEntityType.BROWN_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_BROWN_RABBIT = new PetType(PetCategoryType.RABBIT, "Baby Brown Rabbit", "&5Rabbit: Brown (Baby)", new GMaterial(EnumMaterial.CARROT), "CookieGadgets.pets.babybrownrabbit", 28, Rarity.EPIC, null, GEntityType.BABY_BROWN_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        GOLD_RABBIT = new PetType(PetCategoryType.RABBIT, "Gold Rabbit", "&5Rabbit: Gold", new GMaterial("head:c977a3266bf3b9eaf17e5a02ea5fbb46801159863dd288b93e6c12c9cb"), "CookieGadgets.pets.goldrabbit", 45, Rarity.EPIC, null, GEntityType.GOLD_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_GOLD_RABBIT = new PetType(PetCategoryType.RABBIT, "Baby Gold Rabbit", "&6Rabbit: Gold (Baby)", new GMaterial(EnumMaterial.CARROT), "CookieGadgets.pets.babygoldrabbit", 51, Rarity.LEGENDARY, null, GEntityType.BABY_GOLD_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        SALT_AND_PEPPER_RABBIT = new PetType(PetCategoryType.RABBIT, "Salt & Pepper Rabbit", "&5Rabbit: Salt & Pepper", new GMaterial("head:dc29aebe803e5d4dc3b010c9dd56444f863b2d86a6a2afd4a6a721748ba4fa"), "CookieGadgets.pets.saltandpepperrabbit", 45, Rarity.EPIC, null, GEntityType.SALT_AND_PEPPER_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_SALT_AND_PEPPER_RABBIT = new PetType(PetCategoryType.RABBIT, "Baby Salt & Pepper Rabbit", "&6Rabbit: Salt & Pepper (Baby)", new GMaterial(EnumMaterial.CARROT), "CookieGadgets.pets.babysaltandpepperrabbit", 51, Rarity.LEGENDARY, null, GEntityType.BABY_SALT_AND_PEPPER_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        WHITE_RABBIT = new PetType(PetCategoryType.RABBIT, "White Rabbit", "&9Rabbit: White", new GMaterial("head:374d8298797e712bb1f75ad6ffa7734ac4237ea69be1db92f0e41115a2c170cf"), "CookieGadgets.pets.whiterabbit", 22, Rarity.RARE, null, GEntityType.WHITE_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_WHITE_RABBIT = new PetType(PetCategoryType.RABBIT, "Baby White Rabbit", "&5Rabbit: White (Baby)", new GMaterial(EnumMaterial.CARROT), "CookieGadgets.pets.babywhiterabbit", 28, Rarity.EPIC, null, GEntityType.BABY_WHITE_RABBIT, Arrays.asList(PetItems.BREAD.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CARROT.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        BLACKSMITH_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Blacksmith Villager", "&9Villager: Blacksmith", new GMaterial(EnumMaterial.IRON_ORE), "CookieGadgets.pets.blacksmithvillager", 22, Rarity.RARE, null, GEntityType.BLACKSMITH_VILLAGER, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.BREAD.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_BLACKSMITH_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Baby Blacksmith Villager", "&5Villager: Blacksmith (Baby)", new GMaterial(EnumMaterial.IRON_INGOT), "CookieGadgets.pets.babyblacksmithvillager", 55, Rarity.EPIC, null, GEntityType.BABY_BLACKSMITH_VILLAGER, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.BREAD.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()));
        BUTCHER_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Butcher Villager", "&9Villager: Butcher", new GMaterial(EnumMaterial.IRON_AXE), "CookieGadgets.pets.butchervillager", 22, Rarity.RARE, null, GEntityType.BUTCHER_VILLAGER, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.BREAD.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_BUTCHER_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Baby Butcher Villager", "&5Villager: Butcher (Baby)", new GMaterial(EnumMaterial.WOODEN_AXE), "CookieGadgets.pets.babybutchervillager", 55, Rarity.EPIC, null, GEntityType.BABY_BUTCHER_VILLAGER, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.BREAD.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()));
        FARMER_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Farmer Villager", "&9Villager: Farmer", new GMaterial(EnumMaterial.IRON_HOE), "CookieGadgets.pets.farmervillager", 22, Rarity.RARE, null, GEntityType.FARMER_VILLAGER, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.BREAD.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_FARMER_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Baby Farmer Villager", "&5Villager: Farmer (Baby)", new GMaterial(EnumMaterial.WOODEN_HOE), "CookieGadgets.pets.babyfarmervillager", 55, Rarity.EPIC, null, GEntityType.BABY_FARMER_VILLAGER, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.BREAD.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()));
        LIBRARIAN_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Librarian Villager", "&9Villager: Librarian", new GMaterial(EnumMaterial.BOOKSHELF), "CookieGadgets.pets.librarianvillager", 22, Rarity.RARE, null, GEntityType.LIBRARIAN_VILLAGER, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.BREAD.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_LIBRARIAN_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Baby Librarian Villager", "&5Villager: Librarian (Baby)", new GMaterial(EnumMaterial.BOOK), "CookieGadgets.pets.babylibrarianvillager", 55, Rarity.EPIC, null, GEntityType.BABY_LIBRARIAN_VILLAGER, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.BREAD.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()));
        PRIEST_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Priest Villager", "&9Villager: Priest", new GMaterial(EnumMaterial.WRITABLE_BOOK), "CookieGadgets.pets.priestvillager", 22, Rarity.RARE, null, GEntityType.PRIEST_VILLAGER, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.BREAD.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_PRIEST_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Baby Priest Villager", "&5Villager: Priest (Baby)", new GMaterial(EnumMaterial.MAP), "CookieGadgets.pets.babypriestvillager", 55, Rarity.EPIC, null, GEntityType.BABY_PRIEST_VILLAGER, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.BREAD.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()));
        ZOMBIE_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Zombie Villager", "&5Villager: Zombie", new GMaterial(EnumMaterial.GOLDEN_APPLE), "CookieGadgets.pets.zombievillager", 52, Rarity.EPIC, null, GEntityType.ZOMBIE_VILLAGER, Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.RAW_PORKCHOP.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.STICK.getName()));
        BABY_ZOMBIE_VILLAGER = new PetType(PetCategoryType.VILLAGER, "Baby Zombie Villager", "&6Villager: Zombie (Baby)", new GMaterial(EnumMaterial.GOLDEN_HELMET), "CookieGadgets.pets.babyzombievillager", 75, Rarity.LEGENDARY, null, GEntityType.BABY_ZOMBIE_VILLAGER, Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.LAVA.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        WITCH = new PetType(PetCategoryType.VILLAGER, "Witch", "&5Witch", new GMaterial(EnumMaterial.POTION, 8196), "CookieGadgets.pets.witch", 45, Rarity.EPIC, null, GEntityType.WITCH, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.BAKED_POTATO.getName(), PetItems.WATER.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.PUMPKIN_PIE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()));
        EVOKER = new PetType(PetCategoryType.VILLAGER, "Evoker", "&6Evoker", new GMaterial("head:4f6fb89d1c631bd7e79fe185ba1a6705425f5c31a5ff626521e395d4a6f7e2"), "CookieGadgets.pets.evoker", 80, Rarity.LEGENDARY, null, GEntityType.EVOKER, Arrays.asList(PetItems.MELON.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        VINDICATOR = new PetType(PetCategoryType.VILLAGER, "Vindicator", "&6Vindicator", new GMaterial("head:4f6fb89d1c631bd7e79fe185ba1a6705425f5c31a5ff626521e395d4a6f7e2"), "CookieGadgets.pets.vindicator", 80, Rarity.LEGENDARY, null, GEntityType.VINDICATOR, Arrays.asList(PetItems.MELON.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        ILLUSIONER = new PetType(PetCategoryType.VILLAGER, "Illusioner", "&6Illusioner", new GMaterial("head:1c678c9f4c6dd4d991930f82e6e7d8b89b2891f35cba48a4b18539bbe7ec927"), "CookieGadgets.pets.illusioner", 85, Rarity.LEGENDARY, null, GEntityType.ILLUSIONER, Arrays.asList(PetItems.MELON.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        GOLEM = new PetType(PetCategoryType.GOLEM, "Golem", "&6Golem", new GMaterial(EnumMaterial.IRON_BLOCK), "CookieGadgets.pets.golem", 200, Rarity.LEGENDARY, null, GEntityType.GOLEM, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.WATER.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.FLOWER.getName(), PetItems.LAVA.getName(), PetItems.BALL.getName()));
        ENDERMAN = new PetType(PetCategoryType.ENDERMAN, "Enderman", "&6Enderman", new GMaterial("head:7a59bb0a7a32965b3d90d8eafa899d1835f424509eadd4e6b709ada50b9cf"), "CookieGadgets.pets.enderman", 150, Rarity.LEGENDARY, null, GEntityType.ENDERMAN, Arrays.asList(PetItems.MUSHROOM_SOUP.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName()), Arrays.asList(PetItems.PUMPKIN_PIE.getName(), PetItems.BONE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.BAKED_POTATO.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        BLAZE = new PetType(PetCategoryType.BLAZE, "Blaze", "&6Blaze", new GMaterial(EnumMaterial.BLAZE_ROD), "CookieGadgets.pets.blaze", 55, Rarity.LEGENDARY, null, GEntityType.BLAZE, Arrays.asList(PetItems.MELON.getName(), PetItems.WATER.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.STICK.getName()));
        ENDERMITE = new PetType(PetCategoryType.ENDERMITE, "Endermite", "&6Endermite", new GMaterial(EnumMaterial.ENDERMITE_SPAWN_EGG), "CookieGadgets.pets.endermite", 55, Rarity.LEGENDARY, null, GEntityType.ENDERMITE, Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.RAW_PORKCHOP.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.FRISBEE.getName()));
        COW = new PetType(PetCategoryType.COW, "Cow", "&aCow", new GMaterial(EnumMaterial.LEATHER), "CookieGadgets.pets.cow", 10, Rarity.COMMON, null, GEntityType.COW, Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MUSHROOM_SOUP.getName(), PetItems.COOKIE.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()));
        BABY_COW = new PetType(PetCategoryType.COW, "Baby Cow", "&9Cow (Baby)", new GMaterial(EnumMaterial.WHEAT), "CookieGadgets.pets.babycow", 30, Rarity.RARE, null, GEntityType.BABY_COW, Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MUSHROOM_SOUP.getName(), PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        MUSHROOM_COW = new PetType(PetCategoryType.COW, "Mushroom Cow", "&5Mushroom Cow", new GMaterial(EnumMaterial.RED_MUSHROOM), "CookieGadgets.pets.mushroomcow", 50, Rarity.EPIC, null, GEntityType.MUSHROOM_COW, Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.MUSHROOM_SOUP.getName(), PetItems.WATER.getName(), PetItems.FEATHER.getName()));
        BABY_MUSHROOM_COW = new PetType(PetCategoryType.COW, "Baby Mushroom Cow", "&6Mushroom Cow (Baby)", new GMaterial(EnumMaterial.BROWN_MUSHROOM), "CookieGadgets.pets.babymushroomcow", 90, Rarity.LEGENDARY, null, GEntityType.BABY_MUSHROOM_COW, Arrays.asList(PetItems.COOKIE.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MUSHROOM_SOUP.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.FEATHER.getName()));
        CREEPER = new PetType(PetCategoryType.CREEPER, "Creeper", "&5Creeper", new GMaterial(EnumMaterial.GUNPOWDER), "CookieGadgets.pets.creeper", 52, Rarity.EPIC, null, GEntityType.CREEPER, Arrays.asList(PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.FLOWER.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.LAVA.getName(), PetItems.STICK.getName()));
        POWERED_CREEPER = new PetType(PetCategoryType.CREEPER, "Powered Creeper", "&6Powered Creeper", new GMaterial(EnumMaterial.TNT), "CookieGadgets.pets.poweredcreeper", 75, Rarity.LEGENDARY, null, GEntityType.POWERED_CREEPER, Arrays.asList(PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.FLOWER.getName(), PetItems.LAVA.getName(), PetItems.FRISBEE.getName()));
        BLACK_HORSE = new PetType(PetCategoryType.HORSE, "Black Horse", "&5Horse: Black", new GMaterial(EnumMaterial.COAL), "CookieGadgets.pets.blackhorse", 42, Rarity.EPIC, null, GEntityType.BLACK_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_BLACK_HORSE = new PetType(PetCategoryType.HORSE, "Baby Black Horse", "&6Horse: Black (Baby)", new GMaterial(EnumMaterial.CHARCOAL), "CookieGadgets.pets.babyblackhorse", 60, Rarity.LEGENDARY, null, GEntityType.BABY_BLACK_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        BROWN_HORSE = new PetType(PetCategoryType.HORSE, "Brown Horse", "&aHorse: Brown", new GMaterial(EnumMaterial.BRICK), "CookieGadgets.pets.brownhorse", 15, Rarity.COMMON, null, GEntityType.BROWN_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_BROWN_HORSE = new PetType(PetCategoryType.HORSE, "Baby Brown Horse", "&9Horse: Brown (Baby)", new GMaterial(EnumMaterial.COCOA_BEANS), "CookieGadgets.pets.babybrownhorse", 30, Rarity.RARE, null, GEntityType.BABY_BROWN_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        CHESTNUT_HORSE = new PetType(PetCategoryType.HORSE, "Chestnut Horse", "&9Horse: Chestnut", new GMaterial(EnumMaterial.MELON_SEEDS), "CookieGadgets.pets.chestnuthorse", 25, Rarity.RARE, null, GEntityType.CHESTNUT_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_CHESTNUT_HORSE = new PetType(PetCategoryType.HORSE, "Baby Chestnut Horse", "&5Horse: Chestnut (Baby)", new GMaterial(EnumMaterial.PUMPKIN_SEEDS), "CookieGadgets.pets.babychestnuthorse", 55, Rarity.EPIC, null, GEntityType.BABY_CHESTNUT_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        CREAMY_HORSE = new PetType(PetCategoryType.HORSE, "Creamy Horse", "&9Horse: Creamy", new GMaterial(EnumMaterial.MILK_BUCKET), "CookieGadgets.pets.creamyhorse", 25, Rarity.RARE, null, GEntityType.CREAMY_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_CREAMY_HORSE = new PetType(PetCategoryType.HORSE, "Baby Creamy Horse", "&5Horse: Creamy (Baby)", new GMaterial(EnumMaterial.SUGAR), "CookieGadgets.pets.babycreamyhorse", 55, Rarity.EPIC, null, GEntityType.BABY_CREAMY_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        DARK_BROWN_HORSE = new PetType(PetCategoryType.HORSE, "Dark Brown Horse", "&9Horse: Dark Brown", new GMaterial(EnumMaterial.DARK_OAK_LOG), "CookieGadgets.pets.darkbrownhorse", 25, Rarity.RARE, null, GEntityType.DARK_BROWN_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_DARK_BROWN_HORSE = new PetType(PetCategoryType.HORSE, "Baby Dark Brown Horse", "&5Horse: Dark Brown (Baby)", new GMaterial(EnumMaterial.DARK_OAK_PLANKS), "CookieGadgets.pets.babydarkbrownhorse", 55, Rarity.EPIC, null, GEntityType.BABY_DARK_BROWN_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        GRAY_HORSE = new PetType(PetCategoryType.HORSE, "Gray Horse", "&9Horse: Gray", new GMaterial(EnumMaterial.APPLE), "CookieGadgets.pets.grayhorse", 25, Rarity.RARE, null, GEntityType.GRAY_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_GRAY_HORSE = new PetType(PetCategoryType.HORSE, "Baby Gray Horse", "&5Horse: Gray (Baby)", new GMaterial(EnumMaterial.GRAY_DYE), "CookieGadgets.pets.babygrayhorse", 55, Rarity.EPIC, null, GEntityType.BABY_GRAY_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        WHITE_HORSE = new PetType(PetCategoryType.HORSE, "White Horse", "&5Horse: White", new GMaterial(EnumMaterial.QUARTZ_BLOCK), "CookieGadgets.pets.whitehorse", 42, Rarity.EPIC, null, GEntityType.WHITE_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_WHITE_HORSE = new PetType(PetCategoryType.HORSE, "Baby White Horse", "&6Horse: White (Baby)", new GMaterial(EnumMaterial.DIORITE), "CookieGadgets.pets.babywhitehorse", 60, Rarity.LEGENDARY, null, GEntityType.BABY_WHITE_HORSE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        DONKEY = new PetType(PetCategoryType.HORSE, "Donkey", "&9Donkey", new GMaterial(EnumMaterial.LEATHER), "CookieGadgets.pets.donkey", 27, Rarity.RARE, null, GEntityType.DONKEY, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_DONKEY = new PetType(PetCategoryType.HORSE, "Baby Donkey", "&5Donkey (Baby)", new GMaterial(EnumMaterial.CARROT), "CookieGadgets.pets.babydonkey", 40, Rarity.EPIC, null, GEntityType.BABY_DONKEY, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        MULE = new PetType(PetCategoryType.HORSE, "Mule", "&9Mule", new GMaterial(EnumMaterial.COOKED_MUTTON), "CookieGadgets.pets.mule", 22, Rarity.RARE, null, GEntityType.MULE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_MULE = new PetType(PetCategoryType.HORSE, "Baby Mule", "&5Mule (Baby)", new GMaterial(EnumMaterial.MUTTON), "CookieGadgets.pets.babymule", 35, Rarity.EPIC, null, GEntityType.BABY_MULE, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        SKELETON_HORSE = new PetType(PetCategoryType.HORSE, "Skeleton Horse", "&6Skeleton Horse", new GMaterial(EnumMaterial.BONE), "CookieGadgets.pets.skeletonhorse", 65, Rarity.LEGENDARY, null, GEntityType.SKELETON_HORSE, Arrays.asList(PetItems.MELON.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()));
        BABY_SKELETON_HORSE = new PetType(PetCategoryType.HORSE, "Baby Skeleton Horse", "&6Skeleton Horse (Baby)", new GMaterial(EnumMaterial.BONE_MEAL), "CookieGadgets.pets.babyskeletonhorse", 85, Rarity.LEGENDARY, null, GEntityType.BABY_SKELETON_HORSE, Arrays.asList(PetItems.MELON.getName(), PetItems.WATER.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        UNDEAD_HORSE = new PetType(PetCategoryType.HORSE, "Undead Horse", "&5Undead Horse", new GMaterial(EnumMaterial.DARK_OAK_SAPLING), "CookieGadgets.pets.undeadhorse", 63, Rarity.EPIC, null, GEntityType.UNDEAD_HORSE, Arrays.asList(PetItems.MELON.getName(), PetItems.WATER.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()));
        BABY_UNDEAD_HORSE = new PetType(PetCategoryType.HORSE, "Baby Undead Horse", "&6Undead Horse (Baby)", new GMaterial(EnumMaterial.DEAD_BUSH), "CookieGadgets.pets.babyundeadhorse", 85, Rarity.LEGENDARY, null, GEntityType.BABY_UNDEAD_HORSE, Arrays.asList(PetItems.MELON.getName(), PetItems.WATER.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        PIG = new PetType(PetCategoryType.PIG, "Pig", "&aPig", new GMaterial(EnumMaterial.COOKED_PORKCHOP), "CookieGadgets.pets.pig", 10, Rarity.COMMON, null, GEntityType.PIG, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.PUMPKIN_PIE.getName(), PetItems.CARROT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        BABY_PIG = new PetType(PetCategoryType.PIG, "Baby Pig", "&9Pig (Baby)", new GMaterial(EnumMaterial.CARROT), "CookieGadgets.pets.babypig", 30, Rarity.RARE, null, GEntityType.BABY_PIG, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.PUMPKIN_PIE.getName(), PetItems.BAKED_POTATO.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        PIG_ZOMBIE = new PetType(PetCategoryType.PIG, "Pig Zombie", "&5Pig Zombie", new GMaterial(EnumMaterial.GOLD_INGOT), "CookieGadgets.pets.pigzombie", 50, Rarity.EPIC, null, GEntityType.PIG_ZOMBIE, Arrays.asList(PetItems.MELON.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()));
        BABY_PIG_ZOMBIE = new PetType(PetCategoryType.PIG, "Baby Pig Zombie", "&6Pig Zombie (Baby)", new GMaterial(EnumMaterial.GOLD_NUGGET), "CookieGadgets.pets.babypigzombie", 85, Rarity.LEGENDARY, null, GEntityType.BABY_PIG_ZOMBIE, Arrays.asList(PetItems.MELON.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()));
        BLACK_SHEEP = new PetType(PetCategoryType.SHEEP, "Black Sheep", "&5Sheep: Black", new GMaterial(EnumMaterial.BLACK_TERRACOTTA), "CookieGadgets.pets.blacksheep", 40, Rarity.EPIC, null, GEntityType.BLACK_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_BLACK_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Black Sheep", "&6Sheep: Black (Baby)", new GMaterial(EnumMaterial.BLACK_WOOL), "CookieGadgets.pets.babyblacksheep", 75, Rarity.LEGENDARY, null, GEntityType.BABY_BLACK_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        BLUE_SHEEP = new PetType(PetCategoryType.SHEEP, "Blue Sheep", "&9Sheep: Blue", new GMaterial(EnumMaterial.BLUE_TERRACOTTA), "CookieGadgets.pets.bluesheep", 20, Rarity.RARE, null, GEntityType.BLUE_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_BLUE_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Blue Sheep", "&5Sheep: Blue (Baby)", new GMaterial(EnumMaterial.BLUE_WOOL), "CookieGadgets.pets.babybluesheep", 55, Rarity.EPIC, null, GEntityType.BABY_BLUE_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        BROWN_SHEEP = new PetType(PetCategoryType.SHEEP, "Brown Sheep", "&aSheep: Brown", new GMaterial(EnumMaterial.BROWN_TERRACOTTA), "CookieGadgets.pets.brownsheep", 12, Rarity.COMMON, null, GEntityType.BROWN_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_BROWN_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Brown Sheep", "&9Sheep: Brown (Baby)", new GMaterial(EnumMaterial.BROWN_WOOL), "CookieGadgets.pets.babybrownsheep", 30, Rarity.RARE, null, GEntityType.BABY_BROWN_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        CYAN_SHEEP = new PetType(PetCategoryType.SHEEP, "Cyan Sheep", "&9Sheep: Cyan", new GMaterial(EnumMaterial.CYAN_TERRACOTTA), "CookieGadgets.pets.cyansheep", 20, Rarity.RARE, null, GEntityType.CYAN_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_CYAN_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Cyan Sheep", "&5Sheep: Cyan (Baby)", new GMaterial(EnumMaterial.CYAN_WOOL), "CookieGadgets.pets.babycyansheep", 55, Rarity.EPIC, null, GEntityType.BABY_CYAN_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        GRAY_SHEEP = new PetType(PetCategoryType.SHEEP, "Gray Sheep", "&aSheep: Gray", new GMaterial(EnumMaterial.GRAY_TERRACOTTA), "CookieGadgets.pets.graysheep", 12, Rarity.COMMON, null, GEntityType.GRAY_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_GRAY_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Gray Sheep", "&9Sheep: Gray (Baby)", new GMaterial(EnumMaterial.GRAY_WOOL), "CookieGadgets.pets.babygraysheep", 30, Rarity.RARE, null, GEntityType.BABY_GRAY_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        GREEN_SHEEP = new PetType(PetCategoryType.SHEEP, "Green Sheep", "&9Sheep: Green", new GMaterial(EnumMaterial.GREEN_TERRACOTTA), "CookieGadgets.pets.greensheep", 20, Rarity.RARE, null, GEntityType.GREEN_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_GREEN_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Green Sheep", "&5Sheep: Green (Baby)", new GMaterial(EnumMaterial.GREEN_WOOL), "CookieGadgets.pets.babygreensheep", 55, Rarity.EPIC, null, GEntityType.BABY_GREEN_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        LIGHT_BLUE_SHEEP = new PetType(PetCategoryType.SHEEP, "Light Blue Sheep", "&9Sheep: Light Blue", new GMaterial(EnumMaterial.LIGHT_BLUE_TERRACOTTA), "CookieGadgets.pets.lightbluesheep", 20, Rarity.RARE, null, GEntityType.LIGHT_BLUE_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_LIGHT_BLUE_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Light Blue Sheep", "&5Sheep: Light Blue (Baby)", new GMaterial(EnumMaterial.LIGHT_BLUE_WOOL), "CookieGadgets.pets.babylightbluesheep", 55, Rarity.EPIC, null, GEntityType.BABY_LIGHT_BLUE_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        LIME_SHEEP = new PetType(PetCategoryType.SHEEP, "Lime Sheep", "&9Sheep: Lime", new GMaterial(EnumMaterial.LIME_TERRACOTTA), "CookieGadgets.pets.limesheep", 20, Rarity.RARE, null, GEntityType.LIME_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_LIME_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Lime Sheep", "&5Sheep: Lime (Baby)", new GMaterial(EnumMaterial.LIME_WOOL), "CookieGadgets.pets.babylimesheep", 55, Rarity.EPIC, null, GEntityType.BABY_LIME_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        MAGENTA_SHEEP = new PetType(PetCategoryType.SHEEP, "Magenta Sheep", "&9Sheep: Magenta", new GMaterial(EnumMaterial.MAGENTA_TERRACOTTA), "CookieGadgets.pets.magentasheep", 20, Rarity.RARE, null, GEntityType.MAGENTA_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_MAGENTA_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Magenta Sheep", "&5Sheep: Magenta (Baby)", new GMaterial(EnumMaterial.MAGENTA_WOOL), "CookieGadgets.pets.babymagentasheep", 55, Rarity.EPIC, null, GEntityType.BABY_MAGENTA_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        ORANGE_SHEEP = new PetType(PetCategoryType.SHEEP, "Orange Sheep", "&9Sheep: Orange", new GMaterial(EnumMaterial.ORANGE_TERRACOTTA), "CookieGadgets.pets.orangesheep", 20, Rarity.RARE, null, GEntityType.ORANGE_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_ORANGE_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Orange Sheep", "&5Sheep: Orange (Baby)", new GMaterial(EnumMaterial.ORANGE_WOOL), "CookieGadgets.pets.babyorangesheep", 55, Rarity.EPIC, null, GEntityType.BABY_ORANGE_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        PINK_SHEEP = new PetType(PetCategoryType.SHEEP, "Pink Sheep", "&5Sheep: Pink", new GMaterial(EnumMaterial.PINK_TERRACOTTA), "CookieGadgets.pets.pinksheep", 55, Rarity.EPIC, null, GEntityType.PINK_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_PINK_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Pink Sheep", "&6Sheep: Pink (Baby)", new GMaterial(EnumMaterial.PINK_WOOL), "CookieGadgets.pets.babypinksheep", 75, Rarity.LEGENDARY, null, GEntityType.BABY_PINK_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        PURPLE_SHEEP = new PetType(PetCategoryType.SHEEP, "Purple Sheep", "&9Sheep: Purple", new GMaterial(EnumMaterial.PURPLE_TERRACOTTA), "CookieGadgets.pets.purplesheep", 55, Rarity.RARE, null, GEntityType.PURPLE_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_PURPLE_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Purple Sheep", "&5Sheep: Purple (Baby)", new GMaterial(EnumMaterial.PURPLE_WOOL), "CookieGadgets.pets.babypurplesheep", 75, Rarity.EPIC, null, GEntityType.BABY_PURPLE_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        RED_SHEEP = new PetType(PetCategoryType.SHEEP, "Red Sheep", "&9Sheep: Red", new GMaterial(EnumMaterial.RED_TERRACOTTA), "CookieGadgets.pets.redsheep", 20, Rarity.RARE, null, GEntityType.RED_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_RED_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Red Sheep", "&5Sheep: Red (Baby)", new GMaterial(EnumMaterial.RED_WOOL), "CookieGadgets.pets.babyredsheep", 55, Rarity.EPIC, null, GEntityType.BABY_RED_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        SILVER_SHEEP = new PetType(PetCategoryType.SHEEP, "Silver Sheep", "&aSheep: Silver", new GMaterial(EnumMaterial.LIGHT_GRAY_TERRACOTTA), "CookieGadgets.pets.silversheep", 12, Rarity.COMMON, null, GEntityType.SILVER_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_SILVER_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Silver Sheep", "&9Sheep: Silver (Baby)", new GMaterial(EnumMaterial.LIGHT_GRAY_WOOL), "CookieGadgets.pets.babysilversheep", 30, Rarity.RARE, null, GEntityType.BABY_SILVER_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        WHITE_SHEEP = new PetType(PetCategoryType.SHEEP, "White Sheep", "&aSheep: White", new GMaterial(EnumMaterial.WHITE_TERRACOTTA), "CookieGadgets.pets.whitesheep", 12, Rarity.COMMON, null, GEntityType.WHITE_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_WHITE_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby White Sheep", "&9Sheep: White (Baby)", new GMaterial(EnumMaterial.WHITE_WOOL), "CookieGadgets.pets.babywhitesheep", 30, Rarity.RARE, null, GEntityType.BABY_WHITE_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        YELLOW_SHEEP = new PetType(PetCategoryType.SHEEP, "Yellow Sheep", "&9Sheep: Yellow", new GMaterial(EnumMaterial.YELLOW_TERRACOTTA), "CookieGadgets.pets.yellowsheep", 20, Rarity.RARE, null, GEntityType.YELLOW_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        BABY_YELLOW_SHEEP = new PetType(PetCategoryType.SHEEP, "Baby Yellow Sheep", "&5Sheep: Yellow (Baby)", new GMaterial(EnumMaterial.YELLOW_WOOL), "CookieGadgets.pets.babyyellowsheep", 55, Rarity.EPIC, null, GEntityType.BABY_YELLOW_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.MELON.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()));
        RAINBOW_SHEEP = new PetType(PetCategoryType.SHEEP, "Rainbow Sheep", "&6Sheep: Rainbow", new GMaterial(EnumMaterial.EXPERIENCE_BOTTLE), "CookieGadgets.pets.rainbowsheep", 75, Rarity.LEGENDARY, null, GEntityType.RAINBOW_SHEEP, Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.CARROT.getName(), PetItems.HAY.getName(), PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.LEASH.getName()));
        SLIME_BIG = new PetType(PetCategoryType.SLIME, "Big Slime", "&6Slime (Big)", new GMaterial(EnumMaterial.SLIME_BLOCK), "CookieGadgets.pets.bigslime", 100, Rarity.LEGENDARY, null, GEntityType.SLIME_BIG, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        SLIME_SMALL = new PetType(PetCategoryType.SLIME, "Small Slime", "&5Slime (Small)", new GMaterial(EnumMaterial.SLIME_BLOCK), "CookieGadgets.pets.smallslime", 63, Rarity.EPIC, null, GEntityType.SLIME_SMALL, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        SLIME_TINY = new PetType(PetCategoryType.SLIME, "Tiny Slime", "&9Slime (Tiny)", new GMaterial(EnumMaterial.SLIME_BALL), "CookieGadgets.pets.tinyslime", 30, Rarity.RARE, null, GEntityType.SLIME_TINY, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        MAGMA_CUBE_BIG = new PetType(PetCategoryType.SLIME, "Big Magma Cube", "&6Magma Cube (Big)", new GMaterial(EnumMaterial.MAGMA_CREAM), "CookieGadgets.pets.bigmagmacube", 100, Rarity.LEGENDARY, null, GEntityType.MAGMA_CUBE_BIG, Arrays.asList(PetItems.APPLE.getName(), PetItems.MELON.getName(), PetItems.PUMPKIN_PIE.getName(), PetItems.CARROT.getName(), PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.BREAD.getName(), PetItems.COOKIE.getName(), PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.BONE.getName(), PetItems.MILK.getName(), PetItems.LEASH.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.BALL.getName()));
        MAGMA_CUBE_SMALL = new PetType(PetCategoryType.SLIME, "Small Magma Cube", "&5Magma Cube (Small)", new GMaterial(EnumMaterial.BLAZE_POWDER), "CookieGadgets.pets.smallmagmacube", 63, Rarity.EPIC, null, GEntityType.MAGMA_CUBE_SMALL, Arrays.asList(PetItems.APPLE.getName(), PetItems.MELON.getName(), PetItems.PUMPKIN_PIE.getName(), PetItems.CARROT.getName(), PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.BREAD.getName(), PetItems.COOKIE.getName(), PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.BONE.getName(), PetItems.MILK.getName(), PetItems.LEASH.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.BALL.getName()));
        MAGMA_CUBE_TINY = new PetType(PetCategoryType.SLIME, "Tiny Magma Cube", "&9Magma Cube (Tiny)", new GMaterial(EnumMaterial.LAVA_BUCKET), "CookieGadgets.pets.tinymagmacube", 30, Rarity.RARE, null, GEntityType.MAGMA_CUBE_TINY, Arrays.asList(PetItems.APPLE.getName(), PetItems.MELON.getName(), PetItems.PUMPKIN_PIE.getName(), PetItems.CARROT.getName(), PetItems.BAKED_POTATO.getName(), PetItems.FLOWER.getName(), PetItems.HAY.getName(), PetItems.WHEAT.getName(), PetItems.BREAD.getName(), PetItems.COOKIE.getName(), PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.BONE.getName(), PetItems.MILK.getName(), PetItems.LEASH.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.BALL.getName()));
        SKELETON = new PetType(PetCategoryType.SKELETON, "Skeleton", "&5Skeleton", new GMaterial(EnumMaterial.BOW), "CookieGadgets.pets.skeleton", 50, Rarity.EPIC, null, GEntityType.SKELETON, Arrays.asList(PetItems.MELON.getName(), PetItems.WATER.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()));
        WITHER_SKELETON = new PetType(PetCategoryType.SKELETON, "Wither Skeleton", "&6Wither Skeleton", new GMaterial(EnumMaterial.STONE_SWORD), "CookieGadgets.pets.witherskeleton", 70, Rarity.LEGENDARY, null, GEntityType.WITHER_SKELETON, Arrays.asList(PetItems.MELON.getName(), PetItems.WATER.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.BONE.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()));
        STRAY_SKELETON = new PetType(PetCategoryType.SKELETON, "Stray Skeleton", "&6Stray Skeleton", new GMaterial("head:78ddf76e555dd5c4aa8a0a5fc584520cd63d489c253de969f7f22f85a9a2d56"), "CookieGadgets.pets.strayskeleton", 85, Rarity.LEGENDARY, null, GEntityType.STRAY_SKELETON, Arrays.asList(PetItems.MELON.getName(), PetItems.WATER.getName(), PetItems.FEATHER.getName()), Arrays.asList(PetItems.ROTTEN_FLESH.getName(), PetItems.MILK.getName(), PetItems.FRISBEE.getName()), Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()));
        SQUID = new PetType(PetCategoryType.SQUID, "Squid", "&6Squid", new GMaterial(EnumMaterial.INK_SAC), "CookieGadgets.pets.squid", 78, Rarity.LEGENDARY, null, GEntityType.SQUID, Arrays.asList(PetItems.PUMPKIN_PIE.getName(), PetItems.WHEAT.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.MILK.getName(), PetItems.STICK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.WATER.getName(), PetItems.FRISBEE.getName()));
        POLAR_BEAR = new PetType(PetCategoryType.POLAR_BEAR, "Polar Bear", "&5Polar Bear", new GMaterial("head:bab178f5cdd750f0e356860aa5539153eb2abec1e146ca57c65d25a5df8fdfe"), "CookieGadgets.pets.polarbear", 55, Rarity.EPIC, null, GEntityType.POLAR_BEAR, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        BABY_POLAR_BEAR = new PetType(PetCategoryType.POLAR_BEAR, "Baby Polar Bear", "&6Polar Bear (Baby)", new GMaterial("head:bab178f5cdd750f0e356860aa5539153eb2abec1e146ca57c65d25a5df8fdfe"), "CookieGadgets.pets.babypolarbear", 75, Rarity.LEGENDARY, null, GEntityType.BABY_POLAR_BEAR, Arrays.asList(PetItems.MAGMA_CREAM.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        BROWN_LLAMA = new PetType(PetCategoryType.LLAMA, "Brown Llama", "&5Llama: Brown", new GMaterial("head:c2b1ecff77ffe3b503c30a548eb23a1a08fa26fd67cdff389855d74921368"), "CookieGadgets.pets.brownllama", 60, Rarity.EPIC, null, GEntityType.BROWN_LLAMA, Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        BABY_BROWN_LLAMA = new PetType(PetCategoryType.LLAMA, "Baby Brown Llama", "&6Llama: Brown (Baby)", new GMaterial("head:c2b1ecff77ffe3b503c30a548eb23a1a08fa26fd67cdff389855d74921368"), "CookieGadgets.pets.babybrownllama", 80, Rarity.LEGENDARY, null, GEntityType.BABY_BROWN_LLAMA, Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        CREAMY_LLAMA = new PetType(PetCategoryType.LLAMA, "Creamy Llama", "&5Llama: Creamy", new GMaterial("head:80899acfebf947a0effc4022deb080543f7e389116bd4964c2c227ffa34a3375"), "CookieGadgets.pets.creamyllama", 55, Rarity.EPIC, null, GEntityType.CREAMY_LLAMA, Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        BABY_CREAMY_LLAMA = new PetType(PetCategoryType.LLAMA, "Baby Creamy Llama", "&6Llama: Creamy (Baby)", new GMaterial("head:80899acfebf947a0effc4022deb080543f7e389116bd4964c2c227ffa34a3375"), "CookieGadgets.pets.babycreamyllama", 75, Rarity.LEGENDARY, null, GEntityType.BABY_CREAMY_LLAMA, Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        GRAY_LLAMA = new PetType(PetCategoryType.LLAMA, "Gray Llama", "&5Llama: Gray", new GMaterial("head:cf24e56fd9ffd7133da6d1f3e2f455952b1da462686f753c597ee82299a"), "CookieGadgets.pets.grayllama", 55, Rarity.EPIC, null, GEntityType.GRAY_LLAMA, Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        BABY_GRAY_LLAMA = new PetType(PetCategoryType.LLAMA, "Baby Gray Llama", "&6Llama: Gray (Baby)", new GMaterial("head:cf24e56fd9ffd7133da6d1f3e2f455952b1da462686f753c597ee82299a"), "CookieGadgets.pets.babygrayllama", 75, Rarity.LEGENDARY, null, GEntityType.BABY_GRAY_LLAMA, Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        WHITE_LLAMA = new PetType(PetCategoryType.LLAMA, "White Llama", "&5Llama: White", new GMaterial("head:83d9b5915912ffc2b85761d6adcb428a812f9b83ff634e331162ce46c99e9"), "CookieGadgets.pets.whitellama", 55, Rarity.EPIC, null, GEntityType.WHITE_LLAMA, Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.HAY.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()));
        BABY_WHITE_LLAMA = new PetType(PetCategoryType.LLAMA, "Baby White Llama", "&6Llama: White (Baby)", new GMaterial("head:83d9b5915912ffc2b85761d6adcb428a812f9b83ff634e331162ce46c99e9"), "CookieGadgets.pets.babywhitellama", 75, Rarity.LEGENDARY, null, GEntityType.BABY_WHITE_LLAMA, Arrays.asList(PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.LEASH.getName()), Arrays.asList(PetItems.WHEAT.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.COOKIE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        NORMAL_PANDA = new PetType(PetCategoryType.PANDA, "Normal Panda", "&5Panda: Normal", new GMaterial("head:dca096eea506301bea6d4b17ee1605625a6f5082c71f74a639cc940439f47166"), "CookieGadgets.pets.normalpanda", 55, Rarity.EPIC, null, GEntityType.PANDA_NORMAL, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()));
        BABY_NORMAL_PANDA = new PetType(PetCategoryType.PANDA, "Baby Normal Panda", "&6Panda: Normal (Baby)", new GMaterial("head:dca096eea506301bea6d4b17ee1605625a6f5082c71f74a639cc940439f47166"), "CookieGadgets.pets.babynormalpanda", 75, Rarity.LEGENDARY, null, GEntityType.BABY_PANDA_NORMAL, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        LAZY_PANDA = new PetType(PetCategoryType.PANDA, "Lazy Panda", "&5Panda: Lazy", new GMaterial("head:7818b681cace1c641919f53edadecb142330d089a826b56219138c33b7a5e0db"), "CookieGadgets.pets.lazypanda", 55, Rarity.EPIC, null, GEntityType.PANDA_LAZY, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()));
        BABY_LAZY_PANDA = new PetType(PetCategoryType.PANDA, "Baby Lazy Panda", "&6Panda: Lazy (Baby)", new GMaterial("head:7818b681cace1c641919f53edadecb142330d089a826b56219138c33b7a5e0db"), "CookieGadgets.pets.babylazypanda", 75, Rarity.LEGENDARY, null, GEntityType.BABY_PANDA_LAZY, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        WORRIED_PANDA = new PetType(PetCategoryType.PANDA, "Worried Panda", "&5Panda: Worried", new GMaterial("head:c8e921c57e54dd07337ffba629e72caf3850d836b76562b1bc3bc5949f2d41d"), "CookieGadgets.pets.worriedpanda", 55, Rarity.EPIC, null, GEntityType.PANDA_WORRIED, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()));
        BABY_WORRIED_PANDA = new PetType(PetCategoryType.PANDA, "Baby Worried Panda", "&6Panda: Worried (Baby)", new GMaterial("head:c8e921c57e54dd07337ffba629e72caf3850d836b76562b1bc3bc5949f2d41d"), "CookieGadgets.pets.babyworriedpanda", 75, Rarity.LEGENDARY, null, GEntityType.BABY_PANDA_WORRIED, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        PLAYFUL_PANDA = new PetType(PetCategoryType.PANDA, "Playful Panda", "&5Panda: Playful", new GMaterial("head:b6463e64ce29764db3cb46806cee606afc24bdf0ce14b6660c270a96c787426"), "CookieGadgets.pets.playfulpanda", 55, Rarity.EPIC, null, GEntityType.PANDA_PLAYFUL, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()));
        BABY_PLAYFUL_PANDA = new PetType(PetCategoryType.PANDA, "Baby Playful Panda", "&6Panda: Playful (Baby)", new GMaterial("head:b6463e64ce29764db3cb46806cee606afc24bdf0ce14b6660c270a96c787426"), "CookieGadgets.pets.babyplayfulpanda", 75, Rarity.LEGENDARY, null, GEntityType.BABY_PANDA_PLAYFUL, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        BROWN_PANDA = new PetType(PetCategoryType.PANDA, "Brown Panda", "&5Panda: Brown", new GMaterial("head:c5d0d45bf992b072cf5c513e06beefe8bdc809c8150f3d14b883796a7b74e406"), "CookieGadgets.pets.brownpanda", 55, Rarity.EPIC, null, GEntityType.PANDA_BROWN, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()));
        BABY_BROWN_PANDA = new PetType(PetCategoryType.PANDA, "Baby Brown Panda", "&6Panda: Brown (Baby)", new GMaterial("head:c5d0d45bf992b072cf5c513e06beefe8bdc809c8150f3d14b883796a7b74e406"), "CookieGadgets.pets.babybrownpanda", 75, Rarity.LEGENDARY, null, GEntityType.BABY_PANDA_BROWN, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        WEAK_PANDA = new PetType(PetCategoryType.PANDA, "Weak Panda", "&5Panda: Weak", new GMaterial("head:5c2d25e956337d82791fa0e6617a40086f02d6ebfbfd5a6459889cf206fca787"), "CookieGadgets.pets.weakpanda", 55, Rarity.EPIC, null, GEntityType.PANDA_WEAK, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()));
        BABY_WEAK_PANDA = new PetType(PetCategoryType.PANDA, "Baby Weak Panda", "&6Panda: Weak (Baby)", new GMaterial("head:5c2d25e956337d82791fa0e6617a40086f02d6ebfbfd5a6459889cf206fca787"), "CookieGadgets.pets.babyweakpanda", 75, Rarity.LEGENDARY, null, GEntityType.BABY_PANDA_WEAK, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
        AGGRESSIVE_PANDA = new PetType(PetCategoryType.PANDA, "Aggressive Panda", "&5Panda: Aggressive", new GMaterial("head:83fe1e782ae96a30336a03ef74681ce3a6905fcc673fa56c046aaee6aa28307d"), "CookieGadgets.pets.aggressivepanda", 55, Rarity.EPIC, null, GEntityType.PANDA_AGGRESSIVE, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.BALL.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.STICK.getName()));
        BABY_AGGRESSIVE_PANDA = new PetType(PetCategoryType.PANDA, "Baby Aggressive Panda", "&6Panda: Aggressive (Baby)", new GMaterial("head:83fe1e782ae96a30336a03ef74681ce3a6905fcc673fa56c046aaee6aa28307d"), "CookieGadgets.pets.babyaggressivepanda", 75, Rarity.LEGENDARY, null, GEntityType.BABY_PANDA_AGGRESSIVE, Arrays.asList(PetItems.RAW_FISH.getName(), PetItems.RAW_PORKCHOP.getName(), PetItems.ANGUS_STEAK.getName(), PetItems.LAVA.getName(), PetItems.SPARRING_SWORD.getName()), Arrays.asList(PetItems.CAKE.getName(), PetItems.WATER.getName(), PetItems.STICK.getName()), Arrays.asList(PetItems.APPLE.getName(), PetItems.MILK.getName(), PetItems.BALL.getName()));
    }
    
    private PetType(final PetCategoryType key, final String s, final String displayName, final GMaterial material, final String s2, final int mysteryDust, final Rarity rarity, final List<String> list, final GEntityType entityType, final List<String> list2, final List<String> list3, final List<String> list4) {
        super(Category.PETS, s, displayName, material, s2, mysteryDust, rarity, list, list);
        this.category = key;
        this.entityType = entityType;
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getPetsFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Material") == null) {
            this.material = material;
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Material", material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getPetsFile().getString(String.valueOf(this.getFilePath()) + ".Material"));
        }
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getPetsFile().getInt(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getPetsFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getPetsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Enabled");
        }
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getPetsFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getPetsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Default-Pet-Name") == null) {
            this.defaultPetName = this.displayName;
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Default-Pet-Name", this.defaultPetName);
        }
        else {
            this.defaultPetName = FileManager.getPetsFile().getString(String.valueOf(this.getFilePath()) + ".Default-Pet-Name");
        }
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Move-Speed") == null) {
            this.getEntityType().setMoveSpeed(this.getEntityType().getMoveSpeed());
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Move-Speed", this.getEntityType().getMoveSpeed());
        }
        else {
            this.getEntityType().setMoveSpeed(FileManager.getPetsFile().getDouble(String.valueOf(this.getFilePath()) + ".Move-Speed"));
        }
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Ride-Speed") == null) {
            this.getEntityType().setRideSpeed(this.getEntityType().getRideSpeed());
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Ride-Speed", this.getEntityType().getRideSpeed());
        }
        else {
            this.getEntityType().setRideSpeed(FileManager.getPetsFile().getDouble(String.valueOf(this.getFilePath()) + ".Ride-Speed"));
        }
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest") == null) {
            this.dislike = list2;
            this.reallyLike = list3;
            this.favourite = list4;
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Dislike", this.dislike);
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Really-Like", this.reallyLike);
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Favourite", this.favourite);
        }
        else {
            if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Dislike") == null) {
                this.dislike = list2;
                FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Dislike", this.dislike);
            }
            else {
                this.dislike = FileManager.getPetsFile().getStringList(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Dislike");
            }
            if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Really-Like") == null) {
                this.reallyLike = list3;
                FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Really-Like", this.reallyLike);
            }
            else {
                this.reallyLike = FileManager.getPetsFile().getStringList(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Really-Like");
            }
            if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Favourite") == null) {
                this.favourite = list4;
                FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Favourite", this.favourite);
            }
            else {
                this.favourite = FileManager.getPetsFile().getStringList(String.valueOf(this.getFilePath()) + ".Pet-Item-Interest.Favourite");
            }
        }
        if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = Arrays.asList("&7Make this awesome and", "&7cuddly " + this.getDisplayNameStripColor() + " yours", "&7today!");
            this.unlockedLore = Arrays.asList("{LEVEL}{PET_NAME}", "&7EXP: {EXP}", "&7Type: &9" + this.getDisplayNameStripColor(), "", "&7Status: {HAPPINESS}", "", "&7Hunger: {HUNGER}", "&7Thirst: {THIRST}", "&7Exercise: {EXERCISE}");
            if (!CookieGadgets.getPetData().isEnabled()) {
                this.unlockedLore = Arrays.asList("{PET_NAME}", "&7Type: &9" + this.getDisplayNameStripColor());
            }
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
            FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
        }
        else {
            if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                this.lockedLore = Arrays.asList("&7Make this awesome and", "&7cuddly " + this.getDisplayNameStripColor() + " yours", "&7today!");
                FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
            }
            else {
                this.lockedLore = FileManager.getPetsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getPetsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                this.unlockedLore = Arrays.asList("{LEVEL}{PET_NAME}", "&7EXP: {EXP}", "&7Type: &9" + this.getDisplayNameStripColor(), "", "&7Status: {HAPPINESS}", "", "&7Hunger: {HUNGER}", "&7Thirst: {THIRST}", "&7Exercise: {EXERCISE}");
                if (!CookieGadgets.getPetData().isEnabled()) {
                    this.unlockedLore = Arrays.asList("{PET_NAME}", "&7Type: &9" + this.getDisplayNameStripColor());
                }
                FileManager.getPetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
            else {
                this.unlockedLore = FileManager.getPetsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.clazz = TypeManager.getType(this.entityType.getGEntity());
        if (!PetType.VALUES.contains(this)) {
            PetType.VALUES.add(this);
        }
        if (this.getEntityType().getGEntity().isVersionSupported() && !PetType.PET_CATEGORIES.containsKey(key)) {
            PetType.PET_CATEGORIES.put(key, new ArrayList<PetType>());
        }
        if (!this.getEntityType().getGEntity().isVersionSupported()) {
            this.isEnable = false;
            if (PetType.VALUES.contains(this)) {
                PetType.VALUES.remove(this);
            }
            if (PetType.ENABLED.contains(this)) {
                PetType.ENABLED.remove(this);
            }
        }
    }
    
    public PetCategoryType getPetCategory() {
        return this.category;
    }
    
    @Override
    public String getDisplayName() {
        return ChatUtil.format(this.displayName);
    }
    
    @Override
    public String getDisplayNameStripColor() {
        return ChatUtil.stripColor(this.displayName);
    }
    
    @Override
    public GMaterial getMaterial() {
        return this.material;
    }
    
    @Override
    public int getMysteryDust() {
        return this.mysteryDust;
    }
    
    @Override
    public Rarity getRarity() {
        return this.rarity;
    }
    
    @Override
    public List<String> getLockedLore() {
        return this.lockedLore;
    }
    
    @Override
    public List<String> getUnlockedLore() {
        return this.unlockedLore;
    }
    
    public GEntityType getEntityType() {
        return this.entityType;
    }
    
    public EntityPet getEntityPet() {
        return this.clazz;
    }
    
    public List<String> getDislikeList() {
        return this.dislike;
    }
    
    public boolean containInDislikeList(final PetItems petItems) {
        final Iterator<String> iterator = this.dislike.iterator();
        while (iterator.hasNext()) {
            if (petItems.getName().equalsIgnoreCase(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    public List<String> getReallyLikeList() {
        return this.reallyLike;
    }
    
    public boolean containInReallyLikeList(final PetItems petItems) {
        final Iterator<String> iterator = this.reallyLike.iterator();
        while (iterator.hasNext()) {
            if (petItems.getName().equalsIgnoreCase(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    public List<String> getFavouriteList() {
        return this.favourite;
    }
    
    public boolean containInFavouriteList(final PetItems petItems) {
        final Iterator<String> iterator = this.favourite.iterator();
        while (iterator.hasNext()) {
            if (petItems.getName().equalsIgnoreCase(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    public PetItemInterest getPetInterest(final PetItems petItems) {
        if (this.containInDislikeList(petItems)) {
            return PetItemInterest.DISLIKE;
        }
        if (this.containInReallyLikeList(petItems)) {
            return PetItemInterest.REALLY_LIKE;
        }
        if (this.containInFavouriteList(petItems)) {
            return PetItemInterest.FAVOURITE;
        }
        return PetItemInterest.LIKE;
    }
    
    @Override
    public boolean isEnabled() {
        return this.category.isEnabled() && this.isEnable;
    }
    
    @Override
    public boolean canBeFound() {
        return this.canBeFound;
    }
    
    @Override
    public boolean isPurchasable() {
        return this.purchasable;
    }
    
    public String getDefaultPetName() {
        return ChatUtil.format(this.defaultPetName);
    }
    
    @Override
    public String getFilePath() {
        return "Pets." + this.category.getName() + ".Types." + super.getName();
    }
    
    @Override
    public void equip(final PlayerManager playerManager) {
        if (!this.isEnabled() || !Category.PETS.isEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        this.unequip(playerManager);
        playerManager.setEquippedPet(this);
        playerManager.setSelectedCategoryPet(this.getPetCategory());
        new Pet(playerManager.getUUID(), this);
    }
    
    @Override
    public void unequip(final PlayerManager playerManager) {
        if (playerManager == null) {
            return;
        }
        if (playerManager.getSelectedCategoryPet() != null) {
            playerManager.setSelectedCategoryPet(null);
        }
        if (playerManager.getEquippedPet() != null) {
            playerManager.setEquippedPet(null);
        }
        if (playerManager.getCurrentPet() != null) {
            playerManager.getCurrentPet().clear();
            playerManager.removePet();
        }
    }
    
    @Override
    public boolean checkRequirement(final PlayerManager playerManager) {
        if (!this.getCategory().isEnabled() || !this.isEnabled()) {
            return false;
        }
        if (playerManager == null) {
            return false;
        }
        final Player player = playerManager.getPlayer();
        if (CookieGadgets.getCookieGadgetsData().isAntiLagEnabled() && CookieGadgets.getCookieGadgetsData().disableUsageIfLowTPS() && AntiLagChecker.isLowTPS()) {
            player.sendMessage(MessageType.DISABLE_USAGE_OF_COSMETICS.getFormatMessage());
            return false;
        }
        if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.PETS)) {
            player.sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return false;
        }
        return true;
    }
    
    public static List<PetType> enabled() {
        return PetType.ENABLED;
    }
    
    public static List<PetType> values() {
        return PetType.VALUES;
    }
    
    public static Set<PetCategoryType> petCategories() {
        return PetType.PET_CATEGORIES.keySet();
    }
    
    public static void checkEnabled() {
        for (final PetType petType : values()) {
            if (petType.isEnabled() && !PetType.ENABLED.contains(petType)) {
                PetType.ENABLED.add(petType);
            }
            if (petType.isEnabled() && PetType.PET_CATEGORIES.containsKey(petType.getPetCategory()) && !PetType.PET_CATEGORIES.get(petType.getPetCategory()).contains(petType) && petType.getEntityType().getGEntity().isVersionSupported()) {
                PetType.PET_CATEGORIES.get(petType.getPetCategory()).add(petType);
            }
        }
    }
    
    public static void sortItems() {
        for (final ArrayList<PetType> list : PetType.PET_CATEGORIES.values()) {
            if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.NAME) {
                Collections.sort((List<Object>)list, Comparator.comparing((Function<? super Object, ? extends Comparable>)PetType::getDisplayNameStripColor));
            }
            else {
                if (CookieGadgets.getCookieGadgetsData().getInventorySorting() != EnumInventorySort.RARITY) {
                    continue;
                }
                Collections.sort((List<Object>)list, Comparator.comparing((Function<? super Object, ? extends Comparable>)PetType::getRarity).thenComparing((Function<? super Object, ? extends Comparable>)PetType::getDisplayNameStripColor));
            }
        }
    }
    
    public static List<PetType> getPetCategory(final PetCategoryType petCategoryType) {
        if (PetType.PET_CATEGORIES.containsKey(petCategoryType)) {
            return PetType.PET_CATEGORIES.get(petCategoryType);
        }
        return null;
    }
    
    public static PetType valueOf(final String anotherString) {
        for (final PetType petType : values()) {
            if (petType.getName().equalsIgnoreCase(anotherString)) {
                return petType;
            }
        }
        return null;
    }
}
