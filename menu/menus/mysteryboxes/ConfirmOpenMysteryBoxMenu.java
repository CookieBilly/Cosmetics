

package ws.billy.CookieGadgets.menu.menus.mysteryboxes;

import ws.billy.CookieGadgets.nms.interfaces.IJSONMessage;
import java.util.Iterator;
import java.sql.Timestamp;
import ws.billy.CookieGadgets.utils.GCommandHandler;
import ws.billy.CookieGadgets.utils.StringUtils;
import ws.billy.CookieGadgets.cosmetics.MainMenuType;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.MysteryBoxJSONMessages;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxesLoot;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxesMessages;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.LootCategory;
import org.bukkit.event.Event;
import ws.billy.CookieGadgets.api.event.mysteryboxes.OpenMysteryBoxEvent;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxes;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ConfirmOpenMysteryBoxMenu implements Listener
{
    public static void openConfirmOpenMysteryBoxMenu(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.format(CookieGadgets.getCookieGadgetsData().getConfirmOpenMysteryBoxGUIName()));
        final MysteryBoxes selectedMysteryBox = CookieGadgets.getPlayerManager(player).getSelectedMysteryBox();
        if (selectedMysteryBox == null) {
            throw new IllegalArgumentException("Player must select a mystery box before opening this menu.");
        }
        InventoryUtils.inventory(inventory, selectedMysteryBox.getMysteryBoxType().getDisplayName(), selectedMysteryBox.getMysteryBoxType().getMaterial(), selectedMysteryBox.getLore(), 13);
        InventoryUtils.inventory(inventory, EnumItem.CONFIRM_OPEN_MYSTERY_BOX.getDisplayName(), EnumItem.CONFIRM_OPEN_MYSTERY_BOX.getMaterial(), EnumItem.CONFIRM_OPEN_MYSTERY_BOX.getLore(), EnumItem.CONFIRM_OPEN_MYSTERY_BOX.getSlot());
        InventoryUtils.inventory(inventory, EnumItem.CANCEL_OPEN_MYSTERY_BOX.getDisplayName(), EnumItem.CANCEL_OPEN_MYSTERY_BOX.getMaterial(), EnumItem.CANCEL_OPEN_MYSTERY_BOX.getLore(), EnumItem.CANCEL_OPEN_MYSTERY_BOX.getSlot());
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInvClickConfirmOpenMysteryBoxMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player2 = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(ChatUtil.format(CookieGadgets.getCookieGadgetsData().getConfirmOpenMysteryBoxGUIName()))) {
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
            player2.updateInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (!CookieGadgets.getCookieGadgetsData().isMysteryBoxesEnabled() || !WorldUtils.isWorldEnabled(player2, true)) {
            SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(player2, 1.0f, 0.5f);
            player2.closeInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.CONFIRM_OPEN_MYSTERY_BOX.getDisplayName(), EnumItem.CONFIRM_OPEN_MYSTERY_BOX.getSlot())) {
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(player2);
            if (playerManager == null) {
                player2.sendMessage(MessageType.ERROR.getFormatMessage());
                player2.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (playerManager.isOpeningMysteryBox()) {
                player2.sendMessage(MessageType.CAN_ONLY_OPEN_ONE_MYSTERY_BOX.getFormatMessage());
                player2.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            final MysteryVault selectedMysteryVault = playerManager.getSelectedMysteryVault();
            if (selectedMysteryVault == null || playerManager.getSelectedMysteryBox() == null) {
                player2.sendMessage(MessageType.ERROR.getFormatMessage());
                player2.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            final OpenMysteryBoxEvent openMysteryBoxEvent = new OpenMysteryBoxEvent(player2, playerManager.getSelectedMysteryBox());
            Bukkit.getServer().getPluginManager().callEvent((Event)openMysteryBoxEvent);
            if (openMysteryBoxEvent.isCancelled()) {
                player2.sendMessage(MessageType.ERROR.getFormatMessage());
                player2.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (selectedMysteryVault.isActivated()) {
                player2.sendMessage(MessageType.OPEN_MYSTERY_VAULT_AT_A_TIME.getFormatMessage());
                player2.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            final MysteryBoxes selectedMysteryBox = playerManager.getSelectedMysteryBox();
            final MysteryBoxesLoot open = selectedMysteryBox.open();
            if (open == null || (open.getLootCategory() == LootCategory.BUILT_IN && (open.getCategory() == null || open.getName() == null || open.getPermission() == null)) || (open.getLootCategory() == LootCategory.CUSTOM && (!open.customMysteryBoxesLoot().canBeFound() || open.customMysteryBoxesLoot().getName() == null || open.customMysteryBoxesLoot().getCategoryName() == null))) {
                player2.sendMessage(MessageType.ERROR.getFormatMessage());
                player2.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (!playerManager.isRandomMysteryVaultAnimationSelected() && PermissionUtils.noPermission(playerManager.getPlayer(), playerManager.getMysteryVaultAnimation().getPermission(), EnumPermission.ALL_MYSTERY_VAULT_ANIMATIONS.getPermission(), true)) {
                inventoryClickEvent.setCancelled(true);
                return;
            }
            selectedMysteryVault.setActivate(true);
            if (!playerManager.isRandomMysteryVaultAnimationSelected() && !playerManager.getMysteryVaultAnimation().isEnabled()) {
                playerManager.setMysteryVaultAnimation(AnimationType.enabled().get(0));
            }
            playerManager.setOpeningMysteryBox(true);
            AnimationType randomMysteryVaultAnimation = null;
            if (playerManager.isRandomMysteryVaultAnimationSelected()) {
                randomMysteryVaultAnimation = playerManager.getRandomMysteryVaultAnimation();
                if (PermissionUtils.noPermission(playerManager.getPlayer(), randomMysteryVaultAnimation.getPermission(), EnumPermission.ALL_MYSTERY_VAULT_ANIMATIONS.getPermission(), true)) {
                    selectedMysteryVault.setActivate(false);
                    playerManager.setOpeningMysteryBox(false);
                    player2.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                playerManager.setAnimation(randomMysteryVaultAnimation.equip(playerManager, selectedMysteryVault, selectedMysteryBox.getQuality(), 0L));
            }
            else {
                playerManager.setAnimation(playerManager.getMysteryVaultAnimation().equip(playerManager, selectedMysteryVault, selectedMysteryBox.getQuality(), 0L));
            }
            if (MysteryBoxesMessages.isOpeningMysteryBoxBoardcastEnabled()) {
                final Player player3;
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> Bukkit.getOnlinePlayers().forEach(player -> {
                    if (WorldUtils.isWorldEnabled(player.getWorld()) && player.canSee(player3) && player != player3) {
                        player.sendMessage(ChatUtil.format(MysteryBoxesMessages.getOpeningMysteryBoxBoardcastMessage().replace("{PLAYER}", player3.getDisplayName())));
                    }
                }));
            }
            final Player player4;
            final PlayerManager playerManager2;
            final MysteryVault mysteryVault;
            final MysteryBoxesLoot mysteryBoxesLoot;
            final MysteryBoxes mysteryBoxes;
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> {
                if (player4 == null || !player4.isOnline() || playerManager2 == null || !playerManager2.isOpeningMysteryBox()) {
                    return;
                }
                else {
                    sendLootMessage(playerManager2, player4, mysteryVault, mysteryBoxesLoot);
                    playerManager2.removeMysteryBox(mysteryBoxes);
                    playerManager2.setSelectedMysteryBox(null);
                    return;
                }
            }, playerManager.isRandomMysteryVaultAnimationSelected() ? randomMysteryVaultAnimation.getDurationTime() : playerManager.getMysteryVaultAnimation().getDurationTime());
            player2.closeInventory();
            inventoryClickEvent.setCancelled(true);
        }
        else {
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.CANCEL_OPEN_MYSTERY_BOX.getDisplayName(), EnumItem.CANCEL_OPEN_MYSTERY_BOX.getSlot())) {
                CookieGadgets.getPlayerManager(player2).openMysteryVaultMenu(1);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            inventoryClickEvent.setCancelled(true);
        }
    }
    
    private static void sendLootMessage(final PlayerManager playerManager, final Player player, final MysteryVault mysteryVault, final MysteryBoxesLoot mysteryBoxesLoot) {
        final String displayNameStripColor = mysteryBoxesLoot.getDisplayNameStripColor();
        int randomPetItemsInRange = 0;
        if (mysteryBoxesLoot.getRarity().isGivePetItemsEnabled()) {
            randomPetItemsInRange = mysteryBoxesLoot.getRarity().getRandomPetItemsInRange();
            final Iterator<Integer> iterator = MathUtil.splitNumber(randomPetItemsInRange, 20).iterator();
            while (iterator.hasNext()) {
                playerManager.addPetItems(PetItems.getRandomPetItems(), iterator.next());
            }
        }
        if (mysteryBoxesLoot.getRarity().isSendMessageEnabled()) {
            for (final String s : mysteryBoxesLoot.getRarity().getFoundLootMessages()) {
                if (s.contains("{LOOT}")) {
                    CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(s.replace("{LOOT}", displayNameStripColor))).showText(StringUtils.addPlaceholders(MysteryBoxJSONMessages.valueOf(mysteryBoxesLoot.getRarity()).getMessage(), Arrays.asList("{LOOT}", "{CATEGORY}"), Arrays.asList(displayNameStripColor, (mysteryBoxesLoot.getCategory() == null) ? mysteryBoxesLoot.customMysteryBoxesLoot().getCategoryName() : MainMenuType.valueOf(mysteryBoxesLoot.getCategory().getName()).getDisplayName()))).send(player);
                }
                else if (s.contains("{PET_ITEMS}")) {
                    CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(s.replace("{PET_ITEMS}", String.valueOf(randomPetItemsInRange)))).showText(StringUtils.addPlaceholder(MysteryBoxJSONMessages.PET_ITEMS.getMessage(), "{PET_ITEMS}", String.valueOf(randomPetItemsInRange))).send(player);
                }
                else {
                    player.sendMessage(ChatUtil.format(s));
                }
            }
        }
        if (mysteryBoxesLoot.getRarity().isPlaySoundEnabled()) {
            mysteryBoxesLoot.getRarity().getSound().playSound(mysteryVault.getLocation());
        }
        if (mysteryBoxesLoot.getRarity().isBroadcastMessagesEnabled()) {
            MysteryBoxJSONMessages.valueOf(mysteryBoxesLoot.getRarity());
            final MysteryBoxJSONMessages mysteryBoxJSONMessages;
            final IJSONMessage ijsonMessage;
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> Bukkit.getOnlinePlayers().forEach(player2 -> {
                CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(mysteryBoxesLoot.getRarity().getBroadcastMessage().replace("{PLAYER}", player.getDisplayName()).replace("{LOOT}", displayNameStripColor))).showText(StringUtils.addPlaceholders(mysteryBoxJSONMessages.getMessage(), Arrays.asList("{LOOT}", "{CATEGORY}"), Arrays.asList(displayNameStripColor, (mysteryBoxesLoot.getCategory() == null) ? mysteryBoxesLoot.customMysteryBoxesLoot().getCategoryName() : MainMenuType.valueOf(mysteryBoxesLoot.getCategory().getName()).getDisplayName())));
                if (WorldUtils.isWorldEnabled(player2.getWorld()) && player2.canSee(player) && player2 != player) {
                    ijsonMessage.send(player2);
                }
            }));
        }
        mysteryVault.createLootHologram(mysteryBoxesLoot.getRarity().getFoundLootHologram().replace("{LOOT}", displayNameStripColor));
        mysteryVault.removeLootHologram(3);
        if (mysteryBoxesLoot.getLootCategory() != LootCategory.BUILT_IN && mysteryBoxesLoot.getPermission() == null) {
            if (mysteryBoxesLoot.customMysteryBoxesLoot().executeCustomCommand()) {
                final Iterator<String> iterator3 = mysteryBoxesLoot.customMysteryBoxesLoot().getCustomCommands().iterator();
                while (iterator3.hasNext()) {
                    GCommandHandler.executeCommand(iterator3.next().replace("{PLAYER}", player.getName()));
                }
            }
        }
        else if (!player.hasPermission(mysteryBoxesLoot.getPermission()) && !playerManager.hasPermission(mysteryBoxesLoot.getPermission())) {
            if (CookieGadgets.getCookieGadgetsData().isFoundMysteryBoxLootCommandEnabled()) {
                GCommandHandler.executeCommand(CookieGadgets.getCookieGadgetsData().getFoundMysteryBoxLootCommand().replace("{PLAYER}", player.getName()).replace("{PERMISSION}", mysteryBoxesLoot.getPermission()));
            }
            else {
                playerManager.addUnlockedCosmeticItem(mysteryBoxesLoot.getCategory(), mysteryBoxesLoot.getName(), null);
            }
            if (CookieGadgets.getCookieGadgetsData().isAutoEquipOnLootFoundEnabled() || !PermissionUtils.noPermission(player, EnumPermission.AUTO_EQUIP_ON_LOOT_FOUND.getPermission(), false)) {
                mysteryBoxesLoot.equip(playerManager);
            }
        }
        else {
            int randomMysteryDustInRange = 0;
            if (mysteryBoxesLoot.getRarity().isGiveMysteryDustEnabled()) {
                randomMysteryDustInRange = mysteryBoxesLoot.getRarity().getRandomMysteryDustInRange();
                playerManager.addMysteryDust(randomMysteryDustInRange);
            }
            if (mysteryBoxesLoot.getRarity().isSendAlreadyHadLootMessagesEnabled()) {
                final Iterator<String> iterator4 = mysteryBoxesLoot.getRarity().getAlreadyHadLootMessages().iterator();
                while (iterator4.hasNext()) {
                    CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(iterator4.next().replace("{MYSTERY_DUST}", String.valueOf(randomMysteryDustInRange)).replace("{LOOT}", displayNameStripColor))).showText(MysteryBoxJSONMessages.MYSTERY_DUST.getMessage()).send(player);
                }
            }
            if (mysteryBoxesLoot.getRarity().executeCustomCommand()) {
                GCommandHandler.executeCommand(mysteryBoxesLoot.getRarity().getCustomCommand().replace("{PLAYER}", player.getName()));
            }
        }
        String string = "";
        if (MysteryBoxesMessages.isRecentLootContainRarity()) {
            string = String.valueOf(mysteryBoxesLoot.getRarity().getDisplayName()) + " ";
        }
        if (playerManager.getRecentLootsFound() == null) {
            playerManager.setRecentLootsFound(String.valueOf(string) + mysteryBoxesLoot.getDisplayName());
        }
        else {
            String s2 = "";
            for (int i = 0; i <= ((playerManager.getRecentLootsFound().length >= 5) ? 3 : (playerManager.getRecentLootsFound().length - 1)); ++i) {
                s2 = String.valueOf(s2) + playerManager.getRecentLootsFound()[i] + ", ";
            }
            if (s2.endsWith(", ")) {
                s2 = ChatUtil.format(s2.substring(0, s2.length() - 2));
            }
            playerManager.setRecentLootsFound(String.valueOf(string) + mysteryBoxesLoot.getDisplayName() + ", " + s2);
        }
    }
}
