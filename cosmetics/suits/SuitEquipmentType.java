

package ws.billy.CookieGadgets.cosmetics.suits;

import java.util.Iterator;
import java.util.Set;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.EnumEquipType;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.CategoryManager;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import org.bukkit.Color;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.cosmetics.suits.SuitMaterial;
import ws.billy.CookieGadgets.utils.GMaterial;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticType;

public class SuitEquipmentType extends CosmeticType
{
    private static final List<SuitEquipmentType> VALUES;
    private static final HashMap<SuitType, ArrayList<SuitEquipmentType>> SUIT_CATEGORIES;
    public static final SuitEquipmentType FROG_HELMET;
    public static final SuitEquipmentType FROG_CHESTPLATE;
    public static final SuitEquipmentType FROG_LEGGINGS;
    public static final SuitEquipmentType FROG_BOOTS;
    public static final SuitEquipmentType NINJA_HELMET;
    public static final SuitEquipmentType NINJA_CHESTPLATE;
    public static final SuitEquipmentType NINJA_LEGGINGS;
    public static final SuitEquipmentType NINJA_BOOTS;
    public static final SuitEquipmentType SPEEDSTER_HELMET;
    public static final SuitEquipmentType SPEEDSTER_CHESTPLATE;
    public static final SuitEquipmentType SPEEDSTER_LEGGINGS;
    public static final SuitEquipmentType SPEEDSTER_BOOTS;
    public static final SuitEquipmentType GHOSTLY_HELMET;
    public static final SuitEquipmentType GHOSTLY_CHESTPLATE;
    public static final SuitEquipmentType GHOSTLY_LEGGINGS;
    public static final SuitEquipmentType GHOSTLY_BOOTS;
    public static final SuitEquipmentType DISCO_HELMET;
    public static final SuitEquipmentType DISCO_CHESTPLATE;
    public static final SuitEquipmentType DISCO_LEGGINGS;
    public static final SuitEquipmentType DISCO_BOOTS;
    public static final SuitEquipmentType MERMAID_HELMET;
    public static final SuitEquipmentType MERMAID_CHESTPLATE;
    public static final SuitEquipmentType MERMAID_LEGGINGS;
    public static final SuitEquipmentType MERMAID_BOOTS;
    public static final SuitEquipmentType SPOODERMAN_HELMET;
    public static final SuitEquipmentType SPOODERMAN_CHESTPLATE;
    public static final SuitEquipmentType SPOODERMAN_LEGGINGS;
    public static final SuitEquipmentType SPOODERMAN_BOOTS;
    public static final SuitEquipmentType WARRIOR_HELMET;
    public static final SuitEquipmentType WARRIOR_CHESTPLATE;
    public static final SuitEquipmentType WARRIOR_LEGGINGS;
    public static final SuitEquipmentType WARRIOR_BOOTS;
    public static final SuitEquipmentType NECROMANCER_HELMET;
    public static final SuitEquipmentType NECROMANCER_CHESTPLATE;
    public static final SuitEquipmentType NECROMANCER_LEGGINGS;
    public static final SuitEquipmentType NECROMANCER_BOOTS;
    public static final SuitEquipmentType THOR_HELMET;
    public static final SuitEquipmentType THOR_CHESTPLATE;
    public static final SuitEquipmentType THOR_LEGGINGS;
    public static final SuitEquipmentType THOR_BOOTS;
    public static final SuitEquipmentType BAKER_HELMET;
    public static final SuitEquipmentType BAKER_CHESTPLATE;
    public static final SuitEquipmentType BAKER_LEGGINGS;
    public static final SuitEquipmentType BAKER_BOOTS;
    public static final SuitEquipmentType BUMBLEBEE_HELMET;
    public static final SuitEquipmentType BUMBLEBEE_CHESTPLATE;
    public static final SuitEquipmentType BUMBLEBEE_LEGGINGS;
    public static final SuitEquipmentType BUMBLEBEE_BOOTS;
    public static final SuitEquipmentType FIREFIGHTER_HELMET;
    public static final SuitEquipmentType FIREFIGHTER_CHESTPLATE;
    public static final SuitEquipmentType FIREFIGHTER_LEGGINGS;
    public static final SuitEquipmentType FIREFIGHTER_BOOTS;
    public static final SuitEquipmentType PLUMBER_HELMET;
    public static final SuitEquipmentType PLUMBER_CHESTPLATE;
    public static final SuitEquipmentType PLUMBER_LEGGINGS;
    public static final SuitEquipmentType PLUMBER_BOOTS;
    public static final SuitEquipmentType ICE_WALKER_HELMET;
    public static final SuitEquipmentType ICE_WALKER_CHESTPLATE;
    public static final SuitEquipmentType ICE_WALKER_LEGGINGS;
    public static final SuitEquipmentType ICE_WALKER_BOOTS;
    public static final SuitEquipmentType VAMPIRE_HELMET;
    public static final SuitEquipmentType VAMPIRE_CHESTPLATE;
    public static final SuitEquipmentType VAMPIRE_LEGGINGS;
    public static final SuitEquipmentType VAMPIRE_BOOTS;
    private SuitType category;
    private String displayName;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private GMaterial headMaterial;
    private SuitMaterial suitMaterial;
    private boolean canBeFound;
    private boolean purchasable;
    private ItemStack itemStack;
    
