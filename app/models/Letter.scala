package models

import play.api.libs.json.Json

case class Letter(val greeting: String, val content: String, val sincerely: String)

object Letter{
  implicit val letterFmt = Json.format[Letter]
}

