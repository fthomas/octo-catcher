package octocatcher

import akka.actor.{ActorRef, Props}
import spray.client.HttpConduit;

package object gpodder {
  val (hostname, port) = ("gpodder.net", 443)

  def httpConduit() = {
    actorSystem.actorOf(
      Props(new HttpConduit(httpClient, hostname, port, sslEnabled = true)))
  }
}
