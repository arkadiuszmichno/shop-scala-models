package models

import play.api.libs.json.{Json, OFormat}

case class DiscountCoupon(id: Long, name: String, value: Int)

object DiscountCoupon {
  implicit val discountCouponFormat: OFormat[DiscountCoupon] = Json.format[DiscountCoupon]
}
