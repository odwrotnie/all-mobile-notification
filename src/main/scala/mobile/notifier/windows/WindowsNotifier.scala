package mobile.notifier.windows

import ar.com.fernandospr.wns.WnsService
import ar.com.fernandospr.wns.model.builders.{WnsToastBuilder, WnsTileBuilder}
import mobile.notifier.Notifier
import mobile.notifier.exceptions.Unauthorized

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * @param sid Microsoft SID (unique for the application)
  * @param secret
  * @param channel of the Windows Phone application
  */
case class WindowsNotifier(sid: String, secret: String, channel: String)
  extends Notifier {

  val service = new WnsService(sid, secret, false)

  override def notify(message: String, href: Option[String] = None): Future[Unit] = Future {
    val toast = new WnsToastBuilder().bindingTemplateToastText01(message).build()
    val response = service.pushToast(channel, toast)
    response.notificationStatus match {
      case "received" =>
      case "dropped" => throw Unauthorized("Dropped: " + response.errorDescription)
      case "channelthrottled" => throw Unauthorized("Channel throttled: " + response.errorDescription)
      case _ =>
    }
  }
}
