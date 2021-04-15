

package ws.billy.CookieGadgets.player;

import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxes;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxesManager;
import ws.billy.CookieGadgets.utils.mysteryboxes.MysteryBoxType;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.GPet;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import java.util.UUID;

public class OfflinePlayerManager
{
    private UUID uuid;
    private OfflinePlayer player;
    
    public OfflinePlayerManager(final UUID uuid) {
        this.uuid = uuid;
        this.player = Bukkit.getOfflinePlayer(uuid);
    }
    
    public String getName() {
        return this.player.getName();
    }
    
    public UUID getUUID() {
        return this.uuid;
    }
    
    public OfflinePlayer getPlayer() {
        return this.player;
    }
    
    public boolean isOfflinePlayer() {
        return true;
    }
    
    public int getMysteryDust() {
        try {
            return CookieGadgets.getGEconomyProvider().getMysteryDust(this);
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public boolean addMysteryDust(final int n) {
        return CookieGadgets.getGEconomyProvider().addMysteryDust(this, n);
    }
    
    public boolean setMysteryDust(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The data cannot be negative value.");
        }
        return CookieGadgets.getGEconomyProvider().setMysteryDust(this, n);
    }
    
    public boolean removeMysteryDust(final int n) {
        if (this.getMysteryDust() - n < 0) {
            throw new IllegalArgumentException("The new data cannot be negative value.");
        }
        return CookieGadgets.getGEconomyProvider().removeMysteryDust(this, n);
    }
    
    public GPet getPetData(final PetType petType) {
        return CookieGadgets.getDatabaseManager().getDatabaseUtils().getPetData(this, petType);
    }
    
    public void setPetName(final PetType petType, String format) {
        if (petType == null) {
            throw new NullPointerException();
        }
        format = ChatUtil.format(format);
        GPet gPet = this.getPetData(petType);
        if (gPet == null) {
            gPet = GPet.createNewPetData(petType, format);
        }
        gPet.setPetName(format);
        CookieGadgets.getDatabaseManager().getDatabaseUtils().setPetName(this, petType, gPet);
    }
    
    public void giveMysteryBoxes(final Long n, final boolean b, final String s, final int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("The amount must be possitive number!");
        }
        int i = 0;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            while (i <= n2) {
                MysteryBoxesManager.giveMysteryBoxes(this, MysteryBoxType.valueOfByName("Normal Mystery Box #" + (CookieGadgets.random().nextInt(5) + 1)), b, s, n);
                ++i;
            }
        });
    }
    
    public void giveMysteryBoxes(final MysteryBoxType mysteryBoxType, final Long n, final boolean b, final String s, final int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("The amount must be possitive number!");
        }
        int i = 0;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            while (i <= n2) {
                MysteryBoxesManager.giveMysteryBoxes(this, mysteryBoxType, b, s, n);
                ++i;
            }
        });
    }
    
    public void giveMysteryBoxes(final Long n, final boolean b, final String s, final int n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("The amount must be possitive number!");
        }
        int i = 0;
        final float n9;
        int j;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            while (i <= n2) {
                MathUtil.randomFloat(0.0f, n4 + n5 + n6 + n7 + n8);
                if (n9 <= n4) {
                    j = 1;
                }
                else if (n9 > n4 && n9 <= n4 + n5) {
                    j = 2;
                }
                else if (n9 > n4 + n5 && n9 <= n4 + n5 + n6) {
                    j = 3;
                }
                else if (n9 > n4 + n5 + n6 && n9 <= n4 + n5 + n6 + n7) {
                    j = 4;
                }
                else if (n9 > n4 + n5 + n6 + n7 && n9 <= n4 + n5 + n6 + n7 + n8) {
                    j = 5;
                }
                else {
                    j = CookieGadgets.random().nextInt(5) + 1;
                }
                MysteryBoxesManager.giveMysteryBoxes(this, MysteryBoxType.valueOfByName("Normal Mystery Box #" + j), b, s, n);
                ++i;
            }
        });
    }
    
    public int getMysteryBoxes() {
        if (this.getPlayer() == null) {
            return 0;
        }
        return CookieGadgets.getDatabaseManager().getDatabaseUtils().getMysteryBoxes(this);
    }
    
    public void saveOfflinePlayerMysteryBoxes(final MysteryBoxes mysteryBoxes) {
        if (!this.isOfflinePlayer()) {
            return;
        }
        if (!CookieGadgets.getCookieGadgetsData().isMysteryBoxesEnabled()) {
            return;
        }
        if (this.getPlayer() == null) {
            return;
        }
        if (this.getPlayer().getUniqueId() == null) {
            return;
        }
        if (CookieGadgets.getDatabaseManager().getDatabaseUtils().isExist(this.player)) {
            CookieGadgets.getDatabaseManager().getDatabaseUtils().addMysteryBox(this.getUUID(), this.getUID(), mysteryBoxes);
        }
    }
    
    public int getUID() {
        return CookieGadgets.getDatabaseManager().getDatabaseUtils().getUID(this.uuid);
    }
}
