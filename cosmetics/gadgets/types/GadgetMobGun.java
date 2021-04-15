

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import java.util.Iterator;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.FireworkUtils;
import java.util.Arrays;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.entity.Horse;
import org.bukkit.DyeColor;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Pig;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.entity.Item;
import java.util.ArrayList;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.entity.EntityType;

public class GadgetMobGun extends Gadget
{
    private static EntityType[] types;
    private static EnumMaterial[] type_drops;
    public static String[] name;
    private static String[] displayName;
    private static String actionbarMessage;
    private int type;
    private Entity entity;
    private boolean activated;
    private ArrayList<Item> items;
    
    static {
        GadgetMobGun.types = new EntityType[] { EntityType.PIG, EntityType.VILLAGER, EntityType.OCELOT, EntityType.COW, EntityType.CREEPER, EntityType.SPIDER, EntityType.SQUID, EntityType.CHICKEN, EntityType.SHEEP, EntityType.WOLF, EntityType.WITCH, EntityType.BLAZE, EntityType.HORSE, EntityType.ZOMBIE, EntityType.SKELETON, EntityType.MUSHROOM_COW, EntityType.CAVE_SPIDER, VersionManager.is1_16OrAbove() ? EntityType.ZOMBIFIED_PIGLIN : EntityType.valueOf("PIG_ZOMBIE") };
        GadgetMobGun.type_drops = new EnumMaterial[] { EnumMaterial.COOKED_PORKCHOP, EnumMaterial.EMERALD, EnumMaterial.COOKED_COD, EnumMaterial.COOKED_BEEF, EnumMaterial.GUNPOWDER, EnumMaterial.STRING, EnumMaterial.INK_SAC, EnumMaterial.FEATHER, EnumMaterial.WHITE_WOOL, EnumMaterial.BONE, EnumMaterial.SPLASH_POTION, EnumMaterial.BLAZE_ROD, EnumMaterial.APPLE, EnumMaterial.ROTTEN_FLESH, EnumMaterial.ARROW, EnumMaterial.MUSHROOM_STEW, EnumMaterial.FIRE_CHARGE, EnumMaterial.GOLD_INGOT };
        GadgetMobGun.name = new String[] { "Pig", "Villager", "Ocelot", "Cow", "Creeper", "Spider", "Squid", "Chicken", "Sheep", "Wolf", "Witch", "Blaze", "Horse", "Zombie", "Skeleton", "MushroomCow", "CaveSpider", "PigZombie" };
        GadgetMobGun.displayName = new String[] { "Pig", "Villager", "Ocelot", "Cow", "Creeper", "Spider", "Squid", "Chicken", "Sheep", "Wolf", "Witch", "Blaze", "Horse", "Zombie", "Skeleton", "MushroomCow", "CaveSpider", "PigZombie" };
        GadgetMobGun.actionbarMessage = FileManager.getGadgetsFile().getString("Gadgets.Projectile.Types.MobGun.Actionbar-Message");
    }
    
