package us.rafe.browscap;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserCapabilities {
    static Logger logger = LoggerFactory.getLogger(BrowserCapabilities.class);

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BrowserCapabilities.logger = logger;
    }

    private String browser;
    private Pattern regex;
    private String thumbprint;
    private String leftMatcher;
    private String browscapIdentifier;
    private String version;
    private String majorVer;
    private String minorVer;
    private String parent;
    private String platform;
    private String platformVersion;
    private String platformDescription;
    private String deviceName;
    private String deviceMaker;
    private Boolean isMobileDevice;

    private String extractLeftMatcher(String browscapIdentifier) {
        Pattern matchLeft = Pattern.compile("^[^?*]*");

        Matcher matcher = matchLeft.matcher(browscapIdentifier);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return "";
        }

    }

    public String getBrowscapIdentifier() {
        return browscapIdentifier;
    }

    public String getBrowser() {
        return browser;
    }

    public String getDeviceMaker() {
        return deviceMaker;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public Boolean getIsMobileDevice() {
        return isMobileDevice;
    }

    public Set<String> getLongestNonWildcards() {
        Set<String> thumbprints = new HashSet<String>();

        Pattern noWildcards = Pattern.compile("[^?*]*");

        Matcher matcher = noWildcards.matcher(this.browscapIdentifier);

        while (matcher.find()) {
            if (matcher.group().trim().length() < 2) {
                continue;
            }

            if (!thumbprints.contains(matcher.group())) {
                thumbprints.add(matcher.group());
            }
        }

        return thumbprints;
    }

    public String getMajorVer() {
        return majorVer;
    }

    public String getMinorVer() {
        return minorVer;
    }

    public String getParent() {
        return parent;
    }

    public String getPlatform() {
        return platform;
    }

    public String getPlatformDescription() {
        return platformDescription;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getThumbprint() {
        return thumbprint;
    }

    public String getVersion() {
        return version;
    }

    private String identiferToRegex(String identifier) {
        if (null == identifier) {
            return null;
        }

        String regex = identifier.replaceAll("[.()]", "\\\\Q$0\\\\E").replaceAll("\\?", ".").replaceAll("\\*", ".*?");

        return regex;
    }

    public boolean matches(String userAgent) {
        if (null != userAgent && userAgent.equals(browscapIdentifier)) {
            return true;
        }

        if (!userAgent.startsWith(this.leftMatcher)) {
            return false;
        }

        if (!(userAgent.indexOf(this.thumbprint) > -1)) {
            return false;
        }

        if (null == regex) {
            return false;
        }

        Matcher matcher = regex.matcher(userAgent);

        if (matcher.matches()) {
            return true;
        }

        return false;
    }

    public void setBrowscapIdentifier(String browscapIdentifier) {
        this.browscapIdentifier = browscapIdentifier;

        this.leftMatcher = extractLeftMatcher(browscapIdentifier);

        // Go ahead and set the regex as well.
        this.regex = Pattern.compile(identiferToRegex(browscapIdentifier));
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setDeviceMaker(String deviceMaker) {
        this.deviceMaker = deviceMaker;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setIsMobileDevice(Boolean isMobileDevice) {
        this.isMobileDevice = isMobileDevice;
    }

    public void setMajorVer(String majorVer) {
        this.majorVer = majorVer;
    }

    public void setMinorVer(String minorVer) {
        this.minorVer = minorVer;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setPlatformDescription(String platformDescription) {
        this.platformDescription = platformDescription;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public void setThumbprint(String thumbprint) {
        this.thumbprint = thumbprint;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return this.browser + " " + this.version + " (" + this.platform + ")";
    }

}
