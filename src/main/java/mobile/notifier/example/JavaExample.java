package mobile.notifier.example;

import mobile.notifier.android.AndroidNotifier;
import mobile.notifier.ios.Ios;
import mobile.notifier.windows.WindowsNotifier;

public class JavaExample {

    static Ios ipn = new Ios("token");
    static AndroidNotifier an = new AndroidNotifier("title", "key", "token");
    static WindowsNotifier wn = new WindowsNotifier("sid", "secret", "uri");

    public static void usage() {
        ipn.notifyFuture("The message", "123");
        an.notifyFuture("The message", "123");
        wn.notifyFuture("The message", "123");
    }
}
