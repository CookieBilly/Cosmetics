

package ws.billy.CookieGadgets.utils.mysteryvault;

import ws.billy.CookieGadgets.utils.ServerVersion;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import java.util.List;
import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxesMessages;
import java.util.Iterator;
import ws.billy.CookieGadgets.log.LoggerManager;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.CookieGadgets;
import org.apache.commons.lang.Validate;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSArmorStand;
import java.util.ArrayList;
import org.bukkit.block.BlockFace;
import org.bukkit.Location;

public class MysteryVault
{
    private String name;
    private Location location;
    private BlockFace blockFace;
    private ArrayList<NMSArmorStand> holograms;
    private boolean displayIndividualHolograms;
    private NMSArmorStand availableMysteryBoxesHologram;
    private NMSArmorStand lootHologram;
    private boolean activated;
    
    public MysteryVault(final String name, final BlockFace blockFace, final Location location) {
        this.holograms = new ArrayList<NMSArmorStand>();
        this.displayIndividualHolograms = false;
        this.activated = false;
        if (MysteryVaultManager.vaults().contains(this)) {
            throw new IllegalArgumentException("This mystery vault already exists!");
        }
        Validate.isTrue(name != null && !name.isEmpty(), "The name can not be null or empty!");
        Validate.notNull((Object)blockFace, "BlockFace can not be null!");
        Validate.notNull((Object)location, "Location can not be null!");
        this.name = name;
        this.blockFace = blockFace;
        this.location = location;
        if (CookieGadgets.hasProtocolLibHook()) {
            this.displayIndividualHolograms = true;
        }
    }
    
    public void saveToDatabase() {
        for (final MysteryVault mysteryVault : MysteryVaultManager.vaults()) {
            if (mysteryVault.getName().equalsIgnoreCase(this.name)) {
                throw new IllegalArgumentException("Mystery vault with that name already exists!");
            }
            if (mysteryVault.getLocation().equals((Object)this.location)) {
                throw new IllegalArgumentException("You have already created mystery vault at this location!");
            }
        }
        try {
            FileManager.getMysteryVaultFile().set("Mystery-Vaults." + this.name + ".BlockFace", this.blockFace.toString());
            MysteryVaultUtils.saveLocation(this.location, FileManager.getMysteryVaultFile().createSection("Mystery-Vaults." + this.name + ".Location"));
            MysteryVaultManager.vaults().add(this);
            FileManager.getMysteryVaultFile().save();
        }
        catch (Exception ex) {
            LoggerManager.printLog(MessageType.ERROR.getFormatMessage());
            ex.printStackTrace();
        }
    }
    
    public void updateLocation(final BlockFace blockFace, final Location location) {
        Validate.notNull((Object)blockFace, "BlockFace can not be null!");
        Validate.notNull((Object)location, "Location can not be null!");
        try {
            this.blockFace = blockFace;
            this.location = location;
            FileManager.getMysteryVaultFile().set("Mystery-Vaults." + this.name + ".BlockFace", blockFace.toString());
            MysteryVaultUtils.saveLocation(location, FileManager.getMysteryVaultFile().createSection("Mystery-Vaults." + this.name + ".Location"));
            FileManager.getMysteryVaultFile().save();
        }
        catch (Exception ex) {
            LoggerManager.printLog(MessageType.ERROR.getFormatMessage());
            ex.printStackTrace();
        }
    }
    
    public void updateLocationWithoutSaving(final Location location) {
        Validate.notNull((Object)location, "Location can not be null!");
        try {
            this.location = location;
        }
        catch (Exception ex) {
            LoggerManager.printLog(MessageType.ERROR.getFormatMessage());
            ex.printStackTrace();
        }
    }
    
