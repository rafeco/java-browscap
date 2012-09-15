package us.rafe.browscap;

import junit.framework.TestCase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBrowscap extends TestCase {
    static Logger logger = LoggerFactory.getLogger(TestBrowscap.class);

    @Test
    public void testAndroid() {
        String ua = "Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Android", bc.getBrowser());
        assertEquals("4.0", bc.getVersion());
        assertEquals(Boolean.TRUE, bc.getIsMobileDevice());
        assertEquals("Android", bc.getPlatform());
        assertEquals("2.2", bc.getPlatformVersion());
        assertEquals("Android", bc.getDeviceName());
        assertEquals("Google", bc.getDeviceMaker());
    }

    @Test
    public void testChrome16OSX() {
        String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Chrome", bc.getBrowser());
        assertEquals("16.0", bc.getVersion());
        assertEquals(Boolean.FALSE, bc.getIsMobileDevice());
        assertEquals("MacOSX", bc.getPlatform());
    }

    @Test
    public void testDefault() {
        String ua = "Not a real browser";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Default Browser", bc.getBrowser());
        assertEquals("0.0", bc.getVersion());
        assertEquals(Boolean.FALSE, bc.getIsMobileDevice());
        assertEquals("unknown", bc.getPlatform());
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
    }

    @Test
    public void testIPad3() {
        String ua = "Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Safari", bc.getBrowser());
        assertEquals("4.0", bc.getVersion());
        assertEquals(Boolean.TRUE, bc.getIsMobileDevice());
        assertEquals("3.2", bc.getPlatformVersion());
        assertEquals("iOS", bc.getPlatform());
        assertEquals("iPad", bc.getDeviceName());
        assertEquals("Apple", bc.getDeviceMaker());
    }

    @Test
    public void testIPhone() {
        String ua = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Safari", bc.getBrowser());
        assertEquals("3.0", bc.getVersion());
        assertEquals(Boolean.TRUE, bc.getIsMobileDevice());
        assertEquals("iOS", bc.getPlatform());
        assertEquals("iPhone", bc.getDeviceName());
        assertEquals("Apple", bc.getDeviceMaker());
    }

    @Test
    public void testMaxthon3() {
        String ua = "Mozilla/5.0 (Windows; U; Windows NT 5.1; ) AppleWebKit/534.12 (KHTML, like Gecko) Maxthon/3.0 Safari/534.12";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Maxthon", bc.getBrowser());
        assertEquals("3.0", bc.getVersion());
        assertEquals(Boolean.FALSE, bc.getIsMobileDevice());
        assertEquals("WinXP", bc.getPlatform());
    }

    @Test
    public void testSamsungS3() {
        String ua = "Mozilla/5.0 (Linux; U; Android 4.0.4; en-gb; GT-I9300 Build/IMM76D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";

        BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

        assertNotNull(bc);
        assertEquals("Android", bc.getBrowser());
        assertEquals("4.0", bc.getVersion());
        assertEquals(Boolean.TRUE, bc.getIsMobileDevice());
        assertEquals("Android", bc.getPlatform());
        assertEquals("4.0", bc.getPlatformVersion());
        assertEquals("Android", bc.getDeviceName());
        assertEquals("Google", bc.getDeviceMaker());
    }
}
