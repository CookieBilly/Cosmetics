

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Effect;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.StringUtils;
import ws.billy.CookieGadgets.utils.EnumItem;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.SoundCategory;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.cosmetics.gadgets.JukeboxTrack;

public class GadgetJukebox extends Gadget
{
    private static String JukeboxGUIName;
    private JukeboxTrack track;
    
    static {
        GadgetJukebox.JukeboxGUIName = FileManager.getGadgetsFile().getString("Gadgets.Musical.Types.Jukebox.Items.GUI-Name");
    }
    
    public GadgetJukebox(final UUID uuid) {
        super(uuid, GadgetType.JUKEBOX);
        this.track = null;
        super.addCooldownAfterClick = false;
    }
    
    @Override
    public void onClick() {
        this.openJukeboxMenu(this.getPlayer());
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        if (this.track != null) {
            if (VersionManager.is1_13OrAbove()) {
                try {
                    this.getPlayer().stopSound(this.track.getSoundTrack(), SoundCategory.RECORDS);
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            else if (VersionManager.is1_9OrAbove()) {
                try {
                    this.getPlayer().stopSound(this.track.getSoundTrack());
                }
                catch (NoSuchMethodError noSuchMethodError2) {}
            }
        }
        this.track = null;
    }
    
    private void openJukeboxMenu(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 36, ChatUtil.format(GadgetJukebox.JukeboxGUIName));
        if (VersionManager.is1_9OrAbove()) {
            JukeboxTrack[] values;
            for (int length = (values = JukeboxTrack.values()).length, i = 0; i < length; ++i) {
                final JukeboxTrack jukeboxTrack = values[i];
                InventoryUtils.inventory(inventory, StringUtils.addPlaceholder(EnumItem.JUKEBOX_TRACK.getDisplayName(), "{TRACK}", jukeboxTrack.getDisplayName()), jukeboxTrack.getMaterial(), EnumItem.JUKEBOX_TRACK.getLore(), jukeboxTrack.getSlot());
            }
        }
        InventoryUtils.inventory(inventory, EnumItem.JUKEBOX_STOP_TRACK.getItemStack(), EnumItem.JUKEBOX_STOP_TRACK.getSlot());
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler
    public void onInvClickDisc(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (player != this.getPlayer() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType() || !inventoryClickEvent.getView().getTitle().equals(ChatUtil.format(GadgetJukebox.JukeboxGUIName))) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 36 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (inventoryClickEvent.getCurrentItem() == null || inventoryClickEvent.getCurrentItem().getItemMeta() == null || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName() == null) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (inventoryClickEvent.getClick() == ClickType.SHIFT_LEFT || inventoryClickEvent.getClick() == ClickType.SHIFT_RIGHT || inventoryClickEvent.getClick() == ClickType.NUMBER_KEY || inventoryClickEvent.getClick() == ClickType.UNKNOWN) {
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.JUKEBOX_STOP_TRACK.getItemStack(), EnumItem.JUKEBOX_STOP_TRACK.getSlot())) {
            this.clearAll();
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (VersionManager.is1_9OrAbove()) {
            JukeboxTrack[] values;
            for (int length = (values = JukeboxTrack.values()).length, i = 0; i < length; ++i) {
                final JukeboxTrack track = values[i];
                if (ItemUtils.getCurrentItem(inventoryClickEvent, StringUtils.addPlaceholder(EnumItem.JUKEBOX_TRACK.getDisplayName(), "{TRACK}", track.getDisplayName()), track.getMaterial())) {
                    this.clearAll();
                    this.getPlayer().playEffect(this.getPlayer().getLocation(), Effect.RECORD_PLAY, (Object)track.getMaterial().getEnumMaterial().getType());
                    super.addCooldownTimer();
                    new BukkitRunnable() {
                        int times = 0;
                        
                        public void run() {
                            if (!GadgetJukebox.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetJukebox.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetJukebox.this.getPlayer()).getCurrentGadget().getType() != GadgetJukebox.this.getType()) {
                                GadgetJukebox.this.showCooldownBar = true;
                                this.cancel();
                                return;
                            }
                            if (this.times > 5) {
                                GadgetJukebox.this.showCooldownBar = true;
                                this.cancel();
                                return;
                            }
                            GadgetJukebox.this.showCooldownBar = false;
                            CookieGadgets.getPlayerManager(player).sendActionbarMessage(MessageType.PLAYING_SONG.getFormatMessage().replace("{RANDOM_COLOR}", ChatUtil.getRandomColor()).replace("{SONG}", track.getDisplayName()));
                            ++this.times;
                        }
                    }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 10L);
                    this.track = track;
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
            }
        }
        player.closeInventory();
        inventoryClickEvent.setCancelled(true);
    }
}
