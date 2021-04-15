

package ws.billy.CookieGadgets.cosmetics.emotes;

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
import java.util.Arrays;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.GMaterial;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticType;

public class EmoteType extends CosmeticType
{
    private static final List<EmoteType> ENABLED;
    private static final List<EmoteType> VALUES;
    public static final EmoteType SMILE;
    public static final EmoteType COOL;
    public static final EmoteType WINK;
    public static final EmoteType GRIN;
    public static final EmoteType SURPRISED;
    public static final EmoteType CRY;
    public static final EmoteType SLEEPY;
    public static final EmoteType RAGE;
    public static final EmoteType FROWN;
    public static final EmoteType CHEEKY;
    public static final EmoteType SUN_TAN;
    public static final EmoteType HEART_EYES;
    public static final EmoteType DIZZY;
    public static final EmoteType RIP;
    public static final EmoteType RELAX;
    public static final EmoteType SPICY;
    public static final EmoteType DEAL_WITH_IT;
    private String displayName;
    private int cooldown;
    private int mysteryDust;
    private Rarity rarity;
    private boolean isHologramEnable;
    private String hologram;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private GMaterial.GMaterialHead headTexture;
    private Long ticksPerFrame;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    private ItemStack itemStack;
    private ArrayList<ItemStack> frames;
    
