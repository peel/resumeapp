package models

import play.api.libs.json.Json

case class Skill(name: String, level: Int, description: String) extends PersonValue

object Skill{
  implicit val skillFmt = Json.format[Skill]
}
