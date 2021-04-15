

package ws.billy.CookieGadgets.nms.v1_8_R2.pets.registry;

import net.minecraft.server.v1_8_R2.EntityZombie;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityZombiePet;
import net.minecraft.server.v1_8_R2.EntityWolf;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityWolfPet;
import net.minecraft.server.v1_8_R2.EntityWitch;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityWitchPet;
import net.minecraft.server.v1_8_R2.EntityWither;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityWitherPet;
import net.minecraft.server.v1_8_R2.EntityVillager;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityVillagerPet;
import net.minecraft.server.v1_8_R2.EntitySquid;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntitySquidPet;
import net.minecraft.server.v1_8_R2.EntitySpider;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntitySpiderPet;
import net.minecraft.server.v1_8_R2.EntitySnowman;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntitySnowmanPet;
import net.minecraft.server.v1_8_R2.EntitySkeleton;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntitySkeletonPet;
import net.minecraft.server.v1_8_R2.EntitySlime;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntitySlimePet;
import net.minecraft.server.v1_8_R2.EntitySilverfish;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntitySilverfishPet;
import net.minecraft.server.v1_8_R2.EntitySheep;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntitySheepPet;
import net.minecraft.server.v1_8_R2.EntityRabbit;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityRabbitPet;
import net.minecraft.server.v1_8_R2.EntityPigZombie;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityPigmanPet;
import net.minecraft.server.v1_8_R2.EntityPig;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityPigPet;
import net.minecraft.server.v1_8_R2.EntityOcelot;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityOcelotPet;
import net.minecraft.server.v1_8_R2.EntityMushroomCow;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityMushroomCowPet;
import net.minecraft.server.v1_8_R2.EntityMagmaCube;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityMagmaCubePet;
import net.minecraft.server.v1_8_R2.EntityIronGolem;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityIronGolemPet;
import net.minecraft.server.v1_8_R2.EntityHorse;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityHorsePet;
import net.minecraft.server.v1_8_R2.EntityEndermite;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityEndermitePet;
import net.minecraft.server.v1_8_R2.EntityEnderman;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityEndermanPet;
import net.minecraft.server.v1_8_R2.EntityCreeper;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityCreeperPet;
import net.minecraft.server.v1_8_R2.EntityCow;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityCowPet;
import net.minecraft.server.v1_8_R2.EntityChicken;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityChickenPet;
import net.minecraft.server.v1_8_R2.EntityCaveSpider;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityCaveSpiderPet;
import net.minecraft.server.v1_8_R2.EntityBat;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityBatPet;
import net.minecraft.server.v1_8_R2.EntityBlaze;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types.EntityBlazePet;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.EntityPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import java.lang.reflect.Field;
import java.util.Map;
import net.minecraft.server.v1_8_R2.EntityTypes;

public class PetRegistry
{
    public static void registerPets() {
        List[] values;
        for (int length = (values = List.values()).length, i = 0; i < length; ++i) {
            final List list = values[i];
            if (list.getEntity().isVersionSupported()) {
                registerEntity("CookieGadgets-" + list.getEntity().name().toLowerCase(), list.getEntity().getId(), list.getPetClass());
            }
        }
    }
    
    public static void unregisterPets() {
        List[] values;
        for (int length = (values = List.values()).length, i = 0; i < length; ++i) {
            final List list = values[i];
            if (list.getEntity().isVersionSupported()) {
                unregisterEntity(list.getPetClass());
            }
        }
    }
    
