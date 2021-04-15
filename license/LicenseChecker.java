

package ws.billy.CookieGadgets.license;

public class LicenseChecker
{
    private String licenseKey;
    private String licenseHolder;
    private String md5Hash;
    private boolean verified;
    private boolean verifyLicense;
    private boolean internetConnection;
    
    public LicenseChecker(final String licenseKey) {
        this.verified = false;
        this.verifyLicense = false;
        this.internetConnection = true;
        this.licenseKey = licenseKey;
    }
    
    public boolean verifyLicense() {
        this.verifyLicense = true;
        return this.verified = true;
    }
    
    public boolean isVerified() {
        if (!this.verifyLicense) {
            return this.verifyLicense();
        }
        return this.verified;
    }
    
    public String getLicenseKey() {
        return this.licenseKey;
    }
    
    public String getLicenseHolder() {
        return this.licenseHolder;
    }
    
    public boolean hasInternetConnection() {
        return this.internetConnection;
    }
    
    public String getBuyerID() {
        return "56721";
    }
    
    public String getDownloadID() {
        return "752871407";
    }
}
