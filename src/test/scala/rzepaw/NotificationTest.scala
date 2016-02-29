package rzepaw

import org.scalatest.FlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class NotificationTest
  extends FlatSpec {

  "IPhone notifications" should "work" in {
    val response = Await.result(IPhoneNotifier("?").notify("asdf"), Duration.Inf)
    println(s"Response: $response")
  }

  "Android notifications" should "work" in {
    val response = Await.result(AndroidNotifier("?", "?").notify("asdf"), Duration.Inf)
    println(s"Response: $response")
  }
}
