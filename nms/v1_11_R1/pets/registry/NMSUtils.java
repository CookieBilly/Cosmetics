

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.registry;

import net.minecraft.server.v1_11_R1.EntityInsentient;
import net.minecraft.server.v1_11_R1.EntityEnderCrystal;
import net.minecraft.server.v1_11_R1.EntityVillager;
import net.minecraft.server.v1_11_R1.EntityLlamaSpit;
import net.minecraft.server.v1_11_R1.EntityLlama;
import net.minecraft.server.v1_11_R1.EntityPolarBear;
import net.minecraft.server.v1_11_R1.EntityRabbit;
import net.minecraft.server.v1_11_R1.EntityHorse;
import net.minecraft.server.v1_11_R1.EntityIronGolem;
import net.minecraft.server.v1_11_R1.EntityOcelot;
import net.minecraft.server.v1_11_R1.EntitySnowman;
import net.minecraft.server.v1_11_R1.EntityMushroomCow;
import net.minecraft.server.v1_11_R1.EntityWolf;
import net.minecraft.server.v1_11_R1.EntitySquid;
import net.minecraft.server.v1_11_R1.EntityChicken;
import net.minecraft.server.v1_11_R1.EntityCow;
import net.minecraft.server.v1_11_R1.EntitySheep;
import net.minecraft.server.v1_11_R1.EntityPig;
import net.minecraft.server.v1_11_R1.EntityShulker;
import net.minecraft.server.v1_11_R1.EntityGuardian;
import net.minecraft.server.v1_11_R1.EntityEndermite;
import net.minecraft.server.v1_11_R1.EntityWitch;
import net.minecraft.server.v1_11_R1.EntityBat;
import net.minecraft.server.v1_11_R1.EntityWither;
import net.minecraft.server.v1_11_R1.EntityEnderDragon;
import net.minecraft.server.v1_11_R1.EntityMagmaCube;
import net.minecraft.server.v1_11_R1.EntityBlaze;
import net.minecraft.server.v1_11_R1.EntitySilverfish;
import net.minecraft.server.v1_11_R1.EntityCaveSpider;
import net.minecraft.server.v1_11_R1.EntityEnderman;
import net.minecraft.server.v1_11_R1.EntityPigZombie;
import net.minecraft.server.v1_11_R1.EntityGhast;
import net.minecraft.server.v1_11_R1.EntitySlime;
import net.minecraft.server.v1_11_R1.EntityZombie;
import net.minecraft.server.v1_11_R1.EntityGiantZombie;
import net.minecraft.server.v1_11_R1.EntitySpider;
import net.minecraft.server.v1_11_R1.EntitySkeleton;
import net.minecraft.server.v1_11_R1.EntityCreeper;
import net.minecraft.server.v1_11_R1.EntityMinecartMobSpawner;
import net.minecraft.server.v1_11_R1.EntityMinecartHopper;
import net.minecraft.server.v1_11_R1.EntityMinecartTNT;
import net.minecraft.server.v1_11_R1.EntityMinecartFurnace;
import net.minecraft.server.v1_11_R1.EntityMinecartChest;
import net.minecraft.server.v1_11_R1.EntityMinecartRideable;
import net.minecraft.server.v1_11_R1.EntityBoat;
import net.minecraft.server.v1_11_R1.EntityMinecartCommandBlock;
import net.minecraft.server.v1_11_R1.EntityVindicator;
import net.minecraft.server.v1_11_R1.EntityVex;
import net.minecraft.server.v1_11_R1.EntityEvoker;
import net.minecraft.server.v1_11_R1.EntityEvokerFangs;
import net.minecraft.server.v1_11_R1.EntityHorseMule;
import net.minecraft.server.v1_11_R1.EntityHorseDonkey;
import net.minecraft.server.v1_11_R1.EntityArmorStand;
import net.minecraft.server.v1_11_R1.EntityHorseZombie;
import net.minecraft.server.v1_11_R1.EntityHorseSkeleton;
import net.minecraft.server.v1_11_R1.EntityZombieVillager;
import net.minecraft.server.v1_11_R1.EntityDragonFireball;
import net.minecraft.server.v1_11_R1.EntityShulkerBullet;
import net.minecraft.server.v1_11_R1.EntitySpectralArrow;
import net.minecraft.server.v1_11_R1.EntityZombieHusk;
import net.minecraft.server.v1_11_R1.EntityFireworks;
import net.minecraft.server.v1_11_R1.EntityFallingBlock;
import net.minecraft.server.v1_11_R1.EntityTNTPrimed;
import net.minecraft.server.v1_11_R1.EntityWitherSkull;
import net.minecraft.server.v1_11_R1.EntityItemFrame;
import net.minecraft.server.v1_11_R1.EntityThrownExpBottle;
import net.minecraft.server.v1_11_R1.EntityPotion;
import net.minecraft.server.v1_11_R1.EntityEnderSignal;
import net.minecraft.server.v1_11_R1.EntityEnderPearl;
import net.minecraft.server.v1_11_R1.EntitySmallFireball;
import net.minecraft.server.v1_11_R1.EntityFireball;
import net.minecraft.server.v1_11_R1.EntitySnowball;
import net.minecraft.server.v1_11_R1.EntityArrow;
import net.minecraft.server.v1_11_R1.EntityPainting;
import net.minecraft.server.v1_11_R1.EntityLeash;
import net.minecraft.server.v1_11_R1.EntityEgg;
import net.minecraft.server.v1_11_R1.EntitySkeletonStray;
import net.minecraft.server.v1_11_R1.EntitySkeletonWither;
import net.minecraft.server.v1_11_R1.EntityGuardianElder;
import net.minecraft.server.v1_11_R1.EntityAreaEffectCloud;
import net.minecraft.server.v1_11_R1.EntityExperienceOrb;
import net.minecraft.server.v1_11_R1.EntityItem;
import net.minecraft.server.v1_11_R1.GenericAttributes;
import net.minecraft.server.v1_11_R1.IAttribute;
import net.minecraft.server.v1_11_R1.EntityTypes;
import net.minecraft.server.v1_11_R1.Entity;
import java.util.List;
import net.minecraft.server.v1_11_R1.MinecraftKey;
import net.minecraft.server.v1_11_R1.Item;
import java.util.Iterator;
import org.bukkit.Bukkit;
import net.minecraft.server.v1_11_R1.MinecraftServer;
import org.bukkit.craftbukkit.v1_11_R1.CraftServer;
import net.minecraft.server.v1_11_R1.BiomeBase;
import java.lang.reflect.Field;

