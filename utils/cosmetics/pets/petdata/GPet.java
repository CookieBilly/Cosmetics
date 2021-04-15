

package ws.billy.CookieGadgets.utils.cosmetics.pets.petdata;

import java.sql.Timestamp;
import org.apache.commons.lang.Validate;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;

public class GPet
{
    private PetType type;
    private String petName;
    private PetLevel level;
    private PetAttribute attribute;
    
    public GPet(final PetType type, final String petName, final PetLevel level, final PetAttribute attribute) {
        this.type = type;
        this.petName = petName;
        this.level = level;
        this.attribute = attribute;
    }
    
    public GPet(final String s) {
        final String[] split = s.split(", ");
        this.type = PetType.valueOf(split[0]);
        this.petName = split[1];
        this.level = new PetLevel(s);
        this.attribute = new PetAttribute(s);
        if (this.type == null || this.petName == null || this.level == null || this.attribute == null) {
            throw new NullPointerException();
        }
    }
    
    public PetType getType() {
        return this.type;
    }
    
    public String getPetName() {
        return ChatUtil.format(this.petName);
    }
    
    public String getPetNameStripColor() {
        return ChatUtil.stripColor(this.petName);
    }
    
    public void setPetName(final String s) {
        Validate.notNull((Object)s);
        this.petName = ChatUtil.format(s);
    }
    
    public PetLevel getPetLevel() {
        return this.level;
    }
    
    public PetAttribute getPetAttribute() {
        return this.attribute;
    }
    
    public static GPet createNewPetData(final PetType petType, final String s) {
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new GPet(petType, s, new PetLevel(1, 0), new PetAttribute(0, timestamp, 0, timestamp, 0, timestamp));
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.type.getName()) + ", " + this.petName + ", " + this.level.toString() + ", " + this.attribute.toString();
    }
}
