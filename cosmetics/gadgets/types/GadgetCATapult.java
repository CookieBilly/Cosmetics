

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.FireworkUtils;
import java.util.Arrays;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.entity.Ocelot;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.entity.Cat;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GOcelotType;
import org.bukkit.util.Vector;
import org.bukkit.entity.Animals;
import java.util.HashMap;
import org.bukkit.entity.Item;
import java.util.ArrayList;

public class GadgetCATapult extends Gadget
{
    private boolean activated;
    private ArrayList<Item> items;
    private HashMap<Animals, Vector> ocelots;
    private int step;
    private static GOcelotType[] types;
    
    static {
        GadgetCATapult.types = new GOcelotType[] { GOcelotType.BLACK_CAT, GOcelotType.RED_CAT, GOcelotType.SIAMESE_CAT, GOcelotType.WILD_OCELOT };
    }
    
    public GadgetCATapult(final UUID uuid) {
        super(uuid, GadgetType.CATAPULT);
        this.activated = false;
        this.items = new ArrayList<Item>();
        this.ocelots = new HashMap<Animals, Vector>();
        this.step = 0;
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.activated = true;
    }
    
    @Override
    public void onUpdate() {
        if (this.activated) {
            final Iterator<Animals> iterator;
            Animals key;
            GOcelotType gOcelotType;
            Cat key2;
            Ocelot key3;
            final Iterator<Animals> iterator2;
            Animals animals;
            EnumMaterial[] array;
            final Iterator<Item> iterator3;
            Item item;
            int i = 0;
            final Item e;
            final Iterator<Animals> iterator4;
            Animals animals2;
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                if (!this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType()) {
                    this.onClear();
                }
                else {
                    if (!this.ocelots.isEmpty()) {
                        this.ocelots.keySet().iterator();
                        while (iterator.hasNext()) {
                            key = iterator.next();
                            key.setVelocity(new Vector(this.ocelots.get(key).getX() / 2.0, this.ocelots.get(key).getY() / 2.0, this.ocelots.get(key).getZ() / 2.0));
                        }
                    }
                    ++this.step;
                    if (this.step == 1 || this.step == 5 || this.step == 10 || this.step == 15 || this.step == 20) {
                        CookieGadgets.setBypassCreatureSpawn(true);
                        gOcelotType = GadgetCATapult.types[CookieGadgets.random().nextInt(GadgetCATapult.types.length)];
                        if (VersionManager.is1_14OrAbove() && gOcelotType != GOcelotType.WILD_OCELOT) {
                            key2 = (Cat)this.getPlayer().getWorld().spawn(this.getPlayer().getEyeLocation(), (Class)Cat.class);
                            key2.setCatType(gOcelotType.getType());
                            key2.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            this.ocelots.put((Animals)key2, this.getPlayer().getLocation().getDirection());
                        }
                        else {
                            key3 = (Ocelot)this.getPlayer().getWorld().spawn(this.getPlayer().getEyeLocation(), (Class)Ocelot.class);
                            if (!VersionManager.is1_14OrAbove()) {
                                key3.setCatType(gOcelotType.getDepracatedType());
                            }
                            key3.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            this.ocelots.put((Animals)key3, this.getPlayer().getLocation().getDirection());
                        }
                        SoundEffect.ENTITY_FIREWORK_ROCKET_LAUNCH.playSound(this.getPlayer());
                        CookieGadgets.setBypassCreatureSpawn(false);
                    }
                    else if (this.step == 40) {
                        this.ocelots.keySet().iterator();
                        while (iterator2.hasNext()) {
                            animals = iterator2.next();
                            FireworkUtils.displayFirework(animals.getLocation(), FireworkEffect.Type.BURST, false, false, Arrays.asList(Color.RED), Arrays.asList(Color.RED));
                            array = new EnumMaterial[] { EnumMaterial.POPPY, EnumMaterial.BONE, EnumMaterial.STRING };
                            this.items.iterator();
                            while (iterator3.hasNext()) {
                                item = iterator3.next();
                                if (item.getTicksLived() > 50) {
                                    item.remove();
                                }
                            }
                            while (i <= 15) {
                                this.getPlayer().getWorld().dropItem(animals.getEyeLocation(), ItemUtils.item(UUID.randomUUID().toString(), array[CookieGadgets.random().nextInt(array.length)], 0));
                                e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                                e.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) / 1.7, 0.4, (CookieGadgets.random().nextDouble() - 0.5) / 1.7));
                                this.items.add(e);
                                ++i;
                            }
                            animals.remove();
                        }
                    }
                    else if (this.step == 41) {
                        this.ocelots.keySet().iterator();
                        while (iterator4.hasNext()) {
                            animals2 = iterator4.next();
                            if (animals2.isOnGround()) {
                                ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.ItemData(EnumMaterial.REDSTONE_BLOCK, (byte)0), animals2.getLocation(), 1.0f, 0.0f, 1.0f, 0.0f, 5);
                            }
                        }
                    }
                    else if (this.step >= 80) {
                        this.clearAll();
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
        if (!this.items.isEmpty()) {
            final Iterator<Item> iterator = this.items.iterator();
            while (iterator.hasNext()) {
                iterator.next().remove();
            }
            this.items.clear();
        }
        this.activated = false;
        for (final Animals animals : this.ocelots.keySet()) {
            if (animals.isValid()) {
                animals.remove();
            }
        }
        this.ocelots.clear();
        this.step = 0;
    }
}
