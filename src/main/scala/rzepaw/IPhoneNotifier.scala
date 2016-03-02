package rzepaw

import java.io.File
import com.notnoop.apns.APNS
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

case class IPhoneNotifier(token: String)
  extends Notifier {

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
