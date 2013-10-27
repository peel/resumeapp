package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.Json
import scala.io.Source
import models.Author

object Cvs extends Controller {
  def get(company: String, role: String, letterEnabled: Boolean) = Action{
    val source = Source.fromFile("conf/data.json").getLines().mkString
    val sourceLetter = Source.fromFile("conf/letter.json").getLines().mkString
    val jsonLetter = Json.parse(sourceLetter)
    val author = Json.parse(source).as[Author]
    val letter = (jsonLetter \ "letter").as[String].format(author.name)
    Ok(views.html.cv(company, role, author, letter, letterEnabled))
  }

}