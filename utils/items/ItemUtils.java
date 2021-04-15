

package ws.billy.CookieGadgets.utils.items;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.block.banner.Pattern;
import ws.billy.CookieGadgets.utils.GDyeColor;
import java.lang.reflect.Field;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import com.mojang.authlib.properties.Property;
import java.util.Base64;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import org.bukkit.inventory.meta.SkullMeta;
import ws.billy.CookieGadgets.utils.GMaterial;
import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.EnumPotion;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.Iterator;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import ws.billy.CookieGadgets.utils.ChatUtil;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;

public class ItemUtils
{
    public static ItemStack item(final ItemStack itemStack, final String target, final String replacement) {
        final ItemStack clone = itemStack.clone();
        final ItemMeta itemMeta = clone.getItemMeta();
        if (itemMeta.hasLore()) {
            final ArrayList<String> lore = new ArrayList<String>();
            final Iterator iterator = itemMeta.getLore().iterator();
            while (iterator.hasNext()) {
                lore.add(ChatUtil.format(iterator.next().replace(target, replacement)));
            }
            itemMeta.setLore((List)lore);
        }
        InventoryUtils.addItemFlags(itemMeta);
        clone.setItemMeta(itemMeta);
        return clone;
    }
    
    public static ItemStack item(final ItemStack itemStack, final List<String> list, final List<String> list2) {
        final ItemStack clone = itemStack.clone();
        final ItemMeta itemMeta = clone.getItemMeta();
        if (itemMeta.hasLore()) {
            final ArrayList<String> lore = new ArrayList<String>();
            for (final String s : itemMeta.getLore()) {
                if (list.size() == list2.size()) {
                    String format = s;
                    for (int i = 0; i < list.size(); ++i) {
                        format = ChatUtil.format(format.replace(list.get(i), list2.get(i)));
                    }
                    lore.add(format);
                }
                else {
                    lore.add(ChatUtil.format(s));
                }
            }
            itemMeta.setLore((List)lore);
        }
        InventoryUtils.addItemFlags(itemMeta);
        clone.setItemMeta(itemMeta);
        return clone;
    }
    
    public static ItemStack item(final String s, final EnumMaterial enumMaterial) {
        return item(s, enumMaterial, enumMaterial.getData(), null);
    }
    
    public static ItemStack item(final String s, final EnumMaterial enumMaterial, final int n) {
        return item(s, enumMaterial, n, null);
    }
    
