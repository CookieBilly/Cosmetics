

package ws.billy.CookieGadgets.database.mysql;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.plugin.Plugin;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import java.io.IOException;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.sql.SQLSyntaxErrorException;
import ws.billy.CookieGadgets.log.LoggerManager;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.database.Table;
import ws.billy.CookieGadgets.database.DatabaseUtils;
import java.sql.Connection;
import ws.billy.CookieGadgets.database.DatabaseConnection;
import ws.billy.CookieGadgets.database.DatabaseManager;

public class MySQLManager implements DatabaseManager
{
    private DatabaseConnection dbConnection;
    private Connection connection;
    private DatabaseUtils sqlUtils;
    private Table table;
    private Table mysteryBoxesTable;
    private Table unlockedCosmeticItemsTable;
    private Table petsTable;
    private Table petItemsTable;
    public String tableName;
    public String mysteryBoxesTableName;
    public String unlockedCosmeticItemsTableName;
    public String petsTableName;
    public String petItemsTableName;
    private static boolean keepConnectionTask;
    private int reconnectTimes;
    
    static {
        MySQLManager.keepConnectionTask = false;
    }
    
    public MySQLManager() {
        this.tableName = "CookieGadgets_Data";
        this.mysteryBoxesTableName = "CookieGadgets_Mystery_Boxes";
        this.unlockedCosmeticItemsTableName = "CookieGadgets_Unlocked_Cosmetic_Items";
        this.petsTableName = "CookieGadgets_Pets";
        this.petItemsTableName = "CookieGadgets_Pet_Items";
        this.reconnectTimes = 5;
    }
    
