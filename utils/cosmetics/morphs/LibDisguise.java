

package ws.billy.CookieGadgets.utils.cosmetics.morphs;

import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.AnimalColor;
import me.libraryaddict.disguise.disguisetypes.watchers.SheepWatcher;
import org.bukkit.plugin.Plugin;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.PlayerUtils;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import org.bukkit.scheduler.BukkitRunnable;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import org.bukkit.inventory.ItemStack;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PigWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.CreeperWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.BlazeWatcher;
import org.bukkit.inventory.EquipmentSlot;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.GColor;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.List;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.GMaterial;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.entity.Entity;
import me.libraryaddict.disguise.DisguiseAPI;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.CookieGadgets;

public class LibDisguise implements GDisguise
{
    private static boolean showNameForDisguise;
    
    static {
        LibDisguise.showNameForDisguise = CookieGadgets.getCookieGadgetsData().showNameForMobDisguise();
    }
    
    @Override
    public boolean isDisguised(final Player player) {
        return DisguiseAPI.isDisguised((Entity)player);
    }
    
    @Override
    public GDisguiseType getDisguise(final Player player) {
        return GDisguiseType.valueOfLibDisguiseType(DisguiseAPI.getDisguise((Entity)player).getType());
    }
    
    @Override
    public boolean disguise(final Player player, final GDisguiseType gDisguiseType) {
        if (player == null) {
            return false;
        }
        if (gDisguiseType == null) {
            return false;
        }
        if (DisguiseAPI.isDisguised((Entity)player)) {
            DisguiseAPI.undisguiseToAll((Entity)player);
        }
        final MobDisguise mobDisguise = new MobDisguise(gDisguiseType.getLibDisguiseType());
        mobDisguise.setModifyBoundingBox(false);
        final LivingWatcher watcher = mobDisguise.getWatcher();
        ((FlagWatcher)watcher).setCustomNameVisible(LibDisguise.showNameForDisguise);
        if (LibDisguise.showNameForDisguise) {
            ((FlagWatcher)watcher).setCustomName(player.getName());
        }
        if (gDisguiseType == GDisguiseType.GRINCH) {
            final ItemStack item = ItemUtils.item("Grinch", new GMaterial("head:19c89a889c4bb421ea9c56cbeae7af1e6d85f4faff34acba72e1a8d5c4116a8"), null);
            final ItemStack item2 = ItemUtils.item("Grinch", new GMaterial(EnumMaterial.LEATHER_CHESTPLATE, new GColor("#993333")), null);
            final ItemStack item3 = ItemUtils.item("Grinch", new GMaterial(EnumMaterial.LEATHER_LEGGINGS, new GColor("#993333")), null);
            final ItemStack item4 = ItemUtils.item("Grinch", new GMaterial(EnumMaterial.LEATHER_BOOTS, new GColor("#150000")), null);
            final ItemStack setNBTTag = CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(item, "Cosmetics", "true"), "Category", Category.MORPHS.getNBTTag());
            final ItemStack setNBTTag2 = CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(item2, "Cosmetics", "true"), "Category", Category.MORPHS.getNBTTag());
            final ItemStack setNBTTag3 = CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(item3, "Cosmetics", "true"), "Category", Category.MORPHS.getNBTTag());
            final ItemStack setNBTTag4 = CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(item4, "Cosmetics", "true"), "Category", Category.MORPHS.getNBTTag());
            ((FlagWatcher)watcher).setItemStack(EquipmentSlot.HEAD, setNBTTag);
            ((FlagWatcher)watcher).setItemStack(EquipmentSlot.CHEST, setNBTTag2);
            ((FlagWatcher)watcher).setItemStack(EquipmentSlot.LEGS, setNBTTag3);
            ((FlagWatcher)watcher).setItemStack(EquipmentSlot.FEET, setNBTTag4);
        }
        else if (gDisguiseType == GDisguiseType.SKELETON) {
            ((FlagWatcher)watcher).setItemInHand(ItemUtils.item("Skeleton", EnumMaterial.BOW));
        }
        if (CookieGadgets.getPlayerManager(player).canSeeSelfMorph()) {
            mobDisguise.setViewSelfDisguise(true);
            this.setViewDisguiseToggled(player, true);
        }
        else {
            mobDisguise.setViewSelfDisguise(false);
            this.setViewDisguiseToggled(player, false);
        }
        if (gDisguiseType == GDisguiseType.BLAZE) {
            ((BlazeWatcher)mobDisguise.getWatcher()).setBurning(true);
        }
        else if (gDisguiseType == GDisguiseType.CREEPER) {
            ((CreeperWatcher)mobDisguise.getWatcher()).setPowered(true);
        }
        else if (gDisguiseType == GDisguiseType.PIG) {
            ((PigWatcher)mobDisguise.getWatcher()).setSaddled(true);
        }
        DisguiseAPI.disguiseToAll((Entity)player, (Disguise)mobDisguise);
        return true;
    }
    
    @Override
    public void setViewDisguiseToggled(final Player player, final boolean b) {
        DisguiseAPI.setViewDisguiseToggled((Entity)player, b);
    }
    
    @Override
    public boolean disguiseCreeper(final Player player) {
        if (player == null) {
            return false;
        }
        final MobDisguise mobDisguise = new MobDisguise(DisguiseType.CREEPER);
        mobDisguise.setModifyBoundingBox(false);
        final LivingWatcher watcher = mobDisguise.getWatcher();
        ((FlagWatcher)watcher).setCustomNameVisible(LibDisguise.showNameForDisguise);
        if (LibDisguise.showNameForDisguise) {
            ((FlagWatcher)watcher).setCustomName(player.getName());
        }
        if (CookieGadgets.getPlayerManager(player).canSeeSelfMorph()) {
            mobDisguise.setViewSelfDisguise(true);
            CookieGadgets.getGDisguise().setViewDisguiseToggled(player, true);
        }
        else {
            mobDisguise.setViewSelfDisguise(false);
            CookieGadgets.getGDisguise().setViewDisguiseToggled(player, false);
        }
        DisguiseAPI.disguiseToAll((Entity)player, (Disguise)mobDisguise);
        final CreeperWatcher creeperWatcher = (CreeperWatcher)mobDisguise.getWatcher();
        creeperWatcher.setIgnited(true);
        new BukkitRunnable() {
            public void run() {
                if (!player.isOnline() || CookieGadgets.getPlayerManager(player).getCurrentMorph() == null || CookieGadgets.getPlayerManager(player).getCurrentMorph().getType() != MorphType.CREEPER) {
                    this.cancel();
                    return;
                }
                ParticleEffect.EXPLOSION_HUGE.display(player.getLocation());
                ParticleEffect.SMOKE_LARGE.display(player.getLocation());
                SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(player.getLocation());
                if (FileManager.getMorphsFile().getBoolean("Morphs.Creeper.Affect-Players")) {
                    for (final Player player : PlayerUtils.getNearbyPlayers(player.getLocation(), 3.0)) {
                        if (player instanceof Player && player != player && !player.hasMetadata("NPC")) {
                            MathUtil.applyVelocity((Entity)player, new Vector(player.getVelocity().getX(), 1.0, player.getVelocity().getZ()).add(MathUtil.getRandomCircleVector().multiply(0.7)));
                        }
                    }
                }
                if (CookieGadgets.getGDisguise().getDisguise(player) == null) {
                    return;
                }
                if (CookieGadgets.getGDisguise().getDisguise(player) != GDisguiseType.CREEPER) {
                    return;
                }
                creeperWatcher.setIgnited(false);
                creeperWatcher.setPowered(true);
                mobDisguise.setModifyBoundingBox(false);
                final LivingWatcher watcher = mobDisguise.getWatcher();
                ((FlagWatcher)watcher).setCustomNameVisible(LibDisguise.showNameForDisguise);
                if (LibDisguise.showNameForDisguise) {
                    ((FlagWatcher)watcher).setCustomName(player.getName());
                }
                if (CookieGadgets.getPlayerManager(player).canSeeSelfMorph()) {
                    mobDisguise.setViewSelfDisguise(true);
                    CookieGadgets.getGDisguise().setViewDisguiseToggled(player, true);
                }
                else {
                    mobDisguise.setViewSelfDisguise(false);
                    CookieGadgets.getGDisguise().setViewDisguiseToggled(player, false);
                }
                DisguiseAPI.disguiseToAll((Entity)player, (Disguise)mobDisguise);
            }
        }.runTaskLater((Plugin)CookieGadgets.getInstance(), 30L);
        return true;
    }
    
    @Override
    public boolean disguiseSheep(final Player player) {
        if (player == null) {
            return false;
        }
        SoundEffect.ENTITY_SHEEP_AMBIENT.playSound(player);
        final String[] array = { "BLACK", "BLUE", "BROWN", "CYAN", "GRAY", "GREEN", "LIGHT_BLUE", "LIME", "MAGENTA", "ORANGE", "PINK", "PURPLE", "RED", "SILVER", "WHITE", "YELLOW" };
        final MobDisguise mobDisguise = new MobDisguise(DisguiseType.SHEEP);
        mobDisguise.setModifyBoundingBox(false);
        final LivingWatcher watcher = mobDisguise.getWatcher();
        ((FlagWatcher)watcher).setCustomNameVisible(LibDisguise.showNameForDisguise);
        if (LibDisguise.showNameForDisguise) {
            ((FlagWatcher)watcher).setCustomName(player.getName());
        }
        if (CookieGadgets.getPlayerManager(player).canSeeSelfMorph()) {
            mobDisguise.setViewSelfDisguise(true);
            CookieGadgets.getGDisguise().setViewDisguiseToggled(player, true);
        }
        else {
            mobDisguise.setViewSelfDisguise(false);
            CookieGadgets.getGDisguise().setViewDisguiseToggled(player, false);
        }
        DisguiseAPI.disguiseToAll((Entity)player, (Disguise)mobDisguise);
        new BukkitRunnable() {
            int step = 0;
            private final /* synthetic */ SheepWatcher val$watcher = (SheepWatcher)mobDisguise.getWatcher();
            
            public void run() {
                ++this.step;
                if (!player.isOnline() || CookieGadgets.getPlayerManager(player).getCurrentMorph() == null || CookieGadgets.getPlayerManager(player).getCurrentMorph().getType() != MorphType.SHEEP) {
                    this.step = 33;
                    this.cancel();
                    return;
                }
                if (this.step <= 32) {
                    final AnimalColor white = AnimalColor.WHITE;
                    AnimalColor color;
                    try {
                        color = AnimalColor.valueOf(array[CookieGadgets.random().nextInt(array.length)]);
                    }
                    catch (IllegalArgumentException ex) {
                        color = AnimalColor.LIGHT_GRAY;
                    }
                    this.val$watcher.setColor(color);
                }
                else {
                    this.val$watcher.setColor(AnimalColor.WHITE);
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 1L, 5L);
        return true;
    }
    
    @Override
    public boolean undisguise(final Player player) {
        if (player == null) {
            return false;
        }
        DisguiseAPI.undisguiseToAll((Entity)player);
        return true;
    }
}
