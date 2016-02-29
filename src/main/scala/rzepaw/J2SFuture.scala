package rzepaw

import java.util.concurrent.{Future => JFuture}
import scala.concurrent.{Future => SFuture, Promise}
import scala.util.Try

object J2SFuture {
  def apply[T](jf: JFuture[T]): SFuture[T] = {
    val promise = Promise[T]()
    new Thread(new Runnable {
      def run() {
        promise.complete(Try(jf.get()))
      }
    }).start
    promise.future
  }
}
