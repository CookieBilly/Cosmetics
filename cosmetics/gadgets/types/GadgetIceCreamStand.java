

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.block.data.BlockData;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.Axis;
import org.bukkit.block.data.Orientable;
import org.bukkit.block.data.Openable;
import ws.billy.CookieGadgets.utils.BlockUtil;
import org.bukkit.block.data.Directional;
import java.lang.reflect.Field;
import org.bukkit.inventory.meta.ItemMeta;
import com.mojang.authlib.properties.Property;
import java.util.Base64;
import com.mojang.authlib.GameProfile;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.block.Block;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.List;
import org.bukkit.entity.ArmorStand;
import java.util.ArrayList;
import org.bukkit.Location;
import java.util.HashMap;

public class GadgetIceCreamStand extends Gadget
{
    private boolean activated;
    private HashMap<Location, String> blocks;
    private Location centerLocation;
    private ArrayList<ArmorStand> armorstands;
    private static String[] textures;
    private static List<String> messages;
    
    static {
        GadgetIceCreamStand.textures = new String[] { "95366ca17974892e4fd4c7b9b18feb11f05ba2ec47aa5035c81a9533b28", "06be02de9f61a2cc7c52a73cfadc810b5e4555a2e876d569a54691a226c3b", "3b77d9ec42ce6dc394145025d8baedf4ff9582026b36adf74c4c6ad9cc390", "32c1c0209b10e1b997b461287f9d426e316a4a6672278d4e463b19925fff26", "1149cb7f616a7b59845f4a27b955635bb1bb2b66fb6d9f3f331557e6f52fb7", "ab671c0206e2aead0a5e9b545f707966ca15563f17e019db16ab59d563d", "a6bc364673c34dc34a9578292f30aa9f2d4b9468115b86129e76786cff8299d", "7a19e875a0853fec2d36e8328ec6f664624ae991ffdd932f53489e3d314e", "c59f4b4f1a8bf4f4ad6874f930ac7d7e93e99061f879ab80428f52e757a29" };
        GadgetIceCreamStand.messages = FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Ice Cream Stand.Messages");
    }
    
