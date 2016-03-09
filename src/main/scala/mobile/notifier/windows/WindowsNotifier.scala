package mobile.notifier.windows

import ar.com.fernandospr.wns.WnsService
import ar.com.fernandospr.wns.model.builders.{WnsToastBuilder, WnsTileBuilder}
import mobile.notifier.Notifier
import mobile.notifier.exceptions.Unauthorized
import mobile.notifier.helper.Properties
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * @param sid Microsoft SID (unique for the application)
  * @param secret
  * @param channel of the Windows Phone application
  */
case class WindowsNotifier(channel: String,
                           sid: String = Properties.get("notifier", "windows", "sid").get,
                           secret: String = Properties.get("notifier", "windows", "secret").get)
  extends Notifier[String] {

  val service = new WnsService(sid, secret, false)
  val toaser = new WnsToastBuilder()

  override def notify(message: String, href: Option[String] = None): Future[String] = Future {
    val toast = toaser.bindingTemplateToastText01(message.toString).build()
    val response = service.pushToast(channel, toast)
    (response.code, response.deviceConnectionStatus, response.notificationStatus) match {
      case (200, _, _) =>
        message
      case (code, connection, notification) =>
        throw Unauthorized(s"Code: $code, status: $notification, error: ${ response.errorDescription }, connection: $connection")
    }
  }
}
