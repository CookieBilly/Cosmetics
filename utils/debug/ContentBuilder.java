

package ws.billy.CookieGadgets.utils.debug;

public class ContentBuilder
{
    private StringBuilder sb;
    
    public ContentBuilder() {
        this.sb = new StringBuilder();
    }
    
    public void append(final String s) {
        this.sb.append((s != null) ? s : "");
    }
    
    public void appendLine(final String s) {
        this.sb.append((s != null) ? s : "").append("\n");
    }
    
    @Override
    public String toString() {
        return this.sb.toString();
    }
}
