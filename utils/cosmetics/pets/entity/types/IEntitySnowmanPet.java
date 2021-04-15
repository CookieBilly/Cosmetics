// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;

public interface IEntitySnowmanPet extends IEntityPet
{
    default boolean hasPumpkin() {
        return true;
    }
    
    default void setPumpkin(final boolean flag) {
    }
}
