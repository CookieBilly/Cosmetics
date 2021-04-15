

package ws.billy.CookieGadgets.utils.cosmetics.gadgets;

import org.bukkit.Sound;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.GMaterial;

public enum JukeboxTrack
{
    MUSIC_DISC_13("MUSIC_DISC_13", 0, "13", new GMaterial(EnumMaterial.MUSIC_DISC_13), SoundEffect.MUSIC_DISC_13, 1), 
    MUSIC_DISC_CAT("MUSIC_DISC_CAT", 1, "Cat", new GMaterial(EnumMaterial.MUSIC_DISC_CAT), SoundEffect.MUSIC_DISC_CAT, 2), 
    MUSIC_DISC_BLOCKS("MUSIC_DISC_BLOCKS", 2, "Blocks", new GMaterial(EnumMaterial.MUSIC_DISC_BLOCKS), SoundEffect.MUSIC_DISC_BLOCKS, 3), 
    MUSIC_DISC_CHIRP("MUSIC_DISC_CHIRP", 3, "Chirp", new GMaterial(EnumMaterial.MUSIC_DISC_CHIRP), SoundEffect.MUSIC_DISC_CHIRP, 4), 
    MUSIC_DISC_FAR("MUSIC_DISC_FAR", 4, "Far", new GMaterial(EnumMaterial.MUSIC_DISC_FAR), SoundEffect.MUSIC_DISC_FAR, 5), 
    MUSIC_DISC_MALL("MUSIC_DISC_MALL", 5, "Mall", new GMaterial(EnumMaterial.MUSIC_DISC_MALL), SoundEffect.MUSIC_DISC_MALL, 6), 
    MUSIC_DISC_MELLOHI("MUSIC_DISC_MELLOHI", 6, "Mellohi", new GMaterial(EnumMaterial.MUSIC_DISC_MELLOHI), SoundEffect.MUSIC_DISC_MELLOHI, 7), 
    MUSIC_DISC_STAL("MUSIC_DISC_STAL", 7, "Stal", new GMaterial(EnumMaterial.MUSIC_DISC_STAL), SoundEffect.MUSIC_DISC_STAL, 11), 
    MUSIC_DISC_STRAD("MUSIC_DISC_STRAD", 8, "Strad", new GMaterial(EnumMaterial.MUSIC_DISC_STRAD), SoundEffect.MUSIC_DISC_STRAD, 12), 
    MUSIC_DISC_WARD("MUSIC_DISC_WARD", 9, "Ward", new GMaterial(EnumMaterial.MUSIC_DISC_WARD), SoundEffect.MUSIC_DISC_WARD, 13), 
    MUSIC_DISC_11("MUSIC_DISC_11", 10, "11", new GMaterial(EnumMaterial.MUSIC_DISC_11), SoundEffect.MUSIC_DISC_11, 14), 
    MUSIC_DISC_WAIT("MUSIC_DISC_WAIT", 11, "Wait", new GMaterial(EnumMaterial.MUSIC_DISC_WAIT), SoundEffect.MUSIC_DISC_WAIT, 15);
    
    private String displayName;
    private GMaterial material;
    private SoundEffect sound;
    private int slot;
    
    private JukeboxTrack(final String name, final int ordinal, final String displayName, final GMaterial material, final SoundEffect sound, final int slot) {
        this.displayName = displayName;
        this.material = material;
        this.sound = sound;
        this.slot = slot;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public GMaterial getMaterial() {
        return this.material;
    }
    
    public Sound getSoundTrack() {
        return this.sound.getSound();
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    public static JukeboxTrack getFromName(final String anotherString) {
        JukeboxTrack[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final JukeboxTrack jukeboxTrack = values[i];
            if (jukeboxTrack.getDisplayName().equalsIgnoreCase(anotherString)) {
                return jukeboxTrack;
            }
        }
        return null;
    }
}
