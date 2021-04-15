

package ws.billy.CookieGadgets.menu.menus.mysteryboxes;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.discount.ItemCostDiscount;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.MysteryBoxCraftingDate;
import ws.billy.CookieGadgets.utils.mysteryboxes.MysteryBoxType;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.discount.DiscountManager;
import ws.billy.CookieGadgets.utils.mysteryboxes.CraftMysteryBoxType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class MysteryBoxesCraftingMenu implements Listener
{
    public static void openMysteryBoxesCraftingMenu(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 36, CookieGadgets.getCookieGadgetsData().getMysteryBoxCraftingGUIName());
        for (int i = 1; i <= 5; ++i) {
            InventoryUtils.inventory(inventory, CraftMysteryBoxType.valueOf(i).getDisplayName(), CraftMysteryBoxType.valueOf(i).getMaterial(), new DiscountManager(CookieGadgets.getPlayerManager(player), CraftMysteryBoxType.valueOf(i).getLore(), CraftMysteryBoxType.valueOf(i).getPrice()).getLore(), CraftMysteryBoxType.valueOf(i).getSlot());
        }
        if (EnumItem.GO_BACK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), 31);
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInvClickMysteryBoxCraftingMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(CookieGadgets.getCookieGadgetsData().getMysteryBoxCraftingGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 36 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
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
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.GO_BACK.getItemStack(), 31)) {
            CookieGadgets.getPlayerManager(player).openMysteryVaultMenu(1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        int i = 1;
        while (i <= 5) {
            final CraftMysteryBoxType value = CraftMysteryBoxType.valueOf(i);
            if (ItemUtils.getCurrentItem(inventoryClickEvent, value.getDisplayName(), value.getMaterial().getEnumMaterial(), value.getMaterial().getData(), value.getSlot())) {
                final ItemCostDiscount itemCostDiscount = CookieGadgets.getPlayerManager(player).getItemCostDiscount();
                int n = value.getPrice();
                if (itemCostDiscount != null) {
                    n = itemCostDiscount.getPriceAfterDiscount(value.getPrice());
                }
                if (CookieGadgets.getPlayerManager(player).getMysteryDust() < n) {
                    player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_DUST_TO_PURCHASE.getFormatMessage());
                    SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(player, 1.0f, 0.5f);
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (!CookieGadgets.getPlayerManager(player).removeMysteryDust(n)) {
                    player.sendMessage(MessageType.FAILED_TO_DEDUCT_MYSTERY_DUST.getFormatMessage());
                    if (EnumItem.FAILED_TO_DEDUCT_MYSTERY_DUST.isPlaySoundEnabled()) {
                        EnumItem.FAILED_TO_DEDUCT_MYSTERY_DUST.getSound().playSound(player, 1.0f, 0.5f);
                    }
                    player.closeInventory();
                    return;
                }
                CookieGadgets.getPlayerManager(player).giveMysteryBoxes(MysteryBoxType.valueOfByName("Crafted Mystery Box #" + i), null, false, new MysteryBoxCraftingDate().getDate(), 1);
                player.sendMessage(MessageType.CRAFTED_MYSTERY_BOX.getFormatMessage().replace("{NAME}", ChatUtil.stripColor(value.getDisplayName())));
                SoundEffect.ENTITY_EXPERIENCE_ORB_PICKUP.playSound(player, 1.0f, 2.0f);
                openMysteryBoxesCraftingMenu(player);
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
