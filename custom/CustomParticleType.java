

package ws.billy.CookieGadgets.custom;

import ws.billy.CookieGadgets.cosmetics.particles.ParticleType;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import java.util.List;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import ws.billy.CookieGadgets.utils.GMaterial;

public class CustomParticleType
{
    private String name;
    private String displayName;
    private GMaterial material;
    private String permission;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private ParticleEffect particleEffect;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    
    public static void initCustomParticles() {
        final FileManager customParticlesFile = FileManager.getCustomParticlesFile();
        if (customParticlesFile.get("Custom-Particles") != null) {
            for (final String s : customParticlesFile.getConfigurationSection("Custom-Particles").getKeys(false)) {
                new CustomParticleType(s, "&a" + s.toLowerCase(), new GMaterial(EnumMaterial.BLAZE_POWDER), "CookieGadgets.particles." + s.toLowerCase().replace(" ", ""), 15, Rarity.COMMON, null, ParticleEffect.FLAME);
            }
        }
        else {
            customParticlesFile.addDefault("Custom-Particles.End Rod.Name", "&5End Rod Particle");
            customParticlesFile.addDefault("Custom-Particles.End Rod.Material", "END_ROD");
            customParticlesFile.addDefault("Custom-Particles.End Rod.Mystery Dust", 32);
            customParticlesFile.addDefault("Custom-Particles.End Rod.Rarity", Rarity.EPIC.getName());
            customParticlesFile.addDefault("Custom-Particles.End Rod.Effect", ParticleEffect.END_ROD.getName());
            customParticlesFile.addDefault("Custom-Particles.End Rod.Enabled", false);
            customParticlesFile.addDefault("Custom-Particles.End Rod.CanBeFound", true);
            customParticlesFile.addDefault("Custom-Particles.End Rod.Purchasable", true);
            customParticlesFile.addDefault("Custom-Particles.End Rod.Lore.Locked", "");
            customParticlesFile.addDefault("Custom-Particles.End Rod.Lore.Unlocked", "");
        }
    }
    
    private CustomParticleType(final String name, final String displayName, final GMaterial material, final String permission, final int mysteryDust, final Rarity rarity, final List<String> list, final ParticleEffect particleEffect) {
        this.name = name;
        if (FileManager.getCustomParticlesFile().get("Custom-Particles." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getCustomParticlesFile().getString("Custom-Particles." + this.name + ".Name");
        }
        if (FileManager.getCustomParticlesFile().get("Custom-Particles." + this.name + ".Material") == null) {
            this.material = material;
            FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Material", this.material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getCustomParticlesFile().getString("Custom-Particles." + this.name + ".Material"));
        }
        this.permission = permission;
        if (FileManager.getCustomParticlesFile().get("Custom-Particles." + this.name + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getCustomParticlesFile().getInt("Custom-Particles." + this.name + ".Mystery Dust");
        }
        if (FileManager.getCustomParticlesFile().get("Custom-Particles." + this.name + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getCustomParticlesFile().getString("Custom-Particles." + this.name + ".Rarity"));
        }
        if (FileManager.getCustomParticlesFile().get("Custom-Particles." + this.name + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getCustomParticlesFile().getBoolean("Custom-Particles." + this.name + ".Enabled");
        }
        if (FileManager.getCustomParticlesFile().get("Custom-Particles." + this.name + ".CanBeFound") == null) {
            this.canBeFound = true;
            FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getCustomParticlesFile().getBoolean("Custom-Particles." + this.name + ".CanBeFound");
        }
        if (FileManager.getCustomParticlesFile().get("Custom-Particles." + this.name + ".Purchasable") == null) {
            this.purchasable = true;
            FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Purchasable", true);
        }
        else {
            this.purchasable = FileManager.getCustomParticlesFile().getBoolean("Custom-Particles." + this.name + ".Purchasable");
        }
        if (FileManager.getCustomParticlesFile().get("Custom-Particles." + this.name + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Lore.Locked", "");
                FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Lore.Locked", this.lockedLore);
                FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getCustomParticlesFile().get("Custom-Particles." + this.name + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Lore.Locked", "");
                }
                else {
                    FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getCustomParticlesFile().getStringList("Custom-Particles." + this.name + ".Lore.Locked");
            }
            if (FileManager.getCustomParticlesFile().get("Custom-Particles." + this.name + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getCustomParticlesFile().getStringList("Custom-Particles." + this.name + ".Lore.Unlocked");
            }
        }
        if (FileManager.getCustomParticlesFile().get("Custom-Particles." + this.name + ".Effect") == null) {
            this.particleEffect = particleEffect;
            FileManager.getCustomParticlesFile().set("Custom-Particles." + this.name + ".Effect", particleEffect);
        }
        else {
            try {
                this.particleEffect = ParticleEffect.valueOf(FileManager.getCustomParticlesFile().getString("Custom-Particles." + this.name + ".Effect").toUpperCase());
            }
            catch (IllegalArgumentException ex) {
                this.particleEffect = particleEffect;
            }
        }
        new ParticleType(this.name, this.displayName, this.material, this.permission, this.mysteryDust, this.rarity, this.lockedLore, this.unlockedLore, this.particleEffect, this.isEnable, this.canBeFound, this.purchasable);
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