    @Override
    public void init() {
        this.sqlUtils = new MySQLUtils(this);
        try {
            if (this.dbConnection != null) {
                this.dbConnection.closeConnection();
            }
            this.dbConnection = new MySQLConnection();
            LoggerManager.info(MessageType.CONNECTING_TO_DATABASE.getFormatMessage().replace("{DATABASE}", CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName()));
            this.connection = this.dbConnection.openConnection();
            this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.tableName + "(" + "id INT(11) NOT NULL AUTO_INCREMENT, " + "PRIMARY KEY (id), " + "UUID VARCHAR(36) NOT NULL, " + "Name VARCHAR(255) NOT NULL, " + "Mystery_Dust INT(11) DEFAULT 0 NOT NULL, " + "Mystery_Gift_Packs INT(11) DEFAULT 0 NOT NULL, " + "Mystery_Gift_Sent INT(11) DEFAULT 0 NOT NULL, " + "Mystery_Gift_Received INT(11) DEFAULT 0 NOT NULL, " + "Self_Morph_View VARCHAR(5) DEFAULT 'true' NOT NULL, " + "Bypass_Cooldown VARCHAR(5) DEFAULT 'false' NOT NULL, " + "Mystery_Vault_Animation VARCHAR(255) DEFAULT 'Normal' NOT NULL, " + "Last_Pet_Mission_Time_Millis BIGINT DEFAULT 0 NOT NULL, " + "Play_Time INT DEFAULT 0 NOT NULL, " + "Recent_Loots_Found VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT '' NOT NULL, " + "Selected_Hat VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Animated_Hat VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Particle VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Suit_Helmet VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Suit_Chestplate VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Suit_Leggings VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Suit_Boots VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Gadget VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Pet VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Miniature VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Morph VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Banner VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Emote VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Cloak VARCHAR(255) DEFAULT 'none' not NULL," + "KEY `playeruuid` (`uuid`) USING HASH" + ")").executeUpdate();
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.tableName + "` DROP COLUMN `Pet_Name`").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex4) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.tableName + "` CHANGE `Recent_Loots_Found` `Recent_Loots_Found` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT ''").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex5) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.tableName + "` ADD KEY `playeruuid` (`uuid`) USING HASH").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex6) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.tableName + "` ADD `Selected_Animated_Hat` VARCHAR(255) DEFAULT 'none' not NULL").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex7) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.tableName + "` ADD `Selected_Miniature` VARCHAR(255) DEFAULT 'none' not NULL").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex8) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.tableName + "` ADD `Play_Time` INT DEFAULT 0 not NULL").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex9) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.tableName + "` ADD `Last_Pet_Mission_Time_Millis` BIGINT DEFAULT 0 not NULL").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex10) {}
            this.table = new Table(this.connection, this.tableName);
            this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.mysteryBoxesTableName + "(" + "id INT(11) NOT NULL AUTO_INCREMENT, " + "PRIMARY KEY (id), " + "UUID VARCHAR(36) NOT NULL, " + "UID INT(11) NOT NULL, " + "Loots VARCHAR(2000) NOT NULL" + ")").executeUpdate();
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.mysteryBoxesTableName + "` ADD `UID` INT(11) NOT NULL").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex11) {}
            this.mysteryBoxesTable = new Table(this.connection, this.mysteryBoxesTableName);
            this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.unlockedCosmeticItemsTableName + "(" + "id INT(11) NOT NULL AUTO_INCREMENT, " + "PRIMARY KEY (id), " + "UUID VARCHAR(36) NOT NULL, " + "UID INT(11) NOT NULL, " + "Cosmetic VARCHAR(255) NOT NULL, " + "Type VARCHAR(255) NOT NULL, " + "Unlock_Time datetime NOT NULL, " + "Expiry_Time datetime NOT NULL" + ")").executeUpdate();
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.unlockedCosmeticItemsTableName + "` ADD `UID` INT(11) NOT NULL").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex12) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.unlockedCosmeticItemsTableName + "` CHANGE `Valid_Time` `Expiry_Time` datetime NOT NULL").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex13) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.unlockedCosmeticItemsTableName + "` DROP COLUMN `Name`").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex14) {}
            if (FileManager.getConfigFile().get("Update-MySQL") != null) {
                this.connection.prepareStatement("UPDATE `" + this.unlockedCosmeticItemsTableName + "` uci, `" + this.tableName + "` gd SET uci.UID = gd.id WHERE uci.UUID = gd.UUID AND uci.UID = 0").executeUpdate();
                final PreparedStatement prepareStatement = this.connection.prepareStatement("UPDATE `" + this.mysteryBoxesTableName + "` mb, `" + this.tableName + "` gd SET mb.UID = gd.id WHERE mb.UUID = gd.UUID AND mb.UID = 0");
                prepareStatement.executeUpdate();
                prepareStatement.close();
                FileManager.getConfigFile().set("Update-MySQL", (Object)null);
                try {
                    FileManager.getConfigFile().save(CookieGadgets.getInstance().getFile());
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            this.unlockedCosmeticItemsTable = new Table(this.connection, this.unlockedCosmeticItemsTableName);
            this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.petsTableName + "(" + "id INT(11) NOT NULL AUTO_INCREMENT, " + "PRIMARY KEY (id), " + "UUID VARCHAR(36) NOT NULL, " + "UID INT(11) NOT NULL, " + "Pet_Type VARCHAR(255) NOT NULL," + "Pet_Name VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT 'Pet' NOT NULL," + "Pet_Level INT(3) DEFAULT 1 NOT NULL," + "Pet_XP INT(5) DEFAULT 0 NOT NULL," + "Hunger INT(3) DEFAULT 0 NOT NULL," + "Eat_Timestamp datetime NOT NULL," + "Thirst INT(3) DEFAULT 0 NOT NULL," + "Drink_Timestamp datetime NOT NULL," + "Exercise INT(3) DEFAULT 0 NOT NULL," + "Exercise_Timestamp datetime NOT NULL" + ")").executeUpdate();
            this.petsTable = new Table(this.connection, this.petsTableName);
            try {
                this.connection.prepareStatement("UPDATE `" + this.petsTableName + "` SET Pet_Name = REPLACE(Pet_Name, '§', '&')").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex15) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.petsTableName + "` CHANGE `Pet_Name` `Pet_Name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Pet'").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex16) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.petsTableName + "` DROP COLUMN `Name`").executeUpdate();
            }
            catch (SQLSyntaxErrorException ex17) {}
            String string = "";
            PetItems[] values;
            for (int length = (values = PetItems.values()).length, i = 0; i < length; ++i) {
                string = String.valueOf(string) + values[i].getSQLIndex() + " INT(11) DEFAULT 0 NOT NULL, ";
            }
            PreparedStatement preparedStatement = this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.petItemsTableName + "(" + "id INT(11) NOT NULL AUTO_INCREMENT, " + "PRIMARY KEY (id), " + "UUID VARCHAR(36) NOT NULL, " + "UID INT(11) NOT NULL, " + string.substring(0, string.length() - 2) + ")");
            preparedStatement.executeUpdate();
            try {
                preparedStatement = this.connection.prepareStatement("ALTER TABLE `" + this.petItemsTableName + "` DROP COLUMN `Name`");
                preparedStatement.executeUpdate();
            }
            catch (SQLSyntaxErrorException ex18) {}
            this.petItemsTable = new Table(this.connection, this.petItemsTableName);
            preparedStatement.close();
            LoggerManager.info(MessageType.SUCCESSFULLY_CONNECTED_TO_DATABASE.getFormatMessage().replace("{DATABASE}", CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName()));
            if (!MySQLManager.keepConnectionTask) {
                final PreparedStatement preparedStatement2;
                final ResultSet set;
                Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    try {
                        if (this.dbConnection == null) {
                            return;
                        }
                        else if (this.dbConnection.isClosed() || this.connection.isClosed()) {
                            return;
                        }
                        else {
                            this.getConnection().prepareStatement("SELECT * FROM " + this.tableName + " WHERE id = 1");
                            preparedStatement2.executeQuery();
                            set.first();
                            set.getString("UUID");
                        }
                    }
                    catch (SQLException ex19) {}
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    finally {
                        if (set != null) {
                            try {
                                set.close();
                            }
                            catch (SQLException ex20) {}
                        }
                        if (preparedStatement2 != null) {
                            try {
                                preparedStatement2.close();
                            }
                            catch (SQLException ex21) {}
                        }
                    }
                    if (set != null) {
                        try {
                            set.close();
                        }
                        catch (SQLException ex22) {}
                    }
                    if (preparedStatement2 != null) {
                        try {
                            preparedStatement2.close();
                        }
                        catch (SQLException ex23) {}
                    }
                    return;
                }, 1200L, 9600L);
                MySQLManager.keepConnectionTask = true;
            }
            if (this.reconnectTimes < 5) {
                Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.reconnectTimes < 5) {
                        ++this.reconnectTimes;
                    }
                }, 6000L);
            }
        }
        catch (Exception ex3) {
            LoggerManager.info("--------------------------------------------------");
            LoggerManager.consoleMessage(ChatUtil.format("&c&l" + MessageType.FAILED_TO_CONNECT_TO_DATEBASE.getFormatMessage().replace("{DATABASE}", CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName())));
            LoggerManager.info("--------------------------------------------------");
            ex3.printStackTrace();
            Bukkit.getServer().getPluginManager().disablePlugin((Plugin)CookieGadgets.getInstance());
        }
    }
    
    @Override
    public synchronized void reconnect() {
        try {
            if ((this.connection == null || this.connection.isClosed()) && CookieGadgets.getInstance() != null) {
                if (this.reconnectTimes <= 0) {
                    LoggerManager.printLogWithHeader(LoggerManager.LogLevel.SEVERE, "--------------------------------------------------", MessageType.DATABASE_RECONNECTION_IS_TOO_FREQUENT.getFormatMessage().replace("{DATABASE}", CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName()));
                    return;
                }
                this.init();
                --this.reconnectTimes;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public DatabaseConnection getDBConnection() {
        return this.dbConnection;
    }
    
    @Override
    public Connection getConnection() {
        this.reconnect();
        return this.connection;
    }
    
    @Override
    public Table getTable() {
        this.reconnect();
        return this.table;
    }
    
    @Override
    public Table getMysteryBoxesTable() {
        this.reconnect();
        return this.mysteryBoxesTable;
    }
    
    @Override
    public Table getUnlockedCosmeticItemsTable() {
        this.reconnect();
        return this.unlockedCosmeticItemsTable;
    }
    
    @Override
    public Table getPetsTable() {
        this.reconnect();
        return this.petsTable;
    }
    
    @Override
    public Table getPetItemsTable() {
        this.reconnect();
        return this.petItemsTable;
    }
    
    @Override
    public DatabaseUtils getDatabaseUtils() {
        return this.sqlUtils;
    }
}
