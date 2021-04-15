

package ws.billy.CookieGadgets.menu.menus;

import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.utils.GlowUtil;
import ws.billy.CookieGadgets.utils.EnumPotion;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.inventory.meta.ItemMeta;
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
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetCategoryType;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Listener;

public class GadgetCategoriesMenu implements Listener
{
    public static void openCategoryCookieGadgets(final PlayerManager playerManager) {
        final int size = GadgetCategoryType.enabled().size();
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, Category.GADGETS.getGUIName());
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
                final GadgetCategoryType gadgetCategoryType = GadgetCategoryType.enabled().get(i - 1);
                if (GadgetType.getGadgetCategory(gadgetCategoryType).size() != 0) {
                    if (!PermissionUtils.containsPermission(player, gadgetCategoryType.getPermission())) {
                        if (EnumItem.NO_PERMISSION.showCustomItem()) {
                            inventory(player, inventory, gadgetCategoryType.getDisplayName(), EnumItem.NO_PERMISSION.getCustomItem(), gadgetCategoryType.getLore(), null, GInventory.LAY_OUT_21.getLayOut()[n++], gadgetCategoryType);
                        }
                        else {
                            inventory(player, inventory, gadgetCategoryType.getDisplayName(), gadgetCategoryType.getMaterial(), gadgetCategoryType.getLore(), null, GInventory.LAY_OUT_21.getLayOut()[n++], gadgetCategoryType);
                        }
                    }
                    else {
                        inventoryAddGlow(player, inventory, gadgetCategoryType.getDisplayName(), gadgetCategoryType.getMaterial(), gadgetCategoryType.getLore(), null, GInventory.LAY_OUT_21.getLayOut()[n++], (playerManager.getSelectedCategoryGadget() == null) ? null : playerManager.getSelectedCategoryGadget().getDisplayName(), gadgetCategoryType);
                    }
                }
            }
            catch (Exception ex) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                ex.printStackTrace();
                return;
            }
        }
        if (EnumItem.RESET_GADGET.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RESET_GADGET.getItemStack(), EnumItem.RESET_GADGET.getSlot());
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
    public void onInvClickCategoryGadgets(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(Category.GADGETS.getGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (!Category.GADGETS.isEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
            player.closeInventory();
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
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot())) {
            CookieGadgetsAPI.goBackToMainMenu(player);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RESET_GADGET.getItemStack(), EnumItem.RESET_GADGET.getSlot())) {
            CookieGadgets.getPlayerManager(player).unequipGadget();
            player.sendMessage(MessageType.RESET_GADGET.getFormatMessage());
            if (EnumItem.RESET_GADGET.isPlaySoundEnabled()) {
                EnumItem.RESET_GADGET.getSound().playSound(player, 1.0f, 2.0f);
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
        for (final GadgetCategoryType gadgetCategoryType : GadgetCategoryType.values()) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(gadgetCategoryType.getDisplayName()))) {
                GadgetTypesMenu.openGadgetTypesMenu(CookieGadgets.getPlayerManager(player), gadgetCategoryType, 1);
                inventoryClickEvent.setCancelled(true);
                return;
            }
        }
        inventoryClickEvent.setCancelled(true);
    }
    
    private static ItemStack inventoryAddGlow(final Player player, final Inventory inventory, final String s, final GMaterial material, final List<String> list, final List<String> list2, final int n, final String s2, final GadgetCategoryType gadgetCategoryType) {
        if (material.isSkullHead()) {
            final String string = "http://textures.minecraft.net/texture/" + material.getTexture();
            final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), 1, (short)EnumMaterial.PLAYER_HEAD.getData());
            final SkullMeta itemMeta = (SkullMeta)itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatUtil.format(s));
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
                itemMeta.setLore((List)list3);
            }
            if (EnumItem.UNLOCKED.isShowInLore()) {
                final Iterator<String> iterator3 = EnumItem.UNLOCKED.getLore().iterator();
                while (iterator3.hasNext()) {
                    list3.add(ChatUtil.format(iterator3.next().replace("{HASPERMISSION}", String.valueOf(hasPermission(player, gadgetCategoryType))).replace("{SIZE}", String.valueOf(GadgetType.getGadgetCategory(gadgetCategoryType).size())).replace("{PERCENTAGE}", String.valueOf(hasPermission(player, gadgetCategoryType) * 100 / GadgetType.getGadgetCategory(gadgetCategoryType).size()))));
                }
                itemMeta.setLore((List)list3);
                itemStack.setItemMeta((ItemMeta)itemMeta);
            }
            return InventoryUtils.skullData(inventory, itemStack, itemMeta, string, n);
        }
        final EnumMaterial enumMaterial = material.getEnumMaterial();
        ItemStack itemStack2 = new ItemStack(enumMaterial.getType(), 1, (short)material.getData());
        final ItemMeta itemMeta2 = itemStack2.getItemMeta();
        itemMeta2.setDisplayName(ChatUtil.format(s));
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
            itemMeta2.setLore((List)list4);
        }
        if (EnumItem.UNLOCKED.isShowInLore()) {
            final Iterator<String> iterator6 = EnumItem.UNLOCKED.getLore().iterator();
            while (iterator6.hasNext()) {
                list4.add(ChatUtil.format(iterator6.next().replace("{HASPERMISSION}", String.valueOf(hasPermission(player, gadgetCategoryType))).replace("{SIZE}", String.valueOf(GadgetType.getGadgetCategory(gadgetCategoryType).size())).replace("{PERCENTAGE}", String.valueOf(hasPermission(player, gadgetCategoryType) * 100 / GadgetType.getGadgetCategory(gadgetCategoryType).size()))));
            }
            itemMeta2.setLore((List)list4);
            itemStack2.setItemMeta(itemMeta2);
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(enumMaterial)) {
            itemStack2.setDurability((short)0);
        }
        InventoryUtils.addItemFlags(itemMeta2);
        itemStack2.setItemMeta(itemMeta2);
        if (s2 != null && s2.equals(ChatUtil.format(s))) {
            itemStack2 = GlowUtil.addGlow(itemStack2);
        }
        if (material.getEnumMaterial().isSpawnEgg() && VersionManager.is1_9OrAbove() && !VersionManager.is1_13OrAbove()) {
            itemStack2 = CookieGadgets.getNMSManager().spawnEgg(itemStack2, EntityType.fromId(material.getData()).getName());
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(enumMaterial)) {
            itemStack2 = CookieGadgets.getNMSManager().getPotionFromId(itemStack2, material);
        }
        inventory.setItem(n, itemStack2);
        return itemStack2;
    }
    
    private static ItemStack inventory(final Player player, final Inventory inventory, final String s, final GMaterial gMaterial, final List<String> list, final List<String> list2, final int n, final GadgetCategoryType gadgetCategoryType) {
        if (gMaterial.isSkullHead()) {
            final String string = "http://textures.minecraft.net/texture/" + gMaterial.getTexture();
            final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), 1, (short)EnumMaterial.PLAYER_HEAD.getData());
            final SkullMeta itemMeta = (SkullMeta)itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatUtil.format(s));
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
                itemMeta.setLore((List)list3);
            }
            if (EnumItem.UNLOCKED.isShowInLore()) {
                for (final String s2 : EnumItem.UNLOCKED.getLore()) {
                    if (s2.contains("{HASPERMISSION}") || s2.contains("{SIZE}") || s2.contains("{PERCENTAGE}")) {
                        final int hasPermission = hasPermission(player, gadgetCategoryType);
                        list3.add(ChatUtil.format(s2.replace("{HASPERMISSION}", String.valueOf(hasPermission)).replace("{SIZE}", String.valueOf(GadgetType.getGadgetCategory(gadgetCategoryType).size())).replace("{PERCENTAGE}", String.valueOf(hasPermission * 100 / GadgetType.getGadgetCategory(gadgetCategoryType).size()))));
                    }
                    else {
                        list3.add(ChatUtil.format(s2));
                    }
                }
                itemMeta.setLore((List)list3);
                itemStack.setItemMeta((ItemMeta)itemMeta);
            }
            return InventoryUtils.skullData(inventory, itemStack, itemMeta, string, n);
        }
        final EnumMaterial enumMaterial = gMaterial.getEnumMaterial();
        final ItemStack itemStack2 = new ItemStack(enumMaterial.getType(), 1, (short)gMaterial.getData());
        final ItemMeta itemMeta2 = itemStack2.getItemMeta();
        itemMeta2.setDisplayName(ChatUtil.format(s));
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
            itemMeta2.setLore((List)list4);
        }
        if (EnumItem.UNLOCKED.isShowInLore()) {
            for (final String s3 : EnumItem.UNLOCKED.getLore()) {
                if (s3.contains("{HASPERMISSION}") || s3.contains("{SIZE}") || s3.contains("{PERCENTAGE}")) {
                    final int hasPermission2 = hasPermission(player, gadgetCategoryType);
                    list4.add(ChatUtil.format(s3.replace("{HASPERMISSION}", String.valueOf(hasPermission2)).replace("{SIZE}", String.valueOf(GadgetType.getGadgetCategory(gadgetCategoryType).size())).replace("{PERCENTAGE}", String.valueOf(hasPermission2 * 100 / GadgetType.getGadgetCategory(gadgetCategoryType).size()))));
                }
                else {
                    list4.add(ChatUtil.format(s3));
                }
            }
            itemMeta2.setLore((List)list4);
        }
        return InventoryUtils.itemData(inventory, enumMaterial, gMaterial.getData(), itemStack2, itemMeta2, n);
    }
    
    private static int hasPermission(final Player player, final GadgetCategoryType gadgetCategoryType) {
        if (!gadgetCategoryType.isEnabled()) {
            return 0;
        }
        if (player.hasPermission(EnumPermission.GADGETS.getPermission())) {
            return GadgetType.getGadgetCategory(gadgetCategoryType).size();
        }
        int n = 0;
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        for (final GadgetType gadgetType : GadgetType.getGadgetCategory(gadgetCategoryType)) {
            if (playerManager == null) {
                break;
            }
            if (!gadgetType.isEnabled() || (!player.hasPermission(gadgetType.getPermission()) && !playerManager.hasPermission(gadgetType.getPermission()))) {
                continue;
            }
            ++n;
        }
        return n;
    }
}
