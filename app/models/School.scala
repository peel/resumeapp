package models

import play.api.libs.json.Json


case class School(period : String, name: String, location: String, description: String)

object School{
  implicit val schoolFmt = Json.format[School]
}