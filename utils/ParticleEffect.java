

package ws.billy.CookieGadgets.utils;

import org.bukkit.Color;
import java.util.List;
import java.util.Iterator;
import org.bukkit.GameMode;
import org.bukkit.Bukkit;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.util.Vector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.log.LoggerManager;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public enum ParticleEffect
{
    ASH("ASH", 0, "ASH", 16), 
    BARRIER("BARRIER", 1, "BARRIER", "c", "BARRIER", 35, 8), 
    BLOCK_CRACK("BLOCK_CRACK", 2, "BLOCK_CRACK", "d", "BLOCK", 37, 8), 
    BLOCK_DUST("BLOCK_DUST", 3, "BLOCK_DUST", "d", "BLOCK", 38, 8), 
    BUBBLE_COLUMN_UP("BUBBLE_COLUMN_UP", 4, "BUBBLE_COLUMN_UP", "f", "BUBBLE_COLUMN_UP", 13), 
    BUBBLE_POP("BUBBLE_POP", 5, "BUBBLE_POP", "T", "BUBBLE_POP", 13), 
    CAMPFIRE_COSY_SMOKE("CAMPFIRE_COSY_SMOKE", 6, "CAMPFIRE_COSY_SMOKE", 14), 
    CAMPFIRE_SIGNAL_SMOKE("CAMPFIRE_SIGNAL_SMOKE", 7, "CAMPFIRE_SIGNAL_SMOKE", 14), 
    CLOUD("CLOUD", 8, "CLOUD", "g", "CLOUD", 29, 8), 
    COMPOSTER("COMPOSTER", 9, "COMPOSTER", 14), 
    CRIMSON_SPORE("CRIMSON_SPORE", 10, "CRIMSON_SPORE", 16), 
    CRIT("CRIT", 11, "CRIT", "h", "CRIT", 9, 8), 
    CRIT_MAGIC("CRIT_MAGIC", 12, "CRIT_MAGIC", "p", "ENCHANTED_HIT", 10, 8), 
    CURRENT_DOWN("CURRENT_DOWN", 13, "CURRENT_DOWN", "U", "CURRENT_DOWN", 13), 
    DAMAGE_INDICATOR("DAMAGE_INDICATOR", 14, "DAMAGE_INDICATOR", "i", "DAMAGE_INDICATOR", 44, 9), 
    DOLPHIN("DOLPHIN", 15, "DOLPHIN", "X", "DOLPHIN", 13), 
    DRAGON_BREATH("DRAGON_BREATH", 16, "DRAGON_BREATH", "j", "DRAGON_BREATH", 42, 9), 
    DRIPPING_HONEY("DRIPPING_HONEY", 17, "DRIPPING_HONEY", 15), 
    DRIPPING_OBSIDIAN_TEAR("DRIPPING_OBSIDIAN_TEAR", 18, "DRIPPING_OBSIDIAN_TEAR", 16), 
    DRIP_LAVA("DRIP_LAVA", 19, "DRIP_LAVA", "k", "DRIPPING_LAVA", 19, 8), 
    DRIP_WATER("DRIP_WATER", 20, "DRIP_WATER", "l", "DRIPPING_WATER", 18, 8), 
    ENCHANTMENT_TABLE("ENCHANTMENT_TABLE", 21, "ENCHANTMENT_TABLE", "q", "ENCHANT", 25, 8), 
    END_ROD("END_ROD", 22, "END_ROD", "r", "END_ROD", 43, 9), 
    EXPLOSION_HUGE("EXPLOSION_HUGE", 23, "EXPLOSION_HUGE", "t", "EXPLOSION_EMITTER", 2, 8), 
    EXPLOSION_LARGE("EXPLOSION_LARGE", 24, "EXPLOSION_LARGE", "u", "EXPLOSION", 1, 8), 
    EXPLOSION_NORMAL("EXPLOSION_NORMAL", 25, "EXPLOSION_NORMAL", "J", "POOF", 0, 8), 
    FALLING_DUST("FALLING_DUST", 26, "FALLING_DUST", "v", "FALLING_DUST", 46, 10), 
    FALLING_HONEY("FALLING_HONEY", 27, "FALLING_HONEY", 15), 
    FALLING_LAVA("FALLING_LAVA", 28, "FALLING_LAVA", 14), 
    FALLING_NECTAR("FALLING_NECTAR", 29, "FALLING_NECTAR", 15), 
    FALLING_OBSIDIAN_TEAR("FALLING_OBSIDIAN_TEAR", 30, "FALLING_OBSIDIAN_TEAR", 16), 
    FALLING_WATER("FALLING_WATER", 31, "FALLING_WATER", 14), 
    FIREWORKS_SPARK("FIREWORKS_SPARK", 32, "FIREWORKS_SPARK", "w", "FIREWORK", 3, 8), 
    FLAME("FLAME", 33, "FLAME", "y", "FLAME", 26, 8), 
    FLASH("FLASH", 34, "FLASH", 14), 
    HEART("HEART", 35, "HEART", "A", "HEART", 34, 8), 
    ITEM_CRACK("ITEM_CRACK", 36, "ITEM_CRACK", "C", "ITEM", 36, 8), 
    LANDING_HONEY("LANDING_HONEY", 37, "LANDING_HONEY", 15), 
    LANDING_LAVA("LANDING_LAVA", 38, "LANDING_LAVA", 14), 
    LANDING_OBSIDIAN_TEAR("LANDING_OBSIDIAN_TEAR", 39, "LANDING_OBSIDIAN_TEAR", 16), 
    LAVA("LAVA", 40, "LAVA", "G", "LAVA", 27, 8), 
    MOB_APPEARANCE("MOB_APPEARANCE", 41, "MOB_APPEARANCE", "o", "ELDER_GUARDIAN", 41, 8), 
    NAUTILUS("NAUTILUS", 42, "NAUTILUS", "W", "NAUTILUS", 13), 
    NOTE("NOTE", 43, "NOTE", "I", "NOTE", 23, 8), 
    PORTAL("PORTAL", 44, "PORTAL", "K", "PORTAL", 24, 8), 
    REDSTONE("REDSTONE", 45, "REDSTONE", "m", "DUST", 30, 8), 
    REVERSE_PORTAL("REVERSE_PORTAL", 46, "REVERSE_PORTAL", 16), 
    SLIME("SLIME", 47, "SLIME", "D", "ITEM_SLIME", 33, 8), 
    SMOKE_LARGE("SMOKE_LARGE", 48, "SMOKE_LARGE", "F", "LARGE_SMOKE", 12, 8), 
    SMOKE_NORMAL("SMOKE_NORMAL", 49, "SMOKE_NORMAL", "M", "SMOKE", 11, 8), 
    SNEEZE("SNEEZE", 50, "SNEEZE", 14), 
    SNOW_SHOVEL("SNOW_SHOVEL", 51, "SNOW_SHOVEL", "J", "POOF", 32, 8), 
    SNOWBALL("SNOWBALL", 52, "SNOWBALL", "E", "ITEM_SNOWBALL", 31, 8), 
    SOUL("SOUL", 53, "SOUL", 16), 
    SOUL_FIRE_FLAME("SOUL_FIRE_FLAME", 54, "SOUL_FIRE_FLAME", 16), 
    SPELL("SPELL", 55, "SPELL", "n", "EFFECT", 13, 8), 
    SPELL_INSTANT("SPELL_INSTANT", 56, "SPELL_INSTANT", "B", "INSTANT_EFFECT", 14, 8), 
    SPELL_MOB("SPELL_MOB", 57, "SPELL_MOB", "s", "ENTITY_EFFECT", 15, 8), 
    SPELL_MOB_AMBIENT("SPELL_MOB_AMBIENT", 58, "SPELL_MOB_AMBIENT", "a", "AMBIENT_ENTITY_EFFECT", 16, 8), 
    SPELL_WITCH("SPELL_WITCH", 59, "SPELL_WITCH", "S", "WITCH", 17, 8), 
    SPIT("SPIT", 60, "SPIT", "N", "SPIT", 48, 11), 
    SQUID_INK("SQUID_INK", 61, "SQUID_INK", "V", "SQUID_INK", 13), 
    SUSPENDED("SUSPENDED", 62, "SUSPENDED", "Q", "UNDERWATER", 7, 8), 
    SWEEP_ATTACK("SWEEP_ATTACK", 63, "SWEEP_ATTACK", "O", "SWEEP_ATTACK", 45, 9), 
    TOTEM("TOTEM", 64, "TOTEM", "P", "TOTEM_OF_UNDYING", 47, 11), 
    TOWN_AURA("TOWN_AURA", 65, "TOWN_AURA", "H", "MYCELIUM", 22, 8), 
    VILLAGER_ANGRY("VILLAGER_ANGRY", 66, "VILLAGER_ANGRY", "b", "ANGRY_VILLAGER", 20, 8), 
    VILLAGER_HAPPY("VILLAGER_HAPPY", 67, "VILLAGER_HAPPY", "z", "HAPPY_VILLAGER", 21, 8), 
    WARPED_SPORE("WARPED_SPORE", 68, "WARPED_SPORE", 16), 
    WATER_BUBBLE("WATER_BUBBLE", 69, "WATER_BUBBLE", "e", "BUBBLE", 4, 8), 
    WATER_DROP("WATER_DROP", 70, "WATER_DROP", "L", "RAIN", 39, 8), 
    WATER_SPLASH("WATER_SPLASH", 71, "WATER_SPLASH", "R", "SPLASH", 5, 8), 
    WATER_WAKE("WATER_WAKE", 72, "WATER_WAKE", "x", "FISHING", 6, 8), 
    WHITE_ASH("WHITE_ASH", 73, "WHITE_ASH", 16);
    
    private String name;
    private String v1_13Name;
    private String v1_14Name;
    private int id;
    private int requiredVersion;
    private static int maxRange;
    
    static {
        ParticleEffect.maxRange = 128;
    }
    
    private ParticleEffect(final String name, final int ordinal, final String s, final int requiredVersion) {
        this.name = s;
        this.v1_14Name = s;
        this.id = 0;
        this.requiredVersion = requiredVersion;
    }
    
    private ParticleEffect(final String name, final int ordinal, final String name2, final String v1_13Name, final String v1_14Name, final int requiredVersion) {
        this.name = name2;
        this.v1_13Name = v1_13Name;
        this.v1_14Name = v1_14Name;
        this.id = 0;
        this.requiredVersion = requiredVersion;
    }
    
    private ParticleEffect(final String name, final int ordinal, final String name2, final String v1_13Name, final String v1_14Name, final int id, final int requiredVersion) {
        this.name = name2;
        this.v1_13Name = v1_13Name;
        this.v1_14Name = v1_14Name;
        this.id = id;
        this.requiredVersion = requiredVersion;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getV1_13Name() {
        return this.v1_13Name;
    }
    
    public String getV1_14Name() {
        return this.v1_14Name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getRequiredVersion() {
        return this.requiredVersion;
    }
    
    public boolean isVersionSupported() {
        return ServerVersion.getServerVersion().getCurrentVersionNumber() >= this.getRequiredVersion();
    }
    
    public void display(final Location location) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, 0.0f, 1, true, null).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final Location location, final boolean b, final Player player) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, 0.0f, 1, true, null).sendTo(location, b, player);
    }
    
    public void display(final Location location, final Player player) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, 0.0f, 1, true, null).sendTo(location, player);
    }
    
    public void display(final Location location, final float n) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, n, 1, true, null).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final Location location, final Player player, final float n) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, n, 1, true, null).sendTo(location, player);
    }
    
    public void display(final Location location, final int n) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, 0.0f, n, true, null).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final Location location, final Player player, final int n) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, 0.0f, n, true, null).sendTo(location, player);
    }
    
    public void display(final Location location, final float n, final int n2) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, n, n2, true, null).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final Location location, final boolean b, final Player player, final float n, final int n2) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, n, n2, true, null).sendTo(location, b, player);
    }
    
    public void display(final Location location, final Player player, final float n, final int n2) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, n, n2, true, null).sendTo(location, player);
    }
    
    public void display(final Location location, final float n, final float n2, final float n3) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, 0.0f, 1, true, null).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final Location location, final Player player, final float n, final float n2, final float n3) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, 0.0f, 1, true, null).sendTo(location, player);
    }
    
    public void display(final Location location, final float n, final float n2, final float n3, final float n4) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, n4, 1, true, null).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final Location location, final Player player, final float n, final float n2, final float n3, final float n4) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, n4, 1, true, null).sendTo(location, player);
    }
    
    public void display(final Location location, final float n, final float n2, final float n3, final int n4) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, 0.0f, n4, true, null).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final Location location, final Player player, final float n, final float n2, final float n3, final int n4) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, 0.0f, n4, true, null).sendTo(location, player);
    }
    
    public void display(final Location location, final boolean b, final Player player, final float n, final float n2, final float n3, final int n4) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, 0.0f, n4, true, null).sendTo(location, b, player);
    }
    
    public void display(final Location location, final float n, final float n2, final float n3, final float n4, final int n5) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, n4, n5, true, null).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final Location location, final Player player, final float n, final float n2, final float n3, final float n4, final int n5) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, n4, n5, true, null).sendTo(location, player);
    }
    
    public void display(final Location location, final boolean b, final Player player, final float n, final float n2, final float n3, final float n4, final int n5) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, n4, n5, true, null).sendTo(location, b, player);
    }
    
    public void displayRandomColor(final Location location) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, new OrdinaryColor(MathUtil.randomInt(1, 255), MathUtil.randomInt(1, 255), MathUtil.randomInt(1, 255), 1.0f), true).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void displayColor(final Location location, final GColor gColor) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, new OrdinaryColor(gColor.getRed(), gColor.getGreen(), gColor.getBlue(), 1.0f), true).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void displayColor(final Location location, final int n, final int n2, final int n3) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, new OrdinaryColor(n, n2, n3, 1.0f), true).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void displayColor(final Location location, final Player player, final GColor gColor) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, new OrdinaryColor(gColor.getRed(), gColor.getGreen(), gColor.getBlue(), 1.0f), true).sendTo(location, player);
    }
    
    public void displayColor(final Location location, final Player player, final int n, final int n2, final int n3) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, new OrdinaryColor(n, n2, n3, 1.0f), true).sendTo(location, player);
    }
    
    public void displayColor(final Location location, final boolean b, final Player player, final GColor gColor) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, new OrdinaryColor(gColor.getRed(), gColor.getGreen(), gColor.getBlue(), 1.0f), true).sendTo(location, b, player);
    }
    
    public void displayColor(final Location location, final boolean b, final Player player, final int n, final int n2, final int n3) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, new OrdinaryColor(n, n2, n3, 1.0f), true).sendTo(location, b, player);
    }
    
    public void display(final ItemData itemData, final Location location, final float n, final float n2, final float n3, final float n4, final int n5) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, n4, n5, true, itemData).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final ItemData itemData, final Location location, final float n, final float n2, final float n3) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, 0.0f, 1, true, itemData).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final ItemData itemData, final Location location, final float n, final int n2) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, n, n2, true, itemData).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final ItemData itemData, final Location location, final float n) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, n, 1, true, itemData).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final ItemData itemData, final Location location, final int n) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, 0.0f, n, true, itemData).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final BlockData blockData, final Location location, final float n, final float n2, final float n3, final float n4, final int n5) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, n4, n5, true, blockData).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final BlockData blockData, final Location location, final float n, final float n2, final float n3) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, n, n2, n3, 0.0f, 1, true, blockData).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final BlockData blockData, final Location location, final float n, final int n2) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, n, n2, true, blockData).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final BlockData blockData, final Location location, final float n) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, n, 1, true, blockData).sendTo(location, ParticleEffect.maxRange);
    }
    
    public void display(final BlockData blockData, final Location location, final int n) {
        if (this.isUnSupported()) {
            return;
        }
        new ParticlePacket(this, 0.0f, 0.0f, 0.0f, 0.0f, n, true, blockData).sendTo(location, ParticleEffect.maxRange);
    }
    
    private boolean isUnSupported() {
        if (!this.isVersionSupported()) {
            LoggerManager.printLogWithHeader(LoggerManager.LogLevel.WARNING, "------------------------------------------------------", MessageType.PARTICLE_EFFECT_NOT_SUPPORTED.getFormatMessage().replace("{PARTICLE}", this.name));
            CookieGadgets.getGPlayer().unequipParticle();
            return true;
        }
        return false;
    }
    
    public static final class ParticlePacket
    {
        private static Class<?> enumParticle;
        private static Class<?> nmsPacketPlayOutParticles;
        private static Constructor<?> packetConstructor;
        private static Method getHandle;
        private static Field playerConnection;
        private static Method sendPacket;
        private static boolean initialized;
        private final ParticleEffect effect;
        private float offsetX;
        private final float offsetY;
        private final float offsetZ;
        private float size;
        private float speed;
        private int amount;
        private final boolean longDistance;
        private final ParticleData data;
        private Object packet;
        
        public ParticlePacket(final ParticleEffect effect, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int amount, final boolean longDistance, final ParticleData data) {
            this.size = 1.0f;
            initialize();
            this.effect = effect;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.offsetZ = offsetZ;
            this.speed = speed;
            this.amount = amount;
            this.longDistance = longDistance;
            this.data = data;
            if (speed < 0.0f) {
                this.speed = 0.0f;
                throw new IllegalArgumentException("The speed is lower than 0");
            }
            if (amount < 0) {
                this.amount = 1;
                throw new IllegalArgumentException("The amount is lower than 0");
            }
        }
        
        public ParticlePacket(final ParticleEffect particleEffect, final Vector vector, final float n, final boolean b, final ParticleData particleData) {
            this(particleEffect, (float)vector.getX(), (float)vector.getY(), (float)vector.getZ(), n, 0, b, particleData);
        }
        
        public ParticlePacket(final ParticleEffect particleEffect, final ParticleColor particleColor, final boolean b) {
            this(particleEffect, particleColor.getValueX(), particleColor.getValueY(), particleColor.getValueZ(), 1.0f, 0, b, null);
            this.size = particleColor.getValueSize();
            if (particleEffect == ParticleEffect.REDSTONE && particleColor instanceof OrdinaryColor && ((OrdinaryColor)particleColor).getRed() == 0) {
                this.offsetX = Float.MIN_NORMAL;
            }
        }
        
        public static void initialize() {
            if (ParticlePacket.initialized) {
                return;
            }
            try {
                ParticlePacket.enumParticle = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass(VersionManager.is1_13OrAbove() ? "Particles" : "EnumParticle");
                ParticlePacket.nmsPacketPlayOutParticles = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutWorldParticles");
                ParticlePacket.packetConstructor = ReflectionUtils.getConstructor(ParticlePacket.nmsPacketPlayOutParticles, (Class<?>[])new Class[0]);
                ParticlePacket.getHandle = ReflectionUtils.getMethod("CraftPlayer", ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY, "getHandle", (Class<?>[])new Class[0]);
                ParticlePacket.playerConnection = ReflectionUtils.getField("EntityPlayer", ReflectionUtils.PackageType.MINECRAFT_SERVER, false, "playerConnection");
                ParticlePacket.sendPacket = ReflectionUtils.getMethod(ParticlePacket.playerConnection.getType(), "sendPacket", ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Packet"));
            }
            catch (Exception ex) {
                throw new VersionIncompatibleException("Your current bukkit version seems to be incompatible with this library", ex);
            }
            ParticlePacket.initialized = true;
        }
        
        public static boolean isInitialized() {
            return ParticlePacket.initialized;
        }
        
        private void initializePacket(final Location location) {
            if (this.packet != null) {
                return;
            }
            try {
                if (VersionManager.is1_13OrAbove()) {
                    String s = this.effect.getV1_13Name();
                    if (VersionManager.is1_14OrAbove()) {
                        s = this.effect.getV1_14Name();
                    }
                    Object o = ParticlePacket.enumParticle.getField(s).get(s);
                    if (this.effect == ParticleEffect.REDSTONE) {
                        if (this.offsetX != 0.0f || this.offsetY != 0.0f || this.offsetZ != 0.0f) {
                            o = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ParticleParamRedstone").getConstructor(Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE).newInstance(this.offsetX, this.offsetY, this.offsetZ, this.size);
                        }
                        else {
                            o = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ParticleParamRedstone").getConstructor(Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE).newInstance(MathUtil.randomFloat(0.0f, 1.0f), MathUtil.randomFloat(0.0f, 1.0f), MathUtil.randomFloat(0.0f, 1.0f), this.size);
                        }
                    }
                    else if (this.effect == ParticleEffect.BLOCK_CRACK || this.effect == ParticleEffect.BLOCK_DUST || this.effect == ParticleEffect.FALLING_DUST) {
                        ParticleData data = this.data;
                        if (data == null) {
                            data = new BlockData(EnumMaterial.GRASS_BLOCK, (byte)0);
                        }
                        final Class<?> class1 = ReflectionUtils.PackageType.CRAFTBUKKIT.getClass("block.CraftBlockState");
                        final Object instance = class1.getConstructor(Material.class).newInstance(data.getMaterial().getType());
                        class1.getDeclaredMethod("setRawData", Byte.TYPE).invoke(instance, 10);
                        o = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ParticleParamBlock").getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Particle"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("IBlockData")).newInstance(o, class1.getDeclaredMethod("getHandle", (Class<?>[])new Class[0]).invoke(instance, new Object[0]));
                    }
                    else if (this.effect == ParticleEffect.ITEM_CRACK) {
                        o = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ParticleParamItem").getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Particle"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ItemStack")).newInstance(o, ReflectionUtils.PackageType.CRAFTBUKKIT.getClass("inventory.CraftItemStack").getDeclaredMethod("asNMSCopy", ItemStack.class).invoke(null, ReflectionUtils.PackageType.CRAFTBUKKIT.getClass("inventory.CraftItemStack").getDeclaredMethod("asCraftCopy", ItemStack.class).invoke(null, new ItemStack((this.data == null) ? EnumMaterial.GRASS_BLOCK.getType() : this.data.getMaterial().getType()))));
                    }
                    if (VersionManager.is1_15OrAbove()) {
                        this.packet = ParticlePacket.nmsPacketPlayOutParticles.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ParticleParam"), Boolean.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Integer.TYPE).newInstance(o, true, location.getX(), location.getY(), location.getZ(), this.offsetX, this.offsetY, this.offsetZ, this.speed, this.amount);
                    }
                    else {
                        this.packet = ParticlePacket.nmsPacketPlayOutParticles.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ParticleParam"), Boolean.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Integer.TYPE).newInstance(o, true, (float)location.getX(), (float)location.getY(), (float)location.getZ(), this.offsetX, this.offsetY, this.offsetZ, this.speed, this.amount);
                    }
                }
                else {
                    ParticleData data2 = this.data;
                    if ((this.effect == ParticleEffect.BLOCK_CRACK || this.effect == ParticleEffect.BLOCK_DUST || this.effect == ParticleEffect.FALLING_DUST || this.effect == ParticleEffect.ITEM_CRACK) && data2 == null) {
                        data2 = new BlockData(EnumMaterial.GRASS_BLOCK, (byte)0);
                    }
                    ReflectionUtils.setValue(this.packet = ParticlePacket.packetConstructor.newInstance(new Object[0]), true, "a", ParticlePacket.enumParticle.getEnumConstants()[this.effect.getId()]);
                    ReflectionUtils.setValue(this.packet, true, "j", this.longDistance);
                    if (data2 != null) {
                        final int[] packetData = data2.getPacketData();
                        ReflectionUtils.setValue(this.packet, true, "k", (this.effect == ParticleEffect.ITEM_CRACK) ? packetData : new int[] { packetData[0] | packetData[1] << 12 });
                    }
                    ReflectionUtils.setValue(this.packet, true, "b", (float)location.getX());
                    ReflectionUtils.setValue(this.packet, true, "c", (float)location.getY());
                    ReflectionUtils.setValue(this.packet, true, "d", (float)location.getZ());
                    ReflectionUtils.setValue(this.packet, true, "e", this.offsetX);
                    ReflectionUtils.setValue(this.packet, true, "f", this.offsetY);
                    ReflectionUtils.setValue(this.packet, true, "g", this.offsetZ);
                    ReflectionUtils.setValue(this.packet, true, "h", this.speed);
                    ReflectionUtils.setValue(this.packet, true, "i", this.amount);
                }
            }
            catch (Exception ex) {
                throw new PacketInstantiationException("Packet instantiation failed", ex);
            }
        }
        
        public void sendTo(final Location location, final Player obj) {
            Validate.notNull((Object)location, "Location cannot be null.");
            Validate.notNull((Object)obj, "Player cannot be null.");
            if (location.getWorld() != obj.getWorld()) {
                return;
            }
            this.initializePacket(location);
            try {
                ParticlePacket.sendPacket.invoke(ParticlePacket.playerConnection.get(ParticlePacket.getHandle.invoke(obj, new Object[0])), this.packet);
            }
            catch (Exception ex) {
                throw new PacketSendingException("Failed to send the packet to player '" + obj.getName() + "'", ex);
            }
        }
        
        public void sendTo(final Location location, final boolean b, final Player player) {
            Validate.notNull((Object)player, "Player cannot be null.");
            if (b) {
                final double n = ParticleEffect.maxRange * ParticleEffect.maxRange;
                for (final Player player2 : Bukkit.getOnlinePlayers()) {
                    if (player2.getWorld() == location.getWorld()) {
                        if (player2.getLocation().distanceSquared(location) > n) {
                            continue;
                        }
                        if ((player2.canSee(player) || player.getGameMode() == GameMode.SPECTATOR) && player2 != player) {
                            continue;
                        }
                        this.sendTo(location, player2);
                    }
                }
            }
            else {
                this.sendTo(location, ParticleEffect.maxRange);
            }
        }
        
        public void sendTo(final Location location, final List<Player> list) {
            if (list.isEmpty()) {
                throw new IllegalArgumentException("The player list is empty");
            }
            final Iterator<Player> iterator = list.iterator();
            while (iterator.hasNext()) {
                this.sendTo(location, iterator.next());
            }
        }
        
        public void sendTo(final Location location, final double n) {
            if (n < 1.0) {
                throw new IllegalArgumentException("The range is lower than 1");
            }
            final String name = location.getWorld().getName();
            final double n2 = n * n;
            for (final Player player : Bukkit.getOnlinePlayers()) {
                if (player.getWorld().getName().equals(name)) {
                    if (player.getLocation().distanceSquared(location) > n2) {
                        continue;
                    }
                    this.sendTo(location, player);
                }
            }
        }
        
        private static final class VersionIncompatibleException extends RuntimeException
        {
            private static final long serialVersionUID = 3203085387160737484L;
            
            public VersionIncompatibleException(final String message, final Throwable cause) {
                super(message, cause);
            }
        }
        
        private static final class PacketInstantiationException extends RuntimeException
        {
            private static final long serialVersionUID = 3203085387160737484L;
            
            public PacketInstantiationException(final String message, final Throwable cause) {
                super(message, cause);
            }
        }
        
        private static final class PacketSendingException extends RuntimeException
        {
            private static final long serialVersionUID = 3203085387160737484L;
            
            public PacketSendingException(final String message, final Throwable cause) {
                super(message, cause);
            }
        }
    }
    
    public abstract static class ParticleData
    {
        private final EnumMaterial material;
        private final byte data;
        private final int[] packetData;
        
        public ParticleData(final EnumMaterial material, final byte data) {
            this.material = material;
            this.data = data;
            this.packetData = new int[] { material.getTypeId(), data };
        }
        
        public EnumMaterial getMaterial() {
            return this.material;
        }
        
        public byte getData() {
            return this.data;
        }
        
        public int[] getPacketData() {
            return this.packetData;
        }
        
        public String getPacketDataString() {
            return "_" + this.packetData[0] + "_" + this.packetData[1];
        }
    }
    
    public static final class ItemData extends ParticleData
    {
        public ItemData(final EnumMaterial enumMaterial, final byte b) {
            super(enumMaterial, b);
        }
    }
    
    public static final class BlockData extends ParticleData
    {
        public BlockData(final EnumMaterial enumMaterial, final byte b) {
            super(enumMaterial, b);
            if (!enumMaterial.getType().isBlock()) {
                throw new IllegalArgumentException("The material is not a block");
            }
        }
    }
    
    public abstract static class ParticleColor
    {
        public abstract float getValueX();
        
        public abstract float getValueY();
        
        public abstract float getValueZ();
        
        public abstract float getValueSize();
    }
    
    public static final class OrdinaryColor extends ParticleColor
    {
        private final int red;
        private final int green;
        private final int blue;
        private final float size;
        
        public OrdinaryColor(final int red, final int green, final int blue, final float size) {
            if (red < 0) {
                throw new IllegalArgumentException("The red is lower than 0");
            }
            if (red > 255) {
                throw new IllegalArgumentException("The red is higher than 255");
            }
            this.red = red;
            if (green < 0) {
                throw new IllegalArgumentException("The green is lower than 0");
            }
            if (green > 255) {
                throw new IllegalArgumentException("The green is higher than 255");
            }
            this.green = green;
            if (blue < 0) {
                throw new IllegalArgumentException("The blue is lower than 0");
            }
            if (blue > 255) {
                throw new IllegalArgumentException("The blue is higher than 255");
            }
            this.blue = blue;
            if (size < 0.0f) {
                throw new IllegalArgumentException("The size is lower than 0");
            }
            if (size > 4.0f) {
                throw new IllegalArgumentException("The size is higher than 4");
            }
            this.size = size;
        }
        
        public OrdinaryColor(final Color color, final float n) {
            this(color.getRed(), color.getGreen(), color.getBlue(), n);
        }
        
        public int getRed() {
            return this.red;
        }
        
        public int getGreen() {
            return this.green;
        }
        
        public int getBlue() {
            return this.blue;
        }
        
        public float getSize() {
            return this.size;
        }
        
        @Override
        public float getValueX() {
            return this.red / 255.0f;
        }
        
        @Override
        public float getValueY() {
            return this.green / 255.0f;
        }
        
        @Override
        public float getValueZ() {
            return this.blue / 255.0f;
        }
        
        @Override
        public float getValueSize() {
            return this.size;
        }
    }
    
    public static final class NoteColor extends ParticleColor
    {
        private final int note;
        
        public NoteColor(final int note) {
            if (note < 0) {
                throw new IllegalArgumentException("The note is lower than 0");
            }
            if (note > 24) {
                throw new IllegalArgumentException("The note is higher than 24");
            }
            this.note = note;
        }
        
        @Override
        public float getValueX() {
            return this.note / 24.0f;
        }
        
        @Override
        public float getValueY() {
            return 0.0f;
        }
        
        @Override
        public float getValueZ() {
            return 0.0f;
        }
        
        @Override
        public float getValueSize() {
            return 1.0f;
        }
    }
}
