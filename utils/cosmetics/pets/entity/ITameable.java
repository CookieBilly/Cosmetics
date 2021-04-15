

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity;

public interface ITameable extends IAgeablePet
{
    boolean isTamed();
    
    void setTamed(final boolean p0);
    
    boolean isSitting();
    
    void setSitting(final boolean p0);
}
