

package ws.billy.CookieGadgets.menu.menus;

import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.utils.GlowUtil;
import ws.billy.CookieGadgets.utils.EnumPotion;
import ws.billy.CookieGadgets.utils.VersionManager;
import java.lang.reflect.Field;
import com.mojang.authlib.properties.Property;
import java.util.Base64;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import java.util.ArrayList;
import org.bukkit.inventory.meta.SkullMeta;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.inventory.ItemStack;
import java.util.List;
import ws.billy.CookieGadgets.utils.GMaterial;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.discount.ItemCostDiscount;
import java.util.Iterator;
import ws.billy.CookieGadgets.api.CookieGadgetsAPI;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.InventoryType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetMessages;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.discount.DiscountManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.StringUtils;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import ws.billy.CookieGadgets.cosmetics.pets.PetCategoryType;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Listener;

public class PetTypesMenu implements Listener
{
    public static void openPetTypesMenu(final PlayerManager playerManager, final PetCategoryType petCategoryType, int n) {
        final int size = PetType.getPetCategory(petCategoryType).size();
        final int maxPagesAmount = GInventory.getMaxPagesAmount(21, size);
        if (n <= 0) {
            n = 1;
        }
        if (n > 1 && maxPagesAmount < n) {
            n = 1;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.format(String.valueOf(petCategoryType.getGUIName()) + StringUtils.addPlaceholders(EnumItem.PAGES.getDisplayName(), Arrays.asList("{CURRENT_PAGE}", "{MAX_PAGES}"), Arrays.asList(String.valueOf(n), String.valueOf(maxPagesAmount)))));
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
        for (int i = n3; i <= n4; ++i) {
            try {
                if (i > size) {
                    break;
                }
                final PetType petType = PetType.getPetCategory(petCategoryType).get(i - 1);
                if (PermissionUtils.noPermission(playerManager.getPlayer(), petType.getPermission(), EnumPermission.PETS.getPermission(), false)) {
                    final DiscountManager discountManager = new DiscountManager(playerManager, Category.PETS.isPurchasable() && petType.isPurchasable(), petType.getMysteryDust());
                    if (EnumItem.NO_PERMISSION.showCustomItem()) {
                        InventoryUtils.inventory(inventory, petType.getDisplayName(), EnumItem.NO_PERMISSION.getCustomItem(), petType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_21.getLayOut()[n2++]);
                    }
                    else {
                        InventoryUtils.inventory(inventory, petType.getDisplayName(), petType.getMaterial(), petType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_21.getLayOut()[n2++]);
                    }
                }
                else {
                    playerManager.addPetDataIfAbsent(petType);
                    inventoryAddGlow(inventory, petType.getDisplayName(), petType.getMaterial(), PetMessages.getLore(playerManager.getPlayer(), playerManager.getPetData(petType), petType.getUnlockedLore()), EnumItem.HAS_PERMISSION.isShowInLore() ? EnumItem.HAS_PERMISSION.getLore() : null, GInventory.LAY_OUT_21.getLayOut()[n2++], (playerManager.getEquippedPet() == null) ? null : playerManager.getEquippedPet().getDisplayName());
                }
            }
            catch (Exception ex) {
                playerManager.getPlayer().sendMessage(MessageType.ERROR.getFormatMessage());
                ex.printStackTrace();
                return;
            }
        }
        if (EnumItem.RESET_PET.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RESET_PET.getItemStack(), EnumItem.RESET_PET.getSlot());
        }
        if (n == 1) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot());
        }
        else {
            InventoryUtils.inventory(inventory, EnumItem.PREVIOUS_PAGE.getItemStack(), "{PAGE}", String.valueOf(n - 1), EnumItem.PREVIOUS_PAGE.getSlot());
        }
        if (n < maxPagesAmount) {
            InventoryUtils.inventory(inventory, EnumItem.NEXT_PAGE.getItemStack(), "{PAGE}", String.valueOf(n + 1), EnumItem.NEXT_PAGE.getSlot());
        }
        if (EnumItem.MAIN_MENU_ITEM.show()) {
            InventoryUtils.mainMenuButton(playerManager.getPlayer(), inventory, EnumItem.MAIN_MENU_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        playerManager.getPlayer().openInventory(inventory);
        playerManager.setCurrentMenuPage(n);
    }
    
    @EventHandler
    public void onInvClickPetTypes(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        for (final PetCategoryType petCategoryType : PetCategoryType.values()) {
            if (ChatUtil.stripColor(inventoryClickEvent.getView().getTitle()).equals(ChatUtil.stripColor(String.valueOf(petCategoryType.getGUIName()) + StringUtils.addPlaceholders(EnumItem.PAGES.getDisplayName(), Arrays.asList("{CURRENT_PAGE}", "{MAX_PAGES}"), Arrays.asList(String.valueOf(CookieGadgets.getPlayerManager(player).getCurrentMenuPage()), String.valueOf(GInventory.getMaxPagesAmount(21, PetType.getPetCategory(petCategoryType).size()))))))) {
                if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (!Category.PETS.isEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
                    player.closeInventory();
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
                if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot())) {
                    CookieGadgets.getPlayerManager(player).openCategoryPetsMenu();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
                if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RESET_PET.getItemStack(), EnumItem.RESET_PET.getSlot())) {
                    playerManager.unequipPet();
                    player.sendMessage(MessageType.RESET_PET.getFormatMessage());
                    if (EnumItem.RESET_PET.isPlaySoundEnabled()) {
                        EnumItem.RESET_PET.getSound().playSound(player, 1.0f, 2.0f);
                    }
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.PREVIOUS_PAGE.getDisplayName(), EnumItem.PREVIOUS_PAGE.getSlot())) {
                    openPetTypesMenu(playerManager, petCategoryType, playerManager.getCurrentMenuPage() - 1);
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.NEXT_PAGE.getDisplayName(), EnumItem.NEXT_PAGE.getSlot())) {
                    openPetTypesMenu(playerManager, petCategoryType, playerManager.getCurrentMenuPage() + 1);
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.MAIN_MENU_ITEM.getDisplayName(), EnumItem.MAIN_MENU_ITEM.getSlot())) {
                    CookieGadgetsAPI.goBackToMainMenu(player);
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                for (final PetType petType : PetType.values()) {
                    if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(petType.getDisplayName()))) {
                        if (PermissionUtils.noPermission(player, petType.getPermission(), EnumPermission.PETS.getPermission(), false)) {
                            if (Category.PETS.isPurchasable()) {
                                if (!petType.isPurchasable()) {
                                    player.sendMessage(MessageType.ITEM_UNPURCHASABLE.getFormatMessage());
                                    if (EnumItem.ITEM_UNPURCHASABLE.isPlaySoundEnabled()) {
                                        EnumItem.ITEM_UNPURCHASABLE.getSound().playSound(player, 1.0f, 0.5f);
                                    }
                                    player.closeInventory();
                                    inventoryClickEvent.setCancelled(true);
                                    return;
                                }
                                final ItemCostDiscount itemCostDiscount = playerManager.getItemCostDiscount();
                                int n = petType.getMysteryDust();
                                if (itemCostDiscount != null) {
                                    n = itemCostDiscount.getPriceAfterDiscount(petType.getMysteryDust());
                                }
                                if (playerManager.getMysteryDust() >= n) {
                                    playerManager.purchaseData().setData(Category.PETS, petType.getName(), petType.getDisplayName(), itemCostDiscount, petType.getMysteryDust(), petType.getPermission());
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
                                if (PermissionUtils.noPermission(player, petType.getPermission(), EnumPermission.PETS.getPermission(), true)) {
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
                                if (CookieGadgets.getPetData().isEnabled() && inventoryClickEvent.getClick() == ClickType.RIGHT) {
                                    playerManager.setSelectedProvideCareCategoryPet(petType.getPetCategory());
                                    playerManager.openPetItemsMenu(playerManager.getPetData(petType));
                                }
                                else {
                                    if (playerManager.getEquippedPet() != null && ChatUtil.format(petType.getDisplayName()).equals(playerManager.getEquippedPet().getDisplayName())) {
                                        playerManager.unequipPet();
                                        player.sendMessage(MessageType.RESET_PET.getFormatMessage());
                                        if (EnumItem.RESET_PET.isPlaySoundEnabled()) {
                                            EnumItem.RESET_PET.getSound().playSound(player, 1.0f, 2.0f);
                                        }
                                        player.closeInventory();
                                        inventoryClickEvent.setCancelled(true);
                                        return;
                                    }
                                    if (PermissionUtils.noPermission(player, EnumPermission.SUMMON_PET.getPermission(), false)) {
                                        player.sendMessage(MessageType.NO_PERMISSION_TO_SUMMON_PET.getFormatMessage());
                                        if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                            EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                                        }
                                        player.closeInventory();
                                        inventoryClickEvent.setCancelled(true);
                                        return;
                                    }
                                    if (!petType.checkRequirement(playerManager)) {
                                        if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                            EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                                        }
                                        player.closeInventory();
                                        inventoryClickEvent.setCancelled(true);
                                        return;
                                    }
                                    playerManager.equipPet(petType);
                                    player.sendMessage(MessageType.SELECT_PET.getFormatMessage().replace("{PET}", petType.getDisplayNameStripColor()));
                                    if (EnumItem.HAS_PERMISSION.isPlaySoundEnabled()) {
                                        EnumItem.HAS_PERMISSION.getSound().playSound(player, 1.0f, 2.0f);
                                    }
                                    if (EnumItem.HAS_PERMISSION.isCloseGUIMenuAfterSelect()) {
                                        player.closeInventory();
                                    }
                                    else {
                                        openPetTypesMenu(playerManager, petCategoryType, playerManager.getCurrentMenuPage());
                                    }
                                    inventoryClickEvent.setCancelled(true);
                                    return;
                                }
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
    }
    
    private static ItemStack inventoryAddGlow(final Inventory inventory, final String s, final GMaterial gMaterial, final List<String> list, final List<String> list2, final int n, final String s2) {
        if (gMaterial.isSkullHead()) {
            String str = gMaterial.getTexture();
            if (!str.startsWith("http://textures.minecraft.net/texture/")) {
                str = "http://textures.minecraft.net/texture/" + str;
            }
            final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), 1, (short)EnumMaterial.PLAYER_HEAD.getData());
            final SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
            skullMeta.setDisplayName(ChatUtil.format(s));
            final ArrayList<String> lore = new ArrayList<String>();
            if (list != null) {
                final Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    lore.add(ChatUtil.format(iterator.next()));
                }
            }
            if (list2 != null) {
                final Iterator<String> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    lore.add(ChatUtil.format(iterator2.next()));
                }
            }
            if (list != null || list2 != null) {
                skullMeta.setLore((List)lore);
            }
            return skullData(inventory, itemStack, skullMeta, str, lore, s, s2, n);
        }
        final EnumMaterial enumMaterial = gMaterial.getEnumMaterial();
        final ItemStack itemStack2 = new ItemStack(enumMaterial.getType(), 1, (short)gMaterial.getData());
        Object itemMeta = itemStack2.getItemMeta();
        if (gMaterial.isColorableMaterial()) {
            itemMeta = itemStack2.getItemMeta();
            ((LeatherArmorMeta)itemMeta).setColor(gMaterial.getColor());
        }
        ((ItemMeta)itemMeta).setDisplayName(ChatUtil.format(s));
        final ArrayList<String> lore2 = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator3 = list.iterator();
            while (iterator3.hasNext()) {
                lore2.add(ChatUtil.format(iterator3.next()));
            }
        }
        if (list2 != null) {
            final Iterator<String> iterator4 = list2.iterator();
            while (iterator4.hasNext()) {
                lore2.add(ChatUtil.format(iterator4.next()));
            }
        }
        if (list != null || list2 != null) {
            ((ItemMeta)itemMeta).setLore((List)lore2);
        }
        return itemData(inventory, enumMaterial, gMaterial.getData(), itemStack2, (ItemMeta)itemMeta, lore2, s, s2, n);
    }
    
    private static ItemStack skullData(final Inventory inventory, final ItemStack itemStack, final SkullMeta itemMeta, final String s, final ArrayList<String> lore, final String s2, final String s3, final int n) {
        if (s3 != null && s3.equals(ChatUtil.format(s2))) {
            final Iterator<String> iterator = CookieGadgets.getPetData().getDespawnPetLore().iterator();
            while (iterator.hasNext()) {
                lore.add(ChatUtil.format(iterator.next()));
            }
        }
        else {
            final Iterator<String> iterator2 = CookieGadgets.getPetData().getSummonPetLore().iterator();
            while (iterator2.hasNext()) {
                lore.add(ChatUtil.format(iterator2.next()));
            }
        }
        if (CookieGadgets.getPetData().isEnabled()) {
            final Iterator<String> iterator3 = CookieGadgets.getPetData().getProvideCareLore().iterator();
            while (iterator3.hasNext()) {
                lore.add(ChatUtil.format(iterator3.next()));
            }
        }
        itemMeta.setLore((List)lore);
        itemStack.setItemMeta((ItemMeta)itemMeta);
        final GameProfile value = new GameProfile(UUID.randomUUID(), (String)null);
        value.getProperties().put((Object)"textures", (Object)new Property("textures", new String(Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", s).getBytes()))));
        try {
            final Field declaredField = itemMeta.getClass().getDeclaredField("profile");
            declaredField.setAccessible(true);
            declaredField.set(itemMeta, value);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            final Throwable t;
            t.printStackTrace();
        }
        InventoryUtils.addItemFlags((ItemMeta)itemMeta);
        itemStack.setItemMeta((ItemMeta)itemMeta);
        inventory.setItem(n, itemStack);
        return itemStack;
    }
    
    private static ItemStack itemData(final Inventory inventory, final EnumMaterial material, final int data, ItemStack itemStack, final ItemMeta itemMeta, final ArrayList<String> list, final String s, final String s2, final int n) {
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(material)) {
            itemStack.setDurability((short)0);
        }
        InventoryUtils.addItemFlags(itemMeta);
        itemStack.setItemMeta(itemMeta);
        if (s2 != null && s2.equals(ChatUtil.format(s))) {
            final Iterator<String> iterator = CookieGadgets.getPetData().getDespawnPetLore().iterator();
            while (iterator.hasNext()) {
                list.add(ChatUtil.format(iterator.next()));
            }
            if (CookieGadgets.getPetData().isEnabled()) {
                final Iterator<String> iterator2 = CookieGadgets.getPetData().getProvideCareLore().iterator();
                while (iterator2.hasNext()) {
                    list.add(ChatUtil.format(iterator2.next()));
                }
            }
            itemMeta.setLore((List)list);
            itemStack.setItemMeta(itemMeta);
            itemStack = GlowUtil.addGlow(itemStack);
        }
        else {
            final Iterator<String> iterator3 = CookieGadgets.getPetData().getSummonPetLore().iterator();
            while (iterator3.hasNext()) {
                list.add(ChatUtil.format(iterator3.next()));
            }
            if (CookieGadgets.getPetData().isEnabled()) {
                final Iterator<String> iterator4 = CookieGadgets.getPetData().getProvideCareLore().iterator();
                while (iterator4.hasNext()) {
                    list.add(ChatUtil.format(iterator4.next()));
                }
            }
            itemMeta.setLore((List)list);
            itemStack.setItemMeta(itemMeta);
        }
        if (material.isSpawnEgg() && VersionManager.is1_9OrAbove() && !VersionManager.is1_13OrAbove()) {
            itemStack = CookieGadgets.getNMSManager().spawnEgg(itemStack, EntityType.fromId(data).getName());
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(material)) {
            itemStack = CookieGadgets.getNMSManager().getPotionFromId(itemStack, material, data);
        }
        inventory.setItem(n, itemStack);
        return itemStack;
    }
}
