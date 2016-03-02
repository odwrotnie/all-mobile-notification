package rzepaw

import scala.concurrent.Future

trait Notifier {

  def notify(message: String, href: Option[String]): Future[_]
}
