// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.cosmetics.cloaks;

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
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakFlameOfTheTitans;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakYinAndYang;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakArchangel;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakScanner;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakFirerings;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakShaman;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakIcewings;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakFrosty;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakVampireWings;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakFirewings;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakMystical;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.CloakSuperhero;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.ArrayList;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.Cloak;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticMaterialType;

public class CloakType extends CosmeticMaterialType
{
    private static final List<CloakType> ENABLED;
    private static final List<CloakType> VALUES;
    public static final CloakType SUPERHERO;
    public static final CloakType MYSTICAL;
    public static final CloakType FIREWINGS;
    public static final CloakType VAMPIRE_WINGS;
    public static final CloakType FROSTY;
    public static final CloakType ICEWINGS;
    public static final CloakType SHAMAN;
    public static final CloakType FIRERINGS;
    public static final CloakType SCANNER;
    public static final CloakType ARCHANGEL;
    public static final CloakType YIN_AND_YANG;
    public static final CloakType FLAME_OF_THE_TITANS;
    private String displayName;
    private GMaterial material;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private ParticleEffect effect;
    private long repeatDelay;
    private Class<? extends Cloak> clazz;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    
    static {
        ENABLED = new ArrayList<CloakType>();
        VALUES = new ArrayList<CloakType>();
        SUPERHERO = new CloakType("Superhero", "&6Superhero Cloak", new GMaterial(EnumMaterial.RED_DYE), "CookieGadgets.cloaks.superhero", 65, Rarity.LEGENDARY, Arrays.asList("&7Straight from the comic", "&7books comes that signature", "&7red cloak!"), ParticleEffect.REDSTONE, 5L, CloakSuperhero.class);
        MYSTICAL = new CloakType("Mystical", "&6Mystical Cloak", new GMaterial(EnumMaterial.BOOK), "CookieGadgets.cloaks.mystical", 85, Rarity.LEGENDARY, Arrays.asList("&7Shrouded in mystery, this", "&7cloak is the center of", "&7numerous legends concerning", "&7its powers."), ParticleEffect.SPELL_WITCH, 2L, CloakMystical.class);
        FIREWINGS = new CloakType("Firewings", "&6Firewings Cloak", new GMaterial(EnumMaterial.BLAZE_POWDER), "CookieGadgets.cloaks.firewings", 72, Rarity.LEGENDARY, Arrays.asList("&7Rise from the ashes like a", "&7phoenix in this display of", "&7flames!"), ParticleEffect.FLAME, 10L, CloakFirewings.class);
        VAMPIRE_WINGS = new CloakType("Vampire Wings", "&6Vampire Wings Cloak", new GMaterial(EnumMaterial.REDSTONE), "CookieGadgets.cloaks.vampirewings", 78, Rarity.LEGENDARY, Arrays.asList("&7Giant black and red wings", "&7extend from your back to", "&7form menacing vampire", "&7wings!"), ParticleEffect.SMOKE_NORMAL, 7L, CloakVampireWings.class);
        FROSTY = new CloakType("Frosty", "&6Frosty Cloak", new GMaterial(EnumMaterial.SNOWBALL), "CookieGadgets.cloaks.frosty", 78, Rarity.LEGENDARY, Arrays.asList("&7The cold never bothered me", "&7anyway!"), ParticleEffect.CRIT, 2L, CloakFrosty.class);
        ICEWINGS = new CloakType("Icewings", "&6Icewings Cloak", new GMaterial(EnumMaterial.ICE), "CookieGadgets.cloaks.icewings", 72, Rarity.LEGENDARY, Arrays.asList("&7Is it just me or is it cold", "&7here?"), ParticleEffect.REDSTONE, 7L, CloakIcewings.class);
        SHAMAN = new CloakType("Shaman", "&6Shaman Cloak", new GMaterial(EnumMaterial.BONE), "CookieGadgets.cloaks.shaman", 85, Rarity.LEGENDARY, Arrays.asList("&7Be surrounded by a tornado,", "&7similar to Shaman's Tornado"), ParticleEffect.FIREWORKS_SPARK, 2L, CloakShaman.class);
        FIRERINGS = new CloakType("Firerings", "&6Firerings Cloak", new GMaterial(EnumMaterial.BLAZE_ROD), "CookieGadgets.cloaks.firerings", 50, Rarity.LEGENDARY, Arrays.asList("&7Be surrounded by flames, ", "&7maybe the way you walk is", "&7burnt to ashes."), ParticleEffect.FLAME, 1L, CloakFirerings.class);
        SCANNER = new CloakType("Scanner", "&6Scanner Cloak", new GMaterial(EnumMaterial.BEACON), "CookieGadgets.cloaks.scanner", 65, Rarity.LEGENDARY, Arrays.asList("&7Bleep Bloop!"), ParticleEffect.CRIT_MAGIC, 1L, CloakScanner.class);
        ARCHANGEL = new CloakType("Archangel", "&6Archangel Cloak", new GMaterial("head:36f0485453d3a90f354321e8f382bef106bf506424f70c04a207f82bdb2856b9"), "CookieGadgets.cloaks.archangel", 70, Rarity.LEGENDARY, Arrays.asList("&7Sometimes angels are just", "&7ordinary people that help", "&7us believe in miracles", "&7again."), ParticleEffect.REDSTONE, 7L, CloakArchangel.class);
        YIN_AND_YANG = new CloakType("Yin and Yang", "&6Yin and Yang Cloak", new GMaterial("head:7fc0e041e45e1d96934bb9159e7b9e1ed74ff99a9b9342214302fdba57fd2"), "CookieGadgets.cloaks.yinandyang", 62, Rarity.LEGENDARY, Arrays.asList("&7Achieve the balance of the", "&7universe and watch it spin", "&7beneath your feet."), ParticleEffect.FIREWORKS_SPARK, 1L, CloakYinAndYang.class);
        FLAME_OF_THE_TITANS = new CloakType("Flame of the Titans", "&6Flame of the Titans Cloak", new GMaterial(EnumMaterial.MAGMA_CREAM), "CookieGadgets.cloaks.flameofthetitans", 65, Rarity.LEGENDARY, Arrays.asList("&7These flames are said to be", "&7the spirit of a Titan."), ParticleEffect.FLAME, 1L, CloakFlameOfTheTitans.class);
    }
    
