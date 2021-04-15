

package ws.billy.CookieGadgets.player;

import org.bukkit.OfflinePlayer;
import ws.billy.CookieGadgets.utils.mysteryboxes.MysteryBoxesReward;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import ws.billy.CookieGadgets.utils.MathUtil;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxesManager;
import ws.billy.CookieGadgets.utils.mysteryboxes.MysteryBoxType;
import java.util.Collection;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetMessages;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.api.event.pets.SendPetForMissionEvent;
import ws.billy.CookieGadgets.api.event.pets.PetRenameEvent;
import org.bukkit.inventory.meta.ItemMeta;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.inventory.ItemStack;
import java.sql.Timestamp;
import ws.billy.CookieGadgets.database.DatabaseUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.List;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.WorldUtils;
import ws.billy.CookieGadgets.cosmetics.CategoryManager;
import org.apache.commons.lang.Validate;
import ws.billy.CookieGadgets.menu.menus.PetItemsMenu;
import ws.billy.CookieGadgets.menu.menus.mysteryvault.MysteryVaultMenu;
import ws.billy.CookieGadgets.menu.menus.ItemPurchaseMenu;
import ws.billy.CookieGadgets.menu.menus.PetTypesMenu;
import ws.billy.CookieGadgets.menu.menus.GadgetTypesMenu;
import ws.billy.CookieGadgets.menu.menus.SuitEquipmentMenu;
import java.util.Iterator;
import ws.billy.CookieGadgets.menu.menus.MainMenu;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.api.event.mysterydust.RemoveMysteryDustEvent;
import ws.billy.CookieGadgets.api.event.mysterydust.AssignMysteryDustEvent;
import org.bukkit.event.Event;
import ws.billy.CookieGadgets.api.event.mysterydust.GainMysteryDustEvent;
import ws.billy.CookieGadgets.economy.GStorage;
import ws.billy.CookieGadgets.database.DatabaseStorage;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.utils.discount.DiscountManager;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.PurchaseData;
import ws.billy.CookieGadgets.utils.discount.ItemCostDiscount;
import ws.billy.CookieGadgets.cosmetics.cloaks.types.Cloak;
import ws.billy.CookieGadgets.cosmetics.emotes.Emote;
import ws.billy.CookieGadgets.cosmetics.morphs.types.Morph;
import ws.billy.CookieGadgets.cosmetics.miniatures.types.Miniature;
import ws.billy.CookieGadgets.cosmetics.pets.Pet;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.Gadget;
import ws.billy.CookieGadgets.cosmetics.suits.types.Suit;
import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHat;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticType;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import ws.billy.CookieGadgets.cosmetics.emotes.EmoteType;
import ws.billy.CookieGadgets.cosmetics.banners.BannerType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetCategoryType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitEquipmentType;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import java.util.Map;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import ws.billy.CookieGadgets.cosmetics.particles.ParticleType;
import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHatType;
import ws.billy.CookieGadgets.cosmetics.hats.standard.HatType;
import ws.billy.CookieGadgets.utils.EnumCache;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import ws.billy.CookieGadgets.cosmetics.pets.PetCategoryType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.GPet;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import java.util.HashMap;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxes;
import java.util.ArrayList;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.ConfirmOpenMultipleBoxesMenu;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.types.Animation;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.entity.Player;
import java.util.UUID;

public class PlayerManager extends OfflinePlayerManager
{
    private UUID uuid;
    private Player player;
    private int mysteryDust;
    private BukkitTask mysteryBoxesRewardTask;
    private int afkTime;
    private int playTime;
    private AnimationType mysteryVaultAnimation;
    private Animation animation;
    private ConfirmOpenMultipleBoxesMenu.OpenMultipleBoxesType openType;
    private ArrayList<MysteryBoxes> mysteryBoxes;
    private ArrayList<MysteryBoxes> mysteryBoxesCache;
    private MysteryVault selectedMysteryVault;
    private MysteryBoxes selectedMysteryBox;
    private boolean isOpeningMysteryBox;
    private String recentLootsFound;
    private int giftPacks;
    private int giftSent;
    private int giftReceived;
    private boolean isRefreshTaskActivated;
    private Player selectedSendGiftPlayer;
    private HashMap<PetType, GPet> petsData;
    private long lastPetMissionTimeMillis;
    private PetCategoryType selectedProvideCareCategoryPet;
    private GPet provideCarePet;
    private HashMap<PetItems, Integer> petItems;
    private EnumCache canSeeSelfMorph;
    private EnumCache isBypassCooldown;
    private boolean renamingPet;
    private HatType equippedHat;
    private AnimatedHatType equippedAnimatedHat;
    private ParticleType equippedParticle;
    private SuitType selectedCategorySuit;
    private Map<EnumArmorType, SuitEquipmentType> equippedSuitEquipment;
    private GadgetCategoryType selectedCategoryGadget;
    private GadgetType equippedGadget;
    private PetCategoryType selectedCategoryPet;
    private PetType equippedPet;
    private MiniatureType equippedMiniature;
    private MorphType equippedMorph;
    private BannerType equippedBanner;
    private EmoteType equippedEmote;
    private CloakType equippedCloak;
    private ArrayList<CosmeticType> cacheEquippedCosmetics;
    private AnimatedHat animatedHat;
    private Suit suit;
    private Gadget gadget;
    private Pet pet;
    private Miniature miniature;
    private Morph morph;
    private Emote emote;
    private Cloak cloak;
    private int unlockedHats;
    private int unlockedAnimatedHats;
    private int unlockedParticles;
    private int unlockedSuits;
    private int unlockedGadgets;
    private int unlockedPets;
    private int unlockedMiniatures;
    private int unlockedMorphs;
    private int unlockedBanners;
    private int unlockedEmotes;
    private int unlockedCloaks;
    private HashMap<SuitType, Long> suitCooldown;
    private HashMap<GadgetType, Long> gadgetCooldown;
    private HashMap<MorphType, Long> morphCooldown;
    private HashMap<EmoteType, Long> emoteCooldown;
    private int currentMenuPage;
    private ItemCostDiscount itemCostDiscount;
    private PurchaseData purchaseData;
    private boolean disableBlockDamage;
    private boolean disableFireDamage;
    private boolean disableFallDamage;
    private HashMap<Category, ArrayList<String>> unlockedCosmeticItems;
    private ArrayList<String> unlockedCosmeticItemsPermissions;
    private int UID;
    private boolean isMoving;
    private Location lastLocation;
    private boolean isLoaded;
    
    public PlayerManager(final UUID uuid) {
        super(uuid);
        this.mysteryDust = -1;
        this.afkTime = 0;
        this.playTime = 0;
        this.openType = null;
        this.mysteryBoxes = new ArrayList<MysteryBoxes>();
        this.mysteryBoxesCache = new ArrayList<MysteryBoxes>();
        this.giftPacks = -1;
        this.giftSent = -1;
        this.giftReceived = -1;
        this.isRefreshTaskActivated = false;
        this.selectedSendGiftPlayer = null;
        this.petsData = new HashMap<PetType, GPet>();
        this.selectedProvideCareCategoryPet = null;
        this.provideCarePet = null;
        this.petItems = new HashMap<PetItems, Integer>();
        this.canSeeSelfMorph = EnumCache.UNLOADED;
        this.isBypassCooldown = EnumCache.UNLOADED;
        this.renamingPet = false;
        this.equippedHat = null;
        this.equippedAnimatedHat = null;
        this.equippedParticle = null;
        this.selectedCategorySuit = null;
        this.equippedSuitEquipment = new HashMap<EnumArmorType, SuitEquipmentType>();
        this.selectedCategoryGadget = null;
        this.equippedGadget = null;
        this.selectedCategoryPet = null;
        this.equippedPet = null;
        this.equippedMiniature = null;
        this.equippedMorph = null;
        this.equippedBanner = null;
        this.equippedEmote = null;
        this.equippedCloak = null;
        this.cacheEquippedCosmetics = new ArrayList<CosmeticType>();
        this.unlockedHats = 0;
        this.unlockedAnimatedHats = 0;
        this.unlockedParticles = 0;
        this.unlockedSuits = 0;
        this.unlockedGadgets = 0;
        this.unlockedPets = 0;
        this.unlockedMiniatures = 0;
        this.unlockedMorphs = 0;
        this.unlockedBanners = 0;
        this.unlockedEmotes = 0;
        this.unlockedCloaks = 0;
        this.suitCooldown = new HashMap<SuitType, Long>();
        this.gadgetCooldown = new HashMap<GadgetType, Long>();
        this.morphCooldown = new HashMap<MorphType, Long>();
        this.emoteCooldown = new HashMap<EmoteType, Long>();
        this.currentMenuPage = 1;
        this.disableBlockDamage = false;
        this.disableFireDamage = false;
        this.disableFallDamage = false;
        this.unlockedCosmeticItems = new HashMap<Category, ArrayList<String>>();
        this.unlockedCosmeticItemsPermissions = new ArrayList<String>();
        this.UID = -1;
        this.isMoving = false;
        this.lastLocation = null;
        this.isLoaded = false;
        this.uuid = uuid;
        this.player = Bukkit.getPlayer(this.uuid);
    }
    
    public Player getPlayer() {
        if (this.player == null) {
            return Bukkit.getPlayer(this.uuid);
        }
        return this.player;
    }
    
    @Override
    public String getName() {
        if (this.player != null) {
            return this.player.getName();
        }
        final Player player = Bukkit.getPlayer(this.uuid);
        if (player == null) {
            return null;
        }
        return player.getName();
    }
    
    @Override
    public UUID getUUID() {
        return this.uuid;
    }
    
    @Override
    public boolean isOfflinePlayer() {
        return false;
    }
    
