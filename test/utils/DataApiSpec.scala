package utils

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

@RunWith(classOf[JUnitRunner])
class DataApiSpec extends Specification{

  val JSON_AUTHOR = "data.json"
  "DataApi" should {
    "create" in {
      "an Author object" in {
        DataApi.getCvData(JSON_AUTHOR)
        1 mustEqual 1
      }
      "a Letter object" in {
        1 mustEqual 1
      }
    }
  }

}
