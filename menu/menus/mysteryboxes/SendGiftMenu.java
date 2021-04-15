

package ws.billy.CookieGadgets.menu.menus.mysteryboxes;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.util.Iterator;
import org.bukkit.inventory.Inventory;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.Plugin;
import java.util.List;
import ws.billy.CookieGadgets.utils.GMaterial;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.EnumItem;
import org.bukkit.inventory.InventoryHolder;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.GInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SendGiftMenu implements Listener
{
    public static void openSendGiftMenu(final Player player, int currentMenuPage) {
        final int size = Bukkit.getOnlinePlayers().size();
        final int maxPagesAmount = GInventory.getMaxPagesAmount(36, size);
        if (currentMenuPage <= 0) {
            currentMenuPage = 1;
        }
        if (currentMenuPage > 1 && maxPagesAmount < currentMenuPage) {
            currentMenuPage = 1;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, CookieGadgets.getCookieGadgetsData().getSendGiftGUIName());
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
        InventoryUtils.inventory(inventory, EnumItem.MYSTERY_GIFT_SEND_GIFT.getDisplayName(), EnumItem.MYSTERY_GIFT_SEND_GIFT.getMaterial(), EnumItem.MYSTERY_GIFT_SEND_GIFT.getLore(), EnumItem.MYSTERY_GIFT_SEND_GIFT.getSlot());
        int n4 = 0;
        for (final Player player2 : Bukkit.getOnlinePlayers()) {
            if (player2 != player) {
                if (!player2.isOnline()) {
                    continue;
                }
                if (++n4 < n2 || n4 > n3) {
                    continue;
                }
                try {
                    InventoryUtils.inventory(inventory, MessageType.LOADING.getFormatMessage(), new GMaterial(EnumMaterial.PLAYER_HEAD), null, GInventory.LAY_OUT_36_II.getLayOut()[n++]);
                    final Inventory inventory2;
                    final Player player3;
                    final int n5;
                    Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> InventoryUtils.inventorySkull(inventory2, EnumItem.PLAYER_SEND_GIFT.getDisplayName().replace("{PLAYER}", player3.getName()), EnumItem.PLAYER_SEND_GIFT.getLore(), null, player3, n5));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    player.sendMessage(MessageType.ERROR.getFormatMessage());
                    return;
                }
            }
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
        if (!CookieGadgets.getPlayerManager(player).isRefreshTaskActivated()) {
            new BukkitRunnable() {
                public void run() {
                    if (player.getOpenInventory().getType() != InventoryType.CHEST || player.getOpenInventory() == null || player.getOpenInventory().getTopInventory() == null || !player.getOpenInventory().getTitle().equals(CookieGadgets.getCookieGadgetsData().getSendGiftGUIName())) {
                        CookieGadgets.getPlayerManager(player).setRefreshTaskActivated(false);
                        this.cancel();
                        return;
                    }
                    SendGiftMenu.openSendGiftMenu(player, CookieGadgets.getPlayerManager(player).getCurrentMenuPage());
                }
            }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 200L, 200L);
            CookieGadgets.getPlayerManager(player).setRefreshTaskActivated(true);
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
        CookieGadgets.getPlayerManager(player).setCurrentMenuPage(currentMenuPage);
    }
    
    @EventHandler
    public void onInvClickSendGiftMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(CookieGadgets.getCookieGadgetsData().getSendGiftGUIName())) {
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
            GiftInventoryMenu.openGiftInventoryMenu(player, 1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.PREVIOUS_PAGE.getDisplayName(), 45)) {
            openSendGiftMenu(player, CookieGadgets.getPlayerManager(player).getCurrentMenuPage() - 1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.NEXT_PAGE.getDisplayName(), 53)) {
            openSendGiftMenu(player, CookieGadgets.getPlayerManager(player).getCurrentMenuPage() + 1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        final int[] layOut;
        final int length = (layOut = GInventory.LAY_OUT_36_II.getLayOut()).length;
        int i = 0;
        while (i < length) {
            if (ItemUtils.getCurrentItem(inventoryClickEvent, layOut[i]) && !ChatUtil.stripColor(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName()).equals("")) {
                final Player player2 = Bukkit.getPlayer(ChatUtil.stripColor(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName()));
                if (player2 == null || player2 == player || !player2.isOnline()) {
                    player.sendMessage(MessageType.ERROR.getFormatMessage());
                    openSendGiftMenu(player, CookieGadgets.getPlayerManager(player).getCurrentMenuPage());
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                CookieGadgets.getPlayerManager(player).setSelectedSendGiftPlayer(player2);
                ConfirmSendGiftMenu.openConfirmSendGiftMenu(player);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            else {
                ++i;
            }
        }
        inventoryClickEvent.setCancelled(true);
    }
}
