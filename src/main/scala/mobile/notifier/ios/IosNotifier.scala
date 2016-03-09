package mobile.notifier.ios

import com.notnoop.apns.APNS
import mobile.notifier.Notifier
import mobile.notifier.helper.Properties

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * @param token the token for the iOS application
  */
case class IosNotifier(token: String,
                       certPath: String = Properties.get("notifier", "ios", "cert.path").get,
                       certPass: String = Properties.get("notifier", "ios", "cert.pass").get)
  extends Notifier[Unit] {

  lazy val CERT_PATH = getClass.getResource(certPath).getPath

  override def notify(message: String, href: Option[String] = None): Future[Unit] = Future {
    val service = APNS.newService.withCert(CERT_PATH, certPass)
      .withSandboxDestination.build
    val payload = APNS.newPayload.alertBody(message)
      .badge(1)
      .sound("default")
      .customField("href", href.getOrElse("-")).build
    service.push(token, payload)
  }
}
