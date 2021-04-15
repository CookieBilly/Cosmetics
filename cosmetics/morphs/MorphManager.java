

package ws.billy.CookieGadgets.cosmetics.morphs;

import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;

public class MorphManager
{
    public static void giveSlimeball(final Player player) {
        if (player == null) {
            return;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (playerManager == null) {
            return;
        }
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory().getItem(gadgetSlot) != null) {
            if (player.getInventory().getItem(gadgetSlot).getItemMeta().hasDisplayName() && player.getInventory().getItem(gadgetSlot).getItemMeta().getDisplayName().equals(EnumItem.MORPH_SLIMEBALL.getDisplayName())) {
                player.getInventory().setItem(gadgetSlot, (ItemStack)null);
            }
            else if (playerManager.getEquippedGadget() != null) {
                playerManager.unequipGadget();
            }
            else {
                if (playerManager.getEquippedEmote() == null) {
                    player.sendMessage(MessageType.REMOVE_ITEM_FROM_SLOT_TO_EQUIP_MORPH.getFormatMessage().replace("{SLOT}", String.valueOf(gadgetSlot)).replace("{ITEM}", player.getInventory().getItem(gadgetSlot).getType().name()));
                    return;
                }
                playerManager.unequipEmote();
            }
        }
        player.getInventory().setItem(gadgetSlot, CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(EnumItem.MORPH_SLIMEBALL.getItemStack(), "Cosmetics", "true"), "Category", Category.MORPHS.getNBTTag()));
    }
    
    public static void removeSlimeball(final Player player) {
        final int gadgetSlot = CookieGadgets.getCookieGadgetsData().getGadgetSlot();
        if (player.getInventory().getItem(gadgetSlot) != null && player.getInventory().getItem(gadgetSlot).getItemMeta() != null && player.getInventory().getItem(gadgetSlot).getItemMeta().hasDisplayName() && player.getInventory().getItem(gadgetSlot).getItemMeta().getDisplayName().equals(EnumItem.MORPH_SLIMEBALL.getDisplayName())) {
            player.getInventory().setItem(gadgetSlot, (ItemStack)null);
        }
    }
}
