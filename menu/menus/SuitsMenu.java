

package ws.billy.CookieGadgets.menu.menus;

import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import java.util.ArrayList;
import org.bukkit.inventory.meta.SkullMeta;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.GMaterial;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.api.CookieGadgetsAPI;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.MessageType;
import java.util.List;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Listener;

public class SuitsMenu implements Listener
{
    public static void openSuitsMenu(final PlayerManager playerManager) {
        final int size = SuitType.enabled().size();
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, Category.SUITS.getGUIName());
        int n = 0;
        final int n2 = 1;
        int n3 = 27;
        if (size < 27) {
            n3 = size;
        }
        final Player player = playerManager.getPlayer();
        for (int i = n2; i <= n3; ++i) {
            try {
                if (i > size) {
                    break;
                }
                final SuitType suitType = SuitType.enabled().get(i - 1);
                if (!PermissionUtils.containsPermission(player, suitType.getPermissions())) {
                    if (EnumItem.NO_PERMISSION.showCustomItem()) {
                        inventory(player, inventory, suitType.getDisplayName(), EnumItem.NO_PERMISSION.getCustomItem(), suitType.getLore(), null, GInventory.LAY_OUT_27.getLayOut()[n++], suitType);
                    }
                    else {
                        inventory(player, inventory, suitType.getDisplayName(), suitType.getMaterial(), suitType.getLore(), null, GInventory.LAY_OUT_27.getLayOut()[n++], suitType);
                    }
                }
                else {
                    inventory(player, inventory, suitType.getDisplayName(), suitType.getMaterial(), suitType.getLore(), null, GInventory.LAY_OUT_27.getLayOut()[n++], suitType);
                }
            }
            catch (Exception ex) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                ex.printStackTrace();
                return;
            }
        }
        if (EnumItem.RESET_SUIT.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RESET_SUIT.getItemStack(), EnumItem.RESET_SUIT.getSlot());
        }
        if (EnumItem.GO_BACK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot());
        }
        if (EnumItem.MAIN_MENU_ITEM.show()) {
            InventoryUtils.mainMenuButton(player, inventory, EnumItem.MAIN_MENU_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler
    public void onInvClickSuits(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().startsWith(Category.SUITS.getGUIName())) {
            return;
        }
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
            CookieGadgetsAPI.goBackToMainMenu(player);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RESET_SUIT.getItemStack(), EnumItem.RESET_SUIT.getSlot())) {
            CookieGadgets.getPlayerManager(player).unequipSuit();
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
        for (final SuitType suitType : SuitType.values()) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(suitType.getDisplayName()))) {
                try {
                    SuitEquipmentMenu.openSuitEquipmentMenu(CookieGadgets.getPlayerManager(player), suitType);
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
    
    public static ItemStack inventory(final Player player, final Inventory inventory, final String s, final GMaterial gMaterial, final List<String> list, final List<String> list2, final int n, final SuitType suitType) {
        if (gMaterial.isSkullHead()) {
            final String string = "http://textures.minecraft.net/texture/" + gMaterial.getTexture();
            final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), 1, (short)EnumMaterial.PLAYER_HEAD.getData());
            final SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
            skullMeta.setDisplayName(ChatUtil.format(s));
            final ArrayList<String> list3 = new ArrayList<String>();
            if (list != null) {
                final Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    list3.add(ChatUtil.format(iterator.next()));
                }
            }
            if (list2 != null) {
                final Iterator<String> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    list3.add(ChatUtil.format(iterator2.next()));
                }
            }
            if (list != null || list2 != null) {
                skullMeta.setLore((List)list3);
            }
            if (EnumItem.UNLOCKED.isShowInLore()) {
                for (final String s2 : EnumItem.UNLOCKED.getLore()) {
                    if (s2.contains("{HASPERMISSION}") || s2.contains("{SIZE}") || s2.contains("{PERCENTAGE}")) {
                        final int hasPermission = hasPermission(player, suitType);
                        list3.add(ChatUtil.format(s2.replace("{HASPERMISSION}", String.valueOf(hasPermission)).replace("{SIZE}", String.valueOf(4)).replace("{PERCENTAGE}", String.valueOf(hasPermission * 100 / 4))));
                    }
                    else {
                        list3.add(ChatUtil.format(s2));
                    }
                }
                skullMeta.setLore((List)list3);
            }
            return InventoryUtils.skullData(inventory, itemStack, skullMeta, string, n);
        }
        final EnumMaterial enumMaterial = gMaterial.getEnumMaterial();
        final ItemStack itemStack2 = new ItemStack(enumMaterial.getType(), 1, (short)gMaterial.getData());
        Object itemMeta = itemStack2.getItemMeta();
        if (gMaterial.isColorableMaterial()) {
            itemMeta = itemStack2.getItemMeta();
            ((LeatherArmorMeta)itemMeta).setColor(gMaterial.getColor());
        }
        ((ItemMeta)itemMeta).setDisplayName(ChatUtil.format(s));
        final ArrayList<String> list4 = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator4 = list.iterator();
            while (iterator4.hasNext()) {
                list4.add(ChatUtil.format(iterator4.next()));
            }
        }
        if (list2 != null) {
            final Iterator<String> iterator5 = list2.iterator();
            while (iterator5.hasNext()) {
                list4.add(ChatUtil.format(iterator5.next()));
            }
        }
        if (list != null || list2 != null) {
            ((ItemMeta)itemMeta).setLore((List)list4);
        }
        if (EnumItem.UNLOCKED.isShowInLore()) {
            for (final String s3 : EnumItem.UNLOCKED.getLore()) {
                if (s3.contains("{HASPERMISSION}") || s3.contains("{SIZE}") || s3.contains("{PERCENTAGE}")) {
                    final int hasPermission2 = hasPermission(player, suitType);
                    list4.add(ChatUtil.format(s3.replace("{HASPERMISSION}", String.valueOf(hasPermission2)).replace("{SIZE}", String.valueOf(4)).replace("{PERCENTAGE}", String.valueOf(hasPermission2 * 100 / 4))));
                }
                else {
                    list4.add(ChatUtil.format(s3));
                }
            }
            ((ItemMeta)itemMeta).setLore((List)list4);
        }
        return InventoryUtils.itemData(inventory, enumMaterial, gMaterial.getData(), itemStack2, (ItemMeta)itemMeta, n);
    }
    
    private static int hasPermission(final Player player, final SuitType suitType) {
        if (!suitType.isEnabled()) {
            return 0;
        }
        if (player.hasPermission(EnumPermission.SUITS.getPermission())) {
            return suitType.getPermissions().size();
        }
        int n = 0;
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        for (final String s : suitType.getPermissions()) {
            if (player.hasPermission(s) || playerManager.hasPermission(s)) {
                ++n;
            }
        }
        return n;
    }
}
