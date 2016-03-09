package mobile.notifier.helper

import java.util.Properties
import javax.naming.InitialContext
import scala.util.Try

object Properties {
  def get(path: String*): Option[String] = {
    val results: List[String] = List(
      SystemProperties.get(path.mkString(".")),
      JNDI.get(path.mkString("/")),
      ResourceProperties(s"/${ path.head }.properties").get(path.tail.mkString("."))
    ).flatten
    results.headOption
  }
  def getInt(path: String*): Option[Int] = get(path:_*).flatMap(s => Try(s.toInt).toOption)
}

//

object SystemProperties {
  def get(prop: String): Option[String] = Try(System.getProperty(prop)).toOption.flatMap(Option(_))
}

object JNDI {
  def ic = new InitialContext()
  def get(path: String): Option[String] = Try(ic.lookup(path).asInstanceOf[String]).toOption
}

case class ResourceProperties(path: String) {
  require(path.startsWith("/"), "Path should start with forward slash")
  private val props: Option[Properties] = Try {
    val p = new Properties()
    p.load(getClass.getResourceAsStream(path))
    p
  }.toOption
  def get(prop: String): Option[String] = props flatMap { p =>
    val res = Try(p.getProperty(prop)).toOption.flatMap(Option(_))
    res
  }
}
