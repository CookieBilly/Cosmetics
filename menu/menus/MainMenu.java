

package ws.billy.CookieGadgets.menu.menus;

import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import ws.billy.CookieGadgets.cosmetics.emotes.EmoteType;
import ws.billy.CookieGadgets.cosmetics.banners.BannerType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import ws.billy.CookieGadgets.cosmetics.particles.ParticleType;
import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHatType;
import ws.billy.CookieGadgets.cosmetics.hats.standard.HatType;
import java.util.ArrayList;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.Category;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.api.CookieGadgetsAPI;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.cosmetics.banners.PatternLettersManager;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.configuration.FileManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.cosmetics.MainMenuType;
import org.bukkit.event.Listener;

public class MainMenu implements Listener
{
    private static int[] layout;
    private static int invSize;
    private static int size;
    
    static {
        MainMenu.invSize = 54;
    }
    
    public MainMenu() {
        MainMenu.size = MainMenuType.enabled().size();
        MainMenu.invSize = MainMenuType.getInventorySize();
        switch (MainMenu.size) {
            case 11: {
                MainMenu.layout = new int[] { 11, 12, 13, 14, 15, 19, 20, 31, 22, 24, 25 };
                break;
            }
            case 10: {
                MainMenu.layout = new int[] { 9, 11, 13, 15, 17, 27, 29, 31, 33, 35 };
                break;
            }
            case 9: {
                MainMenu.layout = new int[] { 10, 12, 14, 16, 27, 29, 31, 33, 35 };
                break;
            }
            case 8: {
                MainMenu.layout = new int[] { 10, 12, 14, 16, 28, 30, 32, 34 };
                break;
            }
            case 7: {
                MainMenu.layout = new int[] { 11, 13, 15, 28, 30, 32, 34 };
                break;
            }
            case 6: {
                MainMenu.layout = new int[] { 11, 15, 28, 30, 32, 34 };
                break;
            }
            case 5: {
                MainMenu.layout = new int[] { 11, 15, 28, 31, 34 };
                break;
            }
            case 4: {
                MainMenu.layout = new int[] { 19, 21, 23, 25 };
                break;
            }
            case 3: {
                MainMenu.layout = new int[] { 20, 22, 24 };
                break;
            }
            case 2: {
                MainMenu.layout = new int[] { 20, 24 };
                break;
            }
            case 1: {
                MainMenu.layout = new int[] { 22 };
                break;
            }
        }
    }
    