    public GadgetIceCreamStand(final UUID uuid) {
        super(uuid, GadgetType.ICE_CREAM_STAND);
        this.activated = false;
        this.blocks = new HashMap<Location, String>();
        this.armorstands = new ArrayList<ArmorStand>();
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
        if (!new CuboID(this.getPlayer().getLocation().clone().add(1.0, 0.0, -1.0), this.getPlayer().getLocation().clone().add(3.0, 4.0, 1.0)).isEmpty()) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.centerLocation = this.getPlayer().getLocation().getBlock().getLocation().add(0.5, 0.0, 0.5).clone();
        this.setBlock(this.getLocation(3, 0, 0), EnumMaterial.OAK_FENCE_GATE, true, (byte)5);
        this.setBlock(this.getLocation(1, 0, 0), EnumMaterial.QUARTZ_PILLAR, true, (byte)4);
        this.setBlock(this.getLocation(2, 0, 0), EnumMaterial.QUARTZ_PILLAR, true, (byte)4);
        this.setBlock(this.getLocation(1, 0, -1), EnumMaterial.OAK_TRAPDOOR, true, (byte)4);
        this.setBlock(this.getLocation(2, 0, -1), EnumMaterial.OAK_TRAPDOOR, true, (byte)4);
        this.setBlock(this.getLocation(1, 0, 1), EnumMaterial.OAK_TRAPDOOR, true, (byte)5);
        this.setBlock(this.getLocation(2, 0, 1), EnumMaterial.OAK_TRAPDOOR, true, (byte)5);
        this.setBlock(this.getLocation(1, 1, 0), EnumMaterial.OAK_FENCE, false, (byte)0);
        this.setBlock(this.getLocation(1, 2, 0), EnumMaterial.OAK_FENCE, false, (byte)0);
        this.setBlock(this.getLocation(1, 3, 0), EnumMaterial.OAK_SLAB, false, (byte)0);
        for (int i = -1; i <= 1; i += 2) {
            this.setBlock(this.getLocation(1 + i, 3, -1), EnumMaterial.OAK_SLAB, false, (byte)0);
            this.setBlock(this.getLocation(1 + i, 3, 1), EnumMaterial.OAK_SLAB, false, (byte)0);
        }
        this.setBlock(this.getLocation(0, 3, 0), EnumMaterial.DARK_OAK_SLAB, false, (byte)5);
        this.setBlock(this.getLocation(2, 3, 0), EnumMaterial.DARK_OAK_SLAB, false, (byte)5);
        this.setBlock(this.getLocation(1, 3, -1), EnumMaterial.DARK_OAK_SLAB, false, (byte)5);
        this.setBlock(this.getLocation(1, 3, 1), EnumMaterial.DARK_OAK_SLAB, false, (byte)5);
        for (int j = 1; j <= 2; ++j) {
            for (int k = -1; k <= 1; k += 2) {
                final ArmorStand e = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.centerLocation.clone().add((double)j, -0.7, (double)k), EntityType.ARMOR_STAND);
                e.setVisible(false);
                e.setGravity(false);
                e.setSmall(true);
                e.setArms(false);
                e.setBasePlate(false);
                try {
                    if (VersionManager.is1_9OrAbove()) {
                        e.setSilent(true);
                    }
                    e.setMarker(false);
                }
                catch (NoSuchMethodError noSuchMethodError) {}
                e.setHelmet(this.getRandomIceCream(GadgetIceCreamStand.textures[CookieGadgets.random().nextInt(GadgetIceCreamStand.textures.length)]));
                e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                e.setMetadata("CookieGadgets-IceCream", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                this.armorstands.add(e);
            }
        }
        final ArmorStand e2 = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.centerLocation.clone().add(2.0, 0.3, 0.0), EntityType.ARMOR_STAND);
        e2.setVisible(false);
        e2.setGravity(false);
        e2.setSmall(true);
        e2.setArms(false);
        e2.setBasePlate(false);
        try {
            if (VersionManager.is1_9OrAbove()) {
                e2.setSilent(true);
            }
            e2.setMarker(false);
        }
        catch (NoSuchMethodError noSuchMethodError2) {}
        e2.setHelmet(this.getRandomIceCream("239299c36b54bb11e57c686bfe53a5c1c441310f90f69347bacddbf343609d"));
        e2.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        e2.setMetadata("CookieGadgets-IceCreamStand", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.armorstands.add(e2);
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> {
            if (!this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType() || !this.activated) {
                return;
            }
            else {
                this.clearAll();
                return;
            }
        }, 400L);
        this.activated = true;
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
        if (this.activated && this.centerLocation != null) {
            this.getLocation(1, 0, -1).setType(Material.AIR);
            this.getLocation(2, 0, -1).setType(Material.AIR);
            this.getLocation(1, 0, 1).setType(Material.AIR);
            this.getLocation(2, 0, 1).setType(Material.AIR);
        }
        final Iterator<Location> iterator = this.blocks.keySet().iterator();
        while (iterator.hasNext()) {
            final Block block = iterator.next().clone().getBlock();
            block.setType(Material.AIR);
            block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        }
        this.blocks.clear();
        this.centerLocation = null;
        for (final ArmorStand armorStand : this.armorstands) {
            if (armorStand.isValid()) {
                armorStand.remove();
            }
        }
        this.armorstands.clear();
        this.activated = false;
    }
    
    private ItemStack getRandomIceCream(final String str) {
        final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), 1, (short)EnumMaterial.PLAYER_HEAD.getData());
        final SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        final GameProfile value = new GameProfile(UUID.randomUUID(), (String)null);
        value.getProperties().put((Object)"textures", (Object)new Property("textures", new String(Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/" + str).getBytes()))));
        try {
            final Field declaredField = skullMeta.getClass().getDeclaredField("profile");
            declaredField.setAccessible(true);
            declaredField.set(skullMeta, value);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            final Throwable t;
            t.printStackTrace();
        }
        itemStack.setItemMeta((ItemMeta)skullMeta);
        return itemStack;
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
                    if (enumMaterial != EnumMaterial.QUARTZ_PILLAR) {
                        ((Directional)blockData).setFacing(BlockUtil.getBlockFace((enumMaterial == EnumMaterial.OAK_TRAPDOOR && b2 == 5) ? (b2 - 3) : (b2 - 1)));
                        if (blockData instanceof Openable) {
                            ((Openable)blockData).setOpen(true);
                        }
                    }
                    else if (blockData instanceof Orientable) {
                        ((Orientable)blockData).setAxis(Axis.X);
                    }
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
    
    @EventHandler
    public void onPlayerInteractEntity(final PlayerInteractAtEntityEvent playerInteractAtEntityEvent) {
        if (playerInteractAtEntityEvent.getPlayer() == this.getPlayer() && playerInteractAtEntityEvent.getRightClicked().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            if (playerInteractAtEntityEvent.getRightClicked().hasMetadata("CookieGadgets-IceCreamStand")) {
                if (playerInteractAtEntityEvent.getRightClicked().isValid()) {
                    playerInteractAtEntityEvent.getRightClicked().remove();
                }
                playerInteractAtEntityEvent.setCancelled(true);
                return;
            }
            if (playerInteractAtEntityEvent.getRightClicked().hasMetadata("CookieGadgets-IceCream")) {
                playerInteractAtEntityEvent.getPlayer().sendMessage(ChatUtil.format(GadgetIceCreamStand.messages.get(CookieGadgets.random().nextInt(GadgetIceCreamStand.messages.size()))));
                playerInteractAtEntityEvent.setCancelled(true);
            }
        }
    }
}
