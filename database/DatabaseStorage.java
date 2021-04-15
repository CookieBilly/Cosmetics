

package ws.billy.CookieGadgets.database;

public enum DatabaseStorage
{
    SQLITE("SQLITE", 0, "SQLite"), 
    MYSQL("MYSQL", 1, "MySQL");
    
    private String name;
    
    private DatabaseStorage(final String name, final int ordinal, final String name2) {
        this.name = name2;
    }
    
    public String getName() {
        return this.name;
    }
}
