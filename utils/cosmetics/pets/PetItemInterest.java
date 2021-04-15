

package ws.billy.CookieGadgets.utils.cosmetics.pets;

import ws.billy.CookieGadgets.utils.MessageType;

public enum PetItemInterest
{
    DISLIKE("DISLIKE", 0, "Dislike", MessageType.ATE_FOOD_AND_DONT_LIKE_IT, MessageType.DRANK_DRINK_AND_DONT_LIKE_IT, MessageType.PLAYED_TOY_AND_DONT_LIKE_IT), 
    LIKE("LIKE", 1, "Like", MessageType.ATE_FOOD_AND_LIKE_IT, MessageType.DRANK_DRINK_AND_LIKE_IT, MessageType.PLAYED_TOY_AND_LIKE_IT), 
    REALLY_LIKE("REALLY_LIKE", 2, "Really Like", MessageType.ATE_FOOD_AND_REALLY_LIKE_IT, MessageType.DRANK_DRINK_AND_REALLY_LIKE_IT, MessageType.PLAYED_TOY_AND_REALLY_LIKE_IT), 
    FAVOURITE("FAVOURITE", 3, "Favourite", MessageType.ATE_FAVOURITE_FOOD, MessageType.DRANK_FAVOURITE_DRINK, MessageType.PLAYED_FAVOURITE_TOY);
    
    private String name;
    private MessageType ateFoodMessage;
    private MessageType drankDrinkMessage;
    private MessageType playedToyMessage;
    
    private PetItemInterest(final String name, final int ordinal, final String name2, final MessageType ateFoodMessage, final MessageType drankDrinkMessage, final MessageType playedToyMessage) {
        this.name = name2;
        this.ateFoodMessage = ateFoodMessage;
        this.drankDrinkMessage = drankDrinkMessage;
        this.playedToyMessage = playedToyMessage;
    }
    
    public String getName() {
        return this.name;
    }
    
    public MessageType getAteFoodMessage() {
        return this.ateFoodMessage;
    }
    
    public MessageType getDrankDrinkMessage() {
        return this.drankDrinkMessage;
    }
    
    public MessageType getPlayedToyMessage() {
        return this.playedToyMessage;
    }
    
    public MessageType getMessageByType(final PetItems.PetItemType petItemType) {
        if (petItemType == PetItems.PetItemType.FOOD) {
            return this.ateFoodMessage;
        }
        if (petItemType == PetItems.PetItemType.DRINK) {
            return this.drankDrinkMessage;
        }
        if (petItemType == PetItems.PetItemType.TOY) {
            return this.playedToyMessage;
        }
        return null;
    }
}
