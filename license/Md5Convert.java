

package ws.billy.CookieGadgets.license;

import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Convert
{
    public static String getMd5(final String s) {
        try {
            String str;
            for (str = new BigInteger(1, MessageDigest.getInstance("MD5").digest(s.getBytes())).toString(16); str.length() < 32; str = "0" + str) {}
            return str;
        }
        catch (NoSuchAlgorithmException cause) {
            throw new RuntimeException(cause);
        }
    }
}
