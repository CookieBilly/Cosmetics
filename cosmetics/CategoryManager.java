

package ws.billy.CookieGadgets.cosmetics;

import ws.billy.CookieGadgets.utils.EnumEquipType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetCategoryType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.cosmetics.emotes.EmoteType;
import ws.billy.CookieGadgets.cosmetics.banners.BannerType;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHatType;
import ws.billy.CookieGadgets.cosmetics.hats.standard.HatType;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.CookieGadgets;
import org.apache.commons.lang.Validate;
import ws.billy.CookieGadgets.player.PlayerManager;

public class CategoryManager
{
    public static boolean removeHelmetCosmetic(final PlayerManager playerManager) {
        Validate.notNull((Object)playerManager);
        final Player player = playerManager.getPlayer();
        if (player.getInventory() != null && player.getInventory().getHelmet() != null && player.getInventory().getHelmet().hasItemMeta() && CookieGadgets.getNMSManager().hasNBTTag(player.getInventory().getHelmet(), "Cosmetics")) {
            player.getInventory().setHelmet((ItemStack)null);
            player.updateInventory();
        }
        if (playerManager.getEquippedHat() != null) {
            playerManager.setEquippedHat(null);
        }
        else if (playerManager.getEquippedAnimatedHat() != null) {
            if (playerManager.getCurrentAnimatedHat() != null) {
                playerManager.removeAnimatedHat();
            }
            playerManager.setEquippedAnimatedHat(null);
        }
        else if (!playerManager.getEquippedSuitEquipment().isEmpty() && playerManager.getEquippedSuitEquipment().containsKey(EnumArmorType.HELMET)) {
            if (playerManager.getCurrentSuit() != null) {
                playerManager.getCurrentSuit().onClear();
                playerManager.removeSuit();
            }
            if (!playerManager.getEquippedSuitEquipment().isEmpty()) {
                playerManager.getEquippedSuitEquipment().remove(EnumArmorType.HELMET);
            }
        }
        else if (playerManager.getEquippedBanner() != null) {
            playerManager.setEquippedBanner(null);
        }
        else if (playerManager.getEquippedEmote() != null) {
            playerManager.setEquippedEmote(null);
            if (playerManager.getCurrentEmote() != null) {
                playerManager.removeEmote();
            }
        }
        return true;
    }
    
