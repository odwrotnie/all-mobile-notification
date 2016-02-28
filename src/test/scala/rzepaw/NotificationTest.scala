package rzepaw

import org.scalatest.FlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class NotificationTest
  extends FlatSpec {

  "Notifications" should "work" in {
    val response = Await.result(AndroidNotifier("?", "?").notify("asdf", "asdf"), Duration.Inf)
    println(s"Response: $response")
  }
}
