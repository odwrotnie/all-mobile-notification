package rzepaw

import mobile.notifier.android.AndroidNotifier
import mobile.notifier.helper.Properties
import mobile.notifier.ios.IosNotifier
import mobile.notifier.windows.WindowsNotifier
import org.scalatest.FlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class NotificationTest
  extends FlatSpec {

  "IPhone notifications" should "work" in {
    val n = IosNotifier(token = "4b05a4e61ce8ebc4491524ada6039bc798194a52b77cb3ce848980e479ba3462")
    val response = Await.result(n.notify("Wiadomość!", None), Duration.Inf)
    println(s"Response: $response")
  }

  "Android notifications" should "work" in {
    val n = AndroidNotifier(to = "APA91bG6WB556e96hrNmwalC7fhoZZl4-vjoZ3JCQ-UMPa8FeitrZrF0ewJvic1TQeRDCsuiOsbkVTRU0WeWvaUEcKcb7FyKFrJKTJj8WwLQhHN3Z98HXFfuFrmgR9yKNHpaBO36PMSP")
    val response = Await.result(n.notify("Wiadomość!"), Duration.Inf)
    println(s"Response: $response")
  }

  "Windows notifications" should "work" in {
    val n = WindowsNotifier(channel = "https://db5.notify.windows.com/?token=AwYAAACiDI%2f2R8B%2fS6z6PlTCsACCcwjv8W%2bjFtO%2b9%2bQquXMC4BPwIjfHcbWPTrLLdvvHnmuElz65vjFvS1avPkKTYx9qLuWgQDFqEOTa%2f7D6rCdSpNIb2vJl5pz%2f2pKHsuY%2fMN8%3d")
    val response = Await.result(n.notify("Wiadomość!"), Duration.Inf)
    println(s"Response: $response")
  }
}
