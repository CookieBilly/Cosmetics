

package ws.billy.CookieGadgets.menu.menus;

import java.lang.reflect.Field;
import com.mojang.authlib.properties.Property;
import java.util.Base64;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import org.bukkit.inventory.meta.LeatherArmorMeta;
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
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.GPet;
import ws.billy.CookieGadgets.api.CookieGadgetsAPI;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetMessages;
import ws.billy.CookieGadgets.utils.items.ItemLoreUtils;
import ws.billy.CookieGadgets.utils.StringUtils;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.MessageType;
import java.util.List;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.cosmetics.pets.PetCategoryType;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Listener;

public class PetCategoriesMenu implements Listener
{
    public static void openCategoryPetsMenu(final PlayerManager playerManager) {
        final int size = PetCategoryType.enabled().size();
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, Category.PETS.getGUIName());
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
                final PetCategoryType petCategoryType = PetCategoryType.enabled().get(i - 1);
                if (getSize(petCategoryType) != 0) {
                    if (!PermissionUtils.containsPermission(player, petCategoryType.getPermission())) {
                        if (EnumItem.NO_PERMISSION.showCustomItem()) {
                            inventory(player, inventory, petCategoryType.getDisplayName(), EnumItem.NO_PERMISSION.getCustomItem(), petCategoryType.getLore(), null, GInventory.LAY_OUT_27.getLayOut()[n++], petCategoryType);
                        }
                        else {
                            inventory(player, inventory, petCategoryType.getDisplayName(), petCategoryType.getMaterial(), petCategoryType.getLore(), null, GInventory.LAY_OUT_27.getLayOut()[n++], petCategoryType);
                        }
                    }
                    else {
                        inventoryAddGlow(player, inventory, petCategoryType.getDisplayName(), petCategoryType.getMaterial(), petCategoryType.getLore(), null, GInventory.LAY_OUT_27.getLayOut()[n++], (playerManager.getSelectedCategoryPet() == null) ? null : playerManager.getSelectedCategoryPet().getDisplayName(), petCategoryType);
                    }
                }
            }
            catch (Exception ex) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                ex.printStackTrace();
                return;
            }
        }
        if (EnumItem.RESET_PET.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RESET_PET.getItemStack(), EnumItem.RESET_PET.getSlot());
        }
        if (EnumItem.GO_BACK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot());
        }
        if (CookieGadgets.getPetData().isEnabled() && EnumItem.SEND_PET_ON_MISSION.show()) {
            InventoryUtils.inventory(inventory, ItemLoreUtils.addLore(EnumItem.SEND_PET_ON_MISSION.getItemStack(), playerManager.isSendPetMissionOnCooldown() ? StringUtils.addPlaceholder(CookieGadgets.getPetData().getPetMissionIsOnCooldownLore(), "{COOLDOWN_MINUTES}", String.valueOf(playerManager.getPetMissionCooldownMinutes())) : CookieGadgets.getPetData().getReadyToSendPetMissionLore()), EnumItem.SEND_PET_ON_MISSION.getSlot());
        }
        if (EnumItem.CURRENT_SPAWNED_PET.show() && playerManager.getEquippedPet() != null) {
            final PetType equippedPet = playerManager.getEquippedPet();
            inventory(inventory, EnumItem.CURRENT_SPAWNED_PET.getDisplayName(), equippedPet.getMaterial(), PetMessages.getLore(playerManager.getPlayer(), playerManager.getPetData(equippedPet), equippedPet.getUnlockedLore()), null, EnumItem.CURRENT_SPAWNED_PET.getSlot());
        }
        if (EnumItem.RENAME_PET.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RENAME_PET.getItemStack(), EnumItem.RENAME_PET.getSlot());
        }
        if (EnumItem.MAIN_MENU_ITEM.show()) {
            InventoryUtils.mainMenuButton(player, inventory, EnumItem.MAIN_MENU_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler
    public void onInvClickCategoryPets(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(Category.PETS.getGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (!Category.PETS.isEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
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
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.MAIN_MENU_ITEM.getDisplayName(), EnumItem.MAIN_MENU_ITEM.getSlot())) {
            CookieGadgetsAPI.goBackToMainMenu(player);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.SEND_PET_ON_MISSION.getItemStack(), EnumItem.SEND_PET_ON_MISSION.getSlot())) {
            if (!CookieGadgets.getPetData().isEnabled()) {
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (playerManager.isSendPetMissionOnCooldown()) {
                player.sendMessage(MessageType.SEND_PET_FOR_MISSION_IS_ON_COOLDOWN.getFormatMessage().replace("{COOLDOWN_MINUTES}", String.valueOf(playerManager.getPetMissionCooldownMinutes())));
                playerManager.openCategoryPetsMenu();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            boolean b = false;
            for (final GPet gPet : playerManager.petsData().values()) {
                if (gPet.getPetLevel().getLevel() >= 100) {
                    continue;
                }
                if (gPet.getPetAttribute().isHappy()) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                player.sendMessage(MessageType.REQUEST_PET_WITH_HAPPY_STATUS.getFormatMessage());
                player.sendMessage(MessageType.CLICK_TO_INCREASE_HAPPINESS.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            playerManager.sendPetForMission();
            playerManager.openCategoryPetsMenu();
            inventoryClickEvent.setCancelled(true);
        }
        else if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.CURRENT_SPAWNED_PET.getDisplayName(), EnumItem.CURRENT_SPAWNED_PET.getSlot())) {
            final PetType equippedPet = playerManager.getEquippedPet();
            if (equippedPet == null) {
                playerManager.openCategoryPetsMenu();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (CookieGadgets.getPetData().isEnabled() && inventoryClickEvent.getClick() == ClickType.RIGHT) {
                playerManager.setSelectedProvideCareCategoryPet(null);
                playerManager.openPetItemsMenu(playerManager.getPetData(equippedPet));
            }
            else if (playerManager.getEquippedPet() != null && ChatUtil.format(equippedPet.getDisplayName()).equals(playerManager.getEquippedPet().getDisplayName())) {
                playerManager.unequipPet();
                player.sendMessage(MessageType.RESET_PET.getFormatMessage());
                if (EnumItem.RESET_PET.isPlaySoundEnabled()) {
                    EnumItem.RESET_PET.getSound().playSound(player, 1.0f, 2.0f);
                }
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            inventoryClickEvent.setCancelled(true);
        }
        else {
            if (!ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RENAME_PET.getItemStack(), EnumItem.RENAME_PET.getSlot())) {
                for (final PetCategoryType petCategoryType : PetCategoryType.values()) {
                    if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(petCategoryType.getDisplayName()))) {
                        try {
                            playerManager.openPetTypesMenu(petCategoryType, 1);
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            player.sendMessage(MessageType.ERROR.getFormatMessage());
                        }
                    }
                }
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (PermissionUtils.noPermission(player, EnumPermission.RENAME_PET.getPermission(), EnumPermission.ALL_COMMANDS.getPermission(), false)) {
                player.sendMessage(ChatUtil.format(MessageType.NO_PERMISSION.getFormatMessage().replace("{PERMISSION}", EnumPermission.RENAME_PET.getPermission())));
                if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                }
                if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                    player.closeInventory();
                }
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (playerManager.getEquippedPet() == null) {
                player.sendMessage(MessageType.NO_PET_SPAWNED.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            player.sendMessage(MessageType.RENAME_PET_IN_CHAT.getFormatMessage());
            if (!playerManager.isRenamingPet()) {
                playerManager.setRenamingPet(true);
                new BukkitRunnable() {
                    public void run() {
                        if (playerManager.isRenamingPet()) {
                            playerManager.setRenamingPet(false);
                            player.sendMessage(MessageType.TIMED_OUT.getFormatMessage());
                        }
                    }
                }.runTaskLater((Plugin)CookieGadgets.getInstance(), 400L);
            }
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
        }
    }
    
    private static ItemStack inventoryAddGlow(final Player player, final Inventory inventory, final String s, final GMaterial material, final List<String> list, final List<String> list2, final int n, final String s2, final PetCategoryType petCategoryType) {
        if (material.isSkullHead()) {
            String str = material.getTexture();
            if (!str.startsWith("http://textures.minecraft.net/texture/")) {
                str = "http://textures.minecraft.net/texture/" + str;
            }
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
                for (final String s3 : EnumItem.UNLOCKED.getLore()) {
                    if (s3.contains("{HASPERMISSION}") || s3.contains("{SIZE}") || s3.contains("{PERCENTAGE}")) {
                        final int hasPermission = hasPermission(player, petCategoryType);
                        list3.add(ChatUtil.format(s3.replace("{HASPERMISSION}", String.valueOf(hasPermission)).replace("{SIZE}", String.valueOf(getSize(petCategoryType))).replace("{PERCENTAGE}", String.valueOf(hasPermission * 100 / getSize(petCategoryType)))));
                    }
                    else {
                        list3.add(ChatUtil.format(s3));
                    }
                }
                itemMeta.setLore((List)list3);
                itemStack.setItemMeta((ItemMeta)itemMeta);
            }
            return InventoryUtils.skullData(inventory, itemStack, itemMeta, str, n);
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
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(enumMaterial)) {
            itemStack2.setDurability((short)0);
        }
        InventoryUtils.addItemFlags(itemMeta2);
        itemStack2.setItemMeta(itemMeta2);
        if (EnumItem.UNLOCKED.isShowInLore()) {
            for (final String s4 : EnumItem.UNLOCKED.getLore()) {
                if (s4.contains("{HASPERMISSION}") || s4.contains("{SIZE}") || s4.contains("{PERCENTAGE}")) {
                    final int hasPermission2 = hasPermission(player, petCategoryType);
                    list4.add(ChatUtil.format(s4.replace("{HASPERMISSION}", String.valueOf(hasPermission2)).replace("{SIZE}", String.valueOf(getSize(petCategoryType))).replace("{PERCENTAGE}", String.valueOf(hasPermission2 * 100 / getSize(petCategoryType)))));
                }
                else {
                    list4.add(ChatUtil.format(s4));
                }
            }
            itemMeta2.setLore((List)list4);
            itemStack2.setItemMeta(itemMeta2);
        }
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
    
    private static ItemStack inventory(final Player player, final Inventory inventory, final String s, final GMaterial gMaterial, final List<String> list, final List<String> list2, final int n, final PetCategoryType petCategoryType) {
        if (gMaterial.isSkullHead()) {
            String str = gMaterial.getTexture();
            if (!str.startsWith("http://textures.minecraft.net/texture/")) {
                str = "http://textures.minecraft.net/texture/" + str;
            }
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
                        final int hasPermission = hasPermission(player, petCategoryType);
                        list3.add(ChatUtil.format(s2.replace("{HASPERMISSION}", String.valueOf(hasPermission)).replace("{SIZE}", String.valueOf(getSize(petCategoryType))).replace("{PERCENTAGE}", String.valueOf(hasPermission * 100 / getSize(petCategoryType)))));
                    }
                    else {
                        list3.add(ChatUtil.format(s2));
                    }
                }
                skullMeta.setLore((List)list3);
            }
            return InventoryUtils.skullData(inventory, itemStack, skullMeta, str, n);
        }
        final EnumMaterial enumMaterial = gMaterial.getEnumMaterial();
        final ItemStack itemStack2 = new ItemStack(enumMaterial.getType(), 1, (short)gMaterial.getData());
        final ItemMeta itemMeta = itemStack2.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.format(s));
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
            itemMeta.setLore((List)list4);
        }
        if (EnumItem.UNLOCKED.isShowInLore()) {
            for (final String s3 : EnumItem.UNLOCKED.getLore()) {
                if (s3.contains("{HASPERMISSION}") || s3.contains("{SIZE}") || s3.contains("{PERCENTAGE}")) {
                    final int hasPermission2 = hasPermission(player, petCategoryType);
                    list4.add(ChatUtil.format(s3.replace("{HASPERMISSION}", String.valueOf(hasPermission2)).replace("{SIZE}", String.valueOf(getSize(petCategoryType))).replace("{PERCENTAGE}", String.valueOf(hasPermission2 * 100 / getSize(petCategoryType)))));
                }
                else {
                    list4.add(ChatUtil.format(s3));
                }
            }
            itemMeta.setLore((List)list4);
        }
        return InventoryUtils.itemData(inventory, enumMaterial, gMaterial.getData(), itemStack2, itemMeta, n);
    }
    
    private static ItemStack inventory(final Inventory inventory, final String s, final GMaterial gMaterial, final List<String> list, final List<String> list2, final int n) {
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
            return skullData(inventory, itemStack, skullMeta, str, lore, s, n);
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
        return itemData(inventory, enumMaterial, gMaterial.getData(), itemStack2, (ItemMeta)itemMeta, lore2, s, n);
    }
    
    private static ItemStack skullData(final Inventory inventory, final ItemStack itemStack, final SkullMeta itemMeta, final String s, final ArrayList<String> lore, final String s2, final int n) {
        final Iterator<String> iterator = CookieGadgets.getPetData().getDespawnPetLore().iterator();
        while (iterator.hasNext()) {
            lore.add(ChatUtil.format(iterator.next()));
        }
        if (CookieGadgets.getPetData().isEnabled()) {
            final Iterator<String> iterator2 = CookieGadgets.getPetData().getProvideCareLore().iterator();
            while (iterator2.hasNext()) {
                lore.add(ChatUtil.format(iterator2.next()));
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
    
    private static ItemStack itemData(final Inventory inventory, final EnumMaterial material, final int data, ItemStack itemStack, final ItemMeta itemMeta, final ArrayList<String> lore, final String s, final int n) {
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(material)) {
            itemStack.setDurability((short)0);
        }
        InventoryUtils.addItemFlags(itemMeta);
        final Iterator<String> iterator = CookieGadgets.getPetData().getDespawnPetLore().iterator();
        while (iterator.hasNext()) {
            lore.add(ChatUtil.format(iterator.next()));
        }
        if (CookieGadgets.getPetData().isEnabled()) {
            final Iterator<String> iterator2 = CookieGadgets.getPetData().getProvideCareLore().iterator();
            while (iterator2.hasNext()) {
                lore.add(ChatUtil.format(iterator2.next()));
            }
        }
        itemMeta.setLore((List)lore);
        itemStack.setItemMeta(itemMeta);
        if (material.toString().endsWith("SPAWN_EGG") && VersionManager.is1_9OrAbove() && !VersionManager.is1_13OrAbove()) {
            itemStack = CookieGadgets.getNMSManager().spawnEgg(itemStack, EntityType.fromId(data).getName());
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(material)) {
            itemStack = CookieGadgets.getNMSManager().getPotionFromId(itemStack, material, data);
        }
        inventory.setItem(n, itemStack);
        return itemStack;
    }
    
    private static int hasPermission(final Player player, final PetCategoryType petCategoryType) {
        if (!petCategoryType.isEnabled()) {
            return 0;
        }
        if (player.hasPermission(EnumPermission.PETS.getPermission())) {
            return getSize(petCategoryType);
        }
        int n = 0;
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        for (final PetType petType : PetType.getPetCategory(petCategoryType)) {
            if (playerManager == null) {
                break;
            }
            if (!petType.isEnabled() || (!player.hasPermission(petType.getPermission()) && !playerManager.hasPermission(petType.getPermission()))) {
                continue;
            }
            ++n;
        }
        return n;
    }
    
    private static int getSize(final PetCategoryType petCategoryType) {
        return PetType.getPetCategory(petCategoryType).size();
    }
}
