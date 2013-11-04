package utils

import play.api.libs.json.{Json, Reads}
import scala.io.Source

class DataApi {this: DataSource =>
  def getCvData[T: Reads](s: String): T = {
    val source = getSource(s)
    parseGen[T](source)
  }

  private def parseGen[T: Reads](s: String): T = {
    Json.parse(s).as[T]
  }

}

trait DataSource{
  def getSource(filename: String): String
}

trait FileDataSource extends DataSource{
  def getSource(filename: String) = {
    Source.fromFile(filename).getLines().mkString
  }
}
