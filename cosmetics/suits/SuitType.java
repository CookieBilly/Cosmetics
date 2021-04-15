

package ws.billy.CookieGadgets.cosmetics.suits;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import org.bukkit.entity.Player;
import java.util.Iterator;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitVampire;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitIceWalker;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitPlumber;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitFirefighter;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitBumblebee;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitBaker;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitThor;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitNecromancer;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitWarrior;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitSpooderman;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitMermaid;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitDisco;
import org.bukkit.Color;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitGhostly;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitSpeedster;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitNinja;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitFrog;
import java.util.Arrays;
import java.util.ArrayList;
import ws.billy.CookieGadgets.cosmetics.suits.types.Suit;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;

public class SuitType
{
    private static final List<SuitType> ENABLED;
    private static final List<SuitType> VALUES;
    public static final SuitType FROG;
    public static final SuitType NINJA;
    public static final SuitType SPEEDSTER;
    public static final SuitType GHOSTLY;
    public static final SuitType DISCO;
    public static final SuitType MERMAID;
    public static final SuitType SPOODERMAN;
    public static final SuitType WARRIOR;
    public static final SuitType NECROMANCER;
    public static final SuitType THOR;
    public static final SuitType BAKER;
    public static final SuitType BUMBLEBEE;
    public static final SuitType FIREFIGHTER;
    public static final SuitType PLUMBER;
    public static final SuitType ICE_WALKER;
    public static final SuitType VAMPIRE;
    private String name;
    private String displayName;
    private GMaterial material;
    private String GUIName;
    private int cooldown;
    private List<String> permissions;
    private List<String> lore;
    private long repeatDelay;
    private Class<? extends Suit> clazz;
    private boolean isEnable;
    private boolean isAbilityEnable;
    
