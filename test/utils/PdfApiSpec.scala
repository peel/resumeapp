package utils

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import java.io.ByteArrayOutputStream
import io.github.cloudify.scala.spdf.{PdfConfig, Portrait}

@RunWith(classOf[JUnitRunner])
class PdfApiSpec extends Specification {
  "PdfApi" should {
   "print bytes of a pdf form" in {
     val body = "<html><body><h1>Test</h1></body></html>"
     val bytes = PdfApi(pdfConfig).getPdf(body).get
     bytes must haveClass[Array[Byte]] and not beNull
   }
  }
   val pdfConfig = new PdfConfig {
    orientation := Portrait
    pageSize := "A4"
    marginTop := "0.2in"
    marginBottom := "0.2in"
    marginLeft := "0.2in"
    marginRight := "0.2in"
    grayScale := false
  }

}

