

package ws.billy.CookieGadgets.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import java.io.IOException;
import ws.billy.CookieGadgets.log.LoggerManager;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.common.base.Charsets;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import org.bukkit.configuration.ConfigurationSection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfiguration extends YamlConfiguration
{
    private Map<String, List<String>> comments;
    private boolean newLineAfterHeader;
    private boolean newLinePerKey;
    
    private CustomConfiguration() {
        this.comments = null;
        this.newLineAfterHeader = false;
        this.newLinePerKey = false;
        this.comments = new LinkedHashMap<String, List<String>>();
    }
    
    public void addDefault(final String s, final Object o, final String... array) {
        if (!this.contains(s)) {
            this.set(s, o, array);
        }
    }
    
    public ConfigurationSection createSection(final String s, final String... array) {
        if (s != null && array != null && array.length > 0) {
            final ArrayList<String> list = new ArrayList<String>();
            for (final String s2 : array) {
                if (s2 != null) {
                    list.add(s2);
                }
                else {
                    list.add("");
                }
            }
            this.comments.put(s, list);
        }
        return super.createSection(s);
    }
    
    public void addDefault(final String s, final Object o) {
        if (!this.contains(s)) {
            this.set(s, o);
        }
    }
    
    public void load(final File file) {
        super.load(file);
        BufferedReader bufferedReader = null;
        final ArrayList<Object> list = new ArrayList<Object>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        final boolean b = list.size() < 2 || !trim(list.get(1)).isEmpty();
        String s = null;
        int n = 0;
        boolean b2 = false;
        final LinkedHashMap<Object, List<?>> comments = new LinkedHashMap<Object, List<?>>();
        for (int i = 0; i < list.size(); ++i) {
            final String s2 = list.get(i);
            final String trimPrefixSpaces = trimPrefixSpaces(s2);
            if (trimPrefixSpaces.startsWith("#") && i >= 0 && i <= 10 && n == 0) {
                if (i == 0) {
                    b2 = true;
                }
                if (!b2) {
                    continue;
                }
                final String pathToComment = getPathToComment((List<String>)list, i, s2);
                if (s == null) {
                    s = pathToComment;
                    continue;
                }
                if (s.equals(pathToComment)) {
                    continue;
                }
                n = 1;
            }
            if (trimPrefixSpaces.startsWith("#") && (i > 0 || !b)) {
                final String pathToComment2 = getPathToComment((List<String>)list, i, s2);
                if (pathToComment2 != null) {
                    List<?> list2 = comments.get(pathToComment2);
                    if (list2 == null) {
                        list2 = new ArrayList<Object>();
                    }
                    list2.add(trimPrefixSpaces.substring(trimPrefixSpaces.startsWith("# ") ? 2 : 1));
                    comments.put(pathToComment2, list2);
                }
            }
        }
        this.comments = (Map<String, List<String>>)comments;
    }
    
    public void load(final File file, final boolean b) {
        if (b) {
            this.load(file);
        }
        else {
            super.load(file);
        }
    }
    
    public void save(final File file) {
        super.save(file);
        final ArrayList<Object> list = new ArrayList<Object>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8));
            bufferedWriter.write("");
            for (int i = 0; i < list.size(); ++i) {
                String csq = list.get(i);
                Object pathToKey = null;
                if (!csq.startsWith("#") && csq.contains(":")) {
                    pathToKey = getPathToKey((List<String>)list, i, csq);
                }
                if (pathToKey != null && this.comments.containsKey(pathToKey)) {
                    final int prefixSpaceCount = getPrefixSpaceCount(csq);
                    String string = "";
                    for (int j = 0; j < prefixSpaceCount; ++j) {
                        string = String.valueOf(string) + " ";
                    }
                    final List<String> list2 = this.comments.get(pathToKey);
                    if (list2 != null) {
                        final Iterator<String> iterator = list2.iterator();
                        while (iterator.hasNext()) {
                            bufferedWriter.append((CharSequence)string).append((CharSequence)"# ").append((CharSequence)iterator.next());
                            bufferedWriter.newLine();
                        }
                    }
                }
                if (csq.startsWith("# Storages: 'file' or 'mysql'.")) {
                    csq = "# Storages: 'sqlite' or 'mysql'.";
                }
                bufferedWriter.append((CharSequence)csq);
                bufferedWriter.newLine();
                final boolean startsWith = csq.startsWith("#");
                if (this.newLineAfterHeader && i == 0 && startsWith) {
                    bufferedWriter.newLine();
                }
                else if (this.newLinePerKey && i < list.size() - 1 && !startsWith) {
                    final String s = list.get(i + 1);
                    if (s != null && !s.startsWith(" ") && !s.startsWith("'") && !s.startsWith("-")) {
                        bufferedWriter.newLine();
                    }
                }
            }
        }
        finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
        if (bufferedWriter != null) {
            bufferedWriter.close();
        }
    }
    
    public void set(final String s, final Object o, final String... array) {
        if (o != null) {
            if (array != null) {
                if (array.length > 0) {
                    final ArrayList<String> list = new ArrayList<String>();
                    for (final String s2 : array) {
                        if (s2 != null) {
                            list.add(s2);
                        }
                        else {
                            list.add("");
                        }
                    }
                    this.comments.put(s, list);
                }
                else {
                    this.comments.remove(s);
                }
            }
        }
        else {
            this.comments.remove(s);
        }
        super.set(s, o);
    }
    
    public static CustomConfiguration loadConfiguration(final File obj) {
        final CustomConfiguration customConfiguration = new CustomConfiguration();
        try {
            customConfiguration.load(obj);
        }
        catch (FileNotFoundException ex3) {}
        catch (IOException ex) {
            LoggerManager.printLog(LoggerManager.LogLevel.SEVERE, "Cannot load " + obj, ex);
        }
        catch (InvalidConfigurationException ex2) {
            final File dest = new File(String.valueOf(obj.getAbsolutePath().replace(".yml", "")) + ".broken." + System.currentTimeMillis() + ".yml");
            obj.renameTo(dest);
            LoggerManager.printLog(LoggerManager.LogLevel.SEVERE, "The file " + obj.getName() + " is broken, it has been renamed to " + dest.getName(), ex2.getCause());
        }
        return customConfiguration;
    }
    
    private static String getPathToComment(final List<String> list, final int n, final String s) {
        if (list != null && n >= 0 && n < list.size() - 1 && s != null && trimPrefixSpaces(s).startsWith("#")) {
            int i = n;
            while (i < list.size() - 1) {
                ++i;
                final String s2 = list.get(i);
                final String trimPrefixSpaces = trimPrefixSpaces(s2);
                if (!trimPrefixSpaces.startsWith("#")) {
                    if (trimPrefixSpaces.contains(":")) {
                        return getPathToKey(list, i, s2);
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    private static String getPathToKey(final List<String> list, final int n, final String s) {
        if (list != null && n >= 0 && n < list.size() && s != null && !s.startsWith("#") && s.contains(":")) {
            final int prefixSpaceCount = getPrefixSpaceCount(s);
            String str = trimPrefixSpaces(s.substring(0, s.indexOf(58)));
            if (prefixSpaceCount > 0) {
                int i = n;
                int n2 = -1;
                int n3 = 0;
                while (i > 0) {
                    --i;
                    final String s2 = list.get(i);
                    final int prefixSpaceCount2 = getPrefixSpaceCount(s2);
                    if (trim(s2).isEmpty()) {
                        break;
                    }
                    if (trim(s2).startsWith("#") || prefixSpaceCount2 >= prefixSpaceCount || !s2.contains(":")) {
                        continue;
                    }
                    if (prefixSpaceCount2 <= 0 && n3 != 0) {
                        break;
                    }
                    if (prefixSpaceCount2 == 0) {
                        n3 = 1;
                    }
                    if (n2 != -1 && prefixSpaceCount2 >= n2) {
                        continue;
                    }
                    n2 = prefixSpaceCount2;
                    str = String.valueOf(trimPrefixSpaces(s2.substring(0, s2.indexOf(":")))) + "." + str;
                }
            }
            return str;
        }
        return null;
    }
    
    private static int getPrefixSpaceCount(final String s) {
        int n = 0;
        if (s != null && s.contains(" ")) {
            char[] charArray;
            for (int length = (charArray = s.toCharArray()).length, n2 = 0; n2 < length && charArray[n2] == ' '; ++n2) {
                ++n;
            }
        }
        return n;
    }
    
    private static String trim(final String s) {
        return (s != null) ? s.trim().replace(System.lineSeparator(), "") : "";
    }
    
    private static String trimPrefixSpaces(String substring) {
        if (substring != null) {
            while (substring.startsWith(" ")) {
                substring = substring.substring(1);
            }
        }
        return substring;
    }
}
