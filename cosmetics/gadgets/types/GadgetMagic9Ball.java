

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.List;

public class GadgetMagic9Ball extends Gadget
{
    private static List<String> messages;
    
    static {
        GadgetMagic9Ball.messages = FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Magic 9 Ball.Messages");
    }
    
    public GadgetMagic9Ball(final UUID uuid) {
        super(uuid, GadgetType.MAGIC_9_BALL);
    }
    
    @Override
    public void onClick() {
        this.getPlayer().sendMessage(ChatUtil.format(GadgetMagic9Ball.messages.get(CookieGadgets.random().nextInt(GadgetMagic9Ball.messages.size()))));
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        HandlerList.unregisterAll((Listener)this);
    }
}
