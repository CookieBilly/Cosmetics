

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

public enum GHorseArmor
{
    NONE("NONE", 0, 0), 
    IRON("IRON", 1, 1), 
    GOLD("GOLD", 2, 2), 
    DIAMOND("DIAMOND", 3, 3);
    
    private int id;
    
    private GHorseArmor(final String name, final int ordinal, final int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public static GHorseArmor getByName(final String anotherString) {
        GHorseArmor[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GHorseArmor gHorseArmor = values[i];
            if (gHorseArmor.name().equalsIgnoreCase(anotherString)) {
                return gHorseArmor;
            }
        }
        return GHorseArmor.NONE;
    }
    
    public static GHorseArmor getById(final int n) {
        GHorseArmor[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GHorseArmor gHorseArmor = values[i];
            if (gHorseArmor.getId() == n) {
                return gHorseArmor;
            }
        }
        return null;
    }
}