    private CloakType(final String s, final String displayName, final GMaterial material, final String s2, final int mysteryDust, final Rarity rarity, final List<String> list, final ParticleEffect effect, final long repeatDelay, final Class<? extends Cloak> clazz) {
        super(Category.CLOAKS, s, displayName, material, s2, mysteryDust, rarity, list, list);
        if (FileManager.getCloaksFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getCloaksFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getCloaksFile().get(String.valueOf(this.getFilePath()) + ".Material") == null) {
            this.material = material;
            FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Material", this.material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getCloaksFile().getString(String.valueOf(this.getFilePath()) + ".Material"));
        }
        if (FileManager.getCloaksFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getCloaksFile().getInt(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getCloaksFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getCloaksFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getCloaksFile().get(String.valueOf(this.getFilePath()) + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getCloaksFile().getBoolean(String.valueOf(this.getFilePath()) + ".Enabled");
        }
        if (FileManager.getCloaksFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getCloaksFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getCloaksFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getCloaksFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (FileManager.getCloaksFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getCloaksFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                }
                else {
                    FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getCloaksFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getCloaksFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getCloaksFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getCloaksFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.effect = effect;
        this.repeatDelay = repeatDelay;
        this.clazz = clazz;
        if (!CloakType.VALUES.contains(this)) {
            CloakType.VALUES.add(this);
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
    
    public ParticleEffect getEffect() {
        return this.effect;
    }
    
    public long getRepeatDelay() {
        return this.repeatDelay;
    }
    
    public Class<? extends Cloak> getClazz() {
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
        return "Cloaks." + super.getName();
    }
    
    @Override
    public void equip(final PlayerManager playerManager) {
        if (!this.isEnabled() || !Category.CLOAKS.isEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        playerManager.setEquippedCloak(this);
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
        if (playerManager.getEquippedCloak() != null) {
            playerManager.setEquippedCloak(null);
        }
        if (playerManager.getCurrentCloak() != null) {
            playerManager.removeCloak();
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
        if (CookieGadgets.getCookieGadgetsData().isAntiLagEnabled() && CookieGadgets.getCookieGadgetsData().disableUsageIfLowTPS() && AntiLagChecker.isLowTPS()) {
            playerManager.getPlayer().sendMessage(MessageType.DISABLE_USAGE_OF_COSMETICS.getFormatMessage());
            return false;
        }
        if (WorldGuardUtils.isInBlacklistedRegion(playerManager.getPlayer(), BlacklistedRegion.CLOAKS)) {
            playerManager.getPlayer().sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return false;
        }
        return true;
    }
    
    public static List<CloakType> enabled() {
        return CloakType.ENABLED;
    }
    
    public static List<CloakType> values() {
        return CloakType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final CloakType cloakType : values()) {
            if (cloakType.isEnabled() && !CloakType.ENABLED.contains(cloakType)) {
                CloakType.ENABLED.add(cloakType);
            }
        }
    }
    
    public static void sortItems() {
        if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.NAME) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super CloakType, ? extends Comparable>)CloakType::getDisplayNameStripColor));
        }
        else if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.RARITY) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super Object, ? extends Comparable>)CloakType::getRarity).thenComparing((Function<? super Object, ? extends Comparable>)CloakType::getDisplayNameStripColor));
        }
    }
    
    public static CloakType valueOf(final String anotherString) {
        for (final CloakType cloakType : values()) {
            if (cloakType.getName().equalsIgnoreCase(anotherString)) {
                return cloakType;
            }
        }
        return null;
    }
}
