package octocatcher.gpodder

import octocatcher._
import octocatcher.util.JSONOps._

import scala.concurrent.Future
import spray.client.HttpConduit
import spray.http._
import spray.http.HttpMethods._
import spray.util._

object test extends App {

  //val response = API.getToplist(2)
  val response = API.getPodcastData("http://feeds.feedburner.com/linuxoutlaws")
  
  response onSuccess {
    case x => println(x)
  }
}

object API {
  /** Returns the count most used tags.
    *
    * gpodder.net API Reference:
    * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Top_Tags Retrieve Top Tags]]
    */
  def getTopTags(count: Int): Future[List[Any]] = {
    require(count >= 0)
    getJSONArray(s"/api/2/tags/${count}.json")
  }

  /** Returns the count most-subscribed podcasts that are tagged with tag. 
    *
    * gpodder.net API Reference:
    * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Podcasts_for_Tag Retrieve Podcasts for Tag]]
    */
  def getPodcastsForTag(tag: String, count: Int): Future[List[Any]] = {
    require(tag.length > 0 && count >= 0)
    getJSONArray(s"/api/2/tag/${tag}/${count}.json")
  }

  /** Returns information for the podcast with the given URL.
    *
    * gpodder.net API Reference:
    * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Podcast_Data Retrieve Podcast Data]]
    */
  def getPodcastData(url: String): Future[Map[String, Any]] = {
    require(url.length > 0)
    getJSONObject(s"/api/2/data/podcast.json?url=${url}")
  }

  /** ???
    *
    * gpodder.net API Reference:
    * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Episode_Data Retrieve Episode Data]]
    */
  def getEpisodeData(podcastUrl: String, episodeUrl: String) = {
    require(podcastUrl.length > 0 && episodeUrl.length > 0)
    getJSONObject(
      s"/api/2/data/episode.json?podcast=${podcastUrl}&url=${episodeUrl}")
  }

  /** ???
    *
    * gpodder.net API Reference:
    * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Podcast_Toplist Podcast Toplist]]
    */
  def getToplist(number: Int): Future[List[Any]] = {
    require(1 to 100 contains number)
    getJSONArray(s"/toplist/${number}.json")
  }

  private def requestUri(uri: String): Future[HttpResponse] = {
    val pipeline = HttpConduit.sendReceive(httpConduit())
    pipeline(HttpRequest(GET, uri))
  }

  private def getJSONArray(uri: String): Future[List[Any]] = {
    requestUri(uri).map(toJSONArray(_))
  }

  private def getJSONObject(uri: String): Future[Map[String, Any]] = {
    requestUri(uri).map(toJSONObject(_))
  }
}
