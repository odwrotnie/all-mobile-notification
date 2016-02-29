package rzepaw

import scala.concurrent.Future
import scala.util.Try

trait Notifier {

  def notify(message: String, href: Option[String]): Future[Try[String]]
}
