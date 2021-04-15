

package ws.billy.CookieGadgets.database;

import ws.billy.CookieGadgets.database.query.DeleteQuery;
import ws.billy.CookieGadgets.database.query.InsertQuery;
import ws.billy.CookieGadgets.database.query.UpdateQuery;
import ws.billy.CookieGadgets.database.query.SelectQuery;
import java.sql.Connection;

public class Table
{
    private final Connection connection;
    private final String table;
    
    public Table(final Connection connection, final String table) {
        this.connection = connection;
        this.table = table;
    }
    
    public String getTableName() {
        return this.table;
    }
    
    public SelectQuery select() {
        return this.select("*");
    }
    
    public SelectQuery select(final String str) {
        return new SelectQuery(this.connection, "SELECT " + str + " FROM " + this.table);
    }
    
    public UpdateQuery update() {
        return new UpdateQuery(this.connection, "UPDATE " + this.table + " SET");
    }
    
    public InsertQuery insert() {
        return new InsertQuery(this.connection, "INSERT INTO " + this.table + " (");
    }
    
    public DeleteQuery delete() {
        return new DeleteQuery(this.connection, "DELETE FROM " + this.table);
    }
}
