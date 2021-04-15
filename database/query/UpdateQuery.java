

package ws.billy.CookieGadgets.database.query;

import java.util.Iterator;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;

public class UpdateQuery extends Query
{
    private boolean comma;
    private boolean and;
    private final List<Object> values;
    
    public UpdateQuery(final Connection connection, final String s) {
        super(connection, s);
        this.comma = false;
        this.and = false;
        this.values = new ArrayList<Object>();
    }
    
    public UpdateQuery set(final String str, final Object o) {
        if (this.comma) {
            this.sql = String.valueOf(this.sql) + ",";
        }
        this.sql = String.valueOf(this.sql) + " " + str + "=?";
        this.values.add(o);
        this.comma = true;
        return this;
    }
    
    public UpdateQuery where(final String str, final Object o) {
        this.sql = String.valueOf(this.sql) + (this.and ? " AND" : " WHERE");
        this.sql = String.valueOf(this.sql) + " " + str + "=?";
        this.values.add(o);
        this.and = true;
        return this;
    }
    
    public void execute() {
        try {
            final PreparedStatement prepareStatement = this.connection.prepareStatement(this.sql);
            int n = 1;
            final Iterator<Object> iterator = this.values.iterator();
            while (iterator.hasNext()) {
                prepareStatement.setObject(n, iterator.next());
                ++n;
            }
            prepareStatement.executeUpdate();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
