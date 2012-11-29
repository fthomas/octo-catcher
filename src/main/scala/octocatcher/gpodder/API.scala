// Copyright 2012 Frank S. Thomas
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package octocatcher.gpodder

import octocatcher._
import octocatcher.util.JSONOps._

import scala.concurrent.Future
import spray.client.HttpConduit
import spray.http._
import spray.http.HttpMethods._
import spray.util._

/**
  * @define APIREF ''gpodder.net API reference:''
  */
object API {
  /** Returns the `count` most used tags.
    *
    * $APIREF
    * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Top_Tags Retrieve Top Tags]]
    */
  def getTopTags(count: Int): Future[List[Any]] = {
    require(count >= 0)
    getJSONArray(s"/api/2/tags/${count}.json")
  }

  /** Returns the `count` most-subscribed podcasts that are tagged with `tag`.
    *
    * $APIREF
    * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Podcasts_for_Tag Retrieve Podcasts for Tag]]
    */
  def getPodcastsForTag(tag: String, count: Int): Future[List[Any]] = {
    require(tag.length > 0 && count >= 0)
    getJSONArray(s"/api/2/tag/${tag}/${count}.json")
  }

  /** Returns information for the podcast with the given URL.
    *
    * $APIREF
    * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Podcast_Data Retrieve Podcast Data]]
    */
  def getPodcastData(url: String): Future[Map[String, Any]] = {
    require(url.length > 0)
    getJSONObject(s"/api/2/data/podcast.json?url=${url}")
  }

  /** Returns information for an episode of a podcast.
    *
    * $APIREF
    * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Episode_Data Retrieve Episode Data]]
    */
  def getEpisodeData(podcastUrl: String,
                     episodeUrl: String): Future[Map[String, Any]] = {
    require(podcastUrl.length > 0 && episodeUrl.length > 0)
    getJSONObject(
      s"/api/2/data/episode.json?podcast=${podcastUrl}&url=${episodeUrl}")
  }

  /** Returns the `number` most-popular podcasts in descending order.
    *
    * $APIREF
    * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Podcast_Toplist Podcast Toplist]]
    */
  def getToplist(number: Int): Future[List[Any]] = {
    require(1 to 100 contains number)
    getJSONArray(s"/toplist/${number}.json")
  }

  /** Searches for podcasts that match the given query.
    *
    * $APIREF
    * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Podcast_Search Podcast Search]]
    */
  def searchPodcasts(query: String): Future[List[Any]] = {
    require(query.length > 0)
    getJSONArray(s"/search.json?q=${query}")
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

/*
Directory
Suggestions
Subscriptions
Episode Actions
Devices
Settings
Favorites
Authentication
Device Sync
Podcast Lists
*/