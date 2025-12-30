java-browscap
=============

Java library to extract browser info from a user agent string using browscap.ini. This was primarily used for offline analytics processing to classify browsers from logged user agent strings.

Uses the "lite" version, `lite_asp_browscap.ini` from [browscap.org](http://browscap.org/). Note that this doesn't include all properties (e.g. `Device_Name`, `Platform_Version`).

## Modern Alternatives

If you're starting a new project, consider these more actively maintained alternatives:

**1. ua-parser (Recommended)**
Actively maintained library with implementations for multiple languages including Java. Better accuracy and regular updates for new browsers/devices. See [ua-parser on GitHub](https://github.com/ua-parser).

**2. Analytics Platform Features**
Most modern analytics platforms (Google Analytics, Mixpanel, Amplitude, etc.) handle user agent parsing automatically and provide browser/device classification out of the box.

**3. Client Hints for New Data Collection**
If you're collecting new data, consider logging User-Agent Client Hints alongside or instead of UA strings for more reliable device/browser information. See [MDN documentation](https://developer.mozilla.org/en-US/docs/Web/HTTP/Client_hints).

## Usage

```
import us.rafe.browscap.{Browscap,BrowserCapabilities};

String ua = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3";

BrowserCapabilities bc = Browscap.getInstance().lookup(ua);

System.out.println(bc.getBrowser()); // "Safari"
System.out.println(bc.getVersion()); // "3.0"
System.out.println(bc.getPlatform()); // "iOS"
System.out.println(bc.getIsMobileDevice()); // "true"
System.out.println(bc.getIsTablet()); // "false"
```

## Development

Run tests with:

```
mvn test
```
