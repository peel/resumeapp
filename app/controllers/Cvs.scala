package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.{JsValue, Json, Reads}
import models.{Letter, Author}
import utils.{FileDataSource, DataSource, DataApi}

object Cvs extends Controller {

  val dataApi = new DataApi with FileDataSource

  def get(company: String, role: String, letterEnabled: Boolean) = Action{
    val author = dataApi.getCvData[Author]("conf/data.json")
    val letter = dataApi.getCvData[Letter]("conf/letter.json")

    Ok(views.html.cv(company, role, author, letter, letterEnabled))
  }


  def getPdf(company: String, role: String, letterEnabled: Boolean) = play.mvc.Results.TODO
}