

package ws.billy.CookieGadgets.utils.debug;

import java.util.Iterator;
import java.util.Scanner;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class PastebinPoster
{
    public static String paste(final String value, final String value2) {
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("api_dev_key", (Integer)"d37d4ff384e025ecc3bb3e1761e301fb");
        hashMap.put("api_option", (Integer)"paste");
        if (value2 != null) {
            hashMap.put("api_paste_code", (Integer)value2);
        }
        if (value != null) {
            hashMap.put("api_paste_name", (Integer)value);
        }
        hashMap.put("api_paste_format", (Integer)"yaml");
        hashMap.put("api_paste_expire_date", (Integer)"1W");
        hashMap.put("api_paste_private", 1);
        return post((HashMap<String, Object>)hashMap).get(0);
    }
    
    private static ArrayList<String> post(final HashMap<String, Object> hashMap) {
        try {
            final StringBuffer sb = new StringBuffer();
            for (final Map.Entry<String, Object> entry : hashMap.entrySet()) {
                sb.append("&" + entry.getKey() + "=" + entry.getValue());
            }
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("http://pastebin.com/api/api_post.php").openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("charset", "utf-8");
            final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes(sb.toString());
            dataOutputStream.flush();
            dataOutputStream.close();
            httpURLConnection.disconnect();
            final Scanner scanner = new Scanner(httpURLConnection.getInputStream());
            final ArrayList<String> list = new ArrayList<String>();
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
            scanner.close();
            if (list.get(0).startsWith("Bad API request")) {
                throw new Exception("Failed to create new paste: " + list.get(0));
            }
            return list;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
