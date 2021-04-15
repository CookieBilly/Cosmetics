

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils;

import net.minecraft.server.v1_16_R3.DataWatcherRegistry;
import net.minecraft.server.v1_16_R3.VillagerData;
import java.util.UUID;
import net.minecraft.server.v1_16_R3.IBlockData;
import java.util.Optional;
import net.minecraft.server.v1_16_R3.DataWatcherSerializer;

public class DataWatcherWrapper
{
    public static final DataWatcherSerializer<Byte> BYTE;
    public static final DataWatcherSerializer<Integer> INTEGER;
    public static final DataWatcherSerializer<Float> FLOAT;
    public static final DataWatcherSerializer<Optional<IBlockData>> BLOCK_DATA;
    public static final DataWatcherSerializer<Boolean> BOOLEAN;
    public static final DataWatcherSerializer<Optional<UUID>> UUID;
    public static final DataWatcherSerializer<VillagerData> VILLAGER_DATA;
    
    static {
        BYTE = DataWatcherRegistry.a;
        INTEGER = DataWatcherRegistry.b;
        FLOAT = DataWatcherRegistry.c;
        BLOCK_DATA = DataWatcherRegistry.h;
        BOOLEAN = DataWatcherRegistry.i;
        UUID = DataWatcherRegistry.o;
        VILLAGER_DATA = DataWatcherRegistry.q;
    }
}
