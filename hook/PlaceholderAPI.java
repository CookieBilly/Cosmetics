

package ws.billy.CookieGadgets.hook;

import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.GPet;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.cosmetics.suits.SuitEquipmentType;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import ws.billy.CookieGadgets.cosmetics.emotes.EmoteType;
import ws.billy.CookieGadgets.cosmetics.banners.BannerType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import ws.billy.CookieGadgets.cosmetics.particles.ParticleType;
import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHatType;
import ws.billy.CookieGadgets.cosmetics.hats.standard.HatType;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderAPI extends PlaceholderExpansion
{
    public String getAuthor() {
        return "Yap Zhen Yie";
    }
    
    public String getIdentifier() {
        return "CookieGadgets";
    }
    
    public String getVersion() {
        return "1.0.1";
    }
    
    public boolean persist() {
        return true;
    }
    
    public String onPlaceholderRequest(final Player player, final String s) {
        try {
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
            if (playerManager == null) {
                return String.valueOf("ERROR");
            }
            if (s.equals("mystery_dust")) {
                return String.valueOf(playerManager.getMysteryDust());
            }
            if (s.equals("mystery_boxes")) {
                return String.valueOf(playerManager.getMysteryBoxes());
            }
            if (s.endsWith("pet_name")) {
                final PetType value = PetType.valueOf(s.replace("_pet_name", "").replace("_", " "));
                if (value != null) {
                    final GPet petData = playerManager.getPetData(value);
                    if (petData != null) {
                        return petData.getPetName();
                    }
                }
            }
            if (s.equals("current_pet_name")) {
                final PetType equippedPet = playerManager.getEquippedPet();
                if (equippedPet == null) {
                    return MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage();
                }
                final GPet petData2 = playerManager.getPetData(equippedPet);
                if (petData2 == null) {
                    return MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage();
                }
                return petData2.getPetName();
            }
            else {
                if (s.equals("unlocked_total")) {
                    return String.valueOf(playerManager.getUnlockedTotalCosmeticItems());
                }
                if (s.equals("unlocked_hats")) {
                    return String.valueOf(playerManager.getUnlockedHats());
                }
                if (s.equals("unlocked_animated_hats")) {
                    return String.valueOf(playerManager.getUnlockedAnimatedHats());
                }
                if (s.equals("unlocked_particles")) {
                    return String.valueOf(playerManager.getUnlockedParticles());
                }
                if (s.equals("unlocked_suits")) {
                    return String.valueOf(playerManager.getUnlockedSuits());
                }
                if (s.equals("unlocked_gadgets")) {
                    return String.valueOf(playerManager.getUnlockedGadgets());
                }
                if (s.equals("unlocked_pets")) {
                    return String.valueOf(playerManager.getUnlockedPets());
                }
                if (s.equals("unlocked_miniatures")) {
                    return String.valueOf(playerManager.getUnlockedMiniatures());
                }
                if (s.equals("unlocked_morphs")) {
                    return String.valueOf(playerManager.getUnlockedMorphs());
                }
                if (s.equals("unlocked_banners")) {
                    return String.valueOf(playerManager.getUnlockedBanners());
                }
                if (s.equals("unlocked_emotes")) {
                    return String.valueOf(playerManager.getUnlockedEmotes());
                }
                if (s.equals("unlocked_cloaks")) {
                    return String.valueOf(playerManager.getUnlockedCloaks());
                }
                if (s.equals("locked_total")) {
                    return String.valueOf(HatType.enabled().size() + AnimatedHatType.enabled().size() + ParticleType.enabled().size() + SuitType.enabled().size() * 4 + GadgetType.enabled().size() + PetType.enabled().size() + MiniatureType.enabled().size() + MorphType.enabled().size() + BannerType.enabled().size() + EmoteType.enabled().size() + CloakType.enabled().size() - playerManager.getUnlockedTotalCosmeticItems());
                }
                if (s.equals("locked_hats")) {
                    return String.valueOf(HatType.enabled().size() - playerManager.getUnlockedHats());
                }
                if (s.equals("locked_animated_hats")) {
                    return String.valueOf(AnimatedHatType.enabled().size() - playerManager.getUnlockedHats());
                }
                if (s.equals("locked_particles")) {
                    return String.valueOf(ParticleType.enabled().size() - playerManager.getUnlockedParticles());
                }
                if (s.equals("locked_suits")) {
                    return String.valueOf(SuitType.enabled().size() * 4 - playerManager.getUnlockedSuits());
                }
                if (s.equals("locked_gadgets")) {
                    return String.valueOf(GadgetType.enabled().size() - playerManager.getUnlockedGadgets());
                }
                if (s.equals("locked_pets")) {
                    return String.valueOf(PetType.enabled().size() - playerManager.getUnlockedPets());
                }
                if (s.equals("locked_miniatures")) {
                    return String.valueOf(MiniatureType.enabled().size() - playerManager.getUnlockedMiniatures());
                }
                if (s.equals("locked_morphs")) {
                    return String.valueOf(MorphType.enabled().size() - playerManager.getUnlockedMorphs());
                }
                if (s.equals("locked_banners")) {
                    return String.valueOf(BannerType.enabled().size() - playerManager.getUnlockedBanners());
                }
                if (s.equals("locked_emotes")) {
                    return String.valueOf(EmoteType.enabled().size() - playerManager.getUnlockedEmotes());
                }
                if (s.equals("locked_cloaks")) {
                    return String.valueOf(CloakType.enabled().size() - playerManager.getUnlockedCloaks());
                }
                if (s.equals("total_size")) {
                    return String.valueOf(HatType.enabled().size() + AnimatedHatType.enabled().size() + ParticleType.enabled().size() + SuitType.enabled().size() * 4 + GadgetType.enabled().size() + PetType.enabled().size() + MiniatureType.enabled().size() + MorphType.enabled().size() + BannerType.enabled().size() + EmoteType.enabled().size() + CloakType.enabled().size());
                }
                if (s.equals("hats_size")) {
                    return String.valueOf(HatType.enabled().size());
                }
                if (s.equals("animated_hats_size")) {
                    return String.valueOf(AnimatedHatType.enabled().size());
                }
                if (s.equals("particles_size")) {
                    return String.valueOf(ParticleType.enabled().size());
                }
                if (s.equals("suits_size")) {
                    return String.valueOf(SuitType.enabled().size() * 4);
                }
                if (s.equals("gadgets_size")) {
                    return String.valueOf(GadgetType.enabled().size());
                }
                if (s.equals("pets_size")) {
                    return String.valueOf(PetType.enabled().size());
                }
                if (s.equals("miniatures_size")) {
                    return String.valueOf(MiniatureType.enabled().size());
                }
                if (s.equals("morphs_size")) {
                    return String.valueOf(MorphType.enabled().size());
                }
                if (s.equals("banners_size")) {
                    return String.valueOf(BannerType.enabled().size());
                }
                if (s.equals("emotes_size")) {
                    return String.valueOf(EmoteType.enabled().size());
                }
                if (s.equals("cloaks_size")) {
                    return String.valueOf(CloakType.enabled().size());
                }
                if (s.equals("unlocked_total_percentages")) {
                    return String.valueOf(playerManager.getUnlockedTotalCosmeticItems() * 100 / (HatType.enabled().size() + ParticleType.enabled().size() + SuitType.enabled().size() * 4 + GadgetType.enabled().size() + PetType.enabled().size() + MiniatureType.enabled().size() + MorphType.enabled().size() + BannerType.enabled().size() + EmoteType.enabled().size() + CloakType.enabled().size()));
                }
                if (s.equals("unlocked_hats_percentages")) {
                    return (HatType.enabled().size() == 0) ? "0" : String.valueOf(playerManager.getUnlockedHats() * 100 / HatType.enabled().size());
                }
                if (s.equals("unlocked_animated_hats_percentages")) {
                    return (HatType.enabled().size() == 0) ? "0" : String.valueOf(playerManager.getUnlockedAnimatedHats() * 100 / AnimatedHatType.enabled().size());
                }
                if (s.equals("unlocked_particles_percentages")) {
                    return (ParticleType.enabled().size() == 0) ? "0" : String.valueOf(playerManager.getUnlockedParticles() * 100 / ParticleType.enabled().size());
                }
                if (s.equals("unlocked_suits_percentages")) {
                    return (SuitType.enabled().size() == 0) ? "0" : String.valueOf(playerManager.getUnlockedSuits() * 100 / (SuitType.enabled().size() * 4));
                }
                if (s.equals("unlocked_gadgets_percentages")) {
                    return (GadgetType.enabled().size() == 0) ? "0" : String.valueOf(playerManager.getUnlockedGadgets() * 100 / GadgetType.enabled().size());
                }
                if (s.equals("unlocked_pets_percentages")) {
                    return (PetType.enabled().size() == 0) ? "0" : String.valueOf(playerManager.getUnlockedPets() * 100 / PetType.enabled().size());
                }
                if (s.equals("unlocked_miniatures_percentages")) {
                    return (MiniatureType.enabled().size() == 0) ? "0" : String.valueOf(playerManager.getUnlockedMiniatures() * 100 / MiniatureType.enabled().size());
                }
                if (s.equals("unlocked_morphs_percentages")) {
                    return (MorphType.enabled().size() == 0) ? "0" : String.valueOf(playerManager.getUnlockedMorphs() * 100 / MorphType.enabled().size());
                }
                if (s.equals("unlocked_banners_percentages")) {
                    return (BannerType.enabled().size() == 0) ? "0" : String.valueOf(playerManager.getUnlockedBanners() * 100 / BannerType.enabled().size());
                }
                if (s.equals("unlocked_emotes_percentages")) {
                    return (EmoteType.enabled().size() == 0) ? "0" : String.valueOf(playerManager.getUnlockedEmotes() * 100 / EmoteType.enabled().size());
                }
                if (s.equals("unlocked_cloaks_percentages")) {
                    return (CloakType.enabled().size() == 0) ? "0" : String.valueOf(playerManager.getUnlockedCloaks() * 100 / CloakType.enabled().size());
                }
                if (s.equals("equipped_cosmetics")) {
                    String obj = String.valueOf((playerManager.getEquippedHat() != null) ? new StringBuilder(String.valueOf(playerManager.getEquippedHat().getDisplayName())).append("&b, ").toString() : "") + ((playerManager.getEquippedAnimatedHat() != null) ? (String.valueOf(playerManager.getEquippedAnimatedHat().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedParticle() != null) ? (String.valueOf(playerManager.getEquippedParticle().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedSuitEquipment().get(EnumArmorType.HELMET) != null) ? (String.valueOf(playerManager.getEquippedSuitEquipment().get(EnumArmorType.HELMET).getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE) != null) ? (String.valueOf(playerManager.getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE).getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS) != null) ? (String.valueOf(playerManager.getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS).getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedSuitEquipment().get(EnumArmorType.BOOTS) != null) ? (String.valueOf(playerManager.getEquippedSuitEquipment().get(EnumArmorType.BOOTS).getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedGadget() != null) ? (String.valueOf(playerManager.getEquippedGadget().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedPet() != null) ? (String.valueOf(playerManager.getEquippedPet().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedMiniature() != null) ? (String.valueOf(playerManager.getEquippedMiniature().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedMorph() != null) ? (String.valueOf(playerManager.getEquippedMorph().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedBanner() != null) ? (String.valueOf(playerManager.getEquippedBanner().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedEmote() != null) ? (String.valueOf(playerManager.getEquippedEmote().getDisplayName()) + "&b, ") : "") + ((playerManager.getEquippedCloak() != null) ? (String.valueOf(playerManager.getEquippedCloak().getDisplayName()) + "&b, ") : "");
                    if (obj.endsWith("&b, ")) {
                        obj = obj.substring(0, obj.length() - 4);
                    }
                    if (obj.equals("")) {
                        obj = MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage();
                    }
                    return String.valueOf(obj);
                }
                if (s.equals("equipped_hat")) {
                    return String.valueOf((playerManager.getEquippedHat() == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedHat().getDisplayNameStripColor());
                }
                if (s.equals("equipped_animated_hat")) {
                    return String.valueOf((playerManager.getEquippedAnimatedHat() == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedAnimatedHat().getDisplayNameStripColor());
                }
                if (s.equals("equipped_particle")) {
                    return String.valueOf((playerManager.getEquippedParticle() == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedParticle().getDisplayNameStripColor());
                }
                if (s.equals("equipped_suit_helmet")) {
                    return String.valueOf((playerManager.getEquippedSuitEquipment().get(EnumArmorType.HELMET) == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedSuitEquipment().get(EnumArmorType.HELMET).getDisplayNameStripColor());
                }
                if (s.equals("equipped_suit_chestplate")) {
                    return String.valueOf((playerManager.getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE) == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedSuitEquipment().get(EnumArmorType.CHESTPLATE).getDisplayNameStripColor());
                }
                if (s.equals("equipped_suit_leggings")) {
                    return String.valueOf((playerManager.getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS) == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedSuitEquipment().get(EnumArmorType.LEGGINGS).getDisplayNameStripColor());
                }
                if (s.equals("equipped_suit_boots")) {
                    return String.valueOf((playerManager.getEquippedSuitEquipment().get(EnumArmorType.BOOTS) == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedSuitEquipment().get(EnumArmorType.BOOTS).getDisplayNameStripColor());
                }
                if (s.equals("equipped_gadget")) {
                    return String.valueOf((playerManager.getEquippedGadget() == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedGadget().getDisplayNameStripColor());
                }
                if (s.equals("equipped_pet")) {
                    return String.valueOf((playerManager.getEquippedPet() == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedPet().getDisplayNameStripColor());
                }
                if (s.equals("equipped_miniature")) {
                    return String.valueOf((playerManager.getEquippedMiniature() == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedMiniature().getDisplayNameStripColor());
                }
                if (s.equals("equipped_morph")) {
                    return String.valueOf((playerManager.getEquippedMorph() == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedMorph().getDisplayNameStripColor());
                }
                if (s.equals("equipped_banner")) {
                    return String.valueOf((playerManager.getEquippedBanner() == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedBanner().getDisplayNameStripColor());
                }
                if (s.equals("equipped_emote")) {
                    return String.valueOf((playerManager.getEquippedEmote() == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedEmote().getDisplayNameStripColor());
                }
                if (s.equals("equipped_cloak")) {
                    return String.valueOf((playerManager.getEquippedCloak() == null) ? MessageType.NO_COSMETIC_EQUIPPED.getFormatMessage() : playerManager.getEquippedCloak().getDisplayNameStripColor());
                }
            }
        }
        catch (NullPointerException ex) {
            return "ERROR";
        }
        return null;
    }
}
