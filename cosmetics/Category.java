

package ws.billy.CookieGadgets.cosmetics;

import java.util.Collection;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.menu.menus.CloaksMenu;
import ws.billy.CookieGadgets.menu.menus.EmotesMenu;
import ws.billy.CookieGadgets.menu.menus.BannersMenu;
import ws.billy.CookieGadgets.menu.menus.MorphsMenu;
import ws.billy.CookieGadgets.menu.menus.MiniaturesMenu;
import ws.billy.CookieGadgets.menu.menus.PetCategoriesMenu;
import ws.billy.CookieGadgets.menu.menus.GadgetCategoriesMenu;
import ws.billy.CookieGadgets.menu.menus.SuitsMenu;
import ws.billy.CookieGadgets.menu.menus.ParticlesMenu;
import ws.billy.CookieGadgets.menu.menus.AnimatedHatsMenu;
import ws.billy.CookieGadgets.menu.menus.HatsMenu;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.EnumPermission;
import java.util.List;

public enum Category
{
    HATS("Hats", "Hats", Arrays.asList("Selected_Hat"), "Hat", EnumPermission.HATS, MessageType.HATS_ARE_DISABLED, MessageType.PURCHASED_HAT, (Class<?>)HatsMenu.class), 
    ANIMATED_HATS("Animated Hats", "Animated Hats", Arrays.asList("Selected_Animated_Hat"), "Animated_Hat", EnumPermission.ANIMATED_HATS, MessageType.ANIMATED_HATS_ARE_DISABLED, MessageType.PURCHASED_ANIMATED_HAT, (Class<?>)AnimatedHatsMenu.class), 
    PARTICLES("Particles", "Particles", Arrays.asList("Selected_Particle"), "Particle", EnumPermission.PARTICLES, MessageType.PARTICLES_ARE_DISABLED, MessageType.PURCHASED_PARTICLE, (Class<?>)ParticlesMenu.class), 
    SUITS("Suits", "Suits", Arrays.asList("Selected_Suit_Helmet", "Selected_Suit_Chestplate", "Selected_Suit_Leggings", "Selected_Suit_Boots"), "Suit", EnumPermission.SUITS, MessageType.SUITS_ARE_DISABLED, MessageType.PURCHASED_SUIT, (Class<?>)SuitsMenu.class), 
    GADGETS("Gadgets", "Gadgets", Arrays.asList("Selected_Gadget"), "Gadget", EnumPermission.GADGETS, MessageType.GADGETS_ARE_DISABLED, MessageType.PURCHASED_GADGET, (Class<?>)GadgetCategoriesMenu.class), 
    PETS("Pets", "Pets", Arrays.asList("Selected_Pet"), "Pet", EnumPermission.PETS, MessageType.PETS_ARE_DISABLED, MessageType.PURCHASED_PET, (Class<?>)PetCategoriesMenu.class), 
    MINIATURES("Miniatures", "Miniatures", Arrays.asList("Selected_Miniature"), "Miniature", EnumPermission.MINIATURES, MessageType.MINIATURES_ARE_DISABLED, MessageType.PURCHASED_MINIATURE, (Class<?>)MiniaturesMenu.class), 
    MORPHS("Morphs", "Morphs", Arrays.asList("Selected_Morph"), "Morph", EnumPermission.MORPHS, MessageType.MORPHS_ARE_DISABLED, MessageType.PURCHASED_MORPH, (Class<?>)MorphsMenu.class), 
    BANNERS("Banners", "Banners", Arrays.asList("Selected_Banner"), "Banner", EnumPermission.BANNERS, MessageType.BANNERS_ARE_DISABLED, MessageType.PURCHASED_BANNER, (Class<?>)BannersMenu.class), 
    EMOTES("Emotes", "Emotes", Arrays.asList("Selected_Emote"), "Emote", EnumPermission.EMOTES, MessageType.EMOTES_ARE_DISABLED, MessageType.PURCHASED_EMOTE, (Class<?>)EmotesMenu.class), 
    CLOAKS("Cloaks", "Cloaks", Arrays.asList("Selected_Cloak"), "Cloak", EnumPermission.CLOAKS, MessageType.CLOAKS_ARE_DISABLED, MessageType.PURCHASED_CLOAK, (Class<?>)CloaksMenu.class);
    
    private String name;
    private String guiName;
    private List<String> sqlIndex;
    private boolean isEnabled;
    private boolean isPurchasable;
    private boolean canBeFound;
    private boolean syncOnJoin;
    private String nbtTag;
    private EnumPermission mainPermission;
    private MessageType disabledMessage;
    private MessageType purchaseItemMessage;
    private Class<?> clazz;
    
