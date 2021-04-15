

package ws.billy.CookieGadgets.utils.cosmetics.pets.petdata;

import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import java.sql.Timestamp;

public class PetAttribute
{
    private int hunger;
    private Timestamp eatTimestamp;
    private int thirst;
    private Timestamp drinkTimestamp;
    private int exercise;
    private Timestamp exerciseTimestamp;
    
    public PetAttribute(final int hunger, final Timestamp eatTimestamp, final int thirst, final Timestamp drinkTimestamp, final int exercise, final Timestamp exerciseTimestamp) {
        this.hunger = hunger;
        this.eatTimestamp = eatTimestamp;
        this.thirst = thirst;
        this.drinkTimestamp = drinkTimestamp;
        this.exercise = exercise;
        this.exerciseTimestamp = exerciseTimestamp;
    }
    
    public PetAttribute(final String s) {
        final String[] split = s.split(", ");
        this.hunger = Integer.valueOf(split[4]);
        this.eatTimestamp = Timestamp.valueOf(split[5]);
        this.thirst = Integer.valueOf(split[6]);
        this.drinkTimestamp = Timestamp.valueOf(split[7]);
        this.exercise = Integer.valueOf(split[8]);
        this.exerciseTimestamp = Timestamp.valueOf(split[9]);
    }
    
    public EnumPetHappiness getHappiness() {
        final int hunger = this.getHunger();
        final int thirst = this.getThirst();
        final int exercise = this.getExercise();
        if (hunger >= 75 && thirst >= 75 && exercise >= 75) {
            return EnumPetHappiness.SUPER_HAPPY;
        }
        if ((hunger >= 55 && thirst >= 55) || (thirst >= 55 && exercise >= 55) || (hunger >= 55 && exercise >= 55)) {
            return EnumPetHappiness.VERY_HAPPY;
        }
        if ((hunger >= 25 && thirst >= 25) || (thirst >= 25 && exercise >= 25) || (exercise >= 25 && exercise >= 25)) {
            return EnumPetHappiness.HAPPY;
        }
        if (hunger >= 75 || thirst >= 75 || exercise >= 75) {
            return EnumPetHappiness.HAPPY;
        }
        return EnumPetHappiness.OKAY;
    }
    
    public boolean isHappy() {
        final EnumPetHappiness happiness = this.getHappiness();
        return happiness == EnumPetHappiness.HAPPY || happiness == EnumPetHappiness.VERY_HAPPY || happiness == EnumPetHappiness.SUPER_HAPPY;
    }
    
    public static String getColorCodeByHappiness(final int n) {
        if (n >= 75) {
            return EnumPetHappiness.SUPER_HAPPY.getColor();
        }
        if (n >= 55) {
            return EnumPetHappiness.VERY_HAPPY.getColor();
        }
        if (n >= 30) {
            return EnumPetHappiness.HAPPY.getColor();
        }
        return EnumPetHappiness.OKAY.getColor();
    }
    
    public int getHunger() {
        this.updateHungerAttribute();
        if (this.hunger > 100) {
            return this.hunger = 100;
        }
        if (this.hunger < 0) {
            return this.hunger = 0;
        }
        return this.hunger;
    }
    
    public String getHungerWithColorCode() {
        final int hunger = this.getHunger();
        return String.valueOf(getColorCodeByHappiness(hunger)) + String.valueOf(hunger);
    }
    
    public void addHunger(final int n) {
        this.hunger += n;
        this.updateHungerAttribute();
    }
    
    public void setHunger(final int hunger) {
        this.hunger = hunger;
        this.updateHungerAttribute();
    }
    
    public Timestamp getEatTimestamp() {
        return this.eatTimestamp;
    }
    
    private void setEatTimestamp(final Timestamp eatTimestamp) {
        this.eatTimestamp = eatTimestamp;
    }
    
    public void updateHungerAttribute() {
        final double n = (System.currentTimeMillis() - this.eatTimestamp.getTime()) / 300000.0;
        if (n >= 1.0) {
            if (n >= 100.0) {
                this.hunger = 0;
            }
            else {
                this.hunger -= (int)n;
            }
        }
        this.setEatTimestamp(new Timestamp(System.currentTimeMillis() - (long)((n - (int)n) * 300000.0)));
    }
    
    public int getThirst() {
        this.updateThirstAttribute();
        if (this.thirst > 100) {
            return this.thirst = 100;
        }
        if (this.thirst < 0) {
            return this.thirst = 0;
        }
        return this.thirst;
    }
    
    public String getThirstWithColorCode() {
        final int thirst = this.getThirst();
        return String.valueOf(getColorCodeByHappiness(thirst)) + String.valueOf(thirst);
    }
    
    public void addThirst(final int n) {
        this.thirst += n;
        this.updateThirstAttribute();
    }
    
    public void setThirst(final int thirst) {
        this.thirst = thirst;
        this.updateThirstAttribute();
    }
    
    public Timestamp getDrinkTimestamp() {
        return this.drinkTimestamp;
    }
    
    private void setDrinkTimestamp(final Timestamp drinkTimestamp) {
        this.drinkTimestamp = drinkTimestamp;
    }
    
