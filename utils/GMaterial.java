

package ws.billy.CookieGadgets.utils;

import java.util.List;
import org.apache.commons.lang.Validate;
import org.bukkit.Color;

public class GMaterial
{
    private EnumMaterial material;
    private int data;
    private boolean isHead;
    private String texture;
    private Color color;
    private boolean check;
    
    public GMaterial(final EnumMaterial material) {
        this.material = EnumMaterial.BARRIER;
        this.isHead = false;
        this.check = true;
        if (material != null) {
            this.material = material;
        }
    }
    
    public GMaterial(final EnumMaterial material, final int data) {
        this.material = EnumMaterial.BARRIER;
        this.isHead = false;
        this.check = true;
        if (material != null) {
            this.material = material;
            this.data = data;
        }
    }
    
    public GMaterial(final EnumMaterial material, final GColor gColor) {
        this.material = EnumMaterial.BARRIER;
        this.isHead = false;
        this.check = true;
        if (material != null) {
            this.material = material;
            this.color = gColor.getColor();
        }
    }
    
    public GMaterial(final EnumMaterial material, final Color color) {
        this.material = EnumMaterial.BARRIER;
        this.isHead = false;
        this.check = true;
        if (material != null) {
            this.material = material;
            this.color = color;
        }
    }
    
    public GMaterial(final String s, final int data) {
        this.material = EnumMaterial.BARRIER;
        this.isHead = false;
        this.check = true;
        Validate.notNull((Object)s);
        this.material = EnumMaterial.getByName(s);
        this.data = data;
    }
    
    public GMaterial(final String s) {
        this.material = EnumMaterial.BARRIER;
        this.isHead = false;
        this.check = true;
        Validate.notNull((Object)s);
        if (s.contains(":")) {
            if (s.startsWith("head")) {
                this.material = EnumMaterial.PLAYER_HEAD;
                this.data = 3;
                this.isHead = true;
                this.texture = s.substring(5);
            }
            else {
                try {
                    this.material = EnumMaterial.getByName(s.split("\\:")[0]);
                }
                catch (NullPointerException ex) {
                    this.material = EnumMaterial.BARRIER;
                }
                final String s2 = s.split("\\:")[1];
                if (s2.length() >= 6 && s2.length() <= 7) {
                    this.color = new GColor(s2).getColor();
                }
                else {
                    this.data = Integer.parseInt(s.split("\\:")[1]);
                }
            }
        }
        else {
            try {
                this.material = EnumMaterial.getByName(s);
            }
            catch (NullPointerException ex2) {
                this.material = EnumMaterial.BARRIER;
            }
            this.data = 0;
        }
    }
    
    public GMaterial(final String s, final List<String> list) {
        this.material = EnumMaterial.BARRIER;
        this.isHead = false;
        this.check = true;
        if (s.contains(":")) {
            try {
                this.material = EnumMaterial.getByName(s.split("\\:")[0]);
            }
            catch (NullPointerException ex) {
                this.material = EnumMaterial.BARRIER;
            }
            this.data = Integer.parseInt(s.split("\\:")[1]);
        }
        else {
            try {
                this.material = EnumMaterial.getByName(s);
            }
            catch (NullPointerException ex2) {
                this.material = EnumMaterial.BARRIER;
            }
            this.data = 0;
        }
        if (!list.contains(String.valueOf(this.material)) && this.material == null) {
            this.material = EnumMaterial.BARRIER;
            this.data = 0;
        }
        this.check = false;
    }
    
    public EnumMaterial getEnumMaterial() {
        if (this.material == null && this.check) {
            return EnumMaterial.BARRIER;
        }
        return this.material;
    }
    
    public int getData() {
        if (this.material.getData() != 0) {
            return this.material.getData();
        }
        return this.data;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public boolean isColorableMaterial() {
        return this.color != null && EnumArmorType.isLeatherArmor(this.material);
    }
    
    public boolean isSkullHead() {
        return this.isHead;
    }
    
    public String getTexture() {
        if (!this.isHead) {
            return "";
        }
        if (this.texture == null) {
            return "";
        }
        return this.texture;
    }
    
    public String getCombinedMaterial() {
        if (this.isHead) {
            return "head:" + this.texture;
        }
        return String.valueOf(this.material.getName().toString()) + ((this.data > 15) ? (":" + this.data) : "") + (this.isColorableMaterial() ? (":" + GColor.getHexFromColor(this.color)) : "");
    }
    
    @Override
    public String toString() {
        return this.getCombinedMaterial();
    }
    
    public static class GMaterialHead
    {
        private String texture;
        
        public GMaterialHead(final String texture) {
            this.texture = texture;
        }
        
        public GMaterial getMaterial() {
            return new GMaterial("head:" + this.texture);
        }
    }
}
