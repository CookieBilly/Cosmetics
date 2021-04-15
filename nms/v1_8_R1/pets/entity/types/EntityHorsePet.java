// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseVariant;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseMarking;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseArmor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IChestedAbstractPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHorsePet;
import ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 1.4f, height = 1.6f)
public class EntityHorsePet extends EntityAgeablePet implements IEntityHorsePet, IChestedAbstractPet
{
    protected float jumpPower;
    protected boolean bA;
    
    public EntityHorsePet(final World world) {
        super(world);
        this.jumpPower = 0.0f;
    }
    
    public EntityHorsePet(final World world, final IPet pet) {
        super(world, pet);
        this.jumpPower = 0.0f;
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(16, (Object)0);
        this.datawatcher.a(19, (Object)0);
        this.datawatcher.a(20, (Object)0);
        this.datawatcher.a(21, (Object)"");
        this.datawatcher.a(22, (Object)0);
    }
    
    public int getVariant() {
        return this.datawatcher.getInt(20);
    }
    
    public void setVariant(final GHorseColor gHorseColor) {
    }
    
    @Override
    public GHorseArmor getArmor() {
        return GHorseArmor.getById(this.datawatcher.getInt(22));
    }
    
    @Override
    public void setArmor(final GHorseArmor gHorseArmor) {
        this.datawatcher.watch(22, (Object)gHorseArmor.getId());
    }
    
    @Override
    public GHorseColor getColor() {
        return GHorseColor.values()[this.getVariant() & 0xFF];
    }
    
    @Override
    public void setColor(final GHorseColor gHorseColor) {
        this.datawatcher.watch(20, (Object)((gHorseColor.ordinal() & 0xFF) | this.getMarking().ordinal() << 8));
    }
    
    @Override
    public GHorseMarking getMarking() {
        return GHorseMarking.values()[this.getVariant() >>> 8];
    }
    
    @Override
    public void setMarking(final GHorseMarking gHorseMarking) {
        this.datawatcher.watch(20, (Object)((this.getColor().ordinal() & 0xFF) | gHorseMarking.ordinal() << 8));
    }
    
    @Override
    public GHorseVariant getType() {
        return GHorseVariant.getById(this.datawatcher.getByte(19));
    }
    
    @Override
    public void setType(final GHorseVariant gHorseVariant) {
        this.datawatcher.watch(19, (Object)(byte)gHorseVariant.getId());
    }
    
    @Override
    public boolean hasChest() {
        return this.getHorseVisual(8);
    }
    
    @Override
    public void setHasChest(final boolean b) {
        this.setHorseVisual(8, b);
    }
    
    @Override
    public boolean hasSaddle() {
        return this.getHorseVisual(4);
    }
    
    @Override
    public void setSaddle(final boolean b) {
        this.setHorseVisual(4, b);
    }
    
    public boolean getHorseVisual(final int n) {
        return (this.datawatcher.getInt(16) & n) != 0x0;
    }
    
    public void setHorseVisual(final int n, final boolean b) {
        final int int1 = this.datawatcher.getInt(16);
        if (b) {
            this.datawatcher.watch(16, (Object)(int1 | n));
        }
        else {
            this.datawatcher.watch(16, (Object)(int1 & ~n));
        }
    }
}
