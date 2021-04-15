

package ws.billy.CookieGadgets.cosmetics.pets;

import java.lang.reflect.Field;
import com.mojang.authlib.properties.Property;
import java.util.Base64;
import com.mojang.authlib.GameProfile;
import org.bukkit.inventory.meta.SkullMeta;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.Color;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityPandaPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWitherPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityLlamaPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityZombiePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityZombieVillagerPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GProfession;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityVillagerPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySkeletonPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.ISizable;
import ws.billy.CookieGadgets.utils.GDyeColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySheepPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GRabbitType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityRabbitPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GCatType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityCatPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GOcelotType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityOcelotPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IHorseAbstractPet;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseMarking;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHorsePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityCreeperPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IAgeablePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.ITameable;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntityType;
import ws.billy.CookieGadgets.api.event.pets.PetHatEvent;
import ws.billy.CookieGadgets.utils.EntityUtils;
import ws.billy.CookieGadgets.api.event.pets.PetRideEvent;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.Event;
import org.bukkit.Location;
import ws.billy.CookieGadgets.api.event.pets.PetPreSpawnEvent;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import java.util.UUID;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;

public class Pet implements IPet
{
    private Player owner;
    private PetType type;
    private PlayerManager pManager;
    private IEntityPet entity;
    private boolean isHat;
    
