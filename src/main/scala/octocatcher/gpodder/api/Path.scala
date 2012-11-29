package octocatcher.gpodder.api

object Path {
  sealed trait Format {
    override def toString = getClass.getSimpleName.toLowerCase.init
  }

  object Json extends Format
  object Opml extends Format
  object Txt extends Format
  object Xml extends Format

  // Directory:

  def topTags(count: Int)(implicit f: Format): String = {
    s"/api/2/tags/${count}.${f}"
  }

  def podcastsForTag(tag: String, count: Int)(implicit f: Format): String = {
    s"/api/2/tag/${tag}/${count}.${f}"
  }

  def podcastData(url: String)(implicit f: Format): String = {
    s"/api/2/data/podcast.${f}?url=${url}"
  }

  def episodeData(podcastUrl: String, episodeUrl: String)
                 (implicit f: Format): String = {
    s"/api/2/data/episode.${f}?podcast=${podcastUrl}&url=${episodeUrl}"
  }

  def podcastToplist(count: Int)(implicit f: Format): String = {
    s"/toplist/${count}.${f}"
  }

  def podcastSearch(query: String)(implicit f: Format): String = {
    s"/search.${f}?q=${query}"
  }
}
