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
