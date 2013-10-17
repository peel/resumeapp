package controllers

import play.api.mvc.{Action, Controller}

object Cvs extends Controller {
  def get(company: String, role: String, letterEnabled: Boolean) = Action{
    Ok(views.html.cv(company, role, letterEnabled))
  }
}