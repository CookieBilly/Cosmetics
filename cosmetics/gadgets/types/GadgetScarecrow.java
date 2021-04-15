// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.block.data.BlockData;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import ws.billy.CookieGadgets.utils.BlockUtil;
import org.bukkit.block.data.Directional;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.cosmetics.emotes.EmoteType;
import ws.billy.CookieGadgets.cosmetics.banners.BannerType;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitEquipmentType;
import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHatType;
import ws.billy.CookieGadgets.cosmetics.hats.standard.HatType;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.PlayerUtils;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.configuration.FileManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.entity.Bat;
import java.util.ArrayList;
import org.bukkit.Location;
import java.util.HashMap;

public class GadgetScarecrow extends Gadget
{
    private boolean activated;
    private HashMap<Location, String> blocks;
    private ArrayList<Bat> bats;
    private HashMap<Player, ItemStack> players;
    private Location centerLocation;
    public static HashMap<Player, ItemStack> pumpkinPlayers;
    private static String lanternDisplayName;
    
    static {
        GadgetScarecrow.pumpkinPlayers = new HashMap<Player, ItemStack>();
        GadgetScarecrow.lanternDisplayName = FileManager.getGadgetsFile().getString("Gadgets.Mobs And NPCs.Types.Scarecrow.Jack-O-Lantern-DisplayName");
    }
    
    public GadgetScarecrow(final UUID uuid) {
        super(uuid, GadgetType.SCARECROW);
        this.activated = false;
        this.blocks = new HashMap<Location, String>();
        this.bats = new ArrayList<Bat>();
        this.players = new HashMap<Player, ItemStack>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        if (!this.getPlayer().isOnGround()) {
            this.getPlayer().sendMessage(MessageType.NOT_ON_GROUND.getFormatMessage());
            return false;
        }
        final CuboID cuboID = new CuboID(this.getPlayer().getLocation().clone().add(1.0, 0.0, -1.0), this.getPlayer().getLocation().clone().add(1.0, 2.0, 1.0));
        if (!cuboID.isEmpty() || cuboID.hasHangingEntities()) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        (this.centerLocation = this.getPlayer().getLocation().getBlock().getLocation().clone()).setYaw(-90.0f);
        this.centerLocation.setPitch(this.getPlayer().getLocation().getPitch());
        this.getPlayer().teleport(this.centerLocation.clone().add(0.5, 0.0, 0.5));
        this.setBlock(this.getLocation(1, 0, 0), EnumMaterial.OAK_FENCE, false, (byte)0);
        this.setBlock(this.getLocation(1, 1, -1), EnumMaterial.OAK_FENCE, false, (byte)0);
        this.setBlock(this.getLocation(1, 1, 1), EnumMaterial.OAK_FENCE, false, (byte)0);
        this.setBlock(this.getLocation(1, 1, 0), EnumMaterial.YELLOW_TERRACOTTA, false, (byte)4);
        this.setBlock(this.getLocation(1, 2, 0), EnumMaterial.JACK_O_LANTERN, true, (byte)1);
        new BukkitRunnable() {
            public void run() {
                try {
                    if (!GadgetScarecrow.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetScarecrow.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetScarecrow.this.getPlayer()).getCurrentGadget().getType() != GadgetScarecrow.this.getType() || !GadgetScarecrow.this.activated) {
                        this.cancel();
                        return;
                    }
                    CookieGadgets.setBypassCreatureSpawn(true);
                    final Bat e = (Bat)GadgetScarecrow.this.getPlayer().getWorld().spawnEntity(GadgetScarecrow.this.centerLocation.clone().add(1.0 + CookieGadgets.random().nextDouble(), 1.5 + CookieGadgets.random().nextDouble(), CookieGadgets.random().nextDouble()), EntityType.BAT);
                    GadgetScarecrow.this.bats.add(e);
                    e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    CookieGadgets.setBypassCreatureSpawn(false);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    GadgetScarecrow.this.clearAll();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 10L, 15L);
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> {
            if (!this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType() || !this.activated) {
                return;
            }
            else {
                this.clearAll();
                return;
            }
        }, 200L);
        this.activated = true;
    }
    
