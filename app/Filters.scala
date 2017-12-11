import filters.ExampleFilter
import javax.inject.Inject
import play.api.http.HttpFilters

class Filters @Inject()(exampleFilter: ExampleFilter) extends HttpFilters {
  override val filters = Seq(exampleFilter)
}
