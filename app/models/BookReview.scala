package models

import play.api.libs.json.{Json, OFormat}

case class BookReview(id: Long, book: Long, user: Long, review: String)

object BookReview {
  implicit val bookReviewFormat: OFormat[BookReview] = Json.format[BookReview]
}
