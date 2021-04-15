

package ws.billy.CookieGadgets.menu.menus;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItemInterest;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.api.CookieGadgetsAPI;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import ws.billy.CookieGadgets.cosmetics.Category;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetMessages;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.StringUtils;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.GPet;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Listener;

public class PetItemsMenu implements Listener
{
    public static void openPetItemsMenu(final PlayerManager playerManager, final GPet provideCarePet) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, CookieGadgets.getCookieGadgetsData().getPetItemsGUIName());
        PetItems[] values;
        for (int length = (values = PetItems.values()).length, i = 0; i < length; ++i) {
            final PetItems petItems = values[i];
            try {
                final int petItem = playerManager.getPetItem(petItems);
                if (petItem <= 0 && EnumItem.ZERO_PET_ITEM_REMAIN.showCustomItem()) {
                    InventoryUtils.inventory(inventory, petItems.getDisplayName(), EnumItem.ZERO_PET_ITEM_REMAIN.getCustomItem(), 1, petItems.getLore(), StringUtils.addPlaceholder(petItems.getItemType().getPetItemLore(), "{REMAIN}", String.valueOf(petItem)), petItems.getSlot());
                }
                else {
                    InventoryUtils.inventory(inventory, petItems.getDisplayName(), petItems.getMaterial(), (petItem > 64) ? 64 : ((petItem <= 0) ? 1 : petItem), petItems.getLore(), StringUtils.addPlaceholder(petItems.getItemType().getPetItemLore(), petItems.getItemType().getItemRemainLore(), "{REMAIN}", String.valueOf(petItem)), petItems.getSlot());
                }
            }
            catch (Exception ex) {
                playerManager.getPlayer().sendMessage(MessageType.ERROR.getFormatMessage());
                ex.printStackTrace();
                return;
            }
        }
        if (EnumItem.GO_BACK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), 48);
        }
        final PetType type = provideCarePet.getType();
        if (EnumItem.YOUR_PET.show()) {
            InventoryUtils.inventory(inventory, EnumItem.YOUR_PET.getDisplayName(), type.getMaterial(), PetMessages.getLore(playerManager.getPlayer(), provideCarePet, type.getUnlockedLore()), EnumItem.YOUR_PET.getSlot());
        }
        if (EnumItem.MAIN_MENU_ITEM.show()) {
            InventoryUtils.mainMenuButton(playerManager.getPlayer(), inventory, EnumItem.MAIN_MENU_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        playerManager.getPlayer().openInventory(inventory);
        playerManager.setProvideCarePet(provideCarePet);
    }
    
    @EventHandler
    public void onInvClickPetItems(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(CookieGadgets.getCookieGadgetsData().getPetItemsGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (!Category.PETS.isEnabled() || !WorldUtils.isWorldEnabled(player, true) || !CookieGadgets.getPetData().isEnabled()) {
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
            return;
        }
        if (inventoryClickEvent.getCurrentItem() == null || inventoryClickEvent.getCurrentItem().getItemMeta() == null || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName() == null) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (inventoryClickEvent.getClick() == ClickType.SHIFT_LEFT || inventoryClickEvent.getClick() == ClickType.SHIFT_RIGHT || inventoryClickEvent.getClick() == ClickType.NUMBER_KEY || inventoryClickEvent.getClick() == ClickType.UNKNOWN) {
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
            return;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.GO_BACK.getItemStack(), 48)) {
            if (playerManager.getSelectedProvideCareCategoryPet() == null) {
                playerManager.openCategoryPetsMenu();
            }
            else {
                playerManager.openPetTypesMenu(playerManager.getSelectedProvideCareCategoryPet(), playerManager.getCurrentMenuPage());
            }
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.MAIN_MENU_ITEM.getDisplayName(), EnumItem.MAIN_MENU_ITEM.getSlot())) {
            CookieGadgetsAPI.goBackToMainMenu(player);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        PetItems[] values;
        for (int length = (values = PetItems.values()).length, i = 0; i < length; ++i) {
            final PetItems petItems = values[i];
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(petItems.getDisplayName()))) {
                try {
                    final GPet provideCarePet = playerManager.getProvideCarePet();
                    if (provideCarePet == null) {
                        player.closeInventory();
                        inventoryClickEvent.setCancelled(true);
                        return;
                    }
                    final PetItemInterest petInterest = provideCarePet.getType().getPetInterest(petItems);
                    int incrementByInterest = petItems.getIncrementByInterest(petInterest);
                    if (petItems.getItemType().isPlaySoundEnabled()) {
                        petItems.getItemType().getClickSound().playSound(player, 1.0f, 2.0f);
                    }
                    if (playerManager.getPetItem(petItems) == 0) {
                        player.sendMessage(MessageType.ZERO_PET_ITEM_REMAIN.getFormatMessage());
                        if (EnumItem.ZERO_PET_ITEM_REMAIN.isPlaySoundEnabled()) {
                            EnumItem.ZERO_PET_ITEM_REMAIN.getSound().playSound(player, 1.0f, 0.5f);
                        }
                        inventoryClickEvent.setCancelled(true);
                        return;
                    }
                    final int stat = provideCarePet.getPetAttribute().getStat(petItems.getItemType());
                    if (stat == 100) {
                        player.sendMessage(MessageType.PET_STAT_FULLED.getFormatMessage().replace("{PET_NAME}", provideCarePet.getPetName().replace("{PLAYER}", player.getName())).replace("{STAT}", petItems.getItemType().getDisplayName()));
                        if (petItems.getItemType().isPlaySoundEnabled()) {
                            petItems.getItemType().getFulledSound().playSound(player, 1.0f, 2.0f);
                        }
                        inventoryClickEvent.setCancelled(true);
                        return;
                    }
                    if (stat + incrementByInterest >= 100) {
                        incrementByInterest = 100 - stat;
                    }
                    provideCarePet.getPetAttribute().addStat(petItems.getItemType(), incrementByInterest);
                    playerManager.updatePetData(provideCarePet);
                    playerManager.removePetItems(petItems, 1);
                    player.sendMessage(petInterest.getMessageByType(petItems.getItemType()).getFormatMessage().replace("{PET_NAME}", provideCarePet.getPetName().replace("{PLAYER}", player.getName())).replace("{ITEM}", petItems.getDisplayName()).replace("{INCREMENT}", String.valueOf(incrementByInterest)));
                    if (petItems.getItemType().isPlaySoundEnabled()) {
                        petItems.getItemType().getFeedSound().playSound(player, 1.0f, 2.0f);
                    }
                    openPetItemsMenu(playerManager, provideCarePet);
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                catch (Exception ex) {
                    player.sendMessage(MessageType.ERROR.getFormatMessage());
                    ex.printStackTrace();
                }
            }
        }
        inventoryClickEvent.setCancelled(true);
    }
}
