

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GPandaGene;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IAgeablePet;

public interface IEntityPandaPet extends IAgeablePet
{
    void setSneezing(final boolean p0);
    
    default boolean isSneezing() {
        return this.getFlag(2);
    }
    
    boolean isSitting();
    
    void setSitting(final boolean p0);
    
    GPandaGene getGene();
    
    void setGene(final GPandaGene p0);
    
    boolean getFlag(final int p0);
    
    void setFlag(final int p0, final boolean p1);
    
    default boolean isPlaying() {
        return this.getFlag(4);
    }
    
    default void setPlaying(final boolean value) {
        this.setFlag(4, value);
    }
    
    default boolean isScared() {
        return this.getFlag(8);
    }
    
    default void setScared(final boolean value) {
        this.setFlag(8, value);
    }
    
    default boolean isLyingOnBack() {
        return this.getFlag(16);
    }
    
    default void setLyingOnBack(final boolean value) {
        this.setFlag(16, value);
    }
}
