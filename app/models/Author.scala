package models

import play.api.libs.json.Json


case class Author(name: String,
                  role: String,
                  about: String,
                  contact: Map[String, String],
                  social: Map[String, String],
                  jobs: Seq[Job],
                  schools: Seq[School],
                  skills: Seq[Skill],
                  competencies: Seq[Competency],
                  interests: Seq[Interest])
object Author{
  implicit val authorFmt = Json.format[Author]
}
