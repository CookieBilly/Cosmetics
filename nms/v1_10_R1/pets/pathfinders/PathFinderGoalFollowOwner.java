

package ws.billy.CookieGadgets.nms.v1_10_R1.pets.pathfinders;

import net.minecraft.server.v1_10_R1.GenericAttributes;
import org.bukkit.event.Event;
import ws.billy.CookieGadgets.api.event.pets.PetMoveEvent;
import org.bukkit.Bukkit;
import net.minecraft.server.v1_10_R1.Entity;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity.EntityPet;
import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.ISizable;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_10_R1.PathfinderGoal;

public class PathFinderGoalFollowOwner extends PathfinderGoal
{
    public Player owner;
    public IEntityPet pet;
    protected double speed;
    private PlayerManager pManager;
    private double movingDistance;
    private double teleportDistance;
    private int stayDistance;
    
    public PathFinderGoalFollowOwner(final Player owner, final IEntityPet pet, final double speed) {
        this.movingDistance = 2.0;
        this.teleportDistance = 15.0;
        this.stayDistance = 1;
        this.owner = owner;
        this.pet = pet;
        this.speed = speed;
        this.pManager = CookieGadgets.getPlayerManager(owner);
        if (!pet.getPet().getType().getEntityType().isBaby()) {
            this.movingDistance += pet.getPet().getType().getEntityPet().getExtraFollowDistance();
            this.stayDistance += (int)pet.getPet().getType().getEntityPet().getExtraFollowDistance();
        }
        if (pet instanceof ISizable && !((ISizable)pet).isSmall()) {
            this.movingDistance = 3.0;
            this.stayDistance = 2;
        }
    }
    
    public boolean a() {
        if (this.pet == null || !this.owner.isOnline()) {
            return false;
        }
        if (this.pManager.getEquippedPet() == null) {
            return false;
        }
        final IPet pet = this.pet.getPet();
        if (pet == null || pet.isOwnerRiding() || pet.isHat()) {
            return false;
        }
        final Location location = this.owner.getLocation();
        if (!this.pet.getEntity().getWorld().equals(location.getWorld())) {
            this.pet.getEntityNMS().teleportNMS(location);
            return false;
        }
        return true;
    }
    
    public boolean b() {
        final Location location = this.owner.getLocation();
        if (this.pet.getEntity().getWorld().equals(location.getWorld())) {
            final EntityPet entityPet = (EntityPet)this.pet;
            final double distance = this.pet.getEntity().getLocation().distance(location);
            if (distance >= this.teleportDistance) {
                if (distance >= 80.0) {
                    this.pet.getEntityNMS().teleportNMS(location);
                    entityPet.getNavigation().a((Entity)((CraftPlayer)this.owner).getHandle(), this.speed);
                    return false;
                }
                if (this.owner.isOnGround()) {
                    this.pet.getEntityNMS().teleportNMS(location);
                    entityPet.getNavigation().a((Entity)((CraftPlayer)this.owner).getHandle(), this.speed);
                    return false;
                }
            }
        }
        return !((EntityPet)this.pet).getNavigation().n();
    }
    
    public void c() {
        if (this.pManager.getEquippedPet() != null) {
            if (this.pManager.getCurrentPet().getEntityPet() != null) {
                Bukkit.getServer().getPluginManager().callEvent((Event)new PetMoveEvent(this.pManager.getCurrentPet().getEntityPet(), PetMoveEvent.Cause.WALK));
            }
            final EntityPet entityPet = (EntityPet)this.pet;
            final Location location = this.owner.getLocation();
            final double distance = this.pet.getEntity().getLocation().distance(location);
            if (distance >= this.teleportDistance) {
                if (distance >= 80.0) {
                    this.pet.getEntityNMS().teleportNMS(location);
                    entityPet.getNavigation().a((Entity)((CraftPlayer)this.owner).getHandle(), this.speed);
                    return;
                }
                if (this.owner.isOnGround()) {
                    this.pet.getEntityNMS().teleportNMS(location);
                    entityPet.getNavigation().a((Entity)((CraftPlayer)this.owner).getHandle(), this.speed);
                    return;
                }
            }
            if (distance > this.movingDistance) {
                entityPet.getNavigation().a(location.getX() + this.stayDistance, location.getY(), location.getZ() + this.stayDistance, this.speed);
                entityPet.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(this.speed);
            }
            else {
                entityPet.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.0);
            }
        }
    }
}
