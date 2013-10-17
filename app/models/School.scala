package models

import play.api.db.DB
import anorm._

case class School(period : String, school: String, desc: String) {
}

object School{
  def getAll = {
    DB.withConnection{implicit c => val result: Boolean = SQL("Select 1").execute()}
  }
}