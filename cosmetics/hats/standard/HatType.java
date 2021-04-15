// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.cosmetics.hats.standard;

import java.util.Collections;
import java.util.function.Function;
import java.util.Comparator;
import ws.billy.CookieGadgets.utils.EnumInventorySort;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.EnumEquipType;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.CategoryManager;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.GMaterial;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticType;

public class HatType extends CosmeticType
{
    private static final List<HatType> ENABLED;
    private static final List<HatType> VALUES;
    public static final HatType HAMBURGER;
    public static final HatType CHOCOLATE_DONUT;
    public static final HatType SANWICH;
    public static final HatType BLACK_CHOCOLATE_BAR;
    public static final HatType WHITE_CHOCOLATE_BAR;
    public static final HatType CANDY_CANE;
    public static final HatType COMPUTER;
    public static final HatType GOLD_STEVE;
    public static final HatType DIAMOND_STEVE;
    public static final HatType EMERALD_STEVE;
    public static final HatType GOLD_BLOCK;
    public static final HatType DIAMOND_BLOCK;
    public static final HatType SCARED;
    public static final HatType ANGEL;
    public static final HatType EMBARRASSED;
    public static final HatType SAD;
    public static final HatType COOL;
    public static final HatType SURPRISED;
    public static final HatType DEAD;
    public static final HatType CRY;
    public static final HatType GRIN;
    public static final HatType WINK;
    public static final HatType DERP;
    public static final HatType MUSTACHE;
    public static final HatType BIG_SMILE;
    public static final HatType SMILE;
    public static final HatType NEUTRAL;
    public static final HatType FALL_IN_LOVE;
    public static final HatType NETHERLANDS;
    public static final HatType NORWAY;
    public static final HatType SWEDEN;
    public static final HatType CHILE;
    public static final HatType MONACO;
    public static final HatType CANADA;
    public static final HatType UNITED_STATES;
    public static final HatType ITALY;
    public static final HatType ENGLAND;
    public static final HatType GERMANY;
    public static final HatType SINGAPORE;
    public static final HatType FRANCE;
    public static final HatType SPAIN;
    public static final HatType AUSTRALIA;
    public static final HatType CHINA;
    public static final HatType LETTER_A;
    public static final HatType LETTER_B;
    public static final HatType LETTER_C;
    public static final HatType LETTER_D;
    public static final HatType LETTER_E;
    public static final HatType LETTER_F;
    public static final HatType LETTER_G;
    public static final HatType LETTER_H;
    public static final HatType LETTER_I;
    public static final HatType LETTER_J;
    public static final HatType LETTER_K;
    public static final HatType LETTER_L;
    public static final HatType LETTER_M;
    public static final HatType LETTER_N;
    public static final HatType LETTER_O;
    public static final HatType LETTER_P;
    public static final HatType LETTER_Q;
    public static final HatType LETTER_R;
    public static final HatType LETTER_S;
    public static final HatType LETTER_T;
    public static final HatType LETTER_U;
    public static final HatType LETTER_V;
    public static final HatType LETTER_W;
    public static final HatType LETTER_X;
    public static final HatType LETTER_Y;
    public static final HatType LETTER_Z;
    public static final HatType RAINBOW_LETTER_A;
    public static final HatType RAINBOW_LETTER_B;
    public static final HatType RAINBOW_LETTER_C;
    public static final HatType RAINBOW_LETTER_D;
    public static final HatType RAINBOW_LETTER_E;
    public static final HatType RAINBOW_LETTER_F;
    public static final HatType RAINBOW_LETTER_G;
    public static final HatType RAINBOW_LETTER_H;
    public static final HatType RAINBOW_LETTER_I;
    public static final HatType RAINBOW_LETTER_J;
    public static final HatType RAINBOW_LETTER_K;
    public static final HatType RAINBOW_LETTER_L;
    public static final HatType RAINBOW_LETTER_M;
    public static final HatType RAINBOW_LETTER_N;
    public static final HatType RAINBOW_LETTER_O;
    public static final HatType RAINBOW_LETTER_P;
    public static final HatType RAINBOW_LETTER_Q;
    public static final HatType RAINBOW_LETTER_R;
    public static final HatType RAINBOW_LETTER_S;
    public static final HatType RAINBOW_LETTER_T;
    public static final HatType RAINBOW_LETTER_U;
    public static final HatType RAINBOW_LETTER_V;
    public static final HatType RAINBOW_LETTER_W;
    public static final HatType RAINBOW_LETTER_X;
    public static final HatType RAINBOW_LETTER_Y;
    public static final HatType RAINBOW_LETTER_Z;
    public static final HatType NUMBER_0;
    public static final HatType NUMBER_1;
    public static final HatType NUMBER_2;
    public static final HatType NUMBER_3;
    public static final HatType NUMBER_4;
    public static final HatType NUMBER_5;
    public static final HatType NUMBER_6;
    public static final HatType NUMBER_7;
    public static final HatType NUMBER_8;
    public static final HatType NUMBER_9;
    public static final HatType SYMBOL_PLUS;
    public static final HatType SYMBOL_POUND_KEY;
    public static final HatType SYMBOL_QUESTION;
    public static final HatType SYMBOL_EXCLAMATION;
    public static final HatType AQUARIUS_CONSTELLATION;
    public static final HatType PISCES_CONSTELLATION;
    public static final HatType ARIES_CONSTELLATION;
    public static final HatType TAURUS_CONSTELLATION;
    public static final HatType GEMINI_CONSTELLATION;
    public static final HatType CANCER_CONSTELLATION;
    public static final HatType LEO_CONSTELLATION;
    public static final HatType VIRGO_CONSTELLATION;
    public static final HatType LIBRA_CONSTELLATION;
    public static final HatType SCORPIO_CONSTELLATION;
    public static final HatType SAGITTARIUS_CONSTELLATION;
    public static final HatType CAPRICORN_CONSTELLATION;
    public static final HatType BLAZE;
    public static final HatType ENDERMAN;
    public static final HatType SLIME;
    public static final HatType MAGMA_CUBE;
    public static final HatType OCELOT;
    public static final HatType ENDER_DRAGON;
    public static final HatType CAVE_SPIDER;
    public static final HatType GHAST;
    public static final HatType PIG_ZOMBIE;
    public static final HatType CHICKEN;
    public static final HatType PIG;
    public static final HatType COW;
    public static final HatType MUSHROOM_COW;
    public static final HatType SQUID;
    public static final HatType IRON_GOLEM;
    public static final HatType HORSE;
    public static final HatType HEROBINE;
    public static final HatType BIRD;
    public static final HatType POKE_BALL;
    public static final HatType MARIO;
    public static final HatType NURSE;
    public static final HatType FREDDY_FAZBEAR;
    public static final HatType BONNIE;
    public static final HatType JAKE;
    public static final HatType DOGE;
    public static final HatType COOL_DOGE;
    public static final HatType POLAR_BEAR;
    public static final HatType RABBIT;
    public static final HatType KOALA;
    public static final HatType BEE;
    public static final HatType CLOWNFISH;
    public static final HatType FERRET;
    public static final HatType WALRUS;
    public static final HatType TIGER;
    public static final HatType MONKEY;
    public static final HatType CACTUS;
    public static final HatType DUCK;
    public static final HatType EARTH;
    public static final HatType BEACH_BALL;
    public static final HatType SNOWGLOBE;
    public static final HatType TOASTER;
    public static final HatType CHEESE;
    public static final HatType MARS;
    public static final HatType PENGUIN;
    public static final HatType ELEPHANT;
    public static final HatType ASTRONAUT;
    public static final HatType OTTER;
    public static final HatType MUMMY;
    public static final HatType ORC;
    public static final HatType MINOTAUR;
    public static final HatType DEMON_KNIGHT;
    public static final HatType WHITE_WIZARD;
    public static final HatType MINER;
    public static final HatType MONK;
    public static final HatType WOOD_ELF;
    public static final HatType PIRATE;
    public static final HatType ODIN;
    public static final HatType GHOST;
    public static final HatType SKULL;
    public static final HatType PUMPKIN;
    public static final HatType SCARECROW;
    public static final HatType FOX;
    public static final HatType PUG;
    public static final HatType OWL;
    public static final HatType PANDA;
    public static final HatType SLOTH;
    public static final HatType GORILLA;
    public static final HatType SNOWMAN;
    public static final HatType REINDEER;
    public static final HatType SHULKER;
    public static final HatType TURTLE;
    public static final HatType SOLVED_RUBIKS_CUBE;
    public static final HatType SCRAMBLED_RUBIKS_CUBE;
    public static final HatType RAINBOW_GLITCH;
    public static final HatType PACMAN;
    private String displayName;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private GMaterial material;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    private ItemStack itemStack;
    
