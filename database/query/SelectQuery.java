

package ws.billy.CookieGadgets.database.query;

import java.util.Iterator;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class SelectQuery extends Query
{
    private boolean and;
    private final List<Object> values;
    private PreparedStatement statement;
    
    public SelectQuery(final Connection connection, final String s) {
        super(connection, s);
        this.and = false;
        this.values = new ArrayList<Object>();
    }
    
    public SelectQuery where(final String str, final Object o) {
        this.sql = String.valueOf(this.sql) + (this.and ? " AND" : " WHERE");
        this.sql = String.valueOf(this.sql) + " " + str + "=?";
        this.values.add(o);
        this.and = true;
        return this;
    }
    
    public ResultSet execute() {
        try {
            this.statement = this.connection.prepareStatement(this.sql);
            int n = 1;
            final Iterator<Object> iterator = this.values.iterator();
            while (iterator.hasNext()) {
                this.statement.setObject(n, iterator.next());
                ++n;
            }
            return this.statement.executeQuery();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void close() {
        try {
            this.statement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
