

package ws.billy.CookieGadgets.listeners;

import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import ws.billy.CookieGadgets.cosmetics.pets.Pet;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.utils.EntityUtils;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.Material;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import ws.billy.CookieGadgets.cosmetics.suits.types.SuitPlumber;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;

public class EntityListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getDamager().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            entityDamageByEntityEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(final EntityDamageEvent entityDamageEvent) {
        if (entityDamageEvent.getEntity().hasMetadata("Tetherball-Chicken")) {
            entityDamageEvent.setDamage(0.0);
            return;
        }
        if (entityDamageEvent.getEntity().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            entityDamageEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChunkUnload(final ChunkUnloadEvent chunkUnloadEvent) {
        Entity[] entities;
        for (int length = (entities = chunkUnloadEvent.getChunk().getEntities()).length, i = 0; i < length; ++i) {
            final Entity entity = entities[i];
            if (!entity.isDead() && entity instanceof IEntityPet) {
                entity.remove();
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(final EntityDamageEvent entityDamageEvent) {
        if (entityDamageEvent.getEntity() instanceof Player) {
            final Player player = (Player)entityDamageEvent.getEntity();
            if (!CookieGadgets.getCookieGadgetsData().isMobDisguiseDamageEnabled() && CookieGadgets.getPlayerManager(player).getEquippedMorph() != null && CookieGadgets.getGDisguise().isDisguised(player)) {
                entityDamageEvent.setCancelled(true);
            }
            if ((entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.FIRE || entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) && CookieGadgets.getPlayerManager(player).isFireDamageDisabled()) {
                entityDamageEvent.setCancelled(true);
            }
        }
        if (entityDamageEvent.getCause() != EntityDamageEvent.DamageCause.FIRE) {
            if (entityDamageEvent.getCause() != EntityDamageEvent.DamageCause.FIRE_TICK) {
                return;
            }
        }
        try {
            if (SuitPlumber.disableFireDamage.contains(entityDamageEvent.getEntity())) {
                entityDamageEvent.setCancelled(true);
            }
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            entityDamageEvent.setCancelled(false);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityExplode(final EntityExplodeEvent entityExplodeEvent) {
        if (entityExplodeEvent.getEntity().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            entityExplodeEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityTeleport(final EntityPortalEvent entityPortalEvent) {
        if (entityPortalEvent.getEntity().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            entityPortalEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockForm(final EntityBlockFormEvent entityBlockFormEvent) {
        if (entityBlockFormEvent.getEntity().hasMetadata(CookieGadgets.getInstance().getPluginName()) && entityBlockFormEvent.getNewState().getType().equals((Object)Material.SNOW)) {
            entityBlockFormEvent.setCancelled(false);
            entityBlockFormEvent.getNewState().setType(Material.AIR);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityInteract(final EntityInteractEvent entityInteractEvent) {
        if (entityInteractEvent.getEntity().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            entityInteractEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(final PlayerInteractEvent playerInteractEvent) {
        if (playerInteractEvent.getClickedBlock() == null) {
            return;
        }
        if (playerInteractEvent.getClickedBlock().getLocation().add(0.0, 1.0, 0.0).getBlock().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            playerInteractEvent.setCancelled(true);
        }
        if (playerInteractEvent.getClickedBlock().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            playerInteractEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractEntity(final PlayerInteractEntityEvent playerInteractEntityEvent) {
        if (playerInteractEntityEvent.getRightClicked().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            playerInteractEntityEvent.setCancelled(true);
        }
        if (playerInteractEntityEvent.getRightClicked().getType() == EntityType.ITEM_FRAME && (CookieGadgets.getNMSManager().hasNBTTag(playerInteractEntityEvent.getPlayer().getItemInHand(), "Cosmetics") || CookieGadgets.getNMSManager().hasNBTTag(playerInteractEntityEvent.getPlayer().getItemInHand(), "Menu_Selector"))) {
            playerInteractEntityEvent.setCancelled(true);
        }
        if (playerInteractEntityEvent.getRightClicked().hasMetadata("CookieGadgets-Pet")) {
            try {
                if (playerInteractEntityEvent.getHand() == EquipmentSlot.OFF_HAND) {
                    return;
                }
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (EntityUtils.getEntityHandle(playerInteractEntityEvent.getRightClicked()) instanceof IEntityPet) {
                if (playerInteractEntityEvent.getRightClicked().hasMetadata(playerInteractEntityEvent.getPlayer().getName())) {
                    if (PermissionUtils.noPermission(playerInteractEntityEvent.getPlayer(), EnumPermission.RIDE_PET.getPermission(), MessageType.NO_PERMISSION_TO_RIDE_PET.getFormatMessage())) {
                        playerInteractEntityEvent.setCancelled(true);
                        return;
                    }
                    final Pet currentPet = CookieGadgets.getPlayerManager(playerInteractEntityEvent.getPlayer()).getCurrentPet();
                    if (currentPet != null) {
                        currentPet.setOwnerRidePet(true);
                    }
                }
                else {
                    playerInteractEntityEvent.getPlayer().sendMessage(MessageType.NOT_ALLOWED_TO_RIDE_OTHER_PET.getFormatMessage());
                    playerInteractEntityEvent.setCancelled(true);
                }
                playerInteractEntityEvent.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractAtEntity(final PlayerInteractAtEntityEvent playerInteractAtEntityEvent) {
        if (playerInteractAtEntityEvent.getRightClicked().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            playerInteractAtEntityEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityCombust(final EntityCombustEvent entityCombustEvent) {
        if (entityCombustEvent.getEntity().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            entityCombustEvent.getEntity().setFireTicks(0);
            entityCombustEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreatureSpawn(final CreatureSpawnEvent creatureSpawnEvent) {
        if (creatureSpawnEvent.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM && CookieGadgets.isBypassCreatureSpawn() && creatureSpawnEvent.isCancelled()) {
            creatureSpawnEvent.setCancelled(false);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(final BlockPlaceEvent blockPlaceEvent) {
        if (blockPlaceEvent.getBlock().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            blockPlaceEvent.getBlock().getState().update();
            blockPlaceEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(final BlockBreakEvent blockBreakEvent) {
        if (blockBreakEvent.getBlock().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            blockBreakEvent.getBlock().getState().update();
            blockBreakEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPotionThrow(final PotionSplashEvent potionSplashEvent) {
        if (potionSplashEvent.getPotion().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            potionSplashEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLaunchSnowball(final ProjectileLaunchEvent projectileLaunchEvent) {
        if (projectileLaunchEvent.getEntity().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            projectileLaunchEvent.setCancelled(true);
        }
    }
}
