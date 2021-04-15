

package ws.billy.CookieGadgets.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import java.io.IOException;
import ws.billy.CookieGadgets.log.LoggerManager;
import java.io.FileNotFoundException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.ConfigurationSection;
import java.util.Set;
import java.util.List;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;

public class FileManager
{
    public File file;
    public FileConfiguration fileConfiguration;
    private static FileManager messages;
    private static FileManager mainMenu;
    private static FileManager hats;
    private static FileManager animatedHats;
    private static FileManager particles;
    private static FileManager suits;
    private static FileManager gadgets;
    private static FileManager pets;
    private static FileManager miniatures;
    private static FileManager morphs;
    private static FileManager banners;
    private static FileManager emotes;
    private static FileManager cloaks;
    private static FileManager customHats;
    private static FileManager customAnimatedHats;
    private static FileManager customParticles;
    private static FileManager customBanners;
    private static FileManager customEmotes;
    private static FileManager mysteryBoxes;
    private static FileManager animations;
    private static FileManager customLoots;
    private static FileManager mysteryVault;
    private static FileManager petSystem;
    
    static {
        FileManager.messages = new FileManager("messages");
        FileManager.mainMenu = new FileManager("/categories/mainmenu");
        FileManager.hats = new FileManager("/categories/hats");
        FileManager.animatedHats = new FileManager("/categories/animated hats");
        FileManager.particles = new FileManager("/categories/particles");
        FileManager.suits = new FileManager("/categories/suits");
        FileManager.gadgets = new FileManager("/categories/gadgets");
        FileManager.pets = new FileManager("/categories/pets");
        FileManager.miniatures = new FileManager("/categories/miniatures");
        FileManager.morphs = new FileManager("/categories/morphs");
        FileManager.banners = new FileManager("/categories/banners");
        FileManager.emotes = new FileManager("/categories/emotes");
        FileManager.cloaks = new FileManager("/categories/cloaks");
        FileManager.customHats = new FileManager("/custom cosmetics/custom hats");
        FileManager.customAnimatedHats = new FileManager("/custom cosmetics/custom animated hats");
        FileManager.customParticles = new FileManager("/custom cosmetics/custom particles");
        FileManager.customBanners = new FileManager("/custom cosmetics/custom banners");
        FileManager.customEmotes = new FileManager("/custom cosmetics/custom emotes");
        FileManager.mysteryBoxes = new FileManager("mystery boxes/mystery boxes");
        FileManager.animations = new FileManager("mystery boxes/animations");
        FileManager.customLoots = new FileManager("mystery boxes/custom loots");
        FileManager.mysteryVault = new FileManager("/mystery vaults");
        FileManager.petSystem = new FileManager("/pet system");
    }
    
    private FileManager(final String obj) {
        this.file = null;
        if (!CookieGadgets.getInstance().getDataFolder().exists()) {
            CookieGadgets.getInstance().getDataFolder().mkdir();
        }
        final File file = new File(CookieGadgets.getInstance().getDataFolder(), "/categories");
        if (!file.exists()) {
            file.mkdirs();
        }
        final File file2 = new File(CookieGadgets.getInstance().getDataFolder(), "/custom cosmetics");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        final File file3 = new File(CookieGadgets.getInstance().getDataFolder(), "/mystery boxes");
        if (!file3.exists()) {
            file3.mkdirs();
        }
        this.file = new File(CookieGadgets.getInstance().getDataFolder(), String.valueOf(obj) + ".yml");
        this.fileConfiguration = loadConfiguration(this.file);
    }
    
    private FileManager(final String obj, final String str) {
        this.file = null;
        if (!CookieGadgets.getInstance().getDataFolder().exists()) {
            CookieGadgets.getInstance().getDataFolder().mkdir();
        }
        this.file = new File(CookieGadgets.getInstance().getDataFolder(), String.valueOf(obj) + "." + str);
        this.fileConfiguration = loadConfiguration(this.file);
    }
    
    public static CustomConfiguration getConfigFile() {
        return CookieGadgets.getInstance().getConfig();
    }
    
    public static FileManager getMessagesFile() {
        return FileManager.messages;
    }
    
    public static FileManager getMainMenuFile() {
        return FileManager.mainMenu;
    }
    
    public static FileManager getHatsFile() {
        return FileManager.hats;
    }
    
    public static FileManager getAnimatedHatsFile() {
        return FileManager.animatedHats;
    }
    
    public static FileManager getParticlesFile() {
        return FileManager.particles;
    }
    
    public static FileManager getSuitsFile() {
        return FileManager.suits;
    }
    
    public static FileManager getGadgetsFile() {
        return FileManager.gadgets;
    }
    
    public static FileManager getPetsFile() {
        return FileManager.pets;
    }
    
