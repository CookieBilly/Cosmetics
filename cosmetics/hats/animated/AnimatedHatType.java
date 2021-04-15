

package ws.billy.CookieGadgets.cosmetics.hats.animated;

import java.util.Collections;
import java.util.function.Function;
import java.util.Comparator;
import ws.billy.CookieGadgets.utils.EnumInventorySort;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.EnumEquipType;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.CategoryManager;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.GMaterial;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticType;

public class AnimatedHatType extends CosmeticType
{
    private static final List<AnimatedHatType> ENABLED;
    private static final List<AnimatedHatType> VALUES;
    public static final AnimatedHatType SIREN;
    public static final AnimatedHatType TRAFFIC_LIGHT;
    public static final AnimatedHatType SAMMY_THE_COOKIE;
    public static final AnimatedHatType CHROMA_SLIME;
    public static final AnimatedHatType COLOR_BLOCK;
    public static final AnimatedHatType JOE_THE_PENGUIN;
    public static final AnimatedHatType COMET_THE_REINDEER;
    public static final AnimatedHatType BROKEN_TV;
    private String displayName;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private GMaterial.GMaterialHead headTexture;
    private Long ticksPerFrame;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    private ArrayList<ItemStack> frames;
    
    static {
        ENABLED = new ArrayList<AnimatedHatType>();
        VALUES = new ArrayList<AnimatedHatType>();
        SIREN = new AnimatedHatType("Siren", "&9Siren Animated Hat", "CookieGadgets.animatedhats.siren", 20, Rarity.RARE, null, new GMaterial.GMaterialHead("de1fa6b8449fc4d6e3edce48b8416038d1637ebf4d929e9f89bc2153ddcd690"), 15L);
        TRAFFIC_LIGHT = new AnimatedHatType("Traffic Light", "&9Traffic Light Animated Hat", "CookieGadgets.animatedhats.trafficlight", 22, Rarity.RARE, null, new GMaterial.GMaterialHead("ffdc4c8895e3f6b3ac6a9b1cd55de3a29bbc8c785e7fbdbd9322d8c2231"), 15L);
        SAMMY_THE_COOKIE = new AnimatedHatType("Sammy The Cookie", "&9Sammy The Cookie Animated Hat", "CookieGadgets.animatedhats.sammythecookie", 25, Rarity.RARE, null, new GMaterial.GMaterialHead("3023b1dd91d2b36cade5665c3f7897fcb8de1ae619a4e96185637b19bdcff47"), 8L);
        CHROMA_SLIME = new AnimatedHatType("Chroma Slime", "&9Chroma Slime Animated Hat", "CookieGadgets.animatedhats.chromaslime", 28, Rarity.RARE, null, new GMaterial.GMaterialHead("dad2fadea3e3d71630f4bfd9d457dd5dab7f799651fd37c36f257cf59908ef0d"), 8L);
        COLOR_BLOCK = new AnimatedHatType("Color Block", "&9Color Block Animated Hat", "CookieGadgets.animatedhats.colorblock", 28, Rarity.RARE, null, new GMaterial.GMaterialHead("97c1f1ead4d531caa4a5b0d69edbce29af789a2550e5ddbd23775be05e2df2c4"), 8L);
        JOE_THE_PENGUIN = new AnimatedHatType("Joe the Penguin", "&9Joe the Penguin Animated Hat", "CookieGadgets.animatedhats.joethepenguin", 25, Rarity.RARE, null, new GMaterial.GMaterialHead("f435906a8f2490111025e6e0ca9526ba6630c7e7fc7244de98b7060ce77e2ee8"), 3L);
        COMET_THE_REINDEER = new AnimatedHatType("Comet the Reindeer", "&9Comet the Reindeer Animated Hat", "CookieGadgets.animatedhats.cometthereindeer", 28, Rarity.RARE, null, new GMaterial.GMaterialHead("ebd46b38b21b342caf917ad9ca42afb68388a5591bcc9aded1e8e346e18890"), 3L);
        BROKEN_TV = new AnimatedHatType("Broken TV", "&5Broken TV Animated Hat", "CookieGadgets.animatedhats.brokentv", 35, Rarity.EPIC, null, new GMaterial.GMaterialHead("55b9abe149eabb1264ac791e1bf90dfc89e1b32e9b388e78cf873e91176afbb"), 5L);
        AnimatedHatType.SIREN.addTexture(1, "de1fa6b8449fc4d6e3edce48b8416038d1637ebf4d929e9f89bc2153ddcd690");
        AnimatedHatType.SIREN.addTexture(1, "3834f686997b64fa996c5528e44c7ed54d0f3660f936ddb6da1f3681bd1e1c");
        AnimatedHatType.TRAFFIC_LIGHT.addTexture(1, "ffdc4c8895e3f6b3ac6a9b1cd55de3a29bbc8c785e7fbdbd9322d8c2231");
        AnimatedHatType.TRAFFIC_LIGHT.addTexture(1, "c950de3843e06b33ffc334c154bf9b3988815c7e546362377fe7a07436ab");
        AnimatedHatType.TRAFFIC_LIGHT.addTexture(1, "34d46c3ff88e0dbc51f662193e19eb4a01cd1f40add3a1bb15667099a8bbb2");
        AnimatedHatType.SAMMY_THE_COOKIE.addTexture(1, "3023b1dd91d2b36cade5665c3f7897fcb8de1ae619a4e96185637b19bdcff47");
        AnimatedHatType.SAMMY_THE_COOKIE.addTexture(1, "56e61019132de9d1b06bcacf587fe5fafbe4e9ceaa2ae9ff7c9e2cb2fe749ec");
        AnimatedHatType.SAMMY_THE_COOKIE.addTexture(1, "2586f81751eedcc502a66814fcd9d734fad4d4cf3256c44075215965ed49e6b4");
        AnimatedHatType.SAMMY_THE_COOKIE.addTexture(1, "27b493551c6fb2e75e10ab7c164d14af4e440faf8d51242fce615fef7bbf7f58");
        AnimatedHatType.CHROMA_SLIME.addTexture(1, "dad2fadea3e3d71630f4bfd9d457dd5dab7f799651fd37c36f257cf59908ef0d");
        AnimatedHatType.CHROMA_SLIME.addTexture(1, "78e972ce80afd6176fa37f7156a6f0cbc49b3a28d0be29fe692742edf950d2");
        AnimatedHatType.CHROMA_SLIME.addTexture(1, "72521833a613ffd2ed8d8be5daea145204828867e5de78ab482b698290b4af");
        AnimatedHatType.CHROMA_SLIME.addTexture(1, "dc96ed4ebfa4fedd2c6d7711e7362d0905beef3fcdf7d1aceda5ef0c9a4e62");
        AnimatedHatType.CHROMA_SLIME.addTexture(1, "2166a434ae4165e2a2e29dc28aa78f3fa9a9cdf89a66b3378645f8320eee446");
        AnimatedHatType.CHROMA_SLIME.addTexture(1, "16ad20fc2d579be250d3db659c832da2b478a73a698b7ea10d18c9162e4d9b5");
        AnimatedHatType.CHROMA_SLIME.addTexture(1, "141fd629eaeb6c656e50526e9fed35981113eb29ee157799df976bac7cf9");
        AnimatedHatType.CHROMA_SLIME.addTexture(1, "74d578ff3231c32f43f13d464343574d3153bb5655bb75544b9dfde8a0a6db");
        AnimatedHatType.CHROMA_SLIME.addTexture(1, "f69a60d0c71a81e38a358c62c693d63b863836fe2a0c71478837ff698d08864");
        AnimatedHatType.CHROMA_SLIME.addTexture(1, "20ec2a665fc17f79b96fcdaf442626ec1e15ca8f8f22a781bd6a27a651a29d7e");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "3f46c31d6ee6ea619f72e785232cb048ab270462db0cb1454514436251c1a");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "97c1f1ead4d531caa4a5b0d69edbce29af789a2550e5ddbd23775be05e2df2c4");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "4bac77520b9eee65068ef1cd8abeadb013b4de3953fd29ac68e90e4866227");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "fea590b681589fb9b0e8664ee945b41eb3851faf66aaf48525fba169c34270");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "d3cdb16abb1751d1a481ed87b57db3b883e9961da2f9d485a2986487e2");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "14c4141c1edf3f7e41236bd658c5bc7b5aa7abf7e2a852b647258818acd70d8");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "36f69f7b7538b41dc3439f3658abbd59facca366f190bcf1d6d0a026c8f96");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "cad01592ca44189f8abad0c2efa3cae6aafae76daaa7ea46655cc1293489ff");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "361e5b333c2a3868bb6a58b6674a2639323815738e77e053977419af3f77");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "c96540ce762125e398ca53d4cd9b668396d0467e128b30da5aa62be9ce060");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "b839e381d9fedab6f8b59396a2764238dceb2f7eea856dc6fc44767da382f1");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "3b19dc4d467882dbca1b5c37465f0cfc70ff1f829ecf4a865796b8e5c2809a");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "c1482b755858657fb51b7d3fbf4cd8c090c05e35bd8cdba98b19499d7833acb2");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "489ce89526fc12624678f305493aa65da8a1b360546a505d118eb1fad775");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "7d31d21cb54294ee3a2056137d123b576f78bfc4878cd8144cd51e1931c39b5");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "e9352bcabfc27edb44ceb51b04786542f26a299a0529475346186ee94738f");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "7557db5f15ca6f3701903cca402ce77ec6f885036b6812e8288abd7e94");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "855654b3f1bfb2cdf0f4b52d6360a03d31ddafc710f8afaea99fba667e482df");
        AnimatedHatType.COLOR_BLOCK.addTexture(1, "9133fa52dd74d711e53747da963b8adecf92db946be113b56c38b3dc270eeb3");
        AnimatedHatType.JOE_THE_PENGUIN.addTexture(18, "f435906a8f2490111025e6e0ca9526ba6630c7e7fc7244de98b7060ce77e2ee8");
        AnimatedHatType.JOE_THE_PENGUIN.addTexture(1, "5ae906009be9d5b563e32c9567ce446520b63287fa3a9fdb3b71672022cea957");
        AnimatedHatType.JOE_THE_PENGUIN.addTexture(1, "f435906a8f2490111025e6e0ca9526ba6630c7e7fc7244de98b7060ce77e2ee8");
        AnimatedHatType.JOE_THE_PENGUIN.addTexture(1, "5ae906009be9d5b563e32c9567ce446520b63287fa3a9fdb3b71672022cea957");
        AnimatedHatType.JOE_THE_PENGUIN.addTexture(1, "f435906a8f2490111025e6e0ca9526ba6630c7e7fc7244de98b7060ce77e2ee8");
        AnimatedHatType.COMET_THE_REINDEER.addTexture(18, "ebd46b38b21b342caf917ad9ca42afb68388a5591bcc9aded1e8e346e18890");
        AnimatedHatType.COMET_THE_REINDEER.addTexture(1, "f7974eb3da1bc6d6686686431a7f32b571f5c2922cb4eaff520e4ca352f8bc04");
        AnimatedHatType.COMET_THE_REINDEER.addTexture(1, "e97628b95492d150f7f3990025bd2310a5e01e70666b30a0b8ad4b73b367ea55");
        AnimatedHatType.COMET_THE_REINDEER.addTexture(1, "28920e09f0351922d73df83549adc3a66601c8abd2ec7ba4e5e57472fe78114b");
        AnimatedHatType.COMET_THE_REINDEER.addTexture(1, "e97628b95492d150f7f3990025bd2310a5e01e70666b30a0b8ad4b73b367ea55");
        AnimatedHatType.COMET_THE_REINDEER.addTexture(1, "f7974eb3da1bc6d6686686431a7f32b571f5c2922cb4eaff520e4ca352f8bc04");
        AnimatedHatType.BROKEN_TV.addTexture(1, "d3ad7dc17bf9cc51f7b817b346a50fc8b5e682998a7b95c0c67a60355fa1f4");
        AnimatedHatType.BROKEN_TV.addTexture(1, "869e19dcc40f878a1fef06e29ee7f658cf1f631e3e9ecec46fb76f43c3b396f");
        AnimatedHatType.BROKEN_TV.addTexture(1, "40527eb74cecb2c2ae1b712d4df36f5c6aa97ad62d1d2ac3cf3cb74cdbe697");
        AnimatedHatType.BROKEN_TV.addTexture(1, "e64cc8dff47fefe1edb57c687b46c730c622f39f339d75ebd35d3cb596462d70");
        AnimatedHatType.BROKEN_TV.addTexture(10, "724b8b6bd37962e78cd0e7fa7095abe64aa3ce28781c1de5cf17df29dfad191e");
    }
    
    private AnimatedHatType(final String s, final String displayName, final String s2, final int mysteryDust, final Rarity rarity, final List<String> list, final GMaterial.GMaterialHead headTexture, final Long ticksPerFrame) {
        super(Category.ANIMATED_HATS, s, displayName, s2, mysteryDust, rarity, list, list);
        if (FileManager.getAnimatedHatsFile().get(String.valueOf(this.getFilePath()) + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getAnimatedHatsFile().getString(String.valueOf(this.getFilePath()) + ".Name");
        }
        if (FileManager.getAnimatedHatsFile().get(String.valueOf(this.getFilePath()) + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getAnimatedHatsFile().getInt(String.valueOf(this.getFilePath()) + ".Mystery Dust");
        }
        if (FileManager.getAnimatedHatsFile().get(String.valueOf(this.getFilePath()) + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getAnimatedHatsFile().getString(String.valueOf(this.getFilePath()) + ".Rarity"));
        }
        if (FileManager.getAnimatedHatsFile().get(String.valueOf(this.getFilePath()) + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getAnimatedHatsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Enabled");
        }
        if (FileManager.getAnimatedHatsFile().get(String.valueOf(this.getFilePath()) + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getAnimatedHatsFile().getBoolean(String.valueOf(this.getFilePath()) + ".CanBeFound");
        }
        if (FileManager.getAnimatedHatsFile().get(String.valueOf(this.getFilePath()) + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getAnimatedHatsFile().getBoolean(String.valueOf(this.getFilePath()) + ".Purchasable");
        }
        if (FileManager.getAnimatedHatsFile().get(String.valueOf(this.getFilePath()) + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getAnimatedHatsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", "");
                }
                else {
                    FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getAnimatedHatsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Locked");
            }
            if (FileManager.getAnimatedHatsFile().get(String.valueOf(this.getFilePath()) + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getAnimatedHatsFile().set(String.valueOf(this.getFilePath()) + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getAnimatedHatsFile().getStringList(String.valueOf(this.getFilePath()) + ".Lore.Unlocked");
            }
        }
        this.headTexture = headTexture;
        this.ticksPerFrame = ticksPerFrame;
        this.frames = new ArrayList<ItemStack>();
        if (!AnimatedHatType.VALUES.contains(this)) {
            AnimatedHatType.VALUES.add(this);
        }
    }
    
    public AnimatedHatType(final String s, final String displayName, final String s2, final int mysteryDust, final Rarity rarity, final List<String> lockedLore, final List<String> unlockedLore, final GMaterial.GMaterialHead headTexture, final Long ticksPerFrame, final boolean isEnable, final boolean canBeFound, final boolean purchasable, final ArrayList<ItemStack> frames) {
        super(Category.ANIMATED_HATS, s, displayName, s2, mysteryDust, rarity, lockedLore, unlockedLore);
        this.displayName = displayName;
        this.mysteryDust = mysteryDust;
        this.rarity = rarity;
        this.lockedLore = lockedLore;
        this.unlockedLore = unlockedLore;
        this.headTexture = headTexture;
        this.ticksPerFrame = ticksPerFrame;
        this.isEnable = isEnable;
        this.canBeFound = canBeFound;
        this.purchasable = purchasable;
        this.frames = frames;
        if (!AnimatedHatType.VALUES.contains(this)) {
            AnimatedHatType.VALUES.add(this);
        }
        if (isEnable && !AnimatedHatType.ENABLED.contains(this)) {
            AnimatedHatType.ENABLED.add(this);
        }
    }
    
    @Override
    public String getDisplayName() {
        return ChatUtil.format(this.displayName);
    }
    
    @Override
    public String getDisplayNameStripColor() {
        return ChatUtil.stripColor(this.displayName);
    }
    
    @Override
    public int getMysteryDust() {
        return this.mysteryDust;
    }
    
    @Override
    public Rarity getRarity() {
        return this.rarity;
    }
    
    @Override
    public List<String> getLockedLore() {
        return this.lockedLore;
    }
    
    @Override
    public List<String> getUnlockedLore() {
        return this.unlockedLore;
    }
    
    public GMaterial.GMaterialHead getMaterialHead() {
        return this.headTexture;
    }
    
    public Long getTicksPerFrame() {
        return this.ticksPerFrame;
    }
    
    public void addTexture(final int n, final String s) {
        final ItemStack setNBTTag = CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(ItemUtils.itemSkull(this.displayName, s), "Cosmetics", "true"), "Category", Category.ANIMATED_HATS.getNBTTag());
        if (n == 1) {
            this.frames.add(setNBTTag);
        }
        else {
            for (int i = 0; i < n; ++i) {
                this.frames.add(setNBTTag);
            }
        }
    }
    
    public ArrayList<ItemStack> getFrames() {
        return this.frames;
    }
    
    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }
    
    @Override
    public boolean canBeFound() {
        return this.canBeFound;
    }
    
    @Override
    public boolean isPurchasable() {
        return this.purchasable;
    }
    
    @Override
    public String getFilePath() {
        return "Animated-Hats." + super.getName();
    }
    
    @Override
    public void equip(final PlayerManager playerManager) {
        if (!this.isEnabled() || !Category.ANIMATED_HATS.isEnabled()) {
            return;
        }
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        if (player.getInventory().getHelmet() != null) {
            CategoryManager.removeHelmetCosmetic(playerManager);
            if (player.getInventory().getHelmet() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_HELMET_TO_EQUIP_ANIMATED_HAT.getFormatMessage())) {
                    return;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    final Player player2;
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        player2.getWorld().dropItemNaturally(player2.getLocation(), player2.getInventory().getHelmet().clone());
                        player2.getInventory().setHelmet((ItemStack)null);
                        player2.updateInventory();
                        return;
                    });
                }
            }
        }
        if (playerManager.getEquippedMorph() != null) {
            playerManager.unequipMorph();
        }
        new AnimatedHat(player.getUniqueId(), this);
        playerManager.setEquippedAnimatedHat(this);
    }
    
    @Override
    public void unequip(final PlayerManager playerManager) {
        if (playerManager == null) {
            return;
        }
        final Player player = playerManager.getPlayer();
        if (player.getInventory().getHelmet() != null || playerManager.getEquippedAnimatedHat() != null) {
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta() != null && CookieGadgets.getNMSManager().isNBTTagEqual(player.getInventory().getHelmet(), "Category", Category.ANIMATED_HATS.getNBTTag())) {
                player.getInventory().setHelmet((ItemStack)null);
                player.updateInventory();
            }
            if (playerManager.getEquippedAnimatedHat() != null) {
                playerManager.setEquippedAnimatedHat(null);
            }
            if (playerManager.getCurrentAnimatedHat() != null) {
                playerManager.removeAnimatedHat();
            }
        }
    }
    
    @Override
    public boolean checkRequirement(final PlayerManager playerManager) {
        if (!this.getCategory().isEnabled() || !this.isEnabled()) {
            return false;
        }
        if (playerManager == null) {
            return false;
        }
        final Player player = playerManager.getPlayer();
        if (CookieGadgets.getCookieGadgetsData().isAntiLagEnabled() && CookieGadgets.getCookieGadgetsData().disableUsageIfLowTPS() && AntiLagChecker.isLowTPS()) {
            player.sendMessage(MessageType.DISABLE_USAGE_OF_COSMETICS.getFormatMessage());
            return false;
        }
        if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.ANIMATED_HATS)) {
            player.sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return false;
        }
        if (player.getInventory().getHelmet() != null) {
            CategoryManager.removeHelmetCosmetic(playerManager);
            if (player.getInventory().getHelmet() != null) {
                if (CategoryManager.checkEquipRequirement(playerManager, MessageType.REMOVE_HELMET_TO_EQUIP_ANIMATED_HAT.getFormatMessage())) {
                    return false;
                }
                if (CookieGadgets.getCookieGadgetsData().getEquipCosmeticItemToSlotAction() == EnumEquipType.DROP) {
                    final Player player2;
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                        player2.getWorld().dropItemNaturally(player2.getLocation(), player2.getInventory().getHelmet().clone());
                        player2.getInventory().setHelmet((ItemStack)null);
                        player2.updateInventory();
                        return;
                    });
                }
            }
        }
        return true;
    }
    
    public static List<AnimatedHatType> enabled() {
        return AnimatedHatType.ENABLED;
    }
    
    public static List<AnimatedHatType> values() {
        return AnimatedHatType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final AnimatedHatType animatedHatType : values()) {
            if (animatedHatType.isEnabled() && !AnimatedHatType.ENABLED.contains(animatedHatType)) {
                AnimatedHatType.ENABLED.add(animatedHatType);
            }
        }
    }
    
    public static void sortItems() {
        if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.NAME) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super AnimatedHatType, ? extends Comparable>)AnimatedHatType::getDisplayNameStripColor));
        }
        else if (CookieGadgets.getCookieGadgetsData().getInventorySorting() == EnumInventorySort.RARITY) {
            Collections.sort(enabled(), Comparator.comparing((Function<? super Object, ? extends Comparable>)AnimatedHatType::getRarity).thenComparing((Function<? super Object, ? extends Comparable>)AnimatedHatType::getDisplayNameStripColor));
        }
    }
    
    public static AnimatedHatType valueOf(final String anotherString) {
        for (final AnimatedHatType animatedHatType : values()) {
            if (animatedHatType.getName().equalsIgnoreCase(anotherString)) {
                return animatedHatType;
            }
        }
        return null;
    }
}
