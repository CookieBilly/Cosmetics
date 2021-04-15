

package ws.billy.CookieGadgets.utils.cosmetics.pets.petdata;

import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.SoundEffect;
import java.util.List;

public class PetData
{
    private static boolean isEnabled;
    private static String level;
    private static String renamePet;
    private static String exp;
    private static String maxExp;
    private static String remainEXPBlock;
    private static String retainEXPBlock;
    private static int amountOfEXPBlocks;
    private static String attributePercentageBar;
    private static boolean isPetNameCommandEnabled;
    private static String petNameCommandDescription;
    private static boolean isPetNameBlacklistEnabled;
    private static List<String> petNameBlacklist;
    private static boolean petSilent;
    private static int cooldownMinutesForPetMission;
    private static List<String> readyToSendPetMissionLore;
    private static List<String> sendPetMissionIsOnCooldown;
    private static boolean isPlaySoundEnabledWhenPetGainEXP;
    private static SoundEffect petGainEXPSound;
    private static List<String> summonPetLore;
    private static List<String> despawnPetLore;
    private static List<String> provideCareLore;
    
    static {
        PetData.isPetNameCommandEnabled = true;
        PetData.petNameCommandDescription = "Name your pet.";
        PetData.isPetNameBlacklistEnabled = true;
        PetData.petSilent = false;
        PetData.isPlaySoundEnabledWhenPetGainEXP = true;
    }
    
    public PetData() {
        final FileManager petSystemFile = FileManager.getPetSystemFile();
        PetData.isEnabled = petSystemFile.getBoolean("Pet-System.Enabled");
        PetData.level = petSystemFile.getString("Pet-System.Level");
        PetData.renamePet = petSystemFile.getString("Pet-System.Rename-Pet");
        PetData.exp = petSystemFile.getString("Pet-System.EXP");
        PetData.maxExp = petSystemFile.getString("Pet-System.Max-EXP");
        PetData.remainEXPBlock = petSystemFile.getString("Pet-System.EXP-Block.Remain");
        PetData.retainEXPBlock = petSystemFile.getString("Pet-System.EXP-Block.Retain");
        PetData.amountOfEXPBlocks = petSystemFile.getInt("Pet-System.EXP-Block.Amount-Of-Blocks");
        PetData.attributePercentageBar = petSystemFile.getString("Pet-System.Percentage-Bar");
        PetData.isPetNameCommandEnabled = petSystemFile.getBoolean("Pet-System.Pet-Name-Command.Enabled");
        PetData.petNameCommandDescription = petSystemFile.getString("Pet-System.Pet-Name-Command.Description");
        PetData.isPetNameBlacklistEnabled = petSystemFile.getBoolean("Pet-System.Pet-Name-Blacklist.Enabled");
        PetData.petNameBlacklist = petSystemFile.getStringList("Pet-System.Pet-Name-Blacklist.Blacklist");
        PetData.petSilent = petSystemFile.getBoolean("Pet-System.General-Settings.Pet-Silent");
        PetData.cooldownMinutesForPetMission = petSystemFile.getInt("Pet-System.Send-Pet-On-Mission.Cooldown-Minutes");
        PetData.isPlaySoundEnabledWhenPetGainEXP = petSystemFile.getBoolean("Pet-System.Send-Pet-On-Mission.Play-Sound.Enabled");
        PetData.petGainEXPSound = SoundEffect.valueOf(petSystemFile.getString("Pet-System.Send-Pet-On-Mission.Play-Sound.Sound"));
        PetData.readyToSendPetMissionLore = petSystemFile.getStringList("Pet-System.Send-Pet-On-Mission.Lores.Ready-To-Send-Pet-On-Mission");
        PetData.sendPetMissionIsOnCooldown = petSystemFile.getStringList("Pet-System.Send-Pet-On-Mission.Lores.Send-Pet-On-Mission-On-Cooldown");
        PetData.summonPetLore = petSystemFile.getStringList("Pet-System.Lores.Summon-Pet");
        PetData.despawnPetLore = petSystemFile.getStringList("Pet-System.Lores.Despawn-Pet");
        PetData.provideCareLore = petSystemFile.getStringList("Pet-System.Lores.Provide-Care");
    }
    
    public boolean isEnabled() {
        return PetData.isEnabled;
    }
    
    public String getLevel() {
        return ChatUtil.format(PetData.level);
    }
    
    public String getRenamePet() {
        return ChatUtil.format(PetData.renamePet);
    }
    
    public String getEXP() {
        return PetData.exp;
    }
    
    public String getMaxEXP() {
        return PetData.maxExp;
    }
    
    public String getRemainEXPBlock() {
        return ChatUtil.format(PetData.remainEXPBlock);
    }
    
    public String getRetainEXPBlock() {
        return ChatUtil.format(PetData.retainEXPBlock);
    }
    
    public int getAmountOfEXPBlocks() {
        return PetData.amountOfEXPBlocks;
    }
    
    public String getAttributePercentageBar() {
        return ChatUtil.format(PetData.attributePercentageBar);
    }
    
    public boolean isPetNameCommandEnabled() {
        return PetData.isPetNameCommandEnabled;
    }
    
    public String getPetNameCommandDescription() {
        return PetData.petNameCommandDescription;
    }
    
    public boolean isPetNameBlacklistEnabled() {
        return PetData.isPetNameBlacklistEnabled;
    }
    
    public List<String> getPetNameBlacklist() {
        return PetData.petNameBlacklist;
    }
    
    public boolean isPetSilent() {
        return PetData.petSilent;
    }
    
    public int getCooldownMinutesForPetMission() {
        return PetData.cooldownMinutesForPetMission;
    }
    
    public boolean isPlaySoundWhenPetGainEXP() {
        return PetData.isPlaySoundEnabledWhenPetGainEXP;
    }
    
    public SoundEffect getPetGainEXPSound() {
        return PetData.petGainEXPSound;
    }
    
    public List<String> getReadyToSendPetMissionLore() {
        return PetData.readyToSendPetMissionLore;
    }
    
    public List<String> getPetMissionIsOnCooldownLore() {
        return PetData.sendPetMissionIsOnCooldown;
    }
    
    public List<String> getSummonPetLore() {
        return PetData.summonPetLore;
    }
    
    public List<String> getDespawnPetLore() {
        return PetData.despawnPetLore;
    }
    
    public List<String> getProvideCareLore() {
        return PetData.provideCareLore;
    }
}