public class NMSUtils
{
    private static final Field META_LIST_MONSTER;
    private static final Field META_LIST_CREATURE;
    private static final Field META_LIST_WATER_CREATURE;
    private static final Field META_LIST_AMBIENT;
    private static final BiomeBase[] BIOMES;
    private static final CraftServer CRAFTBUKKIT_SERVER;
    private static final MinecraftServer MINECRAFT_SERVER;
    
    static {
        final Class<BiomeBase> clazz = BiomeBase.class;
        Field declaredField = null;
        Field declaredField2 = null;
        Field declaredField3 = null;
        Field declaredField4 = null;
        try {
            declaredField = clazz.getDeclaredField("u");
            declaredField2 = clazz.getDeclaredField("v");
            declaredField3 = clazz.getDeclaredField("w");
            declaredField4 = clazz.getDeclaredField("x");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        META_LIST_MONSTER = declaredField;
        META_LIST_CREATURE = declaredField2;
        META_LIST_WATER_CREATURE = declaredField3;
        META_LIST_AMBIENT = declaredField4;
        CRAFTBUKKIT_SERVER = (CraftServer)Bukkit.getServer();
        MINECRAFT_SERVER = NMSUtils.CRAFTBUKKIT_SERVER.getServer();
        BIOMES = new BiomeBase[BiomeBase.j.a()];
        final Iterator iterator = BiomeBase.j.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            NMSUtils.BIOMES[n++] = iterator.next();
        }
    }
    
    public static void registerItem(final String s, final int n, final Item item) {
        Item.REGISTRY.a(n, (Object)new MinecraftKey(s), (Object)item);
    }
    
