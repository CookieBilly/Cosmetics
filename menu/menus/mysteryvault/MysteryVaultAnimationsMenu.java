

package ws.billy.CookieGadgets.menu.menus.mysteryvault;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.player.PlayerManager;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.MessageType;
import java.util.List;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class MysteryVaultAnimationsMenu implements Listener
{
    public static void openMysteryVaultAnimationsMenu(final Player player) {
        final int size = AnimationType.enabled().size();
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, CookieGadgets.getCookieGadgetsData().getMysteryVaultAnimationsGUIName());
        int n = 0;
        final int n2 = 1;
        int n3 = 21;
        if (size < 21) {
            n3 = size;
        }
        for (int i = n2; i <= n3; ++i) {
            try {
                if (i > size) {
                    break;
                }
                final AnimationType animationType = AnimationType.enabled().get(i - 1);
                if (PermissionUtils.noPermission(player, animationType.getPermission(), EnumPermission.ALL_MYSTERY_VAULT_ANIMATIONS.getPermission(), false)) {
                    if (EnumItem.NO_PERMISSION.showCustomItem()) {
                        InventoryUtils.inventory(inventory, animationType.getDisplayName(), EnumItem.NO_PERMISSION.getCustomItem(), animationType.getLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, GInventory.LAY_OUT_21.getLayOut()[n++]);
                    }
                    else {
                        InventoryUtils.inventory(inventory, animationType.getDisplayName(), animationType.getMaterial(), animationType.getLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, GInventory.LAY_OUT_21.getLayOut()[n++]);
                    }
                }
                else {
                    final AnimationType mysteryVaultAnimation = CookieGadgets.getPlayerManager(player).getMysteryVaultAnimation();
                    InventoryUtils.inventoryAddGlow(inventory, animationType.getDisplayName(), animationType.getMaterial(), animationType.getLore(), null, GInventory.LAY_OUT_21.getLayOut()[n++], (mysteryVaultAnimation == null) ? null : mysteryVaultAnimation.getDisplayName());
                }
            }
            catch (Exception ex) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                ex.printStackTrace();
                return;
            }
        }
        if (EnumItem.GO_BACK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot());
        }
        if (EnumItem.RANDOM_MYSTERY_VAULT_ANIMATION_ITEM.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RANDOM_MYSTERY_VAULT_ANIMATION_ITEM.getItemStack(), "{STATUS}", CookieGadgets.getPlayerManager(player).isRandomMysteryVaultAnimationSelected() ? MessageType.ENABLED.getFormatMessage() : MessageType.DISABLED.getFormatMessage(), EnumItem.RANDOM_MYSTERY_VAULT_ANIMATION_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler
    public void onInvClickMysteryVaultAnimationsMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(CookieGadgets.getCookieGadgetsData().getMysteryVaultAnimationsGUIName())) {
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
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot())) {
            CookieGadgets.getPlayerManager(player).openMysteryVaultMenu(1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (!ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RANDOM_MYSTERY_VAULT_ANIMATION_ITEM.getDisplayName(), EnumItem.RANDOM_MYSTERY_VAULT_ANIMATION_ITEM.getSlot())) {
            for (final AnimationType mysteryVaultAnimation : AnimationType.values()) {
                try {
                    if (!inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(mysteryVaultAnimation.getDisplayName()))) {
                        continue;
                    }
                    if (PermissionUtils.noPermission(player, mysteryVaultAnimation.getPermission(), EnumPermission.ALL_MYSTERY_VAULT_ANIMATIONS.getPermission(), true)) {
                        if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                            EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                        }
                        if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                            player.closeInventory();
                        }
                        inventoryClickEvent.setCancelled(true);
                        return;
                    }
                    player.sendMessage(MessageType.SELECT_MYSTERY_VAULT_ANIMATION.getFormatMessage().replace("{ANIMATION}", mysteryVaultAnimation.getDisplayNameStripColor()));
                    CookieGadgets.getPlayerManager(player).setMysteryVaultAnimation(mysteryVaultAnimation);
                    if (EnumItem.HAS_PERMISSION.isPlaySoundEnabled()) {
                        EnumItem.HAS_PERMISSION.getSound().playSound(player, 1.0f, 2.0f);
                    }
                    if (EnumItem.HAS_PERMISSION.isCloseGUIMenuAfterSelect()) {
                        player.closeInventory();
                    }
                    else {
                        openMysteryVaultAnimationsMenu(player);
                    }
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                catch (Exception ex) {
                    player.sendMessage(MessageType.ERROR.getFormatMessage());
                    ex.printStackTrace();
                }
            }
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (PermissionUtils.noPermission(player, EnumPermission.RANDOM_MYSTERY_VAULT_ANIMATIONS.getPermission(), EnumPermission.ALL_MYSTERY_VAULT_ANIMATIONS.getPermission(), true)) {
            if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
            }
            if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                player.closeInventory();
            }
            inventoryClickEvent.setCancelled(true);
            return;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (playerManager.isRandomMysteryVaultAnimationSelected()) {
            playerManager.setMysteryVaultAnimation(CookieGadgets.getCookieGadgetsData().getDefaultMysteryVaultAnimation());
            player.sendMessage(MessageType.DISABLED_RANDOM_MYSTERY_VAULT_ANIMATION.getFormatMessage());
        }
        else {
            playerManager.setMysteryVaultAnimation(AnimationType.RANDOM);
            player.sendMessage(MessageType.ENABLED_RANDOM_MYSTERY_VAULT_ANIMATION.getFormatMessage());
        }
        openMysteryVaultAnimationsMenu(player);
        inventoryClickEvent.setCancelled(true);
    }
}
