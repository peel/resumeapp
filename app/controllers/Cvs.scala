package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.{JsValue, Json, Reads}
import models.{Letter, Author}
import utils.DataApi

object Cvs extends Controller {
  def get(company: String, role: String, letterEnabled: Boolean) = Action{
    val author = DataApi.getCvData[Author]("conf/data.json")
    val letter = DataApi.getCvData[Letter]("conf/letter.json")

    Ok(views.html.cv(company, role, author, letter, letterEnabled))
  }


  def getPdf(company: String, role: String, letterEnabled: Boolean) = play.mvc.Results.TODO
}