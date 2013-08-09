// Copyright 2012-2013 Frank S. Thomas
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

package octocatcher.gpodder.api

import octocatcher.gpodder._
import GpodderJsonProtocol._

import scala.concurrent.Future
import spray.httpx.SprayJsonSupport._

object Directory {
  /**
   * Returns the `count` most used tags.
   * @see [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Top_Tags Retrieve Top Tags]]
   */
  def getTopTags(count: Int): Future[List[TagUsage]] = {
    require(count >= 0)

    getUriUnmarshalled[List[TagUsage]](URL.pathToTopTags(count))
  }

  /**
   * Returns the `count` most-subscribed podcasts that are tagged with `tag`.
   * @see [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Podcasts_for_Tag Retrieve Podcasts for Tag]]
   */
  def getPodcastsForTag(tag: String, count: Int): Future[List[PodcastData]] = {
    require(tag.length > 0 && count > 0)

    getUriUnmarshalled[List[PodcastData]](URL.pathToPodcastsForTag(tag, count))
  }

  /**
   * Returns information for the podcast with the given URL.
   * @see [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Podcast_Data Retrieve Podcast Data]]
   */
  def getPodcastData(podcastUrl: String): Future[PodcastData] = {
    require(podcastUrl.length > 0)

    getUriUnmarshalled[PodcastData](URL.pathToPodcastData(podcastUrl))
  }

  /** Returns information for an episode of a podcast.
   * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Retrieve_Episode_Data Retrieve Episode Data]]
   */
  /*def getEpisodeData(podcastUrl: String, episodeUrl: String):
      Future[HttpResponse] = {
    require(podcastUrl.length > 0 && episodeUrl.length > 0)

    getUrl(URL.pathToEpisodeData(podcastUrl, episodeUrl))
  }*/

  /** Returns the `count` most-popular podcasts in descending order.
   * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Podcast_Toplist Podcast Toplist]]
   */
  /*def getToplist(count: Int): Future[HttpResponse] = {
    require(1 to 100 contains count)

    getUrl(URL.pathToToplist(count))
  }*/

  /** Returns search results for podcasts that match the given query.
   * [[http://wiki.gpodder.org/wiki/Web_Services/API_2/Directory#Podcast_Search Podcast Search]]
   */
  /*def searchPodcasts(query: String): Future[HttpResponse] = {
    require(query.length > 0)

    getUrl(URL.pathToSearch(query))
  }*/
}
