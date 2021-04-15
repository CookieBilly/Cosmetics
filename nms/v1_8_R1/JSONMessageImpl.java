

package ws.billy.CookieGadgets.nms.v1_8_R1;

import net.minecraft.server.v1_8_R1.Packet;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import java.util.Iterator;
import java.io.IOException;
import java.io.Writer;
import org.bukkit.craftbukkit.libs.com.google.gson.stream.JsonWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import ws.billy.CookieGadgets.nms.interfaces.IJSONMessage;

public class JSONMessageImpl implements IJSONMessage
{
    private List<MessagePart> messageParts;
    
    public JSONMessageImpl(final String s) {
        (this.messageParts = new ArrayList<MessagePart>()).add(new MessagePart(s));
    }
    
    @Override
    public JSONMessageImpl openFile(final String s) {
        this.onClick("open_file", s);
        return this;
    }
    
    @Override
    public JSONMessageImpl openLink(final String s) {
        this.onClick("open_url", s);
        return this;
    }
    
    @Override
    public JSONMessageImpl suggestCommand(final String s) {
        this.onClick("suggest_command", s);
        return this;
    }
    
    @Override
    public JSONMessageImpl runCommand(final String s) {
        this.onClick("run_command", s);
        return this;
    }
    
    @Override
    public JSONMessageImpl showText(final String s) {
        this.onHover("show_text", s);
        return this;
    }
    
    @Override
    public JSONMessageImpl showText(final List<String> elements) {
        this.onHover("show_text", String.join("\n", elements));
        return this;
    }
    
    @Override
    public JSONMessageImpl then(final Object o) {
        this.messageParts.add(new MessagePart(o.toString()));
        return this;
    }
    
    @Override
    public String toJSONString() {
        final StringWriter stringWriter = new StringWriter();
        final JsonWriter jsonWriter = new JsonWriter((Writer)stringWriter);
        try {
            if (this.messageParts.size() == 1) {
                this.latest().writeJson(jsonWriter);
            }
            else {
                jsonWriter.beginObject().name("text").value("").name("extra").beginArray();
                final Iterator<MessagePart> iterator = this.messageParts.iterator();
                while (iterator.hasNext()) {
                    iterator.next().writeJson(jsonWriter);
                }
                jsonWriter.endArray().endObject();
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Invalid message");
        }
        return stringWriter.toString();
    }
    
    @Override
    public void send(final Player player) {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutChat(ChatSerializer.a(this.toJSONString())));
    }
    
    private MessagePart latest() {
        return this.messageParts.get(this.messageParts.size() - 1);
    }
    
    private void onClick(final String clickActionName, final String clickActionData) {
        final MessagePart latest = this.latest();
        latest.clickActionName = clickActionName;
        latest.clickActionData = clickActionData;
    }
    
    private void onHover(final String hoverActionName, final String hoverActionData) {
        final MessagePart latest = this.latest();
        latest.hoverActionName = hoverActionName;
        latest.hoverActionData = hoverActionData;
    }
    
    static class MessagePart
    {
        public String clickActionName;
        public String clickActionData;
        public String hoverActionName;
        public String hoverActionData;
        public final String text;
        
        public MessagePart(final String text) {
            this.clickActionName = null;
            this.clickActionData = null;
            this.hoverActionName = null;
            this.hoverActionData = null;
            this.text = text;
        }
        
        public JsonWriter writeJson(final JsonWriter jsonWriter) {
            jsonWriter.beginObject().name("text").value(this.text);
            if (this.clickActionName != null && this.clickActionData != null) {
                jsonWriter.name("clickEvent").beginObject().name("action").value(this.clickActionName).name("value").value(this.clickActionData).endObject();
            }
            if (this.hoverActionName != null && this.hoverActionData != null) {
                jsonWriter.name("hoverEvent").beginObject().name("action").value(this.hoverActionName).name("value").value(this.hoverActionData).endObject();
            }
            return jsonWriter.endObject();
        }
    }
}
