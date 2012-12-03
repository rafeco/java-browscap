package us.rafe.browscap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

public class Browscap {

    static Logger logger = LoggerFactory.getLogger(Browscap.class);

    private Map<String, BrowserCapabilities> browscap;
    private List<String> browscapsByLength;

    private static Browscap instance;

    public static Browscap getInstance() {
        if (null == instance) {
            instance = new Browscap();
        }

        return instance;
    }

    private Browscap() {
        Stopwatch timer = new Stopwatch();

        timer.start();
        HierarchicalINIConfiguration browscapIni = loadIniFile();

        if (null != browscapIni) {
            loadBrowscap(browscapIni);
        }

        logger.info("browsscap.ini loaded and processed " + browscap.size() + " entries in " + timer.elapsedMillis()
                + "ms");
    }

    private String digForProperty(String sectionName, String property, HierarchicalINIConfiguration browscapIni) {
        SubnodeConfiguration sectionValues = browscapIni.getSection(sectionName);

        String propertyValue = sectionValues.getString(property);

        if (null != propertyValue) {
            return propertyValue;
        } else {
            String parentSection = sectionValues.getString("Parent");

            if (null != parentSection) {
                return digForProperty(parentSection, property, browscapIni);
            } else {
                return null;
            }
        }
    }

    private void loadBrowscap(HierarchicalINIConfiguration browscapIni) {
        // LinkedHashMap because the order of the browscap file is meaningful
        this.browscap = new LinkedHashMap<String, BrowserCapabilities>();

        Map<String, Integer> thumbprintCounts = new HashMap<String, Integer>();

        for (Object sectionNameObj : browscapIni.getSections()) {
            String sectionName = (String) sectionNameObj;

            BrowserCapabilities browser = new BrowserCapabilities();

            browser.setBrowscapIdentifier(sectionName);
            browser.setBrowser(digForProperty(sectionName, "Browser", browscapIni));
            browser.setVersion(digForProperty(sectionName, "Version", browscapIni));
            browser.setMajorVer(digForProperty(sectionName, "MajorVer", browscapIni));
            browser.setMinorVer(digForProperty(sectionName, "MinorVer", browscapIni));
            browser.setPlatform(digForProperty(sectionName, "Platform", browscapIni));
            browser.setPlatformVersion(digForProperty(sectionName, "Platform_Version", browscapIni));
            browser.setPlatformDescription(digForProperty(sectionName, "Platform_Description", browscapIni));
            browser.setDeviceMaker(digForProperty(sectionName, "Device_Maker", browscapIni));
            browser.setDeviceName(digForProperty(sectionName, "Device_Name", browscapIni));
            browser.setParent(digForProperty(sectionName, "Parent", browscapIni));

            String isMobileDeviceValue = digForProperty(sectionName, "isMobileDevice", browscapIni);
            browser.setIsMobileDevice(null != isMobileDeviceValue ? Boolean.valueOf(isMobileDeviceValue) : null);
            String isTabletValue = digForProperty(sectionName, "isTablet", browscapIni);
            browser.setIsTablet(null != isTabletValue ? Boolean.valueOf(isTabletValue) : null);

            browscap.put(sectionName, browser);

            for (String thumbprint : browser.getLongestNonWildcards()) {
                if (null != thumbprintCounts.get(thumbprint)) {
                    thumbprintCounts.put(thumbprint, thumbprintCounts.get(thumbprint) + 1);
                } else {
                    thumbprintCounts.put(thumbprint, 1);
                }
            }
        }

        for (BrowserCapabilities bc : browscap.values()) {
            for (String thumbprint : bc.getLongestNonWildcards()) {
                if (null != bc.getThumbprint()) {
                    if (thumbprintCounts.get(thumbprint) < thumbprintCounts.get(bc.getThumbprint())) {
                        bc.setThumbprint(thumbprint);
                    }
                } else {
                    bc.setThumbprint(thumbprint);
                }
            }

        }

        this.browscapsByLength = ImmutableList.copyOf(Ordering.from(new DescendingStringLengthComparator()).sortedCopy(
                browscap.keySet()));
    }

    private HierarchicalINIConfiguration loadIniFile() {
        HierarchicalINIConfiguration browscapIni = null;

        try {
            browscapIni = new HierarchicalINIConfiguration(Thread.currentThread().getContextClassLoader()
                    .getResource("browscap.ini"));
        } catch (ConfigurationException e) {
            throw new RuntimeException("Could not load INI file", e);
        }

        return browscapIni;
    }

    public BrowserCapabilities lookup(String ua) {
        Stopwatch timer = new Stopwatch().start();

        // Get the default browser.
        BrowserCapabilities matched = browscap.get("*");

        // If a direct lookup didn't work, we're going to have to use a
        // regex.
        for (String browscapIdentifier : browscapsByLength) {
            if (browscapIdentifier.equals("*")) {
                continue;
            }

            if (browscap.get(browscapIdentifier).matches(ua)) {
                matched = browscap.get(browscapIdentifier);
                break;
            }
        }

        logger.debug("browscap lookup took " + timer.elapsedMillis() + "ms");
        return matched;
    }

}
