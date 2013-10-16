package controllers

import play.api.mvc.{Action, Controller}
import play.api.i18n.Messages

object Cvs extends Controller {
  def get(company: String, role: String, letterEnabled: Boolean) = Action{
    Ok(views.html.cv(company, role, letterEnabled))
  }
}