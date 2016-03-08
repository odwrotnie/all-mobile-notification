package mobile.notifier.exceptions

case class Unauthorized(m: String = "Error") extends Exception(s"Unauthorized: $m")
