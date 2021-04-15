

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.registry;

import net.minecraft.server.v1_11_R1.Entity;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityZombieVillagerPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityZombieHorsePet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityZombiePet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityWolfPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityWitherSkeletonPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityWitherPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityWitchPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityVindicatorPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityVillagerPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityVexPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityStrayPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntitySquidPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntitySpiderPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntitySnowmanPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntitySlimePet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntitySkeletonHorsePet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntitySkeletonPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntitySilverfishPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntitySheepPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityRabbitPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityPolarBearPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityPigmanPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityPigPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityOcelotPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityMushroomCowPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityMulePet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityMagmaCubePet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityLlamaPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityIronGolemPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityHuskPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityHorsePet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityEvokerPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityEndermitePet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityEndermanPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityDonkeyPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityCreeperPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityCowPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityChickenPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityCaveSpiderPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityBlazePet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types.EntityBatPet;
import net.minecraft.server.v1_11_R1.EntityLiving;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;

public enum PetRegistry
{
    BAT(NMSUtils.Type.BAT, GEntity.BAT, (Class<? extends EntityLiving>)EntityBatPet.class), 
    BLAZE(NMSUtils.Type.BLAZE, GEntity.BLAZE, (Class<? extends EntityLiving>)EntityBlazePet.class), 
    CAVE_SPIDER(NMSUtils.Type.CAVE_SPIDER, GEntity.CAVE_SPIDER, (Class<? extends EntityLiving>)EntityCaveSpiderPet.class), 
    CHICKEN(NMSUtils.Type.CHICKEN, GEntity.CHICKEN, (Class<? extends EntityLiving>)EntityChickenPet.class), 
    COW(NMSUtils.Type.COW, GEntity.COW, (Class<? extends EntityLiving>)EntityCowPet.class), 
    CREEPER(NMSUtils.Type.CREEPER, GEntity.CREEPER, (Class<? extends EntityLiving>)EntityCreeperPet.class), 
    DONKEY(NMSUtils.Type.DONKEY, GEntity.DONKEY, (Class<? extends EntityLiving>)EntityDonkeyPet.class), 
    ENDERMAN(NMSUtils.Type.ENDERMAN, GEntity.ENDERMAN, (Class<? extends EntityLiving>)EntityEndermanPet.class), 
    ENDERMITE(NMSUtils.Type.ENDERMITE, GEntity.ENDERMITE, (Class<? extends EntityLiving>)EntityEndermitePet.class), 
    EVOKER(NMSUtils.Type.EVOKER, GEntity.EVOKER, (Class<? extends EntityLiving>)EntityEvokerPet.class), 
    HORSE(NMSUtils.Type.HORSE, GEntity.HORSE, (Class<? extends EntityLiving>)EntityHorsePet.class), 
    HUSK(NMSUtils.Type.HUSK, GEntity.HUSK, (Class<? extends EntityLiving>)EntityHuskPet.class), 
    IRON_GOLEM(NMSUtils.Type.IRON_GOLEM, GEntity.IRON_GOLEM, (Class<? extends EntityLiving>)EntityIronGolemPet.class), 
    LLAMA(NMSUtils.Type.LLAMA, GEntity.LLAMA, (Class<? extends EntityLiving>)EntityLlamaPet.class), 
    MAGMA_CUBE(NMSUtils.Type.MAGMACUBE, GEntity.MAGMA_CUBE, (Class<? extends EntityLiving>)EntityMagmaCubePet.class), 
    MULE(NMSUtils.Type.MULE, GEntity.MULE, (Class<? extends EntityLiving>)EntityMulePet.class), 
    MUSHROOM_COW(NMSUtils.Type.MUSHROOM_COW, GEntity.MUSHROOM_COW, (Class<? extends EntityLiving>)EntityMushroomCowPet.class), 
    OCELOT(NMSUtils.Type.OCELOT, GEntity.OCELOT, (Class<? extends EntityLiving>)EntityOcelotPet.class), 
    PIG(NMSUtils.Type.PIG, GEntity.PIG, (Class<? extends EntityLiving>)EntityPigPet.class), 
    PIGMAN(NMSUtils.Type.ZOMBIE_PIGMAN, GEntity.PIG_ZOMBIE, (Class<? extends EntityLiving>)EntityPigmanPet.class), 
    POLAR_BEAR(NMSUtils.Type.POLARBEAR, GEntity.POLAR_BEAR, (Class<? extends EntityLiving>)EntityPolarBearPet.class), 
    RABBIT(NMSUtils.Type.RABBIT, GEntity.RABBIT, (Class<? extends EntityLiving>)EntityRabbitPet.class), 
    SHEEP(NMSUtils.Type.SHEEP, GEntity.SHEEP, (Class<? extends EntityLiving>)EntitySheepPet.class), 
    SILVERFISH(NMSUtils.Type.SILVERFISH, GEntity.SILVERFISH, (Class<? extends EntityLiving>)EntitySilverfishPet.class), 
    SKELETON(NMSUtils.Type.SKELETON, GEntity.SKELETON, (Class<? extends EntityLiving>)EntitySkeletonPet.class), 
    SKELETON_HORSE(NMSUtils.Type.SKELETON_HORSE, GEntity.SKELETON_HORSE, (Class<? extends EntityLiving>)EntitySkeletonHorsePet.class), 
    SLIME(NMSUtils.Type.SLIME, GEntity.SLIME, (Class<? extends EntityLiving>)EntitySlimePet.class), 
    SNOWMAN(NMSUtils.Type.SNOWMAN, GEntity.SNOWMAN, (Class<? extends EntityLiving>)EntitySnowmanPet.class), 
    SPIDER(NMSUtils.Type.SPIDER, GEntity.SPIDER, (Class<? extends EntityLiving>)EntitySpiderPet.class), 
    SQUID(NMSUtils.Type.SQUID, GEntity.SQUID, (Class<? extends EntityLiving>)EntitySquidPet.class), 
    STRAY(NMSUtils.Type.STRAY, GEntity.STRAY, (Class<? extends EntityLiving>)EntityStrayPet.class), 
    VEX(NMSUtils.Type.VEX, GEntity.VEX, (Class<? extends EntityLiving>)EntityVexPet.class), 
    VILLAGER(NMSUtils.Type.VILLAGER, GEntity.VILLAGER, (Class<? extends EntityLiving>)EntityVillagerPet.class), 
    VINDICATOR(NMSUtils.Type.VINDICATOR, GEntity.VINDICATOR, (Class<? extends EntityLiving>)EntityVindicatorPet.class), 
    WITCH(NMSUtils.Type.WITCH, GEntity.WITCH, (Class<? extends EntityLiving>)EntityWitchPet.class), 
    WITHER(NMSUtils.Type.WITHER, GEntity.WITHER, (Class<? extends EntityLiving>)EntityWitherPet.class), 
    WITHER_SKELETON(NMSUtils.Type.WITHER_SKELETON, GEntity.WITHER_SKELETON, (Class<? extends EntityLiving>)EntityWitherSkeletonPet.class), 
    WOLF(NMSUtils.Type.WOLF, GEntity.WOLF, (Class<? extends EntityLiving>)EntityWolfPet.class), 
    ZOMBIE(NMSUtils.Type.ZOMBIE, GEntity.ZOMBIE, (Class<? extends EntityLiving>)EntityZombiePet.class), 
    ZOMBIE_HORSE(NMSUtils.Type.ZOMBIE_HORSE, GEntity.ZOMBIE_HORSE, (Class<? extends EntityLiving>)EntityZombieHorsePet.class), 
    ZOMBIE_VILLAGER(NMSUtils.Type.ZOMBIE_VILLAGER, GEntity.ZOMBIE_VILLAGER, (Class<? extends EntityLiving>)EntityZombieVillagerPet.class);
    
    private NMSUtils.Type type;
    private GEntity entity;
    private Class<? extends EntityLiving> pet;
    
    private PetRegistry(final NMSUtils.Type type, final GEntity entity, final Class<? extends EntityLiving> pet) {
        this.type = type;
        this.entity = entity;
        this.pet = pet;
    }
    
    public GEntity getGEntity() {
        return this.entity;
    }
    
    public Class<? extends EntityLiving> getPet() {
        return this.pet;
    }
    
    public void registerPet() {
        NMSUtils.registerEntity("GPet_" + this.entity.name().toLowerCase(), this.type, (Class<? extends Entity>)this.pet, false);
    }
}
