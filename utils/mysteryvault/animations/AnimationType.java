

package ws.billy.CookieGadgets.utils.mysteryvault.animations;

import java.lang.reflect.InvocationTargetException;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.types.Animation;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Quality;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.player.PlayerManager;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.types.HolidayAnimation;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.types.HalloweenAnimation;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.types.SummerAnimation;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.types.CraftingAnimation;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.types.StarAnimation;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.types.CountdownAnimation;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.types.NormalAnimation;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.types.NoneAnimation;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;

public class AnimationType
{
    private static final List<AnimationType> ENABLED;
    private static final List<AnimationType> VALUES;
    public static final AnimationType NONE;
    public static final AnimationType NORMAL;
    public static final AnimationType COUNTDOWN;
    public static final AnimationType STAR;
    public static final AnimationType CRAFTING;
    public static final AnimationType SUMMER;
    public static final AnimationType HALLOWEEN;
    public static final AnimationType HOLIDAY;
    public static final AnimationType RANDOM;
    private String name;
    private String displayName;
    private GMaterial material;
    private List<String> lore;
    private GMiniBlock miniBlock;
    private GQualityBlock qualityBlock;
    private String permission;
    private long repeatDelay;
    private long durationTime;
    private Class<?> clazz;
    private boolean isEnable;
    
    static {
        ENABLED = new ArrayList<AnimationType>();
        VALUES = new ArrayList<AnimationType>();
        NONE = new AnimationType("None", "&aAnimation: None", new GMaterial(EnumMaterial.BARRIER), Arrays.asList("&7Set your mystery vault", "&7animation to &aNone&7."), new GMiniBlock(new GMaterial(EnumMaterial.ENDER_CHEST)), "CookieGadgets.animations.none", 1L, 20L, NoneAnimation.class);
        NORMAL = new AnimationType("Normal", "&aAnimation: Normal", new GMaterial(EnumMaterial.ENDER_CHEST), Arrays.asList("&7Set your mystery vault", "&7animation to &aNormal&7."), new GMiniBlock(new GMaterial(EnumMaterial.ENDER_CHEST)), "CookieGadgets.animations.normal", 1L, 120L, NormalAnimation.class);
        COUNTDOWN = new AnimationType("CountDown", "&aAnimation: Countdown", new GMaterial(EnumMaterial.CLOCK), Arrays.asList("&7Set your mystery vault", "&7animation to &aCountdown&7."), new GMiniBlock(new GMaterial(EnumMaterial.ENDER_CHEST)), "CookieGadgets.animations.countdown", 1L, 260L, CountdownAnimation.class);
        STAR = new AnimationType("Star", "&aAnimation: Star", new GMaterial(EnumMaterial.NETHER_STAR), Arrays.asList("&7Set your mystery vault", "&7animation to &aStar&7."), new GMiniBlock(new GMaterial(EnumMaterial.ENDER_CHEST)), new GQualityBlock(new GMaterial(EnumMaterial.NETHER_STAR)), "CookieGadgets.animations.star", 1L, 219L, StarAnimation.class);
        CRAFTING = new AnimationType("Crafting", "&aAnimation: Crafting", new GMaterial(EnumMaterial.CRAFTING_TABLE), Arrays.asList("&7Set your mystery vault", "&7animation to &aCrafting&7."), new GMiniBlock(new GMaterial(EnumMaterial.ENDER_CHEST)), new GQualityBlock(new GMaterial(EnumMaterial.CRAFTING_TABLE), 270, 0, 45), "CookieGadgets.animations.crafting", 1L, 219L, CraftingAnimation.class);
        SUMMER = new AnimationType("Summer", "&aAnimation: Summer", new GMaterial(EnumMaterial.SUNFLOWER), Arrays.asList("&7Set your mystery vault", "&7animation to &aSummer&7."), new GMiniBlock(new GMaterial("head:5a5ab05ea254c32e3c48f3fdcf9fd9d77d3cba04e6b5ec2e68b3cbdcfac3fd")), new GQualityBlock(new GMaterial(EnumMaterial.SUNFLOWER)), "CookieGadgets.animations.summer", 1L, 219L, SummerAnimation.class);
        HALLOWEEN = new AnimationType("Halloween", "&aAnimation: Halloween", new GMaterial(EnumMaterial.JACK_O_LANTERN), Arrays.asList("&7Set your mystery vault", "&7animation to &aHalloween&7."), new GMiniBlock(new GMaterial(EnumMaterial.JACK_O_LANTERN)), "CookieGadgets.animations.halloween", 1L, 159L, HalloweenAnimation.class);
        HOLIDAY = new AnimationType("Holiday", "&aAnimation: Holiday", new GMaterial(EnumMaterial.SNOWBALL), Arrays.asList("&7Set your mystery vault", "&7animation to &aHoliday&7."), new GMiniBlock(new GMaterial("head:f5612dc7b86d71afc1197301c15fd979e9f39e7b1f41d8f1ebdf8115576e2e")), new GQualityBlock(new GMaterial(EnumMaterial.SNOWBALL)), "CookieGadgets.animations.holiday", 1L, 219L, HolidayAnimation.class);
        RANDOM = new AnimationType("Random", "Random");
    }
    
