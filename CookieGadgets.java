// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets;

import org.bukkit.configuration.file.FileConfiguration;
import ws.billy.CookieGadgets.utils.cosmetics.morphs.IDisguise;
import ws.billy.CookieGadgets.utils.cosmetics.morphs.LibDisguise;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.economy.storages.Economy_CoinsAPINB;
import ws.billy.CookieGadgets.economy.storages.Economy_PlayerPoints;
import ws.billy.CookieGadgets.economy.storages.Economy_Vault;
import java.lang.reflect.InvocationTargetException;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import ws.billy.CookieGadgets.custom.CustomEmoteType;
import ws.billy.CookieGadgets.cosmetics.emotes.EmoteType;
import ws.billy.CookieGadgets.custom.CustomBannerType;
import ws.billy.CookieGadgets.cosmetics.banners.BannerType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import ws.billy.CookieGadgets.cosmetics.pets.PetCategoryType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetCategoryType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitEquipmentType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import ws.billy.CookieGadgets.custom.CustomParticleType;
import ws.billy.CookieGadgets.cosmetics.particles.ParticleType;
import ws.billy.CookieGadgets.custom.CustomAnimatedHatType;
import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHatType;
import ws.billy.CookieGadgets.custom.CustomHatType;
import ws.billy.CookieGadgets.cosmetics.hats.standard.HatType;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.cosmetics.MainMenuType;
import ws.billy.CookieGadgets.command.mysteryboxes.subcommand.CommandMode;
import ws.billy.CookieGadgets.command.mysteryboxes.subcommand.CommandGiveAll;
import ws.billy.CookieGadgets.command.mysteryboxes.subcommand.CommandGive;
import ws.billy.CookieGadgets.command.mysteryboxes.subcommand.CommandGift;
import ws.billy.CookieGadgets.command.mysteryboxes.subcommand.CommandCheck;
import ws.billy.CookieGadgets.command.mysterydust.subcommands.CommandSetMysteryDust;
import ws.billy.CookieGadgets.command.mysterydust.subcommands.CommandRemoveMysteryDust;
import ws.billy.CookieGadgets.command.mysterydust.subcommands.CommandPayMysteryDust;
import ws.billy.CookieGadgets.command.mysterydust.subcommands.CommandCheckMysteryDust;
import ws.billy.CookieGadgets.command.mysterydust.subcommands.CommandAddMysteryDust;
import org.bukkit.command.TabCompleter;
import ws.billy.CookieGadgets.command.main.AutoTabCompleter;
import ws.billy.CookieGadgets.command.main.subcommands.CommandStatus;
import ws.billy.CookieGadgets.command.main.subcommands.CommandSettings;
import ws.billy.CookieGadgets.command.main.subcommands.CommandReset;
import ws.billy.CookieGadgets.command.main.subcommands.CommandRemovePermission;
import ws.billy.CookieGadgets.command.main.subcommands.CommandReload;
import ws.billy.CookieGadgets.command.main.subcommands.CommandPetItems;
import ws.billy.CookieGadgets.command.main.subcommands.CommandPermission;
import ws.billy.CookieGadgets.command.main.subcommands.CommandNamePet;
import ws.billy.CookieGadgets.command.main.subcommands.CommandMenuItem;
import ws.billy.CookieGadgets.command.main.subcommands.CommandMenu;
import ws.billy.CookieGadgets.command.main.subcommands.CommandMain;
import ws.billy.CookieGadgets.command.main.subcommands.CommandHelp;
import ws.billy.CookieGadgets.command.main.subcommands.CommandEquip;
import ws.billy.CookieGadgets.command.main.subcommands.CommandCheckUpdate;
import ws.billy.CookieGadgets.command.main.subcommands.CommandAddPermission;
import ws.billy.CookieGadgets.command.main.SubCommand;
import ws.billy.CookieGadgets.command.main.subcommands.CommandAbout;
import ws.billy.CookieGadgets.command.main.CommandManager;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.ConfirmOpenMultipleBoxesMenu;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.OpenMultipleBoxesMenu;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.ConfirmSendGiftMenu;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.SendGiftMenu;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.GiftInventoryMenu;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.MysteryBoxesCraftingMenu;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.ConfirmOpenMysteryBoxMenu;
import ws.billy.CookieGadgets.menu.menus.mysteryvault.MysteryVaultAnimationsMenu;
import ws.billy.CookieGadgets.menu.menus.mysteryvault.MysteryVaultMenu;
import ws.billy.CookieGadgets.listeners.mysteryvault.MysteryVaultPreviewListener;
import ws.billy.CookieGadgets.listeners.mysteryvault.MysteryVaultBreakListener;
import ws.billy.CookieGadgets.menu.menus.PetItemsMenu;
import ws.billy.CookieGadgets.menu.menus.SettingsMenu;
import ws.billy.CookieGadgets.menu.menus.ItemPurchaseMenu;
import ws.billy.CookieGadgets.menu.menus.CloaksMenu;
import ws.billy.CookieGadgets.menu.menus.EmotesMenu;
import ws.billy.CookieGadgets.menu.menus.BannersMenu;
import ws.billy.CookieGadgets.menu.menus.MorphsMenu;
import ws.billy.CookieGadgets.menu.menus.MiniaturesMenu;
import ws.billy.CookieGadgets.menu.menus.PetTypesMenu;
import ws.billy.CookieGadgets.menu.menus.PetCategoriesMenu;
import ws.billy.CookieGadgets.menu.menus.GadgetTypesMenu;
import ws.billy.CookieGadgets.menu.menus.GadgetCategoriesMenu;
import ws.billy.CookieGadgets.menu.menus.SuitEquipmentMenu;
import ws.billy.CookieGadgets.menu.menus.SuitsMenu;
import ws.billy.CookieGadgets.menu.menus.ParticlesMenu;
import ws.billy.CookieGadgets.menu.menus.AnimatedHatsMenu;
import ws.billy.CookieGadgets.menu.menus.HatsMenu;
import ws.billy.CookieGadgets.menu.menus.MainMenu;
import ws.billy.CookieGadgets.listeners.cosmetics.CommandProcessListener;
import ws.billy.CookieGadgets.listeners.cosmetics.EntityPickupItemListener;
import ws.billy.CookieGadgets.listeners.v1_9.PrepareAnvilListener;
import ws.billy.CookieGadgets.listeners.v1_9.PlayerSwapItemListener;
import ws.billy.CookieGadgets.listeners.cosmetics.ArmorStandManipulateListener;
import ws.billy.CookieGadgets.listeners.cosmetics.SelectItemListener;
import ws.billy.CookieGadgets.listeners.cosmetics.PlayerPickupItemListener;
import ws.billy.CookieGadgets.listeners.cosmetics.DropItemListener;
import ws.billy.CookieGadgets.listeners.cosmetics.CraftItemListener;
import ws.billy.CookieGadgets.listeners.RenamePetListener;
import ws.billy.CookieGadgets.listeners.GadgetListener;
import ws.billy.CookieGadgets.listeners.EntityListener;
import ws.billy.CookieGadgets.listeners.OpenMenuListener;
import org.bukkit.event.Listener;
import ws.billy.CookieGadgets.listeners.PlayerListener;
import ws.billy.CookieGadgets.configuration.ConfigVersionManager;
import ws.billy.CookieGadgets.configuration.ConfigurationMessageManager;
import ws.billy.CookieGadgets.utils.FileUtil;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.updater.UpdaterManager;
import ws.billy.CookieGadgets.tasks.WorldGuardRegion;
import ws.billy.CookieGadgets.tasks.UpdatePlayerMysteryDust;
import ws.billy.CookieGadgets.tasks.UpdateAvailableMysteryBoxesHolograms;
import ws.billy.CookieGadgets.tasks.UpdateHolograms;
import ws.billy.CookieGadgets.tasks.PlayerLastLocation;
import ws.billy.CookieGadgets.tasks.CheckPlayerInventory;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVaultManager;
import ws.billy.CookieGadgets.utils.mysteryboxes.CosmeticItems;
import ws.billy.CookieGadgets.mysteryboxes.customloot.CustomMysteryBoxesLoot;
import ws.billy.CookieGadgets.economy.HookPluginManager;
import ws.billy.CookieGadgets.economy.GStorage;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.hook.protocollib.packets.ProtocolLibHookImpl;
import ws.billy.CookieGadgets.database.mysql.MySQLManager;
import ws.billy.CookieGadgets.database.sqlite.SQLiteManager;
import ws.billy.CookieGadgets.database.DatabaseStorage;
import java.io.IOException;
import ws.billy.CookieGadgets.utils.discount.ItemCostDiscountManager;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxesMessages;
import ws.billy.CookieGadgets.configuration.OldConfigurationManager;
import ws.billy.CookieGadgets.cosmetics.pets.TypeManager;
import ws.billy.CookieGadgets.utils.ServerVersion;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.log.LoggerManager;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.economy.storages.Economy_Default;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import ws.billy.CookieGadgets.hook.PlaceholderAPI;
import java.util.Random;
import ws.billy.CookieGadgets.metrics.MetricsStarter;
import ws.billy.CookieGadgets.hook.protocollib.ProtocolLibHook;
import ws.billy.CookieGadgets.utils.cosmetics.morphs.GDisguise;
import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.PetData;
import ws.billy.CookieGadgets.database.DatabaseManager;
import ws.billy.CookieGadgets.economy.GEconomyProvider;
import ws.billy.CookieGadgets.nms.NMSManager;
import ws.billy.CookieGadgets.license.LicenseChecker;

