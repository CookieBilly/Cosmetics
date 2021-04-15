

package ws.billy.CookieGadgets.cosmetics.morphs;

import java.util.Collections;
import java.util.function.Function;
import java.util.Comparator;
import ws.billy.CookieGadgets.utils.EnumInventorySort;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import org.bukkit.entity.Player;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import org.bukkit.plugin.Plugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.EnumEquipType;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.CategoryManager;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphGuardian;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphSnowman;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphWitch;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphIronGolem;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphZombie;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphBlaze;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphCreeper;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphSkeleton;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphSheep;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphSpider;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphChicken;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphEnderman;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphCow;
import ws.billy.CookieGadgets.cosmetics.morphs.types.MorphPig;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.ArrayList;
import ws.billy.CookieGadgets.cosmetics.morphs.types.Morph;
import ws.billy.CookieGadgets.utils.cosmetics.morphs.GDisguiseType;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticMaterialType;

public class MorphType extends CosmeticMaterialType
{
    private static final List<MorphType> ENABLED;
    private static final List<MorphType> VALUES;
    public static final MorphType PIG;
    public static final MorphType COW;
    public static final MorphType ENDERMAN;
    public static final MorphType CHICKEN;
    public static final MorphType SPIDER;
    public static final MorphType SHEEP;
    public static final MorphType SKELETON;
    public static final MorphType CREEPER;
    public static final MorphType BLAZE;
    public static final MorphType ZOMBIE;
    public static final MorphType IRON_GOLEM;
    public static final MorphType WITCH;
    public static final MorphType SNOWMAN;
    public static final MorphType GUARDIAN;
    private String displayName;
    private GMaterial material;
    private int cooldown;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private GDisguiseType disguiseType;
    private Class<? extends Morph> clazz;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    private boolean isAbilityEnable;
    
