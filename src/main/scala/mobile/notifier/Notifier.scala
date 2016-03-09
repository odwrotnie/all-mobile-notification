package mobile.notifier

import java.util.concurrent.{Future => JFuture}

import mobile.notifier.helper.FutureConverter

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

trait Notifier[RESP] {

  def notify(message: String, href: Option[String]): Future[RESP]

  /**
    * Nonblocking method that sends the notification
    * @param message The message shown
    * @param href The reference
    * @return the Unit future
    */
  def notifyFuture(message: String, href: String): JFuture[_] = FutureConverter.toJava(notify(message, Some(href)))

  def notifyAwait(message: String, href: Option[String]): Unit =
    Await.result(notify(message, href), Duration.Inf)
}
