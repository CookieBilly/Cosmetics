

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

public enum GRabbitType
{
    BROWN("BROWN", 0, 0), 
    WHITE("WHITE", 1, 1), 
    BLACK("BLACK", 2, 2), 
    BLACK_AND_WHITE("BLACK_AND_WHITE", 3, 3), 
    GOLD("GOLD", 4, 4), 
    SALT_AND_PEPPER("SALT_AND_PEPPER", 5, 5), 
    THE_KILLER_BUNNY("THE_KILLER_BUNNY", 6, 99);
    
    private int id;
    
    private GRabbitType(final String name, final int ordinal, final int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public static GRabbitType getById(final int n) {
        GRabbitType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GRabbitType gRabbitType = values[i];
            if (gRabbitType.getId() == n) {
                return gRabbitType;
            }
        }
        return null;
    }
    
    public static GRabbitType getByName(final String anotherString) {
        GRabbitType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GRabbitType gRabbitType = values[i];
            if (gRabbitType.name().equalsIgnoreCase(anotherString)) {
                return gRabbitType;
            }
        }
        return null;
    }
}
