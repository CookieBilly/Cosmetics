

package ws.billy.CookieGadgets.nms;

import java.lang.reflect.InvocationTargetException;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Potion
{
    public PotionType type;
    private boolean strong;
    private boolean extended;
    private boolean linger;
    private boolean splash;
    
    public Potion(final PotionType potionType) {
        this(potionType, false, false, false, false);
    }
    
    public Potion(final PotionType potionType, final boolean strong) {
        this(potionType);
        if (potionType == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        if (potionType != PotionType.WATER) {
            throw new IllegalArgumentException("Water bottles cannot be strong!");
        }
        this.strong = strong;
    }
    
    public Potion(final PotionType type, final boolean strong, final boolean extended, final boolean linger, final boolean splash) {
        this.type = type;
        this.strong = strong;
        this.extended = extended;
        this.linger = linger;
        this.splash = splash;
    }
    
    public Potion splash() {
        this.setSplash(true);
        return this;
    }
    
    public Potion extend() {
        this.setHasExtendedDuration(true);
        return this;
    }
    
    public Potion linger() {
        this.setLinger(true);
        return this;
    }
    
    public Potion strong() {
        this.setStrong(true);
        return this;
    }
    
    public PotionType getType() {
        return this.type;
    }
    
    public void setType(final PotionType type) {
        this.type = type;
    }
    
    public boolean isStrong() {
        return this.strong;
    }
    
    public void setStrong(final boolean strong) {
        this.strong = strong;
    }
    
    public boolean isExtendedDuration() {
        return this.extended;
    }
    
    public void setHasExtendedDuration(final boolean extended) {
        this.extended = extended;
    }
    
    public boolean isLinger() {
        return this.linger;
    }
    
    public void setLinger(final boolean linger) {
        this.linger = linger;
    }
    
    public boolean isSplash() {
        return this.splash;
    }
    
    public void setSplash(final boolean splash) {
        this.splash = splash;
    }
    
    public ItemStack toItemStack(final ItemStack itemStack) {
        final ItemStack itemStack2 = new ItemStack(itemStack);
        itemStack2.getItemMeta().addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        try {
            final Object invokeMethod = ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asNMSCopy", itemStack2);
            Object o = ReflectionUtils.invokeMethod(invokeMethod, invokeMethod.getClass(), "getTag", new Object[0]);
            if (o == null) {
                o = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagCompound"), (Class<?>[])new Class[0]).newInstance(new Object[0]);
            }
            String str;
            if (this.type.equals(PotionType.FIRE_RESISTANCE)) {
                if (this.extended) {
                    str = "long_fire_resistance";
                }
                else {
                    str = "fire_resistance";
                }
            }
            else if (this.type.equals(PotionType.INSTANT_DAMAGE)) {
                if (this.strong) {
                    str = "strong_harming";
                }
                else {
                    str = "harming";
                }
            }
            else if (this.type.equals(PotionType.INSTANT_HEAL)) {
                if (this.strong) {
                    str = "strong_healing";
                }
                else {
                    str = "healing";
                }
            }
            else if (this.type.equals(PotionType.INVISIBILITY)) {
                if (this.extended) {
                    str = "long_invisibility";
                }
                else {
                    str = "invisibility";
                }
            }
            else if (this.type.equals(PotionType.JUMP)) {
                if (this.extended) {
                    str = "long_leaping";
                }
                else if (this.strong) {
                    str = "strong_leaping";
                }
                else {
                    str = "leaping";
                }
            }
            else if (this.type.equals(PotionType.LUCK)) {
                str = "luck";
            }
            else if (this.type.equals(PotionType.NIGHT_VISION)) {
                if (this.extended) {
                    str = "long_night_vision";
                }
                else {
                    str = "night_vision";
                }
            }
            else if (this.type.equals(PotionType.POISON)) {
                if (this.extended) {
                    str = "long_poison";
                }
                else if (this.strong) {
                    str = "strong_poison";
                }
                else {
                    str = "poison";
                }
            }
            else if (this.type.equals(PotionType.REGEN)) {
                if (this.extended) {
                    str = "long_regeneration";
                }
                else if (this.strong) {
                    str = "strong_regeneration";
                }
                else {
                    str = "regeneration";
                }
            }
            else if (this.type.equals(PotionType.SLOWNESS)) {
                if (this.extended) {
                    str = "long_slowness";
                }
                else {
                    str = "slowness";
                }
            }
            else if (this.type.equals(PotionType.SPEED)) {
                if (this.extended) {
                    str = "long_swiftness";
                }
                else if (this.strong) {
                    str = "strong_swiftness";
                }
                else {
                    str = "swiftness";
                }
            }
            else if (this.type.equals(PotionType.STRENGTH)) {
                if (this.extended) {
                    str = "long_strength";
                }
                else if (this.strong) {
                    str = "strong_strength";
                }
                else {
                    str = "strength";
                }
            }
            else if (this.type.equals(PotionType.WATER_BREATHING)) {
                if (this.extended) {
                    str = "long_water_breathing";
                }
                else {
                    str = "water_breathing";
                }
            }
            else if (this.type.equals(PotionType.WATER)) {
                str = "water";
            }
            else if (this.type.equals(PotionType.WEAKNESS)) {
                if (this.extended) {
                    str = "long_weakness";
                }
                else {
                    str = "weakness";
                }
            }
            else if (this.type.equals(PotionType.EMPTY)) {
                str = "empty";
            }
            else if (this.type.equals(PotionType.MUNDANE)) {
                str = "mundane";
            }
            else if (this.type.equals(PotionType.THICK)) {
                str = "thick";
            }
            else {
                if (!this.type.equals(PotionType.AWKWARD)) {
                    return null;
                }
                str = "awkward";
            }
            ReflectionUtils.invokeMethod(o, "setString", "Potion", "minecraft:" + str);
            ReflectionUtils.invokeMethod(invokeMethod, "setTag", o);
            return (ItemStack)ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asBukkitCopy", invokeMethod);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | InstantiationException ex) {
            final Throwable t;
            t.printStackTrace();
            return itemStack2;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Potion) {
            final Potion potion = (Potion)o;
            if (potion.type.equals(this.type) && potion.extended == this.extended && potion.linger == this.linger && potion.splash == this.splash) {
                return true;
            }
        }
        return false;
    }
    
    public enum PotionType
    {
        FIRE_RESISTANCE("FIRE_RESISTANCE", 0), 
        INSTANT_DAMAGE("INSTANT_DAMAGE", 1), 
        INSTANT_HEAL("INSTANT_HEAL", 2), 
        INVISIBILITY("INVISIBILITY", 3), 
        JUMP("JUMP", 4), 
        LUCK("LUCK", 5), 
        NIGHT_VISION("NIGHT_VISION", 6), 
        POISON("POISON", 7), 
        REGEN("REGEN", 8), 
        SLOWNESS("SLOWNESS", 9), 
        SPEED("SPEED", 10), 
        STRENGTH("STRENGTH", 11), 
        WATER("WATER", 12), 
        WATER_BREATHING("WATER_BREATHING", 13), 
        WEAKNESS("WEAKNESS", 14), 
        EMPTY("EMPTY", 15), 
        MUNDANE("MUNDANE", 16), 
        THICK("THICK", 17), 
        AWKWARD("AWKWARD", 18);
        
        private PotionType(final String name, final int ordinal) {
        }
    }
}
