

package ws.billy.CookieGadgets.utils;

import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.configuration.FileManager;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public enum EnumItem
{
    ALREADY_SELECTED(FileManager.getMessagesFile().getBoolean("Items.Already-Selected.Show-In-Lore"), FileManager.getMessagesFile().getStringList("Items.Already-Selected.Lore")), 
    CANCEL_PURCHASE_ITEM(FileManager.getMessagesFile().getString("GUI-Menus.Purchase-Menu.Items.Cancel.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Purchase-Menu.Items.Cancel.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Purchase-Menu.Items.Cancel.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Purchase-Menu.Items.Cancel.Slot")), 
    CANCEL_OPEN_MULTIPLE_BOXES(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Open-Multiple-Boxes-Menu.Items.Cancel.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Open-Multiple-Boxes-Menu.Items.Cancel.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Confirm-Open-Multiple-Boxes-Menu.Items.Cancel.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Confirm-Open-Multiple-Boxes-Menu.Items.Cancel.Slot")), 
    CANCEL_OPEN_MYSTERY_BOX(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Cancel.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Cancel.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Cancel.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Cancel.Slot")), 
    CANCEL_SEND_GIFT(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Send-Gift-Menu.Items.Cancel.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Send-Gift-Menu.Items.Cancel.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Confirm-Send-Gift-Menu.Items.Cancel.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Confirm-Send-Gift-Menu.Items.Cancel.Slot")), 
    CLICK_TO_SELECT(FileManager.getMessagesFile().getBoolean("Items.Click-To-Select.Show-In-Lore"), FileManager.getMessagesFile().getStringList("Items.Click-To-Select.Lore")), 
    CONFIRM_PURCHASE_ITEM(FileManager.getMessagesFile().getString("GUI-Menus.Purchase-Menu.Items.Confirm.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Purchase-Menu.Items.Confirm.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Purchase-Menu.Items.Confirm.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Purchase-Menu.Items.Confirm.Slot")), 
    CONFIRM_OPEN_MULTIPLE_BOXES(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Open-Multiple-Boxes-Menu.Items.Confirm.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Open-Multiple-Boxes-Menu.Items.Confirm.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Confirm-Open-Multiple-Boxes-Menu.Items.Confirm.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Confirm-Open-Multiple-Boxes-Menu.Items.Confirm.Slot")), 
    CONFIRM_OPEN_MYSTERY_BOX(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Open.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Open.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Open.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Confirm-Open-Mystery-Box-Menu.Items.Open.Slot")), 
    CONFIRM_SEND_GIFT(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Send-Gift-Menu.Items.Confirm.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Send-Gift-Menu.Items.Confirm.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Confirm-Send-Gift-Menu.Items.Confirm.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Confirm-Send-Gift-Menu.Items.Confirm.Slot")), 
    CRAFT_MYSTERY_BOXES(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Craft-Mystery-Boxes.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Craft-Mystery-Boxes.Material")), FileManager.getMessagesFile().getBoolean("GUI-Menus.Mystery-Vault-Menu.Items.Craft-Mystery-Boxes.Show"), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Vault-Menu.Items.Craft-Mystery-Boxes.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Vault-Menu.Items.Craft-Mystery-Boxes.Slot")), 
    CURRENT_SPAWNED_PET("CURRENT_SPAWNED_PET", 11, FileManager.getMessagesFile().getString("Items.Current-Spawned-Pet.Name"), FileManager.getMessagesFile().getBoolean("Items.Current-Spawned-Pet.Show"), FileManager.getMessagesFile().getInt("Items.Current-Spawned-Pet.Slot")), 
    ENOUGH_MYSTERY_DUST(FileManager.getMessagesFile().getStringList("Items.Enough-Mystery-Dust.Lore"), FileManager.getMessagesFile().getBoolean("Items.Enough-Mystery-Dust.Play-Sound.Enabled"), SoundEffect.valueOf(FileManager.getMessagesFile().getString("Items.Enough-Mystery-Dust.Play-Sound.Sound"))), 
    ENOUGH_MYSTERY_DUST_TO_CRAFT_MYSTERY_BOX(FileManager.getMessagesFile().getStringList("Items.Enough-Mystery-Dust-To-Craft-Mystery-Box.Lore"), FileManager.getMessagesFile().getBoolean("Items.Enough-Mystery-Dust-To-Craft-Mystery-Box.Play-Sound.Enabled"), SoundEffect.valueOf(FileManager.getMessagesFile().getString("Items.Enough-Mystery-Dust-To-Craft-Mystery-Box.Play-Sound.Sound"))), 
    EQUIP_ENTIRE_SUIT(FileManager.getMessagesFile().getString("Items.Equip-Entire-Suit.Name"), new GMaterial(FileManager.getMessagesFile().getString("Items.Equip-Entire-Suit.Material")), FileManager.getMessagesFile().getBoolean("Items.Equip-Entire-Suit.Show"), FileManager.getMessagesFile().getStringList("Items.Equip-Entire-Suit.Lore"), FileManager.getMessagesFile().getInt("Items.Equip-Entire-Suit.Slot")), 
    FAILED_TO_DEDUCT_MYSTERY_DUST("FAILED_TO_DEDUCT_MYSTERY_DUST", 15, FileManager.getMessagesFile().getBoolean("Items.Failed-To-Deduct-Mystery-Dust.Play-Sound.Enabled"), SoundEffect.valueOf(FileManager.getMessagesFile().getString("Items.Failed-To-Deduct-Mystery-Dust.Play-Sound.Sound"))), 
    FAILED_TO_PURCHASE("FAILED_TO_PURCHASE", 16, FileManager.getMessagesFile().getBoolean("Items.Failed-To-Purchase.Play-Sound.Enabled"), SoundEffect.valueOf(FileManager.getMessagesFile().getString("Items.Failed-To-Purchase.Play-Sound.Sound"))), 
    GIFT_INVENTORY(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Gift-Inventory.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Gift-Inventory.Material")), FileManager.getMessagesFile().getBoolean("GUI-Menus.Mystery-Vault-Menu.Items.Gift-Inventory.Show"), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Vault-Menu.Items.Gift-Inventory.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Vault-Menu.Items.Gift-Inventory.Slot")), 
    GO_BACK(FileManager.getMessagesFile().getString("Items.Go-Back.Name"), new GMaterial(FileManager.getMessagesFile().getString("Items.Go-Back.Material")), FileManager.getMessagesFile().getBoolean("Items.Go-Back.Show"), FileManager.getMessagesFile().getStringList("Items.Go-Back.Lore"), FileManager.getMessagesFile().getInt("Items.Go-Back.Slot")), 
    HAS_PERMISSION(FileManager.getConfigFile().getBoolean("Permission.Has-Permission.Show-In-Lore"), (List<String>)FileManager.getConfigFile().getStringList("Permission.Has-Permission.Lore"), FileManager.getConfigFile().getBoolean("Permission.Has-Permission.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getConfigFile().getString("Permission.Has-Permission.Play-Sound.Sound")), FileManager.getConfigFile().getBoolean("Permission.Has-Permission.Close-GUI-Menu-After-Select")), 
    ITEM_UNPURCHASABLE(FileManager.getMessagesFile().getStringList("Items.Item-Unpurchasable.Lore"), FileManager.getMessagesFile().getBoolean("Items.Item-Unpurchasable.Play-Sound.Enabled"), SoundEffect.valueOf(FileManager.getMessagesFile().getString("Items.Item-Unpurchasable.Play-Sound.Sound"))), 
    JUKEBOX_STOP_TRACK(FileManager.getGadgetsFile().getString("Gadgets.Musical.Types.Jukebox.Items.Stop-Track.Name"), new GMaterial(FileManager.getGadgetsFile().getString("Gadgets.Musical.Types.Jukebox.Items.Stop-Track.Material")), FileManager.getGadgetsFile().getStringList("Gadgets.Musical.Types.Jukebox.Items.Stop-Track.Lore"), 31), 
    JUKEBOX_TRACK(FileManager.getGadgetsFile().getString("Gadgets.Musical.Types.Jukebox.Items.Track.Name"), FileManager.getGadgetsFile().getStringList("Gadgets.Musical.Types.Jukebox.Items.Track.Lore")), 
    LOADING_MYSTERY_BOXES(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Loading-Mystery-Boxes.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Loading-Mystery-Boxes.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Vault-Menu.Items.Loading-Mystery-Boxes.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Vault-Menu.Items.Loading-Mystery-Boxes.Slot")), 
    MAIN_MENU_ITEM(FileManager.getMessagesFile().getString("Items.MainMenu-Item.Name"), new GMaterial(FileManager.getMessagesFile().getString("Items.MainMenu-Item.Material")), FileManager.getMessagesFile().getBoolean("Items.MainMenu-Item.Show"), FileManager.getMessagesFile().getStringList("Items.MainMenu-Item.Lore"), FileManager.getMessagesFile().getInt("Items.MainMenu-Item.Slot")), 
    MORPH_SLIMEBALL("MORPH_SLIMEBALL", 25, FileManager.getMessagesFile().getString("Items.Morph-Slimeball.Name"), new GMaterial(EnumMaterial.SLIME_BALL)), 
    MULTIPLE_BOXES_LOOT_BOOK(FileManager.getMessagesFile().getStringList("Items.Multiple-Boxes-Loot-Book")), 
    MYSTERY_BOX_INFORMATION(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Mystery-Box-Information.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Mystery-Box-Information.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Vault-Menu.Items.Mystery-Box-Information.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Vault-Menu.Items.Mystery-Box-Information.Slot")), 
    MYSTERY_GIFT(FileManager.getMessagesFile().getString("GUI-Menus.Gift-Inventory-Menu.Items.Mystery-Gift.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Gift-Inventory-Menu.Items.Mystery-Gift.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Gift-Inventory-Menu.Items.Mystery-Gift.Lore")), 
    MYSTERY_GIFT_CONFIRM_SEND_GIFT(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Send-Gift-Menu.Items.Mystery-Gift.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Send-Gift-Menu.Items.Mystery-Gift.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Confirm-Send-Gift-Menu.Items.Mystery-Gift.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Confirm-Send-Gift-Menu.Items.Mystery-Gift.Slot")), 
    MYSTERY_GIFT_SEND_GIFT(FileManager.getMessagesFile().getString("GUI-Menus.Send-Gift-Menu.Items.Mystery-Gift.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Send-Gift-Menu.Items.Mystery-Gift.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Send-Gift-Menu.Items.Mystery-Gift.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Send-Gift-Menu.Items.Mystery-Gift.Slot")), 
    MYSTERY_VAULT_ANIMATIONS(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Animations.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Animations.Material")), FileManager.getMessagesFile().getBoolean("GUI-Menus.Mystery-Vault-Menu.Items.Animations.Show"), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Vault-Menu.Items.Animations.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Vault-Menu.Items.Animations.Slot")), 
    NEXT_PAGE(FileManager.getMessagesFile().getString("Items.Next-Page.Name"), new GMaterial(FileManager.getMessagesFile().getString("Items.Next-Page.Material")), FileManager.getMessagesFile().getStringList("Items.Next-Page.Lore"), FileManager.getMessagesFile().getInt("Items.Next-Page.Slot")), 
    NOT_ENOUGH_MYSTERY_DUST(FileManager.getMessagesFile().getStringList("Items.Not-Enough-Mystery-Dust.Lore"), FileManager.getMessagesFile().getBoolean("Items.Not-Enough-Mystery-Dust.Play-Sound.Enabled"), SoundEffect.valueOf(FileManager.getMessagesFile().getString("Items.Not-Enough-Mystery-Dust.Play-Sound.Sound"))), 
    NOT_ENOUGH_MYSTERY_DUST_TO_CRAFT_MYSTERY_BOX(FileManager.getMessagesFile().getStringList("Items.Not-Enough-Mystery-Dust-To-Craft-Mystery-Box.Lore"), FileManager.getMessagesFile().getBoolean("Items.Not-Enough-Mystery-Dust-To-Craft-Mystery-Box.Play-Sound.Enabled"), SoundEffect.valueOf(FileManager.getMessagesFile().getString("Items.Not-Enough-Mystery-Dust-To-Craft-Mystery-Box.Play-Sound.Sound"))), 
    NO_MYSTERY_BOX_ERROR(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Error.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Error.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Vault-Menu.Items.Error.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Vault-Menu.Items.Error.Slot")), 
    NO_MYSTERY_GIFT_ERROR(FileManager.getMessagesFile().getString("GUI-Menus.Gift-Inventory-Menu.Items.Error.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Gift-Inventory-Menu.Items.Error.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Gift-Inventory-Menu.Items.Error.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Gift-Inventory-Menu.Items.Error.Slot")), 
    NO_PERMISSION(FileManager.getConfigFile().getBoolean("Permission.No-Permission.Show-In-Lore"), (List<String>)FileManager.getConfigFile().getStringList("Permission.No-Permission.Lore"), FileManager.getConfigFile().getBoolean("Permission.No-Permission.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getConfigFile().getString("Permission.No-Permission.Play-Sound.Sound")), FileManager.getConfigFile().getBoolean("Permission.No-Permission.Show-Custom-Item.Enabled"), new GMaterial(FileManager.getConfigFile().getString("Permission.No-Permission.Show-Custom-Item.Material")), FileManager.getConfigFile().getBoolean("Permission.No-Permission.Close-GUI-Menu-After-Select")), 
    OPEN_MULTIPLE_BOXES_ITEM(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Open-Multiple-Boxes.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.Items.Open-Multiple-Boxes.Material")), FileManager.getMessagesFile().getBoolean("GUI-Menus.Mystery-Vault-Menu.Items.Open-Multiple-Boxes.Show"), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Vault-Menu.Items.Open-Multiple-Boxes.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Vault-Menu.Items.Open-Multiple-Boxes.Slot")), 
    OPEN_20_BOXES(FileManager.getMessagesFile().getString("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-20-Boxes.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-20-Boxes.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-20-Boxes.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-20-Boxes.Slot")), 
    OPEN_50_BOXES(FileManager.getMessagesFile().getString("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-50-Boxes.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-50-Boxes.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-50-Boxes.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-50-Boxes.Slot")), 
    OPEN_250_BOXES(FileManager.getMessagesFile().getString("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-250-Boxes.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-250-Boxes.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-250-Boxes.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Open-Multiple-Boxes-Menu.Items.Open-250-Boxes.Slot")), 
    PAGES("PAGES", 42, FileManager.getMessagesFile().getString("Items.Pages.Name")), 
    PLAYER_CONFIRM_SEND_GIFT(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Send-Gift-Menu.Items.Player.Name"), new GMaterial(EnumMaterial.PLAYER_HEAD), FileManager.getMessagesFile().getStringList("GUI-Menus.Confirm-Send-Gift-Menu.Items.Player.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Confirm-Send-Gift-Menu.Items.Player.Slot")), 
    PLAYER_SEND_GIFT("&7{PLAYER}", FileManager.getMessagesFile().getStringList("GUI-Menus.Send-Gift-Menu.Items.Player.Lore")), 
    PREVIOUS_PAGE(FileManager.getMessagesFile().getString("Items.Previous-Page.Name"), new GMaterial(FileManager.getMessagesFile().getString("Items.Previous-Page.Material")), FileManager.getMessagesFile().getStringList("Items.Previous-Page.Lore"), FileManager.getMessagesFile().getInt("Items.Previous-Page.Slot")), 
    RADIO_STOP_TRACK(FileManager.getGadgetsFile().getString("Gadgets.Musical.Types.Radio.Items.Stop-Track.Name"), new GMaterial(FileManager.getGadgetsFile().getString("Gadgets.Musical.Types.Radio.Items.Stop-Track.Material")), FileManager.getGadgetsFile().getStringList("Gadgets.Musical.Types.Radio.Items.Stop Track.Lore"), 49), 
    RADIO_TRACK(FileManager.getGadgetsFile().getString("Gadgets.Musical.Types.Radio.Items.Track.Name"), new GMaterial(FileManager.getGadgetsFile().getString("Gadgets.Musical.Types.Radio.Items.Track.Material")), FileManager.getGadgetsFile().getStringList("Gadgets.Musical.Types.Radio.Items.Track.Lore")), 
    RENAME_PET(FileManager.getMessagesFile().getString("Items.Rename-Pet.Name"), new GMaterial(FileManager.getMessagesFile().getString("Items.Rename-Pet.Material")), FileManager.getMessagesFile().getBoolean("Items.Rename-Pet.Show"), FileManager.getMessagesFile().getStringList("Items.Rename-Pet.Lore"), FileManager.getMessagesFile().getInt("Items.Rename-Pet.Slot")), 
    RANDOM_MYSTERY_VAULT_ANIMATION_ITEM(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Animations-Menu.Items.Random-Mystery-Vault-Animation.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Animations-Menu.Items.Random-Mystery-Vault-Animation.Material")), FileManager.getMessagesFile().getBoolean("GUI-Menus.Mystery-Vault-Animations-Menu.Items.Random-Mystery-Vault-Animation.Show"), FileManager.getMessagesFile().getStringList("GUI-Menus.Mystery-Vault-Animations-Menu.Items.Random-Mystery-Vault-Animation.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Mystery-Vault-Animations-Menu.Items.Random-Mystery-Vault-Animation.Slot")), 
    RESET_ANIMATED_HAT(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Animated-Hat.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Animated-Hat.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Animated-Hat.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Animated-Hat.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Animated-Hat.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Animated-Hat.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Animated-Hat.Slot")), 
    RESET_BANNER(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Banner.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Banner.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Banner.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Banner.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Banner.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Banner.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Banner.Slot")), 
    RESET_COSMETICS(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Cosmetics.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Cosmetics.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Cosmetics.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Cosmetics.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Cosmetics.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Cosmetics.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Cosmetics.Slot")), 
    RESET_CLOAK(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Cloak.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Cloak.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Cloak.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Cloak.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Cloak.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Cloak.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Cloak.Slot")), 
    RESET_EMOTE(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Emote.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Emote.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Emote.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Emote.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Emote.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Emote.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Emote.Slot")), 
    RESET_GADGET(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Gadget.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Gadget.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Gadget.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Gadget.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Gadget.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Gadget.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Gadget.Slot")), 
    RESET_HAT(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Hat.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Hat.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Hat.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Hat.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Hat.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Hat.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Hat.Slot")), 
    RESET_MINIATURE(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Miniature.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Miniature.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Miniature.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Miniature.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Miniature.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Miniature.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Miniature.Slot")), 
    RESET_MORPH(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Morph.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Morph.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Morph.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Morph.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Morph.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Morph.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Morph.Slot")), 
    RESET_PARTICLE(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Particle.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Particle.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Particle.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Particle.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Particle.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Particle.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Particle.Slot")), 
    RESET_PET(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Pet.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Pet.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Pet.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Pet.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Pet.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Pet.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Pet.Slot")), 
    RESET_SUIT(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Suit.Name"), new GMaterial(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Suit.Material")), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Suit.Show"), FileManager.getMessagesFile().getStringList("Reset-Buttons.Reset-Suit.Lore"), FileManager.getMessagesFile().getBoolean("Reset-Buttons.Reset-Suit.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("Reset-Buttons.Reset-Suit.Play-Sound.Sound")), FileManager.getMessagesFile().getInt("Reset-Buttons.Reset-Suit.Slot")), 
    SELF_MORPH_VIEW(FileManager.getMessagesFile().getString("Items.Self-Morph-View.Name"), new GMaterial(FileManager.getMessagesFile().getString("Items.Self-Morph-View.Material")), FileManager.getMessagesFile().getBoolean("Items.Self-Morph-View.Show"), FileManager.getMessagesFile().getStringList("Items.Self-Morph-View.Lore"), FileManager.getMessagesFile().getInt("Items.Self-Morph-View.Slot")), 
    SEND_PET_ON_MISSION(FileManager.getMessagesFile().getString("Items.Send-Pet-On-Mission.Name"), new GMaterial(FileManager.getMessagesFile().getString("Items.Send-Pet-On-Mission.Material")), FileManager.getMessagesFile().getBoolean("Items.Send-Pet-On-Mission.Show"), FileManager.getMessagesFile().getStringList("Items.Send-Pet-On-Mission.Lore"), FileManager.getMessagesFile().getInt("Items.Send-Pet-On-Mission.Slot")), 
    SETTINGS(FileManager.getMessagesFile().getString("Items.Settings.Name"), new GMaterial(FileManager.getMessagesFile().getString("Items.Settings.Material")), FileManager.getMessagesFile().getBoolean("Items.Settings.Show"), FileManager.getMessagesFile().getStringList("Items.Settings.Lore"), FileManager.getMessagesFile().getInt("Items.Settings.Slot")), 
    SETTINGS_DISABLED(FileManager.getMessagesFile().getString("GUI-Menus.Settings-Menu.Items.Disabled.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Settings-Menu.Items.Disabled.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Settings-Menu.Items.Disabled.Lore")), 
    SETTINGS_ENABLED(FileManager.getMessagesFile().getString("GUI-Menus.Settings-Menu.Items.Enabled.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Settings-Menu.Items.Enabled.Material")), FileManager.getMessagesFile().getStringList("GUI-Menus.Settings-Menu.Items.Enabled.Lore")), 
    SETTINGS_IGNORE_COOLDOWN(FileManager.getMessagesFile().getString("GUI-Menus.Settings-Menu.Items.Ignore-Cooldown.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Settings-Menu.Items.Ignore-Cooldown.Material")), FileManager.getMessagesFile().getBoolean("GUI-Menus.Settings-Menu.Items.Ignore-Cooldown.Show"), FileManager.getMessagesFile().getStringList("GUI-Menus.Settings-Menu.Items.Ignore-Cooldown.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Settings-Menu.Items.Ignore-Cooldown.Slot")), 
    SETTINGS_SELF_MORPH_VIEW(FileManager.getMessagesFile().getString("GUI-Menus.Settings-Menu.Items.Self-Morph-View.Name"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Settings-Menu.Items.Self-Morph-View.Material")), FileManager.getMessagesFile().getBoolean("GUI-Menus.Settings-Menu.Items.Self-Morph-View.Show"), FileManager.getMessagesFile().getStringList("GUI-Menus.Settings-Menu.Items.Self-Morph-View.Lore"), FileManager.getMessagesFile().getInt("GUI-Menus.Settings-Menu.Items.Self-Morph-View.Slot")), 
    SETTINGS_STATUS(FileManager.getMessagesFile().getStringList("Items.Settings-Status")), 
    TIC_TAC_TOE_CHALLENGER(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Challenger.Name"), new GMaterial(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Challenger.Material")), FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Challenger.Lore"), 10), 
    TIC_TAC_TOE_CHALLENGER_SYMBOL(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Challenger-Symbol.Name"), new GMaterial(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Challenger-Symbol.Material")), FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Challenger-Symbol.Lore")), 
    TIC_TAC_TOE_NOT_YOUR_TURN(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Not-Your-Turn.Name"), new GMaterial(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Not-Your-Turn.Material")), FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Not-Your-Turn.Lore")), 
    TIC_TAC_TOE_OPPONENT(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Opponent.Name"), new GMaterial(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Opponent.Material")), FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Opponent.Lore"), 16), 
    TIC_TAC_TOE_OPPONENT_SYMBOL(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Opponent-Symbol.Name"), new GMaterial(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Opponent-Symbol.Material")), FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Opponent-Symbol.Lore")), 
    TIC_TAC_TOE_PLACE_SYMBOL(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Place-Symbol.Name"), new GMaterial(FileManager.getGadgetsFile().getString("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Place-Symbol.Material")), FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Tic Tac Toe.Items.Place-Symbol.Lore")), 
    UNLOCKED(FileManager.getMessagesFile().getBoolean("Items.Unlocked.Show-In-Lore"), FileManager.getMessagesFile().getStringList("Items.Unlocked.Lore")), 
    YOUR_PET("YOUR_PET", 77, FileManager.getMessagesFile().getString("GUI-Menus.Pet-Items-Menu.Items.Your-Pet.Name"), FileManager.getMessagesFile().getBoolean("GUI-Menus.Pet-Items-Menu.Items.Your-Pet.Show"), FileManager.getMessagesFile().getInt("GUI-Menus.Pet-Items-Menu.Items.Your-Pet.Slot")), 
    ZERO_PET_ITEM_REMAIN("ZERO_PET_ITEM_REMAIN", 78, FileManager.getMessagesFile().getBoolean("GUI-Menus.Pet-Items-Menu.Items.Zero-Item-Remain.Show-Custom-Item"), new GMaterial(FileManager.getMessagesFile().getString("GUI-Menus.Pet-Items-Menu.Items.Zero-Item-Remain.Material")), FileManager.getMessagesFile().getBoolean("GUI-Menus.Pet-Items-Menu.Items.Zero-Item-Remain.Play-Sound.Enabled"), SoundEffect.valueOfSound(FileManager.getMessagesFile().getString("GUI-Menus.Pet-Items-Menu.Items.Zero-Item-Remain.Play-Sound.Sound")));
    
    private String displayName;
    private GMaterial material;
    private boolean show;
    private List<String> lore;
    private boolean showInLore;
    private ItemStack itemStack;
    private boolean isPlaySoundEnabled;
    private SoundEffect sound;
    private int slot;
    private boolean showCustomItem;
    private GMaterial customItem;
    private boolean isCloseGUIMenuAfterSelect;
    
    private EnumItem(final String name, final int ordinal, final String displayName) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = displayName;
        this.material = null;
        this.show = true;
        this.showInLore = true;
        this.itemStack = null;
        this.isPlaySoundEnabled = false;
        this.sound = null;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = false;
    }
    
    private EnumItem(final List<String> lore) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = null;
        this.material = null;
        this.show = true;
        this.lore = lore;
        this.showInLore = true;
        this.itemStack = null;
        this.isPlaySoundEnabled = false;
        this.sound = null;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = false;
    }
    
    private EnumItem(final String name, final int ordinal, final String displayName, final GMaterial material) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = displayName;
        this.material = material;
        this.show = true;
        this.showInLore = true;
        this.itemStack = ItemUtils.item(this.displayName, material.getEnumMaterial(), material.getData(), null);
        this.isPlaySoundEnabled = false;
        this.sound = null;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = false;
    }
    
    private EnumItem(final String displayName, final List<String> lore) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = displayName;
        this.material = null;
        this.show = true;
        this.lore = lore;
        this.showInLore = true;
        this.itemStack = null;
        this.isPlaySoundEnabled = false;
        this.sound = null;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = false;
    }
    
    private EnumItem(final String name, final int ordinal, final String displayName, final boolean show) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = displayName;
        this.material = null;
        this.show = show;
        this.showInLore = true;
        this.itemStack = null;
        this.isPlaySoundEnabled = false;
        this.sound = null;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = false;
    }
    
    private EnumItem(final String name, final int ordinal, final String displayName, final int slot) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = displayName;
        this.material = null;
        this.show = true;
        this.showInLore = true;
        this.itemStack = null;
        this.isPlaySoundEnabled = false;
        this.sound = null;
        this.slot = slot;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = false;
    }
    
    private EnumItem(final boolean showInLore, final List<String> lore) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = null;
        this.material = null;
        this.show = true;
        this.lore = lore;
        this.showInLore = showInLore;
        this.itemStack = null;
        this.isPlaySoundEnabled = false;
        this.sound = null;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = false;
    }
    
    private EnumItem(final String name, final int ordinal, final boolean isPlaySoundEnabled, final SoundEffect sound) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = null;
        this.material = null;
        this.show = true;
        this.showInLore = true;
        this.itemStack = null;
        this.isPlaySoundEnabled = isPlaySoundEnabled;
        this.sound = sound;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = true;
    }
    
    private EnumItem(final List<String> lore, final boolean isPlaySoundEnabled, final SoundEffect sound) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = null;
        this.material = null;
        this.show = true;
        this.lore = lore;
        this.showInLore = true;
        this.itemStack = null;
        this.isPlaySoundEnabled = isPlaySoundEnabled;
        this.sound = sound;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = true;
    }
    
    private EnumItem(final String name, final int ordinal, final String displayName, final boolean show, final int slot) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = displayName;
        this.material = null;
        this.show = show;
        this.showInLore = true;
        this.itemStack = null;
        this.isPlaySoundEnabled = false;
        this.sound = null;
        this.slot = slot;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = true;
    }
    
    private EnumItem(final String name, final int ordinal, final boolean showCustomItem, final GMaterial customItem, final boolean isPlaySoundEnabled, final SoundEffect sound) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = null;
        this.material = null;
        this.showInLore = true;
        this.itemStack = null;
        this.isPlaySoundEnabled = isPlaySoundEnabled;
        this.sound = sound;
        this.showCustomItem = showCustomItem;
        this.customItem = customItem;
        this.isCloseGUIMenuAfterSelect = true;
    }
    
    private EnumItem(final boolean showInLore, final List<String> lore, final boolean isPlaySoundEnabled, final SoundEffect sound, final boolean isCloseGUIMenuAfterSelect) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = null;
        this.material = null;
        this.show = true;
        this.lore = lore;
        this.showInLore = showInLore;
        this.itemStack = null;
        this.isPlaySoundEnabled = isPlaySoundEnabled;
        this.sound = sound;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = isCloseGUIMenuAfterSelect;
    }
    
    private EnumItem(final String displayName, final GMaterial material, final List<String> lore) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = displayName;
        this.material = material;
        this.show = true;
        this.lore = lore;
        this.showInLore = true;
        this.itemStack = ItemUtils.item(this.displayName, material, this.lore);
        this.isPlaySoundEnabled = false;
        this.sound = null;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = false;
    }
    
    private EnumItem(final String displayName, final GMaterial material, final List<String> lore, final int slot) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = displayName;
        this.material = material;
        this.show = true;
        this.lore = lore;
        this.showInLore = true;
        this.itemStack = ItemUtils.item(this.displayName, material, this.lore);
        this.isPlaySoundEnabled = false;
        this.sound = null;
        this.slot = slot;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = false;
    }
    
    private EnumItem(final String displayName, final GMaterial material, final boolean show, final List<String> lore, final int slot) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = displayName;
        this.material = material;
        this.show = show;
        this.lore = lore;
        this.showInLore = true;
        this.itemStack = ItemUtils.item(this.displayName, material, this.lore);
        this.isPlaySoundEnabled = false;
        this.sound = null;
        this.slot = slot;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = false;
    }
    
    private EnumItem(final String displayName, final GMaterial material, final boolean show, final List<String> lore, final boolean isPlaySoundEnabled, final SoundEffect sound, final int slot) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = displayName;
        this.material = material;
        this.show = show;
        this.lore = lore;
        this.showInLore = true;
        this.itemStack = ItemUtils.item(this.displayName, material, this.lore);
        this.isPlaySoundEnabled = isPlaySoundEnabled;
        this.sound = sound;
        this.slot = slot;
        this.showCustomItem = false;
        this.customItem = null;
        this.isCloseGUIMenuAfterSelect = false;
    }
    
    private EnumItem(final boolean showInLore, final List<String> lore, final boolean isPlaySoundEnabled, final SoundEffect sound, final boolean showCustomItem, final GMaterial customItem, final boolean isCloseGUIMenuAfterSelect) {
        this.displayName = "ERROR";
        this.material = new GMaterial(EnumMaterial.AIR);
        this.show = true;
        this.lore = null;
        this.showInLore = true;
        this.itemStack = ItemUtils.item("Error", EnumMaterial.BARRIER, 0);
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.slot = 0;
        this.showCustomItem = true;
        this.customItem = new GMaterial(EnumMaterial.AIR);
        this.isCloseGUIMenuAfterSelect = true;
        this.displayName = null;
        this.material = null;
        this.show = true;
        this.lore = lore;
        this.showInLore = showInLore;
        this.itemStack = null;
        this.isPlaySoundEnabled = isPlaySoundEnabled;
        this.sound = sound;
        this.showCustomItem = showCustomItem;
        this.customItem = customItem;
        this.isCloseGUIMenuAfterSelect = isCloseGUIMenuAfterSelect;
    }
    
    public String getDisplayName() {
        return ChatUtil.format(this.displayName);
    }
    
    public GMaterial getMaterial() {
        return this.material;
    }
    
    public boolean show() {
        return this.show;
    }
    
    public List<String> getLore() {
        return this.lore;
    }
    
    public boolean isShowInLore() {
        return this.showInLore;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    public void setItemStack(final ItemStack itemStack) {
        this.itemStack = itemStack;
    }
    
    public boolean isPlaySoundEnabled() {
        return this.isPlaySoundEnabled;
    }
    
    public SoundEffect getSound() {
        return this.sound;
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    public boolean showCustomItem() {
        return this.showCustomItem;
    }
    
    public GMaterial getCustomItem() {
        return this.customItem;
    }
    
    public boolean isCloseGUIMenuAfterSelect() {
        return this.isCloseGUIMenuAfterSelect;
    }
}
