package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class ReturnController @Inject()(cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def addReturn(): Action[AnyContent] = Action { implicit request =>
    Ok("Added return")
  }

  def updateReturn(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Updated return")
  }

  def getReturn(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Return return")
  }

  def getReturns: Action[AnyContent] = Action { implicit request =>
    Ok("Return all returns")
  }

  def removeReturn(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Remove return")
  }

}
