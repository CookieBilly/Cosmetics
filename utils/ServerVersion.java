

package ws.billy.CookieGadgets.utils;

public enum ServerVersion
{
    v1_8_R1("v1_8_R1", 0, "1.8", "1.8-R1", 8, new String[] { "1.8" }), 
    v1_8_R2("v1_8_R2", 1, "1.8", "1.8-R2", 8, new String[] { "1.8.3" }), 
    v1_8_R3("v1_8_R3", 2, "1.8", "1.8-R3", 8, new String[] { "1.8.4", "1.8.5", "1.8.6", "1.8.7", "1.8.8" }), 
    v1_9_R1("v1_9_R1", 3, "1.9", "1.9-R1", 9, new String[] { "1.9", "1.9.2" }), 
    v1_9_R2("v1_9_R2", 4, "1.9", "1.9-R2", 9, new String[] { "1.9.4" }), 
    v1_10_R1("v1_10_R1", 5, "1.10", "1.10-R1", 10, new String[] { "1.10", "1.10.2" }), 
    v1_11_R1("v1_11_R1", 6, "1.11", "1.11-R1", 11, new String[] { "1.11", "1.11.1", "1.11.2" }), 
    v1_12_R1("v1_12_R1", 7, "1.12", "1.12-R1", 12, new String[] { "1.12", "1.12.1", "1.12.2" }), 
    v1_13_R1("v1_13_R1", 8, "1.13", "1.13-R1", 13, new String[] { "1.13" }), 
    v1_13_R2("v1_13_R2", 9, "1.13", "1.13-R2", 13, new String[] { "1.13.1", "1.13.2" }), 
    v1_14_R1("v1_14_R1", 10, "1.14", "1.14-R1", 14, new String[] { "1.14", "1.14.1", "1.14.2", "1.14.3", "1.14.4" }), 
    v1_15_R1("v1_15_R1", 11, "1.15", "1.15-R1", 15, new String[] { "1.15", "1.15.1", "1.15.2" }), 
    v1_16_R1("v1_16_R1", 12, "1.16", "1.16-R1", 16, new String[] { "1.16.1" }), 
    v1_16_R2("v1_16_R2", 13, "1.16", "1.16-R2", 16, new String[] { "1.16.2", "1.16.3" }), 
    v1_16_R3("v1_16_R3", 14, "1.16", "1.16-R3", 16, new String[] { "1.16.4" });
    
    private String name;
    private String detailName;
    private int currentVersionNumber;
    private String[] spigotReleases;
    private static ServerVersion serverVersion;
    
    static {
        ServerVersion.serverVersion = null;
    }
    
    private ServerVersion(final String name, final int ordinal, final String name2, final String detailName, final int currentVersionNumber, final String[] spigotReleases) {
        this.name = name2;
        this.detailName = detailName;
        this.currentVersionNumber = currentVersionNumber;
        this.spigotReleases = spigotReleases;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDetailName() {
        return this.detailName;
    }
    
    public int getCurrentVersionNumber() {
        return this.currentVersionNumber;
    }
    
    public String[] getSpigotReleases() {
        return this.spigotReleases;
    }
    
    public static ServerVersion getServerVersion() {
        return ServerVersion.serverVersion;
    }
    
    public static void setServerVersion(final ServerVersion serverVersion) {
        ServerVersion.serverVersion = serverVersion;
    }
    
    public static ServerVersion valueOfSpigotRelease(final String anotherString) {
        ServerVersion[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final ServerVersion serverVersion = values[i];
            String[] spigotReleases;
            for (int length2 = (spigotReleases = serverVersion.getSpigotReleases()).length, j = 0; j < length2; ++j) {
                if (spigotReleases[j].equalsIgnoreCase(anotherString)) {
                    return serverVersion;
                }
            }
        }
        return null;
    }
}
