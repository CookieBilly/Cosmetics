

package ws.billy.CookieGadgets.database.query;

import java.sql.Connection;

public class Query
{
    protected final Connection connection;
    protected String sql;
    
    public Query(final Connection connection, final String sql) {
        this.connection = connection;
        this.sql = sql;
    }
    
    @Override
    public String toString() {
        return this.sql;
    }
}