    static {
        ENABLED = new ArrayList<EmoteType>();
        VALUES = new ArrayList<EmoteType>();
        SMILE = new EmoteType("Smile", "&aSmile Emote", "CookieGadgets.emotes.smile", 10, 10, Rarity.COMMON, "&fsmiles", Arrays.asList("&7Feeling great? Show the", "&7world how happy you are", "&7with this animated Smile", "&7emote!"), new GMaterial.GMaterialHead("60c432cbc490a8af6e9dfeb28095c0a0ec79fff705fb184674d1e743bd05baa"), 5L);
        COOL = new EmoteType("Cool", "&aCool Emote", "CookieGadgets.emotes.cool", 10, 12, Rarity.COMMON, "&fB)", Arrays.asList("&7Feel like the coolest cat", "&7on the server? Strut your", "&7stuff with this animated", "&7Cool emote!"), new GMaterial.GMaterialHead("766b3eef3c726ecb816c43839189eeb8e36382e3e5fe41128372785185a322"), 2L);
        WINK = new EmoteType("Wink", "&aWink Emote", "CookieGadgets.emotes.wink", 10, 12, Rarity.COMMON, "&fwinks", Arrays.asList("&7Having fun? Let someone", "&7know you're in on the joke", "&7with this animated Wink", "&7emote!"), new GMaterial.GMaterialHead("a42ff870f5538b4c785b2e3873c689d71844c23a5bee15c7aa1f5b8b3593d"), 2L);
        GRIN = new EmoteType("Grin", "&aGrin Emote", "CookieGadgets.emotes.grin", 10, 13, Rarity.COMMON, "&fYay!", Arrays.asList("&7If you are feeling amazing,", "&7there's no better way to", "&7show it than with this", "&7animated Grin emote!"), new GMaterial.GMaterialHead("bd14f201719bac723781fbe664e3199512dbcef5ddff30914ca5a8e7937e26"), 2L);
        SURPRISED = new EmoteType("Surprised", "&aSurprised Emote", "CookieGadgets.emotes.surprised", 10, 15, Rarity.COMMON, "&fOMG", Arrays.asList("&7Has something shocked you?", "&7Let the lobby know with", "&7this animated Surprised", "&7emote!"), new GMaterial.GMaterialHead("1c20905bc09bb8c39b53c4c5f72952c29d4a73734cbfdf3719bdc3bb9f9dcb"), 2L);
        CRY = new EmoteType("Cry", "&9Cry Emote", "CookieGadgets.emotes.cry", 10, 25, Rarity.RARE, "&fWaaaaa!", Arrays.asList("&7You feel sad? Shed tears", "&7with this animated Crying", "&7emote!"), new GMaterial.GMaterialHead("45436e46e4885eeb9882089601e9852f6546ad7b2101cf47b4369156a44b7ba"), 2L);
        SLEEPY = new EmoteType("Sleepy", "&9Sleepy Emote", "CookieGadgets.emotes.sleepy", 10, 20, Rarity.RARE, "&fZZzzzz", Arrays.asList("&7Stayed up all night playing", "&7your favorite game? Fall", "&7asleep with this animated", "&7Sleepy emote!"), new GMaterial.GMaterialHead("8629c6bf575dee657626a84b887a1e714714c994f2782deb96818cc3829e7c69"), 2L);
        RAGE = new EmoteType("Rage", "&9Rage Emote", "CookieGadgets.emotes.rage", 10, 23, Rarity.RARE, "&cGRRRRR!!!", Arrays.asList("&7Is something boiling your", "&7blood? Let off steam with", "&7this animated Rage Emote!"), new GMaterial.GMaterialHead("973fd955ca4389b68642483e53e5e2f1fabafc2416fc8e95d43694b76c5a81"), 2L);
        FROWN = new EmoteType("Frown", "&9Frown Emote", "CookieGadgets.emotes.frown", 10, 22, Rarity.RARE, "&fsniff", Arrays.asList("&7Has something got you down?", "&7Let the world know with", "&7this animated Frown Emote!"), new GMaterial.GMaterialHead("d29652d937554e13b9bb9dd9ffcae8c79e549ac3fbbc23bf92644ce45684"), 2L);
        CHEEKY = new EmoteType("Cheeky", "&9Cheeky Emote", "CookieGadgets.emotes.cheeky", 10, 22, Rarity.RARE, "&f:P", Arrays.asList("&7Up to some mischief? Warn", "&7everyone how you're feeling", "&7with this animated Cheeky", "&7Emote!"), new GMaterial.GMaterialHead("997722293829e13d2ba7ba575ce78a5d7eeb5e745e6e295961348d55cc7fe7"), 2L);
        SUN_TAN = new EmoteType("Sun Tan", "&9Sun Tan Emote", "CookieGadgets.emotes.suntan", 10, 25, Rarity.RARE, "&fSunny", Arrays.asList("&7Did you forget your sun", "&7screen when you went to the", "&7beach? Let others know how", "&7crispy you are with this", "&7Sun Tan Emote!"), new GMaterial.GMaterialHead("2d2175ebe9ae0e1a658d9af82dacfb8369052d8121d4ea3886738a1cca5"), 2L);
        HEART_EYES = new EmoteType("Heart Eyes", "&9Heart Eye Emote", "CookieGadgets.emotes.hearteyes", 10, 28, Rarity.RARE, "&d\u2764  \u2764", Arrays.asList("&7Feeling good? Spread the", "&7good vibes with the Heart", "&7Eyes Emote!"), new GMaterial.GMaterialHead("fd26ae4b5793d087e62a2cf3f34359829d02869aae6626bfcff59de1469f51"), 2L);
        DIZZY = new EmoteType("Dizzy", "&9Dizzy Emote", "CookieGadgets.emotes.dizzy", 10, 28, Rarity.RARE, "&fx_x", Arrays.asList("&7The rollercoaster is not", "&7for you? Show others how", "&7you feel with the Dizzy", "&7Emote!"), new GMaterial.GMaterialHead("4c46ee76fd9e947e3789fac348216ab98c2421268fafaf3eb29ec949c0b82169"), 2L);
        RIP = new EmoteType("RIP", "&9RIP Emote", "CookieGadgets.emotes.rip", 10, 28, Rarity.RARE, "&fRest In Pepperoni", Arrays.asList("&7Did you get that final", "&7blow? Show mercy to your", "&7opponents with this Rest In", "&7Pepperoni Emote!"), new GMaterial.GMaterialHead("439c3df7a628af8d751ecca197642cdc1a07c30e3289b2d3261f7a65cf395b"), 2L);
        RELAX = new EmoteType("Relax", "&9Relax Emote", "CookieGadgets.emotes.relax", 10, 28, Rarity.RARE, "&fPhew", Arrays.asList("&7Feeling cozy? You surely", "&7will with the Relax Emote!"), new GMaterial.GMaterialHead("2f7e6c079efa69cb3a23dd3b147643c7cb5e5c9129b74af0cab47b04f355a"), 2L);
        SPICY = new EmoteType("Spicy", "&9Spicy Emote", "CookieGadgets.emotes.spicy", 10, 28, Rarity.RARE, "&fBurns!", Arrays.asList("&7Did that habanero pepper", "&7get to you? Let the rest", "&7know with this Spicy Emote!"), new GMaterial.GMaterialHead("e34dad6c9eab0baf9f9d9b0f6be6e19936b3a1e20fc3e217b885eadeb318"), 2L);
        DEAL_WITH_IT = new EmoteType("Deal With It", "&9Deal With It Emote", "CookieGadgets.emotes.dealwithit", 10, 28, Rarity.RARE, "&fDeal With It :)", Arrays.asList("&7Do you feel like showing", "&7off? Let others see how", "&7cool you feel with the real", "&7Deal With It Emote!"), new GMaterial.GMaterialHead("599eb74ec2bb0added18ebd777a5a7478e86256deb536dccbfc4acd9be2a28"), 2L);
        EmoteType.SMILE.addTexture(5, "264614ad4bb2eb61b06b1a8b5d57f02448a975a8217ec16571f87c49227cbd");
        EmoteType.SMILE.addTexture(1, "60c432cbc490a8af6e9dfeb28095c0a0ec79fff705fb184674d1e743bd05baa");
        EmoteType.SMILE.addTexture(11, "41ac21d93ce17f2b7ee2e0e07a983eeb4a539e341ce5c77c36c722f77a2235");
        EmoteType.SMILE.addTexture(1, "4168b716281635ceafc3268dfa7d5f46466c8032e11c1cfb7db711a9f647d");
        EmoteType.SMILE.addTexture(11, "41ac21d93ce17f2b7ee2e0e07a983eeb4a539e341ce5c77c36c722f77a2235");
        EmoteType.SMILE.addTexture(1, "4168b716281635ceafc3268dfa7d5f46466c8032e11c1cfb7db711a9f647d");
        EmoteType.SMILE.addTexture(3, "41ac21d93ce17f2b7ee2e0e07a983eeb4a539e341ce5c77c36c722f77a2235");
        EmoteType.COOL.addTexture(16, "a21e6dbfd74a1859ddbae3380fc1ab71f2389745945fc92329b164635bd14f");
        EmoteType.COOL.addTexture(1, "3733db9a94bfe15cdbb7ca5832c85cfada98ad2c839934766bdc41f977b5c163");
        EmoteType.COOL.addTexture(61, "766b3eef3c726ecb816c43839189eeb8e36382e3e5fe41128372785185a322");
        EmoteType.WINK.addTexture(11, "d6a48486db32c98790d55e20bc4278651620ac2787ee13ceb2bb4cc3b2721f");
        EmoteType.WINK.addTexture(1, "b8723d35b8efae1aca8f61ab4613375fa472647c6a80d94c53474f3f77e03d");
        EmoteType.WINK.addTexture(1, "3fac4d8a9e066b31854387375846a22bf23bbde8a2c2084f696b07689b2");
        EmoteType.WINK.addTexture(1, "a42ff870f5538b4c785b2e3873c689d71844c23a5bee15c7aa1f5b8b3593d");
        EmoteType.WINK.addTexture(1, "3fac4d8a9e066b31854387375846a22bf23bbde8a2c2084f696b07689b2");
        EmoteType.WINK.addTexture(11, "a42ff870f5538b4c785b2e3873c689d71844c23a5bee15c7aa1f5b8b3593d");
        EmoteType.WINK.addTexture(1, "3fac4d8a9e066b31854387375846a22bf23bbde8a2c2084f696b07689b2");
        EmoteType.WINK.addTexture(1, "a42ff870f5538b4c785b2e3873c689d71844c23a5bee15c7aa1f5b8b3593d");
        EmoteType.WINK.addTexture(1, "3fac4d8a9e066b31854387375846a22bf23bbde8a2c2084f696b07689b2");
        EmoteType.WINK.addTexture(51, "a42ff870f5538b4c785b2e3873c689d71844c23a5bee15c7aa1f5b8b3593d");
        EmoteType.GRIN.addTexture(16, "798e7b1edcc3152a51b23d0a9d9486d34b1378836fc7757ed9f6078eedaa8");
        EmoteType.GRIN.addTexture(1, "b8723d35b8efae1aca8f61ab4613375fa472647c6a80d94c53474f3f77e03d");
        EmoteType.GRIN.addTexture(1, "3fac4d8a9e066b31854387375846a22bf23bbde8a2c2084f696b07689b2");
        EmoteType.GRIN.addTexture(61, "bd14f201719bac723781fbe664e3199512dbcef5ddff30914ca5a8e7937e26");
        EmoteType.SURPRISED.addTexture(16, "dc9bb8ad34566c2fb3d94bc8bcd7989f2d9c5b5f78604acae8a8b0c09f61f7");
        EmoteType.SURPRISED.addTexture(3, "6c27343072c0a262b13bddc64e6d762ce5ef45c738d290a487d5b847f2a5f62");
        EmoteType.SURPRISED.addTexture(61, "1c20905bc09bb8c39b53c4c5f72952c29d4a73734cbfdf3719bdc3bb9f9dcb");
        EmoteType.CRY.addTexture(21, "bd40f71c40fc308718d39813b2a8fd9602f44b803a5b587d3f3b131e4b8c61");
        EmoteType.CRY.addTexture(3, "1b9932b5658f4cac4f4f8d9e98f6dcee2e17744169ccb5c8145365f17d445f3");
        EmoteType.CRY.addTexture(3, "8987e5c3859823aec3cdebd2be7ada04aeed30a3a83381c4df9c4762f0c9d1");
        EmoteType.CRY.addTexture(3, "b0966259f2e1f97dbf19662ad1d003d5af5dd8b85384bfbb6237839ae9925");
        EmoteType.CRY.addTexture(1, "3e6ef5e168ed65936c74a3351e9bb7e4ff0133bed5af27dccc625d92a3fe91f");
        EmoteType.CRY.addTexture(1, "a8468f09a57bc3bbe614f6bcfdf203f52dfe57b401471acc18977bffa9bfecf");
        for (int i = 0; i <= 15; ++i) {
            EmoteType.CRY.addTexture(1, "652631c8593bcd643761aa81d36aa96dd39c899d2b90eca70e035c4410ae9e");
            EmoteType.CRY.addTexture(1, "a1226aa3c1429363ddb5bcdf6f28556287eb2c554c847789d508c47cfae3b8d");
            EmoteType.CRY.addTexture(1, "45436e46e4885eeb9882089601e9852f6546ad7b2101cf47b4369156a44b7ba");
        }
        EmoteType.SLEEPY.addTexture(11, "bd40f71c40fc308718d39813b2a8fd9602f44b803a5b587d3f3b131e4b8c61");
        EmoteType.SLEEPY.addTexture(1, "8586709ec3fded4f6938218ad21b48fa32eeff3c4ae15c65d7ad26578869c19");
        EmoteType.SLEEPY.addTexture(1, "1592af29d7b115c9d922fbd1a0a3a7afb2e876c1553cd33e640c4c613e8af");
        EmoteType.SLEEPY.addTexture(9, "8629c6bf575dee657626a84b887a1e714714c994f2782deb96818cc3829e7c69");
        EmoteType.SLEEPY.addTexture(1, "194df642b2762624481a5b2be21a813d5ea412f521d10c363a714e4748a9269");
        EmoteType.SLEEPY.addTexture(1, "b2e2f643618b5198b6fdfa4f4efa357f2a679d3dfae90f8fef03ecdaf42ca");
        EmoteType.SLEEPY.addTexture(10, "3e106ec67cb2a232f4f27971a52b3ab4b950a31ea446ea92eeb259263cfd119");
        EmoteType.SLEEPY.addTexture(9, "e434972c326c2c39a5e41d073feeca4aeba91df1a120402b894efc8c5af99");
        EmoteType.SLEEPY.addTexture(10, "3e106ec67cb2a232f4f27971a52b3ab4b950a31ea446ea92eeb259263cfd119");
        EmoteType.SLEEPY.addTexture(9, "e434972c326c2c39a5e41d073feeca4aeba91df1a120402b894efc8c5af99");
        EmoteType.SLEEPY.addTexture(10, "3e106ec67cb2a232f4f27971a52b3ab4b950a31ea446ea92eeb259263cfd119");
        EmoteType.SLEEPY.addTexture(9, "e434972c326c2c39a5e41d073feeca4aeba91df1a120402b894efc8c5af99");
        EmoteType.RAGE.addTexture(7, "2c74e6f64c837671b17694259b07cd5c79fbf8bfd3227332a5191e6ab11f3d5");
        EmoteType.RAGE.addTexture(4, "ad96543cf9304e9a97ecccbdc416b45bb48feb5a0bd1518a52da4b2f7386");
        EmoteType.RAGE.addTexture(3, "2f9aad5c98a444576fe9330e0a36e5b614956b1901ed4c8b1b97114544099");
        EmoteType.RAGE.addTexture(4, "973fd955ca4389b68642483e53e5e2f1fabafc2416fc8e95d43694b76c5a81");
        for (int j = 0; j <= 35; ++j) {
            EmoteType.RAGE.addTexture(1, "1a9ee344c3def5b3f3593e26ce0f52471d95993393e497ed6a77e38ec8a221");
            EmoteType.RAGE.addTexture(1, "9cb2253e5f3e41953757724d458698f3c3137279764dbee3d56be776cddccf79");
        }
        EmoteType.FROWN.addTexture(20, "bd40f71c40fc308718d39813b2a8fd9602f44b803a5b587d3f3b131e4b8c61");
        EmoteType.FROWN.addTexture(6, "80e54c21c825c92144a1b56d19743ee7e5d89be4ccf231caa8235d12eb34e6");
        EmoteType.FROWN.addTexture(5, "21dff48846d1524273859d717729556f626fa5f2185a1c322e723325263f09c");
        EmoteType.FROWN.addTexture(1, "88e4eba3ade2136e401e91aa42af6b981a6deb61d1c11b66e9daa657f69156e");
        EmoteType.FROWN.addTexture(1, "7dae9bab67d2bb9830ced5338cf8a0be67f52a408a92f423aa6ccfb2c786c");
        EmoteType.FROWN.addTexture(1, "b61c50ba43d78570377217b304773110e8ca7d8784e08c73144a659221228");
        EmoteType.FROWN.addTexture(35, "d29652d937554e13b9bb9dd9ffcae8c79e549ac3fbbc23bf92644ce45684");
        EmoteType.FROWN.addTexture(1, "84f8843f7f9e7b4f20668851b499ce709325343c30cf237346cf91dcef5f2");
        EmoteType.FROWN.addTexture(40, "d29652d937554e13b9bb9dd9ffcae8c79e549ac3fbbc23bf92644ce45684");
        EmoteType.FROWN.addTexture(1, "84f8843f7f9e7b4f20668851b499ce709325343c30cf237346cf91dcef5f2");
        EmoteType.FROWN.addTexture(45, "d29652d937554e13b9bb9dd9ffcae8c79e549ac3fbbc23bf92644ce45684");
        EmoteType.FROWN.addTexture(1, "84f8843f7f9e7b4f20668851b499ce709325343c30cf237346cf91dcef5f2");
        EmoteType.FROWN.addTexture(50, "d29652d937554e13b9bb9dd9ffcae8c79e549ac3fbbc23bf92644ce45684");
        EmoteType.CHEEKY.addTexture(16, "bd40f71c40fc308718d39813b2a8fd9602f44b803a5b587d3f3b131e4b8c61");
        EmoteType.CHEEKY.addTexture(7, "264614ad4bb2eb61b06b1a8b5d57f02448a975a8217ec16571f87c49227cbd");
        EmoteType.CHEEKY.addTexture(5, "c14b89d43b2e928f1c5897777dd85e34f45f8b83d0e25c2f755588712a903c");
        EmoteType.CHEEKY.addTexture(7, "f25ce74bf36832a8f779b967bedae551f5ed2f223d767ba18ee63e3615d59bd");
        EmoteType.CHEEKY.addTexture(20, "997722293829e13d2ba7ba575ce78a5d7eeb5e745e6e295961348d55cc7fe7");
        for (int k = 0; k < 3; ++k) {
            EmoteType.CHEEKY.addTexture(1, "5785abea5135083893f1a2f1bfaf9f4972553f9af24d6bd81da68a910736");
            EmoteType.CHEEKY.addTexture(1, "1c49bd4138603a904faafe1366f6bfb73ebd1674058a89856628eb2c95c20");
        }
        for (int l = 0; l < 20; ++l) {
            EmoteType.CHEEKY.addTexture(1, "997722293829e13d2ba7ba575ce78a5d7eeb5e745e6e295961348d55cc7fe7");
        }
        EmoteType.SUN_TAN.addTexture(6, "2d2175ebe9ae0e1a658d9af82dacfb8369052d8121d4ea3886738a1cca5");
        EmoteType.SUN_TAN.addTexture(6, "7ef575629a2689d63a3a3e91bd342ec3f78b4f397687c0833bf6d64bf26d12e8");
        EmoteType.SUN_TAN.addTexture(8, "285c789b1bafeb6274d5c3314e0333ccf6ab92d7312ef214f89793c959d25");
        EmoteType.SUN_TAN.addTexture(8, "e38adde3aa4df2cf8a5216643d3f6f92133e1599d50b2ef41cefc81f1eec17c");
        EmoteType.SUN_TAN.addTexture(6, "285c789b1bafeb6274d5c3314e0333ccf6ab92d7312ef214f89793c959d25");
        EmoteType.SUN_TAN.addTexture(6, "7ef575629a2689d63a3a3e91bd342ec3f78b4f397687c0833bf6d64bf26d12e8");
        EmoteType.SUN_TAN.addTexture(5, "56f0c6a6f5525d0876773768e2a7f6bd43608d8b15f0e8780f64d512f20");
        EmoteType.SUN_TAN.addTexture(5, "f03ec621ef23175ee4eecc5d1d9222b9e27ca8758bede504e3fa3c0ac1532bd");
        EmoteType.SUN_TAN.addTexture(5, "778ab3781dfe96e519b174e47efdbc6881715d7ae8ca41a1d8ae620f4779d2");
        EmoteType.SUN_TAN.addTexture(5, "8ddda3df9fde30208cad1a308634c95f95c9b48a427842adc9c8ccfec626b4f");
        EmoteType.SUN_TAN.addTexture(12, "7365c666e7d9804397e76de355ee2e68d4c969b5ff7c0ab6af77bd7c7e266");
        for (int n = 0; n < 3; ++n) {
            EmoteType.SUN_TAN.addTexture(5, "15e1122c837a87ce18d51e797983da6ad384793fdd9f77253763ae9b6d9a1");
            EmoteType.SUN_TAN.addTexture(15, "803889e44b55465abff5cedc5b86d3bda346bc70c9bf8b97fcf793948f379c1");
        }
        EmoteType.HEART_EYES.addTexture(10, "901b958ed2c36e45bae72b42d4ee719d45240b233669091b1cc9e070e31119");
        for (int n2 = 0; n2 < 5; ++n2) {
            EmoteType.HEART_EYES.addTexture(2, "895f6415bd9424a664d694371a846838c20fb36c3b4a22f385fe7e3dce2996");
            EmoteType.HEART_EYES.addTexture(2, "96fbb52a4d0c62d8e6cae8c485e551b37fec68e6daab23d85f2ff52faa4c4");
            EmoteType.HEART_EYES.addTexture(2, "fd26ae4b5793d087e62a2cf3f34359829d02869aae6626bfcff59de1469f51");
            EmoteType.HEART_EYES.addTexture(2, "96fbb52a4d0c62d8e6cae8c485e551b37fec68e6daab23d85f2ff52faa4c4");
            EmoteType.HEART_EYES.addTexture(2, "fd26ae4b5793d087e62a2cf3f34359829d02869aae6626bfcff59de1469f51");
            EmoteType.HEART_EYES.addTexture(10, "895f6415bd9424a664d694371a846838c20fb36c3b4a22f385fe7e3dce2996");
        }
        EmoteType.HEART_EYES.addTexture(5, "901b958ed2c36e45bae72b42d4ee719d45240b233669091b1cc9e070e31119");
        for (int n3 = 0; n3 < 4; ++n3) {
            EmoteType.DIZZY.addTexture(10, "4c46ee76fd9e947e3789fac348216ab98c2421268fafaf3eb29ec949c0b82169");
            EmoteType.DIZZY.addTexture(1, "416f3dce977d8b797e1e476f5ab93619ed2f2b21a49ac93743140cf67a088");
            EmoteType.DIZZY.addTexture(1, "d892fc55b234b3b53563cd48f98c26e8e3f7e5ae3523b6eed89b262ecf1c5d5");
            EmoteType.DIZZY.addTexture(1, "ba151a55eba734f1c651bbc7e717cc8a7ff5575d6804f137d1cd2161314989f");
            EmoteType.DIZZY.addTexture(1, "61803a7132fbe59e65060ddec6263d674a1da6d238ce0ada140e6799b28559d");
            EmoteType.DIZZY.addTexture(1, "d892fc55b234b3b53563cd48f98c26e8e3f7e5ae3523b6eed89b262ecf1c5d5");
            EmoteType.DIZZY.addTexture(1, "416f3dce977d8b797e1e476f5ab93619ed2f2b21a49ac93743140cf67a088");
            EmoteType.DIZZY.addTexture(1, "61803a7132fbe59e65060ddec6263d674a1da6d238ce0ada140e6799b28559d");
            EmoteType.DIZZY.addTexture(1, "ba151a55eba734f1c651bbc7e717cc8a7ff5575d6804f137d1cd2161314989f");
            EmoteType.DIZZY.addTexture(1, "61803a7132fbe59e65060ddec6263d674a1da6d238ce0ada140e6799b28559d");
            EmoteType.DIZZY.addTexture(1, "d892fc55b234b3b53563cd48f98c26e8e3f7e5ae3523b6eed89b262ecf1c5d5");
            EmoteType.DIZZY.addTexture(1, "ba151a55eba734f1c651bbc7e717cc8a7ff5575d6804f137d1cd2161314989f");
            EmoteType.DIZZY.addTexture(1, "d892fc55b234b3b53563cd48f98c26e8e3f7e5ae3523b6eed89b262ecf1c5d5");
            EmoteType.DIZZY.addTexture(1, "ba151a55eba734f1c651bbc7e717cc8a7ff5575d6804f137d1cd2161314989f");
            EmoteType.DIZZY.addTexture(1, "416f3dce977d8b797e1e476f5ab93619ed2f2b21a49ac93743140cf67a088");
            EmoteType.DIZZY.addTexture(1, "ba151a55eba734f1c651bbc7e717cc8a7ff5575d6804f137d1cd2161314989f");
        }
        EmoteType.RIP.addTexture(8, "30e78285d5aee0b28787ad88a5d58fb05ccf22918daa516ead85a6bf4fe068");
        EmoteType.RIP.addTexture(8, "63611b5724e091854e79926fd11e486bfd0f99042721c3b34177f818639c19d");
        EmoteType.RIP.addTexture(2, "83e0621b45d3a326d236293cd8ea49ae74d52e56fc8d1d133e7fc8bcf2a5988");
        EmoteType.RIP.addTexture(2, "6e16a7ae186c3cfeac364eac0e83d3528741c3dd9ef8277080e03deabc714");
        EmoteType.RIP.addTexture(2, "20ec3a80ed35bd9beb7d20cb75f1ecd5b8ab0d576f1db699f7def13131fbc5");
        EmoteType.RIP.addTexture(2, "b03badcc9fb966c87e0dc1332d735b2b587c2602d35fecb44ba6ed94ceb4");
        EmoteType.RIP.addTexture(70, "439c3df7a628af8d751ecca197642cdc1a07c30e3289b2d3261f7a65cf395b");
        EmoteType.RELAX.addTexture(3, "927ebbf5c2535fe6b5cef8b8a7e1e7067a39ed21ba547f83fce4472184d80c7");
        EmoteType.RELAX.addTexture(3, "fd2dc4db780294919517306964d65ea078b47b823fda5628be48b6ae61c");
        EmoteType.RELAX.addTexture(2, "e744e61b46a5f749ccaa2bf8132981b8986234359f9a2924f547ec0111e4375");
        EmoteType.RELAX.addTexture(2, "f673dd2ced78d89dac845d395443d6d39bcfe9c74c71a652029487b9281");
        EmoteType.RELAX.addTexture(2, "720f158ea771adf0862e7c1a19f01409cddd6edfd64385682ed7bc653eda3");
        EmoteType.RELAX.addTexture(2, "b6d6581ee0ec93ca9d5f4afbf6e28f5a9582a896ccccb9e7c17e6419e597e27");
        EmoteType.RELAX.addTexture(2, "e08876a49b1abbad149724be3eae35aa6305c529e384c118ba381a81e2df59e");
        for (int n4 = 0; n4 < 2; ++n4) {
            EmoteType.RELAX.addTexture(5, "927ebbf5c2535fe6b5cef8b8a7e1e7067a39ed21ba547f83fce4472184d80c7");
            EmoteType.RELAX.addTexture(5, "b6d6581ee0ec93ca9d5f4afbf6e28f5a9582a896ccccb9e7c17e6419e597e27");
        }
        EmoteType.RELAX.addTexture(7, "cc86703cc5839d413e393f173dde4fb71cfc965e1d254ae7d7bb38bf0a233d5");
        EmoteType.RELAX.addTexture(7, "762c3a6265418977a564fa9376fb5b1a87f9f8b8052c63a2d51817691e4223a");
        for (int n5 = 0; n5 < 4; ++n5) {
            EmoteType.RELAX.addTexture(7, "c6aa9e27c69b4738cf2523e387271ddd1f4bb07ed38af3ad923db578e884081");
            EmoteType.RELAX.addTexture(7, "81b13b2dcb94dcfc30ec7ce7705e6e38a6457c43eb9f8ae1c43ba524163fa469");
            EmoteType.RELAX.addTexture(7, "2f7e6c079efa69cb3a23dd3b147643c7cb5e5c9129b74af0cab47b04f355a");
        }
        EmoteType.SPICY.addTexture(5, "927ebbf5c2535fe6b5cef8b8a7e1e7067a39ed21ba547f83fce4472184d80c7");
        EmoteType.SPICY.addTexture(5, "584f9ee685ec654ea5941d789838785d3214e236153fa2e4822876bcfef89");
        EmoteType.SPICY.addTexture(5, "e34dad6c9eab0baf9f9d9b0f6be6e19936b3a1e20fc3e217b885eadeb318");
        EmoteType.SPICY.addTexture(5, "963ba9f88fa5f5359d5cd94e9df8ceebfdcd4355d5bfb5fe673251ede0e7f63d");
        EmoteType.SPICY.addTexture(5, "8e58ac9911456110377799cdfd75a5f4fd731a38bd29ab39887943aaa139");
        EmoteType.SPICY.addTexture(5, "21f96bfc905c4689698c09cd2cbb818825146c1bc618494033e8077cb9a70");
        EmoteType.SPICY.addTexture(5, "1e5675557dedeeedd4599fd0c0b2eae7d8defbfc613f321f510c74c662a3a6");
        for (int n6 = 0; n6 < 20; ++n6) {
            EmoteType.SPICY.addTexture(2, "49f0482ca6ba599fbed46a1e7bb332459ac1321bb5cc2dc9bf2a2ba7f61b8");
            EmoteType.SPICY.addTexture(2, "8811ec3618dfc075818af8a7fda52ee56c0bb203e4d27821e1786f2af55b9d");
        }
        EmoteType.DEAL_WITH_IT.addTexture(5, "65ee6541245fd5b98217ceaf9410a4aa9fbd5abcfb646d33222729a1d6d159f");
        EmoteType.DEAL_WITH_IT.addTexture(5, "9aaf1dcbc1a88534f4c6a83961ad08569f7559dcc967bb648ac48ee70fc4c");
        EmoteType.DEAL_WITH_IT.addTexture(5, "eba7158981232a5121e955ea3f20e3611ab85bd17b3724ac2481dbc0c19054");
        EmoteType.DEAL_WITH_IT.addTexture(5, "599eb74ec2bb0added18ebd777a5a7478e86256deb536dccbfc4acd9be2a28");
        EmoteType.DEAL_WITH_IT.addTexture(15, "de2d826df4cf169cb8bc28ef4a1ce47b273ed57c7993c41c2069c8d36ce47df6");
        EmoteType.DEAL_WITH_IT.addTexture(5, "4734d2b46ad33a689869515d17a820f1f2319aad54554b6ba28e79e2c9ce5eb");
        EmoteType.DEAL_WITH_IT.addTexture(15, "a324b5c8fe69dfd062185589962da66e28a8999a797c04053abc1e3fc1c1");
        EmoteType.DEAL_WITH_IT.addTexture(5, "fe79e5f9d81c8a84e1f9c2a817687f3b9543bdbb609ef8c6f90974f73d0c2");
        EmoteType.DEAL_WITH_IT.addTexture(50, "3923f5b42d1677155d08442c0aa39851596156c5e09b3461aa35868989a4bb");
    }
    