import java.io.File;
import ws.billy.CookieGadgets.configuration.CustomConfiguration;
import ws.billy.CookieGadgets.player.GPlayer;
import org.bukkit.plugin.java.JavaPlugin;

public class CookieGadgets extends JavaPlugin
{
    private static CookieGadgets CookieGadgets;
    private static CookieGadgetsData CookieGadgetsData;
    private static GPlayer gPlayer;
    public CustomConfiguration config;
    private File file;
    private static NMSManager nmsManager;
    private static GEconomyProvider gEconomyProvider;
    private static DatabaseManager databaseManager;
    private static PetData petData;
    private static GDisguise gDisguise;
    private static ProtocolLibHook protocolLib;
    private static MetricsStarter metricsStarter;
    private static Random random;
    private static boolean bypassCreatureSpawn;
    private static PlaceholderAPI placeholderAPI;
    private static WorldGuardPlugin worldGuardPlugin;
    private static boolean isSupport;
    private static boolean errorOnLoading;
    
    static {
        CookieGadgets.nmsManager = null;
        CookieGadgets.gEconomyProvider = new Economy_Default();
        CookieGadgets.gDisguise = null;
        CookieGadgets.protocolLib = null;
        CookieGadgets.random = new Random();
        CookieGadgets.bypassCreatureSpawn = false;
        CookieGadgets.isSupport = true;
        CookieGadgets.errorOnLoading = false;
    }
    
