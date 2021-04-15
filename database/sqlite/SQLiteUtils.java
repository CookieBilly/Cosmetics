

package ws.billy.CookieGadgets.database.sqlite;

import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.GPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.PetAttribute;
import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.PetLevel;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import ws.billy.CookieGadgets.utils.GTimestamp;
import java.sql.Timestamp;
import java.util.HashMap;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxes;
import org.apache.commons.lang.Validate;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import java.util.UUID;
import org.bukkit.OfflinePlayer;
import java.sql.ResultSet;
import ws.billy.CookieGadgets.database.query.SelectQuery;
import java.sql.SQLException;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.database.DatabaseManager;
import ws.billy.CookieGadgets.database.DatabaseUtils;

public class SQLiteUtils extends DatabaseUtils
{
    private SQLiteManager sqliteManager;
    
    public SQLiteUtils(final SQLiteManager sqliteManager) {
        super(sqliteManager);
        this.sqliteManager = sqliteManager;
    }
    
    @Override
    public void initPlayerStats(final PlayerManager playerManager) {
        if (playerManager == null) {
            return;
        }
        if (this.sqliteManager.getConnection() == null) {
            return;
        }
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            final String string = playerManager.getUUID().toString();
            final String name = playerManager.getName();
            where = this.sqliteManager.getTable().select().where("UUID", string);
            execute = where.execute();
            if (!execute.next()) {
                this.sqliteManager.getTable().insert().insert("UUID, Name, Mystery_Dust, Mystery_Vault_Animation").value(string).value(name).value(CookieGadgets.getCookieGadgetsData().getDefaultMysteryDust()).value(CookieGadgets.getCookieGadgetsData().getDefaultMysteryVaultAnimation().getName()).execute();
            }
            final ResultSet execute2 = this.sqliteManager.getTable().select().where("UUID", string).execute();
            execute2.next();
            final int int1 = execute2.getInt("id");
            final String string2 = execute2.getString("Name");
            playerManager.loadDatabaseData(int1, execute2.getInt("Mystery_Dust"), execute2.getInt("Mystery_Gift_Packs"), execute2.getInt("Mystery_Gift_Sent"), execute2.getInt("Mystery_Gift_Received"), Boolean.parseBoolean(execute2.getString("Self_Morph_View")), Boolean.parseBoolean(execute2.getString("Bypass_Cooldown")), AnimationType.valueOf(execute2.getString("Mystery_Vault_Animation")), execute2.getLong("Last_Pet_Mission_Time_Millis"), execute2.getInt("Play_Time"), execute2.getString("Recent_Loots_Found"));
            if (string2 == null) {
                this.sqliteManager.getTable().update().set("Name", name).where("UUID", string).execute();
            }
            if (string2 != null && !string2.equals(name)) {
                this.sqliteManager.getTable().update().set("Name", name).where("UUID", string).execute();
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
        if (where != null) {
            where.close();
        }
        if (execute != null) {
            try {
                execute.close();
            }
            catch (SQLException ex3) {}
        }
    }
    
    @Override
    public void savePlayerStats(final int i, final long l, String s) {
        if (s == null) {
            s = "";
        }
        this.sqliteManager.getTable().update().set("Last_Pet_Mission_Time_Millis", l).set("Recent_Loots_Found", s).where("id", i).execute();
    }
    
    @Override
    public boolean isExist(final OfflinePlayer offlinePlayer) {
        if (offlinePlayer == null) {
            return false;
        }
        if (this.sqliteManager.getConnection() == null) {
            return false;
        }
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("UUID", offlinePlayer.getUniqueId().toString());
            execute = where.execute();
            return execute.next();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return false;
    }
    
    @Override
    public int getUID(final UUID uuid) {
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("UUID", uuid.toString());
            execute = where.execute();
            execute.next();
            return execute.getInt("id");
        }
        catch (SQLException ex) {
            return -1;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public int getMysteryDust(final UUID uuid) {
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("UUID", uuid.toString());
            execute = where.execute();
            execute.next();
            return execute.getInt("Mystery_Dust");
        }
        catch (SQLException ex) {
            return 0;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public boolean addMysteryDust(final UUID uuid, final int n) {
        if (this.sqliteManager == null || this.sqliteManager.getTable() == null) {
            return false;
        }
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getTable().update().set("Mystery_Dust", this.getMysteryDust(uuid) + n).where("UUID", uuid.toString()).execute());
        return true;
    }
    
    @Override
    public boolean removeMysteryDust(final UUID uuid, final int n) {
        if (this.sqliteManager == null || this.sqliteManager.getTable() == null) {
            return false;
        }
        final int n2;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            this.getMysteryDust(uuid);
            this.sqliteManager.getTable().update().set("Mystery_Dust", (n2 <= 0) ? 0 : (n2 - n)).where("UUID", uuid.toString()).execute();
            return;
        });
        return true;
    }
    
