

package ws.billy.CookieGadgets.database;

import java.sql.Connection;

public interface DatabaseConnection
{
    Connection openConnection() throws Exception;
    
    boolean checkConnection();
    
    Connection getConnection();
    
    boolean isClosed();
    
    boolean closeConnection();
}
