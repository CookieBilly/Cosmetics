

package ws.billy.CookieGadgets.utils.cosmetics.miniatures;

public class MiniatureEquipments
{
    private MiniatureArmorType hand;
    private MiniatureArmorType helmet;
    private MiniatureArmorType chestplate;
    private MiniatureArmorType leggings;
    private MiniatureArmorType boots;
    
    public MiniatureEquipments(final MiniatureArmorType hand, final MiniatureArmorType helmet, final MiniatureArmorType chestplate, final MiniatureArmorType leggings, final MiniatureArmorType boots) {
        this.hand = hand;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }
    
    public MiniatureArmorType getHand() {
        return this.hand;
    }
    
    public MiniatureArmorType getHelmet() {
        return this.helmet;
    }
    
    public MiniatureArmorType getChestplate() {
        return this.chestplate;
    }
    
    public MiniatureArmorType getLeggings() {
        return this.leggings;
    }
    
    public MiniatureArmorType getBoots() {
        return this.boots;
    }
}
