package controllers

import play.api.mvc._
import play.api.libs.json.{JsValue, Json, Reads}
import models.{Letter, Author}
import utils.{FileDataSource, DataSource, DataApi}
import scala.concurrent.Future
import play.api.mvc.SimpleResult
import java.io.ByteArrayOutputStream
import io.github.cloudify.scala.spdf.{Portrait, Landscape, PdfConfig, Pdf}

object Cvs extends Controller {

  val dataApi = new DataApi with FileDataSource
  val author = dataApi.getCvData[Author]("conf/data.json")
  val letter = dataApi.getCvData[Letter]("conf/letter.json")

  def get(company: String, role: String, letterEnabled: Boolean) = Action{implicit request =>
    Ok(views.html.cv(company, role, author, letter, letterEnabled))
  }

  def getPdf(company: String, role: String, letterEnabled: Boolean) = Action{implicit request =>
    val view = views.html.cv(company, role, author, letter, letterEnabled).body
    val output = new ByteArrayOutputStream
    val pdf = Pdf(new PdfConfig {
      orientation := Portrait
      pageSize := "A4"
      marginTop := "0.2in"
      marginBottom := "0.2in"
      marginLeft := "0.2in"
      marginRight := "0.2in"
      grayScale := false
    })
    pdf.run(view, output)
    Ok(output.toByteArray).as("application/pdf")
  }
}

