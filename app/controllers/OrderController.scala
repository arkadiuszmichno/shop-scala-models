package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class OrderController @Inject()(cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def addOrder(): Action[AnyContent] = Action { implicit request =>
    Ok("Added order")
  }

  def updateOrder(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Updated order")
  }

  def getOrder(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Return order")
  }

  def getOrders: Action[AnyContent] = Action { implicit request =>
    Ok("Return all orders")
  }

  def removeOrder(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Remove order")
  }

}
