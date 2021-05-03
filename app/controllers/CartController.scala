package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class CartController @Inject()(cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def addCart(): Action[AnyContent] = Action { implicit request =>
    Ok("Added cart")
  }

  def addToCart(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Add to cart")
  }

  def removeFromCart(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Remove from cart")
  }

  def getCart(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Return cart")
  }

  def getCarts: Action[AnyContent] = Action { implicit request =>
    Ok("Return all cart")
  }

  def removeCart(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Remove cart")
  }

}
