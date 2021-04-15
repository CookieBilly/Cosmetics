

package ws.billy.CookieGadgets.utils.cosmetics.pets.petdata;

public class PetLevel
{
    private int level;
    private int experience;
    private int oldLevel;
    private static final int[] EXPERIENCE_LEVELS;
    
    static {
        EXPERIENCE_LEVELS = new int[] { 200, 210, 230, 250, 280, 310, 350, 390, 450, 500, 570, 640, 710, 800, 880, 980, 1080, 1190, 1300, 1420, 1540, 1670, 1810, 1950, 2100, 2260, 2420, 2580, 2760, 2940, 3120, 3310, 3510, 3710, 3920, 4140, 4360, 4590, 4820, 5060, 5310, 5560, 5820, 6090, 6360, 6630, 6920, 7210, 7500, 7800, 8110, 8420, 8740, 9070, 9400, 9740, 10080, 10430, 10780, 11150, 11510, 11890, 12270, 12650, 13050, 13440, 13850, 14260, 14680, 15100, 15530, 15960, 16400, 16850, 17300, 17760, 18230, 18700, 19180, 19660, 20150, 20640, 21150, 21650, 22170, 22690, 23210, 23750, 24280, 24830, 25380, 25930, 26500, 27070, 27640, 28220, 28810, 29400, 30000 };
    }
    
    public PetLevel(final int n, final int experience) {
        this.level = n;
        this.experience = experience;
        this.oldLevel = n;
        this.checkLevelStatus();
    }
    
    public PetLevel(final String s) {
        final String[] split = s.split(", ");
        this.level = Integer.valueOf(split[2]);
        this.experience = Integer.valueOf(split[3]);
        this.oldLevel = this.level;
        this.checkLevelStatus();
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public void levelUp() {
        if (this.level >= 100) {
            return;
        }
        ++this.level;
    }
    
    public boolean levelUpRecently() {
        if (this.oldLevel != this.level) {
            this.oldLevel = this.level;
            return true;
        }
        return false;
    }
    
    public String getFormattedLevel() {
        if (this.level < 1 || this.level >= 100) {
            return String.valueOf(this.level);
        }
        String str = String.valueOf(this.level);
        for (int i = 3; i >= str.length(); str = "0" + str, --i) {}
        return str;
    }
    
    public int getExperience() {
        return this.experience;
    }
    
    public void addExperience(final int n) {
        final int experience = this.experience + n;
        final int levelFullExperience = this.getLevelFullExperience();
        if (experience >= levelFullExperience) {
            this.experience = experience - levelFullExperience;
            this.levelUp();
            this.checkLevelStatus();
            return;
        }
        this.experience = experience;
    }
    
    public int getLevelFullExperience() {
        if (this.level < 1) {
            return 0;
        }
        return PetLevel.EXPERIENCE_LEVELS[Math.min(this.level - 1, 98)];
    }
    
    public int getLevelProgressLeft() {
        return this.getLevelFullExperience() - this.experience;
    }
    
    public void checkLevelStatus() {
        if (this.level < 1 || this.level >= 100) {
            return;
        }
        final int levelFullExperience = this.getLevelFullExperience();
        if (this.experience >= levelFullExperience) {
            this.experience -= levelFullExperience;
            this.levelUp();
            this.checkLevelStatus();
        }
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.level) + ", " + this.experience;
    }
}
