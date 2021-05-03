package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class DiscountCouponController @Inject()(cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def addDiscountCoupon(): Action[AnyContent] = Action { implicit request =>
    Ok("Added discount coupon")
  }

  def updateDiscountCoupon(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Updated discount coupon")
  }

  def getDiscountCoupon(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Return discount coupon")
  }

  def getDiscountCoupons: Action[AnyContent] = Action { implicit request =>
    Ok("Return all discount coupons")
  }

  def removeDiscountCoupon(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Remove discount coupon")
  }

}
