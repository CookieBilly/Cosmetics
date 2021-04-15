

package ws.billy.CookieGadgets.database.query;

import java.util.Iterator;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;

public class InsertQuery extends Query
{
    private boolean firstValue;
    private final List<Object> values;
    
    public InsertQuery(final Connection connection, final String s) {
        super(connection, s);
        this.firstValue = true;
        this.values = new ArrayList<Object>();
    }
    
    public InsertQuery insert(final String str) {
        this.sql = String.valueOf(this.sql) + str + ", ";
        return this;
    }
    
    public InsertQuery value(final Object o) {
        this.values.add(o);
        this.sql = this.sql.substring(0, this.sql.length() - 1);
        if (this.firstValue) {
            this.sql = this.sql.substring(0, this.sql.length() - 1);
            this.sql = String.valueOf(this.sql) + ") VALUES (?)";
            this.firstValue = false;
        }
        else {
            this.sql = String.valueOf(this.sql) + ", ?)";
        }
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
