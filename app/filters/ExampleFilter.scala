package filters

import akka.stream.Materializer
import java.util.concurrent.ConcurrentHashMap
import javax.inject._
import play.api.libs.typedmap.TypedKey
import play.api.mvc._
import scala.collection.JavaConverters._
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
    val cache = new ConcurrentHashMap[String, String](16, 0.75f, 16).asScala
    val newRequest = request.addAttr(ExampleFilter.Cache, cache)
    cache.put("foo", "bar")
    val result = next(newRequest)
    cache.remove("foo")
    result
  }
}

object ExampleFilter {
  val Cache: TypedKey[scala.collection.Map[String, String]] = TypedKey("cache")
}
