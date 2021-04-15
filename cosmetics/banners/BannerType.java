

package ws.billy.CookieGadgets.cosmetics.banners;

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
import ws.billy.CookieGadgets.utils.cosmetics.banners.GPatternType;
import ws.billy.CookieGadgets.utils.cosmetics.banners.GPattern;
import ws.billy.CookieGadgets.utils.GDyeColor;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.cosmetics.banners.GPatterns;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticType;

public class BannerType extends CosmeticType
{
    private static final List<BannerType> ENABLED;
    private static final List<BannerType> VALUES;
    public static final BannerType SNOW_BUNNY;
    public static final BannerType REINDEER;
    public static final BannerType HOLIDAY_TREE;
    public static final BannerType SANTA;
    public static final BannerType HOLIDAY_WREATH;
    public static final BannerType HEART;
    public static final BannerType GUITAR;
    public static final BannerType DINO;
    public static final BannerType REDPOOL;
    public static final BannerType PENGU;
    public static final BannerType PUG;
    public static final BannerType TRYHARD;
    public static final BannerType PUMPKIN;
    public static final BannerType CROWN;
    public static final BannerType FIRE_CREEPER;
    public static final BannerType PORTAL;
    public static final BannerType RAINBOW_WALL;
    public static final BannerType SKULLKING;
    public static final BannerType DEVOURER;
    private String displayName;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private GPatterns gPatterns;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    private ItemStack itemStack;
    