    private Category(final String name2, final String s, final List<String> sqlIndex, final String nbtTag, final EnumPermission mainPermission, final MessageType disabledMessage, final MessageType purchaseItemMessage, final Class<?> clazz) {
        this.name = name2;
        this.guiName = s;
        if (FileManager.getMessagesFile().get("GUI-Menus." + this.name) == null) {
            this.guiName = s;
            FileManager.getMessagesFile().addDefault("GUI-Menus." + this.name, s);
        }
        else {
            this.guiName = FileManager.getMessagesFile().getString("GUI-Menus." + this.name);
        }
        this.sqlIndex = sqlIndex;
        if (FileManager.getConfigFile().get("Disabled-Cosmetics." + this.name) == null) {
            this.isEnabled = true;
            FileManager.getConfigFile().addDefault("Disabled-Cosmetics." + this.name, false);
        }
        else {
            this.isEnabled = !FileManager.getConfigFile().getBoolean("Disabled-Cosmetics." + this.name);
        }
        if (!CookieGadgets.getCookieGadgetsData().isCosmeticItemPurchasable()) {
            this.isPurchasable = false;
        }
        else if (FileManager.getConfigFile().get("Cosmetic-Item-Purchase.Enabled-Cosmetics." + this.name) == null) {
            this.isPurchasable = true;
            FileManager.getConfigFile().addDefault("Cosmetic-Item-Purchase.Enabled-Cosmetics." + this.name, true);
        }
        else {
            this.isPurchasable = FileManager.getConfigFile().getBoolean("Cosmetic-Item-Purchase.Enabled-Cosmetics." + this.name);
        }
        if (!CookieGadgets.getCookieGadgetsData().isMysteryBoxesEnabled()) {
            this.canBeFound = false;
        }
        else if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Loots-Can-Be-Found." + this.name) == null) {
            this.canBeFound = true;
            FileManager.getMysteryBoxesFile().addDefault("Mystery-Boxes.Loots-Can-Be-Found." + this.name, true);
        }
        else {
            this.canBeFound = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Loots-Can-Be-Found." + this.name);
        }
        if (FileManager.getConfigFile().get("Cosmetics-Sync-On-Join." + this.name) == null) {
            this.syncOnJoin = true;
            FileManager.getConfigFile().addDefault("Cosmetics-Sync-On-Join." + this.name, true);
        }
        else {
            this.syncOnJoin = FileManager.getConfigFile().getBoolean("Cosmetics-Sync-On-Join." + this.name);
        }
        this.nbtTag = nbtTag;
        this.mainPermission = mainPermission;
        this.disabledMessage = disabledMessage;
        this.purchaseItemMessage = purchaseItemMessage;
        this.clazz = clazz;
        if (this.name.equals("Morphs") && !CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() && !CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) {
            this.isEnabled = false;
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getGUIName() {
        return ChatUtil.format(this.guiName);
    }
    
    public List<String> getSQLIndex() {
        return this.sqlIndex;
    }
    
    public boolean isEnabled() {
        return this.isEnabled;
    }
    
    public boolean isPurchasable() {
        return this.isPurchasable;
    }
    
    public boolean canBeFound() {
        return this.canBeFound;
    }
    
    public boolean syncOnJoin() {
        return this.syncOnJoin;
    }
    
    public void openMenu(final PlayerManager playerManager, final int i) {
        if (playerManager == null) {
            return;
        }
        try {
            if (this == Category.SUITS || this == Category.MORPHS || this == Category.CLOAKS) {
                this.clazz.getMethod("open" + this.name + "Menu", PlayerManager.class).invoke(this.clazz, playerManager);
            }
            else if (this == Category.GADGETS || this == Category.PETS) {
                this.clazz.getMethod("openCategory" + this.name + "Menu", PlayerManager.class).invoke(this.clazz, playerManager);
            }
            else {
                this.clazz.getMethod("open" + this.name.replace(" ", "") + "Menu", PlayerManager.class, Integer.TYPE).invoke(this.clazz, playerManager, i);
            }
        }
        catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        catch (IllegalArgumentException ex2) {
            ex2.printStackTrace();
        }
        catch (InvocationTargetException ex3) {
            ex3.printStackTrace();
        }
        catch (NoSuchMethodException ex4) {
            ex4.printStackTrace();
        }
        catch (SecurityException ex5) {
            ex5.printStackTrace();
        }
    }
    
    public String getNBTTag() {
        return this.nbtTag;
    }
    
    public EnumPermission getMainPermission() {
        return this.mainPermission;
    }
    
    public MessageType getDisabledMessage() {
        return this.disabledMessage;
    }
    
    public MessageType getPurchaseItemMessage() {
        return this.purchaseItemMessage;
    }
    
    public Class<?> getClazz() {
        return this.clazz;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
    
    public static Category valueOfByName(final String anotherString) {
        Category[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final Category category = values[i];
            if (category.getName().equalsIgnoreCase(anotherString)) {
                return category;
            }
        }
        return null;
    }
    
    public static List<String> getSQLIndexes() {
        final ArrayList<String> list = new ArrayList<String>();
        Category[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            list.addAll((Collection<?>)values[i].getSQLIndex());
        }
        return list;
    }
}
