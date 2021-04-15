

package ws.billy.CookieGadgets.menu.menus.mysteryboxes;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Event;
import ws.billy.CookieGadgets.api.event.mysteryboxes.PlayerSendMysteryGiftEvent;
import ws.billy.CookieGadgets.utils.mysteryboxes.MysteryBoxType;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import java.util.List;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.EnumItem;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ConfirmSendGiftMenu implements Listener
{
    public static void openConfirmSendGiftMenu(final Player player) {
        final Player selectedSendGiftPlayer = CookieGadgets.getPlayerManager(player).getSelectedSendGiftPlayer();
        if (selectedSendGiftPlayer == null || !selectedSendGiftPlayer.isOnline()) {
            player.sendMessage(MessageType.ERROR.getFormatMessage());
            return;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 45, CookieGadgets.getCookieGadgetsData().getConfirmSendGiftGUIName());
        InventoryUtils.inventory(inventory, EnumItem.MYSTERY_GIFT_CONFIRM_SEND_GIFT.getDisplayName(), EnumItem.MYSTERY_GIFT_CONFIRM_SEND_GIFT.getMaterial(), EnumItem.MYSTERY_GIFT_CONFIRM_SEND_GIFT.getLore(), EnumItem.MYSTERY_GIFT_CONFIRM_SEND_GIFT.getSlot());
        InventoryUtils.inventorySkull(inventory, EnumItem.PLAYER_CONFIRM_SEND_GIFT.getDisplayName().replace("{PLAYER}", selectedSendGiftPlayer.getName()), EnumItem.PLAYER_CONFIRM_SEND_GIFT.getLore(), null, selectedSendGiftPlayer, EnumItem.PLAYER_CONFIRM_SEND_GIFT.getSlot());
        InventoryUtils.inventory(inventory, EnumItem.CONFIRM_SEND_GIFT.getItemStack(), EnumItem.CONFIRM_SEND_GIFT.getSlot());
        InventoryUtils.inventory(inventory, EnumItem.CANCEL_SEND_GIFT.getItemStack(), EnumItem.CANCEL_SEND_GIFT.getSlot());
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInvClickConfirmSendGiftMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(CookieGadgets.getCookieGadgetsData().getConfirmSendGiftGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 45 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
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
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.CONFIRM_SEND_GIFT.getItemStack(), EnumItem.CONFIRM_SEND_GIFT.getSlot())) {
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
            final Player selectedSendGiftPlayer = playerManager.getSelectedSendGiftPlayer();
            if (selectedSendGiftPlayer == null) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (!selectedSendGiftPlayer.isOnline()) {
                player.sendMessage(MessageType.PLAYER_IS_OFFLINE.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            for (int i = 1; i <= 5; ++i) {
                int nextInt = CookieGadgets.random().nextInt(5);
                ++nextInt;
                final MysteryBoxType valueOfByName = MysteryBoxType.valueOfByName("Gifted Mystery Box #" + nextInt);
                if (valueOfByName == null) {
                    player.sendMessage(MessageType.ERROR.getFormatMessage());
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                CookieGadgets.getPlayerManager(selectedSendGiftPlayer).giveMysteryBoxes(valueOfByName, null, false, player.getName(), 1);
            }
            playerManager.removeGiftPacks(1);
            CookieGadgets.getPlayerManager(selectedSendGiftPlayer).addGiftReceived(1);
            playerManager.addGiftSent(1);
            Bukkit.getServer().getPluginManager().callEvent((Event)new PlayerSendMysteryGiftEvent(player, selectedSendGiftPlayer));
            player.sendMessage(MessageType.SEND_A_GIFT_TO_PLAYER.getFormatMessage().replace("{PLAYER}", selectedSendGiftPlayer.getName()));
            selectedSendGiftPlayer.sendMessage(MessageType.RECEIVED_GIFT.getFormatMessage().replace("{PLAYER}", player.getName()));
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
        }
        else {
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.CANCEL_SEND_GIFT.getItemStack(), EnumItem.CANCEL_SEND_GIFT.getSlot())) {
                CookieGadgets.getPlayerManager(player).setSelectedSendGiftPlayer(null);
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            inventoryClickEvent.setCancelled(true);
        }
    }
}