    @Override
    public boolean setMysteryDust(final UUID uuid, final int i) {
        if (this.sqliteManager == null || this.sqliteManager.getTable() == null) {
            return false;
        }
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getTable().update().set("Mystery_Dust", i).where("UUID", uuid.toString()).execute());
        return true;
    }
    
    @Override
    public int getMysteryGiftPacks(final OfflinePlayerManager offlinePlayerManager) {
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("id", offlinePlayerManager.getUID());
            execute = where.execute();
            execute.next();
            return execute.getInt("Mystery_Gift_Packs");
        }
        catch (SQLException ex) {
            return 0;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public void addMysteryGiftPacks(final OfflinePlayerManager offlinePlayerManager, final int n) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getTable().update().set("Mystery_Gift_Packs", this.getMysteryGiftPacks(offlinePlayerManager) + n).where("id", offlinePlayerManager.getUID()).execute());
    }
    
    @Override
    public void removeMysteryGiftPacks(final OfflinePlayerManager offlinePlayerManager, final int n) {
        final int n2;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            this.getMysteryGiftPacks(offlinePlayerManager);
            this.sqliteManager.getTable().update().set("Mystery_Gift_Packs", (n2 <= 0) ? 0 : (n2 - n)).where("id", offlinePlayerManager.getUID()).execute();
        });
    }
    
    @Override
    public int getMysteryGiftSent(final OfflinePlayerManager offlinePlayerManager) {
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("id", offlinePlayerManager.getUID());
            execute = where.execute();
            execute.next();
            return execute.getInt("Mystery_Gift_Sent");
        }
        catch (SQLException ex) {
            return 0;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public void addMysteryGiftSent(final OfflinePlayerManager offlinePlayerManager, final int n) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getTable().update().set("Mystery_Gift_Sent", this.getMysteryGiftSent(offlinePlayerManager) + n).where("id", offlinePlayerManager.getUID()).execute());
    }
    
    @Override
    public int getMysteryGiftReceived(final OfflinePlayerManager offlinePlayerManager) {
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("id", offlinePlayerManager.getUID());
            execute = where.execute();
            execute.next();
            return execute.getInt("Mystery_Gift_Received");
        }
        catch (SQLException ex) {
            return 0;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public void addMysteryGiftReceived(final OfflinePlayerManager offlinePlayerManager, final int n) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getTable().update().set("Mystery_Gift_Received", this.getMysteryGiftReceived(offlinePlayerManager) + n).where("id", offlinePlayerManager.getUID()).execute());
    }
    
    @Override
    public void setSeeSelfMorph(final OfflinePlayerManager offlinePlayerManager, final boolean b) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getTable().update().set("Self_Morph_View", b ? "true" : "false").where("id", offlinePlayerManager.getUID()).execute());
    }
    
    @Override
    public boolean canSeeSelfMorph(final OfflinePlayerManager offlinePlayerManager) {
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("id", offlinePlayerManager.getUID());
            execute = where.execute();
            execute.next();
            return Boolean.parseBoolean(execute.getString("Self_Morph_View"));
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public void setCooldownBypass(final OfflinePlayerManager offlinePlayerManager, final boolean b) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getTable().update().set("Bypass_Cooldown", b ? "true" : "false").where("id", offlinePlayerManager.getUID()).execute());
    }
    
    @Override
    public boolean isCooldownBypass(final OfflinePlayerManager offlinePlayerManager) {
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("id", offlinePlayerManager.getUID());
            execute = where.execute();
            execute.next();
            return Boolean.parseBoolean(execute.getString("Bypass_Cooldown"));
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public AnimationType getMysteryVaultAnimation(final OfflinePlayerManager offlinePlayerManager) {
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("id", offlinePlayerManager.getUID());
            execute = where.execute();
            execute.next();
            return AnimationType.valueOf(execute.getString("Mystery_Vault_Animation"));
        }
        catch (SQLException ex) {
            return AnimationType.NORMAL;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public void setMysteryVaultAnimation(final OfflinePlayerManager offlinePlayerManager, final AnimationType animationType) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            if (animationType == null) {
                throw new NullPointerException("The animation can not be null.");
            }
            else {
                this.sqliteManager.getTable().update().set("Mystery_Vault_Animation", animationType.getName()).where("id", offlinePlayerManager.getUID()).execute();
            }
        });
    }
    
    @Override
    public long getLastPetMissionTimeMillis(final OfflinePlayerManager offlinePlayerManager) {
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("id", offlinePlayerManager.getUID());
            execute = where.execute();
            execute.next();
            return execute.getLong("Last_Pet_Mission_Time_Millis");
        }
        catch (SQLException ex) {
            return 0L;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public int getMysteryBoxesRewardPlayTime(final OfflinePlayerManager offlinePlayerManager) {
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("id", offlinePlayerManager.getUID());
            execute = where.execute();
            execute.next();
            return execute.getInt("Play_Time");
        }
        catch (SQLException ex) {
            return 0;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public void setMysteryBoxesRewardPlayTime(final OfflinePlayerManager offlinePlayerManager, final int i) {
        this.sqliteManager.getTable().update().set("Play_Time", i).where("id", offlinePlayerManager.getUID()).execute();
    }
    
    @Override
    public String getRecentLootsFound(final OfflinePlayerManager offlinePlayerManager) {
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getTable().select().where("id", offlinePlayerManager.getUID());
            execute = where.execute();
            execute.next();
            return execute.getString("Recent_Loots_Found");
        }
        catch (SQLException ex) {
            return "";
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public List<String> getEquippedCosmetics(final List<String> list, final int i) {
        ResultSet execute = null;
        SelectQuery where = null;
        try {
            where = this.sqliteManager.getTable().select().where("id", i);
            execute = where.execute();
            execute.next();
            final ArrayList<String> list2 = new ArrayList<String>();
            for (int j = 0; j < list.size(); ++j) {
                list2.add(execute.getString(list.get(j)));
            }
            return list2;
        }
        catch (SQLException ex) {
            return null;
        }
        finally {
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
            if (where != null) {
                where.close();
            }
        }
    }
    
    @Override
    public void syncEquippedCosmetics(final List<String> list, final List<String> list2, final int i) {
        if (list.size() != list2.size()) {
            return;
        }
        String s = "";
        for (int j = 0; j < list.size(); ++j) {
            String str = list2.get(j);
            if (str == null) {
                str = "none";
            }
            s = String.valueOf(s) + list.get(j) + " = '" + str + "', ";
        }
        if (s.endsWith(", ")) {
            s = s.substring(0, s.length() - 2);
        }
        try {
            final PreparedStatement prepareStatement = this.sqliteManager.getConnection().prepareStatement("UPDATE " + this.sqliteManager.tableName + " SET " + s + "  WHERE id = \"" + i + "\"");
            prepareStatement.executeUpdate();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public boolean syncMysteryBoxes(final PlayerManager playerManager) {
        Validate.notNull((Object)playerManager);
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getMysteryBoxesTable().select("Loots").where("UID", playerManager.getUID());
            execute = where.execute();
            while (execute.next()) {
                final MysteryBoxes e = new MysteryBoxes(execute.getString("Loots"));
                if (e.isExpirable() && e.isExpired()) {
                    this.removeMysteryBox(playerManager.getUID(), e);
                }
                else {
                    playerManager.mysteryBoxes().add(e);
                }
            }
            return true;
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public int getMysteryBoxes(final OfflinePlayerManager offlinePlayerManager) {
        Validate.notNull((Object)offlinePlayerManager);
        int n = 0;
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getMysteryBoxesTable().select("Loots").where("UID", offlinePlayerManager.getUID());
            execute = where.execute();
            while (execute.next()) {
                final MysteryBoxes mysteryBoxes = new MysteryBoxes(execute.getString("Loots"));
                if (mysteryBoxes.isExpirable() && mysteryBoxes.isExpired()) {
                    this.removeMysteryBox(offlinePlayerManager.getUID(), mysteryBoxes);
                }
                else {
                    ++n;
                }
            }
            return n;
        }
        catch (SQLException ex) {
            return 0;
        }
        finally {
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
            if (where != null) {
                where.close();
            }
        }
    }
    
    @Override
    public void addMysteryBox(final UUID uuid, final int i, final MysteryBoxes mysteryBoxes) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getMysteryBoxesTable().insert().insert("UUID, UID, Loots").value(uuid.toString()).value(i).value(mysteryBoxes.toEncodedString()).execute());
    }
    
    @Override
    public void removeMysteryBox(final int i, final MysteryBoxes mysteryBoxes) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getMysteryBoxesTable().delete().where("UID", i).where("Loots", mysteryBoxes.toEncodedString()).execute());
    }
    
    @Override
    public boolean loadUnlockedCosmeticItems(final PlayerManager playerManager) {
        if (playerManager == null) {
            throw new NullPointerException();
        }
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getUnlockedCosmeticItemsTable().select().where("UID", playerManager.getUID());
            execute = where.execute();
            while (execute.next()) {
                final Category valueOfByName = Category.valueOfByName(execute.getString("Cosmetic"));
                final String string = execute.getString("Type");
                final HashMap<Category, ArrayList<String>> unlockedCosmeticItems = playerManager.unlockedCosmeticItems();
                if (!unlockedCosmeticItems.containsKey(valueOfByName)) {
                    unlockedCosmeticItems.put(valueOfByName, new ArrayList<String>());
                }
                if (!unlockedCosmeticItems.get(valueOfByName).contains(string)) {
                    unlockedCosmeticItems.get(valueOfByName).add(string);
                }
            }
            return true;
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public void addUnlockedCosmeticItem(final UUID uuid, final int i, final Category category, final String s, final Timestamp timestamp) {
        Validate.notNull((Object)uuid);
        Validate.notNull((Object)i);
        Validate.notNull((Object)category);
        Validate.notNull((Object)s);
        final SelectQuery selectQuery;
        final ResultSet set;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            try {
                this.sqliteManager.getUnlockedCosmeticItemsTable().select().where("UID", i).where("Cosmetic", category.getName()).where("Type", s);
                selectQuery.execute();
                if (!set.next()) {
                    this.sqliteManager.getUnlockedCosmeticItemsTable().insert().insert("UUID, UID, Cosmetic, Type, Unlock_Time, Expiry_Time").value(uuid.toString()).value(i).value(category.getName()).value(s).value(new Timestamp(System.currentTimeMillis())).value((timestamp == null) ? GTimestamp.getForever() : timestamp).execute();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (selectQuery != null) {
                    selectQuery.close();
                }
                if (set != null) {
                    try {
                        set.close();
                    }
                    catch (SQLException ex2) {}
                }
            }
            if (selectQuery != null) {
                selectQuery.close();
            }
            if (set != null) {
                try {
                    set.close();
                }
                catch (SQLException ex3) {}
            }
        });
    }
    
    @Override
    public void removeUnlockedCosmeticItem(final int i, final Category category, final String s) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getUnlockedCosmeticItemsTable().delete().where("UID", i).where("Cosmetic", category.getName()).where("Type", s).execute());
    }
    
    @Override
    public boolean loadPetsData(final PlayerManager playerManager) {
        if (playerManager == null) {
            throw new NullPointerException();
        }
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getPetsTable().select().where("UID", playerManager.getUID());
            execute = where.execute();
            while (execute.next()) {
                final PetType value = PetType.valueOf(execute.getString("Pet_Type"));
                final GPet value2 = new GPet(value, execute.getString("Pet_Name"), new PetLevel(execute.getInt("Pet_Level"), execute.getInt("Pet_XP")), new PetAttribute(execute.getInt("Hunger"), new Timestamp(execute.getLong("Eat_Timestamp")), execute.getInt("Thirst"), new Timestamp(execute.getLong("Drink_Timestamp")), execute.getInt("Exercise"), new Timestamp(execute.getLong("Exercise_Timestamp"))));
                final HashMap<PetType, GPet> petsData = playerManager.petsData();
                if (!petsData.containsKey(value)) {
                    petsData.put(value, value2);
                }
            }
            return true;
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public GPet getPetData(final OfflinePlayerManager offlinePlayerManager, final PetType petType) {
        if (offlinePlayerManager == null || petType == null || offlinePlayerManager.getUID() == -1) {
            throw new NullPointerException();
        }
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getPetsTable().select().where("UID", offlinePlayerManager.getUID()).where("Pet_Type", petType.getName());
            execute = where.execute();
            if (!execute.next()) {
                return null;
            }
            return new GPet(petType, execute.getString("Pet_Name"), new PetLevel(execute.getInt("Pet_Level"), execute.getInt("Pet_XP")), new PetAttribute(execute.getInt("Hunger"), new Timestamp(execute.getLong("Eat_Timestamp")), execute.getInt("Thirst"), new Timestamp(execute.getLong("Drink_Timestamp")), execute.getInt("Exercise"), new Timestamp(execute.getLong("Exercise_Timestamp"))));
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public void updatePetData(final PlayerManager playerManager, final GPet gPet) {
        Validate.notNull((Object)playerManager);
        Validate.notNull((Object)gPet);
        final SelectQuery selectQuery;
        final ResultSet set;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            try {
                this.sqliteManager.getPetsTable().select().where("UID", playerManager.getUID()).where("Pet_Type", gPet.getType().getName());
                selectQuery.execute();
                if (!set.next()) {
                    this.sqliteManager.getPetsTable().insert().insert("UUID, UID, Pet_Type, Pet_Name, Pet_Level, Pet_XP, Hunger, Eat_Timestamp, Thirst, Drink_Timestamp, Exercise, Exercise_Timestamp").value(playerManager.getUUID().toString()).value(playerManager.getUID()).value(gPet.getType().getName()).value(gPet.getPetName().replace("§", "&")).value(gPet.getPetLevel().getLevel()).value(gPet.getPetLevel().getExperience()).value(gPet.getPetAttribute().getHunger()).value(gPet.getPetAttribute().getEatTimestamp()).value(gPet.getPetAttribute().getThirst()).value(gPet.getPetAttribute().getDrinkTimestamp()).value(gPet.getPetAttribute().getExercise()).value(gPet.getPetAttribute().getExerciseTimestamp()).execute();
                }
                else {
                    this.sqliteManager.getPetsTable().update().set("Pet_Name", gPet.getPetName().replace("§", "&")).set("Pet_Level", gPet.getPetLevel().getLevel()).set("Pet_XP", gPet.getPetLevel().getExperience()).set("Hunger", gPet.getPetAttribute().getHunger()).set("Eat_Timestamp", gPet.getPetAttribute().getEatTimestamp()).set("Thirst", gPet.getPetAttribute().getThirst()).set("Drink_Timestamp", gPet.getPetAttribute().getDrinkTimestamp()).set("Exercise", gPet.getPetAttribute().getExercise()).set("Exercise_Timestamp", gPet.getPetAttribute().getExerciseTimestamp()).where("UID", playerManager.getUID()).where("Pet_Type", gPet.getType().getName()).execute();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (selectQuery != null) {
                    selectQuery.close();
                }
                if (set != null) {
                    try {
                        set.close();
                    }
                    catch (SQLException ex2) {}
                }
            }
            if (selectQuery != null) {
                selectQuery.close();
            }
            if (set != null) {
                try {
                    set.close();
                }
                catch (SQLException ex3) {}
            }
        });
    }
    
    @Override
    public void setPetName(final OfflinePlayerManager offlinePlayerManager, final PetType petType, final GPet gPet) {
        if (offlinePlayerManager == null || petType == null || gPet == null || offlinePlayerManager.getUID() == -1) {
            throw new NullPointerException();
        }
        final SelectQuery selectQuery;
        final ResultSet set;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            try {
                this.sqliteManager.getPetsTable().select().where("UID", offlinePlayerManager.getUID()).where("Pet_Type", petType.getName());
                selectQuery.execute();
                if (!set.next()) {
                    this.sqliteManager.getPetsTable().insert().insert("UUID, UID, Pet_Type, Pet_Name, Pet_Level, Pet_XP, Hunger, Eat_Timestamp, Thirst, Drink_Timestamp, Exercise, Exercise_Timestamp").value(offlinePlayerManager.getUUID().toString()).value(offlinePlayerManager.getUID()).value(petType.getName()).value(gPet.getPetName().replace("§", "&")).value(gPet.getPetLevel().getLevel()).value(gPet.getPetLevel().getExperience()).value(gPet.getPetAttribute().getHunger()).value(gPet.getPetAttribute().getEatTimestamp()).value(gPet.getPetAttribute().getThirst()).value(gPet.getPetAttribute().getDrinkTimestamp()).value(gPet.getPetAttribute().getExercise()).value(gPet.getPetAttribute().getExerciseTimestamp()).execute();
                }
                else {
                    this.sqliteManager.getPetsTable().update().set("Pet_Name", gPet.getPetName().replace("§", "&")).where("UID", offlinePlayerManager.getUID()).where("Pet_Type", petType.getName()).execute();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (selectQuery != null) {
                    selectQuery.close();
                }
                if (set != null) {
                    try {
                        set.close();
                    }
                    catch (SQLException ex2) {}
                }
            }
            if (selectQuery != null) {
                selectQuery.close();
            }
            if (set != null) {
                try {
                    set.close();
                }
                catch (SQLException ex3) {}
            }
        });
    }
    
    @Override
    public void loadPetItems(final PlayerManager playerManager) {
        if (playerManager == null) {
            throw new NullPointerException();
        }
        final SelectQuery selectQuery;
        final ResultSet set;
        final Object o;
        int length;
        int i = 0;
        final PetItems[] array;
        PetItems petItems;
        final HashMap<PetItems, Integer> hashMap;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            try {
                this.sqliteManager.getPetItemsTable().select().where("UID", playerManager.getUID());
                selectQuery.execute();
                if (!set.next()) {
                    this.sqliteManager.getPetItemsTable().insert().insert("UUID, UID").value(playerManager.getUUID().toString()).value(playerManager.getUID()).execute();
                }
                else {
                    playerManager.petItems();
                    PetItems.values();
                    for (length = o.length; i < length; ++i) {
                        petItems = array[i];
                        if (!hashMap.containsKey(petItems)) {
                            hashMap.put(petItems, set.getInt(petItems.getSQLIndex()));
                        }
                    }
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (selectQuery != null) {
                    selectQuery.close();
                }
                if (set != null) {
                    try {
                        set.close();
                    }
                    catch (SQLException ex2) {}
                }
            }
            if (selectQuery != null) {
                selectQuery.close();
            }
            if (set != null) {
                try {
                    set.close();
                }
                catch (SQLException ex3) {}
            }
        });
    }
    
    @Override
    public int getPetItem(final PlayerManager playerManager, final PetItems petItems) {
        if (playerManager == null || petItems == null) {
            throw new NullPointerException();
        }
        SelectQuery where = null;
        ResultSet execute = null;
        try {
            where = this.sqliteManager.getPetItemsTable().select().where("UID", playerManager.getUID());
            execute = where.execute();
            if (!execute.next()) {
                this.sqliteManager.getPetItemsTable().insert().insert("UUID, UID").value(playerManager.getUUID().toString()).value(playerManager.getUID()).execute();
                return 0;
            }
            return execute.getInt(petItems.getSQLIndex());
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        finally {
            if (where != null) {
                where.close();
            }
            if (execute != null) {
                try {
                    execute.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    @Override
    public void setPetItems(final PlayerManager playerManager, final PetItems petItems, final int i) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getPetItemsTable().update().set(petItems.getSQLIndex(), i).where("UID", playerManager.getUID()).execute());
    }
    
    @Override
    public void addPetItems(final PlayerManager playerManager, final PetItems petItems, final int n) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.sqliteManager.getPetItemsTable().update().set(petItems.getSQLIndex(), this.getPetItem(playerManager, petItems) + n).where("UID", playerManager.getUID()).execute());
    }
    
    @Override
    public void removePetItems(final PlayerManager playerManager, final PetItems petItems, final int n) {
        final int n2;
        int i = 0;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            this.getPetItem(playerManager, petItems);
            if (n2 - n > 0) {
                i = n2 - n;
            }
            this.sqliteManager.getPetItemsTable().update().set(petItems.getSQLIndex(), i).where("UID", playerManager.getUID()).execute();
        });
    }
    
    @Override
    public void deletePlayerData(final UUID uuid, final int n) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            this.sqliteManager.getTable().delete().where("UUID", uuid.toString()).execute();
            this.sqliteManager.getMysteryBoxesTable().delete().where("UID", n).execute();
            this.sqliteManager.getUnlockedCosmeticItemsTable().delete().where("UID", n).execute();
            this.sqliteManager.getPetsTable().delete().where("UID", n).execute();
            this.sqliteManager.getPetItemsTable().delete().where("UID", n).execute();
        });
    }
}
