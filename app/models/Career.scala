package models

import play.api.libs.json.{Json, OFormat}

case class Career(id: Long, position: String, description: String)

object Career {
  implicit val careerFormat: OFormat[Career] = Json.format[Career]
}
