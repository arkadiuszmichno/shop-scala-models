package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class CategoryController @Inject()(cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def addCategory(): Action[AnyContent] = Action { implicit request =>
    Ok("Added category")
  }

  def updateCategory(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Updated category")
  }

  def getCategory(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Return category")
  }

  def getCategories: Action[AnyContent] = Action { implicit request =>
    Ok("Return all categories")
  }

  def removeCategory(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Remove category")
  }

}