    private EmoteType(final String s, final String displayName, final String s2, final int cooldown, final int mysteryDust, final Rarity rarity, final String hologram, final List<String> list, final GMaterial.GMaterialHead headTexture, final Long ticksPerFrame) {
        super(Category.EMOTES, s, displayName, s2, mysteryDust, rarity, list, list);
        if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getEmotesFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Cooldown") == null) {
            this.cooldown = cooldown;
            FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Cooldown", this.cooldown);
        }
        else {
            this.cooldown = FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Cooldown");
        }
        if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getEmotesFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getEmotesFile().getBoolean(String.valueOf(this.getFilePath()) + ".Enabled");
        }
        if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getEmotesFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getEmotesFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Hologram.Enabled") == null) {
            this.isHologramEnable = true;
            FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Hologram.Enabled", true);
        }
        else {
            this.isHologramEnable = FileManager.getEmotesFile().getBoolean(String.valueOf(this.getFilePath()) + ".Hologram.Enabled");
        }
        if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Hologram.Text") == null) {
            this.hologram = hologram;
            FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Hologram.Text", this.hologram);
        }
        else {
            this.hologram = FileManager.getEmotesFile().getString(String.valueOf(this.getFilePath()) + ".Hologram.Text");
        }
        if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                }
                else {
                    FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getEmotesFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getEmotesFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getEmotesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getEmotesFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.headTexture = headTexture;
        this.ticksPerFrame = ticksPerFrame;
        this.itemStack = ItemUtils.item(this.displayName, this.headTexture.getMaterial());
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Cosmetics", "true");
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Category", Category.EMOTES.getNBTTag());
        this.frames = new ArrayList<ItemStack>();
        if (!EmoteType.VALUES.contains(this)) {
            EmoteType.VALUES.add(this);
        }
    }
    
    public EmoteType(final String s, final String displayName, final String s2, final int cooldown, final int mysteryDust, final Rarity rarity, final boolean isHologramEnable, final String hologram, final List<String> lockedLore, final List<String> unlockedLore, final GMaterial.GMaterialHead headTexture, final Long ticksPerFrame, final boolean isEnable, final boolean canBeFound, final boolean purchasable, final ItemStack itemStack, final ArrayList<ItemStack> frames) {
        super(Category.EMOTES, s, displayName, s2, mysteryDust, rarity, lockedLore, unlockedLore);
        this.displayName = displayName;
        this.cooldown = cooldown;
        this.mysteryDust = mysteryDust;
        this.rarity = rarity;
        this.isHologramEnable = isHologramEnable;
        this.hologram = hologram;
        this.lockedLore = lockedLore;
        this.unlockedLore = unlockedLore;
        this.headTexture = headTexture;
        this.ticksPerFrame = ticksPerFrame;
        this.isEnable = isEnable;
        this.canBeFound = canBeFound;
        this.purchasable = purchasable;
        this.itemStack = itemStack;
        this.frames = frames;
        if (!EmoteType.VALUES.contains(this)) {
            EmoteType.VALUES.add(this);
        }
        if (isEnable && !EmoteType.ENABLED.contains(this)) {
            EmoteType.ENABLED.add(this);
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
    
    public boolean isHologramEnabled() {
        return this.isHologramEnable;
    }
    
    public String getHologram() {
        return ChatUtil.format(this.hologram);
    }
    
    @Override
    public List<String> getLockedLore() {
        return this.lockedLore;
    }
    
    @Override
    public List<String> getUnlockedLore() {
        return this.unlockedLore;
    }
    
    public GMaterial.GMaterialHead getMaterialHead() {
        return this.headTexture;
    }
    
    public Long getTicksPerFrame() {
        return this.ticksPerFrame;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    public void addTexture(final int n, final String s) {
        final ItemStack setNBTTag = CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(ItemUtils.itemSkull(this.displayName, s), "Cosmetics", "true"), "Category", Category.EMOTES.getNBTTag());
        if (n == 1) {
            this.frames.add(setNBTTag);
        }
        else {
            for (int i = 0; i < n; ++i) {
                this.frames.add(setNBTTag);
            }
        }
    }
    
    public ArrayList<ItemStack> getFrames() {
        return this.frames;
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
    
    @Override
    public String getFilePath() {
        return "Emotes." + super.getName();
    }
    
    @Override
    public void equip(final PlayerManager playerManager) {
        if (!this.isEnabled() || !Category.EMOTES.isEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        if (player.getInventory().getHelmet() != null) {
            CategoryManager.removeHelmetCosmetic(playerManager);
            if (player.getInventory().getHelmet() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_HELMET_TO_EQUIP_EMOTE.getFormatMessage())) {
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
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory().getItem(gadgetSlot) != null) {
            CategoryManager.removeHotbarCosmetic(playerManager);
            if (player.getInventory().getItem(gadgetSlot) != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ITEM_FROM_SLOT_TO_EQUIP_EMOTE.getFormatMessage().replace("{SLOT}", String.valueOf(gadgetSlot + 1)).replace("{ITEM}", player.getInventory().getItem(gadgetSlot).getType().name()))) {
                    return;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    final Player player3;
                    final int n;
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        player3.getWorld().dropItemNaturally(player3.getLocation(), player3.getInventory().getItem(n).clone());
                        player3.getInventory().setItem(n, (ItemStack)null);
                        player3.updateInventory();
                        return;
                    });
                }
            }
        }
        if (playerManager.getEquippedMorph() != null) {
            playerManager.unequipMorph();
        }
        playerManager.setEquippedEmote(this);
        player.getInventory().setItem(gadgetSlot, this.getItemStack());
        player.updateInventory();
        new Emote(player.getUniqueId(), this);
    }
    
    @Override
    public void unequip(final PlayerManager playerManager) {
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory().getItem(gadgetSlot) != null || playerManager.getEquippedEmote() != null || player.getInventory().getHelmet() != null) {
            if (player.getInventory().getItem(gadgetSlot) != null && player.getInventory().getItem(gadgetSlot).getItemMeta() != null && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getItem(gadgetSlot), "Category", Category.EMOTES.getNBTTag())) {
                player.getInventory().setItem(gadgetSlot, (ItemStack)null);
                player.updateInventory();
            }
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta() != null && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getHelmet(), "Category", Category.EMOTES.getNBTTag())) {
                player.getInventory().setHelmet((ItemStack)null);
                player.updateInventory();
            }
        }
        if (playerManager.getEquippedEmote() != null) {
            playerManager.setEquippedEmote(null);
        }
        if (playerManager.getCurrentEmote() != null) {
            playerManager.removeEmote();
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
        if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.EMOTES)) {
            player.sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return false;
        }
        if (player.getInventory().getHelmet() != null) {
            CategoryManager.removeHelmetCosmetic(playerManager);
            if (player.getInventory().getHelmet() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_HELMET_TO_EQUIP_EMOTE.getFormatMessage())) {
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
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory().getItem(gadgetSlot) != null) {
            CategoryManager.removeHotbarCosmetic(playerManager);
            if (player.getInventory().getItem(gadgetSlot) != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ITEM_FROM_SLOT_TO_EQUIP_EMOTE.getFormatMessage().replace("{SLOT}", String.valueOf(gadgetSlot + 1)).replace("{ITEM}", player.getInventory().getItem(gadgetSlot).getType().name()))) {
                    return false;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    final Player player3;
                    final int n;
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        player3.getWorld().dropItemNaturally(player3.getLocation(), player3.getInventory().getItem(n).clone());
                        player3.getInventory().setItem(n, (ItemStack)null);
                        player3.updateInventory();
                        return;
                    });
                }
            }
        }
        return true;
    }
    
    public static List<EmoteType> enabled() {
        return EmoteType.ENABLED;
    }
    
    public static List<EmoteType> values() {
        return EmoteType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final EmoteType emoteType : values()) {
            if (emoteType.isEnabled() && !EmoteType.ENABLED.contains(emoteType)) {
                EmoteType.ENABLED.add(emoteType);
            }
        }
    }
    
    public static void sortItems() {
        if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.NAME) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super EmoteType, ? extends Comparable>)EmoteType::getDisplayNameStripColor));
        }
        else if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.RARITY) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super Object, ? extends Comparable>)EmoteType::getRarity).thenComparing((Function<? super Object, ? extends Comparable>)EmoteType::getDisplayNameStripColor));
        }
    }
    
    public static EmoteType valueOf(final String anotherString) {
        for (final EmoteType emoteType : values()) {
            if (emoteType.getName().equalsIgnoreCase(anotherString)) {
                return emoteType;
            }
        }
        return null;
    }
}