    public void updateThirstAttribute() {
        final double n = (System.currentTimeMillis() - this.drinkTimestamp.getTime()) / 300000.0;
        if (n >= 1.0) {
            if (n >= 100.0) {
                this.thirst = 0;
            }
            else {
                this.thirst -= (int)n;
            }
        }
        this.setDrinkTimestamp(new Timestamp(System.currentTimeMillis() - (long)((n - (int)n) * 300000.0)));
    }
    
    public int getExercise() {
        this.updateExerciseAttribute();
        if (this.exercise > 100) {
            return this.exercise = 100;
        }
        if (this.exercise < 0) {
            return this.exercise = 0;
        }
        return this.exercise;
    }
    
    public String getExerciseWithColorCode() {
        final int exercise = this.getExercise();
        return String.valueOf(getColorCodeByHappiness(exercise)) + String.valueOf(exercise);
    }
    
    public void addExercise(final int n) {
        this.exercise += n;
        this.updateExerciseAttribute();
    }
    
    public void setExercise(final int exercise) {
        this.exercise = exercise;
        this.updateExerciseAttribute();
    }
    
    public Timestamp getExerciseTimestamp() {
        return this.exerciseTimestamp;
    }
    
    private void setExerciseTimestamp(final Timestamp exerciseTimestamp) {
        this.exerciseTimestamp = exerciseTimestamp;
    }
    
    public void updateExerciseAttribute() {
        final double n = (System.currentTimeMillis() - this.exerciseTimestamp.getTime()) / 300000.0;
        if (n >= 1.0) {
            if (n >= 100.0) {
                this.exercise = 0;
            }
            else {
                this.exercise -= (int)n;
            }
        }
        this.setExerciseTimestamp(new Timestamp(System.currentTimeMillis() - (long)((n - (int)n) * 300000.0)));
    }
    
    public int getStat(final PetItems.PetItemType petItemType) {
        if (petItemType == PetItems.PetItemType.FOOD) {
            return this.getHunger();
        }
        if (petItemType == PetItems.PetItemType.DRINK) {
            return this.getThirst();
        }
        if (petItemType == PetItems.PetItemType.TOY) {
            return this.getExercise();
        }
        return 0;
    }
    
    public void addStat(final PetItems.PetItemType petItemType, final int n) {
        if (petItemType == PetItems.PetItemType.FOOD) {
            this.addHunger(n);
        }
        else if (petItemType == PetItems.PetItemType.DRINK) {
            this.addThirst(n);
        }
        else if (petItemType == PetItems.PetItemType.TOY) {
            this.addExercise(n);
        }
    }
    
    public void setStat(final PetItems.PetItemType petItemType, final int exercise) {
        if (petItemType == PetItems.PetItemType.FOOD) {
            this.setHunger(exercise);
        }
        else if (petItemType == PetItems.PetItemType.DRINK) {
            this.setThirst(exercise);
        }
        else if (petItemType == PetItems.PetItemType.TOY) {
            this.setExercise(exercise);
        }
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.getHunger()) + ", " + this.eatTimestamp + ", " + this.getThirst() + ", " + this.drinkTimestamp + ", " + this.getExercise() + ", " + this.exerciseTimestamp;
    }
    
    public enum EnumPetHappiness
    {
        OKAY("OKAY", 0, "Okay", "&eOkay", "&e", 0), 
        HAPPY("HAPPY", 1, "Happy", "&2Happy", "&2", 400), 
        VERY_HAPPY("VERY_HAPPY", 2, "Very Happy", "&aVery Happy", "&a", 600), 
        SUPER_HAPPY("SUPER_HAPPY", 3, "Super Happy", "&bSuper Happy", "&b", 800);
        
        private String name;
        private String displayName;
        private String color;
        private int exp;
        
        private EnumPetHappiness(final String name, final int ordinal, final String name2, final String displayName, final String color, final int n) {
            this.name = name2;
            final String string = "Pet-System.Happiness." + this.name.replace(" ", "-");
            if (FileManager.getPetSystemFile().get(String.valueOf(string) + ".DisplayName") == null) {
                this.displayName = displayName;
                FileManager.getPetSystemFile().set(String.valueOf(string) + ".DisplayName", displayName);
            }
            else {
                this.displayName = FileManager.getPetSystemFile().getString(String.valueOf(string) + ".DisplayName");
            }
            if (FileManager.getPetSystemFile().get(String.valueOf(string) + ".Color") == null) {
                this.color = color;
                FileManager.getPetSystemFile().set(String.valueOf(string) + ".Color", color);
            }
            else {
                this.color = FileManager.getPetSystemFile().getString(String.valueOf(string) + ".Color");
            }
            if (this.name != "Okay") {
                if (FileManager.getPetSystemFile().get("Pet-System.Send-Pet-On-Mission.Gain-EXP." + this.name.replace(" ", "-")) == null) {
                    this.exp = n;
                    FileManager.getPetSystemFile().set("Pet-System.Send-Pet-On-Mission.Gain-EXP." + this.name.replace(" ", "-"), n);
                }
                else {
                    this.exp = FileManager.getPetSystemFile().getInt("Pet-System.Send-Pet-On-Mission.Gain-EXP." + this.name.replace(" ", "-"));
                }
            }
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getDisplayName() {
            return ChatUtil.format(this.displayName);
        }
        
        public String getColor() {
            return ChatUtil.format(this.color);
        }
        
        public int getGainEXPFromMission() {
            return this.exp;
        }
    }
}
