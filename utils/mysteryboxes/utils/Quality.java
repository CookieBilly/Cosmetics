

package ws.billy.CookieGadgets.utils.mysteryboxes.utils;

public enum Quality
{
    ONE_STAR("ONE_STAR", 0, "One Star", 1, 4, 1, 1, 1), 
    TWO_STAR("TWO_STAR", 1, "Two Star", 2, 3, 2, 1, 1), 
    THREE_STAR("THREE_STAR", 2, "Three Star", 3, 2, 2, 2, 1), 
    FOUR_STAR("FOUR_STAR", 3, "Four Star", 4, 1, 1, 2, 3), 
    FIVE_STAR("FIVE_STAR", 4, "Five Star", 5, 0, 1, 2, 4);
    
    private String name;
    private int quality;
    private int includedCommonItems;
    private int includedRareItems;
    private int includedEpicItems;
    private int includedLegendaryItems;
    
    private Quality(final String name, final int ordinal, final String name2, final int quality, final int includedCommonItems, final int includedRareItems, final int includedEpicItems, final int includedLegendaryItems) {
        this.name = name2;
        this.quality = quality;
        this.includedCommonItems = includedCommonItems;
        this.includedRareItems = includedRareItems;
        this.includedEpicItems = includedEpicItems;
        this.includedLegendaryItems = includedLegendaryItems;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getQuality() {
        return this.quality;
    }
    
    public int getIncludedCommonItems() {
        return this.includedCommonItems;
    }
    
    public int getIncludedRareItems() {
        return this.includedRareItems;
    }
    
    public int getIncludedEpicItems() {
        return this.includedEpicItems;
    }
    
    public int getIncludedLegendaryItems() {
        return this.includedLegendaryItems;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static Quality valueOfByInteger(final int n) {
        Quality[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final Quality quality = values[i];
            if (quality.getQuality() == n) {
                return quality;
            }
        }
        return null;
    }
}
