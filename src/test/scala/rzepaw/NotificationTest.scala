package rzepaw

import mobile.notifier.android.AndroidNotifier
import mobile.notifier.ios.Ios
import mobile.notifier.windows.WindowsNotifier
import org.scalatest.FlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class NotificationTest
  extends FlatSpec {

//  "IPhone notifications" should "work" in {
//    val n = Ios(
//      token = "4b05a4e61ce8ebc4491524ada6039bc798194a52b77cb3ce848980e479ba3462")
//    val response = Await.result(n.notify("Wiadomość!", None), Duration.Inf)
//    println(s"Response: $response")
//  }

//  "Android notifications" should "work" in {
//    val n = AndroidNotifier(
//      title = "ERCO.Net",
//      key = "AIzaSyA-ZxHs-QnMRRJphoU0KydlYMzPLR3KqeI",
//      to = "APA91bG6WB556e96hrNmwalC7fhoZZl4-vjoZ3JCQ-UMPa8FeitrZrF0ewJvic1TQeRDCsuiOsbkVTRU0WeWvaUEcKcb7FyKFrJKTJj8WwLQhHN3Z98HXFfuFrmgR9yKNHpaBO36PMSP")
//    val response = Await.result(n.notify("Wiadomość!"), Duration.Inf)
//    println(s"Response: $response")
//  }

  "Windows notifications" should "work" in {
    val n = WindowsNotifier(
      sid = "ms-app://s-1-15-2-2987772100-2216063289-1117202556-2166845046-2818900288-893802016-792119000",
      secret = "2eN6QVl28AlH9UyP0Bxfr69RgbgdryjU",
      channel = "https://db5.notify.windows.com/?token=AwYAAAB8%2fKq8efCd3aJwCB6s%2fQyxl%2ftLXyhN1Ly7bs1%2be916UxLdSEBVHE%2bp7aZ%2futTSWVSos0G13Nn9W%2bg5%2f7BwJMPPMWdOOUpua9k1XwmDhfkmG8lJwxSwVL%2bmQgtLwOo9IiQ%3d")
    val response = Await.result(n.notify("Wiadomość!"), Duration.Inf)
    println(s"Response: $response")
  }
}
