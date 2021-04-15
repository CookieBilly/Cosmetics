// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.utils;

import org.bukkit.Color;

public class GColor
{
    private int red;
    private int green;
    private int blue;
    private String hex;
    private Color color;
    
    public GColor(final int red, final int green, final int blue) {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
        this.hex = "";
        if (red < 0) {
            throw new IllegalArgumentException("The red is lower than 0");
        }
        if (red > 255) {
            throw new IllegalArgumentException("The red is higher than 255");
        }
        this.red = red;
        if (green < 0) {
            throw new IllegalArgumentException("The green is lower than 0");
        }
        if (green > 255) {
            throw new IllegalArgumentException("The green is higher than 255");
        }
        this.green = green;
        if (blue < 0) {
            throw new IllegalArgumentException("The blue is lower than 0");
        }
        if (blue > 255) {
            throw new IllegalArgumentException("The blue is higher than 255");
        }
        this.blue = blue;
        this.color = Color.fromRGB(this.red, this.green, this.blue);
    }
    
    public GColor(final String hex) {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
        this.hex = "";
        this.hex = hex;
        if (!this.hex.startsWith("#")) {
            this.hex = "#" + this.hex;
        }
        final java.awt.Color color = new java.awt.Color(Integer.valueOf(this.hex.substring(1, 3), 16), Integer.valueOf(this.hex.substring(3, 5), 16), Integer.valueOf(this.hex.substring(5, 7), 16));
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.color = Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    public int getRed() {
        return this.red;
    }
    
    public int getGreen() {
        return this.green;
    }
    
    public int getBlue() {
        return this.blue;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public static String getHexFromColor(final Color color) {
        return ("#" + toHexString(color.getRed()) + toHexString(color.getGreen()) + toHexString(color.getBlue())).toUpperCase();
    }
    
    private static String toHexString(final int i) {
        final String hexString = Integer.toHexString(i);
        if (hexString.equals("0")) {
            return "00";
        }
        if (hexString.length() == 1) {
            return "0" + hexString;
        }
        return hexString;
    }
}
