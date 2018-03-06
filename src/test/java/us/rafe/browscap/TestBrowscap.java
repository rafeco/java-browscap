package us.rafe.browscap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class TestBrowscap {
    static Logger logger = LoggerFactory.getLogger(TestBrowscap.class);


    @Test
    public void testDefault() {
        String ua = "Not a real browser";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Default Browser", bc.getBrowser());
        assertEquals("0.0", bc.getVersion());
        assertEquals(Boolean.FALSE, bc.getIsMobileDevice());
        assertEquals("unknown", bc.getPlatform());
        assertEquals(Boolean.FALSE, bc.getIsTablet());
    }

    @Test
    public void testAndroidGalaxy() {
        String ua = "Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Android", bc.getBrowser());
        assertEquals("4.0", bc.getVersion());
        assertEquals(Boolean.TRUE, bc.getIsMobileDevice());
        assertEquals("Android", bc.getPlatform());
    }

    @Test
    public void testAndroidTabletFirefox() {
        String ua = "Mozilla/5.0 (Android 4.4; Tablet; rv:46.0) Gecko/46.0 Firefox/46.0";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Firefox", bc.getBrowser());
        assertEquals("46.0", bc.getVersion());
        assertEquals(Boolean.TRUE, bc.getIsMobileDevice());
        assertEquals("Android", bc.getPlatform());
        assertEquals(Boolean.TRUE, bc.getIsTablet());
    }


    @Test
    public void testIE9() {
        String ua = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("IE", bc.getBrowser());
        assertEquals("9.0", bc.getVersion());
        assertEquals(Boolean.FALSE, bc.getIsMobileDevice());
        assertEquals("Win7", bc.getPlatform());
        assertEquals(Boolean.FALSE, bc.getIsTablet());
    }

    @Test
    public void testIE11() {
        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("IE", bc.getBrowser());
        assertEquals("11.0", bc.getVersion());
        assertEquals(Boolean.FALSE, bc.getIsMobileDevice());
        assertEquals("Win10", bc.getPlatform());
        assertEquals(Boolean.FALSE, bc.getIsTablet());
    }

    @Test
    public void testEdge() {
        String ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Edge", bc.getBrowser());
        assertEquals("12.0", bc.getVersion());
        assertEquals(Boolean.FALSE, bc.getIsMobileDevice());
        assertEquals("Win10", bc.getPlatform());
        assertEquals(Boolean.FALSE, bc.getIsTablet());
    }

    @Test
    public void testIPadiOS9() {
        String ua = "Mozilla/5.0 (iPad; CPU OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B137 Safari/601.1";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Safari", bc.getBrowser());
        assertEquals("9.0", bc.getVersion());
        assertEquals(Boolean.TRUE, bc.getIsMobileDevice());
        assertEquals("iOS", bc.getPlatform());
        assertEquals(Boolean.TRUE, bc.getIsTablet());
    }

    @Test
    public void testIPhone() {
        String ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B137 Safari/601.1";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Safari", bc.getBrowser());
        assertEquals("9.0", bc.getVersion());
        assertEquals(Boolean.TRUE, bc.getIsMobileDevice());
        assertEquals("iOS", bc.getPlatform());
        assertEquals(Boolean.FALSE, bc.getIsTablet());
    }

    @Test
    public void testChrome64OSX() {
        String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Chrome", bc.getBrowser());
        assertEquals("64.0", bc.getVersion());
        assertEquals(Boolean.FALSE, bc.getIsMobileDevice());
        assertEquals("MacOSX", bc.getPlatform());
        assertEquals(Boolean.FALSE, bc.getIsTablet());
    }
}
