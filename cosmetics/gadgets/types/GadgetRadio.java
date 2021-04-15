

package ws.billy.CookieGadgets.cosmetics.gadgets.types;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.StringUtils;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import java.util.ArrayList;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.List;
import java.io.File;

public class GadgetRadio extends Gadget
{
    private static String radioGUIName;
    private RadioSongPlayer songPlayer;
    private static File files;
    private static List<File> songs;
    
    static {
        GadgetRadio.radioGUIName = FileManager.getGadgetsFile().getString("Gadgets.Musical.Types.Radio.Items.GUI-Name");
        GadgetRadio.files = new File(String.valueOf(CookieGadgets.getInstance().getDataFolder().getPath()) + "/songs/RadioGadget/");
        GadgetRadio.songs = new ArrayList<File>();
    }
    
    public GadgetRadio(final UUID uuid) {
        super(uuid, GadgetType.RADIO);
        super.addCooldownAfterClick = false;
        if (GadgetRadio.songs.isEmpty()) {
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    if (GadgetRadio.files.exists() && GadgetRadio.files.listFiles().length > 0) {
                        File[] listFiles;
                        for (int length = (listFiles = GadgetRadio.files.listFiles()).length, i = 0; i < length; ++i) {
                            final File file = listFiles[i];
                            if (file.getName().contains(".nbs") && !GadgetRadio.songs.contains(file)) {
                                GadgetRadio.songs.add(file);
                            }
                        }
                    }
                }
            });
        }
    }
    
    @Override
    public void onClick() {
        this.openRadioMenu(this.getPlayer(), 1);
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
        if (this.songPlayer != null && this.songPlayer.isPlaying()) {
            this.songPlayer.setPlaying(false);
        }
        this.songPlayer = null;
    }
    
    private void openRadioMenu(final Player player, int n) {
        if (GadgetRadio.files.exists() && GadgetRadio.files.listFiles().length > 0) {
            File[] listFiles;
            for (int length = (listFiles = GadgetRadio.files.listFiles()).length, i = 0; i < length; ++i) {
                final File file = listFiles[i];
                if (file.getName().contains(".nbs") && !GadgetRadio.songs.contains(file)) {
                    GadgetRadio.songs.add(file);
                }
            }
        }
        final int size = GadgetRadio.songs.size();
        final int maxPagesAmount = GInventory.getMaxPagesAmount(27, size);
        if (n <= 0) {
            n = 1;
        }
        if (n > 1 && maxPagesAmount < n) {
            n = 1;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.format(String.valueOf(GadgetRadio.radioGUIName) + StringUtils.addPlaceholders(EnumItem.PAGES.getDisplayName(), Arrays.asList("{CURRENT_PAGE}", "{MAX_PAGES}"), Arrays.asList(String.valueOf(n), String.valueOf(maxPagesAmount)))));
        int n2 = 0;
        int n3 = 1;
        if (n > 1) {
            n3 = 27 * (n - 1) + 1;
        }
        int n4 = 27;
        if (size < 27) {
            n4 = size;
        }
        if (n > 1) {
            if (size >= 27 * n) {
                n4 = 27 * n;
            }
            else {
                n4 = size;
            }
        }
        for (int j = n3; j <= n4; ++j) {
            try {
                if (j > size) {
                    break;
                }
                InventoryUtils.inventory(inventory, StringUtils.addPlaceholders(EnumItem.RADIO_TRACK.getDisplayName(), Arrays.asList("{TRACK}", ".nbs"), Arrays.asList(GadgetRadio.songs.get(j - 1).getName(), "")), EnumItem.RADIO_TRACK.getMaterial(), EnumItem.RADIO_TRACK.getLore(), GInventory.LAY_OUT_27.getLayOut()[n2++]);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                return;
            }
        }
        if (n > 1) {
            InventoryUtils.inventory(inventory, EnumItem.PREVIOUS_PAGE.getItemStack(), "{PAGE}", String.valueOf(n - 1), EnumItem.PREVIOUS_PAGE.getSlot());
        }
        if (n < maxPagesAmount) {
            InventoryUtils.inventory(inventory, EnumItem.NEXT_PAGE.getItemStack(), "{PAGE}", String.valueOf(n + 1), EnumItem.NEXT_PAGE.getSlot());
        }
        InventoryUtils.inventory(inventory, EnumItem.RADIO_STOP_TRACK.getItemStack(), EnumItem.RADIO_STOP_TRACK.getSlot());
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
        CookieGadgets.getPlayerManager(player).setCurrentMenuPage(n);
    }
    
    @EventHandler
    public void onInvClickDisc(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (player != this.getPlayer() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType() || !inventoryClickEvent.getView().getTitle().startsWith(ChatUtil.format(GadgetRadio.radioGUIName))) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
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
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RADIO_STOP_TRACK.getItemStack(), EnumItem.RADIO_STOP_TRACK.getSlot())) {
            this.clearAll();
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.PREVIOUS_PAGE.getDisplayName(), EnumItem.PREVIOUS_PAGE.getSlot())) {
            this.openRadioMenu(player, CookieGadgets.getPlayerManager(player).getCurrentMenuPage() - 1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.NEXT_PAGE.getDisplayName(), EnumItem.NEXT_PAGE.getSlot())) {
            this.openRadioMenu(player, CookieGadgets.getPlayerManager(player).getCurrentMenuPage() + 1);
            player.updateInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        for (final File file : GadgetRadio.songs) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(FileManager.getGadgetsFile().getString("Gadgets.Musical.Types.Radio.Items.Track.Name").replace("{TRACK}", file.getName()).replace(".nbs", "")))) {
                this.clearAll();
                super.addCooldownTimer();
                (this.songPlayer = new RadioSongPlayer(NBSDecoder.parse(file))).setPlaying(true);
                if (!this.songPlayer.getPlayerList().contains(this.getPlayer())) {
                    this.songPlayer.addPlayer(this.getPlayer());
                }
                this.songPlayer.setVolume((byte)100);
                this.songPlayer.getFadeIn().setFadeStart((byte)25);
                new BukkitRunnable() {
                    int times = 0;
                    
                    public void run() {
                        if (!GadgetRadio.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetRadio.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetRadio.this.getPlayer()).getCurrentGadget().getType() != GadgetRadio.this.getType()) {
                            GadgetRadio.this.showCooldownBar = true;
                            this.cancel();
                            return;
                        }
                        if (this.times > 5) {
                            GadgetRadio.this.showCooldownBar = true;
                            this.cancel();
                            return;
                        }
                        GadgetRadio.this.showCooldownBar = false;
                        CookieGadgets.getPlayerManager(player).sendActionbarMessage(MessageType.PLAYING_SONG.getFormatMessage().replace("{RANDOM_COLOR}", ChatUtil.getRandomColor()).replace("{SONG}", file.getName().replace(".nbs", "")));
                        ++this.times;
                    }
                }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 10L);
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
        }
        inventoryClickEvent.setCancelled(true);
    }
}
