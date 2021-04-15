// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.menu.menus;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import ws.billy.CookieGadgets.utils.discount.ItemCostDiscount;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import ws.billy.CookieGadgets.api.CookieGadgetsAPI;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import ws.billy.CookieGadgets.utils.GMaterial;
import org.bukkit.entity.Player;
import java.util.List;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.discount.DiscountManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.cosmetics.suits.SuitEquipmentType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Listener;

public class SuitEquipmentMenu implements Listener
{
    private static int[] INVENTORY_SLOTS;
    
    static {
        SuitEquipmentMenu.INVENTORY_SLOTS = new int[] { 4, 13, 22, 31 };
    }
    
    public static void openSuitEquipmentMenu(final PlayerManager playerManager, final SuitType selectedCategorySuit) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.format(selectedCategorySuit.getGUIName()));
        final List<SuitEquipmentType> suitCategory = SuitEquipmentType.getSuitCategory(selectedCategorySuit);
        int n = 0;
        final Player player = playerManager.getPlayer();
        for (int i = 0; i <= 3; ++i) {
            final SuitEquipmentType suitEquipmentType = suitCategory.get(i);
            final GMaterial gMaterial = (i == 0) ? suitEquipmentType.getHeadMaterial() : suitEquipmentType.getSuitMaterial().getMaterial();
            if (PermissionUtils.noPermission(player, suitEquipmentType.getPermission(), EnumPermission.SUITS.getPermission(), false)) {
                final DiscountManager discountManager = new DiscountManager(playerManager, Category.SUITS.isPurchasable() && suitEquipmentType.isPurchasable(), suitEquipmentType.getMysteryDust());
                if (EnumItem.NO_PERMISSION.showCustomItem()) {
                    InventoryUtils.inventory(inventory, suitEquipmentType.getDisplayName(), EnumItem.NO_PERMISSION.getCustomItem(), suitEquipmentType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), SuitEquipmentMenu.INVENTORY_SLOTS[n++]);
                }
                else {
                    InventoryUtils.inventory(inventory, suitEquipmentType.getDisplayName(), gMaterial, suitEquipmentType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), SuitEquipmentMenu.INVENTORY_SLOTS[n++]);
                }
            }
            else {
                final SuitEquipmentType suitEquipmentType2 = playerManager.getEquippedSuitEquipment().get(suitEquipmentType.getSuitMaterial().getArmorType());
                InventoryUtils.inventoryAddGlow(inventory, suitEquipmentType.getDisplayName(), gMaterial, suitEquipmentType.getUnlockedLore(), EnumItem.HAS_PERMISSION.isShowInLore() ? EnumItem.HAS_PERMISSION.getLore() : null, SuitEquipmentMenu.INVENTORY_SLOTS[n++], (suitEquipmentType2 == null) ? null : suitEquipmentType2.getDisplayName());
            }
        }
        if (EnumItem.RESET_SUIT.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RESET_SUIT.getItemStack(), EnumItem.RESET_SUIT.getSlot());
        }
        if (EnumItem.EQUIP_ENTIRE_SUIT.show()) {
            InventoryUtils.inventory(inventory, EnumItem.EQUIP_ENTIRE_SUIT.getItemStack(), EnumItem.EQUIP_ENTIRE_SUIT.getSlot());
        }
        if (EnumItem.GO_BACK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot());
        }
        if (EnumItem.MAIN_MENU_ITEM.show()) {
            InventoryUtils.mainMenuButton(player, inventory, EnumItem.MAIN_MENU_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
        playerManager.setSelectedCategorySuit(selectedCategorySuit);
    }
    
    @EventHandler
    public void onInvClickSuitEquipment(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        final Iterator<SuitType> iterator = SuitType.values().iterator();
        while (iterator.hasNext()) {
            if (inventoryClickEvent.getView().getTitle().equals(ChatUtil.format(iterator.next().getGUIName()))) {
                if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (!Category.SUITS.isEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
                    inventoryClickEvent.setCancelled(true);
                    player.closeInventory();
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
                if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot())) {
                    CookieGadgets.getPlayerManager(player).openSuitsMenu();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
                if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RESET_SUIT.getItemStack(), EnumItem.RESET_SUIT.getSlot())) {
                    playerManager.unequipSuit();
                    player.sendMessage(MessageType.RESET_SUIT.getFormatMessage());
                    if (EnumItem.RESET_SUIT.isPlaySoundEnabled()) {
                        EnumItem.RESET_SUIT.getSound().playSound(player, 1.0f, 2.0f);
                    }
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.MAIN_MENU_ITEM.getDisplayName(), EnumItem.MAIN_MENU_ITEM.getSlot())) {
                    CookieGadgetsAPI.goBackToMainMenu(player);
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (!ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.EQUIP_ENTIRE_SUIT.getItemStack(), EnumItem.EQUIP_ENTIRE_SUIT.getSlot())) {
                    for (final SuitEquipmentType suitEquipmentType : SuitEquipmentType.values()) {
                        if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(suitEquipmentType.getDisplayName()))) {
                            if (PermissionUtils.noPermission(player, suitEquipmentType.getPermission(), EnumPermission.SUITS.getPermission(), false)) {
                                if (Category.SUITS.isPurchasable()) {
                                    if (!suitEquipmentType.isPurchasable()) {
                                        player.sendMessage(MessageType.ITEM_UNPURCHASABLE.getFormatMessage());
                                        if (EnumItem.ITEM_UNPURCHASABLE.isPlaySoundEnabled()) {
                                            EnumItem.ITEM_UNPURCHASABLE.getSound().playSound(player, 1.0f, 0.5f);
                                        }
                                        player.closeInventory();
                                        inventoryClickEvent.setCancelled(true);
                                        return;
                                    }
                                    final ItemCostDiscount itemCostDiscount = playerManager.getItemCostDiscount();
                                    int n = suitEquipmentType.getMysteryDust();
                                    if (itemCostDiscount != null) {
                                        n = itemCostDiscount.getPriceAfterDiscount(suitEquipmentType.getMysteryDust());
                                    }
                                    if (playerManager.getMysteryDust() >= n) {
                                        playerManager.purchaseData().setData(Category.SUITS, suitEquipmentType.getName(), suitEquipmentType.getDisplayName(), itemCostDiscount, suitEquipmentType.getMysteryDust(), suitEquipmentType.getPermission());
                                        playerManager.openItemPurchaseMenu();
                                    }
                                    else {
                                        player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_DUST_TO_PURCHASE.getFormatMessage());
                                        if (EnumItem.NOT_ENOUGH_MYSTERY_DUST.isPlaySoundEnabled()) {
                                            EnumItem.NOT_ENOUGH_MYSTERY_DUST.getSound().playSound(player, 1.0f, 0.5f);
                                        }
                                        player.closeInventory();
                                    }
                                    inventoryClickEvent.setCancelled(true);
                                    return;
                                }
                                else {
                                    if (PermissionUtils.noPermission(player, suitEquipmentType.getPermission(), EnumPermission.SUITS.getPermission(), true)) {
                                        if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                            EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                                        }
                                        if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                                            player.closeInventory();
                                        }
                                        inventoryClickEvent.setCancelled(true);
                                        return;
                                    }
                                    continue;
                                }
                            }
                            else {
                                try {
                                    final EnumArmorType armorType = suitEquipmentType.getSuitMaterial().getArmorType();
                                    if (playerManager.getEquippedSuitEquipment().get(armorType) != null && suitEquipmentType == playerManager.getEquippedSuitEquipment().get(armorType)) {
                                        playerManager.unequipSuitEquipment(armorType);
                                        player.sendMessage(armorType.getResetSuitMessage().getFormatMessage());
                                        if (EnumItem.RESET_SUIT.isPlaySoundEnabled()) {
                                            EnumItem.RESET_SUIT.getSound().playSound(player, 1.0f, 2.0f);
                                        }
                                        openSuitEquipmentMenu(playerManager, suitEquipmentType.getSuitCategory());
                                        inventoryClickEvent.setCancelled(true);
                                        return;
                                    }
                                    if (!suitEquipmentType.checkRequirement(playerManager)) {
                                        if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                            EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                                        }
                                        player.closeInventory();
                                        inventoryClickEvent.setCancelled(true);
                                        return;
                                    }
                                    playerManager.equipSuitEquipment(suitEquipmentType);
                                    player.sendMessage(MessageType.SELECT_SUIT.getFormatMessage().replace("{SUIT}", suitEquipmentType.getDisplayNameStripColor()));
                                    if (EnumItem.HAS_PERMISSION.isPlaySoundEnabled()) {
                                        EnumItem.HAS_PERMISSION.getSound().playSound(player, 1.0f, 2.0f);
                                    }
                                    openSuitEquipmentMenu(playerManager, suitEquipmentType.getSuitCategory());
                                    inventoryClickEvent.setCancelled(true);
                                    return;
                                }
                                catch (Exception ex) {
                                    player.sendMessage(MessageType.ERROR.getFormatMessage());
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                final SuitType selectedCategorySuit = playerManager.getSelectedCategorySuit();
                if (selectedCategorySuit == null) {
                    player.sendMessage(MessageType.ERROR.getFormatMessage());
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (CookieGadgets.getCookieGadgetsData().isAntiLagEnabled() && CookieGadgets.getCookieGadgetsData().disableUsageIfLowTPS() && AntiLagChecker.isLowTPS()) {
                    player.sendMessage(MessageType.DISABLE_USAGE_OF_COSMETICS.getFormatMessage());
                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.SUITS)) {
                    player.sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                int i = 0;
                for (final SuitEquipmentType suitEquipmentType2 : SuitEquipmentType.getSuitCategory(selectedCategorySuit)) {
                    if (!PermissionUtils.noPermission(player, suitEquipmentType2.getPermission(), EnumPermission.SUITS.getPermission(), false) && (playerManager.getEquippedSuitEquipment().get(suitEquipmentType2.getSuitMaterial().getArmorType()) == null || suitEquipmentType2 != playerManager.getEquippedSuitEquipment().get(suitEquipmentType2.getSuitMaterial().getArmorType()))) {
                        if (!suitEquipmentType2.checkRequirement(playerManager)) {
                            continue;
                        }
                        playerManager.equipSuitEquipment(suitEquipmentType2);
                        ++i;
                    }
                }
                if (i == 0) {
                    player.sendMessage(MessageType.NO_SUIT_PIECES_TO_EQUIP.getFormatMessage());
                    if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                        EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                    }
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                player.sendMessage(MessageType.SELECT_ENTIRE_SUIT.getFormatMessage().replace("{SUIT}", selectedCategorySuit.getDisplayNameStripColor()).replace("{PIECES}", String.valueOf(i)));
                if (EnumItem.HAS_PERMISSION.isPlaySoundEnabled()) {
                    EnumItem.HAS_PERMISSION.getSound().playSound(player, 1.0f, 2.0f);
                }
                openSuitEquipmentMenu(playerManager, selectedCategorySuit);
                inventoryClickEvent.setCancelled(true);
            }
        }
    }
}