    static {
        ENABLED = new ArrayList<SuitType>();
        VALUES = new ArrayList<SuitType>();
        FROG = new SuitType("Frog", "&6Frog Suit", new GMaterial("head:cc3c601e4ba3064bec98a51552473aa7a5646e60fddc18d187c09b4a351d2"), false, 0, Arrays.asList("CookieGadgets.suits.frog.helmet", "CookieGadgets.suits.frog.chestplate", "CookieGadgets.suits.frog.leggings", "CookieGadgets.suits.frog.boots"), Arrays.asList("", "&7One of the rarest suits around.", "&7Browse dank memes from far above", "&7when you equip this Frog Suit.", "", "&7Full Set Ability: Super high", "&7jump."), 1L, SuitFrog.class);
        NINJA = new SuitType("Ninja", "&6Ninja Suit", new GMaterial("head:e96616bb48ac61a153a9f5c35979f3523c24c366c621d6bacaed10f822b68b"), true, 0, Arrays.asList("CookieGadgets.suits.ninja.helmet", "CookieGadgets.suits.ninja.chestplate", "CookieGadgets.suits.ninja.leggings", "CookieGadgets.suits.ninja.boots"), Arrays.asList("", "&7Silent but deadly, skulk around", "&7your favourite lobby with this", "&7Ninja Suit.", "", "&7Full Set Ability: Click to throw", "&7a Ninja Shuriken!"), 1L, SuitNinja.class);
        SPEEDSTER = new SuitType("Speedster", "&6Speedster Suit", new GMaterial("head:5138ccdee2eb7cf60d5489d388f210629b2e787bb530e65bd1a73126d01067"), false, 0, Arrays.asList("CookieGadgets.suits.speedster.helmet", "CookieGadgets.suits.speedster.chestplate", "CookieGadgets.suits.speedster.leggings", "CookieGadgets.suits.speedster.boots"), Arrays.asList("", "&7Nothing move faster than the", "&7speed of light... except you", "&7when you're wearing this", "&7Speedster Suit!", "", "&7Full Set Ability: Sprint to run", "&7extremely fast."), 1L, SuitSpeedster.class);
        GHOSTLY = new SuitType("Ghostly", "&6Ghostly Skeleton Suit", new GMaterial(EnumMaterial.SKELETON_SKULL), false, 0, Arrays.asList("CookieGadgets.suits.ghostly.helmet", "CookieGadgets.suits.ghostly.chestplate", "CookieGadgets.suits.ghostly.leggings", "CookieGadgets.suits.ghostly.boots"), Arrays.asList("", "&7Are you feeling spooky? Then", "&7equip this spooky scary Ghostly", "&7Skeleton Suit!", "", "&7Full Set Ability: Makes the", "&7wearer invisible under the suit!"), 1L, SuitGhostly.class);
        DISCO = new SuitType("Disco", "&6Disco Suit", new GMaterial(EnumMaterial.LEATHER_HELMET, Color.RED), false, 0, Arrays.asList("CookieGadgets.suits.disco.helmet", "CookieGadgets.suits.disco.chestplate", "CookieGadgets.suits.disco.leggings", "CookieGadgets.suits.disco.boots"), Arrays.asList("", "&7It was your Dad's favourite", "&7dance movement - bring back", "&7boogle with this Disco Suit!", "", "&7Full Set Ability: Changes colors", "&7when equipped!"), 10L, SuitDisco.class);
        MERMAID = new SuitType("Mermaid", "&6Mermaid Suit", new GMaterial("head:aa36d682f9152a98e53cbf3d583b59df8f024e184531c97c2ea25816d1288f"), false, 0, Arrays.asList("CookieGadgets.suits.mermaid.helmet", "CookieGadgets.suits.mermaid.chestplate", "CookieGadgets.suits.mermaid.leggings", "CookieGadgets.suits.mermaid.boots"), Arrays.asList("", "&7Everything's better down where", "&7it's wetter! Head under the sea", "&7with this Mermaid Suit!", "", "&7Full Set Ability: Transform into", "&7a beautiful Squid when under", "&7water."), 10L, SuitMermaid.class);
        SPOODERMAN = new SuitType("Spooderman", "&6Spooderman Suit", new GMaterial("head:9f2f7ebd9e263c939f54f939c9e2bcb18d4431f5577c389d6cbcf1371b11d"), true, 0, Arrays.asList("CookieGadgets.suits.spooderman.helmet", "CookieGadgets.suits.spooderman.chestplate", "CookieGadgets.suits.spooderman.leggings", "CookieGadgets.suits.spooderman.boots"), Arrays.asList("", "&7With great power come great", "&7responsibility. Save the day with", "&7this Spooderman Suit.", "", "&7Full Set Ability: Click to swing", "&7around lobbies!"), 1L, SuitSpooderman.class);
        WARRIOR = new SuitType("Warrior", "&6Warrior Suit", new GMaterial("head:38f56b7f7ac16046a9816e57c2c3296420ccddb7cc51b1c0de8075a816b747d9"), true, 0, Arrays.asList("CookieGadgets.suits.warrior.helmet", "CookieGadgets.suits.warrior.chestplate", "CookieGadgets.suits.warrior.leggings", "CookieGadgets.suits.warrior.boots"), Arrays.asList("", "&7The Warrior's way is one of", "&7honour, resilience, and", "&7butt-kicking special abilities!", "", "&7Full Set Ability: Left click and", "&7look down to Ground Slam. or", "&7look ahead to Seismic Wave."), 1L, SuitWarrior.class);
        NECROMANCER = new SuitType("Necromancer", "&6Necromancer Suit", new GMaterial("head:9ddbd0bb9622ce124dabb6ba3baa9a9ea71430f870fec15b3ea81f1961a412b2"), true, 0, Arrays.asList("CookieGadgets.suits.necromancer.helmet", "CookieGadgets.suits.necromancer.chestplate", "CookieGadgets.suits.necromancer.leggings", "CookieGadgets.suits.necromancer.boots"), Arrays.asList("", "&7Put the RAVE in graveyard and", "&7bring back the dead with this", "&7Necromancer Suit!", "", "&7Full Set Ability: Click to raise", "&7the dead."), 1L, SuitNecromancer.class);
        THOR = new SuitType("Thor", "&6Thor Suit", new GMaterial("head:2a9f83329a2e475a75335b3949aa4d054f9de413bfb28aa60de2e5259ecaad1"), true, 0, Arrays.asList("CookieGadgets.suits.thor.helmet", "CookieGadgets.suits.thor.chestplate", "CookieGadgets.suits.thor.leggings", "CookieGadgets.suits.thor.boots"), Arrays.asList("", "&7\"I like this suit. ANOTHER!\"", "&7Wield the power of the God of", "&7Thunder with this Thor Suit.", "", "&7Full Set Ability: Click to", "&7strike lightning."), 1L, SuitThor.class);
        BAKER = new SuitType("Baker", "&6Baker Suit", new GMaterial("head:b8dea622fb0fbbd2639384c1604f5f1a3c111aa993447319fbac494bff4477"), true, 0, Arrays.asList("CookieGadgets.suits.baker.helmet", "CookieGadgets.suits.baker.chestplate", "CookieGadgets.suits.baker.leggings", "CookieGadgets.suits.baker.boots"), Arrays.asList("", "&7What's better than a cookie? A", "&7man who can bake a million", "&7cookies!", "", "&7Full Set Ability: Click to", "&7deliver baked goods around the", "&7lobby."), 1L, SuitBaker.class);
        BUMBLEBEE = new SuitType("Bumblebee", "&6Bumblebee Suit", new GMaterial("head:bf92d3f385cc16c77675a46de3e833ac17c74ada3e1946ef7021ecdbf9f1ba"), true, 0, Arrays.asList("CookieGadgets.suits.bumblebee.helmet", "CookieGadgets.suits.bumblebee.chestplate", "CookieGadgets.suits.bumblebee.leggings", "CookieGadgets.suits.bumblebee.boots"), Arrays.asList("", "&7We aren't sure what all the buzz", "&7is about this suit.", "", "&7Full Set Ability: This Suit will", "&7play a classical song when you", "&7fly or left click!"), 1L, SuitBumblebee.class);
        FIREFIGHTER = new SuitType("Firefighter", "&6Firefighter Suit", new GMaterial("head:7e9b8d13ae2b8e24b8a06fc982b2628a8131d1b16d6deb9cf31fb633f8ca2e"), true, 0, Arrays.asList("CookieGadgets.suits.firefighter.helmet", "CookieGadgets.suits.firefighter.chestplate", "CookieGadgets.suits.firefighter.leggings", "CookieGadgets.suits.firefighter.boots"), Arrays.asList("", "&7Become an everyday hero when you", "&7equip this Firefighter Suit!", "", "&7Full Set Ability: Click to spray", "&7water."), 1L, SuitFirefighter.class);
        PLUMBER = new SuitType("Plumber", "&6Plumber Suit", new GMaterial("head:6f7eb75e5542cc4937aaad5bb8657393eaf0265006eac1dc96691f32e16437"), true, 0, Arrays.asList("CookieGadgets.suits.plumber.helmet", "CookieGadgets.suits.plumber.chestplate", "CookieGadgets.suits.plumber.leggings", "CookieGadgets.suits.plumber.boots"), Arrays.asList("", "&7We're sorry, your Princess is in", "&7another lobby!", "", "&7Full Set Ability: Click to throw", "&7fireballs!"), 1L, SuitPlumber.class);
        ICE_WALKER = new SuitType("Ice Walker", "&6Ice Walker Suit", new GMaterial("head:371c7c94da4f86a8fe6ea3a5b2f7dad24731ac420ef47ca4c1c766dea60accc"), false, 0, Arrays.asList("CookieGadgets.suits.icewalker.helmet", "CookieGadgets.suits.icewalker.chestplate", "CookieGadgets.suits.icewalker.leggings", "CookieGadgets.suits.icewalker.boots"), Arrays.asList("", "&7No, she doesn't want to build a", "&7snow man... and don't tell her", "&7to \"Let it go!\".", "", "&7Full Set Ability: Absorb heat", "&7and chill the world around you", "&7by walking."), 5L, SuitIceWalker.class);
        VAMPIRE = new SuitType("Vampire", "&6Vampire Suit", new GMaterial("head:8d44756e0b4ece8d746296a3d5e297e1415f4ba17647ffe228385383d161a9"), false, 0, Arrays.asList("CookieGadgets.suits.vampire.helmet", "CookieGadgets.suits.vampire.chestplate", "CookieGadgets.suits.vampire.leggings", "CookieGadgets.suits.vampire.boots"), Arrays.asList("", "&7Like a bat out of hell, become a", "&7blood-craving villain with this", "&7Vampire Suit!", "", "&7Full Set Ability: Transform into", "&7a bat when you fly."), 1L, SuitVampire.class);
    }
    
