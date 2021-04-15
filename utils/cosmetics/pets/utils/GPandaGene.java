

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

public enum GPandaGene
{
    NORMAL("NORMAL", 0, "normal", 0, false), 
    LAZY("LAZY", 1, "lazy", 1, false), 
    WORRIED("WORRIED", 2, "worried", 2, false), 
    PLAYFUL("PLAYFUL", 3, "playful", 3, false), 
    BROWN("BROWN", 4, "brown", 4, true), 
    WEAK("WEAK", 5, "weak", 5, true), 
    AGGRESSIVE("AGGRESSIVE", 6, "aggressive", 6, false);
    
    private String name;
    private int id;
    private boolean recessive;
    
    private GPandaGene(final String name, final int ordinal, final String name2, final int id, final boolean recessive) {
        this.id = id;
        this.name = name2;
        this.recessive = recessive;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public boolean isRecessive() {
        return this.recessive;
    }
    
    public static GPandaGene valueOfById(final int n) {
        GPandaGene[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GPandaGene gPandaGene = values[i];
            if (gPandaGene.getId() == n) {
                return gPandaGene;
            }
        }
        return GPandaGene.NORMAL;
    }
    
    public static GPandaGene valueOfByName(final String anObject) {
        GPandaGene[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GPandaGene gPandaGene = values[i];
            if (gPandaGene.getName().equals(anObject)) {
                return gPandaGene;
            }
        }
        return GPandaGene.NORMAL;
    }
}