    static {
        ENABLED = new ArrayList<HatType>();
        VALUES = new ArrayList<HatType>();
        HAMBURGER = new HatType("Hamburger", "&aHamburger Hat", "CookieGadgets.hats.hamburger", 12, Rarity.COMMON, null, "a6ef1c25f516f2e7d6f7667420e33adcf3cdf938cb37f9a41a8b35869f569b");
        CHOCOLATE_DONUT = new HatType("Chocolate Donut", "&aChocolate Donut Hat", "CookieGadgets.hats.chocolatedonut", 15, Rarity.COMMON, null, "59da54ff366e738e31de92919986abb4d50ca944fa9926af63758b7448f18");
        SANWICH = new HatType("Sandwich", "&aSandwich Hat", "CookieGadgets.hats.sandwich", 15, Rarity.COMMON, null, "9496589fb5c1f69387b7fb17d92312058ff6e8ebeb3eb89e4f73e78196113b");
        BLACK_CHOCOLATE_BAR = new HatType("Black Chocolate Bar", "&aBlack Chocolate Bar Hat", "CookieGadgets.hats.blackchocolatebar", 15, Rarity.COMMON, null, "819f948d17718adace5dd6e050c586229653fef645d7113ab94d17b639cc466");
        WHITE_CHOCOLATE_BAR = new HatType("White Chocolate Bar", "&aWhite Chocolate Bar Hat", "CookieGadgets.hats.whitechocolatebar", 15, Rarity.COMMON, null, "1ed55260dccc8da59338c75e41d544a2e1e7dbef31a69fe42c01b3298bf2d");
        CANDY_CANE = new HatType("Candy Cane", "&9Candy Cane Hat", "CookieGadgets.hats.candycane", 25, Rarity.RARE, null, "4cc3f781c923a2887f14c1eea11050166966f2602578401f1451e6097b979df");
        COMPUTER = new HatType("Computer", "&aComputer Hat", "CookieGadgets.hats.computer", 12, Rarity.COMMON, null, "8ae52ae8c98ac19fd07637a469ffa256ab0b3b10ece6243186188ba38df154");
        GOLD_STEVE = new HatType("Gold Steve", "&9Gold Steve Hat", "CookieGadgets.hats.goldstevehead", 28, Rarity.RARE, null, "f937e1c45bb8da29b2c564dd9a7da780dd2fe54468a5dfb4113b4ff658f043e1");
        DIAMOND_STEVE = new HatType("Diamond Steve", "&5Diamond Steve Hat", "CookieGadgets.hats.diamondstevehead", 38, Rarity.EPIC, null, "10b8eb333622ae7de9b53b3602f41f63db9c2528b5be231ac96516611fb1a");
        EMERALD_STEVE = new HatType("Emerald Steve", "&6Emerald Steve Hat", "CookieGadgets.hats.emeraldstevehead", 48, Rarity.LEGENDARY, null, "b5b656da666d2759e8195642142e119e6585852c6619e2ad79ae2ad181465");
        GOLD_BLOCK = new HatType("Gold Block", "&9Gold Block Hat", "CookieGadgets.hats.goldblock", 28, Rarity.RARE, null, "da14ff912566d4719812788e633ba423d1db1449adfbb7061fafe74ba8809469");
        DIAMOND_BLOCK = new HatType("Diamond Block", "&5Diamond Block Hat", "CookieGadgets.hats.diamondblock", 35, Rarity.EPIC, null, "1e057996cba47328df72fba3ea2b9aa35c8a8227f1cec89c1884dcadaa824d85");
        SCARED = new HatType("Scared", "&aScared Hat", "CookieGadgets.hats.scared", 8, Rarity.COMMON, null, "636e26c44659e8148ed58aa79e4d60db595f426442116f81b5415c2446ed8");
        ANGEL = new HatType("Angel", "&aAngel Hat", "CookieGadgets.hats.angel", 8, Rarity.COMMON, null, "3e1debc73231f8ed4b69d5c3ac1b1f18f3656a8988e23f2e1bdbc4e85f6d46a");
        EMBARRASSED = new HatType("Embarrassed", "&aEmbarrassed Hat", "CookieGadgets.hats.embarrassed", 8, Rarity.COMMON, null, "f720df911c052377065408db78a25c678f791eb944c063935ae86dbe51c71b");
        SAD = new HatType("Sad", "&aSad Hat", "CookieGadgets.hats.sad", 8, Rarity.COMMON, null, "14968ac5af3146826fa2b0d4dd114fda197f8b28f4750553f3f88836a21fac9");
        COOL = new HatType("Cool", "&aCool Hat", "CookieGadgets.hats.cool", 8, Rarity.COMMON, null, "868f4cef949f32e33ec5ae845f9c56983cbe13375a4dec46e5bbfb7dcb6");
        SURPRISED = new HatType("Surprised", "&aSurprised Hat", "CookieGadgets.hats.surprised", 8, Rarity.COMMON, null, "bc2b9b9ae622bd68adff7180f8206ec4494abbfa130e94a584ec692e8984ab2");
        DEAD = new HatType("Dead", "&aDead Hat", "CookieGadgets.hats.dead", 8, Rarity.COMMON, null, "b371e4e1cf6a1a36fdae27137fd9b8748e6169299925f9af2be301e54298c73");
        CRY = new HatType("Cry", "&aCry Hat", "CookieGadgets.hats.cry", 8, Rarity.COMMON, null, "1f1b875de49c587e3b4023ce24d472ff27583a1f054f37e73a1154b5b5498");
        GRIN = new HatType("Grin", "&aGrin Hat", "CookieGadgets.hats.grin", 8, Rarity.COMMON, null, "5059d59eb4e59c31eecf9ece2f9cf3934e45c0ec476fc86bfaef8ea913ea710");
        WINK = new HatType("Wink", "&aWink Hat", "CookieGadgets.hats.wink", 8, Rarity.COMMON, null, "f4ea2d6f939fefeff5d122e63dd26fa8a427df90b2928bc1fa89a8252a7e");
        DERP = new HatType("Derp", "&aDerp Hat", "CookieGadgets.hats.derp", 8, Rarity.COMMON, null, "3baabe724eae59c5d13f442c7dc5d2b1c6b70c2f83364a488ce5973ae80b4c3");
        MUSTACHE = new HatType("Mustache", "&aMustache Hat", "CookieGadgets.hats.mustache", 8, Rarity.COMMON, null, "3636f2724aa6aa4de7ac46c19f3c845fb14847a518c8f7e03d792c82effb1");
        BIG_SMILE = new HatType("Big Smile", "&aBig Smile Hat", "CookieGadgets.hats.bigsmile", 8, Rarity.COMMON, null, "7ffaccf17879b17891fc5ef66472cc066a85bfa31b6d786c32afee4796068d");
        SMILE = new HatType("Smile", "&aSmile Hat", "CookieGadgets.hats.smile", 8, Rarity.COMMON, null, "52e98165deef4ed621953921c1ef817dc638af71c1934a4287b69d7a31f6b8");
        NEUTRAL = new HatType("Neutral", "&aNeutral Hat", "CookieGadgets.hats.neutral", 8, Rarity.COMMON, null, "8115dcc17b2e15cd41831885d7dbb8ff2e9cac4fec7080358fe55f93eea19b");
        FALL_IN_LOVE = new HatType("Fall In Love", "&aFall In Love Hat", "CookieGadgets.hats.fallinlove", 8, Rarity.COMMON, null, "129fb9f593b6ae533dfa8ce79615fcc35894a42cbb41de598d694767352fe");
        NETHERLANDS = new HatType("Netherlands", "&aNetherlands Hat", "CookieGadgets.hats.netherlands", 15, Rarity.COMMON, null, "c23cf210edea396f2f5dfbced69848434f93404eefeabf54b23c073b090adf");
        NORWAY = new HatType("Norway", "&aNorway Hat", "CookieGadgets.hats.norway", 15, Rarity.COMMON, null, "e0596e165ec3f389b59cfdda93dd6e363e97d9c6456e7c2e123973fa6c5fda");
        SWEDEN = new HatType("Sweden", "&aSweden Hat", "CookieGadgets.hats.sweden", 15, Rarity.COMMON, null, "e910904bff9c86f6ed47688e9429c26e8d9c5d5743bd3ebb8e6f5040be192998");
        CHILE = new HatType("Chile", "&aChile Hat", "CookieGadgets.hats.chile", 15, Rarity.COMMON, null, "ed1dddc665614c9f6487ba9c666da7579561589a494ef744aaf8f4f88a16");
        MONACO = new HatType("Monaco", "&aMonaco Hat", "CookieGadgets.hats.monaco", 15, Rarity.COMMON, null, "5db2678ccaba7934412cb97ee16d416463a392574c5906352f18dea42895ee");
        CANADA = new HatType("Canada", "&aCanada Hat", "CookieGadgets.hats.canada", 15, Rarity.COMMON, null, "f241a697f6dfb1c57cda327baa6732a7828c398be4ebfdbd166c232bcae2b");
        UNITED_STATES = new HatType("United States", "&aUnited States Hat", "CookieGadgets.hats.unitedstates", 15, Rarity.COMMON, null, "7d15d566202ac0e76cd897759df5d01c11f991bd46c5c9a04357ea89ee75");
        ITALY = new HatType("Italy", "&aItaly Hat", "CookieGadgets.hats.italy", 15, Rarity.COMMON, null, "a56c5cc17319a6c9ec847252e4d274552d97da95e1085072dba49d117cf3");
        ENGLAND = new HatType("England", "&aEngland Hat", "CookieGadgets.hats.england", 15, Rarity.COMMON, null, "bee5c850afbb7d8843265a146211ac9c615f733dcc5a8e2190e5c247dea32");
        GERMANY = new HatType("Germany", "&aGermany Hat", "CookieGadgets.hats.germany", 15, Rarity.COMMON, null, "5e7899b4806858697e283f084d9173fe487886453774626b24bd8cfecc77b3f");
        SINGAPORE = new HatType("Singapore", "&aSingapore Hat", "CookieGadgets.hats.singapore", 15, Rarity.COMMON, null, "8b5ed11f797f3fc61eaf8dafb6bf3234d31b96ab7596bd2df722d2ef3473c27");
        FRANCE = new HatType("France", "&aFrance Hat", "CookieGadgets.hats.france", 15, Rarity.COMMON, null, "ba25287d1140fb1741d4b6f7e65672f9e64fffe80ea7371c7f3ec5a6f04039");
        SPAIN = new HatType("Spain", "&aSpain Hat", "CookieGadgets.hats.spain", 15, Rarity.COMMON, null, "e24b42abb01aa619d5ebca9259f2ffd9b3c1fbf697d3a798e3746db77817d");
        AUSTRALIA = new HatType("Australia", "&aAustralia Hat", "CookieGadgets.hats.australia", 15, Rarity.COMMON, null, "cf4aa2a244784d48b155ff044b8cf96df5bd4e87e01924a75d62a9242a16cf");
        CHINA = new HatType("China", "&aChina Hat", "CookieGadgets.hats.china", 15, Rarity.COMMON, null, "7f9bc035cdc80f1ab5e1198f29f3ad3fdd2b42d9a69aeb64de990681800b98dc");
        LETTER_A = new HatType("Letter-A", "&a''A'' Hat", "CookieGadgets.hats.letter_a", 7, Rarity.COMMON, null, "9c60da2944a177dd08268fbec04e40812d1d929650be66529b1ee5e1e7eca");
        LETTER_B = new HatType("Letter-B", "&a''B'' Hat", "CookieGadgets.hats.letter_b", 7, Rarity.COMMON, null, "8041f5e86983d36eaec4e167b2bbb5a3727607cde88f7555ca1b522a039bb");
        LETTER_C = new HatType("Letter-C", "&a''C'' Hat", "CookieGadgets.hats.letter_c", 7, Rarity.COMMON, null, "d945996c8ae91e376196d4dc676fec31feac790a2f195b2981a703ca1d16cb6");
        LETTER_D = new HatType("Letter-D", "&a''D'' Hat", "CookieGadgets.hats.letter_d", 7, Rarity.COMMON, null, "1641150f481e8492f7128c948996254d2d91fc90f5a8ff4d8ac5c39a6a88a");
        LETTER_E = new HatType("Letter-E", "&a''E'' Hat", "CookieGadgets.hats.letter_e", 7, Rarity.COMMON, null, "db251487ff8eef2ebc7a57dab6e3d9f1db7fc926ddc66fea14afe3dff15a45");
        LETTER_F = new HatType("Letter-F", "&a''F'' Hat", "CookieGadgets.hats.letter_f", 7, Rarity.COMMON, null, "7e433656b443668ed03dac8c442722a2a41221be8bb48e23b35bd8c2e59f63");
        LETTER_G = new HatType("Letter-G", "&a''G'' Hat", "CookieGadgets.hats.letter_g", 7, Rarity.COMMON, null, "995863b73637605feacbb173b77d5e155e65204c78d5c7911f738f28deb60");
        LETTER_H = new HatType("Letter-H", "&a''H'' Hat", "CookieGadgets.hats.letter_h", 7, Rarity.COMMON, null, "3c1d358d927074289cc26bff5b1240746f9f4f0cc46f942f5981c6595f72dd");
        LETTER_I = new HatType("Letter-I", "&a''I'' Hat", "CookieGadgets.hats.letter_i", 7, Rarity.COMMON, null, "8f2295865bda4e47979d36b8a887a75a13b034e6988f78670b64a1e6442c");
        LETTER_J = new HatType("Letter-J", "&a''J'' Hat", "CookieGadgets.hats.letter_j", 7, Rarity.COMMON, null, "e34462b55d7f5823680ad13f2adbd7d1ed46ba5101017ed4b37aeeeb775d");
        LETTER_K = new HatType("Letter-K", "&a''K'' Hat", "CookieGadgets.hats.letter_k", 7, Rarity.COMMON, null, "773325a935c067b6ef227367f62ca4bf49f67adb9f6da32091e2d32c5dde328");
        LETTER_L = new HatType("Letter-L", "&a''L'' Hat", "CookieGadgets.hats.letter_l", 7, Rarity.COMMON, null, "25a1e3328c571aa495d9c5f494815cca176c3acb184feb5a7b9c96ce8e52fce");
        LETTER_M = new HatType("Letter-M", "&a''M'' Hat", "CookieGadgets.hats.letter_m", 7, Rarity.COMMON, null, "d467bf6be95e5c8e9d01977a2f0c487ed5b0de5c87963a2eb15411c442fb2b");
        LETTER_N = new HatType("Letter-N", "&a''N'' Hat", "CookieGadgets.hats.letter_n", 7, Rarity.COMMON, null, "823e434d6395fe7e63492431bdee5782bd5ee5bc8cab7559467bdd1f93b925a");
        LETTER_O = new HatType("Letter-O", "&a''O'' Hat", "CookieGadgets.hats.letter_o", 7, Rarity.COMMON, null, "88445466bdc5ad5bcea82239c4e1b510f6ea5262d82d8a96d7291c342fb89");
        LETTER_P = new HatType("Letter-P", "&a''P'' Hat", "CookieGadgets.hats.letter_p", 7, Rarity.COMMON, null, "f9de601dee3ffeca4d54595f844201d0ed2091acec4548c696bb16a8a158f6");
        LETTER_Q = new HatType("Letter-Q", "&a''Q'' Hat", "CookieGadgets.hats.letter_q", 7, Rarity.COMMON, null, "66ca769bde25d4cc41e19e42adc35ab4c1557b76af232649acc9967ff198f13");
        LETTER_R = new HatType("Letter-R", "&a''R'' Hat", "CookieGadgets.hats.letter_r", 7, Rarity.COMMON, null, "67a188805162ca5dd4f4649c661d3f6d23c42662aef01645b1a97f78b3f13219");
        LETTER_S = new HatType("Letter-S", "&a''S'' Hat", "CookieGadgets.hats.letter_s", 7, Rarity.COMMON, null, "60d09dfd9f5de6243233e0e3325b6c3479335e7ccf13f2448d4e1f7fc4a0df");
        LETTER_T = new HatType("Letter-T", "&a''T'' Hat", "CookieGadgets.hats.letter_t", 7, Rarity.COMMON, null, "64c75619b91d241f678350ad9237c134c5e08d87d6860741ede306a4ef91");
        LETTER_U = new HatType("Letter-U", "&a''U'' Hat", "CookieGadgets.hats.letter_u", 7, Rarity.COMMON, null, "e9f6d2c6d5285f882ae55d1e91b8f9efdfc9b377208bf4c83f88dd156415e");
        LETTER_V = new HatType("Letter-V", "&a''V'' Hat", "CookieGadgets.hats.letter_v", 7, Rarity.COMMON, null, "dce27a153635f835237d85c6bf74f5b1f2e638c48fee8c83038d0558d41da7");
        LETTER_W = new HatType("Letter-W", "&a''W'' Hat", "CookieGadgets.hats.letter_w", 7, Rarity.COMMON, null, "aedcf4ffcb53b56d42baac9d0dfb118e343462327442dd9b29d49f50a7d38b");
        LETTER_X = new HatType("Letter-X", "&a''X'' Hat", "CookieGadgets.hats.letter_x", 7, Rarity.COMMON, null, "83618ff1217640bec5b525fa2a8e671c75d2a7d7cb2ddc31d79d9d895eab1");
        LETTER_Y = new HatType("Letter-Y", "&a''Y'' Hat", "CookieGadgets.hats.letter_y", 7, Rarity.COMMON, null, "d9c1d29a38bcf113b7e8c34e148a79f9fe41edf41aa8b1de873bb1d433b3861");
        LETTER_Z = new HatType("Letter-Z", "&a''Z'' Hat", "CookieGadgets.hats.letter_z", 7, Rarity.COMMON, null, "b9295734195d2c7fa389b98757e9686ce6437c16c58bdf2b4cd538389b5912");
        RAINBOW_LETTER_A = new HatType("Rainbow-Letter-A", "&aRainbow ''A'' Hat", "CookieGadgets.hats.rainbowletter_a", 15, Rarity.COMMON, null, "a517b4829b83192bd72711277a8efc4196711e4180c22b3e2b8166bea1a9de19");
        RAINBOW_LETTER_B = new HatType("Rainbow-Letter-B", "&aRainbow ''B'' Hat", "CookieGadgets.hats.rainbowletter_b", 15, Rarity.COMMON, null, "e1131aca5fcfe6e58f616ff8befd027416fe6b98eb5ec642e035ed8339607bf0");
        RAINBOW_LETTER_C = new HatType("Rainbow-Letter-C", "&aRainbow ''C'' Hat", "CookieGadgets.hats.rainbowletter_c", 15, Rarity.COMMON, null, "b2e594ea15486eb19261f2111e95837ad6e9a6b1d549c70ecfe7f83e41362b57");
        RAINBOW_LETTER_D = new HatType("Rainbow-Letter-D", "&aRainbow ''D'' Hat", "CookieGadgets.hats.rainbowletter_d", 15, Rarity.COMMON, null, "6b31b79e380df31d5a4d649b1ae9fc02067d7e99487122d04d6d6ab7f7de6181");
        RAINBOW_LETTER_E = new HatType("Rainbow-Letter-E", "&aRainbow ''E'' Hat", "CookieGadgets.hats.rainbowletter_e", 15, Rarity.COMMON, null, "b77165c9db763a9acd13c06220e92d3c970dfa36dac56e5957d02d36f5a9f0b8");
        RAINBOW_LETTER_F = new HatType("Rainbow-Letter-F", "&aRainbow ''F'' Hat", "CookieGadgets.hats.rainbowletter_f", 15, Rarity.COMMON, null, "8442b066e0e5e09a6e6bb9989cc27451f2bd78fb0dc72108aa940fc9db1c24e1");
        RAINBOW_LETTER_G = new HatType("Rainbow-Letter-G", "&aRainbow ''G'' Hat", "CookieGadgets.hats.rainbowletter_g", 15, Rarity.COMMON, null, "5c1a8fc8ea45d744307916eb50ddca5e40065a3461a8e469490439f9e24f4f24");
        RAINBOW_LETTER_H = new HatType("Rainbow-Letter-H", "&aRainbow ''H'' Hat", "CookieGadgets.hats.rainbowletter_h", 15, Rarity.COMMON, null, "dca24ac8c13d21720ff5acbf2eee7270c5b3662383208db93721d0549b45b9e5");
        RAINBOW_LETTER_I = new HatType("Rainbow-Letter-I", "&aRainbow ''I'' Hat", "CookieGadgets.hats.rainbowletter_i", 15, Rarity.COMMON, null, "84bcc9531ade2e0639a6ae03c78bc07a1a9be60fc6f3e3fe39370fb56c6b5976");
        RAINBOW_LETTER_J = new HatType("Rainbow-Letter-J", "&aRainbow ''J'' Hat", "CookieGadgets.hats.rainbowletter_j", 15, Rarity.COMMON, null, "eb060bbe4d6d601469b49e105253ebae05293098a974b6f2d5684f941ca5a5fc");
        RAINBOW_LETTER_K = new HatType("Rainbow-Letter-K", "&aRainbow ''K'' Hat", "CookieGadgets.hats.rainbowletter_k", 15, Rarity.COMMON, null, "b37e51ce0d4c8eadf675941a45e10b928e43d22eab539ac886edbf40bb87ec0f");
        RAINBOW_LETTER_L = new HatType("Rainbow-Letter-L", "&aRainbow ''L'' Hat", "CookieGadgets.hats.rainbowletter_l", 15, Rarity.COMMON, null, "206bc417e3c06b22735d539f9c6c8fd7c1efd19236e2c381534051d9d6bee804");
        RAINBOW_LETTER_M = new HatType("Rainbow-Letter-M", "&aRainbow ''M'' Hat", "CookieGadgets.hats.rainbowletter_m", 15, Rarity.COMMON, null, "1d716256d727ba1df18f826f119051c33a394209a95be837ccf6fae9ee6b871b");
        RAINBOW_LETTER_N = new HatType("Rainbow-Letter-N", "&aRainbow ''N'' Hat", "CookieGadgets.hats.rainbowletter_n", 15, Rarity.COMMON, null, "e713d2601e35242d35018cece3b34c61bf5001f5dbd7463a4c5587ac365b3d1f");
        RAINBOW_LETTER_O = new HatType("Rainbow-Letter-O", "&aRainbow ''O'' Hat", "CookieGadgets.hats.rainbowletter_o", 15, Rarity.COMMON, null, "35385b05e7af54635b10f02cdb0045672c91c724cf164e5193a4f7be7292ff30");
        RAINBOW_LETTER_P = new HatType("Rainbow-Letter-P", "&aRainbow ''P'' Hat", "CookieGadgets.hats.rainbowletter_p", 15, Rarity.COMMON, null, "b553141aabe89a8a5804a172133b43d5d0ee0549cc19db0385684043cfa946a5");
        RAINBOW_LETTER_Q = new HatType("Rainbow-Letter-Q", "&aRainbow ''Q'' Hat", "CookieGadgets.hats.rainbowletter_q", 15, Rarity.COMMON, null, "a4d746e7e3534e729956f1a04757832fa3bf9e2d14ef6d0db8dcfc4e21532338");
        RAINBOW_LETTER_R = new HatType("Rainbow-Letter-R", "&aRainbow ''R'' Hat", "CookieGadgets.hats.rainbowletter_r", 15, Rarity.COMMON, null, "55827f45aae65681bb27e3e0465af6228ed6292bb63b0a7764596224727f8d81");
        RAINBOW_LETTER_S = new HatType("Rainbow-Letter-S", "&aRainbow ''S'' Hat", "CookieGadgets.hats.rainbowletter_s", 15, Rarity.COMMON, null, "dcd7d14c6db841e5864511d16ba7670b3d2038142466981feb05afc6e5edc6cb");
        RAINBOW_LETTER_T = new HatType("Rainbow-Letter-T", "&aRainbow ''T'' Hat", "CookieGadgets.hats.rainbowletter_t", 15, Rarity.COMMON, null, "b94ac36d9a6fbff1c558941381e4dcf595df825913f6c383ffaa71b756a875d3");
        RAINBOW_LETTER_U = new HatType("Rainbow-Letter-U", "&aRainbow ''U'' Hat", "CookieGadgets.hats.rainbowletter_u", 15, Rarity.COMMON, null, "e8060faec45097eefa68088a5c07657743426e0453f8b66cf26b839c04864c00");
        RAINBOW_LETTER_V = new HatType("Rainbow-Letter-V", "&aRainbow ''V'' Hat", "CookieGadgets.hats.rainbowletter_v", 15, Rarity.COMMON, null, "fa3fa916b5e5915e026b91b264544398ff02d1eede7630bc8158f3a663ca02ad");
        RAINBOW_LETTER_W = new HatType("Rainbow-Letter-W", "&aRainbow ''W'' Hat", "CookieGadgets.hats.rainbowletter_w", 15, Rarity.COMMON, null, "23324d1fa070cf698f2be539d69ff438aaa6b1f494c5e013c7ee9d9c33eb83c0");
        RAINBOW_LETTER_X = new HatType("Rainbow-Letter-X", "&aRainbow ''X'' Hat", "CookieGadgets.hats.rainbowletter_x", 15, Rarity.COMMON, null, "55d5c75f6675edc292ea37846077970d226fbd524e7fd6808f3a4781a549b08c");
        RAINBOW_LETTER_Y = new HatType("Rainbow-Letter-Y", "&aRainbow ''Y'' Hat", "CookieGadgets.hats.rainbowletter_y", 15, Rarity.COMMON, null, "1ad30e9e25705c51b846e74e7779623b69c0744645da0004d4db0fe46336ff8e");
        RAINBOW_LETTER_Z = new HatType("Rainbow-Letter-Z", "&aRainbow ''Z'' Hat", "CookieGadgets.hats.rainbowletter_z", 15, Rarity.COMMON, null, "9a24b0f6c184ff173686c7d128df536d10b7280f8008636a5546f1c777234354");
        NUMBER_0 = new HatType("Number-0", "&a''0'' Hat", "CookieGadgets.hats.number_0", 7, Rarity.COMMON, null, "3769fc56dc8f1acaaa2f1c598b4b6269f16e58eb1171c20783a86c23454abe7");
        NUMBER_1 = new HatType("Number-1", "&a''1'' Hat", "CookieGadgets.hats.number_1", 7, Rarity.COMMON, null, "6148891f75c6502b7cc48ea3d1f58752da4fe389efe7324f2b4446aaf2a823");
        NUMBER_2 = new HatType("Number-2", "&a''2'' Hat", "CookieGadgets.hats.number_2", 7, Rarity.COMMON, null, "e8ed5fff389cf8151b2bebac8513bccc58ec10a4fe3819c35efb340b8f2");
        NUMBER_3 = new HatType("Number-3", "&a''3'' Hat", "CookieGadgets.hats.number_3", 7, Rarity.COMMON, null, "3bf03e919cd7ac69ac214baffad998a5bd4b1d11ede333f1ce92d6da417b5c4");
        NUMBER_4 = new HatType("Number-4", "&a''4'' Hat", "CookieGadgets.hats.number_4", 7, Rarity.COMMON, null, "c2d164a1a96318cd6471d9a64a48ce02341fb126c3452bdcd4be188f2f973ce");
        NUMBER_5 = new HatType("Number-5", "&a''5'' Hat", "CookieGadgets.hats.number_5", 7, Rarity.COMMON, null, "7c5418df281e5bb2e175cc804b9244cdc95914c8d28deded5769f65b7ce02d5");
        NUMBER_6 = new HatType("Number-6", "&a''6'' Hat", "CookieGadgets.hats.number_6", 7, Rarity.COMMON, null, "7326af92298364546955377491ad8ac99db40528fbe3cdb817f768a7c9e63b8");
        NUMBER_7 = new HatType("Number-7", "&a''7'' Hat", "CookieGadgets.hats.number_7", 7, Rarity.COMMON, null, "a6d97c32a04c5b9f57712873bbade868488acb9ef6d9b5118debd06abec9a2");
        NUMBER_8 = new HatType("Number-8", "&a''8'' Hat", "CookieGadgets.hats.number_8", 7, Rarity.COMMON, null, "942e2979d1497c31ee03e19b088684ab155957527411f31b279b2a8dbb96ee");
        NUMBER_9 = new HatType("Number-9", "&a''9'' Hat", "CookieGadgets.hats.number_9", 7, Rarity.COMMON, null, "ee5553d1a51dd719c99e8e86d1b9b3901b11889a839ccd7129c1bd128264e1a8");
        SYMBOL_PLUS = new HatType("Symbol-Plus", "&a''+'' Hat", "CookieGadgets.hats.symbol_plus", 7, Rarity.COMMON, null, "f8c874651f7124d142b707afd33b6c336ce09cea38cdcb5596e76ac757bf");
        SYMBOL_POUND_KEY = new HatType("Symbol-Pound-Key", "&a''#'' Hat", "CookieGadgets.hats.symbol_poundkey", 7, Rarity.COMMON, null, "b23578a8d6e4b5f2a53ca70d4925716bc8e56839596cc17be726c50252ccd");
        SYMBOL_QUESTION = new HatType("Symbol-Question", "&a''?'' Hat", "CookieGadgets.hats.symbol_question", 7, Rarity.COMMON, null, "8b85be7db31e879ab75216b55414e1f3856f72f29b62d1f6bb945a8251cf7fb");
        SYMBOL_EXCLAMATION = new HatType("Symbol-Exclamation", "&a''!'' Hat", "CookieGadgets.hats.symbol_exclamation", 7, Rarity.COMMON, null, "7a492fff53c47b5ec388aaee56ada7f4c60b65576b4161d66f53b5e63017bd");
        AQUARIUS_CONSTELLATION = new HatType("Aquarius Constellation", "&9''Aquarius'' Hat", "CookieGadgets.hats.aquarius", 28, Rarity.RARE, null, "208d163f963b7298b12b7fd3c8f9ef6eeec1d8298161aa71da822e9c2d629b5c");
        PISCES_CONSTELLATION = new HatType("Pisces Constellation", "&9''Pisces'' Hat", "CookieGadgets.hats.pisces", 28, Rarity.RARE, null, "92ba17e760b4eaf6ce77f7c579c1fbbc942c7e43e88448626fa64535c4bd8bc9");
        ARIES_CONSTELLATION = new HatType("Aries Constellation", "&9''Aries'' Hat", "CookieGadgets.hats.aries", 28, Rarity.RARE, null, "89e627d1278b2600f8fa5efe88173192782818556ca93433f4b5ef47570ac18e");
        TAURUS_CONSTELLATION = new HatType("Taurus Constellation", "&9''Taurus'' Hat", "CookieGadgets.hats.taurus", 28, Rarity.RARE, null, "d13f2690540098174e31a5857bc6aa69eb20e2c89540d2a1d7c49ea3ad1514e1");
        GEMINI_CONSTELLATION = new HatType("Gemini Constellation", "&9''Gemini'' Hat", "CookieGadgets.hats.gemini", 28, Rarity.RARE, null, "2a4cc5321f5e17d4a39e015c56a95eab5ab478e0a04eec8a5681ff6807a6321c");
        CANCER_CONSTELLATION = new HatType("Cancer Constellation", "&9''Cancer'' Hat", "CookieGadgets.hats.cancer", 28, Rarity.RARE, null, "3778d66b562d25d2a39f259091c14ac9698a1535d99c549c1fdeeafb4b33d113");
        LEO_CONSTELLATION = new HatType("Leo Constellation", "&9''Leo'' Hat", "CookieGadgets.hats.leo", 28, Rarity.RARE, null, "c8875808ffdcfa87e58ba0cd6c0f1d18cc367a65dd46b2975b4422b9dde7e209");
        VIRGO_CONSTELLATION = new HatType("Virgo Constellation", "&9''Virgo'' Hat", "CookieGadgets.hats.virgo", 28, Rarity.RARE, null, "480a305414f1b3137c92c4162a50efa147cec0a8a1ba88919c9b70ffe5173c6a");
        LIBRA_CONSTELLATION = new HatType("Libra Constellation", "&9''Libra'' Hat", "CookieGadgets.hats.libra", 28, Rarity.RARE, null, "3811af1c87674a04361d27709b38c55c90bc8ddb1cfd849d02c15dc6c4b33778");
        SCORPIO_CONSTELLATION = new HatType("Scorpio Constellation", "&9''Scorpio'' Hat", "CookieGadgets.hats.scorpio", 28, Rarity.RARE, null, "a88e1a1e843278d297a9e8cf99d7581cb336298b7f6fe1702793c0bad915330b");
        SAGITTARIUS_CONSTELLATION = new HatType("Sagittarius Constellation", "&9''Sagittarius'' Hat", "CookieGadgets.hats.sagittarius", 28, Rarity.RARE, null, "d13bc25e432b0f5d631ff500d657a46c0c27fe9539bafa3155dd2c2cfbfdb097");
        CAPRICORN_CONSTELLATION = new HatType("Capricorn Constellation", "&9''Capricorn'' Hat", "CookieGadgets.hats.capricorn", 28, Rarity.RARE, null, "28cda54df2e217f325fc980350ab261154b3da5b022769e2cd38f5c959480452");
        BLAZE = new HatType("Blaze", "&aBlaze Hat", "CookieGadgets.hats.blaze", 12, Rarity.COMMON, null, "b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0");
        ENDERMAN = new HatType("Enderman", "&aEnderman Hat", "CookieGadgets.hats.enderman", 12, Rarity.COMMON, null, "7a59bb0a7a32965b3d90d8eafa899d1835f424509eadd4e6b709ada50b9cf");
        SLIME = new HatType("Slime", "&aSlime Hat", "CookieGadgets.hats.slime", 12, Rarity.COMMON, null, "16ad20fc2d579be250d3db659c832da2b478a73a698b7ea10d18c9162e4d9b5");
        MAGMA_CUBE = new HatType("Magma Cube", "&aMagma Cube Hat", "CookieGadgets.hats.magmacube", 15, Rarity.COMMON, null, "38957d5023c937c4c41aa2412d43410bda23cf79a9f6ab36b76fef2d7c429");
        OCELOT = new HatType("Ocelot", "&aOcelot Hat", "CookieGadgets.hats.ocelot", 12, Rarity.COMMON, null, "5657cd5c2989ff97570fec4ddcdc6926a68a3393250c1be1f0b114a1db1");
        ENDER_DRAGON = new HatType("Ender Dragon", "&9Ender Dragon Hat", "CookieGadgets.hats.enderdragon", 18, Rarity.RARE, null, "74ecc040785e54663e855ef0486da72154d69bb4b7424b7381ccf95b095a");
        CAVE_SPIDER = new HatType("Cave Spider", "&aCave Spider Hat", "CookieGadgets.hats.cavespider", 12, Rarity.COMMON, null, "41645dfd77d09923107b3496e94eeb5c30329f97efc96ed76e226e98224");
        GHAST = new HatType("Ghast", "&9Ghast Hat", "CookieGadgets.hats.ghast", 25, Rarity.RARE, null, "8b6a72138d69fbbd2fea3fa251cabd87152e4f1c97e5f986bf685571db3cc0");
        PIG_ZOMBIE = new HatType("Pig Zombie", "&9Pig Zombie Hat", "CookieGadgets.hats.pigzombie", 18, Rarity.RARE, null, "74e9c6e98582ffd8ff8feb3322cd1849c43fb16b158abb11ca7b42eda7743eb");
        CHICKEN = new HatType("Chicken", "&aChicken Hat", "CookieGadgets.hats.chicken", 12, Rarity.COMMON, null, "1638469a599ceef7207537603248a9ab11ff591fd378bea4735b346a7fae893");
        PIG = new HatType("Pig", "&aPig Hat", "CookieGadgets.hats.pig", 12, Rarity.COMMON, null, "621668ef7cb79dd9c22ce3d1f3f4cb6e2559893b6df4a469514e667c16aa4");
        COW = new HatType("Cow", "&aCow Hat", "CookieGadgets.hats.cow", 12, Rarity.COMMON, null, "5d6c6eda942f7f5f71c3161c7306f4aed307d82895f9d2b07ab4525718edc5");
        MUSHROOM_COW = new HatType("Mushroom Cow", "&aMushroom Cow Hat", "CookieGadgets.hats.mushroomcow", 15, Rarity.COMMON, null, "d0bc61b9757a7b83e03cd2507a2157913c2cf016e7c096a4d6cf1fe1b8db");
        SQUID = new HatType("Squid", "&9Squid Hat", "CookieGadgets.hats.squid", 20, Rarity.RARE, null, "01433be242366af126da434b8735df1eb5b3cb2cede39145974e9c483607bac");
        IRON_GOLEM = new HatType("Iron Golem", "&9Iron Golem Hat", "CookieGadgets.hats.irongolem", 25, Rarity.RARE, null, "89091d79ea0f59ef7ef94d7bba6e5f17f2f7d4572c44f90f76c4819a714");
        HORSE = new HatType("Horse", "&9Horse Hat", "CookieGadgets.hats.horse", 15, Rarity.RARE, null, "61902898308730c4747299cb5a5da9c25838b1d059fe46fc36896fee662729");
        HEROBINE = new HatType("Herobine", "&9Herobine Hat", "CookieGadgets.hats.herobine", 25, Rarity.RARE, null, "98b7ca3c7d314a61abed8fc18d797fc30b6efc8445425c4e250997e52e6cb");
        BIRD = new HatType("Bird", "&aBird Hat", "CookieGadgets.hats.bird", 12, Rarity.COMMON, null, "b9627370fedbd0bae7bae6d6f8583555763789c1bd93fa639cfa3dfd48e34850");
        POKE_BALL = new HatType("Poke Ball", "&aPoke Ball Hat", "CookieGadgets.hats.pokeball", 12, Rarity.COMMON, null, "d43d4b7ac24a1d650ddf73bd140f49fc12d2736fc14a8dc25c0f3f29d85f8f");
        MARIO = new HatType("Mario", "&aMario Hat", "CookieGadgets.hats.mario", 8, Rarity.COMMON, null, "dba8d8e53d8a5a75770b62cce73db6bab701cc3de4a9b654d213d54af9615");
        NURSE = new HatType("Nurse", "&aNurse Hat", "CookieGadgets.hats.nurse", 8, Rarity.COMMON, null, "d9362c5fd1d42e563697ad5c282b1e565d671d42156f44e77e87e86016dce80f");
        FREDDY_FAZBEAR = new HatType("Freddy Fazbear", "&aFreddy Fazbear Hat", "CookieGadgets.hats.freddyfazbear", 12, Rarity.COMMON, null, "ed3f3e114c631cadc8a5606021b4b4f9e15fa6ec89d3eeeb1cec825cf29b883");
        BONNIE = new HatType("Bonnie", "&aBonnie Hat", "CookieGadgets.hats.bonnie", 12, Rarity.COMMON, null, "2f3faca3d13e6ec373d7a28dab8959fc2b7ccce5fb617b1c563aadbb03932");
        JAKE = new HatType("Jake", "&aJake Hat", "CookieGadgets.hats.jake", 7, Rarity.COMMON, null, "53d1877be95a9edb86df2256f23958324c2ec19ef94277ce2fb5c3301841dc");
        DOGE = new HatType("Doge", "&aDoge Hat", "CookieGadgets.hats.doge", 9, Rarity.COMMON, null, "b9afb2e5f0b977c4c683e017d2b47fcd1488ab56397766e5b380405a139260");
        COOL_DOGE = new HatType("Cool Doge", "&9Cool Doge Hat", "CookieGadgets.hats.cooldoge", 15, Rarity.RARE, null, "6d9532f4e2e99bd117159e08cd49452e2c69fab2b5626b4b0f36f52b0a692271");
        POLAR_BEAR = new HatType("Polar Bear", "&aPolar Bear Hat", "CookieGadgets.hats.polarbear", 12, Rarity.COMMON, null, "d46d23f04846369fa2a3702c10f759101af7bfe8419966429533cd81a11d2b");
        RABBIT = new HatType("Rabbit", "&aRabbit Hat", "CookieGadgets.hats.rabbit", 12, Rarity.COMMON, null, "dc7a317ec5c1ed7788f89e7f1a6af3d2eeb92d1e9879c05343c57f9d863de130");
        KOALA = new HatType("Koala", "&aKoala Hat", "CookieGadgets.hats.koala", 9, Rarity.COMMON, null, "ca35eb10b94e888427fb23c783082658ceb81f3cf5d0aad25d7d41a194b26");
        BEE = new HatType("Bee", "&aBee Hat", "CookieGadgets.hats.bee", 7, Rarity.COMMON, null, "947322f831e3c168cfbd3e28fe925144b261e79eb39c771349fac55a8126473");
        CLOWNFISH = new HatType("Clownfish", "&9Clownfish Hat", "CookieGadgets.hats.clownfish", 7, Rarity.RARE, null, "36d149e4d499929672e2768949e6477959c21e65254613b327b538df1e4df");
        FERRET = new HatType("Ferret", "&aFerret Hat", "CookieGadgets.hats.ferret", 9, Rarity.COMMON, null, "236edf7de9adca72308a94d1c38c358acc82918fe8fced25d474820f4cb784");
        WALRUS = new HatType("Walrus", "&aWalrus Hat", "CookieGadgets.hats.walrus", 12, Rarity.COMMON, null, "d7baedaf9ad95474eb1be58924445dfc77bbdc252cc1c81644cf7154c441");
        TIGER = new HatType("Tiger", "&aTiger Hat", "CookieGadgets.hats.tiger", 12, Rarity.COMMON, null, "b6b96bbc8ad9bae0e254d35fdfb1db48e822ed97cf5f739d3e9545dd6ce");
        MONKEY = new HatType("Monkey", "&aMonkey Hat", "CookieGadgets.hats.monkey", 12, Rarity.COMMON, null, "3429ca9c6a2e8bb162757f543adb62ef58f665d4e0dfcce5daf58d28fd9dfb");
        CACTUS = new HatType("Cactus", "&aCactus Hat", "CookieGadgets.hats.cactus", 7, Rarity.COMMON, null, "38c9a730269ce1de3e9fa064afb370cbcd0766d729f3e29e4f320a433b098b5");
        DUCK = new HatType("Duck", "&9Duck Hat", "CookieGadgets.hats.duck", 15, Rarity.RARE, null, "53babb28de59071a566b89b215751621137c20c3b4e177ee811df83e74ec54");
        EARTH = new HatType("Earth", "&aEarth Hat", "CookieGadgets.hats.earth", 9, Rarity.COMMON, null, "c9c8881e42915a9d29bb61a16fb26d059913204d265df5b439b3d792acd56");
        BEACH_BALL = new HatType("Beach Ball", "&aBeach Ball Hat", "CookieGadgets.hats.beachball", 12, Rarity.COMMON, null, "5a5ab05ea254c32e3c48f3fdcf9fd9d77d3cba04e6b5ec2e68b3cbdcfac3fd");
        SNOWGLOBE = new HatType("Snowglobe", "&aSnowglobe Hat", "CookieGadgets.hats.snowglobe", 12, Rarity.COMMON, null, "6dd663136cafa11806fdbca6b596afd85166b4ec02142c8d5ac8941d89ab7");
        TOASTER = new HatType("Toaster", "&aToaster Hat", "CookieGadgets.hats.toaster", 12, Rarity.COMMON, null, "bb53e8d374b4f6f573d1286681bf841055b89a462f7cdd99e8e63d2f514e45");
        CHEESE = new HatType("Cheese", "&9Cheese Hat", "CookieGadgets.hats.cheese", 15, Rarity.RARE, null, "36c01bffecfdab6d3c0f1a7c6df6aa1936f2aa7a51b54a4d323e1cacbc539");
        MARS = new HatType("Mars", "&9Mars Hat", "CookieGadgets.hats.mars", 15, Rarity.RARE, null, "777d616bc44ac9b3730fed47f29a378f88a16728c67048c1a387d229e1cba");
        PENGUIN = new HatType("Penguin", "&aPenguin Hat", "CookieGadgets.hats.penguin", 12, Rarity.COMMON, null, "d3c57facbb3a4db7fd55b5c0dc7d19c19cb0813c748ccc9710c714727551f5b9");
        ELEPHANT = new HatType("Elephant", "&aElephant Hat", "CookieGadgets.hats.elephant", 12, Rarity.COMMON, null, "771e82965d33213ef26f6b89722edee4346c354c08e34ecfcfd6ddd361a9e7e");
        ASTRONAUT = new HatType("Astronaut", "&9Astronaut Hat", "CookieGadgets.hats.astronaut", 15, Rarity.RARE, null, "36194375f283332f79be55121909a1fe793e7e857de3c74369c31ca6f5811");
        OTTER = new HatType("Otter", "&9Otter Hat", "CookieGadgets.hats.otter", 20, Rarity.RARE, null, "22cb416f93e85729a62738536cbbf6a5df945ad609e5f81fa4916863a2b25a1");
        MUMMY = new HatType("Mummy", "&9Mummy Hat", "CookieGadgets.hats.mummy", 20, Rarity.RARE, null, "3e91e95822fe98cc5a5658e824b1b8cf14d4de92f0e1af24815372435c9eab6");
        ORC = new HatType("Orc", "&9Orc Hat", "CookieGadgets.hats.orc", 18, Rarity.RARE, null, "927af941c8b33e633aa7ecf7cfc25e9fafb6f86c51a171c5e855a9efc2ad70dc");
        MINOTAUR = new HatType("Minotaur", "&9Minotaur Hat", "CookieGadgets.hats.minotaur", 18, Rarity.RARE, null, "6af8f32a98e4a2ce4d2abd9be28cb8369adf3d08d8ace24dfc4aa17cb9862e");
        DEMON_KNIGHT = new HatType("Demon Knight", "&9Demon Knight Hat", "CookieGadgets.hats.demonknight", 21, Rarity.RARE, null, "1464eb8e99e2878f343803a742ef57ceafacc2283e67b88edec16821316f9f");
        WHITE_WIZARD = new HatType("White Wizard", "&9White Wizard Hat", "CookieGadgets.hats.whitewizard", 15, Rarity.RARE, null, "ecb5716c870911544d3a3d8ac02a7cd48eaa5864cbf7881bdcb24229b6c6");
        MINER = new HatType("Miner", "&9Miner Hat", "CookieGadgets.hats.miner", 15, Rarity.RARE, null, "d357444ade64ec6cea645ec57e775864d67c5fa62299786e03799317ee4ad");
        MONK = new HatType("Monk", "&9Monk Hat", "CookieGadgets.hats.monk", 15, Rarity.RARE, null, "2921ed90678b9d8b6a3a8096a2abca778a1c8f1511d8c58b3b69dd388820f7");
        WOOD_ELF = new HatType("Wood Elf", "&9Wood Elf Hat", "CookieGadgets.hats.woodelf", 15, Rarity.RARE, null, "e0f534bb5e6dcdf3871b9b6ce8a4da18224f3fcb1fe14cbea9b1c3fcba5029");
        PIRATE = new HatType("Pirate", "&9Pirate Hat", "CookieGadgets.hats.pirate", 18, Rarity.RARE, null, "778828ac3a61d8712de5271bb35c4c7146a6b36c6b4e576f5eb8d178da7dfd34");
        ODIN = new HatType("Odin", "&9Odin Hat", "CookieGadgets.hats.odin", 18, Rarity.RARE, null, "1612926722726a24d3cb4120ae362a7f42e189c4d2f45b8e80e753e7b1143dd7");
        GHOST = new HatType("Ghost", "&9Ghost Hat", "CookieGadgets.hats.ghost", 20, Rarity.RARE, null, "68d2183640218ab330ac56d2aab7e29a9790a545f691619e38578ea4a69ae0b6");
        SKULL = new HatType("Skull", "&9Skull Hat", "CookieGadgets.hats.skull", 22, Rarity.RARE, null, "1ae3855f952cd4a03c148a946e3f812a5955ad35cbcb52627ea4acd47d3081");
        PUMPKIN = new HatType("Pumpkin", "&9Pumpkin Hat", "CookieGadgets.hats.pumpkin", 25, Rarity.RARE, null, "ae3b74d13ecc927b7ec1eb95f29b73f213021f4c7bad0225df5ed20701ec407d");
        SCARECROW = new HatType("Scarecrow", "&9Scarecrow Hat", "CookieGadgets.hats.scarecrow", 20, Rarity.RARE, null, "1171baa5ad167bd33e419fe745f7b01840cb6d7e8d7aec6cda31ce2d5cf61");
        FOX = new HatType("Fox", "&9Fox Hat", "CookieGadgets.hats.fox", 18, Rarity.RARE, null, "c0691610278e9084246280f2795adef9b3a88c2554a641a6aa23b7b66488836");
        PUG = new HatType("Pug", "&9Pug Hat", "CookieGadgets.hats.pug", 18, Rarity.RARE, null, "47b4f84e19b52f31217712e7ba9f51d56da59d2445b4d7f39ef6c323b8166");
        OWL = new HatType("Owl", "&9Owl Hat", "CookieGadgets.hats.owl", 20, Rarity.RARE, null, "e9fb2b255c2bcc8d4635e1525da30a8bb4faf61356045723e64571fdc5fffb1");
        PANDA = new HatType("Panda", "&9Panda Hat", "CookieGadgets.hats.panda", 20, Rarity.RARE, null, "d188c980aacfa94cf33088512b1b9517ba826b154d4cafc262aff6977be8a");
        SLOTH = new HatType("Sloth", "&9Sloth Hat", "CookieGadgets.hats.sloth", 25, Rarity.RARE, null, "1b2fbdc2aed275c84f30d2e5d08fdfd8b4de38908a12e067f2165d23c6112c91");
        GORILLA = new HatType("Gorilla", "&9Gorilla Hat", "CookieGadgets.hats.gorilla", 25, Rarity.RARE, null, "de97a97c56d37a3f434a52162ac3add46306c635710224890e68bc7abd86a66");
        SNOWMAN = new HatType("Snowman", "&5Snowman Hat", "CookieGadgets.hats.snowman", 43, Rarity.EPIC, null, "98e334e4bee04264759a766bc1955cfaf3f56201428fafec8d4bf1bb36ae6");
        REINDEER = new HatType("Reindeer", "&5Reindeer Hat", "CookieGadgets.hats.reindeer", 43, Rarity.EPIC, null, "44927ce5ba22ad1e757d6a333e275b36da1a8436fcef077340ab53e3fb");
        SHULKER = new HatType("Shulker", "&5Shulker Hat", "CookieGadgets.hats.shulker", 45, Rarity.EPIC, null, "b1d3534d21fe8499262de87affbeac4d25ffde35c8bdca069e61e1787ff2f");
        TURTLE = new HatType("Turtle", "&5Turtle Hat", "CookieGadgets.hats.turtle", 45, Rarity.EPIC, null, "12e548408ab75d7df8e6d5d2446d90b6ec62aa4f7feb7930d1ee71eefddf6189");
        SOLVED_RUBIKS_CUBE = new HatType("Solved Rubiks Cube", "&5Solved Rubik's Cube Hat", "CookieGadgets.hats.solvedrubikscube", 45, Rarity.EPIC, null, "8f1a277beb9e4faa6e7e356c74786e966155736a6858bf5bb5ad29df5bab61a1");
        SCRAMBLED_RUBIKS_CUBE = new HatType("Scrambled Rubiks Cube", "&6Scrambled Rubik's Cube Hat", "CookieGadgets.hats.scrambledrubikscube", 55, Rarity.LEGENDARY, null, "5d86e7bd28c146f71514c782cac055860d1f372b4a9be3fe65cfe1104733ba");
        RAINBOW_GLITCH = new HatType("Rainbow Glitch", "&9Rainbow Glitch Hat", "CookieGadgets.hats.rainbowglitch", 18, Rarity.RARE, null, "b462ddfa553ce78683be477b8d8654f3dfc3aa2969808478c987ab88c376a0");
        PACMAN = new HatType("Pacman", "&9Pacman Hat", "CookieGadgets.hats.pacman", 18, Rarity.RARE, null, "b3ab918439dc5a1962dbd5ad25801334232baef35d5fed4f9b4486c728a04cac");
    }
    