    static {
        ENABLED = new ArrayList<BannerType>();
        VALUES = new ArrayList<BannerType>();
        SNOW_BUNNY = new BannerType("Snow Bunny", "&5Snow Bunny Banner", "CookieGadgets.banners.snowbunny", 15, Rarity.EPIC, null, new GPatterns(GDyeColor.WHITE, Arrays.asList(new GPattern(GDyeColor.BLACK, GPatternType.CIRCLE_MIDDLE), new GPattern(GDyeColor.WHITE, GPatternType.BORDER), new GPattern(GDyeColor.WHITE, GPatternType.FLOWER), new GPattern(GDyeColor.LIGHT_BLUE, GPatternType.TRIANGLE_TOP), new GPattern(GDyeColor.WHITE, GPatternType.CROSS), new GPattern(GDyeColor.LIGHT_BLUE, GPatternType.CURLY_BORDER), new GPattern(GDyeColor.WHITE, GPatternType.TRIANGLES_BOTTOM))));
        REINDEER = new BannerType("Reindeer", "&5Reindeer Banner", "CookieGadgets.banners.reindeer", 20, Rarity.EPIC, null, new GPatterns(GDyeColor.BROWN, Arrays.asList(new GPattern(GDyeColor.RED, GPatternType.TRIANGLES_BOTTOM), new GPattern(GDyeColor.BLACK, GPatternType.CREEPER), new GPattern(GDyeColor.WHITE, GPatternType.SKULL), new GPattern(GDyeColor.BROWN, GPatternType.RHOMBUS_MIDDLE), new GPattern(GDyeColor.BROWN, GPatternType.STRIPE_SMALL), new GPattern(GDyeColor.LIGHT_GRAY, GPatternType.STRIPE_MIDDLE), new GPattern(GDyeColor.LIGHT_GRAY, GPatternType.HALF_HORIZONTAL), new GPattern(GDyeColor.BLACK, GPatternType.CURLY_BORDER), new GPattern(GDyeColor.BLACK, GPatternType.TRIANGLE_TOP), new GPattern(GDyeColor.BLACK, GPatternType.CIRCLE_MIDDLE), new GPattern(GDyeColor.BLACK, GPatternType.BORDER), new GPattern(GDyeColor.BLACK, GPatternType.CURLY_BORDER))));
        HOLIDAY_TREE = new BannerType("Holiday Tree", "&5Holiday Tree Banner", "CookieGadgets.banners.holidaytree", 20, Rarity.EPIC, null, new GPatterns(GDyeColor.WHITE, Arrays.asList(new GPattern(GDyeColor.GREEN, GPatternType.BRICKS), new GPattern(GDyeColor.LIGHT_BLUE, GPatternType.TRIANGLE_BOTTOM), new GPattern(GDyeColor.BROWN, GPatternType.STRAIGHT_CROSS), new GPattern(GDyeColor.LIGHT_BLUE, GPatternType.STRIPE_MIDDLE), new GPattern(GDyeColor.WHITE, GPatternType.TRIANGLE_TOP), new GPattern(GDyeColor.WHITE, GPatternType.RHOMBUS_MIDDLE), new GPattern(GDyeColor.GREEN, GPatternType.FLOWER), new GPattern(GDyeColor.GREEN, GPatternType.CIRCLE_MIDDLE), new GPattern(GDyeColor.WHITE, GPatternType.MOJANG), new GPattern(GDyeColor.LIGHT_BLUE, GPatternType.CURLY_BORDER))));
        SANTA = new BannerType("Santa", "&5Santa Banner", "CookieGadgets.banners.santa", 25, Rarity.EPIC, null, new GPatterns(GDyeColor.WHITE, Arrays.asList(new GPattern(GDyeColor.RED, GPatternType.RHOMBUS_MIDDLE), new GPattern(GDyeColor.ORANGE, GPatternType.CREEPER), new GPattern(GDyeColor.WHITE, GPatternType.STRIPE_MIDDLE), new GPattern(GDyeColor.RED, GPatternType.HALF_HORIZONTAL), new GPattern(GDyeColor.LIGHT_BLUE, GPatternType.STRIPE_TOP), new GPattern(GDyeColor.LIGHT_BLUE, GPatternType.CURLY_BORDER))));
        HOLIDAY_WREATH = new BannerType("Holiday Wreath", "&5Holiday Wreath Banner", "CookieGadgets.banners.holidaywreath", 28, Rarity.EPIC, null, new GPatterns(GDyeColor.LIME, Arrays.asList(new GPattern(GDyeColor.YELLOW, GPatternType.CREEPER), new GPattern(GDyeColor.RED, GPatternType.SKULL), new GPattern(GDyeColor.GREEN, GPatternType.FLOWER), new GPattern(GDyeColor.GREEN, GPatternType.CIRCLE_MIDDLE), new GPattern(GDyeColor.LIME, GPatternType.CIRCLE_MIDDLE))));
        HEART = new BannerType("Heart", "&5Heart Banner", "CookieGadgets.banners.heart", 30, Rarity.EPIC, null, new GPatterns(GDyeColor.WHITE, Arrays.asList(new GPattern(GDyeColor.RED, GPatternType.RHOMBUS_MIDDLE), new GPattern(GDyeColor.RED, GPatternType.RHOMBUS_MIDDLE), new GPattern(GDyeColor.WHITE, GPatternType.TRIANGLE_TOP), new GPattern(GDyeColor.RED, GPatternType.GRADIENT_UP), new GPattern(GDyeColor.RED, GPatternType.GRADIENT))));
        GUITAR = new BannerType("Guitar", "&5Guitar Banner", "CookieGadgets.banners.guitar", 25, Rarity.EPIC, null, new GPatterns(GDyeColor.BLACK, Arrays.asList(new GPattern(GDyeColor.PURPLE, GPatternType.STRIPE_TOP), new GPattern(GDyeColor.PURPLE, GPatternType.CROSS), new GPattern(GDyeColor.BLACK, GPatternType.HALF_HORIZONTAL_MIRROR), new GPattern(GDyeColor.BLACK, GPatternType.STRAIGHT_CROSS), new GPattern(GDyeColor.PURPLE, GPatternType.BORDER), new GPattern(GDyeColor.PURPLE, GPatternType.CURLY_BORDER))));
        DINO = new BannerType("Dino", "&5Dino Banner", "CookieGadgets.banners.dino", 40, Rarity.EPIC, null, new GPatterns(GDyeColor.BROWN, Arrays.asList(new GPattern(GDyeColor.LIME, GPatternType.CURLY_BORDER), new GPattern(GDyeColor.BROWN, GPatternType.SQUARE_BOTTOM_RIGHT), new GPattern(GDyeColor.BROWN, GPatternType.STRIPE_RIGHT), new GPattern(GDyeColor.BLUE, GPatternType.FLOWER), new GPattern(GDyeColor.LIME, GPatternType.STRIPE_MIDDLE), new GPattern(GDyeColor.BROWN, GPatternType.TRIANGLE_TOP), new GPattern(GDyeColor.LIME, GPatternType.MOJANG))));
        REDPOOL = new BannerType("Redpool", "&5Redpool Banner", "CookieGadgets.banners.redpool", 42, Rarity.EPIC, null, new GPatterns(GDyeColor.WHITE, Arrays.asList(new GPattern(GDyeColor.BLACK, GPatternType.HALF_HORIZONTAL), new GPattern(GDyeColor.BLACK, GPatternType.HALF_HORIZONTAL_MIRROR), new GPattern(GDyeColor.WHITE, GPatternType.STRIPE_MIDDLE), new GPattern(GDyeColor.RED, GPatternType.STRIPE_CENTER), new GPattern(GDyeColor.BLACK, GPatternType.CURLY_BORDER), new GPattern(GDyeColor.RED, GPatternType.TRIANGLE_TOP), new GPattern(GDyeColor.RED, GPatternType.TRIANGLE_BOTTOM))));
        PENGU = new BannerType("Pengu", "&5Pengu Banner", "CookieGadgets.banners.pengu", 50, Rarity.EPIC, null, new GPatterns(GDyeColor.BLACK, Arrays.asList(new GPattern(GDyeColor.WHITE, GPatternType.FLOWER), new GPattern(GDyeColor.BLACK, GPatternType.HALF_HORIZONTAL_MIRROR), new GPattern(GDyeColor.BLACK, GPatternType.CIRCLE_MIDDLE), new GPattern(GDyeColor.WHITE, GPatternType.TRIANGLE_BOTTOM), new GPattern(GDyeColor.ORANGE, GPatternType.RHOMBUS_MIDDLE))));
        PUG = new BannerType("Pug", "&5Pug Banner", "CookieGadgets.banners.pug", 48, Rarity.EPIC, null, new GPatterns(GDyeColor.ORANGE, Arrays.asList(new GPattern(GDyeColor.WHITE, GPatternType.BRICKS), new GPattern(GDyeColor.BROWN, GPatternType.STRIPE_BOTTOM), new GPattern(GDyeColor.ORANGE, GPatternType.BORDER), new GPattern(GDyeColor.BROWN, GPatternType.TRIANGLE_BOTTOM), new GPattern(GDyeColor.PINK, GPatternType.RHOMBUS_MIDDLE), new GPattern(GDyeColor.BLACK, GPatternType.CREEPER), new GPattern(GDyeColor.BLACK, GPatternType.FLOWER), new GPattern(GDyeColor.BROWN, GPatternType.SKULL), new GPattern(GDyeColor.ORANGE, GPatternType.STRIPE_TOP), new GPattern(GDyeColor.ORANGE, GPatternType.TRIANGLE_TOP), new GPattern(GDyeColor.BROWN, GPatternType.CIRCLE_MIDDLE))));
        TRYHARD = new BannerType("Tryhard", "&5Tryhard Banner", "CookieGadgets.banners.tryhard", 48, Rarity.EPIC, null, new GPatterns(GDyeColor.ORANGE, Arrays.asList(new GPattern(GDyeColor.PINK, GPatternType.GRADIENT_UP), new GPattern(GDyeColor.WHITE, GPatternType.CURLY_BORDER), new GPattern(GDyeColor.WHITE, GPatternType.STRIPE_SMALL))));
        PUMPKIN = new BannerType("Pumpkin", "&5Pumpkin Banner", "CookieGadgets.banners.pumpkin", 50, Rarity.EPIC, null, new GPatterns(GDyeColor.BLACK, Arrays.asList(new GPattern(GDyeColor.ORANGE, GPatternType.FLOWER), new GPattern(GDyeColor.ORANGE, GPatternType.CIRCLE_MIDDLE), new GPattern(GDyeColor.ORANGE, GPatternType.TRIANGLES_BOTTOM), new GPattern(GDyeColor.BLACK, GPatternType.HALF_HORIZONTAL), new GPattern(GDyeColor.ORANGE, GPatternType.TRIANGLE_TOP), new GPattern(GDyeColor.ORANGE, GPatternType.STRIPE_MIDDLE))));
        CROWN = new BannerType("Crown", "&5Crown Banner", "CookieGadgets.banners.crown", 48, Rarity.EPIC, null, new GPatterns(GDyeColor.YELLOW, Arrays.asList(new GPattern(GDyeColor.RED, GPatternType.TRIANGLE_TOP), new GPattern(GDyeColor.YELLOW, GPatternType.RHOMBUS_MIDDLE), new GPattern(GDyeColor.RED, GPatternType.STRIPE_BOTTOM), new GPattern(GDyeColor.RED, GPatternType.BORDER))));
        FIRE_CREEPER = new BannerType("Fire Creeper", "&5Fire Creeper Banner", "CookieGadgets.banners.firecreeper", 48, Rarity.EPIC, null, new GPatterns(GDyeColor.ORANGE, Arrays.asList(new GPattern(GDyeColor.RED, GPatternType.GRADIENT_UP), new GPattern(GDyeColor.YELLOW, GPatternType.GRADIENT), new GPattern(GDyeColor.RED, GPatternType.CREEPER))));
        PORTAL = new BannerType("Portal", "&6Portal Banner", "CookieGadgets.banners.portal", 60, Rarity.LEGENDARY, null, new GPatterns(GDyeColor.MAGENTA, Arrays.asList(new GPattern(GDyeColor.PURPLE, GPatternType.BRICKS), new GPattern(GDyeColor.PURPLE, GPatternType.GRADIENT_UP), new GPattern(GDyeColor.PURPLE, GPatternType.GRADIENT), new GPattern(GDyeColor.PURPLE, GPatternType.SKULL), new GPattern(GDyeColor.BLACK, GPatternType.BORDER))));
        RAINBOW_WALL = new BannerType("Rainbow Wall", "&6Rainbow Wall Banner", "CookieGadgets.banners.rainbowwall", 62, Rarity.LEGENDARY, null, new GPatterns(GDyeColor.BLACK, Arrays.asList(new GPattern(GDyeColor.LIME, GPatternType.GRADIENT), new GPattern(GDyeColor.BLUE, GPatternType.GRADIENT_UP), new GPattern(GDyeColor.BLACK, GPatternType.BRICKS), new GPattern(GDyeColor.BLACK, GPatternType.BRICKS), new GPattern(GDyeColor.BLACK, GPatternType.BRICKS), new GPattern(GDyeColor.BLACK, GPatternType.CURLY_BORDER), new GPattern(GDyeColor.BLACK, GPatternType.CURLY_BORDER), new GPattern(GDyeColor.BLACK, GPatternType.CURLY_BORDER), new GPattern(GDyeColor.BLACK, GPatternType.SQUARE_BOTTOM_LEFT), new GPattern(GDyeColor.BLACK, GPatternType.SQUARE_TOP_RIGHT), new GPattern(GDyeColor.BLACK, GPatternType.SQUARE_BOTTOM_LEFT), new GPattern(GDyeColor.BLACK, GPatternType.SQUARE_TOP_RIGHT))));
        SKULLKING = new BannerType("Skull King", "&6Skull King Banner", "CookieGadgets.banners.skullking", 65, Rarity.LEGENDARY, null, new GPatterns(GDyeColor.BLACK, Arrays.asList(new GPattern(GDyeColor.WHITE, GPatternType.CURLY_BORDER), new GPattern(GDyeColor.WHITE, GPatternType.STRIPE_CENTER), new GPattern(GDyeColor.BLACK, GPatternType.STRIPE_BOTTOM), new GPattern(GDyeColor.WHITE, GPatternType.CREEPER), new GPattern(GDyeColor.YELLOW, GPatternType.STRIPE_TOP), new GPattern(GDyeColor.BLACK, GPatternType.TRIANGLES_TOP))));
        DEVOURER = new BannerType("Devourer", "&6Devourer Banner", "CookieGadgets.banners.devourer", 68, Rarity.LEGENDARY, null, new GPatterns(GDyeColor.BROWN, Arrays.asList(new GPattern(GDyeColor.BLACK, GPatternType.RHOMBUS_MIDDLE), new GPattern(GDyeColor.BROWN, GPatternType.STRIPE_LEFT), new GPattern(GDyeColor.BROWN, GPatternType.STRIPE_RIGHT), new GPattern(GDyeColor.RED, GPatternType.TRIANGLE_BOTTOM), new GPattern(GDyeColor.WHITE, GPatternType.TRIANGLES_TOP), new GPattern(GDyeColor.WHITE, GPatternType.TRIANGLES_BOTTOM), new GPattern(GDyeColor.YELLOW, GPatternType.BORDER))));
    }
    
