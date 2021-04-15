

package ws.billy.CookieGadgets.configuration;

import java.util.List;
import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.database.DatabaseStorage;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Arrays;
import java.util.Collection;
import ws.billy.CookieGadgets.log.LoggerManager;
import ws.billy.CookieGadgets.utils.SoundEffect;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.EnumMaterial;

public class ConfigVersionManager
{
    public static void updateConfig() {
        Label_7419: {
            Label_7390: {
                Label_7322: {
                    Label_4015: {
                        Label_3916: {
                            Label_3812: {
                                Label_3767: {
                                    Label_3649: {
                                        Label_3610: {
                                            Label_3341: {
                                                Label_1030: {
                                                    Label_0988: {
                                                        Label_0951: {
                                                            Label_0901: {
                                                                Label_0836: {
                                                                    Label_0781: {
                                                                        final String string;
                                                                        switch (string = FileManager.getConfigFile().getString("Config-Version")) {
                                                                            case "1.0.0": {
                                                                                addConfigMessage("Settings.Default-Self-Morph-View", true, "The default self morph view setting.");
                                                                                addConfigMessage("Settings.Enabled-Self-Morph-View", true, "Do you want to enable self morph view?");
                                                                                FileManager.getConfigFile().set("Config-Version", "1.0.1", "Do not edit this.");
                                                                                break;
                                                                            }
                                                                            case "1.0.1": {
                                                                                break;
                                                                            }
                                                                            case "1.0.2": {
                                                                                break Label_0781;
                                                                            }
                                                                            case "1.0.3": {
                                                                                break Label_0836;
                                                                            }
                                                                            case "1.0.4": {
                                                                                break Label_0901;
                                                                            }
                                                                            case "1.0.5": {
                                                                                break Label_0951;
                                                                            }
                                                                            case "1.0.6": {
                                                                                break Label_0988;
                                                                            }
                                                                            case "1.0.7": {
                                                                                break Label_1030;
                                                                            }
                                                                            case "1.0.8": {
                                                                                break Label_3341;
                                                                            }
                                                                            case "1.0.9": {
                                                                                break Label_3610;
                                                                            }
                                                                            case "2.0.0": {
                                                                                break Label_7322;
                                                                            }
                                                                            case "2.0.1": {
                                                                                break Label_7390;
                                                                            }
                                                                            case "2.0.2": {
                                                                                break Label_7419;
                                                                            }
                                                                            case "1.0.10": {
                                                                                break Label_3649;
                                                                            }
                                                                            case "1.0.11": {
                                                                                break Label_3767;
                                                                            }
                                                                            case "1.0.12": {
                                                                                break Label_3812;
                                                                            }
                                                                            case "1.0.13": {
                                                                                break Label_3916;
                                                                            }
                                                                            case "1.0.14": {
                                                                                break Label_4015;
                                                                            }
                                                                            case "1.0.15": {
                                                                                break Label_4015;
                                                                            }
                                                                            case "1.0.16": {
                                                                                break Label_4015;
                                                                            }
                                                                            case "1.0.17": {
                                                                                break Label_4015;
                                                                            }
                                                                            case "1.0.18": {
                                                                                break Label_4015;
                                                                            }
                                                                            case "1.0.19": {
                                                                                break Label_4015;
                                                                            }
                                                                        }
                                                                        final FileManager messagesFile = FileManager.getMessagesFile();
                                                                        if (messagesFile.get("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Price") != null && messagesFile.getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Price") == 0 && messagesFile.getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Price").equalsIgnoreCase("100")) {
                                                                            messagesFile.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Price", 100);
                                                                        }
                                                                        if (messagesFile.get("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Price") != null && messagesFile.getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Price") == 0 && messagesFile.getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Price").equalsIgnoreCase("200")) {
                                                                            messagesFile.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Price", 200);
                                                                        }
                                                                        if (messagesFile.get("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Price") != null && messagesFile.getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Price") == 0 && messagesFile.getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Price").equalsIgnoreCase("300")) {
                                                                            messagesFile.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Price", 300);
                                                                        }
                                                                        if (messagesFile.get("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Price") != null && messagesFile.getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Price") == 0 && messagesFile.getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Price").equalsIgnoreCase("400")) {
                                                                            messagesFile.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Price", 400);
                                                                        }
                                                                        if (messagesFile.get("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Price") != null && messagesFile.getInt("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Price") == 0 && messagesFile.getString("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Price").equalsIgnoreCase("550")) {
                                                                            messagesFile.set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Price", 550);
                                                                        }
                                                                        FileManager.getConfigFile().set("Config-Version", "1.0.2", "Do not edit this.");
                                                                    }
                                                                    addConfigMessage("Settings.Auto-Equip-After-Purchase", true, "Auto equip cosmetic after player purchase.");
                                                                    addConfigMessage("Settings.Auto-Equip-On-Loot-Found", true, "Auto equip cosmetic when player found loot from mystery box.");
                                                                    FileManager.getConfigFile().set("Config-Version", "1.0.3", "Do not edit this.");
                                                                }
                                                                addConfigMessage("Settings.Show-Particle-Effect-To-Everyone", true, "Set to true will shows particle effect to everyone,", "otherwise will only show to the player itself.");
                                                                addConfigMessage("Settings.Show-Cloak-Effect-To-Everyone", true, "Set to true will shows cloak effect to everyone,", "otherwise will only show to the player itself.");
                                                                FileManager.getConfigFile().set("Config-Version", "1.0.4", "Do not edit this.");
                                                            }
                                                            addConfigMessage("Settings.Equip-Cosmetic-Item-To-Slot", "WARN", "Do action when player equip cosmetic.", "Replace: Replace the old item with equipped cosmetic.", "Warn: Send a warning message to the player and reject to equip cosmetic.", "Drop: Drop the old item on the ground and equip cosmetic.");
                                                            FileManager.getConfigFile().set("Config-Version", "1.0.5", "Do not edit this.");
                                                        }
                                                        addConfigMessage("Cosmetic-Item-Purchase.Reopen-GUI-Menu-After-Purchase", true, "Reopen GUI menu after player purchase item.");
                                                        FileManager.getConfigFile().set("Config-Version", "1.0.6", "Do not edit this.");
                                                    }
                                                    addConfigMessage("Settings.Sync-Cosmetics-On-Join", true, "Sync player's selected cosmetics when they join the server.", "Set to 'false' to reduce server lag.");
                                                    FileManager.getConfigFile().set("Config-Version", "1.0.7", "Do not edit this.");
                                                }
                                                if (FileManager.getMessagesFile().get("Play-A-Track") != null) {
                                                    FileManager.getMessagesFile().set("Play-A-Track", null);
                                                }
                                                final String string2 = FileManager.getConfigFile().getString("Menu-Item.Material");
                                                if (string2 != null) {
                                                    int intValue = 0;
                                                    int n;
                                                    if (string2.contains(":")) {
                                                        n = Integer.valueOf(string2.split("\\:")[0]);
                                                        intValue = Integer.valueOf(string2.split("\\:")[1]);
                                                    }
                                                    else {
                                                        n = Integer.valueOf(string2);
                                                    }
                                                    setConfigMessage("Menu-Item.Material", String.valueOf(EnumMaterial.valueOf(n, intValue).getName()) + ((intValue > 15) ? (":" + intValue) : ""), "The material of the selector.");
                                                }
                                                final String string3 = FileManager.getConfigFile().getString("Permission.No-Permission.Show-Custom-Item.Material");
                                                if (string3 != null) {
                                                    int intValue2 = 0;
                                                    int n2;
                                                    if (string3.contains(":")) {
                                                        n2 = Integer.valueOf(string3.split("\\:")[0]);
                                                        intValue2 = Integer.valueOf(string3.split("\\:")[1]);
                                                    }
                                                    else {
                                                        n2 = Integer.valueOf(string3);
                                                    }
                                                    setConfigMessage("Permission.No-Permission.Show-Custom-Item.Material", String.valueOf(EnumMaterial.valueOf(n2, intValue2).getName()) + ((intValue2 > 15) ? (":" + intValue2) : ""), new String[0]);
                                                }
                                                final String string4 = FileManager.getConfigFile().getString("Fill-Empty-Slot-With-Item.Material");
                                                if (string4 != null) {
                                                    int intValue3 = 0;
                                                    int n3;
                                                    if (string4.contains(":")) {
                                                        n3 = Integer.valueOf(string4.split("\\:")[0]);
                                                        intValue3 = Integer.valueOf(string4.split("\\:")[1]);
                                                    }
                                                    else {
                                                        n3 = Integer.valueOf(string4);
                                                    }
                                                    setConfigMessage("Fill-Empty-Slot-With-Item.Material", String.valueOf(EnumMaterial.valueOf(n3, intValue3).getName()) + ((intValue3 > 15) ? (":" + intValue3) : ""), new String[0]);
                                                }
                                                updateMainMenuFileMaterial("MainMenu.Hats.Material");
                                                updateMainMenuFileMaterial("MainMenu.Particles.Material");
                                                updateMainMenuFileMaterial("MainMenu.Suits.Material");
                                                updateMainMenuFileMaterial("MainMenu.Gadgets.Material");
                                                updateMainMenuFileMaterial("MainMenu.Pets.Material");
                                                updateMainMenuFileMaterial("MainMenu.Morphs.Material");
                                                updateMainMenuFileMaterial("MainMenu.Banners.Material");
                                                if (FileManager.getMainMenuFile().get("MainMenu.Emotes.Material") != null && FileManager.getMainMenuFile().getString("MainMenu.Emotes.Material").equals("1217:1")) {
                                                    FileManager.getMainMenuFile().set("MainMenu.Emotes.Material", "head:60c432cbc490a8af6e9dfeb28095c0a0ec79fff705fb184674d1e743bd05baa");
                                                }
                                                updateMainMenuFileMaterial("MainMenu.Cloaks.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Purchase-Menu.Items.Confirm.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Purchase-Menu.Items.Cancel.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Open.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Cancel.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Mystery-Vault-Menu.Items.Error.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Mystery-Vault-Menu.Items.Loading-Mystery-Boxes.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Mystery-Vault-Menu.Items.Mystery-Box-Information.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Mystery-Vault-Menu.Items.Craft-Mystery-Boxes.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Mystery-Vault-Menu.Items.Animations.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Mystery-Vault-Menu.Items.Open-Multiple-Boxes.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Gift-Inventory-Menu.Items.Mystery-Gift.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Gift-Inventory-Menu.Items.Error.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Send-Gift-Menu.Items.Mystery-Gift.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Confirm-Send-Gift-Menu.Items.Mystery-Gift.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Confirm-Send-Gift-Menu.Items.Confirm.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Confirm-Send-Gift-Menu.Items.Cancel.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Settings-Menu.Items.Ignore-Cooldown.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Settings-Menu.Items.Self-Morph-View.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Settings-Menu.Items.Enabled.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Settings-Menu.Items.Disabled.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-20-Boxes.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-50-Boxes.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-250-Boxes.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Confirm-Open-Multiple-Boxes-Menu.Items.Confirm.Material");
                                                updateMessagesFileMaterial("GUI-Menus.Confirm-Open-Multiple-Boxes-Menu.Items.Cancel.Material");
                                                updateMessagesFileMaterial("Items.Go-Back.Material");
                                                updateMessagesFileMaterial("Items.Previous-Page.Material");
                                                updateMessagesFileMaterial("Items.Next-Page.Material");
                                                updateMessagesFileMaterial("Items.Settings.Material");
                                                updateMessagesFileMaterial("Items.MainMenu-Item.Material");
                                                updateMessagesFileMaterial("Items.Self-Morph-View.Material");
                                                updateMessagesFileMaterial("Items.Rename-Pet.Material");
                                                updateMessagesFileMaterial("Items.Random-Mystery-Vault-Animation.Material");
                                                updateMessagesFileMaterial("Reset-Buttons.Reset-Cosmetics.Material");
                                                updateMessagesFileMaterial("Reset-Buttons.Reset-Hat.Material");
                                                updateMessagesFileMaterial("Reset-Buttons.Reset-Particle.Material");
                                                updateMessagesFileMaterial("Reset-Buttons.Reset-Suit.Material");
                                                updateMessagesFileMaterial("Reset-Buttons.Reset-Gadget.Material");
                                                updateMessagesFileMaterial("Reset-Buttons.Reset-Pet.Material");
                                                updateMessagesFileMaterial("Reset-Buttons.Reset-Morph.Material");
                                                updateMessagesFileMaterial("Reset-Buttons.Reset-Banner.Material");
                                                updateMessagesFileMaterial("Reset-Buttons.Reset-Emote.Material");
                                                updateMessagesFileMaterial("Reset-Buttons.Reset-Cloak.Material");
                                                updateMysteryBoxesFileMaterial("Mystery-Boxes.Types.Normal-Mystery-Box.Material");
                                                updateMysteryBoxesFileMaterial("Mystery-Boxes.Types.Gifted-Mystery-Box.Material");
                                                updateMysteryBoxesFileMaterial("Mystery-Boxes.Types.Crafted-Mystery-Box.One-Star.Material");
                                                updateMysteryBoxesFileMaterial("Mystery-Boxes.Types.Crafted-Mystery-Box.Two-Star.Material");
                                                updateMysteryBoxesFileMaterial("Mystery-Boxes.Types.Crafted-Mystery-Box.Three-Star.Material");
                                                updateMysteryBoxesFileMaterial("Mystery-Boxes.Types.Crafted-Mystery-Box.Four-Star.Material");
                                                updateMysteryBoxesFileMaterial("Mystery-Boxes.Types.Crafted-Mystery-Box.Five-Star.Material");
                                                updateAnimationsFileMaterial("Animations.None.Material");
                                                updateAnimationsFileMaterial("Animations.None.Mini-Block.Material");
                                                updateAnimationsFileMaterial("Animations.Normal.Material");
                                                updateAnimationsFileMaterial("Animations.Normal.Mini-Block.Material");
                                                updateAnimationsFileMaterial("Animations.CountDown.Material");
                                                updateAnimationsFileMaterial("Animations.CountDown.Mini-Block.Material");
                                                updateAnimationsFileMaterial("Animations.Star.Material");
                                                updateAnimationsFileMaterial("Animations.Star.Mini-Block.Material");
                                                updateAnimationsFileMaterial("Animations.Star.Mystery-Box-Quality-Block.Material");
                                                updateAnimationsFileMaterial("Animations.Crafting.Material");
                                                updateAnimationsFileMaterial("Animations.Crafting.Mini-Block.Material");
                                                updateAnimationsFileMaterial("Animations.Crafting.Mystery-Box-Quality-Block.Material");
                                                updateAnimationsFileMaterial("Animations.Summer.Material");
                                                updateAnimationsFileMaterial("Animations.Summer.Mini-Block.Material");
                                                updateAnimationsFileMaterial("Animations.Summer.Mystery-Box-Quality-Block.Material");
                                                updateAnimationsFileMaterial("Animations.Halloween.Material");
                                                updateAnimationsFileMaterial("Animations.Halloween.Mini-Block.Material");
                                                updateAnimationsFileMaterial("Animations.Holiday.Material");
                                                updateAnimationsFileMaterial("Animations.Holiday.Mini-Block.Material");
                                                updateAnimationsFileMaterial("Animations.Holiday.Mystery-Box-Quality-Block.Material");
                                                updateGadgetFileMaterial("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Place-Symbol.Material");
                                                updateGadgetFileMaterial("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Not-Your-Turn.Material");
                                                updateGadgetFileMaterial("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Challenger.Material");
                                                updateGadgetFileMaterial("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Opponent.Material");
                                                updateGadgetFileMaterial("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Challenger-Symbol.Material");
                                                updateGadgetFileMaterial("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Opponent-Symbol.Material");
                                                updateGadgetFileMaterial("Gadgets.Musical.Types.Jukebox.Items.Stop-Track.Material");
                                                updateGadgetFileMaterial("Gadgets.Musical.Types.Radio.Items.Track.Material");
                                                updateGadgetFileMaterial("Gadgets.Musical.Types.Radio.Items.Stop-Track.Material");
                                                try {
                                                    final ArrayList<String> list = new ArrayList<String>();
                                                    for (final String s : FileManager.getGadgetsFile().getStringList("Gadgets.Movement.Types.Paint Trail.BlackList")) {
                                                        int intValue4 = 0;
                                                        boolean b = false;
                                                        String substring = "";
                                                        if (s.contains(":")) {
                                                            if (s.startsWith("head")) {
                                                                final int n4 = 373;
                                                                intValue4 = 3;
                                                                b = true;
                                                                substring = s.substring(5);
                                                            }
                                                            else {
                                                                final int n4 = Integer.valueOf(s.split("\\:")[0]);
                                                                intValue4 = Integer.valueOf(s.split("\\:")[1]);
                                                            }
                                                        }
                                                        else {
                                                            final int n4 = Integer.valueOf(s);
                                                        }
                                                        int n4;
                                                        list.add(b ? ("head:" + substring) : (String.valueOf(EnumMaterial.valueOf(n4, intValue4).getName()) + ((intValue4 > 15) ? (":" + intValue4) : "")));
                                                    }
                                                    FileManager.getGadgetsFile().set("Gadgets.Movement.Types.Paint Trail.BlackList", list);
                                                    final ArrayList<String> list2 = new ArrayList<String>();
                                                    for (final String s2 : FileManager.getGadgetsFile().getStringList("Gadgets.Projectile.Types.Paintball Gun.BlackList")) {
                                                        int intValue5 = 0;
                                                        boolean b2 = false;
                                                        String substring2 = "";
                                                        if (s2.contains(":")) {
                                                            if (s2.startsWith("head")) {
                                                                final int n5 = 373;
                                                                intValue5 = 3;
                                                                b2 = true;
                                                                substring2 = s2.substring(5);
                                                            }
                                                            else {
                                                                final int n5 = Integer.valueOf(s2.split("\\:")[0]);
                                                                intValue5 = Integer.valueOf(s2.split("\\:")[1]);
                                                            }
                                                        }
                                                        else {
                                                            final int n5 = Integer.valueOf(s2);
                                                        }
                                                        int n5;
                                                        list2.add(b2 ? ("head:" + substring2) : (String.valueOf(EnumMaterial.valueOf(n5, intValue5).getName()) + ((intValue5 > 15) ? (":" + intValue5) : "")));
                                                    }
                                                    FileManager.getGadgetsFile().set("Gadgets.Projectile.Types.Paintball Gun.BlackList", list2);
                                                }
                                                catch (NumberFormatException ex) {}
                                                final FileManager customParticlesFile = FileManager.getCustomParticlesFile();
                                                if (customParticlesFile.get("Custom-Particles") != null) {
                                                    for (final String s3 : customParticlesFile.getConfigurationSection("Custom-Particles").getKeys(false)) {
                                                        final String string5 = customParticlesFile.getString("Custom-Particles." + s3 + ".Material");
                                                        int intValue6 = 0;
                                                        boolean b3 = false;
                                                        String substring3 = "";
                                                        if (string5.contains(":")) {
                                                            if (string5.startsWith("head")) {
                                                                final int n6 = 373;
                                                                intValue6 = 3;
                                                                b3 = true;
                                                                substring3 = string5.substring(5);
                                                            }
                                                            else {
                                                                final int n6 = Integer.valueOf(string5.split("\\:")[0]);
                                                                intValue6 = Integer.valueOf(string5.split("\\:")[1]);
                                                            }
                                                        }
                                                        else {
                                                            final int n6 = Integer.valueOf(string5);
                                                        }
                                                        int n6;
                                                        customParticlesFile.set("Custom-Particles." + s3 + ".Material", b3 ? ("head:" + substring3) : (String.valueOf(EnumMaterial.valueOf(n6, intValue6).getName()) + ((intValue6 > 15) ? (":" + intValue6) : "")));
                                                    }
                                                }
                                                FileManager.getConfigFile().set("Permission.No-Permission.Play-Sound.Sound", (Object)SoundEffect.valueOfSound(FileManager.getConfigFile().getString("Permission.No-Permission.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getConfigFile().set("Permission.Has-Permission.Play-Sound.Sound", (Object)SoundEffect.valueOfSound(FileManager.getConfigFile().getString("Permission.Has-Permission.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Items.Enough-Mystery-Dust.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Items.Enough-Mystery-Dust.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Items.Not-Enough-Mystery-Dust.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Items.Not-Enough-Mystery-Dust.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Items.Item-Unpurchasable.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Items.Item-Unpurchasable.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Items.Failed-To-Purchase.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Items.Failed-To-Purchase.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Reset-Buttons.Reset-Cosmetics.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Cosmetics.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Reset-Buttons.Reset-Hat.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Hat.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Reset-Buttons.Reset-Particle.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Particle.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Reset-Buttons.Reset-Suit.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Suit.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Reset-Buttons.Reset-Gadget.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Gadget.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Reset-Buttons.Reset-Pet.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Pet.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Reset-Buttons.Reset-Morph.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Morph.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Reset-Buttons.Reset-Banner.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Banner.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Reset-Buttons.Reset-Emote.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Emote.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMessagesFile().set("Reset-Buttons.Reset-Cloak.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Cloak.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Found-Loot.Common.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Found-Loot.Common.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Found-Loot.Rare.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Found-Loot.Rare.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Found-Loot.Epic.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Found-Loot.Epic.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Found-Loot.Legendary.Play-Sound.Sound", SoundEffect.valueOfSound(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Found-Loot.Legendary.Play-Sound.Sound")).getDefaultName());
                                                FileManager.getConfigFile().set("Config-Version", "1.0.8", "Do not edit this.");
                                            }
                                            if (FileManager.getMainMenuFile().get("MainMenu.Emotes.Material") != null && FileManager.getMainMenuFile().getString("MainMenu.Emotes.Material").equals("CUSTOM_MATERIAL_2")) {
                                                FileManager.getMainMenuFile().set("MainMenu.Emotes.Material", "head:60c432cbc490a8af6e9dfeb28095c0a0ec79fff705fb184674d1e743bd05baa");
                                            }
                                            if (FileManager.getMessagesFile().get("Items.Random-Mystery-Vault-Animation") != null) {
                                                FileManager.getMessagesFile().set("GUI-Menus.Mystery-Vault-Animations-Menu.Items.Random-Mystery-Vault-Animation.Name", FileManager.getMessagesFile().get("Items.Random-Mystery-Vault-Animation.Name"));
                                                FileManager.getMessagesFile().set("GUI-Menus.Mystery-Vault-Animations-Menu.Items.Random-Mystery-Vault-Animation.Material", FileManager.getMessagesFile().get("Items.Random-Mystery-Vault-Animation.Material"));
                                                FileManager.getMessagesFile().set("GUI-Menus.Mystery-Vault-Animations-Menu.Items.Random-Mystery-Vault-Animation.Slot", 40);
                                                FileManager.getMessagesFile().set("GUI-Menus.Mystery-Vault-Animations-Menu.Items.Random-Mystery-Vault-Animation.Show", FileManager.getMessagesFile().get("Items.Random-Mystery-Vault-Animation.Show"));
                                                FileManager.getMessagesFile().set("GUI-Menus.Mystery-Vault-Animations-Menu.Items.Random-Mystery-Vault-Animation.Lore", FileManager.getMessagesFile().get("Items.Random-Mystery-Vault-Animation.Lore"));
                                            }
                                            setConfigMessage("Settings.Hide-Particle-Effect-For-Vanished-Player", true, "Set to true will hide particle effect for vanished player.");
                                            setConfigMessage("Settings.Hide-Cloak-Effect-For-Vanished-Player", true, "Set to true will hide cloak effect for vanished player.");
                                            FileManager.getMessagesFile().set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.1-Star.Lore", null);
                                            FileManager.getMessagesFile().set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.2-Star.Lore", null);
                                            FileManager.getMessagesFile().set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.3-Star.Lore", null);
                                            FileManager.getMessagesFile().set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.4-Star.Lore", null);
                                            FileManager.getMessagesFile().set("GUI-Menus.Mystery-Box-Crafting-Menu.Items.5-Star.Lore", null);
                                            FileManager.getMessagesFile().set("Items.Random-Mystery-Vault-Animation", null);
                                            FileManager.getMessagesFile().set("Gadgets.Fun And Games.Types.Tic Tac Toe.Messages.Already-Had-A-Flower", null);
                                            FileManager.getConfigFile().set("Config-Version", "1.0.9", "Do not edit this.");
                                        }
                                        addConfigMessage("Settings.Gadget-Slot", 5, "The slot when player equip gadget, emote or morph.");
                                        FileManager.getConfigFile().set("Config-Version", "1.0.10", "Do not edit this.");
                                    }
                                    addConfigMessage("Cosmetic-Item-Purchase.Enabled-Cosmetics.Miniatures", true, new String[0]);
                                    addConfigMessage("Settings.Default-Mystery-Vault-Animation", "NORMAL", "The default value of Mystery Vault animation.", "The value of the player who join the server first time.");
                                    addConfigMessage("Disabled-Cosmetics.Miniatures", false, new String[0]);
                                    setConfigMessage("Auto-Update", false, "Set to true will automatic download", "the latest plugin when it's released.");
                                    FileManager.getConfigFile().set("Cosmetic-Item-Purchase.Execute-Command", (Object)null);
                                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Execute-Command", null);
                                    FileManager.getConfigFile().set("Config-Version", "1.0.11", "Do not edit this.");
                                }
                                addConfigMessage("Settings.Enabled-Mob-Disguise-Damage", false, "Do you want to enable mob disguise damage?", "Set to false will disable damage if disguised.");
                                FileManager.getConfigFile().set("Config-Version", "1.0.12", "Do not edit this.");
                            }
                            if (FileManager.getCloaksFile().get("Cloaks.Superhero.Material") != null && FileManager.getCloaksFile().getString("Cloaks.Superhero.Material").equals("ROSE_RED")) {
                                FileManager.getCloaksFile().set("Cloaks.Superhero.Material", EnumMaterial.RED_DYE.getName());
                            }
                            if (FileManager.getMessagesFile().get("None-Cosmetic-Has-Been-Selected") != null) {
                                FileManager.getMessagesFile().set("No-Cosmetic-Equipped", FileManager.getMessagesFile().get("None-Cosmetic-Has-Been-Selected"));
                            }
                            FileManager.getMessagesFile().set("None-Cosmetic-Has-Been-Selected", null);
                            FileManager.getConfigFile().set("Config-Version", "1.0.13", "Do not edit this.");
                        }
                        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Holograms.Available-Mystery-Boxes") != null) {
                            FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Holograms.Individual-Holograms.Available-Mystery-Boxes", FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Holograms.Available-Mystery-Boxes"));
                        }
                        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Holograms.Zero-Mystery-Box-Available") != null) {
                            FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Holograms.Individual-Holograms.Zero-Mystery-Box-Available", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Holograms.Zero-Mystery-Box-Available"));
                        }
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Holograms.Available-Mystery-Boxes", null);
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Holograms.Zero-Mystery-Box-Available", null);
                        FileManager.getConfigFile().set("Config-Version", "1.0.14", "Do not edit this.");
                    }
                    LoggerManager.printLogWithHeader(LoggerManager.LogLevel.INFO, "------------------------------------------------------", "Updating configs, it might takes a while.");
                    if (FileManager.getMessagesFile().get("None-Cosmetic-Has-Been-Selected") != null) {
                        FileManager.getMessagesFile().set("No-Cosmetic-Equipped", FileManager.getMessagesFile().get("None-Cosmetic-Has-Been-Selected"));
                    }
                    setConfigMessage("Settings.Default-Pet-Name", null, new String[0]);
                    addConfigMessage("Menu-Item.Click-Type", "LEFT_AND_RIGHT", new String[0]);
                    if (FileManager.getMessagesFile().get("GUI-Menus.Mystery-Vault-Menu.Items.Gift-Inventory.Texture") != null) {
                        FileManager.getMessagesFile().set("GUI-Menus.Mystery-Vault-Menu.Items.Gift-Inventory.Material", "head:" + FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Gift-Inventory.Texture").replace("http://textures.minecraft.net/texture/", ""));
                    }
                    FileManager.getMessagesFile().set("GUI-Menus.Mystery-Vault-Menu.Items.Gift-Inventory.Texture", null);
                    if (FileManager.getMessagesFile().get("Mystery-Boxes.Chance.Common") != null) {
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Chances.One-Star.Common", FileManager.getMessagesFile().get("Mystery-Boxes.Chance.Common"));
                    }
                    if (FileManager.getMessagesFile().get("Mystery-Boxes.Chance.Rare") != null) {
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Chances.One-Star.Rare", FileManager.getMessagesFile().get("Mystery-Boxes.Chance.Rare"));
                    }
                    if (FileManager.getMessagesFile().get("Mystery-Boxes.Chance.Epic") != null) {
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Chances.One-Star.Epic", FileManager.getMessagesFile().get("Mystery-Boxes.Chance.Epic"));
                    }
                    if (FileManager.getMessagesFile().get("Mystery-Boxes.Chance.Legendary") != null) {
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Chances.One-Star.Legendary", FileManager.getMessagesFile().get("Mystery-Boxes.Chance.Legendary"));
                    }
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Chance", null);
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.One-Star") != null) {
                        final ArrayList<String> list3 = new ArrayList<String>();
                        list3.add(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.One-Star.Name"));
                        list3.addAll((Collection<?>)FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.One-Star.Lore"));
                        FileManager.getMysteryBoxesFile().addDefault("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.One-Star.Message", list3);
                    }
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Two-Star") != null) {
                        final ArrayList<String> list4 = new ArrayList<String>();
                        list4.add(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Two-Star.Name"));
                        list4.addAll((Collection<?>)FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Two-Star.Lore"));
                        FileManager.getMysteryBoxesFile().addDefault("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Two-Star.Message", list4);
                    }
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Three-Star") != null) {
                        final ArrayList<String> list5 = new ArrayList<String>();
                        list5.add(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Three-Star.Name"));
                        list5.addAll((Collection<?>)FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Three-Star.Lore"));
                        FileManager.getMysteryBoxesFile().addDefault("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Three-Star.Message", list5);
                    }
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Four-Star") != null) {
                        final ArrayList<String> list6 = new ArrayList<String>();
                        list6.add(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Four-Star.Name"));
                        list6.addAll((Collection<?>)FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Four-Star.Lore"));
                        FileManager.getMysteryBoxesFile().addDefault("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Four-Star.Message", list6);
                    }
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Five-Star") != null) {
                        final ArrayList<String> list7 = new ArrayList<String>();
                        list7.add(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Five-Star.Name"));
                        list7.addAll((Collection<?>)FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Five-Star.Lore"));
                        FileManager.getMysteryBoxesFile().addDefault("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Five-Star.Message", list7);
                    }
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.One-Star.Name", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.One-Star.Lore", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Two-Star.Name", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Two-Star.Lore", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Three-Star.Name", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Three-Star.Lore", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Four-Star.Name", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Four-Star.Lore", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Five-Star.Name", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Mystery-Box.Five-Star.Lore", null);
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.JSON-Messages.Found-Loot.Common") != null) {
                        final ArrayList<String> list8 = new ArrayList<String>();
                        list8.add(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.JSON-Messages.Found-Loot.Common.Name"));
                        list8.addAll((Collection<?>)FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Loot.Common.Lore"));
                        FileManager.getMysteryBoxesFile().addDefault("Mystery-Boxes.JSON-Messages.Found-Loot.Common.Message", list8);
                    }
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.JSON-Messages.Found-Loot.Rare") != null) {
                        final ArrayList<String> list9 = new ArrayList<String>();
                        list9.add(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.JSON-Messages.Found-Loot.Rare.Name"));
                        list9.addAll((Collection<?>)FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Loot.Rare.Lore"));
                        FileManager.getMysteryBoxesFile().addDefault("Mystery-Boxes.JSON-Messages.Found-Loot.Rare.Message", list9);
                    }
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.JSON-Messages.Found-Loot.Epic") != null) {
                        final ArrayList<String> list10 = new ArrayList<String>();
                        list10.add(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.JSON-Messages.Found-Loot.Epic.Name"));
                        list10.addAll((Collection<?>)FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Loot.Epic.Lore"));
                        FileManager.getMysteryBoxesFile().addDefault("Mystery-Boxes.JSON-Messages.Found-Loot.Epic.Message", list10);
                    }
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.JSON-Messages.Found-Loot.Legendary") != null) {
                        final ArrayList<String> list11 = new ArrayList<String>();
                        list11.add(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.JSON-Messages.Found-Loot.Legendary.Name"));
                        list11.addAll((Collection<?>)FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.JSON-Messages.Found-Loot.Legendary.Lore"));
                        FileManager.getMysteryBoxesFile().addDefault("Mystery-Boxes.JSON-Messages.Found-Loot.Legendary.Message", list11);
                    }
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Loot.Common.Name", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Loot.Common.Lore", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Loot.Rare.Name", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Loot.Rare.Lore", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Loot.Epic.Name", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Loot.Epic.Lore", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Loot.Legendary.Name", null);
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.JSON-Messages.Found-Loot.Legendary.Lore", null);
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot.Common.Send-Message.Message") != null) {
                        final ArrayList<String> list12 = new ArrayList<String>();
                        for (final String s4 : FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Found-Loot.Common.Send-Message.Message")) {
                            list12.add(s4);
                            if (s4.contains("{LOOT}")) {
                                list12.add("  &7{PET_ITEMS}x &a&lPet Items");
                            }
                        }
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Found-Loot.Common.Send-Message.Message", list12);
                    }
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot.Rare.Send-Message.Message") != null) {
                        final ArrayList<String> list13 = new ArrayList<String>();
                        for (final String s5 : FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Found-Loot.Rare.Send-Message.Message")) {
                            list13.add(s5);
                            if (s5.contains("{LOOT}")) {
                                list13.add("  &7{PET_ITEMS}x &a&lPet Items");
                            }
                        }
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Found-Loot.Rare.Send-Message.Message", list13);
                    }
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot.Epic.Send-Message.Message") != null) {
                        final ArrayList<String> list14 = new ArrayList<String>();
                        for (final String s6 : FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Found-Loot.Epic.Send-Message.Message")) {
                            list14.add(s6);
                            if (s6.contains("{LOOT}")) {
                                list14.add("  &7{PET_ITEMS}x &a&lPet Items");
                            }
                        }
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Found-Loot.Epic.Send-Message.Message", list14);
                    }
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot.Legendary.Send-Message.Message") != null) {
                        final ArrayList<String> list15 = new ArrayList<String>();
                        for (final String s7 : FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Found-Loot.Legendary.Send-Message.Message")) {
                            list15.add(s7);
                            if (s7.contains("{LOOT}")) {
                                list15.add("  &7{PET_ITEMS}x &a&lPet Items");
                            }
                        }
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Found-Loot.Legendary.Send-Message.Message", list15);
                    }
                    if (FileManager.getMessagesFile().get("Items.Multiple-Boxes-Loot-Book") != null) {
                        final ArrayList<String> list16 = new ArrayList<String>();
                        for (final String s8 : FileManager.getMessagesFile().getStringList("Items.Multiple-Boxes-Loot-Book")) {
                            list16.add(s8);
                            if (s8.contains("{MYSTERY_DUST}")) {
                                list16.add("&a&l+{PET_ITEMS} Pet Items");
                            }
                        }
                        FileManager.getMessagesFile().set("Items.Multiple-Boxes-Loot-Book", list16);
                    }
                    FileManager.getMessagesFile().set("Rename-Pet", "&eSet your {TYPE} pet's name to &a{NAME}&e.");
                    FileManager.getMessagesFile().set("Rename-Pet-For-Player", "&e{PLAYER} {TYPE} pet's name has been set to &a{NAME}&e.");
                    addConfigMessage("Player-Data.MySQL.useSSL", false, new String[0]);
                    addConfigMessage("Settings.Inventory-Sorting", "DEFAULT", "Set how items are sorted in the menus.", "Sorting Types: DEFAULT, RARITY, NAME");
                    addConfigMessage("Settings.Show-Name-For-Mob-Disguise", true, "Display player name above the mob disguise.");
                    if (FileManager.getConfigFile().get("Cosmetic-Item-Purchase.Deprecated-Execute-Command") != null) {
                        setConfigMessage("Cosmetic-Item-Purchase.Execute-Command.Enabled", true, new String[0]);
                        setConfigMessage("Cosmetic-Item-Purchase.Execute-Command.Command", FileManager.getConfigFile().getString("Cosmetic-Item-Purchase.Deprecated-Execute-Command"), new String[0]);
                    }
                    setConfigMessage("Cosmetic-Item-Purchase.Deprecated-Execute-Command", null, new String[0]);
                    if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Deprecated-Execute-Command") != null) {
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Execute-Command.Enabled", true);
                        FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Execute-Command.Command", FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Deprecated-Execute-Command"));
                    }
                    FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Deprecated-Execute-Command", null);
                    final FileManager customLootsFile = FileManager.getCustomLootsFile();
                    if (customLootsFile.get("Custom-Loots") != null) {
                        for (final String s9 : customLootsFile.getConfigurationSection("Custom-Loots").getKeys(false)) {
                            if (customLootsFile.get("Custom-Loots." + s9 + ".Execute-Command.Command") != null) {
                                customLootsFile.set("Custom-Loots." + s9 + ".Execute-Command.Commands", Arrays.asList(customLootsFile.getString("Custom-Loots." + s9 + ".Execute-Command.Command")));
                            }
                            customLootsFile.set("Custom-Loots." + s9 + ".Execute-Command.Command", null);
                        }
                    }
                    if (FileManager.getConfigFile().get("Item-Cost-Discount.Discount-Rates") != null) {
                        for (final String s10 : FileManager.getConfigFile().getConfigurationSection("Item-Cost-Discount.Discount-Rates").getKeys(false)) {
                            final ArrayList<String> list17 = new ArrayList<String>();
                            final ArrayList<String> list18 = new ArrayList<String>();
                            for (final String s11 : FileManager.getConfigFile().getStringList("Item-Cost-Discount.Discount-Rates." + s10 + ".Lore.Enough-Mystery-Dust")) {
                                if (s11.contains("MYSTERY_DUST}")) {
                                    list17.add(s11.replace("MYSTERY_DUST}", "COST}"));
                                }
                                else {
                                    list17.add(s11);
                                }
                            }
                            setConfigMessage("Item-Cost-Discount.Discount-Rates." + s10 + ".Lore.Enough-Mystery-Dust", list17, new String[0]);
                            for (final String s12 : FileManager.getConfigFile().getStringList("Item-Cost-Discount.Discount-Rates." + s10 + ".Lore.Not-Enough-Mystery-Dust")) {
                                if (s12.contains("{SURPLUS_MYSTERY_DUST}")) {
                                    list18.add(s12.replace("{SURPLUS_MYSTERY_DUST}", "{COST_LEFT}"));
                                }
                                else if (s12.contains("MYSTERY_DUST}")) {
                                    list18.add(s12.replace("MYSTERY_DUST}", "COST}"));
                                }
                                else {
                                    list18.add(s12);
                                }
                            }
                            setConfigMessage("Item-Cost-Discount.Discount-Rates." + s10 + ".Lore.Not-Enough-Mystery-Dust", list18, new String[0]);
                        }
                        if (FileManager.getConfigFile().get("Item-Cost-Discount.Discount-Rates.Default") != null) {
                            final int int1 = FileManager.getConfigFile().getInt("Item-Cost-Discount.Discount-Rates.Default.Priority");
                            final String string6 = FileManager.getConfigFile().getString("Item-Cost-Discount.Discount-Rates.Default.Permission");
                            final int int2 = FileManager.getConfigFile().getInt("Item-Cost-Discount.Discount-Rates.Default.Rate");
                            final List stringList = FileManager.getConfigFile().getStringList("Item-Cost-Discount.Discount-Rates.Default.Lore.Enough-Mystery-Dust");
                            final List stringList2 = FileManager.getConfigFile().getStringList("Item-Cost-Discount.Discount-Rates.Default.Lore.Not-Enough-Mystery-Dust");
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.Default", "", "The name of the discount group.", "This name is use for placeholder to get the cost after discount.", "Placeholder Syntax: {<name>_COST}", "Example: The placeholder for 'Default' is {DEFAULT_COST}.", "{COST}: Get the original price.", "{COST_LEFT}: How many cost left you need.");
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.Default.Priority", int1, "Higher numbers override lower number groups.");
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.Default.Permission", string6, "The permission to grant cost discount.");
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.Default.Rate", int2, "Discount rates.", "Range: 0 - 100", "If you purchase an item with 100 cost and 20% off,", "your final price will be 80.");
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.Default.Lore.Enough-Mystery-Dust", stringList, new String[0]);
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.Default.Lore.Not-Enough-Mystery-Dust", stringList2, new String[0]);
                            if (FileManager.getConfigFile().get("Item-Cost-Discount.Discount-Rates.VIP") != null) {
                                final int int3 = FileManager.getConfigFile().getInt("Item-Cost-Discount.Discount-Rates.VIP.Priority");
                                final String string7 = FileManager.getConfigFile().getString("Item-Cost-Discount.Discount-Rates.VIP.Permission");
                                final int int4 = FileManager.getConfigFile().getInt("Item-Cost-Discount.Discount-Rates.VIP.Rate");
                                final List stringList3 = FileManager.getConfigFile().getStringList("Item-Cost-Discount.Discount-Rates.VIP.Lore.Enough-Mystery-Dust");
                                final List stringList4 = FileManager.getConfigFile().getStringList("Item-Cost-Discount.Discount-Rates.VIP.Lore.Not-Enough-Mystery-Dust");
                                setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP", "", new String[0]);
                                setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP.Priority", int3, "Higher numbers override.");
                                setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP.Permission", string7, "The permission to granted discount.");
                                setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP.Rate", int4, "Discount rates.");
                                setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP.Lore.Enough-Mystery-Dust", stringList3, new String[0]);
                                setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP.Lore.Not-Enough-Mystery-Dust", stringList4, new String[0]);
                            }
                        }
                        else if (FileManager.getConfigFile().get("Item-Cost-Discount.Discount-Rates.VIP") != null) {
                            final int int5 = FileManager.getConfigFile().getInt("Item-Cost-Discount.Discount-Rates.VIP.Priority");
                            final String string8 = FileManager.getConfigFile().getString("Item-Cost-Discount.Discount-Rates.VIP.Permission");
                            final int int6 = FileManager.getConfigFile().getInt("Item-Cost-Discount.Discount-Rates.VIP.Rate");
                            final List stringList5 = FileManager.getConfigFile().getStringList("Item-Cost-Discount.Discount-Rates.VIP.Lore.Enough-Mystery-Dust");
                            final List stringList6 = FileManager.getConfigFile().getStringList("Item-Cost-Discount.Discount-Rates.VIP.Lore.Not-Enough-Mystery-Dust");
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP", "", "The name of the discount group.", "This name is use for placeholder to get the cost after discount.", "Placeholder Syntax: {<name>_COST}", "Example: The placeholder for 'Default' is {DEFAULT_COST}.", "{COST}: Get the original price.", "{COST_LEFT}: How many cost left you need.");
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP.Priority", int5, "Higher numbers override lower number groups.");
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP.Permission", string8, "The permission to grant cost discount.");
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP.Rate", int6, "Discount rates.", "Range: 0 - 100", "If you purchase an item with 100 cost and 20% off,", "your final price will be 80.");
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP.Lore.Enough-Mystery-Dust", stringList5, new String[0]);
                            setConfigMessage("Item-Cost-Discount.Discount-Rates.VIP.Lore.Not-Enough-Mystery-Dust", stringList6, new String[0]);
                        }
                    }
                    if (FileManager.getMessagesFile().get("Items.Enough-Mystery-Dust.Lore") != null) {
                        final ArrayList<String> list19 = new ArrayList<String>();
                        for (final String s13 : FileManager.getMessagesFile().getStringList("Items.Enough-Mystery-Dust.Lore")) {
                            if (s13.contains("{MYSTERY_DUST}")) {
                                list19.add(s13.replace("{MYSTERY_DUST}", "{COST}"));
                            }
                            else {
                                list19.add(s13);
                            }
                        }
                        FileManager.getMessagesFile().set("Items.Enough-Mystery-Dust.Lore", list19);
                    }
                    if (FileManager.getMessagesFile().get("Items.Not-Enough-Mystery-Dust.Lore") != null) {
                        final ArrayList<String> list20 = new ArrayList<String>();
                        for (final String s14 : FileManager.getMessagesFile().getStringList("Items.Not-Enough-Mystery-Dust.Lore")) {
                            if (s14.contains("{MYSTERY_DUST}")) {
                                list20.add(s14.replace("{MYSTERY_DUST}", "{COST}"));
                            }
                            else if (s14.contains("{SURPLUS_MYSTERY_DUST}")) {
                                list20.add(s14.replace("{SURPLUS_MYSTERY_DUST}", "{COST_LEFT}"));
                            }
                            else {
                                list20.add(s14);
                            }
                        }
                        FileManager.getMessagesFile().set("Items.Not-Enough-Mystery-Dust.Lore", list20);
                    }
                    updateLore(FileManager.getEmotesFile(), "Emotes");
                    updateLore(FileManager.getCloaksFile(), "Cloaks");
                    updateLore(FileManager.getMiniaturesFile(), "Miniatures");
                    updateLore(FileManager.getMorphsFile(), "Morphs");
                    updateLore(FileManager.getParticlesFile(), "Particles");
                    updateLore(FileManager.getCustomAnimatedHatsFile(), "Custom-Animated-Hats");
                    updateLore(FileManager.getCustomBannersFile(), "Custom-Banners");
                    updateLore(FileManager.getCustomEmotesFile(), "Custom-Emotes");
                    updateLore(FileManager.getCustomHatsFile(), "Custom-Hats");
                    updateLore(FileManager.getCustomParticlesFile(), "Custom-Particles");
                    updateGadgetsLore(FileManager.getGadgetsFile(), "Gadgets");
                    updatePetsLore(FileManager.getPetsFile(), "Pets");
                    if (FileManager.getConfigFile().getString("Player-Data.Storage").equalsIgnoreCase("mysql")) {
                        FileManager.getConfigFile().set("Update-MySQL", true, "Do not edit this.");
                    }
                    FileManager.getConfigFile().set("Config-Version", "2.0.0", "Do not edit this.");
                }
                if (FileManager.getPetSystemFile().get("Pet-System.Level") != null) {
                    FileManager.getPetSystemFile().set("Pet-System.Level", String.valueOf(FileManager.getPetSystemFile().getString("Pet-System.Level")) + " ");
                }
                FileManager.getConfigFile().set("Config-Version", "2.0.1", "Do not edit this.");
            }
            FileManager.getMessagesFile().set("Does-Not-Support-Special-Characters", null);
            FileManager.getConfigFile().set("Config-Version", "2.0.2", "Do not edit this.");
        }
        boolean b4 = false;
        if (FileManager.getConfigFile().get("Cosmetic-Item-Purchase.Mystery-Dust-Storage") != null) {
            if (FileManager.getConfigFile().getString("Cosmetic-Item-Purchase.Mystery-Dust-Storage").equalsIgnoreCase("file") || FileManager.getConfigFile().getString("Cosmetic-Item-Purchase.Mystery-Dust-Storage").equalsIgnoreCase("mysql")) {
                setConfigMessage("Cosmetic-Item-Purchase.Mystery-Dust-Storage", "default", "Set the storage where do you want to save mystery dust.", "Available storages: 'default', 'coinsapi', 'playerpoints', 'vault'.", "'default' represent follow player data storage.");
                b4 = true;
            }
            else {
                setConfigMessage("Cosmetic-Item-Purchase.Mystery-Dust-Storage", FileManager.getConfigFile().getString("Cosmetic-Item-Purchase.Mystery-Dust-Storage"), "Set the storage where do you want to save mystery dust.", "Available storages: 'default', 'coinsapi', 'playerpoints', 'vault'.", "'default' represent follow player data storage.");
            }
        }
        if (FileManager.getConfigFile().get("Player-Data.Storage") != null && FileManager.getConfigFile().getString("Player-Data.Storage").equalsIgnoreCase("file")) {
            FileManager.getConfigFile().set("Player-Data.Storage", (Object)"sqlite");
            new BukkitRunnable() {
                public void run() {
                    if (CookieGadgets.getDatabaseManager() != null && CookieGadgets.getCookieGadgetsData().getDatabaseStorage() == DatabaseStorage.SQLITE) {
                        OldConfigurationManager.migrateUserData(b4);
                        this.cancel();
                    }
                }
            }.runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), 20L, 20L);
        }
        FileManager.getMessagesFile().set("Scanning-Player-Data-Files", null);
        FileManager.getMessagesFile().set("Deleted-Player-Data-Files", null);
        FileManager.getMessagesFile().set("Folder-Is-Empty", null);
        FileManager.getConfigFile().set("Config-Version", "2.0.3", "Do not edit this.");
    }
    
    private static void addConfigMessage(final String s, final Object o, final String... array) {
        FileManager.getConfigFile().addDefault(s, o, array);
    }
    
    private static void setConfigMessage(final String s, final Object o, final String... array) {
        FileManager.getConfigFile().set(s, o, array);
    }
    
    private static void updateLore(final FileManager fileManager, final String s) {
        if (fileManager.get(s) != null) {
            for (final String s2 : fileManager.getConfigurationSection(s).getKeys(false)) {
                if (fileManager.get(String.valueOf(s) + "." + s2 + ".Lore") != null) {
                    final List<String> stringList = fileManager.getStringList(String.valueOf(s) + "." + s2 + ".Lore");
                    if (stringList.equals("") || stringList.isEmpty()) {
                        fileManager.set(String.valueOf(s) + "." + s2 + ".Lore.Locked", "");
                        fileManager.set(String.valueOf(s) + "." + s2 + ".Lore.Locked", "");
                    }
                    else {
                        fileManager.set(String.valueOf(s) + "." + s2 + ".Lore.Locked", stringList);
                        fileManager.set(String.valueOf(s) + "." + s2 + ".Lore.Unlocked", stringList);
                    }
                }
            }
        }
    }
    
    private static void updateGadgetsLore(final FileManager fileManager, final String obj) {
        if (fileManager.get(obj) != null) {
            for (final String str : fileManager.getConfigurationSection(obj).getKeys(false)) {
                if (fileManager.get(String.valueOf(obj) + "." + str + ".Types") != null) {
                    final Iterator iterator2 = fileManager.getConfigurationSection(String.valueOf(obj) + "." + str + ".Types").getKeys(false).iterator();
                    while (iterator2.hasNext()) {
                        final String string = String.valueOf(obj) + "." + str + ".Types." + iterator2.next() + ".Lore";
                        if (fileManager.get(string) != null) {
                            final List<String> stringList = fileManager.getStringList(string);
                            if (stringList.equals("") || stringList.isEmpty()) {
                                fileManager.set(String.valueOf(string) + ".Locked", "");
                                fileManager.set(String.valueOf(string) + ".Unlocked", "");
                            }
                            else {
                                fileManager.set(String.valueOf(string) + ".Locked", stringList);
                                fileManager.set(String.valueOf(string) + ".Unlocked", stringList);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static void updatePetsLore(final FileManager fileManager, final String obj) {
        if (fileManager.get(obj) != null) {
            for (final String str : fileManager.getConfigurationSection(obj).getKeys(false)) {
                if (fileManager.get(String.valueOf(obj) + "." + str + ".Types") != null) {
                    final Iterator iterator2 = fileManager.getConfigurationSection(String.valueOf(obj) + "." + str + ".Types").getKeys(false).iterator();
                    while (iterator2.hasNext()) {
                        final String string = String.valueOf(obj) + "." + str + ".Types." + iterator2.next() + ".Lore";
                        if (fileManager.get(string) != null) {
                            final List<String> stringList = fileManager.getStringList(string);
                            if (stringList.equals("") || stringList.isEmpty()) {
                                fileManager.set(String.valueOf(string) + ".Locked", "");
                            }
                            else {
                                fileManager.set(String.valueOf(string) + ".Locked", stringList);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static void updateMaterial(final FileManager fileManager, final String s) {
        final String string = fileManager.getString(s);
        if (string == null) {
            return;
        }
        int intValue = 0;
        boolean b = false;
        String substring = "";
        if (string.contains(":")) {
            if (string.startsWith("head")) {
                final int n = 373;
                intValue = 3;
                b = true;
                substring = string.substring(5);
            }
            else {
                final int n = Integer.valueOf(string.split("\\:")[0]);
                intValue = Integer.valueOf(string.split("\\:")[1]);
            }
        }
        else {
            try {
                final int n = Integer.valueOf(string);
            }
            catch (NumberFormatException ex) {
                return;
            }
        }
        int n;
        fileManager.set(s, b ? ("head:" + substring) : (String.valueOf(EnumMaterial.valueOf(n, intValue).getName()) + ((intValue > 15) ? (":" + intValue) : "")));
    }
    
    private static void updateMainMenuFileMaterial(final String s) {
        updateMaterial(FileManager.getMainMenuFile(), s);
    }
    
    private static void updateMessagesFileMaterial(final String s) {
        updateMaterial(FileManager.getMessagesFile(), s);
    }
    
    private static void updateMysteryBoxesFileMaterial(final String s) {
        updateMaterial(FileManager.getMysteryBoxesFile(), s);
    }
    
    private static void updateAnimationsFileMaterial(final String s) {
        updateMaterial(FileManager.getAnimationsFile(), s);
    }
    
    private static void updateGadgetFileMaterial(final String s) {
        updateMaterial(FileManager.getGadgetsFile(), s);
    }
}
