package models

import play.api.libs.json.Json

case class Job(period: String, company: String, role : String, description: String)

object Job{
  implicit val jobFmt = Json.format[Job]
}
