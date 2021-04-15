

package ws.billy.CookieGadgets.cosmetics.balloons;

import java.lang.reflect.Method;
import net.minecraft.server.v1_10_R1.MathHelper;
import org.bukkit.Bukkit;
import net.minecraft.server.v1_10_R1.WorldServer;
import ws.billy.CookieGadgets.log.LoggerManager;
import net.minecraft.server.v1_10_R1.Entity;
import net.minecraft.server.v1_10_R1.World;
import ws.billy.CookieGadgets.nms.v1_10_R1.balloons.NMSBalloons;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import java.util.HashMap;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import java.util.Map;

public class BalloonManager
{
    public static Map<Player, BalloonManager> epicBalloonsMap;
    private Player player;
    private ItemStack item;
    private Slime slime;
    private ArmorStand armorStand;
    
    static {
        BalloonManager.epicBalloonsMap = new HashMap<Player, BalloonManager>();
    }
    
    public BalloonManager(final Player player, final ItemStack item) {
        this.player = player;
        this.item = item;
        if (BalloonManager.epicBalloonsMap.containsKey(this.player)) {
            BalloonManager.epicBalloonsMap.get(this.player).remove();
            System.out.println("duplicated");
        }
        BalloonManager.epicBalloonsMap.put(player, this);
        System.out.println("added");
    }
    
    public void spawn() {
        final WorldServer handle = ((CraftWorld)this.player.getWorld()).getHandle();
        final NMSBalloons nmsBalloons = new NMSBalloons((World)handle, this.player, this.item);
        this.slime = nmsBalloons.getSlime();
        this.armorStand = nmsBalloons.getContents();
        if (!this.addEntityToWorld(handle, (Entity)nmsBalloons)) {
            LoggerManager.printLog(LoggerManager.LogLevel.WARNING, "Coulnd't spawn mystery vault hologram!");
        }
        System.out.println("spawn");
    }
    
    public void remove() {
        this.slime.remove();
        this.armorStand.remove();
        BalloonManager.epicBalloonsMap.remove(this.player, this);
        System.out.println("despawn");
    }
    
    public void setItem(final ItemStack helmet) {
        this.armorStand.setHelmet(helmet);
    }
    
    private boolean addEntityToWorld(final WorldServer obj, final Entity entity) {
        if (!Bukkit.isPrimaryThread()) {
            throw new IllegalArgumentException("Async entity add");
        }
        final int floor = MathHelper.floor(entity.locX / 16.0);
        final int floor2 = MathHelper.floor(entity.locZ / 16.0);
        if (!obj.getChunkProviderServer().isLoaded(floor, floor2)) {
            entity.dead = true;
            return false;
        }
        obj.getChunkAt(floor, floor2).a(entity);
        obj.entityList.add(entity);
        try {
            final Method declaredMethod = World.class.getDeclaredMethod("b", Entity.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, entity);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
