

package ws.billy.CookieGadgets.utils.mysteryboxes;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.MysteryBoxJSONMessages;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.configuration.FileManager;

public class MysteryBoxesReward
{
    private static int rewardTimePeriod;
    private static FileManager mysteryBoxes;
    private static final int chancesGetMysteryBox;
    private static final int oneStar;
    private static final int twoStar;
    private static final int threeStar;
    private static final int fourStar;
    private static final int fiveStar;
    private static final int expiryDateInDays;
    private static final String oneStarMessage;
    private static final String twoStarMessage;
    private static final String threeStarMessage;
    private static final String fourStarMessage;
    private static final String fiveStarMessage;
    
    static {
        MysteryBoxesReward.rewardTimePeriod = 0;
        MysteryBoxesReward.mysteryBoxes = FileManager.getMysteryBoxesFile();
        chancesGetMysteryBox = MysteryBoxesReward.mysteryBoxes.getInt("Mystery-Boxes.Mystery-Boxes-Reward.Chance-To-Get-Mystery-Box");
        oneStar = MysteryBoxesReward.mysteryBoxes.getInt("Mystery-Boxes.Mystery-Boxes-Reward.Chance.One-Star");
        twoStar = MysteryBoxesReward.mysteryBoxes.getInt("Mystery-Boxes.Mystery-Boxes-Reward.Chance.Two-Star");
        threeStar = MysteryBoxesReward.mysteryBoxes.getInt("Mystery-Boxes.Mystery-Boxes-Reward.Chance.Three-Star");
        fourStar = MysteryBoxesReward.mysteryBoxes.getInt("Mystery-Boxes.Mystery-Boxes-Reward.Chance.Four-Star");
        fiveStar = MysteryBoxesReward.mysteryBoxes.getInt("Mystery-Boxes.Mystery-Boxes-Reward.Chance.Five-Star");
        expiryDateInDays = MysteryBoxesReward.mysteryBoxes.getInt("Mystery-Boxes.Mystery-Boxes-Reward.Expiry-Date-In-Days");
        oneStarMessage = MysteryBoxesReward.mysteryBoxes.getString("Mystery-Boxes.Mystery-Boxes-Reward.Message.One-Star");
        twoStarMessage = MysteryBoxesReward.mysteryBoxes.getString("Mystery-Boxes.Mystery-Boxes-Reward.Message.Two-Star");
        threeStarMessage = MysteryBoxesReward.mysteryBoxes.getString("Mystery-Boxes.Mystery-Boxes-Reward.Message.Three-Star");
        fourStarMessage = MysteryBoxesReward.mysteryBoxes.getString("Mystery-Boxes.Mystery-Boxes-Reward.Message.Four-Star");
        fiveStarMessage = MysteryBoxesReward.mysteryBoxes.getString("Mystery-Boxes.Mystery-Boxes-Reward.Message.Five-Star");
    }
    
