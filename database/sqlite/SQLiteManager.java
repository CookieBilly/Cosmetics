

package ws.billy.CookieGadgets.database.sqlite;

import java.sql.PreparedStatement;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import java.sql.SQLException;
import ws.billy.CookieGadgets.log.LoggerManager;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.database.Table;
import ws.billy.CookieGadgets.database.DatabaseUtils;
import java.sql.Connection;
import ws.billy.CookieGadgets.database.DatabaseConnection;
import ws.billy.CookieGadgets.database.DatabaseManager;

public class SQLiteManager implements DatabaseManager
{
    private DatabaseConnection dbConnection;
    private Connection connection;
    private DatabaseUtils sqliteUtils;
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
    private int reconnectTimes;
    
    public SQLiteManager() {
        this.tableName = "CookieGadgets_Data";
        this.mysteryBoxesTableName = "CookieGadgets_Mystery_Boxes";
        this.unlockedCosmeticItemsTableName = "CookieGadgets_Unlocked_Cosmetic_Items";
        this.petsTableName = "CookieGadgets_Pets";
        this.petItemsTableName = "CookieGadgets_Pet_Items";
        this.reconnectTimes = 5;
    }
    
    @Override
    public void init() {
        this.sqliteUtils = new SQLiteUtils(this);
        try {
            if (this.dbConnection != null) {
                this.dbConnection.closeConnection();
            }
            this.dbConnection = new SQLiteConnection();
            LoggerManager.info(MessageType.CONNECTING_TO_DATABASE.getFormatMessage().replace("{DATABASE}", CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName()));
            this.connection = this.dbConnection.openConnection();
            this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.tableName + "(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "UUID VARCHAR(36) NOT NULL, " + "Name VARCHAR(255) NOT NULL, " + "Mystery_Dust INTEGER DEFAULT 0 NOT NULL, " + "Mystery_Gift_Packs INTEGER DEFAULT 0 NOT NULL, " + "Mystery_Gift_Sent INTEGER DEFAULT 0 NOT NULL, " + "Mystery_Gift_Received INTEGER DEFAULT 0 NOT NULL, " + "Self_Morph_View VARCHAR(5) DEFAULT 'true' NOT NULL, " + "Bypass_Cooldown VARCHAR(5) DEFAULT 'false' NOT NULL, " + "Mystery_Vault_Animation VARCHAR(255) DEFAULT 'Normal' NOT NULL, " + "Last_Pet_Mission_Time_Millis INTEGER DEFAULT 0 NOT NULL, " + "Play_Time INTEGER DEFAULT 0 NOT NULL, " + "Recent_Loots_Found VARCHAR(255) DEFAULT '' NOT NULL, " + "Selected_Hat VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Animated_Hat VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Particle VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Suit_Helmet VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Suit_Chestplate VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Suit_Leggings VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Suit_Boots VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Gadget VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Pet VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Miniature VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Morph VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Banner VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Emote VARCHAR(255) DEFAULT 'none' not NULL, " + "Selected_Cloak VARCHAR(255) DEFAULT 'none' not NULL" + ")").executeUpdate();
            this.table = new Table(this.connection, this.tableName);
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.tableName + "` ADD Selected_Miniature VARCHAR(255) DEFAULT 'none' not NULL").executeUpdate();
            }
            catch (SQLException ex2) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.tableName + "` ADD Play_Time INTEGER DEFAULT 0 not NULL").executeUpdate();
            }
            catch (SQLException ex3) {}
            try {
                this.connection.prepareStatement("ALTER TABLE `" + this.tableName + "` ADD Last_Pet_Mission_Time_Millis INTEGER DEFAULT 0 not NULL").executeUpdate();
            }
            catch (SQLException ex4) {}
            this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.mysteryBoxesTableName + "(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "UUID VARCHAR(36) NOT NULL, " + "UID INTEGER NOT NULL, " + "Loots VARCHAR(2000) NOT NULL" + ")").executeUpdate();
            this.mysteryBoxesTable = new Table(this.connection, this.mysteryBoxesTableName);
            this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.unlockedCosmeticItemsTableName + "(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "UUID VARCHAR(36) NOT NULL, " + "UID INTEGER NOT NULL, " + "Cosmetic VARCHAR(255) NOT NULL," + "Type VARCHAR(255) NOT NULL," + "Unlock_Time datetime NOT NULL," + "Expiry_Time datetime NOT NULL" + ")").executeUpdate();
            this.unlockedCosmeticItemsTable = new Table(this.connection, this.unlockedCosmeticItemsTableName);
            this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.petsTableName + "(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "UUID VARCHAR(36) NOT NULL, " + "UID INTEGER NOT NULL, " + "Pet_Type VARCHAR(255) NOT NULL," + "Pet_Name VARCHAR(255) DEFAULT 'Pet' NOT NULL," + "Pet_Level INTEGER DEFAULT 1 NOT NULL," + "Pet_XP INTEGER DEFAULT 0 NOT NULL," + "Hunger INTEGER DEFAULT 0 NOT NULL," + "Eat_Timestamp datetime NOT NULL," + "Thirst INTEGER DEFAULT 0 NOT NULL," + "Drink_Timestamp datetime NOT NULL," + "Exercise INTEGER DEFAULT 0 NOT NULL," + "Exercise_Timestamp datetime NOT NULL" + ")").executeUpdate();
            this.petsTable = new Table(this.connection, this.petsTableName);
            String string = "";
            PetItems[] values;
            for (int length = (values = PetItems.values()).length, i = 0; i < length; ++i) {
                string = String.valueOf(string) + values[i].getSQLIndex() + " INTEGER DEFAULT 0 NOT NULL, ";
            }
            final PreparedStatement prepareStatement = this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.petItemsTableName + "(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "UUID VARCHAR(36) NOT NULL, " + "UID INTEGER NOT NULL, " + string.substring(0, string.length() - 2) + ")");
            prepareStatement.executeUpdate();
            this.petItemsTable = new Table(this.connection, this.petItemsTableName);
            prepareStatement.close();
            LoggerManager.info(MessageType.SUCCESSFULLY_CONNECTED_TO_DATABASE.getFormatMessage().replace("{DATABASE}", CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName()));
            if (this.reconnectTimes < 5) {
                Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.reconnectTimes < 5) {
                        ++this.reconnectTimes;
                    }
                }, 6000L);
            }
        }
        catch (Exception ex) {
            LoggerManager.info("--------------------------------------------------");
            LoggerManager.consoleMessage(ChatUtil.format("&c&l" + MessageType.FAILED_TO_CONNECT_TO_DATEBASE.getFormatMessage().replace("{DATABASE}", CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName())));
            LoggerManager.info("--------------------------------------------------");
            ex.printStackTrace();
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
        return this.sqliteUtils;
    }
}
