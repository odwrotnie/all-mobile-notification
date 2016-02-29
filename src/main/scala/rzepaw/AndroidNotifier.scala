package rzepaw

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.Uri.Path
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import rzepaw.exceptions.Unauthorized

import scala.concurrent.Future
import scala.util.{Try, Failure, Success}
import scala.util.parsing.json.JSONObject

case class AndroidNotifier(title: String, key: String)
  extends LazyLogging
  with Notifier {

  val GCM_ADDRESS: String = "https://gcm-http.googleapis.com"

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  import system.dispatcher

  val uri = Uri(GCM_ADDRESS).withPath(Path / "gcm" / "send")
  val header = RawHeader("Authorization", s"key=$key")

  def notify(message: String, href: Option[String] = None): Future[Try[String]] = {
    val json = JSONObject(Map(
      "data" -> JSONObject(Map(
        "name" -> title,
        "message" -> message,
        "href" -> href
      )),
      "to" -> "/topics/global"
    ))

    val entity = HttpEntity(MediaTypes.`application/json`, json.toString())
    val request = HttpRequest(HttpMethods.POST, uri = uri).withHeaders(header).withEntity(entity)
    val responseFuture: Future[HttpResponse] = Http().singleRequest(request) flatMap {
      case HttpResponse(StatusCodes.Unauthorized, _, _, _) => Future.failed(Unauthorized)
      case r => Future.successful(r)
    }
    val messageFuture: Future[String] = responseFuture.flatMap(response => Unmarshal(response.entity).to[String])

    messageFuture onComplete {
      case Success(s) => logger.debug(s"Message successfuly sent - $s")
      case Failure(t) => logger.error(s"Message sending failure - ${ t.getMessage }")
    }

    messageFuture map { m => Success(m) }
  }
}