    public void initData() {
        final ItemCostDiscount itemCostDiscount;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            if (!this.isLoaded) {
                if (this.getPlayer() != null && this.getPlayer().isOnline()) {
                    this.loadPetsData();
                    this.loadPetItems();
                    DiscountManager.getHighestDiscountGroup(this.getPlayer());
                    if (itemCostDiscount != null) {
                        this.setItemCostDiscount(itemCostDiscount);
                    }
                    this.loadUnlockedCosmeticItems();
                    this.loadMysteryBoxes();
                    this.updateMysteryDust();
                }
            }
        });
    }
    
    @Override
    public int getMysteryDust() {
        if (!this.isLoaded && CookieGadgets.getCookieGadgetsData().getDatabaseStorage() == DatabaseStorage.SQLITE && CookieGadgets.getCookieGadgetsData().getMysteryDustStorage().equals(GStorage.DEFAULT) && this.mysteryDust == -1) {
            try {
                return this.mysteryDust = CookieGadgets.getGEconomyProvider().getMysteryDust(this);
            }
            catch (NullPointerException ex) {
                return this.mysteryDust = -1;
            }
        }
        if (CookieGadgets.getCookieGadgetsData().getMysteryDustStorage().equals(GStorage.DEFAULT)) {
            return this.mysteryDust;
        }
        final int mysteryDust = this.mysteryDust;
        this.mysteryDust = CookieGadgets.getGEconomyProvider().getMysteryDust(this);
        if (this.mysteryDust != mysteryDust) {
            this.updateMenuSelectorDescription();
        }
        return this.mysteryDust;
    }
    
    @Override
    public boolean addMysteryDust(final int n) {
        final GainMysteryDustEvent gainMysteryDustEvent = new GainMysteryDustEvent(this.getPlayer(), n);
        Bukkit.getServer().getPluginManager().callEvent((Event)gainMysteryDustEvent);
        if (gainMysteryDustEvent.isCancelled()) {
            return false;
        }
        final boolean addMysteryDust = CookieGadgets.getGEconomyProvider().addMysteryDust(this, n);
        this.mysteryDust = this.getMysteryDust() + n;
        this.updateMenuSelectorDescription();
        return addMysteryDust;
    }
    
    @Override
    public boolean setMysteryDust(final int mysteryDust) {
        if (mysteryDust < 0) {
            throw new IllegalArgumentException("The data cannot be negative value.");
        }
        final AssignMysteryDustEvent assignMysteryDustEvent = new AssignMysteryDustEvent(this.getPlayer(), mysteryDust);
        Bukkit.getServer().getPluginManager().callEvent((Event)assignMysteryDustEvent);
        if (assignMysteryDustEvent.isCancelled()) {
            return false;
        }
        final boolean setMysteryDust = CookieGadgets.getGEconomyProvider().setMysteryDust(this, mysteryDust);
        this.mysteryDust = mysteryDust;
        this.updateMenuSelectorDescription();
        return setMysteryDust;
    }
    
    @Override
    public boolean removeMysteryDust(final int n) {
        if (this.getMysteryDust() - n < 0) {
            throw new IllegalArgumentException("The new data cannot be negative value.");
        }
        final RemoveMysteryDustEvent removeMysteryDustEvent = new RemoveMysteryDustEvent(this.getPlayer(), n);
        Bukkit.getServer().getPluginManager().callEvent((Event)removeMysteryDustEvent);
        if (removeMysteryDustEvent.isCancelled()) {
            return false;
        }
        final boolean removeMysteryDust = CookieGadgets.getGEconomyProvider().removeMysteryDust(this, n);
        this.mysteryDust = this.getMysteryDust() - n;
        this.updateMenuSelectorDescription();
        return removeMysteryDust;
    }
    
    public void updateMysteryDust() {
        final int mysteryDust = this.mysteryDust;
        this.mysteryDust = CookieGadgets.getGEconomyProvider().getMysteryDust(this);
        if (this.mysteryDust != mysteryDust) {
            this.updateMenuSelectorDescription();
        }
    }
    
    public void goBackToMainMenu() {
        if (CookieGadgets.getCookieGadgetsData().isExecuteCommandsWhenBackToMainMenu()) {
            final Iterator<String> iterator = CookieGadgets.getCookieGadgetsData().getBackToMainMenuCustomCommands().iterator();
            while (iterator.hasNext()) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), ChatUtil.format(iterator.next().replace("{PLAYER}", this.getPlayer().getName())));
            }
        }
        else {
            MainMenu.openMainMenu(this.getPlayer());
        }
    }
    
    public void openHatsMenu(final int n) {
        Category.HATS.openMenu(this, n);
    }
    
    public void openAnimatedHatsMenu(final int n) {
        Category.ANIMATED_HATS.openMenu(this, n);
    }
    
    public void openParticlesMenu(final int n) {
        Category.PARTICLES.openMenu(this, n);
    }
    
    public void openSuitsMenu() {
        Category.SUITS.openMenu(this, 1);
    }
    
    public void openSuitEquipmentMenu(final SuitType suitType) {
        SuitEquipmentMenu.openSuitEquipmentMenu(this, suitType);
    }
    
    public void openCategoryCookieGadgets() {
        Category.GADGETS.openMenu(this, 1);
    }
    
    public void openGadgetTypesMenu(final GadgetCategoryType gadgetCategoryType, final int n) {
        GadgetTypesMenu.openGadgetTypesMenu(this, gadgetCategoryType, n);
    }
    
    public void openCategoryPetsMenu() {
        Category.PETS.openMenu(this, 1);
    }
    
    public void openPetTypesMenu(final PetCategoryType petCategoryType, final int n) {
        PetTypesMenu.openPetTypesMenu(this, petCategoryType, n);
    }
    
    public void openMiniaturesMenu(final int n) {
        Category.MINIATURES.openMenu(this, n);
    }
    
    public void openMorphsMenu() {
        Category.MORPHS.openMenu(this, 1);
    }
    
    public void openBannersMenu(final int n) {
        Category.BANNERS.openMenu(this, n);
    }
    
    public void openEmotesMenu(final int n) {
        Category.EMOTES.openMenu(this, n);
    }
    
    public void openCloaksMenu() {
        Category.CLOAKS.openMenu(this, 1);
    }
    
    public void openItemPurchaseMenu() {
        ItemPurchaseMenu.openItemPurchaseMenu(this.getPlayer());
    }
    
    public void openMysteryVaultMenu(final int n) {
        MysteryVaultMenu.openMysteryVaultMenu(this.getPlayer(), n);
    }
    
    public void openPetItemsMenu(final GPet gPet) {
        PetItemsMenu.openPetItemsMenu(this, gPet);
    }
    
    public boolean hasActiveCosmetics() {
        return this.equippedHat != null || this.equippedAnimatedHat != null || this.equippedParticle != null || !this.equippedSuitEquipment.isEmpty() || this.equippedGadget != null || this.equippedPet != null || this.equippedMiniature != null || this.equippedMorph != null || this.equippedBanner != null || this.equippedEmote != null || this.equippedCloak != null;
    }
    
    public boolean hasActiveCosmetic(final Category category) {
        switch (category) {
            case HATS: {
                if (this.equippedHat != null) {
                    return true;
                }
                break;
            }
            case ANIMATED_HATS: {
                if (this.equippedAnimatedHat != null) {
                    return true;
                }
                break;
            }
            case PARTICLES: {
                if (this.equippedParticle != null) {
                    return true;
                }
                break;
            }
            case SUITS: {
                if (!this.equippedSuitEquipment.isEmpty()) {
                    return true;
                }
                break;
            }
            case GADGETS: {
                if (this.equippedGadget != null) {
                    return true;
                }
                break;
            }
            case PETS: {
                if (this.equippedPet != null) {
                    return true;
                }
                break;
            }
            case MINIATURES: {
                if (this.equippedMiniature != null) {
                    return true;
                }
                break;
            }
            case MORPHS: {
                if (this.equippedMorph != null) {
                    return true;
                }
                break;
            }
            case BANNERS: {
                if (this.equippedBanner != null) {
                    return true;
                }
                break;
            }
            case EMOTES: {
                if (this.equippedEmote != null) {
                    return true;
                }
                break;
            }
            case CLOAKS: {
                if (this.equippedCloak != null) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    public void unequipActiveCosmetics() {
        this.unequipHat();
        this.unequipAnimatedHat();
        this.unequipParticle();
        this.unequipSuit();
        this.unequipGadget();
        this.unequipPet();
        this.unequipMiniature();
        this.unequipMorph();
        this.unequipBanner();
        this.unequipEmote();
        this.unequipCloak();
    }
    
    public void unequipCosmetic(final Category category) {
        switch (category) {
            case HATS: {
                this.unequipHat();
                break;
            }
            case ANIMATED_HATS: {
                this.unequipAnimatedHat();
                break;
            }
            case PARTICLES: {
                this.unequipParticle();
                break;
            }
            case SUITS: {
                this.unequipSuit();
                break;
            }
            case GADGETS: {
                this.unequipGadget();
                break;
            }
            case PETS: {
                this.unequipPet();
                break;
            }
            case MINIATURES: {
                this.unequipMiniature();
                break;
            }
            case MORPHS: {
                this.unequipMorph();
                break;
            }
            case BANNERS: {
                this.unequipBanner();
                break;
            }
            case EMOTES: {
                this.unequipEmote();
                break;
            }
            case CLOAKS: {
                this.unequipCloak();
                break;
            }
        }
    }
    
    public void equipHat(final HatType hatType) {
        Validate.notNull((Object)hatType);
        hatType.equip(this);
    }
    
    public void unequipHat() {
        if (this.equippedHat != null) {
            this.equippedHat.unequip(this);
        }
        else {
            CategoryManager.removeHelmetCosmetic(this, Category.HATS);
        }
    }
    
    public void equipAnimatedHat(final AnimatedHatType animatedHatType) {
        Validate.notNull((Object)animatedHatType);
        animatedHatType.equip(this);
    }
    
    public void unequipAnimatedHat() {
        if (this.equippedAnimatedHat != null) {
            this.equippedAnimatedHat.unequip(this);
        }
        else {
            CategoryManager.removeHelmetCosmetic(this, Category.ANIMATED_HATS);
        }
    }
    
    public void equipParticle(final ParticleType particleType) {
        Validate.notNull((Object)particleType);
        particleType.equip(this);
    }
    
    public void unequipParticle() {
        if (this.equippedParticle != null) {
            this.equippedParticle.unequip(this);
        }
    }
    
    public void equipSuit(final SuitType suitType) {
        Validate.notNull((Object)suitType);
        SuitEquipmentType.getSuitCategory(suitType).get(0).equip(this);
        SuitEquipmentType.getSuitCategory(suitType).get(1).equip(this);
        SuitEquipmentType.getSuitCategory(suitType).get(2).equip(this);
        SuitEquipmentType.getSuitCategory(suitType).get(3).equip(this);
    }
    
    public void unequipSuit() {
        EnumArmorType[] values;
        for (int length = (values = EnumArmorType.values()).length, i = 0; i < length; ++i) {
            final EnumArmorType enumArmorType = values[i];
            if (this.equippedSuitEquipment.get(enumArmorType) != null) {
                this.equippedSuitEquipment.get(enumArmorType).unequip(this);
            }
            else {
                CategoryManager.removeArmorSlotCosmetic(this, Category.SUITS);
            }
        }
    }
    
    public void equipSuitEquipment(final SuitEquipmentType suitEquipmentType) {
        Validate.notNull((Object)suitEquipmentType);
        suitEquipmentType.equip(this);
    }
    
    public void unequipSuitEquipment(final EnumArmorType enumArmorType) {
        switch (enumArmorType) {
            case HELMET: {
                if (this.equippedSuitEquipment.get(EnumArmorType.HELMET) != null) {
                    this.equippedSuitEquipment.get(EnumArmorType.HELMET).unequip(this);
                    break;
                }
                CategoryManager.removeHelmetCosmetic(this, Category.SUITS);
                break;
            }
            case CHESTPLATE: {
                if (this.equippedSuitEquipment.get(EnumArmorType.CHESTPLATE) != null) {
                    this.equippedSuitEquipment.get(EnumArmorType.CHESTPLATE).unequip(this);
                    break;
                }
                CategoryManager.removeChestplateCosmetic(this, Category.SUITS);
                break;
            }
            case LEGGINGS: {
                if (this.equippedSuitEquipment.get(EnumArmorType.LEGGINGS) != null) {
                    this.equippedSuitEquipment.get(EnumArmorType.LEGGINGS).unequip(this);
                    break;
                }
                CategoryManager.removeLeggingsCosmetic(this, Category.SUITS);
                break;
            }
            case BOOTS: {
                if (this.equippedSuitEquipment.get(EnumArmorType.BOOTS) != null) {
                    this.equippedSuitEquipment.get(EnumArmorType.BOOTS).unequip(this);
                    break;
                }
                CategoryManager.removeBootsCosmetic(this, Category.SUITS);
                break;
            }
        }
    }
    
    public void equipGadget(final GadgetType gadgetType) {
        Validate.notNull((Object)gadgetType);
        gadgetType.equip(this);
    }
    
    public void unequipGadget() {
        if (this.equippedGadget != null) {
            this.equippedGadget.unequip(this);
        }
        else {
            CategoryManager.removeHotbarCosmetic(this, Category.GADGETS);
        }
    }
    
    public void equipPet(final PetType petType) {
        Validate.notNull((Object)petType);
        petType.equip(this);
    }
    
    public void unequipPet() {
        if (this.equippedPet != null) {
            this.equippedPet.unequip(this);
        }
    }
    
    public void equipMiniature(final MiniatureType miniatureType) {
        Validate.notNull((Object)miniatureType);
        miniatureType.equip(this);
    }
    
    public void unequipMiniature() {
        if (this.equippedMiniature != null) {
            this.equippedMiniature.unequip(this);
        }
    }
    
    public void equipMorph(final MorphType morphType) {
        Validate.notNull((Object)morphType);
        morphType.equip(this);
    }
    
    public void unequipMorph() {
        if (this.equippedMorph != null) {
            this.equippedMorph.unequip(this);
        }
        else {
            CategoryManager.removeHotbarCosmetic(this, Category.MORPHS);
        }
    }
    
    public void equipBanner(final BannerType bannerType) {
        Validate.notNull((Object)bannerType);
        bannerType.equip(this);
    }
    
    public void unequipBanner() {
        if (this.equippedBanner != null) {
            this.equippedBanner.unequip(this);
        }
        else {
            CategoryManager.removeHelmetCosmetic(this, Category.BANNERS);
        }
    }
    
    public void equipEmote(final EmoteType emoteType) {
        Validate.notNull((Object)emoteType);
        emoteType.equip(this);
    }
    
    public void unequipEmote() {
        if (this.equippedEmote != null) {
            this.equippedEmote.unequip(this);
        }
        else {
            CategoryManager.removeHotbarCosmetic(this, Category.EMOTES);
            CategoryManager.removeHelmetCosmetic(this, Category.EMOTES);
        }
    }
    
    public void equipCloak(final CloakType cloakType) {
        Validate.notNull((Object)cloakType);
        cloakType.equip(this);
    }
    
    public void unequipCloak() {
        if (this.equippedCloak != null) {
            this.equippedCloak.unequip(this);
        }
    }
    
    public void cacheEquippedCosmetics() {
        if (!CookieGadgets.getCookieGadgetsData().syncCosmeticsOnJoin()) {
            return;
        }
        if (this.equippedHat != null) {
            this.cacheEquippedCosmetics.add(this.equippedHat);
        }
        if (this.equippedAnimatedHat != null) {
            this.cacheEquippedCosmetics.add(this.equippedAnimatedHat);
        }
        if (this.equippedParticle != null) {
            this.cacheEquippedCosmetics.add(this.equippedParticle);
        }
        if (this.equippedSuitEquipment.get(EnumArmorType.HELMET) != null) {
            this.cacheEquippedCosmetics.add(this.equippedSuitEquipment.get(EnumArmorType.HELMET));
        }
        if (this.equippedSuitEquipment.get(EnumArmorType.CHESTPLATE) != null) {
            this.cacheEquippedCosmetics.add(this.equippedSuitEquipment.get(EnumArmorType.CHESTPLATE));
        }
        if (this.equippedSuitEquipment.get(EnumArmorType.LEGGINGS) != null) {
            this.cacheEquippedCosmetics.add(this.equippedSuitEquipment.get(EnumArmorType.LEGGINGS));
        }
        if (this.equippedSuitEquipment.get(EnumArmorType.BOOTS) != null) {
            this.cacheEquippedCosmetics.add(this.equippedSuitEquipment.get(EnumArmorType.BOOTS));
        }
        if (this.equippedGadget != null) {
            this.cacheEquippedCosmetics.add(this.equippedGadget);
        }
        if (this.equippedPet != null) {
            this.cacheEquippedCosmetics.add(this.equippedPet);
        }
        if (this.equippedMiniature != null) {
            this.cacheEquippedCosmetics.add(this.equippedMiniature);
        }
        if (this.equippedMorph != null) {
            this.cacheEquippedCosmetics.add(this.equippedMorph);
        }
        if (this.equippedBanner != null) {
            this.cacheEquippedCosmetics.add(this.equippedBanner);
        }
        if (this.equippedEmote != null) {
            this.cacheEquippedCosmetics.add(this.equippedEmote);
        }
        if (this.equippedCloak != null) {
            this.cacheEquippedCosmetics.add(this.equippedCloak);
        }
    }
    
    public void loadEquippedCosmeticsFromCache() {
        if (this.getPlayer() == null) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(this.getPlayer().getWorld())) {
            return;
        }
        if (!CookieGadgets.getCookieGadgetsData().syncCosmeticsOnJoin()) {
            return;
        }
        final Iterator<CosmeticType> iterator;
        CosmeticType cosmeticType;
        final CosmeticType cosmeticType2;
        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            if (!(!WorldUtils.isWorldEnabled(this.getPlayer().getWorld()))) {
                try {
                    this.cacheEquippedCosmetics.iterator();
                    while (iterator.hasNext()) {
                        cosmeticType = iterator.next();
                        if (cosmeticType != null && (cosmeticType.getCategory() == Category.GADGETS || cosmeticType.getCategory() == Category.PETS || cosmeticType.getCategory() == Category.MORPHS)) {
                            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                                if (cosmeticType2.getCategory().syncOnJoin() && !PermissionUtils.noPermission(this.getPlayer(), cosmeticType2.getPermission(), cosmeticType2.getCategory().getMainPermission().getPermission(), false)) {
                                    cosmeticType2.equip(this);
                                }
                                return;
                            });
                        }
                        else if (cosmeticType != null && cosmeticType.getCategory().syncOnJoin() && !PermissionUtils.noPermission(this.getPlayer(), cosmeticType.getPermission(), cosmeticType.getCategory().getMainPermission().getPermission(), false)) {
                            cosmeticType.equip(this);
                        }
                        else {
                            continue;
                        }
                    }
                }
                catch (ConcurrentModificationException ex) {}
                this.removeCachedEquippedCosmetics();
            }
        }, 10L);
    }
    
    public void removeCachedEquippedCosmetics() {
        this.cacheEquippedCosmetics.clear();
    }
    
    public void saveCachedCosmetics(final boolean b) {
        if (!CookieGadgets.getCookieGadgetsData().syncCosmeticsOnJoin()) {
            return;
        }
        if (!this.isLoaded()) {
            return;
        }
        CosmeticType cosmeticType = null;
        CosmeticType cosmeticType2 = null;
        CosmeticType cosmeticType3 = null;
        CosmeticType cosmeticType4 = null;
        CosmeticType cosmeticType5 = null;
        CosmeticType cosmeticType6 = null;
        CosmeticType cosmeticType7 = null;
        CosmeticType cosmeticType8 = null;
        CosmeticType cosmeticType9 = null;
        CosmeticType cosmeticType10 = null;
        CosmeticType cosmeticType11 = null;
        CosmeticType cosmeticType12 = null;
        CosmeticType cosmeticType13 = null;
        CosmeticType cosmeticType14 = null;
        for (final CosmeticType cosmeticType15 : this.cacheEquippedCosmetics) {
            if (cosmeticType15 instanceof HatType) {
                cosmeticType = cosmeticType15;
            }
            else if (cosmeticType15 instanceof AnimatedHatType) {
                cosmeticType2 = cosmeticType15;
            }
            else if (cosmeticType15 instanceof ParticleType) {
                cosmeticType3 = cosmeticType15;
            }
            else if (cosmeticType15 instanceof SuitEquipmentType) {
                final SuitEquipmentType suitEquipmentType = (SuitEquipmentType)cosmeticType15;
                if (suitEquipmentType.getSuitMaterial().getArmorType() == EnumArmorType.HELMET) {
                    cosmeticType4 = cosmeticType15;
                }
                if (suitEquipmentType.getSuitMaterial().getArmorType() == EnumArmorType.CHESTPLATE) {
                    cosmeticType5 = cosmeticType15;
                }
                if (suitEquipmentType.getSuitMaterial().getArmorType() == EnumArmorType.LEGGINGS) {
                    cosmeticType6 = cosmeticType15;
                }
                if (suitEquipmentType.getSuitMaterial().getArmorType() != EnumArmorType.BOOTS) {
                    continue;
                }
                cosmeticType7 = cosmeticType15;
            }
            else if (cosmeticType15 instanceof GadgetType) {
                cosmeticType8 = cosmeticType15;
            }
            else if (cosmeticType15 instanceof PetType) {
                cosmeticType9 = cosmeticType15;
            }
            else if (cosmeticType15 instanceof MiniatureType) {
                cosmeticType10 = cosmeticType15;
            }
            else if (cosmeticType15 instanceof MorphType) {
                cosmeticType11 = cosmeticType15;
            }
            else if (cosmeticType15 instanceof BannerType) {
                cosmeticType12 = cosmeticType15;
            }
            else if (cosmeticType15 instanceof EmoteType) {
                cosmeticType13 = cosmeticType15;
            }
            else {
                if (!(cosmeticType15 instanceof CloakType)) {
                    continue;
                }
                cosmeticType14 = cosmeticType15;
            }
        }
        final List<String> list = Arrays.asList((cosmeticType == null) ? null : cosmeticType.getName(), (cosmeticType2 == null) ? null : cosmeticType2.getName(), (cosmeticType3 == null) ? null : cosmeticType3.getName(), (cosmeticType4 == null) ? null : cosmeticType4.getName(), (cosmeticType5 == null) ? null : cosmeticType5.getName(), (cosmeticType6 == null) ? null : cosmeticType6.getName(), (cosmeticType7 == null) ? null : cosmeticType7.getName(), (cosmeticType8 == null) ? null : cosmeticType8.getName(), (cosmeticType9 == null) ? null : cosmeticType9.getName(), (cosmeticType10 == null) ? null : cosmeticType10.getName(), (cosmeticType11 == null) ? null : cosmeticType11.getName(), (cosmeticType12 == null) ? null : cosmeticType12.getName(), (cosmeticType13 == null) ? null : cosmeticType13.getName(), (cosmeticType14 == null) ? null : cosmeticType14.getName());
        if (b) {
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> CookieGadgets.getDatabaseManager().getDatabaseUtils().syncEquippedCosmetics(Category.getSQLIndexes(), list, this.getUID()));
        }
        else {
            CookieGadgets.getDatabaseManager().getDatabaseUtils().syncEquippedCosmetics(Category.getSQLIndexes(), list, this.getUID());
        }
    }
    
    public void saveEquippedCosmetics(final boolean b) {
        if (!CookieGadgets.getCookieGadgetsData().syncCosmeticsOnJoin()) {
            return;
        }
        if (!this.isLoaded()) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(this.getPlayer().getWorld())) {
            this.saveCachedCosmetics(b);
        }
        else {
            final List<String> list = Arrays.asList((this.equippedHat == null) ? null : this.equippedHat.getName(), (this.equippedAnimatedHat == null) ? null : this.equippedAnimatedHat.getName(), (this.equippedParticle == null) ? null : this.equippedParticle.getName(), (this.equippedSuitEquipment.get(EnumArmorType.HELMET) == null) ? null : this.equippedSuitEquipment.get(EnumArmorType.HELMET).getName(), (this.equippedSuitEquipment.get(EnumArmorType.CHESTPLATE) == null) ? null : this.equippedSuitEquipment.get(EnumArmorType.CHESTPLATE).getName(), (this.equippedSuitEquipment.get(EnumArmorType.LEGGINGS) == null) ? null : this.equippedSuitEquipment.get(EnumArmorType.LEGGINGS).getName(), (this.equippedSuitEquipment.get(EnumArmorType.BOOTS) == null) ? null : this.equippedSuitEquipment.get(EnumArmorType.BOOTS).getName(), (this.equippedGadget == null) ? null : this.equippedGadget.getName(), (this.equippedPet == null) ? null : this.equippedPet.getName(), (this.equippedMiniature == null) ? null : this.equippedMiniature.getName(), (this.equippedMorph == null) ? null : this.equippedMorph.getName(), (this.equippedBanner == null) ? null : this.equippedBanner.getName(), (this.equippedEmote == null) ? null : this.equippedEmote.getName(), (this.equippedCloak == null) ? null : this.equippedCloak.getName());
            if (b) {
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> CookieGadgets.getDatabaseManager().getDatabaseUtils().syncEquippedCosmetics(Category.getSQLIndexes(), list, this.getUID()));
            }
            else {
                CookieGadgets.getDatabaseManager().getDatabaseUtils().syncEquippedCosmetics(Category.getSQLIndexes(), list, this.getUID());
            }
        }
    }
    
    public void loadEquippedCosmeticsAfterDataLoaded() {
        new BukkitRunnable() {
            public void run() {
                if (PlayerManager.this.isLoaded()) {
                    PlayerManager.this.loadEquippedCosmetics();
                    this.cancel();
                }
            }
        }.runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), 10L, 10L);
    }
    
    public void loadEquippedCosmetics() {
        if (this.getPlayer() == null) {
            return;
        }
        if (!CookieGadgets.getCookieGadgetsData().syncCosmeticsOnJoin()) {
            return;
        }
        ArrayList list;
        final DatabaseUtils databaseUtils;
        final List<String> list2;
        final HatType e;
        final AnimatedHatType e2;
        final ParticleType e3;
        final SuitEquipmentType e4;
        final SuitEquipmentType e5;
        final SuitEquipmentType e6;
        final SuitEquipmentType e7;
        final GadgetType e8;
        final PetType e9;
        final MiniatureType e10;
        final MorphType e11;
        final BannerType e12;
        final EmoteType e13;
        final CloakType e14;
        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            if (this.getPlayer() != null) {
                CookieGadgets.getDatabaseManager().getDatabaseUtils();
                list = new ArrayList();
                databaseUtils.getEquippedCosmetics(Category.getSQLIndexes(), this.getUID());
                HatType.valueOf(list2.get(0));
                AnimatedHatType.valueOf(list2.get(1));
                ParticleType.valueOf(list2.get(2));
                SuitEquipmentType.valueOf(list2.get(3));
                SuitEquipmentType.valueOf(list2.get(4));
                SuitEquipmentType.valueOf(list2.get(5));
                SuitEquipmentType.valueOf(list2.get(6));
                GadgetType.valueOf(list2.get(7));
                PetType.valueOf(list2.get(8));
                MiniatureType.valueOf(list2.get(9));
                MorphType.valueOf(list2.get(10));
                BannerType.valueOf(list2.get(11));
                EmoteType.valueOf(list2.get(12));
                CloakType.valueOf(list2.get(13));
                if (!WorldUtils.isWorldEnabled(this.getPlayer().getWorld())) {
                    if (e != null) {
                        this.cacheEquippedCosmetics.add(e);
                    }
                    if (e2 != null) {
                        this.cacheEquippedCosmetics.add(e2);
                    }
                    if (e3 != null) {
                        this.cacheEquippedCosmetics.add(e3);
                    }
                    if (e4 != null) {
                        this.cacheEquippedCosmetics.add(e4);
                    }
                    if (e5 != null) {
                        this.cacheEquippedCosmetics.add(e5);
                    }
                    if (e6 != null) {
                        this.cacheEquippedCosmetics.add(e6);
                    }
                    if (e7 != null) {
                        this.cacheEquippedCosmetics.add(e7);
                    }
                    if (e8 != null) {
                        this.cacheEquippedCosmetics.add(e8);
                    }
                    if (e9 != null) {
                        this.cacheEquippedCosmetics.add(e9);
                    }
                    if (e10 != null) {
                        this.cacheEquippedCosmetics.add(e10);
                    }
                    if (e11 != null) {
                        this.cacheEquippedCosmetics.add(e11);
                    }
                    if (e12 != null) {
                        this.cacheEquippedCosmetics.add(e12);
                    }
                    if (e13 != null) {
                        this.cacheEquippedCosmetics.add(e13);
                    }
                    if (e14 != null) {
                        this.cacheEquippedCosmetics.add(e14);
                    }
                }
                else {
                    if (Category.HATS.syncOnJoin() && e != null && !PermissionUtils.noPermission(this.getPlayer(), e.getPermission(), EnumPermission.HATS.getPermission(), false)) {
                        this.equipHat(e);
                    }
                    if (Category.ANIMATED_HATS.syncOnJoin() && e2 != null && !PermissionUtils.noPermission(this.getPlayer(), e2.getPermission(), EnumPermission.ANIMATED_HATS.getPermission(), false)) {
                        this.equipAnimatedHat(e2);
                    }
                    if (Category.PARTICLES.syncOnJoin() && e3 != null && !PermissionUtils.noPermission(this.getPlayer(), e3.getPermission(), EnumPermission.PARTICLES.getPermission(), false)) {
                        this.equipParticle(e3);
                    }
                    if (Category.SUITS.syncOnJoin()) {
                        if (e4 != null && !PermissionUtils.noPermission(this.getPlayer(), e4.getPermission(), EnumPermission.SUITS.getPermission(), false)) {
                            this.equipSuitEquipment(e4);
                        }
                        if (e5 != null && !PermissionUtils.noPermission(this.getPlayer(), e5.getPermission(), EnumPermission.SUITS.getPermission(), false)) {
                            this.equipSuitEquipment(e5);
                        }
                        if (e6 != null && !PermissionUtils.noPermission(this.getPlayer(), e6.getPermission(), EnumPermission.SUITS.getPermission(), false)) {
                            this.equipSuitEquipment(e6);
                        }
                        if (e7 != null && !PermissionUtils.noPermission(this.getPlayer(), e7.getPermission(), EnumPermission.SUITS.getPermission(), false)) {
                            this.equipSuitEquipment(e7);
                        }
                    }
                    if (Category.GADGETS.syncOnJoin() && e8 != null && !PermissionUtils.noPermission(this.getPlayer(), e8.getPermission(), EnumPermission.GADGETS.getPermission(), false)) {
                        Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> this.equipGadget(e8));
                    }
                    if (Category.PETS.syncOnJoin() && e9 != null && !PermissionUtils.noPermission(this.getPlayer(), e9.getPermission(), EnumPermission.PETS.getPermission(), false)) {
                        Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> this.equipPet(e9));
                    }
                    if (Category.MINIATURES.syncOnJoin() && e10 != null && !PermissionUtils.noPermission(this.getPlayer(), e10.getPermission(), EnumPermission.MINIATURES.getPermission(), false)) {
                        this.equipMiniature(e10);
                    }
                    if (Category.MORPHS.syncOnJoin() && e11 != null && !PermissionUtils.noPermission(this.getPlayer(), e11.getPermission(), EnumPermission.MORPHS.getPermission(), false)) {
                        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> this.equipMorph(e11), 10L);
                    }
                    if (Category.BANNERS.syncOnJoin() && e12 != null && !PermissionUtils.noPermission(this.getPlayer(), e12.getPermission(), EnumPermission.BANNERS.getPermission(), false)) {
                        this.equipBanner(e12);
                    }
                    if (Category.EMOTES.syncOnJoin() && e13 != null && !PermissionUtils.noPermission(this.getPlayer(), e13.getPermission(), EnumPermission.EMOTES.getPermission(), false)) {
                        this.equipEmote(e13);
                    }
                    if (Category.CLOAKS.syncOnJoin() && e14 != null && !PermissionUtils.noPermission(this.getPlayer(), e14.getPermission(), EnumPermission.CLOAKS.getPermission(), false)) {
                        this.equipCloak(e14);
                    }
                }
            }
        }, 10L);
    }
    
    public boolean hasPermission(final String o) {
        return this.unlockedCosmeticItemsPermissions.contains(o);
    }
    
    public void loadUnlockedCosmeticItems() {
        final Iterator<Category> iterator;
        Category key;
        final Iterator<String> iterator2;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            if (this.getPlayer() != null && this.getPlayer().isOnline()) {
                CookieGadgets.getDatabaseManager().getDatabaseUtils().loadUnlockedCosmeticItems(this);
                this.unlockedCosmeticItems.keySet().iterator();
                while (iterator.hasNext()) {
                    key = iterator.next();
                    this.unlockedCosmeticItems.get(key).iterator();
                    while (iterator2.hasNext()) {
                        this.addUnlockedCosmeticItemsPermissions(key, iterator2.next());
                    }
                }
            }
        });
    }
    
    public void addUnlockedCosmeticItem(final Category category, final String s, final Timestamp timestamp) {
        if (category == null || s == null) {
            return;
        }
        if (!this.unlockedCosmeticItems.containsKey(category)) {
            this.unlockedCosmeticItems.put(category, new ArrayList<String>());
        }
        if (!this.unlockedCosmeticItems.get(category).contains(s)) {
            this.unlockedCosmeticItems.get(category).add(s);
        }
        this.addUnlockedCosmeticItemsPermissions(category, s);
        CookieGadgets.getDatabaseManager().getDatabaseUtils().addUnlockedCosmeticItem(this.getUUID(), this.getUID(), category, s, timestamp);
    }
    
    public void removeUnlockedCosmeticItem(final Category key, final String s) {
        if (this.unlockedCosmeticItems.containsKey(key) && this.unlockedCosmeticItems.get(key).contains(s)) {
            this.unlockedCosmeticItems.get(key).remove(s);
        }
        this.removeUnlockedCosmeticItemsPermissions(key, s);
        CookieGadgets.getDatabaseManager().getDatabaseUtils().removeUnlockedCosmeticItem(this.getUID(), key, s);
    }
    
    public HashMap<Category, ArrayList<String>> unlockedCosmeticItems() {
        return this.unlockedCosmeticItems;
    }
    
    private void addUnlockedCosmeticItemsPermissions(final Category category, final String s) {
        final CosmeticType value = CosmeticType.valueOf(s, category);
        if (value != null && value.getPermission() != null) {
            this.unlockedCosmeticItemsPermissions.add(value.getPermission());
        }
    }
    
    private void removeUnlockedCosmeticItemsPermissions(final Category category, final String s) {
        final CosmeticType value = CosmeticType.valueOf(s, category);
        if (value != null && value.getPermission() != null && this.unlockedCosmeticItemsPermissions.contains(value.getPermission())) {
            this.unlockedCosmeticItemsPermissions.remove(value.getPermission());
        }
    }
    
    public void giveMenuSelector() {
        if (this.getPlayer() == null) {
            return;
        }
        if (!this.getPlayer().hasPermission(EnumPermission.MENU_SELECTOR.getPermission())) {
            return;
        }
        this.removeMenuSelector();
        final int n;
        Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
            CookieGadgets.getCookieGadgetsData().getMenuSelectorSlot();
            if (this.getPlayer() != null && this.getPlayer().isOnline()) {
                if (this.getPlayer().getInventory().getItem(n) != null) {
                    if (CookieGadgets.getNMSManager().hasNBTTag(this.getPlayer().getInventory().getItem(n), "Menu_Selector")) {
                        this.getPlayer().getInventory().remove(CookieGadgets.getCookieGadgetsData().getMenuSelector());
                        this.getPlayer().getInventory().setItem(n, (ItemStack)null);
                        this.getPlayer().updateInventory();
                    }
                    else {
                        this.getPlayer().getWorld().dropItemNaturally(this.getPlayer().getLocation(), this.getPlayer().getInventory().getItem(n).clone());
                        this.getPlayer().getInventory().setItem(n, (ItemStack)null);
                        this.getPlayer().updateInventory();
                    }
                }
                this.getPlayer().getInventory().setItem(n, CookieGadgets.getNMSManager().setNBTTag(ItemUtils.item(CookieGadgets.getCookieGadgetsData().getMenuSelector(), Arrays.asList("{MYSTERY_DUST}", "{MYSTERY_BOXES}"), Arrays.asList(String.valueOf(this.getMysteryDust()), String.valueOf(this.getMysteryBoxes()))), "Menu_Selector", "true"));
                this.getPlayer().updateInventory();
            }
        });
    }
    
    public void removeMenuSelector() {
        if (this.getPlayer() == null) {
            return;
        }
        if (!CookieGadgets.getCookieGadgetsData().isGiveMenuSelectorOnJoin() || !this.getPlayer().hasPermission(EnumPermission.MENU_SELECTOR.getPermission())) {
            return;
        }
        final int menuSelectorSlot = CookieGadgets.getCookieGadgetsData().getMenuSelectorSlot();
        if (CookieGadgets.getNMSManager().hasNBTTag(this.getPlayer().getInventory().getItem(menuSelectorSlot), "Menu_Selector")) {
            this.getPlayer().getInventory().setItem(menuSelectorSlot, (ItemStack)null);
            this.getPlayer().updateInventory();
        }
        if (this.getPlayer().getInventory().contains(CookieGadgets.getCookieGadgetsData().getMenuSelector().getType())) {
            for (int i = 0; i < this.getPlayer().getInventory().getSize(); ++i) {
                if (CookieGadgets.getNMSManager().hasNBTTag(this.getPlayer().getInventory().getItem(i), "Menu_Selector")) {
                    this.getPlayer().getInventory().setItem(i, (ItemStack)null);
                    this.getPlayer().updateInventory();
                }
            }
        }
    }
    
    public void updateMenuSelectorDescription() {
        if (this.getPlayer() == null) {
            return;
        }
        if (!CookieGadgets.getCookieGadgetsData().isGiveMenuSelectorOnJoin() || !this.getPlayer().hasPermission(EnumPermission.MENU_SELECTOR.getPermission()) || !WorldUtils.isWorldEnabled(this.getPlayer().getWorld())) {
            return;
        }
        if (this.getPlayer().getInventory().contains(CookieGadgets.getCookieGadgetsData().getMenuSelector().getType())) {
            for (int i = 0; i < this.getPlayer().getInventory().getSize(); ++i) {
                try {
                    final ItemStack item = this.getPlayer().getInventory().getItem(i);
                    if (CookieGadgets.getNMSManager().hasNBTTag(item, "Menu_Selector")) {
                        final ItemMeta itemMeta = item.getItemMeta();
                        final ArrayList<String> lore = new ArrayList<String>();
                        final List lore2 = CookieGadgets.getCookieGadgetsData().getMenuSelector().getItemMeta().getLore();
                        if (lore2 != null) {
                            final Iterator<String> iterator = lore2.iterator();
                            while (iterator.hasNext()) {
                                lore.add(ChatUtil.format(iterator.next().replace("{MYSTERY_DUST}", String.valueOf(this.getMysteryDust())).replace("{MYSTERY_BOXES}", String.valueOf(this.getMysteryBoxes()))));
                            }
                        }
                        itemMeta.setLore((List)lore);
                        item.setItemMeta(itemMeta);
                        this.getPlayer().getInventory().setItem(i, item);
                        this.getPlayer().updateInventory();
                    }
                }
                catch (ConcurrentModificationException ex) {}
                catch (NullPointerException ex2) {}
            }
        }
    }
    
    public HashMap<PetType, GPet> petsData() {
        return this.petsData;
    }
    
    @Override
    public GPet getPetData(final PetType petType) {
        if (this.petsData.containsKey(petType)) {
            return this.petsData.get(petType);
        }
        return null;
    }
    
    public String getPetName(final PetType petType) {
        if (!this.isLoaded) {
            this.loadPetsData();
        }
        if (this.petsData.containsKey(petType)) {
            return ChatUtil.format(this.petsData.get(petType).getPetName().replace("{PLAYER}", this.getPlayer().getName()));
        }
        return ChatUtil.format(petType.getDefaultPetName().replace("{PLAYER}", this.getPlayer().getName()));
    }
    
    @Override
    public void setPetName(final PetType petType, String petName) {
        if (petType == null) {
            throw new NullPointerException();
        }
        if (CookieGadgets.getPetData().isPetNameBlacklistEnabled()) {
            final Iterator<String> iterator = CookieGadgets.getPetData().getPetNameBlacklist().iterator();
            while (iterator.hasNext()) {
                if (ChatUtil.stripColor(petName).contains(iterator.next())) {
                    return;
                }
            }
        }
        petName = ChatUtil.format(petName);
        if (petName.length() > 64) {
            petName = petName.substring(0, 64);
        }
        GPet gPet = this.getPetData(petType);
        if (gPet == null) {
            gPet = GPet.createNewPetData(petType, petName);
        }
        final PetRenameEvent petRenameEvent = new PetRenameEvent(this.getPlayer(), gPet, petName);
        Bukkit.getServer().getPluginManager().callEvent((Event)petRenameEvent);
        if (petRenameEvent.isCancelled()) {
            return;
        }
        gPet.setPetName(petName);
        if (this.getCurrentPet() != null && this.getCurrentPet().getType() == petType) {
            final Pet currentPet = this.getCurrentPet();
            if (currentPet != null && currentPet.getEntityPet() != null && currentPet.getEntityPet().getEntity().isValid()) {
                currentPet.getEntityPet().getEntityNMS().setCustomNameNMS(ChatUtil.format(String.valueOf(CookieGadgets.getPetData().getLevel().replace("{LEVEL}", gPet.getPetLevel().getFormattedLevel())) + "&r" + petName.replace("{PLAYER}", this.getPlayer().getName())));
            }
        }
        CookieGadgets.getDatabaseManager().getDatabaseUtils().setPetName(this, petType, gPet);
    }
    
    public void addPetDataIfAbsent(final PetType key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.getPetData(key) == null) {
            final GPet newPetData = GPet.createNewPetData(key, key.getDefaultPetName());
            this.petsData.putIfAbsent(key, newPetData);
            this.updatePetData(newPetData);
        }
    }
    
    public void loadPetsData() {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> CookieGadgets.getDatabaseManager().getDatabaseUtils().loadPetsData(this));
    }
    
    public void updatePetData(final GPet gPet) {
        CookieGadgets.getDatabaseManager().getDatabaseUtils().updatePetData(this, gPet);
    }
    
    public void sendPetForMission() {
        boolean b = false;
        for (final GPet gPet : this.petsData.values()) {
            final int gainEXPFromMission = gPet.getPetAttribute().getHappiness().getGainEXPFromMission();
            if (gPet.getPetAttribute().isHappy()) {
                if (gPet.getPetLevel().getLevel() >= 100) {
                    continue;
                }
                Bukkit.getServer().getPluginManager().callEvent((Event)new SendPetForMissionEvent(this.getPlayer(), gPet, gainEXPFromMission));
                gPet.getPetLevel().addExperience(gainEXPFromMission);
                this.updatePetData(gPet);
                final boolean levelUpRecently = gPet.getPetLevel().levelUpRecently();
                if (levelUpRecently) {
                    final Pet currentPet = this.getCurrentPet();
                    if (currentPet != null && currentPet.getType() == gPet.getType() && currentPet.getEntityPet() != null && currentPet.getEntityPet().getEntity().isValid()) {
                        currentPet.getEntityPet().getEntityNMS().setCustomNameNMS(ChatUtil.format(String.valueOf(CookieGadgets.getPetData().getLevel().replace("{LEVEL}", gPet.getPetLevel().getFormattedLevel())) + "&r" + gPet.getPetName().replace("{PLAYER}", this.getPlayer().getName())));
                    }
                }
                this.getPlayer().sendMessage(PetMessages.getLevelUpMessage(levelUpRecently ? MessageType.PET_LEVEL_UP.getFormatMessage() : MessageType.PET_EARN_EXP.getFormatMessage(), this.getPlayer(), gPet, gainEXPFromMission));
                if (CookieGadgets.getPetData().isPlaySoundWhenPetGainEXP()) {
                    CookieGadgets.getPetData().getPetGainEXPSound().playSound(this.getPlayer());
                }
                b = true;
            }
        }
        if (b) {
            this.lastPetMissionTimeMillis = System.currentTimeMillis();
        }
    }
    
    public long getLastPetMissionTimeMillis() {
        return this.lastPetMissionTimeMillis;
    }
    
    public int getPetMissionCooldownMinutes() {
        if (this.lastPetMissionTimeMillis == 0L) {
            return 0;
        }
        return (int)(CookieGadgets.getPetData().getCooldownMinutesForPetMission() - (System.currentTimeMillis() - this.lastPetMissionTimeMillis) / 60000L);
    }
    
    public boolean isSendPetMissionOnCooldown() {
        return this.lastPetMissionTimeMillis != 0L && System.currentTimeMillis() - this.lastPetMissionTimeMillis <= 60000L * CookieGadgets.getPetData().getCooldownMinutesForPetMission();
    }
    
    public PetCategoryType getSelectedProvideCareCategoryPet() {
        return this.selectedProvideCareCategoryPet;
    }
    
    public void setSelectedProvideCareCategoryPet(final PetCategoryType selectedProvideCareCategoryPet) {
        this.selectedProvideCareCategoryPet = selectedProvideCareCategoryPet;
    }
    
    public GPet getProvideCarePet() {
        return this.provideCarePet;
    }
    
    public void setProvideCarePet(final GPet provideCarePet) {
        this.provideCarePet = provideCarePet;
    }
    
    public HashMap<PetItems, Integer> petItems() {
        return this.petItems;
    }
    
    public int getPetItem(final PetItems key) {
        if (!this.petItems.containsKey(key)) {
            this.petItems.putIfAbsent(key, 0);
            return 0;
        }
        return this.petItems.get(key);
    }
    
    public void setPetItems(final PetItems key, final int n) {
        if (this.petItems.containsKey(key)) {
            this.petItems.replace(key, n);
        }
        else {
            this.petItems.putIfAbsent(key, n);
        }
        CookieGadgets.getDatabaseManager().getDatabaseUtils().setPetItems(this, key, n);
    }
    
    public void addPetItems(final PetItems petItems, final int i) {
        if (this.petItems.containsKey(petItems)) {
            this.petItems.replace(petItems, this.petItems.get(petItems) + i);
        }
        else {
            this.petItems.putIfAbsent(petItems, i);
        }
        CookieGadgets.getDatabaseManager().getDatabaseUtils().addPetItems(this, petItems, i);
    }
    
    public void removePetItems(final PetItems petItems, final int n) {
        if (this.petItems.containsKey(petItems)) {
            int i = this.petItems.get(petItems) - n;
            if (i < 0) {
                i = 0;
            }
            this.petItems.replace(petItems, i);
        }
        else {
            this.petItems.putIfAbsent(petItems, 0);
        }
        CookieGadgets.getDatabaseManager().getDatabaseUtils().removePetItems(this, petItems, n);
    }
    
    public void loadPetItems() {
        CookieGadgets.getDatabaseManager().getDatabaseUtils().loadPetItems(this);
    }
    
    public long getMysteryBoxesRewardPlayTime() {
        return this.playTime;
    }
    
    public void saveMysteryBoxesRewardPlayTime(final boolean b) {
        if (b) {
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> CookieGadgets.getDatabaseManager().getDatabaseUtils().setMysteryBoxesRewardPlayTime(this, this.playTime));
        }
        else {
            CookieGadgets.getDatabaseManager().getDatabaseUtils().setMysteryBoxesRewardPlayTime(this, this.playTime);
        }
    }
    
    public boolean isBypassCooldown() {
        if (this.isBypassCooldown != EnumCache.UNLOADED) {
            return this.isBypassCooldown == EnumCache.ENABLED;
        }
        if (CookieGadgets.getDatabaseManager().getDatabaseUtils().isCooldownBypass(this)) {
            this.isBypassCooldown = EnumCache.ENABLED;
            return true;
        }
        this.isBypassCooldown = EnumCache.DISABLED;
        return false;
    }
    
    public void setBypassCooldown(final boolean b) {
        if (b) {
            this.getPlayer().sendMessage(MessageType.ENABLED_BYPASS_COOLDOWN.getFormatMessage());
            this.isBypassCooldown = EnumCache.ENABLED;
        }
        else {
            this.getPlayer().sendMessage(MessageType.DISABLED_BYPASS_COOLDOWN.getFormatMessage());
            this.isBypassCooldown = EnumCache.DISABLED;
        }
        CookieGadgets.getDatabaseManager().getDatabaseUtils().setCooldownBypass(this, b);
    }
    
    public boolean canSeeSelfMorph() {
        if (!CookieGadgets.getCookieGadgetsData().isSelfMorphViewEnabled()) {
            return false;
        }
        if (this.canSeeSelfMorph != EnumCache.UNLOADED) {
            return this.canSeeSelfMorph == EnumCache.ENABLED;
        }
        if (CookieGadgets.getDatabaseManager().getDatabaseUtils().canSeeSelfMorph(this)) {
            this.canSeeSelfMorph = EnumCache.ENABLED;
            return true;
        }
        this.canSeeSelfMorph = EnumCache.DISABLED;
        return false;
    }
    
    public void setSeeSelfMorph(final boolean toggle) {
        if (!CookieGadgets.getCookieGadgetsData().isSelfMorphViewEnabled()) {
            return;
        }
        if ((CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() || CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) && CookieGadgets.getGDisguise().isDisguised(this.getPlayer()) && this.equippedMorph != null) {
            CookieGadgets.getGDisguise().setViewDisguiseToggled(this.getPlayer(), toggle);
        }
        if (toggle) {
            this.getPlayer().sendMessage(MessageType.ENABLED_SELF_MORPH_VIEW.getFormatMessage());
            this.canSeeSelfMorph = EnumCache.ENABLED;
        }
        else {
            this.getPlayer().sendMessage(MessageType.DISABLED_SELF_MORPH_VIEW.getFormatMessage());
            this.canSeeSelfMorph = EnumCache.DISABLED;
        }
        CookieGadgets.getDatabaseManager().getDatabaseUtils().setSeeSelfMorph(this, toggle);
    }
    
    public boolean isRenamingPet() {
        return this.renamingPet;
    }
    
    public void setRenamingPet(final boolean renamingPet) {
        this.renamingPet = renamingPet;
    }
    
    public ArrayList<MysteryBoxes> mysteryBoxes() {
        this.syncMysteryBoxesCache();
        return this.mysteryBoxes;
    }
    
    public ArrayList<MysteryBoxes> mysteryBoxesCache() {
        return this.mysteryBoxesCache;
    }
    
    public void syncMysteryBoxesCache() {
        if (this.mysteryBoxesCache.isEmpty() || !this.isLoaded) {
            return;
        }
        final ArrayList<MysteryBoxes> c = new ArrayList<MysteryBoxes>();
        for (final MysteryBoxes e : this.mysteryBoxesCache) {
            if (e.isExpirable()) {
                if (!e.isExpired()) {
                    this.mysteryBoxes.add(e);
                    CookieGadgets.getDatabaseManager().getDatabaseUtils().addMysteryBox(this.getUUID(), this.getUID(), e);
                }
            }
            else {
                this.mysteryBoxes.add(e);
                CookieGadgets.getDatabaseManager().getDatabaseUtils().addMysteryBox(this.getUUID(), this.getUID(), e);
            }
            c.add(e);
        }
        this.mysteryBoxesCache.removeAll(c);
    }
    
    public boolean isRandomMysteryVaultAnimationSelected() {
        return this.getMysteryVaultAnimation() == AnimationType.RANDOM;
    }
    
    public AnimationType getMysteryVaultAnimation() {
        if (this.mysteryVaultAnimation == null) {
            final AnimationType mysteryVaultAnimation = CookieGadgets.getDatabaseManager().getDatabaseUtils().getMysteryVaultAnimation(this);
            return this.mysteryVaultAnimation = ((mysteryVaultAnimation == null) ? CookieGadgets.getCookieGadgetsData().getDefaultMysteryVaultAnimation() : mysteryVaultAnimation);
        }
        return this.mysteryVaultAnimation;
    }
    
    public AnimationType getRandomMysteryVaultAnimation() {
        final ArrayList<AnimationType> list = new ArrayList<AnimationType>();
        for (final AnimationType e : AnimationType.enabled()) {
            if (!PermissionUtils.noPermission(this.getPlayer(), e.getPermission(), EnumPermission.ALL_MYSTERY_VAULT_ANIMATIONS.getPermission(), false)) {
                list.add(e);
            }
        }
        if (list.isEmpty()) {
            return AnimationType.NORMAL;
        }
        return list.get(CookieGadgets.random().nextInt(list.size()));
    }
    
    public void setMysteryVaultAnimation(final AnimationType mysteryVaultAnimation) {
        if (mysteryVaultAnimation == null) {
            throw new NullPointerException("The animation can not be null.");
        }
        CookieGadgets.getDatabaseManager().getDatabaseUtils().setMysteryVaultAnimation(this, mysteryVaultAnimation);
        this.mysteryVaultAnimation = mysteryVaultAnimation;
    }
    
    public void setAnimation(final Animation animation) {
        this.animation = animation;
    }
    
    public void stopAnimation() {
        if (this.animation == null) {
            return;
        }
        this.animation.onClear();
        this.animation.clear();
        this.animation = null;
    }
    
    @Override
    public int getMysteryBoxes() {
        return this.mysteryBoxes.size();
    }
    
    @Override
    public void giveMysteryBoxes(final Long n, final boolean b, final String s, final int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("The amount must be possitive number!");
        }
        this.syncMysteryBoxesCache();
        int i = 0;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            while (i <= n2) {
                MysteryBoxesManager.giveMysteryBoxes(this, MysteryBoxType.valueOfByName("Normal Mystery Box #" + (CookieGadgets.random().nextInt(5) + 1)), b, s, n);
                ++i;
            }
            this.updateMenuSelectorDescription();
        });
    }
    
    @Override
    public void giveMysteryBoxes(final MysteryBoxType mysteryBoxType, final Long n, final boolean b, final String s, final int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("The amount must be possitive number!");
        }
        this.syncMysteryBoxesCache();
        int i = 0;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            while (i <= n2) {
                MysteryBoxesManager.giveMysteryBoxes(this, mysteryBoxType, b, s, n);
                ++i;
            }
            this.updateMenuSelectorDescription();
        });
    }
    
    @Override
    public void giveMysteryBoxes(final Long n, final boolean b, final String s, final int n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("The amount must be possitive number!");
        }
        this.syncMysteryBoxesCache();
        int i = 0;
        final float n9;
        int j;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            while (i <= n2) {
                MathUtil.randomFloat(0.0f, n4 + n5 + n6 + n7 + n8);
                if (n9 <= n4) {
                    j = 1;
                }
                else if (n9 > n4 && n9 <= n4 + n5) {
                    j = 2;
                }
                else if (n9 > n4 + n5 && n9 <= n4 + n5 + n6) {
                    j = 3;
                }
                else if (n9 > n4 + n5 + n6 && n9 <= n4 + n5 + n6 + n7) {
                    j = 4;
                }
                else if (n9 > n4 + n5 + n6 + n7 && n9 <= n4 + n5 + n6 + n7 + n8) {
                    j = 5;
                }
                else {
                    j = CookieGadgets.random().nextInt(5) + 1;
                }
                MysteryBoxesManager.giveMysteryBoxes(this, MysteryBoxType.valueOfByName("Normal Mystery Box #" + j), b, s, n);
                ++i;
            }
            this.updateMenuSelectorDescription();
        });
    }
    
    public void removeMysteryBox(final MysteryBoxes o) {
        this.mysteryBoxes.remove(o);
        CookieGadgets.getDatabaseManager().getDatabaseUtils().removeMysteryBox(this.getUID(), o);
        this.updateMenuSelectorDescription();
    }
    
    private boolean loadMysteryBoxes() {
        if (!CookieGadgets.getCookieGadgetsData().isMysteryBoxesEnabled() || !this.getPlayer().isOnline()) {
            return this.isLoaded = true;
        }
        try {
            final Iterator<MysteryBoxes> iterator;
            MysteryBoxes mysteryBoxes;
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                CookieGadgets.getDatabaseManager().getDatabaseUtils().syncMysteryBoxes(this);
                this.mysteryBoxesCache.iterator();
                while (iterator.hasNext()) {
                    mysteryBoxes = iterator.next();
                    if (mysteryBoxes.isExpirable()) {
                        if (!mysteryBoxes.isExpired()) {
                            this.mysteryBoxes.add(mysteryBoxes);
                            CookieGadgets.getDatabaseManager().getDatabaseUtils().addMysteryBox(this.getUUID(), this.getUID(), mysteryBoxes);
                        }
                        else {
                            continue;
                        }
                    }
                    else {
                        this.mysteryBoxes.add(mysteryBoxes);
                        CookieGadgets.getDatabaseManager().getDatabaseUtils().addMysteryBox(this.getUUID(), this.getUID(), mysteryBoxes);
                    }
                }
                this.mysteryBoxesCache.clear();
                this.isLoaded = true;
                return;
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.isLoaded = true;
            return false;
        }
        return true;
    }
    
    public int getGiftPacks() {
        if (this.giftPacks == -1) {
            return this.giftPacks = CookieGadgets.getDatabaseManager().getDatabaseUtils().getMysteryGiftPacks(this);
        }
        return this.giftPacks;
    }
    
    public void addGiftPacks(final int n) {
        if (this.giftPacks == -1) {
            this.getGiftPacks();
        }
        CookieGadgets.getDatabaseManager().getDatabaseUtils().addMysteryGiftPacks(this, n);
        this.giftPacks += n;
    }
    
    public void removeGiftPacks(final int n) {
        if (this.giftPacks == -1) {
            this.getGiftPacks();
        }
        int giftPacks = this.giftPacks - n;
        if (giftPacks < 0) {
            this.giftPacks = 0;
            giftPacks = 0;
        }
        CookieGadgets.getDatabaseManager().getDatabaseUtils().removeMysteryGiftPacks(this, n);
        this.giftPacks = giftPacks;
    }
    
    public int getGiftSent() {
        if (this.giftSent == -1) {
            return this.giftSent = CookieGadgets.getDatabaseManager().getDatabaseUtils().getMysteryGiftSent(this);
        }
        return this.giftSent;
    }
    
    public void addGiftSent(final int n) {
        CookieGadgets.getDatabaseManager().getDatabaseUtils().addMysteryGiftSent(this, n);
        this.giftSent = this.getGiftSent() + n;
    }
    
    public int getGiftReceived() {
        if (this.giftReceived == -1) {
            return this.giftReceived = CookieGadgets.getDatabaseManager().getDatabaseUtils().getMysteryGiftReceived(this);
        }
        return this.giftReceived;
    }
    
    public void addGiftReceived(final int n) {
        CookieGadgets.getDatabaseManager().getDatabaseUtils().addMysteryGiftReceived(this, n);
        this.giftReceived = this.getGiftReceived() + n;
    }
    
    public String[] getRecentLootsFound() {
        if (this.recentLootsFound == null) {
            this.recentLootsFound = CookieGadgets.getDatabaseManager().getDatabaseUtils().getRecentLootsFound(this);
        }
        return (String[])((this.recentLootsFound == null) ? null : this.recentLootsFound.split("\\, ", 5));
    }
    
    public void setRecentLootsFound(final String s) {
        this.recentLootsFound = s.replace("§", "&");
    }
    
    public boolean isRefreshTaskActivated() {
        return this.isRefreshTaskActivated;
    }
    
    public void setRefreshTaskActivated(final boolean isRefreshTaskActivated) {
        this.isRefreshTaskActivated = isRefreshTaskActivated;
    }
    
    public Player getSelectedSendGiftPlayer() {
        return this.selectedSendGiftPlayer;
    }
    
    public void setSelectedSendGiftPlayer(final Player selectedSendGiftPlayer) {
        this.selectedSendGiftPlayer = selectedSendGiftPlayer;
    }
    
    public MysteryVault getSelectedMysteryVault() {
        return this.selectedMysteryVault;
    }
    
    public void setSelectedMysteryVault(final MysteryVault selectedMysteryVault) {
        this.selectedMysteryVault = selectedMysteryVault;
    }
    
    public MysteryBoxes getSelectedMysteryBox() {
        return this.selectedMysteryBox;
    }
    
    public void setSelectedMysteryBox(final MysteryBoxes selectedMysteryBox) {
        this.selectedMysteryBox = selectedMysteryBox;
    }
    
    public boolean isOpeningMysteryBox() {
        return this.isOpeningMysteryBox;
    }
    
    public void setOpeningMysteryBox(final boolean isOpeningMysteryBox) {
        this.isOpeningMysteryBox = isOpeningMysteryBox;
    }
    
    public ConfirmOpenMultipleBoxesMenu.OpenMultipleBoxesType getOpenMultipleBoxesType() {
        return this.openType;
    }
    
    public void setOpenMultipleBoxesType(final ConfirmOpenMultipleBoxesMenu.OpenMultipleBoxesType openType) {
        this.openType = openType;
    }
    
    public HatType getEquippedHat() {
        return this.equippedHat;
    }
    
    public void setEquippedHat(final HatType equippedHat) {
        this.equippedHat = equippedHat;
    }
    
    public AnimatedHatType getEquippedAnimatedHat() {
        return this.equippedAnimatedHat;
    }
    
    public void setEquippedAnimatedHat(final AnimatedHatType equippedAnimatedHat) {
        this.equippedAnimatedHat = equippedAnimatedHat;
    }
    
    public ParticleType getEquippedParticle() {
        return this.equippedParticle;
    }
    
    public void setEquippedParticle(final ParticleType equippedParticle) {
        this.equippedParticle = equippedParticle;
    }
    
    public SuitType getSelectedCategorySuit() {
        return this.selectedCategorySuit;
    }
    
    public void setSelectedCategorySuit(final SuitType selectedCategorySuit) {
        this.selectedCategorySuit = selectedCategorySuit;
    }
    
    public Map<EnumArmorType, SuitEquipmentType> getEquippedSuitEquipment() {
        return this.equippedSuitEquipment;
    }
    
    public GadgetCategoryType getSelectedCategoryGadget() {
        return this.selectedCategoryGadget;
    }
    
    public void setSelectedCategoryGadget(final GadgetCategoryType selectedCategoryGadget) {
        this.selectedCategoryGadget = selectedCategoryGadget;
    }
    
    public GadgetType getEquippedGadget() {
        return this.equippedGadget;
    }
    
    public void setEquippedGadget(final GadgetType equippedGadget) {
        this.equippedGadget = equippedGadget;
    }
    
    public PetCategoryType getSelectedCategoryPet() {
        return this.selectedCategoryPet;
    }
    
    public void setSelectedCategoryPet(final PetCategoryType selectedCategoryPet) {
        this.selectedCategoryPet = selectedCategoryPet;
    }
    
    public PetType getEquippedPet() {
        return this.equippedPet;
    }
    
    public void setEquippedPet(final PetType equippedPet) {
        this.equippedPet = equippedPet;
    }
    
    public MiniatureType getEquippedMiniature() {
        return this.equippedMiniature;
    }
    
    public void setEquippedMiniature(final MiniatureType equippedMiniature) {
        this.equippedMiniature = equippedMiniature;
    }
    
    public MorphType getEquippedMorph() {
        return this.equippedMorph;
    }
    
    public void setEquippedMorph(final MorphType equippedMorph) {
        this.equippedMorph = equippedMorph;
    }
    
    public BannerType getEquippedBanner() {
        return this.equippedBanner;
    }
    
    public void setEquippedBanner(final BannerType equippedBanner) {
        this.equippedBanner = equippedBanner;
    }
    
    public EmoteType getEquippedEmote() {
        return this.equippedEmote;
    }
    
    public void setEquippedEmote(final EmoteType equippedEmote) {
        this.equippedEmote = equippedEmote;
    }
    
    public CloakType getEquippedCloak() {
        return this.equippedCloak;
    }
    
    public void setEquippedCloak(final CloakType equippedCloak) {
        this.equippedCloak = equippedCloak;
    }
    
    public AnimatedHat getCurrentAnimatedHat() {
        return this.animatedHat;
    }
    
    public AnimatedHat setCurrentAnimatedHat(final AnimatedHat animatedHat) {
        return this.animatedHat = animatedHat;
    }
    
    public void removeAnimatedHat() {
        if (this.animatedHat == null) {
            return;
        }
        this.animatedHat.clear();
        this.animatedHat = null;
    }
    
    public Suit getCurrentSuit() {
        return this.suit;
    }
    
    public Suit setCurrentSuit(final Suit suit) {
        return this.suit = suit;
    }
    
    public void removeSuit() {
        if (this.suit == null) {
            return;
        }
        this.suit.clear();
        this.suit = null;
    }
    
    public Gadget getCurrentGadget() {
        return this.gadget;
    }
    
    public Gadget setCurrentGadget(final Gadget gadget) {
        return this.gadget = gadget;
    }
    
    public void removeGadget() {
        if (this.gadget == null) {
            return;
        }
        this.gadget.clear();
        this.gadget = null;
    }
    
    public Pet getCurrentPet() {
        return this.pet;
    }
    
    public Pet setCurrentPet(final Pet pet) {
        return this.pet = pet;
    }
    
    public void removePet() {
        if (this.pet == null) {
            return;
        }
        this.pet.clear();
        this.pet = null;
    }
    
    public Miniature getCurrentMiniature() {
        return this.miniature;
    }
    
    public Miniature setCurrentMiniature(final Miniature miniature) {
        return this.miniature = miniature;
    }
    
    public void removeMiniature() {
        if (this.miniature == null) {
            return;
        }
        this.miniature.clear();
        this.miniature = null;
    }
    
    public Morph getCurrentMorph() {
        return this.morph;
    }
    
    public Morph setCurrentMorph(final Morph morph) {
        return this.morph = morph;
    }
    
    public void removeMorph() {
        if (this.morph == null) {
            return;
        }
        this.morph.clear();
        this.morph = null;
    }
    
    public Emote getCurrentEmote() {
        return this.emote;
    }
    
    public Emote setCurrentEmote(final Emote emote) {
        return this.emote = emote;
    }
    
    public void removeEmote() {
        if (this.emote == null) {
            return;
        }
        this.emote.clear();
        this.emote = null;
    }
    
    public Cloak getCurrentCloak() {
        return this.cloak;
    }
    
    public Cloak setCurrentCloak(final Cloak cloak) {
        return this.cloak = cloak;
    }
    
    public void removeCloak() {
        if (this.cloak == null) {
            return;
        }
        this.cloak.clear();
        this.cloak = null;
    }
    
    public HashMap<SuitType, Long> suitCooldown() {
        return this.suitCooldown;
    }
    
    public HashMap<GadgetType, Long> gadgetCooldown() {
        return this.gadgetCooldown;
    }
    
    public HashMap<MorphType, Long> morphCooldown() {
        return this.morphCooldown;
    }
    
    public HashMap<EmoteType, Long> emoteCooldown() {
        return this.emoteCooldown;
    }
    
    public void sendCooldownBar(final String replacement, final Long n, final int n2) {
        if (this.getPlayer() == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        final int n3 = (int)((n - System.currentTimeMillis()) / 1000L / (double)n2 * CookieGadgets.getCookieGadgetsData().getTheAmountOfCooldownBlocks());
        CookieGadgets.getCookieGadgetsData().getRemainCooldownBlock();
        for (int i = 0; i < CookieGadgets.getCookieGadgetsData().getTheAmountOfCooldownBlocks(); ++i) {
            String s = CookieGadgets.getCookieGadgetsData().getRemainCooldownBlock();
            if (i < CookieGadgets.getCookieGadgetsData().getTheAmountOfCooldownBlocks() - n3) {
                s = CookieGadgets.getCookieGadgetsData().getRetainCooldownBlock();
            }
            sb.append(ChatUtil.format(s));
        }
        final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator('.');
        symbols.setPatternSeparator('.');
        this.sendActionbarMessage(CookieGadgets.getCookieGadgetsData().getCooldownMessage().replace("{NAME}", replacement).replace("{COOLDOWN_BLOCK}", sb.toString()).replace("{TIME_LEFT}", new DecimalFormat("0", symbols).format((n - System.currentTimeMillis()) / 1000L + 1L)));
    }
    
    public void resetCooldownBar() {
        this.sendActionbarMessage("");
    }
    
    public void sendActionbarMessage(final String message) {
        CookieGadgets.getNMSManager().sendActionbarMessage(this.getPlayer(), message);
    }
    
    public boolean isBlockDamageDisabled() {
        return this.disableBlockDamage;
    }
    
    public void enableBlockDamage() {
        final boolean disableBlockDamage = true;
        this.disableBlockDamage = disableBlockDamage;
        if (disableBlockDamage) {
            this.disableBlockDamage = false;
        }
    }
    
    public void disableBlockDamage() {
        this.disableBlockDamage = true;
    }
    
    public boolean isFallDamageDisabled() {
        return this.disableFallDamage;
    }
    
    public void enableFallDamage() {
        final boolean disableFallDamage = true;
        this.disableFallDamage = disableFallDamage;
        if (disableFallDamage) {
            this.disableFallDamage = false;
        }
    }
    
    public void disableFallDamage() {
        this.disableFallDamage = true;
    }
    
    public boolean isFireDamageDisabled() {
        return this.disableFireDamage;
    }
    
    public void enableFireDamage() {
        final boolean disableFireDamage = true;
        this.disableFireDamage = disableFireDamage;
        if (disableFireDamage) {
            this.disableFireDamage = false;
        }
    }
    
    public void disableFireDamage() {
        this.disableFireDamage = true;
    }
    
    public int getCurrentMenuPage() {
        return this.currentMenuPage;
    }
    
    public void setCurrentMenuPage(final int currentMenuPage) {
        this.currentMenuPage = currentMenuPage;
    }
    
    public ItemCostDiscount getItemCostDiscount() {
        return this.itemCostDiscount;
    }
    
    public void setItemCostDiscount(final ItemCostDiscount itemCostDiscount) {
        this.itemCostDiscount = itemCostDiscount;
    }
    
    public PurchaseData purchaseData() {
        if (this.purchaseData == null) {
            return this.purchaseData = new PurchaseData();
        }
        return this.purchaseData;
    }
    
    public boolean resetPurchaseData() {
        if (this.purchaseData == null) {
            return true;
        }
        this.purchaseData.reset();
        return true;
    }
    
    public int getUnlockedTotalCosmeticItems() {
        return 0 + this.getUnlockedHats() + this.getUnlockedAnimatedHats() + this.getUnlockedParticles() + this.getUnlockedSuits() + this.getUnlockedGadgets() + this.getUnlockedPets() + this.getUnlockedMiniatures() + this.getUnlockedMorphs() + this.getUnlockedBanners() + this.getUnlockedEmotes() + this.getUnlockedCloaks();
    }
    
    public int getUnlockedHats() {
        if (this.getPlayer().hasPermission(EnumPermission.ALL.getPermission()) || this.getPlayer().hasPermission(EnumPermission.HATS.getPermission())) {
            return HatType.enabled().size();
        }
        if (this.unlockedHats > 0) {
            if (CookieGadgets.getInstance().isEnabled()) {
                final Iterator<HatType> iterator;
                HatType hatType;
                int unlockedHats = 0;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.getPlayer() == null) {
                        return;
                    }
                    else {
                        HatType.enabled().iterator();
                        while (iterator.hasNext()) {
                            hatType = iterator.next();
                            if (this.getPlayer() == null) {
                                break;
                            }
                            else if (this.hasPermission(hatType.getPermission()) || this.getPlayer().hasPermission(hatType.getPermission())) {
                                ++unlockedHats;
                            }
                            else {
                                continue;
                            }
                        }
                        this.unlockedHats = unlockedHats;
                        return;
                    }
                });
            }
            return this.unlockedHats;
        }
        for (final HatType hatType2 : HatType.enabled()) {
            if (this.hasPermission(hatType2.getPermission()) || this.getPlayer().hasPermission(hatType2.getPermission())) {
                ++this.unlockedHats;
            }
        }
        return this.unlockedHats;
    }
    
    public int getUnlockedAnimatedHats() {
        if (this.getPlayer().hasPermission(EnumPermission.ALL.getPermission()) || this.getPlayer().hasPermission(EnumPermission.ANIMATED_HATS.getPermission())) {
            return AnimatedHatType.enabled().size();
        }
        if (this.unlockedAnimatedHats > 0) {
            if (CookieGadgets.getInstance().isEnabled()) {
                final Iterator<AnimatedHatType> iterator;
                AnimatedHatType animatedHatType;
                int unlockedAnimatedHats = 0;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.getPlayer() == null) {
                        return;
                    }
                    else {
                        AnimatedHatType.enabled().iterator();
                        while (iterator.hasNext()) {
                            animatedHatType = iterator.next();
                            if (this.getPlayer() == null) {
                                break;
                            }
                            else if (this.hasPermission(animatedHatType.getPermission()) || this.getPlayer().hasPermission(animatedHatType.getPermission())) {
                                ++unlockedAnimatedHats;
                            }
                            else {
                                continue;
                            }
                        }
                        this.unlockedAnimatedHats = unlockedAnimatedHats;
                        return;
                    }
                });
            }
            return this.unlockedAnimatedHats;
        }
        for (final AnimatedHatType animatedHatType2 : AnimatedHatType.enabled()) {
            if (this.hasPermission(animatedHatType2.getPermission()) || this.getPlayer().hasPermission(animatedHatType2.getPermission())) {
                ++this.unlockedAnimatedHats;
            }
        }
        return this.unlockedAnimatedHats;
    }
    
    public int getUnlockedParticles() {
        if (this.getPlayer().hasPermission(EnumPermission.ALL.getPermission()) || this.getPlayer().hasPermission(EnumPermission.PARTICLES.getPermission())) {
            return ParticleType.enabled().size();
        }
        if (this.unlockedParticles > 0) {
            if (CookieGadgets.getInstance().isEnabled()) {
                final Iterator<ParticleType> iterator;
                ParticleType particleType;
                int unlockedParticles = 0;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.getPlayer() == null) {
                        return;
                    }
                    else {
                        ParticleType.enabled().iterator();
                        while (iterator.hasNext()) {
                            particleType = iterator.next();
                            if (this.getPlayer() == null) {
                                break;
                            }
                            else if (this.hasPermission(particleType.getPermission()) || this.getPlayer().hasPermission(particleType.getPermission())) {
                                ++unlockedParticles;
                            }
                            else {
                                continue;
                            }
                        }
                        this.unlockedParticles = unlockedParticles;
                        return;
                    }
                });
            }
            return this.unlockedParticles;
        }
        for (final ParticleType particleType2 : ParticleType.enabled()) {
            if (this.hasPermission(particleType2.getPermission()) || this.getPlayer().hasPermission(particleType2.getPermission())) {
                ++this.unlockedParticles;
            }
        }
        return this.unlockedParticles;
    }
    
    public int getUnlockedSuits() {
        if (this.getPlayer().hasPermission(EnumPermission.ALL.getPermission()) || this.getPlayer().hasPermission(EnumPermission.SUITS.getPermission())) {
            return SuitType.enabled().size() * 4;
        }
        if (this.unlockedSuits > 0) {
            if (CookieGadgets.getInstance().isEnabled()) {
                final Iterator<SuitEquipmentType> iterator;
                SuitEquipmentType suitEquipmentType;
                int unlockedSuits = 0;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.getPlayer() == null) {
                        return;
                    }
                    else {
                        SuitEquipmentType.values().iterator();
                        while (iterator.hasNext()) {
                            suitEquipmentType = iterator.next();
                            if (this.getPlayer() != null) {
                                if (!suitEquipmentType.getCategory().isEnabled()) {
                                    break;
                                }
                                else if (this.hasPermission(suitEquipmentType.getPermission()) || this.getPlayer().hasPermission(suitEquipmentType.getPermission())) {
                                    ++unlockedSuits;
                                }
                                else {
                                    continue;
                                }
                            }
                            else {
                                break;
                            }
                        }
                        this.unlockedSuits = unlockedSuits;
                        return;
                    }
                });
            }
            return this.unlockedSuits;
        }
        for (final SuitEquipmentType suitEquipmentType2 : SuitEquipmentType.values()) {
            if (!suitEquipmentType2.getCategory().isEnabled()) {
                break;
            }
            if (!this.hasPermission(suitEquipmentType2.getPermission()) && !this.getPlayer().hasPermission(suitEquipmentType2.getPermission())) {
                continue;
            }
            ++this.unlockedSuits;
        }
        return this.unlockedSuits;
    }
    
    public int getUnlockedGadgets() {
        if (this.getPlayer().hasPermission(EnumPermission.ALL.getPermission()) || this.getPlayer().hasPermission(EnumPermission.GADGETS.getPermission())) {
            return GadgetType.enabled().size();
        }
        if (this.unlockedGadgets > 0) {
            if (CookieGadgets.getInstance().isEnabled()) {
                final Iterator<GadgetType> iterator;
                GadgetType gadgetType;
                int unlockedGadgets = 0;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.getPlayer() == null) {
                        return;
                    }
                    else {
                        GadgetType.enabled().iterator();
                        while (iterator.hasNext()) {
                            gadgetType = iterator.next();
                            if (this.getPlayer() == null) {
                                break;
                            }
                            else if (this.hasPermission(gadgetType.getPermission()) || this.getPlayer().hasPermission(gadgetType.getPermission())) {
                                ++unlockedGadgets;
                            }
                            else {
                                continue;
                            }
                        }
                        this.unlockedGadgets = unlockedGadgets;
                        return;
                    }
                });
            }
            return this.unlockedGadgets;
        }
        for (final GadgetType gadgetType2 : GadgetType.enabled()) {
            if (this.hasPermission(gadgetType2.getPermission()) || this.getPlayer().hasPermission(gadgetType2.getPermission())) {
                ++this.unlockedGadgets;
            }
        }
        return this.unlockedGadgets;
    }
    
    public int getUnlockedPets() {
        if (this.getPlayer().hasPermission(EnumPermission.ALL.getPermission()) || this.getPlayer().hasPermission(EnumPermission.PETS.getPermission())) {
            return PetType.enabled().size();
        }
        if (this.unlockedPets > 0) {
            if (CookieGadgets.getInstance().isEnabled()) {
                final Iterator<PetType> iterator;
                PetType petType;
                int unlockedPets = 0;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.getPlayer() == null) {
                        return;
                    }
                    else {
                        PetType.enabled().iterator();
                        while (iterator.hasNext()) {
                            petType = iterator.next();
                            if (this.getPlayer() == null) {
                                break;
                            }
                            else if (this.hasPermission(petType.getPermission()) || this.getPlayer().hasPermission(petType.getPermission())) {
                                ++unlockedPets;
                            }
                            else {
                                continue;
                            }
                        }
                        this.unlockedPets = unlockedPets;
                        return;
                    }
                });
            }
            return this.unlockedPets;
        }
        for (final PetType petType2 : PetType.enabled()) {
            if (this.hasPermission(petType2.getPermission()) || this.getPlayer().hasPermission(petType2.getPermission())) {
                ++this.unlockedPets;
            }
        }
        return this.unlockedPets;
    }
    
    public int getUnlockedMiniatures() {
        if (this.getPlayer().hasPermission(EnumPermission.ALL.getPermission()) || this.getPlayer().hasPermission(EnumPermission.MINIATURES.getPermission())) {
            return MiniatureType.enabled().size();
        }
        if (this.unlockedMiniatures > 0) {
            if (CookieGadgets.getInstance().isEnabled()) {
                final Iterator<MiniatureType> iterator;
                MiniatureType miniatureType;
                int unlockedMiniatures = 0;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.getPlayer() == null) {
                        return;
                    }
                    else {
                        MiniatureType.enabled().iterator();
                        while (iterator.hasNext()) {
                            miniatureType = iterator.next();
                            if (this.getPlayer() == null) {
                                break;
                            }
                            else if (this.hasPermission(miniatureType.getPermission()) || this.getPlayer().hasPermission(miniatureType.getPermission())) {
                                ++unlockedMiniatures;
                            }
                            else {
                                continue;
                            }
                        }
                        this.unlockedMiniatures = unlockedMiniatures;
                        return;
                    }
                });
            }
            return this.unlockedMiniatures;
        }
        for (final MiniatureType miniatureType2 : MiniatureType.enabled()) {
            if (this.hasPermission(miniatureType2.getPermission()) || this.getPlayer().hasPermission(miniatureType2.getPermission())) {
                ++this.unlockedMiniatures;
            }
        }
        return this.unlockedMiniatures;
    }
    
    public int getUnlockedMorphs() {
        if (this.getPlayer().hasPermission(EnumPermission.ALL.getPermission()) || this.getPlayer().hasPermission(EnumPermission.MORPHS.getPermission())) {
            return MorphType.enabled().size();
        }
        if (this.unlockedMorphs > 0) {
            if (CookieGadgets.getInstance().isEnabled()) {
                final Iterator<MorphType> iterator;
                MorphType morphType;
                int unlockedMorphs = 0;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.getPlayer() == null) {
                        return;
                    }
                    else {
                        MorphType.enabled().iterator();
                        while (iterator.hasNext()) {
                            morphType = iterator.next();
                            if (this.getPlayer() == null) {
                                break;
                            }
                            else if (this.hasPermission(morphType.getPermission()) || this.getPlayer().hasPermission(morphType.getPermission())) {
                                ++unlockedMorphs;
                            }
                            else {
                                continue;
                            }
                        }
                        this.unlockedMorphs = unlockedMorphs;
                        return;
                    }
                });
            }
            return this.unlockedMorphs;
        }
        for (final MorphType morphType2 : MorphType.enabled()) {
            if (this.hasPermission(morphType2.getPermission()) || this.getPlayer().hasPermission(morphType2.getPermission())) {
                ++this.unlockedMorphs;
            }
        }
        return this.unlockedMorphs;
    }
    
    public int getUnlockedBanners() {
        if (this.getPlayer().hasPermission(EnumPermission.ALL.getPermission()) || this.getPlayer().hasPermission(EnumPermission.BANNERS.getPermission())) {
            return BannerType.enabled().size();
        }
        if (this.unlockedBanners > 0) {
            if (CookieGadgets.getInstance().isEnabled()) {
                final Iterator<BannerType> iterator;
                BannerType bannerType;
                int unlockedBanners = 0;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.getPlayer() == null) {
                        return;
                    }
                    else {
                        BannerType.enabled().iterator();
                        while (iterator.hasNext()) {
                            bannerType = iterator.next();
                            if (this.getPlayer() == null) {
                                break;
                            }
                            else if (this.hasPermission(bannerType.getPermission()) || this.getPlayer().hasPermission(bannerType.getPermission())) {
                                ++unlockedBanners;
                            }
                            else {
                                continue;
                            }
                        }
                        this.unlockedBanners = unlockedBanners;
                        return;
                    }
                });
            }
            return this.unlockedBanners;
        }
        for (final BannerType bannerType2 : BannerType.enabled()) {
            if (this.hasPermission(bannerType2.getPermission()) || this.getPlayer().hasPermission(bannerType2.getPermission())) {
                ++this.unlockedBanners;
            }
        }
        return this.unlockedBanners;
    }
    
    public int getUnlockedEmotes() {
        if (this.getPlayer().hasPermission(EnumPermission.ALL.getPermission()) || this.getPlayer().hasPermission(EnumPermission.EMOTES.getPermission())) {
            return EmoteType.enabled().size();
        }
        if (this.unlockedEmotes > 0) {
            if (CookieGadgets.getInstance().isEnabled()) {
                final Iterator<EmoteType> iterator;
                EmoteType emoteType;
                int unlockedEmotes = 0;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.getPlayer() == null) {
                        return;
                    }
                    else {
                        EmoteType.enabled().iterator();
                        while (iterator.hasNext()) {
                            emoteType = iterator.next();
                            if (this.getPlayer() == null) {
                                break;
                            }
                            else if (this.hasPermission(emoteType.getPermission()) || this.getPlayer().hasPermission(emoteType.getPermission())) {
                                ++unlockedEmotes;
                            }
                            else {
                                continue;
                            }
                        }
                        this.unlockedEmotes = unlockedEmotes;
                        return;
                    }
                });
            }
            return this.unlockedEmotes;
        }
        for (final EmoteType emoteType2 : EmoteType.enabled()) {
            if (this.hasPermission(emoteType2.getPermission()) || this.getPlayer().hasPermission(emoteType2.getPermission())) {
                ++this.unlockedEmotes;
            }
        }
        return this.unlockedEmotes;
    }
    
    public int getUnlockedCloaks() {
        if (this.getPlayer().hasPermission(EnumPermission.ALL.getPermission()) || this.getPlayer().hasPermission(EnumPermission.CLOAKS.getPermission())) {
            return CloakType.enabled().size();
        }
        if (this.unlockedCloaks > 0) {
            if (CookieGadgets.getInstance().isEnabled()) {
                final Iterator<CloakType> iterator;
                CloakType cloakType;
                int unlockedCloaks = 0;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.getPlayer() == null) {
                        return;
                    }
                    else {
                        CloakType.enabled().iterator();
                        while (iterator.hasNext()) {
                            cloakType = iterator.next();
                            if (this.getPlayer() == null) {
                                break;
                            }
                            else if (this.hasPermission(cloakType.getPermission()) || this.getPlayer().hasPermission(cloakType.getPermission())) {
                                ++unlockedCloaks;
                            }
                            else {
                                continue;
                            }
                        }
                        this.unlockedCloaks = unlockedCloaks;
                        return;
                    }
                });
            }
            return this.unlockedCloaks;
        }
        for (final CloakType cloakType2 : CloakType.enabled()) {
            if (this.hasPermission(cloakType2.getPermission()) || this.getPlayer().hasPermission(cloakType2.getPermission())) {
                ++this.unlockedCloaks;
            }
        }
        return this.unlockedCloaks;
    }
    
    public void startMysteryBoxReward() {
        if (this.mysteryBoxesRewardTask != null) {
            return;
        }
        this.mysteryBoxesRewardTask = Bukkit.getServer().getScheduler().runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            if (this.getPlayer() == null || !this.getPlayer().isOnline() || !CookieGadgets.getCookieGadgetsData().isMysteryBoxesRewardEnabled()) {
                this.stopMysteryBoxReward(false);
            }
            else if (this.mysteryBoxesRewardTask == null) {
                this.stopMysteryBoxReward(false);
            }
            else if (!(!WorldUtils.isWorldEnabledForMysteryBoxReward(this.getPlayer().getWorld()))) {
                if (!CookieGadgets.getCookieGadgetsData().isMysteryBoxesRewardAllowAFK()) {
                    if (!this.isMoving() || this.isMovingAroundLastLocation()) {
                        ++this.afkTime;
                    }
                    else {
                        this.afkTime = 0;
                        this.setMoving(false);
                    }
                }
                if (this.afkTime <= 10) {
                    ++this.playTime;
                    if (this.playTime >= MysteryBoxesReward.getRewardTimePeriod()) {
                        MysteryBoxesReward.giveRewardIfHaveChance(this.getPlayer());
                        this.playTime -= MysteryBoxesReward.getRewardTimePeriod();
                    }
                }
            }
        }, 0L, 20L);
    }
    
    public void stopMysteryBoxReward(final boolean b) {
        if (this.mysteryBoxesRewardTask == null) {
            return;
        }
        try {
            if (this.mysteryBoxesRewardTask.isCancelled()) {
                return;
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        this.saveMysteryBoxesRewardPlayTime(b);
        this.mysteryBoxesRewardTask.cancel();
        this.mysteryBoxesRewardTask = null;
    }
    
    public void loadDatabaseData(final int uid, final int mysteryDust, final int giftPacks, final int giftSent, final int giftReceived, final boolean b, final boolean b2, final AnimationType mysteryVaultAnimation, final long lastPetMissionTimeMillis, final int n, final String recentLootsFound) {
        this.UID = uid;
        if (CookieGadgets.getCookieGadgetsData().getMysteryDustStorage() == GStorage.DEFAULT) {
            this.mysteryDust = mysteryDust;
        }
        this.giftPacks = giftPacks;
        this.giftSent = giftSent;
        this.giftReceived = giftReceived;
        this.canSeeSelfMorph = (b ? EnumCache.ENABLED : EnumCache.DISABLED);
        this.isBypassCooldown = (b2 ? EnumCache.ENABLED : EnumCache.DISABLED);
        this.mysteryVaultAnimation = mysteryVaultAnimation;
        this.lastPetMissionTimeMillis = lastPetMissionTimeMillis;
        this.playTime += n;
        this.recentLootsFound = recentLootsFound;
    }
    
    public void savePlayerStats(final boolean b) {
        if (b) {
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> CookieGadgets.getDatabaseManager().getDatabaseUtils().savePlayerStats(this.getUID(), this.lastPetMissionTimeMillis, this.recentLootsFound));
        }
        else {
            CookieGadgets.getDatabaseManager().getDatabaseUtils().savePlayerStats(this.getUID(), this.lastPetMissionTimeMillis, this.recentLootsFound);
        }
    }
    
    public void resetPlayerData() {
        this.petsData.clear();
        this.petItems.clear();
        this.mysteryBoxes.clear();
        this.unlockedCosmeticItems.clear();
        this.unlockedCosmeticItemsPermissions.clear();
    }
    
    public boolean isMoving() {
        return this.isMoving;
    }
    
    public void setMoving(final boolean isMoving) {
        this.isMoving = isMoving;
    }
    
    public boolean isMovingAroundLastLocation() {
        final Location location = this.getPlayer().getLocation();
        final Location lastLocation = this.getLastLocation();
        return location != null && lastLocation != null && lastLocation.getWorld() == location.getWorld() && lastLocation.distance(location) <= 3.0;
    }
    
    public Location getLastLocation() {
        return this.lastLocation;
    }
    
    public void setLastLocation(final Location lastLocation) {
        this.lastLocation = lastLocation;
    }
    
    public boolean isLoaded() {
        return this.isLoaded;
    }
    
    @Override
    public int getUID() {
        if (this.UID == -1) {
            return this.UID = CookieGadgets.getDatabaseManager().getDatabaseUtils().getUID(this.uuid);
        }
        return this.UID;
    }
}
