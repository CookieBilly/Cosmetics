

package ws.billy.CookieGadgets.utils.items;

import org.bukkit.inventory.ItemFlag;
import java.lang.reflect.Field;
import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.utils.EnumPotion;
import ws.billy.CookieGadgets.utils.VersionManager;
import com.mojang.authlib.properties.Property;
import java.util.Base64;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import ws.billy.CookieGadgets.utils.GlowUtil;
import ws.billy.CookieGadgets.utils.EnumItem;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.block.banner.Pattern;
import ws.billy.CookieGadgets.utils.GDyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.Iterator;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import ws.billy.CookieGadgets.utils.ChatUtil;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.inventory.Inventory;

public class InventoryUtils
{
    public static void fillItems(final Inventory inventory) {
        if (CookieGadgets.getCookieGadgetsData().isFillEmptySlotWithItemEnabled()) {
            for (int i = 0; i < inventory.getSize(); ++i) {
                if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) {
                    inventory.setItem(i, CookieGadgets.getCookieGadgetsData().getFillEmptySlotItemStack());
                }
            }
        }
    }
    
    public static int findAvailableSlot(int i, final int n) {
        if (i > n) {
            while (i > n) {
                i -= 9;
            }
            return i;
        }
        return i;
    }
    
    public static int findSlotAboveItem(final int n, final int n2) {
        if (n - 9 <= n2 && n - 9 >= 0) {
            return n - 9;
        }
        if (n > n2 || n + 9 > n2) {
            return 0;
        }
        return n + 9;
    }
    
    public static int findSlotBelowItem(final int n, final int n2) {
        if (n <= n2 && n + 9 <= n2) {
            return n + 9;
        }
        if (n - 9 > n2 || n - 9 < 0) {
            return 0;
        }
        return n - 9;
    }
    
    public static ItemStack inventory(final Inventory inventory, final ItemStack itemStack, final int n) {
        inventory.setItem(n, itemStack);
        return itemStack;
    }
    
    public static ItemStack inventory(final Inventory inventory, final ItemStack itemStack, final String target, final String replacement, final int n) {
        final ItemStack clone = itemStack.clone();
        final ItemMeta itemMeta = clone.getItemMeta();
        if (itemMeta.getLore() != null) {
            final ArrayList<String> lore = new ArrayList<String>();
            final Iterator iterator = itemMeta.getLore().iterator();
            while (iterator.hasNext()) {
                lore.add(ChatUtil.format(iterator.next().replace(target, replacement)));
            }
            itemMeta.setLore((List)lore);
        }
        clone.setItemMeta(itemMeta);
        inventory.setItem(n, clone);
        return clone;
    }
    
    public static ItemStack inventory(final Inventory inventory, final ItemStack itemStack, final List<String> list, final List<String> list2, final int n) {
        final ItemStack clone = itemStack.clone();
        final ItemMeta itemMeta = clone.getItemMeta();
        if (itemMeta.getLore() != null) {
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
        addItemFlags(itemMeta);
        clone.setItemMeta(itemMeta);
        inventory.setItem(n, clone);
        return clone;
    }
    
    public static ItemStack inventory(final Inventory inventory, final ItemStack itemStack, final String s, final String target, final String replacement, final int n) {
        final ItemStack clone = itemStack.clone();
        final ItemMeta itemMeta = clone.getItemMeta();
        if (s != null) {
            final List lore = itemMeta.getLore();
            lore.add(ChatUtil.format(s.replace(target, replacement)));
            itemMeta.setLore(lore);
        }
        addItemFlags(itemMeta);
        clone.setItemMeta(itemMeta);
        inventory.setItem(n, clone);
        return clone;
    }
    
    public static ItemStack inventory(final Inventory inventory, final String s, final GMaterial gMaterial, final List<String> list, final int n) {
        return inventory(inventory, s, gMaterial, 1, list, n);
    }
    
    public static ItemStack inventory(final Inventory inventory, final String s, final GMaterial gMaterial, final int n, final List<String> list, final int n2) {
        return inventory(inventory, s, gMaterial, n, list, null, null, n2);
    }
    
    public static ItemStack inventory(final Inventory inventory, final String s, final GMaterial gMaterial, final List<String> list, final List<String> list2, final int n) {
        return inventory(inventory, s, gMaterial, 1, list, list2, null, n);
    }
    
    public static ItemStack inventory(final Inventory inventory, final String s, final GMaterial gMaterial, final int n, final List<String> list, final List<String> list2, final int n2) {
        return inventory(inventory, s, gMaterial, n, list, list2, null, n2);
    }
    
    public static ItemStack inventory(final Inventory inventory, final String s, final GMaterial gMaterial, final List<String> list, final List<String> list2, final List<String> list3, final int n) {
        return inventory(inventory, s, gMaterial, 1, list, list2, list3, n);
    }
    
    public static ItemStack inventory(final Inventory inventory, final String s, final GMaterial gMaterial, final int n, final List<String> list, final List<String> list2, final List<String> list3, final int n2) {
        if (gMaterial.isSkullHead()) {
            String str = gMaterial.getTexture();
            if (!str.startsWith("http://textures.minecraft.net/texture/")) {
                str = "http://textures.minecraft.net/texture/" + str;
            }
            final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), n, (short)EnumMaterial.PLAYER_HEAD.getData());
            final SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
            skullMeta.setDisplayName(ChatUtil.format(s));
            final ArrayList<String> lore = new ArrayList<String>();
            if (list != null) {
                final Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    lore.add(ChatUtil.format(iterator.next()));
                }
            }
            if (list2 != null) {
                final Iterator<String> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    lore.add(ChatUtil.format(iterator2.next()));
                }
            }
            if (list3 != null) {
                final Iterator<String> iterator3 = list3.iterator();
                while (iterator3.hasNext()) {
                    lore.add(ChatUtil.format(iterator3.next()));
                }
            }
            if (list != null || list2 != null || list3 != null) {
                skullMeta.setLore((List)lore);
            }
            return skullData(inventory, itemStack, skullMeta, str, n2);
        }
        final EnumMaterial enumMaterial = gMaterial.getEnumMaterial();
        final ItemStack itemStack2 = new ItemStack(enumMaterial.getType(), n, (short)gMaterial.getData());
        Object itemMeta = itemStack2.getItemMeta();
        if (gMaterial.isColorableMaterial()) {
            itemMeta = itemStack2.getItemMeta();
            ((LeatherArmorMeta)itemMeta).setColor(gMaterial.getColor());
        }
        ((ItemMeta)itemMeta).setDisplayName(ChatUtil.format(s));
        final ArrayList<String> lore2 = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator4 = list.iterator();
            while (iterator4.hasNext()) {
                lore2.add(ChatUtil.format(iterator4.next()));
            }
        }
        if (list2 != null) {
            final Iterator<String> iterator5 = list2.iterator();
            while (iterator5.hasNext()) {
                lore2.add(ChatUtil.format(iterator5.next()));
            }
        }
        if (list3 != null) {
            final Iterator<String> iterator6 = list3.iterator();
            while (iterator6.hasNext()) {
                lore2.add(ChatUtil.format(iterator6.next()));
            }
        }
        if (list != null || list2 != null || list3 != null) {
            ((ItemMeta)itemMeta).setLore((List)lore2);
        }
        return itemData(inventory, enumMaterial, gMaterial.getData(), itemStack2, (ItemMeta)itemMeta, n2);
    }
    
    public static ItemStack inventoryAddGlow(final Inventory inventory, final String s, final GMaterial gMaterial, final List<String> list, final List<String> list2, final int n, final String s2) {
        if (gMaterial.isSkullHead()) {
            String str = gMaterial.getTexture();
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
            }
            if (list2 != null) {
                final Iterator<String> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    lore.add(ChatUtil.format(iterator2.next()));
                }
            }
            if (list != null || list2 != null) {
                skullMeta.setLore((List)lore);
            }
            return skullData(inventory, itemStack, skullMeta, str, lore, s, s2, n);
        }
        final EnumMaterial enumMaterial = gMaterial.getEnumMaterial();
        final ItemStack itemStack2 = new ItemStack(enumMaterial.getType(), 1, (short)gMaterial.getData());
        Object itemMeta = itemStack2.getItemMeta();
        if (gMaterial.isColorableMaterial()) {
            itemMeta = itemStack2.getItemMeta();
            ((LeatherArmorMeta)itemMeta).setColor(gMaterial.getColor());
        }
        ((ItemMeta)itemMeta).setDisplayName(ChatUtil.format(s));
        final ArrayList<String> lore2 = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator3 = list.iterator();
            while (iterator3.hasNext()) {
                lore2.add(ChatUtil.format(iterator3.next()));
            }
        }
        if (list2 != null) {
            final Iterator<String> iterator4 = list2.iterator();
            while (iterator4.hasNext()) {
                lore2.add(ChatUtil.format(iterator4.next()));
            }
        }
        if (list != null || list2 != null) {
            ((ItemMeta)itemMeta).setLore((List)lore2);
        }
        return itemData(inventory, enumMaterial, gMaterial.getData(), itemStack2, (ItemMeta)itemMeta, lore2, s, s2, n);
    }
    
    public static ItemStack inventorySkull(final Inventory inventory, final String s, final List<String> list, final List<String> list2, final Player player, final int n) {
        final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), 1, (short)EnumMaterial.PLAYER_HEAD.getData());
        final SkullMeta itemMeta = (SkullMeta)itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.format(s));
        itemMeta.setOwner(player.getName());
        final ArrayList<String> lore = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                lore.add(ChatUtil.format(iterator.next()));
            }
        }
        if (list2 != null) {
            final Iterator<String> iterator2 = list2.iterator();
            while (iterator2.hasNext()) {
                lore.add(ChatUtil.format(iterator2.next()));
            }
        }
        if (list != null || list2 != null) {
            itemMeta.setLore((List)lore);
        }
        addItemFlags((ItemMeta)itemMeta);
        itemStack.setItemMeta((ItemMeta)itemMeta);
        inventory.setItem(n, itemStack);
        return itemStack;
    }
    
    public static ItemStack inventoryBanner(final Inventory inventory, final String s, final GDyeColor gDyeColor, final List<Pattern> list, final List<String> list2, final List<String> list3, final int n) {
        return inventoryBanner(inventory, s, gDyeColor, list, list2, list3, null, n);
    }
    
    public static ItemStack inventoryBanner(final Inventory inventory, final String s, final GDyeColor gDyeColor, final List<Pattern> patterns, final List<String> list, final List<String> list2, final List<String> list3, final int n) {
        final ItemStack itemStack = new ItemStack(EnumMaterial.valueOf(String.valueOf(gDyeColor.name()) + "_BANNER").getType(), 1);
        final BannerMeta itemMeta = (BannerMeta)itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.format(s));
        if (gDyeColor != null) {
            itemMeta.setBaseColor(gDyeColor.getColor());
        }
        if (patterns != null) {
            itemMeta.setPatterns((List)patterns);
        }
        final ArrayList<String> lore = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                lore.add(ChatUtil.format(iterator.next()));
            }
        }
        if (list2 != null) {
            final Iterator<String> iterator2 = list2.iterator();
            while (iterator2.hasNext()) {
                lore.add(ChatUtil.format(iterator2.next()));
            }
        }
        if (list3 != null) {
            final Iterator<String> iterator3 = list3.iterator();
            while (iterator3.hasNext()) {
                lore.add(ChatUtil.format(iterator3.next()));
            }
        }
        if (list != null || list2 != null || list3 != null) {
            itemMeta.setLore((List)lore);
        }
        addItemFlags((ItemMeta)itemMeta);
        itemStack.setItemMeta((ItemMeta)itemMeta);
        inventory.setItem(n, itemStack);
        return itemStack;
    }
    
    public static ItemStack inventoryBannerAddGlow(final Inventory inventory, final String s, final GDyeColor gDyeColor, final List<Pattern> patterns, final List<String> list, final List<String> list2, final int n, final String s2) {
        ItemStack addGlow = new ItemStack(EnumMaterial.valueOf(String.valueOf(gDyeColor.name()) + "_BANNER").getType(), 1);
        final BannerMeta itemMeta = (BannerMeta)addGlow.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.format(s));
        if (gDyeColor != null) {
            itemMeta.setBaseColor(gDyeColor.getColor());
        }
        if (patterns != null) {
            itemMeta.setPatterns((List)patterns);
        }
        final ArrayList<String> lore = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                lore.add(ChatUtil.format(iterator.next()));
            }
        }
        if (list2 != null) {
            final Iterator<String> iterator2 = list2.iterator();
            while (iterator2.hasNext()) {
                lore.add(ChatUtil.format(iterator2.next()));
            }
        }
        if (list != null || list2 != null) {
            itemMeta.setLore((List)lore);
        }
        addItemFlags((ItemMeta)itemMeta);
        addGlow.setItemMeta((ItemMeta)itemMeta);
        if (s2 != null && s2.equals(ChatUtil.format(s))) {
            if (EnumItem.ALREADY_SELECTED.isShowInLore()) {
                final Iterator<String> iterator3 = EnumItem.ALREADY_SELECTED.getLore().iterator();
                while (iterator3.hasNext()) {
                    lore.add(ChatUtil.format(iterator3.next()));
                }
                itemMeta.setLore((List)lore);
                addGlow.setItemMeta((ItemMeta)itemMeta);
            }
            addGlow = GlowUtil.addGlow(addGlow);
        }
        else if (EnumItem.CLICK_TO_SELECT.isShowInLore()) {
            final Iterator<String> iterator4 = EnumItem.CLICK_TO_SELECT.getLore().iterator();
            while (iterator4.hasNext()) {
                lore.add(ChatUtil.format(iterator4.next()));
            }
            itemMeta.setLore((List)lore);
            addGlow.setItemMeta((ItemMeta)itemMeta);
        }
        inventory.setItem(n, addGlow);
        return addGlow;
    }
    
    public static ItemStack mainMenuButton(final Player player, final Inventory inventory, final int n) {
        final EnumItem main_MENU_ITEM = EnumItem.MAIN_MENU_ITEM;
        if (main_MENU_ITEM.getMaterial().isSkullHead()) {
            String str = main_MENU_ITEM.getMaterial().getTexture();
            if (!str.startsWith("http://textures.minecraft.net/texture/")) {
                str = "http://textures.minecraft.net/texture/" + str;
            }
            final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), 1, (short)EnumMaterial.PLAYER_HEAD.getData());
            final SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
            skullMeta.setDisplayName(ChatUtil.format(main_MENU_ITEM.getDisplayName()));
            if (main_MENU_ITEM.getLore() != null) {
                final ArrayList<String> lore = new ArrayList<String>();
                final Iterator<String> iterator = main_MENU_ITEM.getLore().iterator();
                while (iterator.hasNext()) {
                    lore.add(ChatUtil.format(iterator.next().replace("{MYSTERY_DUST}", String.valueOf(CookieGadgets.getPlayerManager(player).getMysteryDust())).replace("{MYSTERY_BOXES}", String.valueOf(CookieGadgets.getPlayerManager(player).getMysteryBoxes()))));
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
            addItemFlags((ItemMeta)skullMeta);
            itemStack.setItemMeta((ItemMeta)skullMeta);
            inventory.setItem(n, itemStack);
            return itemStack;
        }
        ItemStack itemStack2 = new ItemStack(main_MENU_ITEM.getMaterial().getEnumMaterial().getType(), 1, (short)main_MENU_ITEM.getMaterial().getData());
        Object itemMeta = itemStack2.getItemMeta();
        if (main_MENU_ITEM.getMaterial().isColorableMaterial()) {
            itemMeta = itemStack2.getItemMeta();
            ((LeatherArmorMeta)itemMeta).setColor(main_MENU_ITEM.getMaterial().getColor());
        }
        ((ItemMeta)itemMeta).setDisplayName(ChatUtil.format(main_MENU_ITEM.getDisplayName()));
        if (main_MENU_ITEM.getLore() != null) {
            final ArrayList<String> lore2 = new ArrayList<String>();
            final Iterator<String> iterator2 = main_MENU_ITEM.getLore().iterator();
            while (iterator2.hasNext()) {
                lore2.add(ChatUtil.format(iterator2.next().replace("{MYSTERY_DUST}", String.valueOf(CookieGadgets.getPlayerManager(player).getMysteryDust())).replace("{MYSTERY_BOXES}", String.valueOf(CookieGadgets.getPlayerManager(player).getMysteryBoxes()))));
            }
            ((ItemMeta)itemMeta).setLore((List)lore2);
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(main_MENU_ITEM.getMaterial().getEnumMaterial())) {
            itemStack2.setDurability((short)0);
        }
        addItemFlags((ItemMeta)itemMeta);
        itemStack2.setItemMeta((ItemMeta)itemMeta);
        if (main_MENU_ITEM.getMaterial().getEnumMaterial().isSpawnEgg() && VersionManager.is1_9OrAbove() && !VersionManager.is1_13OrAbove()) {
            itemStack2 = CookieGadgets.getNMSManager().spawnEgg(itemStack2, EntityType.fromId(main_MENU_ITEM.getMaterial().getData()).getName());
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(main_MENU_ITEM.getMaterial().getEnumMaterial())) {
            itemStack2 = CookieGadgets.getNMSManager().getPotionFromId(itemStack2, main_MENU_ITEM.getMaterial());
        }
        inventory.setItem(n, itemStack2);
        return itemStack2;
    }
    
    public static ItemStack itemData(final Inventory inventory, final EnumMaterial material, final int data, ItemStack itemStack, final ItemMeta itemMeta, final int n) {
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(material)) {
            itemStack.setDurability((short)0);
        }
        addItemFlags(itemMeta);
        itemStack.setItemMeta(itemMeta);
        if (material.isSpawnEgg() && VersionManager.is1_9OrAbove() && !VersionManager.is1_13OrAbove()) {
            itemStack = CookieGadgets.getNMSManager().spawnEgg(itemStack, EntityType.fromId(data).getName());
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(material)) {
            itemStack = CookieGadgets.getNMSManager().getPotionFromId(itemStack, material, data);
        }
        inventory.setItem(n, itemStack);
        return itemStack;
    }
    
    public static ItemStack itemData(final Inventory inventory, final EnumMaterial material, final int data, ItemStack itemStack, final ItemMeta itemMeta, final ArrayList<String> list, final String s, final String s2, final int n) {
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(material)) {
            itemStack.setDurability((short)0);
        }
        addItemFlags(itemMeta);
        itemStack.setItemMeta(itemMeta);
        if (s2 != null && s2.equals(ChatUtil.format(s))) {
            if (EnumItem.ALREADY_SELECTED.isShowInLore()) {
                final Iterator<String> iterator = EnumItem.ALREADY_SELECTED.getLore().iterator();
                while (iterator.hasNext()) {
                    list.add(ChatUtil.format(iterator.next()));
                }
                itemMeta.setLore((List)list);
                itemStack.setItemMeta(itemMeta);
            }
            itemStack = GlowUtil.addGlow(itemStack);
        }
        else if (EnumItem.CLICK_TO_SELECT.isShowInLore()) {
            final Iterator<String> iterator2 = EnumItem.CLICK_TO_SELECT.getLore().iterator();
            while (iterator2.hasNext()) {
                list.add(ChatUtil.format(iterator2.next()));
            }
            itemMeta.setLore((List)list);
            itemStack.setItemMeta(itemMeta);
        }
        if (material.isSpawnEgg() && VersionManager.is1_9OrAbove() && !VersionManager.is1_13OrAbove()) {
            itemStack = CookieGadgets.getNMSManager().spawnEgg(itemStack, EntityType.fromId(data).getName());
        }
        if (VersionManager.is1_9OrAbove() && EnumPotion.isPotion(material)) {
            itemStack = CookieGadgets.getNMSManager().getPotionFromId(itemStack, material, data);
        }
        inventory.setItem(n, itemStack);
        return itemStack;
    }
    
    public static ItemStack skullData(final Inventory inventory, final ItemStack itemStack, final SkullMeta skullMeta, final String s, final int n) {
        final GameProfile value = new GameProfile(UUID.randomUUID(), (String)null);
        value.getProperties().put((Object)"textures", (Object)new Property("textures", new String(Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", s).getBytes()))));
        try {
            final Field declaredField = skullMeta.getClass().getDeclaredField("profile");
            declaredField.setAccessible(true);
            declaredField.set(skullMeta, value);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            final Throwable t;
            t.printStackTrace();
        }
        addItemFlags((ItemMeta)skullMeta);
        itemStack.setItemMeta((ItemMeta)skullMeta);
        inventory.setItem(n, itemStack);
        return itemStack;
    }
    
    public static ItemStack skullData(final Inventory inventory, final ItemStack itemStack, final SkullMeta skullMeta, final String s, final ArrayList<String> list, final String s2, final String s3, final int n) {
        if (s3 != null && s3.equals(ChatUtil.format(s2))) {
            if (EnumItem.ALREADY_SELECTED.isShowInLore()) {
                final Iterator<String> iterator = EnumItem.ALREADY_SELECTED.getLore().iterator();
                while (iterator.hasNext()) {
                    list.add(ChatUtil.format(iterator.next()));
                }
                skullMeta.setLore((List)list);
                itemStack.setItemMeta((ItemMeta)skullMeta);
            }
        }
        else if (EnumItem.CLICK_TO_SELECT.isShowInLore()) {
            final Iterator<String> iterator2 = EnumItem.CLICK_TO_SELECT.getLore().iterator();
            while (iterator2.hasNext()) {
                list.add(ChatUtil.format(iterator2.next()));
            }
            skullMeta.setLore((List)list);
            itemStack.setItemMeta((ItemMeta)skullMeta);
        }
        final GameProfile value = new GameProfile(UUID.randomUUID(), (String)null);
        value.getProperties().put((Object)"textures", (Object)new Property("textures", new String(Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", s).getBytes()))));
        try {
            final Field declaredField = skullMeta.getClass().getDeclaredField("profile");
            declaredField.setAccessible(true);
            declaredField.set(skullMeta, value);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            final Throwable t;
            t.printStackTrace();
        }
        addItemFlags((ItemMeta)skullMeta);
        itemStack.setItemMeta((ItemMeta)skullMeta);
        inventory.setItem(n, itemStack);
        return itemStack;
    }
    
    public static void addItemFlags(final ItemMeta itemMeta) {
        itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_DESTROYS });
        itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_PLACED_ON });
        itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
        itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_UNBREAKABLE });
    }
}
