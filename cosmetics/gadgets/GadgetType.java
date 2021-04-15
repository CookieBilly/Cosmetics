

package ws.billy.CookieGadgets.cosmetics.gadgets;

import java.util.Collections;
import java.util.function.Function;
import java.util.Comparator;
import ws.billy.CookieGadgets.utils.EnumInventorySort;
import java.util.Iterator;
import java.util.Set;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import org.bukkit.entity.Player;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.EnumEquipType;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.CategoryManager;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetDracula;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetTNTFountain;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetPoopBomb;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetPartyPopper;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetGhosts;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetCryotube;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetKawarimiNoJutsu;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetGoldFountain;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetDiamondShower;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetPyromaniac;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetKookieFountain;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetMelonLauncher;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetPaintballGun;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetExplosiveBow;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetRailgun;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetMobGun;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetDJBooth;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetDiscoBall;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetRadio;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetJukebox;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetLetItSnow;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetRocket;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetTeleporter;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetParachute;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetPaintTrail;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetFireTrail;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetTeleportStick;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetCowboy;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetScarecrow;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetBatLauncher;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetExplodingSheep;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetCreeperAstronaut;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetWhenPigsFly;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetCATapult;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetPiggyBank;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetTicTacToe;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetIceCreamStand;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetPocketBeach;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetBBQGrill;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetSandCastle;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetFlowerGiver;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetTrampoline;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetDivingBoard;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetTetherball;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetFortuneCookie;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetMagic9Ball;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.Gadget;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticMaterialType;

public class GadgetType extends CosmeticMaterialType
{
    private static final List<GadgetType> ENABLED;
    private static final List<GadgetType> VALUES;
    private static final HashMap<GadgetCategoryType, ArrayList<GadgetType>> GADGET_CATEGORIES;
    public static final GadgetType MAGIC_9_BALL;
    public static final GadgetType FORTUNE_COOKIE;
    public static final GadgetType TETHERBALL;
    public static final GadgetType DIVING_BOARD;
    public static final GadgetType TRAMPOLINE;
    public static final GadgetType FLOWER_GIVER;
    public static final GadgetType SAND_CASTLE;
    public static final GadgetType BBQ_GRILL;
    public static final GadgetType POCKET_BEACH;
    public static final GadgetType ICE_CREAM_STAND;
    public static final GadgetType TIC_TAC_TOE;
    public static final GadgetType PIGGY_BANK;
    public static final GadgetType CATAPULT;
    public static final GadgetType WHEN_PIGS_FLY;
    public static final GadgetType CREEPER_ASTRONAUT;
    public static final GadgetType EXPLODING_SHEEP;
    public static final GadgetType BAT_LAUNCHER;
    public static final GadgetType SCARECROW;
    public static final GadgetType COWBOY;
    public static final GadgetType TELEPORT_STICK;
    public static final GadgetType FIRE_TRAIL;
    public static final GadgetType PAINT_TRAIL;
    public static final GadgetType PARACHUTE;
    public static final GadgetType TELEPORTER;
    public static final GadgetType ROCKET;
    public static final GadgetType LET_IT_SNOW;
    public static final GadgetType JUKEBOX;
    public static final GadgetType RADIO;
    public static final GadgetType DISCO_BALL;
    public static final GadgetType DJ_BOOTH;
    public static final GadgetType MOB_GUN;
    public static final GadgetType RAIL_GUN;
    public static final GadgetType EXPLOSIVE_BOW;
    public static final GadgetType PAINTBALL_GUN;
    public static final GadgetType MELON_LAUNCHER;
    public static final GadgetType KOOKIE_FOUNTAIN;
    public static final GadgetType PYROMANIAC;
    public static final GadgetType DIAMOND_SHOWER;
    public static final GadgetType GOLD_FOUNTAIN;
    public static final GadgetType KAWARIMI_NO_JUTSU;
    public static final GadgetType CRYOTUBE;
    public static final GadgetType GHOSTS;
    public static final GadgetType PARTY_POPPER;
    public static final GadgetType POOP_BOMB;
    public static final GadgetType TNT_FOUNTAIN;
    public static final GadgetType DRACULA;
    private GadgetCategoryType category;
    private String displayName;
    private GMaterial material;
    private int cooldown;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private Class<? extends Gadget> clazz;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    private ItemStack itemStack;
    
