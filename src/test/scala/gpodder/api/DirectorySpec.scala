
import octocatcher.gpodder.api.Directory._

import scala.concurrent.ExecutionContext.Implicits._
import org.specs2.mutable._


class DirectorySpec extends Specification {
  "Directory" should {
    "getTopTags" in {
      getTopTags(5).onSuccess {
        //case x => x
        case response => println(response)
      }
      //true must_== true
    }
  }
}