    public static FileManager getMiniaturesFile() {
        return FileManager.miniatures;
    }
    
    public static FileManager getMorphsFile() {
        return FileManager.morphs;
    }
    
    public static FileManager getBannersFile() {
        return FileManager.banners;
    }
    
    public static FileManager getEmotesFile() {
        return FileManager.emotes;
    }
    
    public static FileManager getCloaksFile() {
        return FileManager.cloaks;
    }
    
    public static FileManager getCustomHatsFile() {
        return FileManager.customHats;
    }
    
    public static FileManager getCustomAnimatedHatsFile() {
        return FileManager.customAnimatedHats;
    }
    
    public static FileManager getCustomParticlesFile() {
        return FileManager.customParticles;
    }
    
    public static FileManager getCustomBannersFile() {
        return FileManager.customBanners;
    }
    
    public static FileManager getCustomEmotesFile() {
        return FileManager.customEmotes;
    }
    
    public static FileManager getMysteryBoxesFile() {
        return FileManager.mysteryBoxes;
    }
    
    public static FileManager getAnimationsFile() {
        return FileManager.animations;
    }
    
    public static FileManager getCustomLootsFile() {
        return FileManager.customLoots;
    }
    
    public static FileManager getMysteryVaultFile() {
        return FileManager.mysteryVault;
    }
    
    public static FileManager getPetSystemFile() {
        return FileManager.petSystem;
    }
    
    public static FileManager getLicenseFile() {
        return new FileManager("license", "txt");
    }
    
    public void set(final String s, final Object o) {
        this.createFile();
        if (s == null) {
            return;
        }
        this.fileConfiguration.set(s, o);
        try {
            this.fileConfiguration.save(this.file);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void addDefault(final String s, final Object o) {
        this.createFile();
        if (s == null) {
            return;
        }
        if (!this.fileConfiguration.contains(s)) {
            this.set(s, o);
        }
    }
    
    public boolean contains(final String s) {
        this.createFile();
        return this.fileConfiguration.contains(s);
    }
    
    public <T> T get(final String s) {
        this.createFile();
        return (T)this.fileConfiguration.get(s);
    }
    
    public Object get(final String s, final Object o) {
        this.createFile();
        return this.fileConfiguration.get(s, o);
    }
    
    public FileConfiguration getFile() {
        this.createFile();
        return this.fileConfiguration;
    }
    
    public String getString(final String s) {
        this.createFile();
        return this.fileConfiguration.getString(s);
    }
    
    public List<String> getStringList(final String s) {
        this.createFile();
        return (List<String>)this.fileConfiguration.getStringList(s);
    }
    
    public int getInt(final String s) {
        this.createFile();
        return this.fileConfiguration.getInt(s);
    }
    
    public long getLong(final String s) {
        this.createFile();
        return this.fileConfiguration.getLong(s);
    }
    
    public boolean getBoolean(final String s) {
        this.createFile();
        return this.fileConfiguration.getBoolean(s);
    }
    
    public double getDouble(final String s) {
        this.createFile();
        return this.fileConfiguration.getDouble(s);
    }
    
    public Set<String> getKeys(final boolean b) {
        this.createFile();
        return (Set<String>)this.fileConfiguration.getKeys(b);
    }
    
    public ConfigurationSection getConfigurationSection(final String s) {
        this.createFile();
        return this.fileConfiguration.getConfigurationSection(s);
    }
    
    public ConfigurationSection createSection(final String s) {
        this.createFile();
        final ConfigurationSection section = this.fileConfiguration.createSection(s);
        this.save();
        return section;
    }
    
    public void save() {
        try {
            this.createFile();
            this.fileConfiguration.save(this.file);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void reload() {
        try {
            this.createFile();
            this.fileConfiguration = loadConfiguration(this.file);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void createFile() {
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void deleteFile() {
        if (this.file.exists()) {
            try {
                this.file.delete();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static FileConfiguration loadConfiguration(final File obj) {
        Validate.notNull((Object)obj, "File cannot be null");
        final YamlConfiguration yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(obj);
        }
        catch (FileNotFoundException ex3) {}
        catch (IOException ex) {
            LoggerManager.printLog(LoggerManager.LogLevel.SEVERE, "Cannot load " + obj, ex);
            return null;
        }
        catch (InvalidConfigurationException ex2) {
            final File dest = new File(String.valueOf(obj.getAbsolutePath().replace(".yml", "")) + ".broken." + System.currentTimeMillis() + ".yml");
            obj.renameTo(dest);
            LoggerManager.printLog(LoggerManager.LogLevel.SEVERE, "The file " + obj.getName() + " is broken, it has been renamed to " + dest.getName(), ex2.getCause());
            return null;
        }
        return (FileConfiguration)yamlConfiguration;
    }
}
