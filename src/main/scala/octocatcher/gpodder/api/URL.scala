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

package octocatcher.gpodder.api

object URL {
  sealed trait Format {
    override def toString = getClass.getSimpleName.init.toLowerCase
  }

  object JSON extends Format
  object OPML extends Format
  object TXT extends Format
  object XML extends Format

  // Directory:

  def pathToTopTags(count: Int)(implicit format: Format): String = {
    s"/api/2/tags/${count}.${format}"
  }

  def pathToPodcastsForTag(tag: String, count: Int)
                          (implicit format: Format): String = {
    s"/api/2/tag/${tag}/${count}.${format}"
  }

  def pathToPodcastData(url: String)(implicit format: Format): String = {
    s"/api/2/data/podcast.${format}?url=${url}"
  }

  def pathToEpisodeData(podcastUrl: String, episodeUrl: String)
                       (implicit format: Format): String = {
    s"/api/2/data/episode.${format}?podcast=${podcastUrl}&url=${episodeUrl}"
  }

  def pathToToplist(count: Int)(implicit format: Format): String = {
    s"/toplist/${count}.${format}"
  }

  def pathToSearch(query: String)(implicit format: Format): String = {
    s"/search.${format}?q=${query}"
  }

  // Suggestions

  def pathToSuggestions(count: Int)(implicit format: Format): String = {
    s"/suggestions/${count}.${format}"
  }
}
