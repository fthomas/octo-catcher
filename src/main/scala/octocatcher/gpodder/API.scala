package octocatcher.gpodder

import octocatcher._
import octocatcher.util._

import scala.concurrent.Future
import spray.client.HttpConduit
import spray.http._
import spray.http.HttpMethods._
import spray.util._

object test extends App {

  val response = API.getToplist(2)

  response onSuccess {
    case x => println(x)
  }
}

object API {
  def getTopTags(count: Int): Future[List[Any]] = {
    require(count >= 0)
    getJSONArray(s"/api/2/tags/${count}.json")
  }

  def getToplist(number: Int): Future[List[Any]] = {
    require(1 to 100 contains number)
    getJSONArray(s"/toplist/${number}.json")
  }

  private def getJSONArray(uri: String): Future[List[Any]] = {
    val pipeline = HttpConduit.sendReceive(httpConduit())
    val response = pipeline(HttpRequest(GET, uri))

    response.map(JSONOps.toJSONArray(_))
  }
}
