package ws.billy.CookieGadgets.command.main;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.cosmetics.pets.PetItems;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import ws.billy.CookieGadgets.cosmetics.emotes.EmoteType;
import ws.billy.CookieGadgets.cosmetics.banners.BannerType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import ws.billy.CookieGadgets.cosmetics.particles.ParticleType;
import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHatType;
import ws.billy.CookieGadgets.cosmetics.hats.standard.HatType;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class AutoTabCompleter implements TabCompleter
{
    public List<String> onTabComplete(final CommandSender commandSender, final Command command, final String s, final String[] array) {
        if (array.length == 1) {
            final ArrayList<Comparable> list = (ArrayList<Comparable>)new ArrayList<String>();
            final ArrayList<String> list2 = new ArrayList<String>();
            list2.add("about");
            list2.add("addperm");
            list2.add("checkupdate");
            list2.add("equip");
            list2.add("help");
            list2.add("main");
            list2.add("menu");
            list2.add("menuitem");
            list2.add("namepet");
            list2.add("permission");
            list2.add("petitems");
            list2.add("reload");
            list2.add("removeperm");
            list2.add("reset");
            list2.add("settings");
            list2.add("status");
            if (!array[0].equals("")) {
                for (final String e : list2) {
                    if (e.toLowerCase().startsWith(array[0].toLowerCase())) {
                        list.add(e);
                    }
                }
            }
            else {
                final Iterator<String> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    list.add(iterator2.next());
                }
            }
            Collections.sort(list);
            return (List<String>)list;
        }
        if (array.length == 2) {
            if (array[0].equalsIgnoreCase("help") || array[0].equalsIgnoreCase("h")) {
                final ArrayList<Comparable> list3 = (ArrayList<Comparable>)new ArrayList<String>();
                final ArrayList<String> list4 = new ArrayList<String>();
                list4.add("1");
                list4.add("2");
                if (!array[1].equals("")) {
                    for (final String e2 : list4) {
                        if (e2.toLowerCase().startsWith(array[1].toLowerCase())) {
                            list3.add(e2);
                        }
                    }
                }
                else {
                    final Iterator<String> iterator4 = list4.iterator();
                    while (iterator4.hasNext()) {
                        list3.add(iterator4.next());
                    }
                }
                Collections.sort(list3);
                return (List<String>)list3;
            }
            if (array[0].equalsIgnoreCase("permission") || array[0].equalsIgnoreCase("perm") || array[0].equalsIgnoreCase("permissions")) {
                final ArrayList<Comparable> list5 = (ArrayList<Comparable>)new ArrayList<String>();
                final ArrayList<String> list6 = new ArrayList<String>();
                list6.add("animated_hats");
                list6.add("banners");
                list6.add("cloaks");
                list6.add("commands");
                list6.add("emotes");
                list6.add("gadgets");
                list6.add("hats");
                list6.add("miniatures");
                list6.add("morphs");
                list6.add("particles");
                list6.add("pets");
                list6.add("suits");
                if (!array[1].equals("")) {
                    for (final String e3 : list6) {
                        if (e3.toLowerCase().startsWith(array[1].toLowerCase())) {
                            list5.add(e3);
                        }
                    }
                }
                else {
                    final Iterator<String> iterator6 = list6.iterator();
                    while (iterator6.hasNext()) {
                        list5.add(iterator6.next());
                    }
                }
                Collections.sort(list5);
                return (List<String>)list5;
            }
            if (array[0].equalsIgnoreCase("reset") || array[0].equalsIgnoreCase("unequip")) {
                final ArrayList<Comparable> list7 = (ArrayList<Comparable>)new ArrayList<String>();
                final ArrayList<String> list8 = new ArrayList<String>();
                list8.add("all");
                list8.add("animated_hats");
                list8.add("banners");
                list8.add("cloaks");
                list8.add("emotes");
                list8.add("gadgets");
                list8.add("hats");
                list8.add("miniatures");
                list8.add("morphs");
                list8.add("particles");
                list8.add("pets");
                list8.add("suits");
                if (!array[1].equals("")) {
                    for (final String e4 : list8) {
                        if (e4.toLowerCase().startsWith(array[1].toLowerCase())) {
                            list7.add(e4);
                        }
                    }
                }
                else {
                    final Iterator<String> iterator8 = list8.iterator();
                    while (iterator8.hasNext()) {
                        list7.add(iterator8.next());
                    }
                }
                Collections.sort(list7);
                return (List<String>)list7;
            }
            if (array[0].equalsIgnoreCase("menu") || array[0].equalsIgnoreCase("gui")) {
                final ArrayList<Comparable> list9 = (ArrayList<Comparable>)new ArrayList<String>();
                final ArrayList<String> list10 = new ArrayList<String>();
                list10.add("main");
                list10.add("animated_hats");
                list10.add("banners");
                list10.add("cloaks");
                list10.add("emotes");
                list10.add("gadgets");
                list10.add("hats");
                list10.add("miniatures");
                list10.add("morphs");
                list10.add("particles");
                list10.add("pets");
                list10.add("suits");
                if (!array[1].equals("")) {
                    for (final String e5 : list10) {
                        if (e5.toLowerCase().startsWith(array[1].toLowerCase())) {
                            list9.add(e5);
                        }
                    }
                }
                else {
                    final Iterator<String> iterator10 = list10.iterator();
                    while (iterator10.hasNext()) {
                        list9.add(iterator10.next());
                    }
                }
                Collections.sort(list9);
                return (List<String>)list9;
            }
            if (array[0].equalsIgnoreCase("equip")) {
                final ArrayList<Comparable> list11 = (ArrayList<Comparable>)new ArrayList<String>();
                final ArrayList<String> list12 = new ArrayList<String>();
                list12.add("animated_hat");
                list12.add("hat");
                list12.add("particle");
                list12.add("suit");
                list12.add("suithelmet");
                list12.add("suitchestplate");
                list12.add("suitleggings");
                list12.add("suitboots");
                list12.add("gadget");
                list12.add("pet");
                list12.add("miniature");
                list12.add("morph");
                list12.add("banner");
                list12.add("emote");
                list12.add("cloak");
                if (!array[1].equals("")) {
                    for (final String e6 : list12) {
                        if (e6.toLowerCase().startsWith(array[1].toLowerCase())) {
                            list11.add(e6);
                        }
                    }
                }
                else {
                    final Iterator<String> iterator12 = list12.iterator();
                    while (iterator12.hasNext()) {
                        list11.add(iterator12.next());
                    }
                }
                Collections.sort(list11);
                return (List<String>)list11;
            }
            if (array[0].equalsIgnoreCase("addpermission") || array[0].equalsIgnoreCase("addperm") || array[0].equalsIgnoreCase("ap") || array[0].equalsIgnoreCase("removepermission") || array[0].equalsIgnoreCase("removeperm") || array[0].equalsIgnoreCase("rp")) {
                final ArrayList<Comparable> list13 = (ArrayList<Comparable>)new ArrayList<String>();
                final ArrayList<String> list14 = new ArrayList<String>();
                list14.add("animated_hat");
                list14.add("hat");
                list14.add("particle");
                list14.add("suithelmet");
                list14.add("suitchestplate");
                list14.add("suitleggings");
                list14.add("suitboots");
                list14.add("gadget");
                list14.add("pet");
                list14.add("miniature");
                list14.add("morph");
                list14.add("banner");
                list14.add("emote");
                list14.add("cloak");
                if (!array[1].equals("")) {
                    for (final String e7 : list14) {
                        if (e7.toLowerCase().startsWith(array[1].toLowerCase())) {
                            list13.add(e7);
                        }
                    }
                }
                else {
                    final Iterator<String> iterator14 = list14.iterator();
                    while (iterator14.hasNext()) {
                        list13.add(iterator14.next());
                    }
                }
                Collections.sort(list13);
                return (List<String>)list13;
            }
            if (array[0].equalsIgnoreCase("setting") || array[0].equalsIgnoreCase("settings")) {
                final ArrayList<Comparable> list15 = (ArrayList<Comparable>)new ArrayList<String>();
                final ArrayList<String> list16 = new ArrayList<String>();
                list16.add("bypasscooldown");
                list16.add("selfmorphview");
                if (!array[1].equals("")) {
                    for (final String e8 : list16) {
                        if (e8.toLowerCase().startsWith(array[1].toLowerCase())) {
                            list15.add(e8);
                        }
                    }
                }
                else {
                    final Iterator<String> iterator16 = list16.iterator();
                    while (iterator16.hasNext()) {
                        list15.add(iterator16.next());
                    }
                }
                Collections.sort(list15);
                return (List<String>)list15;
            }
            if (array[0].equalsIgnoreCase("petitems") || array[0].equalsIgnoreCase("petitem")) {
                final ArrayList<Comparable> list17 = (ArrayList<Comparable>)new ArrayList<String>();
                final ArrayList<String> list18 = new ArrayList<String>();
                list18.add("add");
                list18.add("check");
                list18.add("remove");
                list18.add("set");
                if (!array[1].equals("")) {
                    for (final String e9 : list18) {
                        if (e9.toLowerCase().startsWith(array[1].toLowerCase())) {
                            list17.add(e9);
                        }
                    }
                }
                else {
                    final Iterator<String> iterator18 = list18.iterator();
                    while (iterator18.hasNext()) {
                        list17.add(iterator18.next());
                    }
                }
                Collections.sort(list17);
                return (List<String>)list17;
            }
        }
        else if (array.length == 3) {
            if (array[0].equalsIgnoreCase("equip") || array[0].equalsIgnoreCase("addpermission") || array[0].equalsIgnoreCase("addperm") || array[0].equalsIgnoreCase("ap") || array[0].equalsIgnoreCase("removepermission") || array[0].equalsIgnoreCase("removeperm") || array[0].equalsIgnoreCase("rp")) {
                if (array[1].equalsIgnoreCase("hat")) {
                    final ArrayList<Comparable> list19 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list20 = new ArrayList<String>();
                    for (int i = 0; i < HatType.enabled().size(); ++i) {
                        list20.add(HatType.enabled().get(i).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e10 : list20) {
                            if (e10.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list19.add(e10);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator20 = list20.iterator();
                        while (iterator20.hasNext()) {
                            list19.add(iterator20.next());
                        }
                    }
                    Collections.sort(list19);
                    return (List<String>)list19;
                }
                if (array[1].equalsIgnoreCase("animated_hat")) {
                    final ArrayList<Comparable> list21 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list22 = new ArrayList<String>();
                    for (int j = 0; j < AnimatedHatType.enabled().size(); ++j) {
                        list22.add(AnimatedHatType.enabled().get(j).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e11 : list22) {
                            if (e11.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list21.add(e11);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator22 = list22.iterator();
                        while (iterator22.hasNext()) {
                            list21.add(iterator22.next());
                        }
                    }
                    Collections.sort(list21);
                    return (List<String>)list21;
                }
                if (array[1].equalsIgnoreCase("particle")) {
                    final ArrayList<Comparable> list23 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list24 = new ArrayList<String>();
                    for (int k = 0; k < ParticleType.enabled().size(); ++k) {
                        list24.add(ParticleType.enabled().get(k).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e12 : list24) {
                            if (e12.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list23.add(e12);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator24 = list24.iterator();
                        while (iterator24.hasNext()) {
                            list23.add(iterator24.next());
                        }
                    }
                    Collections.sort(list23);
                    return (List<String>)list23;
                }
                if (array[1].equalsIgnoreCase("suit")) {
                    final ArrayList<Comparable> list25 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list26 = new ArrayList<String>();
                    for (int l = 0; l < SuitType.enabled().size(); ++l) {
                        list26.add(SuitType.enabled().get(l).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e13 : list26) {
                            if (e13.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list25.add(e13);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator26 = list26.iterator();
                        while (iterator26.hasNext()) {
                            list25.add(iterator26.next());
                        }
                    }
                    Collections.sort(list25);
                    return (List<String>)list25;
                }
                if (array[1].equalsIgnoreCase("suitHelmet")) {
                    final ArrayList<Comparable> list27 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list28 = new ArrayList<String>();
                    for (int n = 0; n < SuitType.enabled().size(); ++n) {
                        list28.add(String.valueOf(SuitType.enabled().get(n).toString()) + "_Helmet");
                    }
                    if (!array[2].equals("")) {
                        for (final String e14 : list28) {
                            if (e14.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list27.add(e14);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator28 = list28.iterator();
                        while (iterator28.hasNext()) {
                            list27.add(iterator28.next());
                        }
                    }
                    Collections.sort(list27);
                    return (List<String>)list27;
                }
                if (array[1].equalsIgnoreCase("suitChestplate")) {
                    final ArrayList<Comparable> list29 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list30 = new ArrayList<String>();
                    for (int n2 = 0; n2 < SuitType.enabled().size(); ++n2) {
                        list30.add(String.valueOf(SuitType.enabled().get(n2).toString()) + "_Chestplate");
                    }
                    if (!array[2].equals("")) {
                        for (final String e15 : list30) {
                            if (e15.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list29.add(e15);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator30 = list30.iterator();
                        while (iterator30.hasNext()) {
                            list29.add(iterator30.next());
                        }
                    }
                    Collections.sort(list29);
                    return (List<String>)list29;
                }
                if (array[1].equalsIgnoreCase("suitLeggings")) {
                    final ArrayList<Comparable> list31 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list32 = new ArrayList<String>();
                    for (int n3 = 0; n3 < SuitType.enabled().size(); ++n3) {
                        list32.add(String.valueOf(SuitType.enabled().get(n3).toString()) + "_Leggings");
                    }
                    if (!array[2].equals("")) {
                        for (final String e16 : list32) {
                            if (e16.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list31.add(e16);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator32 = list32.iterator();
                        while (iterator32.hasNext()) {
                            list31.add(iterator32.next());
                        }
                    }
                    Collections.sort(list31);
                    return (List<String>)list31;
                }
                if (array[1].equalsIgnoreCase("suitBoots")) {
                    final ArrayList<Comparable> list33 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list34 = new ArrayList<String>();
                    for (int n4 = 0; n4 < SuitType.enabled().size(); ++n4) {
                        list34.add(String.valueOf(SuitType.enabled().get(n4).toString()) + "_Boots");
                    }
                    if (!array[2].equals("")) {
                        for (final String e17 : list34) {
                            if (e17.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list33.add(e17);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator34 = list34.iterator();
                        while (iterator34.hasNext()) {
                            list33.add(iterator34.next());
                        }
                    }
                    Collections.sort(list33);
                    return (List<String>)list33;
                }
                if (array[1].equalsIgnoreCase("gadget")) {
                    final ArrayList<Comparable> list35 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list36 = new ArrayList<String>();
                    for (int n5 = 0; n5 < GadgetType.enabled().size(); ++n5) {
                        list36.add(GadgetType.enabled().get(n5).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e18 : list36) {
                            if (e18.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list35.add(e18);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator36 = list36.iterator();
                        while (iterator36.hasNext()) {
                            list35.add(iterator36.next());
                        }
                    }
                    Collections.sort(list35);
                    return (List<String>)list35;
                }
                if (array[1].equalsIgnoreCase("pet")) {
                    final ArrayList<Comparable> list37 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list38 = new ArrayList<String>();
                    for (int n6 = 0; n6 < PetType.enabled().size(); ++n6) {
                        list38.add(PetType.enabled().get(n6).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e19 : list38) {
                            if (e19.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list37.add(e19);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator38 = list38.iterator();
                        while (iterator38.hasNext()) {
                            list37.add(iterator38.next());
                        }
                    }
                    Collections.sort(list37);
                    return (List<String>)list37;
                }
                if (array[1].equalsIgnoreCase("miniature")) {
                    final ArrayList<Comparable> list39 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list40 = new ArrayList<String>();
                    for (int n7 = 0; n7 < MiniatureType.enabled().size(); ++n7) {
                        list40.add(MiniatureType.enabled().get(n7).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e20 : list40) {
                            if (e20.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list39.add(e20);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator40 = list40.iterator();
                        while (iterator40.hasNext()) {
                            list39.add(iterator40.next());
                        }
                    }
                    Collections.sort(list39);
                    return (List<String>)list39;
                }
                if (array[1].equalsIgnoreCase("morph")) {
                    final ArrayList<Comparable> list41 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list42 = new ArrayList<String>();
                    for (int n8 = 0; n8 < MorphType.enabled().size(); ++n8) {
                        list42.add(MorphType.enabled().get(n8).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e21 : list42) {
                            if (e21.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list41.add(e21);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator42 = list42.iterator();
                        while (iterator42.hasNext()) {
                            list41.add(iterator42.next());
                        }
                    }
                    Collections.sort(list41);
                    return (List<String>)list41;
                }
                if (array[1].equalsIgnoreCase("banner")) {
                    final ArrayList<Comparable> list43 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list44 = new ArrayList<String>();
                    for (int n9 = 0; n9 < BannerType.enabled().size(); ++n9) {
                        list44.add(BannerType.enabled().get(n9).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e22 : list44) {
                            if (e22.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list43.add(e22);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator44 = list44.iterator();
                        while (iterator44.hasNext()) {
                            list43.add(iterator44.next());
                        }
                    }
                    Collections.sort(list43);
                    return (List<String>)list43;
                }
                if (array[1].equalsIgnoreCase("emote")) {
                    final ArrayList<Comparable> list45 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list46 = new ArrayList<String>();
                    for (int n10 = 0; n10 < EmoteType.enabled().size(); ++n10) {
                        list46.add(EmoteType.enabled().get(n10).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e23 : list46) {
                            if (e23.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list45.add(e23);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator46 = list46.iterator();
                        while (iterator46.hasNext()) {
                            list45.add(iterator46.next());
                        }
                    }
                    Collections.sort(list45);
                    return (List<String>)list45;
                }
                if (array[1].equalsIgnoreCase("cloak")) {
                    final ArrayList<Comparable> list47 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list48 = new ArrayList<String>();
                    for (int n11 = 0; n11 < CloakType.enabled().size(); ++n11) {
                        list48.add(CloakType.enabled().get(n11).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e24 : list48) {
                            if (e24.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list47.add(e24);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator48 = list48.iterator();
                        while (iterator48.hasNext()) {
                            list47.add(iterator48.next());
                        }
                    }
                    Collections.sort(list47);
                    return (List<String>)list47;
                }
            }
            else {
                if (!(commandSender instanceof Player) && (array[0].equalsIgnoreCase("renamepet") || array[0].equalsIgnoreCase("namepet"))) {
                    final ArrayList<Comparable> list49 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list50 = new ArrayList<String>();
                    for (int n12 = 0; n12 < PetType.enabled().size(); ++n12) {
                        list50.add(PetType.enabled().get(n12).toString().replace(" ", "_"));
                    }
                    if (!array[2].equals("")) {
                        for (final String e25 : list50) {
                            if (e25.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list49.add(e25);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator50 = list50.iterator();
                        while (iterator50.hasNext()) {
                            list49.add(iterator50.next());
                        }
                    }
                    Collections.sort(list49);
                    return (List<String>)list49;
                }
                if ((array[0].equalsIgnoreCase("petitems") || array[0].equalsIgnoreCase("petitem")) && (array[1].equalsIgnoreCase("add") || array[1].equalsIgnoreCase("remove") || array[1].equalsIgnoreCase("set"))) {
                    final ArrayList<Comparable> list51 = (ArrayList<Comparable>)new ArrayList<String>();
                    final ArrayList<String> list52 = new ArrayList<String>();
                    PetItems[] values;
                    for (int length = (values = PetItems.values()).length, n13 = 0; n13 < length; ++n13) {
                        list52.add(values[n13].getSQLIndex().toString());
                    }
                    if (!array[2].equals("")) {
                        for (final String e26 : list52) {
                            if (e26.toLowerCase().startsWith(array[2].toLowerCase())) {
                                list51.add(e26);
                            }
                        }
                    }
                    else {
                        final Iterator<String> iterator52 = list52.iterator();
                        while (iterator52.hasNext()) {
                            list51.add(iterator52.next());
                        }
                    }
                    Collections.sort(list51);
                    return (List<String>)list51;
                }
            }
        }
        return null;
    }
}
