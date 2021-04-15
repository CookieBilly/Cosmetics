

package ws.billy.CookieGadgets.utils.cosmetics.pets;

import org.bukkit.entity.Parrot;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GPandaGene;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GProfession;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GSkeletonType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GRabbitType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GOcelotType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GParrotVariant;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseVariant;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GLlamaColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseColor;
import ws.billy.CookieGadgets.utils.GDyeColor;

public enum GEntityType
{
    BABY_BLACKSMITH_VILLAGER("BABY_BLACKSMITH_VILLAGER", 0, "Baby Blacksmith Villager", GEntity.VILLAGER, 0.7, 0.4, true, GProfession.BLACKSMITH), 
    BABY_BLACK_AND_WHITE_RABBIT("BABY_BLACK_AND_WHITE_RABBIT", 1, "Baby Black And White Rabbit", GEntity.RABBIT, 0.5, 0.4, true, GRabbitType.BLACK_AND_WHITE), 
    BABY_BLACK_CAT("BABY_BLACK_CAT", 2, "Baby Black Cat", VersionManager.is1_14OrAbove() ? GEntity.CAT : GEntity.OCELOT, 0.7, 0.4, true, GOcelotType.BLACK_CAT), 
    BABY_BLACK_HORSE("BABY_BLACK_HORSE", 3, "Baby Black Horse", GEntity.HORSE, 0.6, 0.4, true, GHorseColor.BLACK, GHorseVariant.HORSE), 
    BABY_BLACK_RABBIT("BABY_BLACK_RABBIT", 4, "Baby Black Rabbit", GEntity.RABBIT, 0.5, 0.4, true, GRabbitType.BLACK), 
    BABY_BLACK_SHEEP("BABY_BLACK_SHEEP", 5, "Baby Black Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.BLACK), 
    BABY_BLUE_SHEEP("BABY_BLUE_SHEEP", 6, "Baby Blue Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.BLUE), 
    BABY_BROWN_HORSE("BABY_BROWN_HORSE", 7, "Baby Brown Horse", GEntity.HORSE, 0.6, 0.4, true, GHorseColor.BROWN, GHorseVariant.HORSE), 
    BABY_BROWN_LLAMA("BABY_BROWN_LLAMA", 8, "Baby Brown Llama", GEntity.LLAMA, 0.5, 0.4, true, GLlamaColor.BROWN), 
    BABY_BROWN_RABBIT("BABY_BROWN_RABBIT", 9, "Baby Brown Rabbit", GEntity.RABBIT, 0.5, 0.4, true, GRabbitType.BROWN), 
    BABY_BROWN_SHEEP("BABY_BROWN_SHEEP", 10, "Baby Brown Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.BROWN), 
    BABY_BUTCHER_VILLAGER("BABY_BUTCHER_VILLAGER", 11, "Baby Butcher Villager", GEntity.VILLAGER, 0.7, 0.4, true, GProfession.BUTCHER), 
    BABY_CHESTNUT_HORSE("BABY_CHESTNUT_HORSE", 12, "Baby Chestnut Horse", GEntity.HORSE, 0.6, 0.4, true, GHorseColor.CHESTNUT, GHorseVariant.HORSE), 
    BABY_CHICKEN("BABY_CHICKEN", 13, "Baby Chicken", GEntity.CHICKEN, 0.7, 0.4, true), 
    BABY_COW("BABY_COW", 14, "Baby Cow", GEntity.COW, 0.7, 0.4, true), 
    BABY_CREAMY_HORSE("BABY_CREAMY_HORSE", 15, "Baby Creamy Horse", GEntity.HORSE, 0.6, 0.4, true, GHorseColor.CREAMY, GHorseVariant.HORSE), 
    BABY_CREAMY_LLAMA("BABY_CREAMY_LLAMA", 16, "Baby Creamy Llama", GEntity.LLAMA, 0.5, 0.4, true, GLlamaColor.CREAMY), 
    BABY_CYAN_SHEEP("BABY_CYAN_SHEEP", 17, "Baby Cyan Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.CYAN), 
    BABY_DARK_BROWN_HORSE("BABY_DARK_BROWN_HORSE", 18, "Baby Dark Brown Horse", GEntity.HORSE, 0.6, 0.4, true, GHorseColor.DARK_BROWN, GHorseVariant.HORSE), 
    BABY_DONKEY("BABY_DONKEY", 19, "Baby Donkey", GEntity.DONKEY, 0.6, 0.4, true, (GHorseColor)null, GHorseVariant.DONKEY), 
    BABY_FARMER_VILLAGER("BABY_FARMER_VILLAGER", 20, "Baby Farmer Villager", GEntity.VILLAGER, 0.7, 0.4, true, GProfession.FARMER), 
    BABY_GRAY_HORSE("BABY_GRAY_HORSE", 21, "Baby Gray Horse", GEntity.HORSE, 0.6, 0.4, true, GHorseColor.GRAY, GHorseVariant.HORSE), 
    BABY_GRAY_LLAMA("BABY_GRAY_LLAMA", 22, "Baby Gray Llama", GEntity.LLAMA, 0.5, 0.4, true, GLlamaColor.GRAY), 
    BABY_GRAY_SHEEP("BABY_GRAY_SHEEP", 23, "Baby Gray Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.GRAY), 
    BABY_GREEN_SHEEP("BABY_GREEN_SHEEP", 24, "Baby Green Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.GREEN), 
    BABY_GOLD_RABBIT("BABY_GOLD_RABBIT", 25, "Baby Gold Rabbit", GEntity.RABBIT, 0.5, 0.4, true, GRabbitType.GOLD), 
    BABY_HUSK("BABY_HUSK", 26, "Baby Husk", GEntity.HUSK, 0.6, 0.4, true, GProfession.HUSK), 
    BABY_LIBRARIAN_VILLAGER("BABY_LIBRARIAN_VILLAGER", 27, "Baby Librarian Villager", GEntity.VILLAGER, 0.7, 0.4, true, GProfession.LIBRARIAN), 
    BABY_LIGHT_BLUE_SHEEP("BABY_LIGHT_BLUE_SHEEP", 28, "Baby Light Blue Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.LIGHT_BLUE), 
    BABY_LIME_SHEEP("BABY_LIME_SHEEP", 29, "Baby Lime Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.LIME), 
    BABY_MAGENTA_SHEEP("BABY_MAGENTA_SHEEP", 30, "Baby Magenta Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.MAGENTA), 
    BABY_MULE("BABY_MULE", 31, "Baby Mule", GEntity.MULE, 0.7, 0.4, true, (GHorseColor)null, GHorseVariant.MULE), 
    BABY_MUSHROOM_COW("BABY_MUSHROOM_COW", 32, "Baby Mushroom Cow", GEntity.MUSHROOM_COW, 0.7, 0.4, true), 
    BABY_ORANGE_SHEEP("BABY_ORANGE_SHEEP", 33, "Baby Orange Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.ORANGE), 
    BABY_PANDA_NORMAL("BABY_PANDA_NORMAL", 34, "Normal Panda", GEntity.PANDA, 0.5, 0.4, true, GPandaGene.NORMAL), 
    BABY_PANDA_LAZY("BABY_PANDA_LAZY", 35, "Lazy Panda", GEntity.PANDA, 0.5, 0.4, true, GPandaGene.LAZY), 
    BABY_PANDA_WORRIED("BABY_PANDA_WORRIED", 36, "Worried Panda", GEntity.PANDA, 0.5, 0.4, true, GPandaGene.WORRIED), 
    BABY_PANDA_PLAYFUL("BABY_PANDA_PLAYFUL", 37, "Playful Panda", GEntity.PANDA, 0.5, 0.4, true, GPandaGene.PLAYFUL), 
    BABY_PANDA_BROWN("BABY_PANDA_BROWN", 38, "Brown Panda", GEntity.PANDA, 0.5, 0.4, true, GPandaGene.BROWN), 
    BABY_PANDA_WEAK("BABY_PANDA_WEAK", 39, "Weak Panda", GEntity.PANDA, 0.5, 0.4, true, GPandaGene.WEAK), 
    BABY_PANDA_AGGRESSIVE("BABY_PANDA_AGGRESSIVE", 40, "Aggressive Panda", GEntity.PANDA, 0.5, 0.4, true, GPandaGene.AGGRESSIVE), 
    BABY_PIG("BABY_PIG", 41, "Baby Pig", GEntity.PIG, 0.7, 0.4, true), 
    BABY_PIG_ZOMBIE("BABY_PIG_ZOMBIE", 42, "Baby Pig Zombie", GEntity.PIG_ZOMBIE, 0.5, 0.4, true), 
    BABY_PINK_SHEEP("BABY_PINK_SHEEP", 43, "Baby Pink Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.PINK), 
    BABY_POLAR_BEAR("BABY_POLAR_BEAR", 44, "Baby Polar Bear", GEntity.POLAR_BEAR, 0.5, 0.4, true), 
    BABY_PRIEST_VILLAGER("BABY_PRIEST_VILLAGER", 45, "Baby Priest Villager", GEntity.VILLAGER, 0.7, 0.4, true, GProfession.PRIEST), 
    BABY_PURPLE_SHEEP("BABY_PURPLE_SHEEP", 46, "Baby Purple Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.PURPLE), 
    BABY_RED_CAT("BABY_RED_CAT", 47, "Baby Red Cat", VersionManager.is1_14OrAbove() ? GEntity.CAT : GEntity.OCELOT, 0.7, 0.4, true, GOcelotType.RED_CAT), 
    BABY_RED_SHEEP("BABY_RED_SHEEP", 48, "Baby Red Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.RED), 
    BABY_SALT_AND_PEPPER_RABBIT("BABY_SALT_AND_PEPPER_RABBIT", 49, "Baby Salt And Pepper Rabbit", GEntity.RABBIT, 0.5, 0.4, true, GRabbitType.SALT_AND_PEPPER), 
    BABY_SIAMESE_CAT("BABY_SIAMESE_CAT", 50, "Baby Siamese Cat", VersionManager.is1_14OrAbove() ? GEntity.CAT : GEntity.OCELOT, 0.7, 0.4, true, GOcelotType.SIAMESE_CAT), 
    BABY_SILVER_SHEEP("BABY_SILVER_SHEEP", 51, "Baby Silver Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.LIGHT_GRAY), 
    BABY_SKELETON_HORSE("BABY_SKELETON_HORSE", 52, "Baby Skeleton Horse", GEntity.SKELETON_HORSE, 0.6, 0.4, true, (GHorseColor)null, GHorseVariant.SKELETON_HORSE), 
    BABY_THE_KILLER_BUNNY_RABBIT("BABY_THE_KILLER_BUNNY_RABBIT", 53, "Baby The Killer Bunny Rabbit", GEntity.RABBIT, 0.5, 0.4, true, GRabbitType.THE_KILLER_BUNNY), 
    BABY_UNDEAD_HORSE("BABY_UNDEAD_HORSE", 54, "Baby Undead Horse", GEntity.ZOMBIE_HORSE, 0.6, 0.4, true, (GHorseColor)null, GHorseVariant.UNDEAD_HORSE), 
    BABY_WHITE_HORSE("BABY_WHITE_HORSE", 55, "Baby White Horse", GEntity.HORSE, 0.6, 0.4, true, GHorseColor.WHITE, GHorseVariant.HORSE), 
    BABY_WHITE_LLAMA("BABY_WHITE_LLAMA", 56, "Baby White Llama", GEntity.LLAMA, 0.5, 0.4, true, GLlamaColor.WHITE), 
    BABY_WHITE_RABBIT("BABY_WHITE_RABBIT", 57, "Baby White Rabbit", GEntity.RABBIT, 0.5, 0.4, true, GRabbitType.WHITE), 
    BABY_WHITE_SHEEP("BABY_WHITE_SHEEP", 58, "Baby White Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.WHITE), 
    BABY_WILD_OCELOT("BABY_WILD_OCELOT", 59, "Baby Wild Ocelot", GEntity.OCELOT, 0.7, 0.4, true, GOcelotType.WILD_OCELOT), 
    BABY_WOLF("BABY_WOLF", 60, "Baby Wolf", GEntity.WOLF, 0.7, 0.4, true), 
    BABY_YELLOW_SHEEP("BABY_YELLOW_SHEEP", 61, "Baby Yellow Sheep", GEntity.SHEEP, 0.7, 0.4, true, GDyeColor.YELLOW), 
    BABY_ZOMBIE("BABY_ZOMBIE", 62, "Baby Zombie", GEntity.ZOMBIE, 0.6, 0.4, true), 
    BABY_ZOMBIE_VILLAGER("BABY_ZOMBIE_VILLAGER", 63, "Baby Zombie Villager", GEntity.ZOMBIE_VILLAGER, 0.6, 0.4, true, GProfession.BLACKSMITH), 
    BAT("BAT", 64, "Bat", GEntity.BAT, 0.7, 0.45, false), 
    BLACKSMITH_VILLAGER("BLACKSMITH_VILLAGER", 65, "Blacksmith Villager", GEntity.VILLAGER, 0.7, 0.45, false, GProfession.BLACKSMITH), 
    BLACK_AND_WHITE_RABBIT("BLACK_AND_WHITE_RABBIT", 66, "Black And White Rabbit", GEntity.RABBIT, 0.5, 0.4, false, GRabbitType.BLACK_AND_WHITE), 
    BLACK_CAT("BLACK_CAT", 67, "Black Cat", VersionManager.is1_14OrAbove() ? GEntity.CAT : GEntity.OCELOT, 0.7, 0.45, false, GOcelotType.BLACK_CAT), 
    BLACK_HORSE("BLACK_HORSE", 68, "Black Horse", GEntity.HORSE, 0.7, 0.45, false, GHorseColor.BLACK, GHorseVariant.HORSE), 
    BLACK_RABBIT("BLACK_RABBIT", 69, "Black Rabbit", GEntity.RABBIT, 0.5, 0.4, false, GRabbitType.BLACK), 
    BLACK_SHEEP("BLACK_SHEEP", 70, "Black Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.BLACK), 
    BLAZE("BLAZE", 71, "Blaze", GEntity.BLAZE, 0.7, 0.45, false), 
    BLUE_SHEEP("BLUE_SHEEP", 72, "Blue Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.BLUE), 
    BROWN_HORSE("BROWN_HORSE", 73, "Brown Horse", GEntity.HORSE, 0.7, 0.45, false, GHorseColor.BROWN, GHorseVariant.HORSE), 
    BROWN_LLAMA("BROWN_LLAMA", 74, "Brown Llama", GEntity.LLAMA, 0.6, 0.4, false, GLlamaColor.BROWN), 
    BROWN_RABBIT("BROWN_RABBIT", 75, "Brown Rabbit", GEntity.RABBIT, 0.5, 0.4, false, GRabbitType.BROWN), 
    BROWN_SHEEP("BROWN_SHEEP", 76, "Brown Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.BROWN), 
    BUTCHER_VILLAGER("BUTCHER_VILLAGER", 77, "Butcher Villager", GEntity.VILLAGER, 0.7, 0.45, false, GProfession.BUTCHER), 
    CAVE_SPIDER("CAVE_SPIDER", 78, "Cave Spider", GEntity.CAVE_SPIDER, 0.7, 0.45, false), 
    CHESTNUT_HORSE("CHESTNUT_HORSE", 79, "Chestnut Horse", GEntity.HORSE, 0.7, 0.45, false, GHorseColor.CHESTNUT, GHorseVariant.HORSE), 
    CHICKEN("CHICKEN", 80, "Chicken", GEntity.CHICKEN, 0.7, 0.45, false), 
    COW("COW", 81, "Cow", GEntity.COW, 0.7, 0.45, false), 
    CREAMY_HORSE("CREAMY_HORSE", 82, "Creamy Horse", GEntity.HORSE, 0.7, 0.45, false, GHorseColor.CREAMY, GHorseVariant.HORSE), 
    CREAMY_LLAMA("CREAMY_LLAMA", 83, "Creamy Llama", GEntity.LLAMA, 0.6, 0.4, false, GLlamaColor.CREAMY), 
    CREEPER("CREEPER", 84, "Creeper", GEntity.CREEPER, 0.7, 0.45, false), 
    CYAN_SHEEP("CYAN_SHEEP", 85, "Cyan Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.CYAN), 
    DARK_BROWN_HORSE("DARK_BROWN_HORSE", 86, "Dark Brown Horse", GEntity.HORSE, 0.7, 0.45, false, GHorseColor.DARK_BROWN, GHorseVariant.HORSE), 
    DONKEY("DONKEY", 87, "Donkey", GEntity.DONKEY, 0.7, 0.45, false, (GHorseColor)null, GHorseVariant.DONKEY), 
    ENDERMAN("ENDERMAN", 88, "Enderman", GEntity.ENDERMAN, 0.7, 0.45, false), 
    ENDERMITE("ENDERMITE", 89, "Endermite", GEntity.ENDERMITE, 0.6, 0.4, false), 
    EVOKER("EVOKER", 90, "Evoker", GEntity.EVOKER, 0.6, 0.4, false), 
    FARMER_VILLAGER("FARMER_VILLAGER", 91, "Farmer Villager", GEntity.VILLAGER, 0.7, 0.45, false, GProfession.FARMER), 
    GOLD_RABBIT("GOLD_RABBIT", 92, "Gold Rabbit", GEntity.RABBIT, 0.5, 0.4, false, GRabbitType.GOLD), 
    GOLEM("GOLEM", 93, "Golem", GEntity.IRON_GOLEM, 0.6, 0.4, false), 
    GRAY_HORSE("GRAY_HORSE", 94, "Gray Horse", GEntity.HORSE, 0.7, 0.45, false, GHorseColor.GRAY, GHorseVariant.HORSE), 
    GRAY_LLAMA("GRAY_LLAMA", 95, "Gray Llama", GEntity.LLAMA, 0.6, 0.4, false, GLlamaColor.GRAY), 
    GRAY_SHEEP("GRAY_SHEEP", 96, "Gray Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.GRAY), 
    GREEN_SHEEP("GREEN_SHEEP", 97, "Green Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.GREEN), 
    GUARDIAN("GUARDIAN", 98, "Guardian", GEntity.GUARDIAN, 0.7, 0.45, false), 
    HUSK("HUSK", 99, "Husk", GEntity.HUSK, 0.6, 0.4, false, GProfession.HUSK), 
    ILLUSIONER("ILLUSIONER", 100, "Illusioner", GEntity.ILLUSIONER, 0.6, 0.4, false), 
    LIBRARIAN_VILLAGER("LIBRARIAN_VILLAGER", 101, "Librarian Villager", GEntity.VILLAGER, 0.7, 0.45, false, GProfession.LIBRARIAN), 
    LIGHT_BLUE_SHEEP("LIGHT_BLUE_SHEEP", 102, "Light Blue Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.LIGHT_BLUE), 
    LIME_SHEEP("LIME_SHEEP", 103, "Lime Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.LIME), 
    MAGENTA_SHEEP("MAGENTA_SHEEP", 104, "Magenta Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.MAGENTA), 
    MINI_WITHER("MINI_WITHER", 105, "Mini Wither", GEntity.WITHER, 0.7, 0.45, false), 
    MULE("MULE", 106, "Mule", GEntity.MULE, 0.7, 0.45, false, (GHorseColor)null, GHorseVariant.MULE), 
    MUSHROOM_COW("MUSHROOM_COW", 107, "Mushroom Cow", GEntity.MUSHROOM_COW, 0.7, 0.45, false), 
    ORANGE_SHEEP("ORANGE_SHEEP", 108, "Orange Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.ORANGE), 
    PANDA_NORMAL("PANDA_NORMAL", 109, "Normal Panda", GEntity.PANDA, 0.6, 0.4, false, GPandaGene.NORMAL), 
    PANDA_LAZY("PANDA_LAZY", 110, "Lazy Panda", GEntity.PANDA, 0.6, 0.4, false, GPandaGene.LAZY), 
    PANDA_WORRIED("PANDA_WORRIED", 111, "Worried Panda", GEntity.PANDA, 0.6, 0.4, false, GPandaGene.WORRIED), 
    PANDA_PLAYFUL("PANDA_PLAYFUL", 112, "Playful Panda", GEntity.PANDA, 0.6, 0.4, false, GPandaGene.PLAYFUL), 
    PANDA_BROWN("PANDA_BROWN", 113, "Brown Panda", GEntity.PANDA, 0.6, 0.4, false, GPandaGene.BROWN), 
    PANDA_WEAK("PANDA_WEAK", 114, "Weak Panda", GEntity.PANDA, 0.6, 0.4, false, GPandaGene.WEAK), 
    PANDA_AGGRESSIVE("PANDA_AGGRESSIVE", 115, "Aggressive Panda", GEntity.PANDA, 0.6, 0.4, false, GPandaGene.AGGRESSIVE), 
    PIG("PIG", 116, "Pig", GEntity.PIG, 0.7, 0.45, false), 
    PIG_ZOMBIE("PIG_ZOMBIE", 117, "Pig Zombie", GEntity.PIG_ZOMBIE, 0.6, 0.4, false), 
    PINK_SHEEP("PINK_SHEEP", 118, "Pink Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.PINK), 
    POLAR_BEAR("POLAR_BEAR", 119, "Polar Bear", GEntity.POLAR_BEAR, 0.6, 0.4, false), 
    POWERED_CREEPER("POWERED_CREEPER", 120, "Powered Creeper", GEntity.CREEPER, 0.7, 0.45, false, true), 
    PRIEST_VILLAGER("PRIEST_VILLAGER", 121, "Priest Villager", GEntity.VILLAGER, 0.7, 0.45, false, GProfession.PRIEST), 
    PURPLE_SHEEP("PURPLE_SHEEP", 122, "Purple Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.PURPLE), 
    RAINBOW_SHEEP("RAINBOW_SHEEP", 123, "Rainbow Sheep", GEntity.SHEEP, 0.7, 0.45, false), 
    RED_CAT("RED_CAT", 124, "Red Cat", VersionManager.is1_14OrAbove() ? GEntity.CAT : GEntity.OCELOT, 0.7, 0.45, false, GOcelotType.RED_CAT), 
    RED_SHEEP("RED_SHEEP", 125, "Red Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.RED), 
    SALT_AND_PEPPER_RABBIT("SALT_AND_PEPPER_RABBIT", 126, "Salt And Pepper Rabbit", GEntity.RABBIT, 0.5, 0.4, false, GRabbitType.SALT_AND_PEPPER), 
    SIAMESE_CAT("SIAMESE_CAT", 127, "Siamese Cat", VersionManager.is1_14OrAbove() ? GEntity.CAT : GEntity.OCELOT, 0.7, 0.45, false, GOcelotType.SIAMESE_CAT), 
    SILVERFISH("SILVERFISH", 128, "Silverfish", GEntity.SILVERFISH, 0.6, 0.4, false), 
    SILVER_SHEEP("SILVER_SHEEP", 129, "Silver Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.LIGHT_GRAY), 
    SKELETON_HORSE("SKELETON_HORSE", 130, "Skeleton Horse", GEntity.SKELETON_HORSE, 0.7, 0.45, false, (GHorseColor)null, GHorseVariant.SKELETON_HORSE), 
    SKELETON("SKELETON", 131, "Skeleton", GEntity.SKELETON, 0.7, 0.45, GSkeletonType.NORMAL), 
    SLIME_BIG("SLIME_BIG", 132, "Big Slime", GEntity.SLIME, 0.7, 0.45, 4), 
    SLIME_SMALL("SLIME_SMALL", 133, "Small Slime", GEntity.SLIME, 0.7, 0.45, 2), 
    SLIME_TINY("SLIME_TINY", 134, "Tiny Slime", GEntity.SLIME, 0.7, 0.45, 1), 
    MAGMA_CUBE_BIG("MAGMA_CUBE_BIG", 135, "Big Magma Cube", GEntity.MAGMA_CUBE, 0.7, 0.45, 4), 
    MAGMA_CUBE_SMALL("MAGMA_CUBE_SMALL", 136, "Small Magma Cube", GEntity.MAGMA_CUBE, 0.7, 0.45, 2), 
    MAGMA_CUBE_TINY("MAGMA_CUBE_TINY", 137, "Tiny Magma Cube", GEntity.MAGMA_CUBE, 0.7, 0.45, 1), 
    SNOWMAN("SNOWMAN", 138, "Snowman", GEntity.SNOWMAN, 0.6, 0.4, false), 
    SPIDER("SPIDER", 139, "Spider", GEntity.SPIDER, 0.7, 0.45, false), 
    SQUID("SQUID", 140, "Squid", GEntity.SQUID, 0.7, 0.45, false), 
    STRAY_SKELETON("STRAY_SKELETON", 141, "Stray Skeleton", GEntity.STRAY, 0.6, 0.4, GSkeletonType.STRAY), 
    THE_KILLER_BUNNY_RABBIT("THE_KILLER_BUNNY_RABBIT", 142, "The Killer Bunny Rabbit", GEntity.RABBIT, 0.5, 0.4, false, GRabbitType.THE_KILLER_BUNNY), 
    UNDEAD_HORSE("UNDEAD_HORSE", 143, "Undead Horse", GEntity.ZOMBIE_HORSE, 0.7, 0.45, false, (GHorseColor)null, GHorseVariant.UNDEAD_HORSE), 
    VINDICATOR("VINDICATOR", 144, "Vindicator", GEntity.VINDICATOR, 0.6, 0.4, false), 
    WHITE_HORSE("WHITE_HORSE", 145, "White Horse", GEntity.HORSE, 0.7, 0.45, false, GHorseColor.WHITE, GHorseVariant.HORSE), 
    WHITE_LLAMA("WHITE_LLAMA", 146, "White Llama", GEntity.LLAMA, 0.6, 0.4, false, GLlamaColor.WHITE), 
    WHITE_RABBIT("WHITE_RABBIT", 147, "White Rabbit", GEntity.RABBIT, 0.5, 0.4, false, GRabbitType.WHITE), 
    WHITE_SHEEP("WHITE_SHEEP", 148, "White Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.WHITE), 
    WILD_OCELOT("WILD_OCELOT", 149, "Wild Ocelot", GEntity.OCELOT, 0.7, 0.45, false, GOcelotType.WILD_OCELOT), 
    WITCH("WITCH", 150, "Witch", GEntity.WITCH, 0.7, 0.45, false), 
    WITHER_SKELETON("WITHER_SKELETON", 151, "Wither Skeleton", GEntity.WITHER_SKELETON, 0.7, 0.45, GSkeletonType.WITHER), 
    WOLF("WOLF", 152, "Wolf", GEntity.WOLF, 0.7, 0.45, false), 
    YELLOW_SHEEP("YELLOW_SHEEP", 153, "Yellow Sheep", GEntity.SHEEP, 0.7, 0.45, false, GDyeColor.YELLOW), 
    ZOMBIE("ZOMBIE", 154, "Zombie", GEntity.ZOMBIE, 0.6, 0.4, false), 
    ZOMBIE_VILLAGER("ZOMBIE_VILLAGER", 155, "Zombie Villager", GEntity.ZOMBIE_VILLAGER, 0.6, 0.4, false, GProfession.BLACKSMITH), 
    RED_LITTLE_HELPER("RED_LITTLE_HELPER", 156, "Red Little Helper", GEntity.ZOMBIE, 0.6, 0.4, true), 
    GREEN_LITTLE_HELPER("GREEN_LITTLE_HELPER", 157, "Green Little Helper", GEntity.ZOMBIE, 0.6, 0.4, true);
    
    private String name;
    private GEntity entityType;
    private double moveSpeed;
    private double rideSpeed;
    private boolean baby;
    private int size;
    private GDyeColor dyeColor;
    private GHorseColor horseColor;
    private GLlamaColor llamaColor;
    private GHorseVariant variant;
    private GParrotVariant parrotVariant;
    private GOcelotType ocelotType;
    private GRabbitType rabbitType;
    private GSkeletonType skeletonType;
    private GProfession profession;
    private boolean isPowered;
    private GPandaGene pandaGene;
    
    private GEntityType(final String name, final int ordinal, final String name2, final GEntity entityType, final double moveSpeed, final double rideSpeed, final boolean baby) {
        this.name = name2;
        this.entityType = entityType;
        this.moveSpeed = moveSpeed;
        this.rideSpeed = rideSpeed;
        this.baby = baby;
    }
    
    private GEntityType(final String s, final int n, final String s2, final GEntity gEntity, final double n2, final double n3, final int size) {
        this(s, n, s2, gEntity, n2, n3, false);
        this.size = size;
    }
    
    private GEntityType(final String s, final int n, final String s2, final GEntity gEntity, final double n2, final double n3, final boolean b, final GDyeColor dyeColor) {
        this(s, n, s2, gEntity, n2, n3, b);
        this.dyeColor = dyeColor;
    }
    
    private GEntityType(final String s, final int n, final String s2, final GEntity gEntity, final double n2, final double n3, final boolean b, final GLlamaColor llamaColor) {
        this(s, n, s2, gEntity, n2, n3, b);
        this.llamaColor = llamaColor;
    }
    
    private GEntityType(final String s, final int n, final String s2, final GEntity gEntity, final double n2, final double n3, final boolean b, final GPandaGene pandaGene) {
        this(s, n, s2, gEntity, n2, n3, b);
        this.pandaGene = pandaGene;
    }
    
    private GEntityType(final String s, final int n, final String s2, final GEntity gEntity, final double n2, final double n3, final boolean b, final GParrotVariant parrotVariant) {
        this(s, n, s2, gEntity, n2, n3, b);
        this.parrotVariant = parrotVariant;
    }
    
    private GEntityType(final String s, final int n, final String s2, final GEntity gEntity, final double n2, final double n3, final boolean b, final GHorseColor horseColor, final GHorseVariant variant) {
        this(s, n, s2, gEntity, n2, n3, b);
        this.horseColor = horseColor;
        this.variant = variant;
    }
    
    private GEntityType(final String s, final int n, final String s2, final GEntity gEntity, final double n2, final double n3, final boolean b, final GOcelotType ocelotType) {
        this(s, n, s2, gEntity, n2, n3, b);
        this.ocelotType = ocelotType;
    }
    
    private GEntityType(final String s, final int n, final String s2, final GEntity gEntity, final double n2, final double n3, final boolean b, final GRabbitType rabbitType) {
        this(s, n, s2, gEntity, n2, n3, b);
        this.rabbitType = rabbitType;
    }
    
    private GEntityType(final String s, final int n, final String s2, final GEntity gEntity, final double n2, final double n3, final GSkeletonType skeletonType) {
        this(s, n, s2, gEntity, n2, n3, false);
        this.skeletonType = skeletonType;
    }
    
    private GEntityType(final String s, final int n, final String s2, final GEntity gEntity, final double n2, final double n3, final boolean b, final GProfession profession) {
        this(s, n, s2, gEntity, n2, n3, b);
        this.profession = profession;
    }
    
    private GEntityType(final String s, final int n, final String s2, final GEntity gEntity, final double n2, final double n3, final boolean b, final boolean isPowered) {
        this(s, n, s2, gEntity, n2, n3, b);
        this.isPowered = isPowered;
    }
    
    public String getName() {
        return this.name;
    }
    
    public GEntity getGEntity() {
        return this.entityType;
    }
    
    public double getMoveSpeed() {
        return this.moveSpeed;
    }
    
    public double setMoveSpeed(final double moveSpeed) {
        return this.moveSpeed = moveSpeed;
    }
    
    public double getRideSpeed() {
        return this.rideSpeed;
    }
    
    public double setRideSpeed(final double rideSpeed) {
        return this.rideSpeed = rideSpeed;
    }
    
    public boolean isBaby() {
        return this.baby;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public GDyeColor getDyeColor() {
        return this.dyeColor;
    }
    
    public GHorseVariant getVariant() {
        return this.variant;
    }
    
    public Parrot.Variant getParrotVariant() {
        return this.parrotVariant.toParrotVariant();
    }
    
    public GHorseColor getHorseColor() {
        return this.horseColor;
    }
    
    public GLlamaColor getLlamaColor() {
        return this.llamaColor;
    }
    
    public GOcelotType getOcelotType() {
        return this.ocelotType;
    }
    
    public GRabbitType getRabbitType() {
        return this.rabbitType;
    }
    
    public GSkeletonType getSkeletonType() {
        return this.skeletonType;
    }
    
    public GProfession getProfession() {
        return this.profession;
    }
    
    public boolean getIsPowered() {
        return this.isPowered;
    }
    
    public GPandaGene getPandaGene() {
        return this.pandaGene;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static GEntityType valueOfName(final String anotherString) {
        GEntityType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GEntityType gEntityType = values[i];
            if (gEntityType.getName().equalsIgnoreCase(anotherString)) {
                return gEntityType;
            }
        }
        return null;
    }
}
