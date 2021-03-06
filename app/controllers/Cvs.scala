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
import com.wordnik.swagger.annotations.{ApiParam, ApiOperation, Api}

@Api(value="/", description="Operations on CVs")
object Cvs extends Controller {

  private val dataApi = new DataApi with FileDataSource
  private val author = dataApi.getCvData[Author]("conf/data.json")
  private val letter = dataApi.getCvData[Letter]("conf/letter.json")
  private val pdfConfig = new PdfConfig {
    orientation := Portrait
    pageSize := "A4"
    marginTop := "0.2in"
    marginBottom := "0.2in"
    marginLeft := "0.2in"
    marginRight := "0.2in"
    zoom := 0.9F
    grayScale := false
  }

  @ApiOperation(value="Get a HTML CV for company and role", notes="Returns a HTML CV page", httpMethod="GET")
  def getHtml(@ApiParam(value="Company to which the CV is addressed")company: String, 
              @ApiParam(value="Role for which the CV is applied") role: String,
              @ApiParam(value="Should letter be attached") letterEnabled: Boolean
             ) = Action{implicit request =>
    Ok(cvFormat(company, role, letterEnabled))
  }

  @ApiOperation(value="Get a PDF CV for company and role", notes="Returns a HTML CV page", httpMethod="GET")
  def getPdf(@ApiParam(value="Company to which the CV is addressed")company: String, 
             @ApiParam(value="Role for which the CV is applied") role: String, 
             @ApiParam(value="Should letter be attached") letterEnabled: Boolean
            ) = Action{implicit request =>
    val view = cvFormat(company, role, letterEnabled).body
    PdfApi(pdfConfig).getPdf(view) match {
      case Success(r) => Ok(r).as("application/pdf")
      case Failure(e) => e match {
        case NoExecutableException(_) => BadRequest(e.getMessage)
        case _ => BadRequest("Ups! something went all awry here.")
      }
    }
  }

  private def cvFormat(company: String, role: String, letterEnabled: Boolean): HtmlFormat.Appendable = {
    views.html.cv(company, role, author, letter, letterEnabled)
  }

}
