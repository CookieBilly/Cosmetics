

package ws.billy.CookieGadgets.nms.v1_9_R2.pets.entity;

import net.minecraft.server.v1_9_R2.WorldServer;
import ws.billy.CookieGadgets.log.LoggerManager;
import net.minecraft.server.v1_9_R2.Entity;
import ws.billy.CookieGadgets.nms.v1_9_R2.NMSManagerImpl;
import net.minecraft.server.v1_9_R2.World;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import org.bukkit.Location;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ServerVersion;
import ws.billy.CookieGadgets.cosmetics.pets.TypeManager;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import java.util.HashMap;
import ws.billy.CookieGadgets.nms.interfaces.pets.ISpawnUtil;

public class SpawnUtil implements ISpawnUtil
{
    private static HashMap<Class<? extends IEntityPet>, Class<? extends EntityPet>> nmsPets;
    
    static {
        SpawnUtil.nmsPets = new HashMap<Class<? extends IEntityPet>, Class<? extends EntityPet>>();
    }
    
    @Override
    public void initNMSPets() {
        for (final ws.billy.CookieGadgets.cosmetics.pets.EntityPet entityPet : TypeManager.getTypes()) {
            final String replaceFirst = entityPet.getEntityClass().getSimpleName().replaceFirst("I", "");
            Class<?> forName;
            try {
                forName = Class.forName("ws.billy.CookieGadgets.nms." + ServerVersion.getServerVersion() + ".pets.entity.types." + replaceFirst);
            }
            catch (ClassNotFoundException ex) {
                continue;
            }
            SpawnUtil.nmsPets.put(entityPet.getEntityClass(), (Class<? extends EntityPet>)forName);
        }
    }
    
    @Override
    public IEntityPet spawnEntityPet(final Location location, final IPet pet, final Class<? extends IEntityPet> clazz) {
        return this.spawn(location, pet, clazz);
    }
    
    private IEntityPet spawn(final Location location, final IPet pet, final Class<? extends IEntityPet> clazz) {
        if (!SpawnUtil.nmsPets.containsKey(clazz)) {
            return null;
        }
        try {
            final WorldServer handle = ((CraftWorld)location.getWorld()).getHandle();
            final EntityPet entityPet = (EntityPet)SpawnUtil.nmsPets.get(clazz).getDeclaredConstructor(World.class, IPet.class).newInstance(handle, pet);
            entityPet.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
            if (!NMSManagerImpl.addEntityToWorld(handle, (Entity)entityPet)) {
                LoggerManager.printLog(LoggerManager.LogLevel.WARNING, "Couldn't spawn pet!");
            }
            return entityPet;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
