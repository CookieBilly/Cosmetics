

package ws.billy.CookieGadgets.menu.menus.mysteryboxes;

import java.util.Iterator;
import java.util.List;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import ws.billy.CookieGadgets.utils.MathUtil;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxesMessages;
import java.sql.Timestamp;
import ws.billy.CookieGadgets.utils.GCommandHandler;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.LootCategory;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxes;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxesLoot;
import java.util.ArrayList;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Quality;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.event.Event;
import ws.billy.CookieGadgets.api.event.mysteryboxes.OpenMysteryBoxEvent;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.EnumItem;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ConfirmOpenMultipleBoxesMenu implements Listener
{
    public static void openConfirmOpenMultipleBoxesMenu(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 27, CookieGadgets.getCookieGadgetsData().getConfirmOpenMultipleBoxesGUIName());
        InventoryUtils.inventory(inventory, EnumItem.CONFIRM_OPEN_MULTIPLE_BOXES.getItemStack(), EnumItem.CONFIRM_OPEN_MULTIPLE_BOXES.getSlot());
        InventoryUtils.inventory(inventory, EnumItem.CANCEL_OPEN_MULTIPLE_BOXES.getItemStack(), EnumItem.CANCEL_OPEN_MULTIPLE_BOXES.getSlot());
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInvClickConfirmOpenMultipleBoxesMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(ChatUtil.format(CookieGadgets.getCookieGadgetsData().getConfirmOpenMultipleBoxesGUIName()))) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 27 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
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
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.CONFIRM_OPEN_MULTIPLE_BOXES.getDisplayName(), EnumItem.CONFIRM_OPEN_MULTIPLE_BOXES.getSlot())) {
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
            if (playerManager == null) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            final MysteryVault selectedMysteryVault = playerManager.getSelectedMysteryVault();
            if (playerManager.isOpeningMysteryBox()) {
                player.sendMessage(MessageType.CAN_ONLY_OPEN_ONE_MYSTERY_BOX.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (selectedMysteryVault == null || playerManager.getOpenMultipleBoxesType() == null) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            final OpenMysteryBoxEvent openMysteryBoxEvent = new OpenMysteryBoxEvent(player, playerManager.getSelectedMysteryBox());
            Bukkit.getServer().getPluginManager().callEvent((Event)openMysteryBoxEvent);
            if (openMysteryBoxEvent.isCancelled()) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (selectedMysteryVault.isActivated()) {
                player.sendMessage(MessageType.OPEN_MYSTERY_VAULT_AT_A_TIME.getFormatMessage());
                player.closeInventory();
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
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                playerManager.setAnimation(randomMysteryVaultAnimation.equip(playerManager, selectedMysteryVault, Quality.FIVE_STAR, 0L));
            }
            else {
                playerManager.setAnimation(playerManager.getMysteryVaultAnimation().equip(playerManager, selectedMysteryVault, Quality.FIVE_STAR, 0L));
            }
            final Player player2;
            final PlayerManager playerManager2;
            final MysteryVault mysteryVault;
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> {
                if (player2 == null || !player2.isOnline() || playerManager2 == null || !playerManager2.isOpeningMysteryBox()) {
                    return;
                }
                else {
                    openBoxes(playerManager2, mysteryVault, playerManager2.getOpenMultipleBoxesType());
                    return;
                }
            }, playerManager.isRandomMysteryVaultAnimationSelected() ? randomMysteryVaultAnimation.getDurationTime() : playerManager.getMysteryVaultAnimation().getDurationTime());
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
        }
        else {
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.CANCEL_OPEN_MULTIPLE_BOXES.getDisplayName(), EnumItem.CANCEL_OPEN_MULTIPLE_BOXES.getSlot())) {
                CookieGadgets.getPlayerManager(player).openMysteryVaultMenu(1);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            inventoryClickEvent.setCancelled(true);
        }
    }
    
    private static void openBoxes(final PlayerManager playerManager, final MysteryVault mysteryVault, final OpenMultipleBoxesType openMultipleBoxesType) {
        final ArrayList<MysteryBoxesLoot> list = new ArrayList<MysteryBoxesLoot>();
        final ArrayList<MysteryBoxesLoot> list2 = new ArrayList<MysteryBoxesLoot>();
        final ArrayList<MysteryBoxesLoot> list3 = new ArrayList<MysteryBoxesLoot>();
        final ArrayList<MysteryBoxesLoot> list4 = new ArrayList<MysteryBoxesLoot>();
        int i = 0;
        int j = 0;
        final int mysteryBoxes = playerManager.getMysteryBoxes();
        int mysteryBoxes2 = playerManager.getMysteryBoxes();
        int k = 0;
        if (openMultipleBoxesType == OpenMultipleBoxesType.OPEN_20_BOXES && playerManager.getMysteryBoxes() >= 20) {
            mysteryBoxes2 = 20;
        }
        else if (openMultipleBoxesType == OpenMultipleBoxesType.OPEN_50_BOXES && playerManager.getMysteryBoxes() >= 50) {
            mysteryBoxes2 = 50;
        }
        else if (openMultipleBoxesType == OpenMultipleBoxesType.OPEN_250_BOXES && playerManager.getMysteryBoxes() >= 250) {
            mysteryBoxes2 = 250;
        }
        for (int n = 1; n <= mysteryBoxes2 && mysteryBoxes - n >= 0; ++n) {
            final MysteryBoxes mysteryBoxes3 = playerManager.mysteryBoxes().get(mysteryBoxes - n);
            if (mysteryBoxes3.isRequiredPermission() && PermissionUtils.noPermission(playerManager.getPlayer(), mysteryBoxes3.getMysteryBoxType().getPermission(), EnumPermission.OPEN_ALL_MYSTERY_BOXES.getPermission(), false)) {
                ++mysteryBoxes2;
            }
            else {
                final MysteryBoxesLoot open = mysteryBoxes3.open();
                if (open == null || (open.getLootCategory() == LootCategory.BUILT_IN && (open.getCategory() == null || open.getName() == null || open.getPermission() == null)) || (open.getLootCategory() == LootCategory.CUSTOM && (!open.customMysteryBoxesLoot().canBeFound() || open.customMysteryBoxesLoot().getName() == null || open.customMysteryBoxesLoot().getCategoryName() == null))) {
                    ++mysteryBoxes2;
                }
                else {
                    ++k;
                    if (open.getRarity() == Rarity.COMMON) {
                        for (final MysteryBoxesLoot o : list) {
                            if (o.toString().equalsIgnoreCase(open.toString())) {
                                list.remove(o);
                                break;
                            }
                        }
                        list.add(open);
                    }
                    else if (open.getRarity() == Rarity.RARE) {
                        for (final MysteryBoxesLoot o2 : list2) {
                            if (o2.toString().equalsIgnoreCase(open.toString())) {
                                list2.remove(o2);
                                break;
                            }
                        }
                        list2.add(open);
                    }
                    else if (open.getRarity() == Rarity.EPIC) {
                        for (final MysteryBoxesLoot o3 : list3) {
                            if (o3.toString().equalsIgnoreCase(open.toString())) {
                                list3.remove(o3);
                                break;
                            }
                        }
                        list3.add(open);
                    }
                    else if (open.getRarity() == Rarity.LEGENDARY) {
                        for (final MysteryBoxesLoot o4 : list4) {
                            if (o4.toString().equalsIgnoreCase(open.toString())) {
                                list4.remove(o4);
                                break;
                            }
                        }
                        list4.add(open);
                    }
                    if (open.getRarity().isGivePetItemsEnabled()) {
                        j += open.getRarity().getRandomPetItemsInRange();
                    }
                    if (open.getLootCategory() != LootCategory.BUILT_IN && open.getPermission() == null) {
                        if (open.customMysteryBoxesLoot().executeCustomCommand()) {
                            final Iterator<String> iterator5 = open.customMysteryBoxesLoot().getCustomCommands().iterator();
                            while (iterator5.hasNext()) {
                                GCommandHandler.executeCommand(iterator5.next().replace("{PLAYER}", playerManager.getPlayer().getName()));
                            }
                        }
                    }
                    else if (!playerManager.getPlayer().hasPermission(open.getPermission()) && !playerManager.hasPermission(open.getPermission())) {
                        if (CookieGadgets.getCookieGadgetsData().isFoundMysteryBoxLootCommandEnabled()) {
                            GCommandHandler.executeCommand(CookieGadgets.getCookieGadgetsData().getFoundMysteryBoxLootCommand().replace("{PLAYER}", playerManager.getPlayer().getName()).replace("{PERMISSION}", open.getPermission()));
                        }
                        else {
                            playerManager.addUnlockedCosmeticItem(open.getCategory(), open.getName(), null);
                        }
                    }
                    else {
                        if (open.getRarity().isGiveMysteryDustEnabled()) {
                            i += open.getRarity().getRandomMysteryDustInRange();
                        }
                        if (open.getRarity().executeCustomCommand()) {
                            GCommandHandler.executeCommand(open.getRarity().getCustomCommand().replace("{PLAYER}", playerManager.getPlayer().getName()));
                        }
                    }
                    String string = "";
                    if (MysteryBoxesMessages.isRecentLootContainRarity()) {
                        string = String.valueOf(open.getRarity().getDisplayName()) + " ";
                    }
                    if (playerManager.getRecentLootsFound() == null) {
                        playerManager.setRecentLootsFound(String.valueOf(string) + open.getDisplayName() + ", ");
                    }
                    else {
                        String str = "";
                        for (int l = 0; l <= ((playerManager.getRecentLootsFound().length >= 5) ? 3 : (playerManager.getRecentLootsFound().length - 1)); ++l) {
                            if (l == 3 && playerManager.getRecentLootsFound().length >= 5) {
                                str = String.valueOf(str) + playerManager.getRecentLootsFound()[l];
                                break;
                            }
                            str = String.valueOf(str) + playerManager.getRecentLootsFound()[l] + ", ";
                        }
                        if (str.endsWith(", ")) {
                            str = ChatUtil.format(str.substring(0, str.length() - 2));
                        }
                        playerManager.setRecentLootsFound(String.valueOf(string) + open.getDisplayName() + ", " + str);
                    }
                    playerManager.removeMysteryBox(mysteryBoxes3);
                }
            }
        }
        playerManager.addMysteryDust(i);
        if (j > 0) {
            final int n2;
            final Iterator<Integer> iterator6;
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                MathUtil.splitNumber(n2, 100).iterator();
                while (iterator6.hasNext()) {
                    playerManager.addPetItems(PetItems.getRandomPetItems(), iterator6.next());
                }
                return;
            });
        }
        final ArrayList<String> contents = new ArrayList<String>();
        int n3 = 1;
        String e = "";
        for (final MysteryBoxesLoot mysteryBoxesLoot : list4) {
            e = String.valueOf(e) + mysteryBoxesLoot.getRarity().getDisplayName() + " " + mysteryBoxesLoot.getDisplayName() + ((n3 == 14) ? "" : "\n");
            if (e.replace("\n", "").length() > 260) {
                if (e.endsWith("\n")) {
                    e = ChatUtil.format(e.substring(0, e.length() - 1));
                }
                contents.add(e);
                n3 = 1;
                e = "";
            }
            else if (n3 == 14) {
                contents.add(e);
                n3 = 1;
                e = "";
            }
            else {
                ++n3;
            }
        }
        for (final MysteryBoxesLoot mysteryBoxesLoot2 : list3) {
            e = String.valueOf(e) + mysteryBoxesLoot2.getRarity().getDisplayName() + " " + mysteryBoxesLoot2.getDisplayName() + ((n3 == 14) ? "" : "\n");
            if (e.replace("\n", "").length() > 260) {
                if (e.endsWith("\n")) {
                    e = ChatUtil.format(e.substring(0, e.length() - 1));
                }
                contents.add(e);
                n3 = 1;
                e = "";
            }
            else if (n3 == 14) {
                contents.add(e);
                n3 = 1;
                e = "";
            }
            else {
                ++n3;
            }
        }
        for (final MysteryBoxesLoot mysteryBoxesLoot3 : list2) {
            e = String.valueOf(e) + mysteryBoxesLoot3.getRarity().getDisplayName() + " " + mysteryBoxesLoot3.getDisplayName() + ((n3 == 14) ? "" : "\n");
            if (e.replace("\n", "").length() > 260) {
                if (e.endsWith("\n")) {
                    e = ChatUtil.format(e.substring(0, e.length() - 1));
                }
                contents.add(e);
                n3 = 1;
                e = "";
            }
            else if (n3 == 14) {
                contents.add(e);
                n3 = 1;
                e = "";
            }
            else {
                ++n3;
            }
        }
        for (final MysteryBoxesLoot mysteryBoxesLoot4 : list) {
            e = String.valueOf(e) + mysteryBoxesLoot4.getRarity().getDisplayName() + " " + mysteryBoxesLoot4.getDisplayName() + ((n3 == 14) ? "" : "\n");
            if (e.replace("\n", "").length() > 260) {
                if (e.endsWith("\n")) {
                    e = ChatUtil.format(e.substring(0, e.length() - 1));
                }
                contents.add(e);
                n3 = 1;
                e = "";
            }
            else if (n3 == 14) {
                contents.add(e);
                n3 = 1;
                e = "";
            }
            else {
                ++n3;
            }
        }
        if (n3 != 1) {
            if (e.endsWith("\n")) {
                e = ChatUtil.format(e.substring(0, e.length() - 1));
            }
            contents.add(e);
        }
        String string2 = "";
        final Iterator<String> iterator11 = EnumItem.MULTIPLE_BOXES_LOOT_BOOK.getLore().iterator();
        while (iterator11.hasNext()) {
            string2 = String.valueOf(string2) + ChatUtil.format(String.valueOf(iterator11.next()) + "\n").replace("{MYSTERY_DUST}", String.valueOf(i)).replace("{PET_ITEMS}", String.valueOf(j)).replace("{MYSTERY_BOXES_OPENED}", String.valueOf(k));
        }
        CookieGadgets.getNMSManager().openBook(CookieGadgets.getNMSManager().createBook("Mystery Boxes", string2, contents), playerManager.getPlayer());
        if (list4.size() > 0) {
            if (Rarity.LEGENDARY.isPlaySoundEnabled()) {
                Rarity.LEGENDARY.getSound().playSound(mysteryVault.getLocation());
            }
            mysteryVault.createLootHologram(Rarity.LEGENDARY.getFoundLootHologram().replace("{LOOT}", list4.get(CookieGadgets.random().nextInt(list4.size())).getDisplayNameStripColor()));
            mysteryVault.removeLootHologram(3);
            return;
        }
        if (list3.size() > 0) {
            if (Rarity.EPIC.isPlaySoundEnabled()) {
                Rarity.EPIC.getSound().playSound(mysteryVault.getLocation());
            }
            mysteryVault.createLootHologram(Rarity.EPIC.getFoundLootHologram().replace("{LOOT}", list3.get(CookieGadgets.random().nextInt(list3.size())).getDisplayNameStripColor()));
            mysteryVault.removeLootHologram(3);
            return;
        }
        if (list2.size() > 0) {
            if (Rarity.RARE.isPlaySoundEnabled()) {
                Rarity.RARE.getSound().playSound(mysteryVault.getLocation());
            }
            mysteryVault.createLootHologram(Rarity.RARE.getFoundLootHologram().replace("{LOOT}", list2.get(CookieGadgets.random().nextInt(list2.size())).getDisplayNameStripColor()));
            mysteryVault.removeLootHologram(3);
            return;
        }
        if (list.size() > 0) {
            if (Rarity.COMMON.isPlaySoundEnabled()) {
                Rarity.COMMON.getSound().playSound(mysteryVault.getLocation());
            }
            mysteryVault.createLootHologram(Rarity.COMMON.getFoundLootHologram().replace("{LOOT}", list.get(CookieGadgets.random().nextInt(list.size())).getDisplayNameStripColor()));
            mysteryVault.removeLootHologram(3);
        }
    }
    
    public enum OpenMultipleBoxesType
    {
        OPEN_20_BOXES("OPEN_20_BOXES", 0, 20), 
        OPEN_50_BOXES("OPEN_50_BOXES", 1, 50), 
        OPEN_250_BOXES("OPEN_250_BOXES", 2, 250);
        
        private int openBoxes;
        
        private OpenMultipleBoxesType(final String name, final int ordinal, final int openBoxes) {
            this.openBoxes = openBoxes;
        }
        
        public int getOpenBoxes() {
            return this.openBoxes;
        }
    }
}
