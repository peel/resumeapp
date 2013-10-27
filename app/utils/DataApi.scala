package utils

import play.api.libs.json.{Json, Reads}
import scala.io.Source

object DataApi {
  def getCvData[T: Reads](s: String): T = {
    val source = getSource(s)
    parseGen[T](source)
  }

  private def parseGen[T: Reads](s: String): T = {
    Json.parse(s).as[T]
  }

  private def getSource(filename: String) = {
    Source.fromFile(filename).getLines().mkString
  }

}