    static {
        ENABLED = new ArrayList<GadgetType>();
        VALUES = new ArrayList<GadgetType>();
        GADGET_CATEGORIES = new HashMap<GadgetCategoryType, ArrayList<GadgetType>>();
        MAGIC_9_BALL = new GadgetType(GadgetCategoryType.Fun_AND_GAMES, "Magic 9 Ball", "&aMagic 9 Ball Gadget", new GMaterial(EnumMaterial.BLACK_WOOL), "CookieGadgets.gadgets.magic9ball", 3, 22, Rarity.COMMON, Arrays.asList("&7Learn the answers to your", "&7problems through some of", "&7the world's greatest", "&7leaders."), GadgetMagic9Ball.class);
        FORTUNE_COOKIE = new GadgetType(GadgetCategoryType.Fun_AND_GAMES, "Fortune Cookie", "&aFortune Cookie Gadget", new GMaterial(EnumMaterial.COOKIE), "CookieGadgets.gadgets.fortunecookie", 0, 10, Rarity.COMMON, Arrays.asList("&7What's better than a", "&7cookie? A cookie that gives", "&7solid life advice! Enjoy a", "&7sweet treat and wise words", "&7with this Fortune Cookie", "&7gadget."), GadgetFortuneCookie.class);
        TETHERBALL = new GadgetType(GadgetCategoryType.Fun_AND_GAMES, "Tetherball", "&5Tetherball Gadget", new GMaterial(EnumMaterial.OAK_FENCE), "CookieGadgets.gadgets.tetherball", 90, 55, Rarity.EPIC, Arrays.asList("&7Spawns a pole, complete", "&7with a tether ball - ", "&7prefect for a quick game of", "&7tetherball!"), GadgetTetherball.class);
        DIVING_BOARD = new GadgetType(GadgetCategoryType.Fun_AND_GAMES, "Diving Board", "&6Diving Board Gadget", new GMaterial(EnumMaterial.CAULDRON), "CookieGadgets.gadgets.divingboard", 60, 75, Rarity.LEGENDARY, Arrays.asList("&7Everything was going", "&7swimmingly, until", "&7suddenly... CANNONBALL!", "&7Show off your aquatic", "&7skills with this Diving", "&7Board gadget."), GadgetDivingBoard.class);
        TRAMPOLINE = new GadgetType(GadgetCategoryType.Fun_AND_GAMES, "Trampoline", "&6Trampoline Gadget", new GMaterial(EnumMaterial.HOPPER), "CookieGadgets.gadgets.trampoline", 80, 92, Rarity.LEGENDARY, Arrays.asList("&7Constructs a trampoline", "&7that sends you into the", "&7air. Invite your friends!"), GadgetTrampoline.class);
        FLOWER_GIVER = new GadgetType(GadgetCategoryType.Fun_AND_GAMES, "Flower Giver", "&9Flower Giver Gadget", new GMaterial(EnumMaterial.FLOWER_POT), "CookieGadgets.gadgets.flowergiver", 5, 25, Rarity.RARE, Arrays.asList("&7Spread the good vibes by", "&7giving flowers to everyone", "&7around you!"), GadgetFlowerGiver.class);
        SAND_CASTLE = new GadgetType(GadgetCategoryType.Fun_AND_GAMES, "Sand Castle", "&9Sand Castle Gadget", new GMaterial("head:9918b3b7516b1946566fd7b287f82ef44afafea9a2c3bd385691f7f0f42097f8"), "CookieGadgets.gadgets.sandcastle", 30, 28, Rarity.RARE, Arrays.asList("&7Build your own castle with", "&7real sand!"), GadgetSandCastle.class);
        BBQ_GRILL = new GadgetType(GadgetCategoryType.Fun_AND_GAMES, "BBQ Grill", "&5BBQ Grill Gadget", new GMaterial(EnumMaterial.OBSIDIAN), "CookieGadgets.gadgets.bbqgrill", 45, 58, Rarity.EPIC, Arrays.asList("&7Come on, you earned it!", "&7Celebrate a real party BBQ", "&7style!"), GadgetBBQGrill.class);
        POCKET_BEACH = new GadgetType(GadgetCategoryType.Fun_AND_GAMES, "Pocket Beach", "&5Pocket Beach Gadget", new GMaterial(EnumMaterial.SAND), "CookieGadgets.gadgets.pocketbeach", 60, 55, Rarity.EPIC, Arrays.asList("&7Can't go to the beach? No", "&7problem! Carry your own", "&7spot in your pocket!"), GadgetPocketBeach.class);
        ICE_CREAM_STAND = new GadgetType(GadgetCategoryType.Fun_AND_GAMES, "Ice Cream Stand", "&6Ice Cream Stand Gadget", new GMaterial("head:239299c36b54bb11e57c686bfe53a5c1c441310f90f69347bacddbf343609d"), "CookieGadgets.gadgets.icecreamstand", 30, 68, Rarity.LEGENDARY, Arrays.asList("&7\"I'll have a vanilla fudge", "&7ice cream, thanks!\"."), GadgetIceCreamStand.class);
        TIC_TAC_TOE = new GadgetType(GadgetCategoryType.Fun_AND_GAMES, "Tic Tac Toe", "&6Tic Tac Toe Gadget", new GMaterial("head:a6864136e374f5d676fb4f5b03e10a7bcdb4190b376378ae9492045fb4adf9d"), "CookieGadgets.gadgets.tictactoe", 30, 85, Rarity.LEGENDARY, Arrays.asList("&7Challenge your friends to a", "&7tic-tac-toe game. Who will", "&7be the winner?"), GadgetTicTacToe.class);
        PIGGY_BANK = new GadgetType(GadgetCategoryType.MOBS_AND_NPCS, "Piggy Bank", "&9Piggy Bank Gadget", new GMaterial("head:198df42f477f213ff5e9d7fa5a4cc4a69f20d9cef2b90c4ae4f29bd17287b5"), "CookieGadgets.gadgets.piggybank", 40, 35, Rarity.RARE, Arrays.asList("&7Explosion a piggy and get", "&7all the gold."), GadgetPiggyBank.class);
        CATAPULT = new GadgetType(GadgetCategoryType.MOBS_AND_NPCS, "CATapult", "&9CATapult Gadget", new GMaterial(EnumMaterial.OCELOT_SPAWN_EGG), "CookieGadgets.gadgets.catapult", 50, 25, Rarity.RARE, Arrays.asList("&7Attack your enemies with", "&7their worst fear, cats!", "&7Launches 5 exploding cats", "&7in the direction you aim."), GadgetCATapult.class);
        WHEN_PIGS_FLY = new GadgetType(GadgetCategoryType.MOBS_AND_NPCS, "When Pigs Fly", "&9When Pigs Fly Gadget", new GMaterial(EnumMaterial.SADDLE), "CookieGadgets.gadgets.whenpigsfly", 30, 28, Rarity.RARE, Arrays.asList("&7Spawns a flying pig with", "&7you riding it into battle!"), GadgetWhenPigsFly.class);
        CREEPER_ASTRONAUT = new GadgetType(GadgetCategoryType.MOBS_AND_NPCS, "Creeper Astronaut", "&9Creeper Astronaut Gadget", new GMaterial(EnumMaterial.CREEPER_SPAWN_EGG), "CookieGadgets.gadgets.creeperastronaut", 40, 30, Rarity.RARE, Arrays.asList("&7Shoot a creeper into the", "&7air, if it makes it to the", "&7top, enjoy the spectacular", "&7show!"), GadgetCreeperAstronaut.class);
        EXPLODING_SHEEP = new GadgetType(GadgetCategoryType.MOBS_AND_NPCS, "Exploding Sheep", "&9Exploding Sheep Gadget", new GMaterial(EnumMaterial.SHEARS), "CookieGadgets.gadgets.explodingsheep", 15, 25, Rarity.RARE, Arrays.asList("&7Right out of Farm Hunt,", "&7it's the one and only", "&7Explosive Sheep back and", "&7more explosive that ever."), GadgetExplodingSheep.class);
        BAT_LAUNCHER = new GadgetType(GadgetCategoryType.MOBS_AND_NPCS, "Bat Launcher", "&9Bat Launcher Gadget", new GMaterial(EnumMaterial.IRON_HORSE_ARMOR), "CookieGadgets.gadgets.batlauncher", 10, 20, Rarity.RARE, Arrays.asList("&7Launcher a wave of bats", "&7to which people you do", "&7not like!"), GadgetBatLauncher.class);
        SCARECROW = new GadgetType(GadgetCategoryType.MOBS_AND_NPCS, "Scarecrow", "&6Scarecrow Gadget", new GMaterial(EnumMaterial.JACK_O_LANTERN), "CookieGadgets.gadgets.scarecrow", 30, 65, Rarity.LEGENDARY, Arrays.asList("&7Spawns a scarecrow that", "&7changes the head of nearby", "&7players into a pumpkin."), GadgetScarecrow.class);
        COWBOY = new GadgetType(GadgetCategoryType.MOVEMENT, "Cowboy", "&5Cowboy Gadget", new GMaterial(EnumMaterial.CACTUS), "CookieGadgets.gadgets.cowboy", 5, 30, Rarity.EPIC, Arrays.asList("&7Allows you to ride the", "&7nearest player. Create", "&7towers by riding other", "&7players!"), GadgetCowboy.class);
        TELEPORT_STICK = new GadgetType(GadgetCategoryType.MOVEMENT, "Teleport Stick", "&9Teleport Stick Gadget", new GMaterial(EnumMaterial.STICK), "CookieGadgets.gadgets.teleportstick", 5, 18, Rarity.RARE, Arrays.asList("&7Allows you to teleport", "&7anywhere by right-clicking", "&7and pointing with this", "&7magical stick!"), GadgetTeleportStick.class);
        FIRE_TRAIL = new GadgetType(GadgetCategoryType.MOVEMENT, "Fire Trail", "&9Fire Trail Gadget", new GMaterial(EnumMaterial.BLAZE_POWDER), "CookieGadgets.gadgets.firetrail", 50, 20, Rarity.RARE, Arrays.asList("&7Gives you Speed II and", "&7give a trail of blazing", "&7fire behind you."), GadgetFireTrail.class);
        PAINT_TRAIL = new GadgetType(GadgetCategoryType.MOVEMENT, "Paint Trail", "&9Paint Trail Gadget", new GMaterial(EnumMaterial.MAGENTA_DYE), "CookieGadgets.gadgets.painttrail", 35, 22, Rarity.RARE, Arrays.asList("&7Leaves a trail of randomly", "&7colored clay behind you", "&7that disappears a few", "&7seconds later."), GadgetPaintTrail.class);
        PARACHUTE = new GadgetType(GadgetCategoryType.MOVEMENT, "Parachute", "&5Parachute Gadget", new GMaterial(EnumMaterial.LEAD), "CookieGadgets.gadgets.parachute", 60, 40, Rarity.EPIC, Arrays.asList("&7Rockets you into the sky,", "&7shortly before deploying", "&7your parachute for a soft", "&7landing."), GadgetParachute.class);
        TELEPORTER = new GadgetType(GadgetCategoryType.MOVEMENT, "Teleporter", "&5Teleporter Gadget", new GMaterial(EnumMaterial.ENDER_EYE), "CookieGadgets.gadgets.teleporter", 30, 45, Rarity.EPIC, Arrays.asList("&7In the blink of an eye you", "&7vanish, and magically", "&7appear somewhere else.", "&7Traverse the space-time", "&7continuum with this", "&7Teleporter gadget."), GadgetTeleporter.class);
        ROCKET = new GadgetType(GadgetCategoryType.MOVEMENT, "Rocket", "&6Rocket Gadget", new GMaterial(EnumMaterial.FIREWORK_ROCKET), "CookieGadgets.gadgets.rocket", 120, 125, Rarity.LEGENDARY, Arrays.asList("&7Blast off to the moon with", "&7this large scale rocket", "&7complete with countdown,", "&7sounds and an explosive", "&7ending."), GadgetRocket.class);
        LET_IT_SNOW = new GadgetType(GadgetCategoryType.MOVEMENT, "Let It Snow", "&9Let It Snow Gadget", new GMaterial(EnumMaterial.SNOW_BLOCK), "CookieGadgets.gadgets.letitsnow", 80, 60, Rarity.RARE, Arrays.asList("&7Release a small snowstorm", "&7around you, covering the", "&7area in white for a short", "&7amount of time!"), GadgetLetItSnow.class);
        JUKEBOX = new GadgetType(GadgetCategoryType.MUSICAL, "Jukebox", "&5Jukebox Gadget", new GMaterial(EnumMaterial.MUSIC_DISC_CAT), "CookieGadgets.gadgets.jukebox", 3, 52, Rarity.EPIC, Arrays.asList("&7Allows you to open a music", "&7player where you can select", "&7from various C418 hit", "&7singles to play."), GadgetJukebox.class);
        RADIO = new GadgetType(GadgetCategoryType.MUSICAL, "Radio", "&6Radio Gadget", new GMaterial(EnumMaterial.MUSIC_DISC_13), "CookieGadgets.gadgets.radio", 3, 105, Rarity.LEGENDARY, Arrays.asList("&7Listen to your favourite", "&7Noteblock hits with this", "&7patented Radio gadget!"), GadgetRadio.class);
        DISCO_BALL = new GadgetType(GadgetCategoryType.MUSICAL, "Disco Ball", "&5Disco Ball Gadget", new GMaterial(EnumMaterial.LIGHT_BLUE_STAINED_GLASS), "CookieGadgets.gadgets.discoball", 80, 60, Rarity.EPIC, Arrays.asList("&7Spawns a Disco Ball,", "&7complete with fancy effects", "&7and music."), GadgetDiscoBall.class);
        DJ_BOOTH = new GadgetType(GadgetCategoryType.MUSICAL, "DJ Booth", "&6DJ Booth Gadget", new GMaterial(EnumMaterial.NOTE_BLOCK), "CookieGadgets.gadgets.djbooth", 60, 85, Rarity.LEGENDARY, Arrays.asList("&7If you ever wanted to", "&7become a DJ, it's now or", "&7never! Mesmerize your", "&7friends with this DJ set", "&7and dance floor."), GadgetDJBooth.class);
        MOB_GUN = new GadgetType(GadgetCategoryType.PROJECTILE, "MobGun", "&9MobGun Gadget", new GMaterial(EnumMaterial.BLAZE_ROD), "CookieGadgets.gadgets.mobgun", 30, 28, Rarity.RARE, Arrays.asList("&7Launch exploding", "&7projectiles using 18", "&7different types of mobs."), GadgetMobGun.class);
        RAIL_GUN = new GadgetType(GadgetCategoryType.PROJECTILE, "Railgun", "&aRailgun Gadget", new GMaterial(EnumMaterial.LEVER), "CookieGadgets.gadgets.railgun", 5, 16, Rarity.COMMON, Arrays.asList("&7It's the basic railgun", "&7ready to fire at will!"), GadgetRailgun.class);
        EXPLOSIVE_BOW = new GadgetType(GadgetCategoryType.PROJECTILE, "Explosive Bow", "&aExplosive Bow Gadget", new GMaterial(EnumMaterial.BOW), "CookieGadgets.gadgets.explosivebow", 8, 10, Rarity.COMMON, Arrays.asList("&7Teleport Sticks are for", "&7losers. All the cool kids", "&7use the Explosive Bow"), GadgetExplosiveBow.class);
        PAINTBALL_GUN = new GadgetType(GadgetCategoryType.PROJECTILE, "Paintball Gun", "&aPaintball Gun Gadget", new GMaterial(EnumMaterial.DIAMOND_HORSE_ARMOR), "CookieGadgets.gadgets.paintballgun", 3, 10, Rarity.COMMON, Arrays.asList("&7What better way to spruce up", "&7the lobby than with a", "&7little color? Or a lot,", "&7who''s counting?"), GadgetPaintballGun.class);
        MELON_LAUNCHER = new GadgetType(GadgetCategoryType.PROJECTILE, "Melon Launcher", "&aMelon Launcher Gadget", new GMaterial(EnumMaterial.MELON), "CookieGadgets.gadgets.melonlauncher", 3, 10, Rarity.COMMON, Arrays.asList("&7Eat the melon slices for", "&7a temporary speed boost!"), GadgetMelonLauncher.class);
        KOOKIE_FOUNTAIN = new GadgetType(GadgetCategoryType.VISUAL, "Kookie Fountain", "&5Kookie Fountain Gadget", new GMaterial(EnumMaterial.COOKIE), "CookieGadgets.gadgets.kookiefountain", 60, 62, Rarity.EPIC, Arrays.asList("&7Makes kookies rain around", "&7you!"), GadgetKookieFountain.class);
        PYROMANIAC = new GadgetType(GadgetCategoryType.VISUAL, "Pyromaniac", "&9Pyromaniac Gadget", new GMaterial(EnumMaterial.FLINT_AND_STEEL), "CookieGadgets.gadgets.pyromaniac", 30, 35, Rarity.RARE, Arrays.asList("&7Ignites you into a ball of", "&7fire for 10 seconds, before", "&7cooling off."), GadgetPyromaniac.class);
        DIAMOND_SHOWER = new GadgetType(GadgetCategoryType.VISUAL, "Diamond Shower", "&5Diamond Shower Gadget", new GMaterial(EnumMaterial.DIAMOND), "CookieGadgets.gadgets.diamondshower", 60, 75, Rarity.EPIC, Arrays.asList("&7Showers you in Diamonds,", "&7fantastic for showing off", "&7your amazing wealth."), GadgetDiamondShower.class);
        GOLD_FOUNTAIN = new GadgetType(GadgetCategoryType.VISUAL, "Gold Fountain", "&9Gold Fountain Gadget", new GMaterial(EnumMaterial.GOLD_NUGGET), "CookieGadgets.gadgets.goldfountain", 60, 70, Rarity.RARE, Arrays.asList("&7What's the best thing to do", "&7with Gold? Making it rain", "&7Gold."), GadgetGoldFountain.class);
        KAWARIMI_NO_JUTSU = new GadgetType(GadgetCategoryType.VISUAL, "Kawarimi No Jutsu", "&9Kawarimi No Jutsu Gadget", new GMaterial(EnumMaterial.OAK_LOG), "CookieGadgets.gadgets.kawariminojutsu", 30, 25, Rarity.RARE, Arrays.asList("&7With this technique, users", "&7replace their own body with", "&7a block of wood."), GadgetKawarimiNoJutsu.class);
        CRYOTUBE = new GadgetType(GadgetCategoryType.VISUAL, "Cryotube", "&9Cryotube Gadget", new GMaterial(EnumMaterial.SNOWBALL), "CookieGadgets.gadgets.cryotube", 45, 25, Rarity.RARE, Arrays.asList("&7Freezes you inside ice,", "&7great if you're planning on", "&7surviving any random", "&7apocalypses."), GadgetCryotube.class);
        GHOSTS = new GadgetType(GadgetCategoryType.VISUAL, "Ghosts", "&9Ghosts Gadget", new GMaterial(EnumMaterial.WITHER_SKELETON_SKULL), "CookieGadgets.gadgets.ghosts", 40, 30, Rarity.RARE, Arrays.asList("&7Spawns floating ghost heads", "&7that fly away before", "&7disappearing as soon as", "&7they came."), GadgetGhosts.class);
        PARTY_POPPER = new GadgetType(GadgetCategoryType.VISUAL, "Party Popper", "&9Party Popper Gadget", new GMaterial(EnumMaterial.ENDER_CHEST), "CookieGadgets.gadgets.partypopper", 60, 50, Rarity.RARE, Arrays.asList("&7Explodes you into a confetti", "&7complete with noises and!", "&7everything to make your lobby", "&7party amazing."), GadgetPartyPopper.class);
        POOP_BOMB = new GadgetType(GadgetCategoryType.VISUAL, "Poop Bomb", "&5Poop Bomb Gadget", new GMaterial(EnumMaterial.COCOA_BEANS), "CookieGadgets.gadgets.poopbomb", 40, 45, Rarity.EPIC, Arrays.asList("&7If the name doesn't say", "&7enough, this is pretty much", "&7just a bomb that explodes", "&7releasing poop everywhere.", "&7Yup."), GadgetPoopBomb.class);
        TNT_FOUNTAIN = new GadgetType(GadgetCategoryType.VISUAL, "TNT Fountain", "&6TNT Fountain Gadget", new GMaterial(EnumMaterial.TNT), "CookieGadgets.gadgets.tntfountain", 80, 90, Rarity.LEGENDARY, Arrays.asList("&7Spawns an animated fountain", "&7that rains down primed TNT."), GadgetTNTFountain.class);
        DRACULA = new GadgetType(GadgetCategoryType.VISUAL, "Dracula", "&aDracula Gadget", new GMaterial(EnumMaterial.GHAST_TEAR), "CookieGadgets.gadgets.dracula", 30, 15, Rarity.COMMON, Arrays.asList("&7Launcher a wave of bats", "&7and give you a temporary", "&7invisibility."), GadgetDracula.class);
    }
    
