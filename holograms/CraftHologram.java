

package ws.billy.CookieGadgets.holograms;

import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.Chunk;
import org.bukkit.Location;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSNameable;
import org.bukkit.World;

public class CraftHologram
{
    private World world;
    private double x;
    private double y;
    private double z;
    private int chunkX;
    private int chunkZ;
    private NMSNameable nmsNameable;
    private CraftVisibilityManager visibilityManager;
    
    public CraftHologram(final Location location) {
        this.updateLocation(location.getWorld(), location.getX(), location.getY(), location.getZ());
        this.visibilityManager = new CraftVisibilityManager(this);
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public Location getLocation() {
        return new Location(this.world, this.x, this.y, this.z);
    }
    
    public boolean isInChunk(final Chunk chunk) {
        return chunk.getX() == this.chunkX && chunk.getZ() == this.chunkZ;
    }
    
    public NMSNameable getNMSNameable() {
        return this.nmsNameable;
    }
    
    public void setNMSNameable(final NMSNameable nmsNameable) {
        this.nmsNameable = nmsNameable;
    }
    
    private void updateLocation(final World world, final double x, final double y, final double z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.chunkX = MathUtil.floor(x) >> 4;
        this.chunkZ = MathUtil.floor(z) >> 4;
    }
    
    public CraftVisibilityManager getVisibilityManager() {
        return this.visibilityManager;
    }
}
