package mobile.notifier.ios

import com.notnoop.apns.APNS
import mobile.notifier.Notifier
import mobile.notifier.helper.Properties

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * @param token the token for the iOS application
  */
case class Ios(token: String = Properties.get("notifier", "ios", "token").get)
  extends Notifier[Unit] {

  lazy val CERT_NAME = "/erconet.p12"
  lazy val CERT_PATH = getClass.getResource(CERT_NAME).getPath
  lazy val CERT_PASS = "1234"

  override def notify(message: String, href: Option[String] = None): Future[Unit] = Future {
    val service = APNS.newService.withCert(CERT_PATH, CERT_PASS)
      .withSandboxDestination.build
    val payload = APNS.newPayload.alertBody(message)
      .badge(1)
      .sound("default")
      .customField("href", href.getOrElse("-")).build
    service.push(token, payload)
  }
}
