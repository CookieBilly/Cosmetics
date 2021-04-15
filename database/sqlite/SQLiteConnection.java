

package ws.billy.CookieGadgets.database.sqlite;

import java.sql.SQLException;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.log.LoggerManager;
import java.sql.DriverManager;
import java.io.File;
import ws.billy.CookieGadgets.CookieGadgets;
import java.sql.Connection;
import ws.billy.CookieGadgets.database.DatabaseConnection;

public class SQLiteConnection implements DatabaseConnection
{
    private Connection connection;
    
    @Override
    public Connection openConnection() {
        if (!CookieGadgets.getInstance().getDataFolder().exists()) {
            CookieGadgets.getInstance().getDataFolder().mkdir();
        }
        final File obj = new File(CookieGadgets.getInstance().getDataFolder(), "/database.db");
        Class.forName("org.sqlite.JDBC");
        return this.connection = DriverManager.getConnection("jdbc:sqlite:" + obj);
    }
    
    @Override
    public boolean checkConnection() {
        try {
            return this.connection != null && !this.connection.isClosed();
        }
        catch (SQLException ex) {
            LoggerManager.printLog(LoggerManager.LogLevel.SEVERE, "--------------------------------------------------", MessageType.FAILED_TO_CONNECT_TO_DATEBASE.getFormatMessage().replace("{DATABASE}", CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName()));
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Connection getConnection() {
        return this.connection;
    }
    
    @Override
    public boolean isClosed() {
        if (this.connection == null) {
            return true;
        }
        try {
            return this.connection.isClosed();
        }
        catch (SQLException ex) {
            LoggerManager.printLog(LoggerManager.LogLevel.SEVERE, "--------------------------------------------------", MessageType.FAILED_TO_CLOSE_THE_DATEBASE.getFormatMessage().replace("{DATABASE}", CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName()));
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean closeConnection() {
        if (this.connection == null) {
            return false;
        }
        try {
            this.connection.close();
            LoggerManager.info(MessageType.DATABASE_CONNECTION_IS_CLOSED.getFormatMessage().replace("{DATABASE}", CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName()));
        }
        catch (SQLException ex) {
            LoggerManager.printLog(LoggerManager.LogLevel.SEVERE, "--------------------------------------------------", MessageType.FAILED_TO_CLOSE_THE_DATEBASE.getFormatMessage().replace("{DATABASE}", CookieGadgets.getCookieGadgetsData().getDatabaseStorage().getName()));
            ex.printStackTrace();
        }
        return true;
    }
}