    private BannerType(final String s, final String displayName, final String s2, final int mysteryDust, final Rarity rarity, final List<String> list, final GPatterns gPatterns) {
        super(Category.BANNERS, s, displayName, s2, mysteryDust, rarity, list, list);
        if (FileManager.getBannersFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getBannersFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getBannersFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getBannersFile().getInt(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getBannersFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getBannersFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getBannersFile().get(String.valueOf(this.getFilePath()) + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getBannersFile().getBoolean(String.valueOf(this.getFilePath()) + ".Enabled");
        }
        if (FileManager.getBannersFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getBannersFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getBannersFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getBannersFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (FileManager.getBannersFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getBannersFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                }
                else {
                    FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getBannersFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getBannersFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getBannersFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getBannersFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.gPatterns = gPatterns;
        this.itemStack = ItemUtils.itemBanner(this.displayName, this.gPatterns.getBaseColor(), this.gPatterns.getPatterns(), null);
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Cosmetics", "true");
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Category", Category.BANNERS.getNBTTag());
        if (!BannerType.VALUES.contains(this)) {
            BannerType.VALUES.add(this);
        }
    }
    
    public BannerType(final String s, final String displayName, final String s2, final int mysteryDust, final Rarity rarity, final List<String> lockedLore, final List<String> unlockedLore, final GPatterns gPatterns, final boolean isEnable, final boolean canBeFound, final boolean purchasable, final ItemStack itemStack) {
        super(Category.BANNERS, s, displayName, s2, mysteryDust, rarity, lockedLore, unlockedLore);
        this.displayName = displayName;
        this.mysteryDust = mysteryDust;
        this.rarity = rarity;
        this.lockedLore = lockedLore;
        this.unlockedLore = unlockedLore;
        this.gPatterns = gPatterns;
        this.isEnable = isEnable;
        this.canBeFound = canBeFound;
        this.purchasable = purchasable;
        this.itemStack = itemStack;
        if (!BannerType.VALUES.contains(this)) {
            BannerType.VALUES.add(this);
        }
        if (isEnable && !BannerType.ENABLED.contains(this)) {
            BannerType.ENABLED.add(this);
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
    
    public GPatterns getGPatterns() {
        return this.gPatterns;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
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
        return "Banners." + super.getName();
    }
    
    @Override
    public void equip(final PlayerManager playerManager) {
        if (!this.isEnabled() || !Category.BANNERS.isEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        if (player.getInventory().getHelmet() != null) {
            CategoryManager.removeHelmetCosmetic(playerManager);
            if (player.getInventory().getHelmet() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_HELMET_TO_EQUIP_BANNER.getFormatMessage())) {
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
        player.updateInventory();
        playerManager.setEquippedBanner(this);
    }
    
    @Override
    public void unequip(final PlayerManager playerManager) {
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        if (player.getInventory().getHelmet() != null || playerManager.getEquippedBanner() != null) {
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta() != null && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getHelmet(), "Category", Category.BANNERS.getNBTTag())) {
                player.getInventory().setHelmet((ItemStack)null);
                player.updateInventory();
            }
            if (playerManager.getEquippedBanner() != null) {
                playerManager.setEquippedBanner(null);
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
            playerManager.getPlayer().sendMessage(MessageType.DISABLE_USAGE_OF_COSMETICS.getFormatMessage());
            return false;
        }
        if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.BANNERS)) {
            playerManager.getPlayer().sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return false;
        }
        if (player.getInventory().getHelmet() != null) {
            CategoryManager.removeHelmetCosmetic(playerManager);
            if (player.getInventory().getHelmet() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_HELMET_TO_EQUIP_BANNER.getFormatMessage())) {
                    return false;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    player.getWorld().dropItemNaturally(player.getLocation(), player.getInventory().getHelmet().clone());
                    player.getInventory().setHelmet((ItemStack)null);
                    player.updateInventory();
                }
            }
        }
        return true;
    }
    
    public static List<BannerType> enabled() {
        return BannerType.ENABLED;
    }
    
    public static List<BannerType> values() {
        return BannerType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final BannerType bannerType : values()) {
            if (bannerType.isEnabled() && !BannerType.ENABLED.contains(bannerType)) {
                BannerType.ENABLED.add(bannerType);
            }
        }
    }
    
    public static void sortItems() {
        if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.NAME) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super BannerType, ? extends Comparable>)BannerType::getDisplayNameStripColor));
        }
        else if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.RARITY) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super Object, ? extends Comparable>)BannerType::getRarity).thenComparing((Function<? super Object, ? extends Comparable>)BannerType::getDisplayNameStripColor));
        }
    }
    
    public static BannerType valueOf(final String anotherString) {
        for (final BannerType bannerType : values()) {
            if (bannerType.getName().equalsIgnoreCase(anotherString)) {
                return bannerType;
            }
        }
        return null;
    }
}
