

package ws.billy.CookieGadgets.utils.cosmetics.pets;

import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.PetLevel;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import java.util.ArrayList;
import java.util.List;
import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.GPet;
import org.bukkit.entity.Player;

public class PetMessages
{
    public static List<String> getLore(final Player player, final GPet gPet, final List<String> list) {
        if (gPet == null) {
            return list;
        }
        final ArrayList<String> list2 = new ArrayList<String>();
        for (final String s : list) {
            String e;
            if (CookieGadgets.getPetData().isEnabled()) {
                e = ChatUtil.format(replaceWithPlaceHolders(player, gPet, s));
            }
            else {
                e = s.replace("{PET_NAME}", getPetName(player, gPet));
            }
            list2.add(e);
        }
        return list2;
    }
    
    public static String getLevelUpMessage(String s, final Player player, final GPet gPet, final int i) {
        s = s.replace("{LEVEL}", CookieGadgets.getPetData().getLevel().replace("{LEVEL}", gPet.getPetLevel().getFormattedLevel()));
        s = s.replace("{PET_NAME}", gPet.getPetName().replace("{PLAYER}", player.getName()));
        s = s.replace("{EXP}", String.valueOf(i));
        return s;
    }
    
    private static String replaceWithPlaceHolders(final Player player, final GPet gPet, String s) {
        s = s.replace("{LEVEL}", CookieGadgets.getPetData().getLevel().replace("{LEVEL}", gPet.getPetLevel().getFormattedLevel()));
        s = s.replace("{EXP}", (gPet.getPetLevel().getLevel() >= 100) ? CookieGadgets.getPetData().getMaxEXP().replace("{EXP_BLOCKS}", EXPBlocksBar(gPet.getPetLevel())) : CookieGadgets.getPetData().getEXP().replace("{EXP_BLOCKS}", EXPBlocksBar(gPet.getPetLevel())).replace("{OBTAINED_EXP}", String.valueOf(gPet.getPetLevel().getExperience())).replace("{MAX_EXP}", String.valueOf(gPet.getPetLevel().getLevelFullExperience())));
        s = s.replace("{PET_NAME}", getPetName(player, gPet));
        s = s.replace("{HAPPINESS}", gPet.getPetAttribute().getHappiness().getDisplayName());
        s = s.replace("{HUNGER}", CookieGadgets.getPetData().getAttributePercentageBar().replace("{REMAIN}", gPet.getPetAttribute().getHungerWithColorCode()));
        s = s.replace("{THIRST}", CookieGadgets.getPetData().getAttributePercentageBar().replace("{REMAIN}", gPet.getPetAttribute().getThirstWithColorCode()));
        s = s.replace("{EXERCISE}", CookieGadgets.getPetData().getAttributePercentageBar().replace("{REMAIN}", gPet.getPetAttribute().getExerciseWithColorCode()));
        return s;
    }
    
    private static String getPetName(final Player player, final GPet gPet) {
        if (gPet.getPetName().equals(gPet.getType().getDefaultPetName())) {
            return CookieGadgets.getPetData().getRenamePet();
        }
        return gPet.getPetName().replace("{PLAYER}", player.getName());
    }
    
    private static String EXPBlocksBar(final PetLevel petLevel) {
        int n = (int)(petLevel.getLevelProgressLeft() / (float)petLevel.getLevelFullExperience() * CookieGadgets.getPetData().getAmountOfEXPBlocks());
        if (petLevel.getLevel() >= 100) {
            n = 0;
        }
        final StringBuilder sb = new StringBuilder();
        CookieGadgets.getPetData().getRemainEXPBlock();
        for (int i = 0; i < CookieGadgets.getPetData().getAmountOfEXPBlocks(); ++i) {
            String s = CookieGadgets.getPetData().getRemainEXPBlock();
            if (i < CookieGadgets.getPetData().getAmountOfEXPBlocks() - n) {
                s = CookieGadgets.getPetData().getRetainEXPBlock();
            }
            sb.append(ChatUtil.format(s));
        }
        return sb.toString();
    }
}
