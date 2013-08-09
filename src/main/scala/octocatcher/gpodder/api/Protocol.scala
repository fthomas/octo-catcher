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

import spray.json._

case class PodcastData(
  title: String,
  url: String,
  description: String,
  subscribers: Int,
  logo_url: Option[String],
  website: String,
  mygpo_link: String)

case class EpisodeData(
  title: String,
  url: String,
  description: String,
  podcast_title: String,
  podcast_url: String,
  website: String,
  released: String,
  mygpo_link: String)

case class TagUsage(tag: String, usage: Int)

object GpodderJsonProtocol extends DefaultJsonProtocol {
  implicit val podcastDataFormat = jsonFormat7(PodcastData)
  implicit val episodeDataFormat = jsonFormat8(EpisodeData)
  implicit val tagUsageFormat = jsonFormat2(TagUsage)
}
