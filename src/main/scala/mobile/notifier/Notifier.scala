package mobile.notifier

import java.util.concurrent.{Future => JFuture}

import mobile.notifier.android.AndroidNotifier
import mobile.notifier.helper.FutureConverter
import mobile.notifier.ios.IosNotifier
import mobile.notifier.windows.WindowsNotifier

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

trait Notifier[RESP] {

  def notify(message: String, href: Option[String]): Future[RESP]

  /**
    * Nonblocking method that sends the notification
    *
    * @param message The message shown
    * @param href The reference
    * @return the Unit future
    */
  def notifyFuture(message: String, href: Option[String]): JFuture[_] = {
    FutureConverter.toJava(notify(message, Option(href).flatten))
  }

  def notifyAwait(message: String, href: Option[String]): Unit =
    Await.result(notify(message, Option(href).flatten), Duration.Inf)
}

object Notifier {
  def ios(device: String) = IosNotifier(device)
  def android(device: String) = AndroidNotifier(device)
  def windows(device: String) = WindowsNotifier(device)
}