    public Pet(final UUID uuid, final PetType type) {
        if (!type.isEnabled()) {
            return;
        }
        if (uuid == null) {
            return;
        }
        this.owner = Bukkit.getPlayer(uuid);
        this.type = type;
        this.pManager = CookieGadgets.getPlayerManager(this.getOwner());
        if (PermissionUtils.noPermission(this.getOwner(), type.getPermission(), EnumPermission.PETS.getPermission(), true)) {
            this.pManager.unequipPet();
            return;
        }
        if (PermissionUtils.noPermission(this.getOwner(), EnumPermission.SUMMON_PET.getPermission(), false)) {
            this.getOwner().sendMessage(MessageType.NO_PERMISSION_TO_SUMMON_PET.getFormatMessage());
            this.pManager.unequipPet();
            return;
        }
        if (this.pManager.getCurrentPet() != null) {
            this.pManager.removePet();
        }
        Bukkit.getServer().getPluginManager().callEvent((Event)new PetPreSpawnEvent(this.owner, null, type));
        if (!WorldUtils.isWorldEnabled(this.owner, true)) {
            return;
        }
        CookieGadgets.setBypassCreatureSpawn(true);
        this.pManager.addPetDataIfAbsent(type);
        Location location = this.owner.getLocation().clone().add(0.8, 0.25, 0.8);
        if (!location.getBlock().isEmpty()) {
            location = this.owner.getLocation();
        }
        final IEntityPet spawnEntityPet = CookieGadgets.getNMSManager().getSpawnUtils().spawnEntityPet(location, this, type.getEntityPet().getEntityClass());
        spawnEntityPet.getEntity().setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        spawnEntityPet.getEntity().setMetadata("CookieGadgets-Pet", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        spawnEntityPet.getEntity().setMetadata(this.getOwner().getName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        spawnEntityPet.getEntityNMS().setCustomNameNMS(ChatUtil.format(String.valueOf(CookieGadgets.getPetData().getLevel().replace("{LEVEL}", this.pManager.getPetData(type).getPetLevel().getFormattedLevel())) + "&r" + this.pManager.getPetName(type)));
        this.setEntityFlag(spawnEntityPet, type.getEntityType());
        CookieGadgets.getNMSManager().handlePathfinders(this.owner, spawnEntityPet.getEntity(), this.type.getEntityType().getMoveSpeed());
        this.entity = spawnEntityPet;
        CookieGadgets.setBypassCreatureSpawn(false);
        this.pManager.setCurrentPet(this);
    }
    
    @Override
    public Player getOwner() {
        return this.owner;
    }
    
    @Override
    public PetType getType() {
        return this.type;
    }
    
    @Override
    public GEntity getGEntity() {
        return this.type.getEntityPet().getGEntity();
    }
    
    @Override
    public IEntityPet getEntityPet() {
        return this.entity;
    }
    
    public Entity getPet() {
        return this.entity.getEntity();
    }
    
    public void clear() {
        CookieGadgets.getPlayerManager(this.getOwner()).setCurrentPet(null);
        this.removePet();
        this.owner = null;
        this.pManager = null;
        this.entity = null;
    }
    
    @Override
    public void removePet() {
        if (this.isHat()) {
            this.setAsHat(false);
        }
        if (this.getEntityPet().getEntityNMS() != null && !this.getEntityPet().getEntityNMS().isDeadNMS()) {
            this.getEntityPet().getEntityNMS().killEntityNMS();
        }
    }
    
    @Override
    public boolean isOwnerRiding() {
        return this.getPet().getPassenger() != null;
    }
    
    @Override
    public void setOwnerRidePet(final boolean b) {
        if (b) {
            if (this.isOwnerRiding()) {
                return;
            }
            if (this.isHat()) {
                this.setAsHat(false);
            }
            final PetRideEvent petRideEvent = new PetRideEvent(this, PetRideEvent.Type.MOUNT);
            Bukkit.getServer().getPluginManager().callEvent((Event)petRideEvent);
            if (!petRideEvent.isCancelled()) {
                this.getEntityPet().getEntityNMS().setOwnerRidePetNMS();
            }
        }
        else if (this.isOwnerRiding()) {
            final PetRideEvent petRideEvent2 = new PetRideEvent(this, PetRideEvent.Type.DISMOUNT);
            Bukkit.getServer().getPluginManager().callEvent((Event)petRideEvent2);
            if (!petRideEvent2.isCancelled()) {
                this.getEntityPet().getEntityNMS().removePassengerNMS();
            }
        }
    }
    
    @Override
    public boolean isHat() {
        if (this.getOwner().getPassenger() != null && EntityUtils.getEntityHandle(this.getOwner().getPassenger()) instanceof IEntityPet) {
            return this.isHat = true;
        }
        return this.isHat;
    }
    
    @Override
    public void setAsHat(final boolean b) {
        if (this.isOwnerRiding()) {
            this.setOwnerRidePet(false);
        }
        if (b) {
            if (this.isHat()) {
                return;
            }
            final PetHatEvent petHatEvent = new PetHatEvent(this, PetHatEvent.Type.SET);
            Bukkit.getServer().getPluginManager().callEvent((Event)petHatEvent);
            if (!petHatEvent.isCancelled()) {
                CookieGadgets.getNMSManager().clearPathfinders(this.getEntityPet().getEntity());
                this.getEntityPet().getEntityNMS().setAsHatNMS();
                this.isHat = b;
            }
        }
        else {
            if (!this.isHat()) {
                return;
            }
            final PetHatEvent petHatEvent2 = new PetHatEvent(this, PetHatEvent.Type.REMOVE);
            Bukkit.getServer().getPluginManager().callEvent((Event)petHatEvent2);
            if (!petHatEvent2.isCancelled()) {
                CookieGadgets.getNMSManager().handlePathfinders(this.getOwner(), this.getEntityPet().getEntity(), this.type.getEntityType().getMoveSpeed());
                this.getEntityPet().getEntityNMS().removeHatNMS();
                this.isHat = b;
            }
        }
    }
    
    @Override
    public void toggleRidePet() {
        this.setOwnerRidePet(!this.isOwnerRiding());
    }
    
    @Override
    public void toggleHat() {
        this.setAsHat(!this.isHat());
    }
    
    private void setEntityFlag(final IEntityPet entityPet, final GEntityType gEntityType) {
        if (entityPet instanceof ITameable) {
            ((ITameable)entityPet).setTamed(true);
        }
        if (entityPet instanceof IAgeablePet) {
            ((IAgeablePet)entityPet).setBaby(gEntityType.isBaby());
        }
        if (entityPet instanceof IEntityCreeperPet) {
            ((IEntityCreeperPet)entityPet).setPowered(gEntityType.getIsPowered());
        }
        if (entityPet instanceof IEntityHorsePet) {
            final IEntityHorsePet entityHorsePet = (IEntityHorsePet)entityPet;
            entityHorsePet.setColor((gEntityType.getHorseColor() == null) ? GHorseColor.BROWN : gEntityType.getHorseColor());
            entityHorsePet.setMarking(GHorseMarking.WHITEFIELD);
            if (!VersionManager.is1_11OrAbove()) {
                entityHorsePet.setType(gEntityType.getVariant());
            }
        }
        if (entityPet instanceof IHorseAbstractPet) {
            ((IHorseAbstractPet)entityPet).setSaddle(true);
        }
        if (entityPet instanceof IEntityOcelotPet) {
            ((IEntityOcelotPet)entityPet).setOcelotType((gEntityType.getOcelotType() == null) ? GOcelotType.BLACK_CAT : gEntityType.getOcelotType());
        }
        if (entityPet instanceof IEntityCatPet) {
            ((IEntityCatPet)entityPet).setCatType((gEntityType.getOcelotType() == null) ? GCatType.BLACK : GCatType.getById(gEntityType.getOcelotType().getId()));
        }
        if (entityPet instanceof IEntityRabbitPet) {
            ((IEntityRabbitPet)entityPet).setRabbitType((gEntityType.getRabbitType() == null) ? GRabbitType.BLACK : gEntityType.getRabbitType());
        }
        if (entityPet instanceof IEntitySheepPet) {
            final IEntitySheepPet entitySheepPet = (IEntitySheepPet)entityPet;
            if (gEntityType == GEntityType.RAINBOW_SHEEP) {
                entitySheepPet.setRainbow(true);
            }
            else {
                entitySheepPet.setColor((gEntityType.getDyeColor() == null) ? GDyeColor.WHITE : gEntityType.getDyeColor());
            }
        }
        if (entityPet instanceof ISizable) {
            ((ISizable)entityPet).setSize(gEntityType.getSize());
        }
        if (entityPet instanceof IEntitySkeletonPet && !VersionManager.is1_11OrAbove()) {
            ((IEntitySkeletonPet)entityPet).setSkeletonType(gEntityType.getSkeletonType());
        }
        if (entityPet instanceof IEntityVillagerPet) {
            ((IEntityVillagerPet)entityPet).setProfession((gEntityType.getProfession() == null) ? GProfession.NORMAL : gEntityType.getProfession());
        }
        if (entityPet instanceof IEntityZombieVillagerPet) {
            ((IEntityZombieVillagerPet)entityPet).setProfession(GProfession.BLACKSMITH);
        }
        if (entityPet.getGEntity() == GEntity.ZOMBIE_VILLAGER && entityPet instanceof IEntityZombiePet) {
            ((IEntityZombiePet)entityPet).setVillager(true);
        }
        if (entityPet instanceof IEntityLlamaPet) {
            final IEntityLlamaPet entityLlamaPet = (IEntityLlamaPet)entityPet;
            entityLlamaPet.setLlamaColor(gEntityType.getLlamaColor());
            entityLlamaPet.setHasChest(false);
        }
        if (entityPet instanceof IEntityWitherPet) {
            ((IEntityWitherPet)entityPet).setSmall(true);
        }
        if (entityPet instanceof IEntityPandaPet) {
            ((IEntityPandaPet)entityPet).setGene(gEntityType.getPandaGene());
        }
        if (gEntityType != GEntityType.RED_LITTLE_HELPER) {
            if (gEntityType != GEntityType.GREEN_LITTLE_HELPER) {
                return;
            }
        }
        try {
            if (VersionManager.is1_9OrAbove()) {
                entityPet.getEntityNMS().setSilentNMS(true);
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        final Color color = (gEntityType == GEntityType.RED_LITTLE_HELPER) ? Color.RED : Color.LIME;
        final ItemStack colorLeatherArmor = colorLeatherArmor(new ItemStack(Material.LEATHER_CHESTPLATE), color);
        final ItemStack colorLeatherArmor2 = colorLeatherArmor(new ItemStack(Material.LEATHER_LEGGINGS), color);
        final ItemStack colorLeatherArmor3 = colorLeatherArmor(new ItemStack(Material.LEATHER_BOOTS), color);
        entityPet.getEntityNMS().setEquipmentNMS(EnumArmorType.HELMET, skull("d88adb221c6ea4f4e8adba76ef84c2811a60844b4313fc923d1359bce51714ca"));
        entityPet.getEntityNMS().setEquipmentNMS(EnumArmorType.CHESTPLATE, colorLeatherArmor);
        entityPet.getEntityNMS().setEquipmentNMS(EnumArmorType.LEGGINGS, colorLeatherArmor2);
        entityPet.getEntityNMS().setEquipmentNMS(EnumArmorType.BOOTS, colorLeatherArmor3);
    }
    
    private static ItemStack colorLeatherArmor(final ItemStack itemStack, final Color color) {
        final LeatherArmorMeta itemMeta = (LeatherArmorMeta)itemStack.getItemMeta();
        itemMeta.setColor(color);
        itemStack.setItemMeta((ItemMeta)itemMeta);
        return itemStack;
    }
    
    private static ItemStack skull(String string) {
        string = "http://textures.minecraft.net/texture/" + string;
        final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), 1, (short)EnumMaterial.PLAYER_HEAD.getData());
        final SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        if (string.isEmpty()) {
            return itemStack;
        }
        final GameProfile value = new GameProfile(UUID.randomUUID(), (String)null);
        value.getProperties().put((Object)"textures", (Object)new Property("textures", new String(Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", string).getBytes()))));
        try {
            final Field declaredField = skullMeta.getClass().getDeclaredField("profile");
            declaredField.setAccessible(true);
            declaredField.set(skullMeta, value);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            final Throwable t;
            t.printStackTrace();
        }
        itemStack.setItemMeta((ItemMeta)skullMeta);
        return itemStack;
    }
}