    public static void giveRewardIfHaveChance(final Player player) {
        if (MysteryBoxesReward.chancesGetMysteryBox >= CookieGadgets.random().nextInt(100) || MysteryBoxesReward.chancesGetMysteryBox > 100) {
            final int nextInt = CookieGadgets.random().nextInt(MysteryBoxesReward.oneStar + MysteryBoxesReward.twoStar + MysteryBoxesReward.threeStar + MysteryBoxesReward.fourStar + MysteryBoxesReward.fiveStar);
            if (nextInt <= MysteryBoxesReward.oneStar) {
                CookieGadgets.getPlayerManager(player).giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_1, System.currentTimeMillis() + MysteryBoxesReward.expiryDateInDays * 86400 * 1000L, true, null, 1);
                CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(MysteryBoxesReward.oneStarMessage)).showText(MysteryBoxJSONMessages.FOUND_ONE_STAR_BOX.getMessage()).send(player);
                broadcast(player, 1, "One-Star");
            }
            else if (nextInt > MysteryBoxesReward.oneStar && nextInt <= MysteryBoxesReward.oneStar + MysteryBoxesReward.twoStar) {
                CookieGadgets.getPlayerManager(player).giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_2, System.currentTimeMillis() + MysteryBoxesReward.expiryDateInDays * 86400 * 1000L, true, null, 1);
                CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(MysteryBoxesReward.twoStarMessage)).showText(MysteryBoxJSONMessages.FOUND_TWO_STAR_BOX.getMessage()).send(player);
                broadcast(player, 2, "Two-Star");
            }
            else if (nextInt > MysteryBoxesReward.oneStar + MysteryBoxesReward.twoStar && nextInt <= MysteryBoxesReward.oneStar + MysteryBoxesReward.twoStar + MysteryBoxesReward.threeStar) {
                CookieGadgets.getPlayerManager(player).giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_3, System.currentTimeMillis() + MysteryBoxesReward.expiryDateInDays * 86400 * 1000L, true, null, 1);
                CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(MysteryBoxesReward.threeStarMessage)).showText(MysteryBoxJSONMessages.FOUND_THREE_STAR_BOX.getMessage()).send(player);
                broadcast(player, 3, "Three-Star");
            }
            else if (nextInt > MysteryBoxesReward.oneStar + MysteryBoxesReward.twoStar + MysteryBoxesReward.threeStar && nextInt <= MysteryBoxesReward.oneStar + MysteryBoxesReward.twoStar + MysteryBoxesReward.threeStar + MysteryBoxesReward.fourStar) {
                CookieGadgets.getPlayerManager(player).giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_4, System.currentTimeMillis() + MysteryBoxesReward.expiryDateInDays * 86400 * 1000L, true, null, 1);
                CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(MysteryBoxesReward.fourStarMessage)).showText(MysteryBoxJSONMessages.FOUND_FOUR_STAR_BOX.getMessage()).send(player);
                broadcast(player, 4, "Four-Star");
            }
            else if (nextInt > MysteryBoxesReward.oneStar + MysteryBoxesReward.twoStar + MysteryBoxesReward.threeStar + MysteryBoxesReward.fourStar && nextInt <= MysteryBoxesReward.oneStar + MysteryBoxesReward.twoStar + MysteryBoxesReward.threeStar + MysteryBoxesReward.fourStar + MysteryBoxesReward.fiveStar) {
                CookieGadgets.getPlayerManager(player).giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_5, System.currentTimeMillis() + MysteryBoxesReward.expiryDateInDays * 86400 * 1000L, true, null, 1);
                CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(MysteryBoxesReward.fiveStarMessage)).showText(MysteryBoxJSONMessages.FOUND_FIVE_STAR_BOX.getMessage()).send(player);
                broadcast(player, 5, "Five-Star");
            }
        }
    }
    
    public static int getRewardTimePeriod() {
        if (MysteryBoxesReward.rewardTimePeriod == 0) {
            return MysteryBoxesReward.rewardTimePeriod = MysteryBoxesReward.mysteryBoxes.getInt("Mystery-Boxes.Mystery-Boxes-Reward.Play-Time.Hours") * 60 * 60 + MysteryBoxesReward.mysteryBoxes.getInt("Mystery-Boxes.Mystery-Boxes-Reward.Play-Time.Minutes") * 60 + MysteryBoxesReward.mysteryBoxes.getInt("Mystery-Boxes.Mystery-Boxes-Reward.Play-Time.Seconds");
        }
        return MysteryBoxesReward.rewardTimePeriod;
    }
    
    private static void broadcast(final Player player, final int n, final String s) {
        if (CookieGadgets.getCookieGadgetsData().isMysteryBoxesRewardEnabled() && MysteryBoxesReward.mysteryBoxes.getBoolean("Mystery-Boxes.Broadcast.Found-Mystery-Box." + s + ".Enabled")) {
            for (final Player player2 : Bukkit.getOnlinePlayers()) {
                if (player2.isOnline() && WorldUtils.isWorldEnabled(player2.getWorld()) && player2 != player && player2.canSee(player)) {
                    CookieGadgets.getNMSManager().newJSONMessage(ChatUtil.format(MysteryBoxesReward.mysteryBoxes.getString("Mystery-Boxes.Broadcast.Found-Mystery-Box." + s + ".Message").replace("{PLAYER}", player.getDisplayName()))).showText(MysteryBoxJSONMessages.valueOfBoxFound(n).getMessage()).send(player2);
                }
            }
        }
    }
}
