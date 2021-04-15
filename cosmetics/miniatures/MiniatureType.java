

package ws.billy.CookieGadgets.cosmetics.miniatures;

import java.util.Collections;
import java.util.function.Function;
import java.util.Comparator;
import ws.billy.CookieGadgets.utils.EnumInventorySort;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureSnowglobe;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureMars;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureGlobe;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniaturePresent;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureLantern;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureReindeer;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureSnowman;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureSanta;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureMiner;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureGrimReaper;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureGhost;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureClown;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureScarecrow;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureSwampMonster;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureWitch;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureIronGolem;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureEnderman;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureZombie;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureAstronaut;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureDevil;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureMrSmiley;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.MiniatureDoge;
import ws.billy.CookieGadgets.utils.GColor;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.cosmetics.miniatures.MiniatureArmorType;
import ws.billy.CookieGadgets.utils.cosmetics.miniatures.EnumMiniatureEquipments;
import java.util.Arrays;
import java.util.ArrayList;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.Miniature;
import ws.billy.CookieGadgets.utils.cosmetics.miniatures.MiniatureEquipments;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticMaterialType;

public class MiniatureType extends CosmeticMaterialType
{
    private static final List<MiniatureType> ENABLED;
    private static final List<MiniatureType> VALUES;
    public static final MiniatureType DOGE;
    public static final MiniatureType MR_SMILEY;
    public static final MiniatureType DEVIL;
    public static final MiniatureType ASTRONAUT;
    public static final MiniatureType ZOMBIE;
    public static final MiniatureType ENDERMAN;
    public static final MiniatureType IRON_GOLEM;
    public static final MiniatureType WITCH;
    public static final MiniatureType SWAMP_MONSTER;
    public static final MiniatureType SCARECROW;
    public static final MiniatureType CLOWN;
    public static final MiniatureType GHOST;
    public static final MiniatureType GRIM_REAPER;
    public static final MiniatureType MINER;
    public static final MiniatureType SANTA;
    public static final MiniatureType SNOWMAN;
    public static final MiniatureType REINDEER;
    public static final MiniatureType LANTERN;
    public static final MiniatureType PRESENT;
    public static final MiniatureType GLOBE;
    public static final MiniatureType MARS;
    public static final MiniatureType SNOWGLOBE;
    private String displayName;
    private GMaterial material;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private boolean invisible;
    private MiniatureEquipments equipments;
    private long repeatDelay;
    private Class<? extends Miniature> clazz;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    
    static {
        ENABLED = new ArrayList<MiniatureType>();
        VALUES = new ArrayList<MiniatureType>();
        DOGE = new MiniatureType("Doge", "&9Doge Miniature", new GMaterial("head:b72e164fc95e9fe7389da2f1b08e138ce1df6bc256511a50de23cc358cc83c"), "CookieGadgets.miniatures.doge", 42, Rarity.RARE, Arrays.asList("&7Select a Doge Miniature to", "&7follow you."), false, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "b72e164fc95e9fe7389da2f1b08e138ce1df6bc256511a50de23cc358cc83c"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#cb9800"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#cb9800"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#000000")))), 2L, MiniatureDoge.class);
        MR_SMILEY = new MiniatureType("Mr Smiley", "&9Mr Smiley Miniature", new GMaterial("head:0d366a476466f0241af31c71db697855a39a16dc14bddff2098ed5050ff"), "CookieGadgets.miniatures.mrsmiley", 45, Rarity.RARE, Arrays.asList("&7Select a Mr Smiley Miniature to", "&7follow you."), false, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "0d366a476466f0241af31c71db697855a39a16dc14bddff2098ed5050ff"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#7FFF00"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#7FFF00"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#7FFF00")))), 2L, MiniatureMrSmiley.class);
        DEVIL = new MiniatureType("Devil", "&9Devil Miniature", new GMaterial("head:9da39269ef45f825ec61bb4f8aa09bd3cf07996fb6fac338a6e91d6699ae425"), "CookieGadgets.miniatures.devil", 48, Rarity.RARE, Arrays.asList("&7Select a Devil Miniature to", "&7follow you."), false, new MiniatureEquipments(new MiniatureArmorType(EnumMiniatureEquipments.HAND, new GMaterial(EnumMaterial.FLINT_AND_STEEL)), new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "9da39269ef45f825ec61bb4f8aa09bd3cf07996fb6fac338a6e91d6699ae425"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#F41600"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#F41600"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#F41600")))), 2L, MiniatureDevil.class);
        ASTRONAUT = new MiniatureType("Astronaut", "&5Astronaut Miniature", new GMaterial("head:ec342e45dc4d016b355bdb1e80acf9928b2d420c8eafcc59741d672145cf4385"), "CookieGadgets.miniatures.astronaut", 58, Rarity.EPIC, Arrays.asList("&7Select an Astronaut Miniature to", "&7follow you."), false, new MiniatureEquipments(new MiniatureArmorType(EnumMiniatureEquipments.HAND, new GMaterial(EnumMaterial.NETHER_STAR)), new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "ec342e45dc4d016b355bdb1e80acf9928b2d420c8eafcc59741d672145cf4385"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#C0C0C0"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#C0C0C0"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#C0C0C0")))), 2L, MiniatureAstronaut.class);
        ZOMBIE = new MiniatureType("Zombie", "&9Zombie Miniature", new GMaterial(EnumMaterial.ZOMBIE_HEAD), "CookieGadgets.miniatures.zombie", 55, Rarity.RARE, Arrays.asList("&7Select a Zombie Miniature to", "&7follow you."), false, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, new GMaterial(EnumMaterial.ZOMBIE_HEAD)), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#00AA00"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#00AA00"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#00AA00")))), 2L, MiniatureZombie.class);
        ENDERMAN = new MiniatureType("Enderman", "&5Enderman Miniature", new GMaterial("head:7a59bb0a7a32965b3d90d8eafa899d1835f424509eadd4e6b709ada50b9cf"), "CookieGadgets.miniatures.enderman", 65, Rarity.EPIC, Arrays.asList("&7Select an Enderman Miniature to", "&7follow you."), false, new MiniatureEquipments(new MiniatureArmorType(EnumMiniatureEquipments.HAND, new GMaterial(EnumMaterial.ENDER_PEARL)), new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "7a59bb0a7a32965b3d90d8eafa899d1835f424509eadd4e6b709ada50b9cf"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#000000"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#000000"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#000000")))), 2L, MiniatureEnderman.class);
        IRON_GOLEM = new MiniatureType("Iron Golem", "&5Iron Golem Miniature", new GMaterial("head:89091d79ea0f59ef7ef94d7bba6e5f17f2f7d4572c44f90f76c4819a714"), "CookieGadgets.miniatures.irongolem", 65, Rarity.EPIC, Arrays.asList("&7Select an Iron Golem Miniature", "&7to follow you."), false, new MiniatureEquipments(new MiniatureArmorType(EnumMiniatureEquipments.HAND, new GMaterial(EnumMaterial.POPPY)), new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "89091d79ea0f59ef7ef94d7bba6e5f17f2f7d4572c44f90f76c4819a714"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#C0C0C0"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#C0C0C0"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#C0C0C0")))), 2L, MiniatureIronGolem.class);
        WITCH = new MiniatureType("Witch", "&6Witch Miniature", new GMaterial("head:d122b564634cc7b0305881336dab63dbb6d311c5acf1f604eae179597f2364"), "CookieGadgets.miniatures.witch", 70, Rarity.LEGENDARY, Arrays.asList("&7Select a Witch Miniature to", "&7follow you."), false, new MiniatureEquipments(new MiniatureArmorType(EnumMiniatureEquipments.HAND, new GMaterial(EnumMaterial.POTION)), new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "d122b564634cc7b0305881336dab63dbb6d311c5acf1f604eae179597f2364"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#AA00AA"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#AA00AA"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#AA00AA")))), 2L, MiniatureWitch.class);
        SWAMP_MONSTER = new MiniatureType("Swamp Monster", "&6Swamp Monster Miniature", new GMaterial("head:d88ba8bb50b79e441e47b7e452764d5fff6693779d2dadd9f7f52f98d7ea0"), "CookieGadgets.miniatures.swampmonster", 72, Rarity.LEGENDARY, Arrays.asList("&7Select a Swamp Monster Miniature", "&7to follow you."), false, new MiniatureEquipments(new MiniatureArmorType(EnumMiniatureEquipments.HAND, new GMaterial(EnumMaterial.SLIME_BALL)), new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "d88ba8bb50b79e441e47b7e452764d5fff6693779d2dadd9f7f52f98d7ea0"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#00AA00"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#00AA00"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#00AA00")))), 2L, MiniatureSwampMonster.class);
        SCARECROW = new MiniatureType("Scarecrow", "&6Scarecrow Miniature", new GMaterial("head:7cf772a5542b97c98de6b1133942444aa6b83fe2916fab816c106eab9468"), "CookieGadgets.miniatures.scarecrow", 72, Rarity.LEGENDARY, Arrays.asList("&7Select a Scarecrow Miniature to", "&7follow you."), false, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "7cf772a5542b97c98de6b1133942444aa6b83fe2916fab816c106eab9468"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#A0522D"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#A0522D"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#A0522D")))), 2L, MiniatureScarecrow.class);
        CLOWN = new MiniatureType("Clown", "&5Clown Miniature", new GMaterial("head:86dbc1debc57438a5de4ba915151382abc3d8f1318e2a35e78dfb30f04bc467"), "CookieGadgets.miniatures.clown", 62, Rarity.EPIC, Arrays.asList("&7Select a Clown Miniature to", "&7follow you."), false, new MiniatureEquipments(new MiniatureArmorType(EnumMiniatureEquipments.HAND, new GMaterial(EnumMaterial.POPPY)), new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "86dbc1debc57438a5de4ba915151382abc3d8f1318e2a35e78dfb30f04bc467"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#AA0000"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#FFFFFF"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#000000")))), 2L, MiniatureClown.class);
        GHOST = new MiniatureType("Ghost", "&5Ghost Miniature", new GMaterial("head:32555ca901ab6301e7ce9789b57ebab59349c1acd7973f4ebbd0c38e10ada1f1"), "CookieGadgets.miniatures.ghost", 60, Rarity.EPIC, Arrays.asList("&7Select a Ghost Miniature to", "&7follow you."), true, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "32555ca901ab6301e7ce9789b57ebab59349c1acd7973f4ebbd0c38e10ada1f1"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#FFFFFF"))), null, null), 2L, MiniatureGhost.class);
        GRIM_REAPER = new MiniatureType("Grim Reaper", "&6Grim Reaper Miniature", new GMaterial("head:badfc62ff19950a9afd6c23291d0b25e19c74eb922c184e5edd3255f3fad9565"), "CookieGadgets.miniatures.grimreaper", 75, Rarity.LEGENDARY, Arrays.asList("&7Select a Grim Reaper Miniature", "&7to follow you."), true, new MiniatureEquipments(new MiniatureArmorType(EnumMiniatureEquipments.HAND, new GMaterial(EnumMaterial.IRON_HOE)), new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "badfc62ff19950a9afd6c23291d0b25e19c74eb922c184e5edd3255f3fad9565"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#000000"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#000000"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#000000")))), 2L, MiniatureGrimReaper.class);
        MINER = new MiniatureType("Miner", "&6Miner Miniature", new GMaterial("head:d357444ade64ec6cea645ec57e775864d67c5fa62299786e03799317ee4ad"), "CookieGadgets.miniatures.miner", 75, Rarity.LEGENDARY, Arrays.asList("&7Select a Miner Miniature to", "&7follow you."), false, new MiniatureEquipments(new MiniatureArmorType(EnumMiniatureEquipments.HAND, new GMaterial(EnumMaterial.DIAMOND_PICKAXE)), new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "d357444ade64ec6cea645ec57e775864d67c5fa62299786e03799317ee4ad"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#FFFFFF"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#5555FF"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#000000")))), 2L, MiniatureMiner.class);
        SANTA = new MiniatureType("Santa", "&6Santa Miniature", new GMaterial("head:14e424b1676feec3a3f8ebade9e7d6a6f71f7756a869f36f7df0fc182d436e"), "CookieGadgets.miniatures.santa", 68, Rarity.LEGENDARY, Arrays.asList("&7Select a Santa Miniature to", "&7follow you."), false, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "14e424b1676feec3a3f8ebade9e7d6a6f71f7756a869f36f7df0fc182d436e"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#AA0000"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#AA0000"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#AA0000")))), 2L, MiniatureSanta.class);
        SNOWMAN = new MiniatureType("Snowman", "&6Snowman Miniature", new GMaterial("head:ce4bee0403a208fbdc2ab2a61e3ff23b3270554253683a6d0a5516aca84a2be7"), "CookieGadgets.miniatures.snowman", 68, Rarity.LEGENDARY, Arrays.asList("&7Select a Snowman Miniature to", "&7follow you."), false, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "ce4bee0403a208fbdc2ab2a61e3ff23b3270554253683a6d0a5516aca84a2be7"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#FFFFFF"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#FFFFFF"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#FFFFFF")))), 2L, MiniatureSnowman.class);
        REINDEER = new MiniatureType("Reindeer", "&5Reindeer Miniature", new GMaterial("head:ebd46b38b21b342caf917ad9ca42afb68388a5591bcc9aded1e8e346e18890"), "CookieGadgets.miniatures.reindeer", 65, Rarity.EPIC, Arrays.asList("&7Select a Reindeer Miniature to", "&7follow you."), false, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "ebd46b38b21b342caf917ad9ca42afb68388a5591bcc9aded1e8e346e18890"), new MiniatureArmorType(EnumMiniatureEquipments.CHESTPLATE, new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#AA6630"))), new MiniatureArmorType(EnumMiniatureEquipments.LEGGINGS, new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#AA6630"))), new MiniatureArmorType(EnumMiniatureEquipments.BOOTS, new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#AA6630")))), 2L, MiniatureReindeer.class);
        LANTERN = new MiniatureType("Lantern", "&5Lantern Miniature", new GMaterial("head:f243706075a4f5f2731a8ad5f636058b1d5b3a49c1f14f5b9babcf2044f59355"), "CookieGadgets.miniatures.lantern", 65, Rarity.EPIC, Arrays.asList("&7Select a Lantern Miniature to", "&7follow you."), true, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "f243706075a4f5f2731a8ad5f636058b1d5b3a49c1f14f5b9babcf2044f59355"), null, null, null), 0L, MiniatureLantern.class);
        PRESENT = new MiniatureType("Present", "&6Present Miniature", new GMaterial("head:6cef9aa14e884773eac134a4ee8972063f466de678363cf7b1a21a85b7"), "CookieGadgets.miniatures.present", 78, Rarity.LEGENDARY, Arrays.asList("&7Select a Present Miniature to", "&7follow you."), true, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "6cef9aa14e884773eac134a4ee8972063f466de678363cf7b1a21a85b7"), null, null, null), 0L, MiniaturePresent.class);
        GLOBE = new MiniatureType("Globe", "&6Globe Miniature", new GMaterial("head:c9c8881e42915a9d29bb61a16fb26d059913204d265df5b439b3d792acd56"), "CookieGadgets.miniatures.globe", 78, Rarity.LEGENDARY, Arrays.asList("&7Select a Globe Miniature to", "&7follow you."), true, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "c9c8881e42915a9d29bb61a16fb26d059913204d265df5b439b3d792acd56"), null, null, null), 0L, MiniatureGlobe.class);
        MARS = new MiniatureType("Mars", "&6Mars Miniature", new GMaterial("head:777d616bc44ac9b3730fed47f29a378f88a16728c67048c1a387d229e1cba"), "CookieGadgets.miniatures.mars", 80, Rarity.LEGENDARY, Arrays.asList("&7Select a Mars Miniature to", "&7follow you."), true, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "777d616bc44ac9b3730fed47f29a378f88a16728c67048c1a387d229e1cba"), null, null, null), 0L, MiniatureMars.class);
        SNOWGLOBE = new MiniatureType("Snowglobe", "&6Snowglobe Miniature", new GMaterial("head:7bbe8fd1aa39f15076e884dfe6ddb9a3f3761db31e2b1f9940b5dfd34d1c4d"), "CookieGadgets.miniatures.snowglobe", 85, Rarity.LEGENDARY, Arrays.asList("&7Select a Snowglobe Miniature to", "&7follow you."), true, new MiniatureEquipments(null, new MiniatureArmorType(EnumMiniatureEquipments.HELMET, "7bbe8fd1aa39f15076e884dfe6ddb9a3f3761db31e2b1f9940b5dfd34d1c4d"), null, null, null), 0L, MiniatureSnowglobe.class);
    }
    
    private MiniatureType(final String s, final String displayName, final GMaterial material, final String s2, final int mysteryDust, final Rarity rarity, final List<String> list, final boolean invisible, final MiniatureEquipments equipments, final long repeatDelay, final Class<? extends Miniature> clazz) {
        super(Category.MINIATURES, s, displayName, material, s2, mysteryDust, rarity, list, list);
        if (FileManager.getMiniaturesFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getMiniaturesFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getMiniaturesFile().get(String.valueOf(this.getFilePath()) + ".Material") == null) {
            this.material = material;
            FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Material", this.material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getMiniaturesFile().getString(String.valueOf(this.getFilePath()) + ".Material"));
        }
        if (FileManager.getMiniaturesFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getMiniaturesFile().getInt(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getMiniaturesFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getMiniaturesFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getMiniaturesFile().get(String.valueOf(this.getFilePath()) + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getMiniaturesFile().getBoolean(String.valueOf(this.getFilePath()) + ".Enabled");
        }
        if (FileManager.getMiniaturesFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getMiniaturesFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getMiniaturesFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getMiniaturesFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (FileManager.getMiniaturesFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getMiniaturesFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                }
                else {
                    FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getMiniaturesFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getMiniaturesFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getMiniaturesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getMiniaturesFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.invisible = invisible;
        this.equipments = equipments;
        this.repeatDelay = repeatDelay;
        this.clazz = clazz;
        if (!MiniatureType.VALUES.contains(this)) {
            MiniatureType.VALUES.add(this);
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
    
    public boolean isInvisible() {
        return this.invisible;
    }
    
    public MiniatureEquipments getEquipments() {
        return this.equipments;
    }
    
    public long getRepeatDelay() {
        return this.repeatDelay;
    }
    
    public Class<? extends Miniature> getClazz() {
        return this.clazz;
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
        return "Miniatures." + super.getName();
    }
    
    @Override
    public void equip(final PlayerManager playerManager) {
        if (!this.isEnabled() || !Category.MINIATURES.isEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        this.unequip(playerManager);
        playerManager.setEquippedMiniature(this);
        final Player player = playerManager.getPlayer();
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
    }
    
    @Override
    public void unequip(final PlayerManager playerManager) {
        if (playerManager == null) {
            return;
        }
        if (playerManager.getEquippedMiniature() != null) {
            playerManager.setEquippedMiniature(null);
        }
        if (playerManager.getCurrentMiniature() != null) {
            playerManager.removeMiniature();
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
        if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.MINIATURES)) {
            player.sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return false;
        }
        return true;
    }
    
    public static List<MiniatureType> enabled() {
        return MiniatureType.ENABLED;
    }
    
    public static List<MiniatureType> values() {
        return MiniatureType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final MiniatureType miniatureType : values()) {
            if (miniatureType.isEnabled() && !MiniatureType.ENABLED.contains(miniatureType)) {
                MiniatureType.ENABLED.add(miniatureType);
            }
        }
    }
    
    public static void sortItems() {
        if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.NAME) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super MiniatureType, ? extends Comparable>)MiniatureType::getDisplayNameStripColor));
        }
        else if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.RARITY) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super Object, ? extends Comparable>)MiniatureType::getRarity).thenComparing((Function<? super Object, ? extends Comparable>)MiniatureType::getDisplayNameStripColor));
        }
    }
    
    public static MiniatureType valueOf(final String anotherString) {
        for (final MiniatureType miniatureType : values()) {
            if (miniatureType.getName().equalsIgnoreCase(anotherString)) {
                return miniatureType;
            }
        }
        return null;
    }
}
