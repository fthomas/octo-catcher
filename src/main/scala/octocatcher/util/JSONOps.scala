package octocatcher.util

import scala.util.parsing.json._
import spray.http.HttpResponse

object JSONOps {
  def parseFullArray(input: String): List[Any] = {
    JSON.parseFull(input) match {
      case Some(list: List[Any]) => list
      case _ => List()
    }
  }

  def parseFullObject(input: String): Map[String, Any] = {
    type JSONObjectType = Map[String, Any]

    JSON.parseFull(input) match {
      case Some(map: JSONObjectType) => map
      case _ => Map()
    }
  }

  def toJSONArray(response: HttpResponse): List[Any] =
    parseFullArray(response.entity.asString)

  def toJSONObject(response: HttpResponse): Map[String, Any] =
    parseFullObject(response.entity.asString)
}