    static {
        VALUES = new ArrayList<SuitEquipmentType>();
        SUIT_CATEGORIES = new HashMap<SuitType, ArrayList<SuitEquipmentType>>();
        FROG_HELMET = new SuitEquipmentType(SuitType.FROG, "Frog Helmet", "&6Frog Suit Helmet", "CookieGadgets.suits.frog.helmet", 80, Rarity.LEGENDARY, null, new GMaterial("head:cc3c601e4ba3064bec98a51552473aa7a5646e60fddc18d187c09b4a351d2"));
        FROG_CHESTPLATE = new SuitEquipmentType(SuitType.FROG, "Frog Chestplate", "&5Frog Suit Chestplate", "CookieGadgets.suits.frog.chestplate", 32, Rarity.EPIC, null, SuitMaterial.FROG_CHESTPLATE);
        FROG_LEGGINGS = new SuitEquipmentType(SuitType.FROG, "Frog Leggings", "&9Frog Suit Leggings", "CookieGadgets.suits.frog.leggings", 18, Rarity.RARE, null, SuitMaterial.FROG_LEGGINGS);
        FROG_BOOTS = new SuitEquipmentType(SuitType.FROG, "Frog Boots", "&aFrog Suit Boots", "CookieGadgets.suits.frog.boots", 7, Rarity.COMMON, null, SuitMaterial.FROG_BOOTS);
        NINJA_HELMET = new SuitEquipmentType(SuitType.NINJA, "Ninja Helmet", "&6Ninja Suit Helmet", "CookieGadgets.suits.ninja.helmet", 80, Rarity.LEGENDARY, null, new GMaterial("head:e96616bb48ac61a153a9f5c35979f3523c24c366c621d6bacaed10f822b68b"));
        NINJA_CHESTPLATE = new SuitEquipmentType(SuitType.NINJA, "Ninja Chestplate", "&5Ninja Suit Chestplate", "CookieGadgets.suits.ninja.chestplate", 35, Rarity.EPIC, null, SuitMaterial.NINJA_CHESTPLATE);
        NINJA_LEGGINGS = new SuitEquipmentType(SuitType.NINJA, "Ninja Leggings", "&9Ninja Suit Leggings", "CookieGadgets.suits.ninja.leggings", 20, Rarity.RARE, null, SuitMaterial.NINJA_LEGGINGS);
        NINJA_BOOTS = new SuitEquipmentType(SuitType.NINJA, "Ninja Boots", "&aNinja Suit Boots", "CookieGadgets.suits.ninja.boots", 10, Rarity.COMMON, null, SuitMaterial.NINJA_BOOTS);
        SPEEDSTER_HELMET = new SuitEquipmentType(SuitType.SPEEDSTER, "Speedster Helmet", "&6Speedster Suit Helmet", "CookieGadgets.suits.speedster.helmet", 80, Rarity.LEGENDARY, null, new GMaterial("head:5138ccdee2eb7cf60d5489d388f210629b2e787bb530e65bd1a73126d01067"));
        SPEEDSTER_CHESTPLATE = new SuitEquipmentType(SuitType.SPEEDSTER, "Speedster Chestplate", "&5Speedster Suit Chestplate", "CookieGadgets.suits.speedster.chestplate", 25, Rarity.EPIC, null, SuitMaterial.SPEEDSTER_CHESTPLATE);
        SPEEDSTER_LEGGINGS = new SuitEquipmentType(SuitType.SPEEDSTER, "Speedster Leggings", "&9Speedster Suit Leggings", "CookieGadgets.suits.speedster.leggings", 13, Rarity.RARE, null, SuitMaterial.SPEEDSTER_LEGGINGS);
        SPEEDSTER_BOOTS = new SuitEquipmentType(SuitType.SPEEDSTER, "Speedster Boots", "&aSpeedster Suit Boots", "CookieGadgets.suits.speedster.boots", 5, Rarity.COMMON, null, SuitMaterial.SPEEDSTER_BOOTS);
        GHOSTLY_HELMET = new SuitEquipmentType(SuitType.GHOSTLY, "Ghostly Helmet", "&6Ghostly Skeleton Suit Helmet", "CookieGadgets.suits.ghostly.helmet", 80, Rarity.LEGENDARY, null, new GMaterial(EnumMaterial.SKELETON_SKULL));
        GHOSTLY_CHESTPLATE = new SuitEquipmentType(SuitType.GHOSTLY, "Ghostly Chestplate", "&5Ghostly Skeleton Suit Chestplate", "CookieGadgets.suits.ghostly.chestplate", 25, Rarity.EPIC, null, SuitMaterial.GHOSTLY_CHESTPLATE);
        GHOSTLY_LEGGINGS = new SuitEquipmentType(SuitType.GHOSTLY, "Ghostly Leggings", "&9Ghostly Skeleton Suit Leggings", "CookieGadgets.suits.ghostly.leggings", 13, Rarity.RARE, null, SuitMaterial.GHOSTLY_LEGGINGS);
        GHOSTLY_BOOTS = new SuitEquipmentType(SuitType.GHOSTLY, "Ghostly Boots", "&aGhostly Skeleton Suit Boots", "CookieGadgets.suits.ghostly.boots", 5, Rarity.COMMON, null, SuitMaterial.GHOSTLY_BOOTS);
        DISCO_HELMET = new SuitEquipmentType(SuitType.DISCO, "Disco Helmet", "&6Disco Suit Helmet", "CookieGadgets.suits.disco.helmet", 80, Rarity.LEGENDARY, null, new GMaterial(EnumMaterial.LEATHER_HELMET, Color.RED));
        DISCO_CHESTPLATE = new SuitEquipmentType(SuitType.DISCO, "Disco Chestplate", "&5Disco Suit Chestplate", "CookieGadgets.suits.disco.chestplate", 25, Rarity.EPIC, null, SuitMaterial.DISCO_CHESTPLATE);
        DISCO_LEGGINGS = new SuitEquipmentType(SuitType.DISCO, "Disco Leggings", "&9Disco Suit Leggings", "CookieGadgets.suits.disco.leggings", 13, Rarity.RARE, null, SuitMaterial.DISCO_LEGGINGS);
        DISCO_BOOTS = new SuitEquipmentType(SuitType.DISCO, "Disco Boots", "&aDisco Suit Boots", "CookieGadgets.suits.disco.boots", 5, Rarity.COMMON, null, SuitMaterial.DISCO_BOOTS);
        MERMAID_HELMET = new SuitEquipmentType(SuitType.MERMAID, "Mermaid Helmet", "&6Mermaid Suit Helmet", "CookieGadgets.suits.mermaid.helmet", 70, Rarity.LEGENDARY, null, new GMaterial("head:aa36d682f9152a98e53cbf3d583b59df8f024e184531c97c2ea25816d1288f"));
        MERMAID_CHESTPLATE = new SuitEquipmentType(SuitType.MERMAID, "Mermaid Chestplate", "&5Mermaid Suit Chestplate", "CookieGadgets.suits.mermaid.chestplate", 30, Rarity.EPIC, null, SuitMaterial.MERMAID_CHESTPLATE);
        MERMAID_LEGGINGS = new SuitEquipmentType(SuitType.MERMAID, "Mermaid Leggings", "&9Mermaid Suit Leggings", "CookieGadgets.suits.mermaid.leggings", 16, Rarity.RARE, null, SuitMaterial.MERMAID_LEGGINGS);
        MERMAID_BOOTS = new SuitEquipmentType(SuitType.MERMAID, "Mermaid Boots", "&aMermaid Suit Boots", "CookieGadgets.suits.mermaid.boots", 6, Rarity.COMMON, null, SuitMaterial.MERMAID_BOOTS);
        SPOODERMAN_HELMET = new SuitEquipmentType(SuitType.SPOODERMAN, "Spooderman Helmet", "&6Spooderman Suit Helmet", "CookieGadgets.suits.spooderman.helmet", 75, Rarity.LEGENDARY, null, new GMaterial("head:9f2f7ebd9e263c939f54f939c9e2bcb18d4431f5577c389d6cbcf1371b11d"));
        SPOODERMAN_CHESTPLATE = new SuitEquipmentType(SuitType.SPOODERMAN, "Spooderman Chestplate", "&5Spooderman Suit Chestplate", "CookieGadgets.suits.spooderman.chestplate", 33, Rarity.EPIC, null, SuitMaterial.SPOODERMAN_CHESTPLATE);
        SPOODERMAN_LEGGINGS = new SuitEquipmentType(SuitType.SPOODERMAN, "Spooderman Leggings", "&9Spooderman Suit Leggings", "CookieGadgets.suits.spooderman.leggings", 17, Rarity.RARE, null, SuitMaterial.SPOODERMAN_LEGGINGS);
        SPOODERMAN_BOOTS = new SuitEquipmentType(SuitType.SPOODERMAN, "Spooderman Boots", "&aSpooderman Suit Boots", "CookieGadgets.suits.spooderman.boots", 8, Rarity.COMMON, null, SuitMaterial.SPOODERMAN_BOOTS);
        WARRIOR_HELMET = new SuitEquipmentType(SuitType.WARRIOR, "Warrior Helmet", "&6Warrior Suit Helmet", "CookieGadgets.suits.warrior.helmet", 75, Rarity.LEGENDARY, null, new GMaterial("head:38f56b7f7ac16046a9816e57c2c3296420ccddb7cc51b1c0de8075a816b747d9"));
        WARRIOR_CHESTPLATE = new SuitEquipmentType(SuitType.WARRIOR, "Warrior Chestplate", "&5Warrior Suit Chestplate", "CookieGadgets.suits.warrior.chestplate", 30, Rarity.EPIC, null, SuitMaterial.WARRIOR_CHESTPLATE);
        WARRIOR_LEGGINGS = new SuitEquipmentType(SuitType.WARRIOR, "Warrior Leggings", "&9Warrior Suit Leggings", "CookieGadgets.suits.warrior.leggings", 16, Rarity.RARE, null, SuitMaterial.WARRIOR_LEGGINGS);
        WARRIOR_BOOTS = new SuitEquipmentType(SuitType.WARRIOR, "Warrior Boots", "&aWarrior Suit Boots", "CookieGadgets.suits.warrior.boots", 6, Rarity.COMMON, null, SuitMaterial.WARRIOR_BOOTS);
        NECROMANCER_HELMET = new SuitEquipmentType(SuitType.NECROMANCER, "Necromancer Helmet", "&6Necromancer Suit Helmet", "CookieGadgets.suits.necromancer.helmet", 80, Rarity.LEGENDARY, null, new GMaterial("head:9ddbd0bb9622ce124dabb6ba3baa9a9ea71430f870fec15b3ea81f1961a412b2"));
        NECROMANCER_CHESTPLATE = new SuitEquipmentType(SuitType.NECROMANCER, "Necromancer Chestplate", "&5Necromancer Suit Chestplate", "CookieGadgets.suits.necromancer.chestplate", 35, Rarity.EPIC, null, SuitMaterial.NECROMANCER_CHESTPLATE);
        NECROMANCER_LEGGINGS = new SuitEquipmentType(SuitType.NECROMANCER, "Necromancer Leggings", "&9Necromancer Suit Leggings", "CookieGadgets.suits.necromancer.leggings", 20, Rarity.RARE, null, SuitMaterial.NECROMANCER_LEGGINGS);
        NECROMANCER_BOOTS = new SuitEquipmentType(SuitType.NECROMANCER, "Necromancer Boots", "&aNecromancer Suit Boots", "CookieGadgets.suits.necromancer.boots", 10, Rarity.COMMON, null, SuitMaterial.NECROMANCER_BOOTS);
        THOR_HELMET = new SuitEquipmentType(SuitType.THOR, "Thor Helmet", "&6Thor Suit Helmet", "CookieGadgets.suits.thor.helmet", 70, Rarity.LEGENDARY, null, new GMaterial("head:2a9f83329a2e475a75335b3949aa4d054f9de413bfb28aa60de2e5259ecaad1"));
        THOR_CHESTPLATE = new SuitEquipmentType(SuitType.THOR, "Thor Chestplate", "&5Thor Suit Chestplate", "CookieGadgets.suits.thor.chestplate", 25, Rarity.EPIC, null, SuitMaterial.THOR_CHESTPLATE);
        THOR_LEGGINGS = new SuitEquipmentType(SuitType.THOR, "Thor Leggings", "&9Thor Suit Leggings", "CookieGadgets.suits.thor.leggings", 13, Rarity.RARE, null, SuitMaterial.THOR_LEGGINGS);
        THOR_BOOTS = new SuitEquipmentType(SuitType.THOR, "Thor Boots", "&aThor Suit Boots", "CookieGadgets.suits.thor.boots", 5, Rarity.COMMON, null, SuitMaterial.THOR_BOOTS);
        BAKER_HELMET = new SuitEquipmentType(SuitType.BAKER, "Baker Helmet", "&6Baker Suit Helmet", "CookieGadgets.suits.baker.helmet", 70, Rarity.LEGENDARY, null, new GMaterial("head:b8dea622fb0fbbd2639384c1604f5f1a3c111aa993447319fbac494bff4477"));
        BAKER_CHESTPLATE = new SuitEquipmentType(SuitType.BAKER, "Baker Chestplate", "&5Baker Suit Chestplate", "CookieGadgets.suits.baker.chestplate", 25, Rarity.EPIC, null, SuitMaterial.BAKER_CHESTPLATE);
        BAKER_LEGGINGS = new SuitEquipmentType(SuitType.BAKER, "Baker Leggings", "&9Baker Suit Leggings", "CookieGadgets.suits.baker.leggings", 13, Rarity.RARE, null, SuitMaterial.BAKER_LEGGINGS);
        BAKER_BOOTS = new SuitEquipmentType(SuitType.BAKER, "Baker Boots", "&aBaker Suit Boots", "CookieGadgets.suits.baker.boots", 5, Rarity.COMMON, null, SuitMaterial.BAKER_BOOTS);
        BUMBLEBEE_HELMET = new SuitEquipmentType(SuitType.BUMBLEBEE, "Bumblebee Helmet", "&6Bumblebee Suit Helmet", "CookieGadgets.suits.bumblebee.helmet", 70, Rarity.LEGENDARY, null, new GMaterial("head:bf92d3f385cc16c77675a46de3e833ac17c74ada3e1946ef7021ecdbf9f1ba"));
        BUMBLEBEE_CHESTPLATE = new SuitEquipmentType(SuitType.BUMBLEBEE, "Bumblebee Chestplate", "&5Bumblebee Suit Chestplate", "CookieGadgets.suits.bumblebee.chestplate", 25, Rarity.EPIC, null, SuitMaterial.BUMBLEBEE_CHESTPLATE);
        BUMBLEBEE_LEGGINGS = new SuitEquipmentType(SuitType.BUMBLEBEE, "Bumblebee Leggings", "&9Bumblebee Suit Leggings", "CookieGadgets.suits.bumblebee.leggings", 13, Rarity.RARE, null, SuitMaterial.BUMBLEBEE_LEGGINGS);
        BUMBLEBEE_BOOTS = new SuitEquipmentType(SuitType.BUMBLEBEE, "Bumblebee Boots", "&aBumblebee Suit Boots", "CookieGadgets.suits.bumblebee.boots", 5, Rarity.COMMON, null, SuitMaterial.BUMBLEBEE_BOOTS);
        FIREFIGHTER_HELMET = new SuitEquipmentType(SuitType.FIREFIGHTER, "Firefighter Helmet", "&6Firefighter Suit Helmet", "CookieGadgets.suits.firefighter.helmet", 80, Rarity.LEGENDARY, null, new GMaterial("head:7e9b8d13ae2b8e24b8a06fc982b2628a8131d1b16d6deb9cf31fb633f8ca2e"));
        FIREFIGHTER_CHESTPLATE = new SuitEquipmentType(SuitType.FIREFIGHTER, "Firefighter Chestplate", "&5Firefighter Suit Chestplate", "CookieGadgets.suits.firefighter.chestplate", 34, Rarity.EPIC, null, SuitMaterial.FIREFIGHTER_CHESTPLATE);
        FIREFIGHTER_LEGGINGS = new SuitEquipmentType(SuitType.FIREFIGHTER, "Firefighter Leggings", "&9Firefighter Suit Leggings", "CookieGadgets.suits.firefighter.leggings", 19, Rarity.RARE, null, SuitMaterial.FIREFIGHTER_LEGGINGS);
        FIREFIGHTER_BOOTS = new SuitEquipmentType(SuitType.FIREFIGHTER, "Firefighter Boots", "&aFirefighter Suit Boots", "CookieGadgets.suits.firefighter.boots", 9, Rarity.COMMON, null, SuitMaterial.FIREFIGHTER_BOOTS);
        PLUMBER_HELMET = new SuitEquipmentType(SuitType.PLUMBER, "Plumber Helmet", "&6Plumber Suit Helmet", "CookieGadgets.suits.plumber.helmet", 73, Rarity.LEGENDARY, null, new GMaterial("head:6f7eb75e5542cc4937aaad5bb8657393eaf0265006eac1dc96691f32e16437"));
        PLUMBER_CHESTPLATE = new SuitEquipmentType(SuitType.PLUMBER, "Plumber Chestplate", "&5Plumber Suit Chestplate", "CookieGadgets.suits.plumber.chestplate", 32, Rarity.EPIC, null, SuitMaterial.PLUMBER_CHESTPLATE);
        PLUMBER_LEGGINGS = new SuitEquipmentType(SuitType.PLUMBER, "Plumber Leggings", "&9Plumber Suit Leggings", "CookieGadgets.suits.plumber.leggings", 18, Rarity.RARE, null, SuitMaterial.PLUMBER_LEGGINGS);
        PLUMBER_BOOTS = new SuitEquipmentType(SuitType.PLUMBER, "Plumber Boots", "&aPlumber Suit Boots", "CookieGadgets.suits.plumber.boots", 7, Rarity.COMMON, null, SuitMaterial.PLUMBER_BOOTS);
        ICE_WALKER_HELMET = new SuitEquipmentType(SuitType.ICE_WALKER, "Ice Walker Helmet", "&6Ice Walker Suit Helmet", "CookieGadgets.suits.icewalker.helmet", 70, Rarity.LEGENDARY, null, new GMaterial("head:371c7c94da4f86a8fe6ea3a5b2f7dad24731ac420ef47ca4c1c766dea60accc"));
        ICE_WALKER_CHESTPLATE = new SuitEquipmentType(SuitType.ICE_WALKER, "Ice Walker Chestplate", "&5Ice Walker Suit Chestplate", "CookieGadgets.suits.icewalker.chestplate", 25, Rarity.EPIC, null, SuitMaterial.ICE_WALKER_CHESTPLATE);
        ICE_WALKER_LEGGINGS = new SuitEquipmentType(SuitType.ICE_WALKER, "Ice Walker Leggings", "&9Ice Walker Suit Leggings", "CookieGadgets.suits.icewalker.leggings", 13, Rarity.RARE, null, SuitMaterial.ICE_WALKER_LEGGINGS);
        ICE_WALKER_BOOTS = new SuitEquipmentType(SuitType.ICE_WALKER, "Ice Walker Boots", "&aIce Walker Suit Boots", "CookieGadgets.suits.icewalker.boots", 5, Rarity.COMMON, null, SuitMaterial.ICE_WALKER_BOOTS);
        VAMPIRE_HELMET = new SuitEquipmentType(SuitType.VAMPIRE, "Vampire Helmet", "&6Vampire Suit Helmet", "CookieGadgets.suits.vampire.helmet", 74, Rarity.LEGENDARY, null, new GMaterial("head:8d44756e0b4ece8d746296a3d5e297e1415f4ba17647ffe228385383d161a9"));
        VAMPIRE_CHESTPLATE = new SuitEquipmentType(SuitType.VAMPIRE, "Vampire Chestplate", "&5Vampire Suit Chestplate", "CookieGadgets.suits.vampire.chestplate", 34, Rarity.EPIC, null, SuitMaterial.VAMPIRE_CHESTPLATE);
        VAMPIRE_LEGGINGS = new SuitEquipmentType(SuitType.VAMPIRE, "Vampire Leggings", "&9Vampire Suit Leggings", "CookieGadgets.suits.vampire.leggings", 18, Rarity.RARE, null, SuitMaterial.VAMPIRE_LEGGINGS);
        VAMPIRE_BOOTS = new SuitEquipmentType(SuitType.VAMPIRE, "Vampire Boots", "&aVampire Suit Boots", "CookieGadgets.suits.vampire.boots", 7, Rarity.COMMON, null, SuitMaterial.VAMPIRE_BOOTS);
    }
    
