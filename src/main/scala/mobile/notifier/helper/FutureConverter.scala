package mobile.notifier.helper

import java.util.concurrent.{Future => JFuture, TimeUnit}

import scala.concurrent.duration.Duration
import scala.concurrent.{Future => SFuture, Await, Promise}
import scala.util.Try

object FutureConverter {

  def toScala[T](jf: JFuture[T]): SFuture[T] = {
    val promise = Promise[T]()
    new Thread(new Runnable {
      def run() {
        promise.complete(Try(jf.get()))
      }
    }).start
    promise.future
  }

  def toJava[T](x:SFuture[T]): JFuture[T] = {
    new JFuture[T] {
      override def isCancelled: Boolean = throw new UnsupportedOperationException
      override def get(): T = Await.result(x, Duration.Inf)
      override def get(timeout: Long, unit: TimeUnit): T = Await.result(x, Duration.create(timeout, unit))
      override def cancel(mayInterruptIfRunning: Boolean): Boolean = throw new UnsupportedOperationException
      override def isDone: Boolean = x.isCompleted
    }
  }
}