    private AnimationType(final String name, final String displayName) {
        this.name = name;
        this.displayName = displayName;
        this.permission = EnumPermission.RANDOM_MYSTERY_VAULT_ANIMATIONS.getPermission();
        this.isEnable = false;
        if (!AnimationType.VALUES.contains(this)) {
            AnimationType.VALUES.add(this);
        }
    }
    
    private AnimationType(final String name, final String displayName, final GMaterial material, final List<String> lore, final GMiniBlock miniBlock, final String permission, final long repeatDelay, final long durationTime, final Class<?> clazz) {
        this.name = name;
        if (FileManager.getAnimationsFile().get("Animations." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getAnimationsFile().getString("Animations." + this.name + ".Name");
        }
        if (FileManager.getAnimationsFile().get("Animations." + this.name + ".Material") == null) {
            this.material = material;
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Material", this.material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getAnimationsFile().getString("Animations." + this.name + ".Material"));
        }
        if (FileManager.getAnimationsFile().get("Animations." + this.name + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getAnimationsFile().getBoolean("Animations." + this.name + ".Enabled");
        }
        if (FileManager.getAnimationsFile().get("Animations." + this.name + ".Mini-Block.Material") == null) {
            this.miniBlock = miniBlock;
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Mini-Block.Material", this.miniBlock.getMaterial().getCombinedMaterial());
        }
        else {
            this.miniBlock = new GMiniBlock(new GMaterial(FileManager.getAnimationsFile().getString("Animations." + this.name + ".Mini-Block.Material")));
        }
        this.qualityBlock = null;
        if (FileManager.getAnimationsFile().get("Animations." + this.name + ".Lore") == null) {
            if ((this.lore = lore) == null) {
                FileManager.getAnimationsFile().set("Animations." + this.name + ".Lore", "");
            }
            else {
                FileManager.getAnimationsFile().set("Animations." + this.name + ".Lore", this.lore);
            }
        }
        else {
            this.lore = FileManager.getAnimationsFile().getStringList("Animations." + this.name + ".Lore");
        }
        this.permission = permission;
        this.repeatDelay = repeatDelay;
        this.durationTime = durationTime;
        this.clazz = clazz;
        if (!AnimationType.VALUES.contains(this)) {
            AnimationType.VALUES.add(this);
        }
    }
    
    private AnimationType(final String name, final String displayName, final GMaterial material, final List<String> lore, final GMiniBlock miniBlock, final GQualityBlock gQualityBlock, final String permission, final long repeatDelay, final long durationTime, final Class<?> clazz) {
        this.name = name;
        if (FileManager.getAnimationsFile().get("Animations." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getAnimationsFile().getString("Animations." + this.name + ".Name");
        }
        if (FileManager.getAnimationsFile().get("Animations." + this.name + ".Material") == null) {
            this.material = material;
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Material", this.material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getAnimationsFile().getString("Animations." + this.name + ".Material"));
        }
        if (FileManager.getAnimationsFile().get("Animations." + this.name + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getAnimationsFile().getBoolean("Animations." + this.name + ".Enabled");
        }
        if (FileManager.getAnimationsFile().get("Animations." + this.name + ".Mini-Block.Material") == null) {
            this.miniBlock = miniBlock;
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Mini-Block.Material", this.miniBlock.getMaterial().getCombinedMaterial());
        }
        else {
            this.miniBlock = new GMiniBlock(new GMaterial(FileManager.getAnimationsFile().getString("Animations." + this.name + ".Mini-Block.Material")));
        }
        if (FileManager.getAnimationsFile().get("Animations." + this.name + ".Mystery-Box-Quality-Block.Material") == null) {
            this.qualityBlock = gQualityBlock;
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Mystery-Box-Quality-Block.Material", this.qualityBlock.getMaterial().getCombinedMaterial());
        }
        else if (!gQualityBlock.hasRotate()) {
            this.qualityBlock = new GQualityBlock(new GMaterial(FileManager.getAnimationsFile().getString("Animations." + this.name + ".Mystery-Box-Quality-Block.Material")));
        }
        if (gQualityBlock.hasRotate() && FileManager.getAnimationsFile().get("Animations." + this.name + ".Mystery-Box-Quality-Block.Rotational-Angle") == null) {
            this.qualityBlock = gQualityBlock;
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Mystery-Box-Quality-Block.Rotational-Angle.X", this.qualityBlock.getRotationalAngleX());
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Mystery-Box-Quality-Block.Rotational-Angle.Y", this.qualityBlock.getRotationalAngleY());
            FileManager.getAnimationsFile().set("Animations." + this.name + ".Mystery-Box-Quality-Block.Rotational-Angle.Z", this.qualityBlock.getRotationalAngleZ());
        }
        else if (gQualityBlock.hasRotate()) {
            this.qualityBlock = new GQualityBlock(new GMaterial(FileManager.getAnimationsFile().getString("Animations." + this.name + ".Mystery-Box-Quality-Block.Material")), FileManager.getAnimationsFile().getInt("Animations." + this.name + ".Mystery-Box-Quality-Block.Rotational-Angle.X"), FileManager.getAnimationsFile().getInt("Animations." + this.name + ".Mystery-Box-Quality-Block.Rotational-Angle.Y"), FileManager.getAnimationsFile().getInt("Animations." + this.name + ".Mystery-Box-Quality-Block.Rotational-Angle.Z"));
        }
        if (FileManager.getAnimationsFile().get("Animations." + this.name + ".Lore") == null) {
            if ((this.lore = lore) == null) {
                FileManager.getAnimationsFile().set("Animations." + this.name + ".Lore", "");
            }
            else {
                FileManager.getAnimationsFile().set("Animations." + this.name + ".Lore", this.lore);
            }
        }
        else {
            this.lore = FileManager.getAnimationsFile().getStringList("Animations." + this.name + ".Lore");
        }
        this.permission = permission;
        this.repeatDelay = repeatDelay;
        this.durationTime = durationTime;
        this.clazz = clazz;
        if (!AnimationType.VALUES.contains(this)) {
            AnimationType.VALUES.add(this);
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDisplayName() {
        return ChatUtil.format(this.displayName);
    }
    
    public String getDisplayNameStripColor() {
        return ChatUtil.stripColor(this.displayName);
    }
    
    public GMaterial getMaterial() {
        return this.material;
    }
    
    public List<String> getLore() {
        return this.lore;
    }
    
    public GMiniBlock getMiniBlock() {
        return this.miniBlock;
    }
    
    public GQualityBlock getQualityBlock() {
        return this.qualityBlock;
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public long getRepeatDelay() {
        return this.repeatDelay;
    }
    
    public long getDurationTime() {
        return this.durationTime;
    }
    
    public boolean isEnabled() {
        return this.isEnable;
    }
    
    public static List<AnimationType> enabled() {
        return AnimationType.ENABLED;
    }
    
    public static List<AnimationType> values() {
        return AnimationType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final AnimationType animationType : values()) {
            if (animationType.isEnabled() && !AnimationType.ENABLED.contains(animationType)) {
                AnimationType.ENABLED.add(animationType);
            }
        }
    }
    
    public Animation equip(final PlayerManager playerManager, final MysteryVault mysteryVault, final Quality quality, final long l) {
        Animation animation = null;
        try {
            animation = (Animation)this.clazz.getDeclaredConstructor(PlayerManager.class, MysteryVault.class, Quality.class, Long.TYPE).newInstance(playerManager, mysteryVault, quality, l);
        }
        catch (InstantiationException ex) {
            ex.printStackTrace();
        }
        catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        catch (InvocationTargetException ex3) {
            ex3.printStackTrace();
        }
        catch (NoSuchMethodException ex4) {
            ex4.printStackTrace();
        }
        return animation;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static AnimationType valueOf(final String anotherString) {
        for (final AnimationType animationType : values()) {
            if (animationType.getName().equalsIgnoreCase(anotherString)) {
                return animationType;
            }
        }
        return null;
    }
}