    private SuitEquipmentType(final SuitType key, final String s, final String displayName, final String s2, final int mysteryDust, final Rarity rarity, final List<String> list, final SuitMaterial suitMaterial) {
        super(Category.SUITS, s, displayName, s2, mysteryDust, rarity, list, list);
        this.category = key;
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getSuitsFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getSuitsFile().getInt(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getSuitsFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getSuitsFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getSuitsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                }
                else {
                    FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getSuitsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getSuitsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.headMaterial = null;
        this.suitMaterial = suitMaterial;
        this.itemStack = ItemUtils.item(this.displayName, this.suitMaterial.getMaterial(), null);
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Cosmetics", "true");
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Category", Category.SUITS.getNBTTag());
        if (!SuitEquipmentType.VALUES.contains(this)) {
            SuitEquipmentType.VALUES.add(this);
        }
        if (!SuitEquipmentType.SUIT_CATEGORIES.containsKey(key)) {
            SuitEquipmentType.SUIT_CATEGORIES.put(key, new ArrayList<SuitEquipmentType>());
        }
    }
    
    private SuitEquipmentType(final SuitType key, final String s, final String displayName, final String s2, final int mysteryDust, final Rarity rarity, final List<String> list, final GMaterial headMaterial) {
        super(Category.SUITS, s, displayName, s2, mysteryDust, rarity, list, list);
        this.category = key;
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getSuitsFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getSuitsFile().getInt(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getSuitsFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getSuitsFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getSuitsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                }
                else {
                    FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getSuitsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getSuitsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getSuitsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getSuitsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.headMaterial = headMaterial;
        this.suitMaterial = SuitMaterial.PLAYER_SKULL;
        this.itemStack = ItemUtils.item(this.displayName, this.headMaterial);
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Cosmetics", "true");
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Category", Category.SUITS.getNBTTag());
        if (!SuitEquipmentType.VALUES.contains(this)) {
            SuitEquipmentType.VALUES.add(this);
        }
        if (!SuitEquipmentType.SUIT_CATEGORIES.containsKey(key)) {
            SuitEquipmentType.SUIT_CATEGORIES.put(key, new ArrayList<SuitEquipmentType>());
        }
    }
    
    public SuitType getSuitCategory() {
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
    
    public GMaterial getHeadMaterial() {
        return this.headMaterial;
    }
    
    public SuitMaterial getSuitMaterial() {
        return this.suitMaterial;
    }
    
    @Override
    public boolean isEnabled() {
        return this.category.isEnabled();
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
        return "Suits." + this.category.getName() + ".Equipments." + super.getName();
    }
    
    @Override
    public void equip(final PlayerManager playerManager) {
        if (!this.isEnabled() || !Category.SUITS.isEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        if (this.getSuitMaterial().getArmorType() == EnumArmorType.HELMET) {
            if (player.getInventory().getHelmet() != null) {
                CategoryManager.removeHelmetCosmetic(playerManager);
                if (player.getInventory().getHelmet() != null) {
                    if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ARMOR_TO_EQUIP_SUIT.getFormatMessage())) {
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
            playerManager.getEquippedSuitEquipment().put(EnumArmorType.HELMET, this);
            player.getInventory().setHelmet(this.getItemStack());
            this.getSuitCategory().equip(player);
        }
        else if (this.getSuitMaterial().getArmorType() == EnumArmorType.CHESTPLATE) {
            if (player.getInventory().getChestplate() != null) {
                CategoryManager.removeChestplateCosmetic(playerManager);
                if (player.getInventory().getChestplate() != null) {
                    if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ARMOR_TO_EQUIP_SUIT.getFormatMessage())) {
                        return;
                    }
                    if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                        final Player player3;
                        Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                            player3.getWorld().dropItemNaturally(player3.getLocation(), player3.getInventory().getChestplate().clone());
                            player3.getInventory().setChestplate((ItemStack)null);
                            player3.updateInventory();
                            return;
                        });
                    }
                }
            }
            if (playerManager.getEquippedMorph() != null) {
                playerManager.unequipMorph();
            }
            playerManager.getEquippedSuitEquipment().put(EnumArmorType.CHESTPLATE, this);
            player.getInventory().setChestplate(this.getItemStack());
            this.getSuitCategory().equip(player);
        }
        else if (this.getSuitMaterial().getArmorType() == EnumArmorType.LEGGINGS) {
            if (player.getInventory().getLeggings() != null) {
                CategoryManager.removeLeggingsCosmetic(playerManager);
                if (player.getInventory().getLeggings() != null) {
                    if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ARMOR_TO_EQUIP_SUIT.getFormatMessage())) {
                        return;
                    }
                    if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                        final Player player4;
                        Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                            player4.getWorld().dropItemNaturally(player4.getLocation(), player4.getInventory().getLeggings().clone());
                            player4.getInventory().setLeggings((ItemStack)null);
                            player4.updateInventory();
                            return;
                        });
                    }
                }
            }
            if (playerManager.getEquippedMorph() != null) {
                playerManager.unequipMorph();
            }
            playerManager.getEquippedSuitEquipment().put(EnumArmorType.LEGGINGS, this);
            player.getInventory().setLeggings(this.getItemStack());
            this.getSuitCategory().equip(player);
        }
        else if (this.getSuitMaterial().getArmorType() == EnumArmorType.BOOTS) {
            if (player.getInventory().getBoots() != null) {
                CategoryManager.removeBootsCosmetic(playerManager);
                if (player.getInventory().getBoots() != null) {
                    if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ARMOR_TO_EQUIP_SUIT.getFormatMessage())) {
                        return;
                    }
                    if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                        final Player player5;
                        Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                            player5.getWorld().dropItemNaturally(player5.getLocation(), player5.getInventory().getBoots().clone());
                            player5.getInventory().setBoots((ItemStack)null);
                            player5.updateInventory();
                            return;
                        });
                    }
                }
            }
            if (playerManager.getEquippedMorph() != null) {
                playerManager.unequipMorph();
            }
            playerManager.getEquippedSuitEquipment().put(EnumArmorType.BOOTS, this);
            player.getInventory().setBoots(this.getItemStack());
            this.getSuitCategory().equip(player);
        }
    }
    
    @Override
    public void unequip(final PlayerManager playerManager) {
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        if (this.getSuitMaterial().getArmorType() == EnumArmorType.HELMET) {
            if (player.getInventory().getHelmet() != null || playerManager.getEquippedSuitEquipment().get(EnumArmorType.HELMET) != null) {
                if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta() != null && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getHelmet(), "Category", Category.SUITS.getNBTTag())) {
                    player.getInventory().setHelmet((ItemStack)null);
                    player.updateInventory();
                }
                if (playerManager.getCurrentSuit() != null) {
                    playerManager.getCurrentSuit().onClear();
                    playerManager.removeSuit();
                }
                if (!playerManager.getEquippedSuitEquipment().isEmpty()) {
                    playerManager.getEquippedSuitEquipment().remove(EnumArmorType.HELMET);
                }
            }
        }
        else if (this.getSuitMaterial().getArmorType() == EnumArmorType.CHESTPLATE) {
            if (player.getInventory().getChestplate() != null || playerManager.getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE) != null) {
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta() != null && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getChestplate(), "Category", Category.SUITS.getNBTTag())) {
                    player.getInventory().setChestplate((ItemStack)null);
                    player.updateInventory();
                }
                if (playerManager.getCurrentSuit() != null) {
                    playerManager.getCurrentSuit().onClear();
                    playerManager.removeSuit();
                }
                if (!playerManager.getEquippedSuitEquipment().isEmpty()) {
                    playerManager.getEquippedSuitEquipment().remove(EnumArmorType.CHESTPLATE);
                }
            }
        }
        else if (this.getSuitMaterial().getArmorType() == EnumArmorType.LEGGINGS) {
            if (player.getInventory().getLeggings() != null || playerManager.getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS) != null) {
                if (player.getInventory().getLeggings() != null && player.getInventory().getLeggings().getItemMeta() != null && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getLeggings(), "Category", Category.SUITS.getNBTTag())) {
                    player.getInventory().setLeggings((ItemStack)null);
                    player.updateInventory();
                }
                if (playerManager.getCurrentSuit() != null) {
                    playerManager.getCurrentSuit().onClear();
                    playerManager.removeSuit();
                }
                if (!playerManager.getEquippedSuitEquipment().isEmpty()) {
                    playerManager.getEquippedSuitEquipment().remove(EnumArmorType.LEGGINGS);
                }
            }
        }
        else if (this.getSuitMaterial().getArmorType() == EnumArmorType.BOOTS && (player.getInventory().getBoots() != null || playerManager.getEquippedSuitEquipment().get(EnumArmorType.BOOTS) != null)) {
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta() != null && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getBoots(), "Category", Category.SUITS.getNBTTag())) {
                player.getInventory().setBoots((ItemStack)null);
                player.updateInventory();
            }
            if (playerManager.getCurrentSuit() != null) {
                playerManager.getCurrentSuit().onClear();
                playerManager.removeSuit();
            }
            if (!playerManager.getEquippedSuitEquipment().isEmpty()) {
                playerManager.getEquippedSuitEquipment().remove(EnumArmorType.BOOTS);
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
        if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.SUITS)) {
            player.sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return false;
        }
        if (this.getSuitMaterial().getArmorType() == EnumArmorType.HELMET && player.getInventory().getHelmet() != null) {
            CategoryManager.removeHelmetCosmetic(playerManager);
            if (player.getInventory().getHelmet() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ARMOR_TO_EQUIP_SUIT.getFormatMessage())) {
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
        if (this.getSuitMaterial().getArmorType() == EnumArmorType.CHESTPLATE && player.getInventory().getChestplate() != null) {
            CategoryManager.removeChestplateCosmetic(playerManager);
            if (player.getInventory().getChestplate() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ARMOR_TO_EQUIP_SUIT.getFormatMessage())) {
                    return false;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    final Player player3;
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        player3.getWorld().dropItemNaturally(player3.getLocation(), player3.getInventory().getChestplate().clone());
                        player3.getInventory().setChestplate((ItemStack)null);
                        player3.updateInventory();
                        return;
                    });
                }
            }
        }
        if (this.getSuitMaterial().getArmorType() == EnumArmorType.LEGGINGS && player.getInventory().getLeggings() != null) {
            CategoryManager.removeLeggingsCosmetic(playerManager);
            if (player.getInventory().getLeggings() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ARMOR_TO_EQUIP_SUIT.getFormatMessage())) {
                    return false;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    final Player player4;
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        player4.getWorld().dropItemNaturally(player4.getLocation(), player4.getInventory().getLeggings().clone());
                        player4.getInventory().setLeggings((ItemStack)null);
                        player4.updateInventory();
                        return;
                    });
                }
            }
        }
        if (this.getSuitMaterial().getArmorType() == EnumArmorType.BOOTS && player.getInventory().getBoots() != null) {
            CategoryManager.removeBootsCosmetic(playerManager);
            if (player.getInventory().getBoots() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ARMOR_TO_EQUIP_SUIT.getFormatMessage())) {
                    return false;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    final Player player5;
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        player5.getWorld().dropItemNaturally(player5.getLocation(), player5.getInventory().getBoots().clone());
                        player5.getInventory().setBoots((ItemStack)null);
                        player5.updateInventory();
                        return;
                    });
                }
            }
        }
        if (playerManager.getEquippedMorph() != null) {
            playerManager.unequipMorph();
        }
        return true;
    }
    
    public static List<SuitEquipmentType> values() {
        return SuitEquipmentType.VALUES;
    }
    
    public static Set<SuitType> suitCategories() {
        return SuitEquipmentType.SUIT_CATEGORIES.keySet();
    }
    
    public static void checkEnabled() {
        for (final SuitEquipmentType suitEquipmentType : values()) {
            if (SuitEquipmentType.SUIT_CATEGORIES.containsKey(suitEquipmentType.getSuitCategory()) && !SuitEquipmentType.SUIT_CATEGORIES.get(suitEquipmentType.getSuitCategory()).contains(suitEquipmentType)) {
                SuitEquipmentType.SUIT_CATEGORIES.get(suitEquipmentType.getSuitCategory()).add(suitEquipmentType);
            }
        }
    }
    
    public static List<SuitEquipmentType> getSuitCategory(final SuitType suitType) {
        if (SuitEquipmentType.SUIT_CATEGORIES.containsKey(suitType)) {
            return SuitEquipmentType.SUIT_CATEGORIES.get(suitType);
        }
        return null;
    }
    
    public static SuitEquipmentType valueOf(final String anotherString) {
        for (final SuitEquipmentType suitEquipmentType : values()) {
            if (suitEquipmentType.getName().equalsIgnoreCase(anotherString)) {
                return suitEquipmentType;
            }
        }
        return null;
    }
    
    public static SuitEquipmentType valueOfDisplayName(final String anotherString) {
        for (final SuitEquipmentType suitEquipmentType : values()) {
            if (suitEquipmentType.getDisplayName().equalsIgnoreCase(anotherString)) {
                return suitEquipmentType;
            }
        }
        return null;
    }
}
