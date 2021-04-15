// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.menu.menus.mysteryboxes;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.EnumItem;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class GiftInventoryMenu implements Listener
{
    public static void openGiftInventoryMenu(final Player player, int currentMenuPage) {
        final int giftPacks = CookieGadgets.getPlayerManager(player).getGiftPacks();
        final int maxPagesAmount = GInventory.getMaxPagesAmount(36, giftPacks);
        if (currentMenuPage <= 0) {
            currentMenuPage = 1;
        }
        if (currentMenuPage > 1 && maxPagesAmount < currentMenuPage) {
            currentMenuPage = 1;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, CookieGadgets.getCookieGadgetsData().getGiftInventoryGUIName());
        int n = 0;
        int n2 = 1;
        if (currentMenuPage > 1) {
            n2 = 36 * (currentMenuPage - 1) + 1;
        }
        int n3 = 36;
        if (giftPacks < 36) {
            n3 = giftPacks;
        }
        if (currentMenuPage > 1) {
            if (giftPacks >= 36 * currentMenuPage) {
                n3 = 36 * currentMenuPage;
            }
            else {
                n3 = giftPacks;
            }
        }
        if (giftPacks > 0) {
            for (int i = n2; i <= n3; ++i) {
                try {
                    if (i > giftPacks) {
                        break;
                    }
                    InventoryUtils.inventory(inventory, EnumItem.MYSTERY_GIFT.getDisplayName(), EnumItem.MYSTERY_GIFT.getMaterial(), 5, EnumItem.MYSTERY_GIFT.getLore(), GInventory.LAY_OUT_36_I.getLayOut()[n++]);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    player.sendMessage(MessageType.ERROR.getFormatMessage());
                    return;
                }
            }
        }
        else {
            InventoryUtils.inventory(inventory, EnumItem.NO_MYSTERY_GIFT_ERROR.getDisplayName(), EnumItem.NO_MYSTERY_GIFT_ERROR.getMaterial(), EnumItem.NO_MYSTERY_GIFT_ERROR.getLore(), EnumItem.NO_MYSTERY_GIFT_ERROR.getSlot());
        }
        if (EnumItem.GO_BACK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), 49);
        }
        if (currentMenuPage > 1) {
            InventoryUtils.inventory(inventory, EnumItem.PREVIOUS_PAGE.getItemStack(), "{PAGE}", String.valueOf(currentMenuPage - 1), 45);
        }
        if (currentMenuPage < maxPagesAmount) {
            InventoryUtils.inventory(inventory, EnumItem.NEXT_PAGE.getItemStack(), "{PAGE}", String.valueOf(currentMenuPage + 1), 53);
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
        CookieGadgets.getPlayerManager(player).setCurrentMenuPage(currentMenuPage);
    }
    
    @EventHandler
    public void onInvClickGiftInventoryMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(CookieGadgets.getCookieGadgetsData().getGiftInventoryGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            inventoryClickEvent.setCancelled(true);
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
        if (!CookieGadgets.getCookieGadgetsData().isMysteryBoxesEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
            SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(player, 1.0f, 0.5f);
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.GO_BACK.getItemStack(), 49)) {
            CookieGadgets.getPlayerManager(player).openMysteryVaultMenu(1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.PREVIOUS_PAGE.getDisplayName(), 45)) {
            openGiftInventoryMenu(player, CookieGadgets.getPlayerManager(player).getCurrentMenuPage() - 1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.NEXT_PAGE.getDisplayName(), 53)) {
            openGiftInventoryMenu(player, CookieGadgets.getPlayerManager(player).getCurrentMenuPage() + 1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(EnumItem.MYSTERY_GIFT.getDisplayName()))) {
            int[] layOut;
            for (int length = (layOut = GInventory.LAY_OUT_36_I.getLayOut()).length, i = 0; i < length; ++i) {
                if (ItemUtils.getCurrentItem(inventoryClickEvent, layOut[i])) {
                    SendGiftMenu.openSendGiftMenu(player, 1);
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
            }
        }
        inventoryClickEvent.setCancelled(true);
    }
}
