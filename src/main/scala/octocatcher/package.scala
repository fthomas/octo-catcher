import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import spray.can.client.HttpClient
import spray.io.IOBridge

package object octocatcher {
  implicit val actorSystem = ActorSystem("octo-catcher")

  val ioBridge = new IOBridge(actorSystem).start()
  val httpClient = {
    val cfg = ConfigFactory.parseString("spray.can.client.ssl-encryption = on")
    actorSystem.actorOf(Props(new HttpClient(ioBridge, cfg)))
  }

  actorSystem.registerOnTermination {
    ioBridge.stop()
  }
}
