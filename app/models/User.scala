package models

import play.api.libs.json.{Json, OFormat}

case class User(id: Long, firstName: String, lastName: String, login: String, gender: String)

object User {
  implicit val userFormat: OFormat[User] = Json.format[User]
}
