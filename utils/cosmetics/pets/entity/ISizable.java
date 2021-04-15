

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity;

public interface ISizable
{
    int getSize();
    
    void setSize(final int p0);
    
    default boolean isSmall() {
        return this.getSize() <= 1;
    }
}
