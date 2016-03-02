package rzepaw

import ar.com.fernandospr.wns.WnsService
import ar.com.fernandospr.wns.model.builders.WnsTileBuilder
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

case class WindowsNotifier(token: String)
  extends Notifier {

  val sid = "yourSID"
  val clientSecret = "yourClientSecret"

  val service = new WnsService(sid, clientSecret, false)

  override def notify(message: String, href: Option[String] = None): Future[Unit] = Future {
    val channelUri = "yourChannelUri"
    val tile = new WnsTileBuilder().bindingTemplateTileWideText03("Hello world").build()
    service.pushTile(channelUri, tile);
  }
}
