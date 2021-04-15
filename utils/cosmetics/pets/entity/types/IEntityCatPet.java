// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.GDyeColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GCatType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.ITameable;

public interface IEntityCatPet extends ITameable
{
    GCatType getCatType();
    
    void setCatType(final GCatType p0);
    
    GDyeColor getCollarColor();
    
    void setCollarColor(final GDyeColor p0);
    
    boolean isLying();
    
    void setLying(final boolean p0);
    
    boolean isLookUp();
    
    void setLookUp(final boolean p0);
}
