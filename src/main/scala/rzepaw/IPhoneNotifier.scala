package rzepaw

import java.io.File
import java.util.concurrent.{Future => JFuture}
import com.relayrides.pushy.apns.{PushNotificationResponse, ApnsClient}
import com.relayrides.pushy.apns.util.{TokenUtil, ApnsPayloadBuilder, SimpleApnsPushNotification}
import scala.concurrent.{Future => SFuture, Promise}
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

case class IPhoneNotifier(token: String) {

  def notify(message: String): SFuture[Try[String]] = {

    val certificate: File = new File(getClass().getResource("/apple.certificate").toURI)

    val apnsClient = new ApnsClient[SimpleApnsPushNotification](certificate, "p12-file-password")
    apnsClient.connect(ApnsClient.DEVELOPMENT_APNS_HOST).await()

    val payloadBuilder = new ApnsPayloadBuilder()
    payloadBuilder.setAlertBody(message)

    val payload = payloadBuilder.buildWithDefaultMaximumLength()
    val sanitizedToken = TokenUtil.sanitizeTokenString(token)

    val pushNotification: SimpleApnsPushNotification = new SimpleApnsPushNotification(sanitizedToken, "com.example.myApp", payload)

    val sendNotificationFuture: JFuture[PushNotificationResponse[SimpleApnsPushNotification]] = apnsClient.sendNotification(pushNotification)

    val responseFuture: SFuture[PushNotificationResponse[SimpleApnsPushNotification]] =J2SFuture(sendNotificationFuture)

    responseFuture.onComplete(_ => apnsClient.disconnect())

    responseFuture.map { r =>
      if (r.isAccepted) Success("Sent")
      else Failure(new Exception(r.getRejectionReason))
    }
  }
}