    private HatType(final String s, final String displayName, final String s2, final int mysteryDust, final Rarity rarity, final List<String> list, final String str) {
        super(Category.HATS, s, displayName, s2, mysteryDust, rarity, list, list);
        if (FileManager.getHatsFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getHatsFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getHatsFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getHatsFile().getInt(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getHatsFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getHatsFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getHatsFile().get(String.valueOf(this.getFilePath()) + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getHatsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Enabled");
        }
        if (FileManager.getHatsFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getHatsFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getHatsFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getHatsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (FileManager.getHatsFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getHatsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                }
                else {
                    FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getHatsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getHatsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getHatsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.material = new GMaterial("head:" + str);
        this.itemStack = ItemUtils.item(this.displayName, this.material);
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Cosmetics", "true");
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Category", Category.HATS.getNBTTag());
        if (!HatType.VALUES.contains(this)) {
            HatType.VALUES.add(this);
        }
    }
    
    public HatType(final String s, final String displayName, final String s2, final int mysteryDust, final Rarity rarity, final List<String> lockedLore, final List<String> unlockedLore, final GMaterial material, final boolean isEnable, final boolean canBeFound, final boolean purchasable, final ItemStack itemStack) {
        super(Category.HATS, s, displayName, s2, mysteryDust, rarity, lockedLore, unlockedLore);
        this.displayName = displayName;
        this.mysteryDust = mysteryDust;
        this.rarity = rarity;
        this.lockedLore = lockedLore;
        this.unlockedLore = unlockedLore;
        this.material = material;
        this.isEnable = isEnable;
        this.canBeFound = canBeFound;
        this.purchasable = purchasable;
        this.itemStack = itemStack;
        if (!HatType.VALUES.contains(this)) {
            HatType.VALUES.add(this);
        }
        if (isEnable && !HatType.ENABLED.contains(this)) {
            HatType.ENABLED.add(this);
        }
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
    
    public GMaterial getMaterial() {
        return this.material;
    }
    
    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }
    
    @Override
    public boolean canBeFound() {
        return this.canBeFound;
    }
    
    @Override
    public boolean isPurchasable() {
        return this.purchasable;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    @Override
    public String getFilePath() {
        return "Hats." + super.getName();
    }
    
    @Override
    public void equip(final PlayerManager playerManager) {
        if (!this.isEnabled() || !Category.HATS.isEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        if (player.getInventory().getHelmet() != null) {
            CategoryManager.removeHelmetCosmetic(playerManager);
            if (player.getInventory().getHelmet() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_HELMET_TO_EQUIP_HAT.getFormatMessage())) {
                    return;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    final Player player2;
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        player2.getWorld().dropItemNaturally(player2.getLocation(), player2.getInventory().getHelmet().clone());
                        player2.getInventory().setHelmet((ItemStack)null);
                        player2.updateInventory();
                        return;
                    });
                }
            }
        }
        if (playerManager.getEquippedMorph() != null) {
            playerManager.unequipMorph();
        }
        player.getInventory().setHelmet(this.getItemStack());
        playerManager.setEquippedHat(this);
    }
    
    @Override
    public void unequip(final PlayerManager playerManager) {
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        if (player.getInventory().getHelmet() != null || playerManager.getEquippedHat() != null) {
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta() != null && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getHelmet(), "Category", Category.HATS.getNBTTag())) {
                player.getInventory().setHelmet((ItemStack)null);
                player.updateInventory();
            }
            if (playerManager.getEquippedHat() != null) {
                playerManager.setEquippedHat(null);
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
        if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.HATS)) {
            player.sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return false;
        }
        if (player.getInventory().getHelmet() != null) {
            CategoryManager.removeHelmetCosmetic(playerManager);
            if (player.getInventory().getHelmet() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_HELMET_TO_EQUIP_HAT.getFormatMessage())) {
                    return false;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    final Player player2;
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        player2.getWorld().dropItemNaturally(player2.getLocation(), player2.getInventory().getHelmet().clone());
                        player2.getInventory().setHelmet((ItemStack)null);
                        player2.updateInventory();
                        return;
                    });
                }
            }
        }
        return true;
    }
    
    public static List<HatType> enabled() {
        return HatType.ENABLED;
    }
    
    public static List<HatType> values() {
        return HatType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final HatType hatType : values()) {
            if (hatType.isEnabled() && !HatType.ENABLED.contains(hatType)) {
                HatType.ENABLED.add(hatType);
            }
        }
    }
    
    public static void sortItems() {
        if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.NAME) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super HatType, ? extends Comparable>)HatType::getDisplayNameStripColor));
        }
        else if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.RARITY) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super Object, ? extends Comparable>)HatType::getRarity).thenComparing((Function<? super Object, ? extends Comparable>)HatType::getDisplayNameStripColor));
        }
    }
    
    public static HatType valueOf(final String anotherString) {
        for (final HatType hatType : values()) {
            if (hatType.getName().equalsIgnoreCase(anotherString)) {
                return hatType;
            }
        }
        return null;
    }
}
