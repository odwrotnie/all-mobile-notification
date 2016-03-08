package mobile.notifier.android

import spray.json.DefaultJsonProtocol

case class GoogleResult(message_id: Option[String], error: Option[String])
case class GoogleResponse(multicast_id: Long, success: Int, failure: Int, canonical_ids: Int, results: List[GoogleResult])

trait AndroidProtocol
  extends DefaultJsonProtocol {
  implicit val googleResultFormat = jsonFormat2(GoogleResult.apply)
  implicit val googleResultListFormat = listFormat(googleResultFormat)
  implicit val googleResponseFormat = jsonFormat5(GoogleResponse.apply)
}