    public static ItemStack item(final String s, final EnumMaterial material, final int data, final List<String> list) {
        ItemStack itemStack = new ItemStack(material.getType(), 1, (short)data);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.format(s));
        if (list != null) {
            final ArrayList<String> lore = new ArrayList<String>();
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                lore.add(ChatUtil.format(iterator.next()));
            }
            itemMeta.setLore((List)lore);
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(material)) {
            itemStack.setDurability((short)0);
        }
        InventoryUtils.addItemFlags(itemMeta);
        itemStack.setItemMeta(itemMeta);
        if (material.isSpawnEgg() && VersionManager.is1_9OrAbove() && !VersionManager.is1_13OrAbove()) {
            itemStack = CookieGadgets.getNMSManager().spawnEgg(itemStack, EntityType.fromId(data).getName());
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(material)) {
            itemStack = CookieGadgets.getNMSManager().getPotionFromId(itemStack, material, data);
        }
        return itemStack;
    }
    
    public static ItemStack item(final String s, final GMaterial gMaterial) {
        return item(s, gMaterial, null);
    }
    
    public static ItemStack item(final String s, final GMaterial material, final List<String> list) {
        if (material.isSkullHead()) {
            String str = material.getTexture();
            if (!str.startsWith("http://textures.minecraft.net/texture/")) {
                str = "http://textures.minecraft.net/texture/" + str;
            }
            final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), 1, (short)EnumMaterial.PLAYER_HEAD.getData());
            final SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
            skullMeta.setDisplayName(ChatUtil.format(s));
            final ArrayList<String> lore = new ArrayList<String>();
            if (list != null) {
                final Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    lore.add(ChatUtil.format(iterator.next()));
                }
                skullMeta.setLore((List)lore);
            }
            final GameProfile value = new GameProfile(UUID.randomUUID(), (String)null);
            value.getProperties().put((Object)"textures", (Object)new Property("textures", new String(Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", str).getBytes()))));
            try {
                final Field declaredField = skullMeta.getClass().getDeclaredField("profile");
                declaredField.setAccessible(true);
                declaredField.set(skullMeta, value);
            }
            catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
                final Throwable t;
                t.printStackTrace();
            }
            InventoryUtils.addItemFlags((ItemMeta)skullMeta);
            itemStack.setItemMeta((ItemMeta)skullMeta);
            return itemStack;
        }
        final EnumMaterial enumMaterial = material.getEnumMaterial();
        ItemStack itemStack2 = new ItemStack(enumMaterial.getType(), 1, (short)enumMaterial.getData());
        Object itemMeta = itemStack2.getItemMeta();
        if (material.isColorableMaterial()) {
            itemMeta = itemStack2.getItemMeta();
            ((LeatherArmorMeta)itemMeta).setColor(material.getColor());
        }
        ((ItemMeta)itemMeta).setDisplayName(ChatUtil.format(s));
        if (list != null) {
            final ArrayList<String> lore2 = new ArrayList<String>();
            final Iterator<String> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                lore2.add(ChatUtil.format(iterator2.next()));
            }
            ((ItemMeta)itemMeta).setLore((List)lore2);
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(enumMaterial)) {
            itemStack2.setDurability((short)0);
        }
        InventoryUtils.addItemFlags((ItemMeta)itemMeta);
        itemStack2.setItemMeta((ItemMeta)itemMeta);
        if (material.getEnumMaterial().isSpawnEgg() && VersionManager.is1_9OrAbove() && !VersionManager.is1_13OrAbove()) {
            itemStack2 = CookieGadgets.getNMSManager().spawnEgg(itemStack2, EntityType.fromId((int)enumMaterial.getData()).getName());
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(enumMaterial)) {
            itemStack2 = CookieGadgets.getNMSManager().getPotionFromId(itemStack2, material);
        }
        return itemStack2;
    }
    
    public static ItemStack itemSkull(final String s, final String s2) {
        return itemSkull(s, null, s2);
    }
    
    public static ItemStack itemSkull(final String s, final List<String> list, String string) {
        if (!string.startsWith("http://textures.minecraft.net/texture/")) {
            string = "http://textures.minecraft.net/texture/" + string;
        }
        final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), 1, (short)EnumMaterial.PLAYER_HEAD.getData());
        final SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setDisplayName(ChatUtil.format(s));
        final ArrayList<String> lore = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                lore.add(ChatUtil.format(iterator.next()));
            }
            skullMeta.setLore((List)lore);
        }
        if (string.isEmpty()) {
            return itemStack;
        }
        final GameProfile value = new GameProfile(UUID.randomUUID(), (String)null);
        value.getProperties().put((Object)"textures", (Object)new Property("textures", new String(Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", string).getBytes()))));
        try {
            final Field declaredField = skullMeta.getClass().getDeclaredField("profile");
            declaredField.setAccessible(true);
            declaredField.set(skullMeta, value);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            final Throwable t;
            t.printStackTrace();
        }
        InventoryUtils.addItemFlags((ItemMeta)skullMeta);
        itemStack.setItemMeta((ItemMeta)skullMeta);
        return itemStack;
    }
    
    public static ItemStack itemBanner(final String s, final GDyeColor gDyeColor, final List<Pattern> patterns, final List<String> list) {
        final ItemStack itemStack = new ItemStack(EnumMaterial.valueOf(String.valueOf(gDyeColor.name()) + "_BANNER").getType(), 1);
        final BannerMeta itemMeta = (BannerMeta)itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.format(s));
        if (gDyeColor != null) {
            itemMeta.setBaseColor(gDyeColor.getColor());
        }
        if (patterns != null) {
            itemMeta.setPatterns((List)patterns);
        }
        if (list != null) {
            final ArrayList<String> lore = new ArrayList<String>();
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                lore.add(ChatUtil.format(iterator.next()));
            }
            itemMeta.setLore((List)lore);
        }
        InventoryUtils.addItemFlags((ItemMeta)itemMeta);
        itemStack.setItemMeta((ItemMeta)itemMeta);
        return itemStack;
    }
    
    public static boolean getCurrentItem(final InventoryClickEvent inventoryClickEvent, final int n) {
        return inventoryClickEvent.getSlot() == n;
    }
    
    public static boolean getCurrentItem(final InventoryClickEvent inventoryClickEvent, final ItemStack itemStack) {
        return inventoryClickEvent.getCurrentItem().getType() == itemStack.getType() && inventoryClickEvent.getCurrentItem().getDurability() == itemStack.getDurability() && itemStack.hasItemMeta() && inventoryClickEvent.getCurrentItem().hasItemMeta() && itemStack.getItemMeta().hasDisplayName() && inventoryClickEvent.getCurrentItem().getItemMeta().hasDisplayName() && inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(itemStack.getItemMeta().getDisplayName()));
    }
    
    public static boolean getCurrentItem(final InventoryClickEvent inventoryClickEvent, final ItemStack itemStack, final int n) {
        return getCurrentItem(inventoryClickEvent, itemStack) && inventoryClickEvent.getSlot() == n;
    }
    
    public static boolean getCurrentItem(final InventoryClickEvent inventoryClickEvent, final String s, final int n) {
        return ChatUtil.correction(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName()).equals(ChatUtil.format(s)) && inventoryClickEvent.getSlot() == n;
    }
    
    public static boolean getCurrentItem(final InventoryClickEvent inventoryClickEvent, final String s, final GMaterial gMaterial, final int n) {
        return getCurrentItem(inventoryClickEvent, s, gMaterial.getEnumMaterial(), gMaterial.getData(), n);
    }
    
    public static boolean getCurrentItem(final InventoryClickEvent inventoryClickEvent, final String s, final EnumMaterial enumMaterial, final int n, final int n2) {
        return ChatUtil.correction(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName()).equals(ChatUtil.format(s)) && inventoryClickEvent.getCurrentItem().getType() == enumMaterial.getType() && inventoryClickEvent.getCurrentItem().getDurability() == n && inventoryClickEvent.getSlot() == n2;
    }
    
    public static boolean getCurrentItem(final InventoryClickEvent inventoryClickEvent, final String s, final GMaterial gMaterial) {
        return getCurrentItem(inventoryClickEvent, s, gMaterial.getEnumMaterial(), gMaterial.getData());
    }
    
    public static boolean getCurrentItem(final InventoryClickEvent inventoryClickEvent, final String s, final EnumMaterial enumMaterial, final int n) {
        return ChatUtil.correction(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName()).equals(ChatUtil.format(s)) && inventoryClickEvent.getCurrentItem().getType() == enumMaterial.getType() && inventoryClickEvent.getCurrentItem().getDurability() == n;
    }
}
