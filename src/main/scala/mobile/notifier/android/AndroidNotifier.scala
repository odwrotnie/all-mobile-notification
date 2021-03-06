package mobile.notifier.android

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.Uri.Path
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import mobile.notifier.Notifier
import mobile.notifier.exceptions.Unauthorized
import mobile.notifier.helper.Properties

import scala.concurrent.Future
import scala.util.parsing.json.JSONObject
import scala.util.{Failure, Success}

/**
  * @param title shown in the header of the message
  * @param key for Google Messages authentication
  * @param to the Android application token
  */
case class AndroidNotifier(to: String,
                           title: String = Properties.get("notifier", "android", "title").get,
                           key: String = Properties.get("notifier", "android", "key").get)
  extends Notifier[GoogleResponse]
    with AndroidProtocol
    with LazyLogging {

  val GCM_ADDRESS: String = "https://gcm-http.googleapis.com"

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  import system.dispatcher

  val uri = Uri(GCM_ADDRESS).withPath(Path / "gcm" / "send")
  val header = RawHeader("Authorization", s"key=$key")

  def notify(message: String, href: Option[String] = None): Future[GoogleResponse] = {

    val e = entity(json(message, href))

    val request = HttpRequest(HttpMethods.POST, uri = uri).withHeaders(header).withEntity(e)
    val responseFuture: Future[HttpResponse] = Http().singleRequest(request) flatMap {
      case HttpResponse(StatusCodes.Unauthorized, _, _, _) =>
        Future.failed(Unauthorized("Http status code = 401"))
      case r =>
        Future.successful(r)
    }
    val messageFuture: Future[GoogleResponse] = responseFuture.flatMap(response => Unmarshal(response.entity).to[GoogleResponse])

    messageFuture onComplete {
      case Success(s) => logger.debug(s"Message successfuly sent - $s")
      case Failure(t) => logger.error(s"Message sending failure - ${ t.getMessage }")
    }

    messageFuture map {
      case GoogleResponse(_, _, 1, _, results) =>
        val message = results.flatMap(_.error).mkString(", ")
        throw Unauthorized(message)
      case gr: GoogleResponse =>
        gr
    }
  }

  def json(message: String, href: Option[String]) = JSONObject(Map(
    "data" -> JSONObject(Map(
      "title" -> title,
      "msgcnt" -> 0,
      "message" -> message,
      "href" -> href.getOrElse("")
    )),
    "to" -> to
  ))

  def entity(json: JSONObject) = HttpEntity(MediaTypes.`application/json`, json.toString())
}