    private GadgetType(final GadgetCategoryType category, final String s, final String displayName, final GMaterial material, final String s2, final int cooldown, final int mysteryDust, final Rarity rarity, final List<String> list, final Class<? extends Gadget> clazz) {
        super(Category.GADGETS, s, displayName, material, s2, mysteryDust, rarity, list, list);
        this.category = category;
        if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getGadgetsFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".Material") == null) {
            this.material = material;
            FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Material", material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getGadgetsFile().getString(String.valueOf(this.getFilePath()) + ".Material"));
        }
        if (s == "Paintball Gun") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Radius", 3);
            if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".BlackList") != null) {
                FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Blacklist", FileManager.getGadgetsFile().getStringList(String.valueOf(this.getFilePath()) + ".BlackList"));
                FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".BlackList", null);
            }
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Blacklist", Arrays.asList("SAND", "GRAVEL", "SPAWNER"));
        }
        if (s == "Paint Trail") {
            if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".BlackList") != null) {
                FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Blacklist", FileManager.getGadgetsFile().getStringList(String.valueOf(this.getFilePath()) + ".BlackList"));
                FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".BlackList", null);
            }
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Blacklist", Arrays.asList("SPAWNER"));
        }
        if (s == "Disco Ball") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Duration-Seconds", 60);
        }
        if (s == "DJ Booth") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Duration-Seconds", 60);
        }
        if (s == "Bat Launcher") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Affect-Players", true);
        }
        if (s == "Party Popper") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Affect-Players", true);
        }
        if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".Cooldown") == null) {
            this.cooldown = cooldown;
            FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Cooldown", this.cooldown);
        }
        else {
            this.cooldown = FileManager.getGadgetsFile().getInt(String.valueOf(this.getFilePath()) + ".Cooldown");
        }
        if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getGadgetsFile().getInt(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getGadgetsFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getGadgetsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Enabled");
        }
        if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getGadgetsFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getGadgetsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (s == "Magic 9 Ball") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages", Arrays.asList("&eThe Cat says: &fFEED ME.", "&eThe Troll says: &fTrololololololol", "&eThe staff says: &fPlease dont break rules.", "&eYour mom says: &fYou should stop playing so much games.", "&eKevin fisherman says: &fLets catch a fish!", "&eBryan Mills says: &fI will find you, and I will kill you.", "&eAnna says: &fDo you wanna build a snowman?", "&eMinion says: &fBEE DO BEE DO BEE DO!", "&eHerobrine says: &fNo one will survive!", "&eDog says: &fMeow!", "&eDarth Vader says: &fI am your father.", "&eElsa says: &fThe cold never bothered me anyway.", "&eThe Creeper says: &fNo don't worry. I won't blow up in your face", "&eNotch says: &fLife is more fun if you play minecraft.", "&eMario says: &fIt's me, Mario!", "&eCat says: &fBark!", "Bob Ross says: There are no mistakes, only happy accidents."));
        }
        if (s == "Fortune Cookie") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Player", Arrays.asList("&e{PLAYER}&f: Oh look, a cookie!", "&e{PLAYER}&f: That cookie was delicious!", "&e{PLAYER}&f: But wait, there was a piece of paper in it! What does itsay?"));
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Fortune Cookie", Arrays.asList("&eFortune Cookie&f: If you're not happy where you are, take a few steps to the left. If you're still not happy then we cannot help you.", "&eFortune Cookie&f: Some of uslearn from the mistakes of others; the rest are others.", "&eFortune Cookie&f: Tomorrow, take a left turn. Then a right turn. Then another left turn. You have now reached your destination.", "&eFortune Cookie&f: You are unique, just like everybody else.", "&eFortune Cookie&f: You may or may not be an indecisive person.", "&eFortune Cookie&f: The fortune you seek is inside another cookie.", "&eFortune Cookie&f: There are three kinds of people in the world : those who can count, and those who can't.", "&eFortune Cookie&f: In a hairdressing shop with 2 hairdressers, choose the one with the worst haircut.", "&eFortune Cookie&f: A clean bedroom is the sign of a broken computer.", "&eFortune Cookie&f: The early bird gets the work, but that's gross who would want a worm???", "&eFortune Cookie&f: 34% of statistics found on the internet are completely made up!", "&eFortune Cookie&f: If you don't want someone to ask you to do something again, do it terribly the first time!", "&eFortune Cookie&f: Strong people don't put others down. They lift them up and slam them on the ground for maximum damage.", "&eFortune Cookie&f: Don't worry about stats too much - what matters is the fun you have!", "&eFortune Cookie&f: The next sentence is a lie. The previous sentence is the truth.", "&eFortune Cookie&f: If you push hard enough you can get through any obstacle - except a door marked ''pull''!", "&eFortune Cookie&f: If you need to get your family's attention, just turn off the WiFi for a minute!", "&eFortune Cookie&f: Nothing ruins a Friday more then realising it's actually a Tuesday.", "&eFortune Cookie&f: You started out with nothing, and you still have most of it.", "&eFortune Cookie&f: Do it! Just do it! Don't let your dreams be dreams, just do it!", "&eFortune Cookie&f: Life is like a box of chocolates. Some people are soft-centered, some are tough and chewy, and a few are just plain nuts!", "&eFortune Cookie&f: If Mom says no, ask Dad!", "&eFortune Cookie&f: The universe contains protons, neutrons, electrons and morons.", "&eFortune Cookie&f: Take regular breaks from playing - it's good for your health!", "&eFortune Cookie&f: If there aren't any open doors, try a window. If there aren't any open windows, stop trying to break into my house!", "&eFortune Cookie&f: If you think that nobody cares about you, try not paying your bills!", "&eFortune Cookie&f: Only listen to advice gained from cookies - do not trust other fortune-telling foods!", "&eFortune Cookie&f: Never give up, unless you have entered the International Giving Up competition.", "&eFortune Cookie&f: Make someone else's day by being a good sport, and somebody will make yours!", "&eFortune Cookie&f: When life gives you lemons, make lemonade. When life gives you limes, throw the limes out the window and buy some damn lemonade!", "&eFortune Cookie&f: If you're not supposed to eat after dark, why is there a light in the refrigerator?", "&eFortune Cookie&f: Change your password to ''incorrect''. That way if you forget it, your computer will tell you what it is!", "&eFortune Cookie&f: If you forget what you look like, just look into a mirror. If the mirror doesn't look back, it's a window!", "&eFortune Cookie&f: You'll win that *insert game* game. Just keep trying until you succeed!", "&eFortune Cookie&f: The only substitute for good manners is fast reflexes.", "&eFortune Cookie&f: Relationships are like algebra. You look at your X and wonder Y?"));
        }
        if (s == "Diving Board") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages", Arrays.asList("&eBackflip!", "&eFrontflip!", "&eSwan Dive", "&eBelly Flop!", "&eCannonball!"));
        }
        if (s == "Flower Giver") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Target-A-Player", "&cRight click a player to give them a flower!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Already-Had-A-Flower", "&cThat player already had a flower.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Sent-A-Flower", "&eYou sent &r{PLAYER} &ea flower!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Already-Sent-A-Request", "&cYou've already sent a flower request to &r{PLAYER}&c.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Received-Flower", "{PLAYER} &ehas given you a flower!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Accept", "&a&l[ACCEPT]");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Decline", "&c&l[DECLINE]");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Accept-Json-message", "&7Click here to accept the flower.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Decline-Json-message", "&7Click here to decline the flower.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.No-Pending-Invite", "&cYou don't have a pending invite from that player.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Accepted-Flower", "{PLAYER} &aaccepted your flower!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Declined-Flower", "{PLAYER} &cdeclined your flower!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Right-Click-To-Remove-Flower", "&eRight-Click to remove your flower!");
        }
        if (s == "Ice Cream Stand") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages", Arrays.asList("&eIce Cream Guy&f: Hazelnuts topping... Double chcolate... And vanilla... Forgot anything?", "&eIce Cream Guy&f: Strawberry icecream, my favorite!", "&eIce Cream Guy&f: I'm afraid I don't have change at the minute :(", "&eIce Cream Guy&f: Sorry, we only accept cash for now!"));
        }
        if (s == "Tic Tac Toe") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Target-A-Player", "&cRight click a player to initiate a game of Tic Tac Toe with them!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Target-Other-Player", "&cThat player currently playing Tic Tac Toe. Please right click other player to initiate a game of Tic Tac Toe with them!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Challenge", "&eYou challenged {PLAYER} &eto a game of Tic Tac Toe.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Already-Sent-A-Request", "&cYou've already sent a challenge request to &r{PLAYER}&c.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Received-Challenge", "{PLAYER} &ehas challenged you to a game of Tic Tac Toe.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Accept", "&a&l[ACCEPT]");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Decline", "&c&l[DECLINE]");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Accept-Json-message", "&7Click here to accept the challenge.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Decline-Json-message", "&7Click here to decline the challenge.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.No-Pending-Challenge", "&cYou don't have a pending challenge from that player.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Accepted-Challenge", "{PLAYER} &aaccepted your challenge!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Declined-Challenge", "{PLAYER} &cdeclined your challenge!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Draw", "&cThe Tic Tac Toe game with &r{PLAYER} &cended in a draw.");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Win", "&aYou defeated &r{PLAYER} &ain Tic Tac Toe!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Loss", "{PLAYER} &cdefeated you in Tic Tac Toe!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Opponent-Left-Game", "&cYour opponent left the Tic Tac Toe game!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Player-Left-Game", "&cYou left the Tic Tac Toe game!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.GUI-Name", "Tic Tac Toe - {OPPONENT}");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Place-Symbol.Name", "&aClick here!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Place-Symbol.Material", new GMaterial(EnumMaterial.ITEM_FRAME).getCombinedMaterial());
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Place-Symbol.Lore", Arrays.asList("&7Click here to place your", "&7symbol here."));
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Not-Your-Turn.Name", "&cIt's not your turn!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Not-Your-Turn.Material", new GMaterial(EnumMaterial.PAINTING).getCombinedMaterial());
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Not-Your-Turn.Lore", Arrays.asList("&7It's currently not your", "&7turn - you can't do", "&7anything."));
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Challenger.Name", "&6Orange: &f{PLAYER}");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Challenger.Material", new GMaterial(EnumMaterial.ORANGE_TERRACOTTA).getCombinedMaterial());
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Challenger.Lore", Arrays.asList("&7Orange player is", "&7{PLAYER}&7."));
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Opponent.Name", "&bBlue: &f{PLAYER}");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Opponent.Material", new GMaterial(EnumMaterial.LIGHT_BLUE_TERRACOTTA).getCombinedMaterial());
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Opponent.Lore", Arrays.asList("&7Blue player is", "&7{PLAYER}&7."));
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Challenger-Symbol.Name", "&6Orange");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Challenger-Symbol.Material", new GMaterial(EnumMaterial.ORANGE_TERRACOTTA).getCombinedMaterial());
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Challenger-Symbol.Lore", Arrays.asList("&7This slot belongs to", "&7Orange."));
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Opponent-Symbol.Name", "&bBlue");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Opponent-Symbol.Material", new GMaterial(EnumMaterial.LIGHT_BLUE_TERRACOTTA).getCombinedMaterial());
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Opponent-Symbol.Lore", Arrays.asList("&7This slot belongs to Blue."));
        }
        if (s == "Teleporter") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Initiating-Teleport-Sequence", "&e&lInitiating teleport sequence. Please stand by!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Scanning-Players", "&e&lScanning players for a target to teleport to...");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Target-Acquired", "&e&lTarget acquired: &k&lRANDOMCHARATERS&e&l!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Teleport-Initiating", "&e&lBuckle up! Teleport initiating...");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Teleport-Complete", "&e&lTeleport complete! Thank you for using Teleporter Fast Travel!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Process-Failed", "&c&lProcess \"find target\" failed! Could not locate a valid target!");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Messages.Aborted-Teleport-Sequence", "&c&lAborted teleporting sequence...");
        }
        if (s == "Teleport Stick") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Range", 30);
        }
        if (s == "Scarecrow") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Jack-O-Lantern-DisplayName", "&cScarecrow");
        }
        if (s == "Exploding Sheep") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Remain-Time", "&c&l{REMAIN_TIME}s");
        }
        if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                }
                else {
                    FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getGadgetsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getGadgetsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getGadgetsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getGadgetsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.clazz = clazz;
        this.itemStack = ItemUtils.item(this.displayName, this.material);
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Cosmetics", "true");
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Category", Category.GADGETS.getNBTTag());
        if (!GadgetType.VALUES.contains(this)) {
            GadgetType.VALUES.add(this);
        }
        if (!GadgetType.GADGET_CATEGORIES.containsKey(this.category)) {
            GadgetType.GADGET_CATEGORIES.put(this.category, new ArrayList<GadgetType>());
        }
        if (s == "Jukebox") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.GUI-Name", "Jukebox");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Track.Name", "&b{TRACK}");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Track.Lore", Arrays.asList("", "&eClick to play!"));
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Stop-Track.Name", "&cStop Track");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Stop-Track.Material", new GMaterial(EnumMaterial.RED_WOOL).getCombinedMaterial());
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Stop-Track.Lore", Arrays.asList("&7Stop playing the current track."));
        }
        if (s == "Radio") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.GUI-Name", "Radio");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Track.Name", "&b{TRACK}");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Track.Material", new GMaterial(EnumMaterial.NOTE_BLOCK).getCombinedMaterial());
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Track.Lore", Arrays.asList("", "&eClick to play!"));
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Stop-Track.Name", "&cStop Track");
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Stop-Track.Material", new GMaterial(EnumMaterial.RED_WOOL).getCombinedMaterial());
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Items.Stop-Track.Lore", Arrays.asList("&7Stop playing the current track."));
        }
        if (FileManager.getGadgetsFile().get("Gadgets.Musical.Types.Broadcast Radio") != null) {
            FileManager.getGadgetsFile().set("Gadgets.Musical.Types.Broadcast Radio", null);
        }
        if (s == "MobGun") {
            FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Actionbar-Message", "{GADGET} &r- {MOB}");
            String[] name;
            for (int length = (name = GadgetMobGun.name).length, i = 0; i < length; ++i) {
                final String str = name[i];
                FileManager.getGadgetsFile().addDefault(String.valueOf(this.getFilePath()) + ".Mob-Name." + str, str);
            }
        }
        if (s == "Jukebox" && !VersionManager.is1_10OrAbove()) {
            this.isEnable = false;
            if (GadgetType.VALUES.contains(this)) {
                GadgetType.VALUES.remove(this);
            }
        }
        if (s == "Tetherball" && (VersionManager.is1_8Version() || VersionManager.is1_14OrAbove())) {
            this.isEnable = false;
            if (GadgetType.VALUES.contains(this)) {
                GadgetType.VALUES.remove(this);
            }
        }
    }
    
    public GadgetCategoryType getGadgetCategory() {
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
    
    public int getCooldown() {
        return this.cooldown;
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
    
    public Class<? extends Gadget> getClazz() {
        return this.clazz;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
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
    
    @Override
    public String getFilePath() {
        return "Gadgets." + this.category.getName() + ".Types." + super.getName();
    }
    
    @Override
    public void equip(final PlayerManager playerManager) {
        if (!this.isEnabled() || !Category.GADGETS.isEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory().getItem(gadgetSlot) != null) {
            CategoryManager.removeHotbarCosmetic(playerManager);
            if (player.getInventory().getItem(gadgetSlot) != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ITEM_FROM_SLOT_TO_EQUIP_GADGET.getFormatMessage().replace("{SLOT}", String.valueOf(gadgetSlot + 1)).replace("{ITEM}", player.getInventory().getItem(gadgetSlot).getType().name()))) {
                    return;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    final Player player2;
                    final int n;
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        player2.getWorld().dropItemNaturally(player2.getLocation(), player2.getInventory().getItem(n).clone());
                        player2.getInventory().setItem(n, (ItemStack)null);
                        player2.updateInventory();
                        return;
                    });
                }
            }
        }
        playerManager.setSelectedCategoryGadget(this.getGadgetCategory());
        playerManager.setEquippedGadget(this);
        try {
            this.clazz.getDeclaredConstructor(UUID.class).newInstance((player == null) ? null : player.getUniqueId());
        }
        catch (InstantiationException ex) {
            ex.printStackTrace();
        }
        catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        catch (InvocationTargetException ex3) {
            ex3.printStackTrace();
        }
        catch (NoSuchMethodException ex4) {
            ex4.printStackTrace();
        }
        player.getInventory().setItem(gadgetSlot, this.getItemStack());
        player.updateInventory();
    }
    
    @Override
    public void unequip(final PlayerManager playerManager) {
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory().getItem(gadgetSlot) != null || playerManager != null || playerManager.getEquippedGadget() != null) {
            if (player.getInventory().getItem(gadgetSlot) != null && player.getInventory().getItem(gadgetSlot).getItemMeta() != null && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getItem(gadgetSlot), "Category", Category.GADGETS.getNBTTag())) {
                player.getInventory().setItem(gadgetSlot, (ItemStack)null);
                player.updateInventory();
            }
            if (playerManager.getSelectedCategoryGadget() != null) {
                playerManager.setSelectedCategoryGadget(null);
            }
            if (playerManager.getEquippedGadget() != null) {
                playerManager.setEquippedGadget(null);
            }
            if (playerManager.getCurrentGadget() != null) {
                playerManager.getCurrentGadget().onClear();
                playerManager.removeGadget();
            }
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
        if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.GADGETS)) {
            player.sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return false;
        }
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory().getItem(gadgetSlot) != null) {
            CategoryManager.removeHotbarCosmetic(playerManager);
            if (player.getInventory().getItem(gadgetSlot) != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ITEM_FROM_SLOT_TO_EQUIP_GADGET.getFormatMessage().replace("{SLOT}", String.valueOf(gadgetSlot + 1)).replace("{ITEM}", player.getInventory().getItem(gadgetSlot).getType().name()))) {
                    return false;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    final Player player2;
                    final int n;
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        player2.getWorld().dropItemNaturally(player2.getLocation(), player2.getInventory().getItem(n).clone());
                        player2.getInventory().setItem(n, (ItemStack)null);
                        player2.updateInventory();
                        return;
                    });
                }
            }
        }
        return true;
    }
    
    public static List<GadgetType> enabled() {
        return GadgetType.ENABLED;
    }
    
    public static List<GadgetType> values() {
        return GadgetType.VALUES;
    }
    
    public static Set<GadgetCategoryType> gadgetCategories() {
        return GadgetType.GADGET_CATEGORIES.keySet();
    }
    
    public static void checkEnabled() {
        for (final GadgetType gadgetType : values()) {
            if (gadgetType.isEnabled() && !GadgetType.ENABLED.contains(gadgetType)) {
                GadgetType.ENABLED.add(gadgetType);
            }
            if (gadgetType.isEnabled() && GadgetType.GADGET_CATEGORIES.containsKey(gadgetType.getGadgetCategory()) && !GadgetType.GADGET_CATEGORIES.get(gadgetType.getGadgetCategory()).contains(gadgetType)) {
                GadgetType.GADGET_CATEGORIES.get(gadgetType.getGadgetCategory()).add(gadgetType);
            }
        }
    }
    
    public static void sortItems() {
        for (final ArrayList<GadgetType> list : GadgetType.GADGET_CATEGORIES.values()) {
            if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.NAME) {
                Collections.sort((List<Object>)list, Comparator.comparing((Function<? super Object, ? extends Comparable>)GadgetType::getDisplayNameStripColor));
            }
            else {
                if (CookieGadgets.getCookieGadgetsData().getInventorySorting() != EnumInventorySort.RARITY) {
                    continue;
                }
                Collections.sort((List<Object>)list, Comparator.comparing((Function<? super Object, ? extends Comparable>)GadgetType::getRarity).thenComparing((Function<? super Object, ? extends Comparable>)GadgetType::getDisplayNameStripColor));
            }
        }
    }
    
    public static List<GadgetType> getGadgetCategory(final GadgetCategoryType gadgetCategoryType) {
        if (GadgetType.GADGET_CATEGORIES.containsKey(gadgetCategoryType)) {
            return GadgetType.GADGET_CATEGORIES.get(gadgetCategoryType);
        }
        return null;
    }
    
    public static GadgetType valueOf(final String anotherString) {
        for (final GadgetType gadgetType : values()) {
            if (gadgetType.getName().equalsIgnoreCase(anotherString)) {
                return gadgetType;
            }
        }
        return null;
    }
}
