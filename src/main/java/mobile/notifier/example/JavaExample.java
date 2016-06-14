package mobile.notifier.example;

import mobile.notifier.Notifier$;
import mobile.notifier.android.AndroidNotifier;
import mobile.notifier.ios.IosNotifier;
import mobile.notifier.windows.WindowsNotifier;
import scala.None;
import scala.None$;

public class JavaExample {

    public static void usage() {

        Notifier$ factory = Notifier$.MODULE$;

        IosNotifier ipn = factory.ios("a2de0b5d2a8f1218e691bf1673f421a4da8f59884c2d86bcd6065d2844017b5d");
        AndroidNotifier an = factory.android("APA91bG5c5ixhI3pgy1uk6BRQ4Z2dmZII9upENU3obOoQiBldXGFj9B28uPgFb8JzEf8oYfAwz6-7lGT3JudfzatdpH9i7B8VqH2AAapvg19matTiwZYJsmPkqDOH0ty4CCYIC16hzdp");
        WindowsNotifier wn = factory.windows("https://db5.notify.windows.com/?token=AwYAAACiDI%2f2R8B%2fS6z6PlTCsACCcwjv8W%2bjFtO%2b9%2bQquXMC4BPwIjfHcbWPTrLLdvvHnmuElz65vjFvS1avPkKTYx9qLuWgQDFqEOTa%2f7D6rCdSpNIb2vJl5pz%2f2pKHsuY%2fMN8%3d");

        scala.Option<String> href = scala.Option.apply(null);

        ipn.notifyFuture("The message", href);
        an.notifyFuture("The message", href);
        //wn.notifyFuture("The message", href);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