    public GadgetMobGun(final UUID uuid) {
        super(uuid, GadgetType.MOB_GUN);
        this.type = 0;
        this.entity = null;
        this.activated = false;
        this.items = new ArrayList<Item>();
        int n = 0;
        String[] name;
        for (int length = (name = GadgetMobGun.name).length, i = 0; i < length; ++i) {
            final String s = name[i];
            if (FileManager.getGadgetsFile().get("Gadgets.Projectile.Types.MobGun.Mob-Name." + s) != null) {
                GadgetMobGun.displayName[n++] = ChatUtil.stripColor(FileManager.getGadgetsFile().getString("Gadgets.Projectile.Types.MobGun.Mob-Name." + s));
            }
        }
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        final int type = this.type;
        CookieGadgets.setBypassCreatureSpawn(true);
        final Entity spawnEntity = this.getPlayer().getWorld().spawnEntity(this.getPlayer().getEyeLocation().add(0.0, -0.5, 0.0), GadgetMobGun.types[type]);
        if (GadgetMobGun.types[type] == EntityType.PIG) {
            ((Pig)spawnEntity).setSaddle(true);
        }
        else if (GadgetMobGun.types[type] == EntityType.VILLAGER) {
            ((Villager)spawnEntity).setProfession(Villager.Profession.BUTCHER);
        }
        else if (GadgetMobGun.types[type] == EntityType.OCELOT) {
            if (!VersionManager.is1_14OrAbove()) {
                ((Ocelot)spawnEntity).setCatType(Ocelot.Type.WILD_OCELOT);
            }
        }
        else if (GadgetMobGun.types[type] == EntityType.SHEEP) {
            ((Sheep)spawnEntity).setColor(DyeColor.WHITE);
        }
        else if (GadgetMobGun.types[type] == EntityType.HORSE) {
            if (!VersionManager.is1_11OrAbove()) {
                ((Horse)spawnEntity).setVariant(Horse.Variant.HORSE);
            }
            ((Horse)spawnEntity).setColor(Horse.Color.BROWN);
        }
        spawnEntity.setPassenger((Entity)null);
        spawnEntity.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        spawnEntity.setVelocity(new Vector(spawnEntity.getLocation().getDirection().getX() / 2.0, 0.0, spawnEntity.getLocation().getDirection().getZ() / 2.0));
        CookieGadgets.getNMSManager().clearPathfinders(spawnEntity);
        this.entity = spawnEntity;
        CookieGadgets.setBypassCreatureSpawn(false);
        this.activated = true;
        new BukkitRunnable() {
            public void run() {
                if (!GadgetMobGun.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetMobGun.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetMobGun.this.getPlayer()).getCurrentGadget().getType() != GadgetMobGun.this.getType()) {
                    GadgetMobGun.this.onClear();
                    this.cancel();
                    return;
                }
                FireworkUtils.displayFirework(GadgetMobGun.this.entity.getLocation(), FireworkEffect.Type.BURST, false, false, Arrays.asList(Color.RED), Arrays.asList(Color.RED));
                for (int i = 0; i <= 25; ++i) {
                    final Item dropItem = GadgetMobGun.this.getPlayer().getWorld().dropItem(GadgetMobGun.this.entity.getLocation(), ItemUtils.item(UUID.randomUUID().toString(), GadgetMobGun.type_drops[type], 0));
                    dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    dropItem.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) / 1.7, 0.4, (CookieGadgets.random().nextDouble() - 0.5) / 1.7));
                    dropItem.setPickupDelay(Integer.MAX_VALUE);
                    GadgetMobGun.this.items.add(dropItem);
                }
                if (GadgetMobGun.this.entity != null) {
                    GadgetMobGun.this.entity.remove();
                }
                GadgetMobGun.access$3(GadgetMobGun.this, null);
                new BukkitRunnable() {
                    public void run() {
                        GadgetMobGun.this.clearAll();
                    }
                }.runTaskLater((Plugin)CookieGadgets.getInstance(), 40L);
            }
        }.runTaskLater((Plugin)CookieGadgets.getInstance(), 80L);
    }
    
    @Override
    public void onUpdate() {
        Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
            if (this.activated && this.entity != null && this.entity.isValid() && !this.entity.isDead()) {
                try {
                    if (this.entity.getFireTicks() > 0) {
                        this.entity.setFireTicks(0);
                    }
                }
                catch (NullPointerException ex) {}
                this.entity.setVelocity(new Vector(this.entity.getLocation().getDirection().getX() / 1.7, -0.07, this.entity.getLocation().getDirection().getZ() / 1.7));
                ParticleEffect.REDSTONE.displayColor(this.entity.getLocation(), CookieGadgets.random().nextInt(255), CookieGadgets.random().nextInt(255), CookieGadgets.random().nextInt(255));
            }
        });
    }
    
    @Override
    public void onClear() {
        HandlerList.unregisterAll((Listener)this);
        this.clearAll();
        this.type = 0;
    }
    
    private void clearAll() {
        final Iterator<Item> iterator = this.items.iterator();
        while (iterator.hasNext()) {
            iterator.next().remove();
        }
        this.items.clear();
        if (this.entity != null) {
            this.entity.remove();
        }
        this.entity = null;
        this.activated = false;
    }
    
    @EventHandler
    public void onPlayerChangingMobType(final PlayerInteractEvent playerInteractEvent) {
        if (playerInteractEvent.getAction() != Action.LEFT_CLICK_AIR && playerInteractEvent.getAction() != Action.LEFT_CLICK_BLOCK) {
            return;
        }
        if (playerInteractEvent.getPlayer() == this.getPlayer() && this.getPlayer().isOnline() && CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() != null && CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() == this.getType() && this.getPlayer().getItemInHand().hasItemMeta() && this.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && this.getPlayer().getItemInHand().getItemMeta().getDisplayName().startsWith(ChatUtil.format(this.getType().getDisplayName())) && this.getPlayer().getItemInHand().getType() == this.getType().getMaterial().getEnumMaterial().getType()) {
            if (this.type < 17) {
                ++this.type;
            }
            else if (this.type >= 17) {
                this.type = 0;
            }
            CookieGadgets.getPlayerManager(playerInteractEvent.getPlayer()).sendActionbarMessage(ChatUtil.format(GadgetMobGun.actionbarMessage.replace("{GADGET}", this.getType().getDisplayName()).replace("{MOB}", GadgetMobGun.displayName[this.type])));
            playerInteractEvent.setCancelled(true);
        }
    }
    
    static /* synthetic */ void access$3(final GadgetMobGun gadgetMobGun, final Entity entity) {
        gadgetMobGun.entity = entity;
    }
}
