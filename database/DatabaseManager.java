

package ws.billy.CookieGadgets.database;

import java.sql.Connection;

public interface DatabaseManager
{
    void init();
    
    void reconnect();
    
    DatabaseConnection getDBConnection();
    
    Connection getConnection();
    
    Table getTable();
    
    Table getMysteryBoxesTable();
    
    Table getUnlockedCosmeticItemsTable();
    
    Table getPetsTable();
    
    Table getPetItemsTable();
    
    DatabaseUtils getDatabaseUtils();
}