    public static boolean addRandomSpawn(final Type type, final SpawnData spawnData, final Biome... array) {
        if (type.isSpecial()) {
            return false;
        }
        final Field field;
        if ((field = type.getMeta().getField()) == null) {
            return false;
        }
        try {
            field.setAccessible(true);
            for (int length = array.length, i = 0; i < length; ++i) {
                BiomeBase[] nmsBiomeArray;
                for (int length2 = (nmsBiomeArray = array[i].getNMSBiomeArray()).length, j = 0; j < length2; ++j) {
                    final BiomeBase biomeBase = nmsBiomeArray[j];
                    final List value = (List)field.get(biomeBase);
                    value.add(spawnData);
                    field.set(biomeBase, value);
                }
            }
            field.setAccessible(false);
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static void registerEntity(final Type type, final Class<? extends Entity> clazz, final boolean b) {
        registerEntity(type.getName(), type, clazz, b);
    }
    
    public static void registerEntity(final int n, final String s, final Type type, final Class<? extends Entity> b, final Biome... array) {
        final MinecraftKey minecraftKey = new MinecraftKey(s);
        EntityTypes.b.a(n, (Object)minecraftKey, (Object)b);
        if (!EntityTypes.d.contains(minecraftKey)) {
            EntityTypes.d.add(minecraftKey);
        }
        if (array.length == 0 || type.isSpecial()) {
            return;
        }
        final Field field;
        if ((field = type.getMeta().getField()) == null) {
            return;
        }
        try {
            field.setAccessible(true);
            for (int length = array.length, i = 0; i < length; ++i) {
                BiomeBase[] nmsBiomeArray;
                for (int length2 = (nmsBiomeArray = array[i].getNMSBiomeArray()).length, j = 0; j < length2; ++j) {
                    for (final BiomeBase.BiomeMeta biomeMeta : (List)field.get(nmsBiomeArray[j])) {
                        if (biomeMeta.b == type.getNMSClass()) {
                            biomeMeta.b = b;
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void registerEntity(final String s, final Type type, final Class<? extends Entity> b, final boolean b2) {
        final MinecraftKey minecraftKey = new MinecraftKey(s);
        EntityTypes.b.a(type.getId(), (Object)minecraftKey, (Object)b);
        if (!EntityTypes.d.contains(minecraftKey)) {
            EntityTypes.d.add(minecraftKey);
        }
        if (!b2 || type.isSpecial()) {
            return;
        }
        final Field field;
        if ((field = type.getMeta().getField()) == null) {
            return;
        }
        try {
            field.setAccessible(true);
            BiomeBase[] biomes;
            for (int length = (biomes = NMSUtils.BIOMES).length, i = 0; i < length; ++i) {
                for (final BiomeBase.BiomeMeta biomeMeta : (List)field.get(biomes[i])) {
                    if (biomeMeta.b == type.getNMSClass()) {
                        biomeMeta.b = b;
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public enum Attributes
    {
        MAX_HEALTH("MAX_HEALTH", 0, "generic.maxHealth", GenericAttributes.maxHealth), 
        MOVEMENT_SPEED("MOVEMENT_SPEED", 1, "generic.movementSpeed", GenericAttributes.MOVEMENT_SPEED), 
        ATTACK_DAMAGE("ATTACK_DAMAGE", 2, "generic.attackDamage", GenericAttributes.ATTACK_DAMAGE), 
        FOLLOW_RANGE("FOLLOW_RANGE", 3, "generic.followRange", GenericAttributes.FOLLOW_RANGE), 
        ARMOR("ARMOR", 4, "generic.armor", GenericAttributes.h), 
        ARMOR_TOUGHNESS("ARMOR_TOUGHNESS", 5, "generic.armorToughness", GenericAttributes.i), 
        ATTACK_SPEED("ATTACK_SPEED", 6, "generic.attackSpeed", GenericAttributes.g), 
        KNOCKBACK_RESISTANCE("KNOCKBACK_RESISTANCE", 7, "generic.knockbackResistance", GenericAttributes.c);
        
        private String name;
        private IAttribute attribute;
        
        private Attributes(final String name, final int ordinal, final String name2, final IAttribute attribute) {
            this.name = name2;
            this.attribute = attribute;
        }
        
        public String getName() {
            return this.name;
        }
        
        public IAttribute asIAttribute() {
            return this.attribute;
        }
    }
    
    public enum Biome
    {
        OCEAN("OCEAN", 0, new int[] { 0 }), 
        PLAINS("PLAINS", 1, new int[] { 1 }), 
        DESERT("DESERT", 2, new int[] { 2 }), 
        EXTREME_HILLS("EXTREME_HILLS", 3, new int[] { 3 }), 
        FOREST("FOREST", 4, new int[] { 4 }), 
        TAIGA("TAIGA", 5, new int[] { 5 }), 
        SWAMPLAND("SWAMPLAND", 6, new int[] { 6 }), 
        RIVER("RIVER", 7, new int[] { 7 }), 
        HELL("HELL", 8, new int[] { 8 }), 
        SKY("SKY", 9, new int[] { 9 }), 
        FROZEN_OCEAN("FROZEN_OCEAN", 10, new int[] { 10 }), 
        FROZEN_RIVER("FROZEN_RIVER", 11, new int[] { 11 }), 
        ICE_PLAINS("ICE_PLAINS", 12, new int[] { 12 }), 
        ICE_MOUNTAINS("ICE_MOUNTAINS", 13, new int[] { 13 }), 
        MUSHROOM_ISLAND("MUSHROOM_ISLAND", 14, new int[] { 14 }), 
        MUSHROOM_ISLAND_SHORE("MUSHROOM_ISLAND_SHORE", 15, new int[] { 15 }), 
        BEACHES("BEACHES", 16, new int[] { 16 }), 
        DESERT_HILLS("DESERT_HILLS", 17, new int[] { 17 }), 
        FOREST_HILLS("FOREST_HILLS", 18, new int[] { 18 }), 
        TAIGA_HILLS("TAIGA_HILLS", 19, new int[] { 19 }), 
        EXTREME_HILLS_EDGE("EXTREME_HILLS_EDGE", 20, new int[] { 20 }), 
        JUNGLE("JUNGLE", 21, new int[] { 21 }), 
        JUNGLE_HILLS("JUNGLE_HILLS", 22, new int[] { 22 }), 
        JUNGLE_EDGE("JUNGLE_EDGE", 23, new int[] { 23 }), 
        DEEP_OCEAN("DEEP_OCEAN", 24, new int[] { 24 }), 
        STONE_BEACH("STONE_BEACH", 25, new int[] { 25 }), 
        COLD_BEACH("COLD_BEACH", 26, new int[] { 26 }), 
        BIRCH_FOREST("BIRCH_FOREST", 27, new int[] { 27 }), 
        BIRCH_FOREST_HILLS("BIRCH_FOREST_HILLS", 28, new int[] { 28 }), 
        ROOFED_FOREST("ROOFED_FOREST", 29, new int[] { 29 }), 
        COLD_TAIGA("COLD_TAIGA", 30, new int[] { 30 }), 
        COLD_TAIGA_HILLS("COLD_TAIGA_HILLS", 31, new int[] { 31 }), 
        MEGA_TAIGA("MEGA_TAIGA", 32, new int[] { 32 }), 
        MEGA_TAIGA_HILLS("MEGA_TAIGA_HILLS", 33, new int[] { 33 }), 
        EXTREME_HILLS_WITH_TREES("EXTREME_HILLS_WITH_TREES", 34, new int[] { 34 }), 
        SAVANNA("SAVANNA", 35, new int[] { 35 }), 
        SAVANNA_PLATEAU("SAVANNA_PLATEAU", 36, new int[] { 36 }), 
        MESA("MESA", 37, new int[] { 37 }), 
        MESA_PLATEAU_F("MESA_PLATEAU_F", 38, new int[] { 38 }), 
        MESA_PLATEAU("MESA_PLATEAU", 39, new int[] { 39 }), 
        VOID("VOID", 40, new int[] { 127 }), 
        SUNFLOWER_PLAINS("SUNFLOWER_PLAINS", 41, new int[] { 129 }), 
        DESERT_M("DESERT_M", 42, new int[] { 130 }), 
        EXTREME_HILLS_M("EXTREME_HILLS_M", 43, new int[] { 131 }), 
        FLOWER_FOREST("FLOWER_FOREST", 44, new int[] { 132 }), 
        TAIGA_M("TAIGA_M", 45, new int[] { 133 }), 
        SWAMPLAND_M("SWAMPLAND_M", 46, new int[] { 134 }), 
        ICE_SPIKES("ICE_SPIKES", 47, new int[] { 140 }), 
        JUNGLE_M("JUNGLE_M", 48, new int[] { 149 }), 
        JUNGLE_EDGE_M("JUNGLE_EDGE_M", 49, new int[] { 151 }), 
        BIRCH_FOREST_M("BIRCH_FOREST_M", 50, new int[] { 155 }), 
        BIRCH_FOREST_HILLS_M("BIRCH_FOREST_HILLS_M", 51, new int[] { 156 }), 
        ROOFED_FOREST_M("ROOFED_FOREST_M", 52, new int[] { 157 }), 
        COLD_TAIGA_M("COLD_TAIGA_M", 53, new int[] { 158 }), 
        MEGA_SPRUCE_TAIGA("MEGA_SPRUCE_TAIGA", 54, new int[] { 160 }), 
        REDWOOD_TAIGA_HILLS_M("REDWOOD_TAIGA_HILLS_M", 55, new int[] { 161 }), 
        EXTREME_HILLS_PLUS_M("EXTREME_HILLS_PLUS_M", 56, new int[] { 162 }), 
        SAVANNA_M("SAVANNA_M", 57, new int[] { 163 }), 
        SAVANNA_PLATEAU_M("SAVANNA_PLATEAU_M", 58, new int[] { 164 }), 
        MESA_BRYCE("MESA_BRYCE", 59, new int[] { 165 }), 
        MESA_PLATEAU_F_M("MESA_PLATEAU_F_M", 60, new int[] { 166 }), 
        MESA_PLATEAU_M("MESA_PLATEAU_M", 61, new int[] { 167 }), 
        COLLECTION_MESA("COLLECTION_MESA", 62, new int[] { 37, 38, 39, 165, 166, 167 }), 
        COLLECTION_FORESTS_ALL("COLLECTION_FORESTS_ALL", 63, new int[] { 4, 18, 27, 28, 29, 30, 31, 32, 33, 132, 133, 155, 156, 157, 158, 160 }), 
        COLLECTION_DESERTS("COLLECTION_DESERTS", 64, new int[] { 2, 17, 130 }), 
        COLLECTION_PLAINS("COLLECTION_PLAINS", 65, new int[] { 1, 12, 129 }), 
        COLLECTION_ICY_BIOMES("COLLECTION_ICY_BIOMES", 66, new int[] { 12, 13, 10, 11, 140 }), 
        COLLECTION_TAIGA("COLLECTION_TAIGA", 67, new int[] { 30, 31, 32, 33, 133, 158, 161, 5 }), 
        COLLECTION_WATER("COLLECTION_WATER", 68, new int[] { 0, 7, 10, 11, 24 }), 
        COLLECTION_BEACHES("COLLECTION_BEACHES", 69, new int[] { 16, 25, 26 }), 
        COLLECTION_JUNGLE("COLLECTION_JUNGLE", 70, new int[] { 21, 22, 23, 149, 151 }), 
        COLLECTION_EXTREME_HILLS("COLLECTION_EXTREME_HILLS", 71, new int[] { 3, 20, 34, 131, 162 }), 
        COLLECTION_SWAMPLAND("COLLECTION_SWAMPLAND", 72, new int[] { 6, 134 }), 
        COLLECTION_SAVANNA("COLLECTION_SAVANNA", 73, new int[] { 35, 36, 163, 164 }), 
        COLLECTION_ALL("COLLECTION_ALL", 74, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 127, 129, 130, 131, 132, 133, 134, 140, 149, 151, 155, 156, 157, 158, 160, 161, 162, 163, 164, 165, 166, 167 });
        
        private static final Biome[] ID_LOOKUP_TABLE;
        private BiomeBase[] biomes;
        private BiomeBase biome;
        
        static {
            ID_LOOKUP_TABLE = new Biome[168];
            Biome[] values;
            for (int length = (values = values()).length, i = 0; i < length; ++i) {
                final Biome biome = values[i];
                Biome.ID_LOOKUP_TABLE[BiomeBase.REGISTRY_ID.a((Object)biome.biome)] = biome;
            }
        }
        
        private Biome(final String name, final int ordinal, final int... array) {
            assert array.length > 0;
            this.biomes = new BiomeBase[array.length];
            for (int i = 0; i < this.biomes.length; ++i) {
                this.biomes[i] = (BiomeBase)BiomeBase.REGISTRY_ID.getId(array[i]);
            }
            this.biome = this.biomes[0];
        }
        
        public static Biome fromId(final int n) {
            return Biome.ID_LOOKUP_TABLE[n];
        }
        
        public BiomeBase asNMSBiome() {
            return this.biome;
        }
        
        public BiomeBase[] getNMSBiomeArray() {
            return this.biomes;
        }
    }
    
    public enum MobMeta
    {
        MONSTER("MONSTER", 0, NMSUtils.META_LIST_MONSTER), 
        CREATURE("CREATURE", 1, NMSUtils.META_LIST_CREATURE), 
        WATER_CREATURE("WATER_CREATURE", 2, NMSUtils.META_LIST_WATER_CREATURE), 
        AMBIENT("AMBIENT", 3, NMSUtils.META_LIST_AMBIENT), 
        UNDEFINED("UNDEFINED", 4, (Field)null);
        
        private Field field;
        
        private MobMeta(final String name, final int ordinal, final Field field) {
            this.field = field;
        }
        
        public Field getField() {
            return this.field;
        }
    }
    
    public enum Type
    {
        DROPPED_ITEM(1, "item", (Class<? extends Entity>)EntityItem.class, MobMeta.UNDEFINED, true), 
        EXPERIENCE_ORB(2, "xp_orb", (Class<? extends Entity>)EntityExperienceOrb.class, MobMeta.UNDEFINED, true), 
        AREA_EFFECT_CLOUD(3, "area_effect_cloud", (Class<? extends Entity>)EntityAreaEffectCloud.class, MobMeta.UNDEFINED, true), 
        ELDER_GUARDIAN(4, "elder_guardian", (Class<? extends Entity>)EntityGuardianElder.class, MobMeta.MONSTER, false), 
        WITHER_SKELETON(5, "wither_skeleton", (Class<? extends Entity>)EntitySkeletonWither.class, MobMeta.MONSTER, false), 
        STRAY(6, "stray", (Class<? extends Entity>)EntitySkeletonStray.class, MobMeta.MONSTER, false), 
        THROWN_EGG(7, "egg", (Class<? extends Entity>)EntityEgg.class, MobMeta.UNDEFINED, true), 
        LEAD_KNOT(8, "leash_knot", (Class<? extends Entity>)EntityLeash.class, MobMeta.UNDEFINED, true), 
        PAINTING(9, "painting", (Class<? extends Entity>)EntityPainting.class, MobMeta.UNDEFINED, true), 
        ARROW(10, "arrow", (Class<? extends Entity>)EntityArrow.class, MobMeta.UNDEFINED, true), 
        SNOWBALL(11, "snowball", (Class<? extends Entity>)EntitySnowball.class, MobMeta.UNDEFINED, true), 
        FIREBALL(12, "fireball", (Class<? extends Entity>)EntityFireball.class, MobMeta.UNDEFINED, true), 
        SMALL_FIREBALL(13, "fireball", (Class<? extends Entity>)EntitySmallFireball.class, MobMeta.UNDEFINED, true), 
        ENDER_PEARL(14, "ender_pearl", (Class<? extends Entity>)EntityEnderPearl.class, MobMeta.UNDEFINED, true), 
        EYE_OF_ENDER(15, "eye_of_ender_signal", (Class<? extends Entity>)EntityEnderSignal.class, MobMeta.UNDEFINED, true), 
        POTION(16, "potion", (Class<? extends Entity>)EntityPotion.class, MobMeta.UNDEFINED, true), 
        EXP_BOTTLE(17, "xp_bottle", (Class<? extends Entity>)EntityThrownExpBottle.class, MobMeta.UNDEFINED, true), 
        ITEM_FRAME(18, "item_frame", (Class<? extends Entity>)EntityItemFrame.class, MobMeta.UNDEFINED, true), 
        WITHER_SKULL(19, "wither_skull", (Class<? extends Entity>)EntityWitherSkull.class, MobMeta.UNDEFINED, true), 
        PRIMED_TNT(20, "tnt", (Class<? extends Entity>)EntityTNTPrimed.class, MobMeta.UNDEFINED, true), 
        FALLING_BLOCK(21, "falling_block", (Class<? extends Entity>)EntityFallingBlock.class, MobMeta.UNDEFINED, true), 
        FIREWORK_ROCKET(22, "fireworks_rocket", (Class<? extends Entity>)EntityFireworks.class, MobMeta.UNDEFINED, true), 
        HUSK(23, "husk", (Class<? extends Entity>)EntityZombieHusk.class, MobMeta.MONSTER, false), 
        SPECTRAL_ARROW(24, "spectral_arrow", (Class<? extends Entity>)EntitySpectralArrow.class, MobMeta.UNDEFINED, true), 
        SHULKER_BULLET(25, "shulker_bullet", (Class<? extends Entity>)EntityShulkerBullet.class, MobMeta.UNDEFINED, true), 
        DRAGON_FIREBALL(26, "dragon_fireball", (Class<? extends Entity>)EntityDragonFireball.class, MobMeta.UNDEFINED, true), 
        ZOMBIE_VILLAGER(27, "zombie_villager", (Class<? extends Entity>)EntityZombieVillager.class, MobMeta.MONSTER, false), 
        SKELETON_HORSE(28, "skeleton_horse", (Class<? extends Entity>)EntityHorseSkeleton.class, MobMeta.CREATURE, false), 
        ZOMBIE_HORSE(29, "zombie_horse", (Class<? extends Entity>)EntityHorseZombie.class, MobMeta.CREATURE, false), 
        ARMOR_STAND(30, "armor_stand", (Class<? extends Entity>)EntityArmorStand.class, MobMeta.UNDEFINED, true), 
        DONKEY(31, "donkey", (Class<? extends Entity>)EntityHorseDonkey.class, MobMeta.CREATURE, false), 
        MULE(32, "mule", (Class<? extends Entity>)EntityHorseMule.class, MobMeta.CREATURE, false), 
        EVOCATION_FANGS(33, "evocation_fangs", (Class<? extends Entity>)EntityEvokerFangs.class, MobMeta.UNDEFINED, true), 
        EVOKER(34, "evocation_illager", (Class<? extends Entity>)EntityEvoker.class, MobMeta.MONSTER, false), 
        VEX(35, "vex", (Class<? extends Entity>)EntityVex.class, MobMeta.MONSTER, false), 
        VINDICATOR(36, "vindication_illager", (Class<? extends Entity>)EntityVindicator.class, MobMeta.MONSTER, false), 
        COMMAND_BLOCK_MINECART(40, "commandblock_minecart", (Class<? extends Entity>)EntityMinecartCommandBlock.class, MobMeta.UNDEFINED, true), 
        BOAT(41, "boat", (Class<? extends Entity>)EntityBoat.class, MobMeta.UNDEFINED, true), 
        MINECART(42, "minecart", (Class<? extends Entity>)EntityMinecartRideable.class, MobMeta.UNDEFINED, true), 
        CHEST_MINECART(43, "chest_minecart", (Class<? extends Entity>)EntityMinecartChest.class, MobMeta.UNDEFINED, true), 
        FURNACE_MINECART(44, "furnace_minecart", (Class<? extends Entity>)EntityMinecartFurnace.class, MobMeta.UNDEFINED, true), 
        TNT_MINECART(45, "tnt_minecart", (Class<? extends Entity>)EntityMinecartTNT.class, MobMeta.UNDEFINED, true), 
        HOPPER_MINECART(46, "hopper_minecart", (Class<? extends Entity>)EntityMinecartHopper.class, MobMeta.UNDEFINED, true), 
        SPAWNER_MINECART(47, "spawner_minecart", (Class<? extends Entity>)EntityMinecartMobSpawner.class, MobMeta.UNDEFINED, true), 
        CREEPER(50, "creeper", (Class<? extends Entity>)EntityCreeper.class, MobMeta.MONSTER, false), 
        SKELETON(51, "skeleton", (Class<? extends Entity>)EntitySkeleton.class, MobMeta.MONSTER, false), 
        SPIDER(52, "spider", (Class<? extends Entity>)EntitySpider.class, MobMeta.MONSTER, false), 
        GIANT(53, "giant", (Class<? extends Entity>)EntityGiantZombie.class, MobMeta.MONSTER, false), 
        ZOMBIE(54, "zombie", (Class<? extends Entity>)EntityZombie.class, MobMeta.MONSTER, false), 
        SLIME(55, "slime", (Class<? extends Entity>)EntitySlime.class, MobMeta.MONSTER, false), 
        GHAST(56, "ghast", (Class<? extends Entity>)EntityGhast.class, MobMeta.MONSTER, false), 
        ZOMBIE_PIGMAN(57, "zombie_pigman", (Class<? extends Entity>)EntityPigZombie.class, MobMeta.MONSTER, false), 
        ENDERMAN(58, "enderman", (Class<? extends Entity>)EntityEnderman.class, MobMeta.MONSTER, false), 
        CAVE_SPIDER(59, "cave_spider", (Class<? extends Entity>)EntityCaveSpider.class, MobMeta.MONSTER, false), 
        SILVERFISH(60, "silverfish", (Class<? extends Entity>)EntitySilverfish.class, MobMeta.MONSTER, false), 
        BLAZE(61, "blaze", (Class<? extends Entity>)EntityBlaze.class, MobMeta.MONSTER, false), 
        MAGMACUBE(62, "magma_cube", (Class<? extends Entity>)EntityMagmaCube.class, MobMeta.MONSTER, false), 
        ENDER_DRAGON(63, "ender_dragon", (Class<? extends Entity>)EntityEnderDragon.class, MobMeta.MONSTER, false), 
        WITHER(64, "wither", (Class<? extends Entity>)EntityWither.class, MobMeta.MONSTER, false), 
        BAT(65, "bat", (Class<? extends Entity>)EntityBat.class, MobMeta.AMBIENT, false), 
        WITCH(66, "witch", (Class<? extends Entity>)EntityWitch.class, MobMeta.MONSTER, false), 
        ENDERMITE(67, "endermite", (Class<? extends Entity>)EntityEndermite.class, MobMeta.MONSTER, false), 
        GUARDIAN(68, "guardian", (Class<? extends Entity>)EntityGuardian.class, MobMeta.MONSTER, false), 
        SHULKER(69, "shulker", (Class<? extends Entity>)EntityShulker.class, MobMeta.MONSTER, false), 
        PIG(90, "pig", (Class<? extends Entity>)EntityPig.class, MobMeta.CREATURE, false), 
        SHEEP(91, "sheep", (Class<? extends Entity>)EntitySheep.class, MobMeta.CREATURE, false), 
        COW(92, "cow", (Class<? extends Entity>)EntityCow.class, MobMeta.CREATURE, false), 
        CHICKEN(93, "chicken", (Class<? extends Entity>)EntityChicken.class, MobMeta.CREATURE, false), 
        SQUID(94, "squid", (Class<? extends Entity>)EntitySquid.class, MobMeta.WATER_CREATURE, false), 
        WOLF(95, "wolf", (Class<? extends Entity>)EntityWolf.class, MobMeta.CREATURE, false), 
        MUSHROOM_COW(96, "mushroom_cow", (Class<? extends Entity>)EntityMushroomCow.class, MobMeta.CREATURE, false), 
        SNOWMAN(97, "snowman", (Class<? extends Entity>)EntitySnowman.class, MobMeta.CREATURE, false), 
        OCELOT(98, "ocelot", (Class<? extends Entity>)EntityOcelot.class, MobMeta.CREATURE, false), 
        IRON_GOLEM(99, "villager_golem", (Class<? extends Entity>)EntityIronGolem.class, MobMeta.CREATURE, false), 
        HORSE(100, "horse", (Class<? extends Entity>)EntityHorse.class, MobMeta.CREATURE, false), 
        RABBIT(101, "rabbit", (Class<? extends Entity>)EntityRabbit.class, MobMeta.CREATURE, false), 
        POLARBEAR(102, "polar_bear", (Class<? extends Entity>)EntityPolarBear.class, MobMeta.CREATURE, false), 
        LLAMA(103, "llama", (Class<? extends Entity>)EntityLlama.class, MobMeta.CREATURE, false), 
        LLAMA_SPIT(104, "llama_spit", (Class<? extends Entity>)EntityLlamaSpit.class, MobMeta.UNDEFINED, true), 
        VILLAGER(120, "villager", (Class<? extends Entity>)EntityVillager.class, MobMeta.CREATURE, false), 
        ENDER_CRYSTAL(200, "ender_crystal", (Class<? extends Entity>)EntityEnderCrystal.class, MobMeta.UNDEFINED, true);
        
        private int id;
        private String name;
        private Class<? extends Entity> clazz;
        private MobMeta meta;
        private boolean special;
        
        private Type(final int id, final String name2, final Class<? extends Entity> clazz, final MobMeta meta, final boolean special) {
            this.id = id;
            this.name = name2;
            this.clazz = clazz;
            this.meta = meta;
            this.special = special;
        }
        
        public MobMeta getMeta() {
            return this.meta;
        }
        
        public int getId() {
            return this.id;
        }
        
        public String getName() {
            return this.name;
        }
        
        public Class<? extends Entity> getNMSClass() {
            return this.clazz;
        }
        
        public boolean isSpecial() {
            return this.special;
        }
    }
    
    public static class SpawnData extends BiomeBase.BiomeMeta
    {
        public SpawnData(final Class<? extends EntityInsentient> clazz, final int n, final int n2, final int n3) {
            super((Class)clazz, n, n2, n3);
        }
        
        public Class<? extends EntityInsentient> getCustomClass() {
            return (Class<? extends EntityInsentient>)this.b;
        }
        
        public int getSpawnWeight() {
            return this.a;
        }
        
        public int getMinSpawns() {
            return this.c;
        }
        
        public int getMaxSpawns() {
            return this.d;
        }
    }
    
    public class NBTTagType
    {
        public static final int COMPOUND = 10;
        public static final int LIST = 9;
        public static final int STRING = 8;
        public static final int LONG_ARRAY = 12;
        public static final int INT_ARRAY = 11;
        public static final int BYTE_ARRAY = 7;
        public static final int DOUBLE = 6;
        public static final int FLOAT = 5;
        public static final int LONG = 4;
        public static final int INT = 3;
        public static final int SHORT = 2;
        public static final int BYTE = 1;
        public static final int BOOLEAN = 1;
        public static final int END = 0;
    }
}