    static {
        ENABLED = new ArrayList<MorphType>();
        VALUES = new ArrayList<MorphType>();
        PIG = new MorphType("Pig", "&9Pig Morph", new GMaterial(EnumMaterial.COOKED_PORKCHOP), "CookieGadgets.morphs.pig", 10, 25, Rarity.RARE, Arrays.asList("&7Transform into a Pig. While", "&7transformed, you can squeal", "&7by right-clicking the Slimeball", "&7item in your hotbar."), GDisguiseType.PIG, MorphPig.class);
        COW = new MorphType("Cow", "&9Cow Morph", new GMaterial(EnumMaterial.COOKED_BEEF), "CookieGadgets.morphs.cow", 10, 20, Rarity.RARE, Arrays.asList("&7Transform into a Cow. While", "&7transformed, you can moo", "&7by right-clicking the Slimeball", "&7item in your hotbar."), GDisguiseType.COW, MorphCow.class);
        ENDERMAN = new MorphType("Enderman", "&5Enderman Morph", new GMaterial(EnumMaterial.ENDER_PEARL), "CookieGadgets.morphs.enderman", 5, 60, Rarity.EPIC, Arrays.asList("&7Transform into a Enderman. While", "&7transformed, you can teleport", "&7randomly by right-clicking the", "&7Slimeball item in your hotbar."), GDisguiseType.ENDERMAN, MorphEnderman.class);
        CHICKEN = new MorphType("Chicken", "&5Chicken Morph", new GMaterial(EnumMaterial.EGG), "CookieGadgets.morphs.chicken", 3, 35, Rarity.EPIC, Arrays.asList("&7Transform into a Chicken. While", "&7transformed, you can lay eggs", "&7by right-clicking the Slimeball", "&7item in your hotbar."), GDisguiseType.CHICKEN, MorphChicken.class);
        SPIDER = new MorphType("Spider", "&5Spider Morph", new GMaterial(EnumMaterial.COBWEB), "CookieGadgets.morphs.spider", 10, 50, Rarity.EPIC, Arrays.asList("&7Transform into a Spider. While", "&7transformed, you can shoot webs", "&7all around you by right-clicking", "&7the Slimeball item in your", "&7hotbar."), GDisguiseType.SPIDER, MorphSpider.class);
        SHEEP = new MorphType("Sheep", "&5Sheep Morph", new GMaterial(EnumMaterial.WHITE_WOOL), "CookieGadgets.morphs.sheep", 10, 50, Rarity.EPIC, Arrays.asList("&7Transform into a Sheep. While", "&7transformed, you can activate", "&7Rainbow Sheep Mode by", "&7right-clicking the Slimeball item", "&7in your hotbar."), GDisguiseType.SHEEP, MorphSheep.class);
        SKELETON = new MorphType("Skeleton", "&5Skeleton Morph", new GMaterial(EnumMaterial.BOW), "CookieGadgets.morphs.skeleton", 2, 70, Rarity.EPIC, Arrays.asList("&7Transform into a Skeleton. While", "&7transformed, you can shoot arrows", "&7by right-clicking the Slimeball", "&7item in your hotbar."), GDisguiseType.SKELETON, MorphSkeleton.class);
        CREEPER = new MorphType("Creeper", "&6Creeper Morph", new GMaterial(EnumMaterial.TNT), "CookieGadgets.morphs.creeper", 10, 95, Rarity.LEGENDARY, Arrays.asList("&7Transform into a Creeper. While", "&7transformed, you can explode to", "&7knock nearby players aways by", "&7right-clicking the Slimeball", "&7item in your hotbar."), GDisguiseType.CREEPER, MorphCreeper.class);
        BLAZE = new MorphType("Blaze", "&6Blaze Morph", new GMaterial(EnumMaterial.BLAZE_POWDER), "CookieGadgets.morphs.blaze", 3, 125, Rarity.LEGENDARY, Arrays.asList("&7Transform into a Blaze. While", "&7transformed, you can shoot", "&7fireballs by right-clicking the", "&7Slimeball item in your hotbar."), GDisguiseType.BLAZE, MorphBlaze.class);
        ZOMBIE = new MorphType("Zombie", "&6Zombie Morph", new GMaterial(EnumMaterial.ROTTEN_FLESH), "CookieGadgets.morphs.zombie", 10, 105, Rarity.LEGENDARY, Arrays.asList("&7Transform into a Zombie. While", "&7transformed, you can infect other", "&7players and turn them into a", "&7zombie by right-clicking with", "&7the Slimeball item in your hotbar."), GDisguiseType.ZOMBIE, MorphZombie.class);
        IRON_GOLEM = new MorphType("Iron Golem", "&6Iron Golem Morph", new GMaterial(EnumMaterial.IRON_BLOCK), "CookieGadgets.morphs.irongolem", 10, 120, Rarity.LEGENDARY, Arrays.asList("&7Transform into a Iron Golem.", "&7While transformed, you can vigorously", "&7hit the ground by", "&7right-clicking with the Slimeball", "&7item in your hotbar."), GDisguiseType.IRON_GOLEM, MorphIronGolem.class);
        WITCH = new MorphType("Witch", "&6Witch Morph", new GMaterial(EnumMaterial.POTION, 8196), "CookieGadgets.morphs.witch", 10, 110, Rarity.LEGENDARY, Arrays.asList("&7Transform into a Witch. While", "&7transformed, you can throw special", "&7potions by right-clicking with the", "&7Slimeball item in your hotbar."), GDisguiseType.WITCH, MorphWitch.class);
        SNOWMAN = new MorphType("Snowman", "&6Snowman Morph", new GMaterial(EnumMaterial.SNOWBALL), "CookieGadgets.morphs.snowman", 0, 125, Rarity.LEGENDARY, Arrays.asList("&7Transform into a Snowman. While", "&7transformed, you can throw", "&7snowballs by right-clicking with", "&7the Slimeball item in your", "&7hotbar."), GDisguiseType.SNOWMAN, MorphSnowman.class);
        GUARDIAN = new MorphType("Guardian", "&6Guardian Morph", new GMaterial(EnumMaterial.PRISMARINE_SHARD), "CookieGadgets.morphs.guardian", 3, 130, Rarity.LEGENDARY, Arrays.asList("&7Transform into a Guardian.", "&7While transformed, you can shoot", "&7lasers by right-clicking with", "&7the Slimeball item in your", "&7hotbar."), GDisguiseType.GUARDIAN, MorphGuardian.class);
    }
    
