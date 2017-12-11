package filters

import akka.stream.Materializer
import javax.inject._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}

/**
 * This is a simple filter that adds a header to all requests. It's
 * added to the application's list of filters by the
 * [[Filters]] class.
 *
 * @param ec This class is needed to execute code asynchronously.
 * It is used below by the `map` method.
 */
@Singleton
class ExampleFilter @Inject()(implicit val mat: Materializer, ec: ExecutionContext) extends Filter {
  override def apply(next: RequestHeader => Future[Result])(request: RequestHeader): Future[Result] = {
    throw new RuntimeException("WRONG")
  }
}
