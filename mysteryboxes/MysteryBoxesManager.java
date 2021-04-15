

package ws.billy.CookieGadgets.mysteryboxes;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.LootCategory;
import ws.billy.CookieGadgets.utils.mysteryboxes.CosmeticItems;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Quality;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.MysteryBoxCraftingDate;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.mysteryboxes.MysteryBoxType;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;

public class MysteryBoxesManager
{
    public static void giveMysteryBoxes(final OfflinePlayerManager offlinePlayerManager, final MysteryBoxType mysteryBoxType, final boolean b, String date, final Long n) {
        final ArrayList<MysteryBoxesLoot> list = new ArrayList<MysteryBoxesLoot>();
        final Quality quality = mysteryBoxType.getQuality();
        if (quality.getIncludedCommonItems() > 0) {
            final ArrayList<MysteryBoxesLoot> list2 = new ArrayList<MysteryBoxesLoot>();
            for (int i = 1; i <= quality.getIncludedCommonItems(); ++i) {
                list.add(item(Rarity.COMMON, list2));
            }
        }
        if (quality.getIncludedRareItems() > 0) {
            final ArrayList<MysteryBoxesLoot> list3 = new ArrayList<MysteryBoxesLoot>();
            for (int j = 1; j <= quality.getIncludedRareItems(); ++j) {
                list.add(item(Rarity.RARE, list3));
            }
        }
        if (quality.getIncludedEpicItems() > 0) {
            final ArrayList<MysteryBoxesLoot> list4 = new ArrayList<MysteryBoxesLoot>();
            for (int k = 1; k <= quality.getIncludedEpicItems(); ++k) {
                list.add(item(Rarity.EPIC, list4));
            }
        }
        if (quality.getIncludedLegendaryItems() > 0) {
            final ArrayList<MysteryBoxesLoot> list5 = new ArrayList<MysteryBoxesLoot>();
            for (int l = 1; l <= quality.getIncludedLegendaryItems(); ++l) {
                list.add(item(Rarity.LEGENDARY, list5));
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (int index = 0; index < list.size(); ++index) {
            sb.append(String.valueOf(list.get(index).toString()) + ((index != list.size() - 1) ? " %% " : ""));
        }
        if (mysteryBoxType.isNormalMysteryBox()) {
            date = null;
        }
        else if (mysteryBoxType.isCraftedMysteryBox()) {
            date = new MysteryBoxCraftingDate().getDate();
        }
        final MysteryBoxes mysteryBoxes = new MysteryBoxes(mysteryBoxType, n != null, n, b, date, sb.toString());
        if (offlinePlayerManager.isOfflinePlayer()) {
            offlinePlayerManager.saveOfflinePlayerMysteryBoxes(mysteryBoxes);
        }
        else {
            final PlayerManager playerManager;
            final MysteryBoxes mysteryBoxes2;
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                playerManager = (PlayerManager)offlinePlayerManager;
                if (playerManager.isLoaded()) {
                    playerManager.mysteryBoxes().add(mysteryBoxes2);
                }
                else {
                    playerManager.mysteryBoxesCache().add(mysteryBoxes2);
                }
                CookieGadgets.getDatabaseManager().getDatabaseUtils().addMysteryBox(playerManager.getUUID(), playerManager.getUID(), mysteryBoxes2);
            });
        }
    }
    
    private static MysteryBoxesLoot item(final Rarity rarity, final ArrayList<MysteryBoxesLoot> list) {
        return randomGetCosmeticItem(rarity, list, 0);
    }
    
    private static MysteryBoxesLoot randomGetCosmeticItem(final Rarity rarity, final ArrayList<MysteryBoxesLoot> list, int n) {
        final MysteryBoxesLoot randomItem = new CosmeticItems(rarity).getRandomItem();
        if (n >= 5) {
            return randomItem;
        }
        for (final MysteryBoxesLoot mysteryBoxesLoot : list) {
            if (mysteryBoxesLoot.getLootCategory() != randomItem.getLootCategory()) {
                continue;
            }
            if (mysteryBoxesLoot.getLootCategory() != LootCategory.BUILT_IN && randomItem.getLootCategory() != LootCategory.BUILT_IN) {
                if (mysteryBoxesLoot.customMysteryBoxesLoot().getCategoryName().equals(randomItem.customMysteryBoxesLoot().getCategoryName()) && mysteryBoxesLoot.customMysteryBoxesLoot().getName().equals(randomItem.customMysteryBoxesLoot().getName())) {
                    ++n;
                    return randomGetCosmeticItem(rarity, list, n);
                }
                continue;
            }
            else {
                if (mysteryBoxesLoot.getCategory().equals(randomItem.getCategory()) && mysteryBoxesLoot.getName().equals(randomItem.getName())) {
                    ++n;
                    return randomGetCosmeticItem(rarity, list, n);
                }
                continue;
            }
        }
        list.add(randomItem);
        return randomItem;
    }
}
