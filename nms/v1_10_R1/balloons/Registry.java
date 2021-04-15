

package ws.billy.CookieGadgets.nms.v1_10_R1.balloons;

import java.lang.reflect.Field;
import org.bukkit.entity.EntityType;
import java.util.Map;
import net.minecraft.server.v1_10_R1.EntityTypes;

public class Registry
{
    public Registry() {
        try {
            Field[] declaredFields;
            for (int length = (declaredFields = EntityTypes.class.getDeclaredFields()).length, i = 0; i < length; ++i) {
                final Field field = declaredFields[i];
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                final String name;
                switch (name = field.getName()) {
                    case "d": {
                        ((Map)field.get(null)).put(NMSBalloons.class, "CookieGadgets-Balloon");
                        System.out.println("test1");
                        break;
                    }
                    case "f": {
                        System.out.println(EntityType.SLIME.getTypeId());
                        ((Map)field.get(null)).put(NMSBalloons.class, (int)EntityType.SLIME.getTypeId());
                        System.out.println("test2");
                        break;
                    }
                    default:
                        break;
                }
            }
            System.out.println("test2");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void unregister() {
        try {
            Field[] declaredFields;
            for (int length = (declaredFields = EntityTypes.class.getDeclaredFields()).length, i = 0; i < length; ++i) {
                final Field field = declaredFields[i];
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                final String name;
                switch (name = field.getName()) {
                    case "Wololo": {
                        System.out.println(((Map)field.get(null)).remove(NMSBalloons.class));
                        System.out.println("test3");
                        break;
                    }
                    case "f": {
                        System.out.println(((Map)field.get(null)).remove(NMSBalloons.class));
                        System.out.println("test4");
                        break;
                    }
                    default:
                        break;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
