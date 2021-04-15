

package ws.billy.CookieGadgets.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.OutputStream;

public class DownloadUtil
{
    public static String downloadFile(final String s, final OutputStream outputStream) {
        return downloadFile(s, "GET", outputStream);
    }
    
    public static String downloadFile(final String s, final String requestMethod, final OutputStream outputStream) {
        try {
            outputStream.flush();
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s.replace(" ", "%20")).openConnection();
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.connect();
            final int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                return "ERROR:" + responseCode;
            }
            final byte[] array = new byte[4096];
            int read;
            while ((read = httpURLConnection.getInputStream().read(array)) > 0) {
                outputStream.write(array, 0, read);
                outputStream.flush();
            }
            outputStream.close();
        }
        catch (MalformedURLException ex) {
            return "ERROR:500";
        }
        catch (ProtocolException ex2) {
            return "ERROR:500";
        }
        catch (IOException ex3) {
            return "ERROR:500";
        }
        return null;
    }
    
    public static String getFileContent(final String spec) {
        String next;
        try {
            next = new Scanner(new URL(spec).openStream()).useDelimiter("\\Z").next();
        }
        catch (MalformedURLException ex) {
            return "ERROR:500";
        }
        catch (IOException ex2) {
            return "ERROR:404";
        }
        catch (NoSuchElementException ex3) {
            return "ERROR";
        }
        return next;
    }
    
    public static List<String> getFileContents(final String spec) {
        final ArrayList<String> list = new ArrayList<String>();
        try {
            final Scanner useDelimiter = new Scanner(new URL(spec).openStream()).useDelimiter("\\Z");
            while (useDelimiter.hasNextLine()) {
                list.add(useDelimiter.nextLine());
            }
        }
        catch (MalformedURLException ex) {
            return list;
        }
        catch (IOException ex2) {
            return list;
        }
        catch (NoSuchElementException ex3) {}
        return list;
    }
    
    public static void touchURL(final String spec) {
        try {
            new URL(spec).openStream().close();
        }
        catch (MalformedURLException ex) {}
        catch (IOException ex2) {}
    }
    
    public static String checkErrorCodes(final String s, final boolean b) {
        if (!s.contains("ERROR")) {
            return null;
        }
        if (!b) {
            return null;
        }
        final String[] split = s.split(":");
        if (split[1].equalsIgnoreCase("404")) {
            return ChatUtil.format("&cError: Code " + split[1] + " &f- " + "&cThe required file don't exists!");
        }
        if (split[1].equalsIgnoreCase("500")) {
            return ChatUtil.format("&cError: Code " + split[1] + " &f- " + "&cPlease report it to plugin developer!");
        }
        return ChatUtil.format("&cUnknown connection error: " + split[1] + "!");
    }
}
