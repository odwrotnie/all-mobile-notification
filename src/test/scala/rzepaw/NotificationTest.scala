package rzepaw

import org.scalatest.FlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class NotificationTest
  extends FlatSpec {

//  "IPhone notifications" should "work" in {
//    val n = IPhoneNotifier("84743a29c1122488b714c156969139e0ca611d9896bb323be8e97d73f538632f")
//    val response = Await.result(n.notify("Wiadomość!", None), Duration.Inf)
//    println(s"Response: $response")
//  }

  "Android notifications" should "work" in {
    val n = AndroidNotifier("ERCO.Net", "AIzaSyA-ZxHs-QnMRRJphoU0KydlYMzPLR3KqeI", "APA91bG6WB556e96hrNmwalC7fhoZZl4-vjoZ3JCQ-UMPa8FeitrZrF0ewJvic1TQeRDCsuiOsbkVTRU0WeWvaUEcKcb7FyKFrJKTJj8WwLQhHN3Z98HXFfuFrmgR9yKNHpaBO36PMSP")
    val response = Await.result(n.notify("Uwaga, pedał Karol!"), Duration.Inf)
    println(s"Response: $response")
  }
}
