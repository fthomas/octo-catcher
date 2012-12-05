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

import scala.concurrent.Future
import akka.actor.{ActorRef, Props}
import spray.client.HttpConduit;
import spray.http._
import spray.http.HttpMethods._

package object gpodder {
  val (hostname, port) = ("gpodder.net", 443)

  def makeHttpsConduit() = {
    actorSystem.actorOf(
      Props(new HttpConduit(httpClient, hostname, port, sslEnabled = true)))
  }

  lazy val pipeline = HttpConduit.sendReceive(makeHttpsConduit())

  def getUrl(url: String): Future[HttpResponse] = {
    pipeline(HttpRequest(GET, url))
  }
}
