

package ws.billy.CookieGadgets.configuration;

import org.bukkit.plugin.Plugin;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.sql.ResultSet;
import ws.billy.CookieGadgets.database.query.SelectQuery;
import org.bukkit.configuration.file.FileConfiguration;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.GPet;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import java.util.Map;
import ws.billy.CookieGadgets.utils.GTimestamp;
import java.sql.Timestamp;
import java.sql.SQLException;
import ws.billy.CookieGadgets.log.LoggerManager;
import java.io.File;
import ws.billy.CookieGadgets.CookieGadgets;

public class OldConfigurationManager
{
    private static CustomConfiguration config;
    private static FileManager messages;
    private static FileManager mysteryVault;
    private static boolean executed;
    
    static {
        OldConfigurationManager.config = FileManager.getConfigFile();
        OldConfigurationManager.messages = FileManager.getMessagesFile();
        OldConfigurationManager.mysteryVault = FileManager.getMysteryVaultFile();
        OldConfigurationManager.executed = false;
    }
    
    public static void migrateUserData(final boolean b) {
        if (OldConfigurationManager.executed) {
            return;
        }
        OldConfigurationManager.executed = true;
        final long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        final File file = new File(CookieGadgets.getInstance().getDataFolder(), "/userdata");
        if (file.exists()) {
            LoggerManager.printLogWithHeader(LoggerManager.LogLevel.INFO, "------------------------------------------------------", "Migrating user's data from flat file to SQLite.", "Processing in async task, it might takes a while.");
            final File[] array = new File(CookieGadgets.getInstance().getDataFolder(), "/userdata/").listFiles().clone();
            LoggerManager.info("There are " + array.length + " file(s) waiting for migration.");
            String string = "unknown";
            String string2 = "unknown";
            int int1 = 0;
            int int2 = 0;
            int int3 = 0;
            int int4 = 0;
            boolean boolean1 = true;
            boolean boolean2 = false;
            String string3 = "";
            String string4 = "Normal";
            int int5 = 0;
            long long1 = 0L;
            String string5 = "none";
            String string6 = "none";
            String string7 = "none";
            String string8 = "none";
            String string9 = "none";
            String string10 = "none";
            String string11 = "none";
            String string12 = "none";
            String string13 = "none";
            String string14 = "none";
            String string15 = "none";
            String string16 = "none";
            String string17 = "none";
            String string18 = "none";
            int int6 = -1;
            File[] array2;
            for (int length = (array2 = array).length, j = 0; j < length; ++j) {
                final File file2 = array2[j];
                if (i % (float)(array.length / 10) == 0.0f) {
                    LoggerManager.info("Files migrated: " + (int)(i / (float)array.length * 100.0f) + "%");
                }
                if (file2.getName().contains("broken")) {
                    LoggerManager.info(String.valueOf(file2.getName()) + " file is broken. Skipping this file...");
                }
                else {
                    final FileConfiguration loadConfiguration = FileManager.loadConfiguration(file2);
                    if (loadConfiguration == null) {
                        LoggerManager.warn("Failed to migrate a user data to SQLite database! (File Corrupted) Skipping this file...");
                    }
                    else {
                        if (loadConfiguration.get("Name") != null) {
                            string = loadConfiguration.getString("Name");
                        }
                        if (loadConfiguration.get("UUID") != null) {
                            string2 = loadConfiguration.getString("UUID");
                        }
                        if (b && loadConfiguration.get("Mystery Dust") != null) {
                            int1 = loadConfiguration.getInt("Mystery Dust");
                        }
                        if (loadConfiguration.get("Mystery-Gift.Gift-Packs") != null) {
                            int2 = loadConfiguration.getInt("Mystery-Gift.Gift-Packs");
                        }
                        if (loadConfiguration.get("Mystery-Gift.Gift-Sent") != null) {
                            int3 = loadConfiguration.getInt("Mystery-Gift.Gift-Sent");
                        }
                        if (loadConfiguration.get("Mystery-Gift.Gift-Received") != null) {
                            int4 = loadConfiguration.getInt("Mystery-Gift.Gift-Received");
                        }
                        if (loadConfiguration.get("Settings.Self-Morph-View") != null) {
                            boolean1 = loadConfiguration.getBoolean("Settings.Self-Morph-View");
                        }
                        if (loadConfiguration.get("Settings.Bypass-Cooldown") != null) {
                            boolean2 = loadConfiguration.getBoolean("Settings.Bypass-Cooldown");
                        }
                        if (loadConfiguration.get("Recent-Loots-Found") != null) {
                            string3 = loadConfiguration.getString("Recent-Loots-Found");
                        }
                        if (loadConfiguration.get("Settings.Mystery-Vault-Animation") != null) {
                            string4 = loadConfiguration.getString("Settings.Mystery-Vault-Animation");
                        }
                        if (loadConfiguration.get("Settings.Play-Time") != null) {
                            int5 = loadConfiguration.getInt("Settings.Play-Time");
                        }
                        if (loadConfiguration.get("Settings.Last-Pet-Mission-Time-Millis") != null) {
                            long1 = loadConfiguration.getLong("Settings.Last-Pet-Mission-Time-Millis");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Hat") != null) {
                            string5 = loadConfiguration.getString("Selected-Cosmetics.Hat");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Animated-Hat") != null) {
                            string6 = loadConfiguration.getString("Selected-Cosmetics.Animated-Hat");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Particle") != null) {
                            string7 = loadConfiguration.getString("Selected-Cosmetics.Particle");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Suits.Helmet") != null) {
                            string8 = loadConfiguration.getString("Selected-Cosmetics.Suits.Helmet");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Suits.Chestplate") != null) {
                            string9 = loadConfiguration.getString("Selected-Cosmetics.Suits.Chestplate");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Suits.Leggings") != null) {
                            string10 = loadConfiguration.getString("Selected-Cosmetics.Suits.Leggings");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Suits.Boots") != null) {
                            string11 = loadConfiguration.getString("Selected-Cosmetics.Suits.Boots");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Gadget") != null) {
                            string12 = loadConfiguration.getString("Selected-Cosmetics.Gadget");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Pet") != null) {
                            string13 = loadConfiguration.getString("Selected-Cosmetics.Pet");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Morph") != null) {
                            string14 = loadConfiguration.getString("Selected-Cosmetics.Morph");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Miniature") != null) {
                            string15 = loadConfiguration.getString("Selected-Cosmetics.Miniature");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Banner") != null) {
                            string16 = loadConfiguration.getString("Selected-Cosmetics.Banner");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Emote") != null) {
                            string17 = loadConfiguration.getString("Selected-Cosmetics.Emote");
                        }
                        if (loadConfiguration.get("Selected-Cosmetics.Cloak") != null) {
                            string18 = loadConfiguration.getString("Selected-Cosmetics.Cloak");
                        }
                        if (string2 == "unknown") {
                            LoggerManager.warn("Failed to migrate a user data to SQLite database! (Unknown UUID) Skipping this file...");
                        }
                        else {
                            SelectQuery selectQuery = CookieGadgets.getDatabaseManager().getTable().select().where("UUID", string2);
                            ResultSet set = selectQuery.execute();
                            try {
                                if (!set.next()) {
                                    CookieGadgets.getDatabaseManager().getTable().insert().insert("UUID, Name, Mystery_Dust, Mystery_Gift_Packs, Mystery_Gift_Sent, Mystery_Gift_Received, Self_Morph_View, Bypass_Cooldown, Recent_Loots_Found, Mystery_Vault_Animation, Play_Time, Last_Pet_Mission_Time_Millis, Selected_Hat, Selected_Animated_Hat, Selected_Particle, Selected_Suit_Helmet, Selected_Suit_Chestplate, Selected_Suit_Leggings, Selected_Suit_Boots, Selected_Gadget, Selected_Pet, Selected_Morph, Selected_Miniature, Selected_Banner, Selected_Emote, Selected_Cloak").value(string2).value(string).value(int1).value(int2).value(int3).value(int4).value(boolean1 ? "true" : "false").value(boolean2 ? "true" : "false").value(string3).value(string4).value(int5).value(long1).value(string5).value(string6).value(string7).value(string8).value(string9).value(string10).value(string11).value(string12).value(string13).value(string14).value(string15).value(string16).value(string17).value(string18).execute();
                                }
                                else {
                                    CookieGadgets.getDatabaseManager().getTable().update().set("Mystery_Dust", int1).set("Mystery_Gift_Packs", int2).set("Mystery_Gift_Sent", int3).set("Mystery_Gift_Received", int4).set("Self_Morph_View", boolean1 ? "true" : "false").set("Bypass_Cooldown", boolean2 ? "true" : "false").set("Recent_Loots_Found", string3).set("Mystery_Vault_Animation", string4).set("Play_Time", int5).set("Last_Pet_Mission_Time_Millis", long1).set("Selected_Hat", string5).set("Selected_Animated_Hat", string6).set("Selected_Particle", string7).set("Selected_Suit_Helmet", string8).set("Selected_Suit_Chestplate", string9).set("Selected_Suit_Leggings", string10).set("Selected_Suit_Boots", string11).set("Selected_Gadget", string12).set("Selected_Pet", string13).set("Selected_Morph", string14).set("Selected_Miniature", string15).set("Selected_Banner", string16).set("Selected_Emote", string17).set("Selected_Cloak", string18).where("UUID", string2).execute();
                                }
                            }
                            catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            finally {
                                if (selectQuery != null) {
                                    selectQuery.close();
                                }
                                if (set != null) {
                                    try {
                                        set.close();
                                    }
                                    catch (SQLException ex3) {}
                                }
                            }
                            if (selectQuery != null) {
                                selectQuery.close();
                            }
                            if (set != null) {
                                try {
                                    set.close();
                                }
                                catch (SQLException ex4) {}
                            }
                            try {
                                selectQuery = CookieGadgets.getDatabaseManager().getTable().select().where("UUID", string2.toString());
                                set = selectQuery.execute();
                                set.next();
                                int6 = set.getInt("id");
                            }
                            catch (SQLException ex5) {}
                            finally {
                                if (selectQuery != null) {
                                    selectQuery.close();
                                }
                                if (set != null) {
                                    try {
                                        set.close();
                                    }
                                    catch (SQLException ex6) {}
                                }
                            }
                            if (selectQuery != null) {
                                selectQuery.close();
                            }
                            if (set != null) {
                                try {
                                    set.close();
                                }
                                catch (SQLException ex7) {}
                            }
                            if (int6 == -1) {
                                LoggerManager.warn("Failed to migrate a user data to SQLite database! (Unknown UID) Skipping this file...");
                            }
                            else {
                                if (loadConfiguration.get("Mystery-Boxes") != null) {
                                    final Iterator iterator = loadConfiguration.getStringList("Mystery-Boxes").iterator();
                                    while (iterator.hasNext()) {
                                        CookieGadgets.getDatabaseManager().getMysteryBoxesTable().insert().insert("UUID, UID, Loots").value(string2).value(int6).value(iterator.next()).execute();
                                    }
                                }
                                if (loadConfiguration.get("Unlocked-Cosmetic-Items") != null) {
                                    for (final String s : loadConfiguration.getStringList("Unlocked-Cosmetic-Items")) {
                                        CookieGadgets.getDatabaseManager().getUnlockedCosmeticItemsTable().insert().insert("UUID, UID, Cosmetic, Type, Unlock_Time, Expiry_Time").value(string2).value(int6).value(s.split("\\:")[0]).value(s.split("\\:")[1]).value(new Timestamp(System.currentTimeMillis())).value(GTimestamp.getForever()).execute();
                                    }
                                }
                                if (loadConfiguration.get("Pet-Data") != null && loadConfiguration.getConfigurationSection("Pet-Data") != null) {
                                    for (final Map.Entry<String, V> entry : loadConfiguration.getConfigurationSection("Pet-Data").getValues(false).entrySet()) {
                                        if (PetType.valueOf(entry.getKey()) == null) {
                                            continue;
                                        }
                                        final GPet gPet = new GPet(entry.getValue().toString());
                                        CookieGadgets.getDatabaseManager().getPetsTable().insert().insert("UUID, UID, Pet_Type, Pet_Name, Pet_Level, Pet_XP, Hunger, Eat_Timestamp, Thirst, Drink_Timestamp, Exercise, Exercise_Timestamp").value(string2).value(int6).value(gPet.getType().getName()).value(gPet.getPetName().replace("§", "&")).value(gPet.getPetLevel().getLevel()).value(gPet.getPetLevel().getExperience()).value(gPet.getPetAttribute().getHunger()).value(gPet.getPetAttribute().getEatTimestamp()).value(gPet.getPetAttribute().getThirst()).value(gPet.getPetAttribute().getDrinkTimestamp()).value(gPet.getPetAttribute().getExercise()).value(gPet.getPetAttribute().getExerciseTimestamp()).execute();
                                    }
                                }
                                if (loadConfiguration.get("Pet-Items") != null && loadConfiguration.getConfigurationSection("Pet-Items") != null) {
                                    String string19 = "UUID, UID";
                                    String s2 = "'" + string2 + "', " + int6;
                                    for (final Map.Entry<String, V> entry2 : loadConfiguration.getConfigurationSection("Pet-Items").getValues(false).entrySet()) {
                                        final PetItems byName = PetItems.getByName(entry2.getKey().replace("-", " "));
                                        if (byName == null) {
                                            continue;
                                        }
                                        int intValue = 0;
                                        try {
                                            intValue = (int)entry2.getValue();
                                        }
                                        catch (NumberFormatException ex8) {}
                                        string19 = String.valueOf(string19) + ", " + byName.getSQLIndex();
                                        s2 = String.valueOf(s2) + ", " + intValue;
                                    }
                                    try {
                                        final PreparedStatement prepareStatement = CookieGadgets.getDatabaseManager().getConnection().prepareStatement("INSERT INTO CookieGadgets_Pet_Items (" + string19 + ") VALUES (" + s2 + ")");
                                        prepareStatement.executeUpdate();
                                        prepareStatement.close();
                                    }
                                    catch (SQLException ex2) {
                                        ex2.printStackTrace();
                                    }
                                }
                                file2.delete();
                                ++i;
                            }
                        }
                    }
                }
            }
            boolean b2 = false;
            if (array.length == i) {
                b2 = true;
                file.delete();
            }
            final long lng = System.currentTimeMillis() - currentTimeMillis;
            LoggerManager.info("----------------------------------");
            LoggerManager.info("Successful migrated " + i + " users.");
            LoggerManager.info(String.valueOf(array.length - i) + " file(s) failed to migrate.");
            if (b2) {
                LoggerManager.info("Removed userdata folder.");
            }
            LoggerManager.info("Time elapsed " + lng + " ms.");
            LoggerManager.info("Migrated completed!");
            LoggerManager.info("----------------------------------");
        }
    }
    
