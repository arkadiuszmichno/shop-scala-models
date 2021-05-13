package models

import play.api.libs.json.{Json, OFormat}

case class GiftCard(id: Long, name: String, value: Int, category: Int)

object GiftCard {
  implicit val giftCardFormat: OFormat[GiftCard] = Json.format[GiftCard]
}
