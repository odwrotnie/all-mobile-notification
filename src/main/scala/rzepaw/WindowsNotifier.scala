package rzepaw

import scala.concurrent.Future
import scala.util.Try

class WindowsNotifier
  extends Notifier {

  override def notify(message: String, href: Option[String]): Future[String] = {
    ???
  }
}
