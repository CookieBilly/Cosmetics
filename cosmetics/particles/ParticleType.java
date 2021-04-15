

package ws.billy.CookieGadgets.cosmetics.particles;

import java.util.Collections;
import java.util.function.Function;
import java.util.Comparator;
import ws.billy.CookieGadgets.utils.EnumInventorySort;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.VersionManager;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticMaterialType;

public class ParticleType extends CosmeticMaterialType
{
    private static final List<ParticleType> ENABLED;
    private static final List<ParticleType> VALUES;
    public static final ParticleType WATER_SPLASH;
    public static final ParticleType DRIP_WATER;
    public static final ParticleType DRIP_LAVA;
    public static final ParticleType CRIT;
    public static final ParticleType CRIT_MAGIC;
    public static final ParticleType SPELL;
    public static final ParticleType SPELL_INSTANT;
    public static final ParticleType SPELL_MOB;
    public static final ParticleType SPELL_WITCH;
    public static final ParticleType VILLAGER_ANGRY;
    public static final ParticleType VILLAGER_HAPPY;
    public static final ParticleType TOWN_AURA;
    public static final ParticleType NOTE;
    public static final ParticleType PORTAL;
    public static final ParticleType ENCHANTMENT_TABLE;
    public static final ParticleType FLAME;
    public static final ParticleType REDSTONE;
    public static final ParticleType HEART;
    public static final ParticleType FIREWORKS_SPARK;
    public static final ParticleType SMOKE_NORMAL;
    public static final ParticleType SLIME;
    private String displayName;
    private GMaterial material;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private ParticleEffect particleEffect;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    
    static {
        ENABLED = new ArrayList<ParticleType>();
        VALUES = new ArrayList<ParticleType>();
        WATER_SPLASH = new ParticleType("Water Splash", "&aWater Splash Particle", new GMaterial(EnumMaterial.NETHER_STAR), "CookieGadgets.particles.watersplash", 15, Rarity.COMMON, Arrays.asList("&7Display a custom Water", "&7Splash particles around", "&7you in lobby."), ParticleEffect.WATER_SPLASH);
        DRIP_WATER = new ParticleType("Drip Water", "&9Drip Water Particle", new GMaterial(EnumMaterial.WATER_BUCKET), "CookieGadgets.particles.dripwater", 25, Rarity.RARE, Arrays.asList("&7Display a custom Drip", "&7Water particles around", "&7you in lobby."), ParticleEffect.DRIP_WATER);
        DRIP_LAVA = new ParticleType("Drip Lava", "&9Drip Lava Particle", new GMaterial(EnumMaterial.LAVA_BUCKET), "CookieGadgets.particles.driplava", 25, Rarity.RARE, Arrays.asList("&7Display a custom Drip", "&7Lava particles around", "&7you in lobby."), ParticleEffect.DRIP_LAVA);
        CRIT = new ParticleType("Crit", "&9Crit Particle", new GMaterial(EnumMaterial.STONE_SWORD), "CookieGadgets.particles.crit", 22, Rarity.RARE, Arrays.asList("&7Display a custom Crit", "&7particles around you", "&7in lobby."), ParticleEffect.CRIT);
        CRIT_MAGIC = new ParticleType("Magic Crit", "&9Magic Crit Particle", new GMaterial(VersionManager.is1_8Version() ? EnumMaterial.POTION : EnumMaterial.SPLASH_POTION, 8204), "CookieGadgets.particles.magiccrit", 20, Rarity.RARE, Arrays.asList("&7Display a custom Magic", "&7Crit particles around", "&7you in lobby."), ParticleEffect.CRIT_MAGIC);
        SPELL = new ParticleType("Spell", "&5Spell Particle", new GMaterial(EnumMaterial.ENDER_CHEST), "CookieGadgets.particles.spell", 30, Rarity.EPIC, Arrays.asList("&7Display a custom Spell", "&7particles around you", "&7in lobby."), ParticleEffect.SPELL);
        SPELL_INSTANT = new ParticleType("Instant Spell", "&5Instant Spell Particle", new GMaterial(EnumMaterial.GHAST_TEAR), "CookieGadgets.particles.instantspell", 32, Rarity.EPIC, Arrays.asList("&7Display a custom Instant", "&7Spell particles around", "&7you in lobby."), ParticleEffect.SPELL_INSTANT);
        SPELL_MOB = new ParticleType("Mob Spell", "&5Mob Spell Particle", new GMaterial(EnumMaterial.EXPERIENCE_BOTTLE), "CookieGadgets.particles.mobspell", 35, Rarity.EPIC, Arrays.asList("&7Display a custom Mob", "&7Spell particles around", "&7you in lobby."), ParticleEffect.SPELL_MOB);
        SPELL_WITCH = new ParticleType("Witch Spell", "&6Witch Spell Particle", new GMaterial(EnumMaterial.POTION, 8260), "CookieGadgets.particles.witchspell", 40, Rarity.LEGENDARY, Arrays.asList("&7Display a custom Witch", "&7Spell particles around", "&7you in lobby."), ParticleEffect.SPELL_WITCH);
        VILLAGER_ANGRY = new ParticleType("Angry Villager", "&5Angry Villager Particle", new GMaterial(EnumMaterial.FIRE_CHARGE), "CookieGadgets.particles.angryvillager", 32, Rarity.EPIC, Arrays.asList("&7Display a custom Angry", "&7Villager particles around", "&7you in lobby."), ParticleEffect.VILLAGER_ANGRY);
        VILLAGER_HAPPY = new ParticleType("Happy Villager", "&5Happy Villager Particle", new GMaterial(EnumMaterial.EMERALD), "CookieGadgets.particles.happyvillager", 35, Rarity.EPIC, Arrays.asList("&7Display a custom Happy", "&7Villager particles around", "&7you in lobby."), ParticleEffect.VILLAGER_HAPPY);
        TOWN_AURA = new ParticleType("Town Aura", "&aTown Aura Particle", new GMaterial(EnumMaterial.MYCELIUM), "CookieGadgets.particles.townaura", 18, Rarity.COMMON, Arrays.asList("&7Display a custom Town", "&7Aura particles around", "&7you in lobby."), ParticleEffect.TOWN_AURA);
        NOTE = new ParticleType("Note", "&5Note Particle", new GMaterial(EnumMaterial.NOTE_BLOCK), "CookieGadgets.particles.note", 35, Rarity.EPIC, Arrays.asList("&7Display a custom Note", "&7particles around you", "&7in lobby."), ParticleEffect.NOTE);
        PORTAL = new ParticleType("Portal", "&aPortal Particle", new GMaterial(EnumMaterial.ENDER_PEARL), "CookieGadgets.particles.portal", 18, Rarity.COMMON, Arrays.asList("&7Display a custom Portal", "&7particles around you", "&7in lobby."), ParticleEffect.PORTAL);
        ENCHANTMENT_TABLE = new ParticleType("Enchantment Table", "&9Enchantment Table Particle", new GMaterial(EnumMaterial.ENCHANTING_TABLE), "CookieGadgets.particles.enchantment", 28, Rarity.RARE, Arrays.asList("&7Display a custom Enchantment", "&7Table particles around", "&7you in lobby."), ParticleEffect.ENCHANTMENT_TABLE);
        FLAME = new ParticleType("Flame", "&6Flame Particle", new GMaterial(EnumMaterial.GOLD_NUGGET), "CookieGadgets.particles.flame", 45, Rarity.LEGENDARY, Arrays.asList("&7Display a custom Flame", "&7particles around you", "&7in lobby."), ParticleEffect.FLAME);
        REDSTONE = new ParticleType("Redstone", "&6Redstone Particle", new GMaterial(EnumMaterial.REDSTONE), "CookieGadgets.particles.redstone", 48, Rarity.LEGENDARY, Arrays.asList("&7Display a custom Redstone", "&7particles around you", "&7in lobby."), ParticleEffect.REDSTONE);
        HEART = new ParticleType("Heart", "&9Heart Particle", new GMaterial(EnumMaterial.POPPY), "CookieGadgets.particles.heart", 28, Rarity.RARE, Arrays.asList("&7Display a custom Heart", "&7particles around you", "&7in lobby."), ParticleEffect.HEART);
        FIREWORKS_SPARK = new ParticleType("Fireworks Spark", "&6Fireworks Spark Particle", new GMaterial(EnumMaterial.FIREWORK_ROCKET), "CookieGadgets.particles.fireworkspark", 52, Rarity.LEGENDARY, Arrays.asList("&7Display a custom Fireworks", "&7Spark particles around", "&7you in lobby."), ParticleEffect.FIREWORKS_SPARK);
        SMOKE_NORMAL = new ParticleType("Smoke", "&aSmoke Particle", new GMaterial(EnumMaterial.FIREWORK_STAR), "CookieGadgets.particles.smoke", 18, Rarity.COMMON, Arrays.asList("&7Display a custom Smoke", "&7particles around you", "&7in lobby."), ParticleEffect.SMOKE_NORMAL);
        SLIME = new ParticleType("Slime", "&aSlime Particle", new GMaterial(EnumMaterial.SLIME_BALL), "CookieGadgets.particles.slime", 22, Rarity.COMMON, Arrays.asList("&7Display a custom slime", "&7particles around you", "&7in lobby."), ParticleEffect.SLIME);
    }
    