    public void onEnable() {
        loadConfig0();
        CookieGadgets.CookieGadgets = this;
        if (!VersionManager.isSupported(VersionManager.getMinecraftVersion(), "1.8", "1.16")) {
            LoggerManager.printLogWithHeader(LoggerManager.LogLevel.INFO, "------------------------------------------------------", "This version of CookieGadgets only", "works on spigot versions from 1.8 to 1.16.", "The plugin will be disabled.");
            CookieGadgets.isSupport = false;
            Bukkit.getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        try {
            new VersionManager();
        }
        catch (IllegalArgumentException ex3) {
            LoggerManager.printLogWithHeader(LoggerManager.LogLevel.INFO, "------------------------------------------------------", "This version of CookieGadgets only", "works on spigot versions from 1.8 to 1.16.", "The plugin will be disabled.");
            CookieGadgets.isSupport = false;
            Bukkit.getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        try {
            this.initNMS();
            CookieGadgets.CookieGadgetsData = new CookieGadgetsData();
            TypeManager.initializePets();
            CookieGadgets.nmsManager.registerPets();
            CookieGadgets.nmsManager.getSpawnUtils().initNMSPets();
            CookieGadgets.gPlayer = new GPlayer();
            this.setupConfig();
            this.initConfiguration();
            CookieGadgets.petData = new PetData();
            if (this.config.get("Config Version") != null) {
                LoggerManager.printLogWithHeader(LoggerManager.LogLevel.INFO, "------------------------------------------------------", "Transferring data into new file...");
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)this, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        OldConfigurationManager.transferMessages();
                    }
                });
            }
            getCookieGadgetsData().initData();
            this.initGEconomyProvider();
            MysteryBoxesMessages.initMessages();
            this.registerListeners();
            this.initCommands();
            this.initCosmetics();
            ItemCostDiscountManager.loadDiscountGroups();
        }
        catch (ExceptionInInitializerError | Exception exceptionInInitializerError) {
            final Object o2;
            final Object o = o2;
            LoggerManager.warn("Cannot load CookieGadgets properly, plugin will be disabled. Please contact the author for help.");
            ((Throwable)o).printStackTrace();
            CookieGadgets.errorOnLoading = true;
            this.getPluginLoader().disablePlugin((Plugin)this);
            return;
        }
        final MetricsStarter metricsStarter = new MetricsStarter(CookieGadgets.CookieGadgets);
        metricsStarter.start();
        CookieGadgets.metricsStarter = metricsStarter;
        try {
            this.config.save(this.file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        final DatabaseStorage databaseStorage = getCookieGadgetsData().getDatabaseStorage();
        if (databaseStorage == DatabaseStorage.SQLITE) {
            CookieGadgets.databaseManager = new SQLiteManager();
        }
        else if (databaseStorage == DatabaseStorage.MYSQL) {
            CookieGadgets.databaseManager = new MySQLManager();
        }
        CookieGadgets.databaseManager.init();
        if (CookieGadgets.CookieGadgets == null || CookieGadgets.errorOnLoading) {
            return;
        }
        if (Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
            try {
                CookieGadgets.protocolLib = new ProtocolLibHookImpl();
                if (CookieGadgets.protocolLib.hook((Plugin)this, CookieGadgets.nmsManager)) {
                    this.getLogger().info("Enabled Mystery Vault individual hologram.");
                }
            }
            catch (NoClassDefFoundError | Exception noClassDefFoundError) {
                LoggerManager.warn("Failed to hook ProtocolLib. Please update ProtocolLib to the latest version.");
            }
        }
        this.initSongsFolder();
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            try {
                CookieGadgets.placeholderAPI = new PlaceholderAPI();
                if (CookieGadgets.placeholderAPI.register()) {
                    LoggerManager.info("[Placeholder] PlaceholderAPI hooked.");
                }
                else {
                    LoggerManager.consoleMessage(ChatUtil.format("&cFailed to hook PlaceholderAPI."));
                }
            }
            catch (Exception ex2) {
                LoggerManager.consoleMessage(ChatUtil.format("&cFailed to hook PlaceholderAPI."));
                ex2.printStackTrace();
            }
        }
        if (getCookieGadgetsData().getMysteryDustStorage() != GStorage.DEFAULT) {
            new HookPluginManager(this).hookPlugins(getCookieGadgetsData().getMysteryDustStorage());
        }
        CustomMysteryBoxesLoot.loadCustomLoots();
        CosmeticItems.loadItems();
        MysteryVaultManager.loadMysteryVaults();
        if (CookieGadgets.CookieGadgets == null || CookieGadgets.errorOnLoading) {
            return;
        }
        if (getCookieGadgetsData().isAntiLagEnabled()) {
            Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)this, (Runnable)new AntiLagChecker(), 100L, 20L);
        }
        Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)this, (Runnable)new CheckPlayerInventory(), 100L, 1200L);
        if (!getCookieGadgetsData().isMysteryBoxesRewardAllowAFK()) {
            Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)this, (Runnable)new PlayerLastLocation(), 10L, 40L);
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this, (Runnable)new UpdateHolograms(), 10L, 10L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this, (Runnable)new UpdateAvailableMysteryBoxesHolograms(), 12L, 12L);
        Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)this, (Runnable)new UpdatePlayerMysteryDust(), 100L, 12000L);
        Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)this, (Runnable)new WorldGuardRegion(), 10L, 10L);
        if (getCookieGadgetsData().isCheckUpdateEnabled()) {
            final Iterator<Player> iterator;
            Player player;
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)this, () -> {
                CookieGadgets.updaterChecker = UpdaterManager.checkUpdate((CommandSender)this.getServer().getConsoleSender(), true);
                if (getUpdaterChecker().isOutdated()) {
                    Bukkit.getOnlinePlayers().iterator();
                    while (iterator.hasNext()) {
                        player = iterator.next();
                        if (player.isOp() || player.hasPermission(EnumPermission.COMMAND_CHECK_UPDATE.getPermission())) {
                            player.sendMessage(String.valueOf(getCookieGadgetsData().getPrefix()) + UpdaterManager.getMessage());
                        }
                    }
                }
                return;
            });
        }
        CookieGadgets.gPlayer.initPlayers();
    }
    
    public void onLoad() {
        final Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin != null && plugin instanceof WorldGuardPlugin) {
            CookieGadgets.worldGuardPlugin = (WorldGuardPlugin)plugin;
        }
    }
    
    public void onDisable() {
        if (CookieGadgets.isSupport && !CookieGadgets.errorOnLoading && CookieGadgets.CookieGadgets != null) {
            if (CookieGadgets.gPlayer != null) {
                CookieGadgets.gPlayer.dispose();
            }
            MysteryVaultManager.forceRemoveHolograms();
            MysteryVaultManager.forceRemoveLootHologram();
            MysteryVaultManager.forceRemoveAvailableMysteryBoxesHologram();
            TypeManager.unLoadRegisteredPets();
            CookieGadgets.nmsManager.unregisterPets();
            if (CookieGadgets.databaseManager != null && !CookieGadgets.databaseManager.getDBConnection().isClosed()) {
                CookieGadgets.databaseManager.getDBConnection().closeConnection();
            }
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") && CookieGadgets.placeholderAPI != null) {
                try {
                    CookieGadgets.placeholderAPI.unregister();
                }
                catch (NoSuchMethodError noSuchMethodError) {
                    me.clip.placeholderapi.PlaceholderAPI.unregisterPlaceholderHook("CookieGadgets");
                }
            }
            HandlerList.unregisterAll((Plugin)getInstance());
            Bukkit.getScheduler().cancelTasks((Plugin)getInstance());
        }
        CookieGadgets.CookieGadgets = null;
    }
    
    private void setupConfig() {
        this.file = new File(this.getDataFolder(), "config.yml");
        boolean b = false;
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            FileUtil.copy(this.getResource("config.yml"), this.file);
            b = true;
        }
        this.config = CustomConfiguration.loadConfiguration(this.file);
        ConfigurationMessageManager.loadConfigFileMessages(b);
        ConfigVersionManager.updateConfig();
        try {
            this.config.save(this.file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void registerListeners() {
        this.registerListener((Listener)new PlayerListener());
        this.registerListener((Listener)new OpenMenuListener());
        this.registerListener((Listener)new EntityListener());
        this.registerListener((Listener)new GadgetListener());
        this.registerListener((Listener)new RenamePetListener());
        this.registerListener((Listener)new CraftItemListener());
        this.registerListener((Listener)new DropItemListener());
        this.registerListener((Listener)new PlayerPickupItemListener());
        this.registerListener((Listener)new SelectItemListener());
        this.registerListener((Listener)new ArmorStandManipulateListener());
        if (VersionManager.is1_9OrAbove()) {
            this.registerListener((Listener)new PlayerSwapItemListener());
            this.registerListener((Listener)new PrepareAnvilListener());
        }
        if (VersionManager.is1_12OrAbove()) {
            this.registerListener((Listener)new EntityPickupItemListener());
        }
        this.registerListener((Listener)new CommandProcessListener());
        this.registerListener((Listener)new MainMenu());
        this.registerListener((Listener)new HatsMenu());
        this.registerListener((Listener)new AnimatedHatsMenu());
        this.registerListener((Listener)new ParticlesMenu());
        this.registerListener((Listener)new SuitsMenu());
        this.registerListener((Listener)new SuitEquipmentMenu());
        this.registerListener((Listener)new GadgetCategoriesMenu());
        this.registerListener((Listener)new GadgetTypesMenu());
        this.registerListener((Listener)new PetCategoriesMenu());
        this.registerListener((Listener)new PetTypesMenu());
        this.registerListener((Listener)new MiniaturesMenu());
        this.registerListener((Listener)new MorphsMenu());
        this.registerListener((Listener)new BannersMenu());
        this.registerListener((Listener)new EmotesMenu());
        this.registerListener((Listener)new CloaksMenu());
        this.registerListener((Listener)new ItemPurchaseMenu());
        this.registerListener((Listener)new SettingsMenu());
        this.registerListener((Listener)new PetItemsMenu());
        this.registerListener((Listener)new MysteryVaultBreakListener());
        this.registerListener((Listener)new MysteryVaultPreviewListener());
        this.registerListener((Listener)new MysteryVaultMenu());
        this.registerListener((Listener)new MysteryVaultAnimationsMenu());
        this.registerListener((Listener)new ConfirmOpenMysteryBoxMenu());
        this.registerListener((Listener)new MysteryBoxesCraftingMenu());
        this.registerListener((Listener)new GiftInventoryMenu());
        this.registerListener((Listener)new SendGiftMenu());
        this.registerListener((Listener)new ConfirmSendGiftMenu());
        this.registerListener((Listener)new OpenMultipleBoxesMenu());
        this.registerListener((Listener)new ConfirmOpenMultipleBoxesMenu());
    }
    
    private void initCommands() {
        final CommandManager commandManager = new CommandManager((Plugin)this);
        commandManager.registerCommand(new CommandAbout());
        commandManager.registerCommand(new CommandAddPermission());
        commandManager.registerCommand(new CommandCheckUpdate());
        commandManager.registerCommand(new CommandEquip());
        commandManager.registerCommand(new CommandHelp());
        commandManager.registerCommand(new CommandMain());
        commandManager.registerCommand(new CommandMenu());
        commandManager.registerCommand(new CommandMenuItem());
        commandManager.registerCommand(new CommandNamePet());
        commandManager.registerCommand(new CommandPermission());
        commandManager.registerCommand(new CommandPetItems());
        commandManager.registerCommand(new CommandReload());
        commandManager.registerCommand(new CommandRemovePermission());
        commandManager.registerCommand(new CommandReset());
        commandManager.registerCommand(new CommandSettings());
        commandManager.registerCommand(new CommandStatus());
        this.getCommand("gmenu").setTabCompleter((TabCompleter)new AutoTabCompleter());
        this.getCommand("CookieGadgets").setTabCompleter((TabCompleter)new AutoTabCompleter());
        final ws.billy.CookieGadgets.command.mysterydust.CommandManager commandManager2 = new ws.billy.CookieGadgets.command.mysterydust.CommandManager((Plugin)this);
        commandManager2.registerCommand(new CommandAddMysteryDust());
        commandManager2.registerCommand(new CommandCheckMysteryDust());
        commandManager2.registerCommand(new CommandPayMysteryDust());
        commandManager2.registerCommand(new CommandRemoveMysteryDust());
        commandManager2.registerCommand(new CommandSetMysteryDust());
        this.getCommand("mysterydust").setTabCompleter((TabCompleter)new ws.billy.CookieGadgets.command.mysterydust.AutoTabCompleter());
        this.getCommand("dust").setTabCompleter((TabCompleter)new ws.billy.CookieGadgets.command.mysterydust.AutoTabCompleter());
        final ws.billy.CookieGadgets.command.mysteryboxes.CommandManager commandManager3 = new ws.billy.CookieGadgets.command.mysteryboxes.CommandManager((Plugin)this);
        commandManager3.registerCommand(new CommandCheck());
        commandManager3.registerCommand(new CommandGift());
        commandManager3.registerCommand(new CommandGive());
        commandManager3.registerCommand(new CommandGiveAll());
        commandManager3.registerCommand(new CommandMode());
        this.getCommand("gmysteryboxes").setTabCompleter((TabCompleter)new ws.billy.CookieGadgets.command.mysteryboxes.AutoTabCompleter());
        this.getCommand("gmysterybox").setTabCompleter((TabCompleter)new ws.billy.CookieGadgets.command.mysteryboxes.AutoTabCompleter());
        this.getCommand("gmb").setTabCompleter((TabCompleter)new ws.billy.CookieGadgets.command.mysteryboxes.AutoTabCompleter());
    }
    
    private void initCosmetics() {
        MainMenuType.checkEnabled();
        new MainMenu();
        if (Category.HATS.isEnabled()) {
            HatType.checkEnabled();
            CustomHatType.initCustomHats();
            HatType.sortItems();
        }
        if (Category.ANIMATED_HATS.isEnabled()) {
            AnimatedHatType.checkEnabled();
            CustomAnimatedHatType.initCustomAnimatedHats();
            AnimatedHatType.sortItems();
        }
        if (Category.PARTICLES.isEnabled()) {
            ParticleType.checkEnabled();
            CustomParticleType.initCustomParticles();
            ParticleType.sortItems();
        }
        if (Category.SUITS.isEnabled()) {
            SuitType.checkEnabled();
            SuitEquipmentType.checkEnabled();
        }
        if (Category.GADGETS.isEnabled()) {
            GadgetCategoryType.checkEnabled();
            GadgetType.checkEnabled();
            GadgetType.sortItems();
        }
        if (Category.PETS.isEnabled()) {
            PetCategoryType.checkEnabled();
            PetType.checkEnabled();
            PetItems.values();
            PetType.sortItems();
        }
        if (Category.MINIATURES.isEnabled()) {
            MiniatureType.checkEnabled();
            MiniatureType.sortItems();
        }
        if (Category.MORPHS.isEnabled() && (getCookieGadgetsData().isLibsDisguiseEnabled() || getCookieGadgetsData().isIDisguiseEnabled())) {
            MorphType.checkEnabled();
            MorphType.sortItems();
        }
        if (Category.BANNERS.isEnabled()) {
            BannerType.checkEnabled();
            CustomBannerType.initCustomBanners();
            BannerType.sortItems();
        }
        if (Category.EMOTES.isEnabled()) {
            EmoteType.checkEnabled();
            CustomEmoteType.initCustomEmotes();
            EmoteType.sortItems();
        }
        if (Category.CLOAKS.isEnabled()) {
            CloakType.checkEnabled();
            CloakType.sortItems();
        }
        AnimationType.checkEnabled();
    }
    
    private void initNMS() {
        Object instantiateObject = null;
        try {
            instantiateObject = ReflectionUtils.instantiateObject(Class.forName("ws.billy.CookieGadgets.nms." + ServerVersion.getServerVersion() + ".NMSManagerImpl"), new Object[0]);
        }
        catch (ClassNotFoundException ex) {}
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex2) {
            final Throwable t;
            t.printStackTrace();
        }
        CookieGadgets.nmsManager = (NMSManager)instantiateObject;
    }
    
    private void initGEconomyProvider() {
        if (getCookieGadgetsData().getMysteryDustStorage() == GStorage.DEFAULT) {
            CookieGadgets.gEconomyProvider = new Economy_Default();
        }
        else if (getCookieGadgetsData().getMysteryDustStorage() == GStorage.VAULT) {
            CookieGadgets.gEconomyProvider = new Economy_Vault();
        }
        else if (getCookieGadgetsData().getMysteryDustStorage() == GStorage.PLAYERPOINTS) {
            CookieGadgets.gEconomyProvider = new Economy_PlayerPoints();
        }
        else if (getCookieGadgetsData().getMysteryDustStorage() == GStorage.COINSAPI) {
            CookieGadgets.gEconomyProvider = new Economy_CoinsAPINB();
        }
    }
    
    private void initConfiguration() {
        ConfigurationMessageManager.loadMessagesFileMessages();
        ConfigurationMessageManager.loadMysteryBoxesFileMessages();
        ConfigurationMessageManager.loadPetSystemFileMessages();
        FileManager.getMessagesFile().reload();
        FileManager.getMessagesFile().save();
        FileManager.getMainMenuFile().reload();
        FileManager.getMainMenuFile().save();
        FileManager.getHatsFile().reload();
        FileManager.getHatsFile().save();
        FileManager.getAnimatedHatsFile().reload();
        FileManager.getAnimatedHatsFile().save();
        FileManager.getParticlesFile().reload();
        FileManager.getParticlesFile().save();
        FileManager.getGadgetsFile().reload();
        FileManager.getGadgetsFile().save();
        FileManager.getPetsFile().reload();
        FileManager.getPetsFile().save();
        FileManager.getMiniaturesFile().reload();
        FileManager.getMiniaturesFile().save();
        FileManager.getMorphsFile().reload();
        FileManager.getMorphsFile().save();
        FileManager.getBannersFile().reload();
        FileManager.getBannersFile().save();
        FileManager.getEmotesFile().reload();
        FileManager.getEmotesFile().save();
        FileManager.getCloaksFile().reload();
        FileManager.getCloaksFile().save();
        FileManager.getCustomHatsFile().reload();
        FileManager.getCustomHatsFile().save();
        FileManager.getCustomAnimatedHatsFile().reload();
        FileManager.getCustomAnimatedHatsFile().save();
        FileManager.getCustomParticlesFile().reload();
        FileManager.getCustomParticlesFile().save();
        FileManager.getCustomBannersFile().reload();
        FileManager.getCustomBannersFile().save();
        FileManager.getCustomEmotesFile().reload();
        FileManager.getCustomEmotesFile().save();
        FileManager.getMysteryBoxesFile().reload();
        FileManager.getMysteryBoxesFile().save();
        FileManager.getPetSystemFile().reload();
        FileManager.getPetSystemFile().save();
    }
    
    private void initSongsFolder() {
        final File file = new File(String.valueOf(this.getDataFolder().getPath()) + "/songs/");
        if (!file.exists() || file.listFiles().length <= 0) {
            this.saveResource("songs/Bumblebee.nbs", true);
        }
        final File file2 = new File(String.valueOf(this.getDataFolder().getPath()) + "/songs/RadioGadget");
        if (!file2.exists() || file2.listFiles().length <= 0) {
            this.saveResource("songs/RadioGadget/Animals.nbs", true);
            this.saveResource("songs/RadioGadget/Another One Bites the Dust.nbs", true);
            this.saveResource("songs/RadioGadget/Beverly Hills Cop.nbs", true);
            this.saveResource("songs/RadioGadget/Carol of the Bells.nbs", true);
            this.saveResource("songs/RadioGadget/Downtown.nbs", true);
            this.saveResource("songs/RadioGadget/Everybody Dance Now.nbs", true);
            this.saveResource("songs/RadioGadget/Frosty the Snowman.nbs", true);
            this.saveResource("songs/RadioGadget/Fugue In D Minor.nbs", true);
            this.saveResource("songs/RadioGadget/Fur Elise.nbs", true);
            this.saveResource("songs/RadioGadget/Gerudo Valley.nbs", true);
            this.saveResource("songs/RadioGadget/Ghostbusters.nbs", true);
            this.saveResource("songs/RadioGadget/Grandma Got Run Over by a Reindeer.nbs", true);
            this.saveResource("songs/RadioGadget/Happy.nbs", true);
            this.saveResource("songs/RadioGadget/Hotel California.nbs", true);
            this.saveResource("songs/RadioGadget/Let It Be.nbs", true);
            this.saveResource("songs/RadioGadget/Luigi's Mansion.nbs", true);
            this.saveResource("songs/RadioGadget/Mortal Kombat.nbs", true);
            this.saveResource("songs/RadioGadget/Numb.nbs", true);
            this.saveResource("songs/RadioGadget/Ob-La-Di, Ob-La-Da.nbs", true);
            this.saveResource("songs/RadioGadget/Rainbow Tylenol.nbs", true);
            this.saveResource("songs/RadioGadget/Reptilia.nbs", true);
            this.saveResource("songs/RadioGadget/StarFox64Theme.nbs", true);
            this.saveResource("songs/RadioGadget/The Fox.nbs", true);
            this.saveResource("songs/RadioGadget/Wizards in Winter.nbs", true);
        }
        final File file3 = new File(String.valueOf(this.getDataFolder().getPath()) + "/songs/DiscoBallGadget");
        if (!file3.exists() || file3.listFiles().length <= 0) {
            this.saveResource("songs/DiscoBallGadget/Demons.nbs", true);
            this.saveResource("songs/DiscoBallGadget/Let It Go.nbs", true);
            this.saveResource("songs/DiscoBallGadget/Levels.nbs", true);
            this.saveResource("songs/DiscoBallGadget/The Fox.nbs", true);
            this.saveResource("songs/DiscoBallGadget/Wizards in Winter.nbs", true);
        }
        final File file4 = new File(String.valueOf(this.getDataFolder().getPath()) + "/songs/DjBoothGadget");
        if (!file4.exists() || file4.listFiles().length <= 0) {
            this.saveResource("songs/DjBoothGadget/Another One Bites the Dust.nbs", true);
            this.saveResource("songs/DjBoothGadget/Everybody Dance Now.nbs", true);
            this.saveResource("songs/DjBoothGadget/Wizards in Winter.nbs", true);
        }
    }
    
    public File getFile() {
        return this.file;
    }
    
    public CustomConfiguration getConfig() {
        return this.config;
    }
    
    public void registerListener(final Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, (Plugin)getInstance());
    }
    
    public static Random random() {
        return CookieGadgets.random;
    }
    
    public static GPlayer getGPlayer() {
        return CookieGadgets.gPlayer;
    }
    
    public static PlayerManager getPlayerManager(final Player player) {
        return CookieGadgets.gPlayer.getPlayerManager(player);
    }
    
    public static CookieGadgetsData getCookieGadgetsData() {
        return CookieGadgets.CookieGadgetsData;
    }
    
    public static PetData getPetData() {
        return CookieGadgets.petData;
    }
    
    public static NMSManager getNMSManager() {
        return CookieGadgets.nmsManager;
    }
    
    public static UpdaterChecker getUpdaterChecker() {
        return CookieGadgets.updaterChecker;
    }
    
    public static LicenseChecker getLicenseChecker() {
        return CookieGadgets.licenseChecker;
    }
    
    public static DatabaseManager getDatabaseManager() {
        return CookieGadgets.databaseManager;
    }
    
    public static GEconomyProvider getGEconomyProvider() {
        return CookieGadgets.gEconomyProvider;
    }
    
    public static void setGEconomyProvider(final GEconomyProvider gEconomyProvider) {
        if (gEconomyProvider == null) {
            throw new NullPointerException("Storage provider can not be null!");
        }
        CookieGadgets.gEconomyProvider = gEconomyProvider;
    }
    
    public static GDisguise getGDisguise() {
        if (!getCookieGadgetsData().isLibsDisguiseEnabled() && !getCookieGadgetsData().isIDisguiseEnabled()) {
            return null;
        }
        if (CookieGadgets.gDisguise != null) {
            return CookieGadgets.gDisguise;
        }
        if (getCookieGadgetsData().isLibsDisguiseEnabled()) {
            return CookieGadgets.gDisguise = new LibDisguise();
        }
        if (getCookieGadgetsData().isIDisguiseEnabled()) {
            return CookieGadgets.gDisguise = new IDisguise();
        }
        return null;
    }
    
    public static WorldGuardPlugin getWorldGuardPlugin() {
        return CookieGadgets.worldGuardPlugin;
    }
    
    public static boolean hasProtocolLibHook() {
        return CookieGadgets.protocolLib != null;
    }
    
    public static ProtocolLibHook getProtocolLib() {
        return CookieGadgets.protocolLib;
    }
    
    public static MetricsStarter getMetrics() {
        return CookieGadgets.metricsStarter;
    }
    
    public static boolean isBypassCreatureSpawn() {
        return CookieGadgets.bypassCreatureSpawn;
    }
    
    public static void setBypassCreatureSpawn(final boolean bypassCreatureSpawn) {
        CookieGadgets.bypassCreatureSpawn = bypassCreatureSpawn;
    }
    
    public static CookieGadgets getInstance() {
        return CookieGadgets.CookieGadgets;
    }
    
    public String getPluginName() {
        return "CookieGadgets";
    }
}
