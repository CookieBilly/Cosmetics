

package ws.billy.CookieGadgets.menu.menus;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.discount.ItemCostDiscount;
import java.util.Iterator;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.api.CookieGadgetsAPI;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.discount.DiscountManager;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.StringUtils;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.cosmetics.emotes.EmoteType;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Listener;

public class EmotesMenu implements Listener
{
    public static void openEmotesMenu(final PlayerManager playerManager, int n) {
        final int size = EmoteType.enabled().size();
        final int maxPagesAmount = GInventory.getMaxPagesAmount(21, size);
        if (n <= 0) {
            n = 1;
        }
        if (n > 1 && maxPagesAmount < n) {
            n = 1;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.format(String.valueOf(Category.EMOTES.getGUIName()) + StringUtils.addPlaceholders(EnumItem.PAGES.getDisplayName(), Arrays.asList("{CURRENT_PAGE}", "{MAX_PAGES}"), Arrays.asList(String.valueOf(n), String.valueOf(maxPagesAmount)))));
        int n2 = 0;
        int n3 = 1;
        if (n > 1) {
            n3 = 21 * (n - 1) + 1;
        }
        int n4 = 21;
        if (size < 21) {
            n4 = size;
        }
        if (n > 1) {
            if (size >= 21 * n) {
                n4 = 21 * n;
            }
            else {
                n4 = size;
            }
        }
        final Player player = playerManager.getPlayer();
        for (int i = n3; i <= n4; ++i) {
            try {
                if (i > size) {
                    break;
                }
                final EmoteType emoteType = EmoteType.enabled().get(i - 1);
                if (PermissionUtils.noPermission(player, emoteType.getPermission(), EnumPermission.EMOTES.getPermission(), false)) {
                    final DiscountManager discountManager = new DiscountManager(playerManager, Category.EMOTES.isPurchasable() && emoteType.isPurchasable(), emoteType.getMysteryDust());
                    if (EnumItem.NO_PERMISSION.showCustomItem()) {
                        InventoryUtils.inventory(inventory, emoteType.getDisplayName(), EnumItem.NO_PERMISSION.getCustomItem(), emoteType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_21.getLayOut()[n2++]);
                    }
                    else {
                        InventoryUtils.inventory(inventory, emoteType.getDisplayName(), emoteType.getMaterialHead().getMaterial(), emoteType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_21.getLayOut()[n2++]);
                    }
                }
                else {
                    InventoryUtils.inventoryAddGlow(inventory, emoteType.getDisplayName(), emoteType.getMaterialHead().getMaterial(), emoteType.getUnlockedLore(), EnumItem.HAS_PERMISSION.isShowInLore() ? EnumItem.HAS_PERMISSION.getLore() : null, GInventory.LAY_OUT_21.getLayOut()[n2++], (playerManager.getEquippedEmote() == null) ? null : playerManager.getEquippedEmote().getDisplayName());
                }
            }
            catch (Exception ex) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                ex.printStackTrace();
                return;
            }
        }
        if (EnumItem.RESET_EMOTE.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RESET_EMOTE.getItemStack(), EnumItem.RESET_EMOTE.getSlot());
        }
        if (n == 1) {
            if (EnumItem.GO_BACK.show()) {
                InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot());
            }
        }
        else {
            InventoryUtils.inventory(inventory, EnumItem.PREVIOUS_PAGE.getItemStack(), "{PAGE}", String.valueOf(n - 1), EnumItem.PREVIOUS_PAGE.getSlot());
        }
        if (n < maxPagesAmount) {
            InventoryUtils.inventory(inventory, EnumItem.NEXT_PAGE.getItemStack(), "{PAGE}", String.valueOf(n + 1), EnumItem.NEXT_PAGE.getSlot());
        }
        if (EnumItem.MAIN_MENU_ITEM.show()) {
            InventoryUtils.mainMenuButton(player, inventory, EnumItem.MAIN_MENU_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
        playerManager.setCurrentMenuPage(n);
    }
    
    @EventHandler
    public void onInvClickEmotes(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().startsWith(Category.EMOTES.getGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (!Category.EMOTES.isEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
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
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot())) {
            CookieGadgetsAPI.goBackToMainMenu(player);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RESET_EMOTE.getItemStack(), EnumItem.RESET_EMOTE.getSlot())) {
            playerManager.unequipEmote();
            player.sendMessage(MessageType.RESET_EMOTE.getFormatMessage());
            if (EnumItem.RESET_EMOTE.isPlaySoundEnabled()) {
                EnumItem.RESET_EMOTE.getSound().playSound(player, 1.0f, 2.0f);
            }
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.PREVIOUS_PAGE.getDisplayName(), EnumItem.PREVIOUS_PAGE.getSlot())) {
            openEmotesMenu(playerManager, playerManager.getCurrentMenuPage() - 1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.NEXT_PAGE.getDisplayName(), EnumItem.NEXT_PAGE.getSlot())) {
            openEmotesMenu(playerManager, playerManager.getCurrentMenuPage() + 1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.MAIN_MENU_ITEM.getDisplayName(), EnumItem.MAIN_MENU_ITEM.getSlot())) {
            CookieGadgetsAPI.goBackToMainMenu(player);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        for (final EmoteType emoteType : EmoteType.values()) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(emoteType.getDisplayName()))) {
                if (PermissionUtils.noPermission(player, emoteType.getPermission(), EnumPermission.EMOTES.getPermission(), false)) {
                    if (Category.EMOTES.isPurchasable()) {
                        if (!emoteType.isPurchasable()) {
                            player.sendMessage(MessageType.ITEM_UNPURCHASABLE.getFormatMessage());
                            if (EnumItem.ITEM_UNPURCHASABLE.isPlaySoundEnabled()) {
                                EnumItem.ITEM_UNPURCHASABLE.getSound().playSound(player, 1.0f, 0.5f);
                            }
                            player.closeInventory();
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        final ItemCostDiscount itemCostDiscount = playerManager.getItemCostDiscount();
                        int n = emoteType.getMysteryDust();
                        if (itemCostDiscount != null) {
                            n = itemCostDiscount.getPriceAfterDiscount(emoteType.getMysteryDust());
                        }
                        if (playerManager.getMysteryDust() >= n) {
                            playerManager.purchaseData().setData(Category.EMOTES, emoteType.getName(), emoteType.getDisplayName(), itemCostDiscount, emoteType.getMysteryDust(), emoteType.getPermission());
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
                        if (PermissionUtils.noPermission(player, emoteType.getPermission(), EnumPermission.EMOTES.getPermission(), true)) {
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
                        if (playerManager.getEquippedEmote() != null && ChatUtil.format(emoteType.getDisplayName()).equals(playerManager.getEquippedEmote().getDisplayName())) {
                            playerManager.unequipEmote();
                            player.sendMessage(MessageType.RESET_EMOTE.getFormatMessage());
                            if (EnumItem.RESET_EMOTE.isPlaySoundEnabled()) {
                                EnumItem.RESET_EMOTE.getSound().playSound(player, 1.0f, 2.0f);
                            }
                            player.closeInventory();
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        if (!emoteType.checkRequirement(playerManager)) {
                            if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                            }
                            player.closeInventory();
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        playerManager.equipEmote(emoteType);
                        player.sendMessage(MessageType.SELECT_EMOTE.getFormatMessage().replace("{EMOTE}", emoteType.getDisplayNameStripColor()));
                        if (EnumItem.HAS_PERMISSION.isPlaySoundEnabled()) {
                            EnumItem.HAS_PERMISSION.getSound().playSound(player, 1.0f, 2.0f);
                        }
                        if (EnumItem.HAS_PERMISSION.isCloseGUIMenuAfterSelect()) {
                            player.closeInventory();
                        }
                        else {
                            openEmotesMenu(playerManager, playerManager.getCurrentMenuPage());
                        }
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
    }
}
