

package ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_12_R1.World;
import net.minecraft.server.v1_12_R1.DataWatcher;
import net.minecraft.server.v1_12_R1.DataWatcherRegistry;
import net.minecraft.server.v1_12_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityVexPet;
import ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.EntityNoClipPet;

@EntitySize(width = 0.4f, height = 0.8f)
public class EntityVexPet extends EntityNoClipPet implements IEntityVexPet
{
    protected static final DataWatcherObject<Byte> DATA;
    
    static {
        DATA = DataWatcher.a((Class)EntityVexPet.class, DataWatcherRegistry.a);
    }
    
    public EntityVexPet(final World world) {
        super(world);
    }
    
    public EntityVexPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.getDataWatcher().register((DataWatcherObject)EntityVexPet.DATA, (Object)0);
    }
    
    private void setData(final int n, final boolean b) {
        final byte byteValue = (byte)this.datawatcher.get((DataWatcherObject)EntityVexPet.DATA);
        byte b2;
        if (b) {
            b2 = (byte)(byteValue | n);
        }
        else {
            b2 = (byte)(byteValue & ~n);
        }
        this.datawatcher.set((DataWatcherObject)EntityVexPet.DATA, (Object)(byte)(b2 & 0xFF));
    }
    
    @Override
    public boolean isPowered() {
        return this.getData(1);
    }
    
    @Override
    public void setPowered(final boolean b) {
        this.setData(1, b);
    }
    
    private boolean getData(final int n) {
        return ((byte)this.datawatcher.get((DataWatcherObject)EntityVexPet.DATA) & n) != 0x0;
    }
}
