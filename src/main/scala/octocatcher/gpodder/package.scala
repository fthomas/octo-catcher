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

package octocatcher

import akka.actor.Props
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import spray.client.HttpConduit
import spray.client.HttpConduit._
import spray.http.HttpResponse
import spray.httpx.unmarshalling.Unmarshaller

package object gpodder {
  val (hostname, port) = ("gpodder.net", 443)

  def makeHttpsConduit() = actorSystem.actorOf(
    Props(new HttpConduit(httpClient, hostname, port, sslEnabled = true)))

  lazy val defaultPipeline = sendReceive(makeHttpsConduit())

  def getUri(uri: String): Future[HttpResponse] = {
    defaultPipeline(Get(uri))
  }

  def getUriUnmarshalled[A : Unmarshaller](uri: String): Future[A] = {
    val pipeline = defaultPipeline ~> unmarshal[A]
    pipeline(Get(uri))
  }
}
