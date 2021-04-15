// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.cosmetics.suits.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Color;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.Material;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.EnumColor;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;

public class SuitDisco extends Suit
{
    public SuitDisco(final UUID uuid) {
        super(uuid, SuitType.DISCO);
    }
    
    public void onUpdate() {
        final Color color = EnumColor.values()[CookieGadgets.random().nextInt(EnumColor.values().length)].getColor();
        final ItemStack helmet = this.getPlayer().getInventory().getHelmet();
        if (helmet.getType() == Material.LEATHER_HELMET) {
            final LeatherArmorMeta itemMeta = (LeatherArmorMeta)helmet.getItemMeta();
            itemMeta.setColor(color);
            helmet.setItemMeta((ItemMeta)itemMeta);
        }
        final ItemStack chestplate = this.getPlayer().getInventory().getChestplate();
        if (chestplate.getType() == Material.LEATHER_CHESTPLATE) {
            final LeatherArmorMeta itemMeta2 = (LeatherArmorMeta)chestplate.getItemMeta();
            itemMeta2.setColor(color);
            chestplate.setItemMeta((ItemMeta)itemMeta2);
        }
        final ItemStack leggings = this.getPlayer().getInventory().getLeggings();
        if (leggings.getType() == Material.LEATHER_LEGGINGS) {
            final LeatherArmorMeta itemMeta3 = (LeatherArmorMeta)leggings.getItemMeta();
            itemMeta3.setColor(color);
            leggings.setItemMeta((ItemMeta)itemMeta3);
        }
        final ItemStack boots = this.getPlayer().getInventory().getBoots();
        if (boots.getType() == Material.LEATHER_BOOTS) {
            final LeatherArmorMeta itemMeta4 = (LeatherArmorMeta)boots.getItemMeta();
            itemMeta4.setColor(color);
            boots.setItemMeta((ItemMeta)itemMeta4);
        }
    }
    
    @Override
    public void onClear() {
        HandlerList.unregisterAll((Listener)this);
    }
}