    public static void openMainMenu(final Player player) {
        if (MainMenuType.getTheHighestItemSlot() > MainMenu.invSize) {
            MainMenu.invSize = (int)Math.round(Math.ceil(MainMenuType.getTheHighestItemSlot() / 9.0) * 9.0) + 9;
        }
        if (MainMenu.layout.length == MainMenuType.getLayout().length && isSlotAbleToPlace(MainMenu.invSize, MainMenuType.getLayout())) {
            MainMenu.layout = MainMenuType.getLayout();
        }
        else {
            FileManager.getMainMenuFile().set("Slot-Layout", MainMenuType.getDefaultLayout());
        }
        int n = 0;
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, MainMenu.invSize, CookieGadgets.getCookieGadgetsData().getMainMenuGUIName());
        for (int n2 = 1; n2 <= MainMenu.size && n2 <= MainMenu.size; ++n2) {
            final MainMenuType mainMenuType = MainMenuType.enabled().get(n2 - 1);
            if (mainMenuType.getMaterial().getEnumMaterial() == EnumMaterial.CUSTOM_MATERIAL_1) {
                InventoryUtils.inventoryBanner(inventory, mainMenuType.getDisplayName(), PatternLettersManager.bannerLetterB().getBaseColor(), PatternLettersManager.bannerLetterB().getPatterns(), mainMenuType.getLore(), getSize(player, mainMenuType.getCategory(), EnumItem.UNLOCKED.getLore()), MainMenu.layout[n++]);
            }
            else {
                InventoryUtils.inventory(inventory, mainMenuType.getDisplayName(), mainMenuType.getMaterial(), mainMenuType.getLore(), getSize(player, mainMenuType.getCategory(), EnumItem.UNLOCKED.getLore()), MainMenu.layout[n++]);
            }
        }
        if (EnumItem.RESET_COSMETICS.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RESET_COSMETICS.getItemStack(), InventoryUtils.findAvailableSlot(EnumItem.RESET_COSMETICS.getSlot(), MainMenu.invSize));
        }
        if (EnumItem.SETTINGS.show()) {
            InventoryUtils.inventory(inventory, EnumItem.SETTINGS.getDisplayName(), EnumItem.SETTINGS.getMaterial(), EnumItem.SETTINGS.getLore(), InventoryUtils.findAvailableSlot(EnumItem.SETTINGS.getSlot(), MainMenu.invSize));
        }
        if (EnumItem.MAIN_MENU_ITEM.show()) {
            InventoryUtils.mainMenuButton(player, inventory, InventoryUtils.findAvailableSlot(EnumItem.MAIN_MENU_ITEM.getSlot(), MainMenu.invSize));
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler
    public void onInvClickMainMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(CookieGadgets.getCookieGadgetsData().getMainMenuGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != MainMenu.invSize || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (inventoryClickEvent.getCurrentItem() == null || inventoryClickEvent.getCurrentItem().getItemMeta() == null || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName() == null) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (!WorldUtils.isWorldEnabled(player, true)) {
            inventoryClickEvent.setCancelled(true);
            player.closeInventory();
            return;
        }
        if (inventoryClickEvent.getClick() == ClickType.SHIFT_LEFT || inventoryClickEvent.getClick() == ClickType.SHIFT_RIGHT || inventoryClickEvent.getClick() == ClickType.NUMBER_KEY || inventoryClickEvent.getClick() == ClickType.UNKNOWN) {
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
            return;
        }
        if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(EnumItem.RESET_COSMETICS.getDisplayName())) {
            CookieGadgets.getPlayerManager(player).unequipActiveCosmetics();
            player.sendMessage(MessageType.RESET_COSMETICS.getFormatMessage());
            if (EnumItem.RESET_COSMETICS.isPlaySoundEnabled()) {
                EnumItem.RESET_COSMETICS.getSound().playSound(player, 1.0f, 2.0f);
            }
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(EnumItem.MAIN_MENU_ITEM.getDisplayName())) {
            CookieGadgetsAPI.goBackToMainMenu(player);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(EnumItem.SETTINGS.getDisplayName())) {
            SettingsMenu.openSettingsMenu(player);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        for (final MainMenuType mainMenuType : MainMenuType.values()) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(mainMenuType.getDisplayName()))) {
                if (mainMenuType.isEnabled()) {
                    mainMenuType.getCategory().openMenu(CookieGadgets.getPlayerManager(player), 1);
                }
                else {
                    player.sendMessage(mainMenuType.getCategory().getDisabledMessage().getFormatMessage());
                }
            }
        }
        inventoryClickEvent.setCancelled(true);
    }
    
    private static List<String> getSize(final Player player, final Category category, final List<String> list) {
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        final ArrayList<String> list2 = new ArrayList<String>();
        try {
            for (final String s : list) {
                if (s.contains("{HASPERMISSION}") || s.contains("{SIZE}") || s.contains("{PERCENTAGE}")) {
                    int i = 0;
                    int j = 0;
                    switch (category) {
                        case HATS: {
                            i = playerManager.getUnlockedHats();
                            j = HatType.enabled().size();
                            break;
                        }
                        case ANIMATED_HATS: {
                            i = playerManager.getUnlockedAnimatedHats();
                            j = AnimatedHatType.enabled().size();
                            break;
                        }
                        case PARTICLES: {
                            i = playerManager.getUnlockedParticles();
                            j = ParticleType.enabled().size();
                            break;
                        }
                        case SUITS: {
                            i = playerManager.getUnlockedSuits();
                            j = SuitType.enabled().size() * 4;
                            break;
                        }
                        case GADGETS: {
                            i = playerManager.getUnlockedGadgets();
                            j = GadgetType.enabled().size();
                            break;
                        }
                        case PETS: {
                            i = playerManager.getUnlockedPets();
                            j = PetType.enabled().size();
                            break;
                        }
                        case MINIATURES: {
                            i = playerManager.getUnlockedMiniatures();
                            j = MiniatureType.enabled().size();
                            break;
                        }
                        case MORPHS: {
                            if (CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() || CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) {
                                i = playerManager.getUnlockedMorphs();
                                j = MorphType.enabled().size();
                                break;
                            }
                            break;
                        }
                        case BANNERS: {
                            i = playerManager.getUnlockedBanners();
                            j = BannerType.enabled().size();
                            break;
                        }
                        case EMOTES: {
                            i = playerManager.getUnlockedEmotes();
                            j = EmoteType.enabled().size();
                            break;
                        }
                        case CLOAKS: {
                            i = playerManager.getUnlockedCloaks();
                            j = CloakType.enabled().size();
                            break;
                        }
                    }
                    list2.add(ChatUtil.format(s.replace("{HASPERMISSION}", String.valueOf(i)).replace("{SIZE}", String.valueOf(j)).replace("{PERCENTAGE}", String.valueOf(i * 100 / j))));
                }
                else {
                    list2.add(ChatUtil.format(s));
                }
            }
        }
        catch (ArithmeticException ex) {
            final Iterator<String> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                list2.add(ChatUtil.format(iterator2.next().replace("{HASPERMISSION}", String.valueOf(0)).replace("{SIZE}", String.valueOf(0)).replace("{PERCENTAGE}", String.valueOf(0))));
            }
        }
        return list2;
    }
    
    private static boolean isSlotAbleToPlace(final int n, final int[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] > n - 1) {
                return false;
            }
        }
        return true;
    }
}
