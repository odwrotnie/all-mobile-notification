package rzepaw

import org.scalatest.FlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class NotificationTest
  extends FlatSpec {

  "IPhone notifications" should "work" in {
    val n = IPhoneNotifier("4b05a4e61ce8ebc4491524ada6039bc798194a52b77cb3ce848980e479ba3462")
    val response = Await.result(n.notify("Wiadomość!", None), Duration.Inf)
    println(s"Response: $response")
  }

  "Android notifications" should "work" in {
    val n = AndroidNotifier("ERCO.Net", "AIzaSyA-ZxHs-QnMRRJphoU0KydlYMzPLR3KqeI", "APA91bG6WB556e96hrNmwalC7fhoZZl4-vjoZ3JCQ-UMPa8FeitrZrF0ewJvic1TQeRDCsuiOsbkVTRU0WeWvaUEcKcb7FyKFrJKTJj8WwLQhHN3Z98HXFfuFrmgR9yKNHpaBO36PMSP")
    val response = Await.result(n.notify("Wiadomość!"), Duration.Inf)
    println(s"Response: $response")
  }
}
