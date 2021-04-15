

package ws.billy.CookieGadgets.cosmetics.pets;

import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.ServerVersion;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;

public abstract class EntityPet
{
    private GEntity entity;
    private SoundEffect ambient;
    private boolean canFloat;
    private double extraFollowDistance;
    
    public EntityPet(final GEntity entity, final SoundEffect ambient) {
        this.ambient = null;
        this.extraFollowDistance = 0.0;
        this.entity = entity;
        this.ambient = ambient;
        this.canFloat = false;
    }
    
    public GEntity getGEntity() {
        return this.entity;
    }
    
    public SoundEffect getAmbientSound() {
        return this.ambient;
    }
    
    public ServerVersion getRequiredVersion() {
        return ServerVersion.v1_8_R1;
    }
    
    public abstract Class<? extends IEntityPet> getEntityClass();
    
    public boolean canFly() {
        return false;
    }
    
    public boolean canFloat() {
        return this.canFloat;
    }
    
    public double getExtraFollowDistance() {
        return this.extraFollowDistance;
    }
    
    public void addExtraFollowDistance(final double n) {
        this.extraFollowDistance += n;
    }
    
    public boolean isSupported() {
        return VersionManager.isVersionGreaterEqual(ServerVersion.getServerVersion().getName(), this.getRequiredVersion().getName());
    }
}
