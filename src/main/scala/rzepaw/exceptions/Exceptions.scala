package rzepaw.exceptions

case class Unauthorized(m: String = "Error") extends Exception(s"Unauthorized: $m")
