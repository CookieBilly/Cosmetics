

package ws.billy.CookieGadgets.utils;

public enum EnumPermission
{
    ALL("ALL", 0, "CookieGadgets.*"),
    ALL_MYSTERY_VAULT_ANIMATIONS("ALL_MYSTERY_VAULT_ANIMATIONS", 1, "CookieGadgets.animations.*"),
    ALL_COMMANDS("ALL_COMMANDS", 2, "CookieGadgets.commands.*"),
    ITEM_COST_DISCOUNT("ITEM_COST_DISCOUNT", 3, "CookieGadgets.discount.*"),
    RANDOM_MYSTERY_VAULT_ANIMATIONS("RANDOM_MYSTERY_VAULT_ANIMATIONS", 4, "CookieGadgets.animations.random"),
    AUTO_EQUIP_AFTER_PURCHASE("AUTO_EQUIP_AFTER_PURCHASE", 5, "CookieGadgets.autoequip.purchase"),
    AUTO_EQUIP_ON_LOOT_FOUND("AUTO_EQUIP_ON_LOOT_FOUND", 6, "CookieGadgets.autoequip.foundloot"),
    COMMAND_CHECK_UPDATE("COMMAND_CHECK_UPDATE", 7, "CookieGadgets.commands.checkupdate"),
    MENU_SELECTOR("MENU_SELECTOR", 8, "CookieGadgets.menuselector"),
    OPEN_ALL_MYSTERY_BOXES("OPEN_ALL_MYSTERY_BOXES", 9, "CookieGadgets.mysteryboxes.open.*"),
    RENAME_PET("RENAME_PET", 10, "CookieGadgets.commands.namepet"),
    RIDE_PET("RIDE_PET", 11, "CookieGadgets.ridepet"),
    SUMMON_PET("SUMMON_PET", 12, "CookieGadgets.summonpet"),
    BYPASS_COOLDOWN("BYPASS_COOLDOWN", 13, "CookieGadgets.cooldown.bypass"),
    BYPASS_DISCOBALL_DURATION("BYPASS_DISCOBALL_DURATION", 14, "CookieGadgets.bypassduration.discoball"),
    BYPASS_DJ_BOOTH_DURATION("BYPASS_DJ_BOOTH_DURATION", 15, "CookieGadgets.bypassduration.djbooth"),
    OPEN_MULTIPLE_BOXES("OPEN_MULTIPLE_BOXES", 16, "CookieGadgets.multipleboxes.*"),
    OPEN_20_BOXES("OPEN_20_BOXES", 17, "CookieGadgets.multipleboxes.20"),
    OPEN_50_BOXES("OPEN_50_BOXES", 18, "CookieGadgets.multipleboxes.50"),
    OPEN_250_BOXES("OPEN_250_BOXES", 19, "CookieGadgets.multipleboxes.250"),
    BYPASS_COSMETICS_IN_REGION("BYPASS_COSMETICS_IN_REGION", 20, "CookieGadgets.bypassregion"),
    HATS("HATS", 21, "CookieGadgets.hats.*"),
    ANIMATED_HATS("ANIMATED_HATS", 22, "CookieGadgets.animatedhats.*"),
    PARTICLES("PARTICLES", 23, "CookieGadgets.particles.*"),
    SUITS("SUITS", 24, "CookieGadgets.suits.*"),
    GADGETS("GADGETS", 25, "CookieGadgets.gadgets.*"),
    PETS("PETS", 26, "CookieGadgets.pets.*"),
    MINIATURES("MINIATURES", 27, "CookieGadgets.miniatures.*"),
    MORPHS("MORPHS", 28, "CookieGadgets.morphs.*"),
    BANNERS("BANNERS", 29, "CookieGadgets.banners.*"),
    EMOTES("EMOTES", 30, "CookieGadgets.emotes.*"),
    CLOAKS("CLOAKS", 31, "CookieGadgets.cloaks.*");
    
    private String permission;
    
    private EnumPermission(final String name, final int ordinal, final String permission) {
        this.permission = permission;
    }
    
    public String getPermission() {
        return this.permission;
    }
}
