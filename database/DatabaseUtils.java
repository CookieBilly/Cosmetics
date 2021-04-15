

package ws.billy.CookieGadgets.database;

import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.GPet;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import java.sql.Timestamp;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxes;
import java.util.List;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import java.util.UUID;
import org.bukkit.OfflinePlayer;
import ws.billy.CookieGadgets.player.PlayerManager;

public abstract class DatabaseUtils
{
    protected DatabaseManager databaseManager;
    
    public DatabaseUtils(final DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
    
    public abstract void initPlayerStats(final PlayerManager p0);
    
    public abstract void savePlayerStats(final int p0, final long p1, final String p2);
    
    public abstract boolean isExist(final OfflinePlayer p0);
    
    public abstract int getUID(final UUID p0);
    
    public abstract int getMysteryDust(final UUID p0);
    
    public abstract boolean addMysteryDust(final UUID p0, final int p1);
    
    public abstract boolean removeMysteryDust(final UUID p0, final int p1);
    
    public abstract boolean setMysteryDust(final UUID p0, final int p1);
    
    public abstract int getMysteryGiftPacks(final OfflinePlayerManager p0);
    
    public abstract void addMysteryGiftPacks(final OfflinePlayerManager p0, final int p1);
    
    public abstract void removeMysteryGiftPacks(final OfflinePlayerManager p0, final int p1);
    
    public abstract int getMysteryGiftSent(final OfflinePlayerManager p0);
    
    public abstract void addMysteryGiftSent(final OfflinePlayerManager p0, final int p1);
    
    public abstract int getMysteryGiftReceived(final OfflinePlayerManager p0);
    
    public abstract void addMysteryGiftReceived(final OfflinePlayerManager p0, final int p1);
    
    public abstract void setSeeSelfMorph(final OfflinePlayerManager p0, final boolean p1);
    
    public abstract boolean canSeeSelfMorph(final OfflinePlayerManager p0);
    
    public abstract void setCooldownBypass(final OfflinePlayerManager p0, final boolean p1);
    
    public abstract boolean isCooldownBypass(final OfflinePlayerManager p0);
    
    public abstract AnimationType getMysteryVaultAnimation(final OfflinePlayerManager p0);
    
    public abstract void setMysteryVaultAnimation(final OfflinePlayerManager p0, final AnimationType p1);
    
    public abstract long getLastPetMissionTimeMillis(final OfflinePlayerManager p0);
    
    public abstract int getMysteryBoxesRewardPlayTime(final OfflinePlayerManager p0);
    
    public abstract void setMysteryBoxesRewardPlayTime(final OfflinePlayerManager p0, final int p1);
    
    public abstract String getRecentLootsFound(final OfflinePlayerManager p0);
    
    public abstract List<String> getEquippedCosmetics(final List<String> p0, final int p1);
    
    public abstract void syncEquippedCosmetics(final List<String> p0, final List<String> p1, final int p2);
    
    public abstract boolean syncMysteryBoxes(final PlayerManager p0);
    
    public abstract int getMysteryBoxes(final OfflinePlayerManager p0);
    
    public abstract void addMysteryBox(final UUID p0, final int p1, final MysteryBoxes p2);
    
    public abstract void removeMysteryBox(final int p0, final MysteryBoxes p1);
    
    public abstract boolean loadUnlockedCosmeticItems(final PlayerManager p0);
    
    public abstract void addUnlockedCosmeticItem(final UUID p0, final int p1, final Category p2, final String p3, final Timestamp p4);
    
    public abstract void removeUnlockedCosmeticItem(final int p0, final Category p1, final String p2);
    
    public abstract boolean loadPetsData(final PlayerManager p0);
    
    public abstract GPet getPetData(final OfflinePlayerManager p0, final PetType p1);
    
    public abstract void updatePetData(final PlayerManager p0, final GPet p1);
    
    public abstract void setPetName(final OfflinePlayerManager p0, final PetType p1, final GPet p2);
    
    public abstract void loadPetItems(final PlayerManager p0);
    
    public abstract int getPetItem(final PlayerManager p0, final PetItems p1);
    
    public abstract void setPetItems(final PlayerManager p0, final PetItems p1, final int p2);
    
    public abstract void addPetItems(final PlayerManager p0, final PetItems p1, final int p2);
    
    public abstract void removePetItems(final PlayerManager p0, final PetItems p1, final int p2);
    
    public abstract void deletePlayerData(final UUID p0, final int p1);
}
