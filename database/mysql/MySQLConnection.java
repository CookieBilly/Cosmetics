// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.database.mysql;

import java.sql.SQLException;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.log.LoggerManager;
import java.sql.DriverManager;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.sql.Connection;
import ws.billy.CookieGadgets.database.DatabaseConnection;

public class MySQLConnection implements DatabaseConnection
{
    private final String hostname;
    private final String username;
    private final String database;
    private final String port;
    private final String password;
    private final boolean useSSL;
    private Connection connection;
    
    public MySQLConnection() {
        this.hostname = FileManager.getConfigFile().getString("Player-Data.MySQL.hostname");
        this.username = FileManager.getConfigFile().getString("Player-Data.MySQL.username");
        this.database = FileManager.getConfigFile().getString("Player-Data.MySQL.database");
        this.port = FileManager.getConfigFile().getString("Player-Data.MySQL.port");
        this.password = FileManager.getConfigFile().getString("Player-Data.MySQL.password");
        this.useSSL = FileManager.getConfigFile().getBoolean("Player-Data.MySQL.useSSL");
    }
    
    @Override
    public Connection openConnection() {
        if (this.checkConnection()) {
            return this.connection;
        }
        Class.forName("com.mysql.jdbc.Driver");
        return this.connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database + "?" + "&autoReconnect=true&wait_timeout=31536000&interactive_timeout=31536000&useUnicode=true&characterEncoding=utf8&useSSL=" + this.useSSL, this.username, this.password);
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