    public static boolean removeHelmetCosmetic(final PlayerManager playerManager, final Category category) {
        Validate.notNull((Object)playerManager);
        Validate.notNull((Object)category);
        final Player player = playerManager.getPlayer();
        if (player != null && player.getInventory() != null && player.getInventory().getHelmet() != null && player.getInventory().getHelmet().hasItemMeta() && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getHelmet(), "Category", category.getNBTTag())) {
            player.getInventory().setHelmet((ItemStack)null);
            player.updateInventory();
        }
        return true;
    }
    
    public static boolean removeChestplateCosmetic(final PlayerManager playerManager) {
        Validate.notNull((Object)playerManager);
        final Player player = playerManager.getPlayer();
        if (player.getInventory() != null && player.getInventory().getChestplate() != null && player.getInventory().getChestplate().hasItemMeta() && CookieGadgets.getNMSManager().hasNBTTag(player.getInventory().getChestplate(), "Cosmetics")) {
            player.getInventory().setChestplate((ItemStack)null);
            player.updateInventory();
        }
        if (!playerManager.getEquippedSuitEquipment().isEmpty() && playerManager.getEquippedSuitEquipment().containsKey(EnumArmorType.CHESTPLATE)) {
            if (playerManager.getCurrentSuit() != null) {
                playerManager.getCurrentSuit().onClear();
                playerManager.removeSuit();
            }
            if (!playerManager.getEquippedSuitEquipment().isEmpty()) {
                playerManager.getEquippedSuitEquipment().remove(EnumArmorType.CHESTPLATE);
            }
        }
        return true;
    }
    
    public static boolean removeChestplateCosmetic(final PlayerManager playerManager, final Category category) {
        Validate.notNull((Object)playerManager);
        Validate.notNull((Object)category);
        final Player player = playerManager.getPlayer();
        if (player.getInventory() != null && player.getInventory().getChestplate() != null && player.getInventory().getChestplate().hasItemMeta() && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getChestplate(), "Category", category.getNBTTag())) {
            player.getInventory().setChestplate((ItemStack)null);
            player.updateInventory();
        }
        return true;
    }
    
    public static boolean removeLeggingsCosmetic(final PlayerManager playerManager) {
        Validate.notNull((Object)playerManager);
        final Player player = playerManager.getPlayer();
        if (player.getInventory() != null && player.getInventory().getLeggings() != null && player.getInventory().getLeggings().hasItemMeta() && CookieGadgets.getNMSManager().hasNBTTag(player.getInventory().getLeggings(), "Cosmetics")) {
            player.getInventory().setLeggings((ItemStack)null);
            player.updateInventory();
        }
        if (!playerManager.getEquippedSuitEquipment().isEmpty() && playerManager.getEquippedSuitEquipment().containsKey(EnumArmorType.LEGGINGS)) {
            if (playerManager.getCurrentSuit() != null) {
                playerManager.getCurrentSuit().onClear();
                playerManager.removeSuit();
            }
            if (!playerManager.getEquippedSuitEquipment().isEmpty()) {
                playerManager.getEquippedSuitEquipment().remove(EnumArmorType.LEGGINGS);
            }
        }
        return true;
    }
    
    public static boolean removeLeggingsCosmetic(final PlayerManager playerManager, final Category category) {
        Validate.notNull((Object)playerManager);
        Validate.notNull((Object)category);
        final Player player = playerManager.getPlayer();
        if (player.getInventory() != null && player.getInventory().getLeggings() != null && player.getInventory().getLeggings().hasItemMeta() && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getLeggings(), "Category", category.getNBTTag())) {
            player.getInventory().setLeggings((ItemStack)null);
            player.updateInventory();
        }
        return true;
    }
    
    public static boolean removeBootsCosmetic(final PlayerManager playerManager) {
        Validate.notNull((Object)playerManager);
        final Player player = playerManager.getPlayer();
        if (player.getInventory() != null && player.getInventory().getBoots() != null && player.getInventory().getBoots().hasItemMeta() && CookieGadgets.getNMSManager().hasNBTTag(player.getInventory().getBoots(), "Cosmetics")) {
            player.getInventory().setBoots((ItemStack)null);
            player.updateInventory();
        }
        if (!playerManager.getEquippedSuitEquipment().isEmpty() && playerManager.getEquippedSuitEquipment().containsKey(EnumArmorType.BOOTS)) {
            if (playerManager.getCurrentSuit() != null) {
                playerManager.getCurrentSuit().onClear();
                playerManager.removeSuit();
            }
            if (!playerManager.getEquippedSuitEquipment().isEmpty()) {
                playerManager.getEquippedSuitEquipment().remove(EnumArmorType.BOOTS);
            }
        }
        return true;
    }
    
    public static boolean removeBootsCosmetic(final PlayerManager playerManager, final Category category) {
        Validate.notNull((Object)playerManager);
        Validate.notNull((Object)category);
        final Player player = playerManager.getPlayer();
        if (player.getInventory() != null && player.getInventory().getBoots() != null && player.getInventory().getBoots().hasItemMeta() && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getBoots(), "Category", category.getNBTTag())) {
            player.getInventory().setBoots((ItemStack)null);
            player.updateInventory();
        }
        return true;
    }
    
    public static boolean removeArmorSlotCosmetic(final PlayerManager playerManager) {
        Validate.notNull((Object)playerManager);
        final Player player = playerManager.getPlayer();
        if (player.getInventory() != null && player.getInventory().getHelmet() != null && player.getInventory().getHelmet().hasItemMeta() && CookieGadgets.getNMSManager().hasNBTTag(player.getInventory().getHelmet(), "Cosmetics")) {
            player.getInventory().setHelmet((ItemStack)null);
            player.updateInventory();
        }
        if (player.getInventory() != null && player.getInventory().getChestplate() != null && player.getInventory().getChestplate().hasItemMeta() && CookieGadgets.getNMSManager().hasNBTTag(player.getInventory().getChestplate(), "Cosmetics")) {
            player.getInventory().setChestplate((ItemStack)null);
            player.updateInventory();
        }
        if (player.getInventory() != null && player.getInventory().getLeggings() != null && player.getInventory().getLeggings().hasItemMeta() && CookieGadgets.getNMSManager().hasNBTTag(player.getInventory().getLeggings(), "Cosmetics")) {
            player.getInventory().setLeggings((ItemStack)null);
            player.updateInventory();
        }
        if (player.getInventory() != null && player.getInventory().getBoots() != null && player.getInventory().getBoots().hasItemMeta() && CookieGadgets.getNMSManager().hasNBTTag(player.getInventory().getBoots(), "Cosmetics")) {
            player.getInventory().setBoots((ItemStack)null);
            player.updateInventory();
        }
        if (playerManager.getEquippedHat() != null) {
            playerManager.setEquippedHat(null);
        }
        else if (playerManager.getEquippedAnimatedHat() != null) {
            if (playerManager.getCurrentAnimatedHat() != null) {
                playerManager.removeAnimatedHat();
            }
            playerManager.setEquippedAnimatedHat(null);
        }
        else if (!playerManager.getEquippedSuitEquipment().isEmpty()) {
            if (playerManager.getCurrentSuit() != null) {
                playerManager.getCurrentSuit().onClear();
                playerManager.removeSuit();
            }
            if (!playerManager.getEquippedSuitEquipment().isEmpty()) {
                playerManager.getEquippedSuitEquipment().remove(EnumArmorType.HELMET);
                playerManager.getEquippedSuitEquipment().remove(EnumArmorType.CHESTPLATE);
                playerManager.getEquippedSuitEquipment().remove(EnumArmorType.LEGGINGS);
                playerManager.getEquippedSuitEquipment().remove(EnumArmorType.BOOTS);
            }
        }
        else if (playerManager.getEquippedBanner() != null) {
            playerManager.setEquippedBanner(null);
        }
        else if (playerManager.getEquippedEmote() != null) {
            playerManager.setEquippedEmote(null);
            if (playerManager.getCurrentEmote() != null) {
                playerManager.removeEmote();
            }
        }
        return true;
    }
    
    public static boolean removeArmorSlotCosmetic(final PlayerManager playerManager, final Category category) {
        Validate.notNull((Object)playerManager);
        Validate.notNull((Object)category);
        final Player player = playerManager.getPlayer();
        if (player.getInventory() != null && player.getInventory().getHelmet() != null && player.getInventory().getHelmet().hasItemMeta() && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getHelmet(), "Category", category.getNBTTag())) {
            player.getInventory().setHelmet((ItemStack)null);
            player.updateInventory();
        }
        if (player.getInventory() != null && player.getInventory().getChestplate() != null && player.getInventory().getChestplate().hasItemMeta() && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getChestplate(), "Category", category.getNBTTag())) {
            player.getInventory().setChestplate((ItemStack)null);
            player.updateInventory();
        }
        if (player.getInventory() != null && player.getInventory().getLeggings() != null && player.getInventory().getLeggings().hasItemMeta() && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getLeggings(), "Category", category.getNBTTag())) {
            player.getInventory().setLeggings((ItemStack)null);
            player.updateInventory();
        }
        if (player.getInventory() != null && player.getInventory().getBoots() != null && player.getInventory().getBoots().hasItemMeta() && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getBoots(), "Category", category.getNBTTag())) {
            player.getInventory().setBoots((ItemStack)null);
            player.updateInventory();
        }
        return true;
    }
    
    public static boolean removeHotbarCosmetic(final PlayerManager playerManager) {
        Validate.notNull((Object)playerManager);
        final Player player = playerManager.getPlayer();
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory() != null && player.getInventory().getItem(gadgetSlot) != null && player.getInventory().getItem(gadgetSlot).hasItemMeta() && CookieGadgets.getNMSManager().hasNBTTag(player.getInventory().getItem(gadgetSlot), "Cosmetics")) {
            player.getInventory().setItem(gadgetSlot, (ItemStack)null);
            player.updateInventory();
        }
        if (playerManager.getEquippedGadget() != null) {
            playerManager.setEquippedGadget(null);
            if (playerManager.getSelectedCategoryGadget() != null) {
                playerManager.setSelectedCategoryGadget(null);
            }
            if (playerManager.getCurrentGadget() != null) {
                playerManager.getCurrentGadget().onClear();
                playerManager.removeGadget();
            }
            return true;
        }
        if (playerManager.getEquippedMorph() != null) {
            playerManager.setEquippedMorph(null);
            if (CookieGadgets.getGDisguise().isDisguised(player)) {
                CookieGadgets.getGDisguise().undisguise(player);
            }
            if (playerManager.getCurrentMorph() != null) {
                playerManager.getCurrentMorph().onClear();
                playerManager.removeMorph();
            }
            return true;
        }
        if (playerManager.getEquippedEmote() != null) {
            playerManager.setEquippedEmote(null);
            if (playerManager.getCurrentEmote() != null) {
                playerManager.removeEmote();
            }
            return true;
        }
        return false;
    }
    
    public static boolean removeHotbarCosmetic(final PlayerManager playerManager, final Category category) {
        Validate.notNull((Object)playerManager);
        Validate.notNull((Object)category);
        final Player player = playerManager.getPlayer();
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory() != null && player.getInventory().getItem(gadgetSlot) != null && player.getInventory().getItem(gadgetSlot).hasItemMeta() && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getItem(gadgetSlot), "Category", category.getNBTTag())) {
            player.getInventory().setItem(gadgetSlot, (ItemStack)null);
            player.updateInventory();
        }
        return false;
    }
    
    public static void removeItemFromImproperSlot(final PlayerManager playerManager) {
        Validate.notNull((Object)playerManager);
        final Player player = playerManager.getPlayer();
        for (int i = 0; i <= 35; ++i) {
            final ItemStack item = player.getInventory().getItem(i);
            if (CookieGadgets.getNMSManager().hasNBTTag(item, "Cosmetics")) {
                if (CookieGadgets.getNMSManager().isNBTTagEqual(item, "Category", Category.HATS.getNBTTag()) || CookieGadgets.getNMSManager().isNBTTagEqual(item, "Category", Category.ANIMATED_HATS.getNBTTag()) || CookieGadgets.getNMSManager().isNBTTagEqual(item, "Category", Category.SUITS.getNBTTag()) || CookieGadgets.getNMSManager().isNBTTagEqual(item, "Category", Category.BANNERS.getNBTTag())) {
                    player.getInventory().setItem(i, (ItemStack)null);
                    player.updateInventory();
                }
                else if (i != CookieGadgets.getCookieGadgetsData().getGadgetSlot() && (CookieGadgets.getNMSManager().isNBTTagEqual(item, "Category", Category.EMOTES.getNBTTag()) || CookieGadgets.getNMSManager().isNBTTagEqual(item, "Category", Category.MORPHS.getNBTTag()) || CookieGadgets.getNMSManager().isNBTTagEqual(item, "Category", Category.GADGETS.getNBTTag()))) {
                    player.getInventory().setItem(i, (ItemStack)null);
                    player.updateInventory();
                }
            }
        }
    }
    
    public static boolean checkEquipRequirement(final PlayerManager playerManager, final String s) {
        if (playerManager == null) {
            return false;
        }
        final Player player = playerManager.getPlayer();
        final EnumEquipType equipCosmeticItemToSlotAction = CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction();
        if (equipCosmeticItemToSlotAction == EnumEquipType.REPLACE) {
            return false;
        }
        if (equipCosmeticItemToSlotAction == EnumEquipType.WARN) {
            player.sendMessage(s);
            return true;
        }
        if (equipCosmeticItemToSlotAction == EnumEquipType.DROP) {
            return false;
        }
        player.sendMessage(s);
        return true;
    }
}
