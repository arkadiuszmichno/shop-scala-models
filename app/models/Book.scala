package models

import play.api.libs.json.{Json, OFormat}

case class Book(id: Long, name: String, description: String, category: Int)

object Book {
  implicit val bookFormat: OFormat[Book] = Json.format[Book]
}