    public static void transferMessages() {
        if (OldConfigurationManager.config.get("Config Version") != null) {
            final File file = new File(CookieGadgets.getInstance().getDataFolder(), "/cosmetics/hats.yml");
            final File dest = new File(CookieGadgets.getInstance().getDataFolder(), "/categories/hats.yml");
            if (dest.exists()) {
                dest.delete();
            }
            if (file.exists()) {
                file.renameTo(dest);
                file.delete();
            }
            final File file2 = new File(CookieGadgets.getInstance().getDataFolder(), "/cosmetics/particles.yml");
            final File dest2 = new File(CookieGadgets.getInstance().getDataFolder(), "/categories/particles.yml");
            if (dest2.exists()) {
                dest2.delete();
            }
            if (file2.exists()) {
                file2.renameTo(dest2);
                file2.delete();
            }
            final File file3 = new File(CookieGadgets.getInstance().getDataFolder(), "/cosmetics/suits.yml");
            final File dest3 = new File(CookieGadgets.getInstance().getDataFolder(), "/categories/suits.yml");
            if (dest3.exists()) {
                dest3.delete();
            }
            if (file3.exists()) {
                file3.renameTo(dest3);
                file3.delete();
            }
            final File file4 = new File(CookieGadgets.getInstance().getDataFolder(), "/cosmetics/gadgets.yml");
            final File dest4 = new File(CookieGadgets.getInstance().getDataFolder(), "/categories/gadgets.yml");
            if (dest4.exists()) {
                dest4.delete();
            }
            if (file4.exists()) {
                file4.renameTo(dest4);
                file4.delete();
            }
            final File file5 = new File(CookieGadgets.getInstance().getDataFolder(), "/cosmetics/pets.yml");
            final File dest5 = new File(CookieGadgets.getInstance().getDataFolder(), "/categories/pets.yml");
            if (dest5.exists()) {
                dest5.delete();
            }
            if (file5.exists()) {
                file5.renameTo(dest5);
                file5.delete();
            }
            final File file6 = new File(CookieGadgets.getInstance().getDataFolder(), "/cosmetics/morphs.yml");
            final File dest6 = new File(CookieGadgets.getInstance().getDataFolder(), "/categories/morphs.yml");
            if (dest6.exists()) {
                dest6.delete();
            }
            if (file6.exists()) {
                file6.renameTo(dest6);
                file6.delete();
            }
            final File file7 = new File(CookieGadgets.getInstance().getDataFolder(), "/cosmetics/banners.yml");
            final File dest7 = new File(CookieGadgets.getInstance().getDataFolder(), "/categories/banners.yml");
            if (dest7.exists()) {
                dest7.delete();
            }
            if (file7.exists()) {
                file7.renameTo(dest7);
                file7.delete();
            }
            final File file8 = new File(CookieGadgets.getInstance().getDataFolder(), "/cosmetics/emotes.yml");
            final File dest8 = new File(CookieGadgets.getInstance().getDataFolder(), "/categories/emotes.yml");
            if (dest8.exists()) {
                dest8.delete();
            }
            if (file8.exists()) {
                file8.renameTo(dest8);
                file8.delete();
            }
            final File file9 = new File(CookieGadgets.getInstance().getDataFolder(), "/cosmetics/cloaks.yml");
            final File dest9 = new File(CookieGadgets.getInstance().getDataFolder(), "/categories/cloaks.yml");
            if (dest9.exists()) {
                dest9.delete();
            }
            if (file9.exists()) {
                file9.renameTo(dest9);
                file9.delete();
            }
            final File file10 = new File(CookieGadgets.getInstance().getDataFolder(), "/cosmetics/mainmenu.yml");
            final File dest10 = new File(CookieGadgets.getInstance().getDataFolder(), "/categories/mainmenu.yml");
            if (dest10.exists()) {
                dest10.delete();
            }
            if (file10.exists()) {
                file10.renameTo(dest10);
                file10.delete();
            }
            final File file11 = new File(CookieGadgets.getInstance().getDataFolder(), "/songs/BroadcastRadioGadget");
            final File dest11 = new File(CookieGadgets.getInstance().getDataFolder(), "/songs/RadioGadget");
            if (dest11.exists()) {
                dest11.delete();
            }
            if (file11.exists()) {
                file11.renameTo(dest11);
                file11.delete();
            }
            final File file12 = new File(CookieGadgets.getInstance().getDataFolder(), "/cosmetics");
            if (file12.exists()) {
                file12.delete();
            }
            final File file13 = new File(CookieGadgets.getInstance().getDataFolder(), "/Mystery Vault.yml");
            final File dest12 = new File(CookieGadgets.getInstance().getDataFolder(), "/mystery vaults.yml");
            if (dest12.exists()) {
                dest12.delete();
            }
            if (file13.exists()) {
                file13.renameTo(dest12);
                file13.delete();
                FileManager.getMysteryVaultFile().reload();
                FileManager.getMysteryVaultFile().save();
            }
            if (OldConfigurationManager.config.get("CookieGadgets") != null) {
                if (OldConfigurationManager.config.get("CookieGadgets.Prefix") != null) {
                    OldConfigurationManager.messages.set("Prefix", OldConfigurationManager.config.getString("CookieGadgets.Prefix"));
                }
                if (OldConfigurationManager.config.get("CookieGadgets.GUI") != null) {
                    if (OldConfigurationManager.config.get("CookieGadgets.GUI.MainMenu") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Main", OldConfigurationManager.config.getString("CookieGadgets.GUI.MainMenu"));
                    }
                    if (OldConfigurationManager.config.get("CookieGadgets.GUI.Hats") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Hats", OldConfigurationManager.config.getString("CookieGadgets.GUI.Hats"));
                    }
                    if (OldConfigurationManager.config.get("CookieGadgets.GUI.Particles") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Particles", OldConfigurationManager.config.getString("CookieGadgets.GUI.Particles"));
                    }
                    if (OldConfigurationManager.config.get("CookieGadgets.GUI.Suits") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Suits", OldConfigurationManager.config.getString("CookieGadgets.GUI.Suits"));
                    }
                    if (OldConfigurationManager.config.get("CookieGadgets.GUI.Gadgets") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Gadgets", OldConfigurationManager.config.getString("CookieGadgets.GUI.Gadgets"));
                    }
                    if (OldConfigurationManager.config.get("CookieGadgets.GUI.Pets") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Pets", OldConfigurationManager.config.getString("CookieGadgets.GUI.Pets"));
                    }
                    if (OldConfigurationManager.config.get("CookieGadgets.GUI.Morphs") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Morphs", OldConfigurationManager.config.getString("CookieGadgets.GUI.Morphs"));
                    }
                    if (OldConfigurationManager.config.get("CookieGadgets.GUI.Banners") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Banners", OldConfigurationManager.config.getString("CookieGadgets.GUI.Banners"));
                    }
                    if (OldConfigurationManager.config.get("CookieGadgets.GUI.Emotes") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Emotes", OldConfigurationManager.config.getString("CookieGadgets.GUI.Emotes"));
                    }
                    if (OldConfigurationManager.config.get("CookieGadgets.GUI.Cloaks") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Cloaks", OldConfigurationManager.config.getString("CookieGadgets.GUI.Cloaks"));
                    }
                }
                if (OldConfigurationManager.config.get("CookieGadgets.Settings") != null) {
                    if (OldConfigurationManager.config.get("CookieGadgets.Settings.Default Pet Name") != null) {
                        OldConfigurationManager.config.set("Settings.Default-Pet-Name", (Object)OldConfigurationManager.config.getString("CookieGadgets.Settings.Default Pet Name"));
                    }
                    if (OldConfigurationManager.config.get("Purchase System.Starter Mystery Dust") != null) {
                        OldConfigurationManager.config.set("Settings.Starting-Mystery-Dust", (Object)OldConfigurationManager.config.getInt("Purchase System.Starter Mystery Dust"));
                    }
                    if (OldConfigurationManager.config.get("CookieGadgets.Settings.Max-Name-Pet-Length") != null) {
                        OldConfigurationManager.config.set("Settings.Max-Pet-Name-Characters", (Object)OldConfigurationManager.config.getInt("CookieGadgets.Settings.Max-Name-Pet-Length"));
                    }
                    if (OldConfigurationManager.config.get("CookieGadgets.Settings.Gadget-Slot") != null) {
                        OldConfigurationManager.config.set("Settings.Gadget-Slot", (Object)OldConfigurationManager.config.getInt("CookieGadgets.Settings.Gadget-Slot"));
                    }
                }
            }
            if (OldConfigurationManager.config.get("Player-Data") != null && OldConfigurationManager.config.get("Player-Data.Database") != null) {
                OldConfigurationManager.config.set("Player-Data.Storage", (Object)(OldConfigurationManager.config.getBoolean("Player-Data.Database") ? "mysql" : "file"));
            }
            if (OldConfigurationManager.config.get("Purchase System") != null) {
                if (OldConfigurationManager.config.get("Purchase System.Enabled") != null) {
                    OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Enabled", (Object)OldConfigurationManager.config.getBoolean("Purchase System.Enabled"));
                }
                if (OldConfigurationManager.config.get("Purchase System.Storage") != null) {
                    OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Mystery-Dust-Storage", (Object)OldConfigurationManager.config.getString("Purchase System.Storage"));
                }
                if (OldConfigurationManager.config.get("Purchase System.Enabled Cosmetics") != null) {
                    if (OldConfigurationManager.config.get("Purchase System.Enabled Cosmetics.Hats") != null) {
                        OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Enabled-Cosmetics.Hats", (Object)OldConfigurationManager.config.getBoolean("Purchase System.Enabled Cosmetics.Hats"));
                    }
                    if (OldConfigurationManager.config.get("Purchase System.Enabled Cosmetics.Particles") != null) {
                        OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Enabled-Cosmetics.Particles", (Object)OldConfigurationManager.config.getBoolean("Purchase System.Enabled Cosmetics.Particles"));
                    }
                    if (OldConfigurationManager.config.get("Purchase System.Enabled Cosmetics.Suits") != null) {
                        OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Enabled-Cosmetics.Suits", (Object)OldConfigurationManager.config.getBoolean("Purchase System.Enabled Cosmetics.Suits"));
                    }
                    if (OldConfigurationManager.config.get("Purchase System.Enabled Cosmetics.Gadgets") != null) {
                        OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Enabled-Cosmetics.Gadgets", (Object)OldConfigurationManager.config.getBoolean("Purchase System.Enabled Cosmetics.Gadgets"));
                    }
                    if (OldConfigurationManager.config.get("Purchase System.Enabled Cosmetics.Pets") != null) {
                        OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Enabled-Cosmetics.Pets", (Object)OldConfigurationManager.config.getBoolean("Purchase System.Enabled Cosmetics.Pets"));
                    }
                    if (OldConfigurationManager.config.get("Purchase System.Enabled Cosmetics.Morphs") != null) {
                        OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Enabled-Cosmetics.Morphs", (Object)OldConfigurationManager.config.getBoolean("Purchase System.Enabled Cosmetics.Morphs"));
                    }
                    if (OldConfigurationManager.config.get("Purchase System.Enabled Cosmetics.Banners") != null) {
                        OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Enabled-Cosmetics.Banners", (Object)OldConfigurationManager.config.getBoolean("Purchase System.Enabled Cosmetics.Banners"));
                    }
                    if (OldConfigurationManager.config.get("Purchase System.Enabled Cosmetics.Emotes") != null) {
                        OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Enabled-Cosmetics.Emotes", (Object)OldConfigurationManager.config.getBoolean("Purchase System.Enabled Cosmetics.Emotes"));
                    }
                    if (OldConfigurationManager.config.get("Purchase System.Enabled Cosmetics.Cloaks") != null) {
                        OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Enabled-Cosmetics.Cloaks", (Object)OldConfigurationManager.config.getBoolean("Purchase System.Enabled Cosmetics.Cloaks"));
                    }
                }
                if (OldConfigurationManager.config.get("Purchase System.Run-Command") != null) {
                    OldConfigurationManager.config.set("Cosmetic-Item-Purchase.Execute-Command", (Object)OldConfigurationManager.config.getString("Purchase System.Run-Command"));
                }
                if (OldConfigurationManager.config.get("Purchase System.Lore") != null) {
                    if (OldConfigurationManager.config.get("Purchase System.Lore.Enough Mystery Dust") != null) {
                        OldConfigurationManager.messages.set("Items.Enough-Mystery-Dust.Lore", OldConfigurationManager.config.getStringList("Purchase System.Lore.Enough Mystery Dust"));
                    }
                    if (OldConfigurationManager.config.get("Purchase System.Lore.Not Enough Mystery Dust") != null) {
                        OldConfigurationManager.messages.set("Items.Not-Enough-Mystery-Dust.Lore", OldConfigurationManager.config.getStringList("Purchase System.Lore.Not Enough Mystery Dust"));
                    }
                    if (OldConfigurationManager.config.get("Purchase System.Lore.Item Unpurchasable") != null) {
                        OldConfigurationManager.messages.set("Items.Item-Unpurchasable.Lore", OldConfigurationManager.config.getStringList("Purchase System.Lore.Item Unpurchasable"));
                    }
                }
            }
            if (OldConfigurationManager.config.get("Menu Item") != null) {
                if (OldConfigurationManager.config.get("Menu Item.Name") != null) {
                    OldConfigurationManager.config.set("Menu-Item.Name", (Object)OldConfigurationManager.config.getString("Menu Item.Name"));
                }
                if (OldConfigurationManager.config.get("Menu Item.Material") != null) {
                    OldConfigurationManager.config.set("Menu-Item.Material", (Object)OldConfigurationManager.config.getString("Menu Item.Material"));
                }
                if (OldConfigurationManager.config.get("Menu Item.Slot") != null) {
                    OldConfigurationManager.config.set("Menu-Item.Slot", (Object)OldConfigurationManager.config.getInt("Menu Item.Slot"));
                }
                if (OldConfigurationManager.config.get("Menu Item.Give Item") != null) {
                    OldConfigurationManager.config.set("Menu-Item.Give-On-Join", (Object)OldConfigurationManager.config.getBoolean("Menu Item.Give Item"));
                }
                if (OldConfigurationManager.config.get("Menu Item.Able to Move") != null) {
                    OldConfigurationManager.config.set("Menu-Item.Able-To-Move", (Object)OldConfigurationManager.config.getBoolean("Menu Item.Able to Move"));
                }
            }
            if (OldConfigurationManager.config.get("Enabled Worlds") != null) {
                OldConfigurationManager.config.set("Enabled-Worlds", (Object)OldConfigurationManager.config.getStringList("Enabled Worlds"));
            }
            if (OldConfigurationManager.config.get("Disabled Cosmetics") != null) {
                if (OldConfigurationManager.config.get("Disabled Cosmetics.Hats") != null) {
                    OldConfigurationManager.config.set("Disabled-Cosmetics.Hats", (Object)OldConfigurationManager.config.getBoolean("Disabled Cosmetics.Hats"));
                }
                if (OldConfigurationManager.config.get("Disabled Cosmetics.Particles") != null) {
                    OldConfigurationManager.config.set("Disabled-Cosmetics.Particles", (Object)OldConfigurationManager.config.getBoolean("Disabled Cosmetics.Particles"));
                }
                if (OldConfigurationManager.config.get("Disabled Cosmetics.Suits") != null) {
                    OldConfigurationManager.config.set("Disabled-Cosmetics.Suits", (Object)OldConfigurationManager.config.getBoolean("Disabled Cosmetics.Suits"));
                }
                if (OldConfigurationManager.config.get("Disabled Cosmetics.Gadgets") != null) {
                    OldConfigurationManager.config.set("Disabled-Cosmetics.Gadgets", (Object)OldConfigurationManager.config.getBoolean("Disabled Cosmetics.Gadgets"));
                }
                if (OldConfigurationManager.config.get("Disabled Cosmetics.Pets") != null) {
                    OldConfigurationManager.config.set("Disabled-Cosmetics.Pets", (Object)OldConfigurationManager.config.getBoolean("Disabled Cosmetics.Pets"));
                }
                if (OldConfigurationManager.config.get("Disabled Cosmetics.Morphs") != null) {
                    OldConfigurationManager.config.set("Disabled-Cosmetics.Morphs", (Object)OldConfigurationManager.config.getBoolean("Disabled Cosmetics.Morphs"));
                }
                if (OldConfigurationManager.config.get("Disabled Cosmetics.Banners") != null) {
                    OldConfigurationManager.config.set("Disabled-Cosmetics.Banners", (Object)OldConfigurationManager.config.getBoolean("Disabled Cosmetics.Banners"));
                }
                if (OldConfigurationManager.config.get("Disabled Cosmetics.Emotes") != null) {
                    OldConfigurationManager.config.set("Disabled-Cosmetics.Emotes", (Object)OldConfigurationManager.config.getBoolean("Disabled Cosmetics.Emotes"));
                }
                if (OldConfigurationManager.config.get("Disabled Cosmetics.Cloaks") != null) {
                    OldConfigurationManager.config.set("Disabled-Cosmetics.Cloaks", (Object)OldConfigurationManager.config.getBoolean("Disabled Cosmetics.Cloaks"));
                }
            }
            if (OldConfigurationManager.config.get("Close GUI Menu After Select") != null) {
                if (OldConfigurationManager.config.get("Close GUI Menu After Select.No Permission") != null) {
                    OldConfigurationManager.config.set("Permission.No-Permission.Close-GUI-Menu-After-Select", (Object)OldConfigurationManager.config.getBoolean("Close GUI Menu After Select.No Permission"));
                }
                if (OldConfigurationManager.config.get("Close GUI Menu After Select.Select") != null) {
                    OldConfigurationManager.config.set("Permission.Has-Permission.Close-GUI-Menu-After-Select", (Object)OldConfigurationManager.config.getBoolean("Close GUI Menu After Select.Select"));
                }
            }
            if (OldConfigurationManager.config.get("Permission") != null) {
                if (OldConfigurationManager.config.get("Permission.No Permission") != null) {
                    if (OldConfigurationManager.config.get("Permission.No Permission.Material") != null) {
                        OldConfigurationManager.config.set("Permission.No-Permission.Show-Custom-Item.Material", (Object)OldConfigurationManager.config.getString("Permission.No Permission.Material"));
                    }
                    if (OldConfigurationManager.config.get("Permission.No Permission.Play Sound.Enabled") != null) {
                        OldConfigurationManager.config.set("Permission.No-Permission.Play-Sound.Enabled", (Object)OldConfigurationManager.config.getBoolean("Permission.No Permission.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.config.get("Permission.No Permission.Play Sound.Sound") != null) {
                        OldConfigurationManager.config.set("Permission.No-Permission.Play-Sound.Sound", (Object)OldConfigurationManager.config.getString("Permission.No Permission.Play Sound.Sound"));
                    }
                    if (OldConfigurationManager.config.get("Permission.No Permission.Lore") != null && !OldConfigurationManager.config.getString("Permission.No Permission.Lore").equals("")) {
                        OldConfigurationManager.config.set("Permission.No-Permission.Lore", (Object)OldConfigurationManager.config.getStringList("Permission.No Permission.Lore"));
                    }
                }
                if (OldConfigurationManager.config.get("Permission.Has Permission") != null) {
                    if (OldConfigurationManager.config.get("Permission.Has Permission.Play Sound.Enabled") != null) {
                        OldConfigurationManager.config.set("Permission.Has-Permission.Play-Sound.Enabled", (Object)OldConfigurationManager.config.getBoolean("Permission.Has Permission.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.config.get("Permission.Has Permission.Play Sound.Sound") != null) {
                        OldConfigurationManager.config.set("Permission.Has-Permission.Play-Sound.Sound", (Object)OldConfigurationManager.config.getString("Permission.Has Permission.Play Sound.Sound"));
                    }
                    if (OldConfigurationManager.config.get("Permission.Has Permission.Lore") != null && !OldConfigurationManager.config.getString("Permission.Has Permission.Lore").equals("")) {
                        OldConfigurationManager.config.set("Permission.Has-Permission.Lore", (Object)OldConfigurationManager.config.getStringList("Permission.Has Permission.Lore"));
                    }
                }
            }
            if (OldConfigurationManager.config.get("Check Update") != null) {
                OldConfigurationManager.config.set("Check-Update", (Object)OldConfigurationManager.config.getBoolean("Check Update"));
            }
            if (OldConfigurationManager.config.get("Auto Update") != null) {
                OldConfigurationManager.config.set("Auto-Update", (Object)OldConfigurationManager.config.getBoolean("Auto Update"));
            }
            if (OldConfigurationManager.mysteryVault.get("Mystery Vault") != null) {
                for (final String str : OldConfigurationManager.mysteryVault.getConfigurationSection("Mystery Vault").getKeys(false)) {
                    if (OldConfigurationManager.mysteryVault.get("Mystery Vault." + str + ".BlockFace") != null) {
                        OldConfigurationManager.mysteryVault.set("Mystery-Vaults." + str + ".BlockFace", OldConfigurationManager.mysteryVault.getString("Mystery Vault." + str + ".BlockFace"));
                    }
                    if (OldConfigurationManager.mysteryVault.get("Mystery Vault." + str + ".Location.world") != null) {
                        OldConfigurationManager.mysteryVault.set("Mystery-Vaults." + str + ".Location.world", OldConfigurationManager.mysteryVault.getString("Mystery Vault." + str + ".Location.world"));
                    }
                    if (OldConfigurationManager.mysteryVault.get("Mystery Vault." + str + ".Location.x") != null) {
                        OldConfigurationManager.mysteryVault.set("Mystery-Vaults." + str + ".Location.x", OldConfigurationManager.mysteryVault.getDouble("Mystery Vault." + str + ".Location.x"));
                    }
                    if (OldConfigurationManager.mysteryVault.get("Mystery Vault." + str + ".Location.y") != null) {
                        OldConfigurationManager.mysteryVault.set("Mystery-Vaults." + str + ".Location.y", OldConfigurationManager.mysteryVault.getDouble("Mystery Vault." + str + ".Location.y"));
                    }
                    if (OldConfigurationManager.mysteryVault.get("Mystery Vault." + str + ".Location.z") != null) {
                        OldConfigurationManager.mysteryVault.set("Mystery-Vaults." + str + ".Location.z", OldConfigurationManager.mysteryVault.getDouble("Mystery Vault." + str + ".Location.z"));
                    }
                }
            }
            if (OldConfigurationManager.messages.get("Items") != null) {
                if (OldConfigurationManager.messages.get("Items.Go Back") != null) {
                    if (OldConfigurationManager.messages.get("Items.Go Back.Name") != null) {
                        OldConfigurationManager.messages.set("Items.Go-Back.Name", OldConfigurationManager.messages.getString("Items.Go Back.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Go Back.Material") != null) {
                        OldConfigurationManager.messages.set("Items.Go-Back.Material", OldConfigurationManager.messages.getString("Items.Go Back.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Go Back.Show") != null) {
                        OldConfigurationManager.messages.set("Items.Go-Back.Show", OldConfigurationManager.messages.getBoolean("Items.Go Back.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Go Back.Lore") != null && !OldConfigurationManager.messages.getString("Items.Go Back.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Items.Go-Back.Lore", OldConfigurationManager.messages.getStringList("Items.Go Back.Lore"));
                    }
                }
                if (OldConfigurationManager.messages.get("Items.Previous Page") != null) {
                    if (OldConfigurationManager.messages.get("Items.Previous Page.Name") != null) {
                        OldConfigurationManager.messages.set("Items.Previous-Page.Name", OldConfigurationManager.messages.getString("Items.Previous Page.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Previous Page.Material") != null) {
                        OldConfigurationManager.messages.set("Items.Previous-Page.Material", OldConfigurationManager.messages.getString("Items.Previous Page.Material"));
                    }
                }
                if (OldConfigurationManager.messages.get("Items.Next Page") != null) {
                    if (OldConfigurationManager.messages.get("Items.Next Page.Name") != null) {
                        OldConfigurationManager.messages.set("Items.Next-Page.Name", OldConfigurationManager.messages.getString("Items.Next Page.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Next Page.Material") != null) {
                        OldConfigurationManager.messages.set("Items.Next-Page.Material", OldConfigurationManager.messages.getString("Items.Next Page.Material"));
                    }
                }
                if (OldConfigurationManager.messages.get("Items.Already Selected") != null) {
                    if (OldConfigurationManager.messages.get("Items.Already Selected.Show in Lore") != null) {
                        OldConfigurationManager.messages.set("Items.Already-Selected.Show-In-Lore", OldConfigurationManager.messages.getBoolean("Items.Already Selected.Show in Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Already Selected.Lore") != null && !OldConfigurationManager.messages.getString("Items.Already Selected.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Items.Already-Selected.Lore", OldConfigurationManager.messages.getStringList("Items.Already Selected.Lore"));
                    }
                }
                if (OldConfigurationManager.messages.get("Items.Click To Select") != null) {
                    if (OldConfigurationManager.messages.get("Items.Click To Select.Show in Lore") != null) {
                        OldConfigurationManager.messages.set("Items.Click-To-Select.Show-In-Lore", OldConfigurationManager.messages.getBoolean("Items.Click To Select.Show in Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Click To Select.Lore") != null && !OldConfigurationManager.messages.getString("Items.Click To Select.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Items.Click-To-Select.Lore", OldConfigurationManager.messages.getStringList("Items.Click To Select.Lore"));
                    }
                }
                if (OldConfigurationManager.messages.get("Items.Unlocked") != null && OldConfigurationManager.messages.get("Items.Unlocked.Show in Lore") != null) {
                    OldConfigurationManager.messages.set("Items.Unlocked.Show-In-Lore", OldConfigurationManager.messages.getBoolean("Items.Unlocked.Show in Lore"));
                }
                if (OldConfigurationManager.messages.get("Items.MainMenu Button") != null) {
                    if (OldConfigurationManager.messages.get("Items.MainMenu Button.Name") != null) {
                        OldConfigurationManager.messages.set("Items.MainMenu-Item.Name", OldConfigurationManager.messages.getString("Items.MainMenu Button.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Items.MainMenu Button.Material") != null) {
                        OldConfigurationManager.messages.set("Items.MainMenu-Item.Material", OldConfigurationManager.messages.getString("Items.MainMenu Button.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Items.MainMenu Button.Show") != null) {
                        OldConfigurationManager.messages.set("Items.MainMenu-Item.Show", OldConfigurationManager.messages.getBoolean("Items.MainMenu Button.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Items.MainMenu Button.Lore") != null && !OldConfigurationManager.messages.getString("Items.MainMenu Button.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Items.MainMenu-Item.Lore", OldConfigurationManager.messages.getStringList("Items.MainMenu Button.Lore"));
                    }
                }
                if (OldConfigurationManager.messages.get("Items.Morph Slimeball") != null) {
                    OldConfigurationManager.messages.set("Items.Morph-Slimeball.Name", OldConfigurationManager.messages.getString("Items.Morph Slimeball.Name"));
                }
                if (OldConfigurationManager.messages.get("Items.Morphs Self View") != null) {
                    if (OldConfigurationManager.messages.get("Items.Morphs Self View.Name") != null) {
                        OldConfigurationManager.messages.set("Items.Self-Morph-View.Name", OldConfigurationManager.messages.getString("Items.Morphs Self View.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Morphs Self View.Material") != null) {
                        OldConfigurationManager.messages.set("Items.Self-Morph-View.Material", OldConfigurationManager.messages.getString("Items.Morphs Self View.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Morphs Self View.Show") != null) {
                        OldConfigurationManager.messages.set("Items.Self-Morph-View.Show", OldConfigurationManager.messages.getBoolean("Items.Morphs Self View.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Morphs Self View.Lore") != null && !OldConfigurationManager.messages.getString("Items.Morphs Self View.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Items.Self-Morph-View.Lore", OldConfigurationManager.messages.getStringList("Items.Morphs Self View.Lore"));
                    }
                }
                if (OldConfigurationManager.messages.get("Items.Rename Pet") != null) {
                    if (OldConfigurationManager.messages.get("Items.Rename Pet.Name") != null) {
                        OldConfigurationManager.messages.set("Items.Rename-Pet.Name", OldConfigurationManager.messages.getString("Items.Rename Pet.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Rename Pet.Material") != null) {
                        OldConfigurationManager.messages.set("Items.Rename-Pet.Material", OldConfigurationManager.messages.getString("Items.Rename Pet.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Rename Pet.Show") != null) {
                        OldConfigurationManager.messages.set("Items.Rename-Pet.Show", OldConfigurationManager.messages.getBoolean("Items.Rename Pet.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Items.Rename Pet.Lore") != null && !OldConfigurationManager.messages.getString("Items.Rename Pet.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Items.Rename-Pet.Lore", OldConfigurationManager.messages.getStringList("Items.Rename Pet.Lore"));
                    }
                }
            }
            if (OldConfigurationManager.messages.get("Reset Button") != null) {
                if (OldConfigurationManager.messages.get("Reset Button.") != null) {
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cosmetics.Name") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cosmetics.Name", OldConfigurationManager.messages.getString("Reset Button.Reset Cosmetics.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cosmetics.Material") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cosmetics.Material", OldConfigurationManager.messages.getString("Reset Button.Reset Cosmetics.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cosmetics.Show") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cosmetics.Show", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Cosmetics.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cosmetics.Lore") != null && !OldConfigurationManager.messages.getString("Reset Button.Reset Cosmetics.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cosmetics.Lore", OldConfigurationManager.messages.getStringList("Reset Button.Reset Cosmetics.Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cosmetics.Play Sound.Enabled") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cosmetics.Play-Sound.Enabled", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Cosmetics.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cosmetics.Play Sound.Sound") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cosmetics.Play-Sound.Sound", OldConfigurationManager.messages.getString("Reset Button.Reset Cosmetics.Play Sound.Sound"));
                    }
                }
                if (OldConfigurationManager.messages.get("Reset Button.Reset Hat") != null) {
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Hat.Name") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Hat.Name", OldConfigurationManager.messages.getString("Reset Button.Reset Hat.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Hat.Material") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Hat.Material", OldConfigurationManager.messages.getString("Reset Button.Reset Hat.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Hat.Show") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Hat.Show", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Hat.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Hat.Lore") != null && !OldConfigurationManager.messages.getString("Reset Button.Reset Hat.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Hat.Lore", OldConfigurationManager.messages.getStringList("Reset Button.Reset Hat.Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Hat.Play Sound.Enabled") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Hat.Play-Sound.Enabled", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Hat.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Hat.Play Sound.Sound") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Hat.Play-Sound.Sound", OldConfigurationManager.messages.getString("Reset Button.Reset Hat.Play Sound.Sound"));
                    }
                }
                if (OldConfigurationManager.messages.get("Reset Button.Reset Particle") != null) {
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Particle.Name") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Particle.Name", OldConfigurationManager.messages.getString("Reset Button.Reset Particle.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Particle.Material") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Particle.Material", OldConfigurationManager.messages.getString("Reset Button.Reset Particle.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Particle.Show") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Particle.Show", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Particle.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Particle.Lore") != null && !OldConfigurationManager.messages.getString("Reset Button.Reset Particle.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Particle.Lore", OldConfigurationManager.messages.getStringList("Reset Button.Reset Particle.Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Particle.Play Sound.Enabled") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Particle.Play-Sound.Enabled", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Particle.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Particle.Play Sound.Sound") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Particle.Play-Sound.Sound", OldConfigurationManager.messages.getString("Reset Button.Reset Particle.Play Sound.Sound"));
                    }
                }
                if (OldConfigurationManager.messages.get("Reset Button.Reset Suit") != null) {
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Suit.Name") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Suit.Name", OldConfigurationManager.messages.getString("Reset Button.Reset Suit.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Suit.Material") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Suit.Material", OldConfigurationManager.messages.getString("Reset Button.Reset Suit.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Suit.Show") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Suit.Show", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Suit.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Suit.Lore") != null && !OldConfigurationManager.messages.getString("Reset Button.Reset Suit.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Suit.Lore", OldConfigurationManager.messages.getStringList("Reset Button.Reset Suit.Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Suit.Play Sound.Enabled") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Suit.Play-Sound.Enabled", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Suit.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Suit.Play Sound.Sound") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Suit.Play-Sound.Sound", OldConfigurationManager.messages.getString("Reset Button.Reset Suit.Play Sound.Sound"));
                    }
                }
                if (OldConfigurationManager.messages.get("Reset Button.Reset Gadget") != null) {
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Gadget.Name") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Gadget.Name", OldConfigurationManager.messages.getString("Reset Button.Reset Gadget.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Gadget.Material") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Gadget.Material", OldConfigurationManager.messages.getString("Reset Button.Reset Gadget.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Gadget.Show") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Gadget.Show", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Gadget.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Gadget.Lore") != null && !OldConfigurationManager.messages.getString("Reset Button.Reset Gadget.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Gadget.Lore", OldConfigurationManager.messages.getStringList("Reset Button.Reset Gadget.Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Gadget.Play Sound.Enabled") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Gadget.Play-Sound.Enabled", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Gadget.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Gadget.Play Sound.Sound") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Gadget.Play-Sound.Sound", OldConfigurationManager.messages.getString("Reset Button.Reset Gadget.Play Sound.Sound"));
                    }
                }
                if (OldConfigurationManager.messages.get("Reset Button.Reset Pet") != null) {
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Pet.Name") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Pet.Name", OldConfigurationManager.messages.getString("Reset Button.Reset Pet.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Pet.Material") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Pet.Material", OldConfigurationManager.messages.getString("Reset Button.Reset Pet.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Pet.Show") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Pet.Show", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Pet.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Pet.Lore") != null && !OldConfigurationManager.messages.getString("Reset Button.Reset Pet.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Pet.Lore", OldConfigurationManager.messages.getStringList("Reset Button.Reset Pet.Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Pet.Play Sound.Enabled") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Pet.Play-Sound.Enabled", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Pet.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Pet.Play Sound.Sound") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Pet.Play-Sound.Sound", OldConfigurationManager.messages.getString("Reset Button.Reset Pet.Play Sound.Sound"));
                    }
                }
                if (OldConfigurationManager.messages.get("Reset Button.Reset Morph") != null) {
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Morph.Name") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Morph.Name", OldConfigurationManager.messages.getString("Reset Button.Reset Morph.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Morph.Material") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Morph.Material", OldConfigurationManager.messages.getString("Reset Button.Reset Morph.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Morph.Show") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Morph.Show", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Morph.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Morph.Lore") != null && !OldConfigurationManager.messages.getString("Reset Button.Reset Morph.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Morph.Lore", OldConfigurationManager.messages.getStringList("Reset Button.Reset Morph.Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Morph.Play Sound.Enabled") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Morph.Play-Sound.Enabled", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Morph.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Morph.Play Sound.Sound") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Morph.Play-Sound.Sound", OldConfigurationManager.messages.getString("Reset Button.Reset Morph.Play Sound.Sound"));
                    }
                }
                if (OldConfigurationManager.messages.get("Reset Button.Reset Banner") != null) {
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Banner.Name") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Banner.Name", OldConfigurationManager.messages.getString("Reset Button.Reset Banner.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Banner.Material") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Banner.Material", OldConfigurationManager.messages.getString("Reset Button.Reset Banner.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Banner.Show") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Banner.Show", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Banner.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Banner.Lore") != null && !OldConfigurationManager.messages.getString("Reset Button.Reset Banner.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Banner.Lore", OldConfigurationManager.messages.getStringList("Reset Button.Reset Banner.Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Banner.Play Sound.Enabled") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Banner.Play-Sound.Enabled", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Banner.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Banner.Play Sound.Sound") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Banner.Play-Sound.Sound", OldConfigurationManager.messages.getString("Reset Button.Reset Banner.Play Sound.Sound"));
                    }
                }
                if (OldConfigurationManager.messages.get("Reset Button.Reset Emote") != null) {
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Emote.Name") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Emote.Name", OldConfigurationManager.messages.getString("Reset Button.Reset Emote.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button..Material") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Emote.Material", OldConfigurationManager.messages.getString("Reset Button.Reset Emote.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button..Show") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Emote.Show", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Emote.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Emote.Lore") != null && !OldConfigurationManager.messages.getString("Reset Button.Reset Emote.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Emote.Lore", OldConfigurationManager.messages.getStringList("Reset Button.Reset Emote.Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Emote.Play Sound.Enabled") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Emote.Play-Sound.Enabled", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Emote.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Emote.Play Sound.Sound") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Emote.Play-Sound.Sound", OldConfigurationManager.messages.getString("Reset Button.Reset Emote.Play Sound.Sound"));
                    }
                }
                if (OldConfigurationManager.messages.get("Reset Button.Reset Cloak") != null) {
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cloak.Name") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cloak.Name", OldConfigurationManager.messages.getString("Reset Button.Reset Cloak.Name"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cloak.Material") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cloak.Material", OldConfigurationManager.messages.getString("Reset Button.Reset Cloak.Material"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cloak.Show") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cloak.Show", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Cloak.Show"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cloak.Lore") != null && !OldConfigurationManager.messages.getString("Reset Button.Reset Cloak.Lore").equals("")) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cloak.Lore", OldConfigurationManager.messages.getStringList("Reset Button.Reset Cloak.Lore"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cloak.Play Sound.Enabled") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cloak.Play-Sound.Enabled", OldConfigurationManager.messages.getBoolean("Reset Button.Reset Cloak.Play Sound.Enabled"));
                    }
                    if (OldConfigurationManager.messages.get("Reset Button.Reset Cloak.Play Sound.Sound") != null) {
                        OldConfigurationManager.messages.set("Reset-Buttons.Reset-Cloak.Play-Sound.Sound", OldConfigurationManager.messages.getString("Reset Button.Reset Cloak.Play Sound.Sound"));
                    }
                }
            }
            if (OldConfigurationManager.messages.get("GUI-Menus") != null) {
                if (OldConfigurationManager.messages.get("GUI-Menus.Purchase Menu") != null) {
                    if (OldConfigurationManager.messages.get("GUI-Menus.Purchase Menu.GUI-Name") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Purchase-Menu.GUI-Name", OldConfigurationManager.messages.getString("GUI-Menus.Purchase Menu.GUI-Name"));
                    }
                    if (OldConfigurationManager.messages.get("GUI-Menus.Purchase Menu.Items.Confirm") != null) {
                        if (OldConfigurationManager.messages.get("GUI-Menus.Purchase Menu.Items.Confirm.Name") != null) {
                            OldConfigurationManager.messages.set("GUI-Menus.Purchase-Menu.Items.Confirm.Name", OldConfigurationManager.messages.getString("GUI-Menus.Purchase Menu.Items.Confirm.Name"));
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Purchase Menu.Items.Confirm.Material") != null) {
                            OldConfigurationManager.messages.set("GUI-Menus.Purchase-Menu.Items.Confirm.Material", OldConfigurationManager.messages.getString("GUI-Menus.Purchase Menu.Items.Confirm.Material"));
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Purchase Menu.Items.Confirm.Lore") != null && !OldConfigurationManager.messages.getString("GUI-Menus.Purchase Menu.Items.Confirm.Lore").equals("")) {
                            OldConfigurationManager.messages.set("GUI-Menus.Purchase-Menu.Items.Confirm.Lore", OldConfigurationManager.messages.getStringList("GUI-Menus.Purchase Menu.Items.Confirm.Lore"));
                        }
                    }
                    if (OldConfigurationManager.messages.get("GUI-Menus.Purchase Menu.Items.Cancel") != null) {
                        if (OldConfigurationManager.messages.get("GUI-Menus.Purchase Menu.Items.Cancel.Name") != null) {
                            OldConfigurationManager.messages.set("GUI-Menus.Purchase-Menu.Items.Cancel.Name", OldConfigurationManager.messages.getString("GUI-Menus.Purchase Menu.Items.Cancel.Name"));
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Purchase Menu.Items.Cancel.Material") != null) {
                            OldConfigurationManager.messages.set("GUI-Menus.Purchase-Menu.Items.Cancel.Material", OldConfigurationManager.messages.getString("GUI-Menus.Purchase Menu.Items.Cancel.Material"));
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Purchase Menu.Items.Cancel.Lore") != null && !OldConfigurationManager.messages.getString("GUI-Menus.Purchase Menu.Items.Cancel.Lore").equals("")) {
                            OldConfigurationManager.messages.set("GUI-Menus.Purchase-Menu.Items.Cancel.Lore", OldConfigurationManager.messages.getStringList("GUI-Menus.Purchase Menu.Items.Cancel.Lore"));
                        }
                    }
                }
                if (OldConfigurationManager.messages.get("GUI-Menus.Confirm Menu") != null) {
                    if (OldConfigurationManager.messages.get("GUI-Menus.Confirm Menu.GUI-Name") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Confirm-Open-Mystery-Box-Menu.GUI-Name", OldConfigurationManager.messages.getString("GUI-Menus.Confirm Menu.GUI-Name"));
                    }
                    if (OldConfigurationManager.messages.get("GUI-Menus.Confirm Menu.Items.Open") != null) {
                        if (OldConfigurationManager.messages.get("GUI-Menus.Confirm Menu.Items.Open.Name") != null) {
                            OldConfigurationManager.messages.set("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Open.Name", OldConfigurationManager.messages.getString("GUI-Menus.Confirm Menu.Items.Open.Name"));
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Confirm Menu.Items.Open.Material") != null) {
                            OldConfigurationManager.messages.set("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Open.Material", OldConfigurationManager.messages.getString("GUI-Menus.Confirm Menu.Items.Open.Material"));
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Confirm Menu.Items.Open.Lore") != null && !OldConfigurationManager.messages.getString("GUI-Menus.Confirm Menu.Items.Open.Lore").equals("")) {
                            OldConfigurationManager.messages.set("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Open.Lore", OldConfigurationManager.messages.getStringList("GUI-Menus.Confirm Menu.Items.Open.Lore"));
                        }
                    }
                    if (OldConfigurationManager.messages.get("GUI-Menus.Confirm Menu.Items.Cancel") != null) {
                        if (OldConfigurationManager.messages.get("GUI-Menus.Confirm Menu.Items.Cancel.Name") != null) {
                            OldConfigurationManager.messages.set("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Cancel.Name", OldConfigurationManager.messages.getString("GUI-Menus.Confirm Menu.Items.Cancel.Name"));
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Confirm Menu.Items.Cancel.Material") != null) {
                            OldConfigurationManager.messages.set("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Cancel.Material", OldConfigurationManager.messages.getString("GUI-Menus.Confirm Menu.Items.Cancel.Material"));
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Confirm Menu.Items.Cancel.Lore") != null && !OldConfigurationManager.messages.getString("GUI-Menus.Confirm Menu.Items.Cancel.Lore").equals("")) {
                            OldConfigurationManager.messages.set("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Cancel.Lore", OldConfigurationManager.messages.getStringList("GUI-Menus.Confirm Menu.Items.Cancel.Lore"));
                        }
                    }
                }
                if (OldConfigurationManager.messages.get("GUI-Menus.Mystery Vault Menu") != null) {
                    if (OldConfigurationManager.messages.get("GUI-Menus.Mystery Vault Menu.GUI-Name") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Mystery-Vault-Menu.GUI-Name", OldConfigurationManager.messages.getString("GUI-Menus.Mystery Vault Menu.GUI-Name"));
                    }
                    if (OldConfigurationManager.messages.get("GUI-Menus.Mystery Vault Menu.Items") != null) {
                        if (OldConfigurationManager.messages.get("GUI-Menus.Mystery Vault Menu.Items.Error") != null) {
                            if (OldConfigurationManager.messages.get("GUI-Menus.Mystery Vault Menu.Items.Error.Name") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Vault-Menu.Items.Error.Name", OldConfigurationManager.messages.getString("GUI-Menus.Mystery Vault Menu.Items.Error.Name"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Mystery Vault Menu.Items.Error.Material") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Vault-Menu.Items.Error.Material", OldConfigurationManager.messages.getString("GUI-Menus.Mystery Vault Menu.Items.Error.Material"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Mystery Vault Menu.Items.Error.Lore") != null && !OldConfigurationManager.messages.getString("GUI-Menus.Mystery Vault Menu.Items.Error.Lore").equals("")) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Vault-Menu.Items.Error.Lore", OldConfigurationManager.messages.getStringList("GUI-Menus.Mystery Vault Menu.Items.Error.Lore"));
                            }
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Mystery Vault Menu.Items.Craft Mystery Boxes") != null) {
                            if (OldConfigurationManager.messages.get("GUI-Menus.Mystery Vault Menu.Items.Craft Mystery Boxes.Name") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Vault-Menu.Items.Craft-Mystery-Boxes.Name", OldConfigurationManager.messages.getString("GUI-Menus.Mystery Vault Menu.Items.Craft Mystery Boxes.Name"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Mystery Vault Menu.Items.Craft Mystery Boxes.Material") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Vault-Menu.Items.Craft-Mystery-Boxes.Material", OldConfigurationManager.messages.getString("GUI-Menus.Mystery Vault Menu.Items.Craft Mystery Boxes.Material"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Mystery Vault Menu.Items.Craft Mystery Boxes.Lore") != null && !OldConfigurationManager.messages.getString("GUI-Menus.Mystery Vault Menu.Items.Craft Mystery Boxes.Lore").equals("")) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Vault-Menu.Items.Craft-Mystery-Boxes.Lore", OldConfigurationManager.messages.getStringList("GUI-Menus.Mystery Vault Menu.Items.Craft Mystery Boxes.Lore"));
                            }
                        }
                    }
                }
                if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu") != null) {
                    if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.GUI-Name") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.GUI-Name", OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.GUI-Name"));
                    }
                    if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items") != null) {
                        if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.1 Star") != null) {
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.1 Star.Name") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Name", OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.1 Star.Name"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.1 Star.Material") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Material", OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.1 Star.Material"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.1 Star.Price") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Price", OldConfigurationManager.messages.getInt("GUI-Menus.Craft Mystery Boxes Menu.Items.1 Star.Price"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.1 Star.Lore") != null && !OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.1 Star.Lore").equals("")) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Lore", OldConfigurationManager.messages.getStringList("GUI-Menus.Craft Mystery Boxes Menu.Items.1 Star.Lore"));
                            }
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.2 Star") != null) {
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.2 Star.Name") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Name", OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.2 Star.Name"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.2 Star.Material") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Material", OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.2 Star.Material"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.2 Star.Price") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Price", OldConfigurationManager.messages.getInt("GUI-Menus.Craft Mystery Boxes Menu.Items.2 Star.Price"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.2 Star.Lore") != null && !OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.2 Star.Lore").equals("")) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Lore", OldConfigurationManager.messages.getStringList("GUI-Menus.Craft Mystery Boxes Menu.Items.2 Star.Lore"));
                            }
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.3 Star") != null) {
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.3 Star.Name") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Name", OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.3 Star.Name"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.3 Star.Material") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Material", OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.3 Star.Material"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.3 Star.Price") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Price", OldConfigurationManager.messages.getInt("GUI-Menus.Craft Mystery Boxes Menu.Items.3 Star.Price"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.3 Star.Lore") != null && !OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.3 Star.Lore").equals("")) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Lore", OldConfigurationManager.messages.getStringList("GUI-Menus.Craft Mystery Boxes Menu.Items.3 Star.Lore"));
                            }
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.4 Star") != null) {
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.4 Star.Name") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Name", OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.4 Star.Name"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.4 Star.Material") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Material", OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.4 Star.Material"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.4 Star.Price") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Price", OldConfigurationManager.messages.getInt("GUI-Menus.Craft Mystery Boxes Menu.Items.4 Star.Price"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.4 Star.Lore") != null && !OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.4 Star.Lore").equals("")) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Lore", OldConfigurationManager.messages.getStringList("GUI-Menus.Craft Mystery Boxes Menu.Items.4 Star.Lore"));
                            }
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.5 Star") != null) {
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.5 Star.Name") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Name", OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.5 Star.Name"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.5 Star.Material") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Material", OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.5 Star.Material"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.5 Star.Price") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Price", OldConfigurationManager.messages.getInt("GUI-Menus.Craft Mystery Boxes Menu.Items.5 Star.Price"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Craft Mystery Boxes Menu.Items.5 Star.Lore") != null && !OldConfigurationManager.messages.getString("GUI-Menus.Craft Mystery Boxes Menu.Items.5 Star.Lore").equals("")) {
                                OldConfigurationManager.messages.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Lore", OldConfigurationManager.messages.getStringList("GUI-Menus.Craft Mystery Boxes Menu.Items.5 Star.Lore"));
                            }
                        }
                    }
                }
                if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu") != null) {
                    if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.GUI-Name") != null) {
                        OldConfigurationManager.messages.set("GUI-Menus.Settings-Menu.GUI-Name", OldConfigurationManager.messages.getString("GUI-Menus.Settings Menu.GUI-Name"));
                    }
                    if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items") != null) {
                        if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Ignore Cooldown") != null) {
                            if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Ignore Cooldown.Name") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Settings-Menu.Items.Ignore-Cooldown.Name", OldConfigurationManager.messages.getString("GUI-Menus.Settings Menu.Items.Ignore Cooldown.Name"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Ignore Cooldown.Material") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Settings-Menu.Items.Ignore-Cooldown.Material", OldConfigurationManager.messages.getString("GUI-Menus.Settings Menu.Items.Ignore Cooldown.Material"));
                            }
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Morphs Self View") != null) {
                            if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Morphs Self View.Name") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Settings-Menu.Items.Self-Morph-View.Name", OldConfigurationManager.messages.getString("GUI-Menus.Settings Menu.Items.Morphs Self View.Name"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Morphs Self View.Material") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Settings-Menu.Items.Self-Morph-View.Material", OldConfigurationManager.messages.getString("GUI-Menus.Settings Menu.Items.Morphs Self View.Material"));
                            }
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Enabled") != null) {
                            if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Enabled.Name") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Settings-Menu.Items.Enabled.Name", OldConfigurationManager.messages.getString("GUI-Menus.Settings Menu.Items.Enabled.Name"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Enabled.Material") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Settings-Menu.Items.Enabled.Material", OldConfigurationManager.messages.getString("GUI-Menus.Settings Menu.Items.Enabled.Material"));
                            }
                        }
                        if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Disabled") != null) {
                            if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Disabled.Name") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Settings-Menu.Items.Disabled.Name", OldConfigurationManager.messages.getString("GUI-Menus.Settings Menu.Items.Disabled.Name"));
                            }
                            if (OldConfigurationManager.messages.get("GUI-Menus.Settings Menu.Items.Disabled.Material") != null) {
                                OldConfigurationManager.messages.set("GUI-Menus.Settings-Menu.Items.Disabled.Material", OldConfigurationManager.messages.getString("GUI-Menus.Settings Menu.Items.Disabled.Material"));
                            }
                        }
                    }
                }
            }
            OldConfigurationManager.config.set("CookieGadgets", (Object)null);
            OldConfigurationManager.config.set("Player-Data.Database", (Object)null);
            OldConfigurationManager.config.set("Purchase System", (Object)null);
            OldConfigurationManager.config.set("Menu Item", (Object)null);
            OldConfigurationManager.config.set("Enabled Worlds", (Object)null);
            OldConfigurationManager.config.set("Disabled Cosmetics", (Object)null);
            OldConfigurationManager.config.set("Close GUI Menu After Select", (Object)null);
            OldConfigurationManager.config.set("Permission.Show in Lore", (Object)null);
            OldConfigurationManager.config.set("Permission.No Permission", (Object)null);
            OldConfigurationManager.config.set("Permission.Has Permission", (Object)null);
            OldConfigurationManager.config.set("Check Update", (Object)null);
            OldConfigurationManager.config.set("Auto Update", (Object)null);
            OldConfigurationManager.config.set("Config Version", (Object)null);
            OldConfigurationManager.config.set("Mystery Box", (Object)null);
            OldConfigurationManager.messages.set("Items.Go Back", null);
            OldConfigurationManager.messages.set("Items.Previous Page", null);
            OldConfigurationManager.messages.set("Items.Next Page", null);
            OldConfigurationManager.messages.set("Items.Already Selected", null);
            OldConfigurationManager.messages.set("Items.Click To Select", null);
            OldConfigurationManager.messages.set("Items.Unlocked.Show in Lore", null);
            OldConfigurationManager.messages.set("Items.MainMenu Button", null);
            OldConfigurationManager.messages.set("Items.Page", null);
            OldConfigurationManager.messages.set("Items.Morph Slimeball", null);
            OldConfigurationManager.messages.set("Items.Morphs Self View", null);
            OldConfigurationManager.messages.set("Items.Rename Pet", null);
            OldConfigurationManager.messages.set("GUI-Menus.Purchase Menu", null);
            OldConfigurationManager.messages.set("GUI-Menus.Settings Menu", null);
            OldConfigurationManager.messages.set("GUI-Menus.Confirm Menu", null);
            OldConfigurationManager.messages.set("GUI-Menus.Mystery Vault Menu", null);
            OldConfigurationManager.messages.set("GUI-Menus.Craft Mystery Boxes Menu", null);
            OldConfigurationManager.messages.set("Reset Button", null);
            OldConfigurationManager.messages.set("Plugin-Reloaded", null);
            OldConfigurationManager.messages.set("Reloading-Plugin", null);
            OldConfigurationManager.messages.set("Purchase-Is-Disabled", null);
            OldConfigurationManager.messages.set("Give-Selector", null);
            OldConfigurationManager.messages.set("No-Pet-Spawned", null);
            OldConfigurationManager.messages.set("Received-Menu", null);
            OldConfigurationManager.messages.set("Morphs-Self-View", null);
            OldConfigurationManager.messages.set("Radius-Is-Too-Big", null);
            OldConfigurationManager.messages.set("Play-A-Track", null);
            OldConfigurationManager.messages.set("Length-Too-Long", null);
            OldConfigurationManager.mysteryVault.set("Mystery Vault", null);
            OldConfigurationManager.config.set("Config-Version", "1.0.0", "Do not edit this.");
            FileManager.getGadgetsFile().set("Gadgets.Movement.Types.Paint Trail.BlackList", null);
            FileManager.getGadgetsFile().set("Gadgets.Projectile.Types.Paintball Gun.BlackList", null);
            FileManager.getGadgetsFile().set("Gadgets.Musical.Types.Jukebox.Items.Stop Track", null);
            CookieGadgets.getInstance().config = CustomConfiguration.loadConfiguration(CookieGadgets.getInstance().getFile());
            try {
                OldConfigurationManager.config.save(CookieGadgets.getInstance().getFile());
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            FileManager.getMessagesFile().reload();
            FileManager.getMessagesFile().save();
            FileManager.getMysteryVaultFile().reload();
            FileManager.getMysteryVaultFile().save();
            LoggerManager.printLogWithHeader(LoggerManager.LogLevel.INFO, "------------------------------------------------------", "Disable CookieGadgets due to first time load this plugin.", "You need to restart server to enable CookieGadgets plugin.");
            CookieGadgets.getInstance().getServer().getPluginManager().disablePlugin((Plugin)CookieGadgets.getInstance());
        }
    }
}