    private static void registerEntity(final String s, final int i, final Class<?> clazz) {
        if (clazz != null) {
            try {
                Field[] declaredFields;
                for (int length = (declaredFields = EntityTypes.class.getDeclaredFields()).length, j = 0; j < length; ++j) {
                    final Field field = declaredFields[j];
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    final String name;
                    switch (name = field.getName()) {
                        case "d": {
                            ((Map)field.get(null)).put(clazz, s);
                            break;
                        }
                        case "f": {
                            ((Map)field.get(null)).put(clazz, i);
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private static void unregisterEntity(final Class<?> clazz) {
        if (clazz != null) {
            try {
                Field[] declaredFields;
                for (int length = (declaredFields = EntityTypes.class.getDeclaredFields()).length, i = 0; i < length; ++i) {
                    final Field field = declaredFields[i];
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    final String name;
                    switch (name = field.getName()) {
                        case "d": {
                            ((Map)field.get(null)).remove(clazz);
                            break;
                        }
                        case "f": {
                            ((Map)field.get(null)).remove(clazz);
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public enum List
    {
        BLAZE(GEntity.BLAZE, (Class<? extends EntityPet>)EntityBlazePet.class, (Class<?>)EntityBlaze.class), 
        BAT(GEntity.BAT, (Class<? extends EntityPet>)EntityBatPet.class, (Class<?>)EntityBat.class), 
        CAVE_SPIDER(GEntity.CAVE_SPIDER, (Class<? extends EntityPet>)EntityCaveSpiderPet.class, (Class<?>)EntityCaveSpider.class), 
        CHICKEN(GEntity.CHICKEN, (Class<? extends EntityPet>)EntityChickenPet.class, (Class<?>)EntityChicken.class), 
        COW(GEntity.COW, (Class<? extends EntityPet>)EntityCowPet.class, (Class<?>)EntityCow.class), 
        CREEPER(GEntity.CREEPER, (Class<? extends EntityPet>)EntityCreeperPet.class, (Class<?>)EntityCreeper.class), 
        ENDERMAN(GEntity.ENDERMAN, (Class<? extends EntityPet>)EntityEndermanPet.class, (Class<?>)EntityEnderman.class), 
        ENDERMITE(GEntity.ENDERMITE, (Class<? extends EntityPet>)EntityEndermitePet.class, (Class<?>)EntityEndermite.class), 
        HORSE(GEntity.HORSE, (Class<? extends EntityPet>)EntityHorsePet.class, (Class<?>)EntityHorse.class), 
        IRON_GOLEM(GEntity.IRON_GOLEM, (Class<? extends EntityPet>)EntityIronGolemPet.class, (Class<?>)EntityIronGolem.class), 
        MAGMA_CUBE(GEntity.MAGMA_CUBE, (Class<? extends EntityPet>)EntityMagmaCubePet.class, (Class<?>)EntityMagmaCube.class), 
        MUSHROOM_COW(GEntity.MUSHROOM_COW, (Class<? extends EntityPet>)EntityMushroomCowPet.class, (Class<?>)EntityMushroomCow.class), 
        OCELOT(GEntity.OCELOT, (Class<? extends EntityPet>)EntityOcelotPet.class, (Class<?>)EntityOcelot.class), 
        PIG(GEntity.PIG, (Class<? extends EntityPet>)EntityPigPet.class, (Class<?>)EntityPig.class), 
        PIG_ZOMBIE(GEntity.PIG_ZOMBIE, (Class<? extends EntityPet>)EntityPigmanPet.class, (Class<?>)EntityPigZombie.class), 
        RABBIT(GEntity.RABBIT, (Class<? extends EntityPet>)EntityRabbitPet.class, (Class<?>)EntityRabbit.class), 
        SHEEP(GEntity.SHEEP, (Class<? extends EntityPet>)EntitySheepPet.class, (Class<?>)EntitySheep.class), 
        SILVERFISH(GEntity.SILVERFISH, (Class<? extends EntityPet>)EntitySilverfishPet.class, (Class<?>)EntitySilverfish.class), 
        SLIME(GEntity.SLIME, (Class<? extends EntityPet>)EntitySlimePet.class, (Class<?>)EntitySlime.class), 
        SKELETON(GEntity.SKELETON, (Class<? extends EntityPet>)EntitySkeletonPet.class, (Class<?>)EntitySkeleton.class), 
        SNOWMAN(GEntity.SNOWMAN, (Class<? extends EntityPet>)EntitySnowmanPet.class, (Class<?>)EntitySnowman.class), 
        SPIDER(GEntity.SPIDER, (Class<? extends EntityPet>)EntitySpiderPet.class, (Class<?>)EntitySpider.class), 
        SQUID(GEntity.SQUID, (Class<? extends EntityPet>)EntitySquidPet.class, (Class<?>)EntitySquid.class), 
        VILLAGER(GEntity.VILLAGER, (Class<? extends EntityPet>)EntityVillagerPet.class, (Class<?>)EntityVillager.class), 
        WITHER(GEntity.WITHER, (Class<? extends EntityPet>)EntityWitherPet.class, (Class<?>)EntityWither.class), 
        WITCH(GEntity.WITCH, (Class<? extends EntityPet>)EntityWitchPet.class, (Class<?>)EntityWitch.class), 
        WOLF(GEntity.WOLF, (Class<? extends EntityPet>)EntityWolfPet.class, (Class<?>)EntityWolf.class), 
        ZOMBIE(GEntity.ZOMBIE, (Class<? extends EntityPet>)EntityZombiePet.class, (Class<?>)EntityZombie.class);
        
        private GEntity entity;
        private Class<? extends EntityPet> petClass;
        private Class<?> nmsClass;
        
        private List(final GEntity entity, final Class<? extends EntityPet> petClass, final Class<?> nmsClass) {
            if (entity.isVersionSupported()) {
                this.entity = entity;
                this.petClass = petClass;
                this.nmsClass = nmsClass;
            }
        }
        
        public GEntity getEntity() {
            return this.entity;
        }
        
        public Class<?> getPetClass() {
            return this.petClass;
        }
        
        public Class<?> getNMSClass() {
            return this.nmsClass;
        }
    }
}
