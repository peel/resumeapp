package models

import play.api.libs.json.Json

case class Competency(name: String, level: Int, description: String) extends PersonValue

object Competency{
  implicit val comptenecyFmt = Json.format[Competency]
}
