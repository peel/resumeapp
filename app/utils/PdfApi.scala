package utils

import io.github.cloudify.scala.spdf.{Portrait, Pdf, PdfConfig}
import java.io.ByteArrayOutputStream
import scala.util.{Failure, Success, Try}

case class PdfApi(config: PdfConfig) {
  private val pdf = Try(Pdf(config))

  def getPdf(view: String) = {
    val output = new ByteArrayOutputStream
    pdfStream(view, output)
    toBytes(output)
  }

  private def toBytes(output: ByteArrayOutputStream) = Try(output.toByteArray)

  private def pdfStream(view: String, output: ByteArrayOutputStream) = pdf.map(pdf => pdf.run(view, output))
}
