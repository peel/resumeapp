package controllers

import play.api.mvc._
import play.api.libs.json.{JsValue, Json, Reads}
import models.{Letter, Author}
import utils.{PdfApi, FileDataSource, DataSource, DataApi}
import scala.concurrent.Future
import play.api.mvc.SimpleResult
import java.io.ByteArrayOutputStream
import io.github.cloudify.scala.spdf._
import play.api.templates.HtmlFormat
import scala.util.{Success, Failure}
import scala.util.Success
import scala.util.Failure

object Cvs extends Controller {

  val dataApi = new DataApi with FileDataSource
  val author = dataApi.getCvData[Author]("conf/data.json")
  val letter = dataApi.getCvData[Letter]("conf/letter.json")

  def get(company: String, role: String, letterEnabled: Boolean) = Action{implicit request =>
    Ok(getCvHtml(company, role, letterEnabled))
  }

  def getPdf(company: String, role: String, letterEnabled: Boolean) = Action{implicit request =>
    val view = getCvHtml(company, role, letterEnabled).body
    PdfApi(pdfConfig).getPdf(view) match {
      case Success(r) => Ok(r).as("application/pdf")
      case Failure(e) => e match {
        case NoExecutableException(_) => BadRequest(e.getMessage)
        case _ => BadRequest("Ups! something went all awry here.")
      }
    }
  }

  private def pdfConfig = new PdfConfig {
    orientation := Portrait
    pageSize := "A4"
    marginTop := "0.2in"
    marginBottom := "0.2in"
    marginLeft := "0.2in"
    marginRight := "0.2in"
    grayScale := false
  }

  private def getCvHtml(company: String, role: String, letterEnabled: Boolean): HtmlFormat.Appendable = {
    views.html.cv(company, role, author, letter, letterEnabled)
  }

}
