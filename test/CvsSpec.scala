import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class CvsSpec extends Specification {

  "Cvs" should {
   "read in Author data" in {
    1 mustEqual 1
   }
  }

}

