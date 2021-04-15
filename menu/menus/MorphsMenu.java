

package ws.billy.CookieGadgets.menu.menus;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.discount.ItemCostDiscount;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.entity.Entity;
import me.libraryaddict.disguise.DisguiseAPI;
import ws.billy.CookieGadgets.api.CookieGadgetsAPI;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.discount.DiscountManager;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Listener;

public class MorphsMenu implements Listener
{
    public static void openMorphsMenu(final PlayerManager playerManager) {
        final int size = MorphType.enabled().size();
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, Category.MORPHS.getGUIName());
        int n = 0;
        final int n2 = 1;
        int n3 = 21;
        if (size < 21) {
            n3 = size;
        }
        final Player player = playerManager.getPlayer();
        for (int i = n2; i <= n3; ++i) {
            try {
                if (i > size) {
                    break;
                }
                final MorphType morphType = MorphType.enabled().get(i - 1);
                if (PermissionUtils.noPermission(player, morphType.getPermission(), EnumPermission.MORPHS.getPermission(), false)) {
                    final DiscountManager discountManager = new DiscountManager(playerManager, Category.MORPHS.isPurchasable() && morphType.isPurchasable(), morphType.getMysteryDust());
                    if (EnumItem.NO_PERMISSION.showCustomItem()) {
                        InventoryUtils.inventory(inventory, morphType.getDisplayName(), EnumItem.NO_PERMISSION.getCustomItem(), morphType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_21.getLayOut()[n++]);
                    }
                    else {
                        InventoryUtils.inventory(inventory, morphType.getDisplayName(), morphType.getMaterial(), morphType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_21.getLayOut()[n++]);
                    }
                }
                else {
                    InventoryUtils.inventoryAddGlow(inventory, morphType.getDisplayName(), morphType.getMaterial(), morphType.getUnlockedLore(), EnumItem.HAS_PERMISSION.isShowInLore() ? EnumItem.HAS_PERMISSION.getLore() : null, GInventory.LAY_OUT_21.getLayOut()[n++], (playerManager.getEquippedMorph() == null) ? null : playerManager.getEquippedMorph().getDisplayName());
                }
            }
            catch (Exception ex) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                ex.printStackTrace();
                return;
            }
        }
        if (EnumItem.RESET_MORPH.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RESET_MORPH.getItemStack(), EnumItem.RESET_MORPH.getSlot());
        }
        if (EnumItem.GO_BACK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot());
        }
        if (EnumItem.SELF_MORPH_VIEW.show() && CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() && CookieGadgets.getCookieGadgetsData().isSelfMorphViewEnabled()) {
            InventoryUtils.inventory(inventory, EnumItem.SELF_MORPH_VIEW.getItemStack(), "{STATUS}", playerManager.canSeeSelfMorph() ? MessageType.ENABLED.getFormatMessage() : MessageType.DISABLED.getFormatMessage(), EnumItem.SELF_MORPH_VIEW.getSlot());
        }
        if (EnumItem.MAIN_MENU_ITEM.show()) {
            InventoryUtils.mainMenuButton(player, inventory, EnumItem.MAIN_MENU_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler
    public void onInvClickMorphs(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(Category.MORPHS.getGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (!Category.MORPHS.isEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
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
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RESET_MORPH.getItemStack(), EnumItem.RESET_MORPH.getSlot())) {
            if (CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() && !DisguiseAPI.isDisguised((Entity)player)) {
                player.sendMessage(MessageType.IS_NOT_MORPHED.getFormatMessage());
                if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                }
            }
            else {
                playerManager.unequipMorph();
                player.sendMessage(MessageType.RESET_MORPH.getFormatMessage());
                if (EnumItem.RESET_MORPH.isPlaySoundEnabled()) {
                    EnumItem.RESET_MORPH.getSound().playSound(player, 1.0f, 2.0f);
                }
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
        if (!ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.SELF_MORPH_VIEW.getDisplayName(), EnumItem.SELF_MORPH_VIEW.getSlot())) {
            for (final MorphType morphType : MorphType.values()) {
                if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(morphType.getDisplayName()))) {
                    if (PermissionUtils.noPermission(player, morphType.getPermission(), EnumPermission.MORPHS.getPermission(), false)) {
                        if (Category.MORPHS.isPurchasable()) {
                            if (!morphType.isPurchasable()) {
                                player.sendMessage(MessageType.ITEM_UNPURCHASABLE.getFormatMessage());
                                if (EnumItem.ITEM_UNPURCHASABLE.isPlaySoundEnabled()) {
                                    EnumItem.ITEM_UNPURCHASABLE.getSound().playSound(player, 1.0f, 0.5f);
                                }
                                player.closeInventory();
                                inventoryClickEvent.setCancelled(true);
                                return;
                            }
                            final ItemCostDiscount itemCostDiscount = playerManager.getItemCostDiscount();
                            int n = morphType.getMysteryDust();
                            if (itemCostDiscount != null) {
                                n = itemCostDiscount.getPriceAfterDiscount(morphType.getMysteryDust());
                            }
                            if (playerManager.getMysteryDust() >= n) {
                                playerManager.purchaseData().setData(Category.MORPHS, morphType.getName(), morphType.getDisplayName(), itemCostDiscount, morphType.getMysteryDust(), morphType.getPermission());
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
                            if (!PermissionUtils.noPermission(player, morphType.getPermission(), EnumPermission.MORPHS.getPermission(), true)) {
                                continue;
                            }
                            if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                            }
                            if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                                player.closeInventory();
                            }
                            inventoryClickEvent.setCancelled(true);
                        }
                    }
                    else {
                        try {
                            if (playerManager.getEquippedMorph() != null && ChatUtil.format(morphType.getDisplayName()).equals(playerManager.getEquippedMorph().getDisplayName())) {
                                playerManager.unequipMorph();
                                player.sendMessage(MessageType.RESET_MORPH.getFormatMessage());
                                if (EnumItem.RESET_MORPH.isPlaySoundEnabled()) {
                                    EnumItem.RESET_MORPH.getSound().playSound(player, 1.0f, 2.0f);
                                }
                                player.closeInventory();
                                inventoryClickEvent.setCancelled(true);
                                return;
                            }
                            if (!morphType.checkRequirement(playerManager)) {
                                if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                                }
                                player.closeInventory();
                                inventoryClickEvent.setCancelled(true);
                                return;
                            }
                            playerManager.equipMorph(morphType);
                            player.sendMessage(MessageType.SELECT_MORPH.getFormatMessage().replace("{MORPH}", morphType.getDisplayNameStripColor()));
                            if (EnumItem.HAS_PERMISSION.isPlaySoundEnabled()) {
                                EnumItem.HAS_PERMISSION.getSound().playSound(player, 1.0f, 2.0f);
                            }
                            if (EnumItem.HAS_PERMISSION.isCloseGUIMenuAfterSelect()) {
                                player.closeInventory();
                            }
                            else {
                                openMorphsMenu(playerManager);
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
            return;
        }
        if (!CookieGadgets.getCookieGadgetsData().isSelfMorphViewEnabled()) {
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        playerManager.setSeeSelfMorph(!playerManager.canSeeSelfMorph());
        openMorphsMenu(playerManager);
        inventoryClickEvent.setCancelled(true);
    }
}
