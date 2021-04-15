

package ws.billy.CookieGadgets.cosmetics.pets;

import java.util.Collection;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityZombieVillager;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityZombieHorse;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityZombie;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityWolf;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityWitherSkeleton;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityWither;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityWitch;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityVindicator;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityVillager;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityVex;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityTurtle;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityStray;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntitySquid;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntitySpider;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntitySnowman;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntitySlime;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntitySkeletonHorse;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntitySkeleton;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntitySilverfish;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntitySheep;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityRabbit;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityPolarBear;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityPigman;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityPig;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityPanda;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityOcelot;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityMule;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityMushroomCow;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityMagmaCube;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityLlama;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityIronGolem;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityIllusioner;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityHusk;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityHorse;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityEvoker;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityEndermite;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityEnderman;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityDonkey;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityCreeper;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityCow;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityChicken;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityCaveSpider;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityCat;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityBlaze;
import ws.billy.CookieGadgets.cosmetics.pets.types.EntityBat;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import java.util.LinkedHashMap;

public class TypeManager
{
    private static LinkedHashMap<GEntity, EntityPet> types;
    
    static {
        TypeManager.types = new LinkedHashMap<GEntity, EntityPet>();
    }
    
    public static void initializePets() {
        register(new EntityBat());
        register(new EntityBlaze());
        register(new EntityCat());
        register(new EntityCaveSpider());
        register(new EntityChicken());
        register(new EntityCow());
        register(new EntityCreeper());
        register(new EntityDonkey());
        register(new EntityEnderman());
        register(new EntityEndermite());
        register(new EntityEvoker());
        register(new EntityHorse());
        register(new EntityHusk());
        register(new EntityIllusioner());
        register(new EntityIronGolem());
        register(new EntityLlama());
        register(new EntityMagmaCube());
        register(new EntityMushroomCow());
        register(new EntityMule());
        register(new EntityOcelot());
        register(new EntityPanda());
        register(new EntityPig());
        register(new EntityPigman());
        register(new EntityPolarBear());
        register(new EntityRabbit());
        register(new EntitySheep());
        register(new EntitySilverfish());
        register(new EntitySkeleton());
        register(new EntitySkeletonHorse());
        register(new EntitySlime());
        register(new EntitySnowman());
        register(new EntitySpider());
        register(new EntitySquid());
        register(new EntityStray());
        register(new EntityTurtle());
        register(new EntityVex());
        register(new EntityVillager());
        register(new EntityVindicator());
        register(new EntityWitch());
        register(new EntityWither());
        register(new EntityWitherSkeleton());
        register(new EntityWolf());
        register(new EntityZombie());
        register(new EntityZombieHorse());
        register(new EntityZombieVillager());
    }
    
    public static void unLoadRegisteredPets() {
        if (TypeManager.types != null) {
            TypeManager.types.clear();
        }
        TypeManager.types = null;
    }
    
    public static EntityPet getType(final GEntity gEntity) {
        if (VersionManager.is1_11OrAbove()) {
            return TypeManager.types.getOrDefault(gEntity, null);
        }
        if (gEntity == GEntity.DONKEY || gEntity == GEntity.MULE || gEntity == GEntity.SKELETON_HORSE || gEntity == GEntity.ZOMBIE_HORSE) {
            return TypeManager.types.get(GEntity.HORSE);
        }
        if (gEntity == GEntity.HUSK || gEntity == GEntity.ZOMBIE_VILLAGER) {
            return TypeManager.types.get(GEntity.ZOMBIE);
        }
        if (gEntity == GEntity.STRAY || gEntity == GEntity.WITHER_SKELETON) {
            return TypeManager.types.get(GEntity.SKELETON);
        }
        return TypeManager.types.getOrDefault(gEntity, null);
    }
    
    public static Collection<EntityPet> getTypes() {
        return TypeManager.types.values();
    }
    
    private static void register(final EntityPet value) {
        if (!value.isSupported()) {
            return;
        }
        TypeManager.types.put(value.getGEntity(), value);
    }
}
