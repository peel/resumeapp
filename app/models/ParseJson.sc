import play.api.libs.json.Json
case class Interest(name: String, level: Int, description: String)
  implicit val interestFmt = Json.format[Interest]

case class Job(period: String, company: String, role : String, description: String)
  implicit val jobFmt = Json.format[Job]

case class Skill(name: String, level: Int, description: String)
implicit val skillFmt = Json.format[Skill]

case class Competency(name: String, level: Int, description: String)
  implicit val comptenecyFmt = Json.format[Competency]

case class School(period : String, name: String, location: String, description: String)
  implicit val schoolFmt = Json.format[School]

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

import scala.io.Source
val source = Source.fromFile("conf/data.json").getLines().mkString

val json = Json.parse(source)

val resp = Json.toJson(json)
