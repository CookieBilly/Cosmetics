

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

import org.bukkit.entity.Skeleton;

public enum GSkeletonType
{
    NORMAL("NORMAL", 0, "Normal", 0), 
    WITHER("WITHER", 1, "Wither", 1), 
    STRAY("STRAY", 2, "Stray", 2);
    
    private String name;
    private int id;
    
    private GSkeletonType(final String name, final int ordinal, final String name2, final int id) {
        this.name = name2;
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getId() {
        return this.id;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public Skeleton.SkeletonType toSkeletonType() {
        return Skeleton.SkeletonType.valueOf(this.name.toUpperCase());
    }
    
    public static GSkeletonType getById(final int n) {
        GSkeletonType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GSkeletonType gSkeletonType = values[i];
            if (gSkeletonType.getId() == n) {
                return gSkeletonType;
            }
        }
        return null;
    }
}
