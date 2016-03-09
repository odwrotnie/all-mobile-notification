package mobile.notifier.example;

import mobile.notifier.Notifier$;
import mobile.notifier.android.AndroidNotifier;
import mobile.notifier.ios.IosNotifier;
import mobile.notifier.windows.WindowsNotifier;

public class JavaExample {

    public void usage() {

        Notifier$ factory = Notifier$.MODULE$;

        IosNotifier ipn = factory.ios("4b05a4e61ce8ebc4491524ada6039bc798194a52b77cb3ce848980e479ba3462");
        AndroidNotifier an = factory.android("APA91bG6WB556e96hrNmwalC7fhoZZl4-vjoZ3JCQ-UMPa8FeitrZrF0ewJvic1TQeRDCsuiOsbkVTRU0WeWvaUEcKcb7FyKFrJKTJj8WwLQhHN3Z98HXFfuFrmgR9yKNHpaBO36PMSP");
        WindowsNotifier wn = factory.windows("https://db5.notify.windows.com/?token=AwYAAACiDI%2f2R8B%2fS6z6PlTCsACCcwjv8W%2bjFtO%2b9%2bQquXMC4BPwIjfHcbWPTrLLdvvHnmuElz65vjFvS1avPkKTYx9qLuWgQDFqEOTa%2f7D6rCdSpNIb2vJl5pz%2f2pKHsuY%2fMN8%3d");

        ipn.notifyFuture("The message", "123");
        an.notifyFuture("The message", "123");
        wn.notifyFuture("The message", "123");
    }
}
