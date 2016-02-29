package rzepaw

import org.scalatest.FlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class NotificationTest
  extends FlatSpec {

//  "IPhone notifications" should "work" in {
//    val response = Await.result(IPhoneNotifier("?").notify("asdf"), Duration.Inf)
//    println(s"Response: $response")
//  }

  "Android notifications" should "work" in {
    val n = AndroidNotifier("ERCO.Net", "AIzaSyA-ZxHs-QnMRRJphoU0KydlYMzPLR3KqeI", "APA91bG6WB556e96hrNmwalC7fhoZZl4-vjoZ3JCQ-UMPa8FeitrZrF0ewJvic1TQeRDCsuiOsbkVTRU0WeWvaUEcKcb7FyKFrJKTJj8WwLQhHN3Z98HXFfuFrmgR9yKNHpaBO36PMSP")
    val response = Await.result(n.notify("Jakaś dłuższa wiadomość"), Duration.Inf)
    println(s"Response: $response")
  }
}