    private MorphType(final String s, final String displayName, final GMaterial material, final String s2, final int cooldown, final int mysteryDust, final Rarity rarity, final List<String> list, final GDisguiseType disguiseType, final Class<? extends Morph> clazz) {
        super(Category.MORPHS, s, displayName, material, s2, mysteryDust, rarity, list, list);
        if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getMorphsFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Material") == null) {
            this.material = material;
            FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Material", this.material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getMorphsFile().getString(String.valueOf(this.getFilePath()) + ".Material"));
        }
        if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Cooldown") == null) {
            this.cooldown = cooldown;
            FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Cooldown", this.cooldown);
        }
        else {
            this.cooldown = FileManager.getMorphsFile().getInt(String.valueOf(this.getFilePath()) + ".Cooldown");
        }
        if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getMorphsFile().getInt(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getMorphsFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getMorphsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Enabled");
        }
        if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getMorphsFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getMorphsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Morph-Ability") == null) {
            if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Enabled-Ability") != null) {
                this.isAbilityEnable = FileManager.getMorphsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Enabled-Ability");
                FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Morph-Ability", this.isAbilityEnable);
            }
            else {
                this.isAbilityEnable = true;
                FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Enabled-Ability", true);
            }
            FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Enabled-Ability", null);
        }
        else {
            this.isAbilityEnable = FileManager.getMorphsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Morph-Ability");
        }
        if (s == "Creeper") {
            FileManager.getMorphsFile().addDefault(String.valueOf(this.getFilePath()) + ".Affect-Players", true);
        }
        if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                }
                else {
                    FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getMorphsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getMorphsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getMorphsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getMorphsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.disguiseType = disguiseType;
        this.clazz = clazz;
        if (!MorphType.VALUES.contains(this)) {
            MorphType.VALUES.add(this);
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
    
    public GDisguiseType getDisguiseType() {
        return this.disguiseType;
    }
    
    public Class<? extends Morph> getClazz() {
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
    
    public boolean isAbilityEnabled() {
        return this.isAbilityEnable;
    }
    
    @Override
    public String getFilePath() {
        return "Morphs." + super.getName();
    }
    
    @Override
    public void equip(final PlayerManager playerManager) {
        if (!this.isEnabled() || !Category.MORPHS.isEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        if (!CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() && !CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) {
            return;
        }
        final Player player = playerManager.getPlayer();
        CategoryManager.removeArmorSlotCosmetic(playerManager);
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory().getItem(gadgetSlot) != null) {
            CategoryManager.removeHotbarCosmetic(playerManager);
            if (player.getInventory().getItem(gadgetSlot) != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ITEM_FROM_SLOT_TO_EQUIP_MORPH.getFormatMessage().replace("{SLOT}", String.valueOf(gadgetSlot + 1)).replace("{ITEM}", player.getInventory().getItem(gadgetSlot).getType().name()))) {
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
        playerManager.setEquippedMorph(this);
        CookieGadgets.getGDisguise().disguise(player, this.getDisguiseType());
        if (CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() && this.isAbilityEnabled()) {
            MorphManager.giveSlimeball(player);
        }
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
        if (!CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() && !CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        MorphManager.removeSlimeball(player);
        if (CookieGadgets.getGDisguise().isDisguised(player) || playerManager.getEquippedMorph() != null) {
            if (CookieGadgets.getGDisguise().isDisguised(player)) {
                CookieGadgets.getGDisguise().undisguise(player);
            }
            if (playerManager.getEquippedMorph() != null) {
                playerManager.setEquippedMorph(null);
            }
            if (playerManager.getCurrentMorph() != null) {
                playerManager.getCurrentMorph().onClear();
                playerManager.removeMorph();
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
        if (!CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() && !CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) {
            return false;
        }
        final Player player = playerManager.getPlayer();
        if (CookieGadgets.getCookieGadgetsData().isAntiLagEnabled() && CookieGadgets.getCookieGadgetsData().disableUsageIfLowTPS() && AntiLagChecker.isLowTPS()) {
            player.sendMessage(MessageType.DISABLE_USAGE_OF_COSMETICS.getFormatMessage());
            return false;
        }
        if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.MORPHS)) {
            player.sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return false;
        }
        CategoryManager.removeArmorSlotCosmetic(playerManager);
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory().getItem(gadgetSlot) != null) {
            CategoryManager.removeHotbarCosmetic(playerManager);
            if (player.getInventory().getItem(gadgetSlot) != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_ITEM_FROM_SLOT_TO_EQUIP_MORPH.getFormatMessage().replace("{SLOT}", String.valueOf(gadgetSlot + 1)).replace("{ITEM}", player.getInventory().getItem(gadgetSlot).getType().name()))) {
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
    
    public static List<MorphType> enabled() {
        return MorphType.ENABLED;
    }
    
    public static List<MorphType> values() {
        return MorphType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final MorphType morphType : values()) {
            if (morphType.isEnabled() && !MorphType.ENABLED.contains(morphType)) {
                MorphType.ENABLED.add(morphType);
            }
        }
    }
    
    public static void sortItems() {
        if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.NAME) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super MorphType, ? extends Comparable>)MorphType::getDisplayNameStripColor));
        }
        else if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.RARITY) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super Object, ? extends Comparable>)MorphType::getRarity).thenComparing((Function<? super Object, ? extends Comparable>)MorphType::getDisplayNameStripColor));
        }
    }
    
    public static MorphType valueOf(final String anotherString) {
        for (final MorphType morphType : values()) {
            if (morphType.getName().equalsIgnoreCase(anotherString)) {
                return morphType;
            }
        }
        return null;
    }
}
