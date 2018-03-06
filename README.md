java-browscap
=============

Java library to extract browser info from a user agent string using browscap.ini. 

Uses the "lite" version, `lite_asp_browscap.ini` from [browscap.org](http://browscap.org/). Note that this doesn't include all properties (e.g. `Device_Name`, `Platform_Version`).

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
