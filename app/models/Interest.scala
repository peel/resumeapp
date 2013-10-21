package models

import play.api.libs.json.Json

case class Interest(name: String, level: Int, description: String)

object Interest{
  implicit val interestFmt = Json.format[Interest]
}
