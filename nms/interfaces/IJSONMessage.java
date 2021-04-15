

package ws.billy.CookieGadgets.nms.interfaces;

import org.bukkit.entity.Player;
import java.util.List;

public interface IJSONMessage
{
    IJSONMessage openFile(final String p0);
    
    IJSONMessage openLink(final String p0);
    
    IJSONMessage suggestCommand(final String p0);
    
    IJSONMessage runCommand(final String p0);
    
    IJSONMessage showText(final String p0);
    
    IJSONMessage showText(final List<String> p0);
    
    IJSONMessage then(final Object p0);
    
    String toJSONString();
    
    void send(final Player p0);
}
