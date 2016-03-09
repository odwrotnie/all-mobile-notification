# all-mobile-notification

## Settings

### Android

Set the following properties in `notifier.properties` or `System.properties`:
 - `android.title`
 - `android.key`

### iOS

Place the Apple certificate in `ios-cert.p12` in the classpath.

Set the following properties in `notifier.properties` or `System.properties`:
 - `ios.cert.path`
 - `ios.cert.pass`

### Windows

Set the following properties in `notifier.properties` or `System.properties`:
 - `windows.sid`
 - `windows.secret`
