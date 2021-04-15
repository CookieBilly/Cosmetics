

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.Effect;
import org.bukkit.Location;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.FireworkUtils;
import java.util.Arrays;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.block.Block;
import org.bukkit.Material;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.entity.Item;
import java.util.ArrayList;
import org.bukkit.entity.Creeper;

public class GadgetCreeperAstronaut extends Gadget
{
    private boolean activated;
    private Creeper creeper;
    private ArrayList<Item> items;
    private static EnumMaterial[] materials;
    
    static {
        GadgetCreeperAstronaut.materials = new EnumMaterial[] { EnumMaterial.BONE, EnumMaterial.GUNPOWDER, EnumMaterial.TNT };
    }
    
    public GadgetCreeperAstronaut(final UUID uuid) {
        super(uuid, GadgetType.CREEPER_ASTRONAUT);
        this.activated = false;
        this.items = new ArrayList<Item>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        final Block targetBlock = BlockUtil.getTargetBlock(this.getPlayer(), 5);
        if (targetBlock.isEmpty() || targetBlock.getType().equals((Object)Material.AIR)) {
            this.getPlayer().sendMessage(MessageType.TARGET_A_BLOCK.getFormatMessage());
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        final Block targetBlock = BlockUtil.getTargetBlock(this.getPlayer(), 5);
        CookieGadgets.setBypassCreatureSpawn(true);
        final Creeper creeper = (Creeper)targetBlock.getLocation().getWorld().spawn(targetBlock.getLocation().add(0.5, 2.0, 0.5), (Class)Creeper.class);
        creeper.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.creeper = creeper;
        CookieGadgets.setBypassCreatureSpawn(false);
        this.activated = true;
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!GadgetCreeperAstronaut.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetCreeperAstronaut.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetCreeperAstronaut.this.getPlayer()).getCurrentGadget().getType() != GadgetCreeperAstronaut.this.getType()) {
                    this.step = 10;
                    GadgetCreeperAstronaut.this.onClear();
                    this.cancel();
                    return;
                }
                if (this.step <= 9) {
                    ParticleEffect.SMOKE_LARGE.display(creeper.getLocation(), 0.3f, 10);
                    SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(creeper.getLocation());
                    creeper.setVelocity(new Vector(0.0, 0.7, 0.0));
                }
                else {
                    FireworkUtils.displayFirework(creeper.getLocation(), FireworkEffect.Type.BURST, false, false, Arrays.asList(Color.RED), Arrays.asList(Color.YELLOW));
                    for (int i = 0; i <= 25; ++i) {
                        final Item dropItem = GadgetCreeperAstronaut.this.getPlayer().getWorld().dropItem(creeper.getLocation(), ItemUtils.item(UUID.randomUUID().toString(), GadgetCreeperAstronaut.materials[CookieGadgets.random().nextInt(GadgetCreeperAstronaut.materials.length)], 0));
                        dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        dropItem.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) / 1.7, 0.4, (CookieGadgets.random().nextDouble() - 0.5) / 1.7));
                        dropItem.setPickupDelay(Integer.MAX_VALUE);
                        GadgetCreeperAstronaut.this.items.add(dropItem);
                    }
                    GadgetCreeperAstronaut.this.displayStepSound(creeper.getLocation(), Material.REDSTONE_BLOCK, 5);
                    if (GadgetCreeperAstronaut.this.creeper != null) {
                        GadgetCreeperAstronaut.this.creeper.remove();
                    }
                    this.cancel();
                    new BukkitRunnable() {
                        public void run() {
                            GadgetCreeperAstronaut.this.clearAll();
                        }
                    }.runTaskLater((Plugin)CookieGadgets.getInstance(), 70L);
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 10L, 5L);
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
    }
    
    private void clearAll() {
        final Iterator<Item> iterator = this.items.iterator();
        while (iterator.hasNext()) {
            iterator.next().remove();
        }
        this.items.clear();
        if (this.creeper != null) {
            this.creeper.remove();
        }
        this.creeper = null;
        this.activated = false;
    }
    
    private void displayStepSound(final Location location, final Material material, final int n) {
        for (int i = 0; i < n; ++i) {
            location.getWorld().playEffect(location, Effect.STEP_SOUND, (Object)material);
        }
    }
}