    private ParticleType(final String s, final String displayName, final GMaterial material, final String s2, final int mysteryDust, final Rarity rarity, final List<String> list, final ParticleEffect particleEffect) {
        super(Category.PARTICLES, s, displayName, material, s2, mysteryDust, rarity, list, list);
        if (FileManager.getParticlesFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getParticlesFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getParticlesFile().get(String.valueOf(this.getFilePath()) + ".Material") == null) {
            this.material = material;
            FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Material", this.material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getParticlesFile().getString(String.valueOf(this.getFilePath()) + ".Material"));
        }
        if (FileManager.getParticlesFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getParticlesFile().getInt(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getParticlesFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getParticlesFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getParticlesFile().get(String.valueOf(this.getFilePath()) + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getParticlesFile().getBoolean(String.valueOf(this.getFilePath()) + ".Enabled");
        }
        if (FileManager.getParticlesFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getParticlesFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getParticlesFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getParticlesFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (FileManager.getParticlesFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getParticlesFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                }
                else {
                    FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getParticlesFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getParticlesFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getParticlesFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getParticlesFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.particleEffect = particleEffect;
        if (!ParticleType.VALUES.contains(this)) {
            ParticleType.VALUES.add(this);
        }
    }
    
    public ParticleType(final String s, final String displayName, final GMaterial material, final String s2, final int mysteryDust, final Rarity rarity, final List<String> lockedLore, final List<String> unlockedLore, final ParticleEffect particleEffect, final boolean isEnable, final boolean canBeFound, final boolean purchasable) {
        super(Category.PARTICLES, s, displayName, material, s2, mysteryDust, rarity, lockedLore, unlockedLore);
        this.displayName = displayName;
        this.material = material;
        this.mysteryDust = mysteryDust;
        this.rarity = rarity;
        this.lockedLore = lockedLore;
        this.unlockedLore = unlockedLore;
        this.particleEffect = particleEffect;
        this.isEnable = isEnable;
        this.canBeFound = canBeFound;
        this.purchasable = purchasable;
        if (!ParticleType.VALUES.contains(this)) {
            ParticleType.VALUES.add(this);
        }
        if (isEnable && !ParticleType.ENABLED.contains(this)) {
            ParticleType.ENABLED.add(this);
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
    
    public ParticleEffect getParticleEffect() {
        return this.particleEffect;
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
        return "Particles." + super.getName();
    }
    
    @Override
    public void equip(final PlayerManager playerManager) {
        if (!this.isEnabled() || !Category.PARTICLES.isEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        new BukkitRunnable() {
            int step;
            boolean showToEveryone = CookieGadgets.getCookieGadgetsData().showParticleEffectToEveryone();
            boolean hideParticleEffectForVanishedPlayer = CookieGadgets.getCookieGadgetsData().hideParticleEffectForVanishedPlayer();
            boolean isVolatileParticle = ParticleType.isVolatileParticleEffect(ParticleType.this.getParticleEffect());
            private final /* synthetic */ Player val$player = playerManager.getPlayer();
            
            public void run() {
                if (playerManager.getEquippedParticle() == null || playerManager.getEquippedParticle() != ParticleType.this) {
                    this.cancel();
                    return;
                }
                if (this.step >= Integer.MAX_VALUE) {
                    this.step = 0;
                }
                final double n = this.step * 0.07853981633974483;
                final Vector vector = new Vector();
                vector.setX(Math.cos(n) * 0.43);
                vector.setZ(Math.sin(n) * 0.43);
                if (this.isVolatileParticle) {
                    if (this.showToEveryone) {
                        ParticleType.this.getParticleEffect().display(this.val$player.getLocation().add(vector).add(0.0, 2.0, 0.0), this.hideParticleEffectForVanishedPlayer, this.val$player, 0.0f, 1);
                    }
                    else {
                        ParticleType.this.getParticleEffect().display(this.val$player.getLocation().add(vector).add(0.0, 2.0, 0.0), this.val$player, 0.0f, 1);
                    }
                }
                else if (this.showToEveryone) {
                    if (ParticleType.this.getParticleEffect() == ParticleEffect.SLIME) {
                        ParticleType.this.getParticleEffect().display(this.val$player.getLocation().add(vector).add(0.0, 2.0, 0.0), this.hideParticleEffectForVanishedPlayer, this.val$player, 5.0f, 5);
                    }
                    else {
                        ParticleType.this.getParticleEffect().display(this.val$player.getLocation().add(vector).add(0.0, 2.0, 0.0), this.hideParticleEffectForVanishedPlayer, this.val$player, 5.0f, 1);
                    }
                }
                else if (ParticleType.this.getParticleEffect() == ParticleEffect.SLIME) {
                    ParticleType.this.getParticleEffect().display(this.val$player.getLocation().add(vector).add(0.0, 2.0, 0.0), this.val$player, 5.0f, 5);
                }
                else {
                    ParticleType.this.getParticleEffect().display(this.val$player.getLocation().add(vector).add(0.0, 2.0, 0.0), this.val$player, 5.0f, 1);
                }
                this.step += 4;
            }
        }.runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), 0L, 2L);
        playerManager.setEquippedParticle(this);
    }
    
    @Override
    public void unequip(final PlayerManager playerManager) {
        if (playerManager == null) {
            return;
        }
        if (playerManager.getEquippedParticle() != null) {
            playerManager.setEquippedParticle(null);
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
        if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.PARTICLES)) {
            player.sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return false;
        }
        return true;
    }
    
    public static List<ParticleType> enabled() {
        return ParticleType.ENABLED;
    }
    
    public static List<ParticleType> values() {
        return ParticleType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final ParticleType particleType : values()) {
            if (particleType.isEnabled() && !ParticleType.ENABLED.contains(particleType)) {
                ParticleType.ENABLED.add(particleType);
            }
        }
    }
    
    public static void sortItems() {
        if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.NAME) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super ParticleType, ? extends Comparable>)ParticleType::getDisplayNameStripColor));
        }
        else if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.RARITY) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super Object, ? extends Comparable>)ParticleType::getRarity).thenComparing((Function<? super Object, ? extends Comparable>)ParticleType::getDisplayNameStripColor));
        }
    }
    
    public static ParticleType valueOf(final String anotherString) {
        for (final ParticleType particleType : values()) {
            if (particleType.getName().equalsIgnoreCase(anotherString)) {
                return particleType;
            }
        }
        return null;
    }
    
    public static boolean isVolatileParticleEffect(final ParticleEffect particleEffect) {
        return particleEffect == ParticleEffect.BLOCK_DUST || particleEffect == ParticleEffect.CAMPFIRE_COSY_SMOKE || particleEffect == ParticleEffect.CAMPFIRE_SIGNAL_SMOKE || particleEffect == ParticleEffect.CLOUD || particleEffect == ParticleEffect.CRIT || particleEffect == ParticleEffect.CRIMSON_SPORE || particleEffect == ParticleEffect.CRIT_MAGIC || particleEffect == ParticleEffect.DAMAGE_INDICATOR || particleEffect == ParticleEffect.DRAGON_BREATH || particleEffect == ParticleEffect.ENCHANTMENT_TABLE || particleEffect == ParticleEffect.END_ROD || particleEffect == ParticleEffect.FIREWORKS_SPARK || particleEffect == ParticleEffect.FLAME || particleEffect == ParticleEffect.FLASH || particleEffect == ParticleEffect.ITEM_CRACK || particleEffect == ParticleEffect.PORTAL || particleEffect == ParticleEffect.SMOKE_NORMAL || particleEffect == ParticleEffect.SMOKE_LARGE || particleEffect == ParticleEffect.SNEEZE || particleEffect == ParticleEffect.SNOW_SHOVEL || particleEffect == ParticleEffect.SOUL || particleEffect == ParticleEffect.SOUL_FIRE_FLAME || particleEffect == ParticleEffect.SPIT || particleEffect == ParticleEffect.SWEEP_ATTACK || particleEffect == ParticleEffect.TOTEM || particleEffect == ParticleEffect.WARPED_SPORE || particleEffect == ParticleEffect.WATER_WAKE || particleEffect == ParticleEffect.WHITE_ASH;
    }
}
