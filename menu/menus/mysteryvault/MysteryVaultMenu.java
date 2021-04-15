

package ws.billy.CookieGadgets.menu.menus.mysteryvault;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.ConfirmOpenMysteryBoxMenu;
import ws.billy.CookieGadgets.utils.mysteryboxes.MysteryBoxType;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.OpenMultipleBoxesMenu;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.GiftInventoryMenu;
import ws.billy.CookieGadgets.menu.menus.mysteryboxes.MysteryBoxesCraftingMenu;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.inventory.Inventory;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxes;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class MysteryVaultMenu implements Listener
{
    public static void openMysteryVaultMenu(final Player player, int currentMenuPage) {
        final int size = CookieGadgets.getPlayerManager(player).mysteryBoxes().size();
        final int maxPagesAmount = GInventory.getMaxPagesAmount(36, size);
        if (currentMenuPage <= 0) {
            currentMenuPage = 1;
        }
        if (currentMenuPage > 1 && maxPagesAmount < currentMenuPage) {
            currentMenuPage = 1;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.format(CookieGadgets.getCookieGadgetsData().getMysteryVaultGUIName()));
        int n = 0;
        int n2 = 1;
        if (currentMenuPage > 1) {
            n2 = 36 * (currentMenuPage - 1) + 1;
        }
        int n3 = 36;
        if (size < 36) {
            n3 = size;
        }
        if (currentMenuPage > 1) {
            if (size >= 36 * currentMenuPage) {
                n3 = 36 * currentMenuPage;
            }
            else {
                n3 = size;
            }
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (playerManager.isLoaded()) {
            if (size > 0) {
                for (int i = n2; i <= n3; ++i) {
                    try {
                        if (i > size) {
                            break;
                        }
                        final MysteryBoxes o = playerManager.mysteryBoxes().get(i - 1);
                        if (o.isExpirable() && o.isExpired()) {
                            playerManager.mysteryBoxes().remove(o);
                            --i;
                            --n3;
                        }
                        else {
                            InventoryUtils.inventory(inventory, o.getMysteryBoxType().getDisplayName(), o.getMysteryBoxType().getMaterial(), o.getLore(), GInventory.LAY_OUT_36_I.getLayOut()[n++]);
                        }
                    }
                    catch (Exception ex) {
                        player.sendMessage(MessageType.ERROR.getFormatMessage());
                        ex.printStackTrace();
                        return;
                    }
                }
            }
            else {
                InventoryUtils.inventory(inventory, EnumItem.NO_MYSTERY_BOX_ERROR.getDisplayName(), EnumItem.NO_MYSTERY_BOX_ERROR.getMaterial(), EnumItem.NO_MYSTERY_BOX_ERROR.getLore(), EnumItem.NO_MYSTERY_BOX_ERROR.getSlot());
            }
        }
        else {
            InventoryUtils.inventory(inventory, EnumItem.LOADING_MYSTERY_BOXES.getDisplayName(), EnumItem.LOADING_MYSTERY_BOXES.getMaterial(), EnumItem.LOADING_MYSTERY_BOXES.getLore(), EnumItem.LOADING_MYSTERY_BOXES.getSlot());
        }
        if (currentMenuPage > 1) {
            InventoryUtils.inventory(inventory, EnumItem.PREVIOUS_PAGE.getItemStack(), "{PAGE}", String.valueOf(currentMenuPage - 1), EnumItem.PREVIOUS_PAGE.getSlot());
        }
        if (currentMenuPage < maxPagesAmount && n == 36) {
            InventoryUtils.inventory(inventory, EnumItem.NEXT_PAGE.getItemStack(), "{PAGE}", String.valueOf(currentMenuPage + 1), EnumItem.NEXT_PAGE.getSlot());
        }
        if (EnumItem.GIFT_INVENTORY.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GIFT_INVENTORY.getItemStack(), Arrays.asList("{GIFT_SENT}", "{GIFT_RECEIVED}"), Arrays.asList(playerManager.isLoaded() ? String.valueOf(playerManager.getGiftSent()) : MessageType.LOADING.getFormatMessage(), playerManager.isLoaded() ? String.valueOf(playerManager.getGiftReceived()) : MessageType.LOADING.getFormatMessage()), EnumItem.GIFT_INVENTORY.getSlot());
        }
        final ArrayList<String> list = new ArrayList<String>();
        list.add(String.valueOf(playerManager.getMysteryDust()));
        list.add(String.valueOf(playerManager.getMysteryBoxes()));
        for (int j = 0; j < 5; ++j) {
            if (playerManager.getRecentLootsFound() == null || playerManager.getRecentLootsFound().length < j + 1 || playerManager.getRecentLootsFound()[j] == null || (playerManager.getRecentLootsFound()[j] != null && playerManager.getRecentLootsFound()[j].toString().equals(""))) {
                list.add(MessageType.NO_RECENT_LOOT_FOUND.getFormatMessage());
            }
            else {
                list.add(ChatUtil.format(playerManager.getRecentLootsFound()[j]));
            }
        }
        InventoryUtils.inventory(inventory, EnumItem.MYSTERY_BOX_INFORMATION.getItemStack(), Arrays.asList("{MYSTERY_DUST}", "{MYSTERY_BOXES}", "{RECENT_LOOT_1}", "{RECENT_LOOT_2}", "{RECENT_LOOT_3}", "{RECENT_LOOT_4}", "{RECENT_LOOT_5}"), list, EnumItem.MYSTERY_BOX_INFORMATION.getSlot());
        if (EnumItem.CRAFT_MYSTERY_BOXES.show()) {
            InventoryUtils.inventory(inventory, EnumItem.CRAFT_MYSTERY_BOXES.getItemStack(), Arrays.asList("{MYSTERY_DUST}", "{MYSTERY_BOXES}"), Arrays.asList(String.valueOf(playerManager.getMysteryDust()), String.valueOf(playerManager.getMysteryBoxes())), EnumItem.CRAFT_MYSTERY_BOXES.getSlot());
        }
        if (EnumItem.MYSTERY_VAULT_ANIMATIONS.show()) {
            InventoryUtils.inventory(inventory, EnumItem.MYSTERY_VAULT_ANIMATIONS.getDisplayName(), EnumItem.MYSTERY_VAULT_ANIMATIONS.getMaterial(), EnumItem.MYSTERY_VAULT_ANIMATIONS.getLore(), EnumItem.MYSTERY_VAULT_ANIMATIONS.getSlot());
        }
        if (EnumItem.OPEN_MULTIPLE_BOXES_ITEM.show()) {
            InventoryUtils.inventory(inventory, EnumItem.OPEN_MULTIPLE_BOXES_ITEM.getDisplayName(), EnumItem.OPEN_MULTIPLE_BOXES_ITEM.getMaterial(), EnumItem.OPEN_MULTIPLE_BOXES_ITEM.getLore(), EnumItem.OPEN_MULTIPLE_BOXES_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
        playerManager.setCurrentMenuPage(currentMenuPage);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInvClickMysteryVault(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (inventoryClickEvent.getView().getTitle().startsWith(ChatUtil.format(CookieGadgets.getCookieGadgetsData().getMysteryVaultGUIName()))) {
            if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (inventoryClickEvent.getCurrentItem() == null || inventoryClickEvent.getCurrentItem().getItemMeta() == null || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName() == null) {
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (inventoryClickEvent.getClick() == ClickType.SHIFT_LEFT || inventoryClickEvent.getClick() == ClickType.SHIFT_RIGHT || inventoryClickEvent.getClick() == ClickType.NUMBER_KEY || inventoryClickEvent.getClick() == ClickType.UNKNOWN) {
                player.updateInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (!CookieGadgets.getCookieGadgetsData().isMysteryBoxesEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
                SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(player, 1.0f, 0.5f);
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.PREVIOUS_PAGE.getDisplayName(), EnumItem.PREVIOUS_PAGE.getSlot())) {
                openMysteryVaultMenu(player, playerManager.getCurrentMenuPage() - 1);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.NEXT_PAGE.getDisplayName(), EnumItem.NEXT_PAGE.getSlot())) {
                openMysteryVaultMenu(player, playerManager.getCurrentMenuPage() + 1);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.CRAFT_MYSTERY_BOXES.getDisplayName(), EnumItem.CRAFT_MYSTERY_BOXES.getSlot())) {
                MysteryBoxesCraftingMenu.openMysteryBoxesCraftingMenu(player);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.GIFT_INVENTORY.getDisplayName(), EnumItem.GIFT_INVENTORY.getSlot()) && playerManager.isLoaded()) {
                GiftInventoryMenu.openGiftInventoryMenu(player, 1);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.LOADING_MYSTERY_BOXES.getDisplayName(), EnumItem.LOADING_MYSTERY_BOXES.getSlot())) {
                openMysteryVaultMenu(player, 1);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.MYSTERY_VAULT_ANIMATIONS.getDisplayName(), EnumItem.MYSTERY_VAULT_ANIMATIONS.getSlot())) {
                MysteryVaultAnimationsMenu.openMysteryVaultAnimationsMenu(player);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.OPEN_MULTIPLE_BOXES_ITEM.getDisplayName(), EnumItem.OPEN_MULTIPLE_BOXES_ITEM.getSlot())) {
                if (!PermissionUtils.containsPermission(player, Arrays.asList(EnumPermission.OPEN_MULTIPLE_BOXES.getPermission(), EnumPermission.OPEN_20_BOXES.getPermission(), EnumPermission.OPEN_50_BOXES.getPermission(), EnumPermission.OPEN_250_BOXES.getPermission()))) {
                    if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                        EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                    }
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                OpenMultipleBoxesMenu.openOpenMultipleBoxesMenu(player);
                inventoryClickEvent.setCancelled(true);
            }
            else {
                if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(MysteryBoxType.NORMAL_MYSTERY_BOX_1.getDisplayName())) || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(MysteryBoxType.GIFTED_MYSTERY_BOX_1.getDisplayName())) || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(MysteryBoxType.CRAFTED_MYSTERY_BOX_1.getDisplayName())) || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(MysteryBoxType.CRAFTED_MYSTERY_BOX_2.getDisplayName())) || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(MysteryBoxType.CRAFTED_MYSTERY_BOX_3.getDisplayName())) || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(MysteryBoxType.CRAFTED_MYSTERY_BOX_4.getDisplayName())) || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(MysteryBoxType.CRAFTED_MYSTERY_BOX_5.getDisplayName()))) {
                    if (playerManager.isOpeningMysteryBox()) {
                        player.sendMessage(MessageType.CAN_ONLY_OPEN_ONE_MYSTERY_BOX.getFormatMessage());
                        player.closeInventory();
                        inventoryClickEvent.setCancelled(true);
                        return;
                    }
                    if (playerManager.getSelectedMysteryVault() == null) {
                        player.sendMessage(MessageType.ERROR.getFormatMessage());
                        player.closeInventory();
                        inventoryClickEvent.setCancelled(true);
                        return;
                    }
                    if (playerManager.getSelectedMysteryVault().isActivated()) {
                        player.sendMessage(MessageType.OPEN_MYSTERY_VAULT_AT_A_TIME.getFormatMessage());
                        player.closeInventory();
                        inventoryClickEvent.setCancelled(true);
                        return;
                    }
                    int n = 0;
                    final int currentMenuPage = playerManager.getCurrentMenuPage();
                    final int[] layOut;
                    final int length = (layOut = GInventory.LAY_OUT_36_I.getLayOut()).length;
                    int i = 0;
                    while (i < length) {
                        if (ItemUtils.getCurrentItem(inventoryClickEvent, layOut[i])) {
                            MysteryBoxes selectedMysteryBox;
                            try {
                                selectedMysteryBox = playerManager.mysteryBoxes().get((currentMenuPage == 1) ? n : ((currentMenuPage - 1) * 36 + n));
                            }
                            catch (IndexOutOfBoundsException ex) {
                                break;
                            }
                            if (selectedMysteryBox.isExpirable() && selectedMysteryBox.isExpired()) {
                                openMysteryVaultMenu(player, currentMenuPage);
                                player.sendMessage(MessageType.MYSTERY_BOX_WAS_EXPIRED.getFormatMessage());
                                inventoryClickEvent.setCancelled(true);
                                return;
                            }
                            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(MysteryBoxType.NORMAL_MYSTERY_BOX_1.getDisplayName())) && selectedMysteryBox.isRequiredPermission() && PermissionUtils.noPermission(player, selectedMysteryBox.getMysteryBoxType().getPermission(), EnumPermission.OPEN_ALL_MYSTERY_BOXES.getPermission(), false)) {
                                player.sendMessage(selectedMysteryBox.getMysteryBoxType().getNoPermissionMessage());
                                if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                                }
                                if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                                    player.closeInventory();
                                }
                                inventoryClickEvent.setCancelled(true);
                                return;
                            }
                            playerManager.setSelectedMysteryBox(selectedMysteryBox);
                            ConfirmOpenMysteryBoxMenu.openConfirmOpenMysteryBoxMenu(player);
                            inventoryClickEvent.setCancelled(true);
                            break;
                        }
                        else {
                            ++n;
                            ++i;
                        }
                    }
                    inventoryClickEvent.setCancelled(true);
                }
                inventoryClickEvent.setCancelled(true);
            }
        }
    }
}
