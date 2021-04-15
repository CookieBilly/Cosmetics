

package ws.billy.CookieGadgets.menu.menus.mysteryboxes;

import java.util.Iterator;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxes;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class OpenMultipleBoxesMenu implements Listener
{
    public static void openOpenMultipleBoxesMenu(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, CookieGadgets.getCookieGadgetsData().getOpenMultipleBoxesGUIName());
        if (PermissionUtils.noPermission(player, EnumPermission.OPEN_20_BOXES.getPermission(), EnumPermission.OPEN_MULTIPLE_BOXES.getPermission(), false)) {
            InventoryUtils.inventory(inventory, EnumItem.OPEN_20_BOXES.getDisplayName(), EnumItem.OPEN_20_BOXES.getMaterial(), EnumItem.OPEN_20_BOXES.getLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, EnumItem.OPEN_20_BOXES.getSlot());
        }
        else {
            InventoryUtils.inventory(inventory, EnumItem.OPEN_20_BOXES.getDisplayName(), EnumItem.OPEN_20_BOXES.getMaterial(), EnumItem.OPEN_20_BOXES.getLore(), EnumItem.OPEN_20_BOXES.getSlot());
        }
        if (PermissionUtils.noPermission(player, EnumPermission.OPEN_50_BOXES.getPermission(), EnumPermission.OPEN_MULTIPLE_BOXES.getPermission(), false)) {
            InventoryUtils.inventory(inventory, EnumItem.OPEN_50_BOXES.getDisplayName(), EnumItem.OPEN_50_BOXES.getMaterial(), EnumItem.OPEN_50_BOXES.getLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, EnumItem.OPEN_50_BOXES.getSlot());
        }
        else {
            InventoryUtils.inventory(inventory, EnumItem.OPEN_50_BOXES.getDisplayName(), EnumItem.OPEN_50_BOXES.getMaterial(), EnumItem.OPEN_50_BOXES.getLore(), EnumItem.OPEN_50_BOXES.getSlot());
        }
        if (PermissionUtils.noPermission(player, EnumPermission.OPEN_250_BOXES.getPermission(), EnumPermission.OPEN_MULTIPLE_BOXES.getPermission(), false)) {
            InventoryUtils.inventory(inventory, EnumItem.OPEN_250_BOXES.getDisplayName(), EnumItem.OPEN_250_BOXES.getMaterial(), EnumItem.OPEN_250_BOXES.getLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, EnumItem.OPEN_250_BOXES.getSlot());
        }
        else {
            InventoryUtils.inventory(inventory, EnumItem.OPEN_250_BOXES.getDisplayName(), EnumItem.OPEN_250_BOXES.getMaterial(), EnumItem.OPEN_250_BOXES.getLore(), EnumItem.OPEN_250_BOXES.getSlot());
        }
        if (EnumItem.GO_BACK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), 49);
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInvClickOpenMultipleBoxesMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(CookieGadgets.getCookieGadgetsData().getOpenMultipleBoxesGUIName())) {
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
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.OPEN_20_BOXES.getItemStack(), EnumItem.OPEN_20_BOXES.getSlot())) {
            if (PermissionUtils.noPermission(player, EnumPermission.OPEN_20_BOXES.getPermission(), EnumPermission.OPEN_MULTIPLE_BOXES.getPermission(), true)) {
                if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                }
                if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                    player.closeInventory();
                }
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (CookieGadgets.getPlayerManager(player).getMysteryBoxes() <= 0) {
                SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(player, 1.0f, 0.5f);
                player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_BOXES.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (hasMysteryBoxesPermission(player) <= 0) {
                player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_BOXES.getFormatMessage());
                if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                }
                if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                    player.closeInventory();
                }
                inventoryClickEvent.setCancelled(true);
                return;
            }
            ConfirmOpenMultipleBoxesMenu.openConfirmOpenMultipleBoxesMenu(player);
            CookieGadgets.getPlayerManager(player).setOpenMultipleBoxesType(ConfirmOpenMultipleBoxesMenu.OpenMultipleBoxesType.OPEN_20_BOXES);
            inventoryClickEvent.setCancelled(true);
        }
        else if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.OPEN_50_BOXES.getItemStack(), EnumItem.OPEN_50_BOXES.getSlot())) {
            if (PermissionUtils.noPermission(player, EnumPermission.OPEN_50_BOXES.getPermission(), EnumPermission.OPEN_MULTIPLE_BOXES.getPermission(), true)) {
                if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                }
                if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                    player.closeInventory();
                }
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (CookieGadgets.getPlayerManager(player).getMysteryBoxes() < 20) {
                SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(player, 1.0f, 0.5f);
                player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_BOXES.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            final int hasMysteryBoxesPermission = hasMysteryBoxesPermission(player);
            if (hasMysteryBoxesPermission > 0 && hasMysteryBoxesPermission >= 20) {
                ConfirmOpenMultipleBoxesMenu.openConfirmOpenMultipleBoxesMenu(player);
                CookieGadgets.getPlayerManager(player).setOpenMultipleBoxesType(ConfirmOpenMultipleBoxesMenu.OpenMultipleBoxesType.OPEN_50_BOXES);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (hasMysteryBoxesPermission < 20) {
                SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(player, 1.0f, 0.5f);
                player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_BOXES.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_BOXES.getFormatMessage());
            if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
            }
            if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                player.closeInventory();
            }
            inventoryClickEvent.setCancelled(true);
        }
        else {
            if (!ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.OPEN_250_BOXES.getItemStack(), EnumItem.OPEN_250_BOXES.getSlot())) {
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (PermissionUtils.noPermission(player, EnumPermission.OPEN_250_BOXES.getPermission(), EnumPermission.OPEN_MULTIPLE_BOXES.getPermission(), true)) {
                if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                }
                if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                    player.closeInventory();
                }
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (CookieGadgets.getPlayerManager(player).getMysteryBoxes() < 50) {
                SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(player, 1.0f, 0.5f);
                player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_BOXES.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            final int hasMysteryBoxesPermission2 = hasMysteryBoxesPermission(player);
            if (hasMysteryBoxesPermission2 > 0 && hasMysteryBoxesPermission2 >= 50) {
                ConfirmOpenMultipleBoxesMenu.openConfirmOpenMultipleBoxesMenu(player);
                CookieGadgets.getPlayerManager(player).setOpenMultipleBoxesType(ConfirmOpenMultipleBoxesMenu.OpenMultipleBoxesType.OPEN_250_BOXES);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (hasMysteryBoxesPermission2 < 50) {
                SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(player, 1.0f, 0.5f);
                player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_BOXES.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_BOXES.getFormatMessage());
            if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
            }
            if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                player.closeInventory();
            }
            inventoryClickEvent.setCancelled(true);
        }
    }
    
    private static int hasMysteryBoxesPermission(final Player player) {
        int n = 0;
        for (final MysteryBoxes mysteryBoxes : CookieGadgets.getPlayerManager(player).mysteryBoxes()) {
            if (!mysteryBoxes.isRequiredPermission()) {
                ++n;
            }
            else {
                if (!mysteryBoxes.isRequiredPermission() || PermissionUtils.noPermission(player, mysteryBoxes.getMysteryBoxType().getPermission(), EnumPermission.OPEN_ALL_MYSTERY_BOXES.getPermission(), false)) {
                    continue;
                }
                ++n;
            }
        }
        return n;
    }
}