    @Override
    public void onUpdate() {
        if (this.activated) {
            final Iterator<Player> iterator;
            Player player;
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                if (this.getPlayer().isOnline() && CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() != null && CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() == this.getType() && this.activated) {
                    ParticleEffect.SMOKE_LARGE.display(this.centerLocation.clone().add(1.0, 0.5, 0.0), 1.3f, 0.3f, 1.3f, 0.0f, 2);
                    PlayerUtils.getNearbyPlayers(this.centerLocation, 5.0).iterator();
                    while (iterator.hasNext()) {
                        player = iterator.next();
                        if (!GadgetScarecrow.pumpkinPlayers.containsKey(player) && !this.players.containsKey(player) && !player.hasMetadata("NPC")) {
                            GadgetScarecrow.pumpkinPlayers.put(player, player.getInventory().getHelmet());
                            this.players.put(player, player.getInventory().getHelmet());
                            player.getInventory().setHelmet(CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(ItemUtils.item(GadgetScarecrow.lanternDisplayName, EnumMaterial.JACK_O_LANTERN, 0), "Cosmetics", "true"), "Category", "Gadget"));
                        }
                    }
                }
            });
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        final Iterator<Location> iterator = this.blocks.keySet().iterator();
        while (iterator.hasNext()) {
            final Block block = iterator.next().clone().getBlock();
            block.setType(Material.AIR);
            block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        }
        this.blocks.clear();
        for (final Bat bat : this.bats) {
            if (bat.isValid()) {
                ParticleEffect.SMOKE_LARGE.display(bat.getLocation(), 0.1f, 0.1f, 0.1f, 1);
                bat.remove();
            }
        }
        this.bats.clear();
        for (final Player player : this.players.keySet()) {
            if (player != null && player.isOnline()) {
                unequipLitPumpkin(player);
            }
        }
        this.players.clear();
        this.centerLocation = null;
        this.activated = false;
    }
    
    public static void unequipLitPumpkin(final Player key) {
        if (key == null || !key.isOnline()) {
            return;
        }
        if (GadgetScarecrow.pumpkinPlayers.containsKey(key)) {
            if (key.getInventory().getHelmet() != null && key.getInventory().getHelmet().getItemMeta() != null && key.getInventory().getHelmet().getItemMeta().hasDisplayName() && key.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatUtil.format(GadgetScarecrow.lanternDisplayName))) {
                final ItemStack itemStack = GadgetScarecrow.pumpkinPlayers.get(key);
                if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().hasDisplayName()) {
                    final String format = ChatUtil.format(itemStack.getItemMeta().getDisplayName());
                    final PlayerManager playerManager = CookieGadgets.getPlayerManager(key);
                    if (playerManager != null) {
                        final Iterator<HatType> iterator = HatType.values().iterator();
                        while (iterator.hasNext()) {
                            if (iterator.next().getDisplayName().equals(format) && CookieGadgets.getPlayerManager(key).getEquippedHat() == null) {
                                key.getInventory().setHelmet((ItemStack)null);
                                key.updateInventory();
                                GadgetScarecrow.pumpkinPlayers.remove(key);
                                return;
                            }
                        }
                        final Iterator<AnimatedHatType> iterator2 = AnimatedHatType.values().iterator();
                        while (iterator2.hasNext()) {
                            if (iterator2.next().getDisplayName().equals(format) && CookieGadgets.getPlayerManager(key).getEquippedAnimatedHat() == null) {
                                key.getInventory().setHelmet((ItemStack)null);
                                key.updateInventory();
                                GadgetScarecrow.pumpkinPlayers.remove(key);
                                return;
                            }
                        }
                        for (final SuitEquipmentType suitEquipmentType : SuitEquipmentType.values()) {
                            if (suitEquipmentType.getSuitMaterial().getArmorType() == EnumArmorType.HELMET && suitEquipmentType.getDisplayName().equals(format) && (playerManager.getEquippedSuitEquipment().isEmpty() || (!playerManager.getEquippedSuitEquipment().isEmpty() && !CookieGadgets.getPlayerManager(key).getEquippedSuitEquipment().containsKey(EnumArmorType.HELMET)))) {
                                key.getInventory().setHelmet((ItemStack)null);
                                key.updateInventory();
                                GadgetScarecrow.pumpkinPlayers.remove(key);
                                return;
                            }
                        }
                        final Iterator<BannerType> iterator4 = BannerType.values().iterator();
                        while (iterator4.hasNext()) {
                            if (iterator4.next().getDisplayName().equals(format) && CookieGadgets.getPlayerManager(key).getEquippedBanner() == null) {
                                key.getInventory().setHelmet((ItemStack)null);
                                key.updateInventory();
                                GadgetScarecrow.pumpkinPlayers.remove(key);
                                return;
                            }
                        }
                        final Iterator<EmoteType> iterator5 = EmoteType.values().iterator();
                        while (iterator5.hasNext()) {
                            if (iterator5.next().getDisplayName().equals(format) && CookieGadgets.getPlayerManager(key).getEquippedEmote() == null) {
                                key.getInventory().setHelmet((ItemStack)null);
                                key.updateInventory();
                                GadgetScarecrow.pumpkinPlayers.remove(key);
                                return;
                            }
                        }
                    }
                }
                key.getInventory().setHelmet((ItemStack)GadgetScarecrow.pumpkinPlayers.get(key));
            }
            GadgetScarecrow.pumpkinPlayers.remove(key);
        }
    }
    
    private void setBlock(final Block block, final EnumMaterial enumMaterial, final boolean b, final byte b2) {
        if (!this.blocks.containsKey(block.getLocation())) {
            if (VersionManager.is1_13OrAbove()) {
                try {
                    this.blocks.put(block.getLocation(), block.getBlockData().getAsString());
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            else {
                try {
                    this.blocks.put(block.getLocation(), String.valueOf(block.getType().toString()) + "," + block.getData());
                }
                catch (NoSuchMethodError noSuchMethodError2) {}
            }
            block.setType(enumMaterial.getType());
            if (VersionManager.is1_13OrAbove()) {
                if (b) {
                    final BlockData blockData = block.getBlockData();
                    ((Directional)blockData).setFacing(BlockUtil.getBlockFace(b2));
                    block.setBlockData(blockData);
                }
            }
            else {
                ReflectionUtils.setData(block, b ? b2 : enumMaterial.getData());
            }
            block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        }
    }
    
    private Block getLocation(final int n, final int n2, final int n3) {
        return this.centerLocation.getBlock().getRelative(n, n2, n3);
    }
}
