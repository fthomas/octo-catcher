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

  // Subscriptions

  def pathToSubscriptionsForDevice(username: String, deviceId: String)
      (implicit format: Format): String = {
    s"/subscriptions/${username}/${deviceId}.${format}"
  }

  def pathToSubscriptions(username: String)
      (implicit format: Format): String = {
    s"/subscriptions/${username}.${format}"
  }

  def pathToUploadSubscriptions(username: String, deviceId: String)
      (implicit format: Format): String = {
    s"/subscriptions/${username}/${deviceId}.${format}"
  }

  def pathToUploadSubscriptionsChanges(username: String, deviceId: String)
      (implicit format: Format): String = {
    s"/api/2/subscriptions/${username}/${deviceId}.${format}"
  }

  def pathToSubscriptionsChanges(username: String, deviceId: String,
      timestamp: String)(implicit format: Format): String = {
    s"/api/2/subscriptions/${username}/${deviceId}.${format}?since=${timestamp}"
  }

  // Episode Actions

  def pathToUploadEpisodeActions(username: String)
      (implicit format: Format): String = {
    s"/api/2/episodes/${username}.${format}"
  }

  def pathToGetEpisodeActions(username: String)
      (implicit format: Format): String = {
    s"/api/2/episodes/${username}.${format}"
  }

  // Devices

  def pathToDeviceConfiguration(username: String, deviceId: String)
      (implicit format: Format): String = {
    s"/api/2/devices/${username}/${deviceId}.${format}"
  }

  def pathToDevicesList(username: String)(implicit format: Format): String = {
    s"/api/2/devices/${username}.${format}"
  }

  def pathToDeviceUpdates(username: String, deviceId: String,
      timestamp: String)(implicit format: Format): String = {
    s"/api/2/updates/${username}/${deviceId}.${format}?since=${timestamp}"
  }

  // Settings

  def pathToAccountSettings(username: String)
      (implicit format: Format): String = {
    s"/api/2/settings/${username}/account.${format}"
  }

  def pathToDeviceSettings(username: String, deviceId: String)
      (implicit format: Format): String = {
    s"/api/2/settings/${username}/device.${format}?device=${deviceId}"
  }

  def pathToPodcastSettings(username: String, podcastUrl: String)
      (implicit format: Format): String = {
    s"/api/2/settings/${username}/podcast.${format}?podcast=${podcastUrl}"
  }

  def pathToEpisodeSettings(username: String, podcastUrl: String,
      episodeUrl: String)(implicit format: Format): String = {
    s"/api/2/settings/${username}/episode.${format}" +
      s"?podcast=${podcastUrl}&episode=${episodeUrl}"
  }

  // Favorites

  def pathToFavorites(username: String)(implicit format: Format): String = {
    s"/api/2/favorites/${username}.${format}"
  }

  // Authentication

  def pathToLogin(username: String)(implicit format: Format): String = {
    s"/api/2/auth/${username}/login.${format}"
  }

  def pathToLogout(username: String)(implicit format: Format): String = {
    s"/api/2/auth/${username}/logout.${format}"
  }

  // Device Sync

  // Podcast Lists
}