    private SuitType(final String name, final String displayName, final GMaterial material, final boolean b, final int cooldown, final List<String> permissions, final List<String> lore, final long repeatDelay, final Class<? extends Suit> clazz) {
        this.name = name;
        if (FileManager.getSuitsFile().get("Suits." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getSuitsFile().set("Suits." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getSuitsFile().getString("Suits." + this.name + ".Name");
        }
        if (FileManager.getSuitsFile().get("Suits." + this.name + ".Material") == null) {
            this.material = material;
            FileManager.getSuitsFile().set("Suits." + this.name + ".Material", this.material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getSuitsFile().getString("Suits." + this.name + ".Material"));
        }
        if (FileManager.getSuitsFile().get("Suits." + this.name + ".GUI-Name") == null) {
            this.GUIName = ChatUtil.stripColor(this.displayName);
            FileManager.getSuitsFile().set("Suits." + this.name + ".GUI-Name", this.GUIName);
        }
        else {
            this.GUIName = FileManager.getSuitsFile().getString("Suits." + this.name + ".GUI-Name");
        }
        this.permissions = permissions;
        if (FileManager.getSuitsFile().get("Suits." + this.name + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getSuitsFile().set("Suits." + this.name + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getSuitsFile().getBoolean("Suits." + this.name + ".Enabled");
        }
        if (b) {
            if (FileManager.getSuitsFile().get("Suits." + this.name + ".Cooldown") == null) {
                this.cooldown = cooldown;
                FileManager.getSuitsFile().set("Suits." + this.name + ".Cooldown", this.cooldown);
            }
            else {
                this.cooldown = FileManager.getSuitsFile().getInt("Suits." + this.name + ".Cooldown");
            }
        }
        else {
            this.cooldown = 0;
        }
        if (FileManager.getSuitsFile().get("Suits." + this.name + ".Enabled-Ability") == null) {
            this.isAbilityEnable = true;
            FileManager.getSuitsFile().set("Suits." + this.name + ".Enabled-Ability", true);
        }
        else {
            this.isAbilityEnable = FileManager.getSuitsFile().getBoolean("Suits." + this.name + ".Enabled-Ability");
        }
        if (FileManager.getSuitsFile().get("Suits." + this.name + ".Lore") == null) {
            if ((this.lore = lore) == null) {
                FileManager.getSuitsFile().set("Suits." + this.name + ".Lore", "");
            }
            else {
                FileManager.getSuitsFile().set("Suits." + this.name + ".Lore", this.lore);
            }
        }
        else {
            this.lore = FileManager.getSuitsFile().getStringList("Suits." + this.name + ".Lore");
        }
        this.repeatDelay = repeatDelay;
        this.clazz = clazz;
        if ((name == "Mermaid" || name == "Vampire") && !CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() && !CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) {
            this.isEnable = false;
        }
        if (!SuitType.VALUES.contains(this)) {
            SuitType.VALUES.add(this);
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
    
    public int getCooldown() {
        return this.cooldown;
    }
    
    public List<String> getPermissions() {
        return this.permissions;
    }
    
    public List<String> getLore() {
        return this.lore;
    }
    
    public long getRepeatDelay() {
        return this.repeatDelay;
    }
    
    public Class<? extends Suit> getClazz() {
        return this.clazz;
    }
    
    public boolean isEnabled() {
        return this.isEnable;
    }
    
    public boolean isAbilityEnabled() {
        return this.isAbilityEnable;
    }
    
    public static List<SuitType> enabled() {
        return SuitType.ENABLED;
    }
    
    public static List<SuitType> values() {
        return SuitType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final SuitType suitType : values()) {
            if (suitType.isEnabled() && !SuitType.ENABLED.contains(suitType)) {
                SuitType.ENABLED.add(suitType);
            }
        }
    }
    
    public Suit equip(final Player player) {
        Suit suit = null;
        try {
            suit = (Suit)this.clazz.getDeclaredConstructor(UUID.class).newInstance((player == null) ? null : player.getUniqueId());
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
        return suit;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static SuitType valueOf(final String anotherString) {
        for (final SuitType suitType : values()) {
            if (suitType.getName().equalsIgnoreCase(anotherString)) {
                return suitType;
            }
        }
        return null;
    }
}