    public boolean delete() {
        if (FileManager.getMysteryVaultFile().get("Mystery-Vaults." + this.name) == null) {
            return false;
        }
        if (!MysteryVaultManager.vaults().contains(this)) {
            return false;
        }
        try {
            this.removeHolograms();
            this.removeLootHologram();
            this.removeAvailableMysteryBoxesHologram();
            MysteryVaultManager.vaults().remove(this);
            FileManager.getMysteryVaultFile().set("Mystery-Vaults." + this.name, null);
            return true;
        }
        catch (Exception ex) {
            LoggerManager.printLog(MessageType.ERROR.getFormatMessage());
            ex.printStackTrace();
            return false;
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public BlockFace getBlockFace() {
        return this.blockFace;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public void createHolograms() {
        if (!this.holograms.isEmpty()) {
            if (this.holograms.get(0) != null && this.holograms.get(0).isDeadNMS()) {
                this.holograms.set(0, CookieGadgets.getNMSManager().spawnNMSArmorStand(this.location.getWorld(), this.location.getX() + 0.5, this.location.getY() + EnumHologramAdditionY.getAdditionY() + 0.24, this.location.getZ() + 0.5, MysteryBoxesMessages.hologramLineOne));
            }
            if (this.holograms.get(1) != null && this.holograms.get(1).isDeadNMS()) {
                this.holograms.set(1, CookieGadgets.getNMSManager().spawnNMSArmorStand(this.location.getWorld(), this.location.getX() + 0.5, this.location.getY() + EnumHologramAdditionY.getAdditionY(), this.location.getZ() + 0.5, MysteryBoxesMessages.hologramLineTwo));
            }
            return;
        }
        final NMSArmorStand spawnNMSArmorStand = CookieGadgets.getNMSManager().spawnNMSArmorStand(this.location.getWorld(), this.location.getX() + 0.5, this.location.getY() + EnumHologramAdditionY.getAdditionY() + 0.24, this.location.getZ() + 0.5, MysteryBoxesMessages.hologramLineOne);
        final NMSArmorStand spawnNMSArmorStand2 = CookieGadgets.getNMSManager().spawnNMSArmorStand(this.location.getWorld(), this.location.getX() + 0.5, this.location.getY() + EnumHologramAdditionY.getAdditionY(), this.location.getZ() + 0.5, MysteryBoxesMessages.hologramLineTwo);
        this.holograms.add(spawnNMSArmorStand);
        this.holograms.add(spawnNMSArmorStand2);
    }
    
    public void removeHolograms() {
        for (final NMSArmorStand nmsArmorStand : this.holograms) {
            if (!nmsArmorStand.isDeadNMS()) {
                nmsArmorStand.killEntityNMS();
            }
        }
    }
    
    public List<String> getHolograms() {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<NMSArmorStand> iterator = this.holograms.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().getCustomNameNMS());
        }
        return list;
    }
    
    public NMSArmorStand getAvailableMysteryBoxesHologram() {
        return this.availableMysteryBoxesHologram;
    }
    
    public void createAvailableMysteryBoxesHologram() {
        if (this.availableMysteryBoxesHologram != null && !this.availableMysteryBoxesHologram.isDeadNMS()) {
            this.availableMysteryBoxesHologram.killEntityNMS();
        }
        if (!this.displayIndividualHolograms) {
            return;
        }
        if (!MysteryVaultManager.isIndividualHologramsEnabled()) {
            return;
        }
        String obj = MysteryVaultManager.getAvailableMysteryBoxesHolograms().get(0);
        if (obj == null) {
            obj = "";
        }
        this.availableMysteryBoxesHologram = CookieGadgets.getNMSManager().spawnNMSArmorStand(this.location.getWorld(), this.location.getX() + 0.5, this.location.getY() + EnumHologramAdditionY.getAdditionY() + 0.55, this.location.getZ() + 0.5, ChatUtil.format(String.valueOf(obj) + ChatUtil.randomColor("")));
    }
    
    public void refreshAvailableMysteryBoxesHologram(final int index) {
        if (!this.displayIndividualHolograms) {
            return;
        }
        if (!MysteryVaultManager.isIndividualHologramsEnabled()) {
            return;
        }
        if (this.availableMysteryBoxesHologram == null) {
            this.createAvailableMysteryBoxesHologram();
            return;
        }
        if (MysteryVaultManager.getAvailableMysteryBoxesHolograms().size() < index + 1) {
            return;
        }
        String obj = MysteryVaultManager.getAvailableMysteryBoxesHolograms().get(index);
        if (obj == null) {
            obj = "";
        }
        this.availableMysteryBoxesHologram.setCustomNameNMS(ChatUtil.format(String.valueOf(obj) + ChatUtil.randomColor("")));
    }
    
    public void removeAvailableMysteryBoxesHologram() {
        if (this.availableMysteryBoxesHologram != null && !this.availableMysteryBoxesHologram.isDeadNMS()) {
            this.availableMysteryBoxesHologram.killEntityNMS();
            this.availableMysteryBoxesHologram = null;
        }
    }
    
    public boolean displayIndividualHolograms() {
        return this.displayIndividualHolograms;
    }
    
    public void enableAvailableMysteryBoxesHolograms(final boolean b) {
        if (this.displayIndividualHolograms) {
            if (!b) {
                this.displayIndividualHolograms = false;
                this.removeAvailableMysteryBoxesHologram();
            }
        }
        else if (b) {
            this.displayIndividualHolograms = true;
            this.createAvailableMysteryBoxesHologram();
        }
    }
    
    public void createLootHologram(final String s) {
        this.removeLootHologram();
        double n = EnumHologramAdditionY.getAdditionY() + 0.86;
        if (!this.displayIndividualHolograms || !MysteryVaultManager.isIndividualHologramsEnabled()) {
            n = EnumHologramAdditionY.getAdditionY() + 0.55;
        }
        this.lootHologram = CookieGadgets.getNMSManager().spawnNMSArmorStand(this.location.getWorld(), this.location.getX() + 0.5, this.location.getY() + n, this.location.getZ() + 0.5, (s == null) ? "&cERROR" : s);
    }
    
    public void removeLootHologram() {
        if (this.lootHologram != null) {
            if (!this.lootHologram.isDeadNMS()) {
                this.lootHologram.killEntityNMS();
            }
            this.lootHologram = null;
        }
    }
    
    public void removeLootHologram(final int n) {
        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            if (!this.activated) {
                if (this.lootHologram != null) {
                    if (!this.lootHologram.isDeadNMS()) {
                        this.lootHologram.killEntityNMS();
                    }
                    this.lootHologram = null;
                }
            }
        }, (long)(n * 20));
    }
    
    public boolean isActivated() {
        return this.activated;
    }
    
    public void setActivate(final boolean activated) {
        this.activated = activated;
    }
    
    private enum EnumHologramAdditionY
    {
        v1_8("v1_8", 0, 0.26, 8), 
        v1_9("v1_9", 1, 1.14, 9), 
        v1_10("v1_10", 2, 1.14, 10);
        
        private double increasement;
        private int requiredVersion;
        private static double incrementY;
        
        private EnumHologramAdditionY(final String name, final int ordinal, final double increasement, final int requiredVersion) {
            this.increasement = 1.21;
            this.increasement = increasement;
            this.requiredVersion = requiredVersion;
        }
        
        public double getIncreasement() {
            return this.increasement;
        }
        
        public int getRequiredVersion() {
            return this.requiredVersion;
        }
        
        public static double getAdditionY() {
            if (EnumHologramAdditionY.incrementY != 0.0) {
                return EnumHologramAdditionY.incrementY;
            }
            if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Holograms.Hologram-Height") != null) {
                return EnumHologramAdditionY.incrementY = FileManager.getMysteryBoxesFile().getDouble("Mystery-Boxes.Holograms.Hologram-Height");
            }
            if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Holograms.Y-Increment") != null) {
                EnumHologramAdditionY.incrementY = FileManager.getMysteryBoxesFile().getDouble("Mystery-Boxes.Holograms.Y-Increment");
                FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Holograms.Hologram-Height", EnumHologramAdditionY.incrementY);
                FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Holograms.Y-Increment", null);
                return EnumHologramAdditionY.incrementY;
            }
            EnumHologramAdditionY[] values;
            for (int length = (values = values()).length, i = 0; i < length; ++i) {
                final EnumHologramAdditionY enumHologramAdditionY = values[i];
                if (enumHologramAdditionY.getRequiredVersion() == ServerVersion.getServerVersion().getCurrentVersionNumber()) {
                    EnumHologramAdditionY.incrementY = enumHologramAdditionY.getIncreasement();
                    break;
                }
            }
            if (EnumHologramAdditionY.incrementY == 0.0) {
                EnumHologramAdditionY.incrementY = 1.21;
            }
            FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Holograms.Hologram-Height", EnumHologramAdditionY.incrementY);
            return EnumHologramAdditionY.incrementY;
        }
    }
}
