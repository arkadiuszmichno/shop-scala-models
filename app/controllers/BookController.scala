package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class BookController @Inject()(cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def addBook(): Action[AnyContent] = Action { implicit request =>
    Ok("Added book")
  }

  def updateBook(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Updated book")
  }

  def getBook(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Return book")
  }

  def getBooks: Action[AnyContent] = Action { implicit request =>
    Ok("Return all books")
  }

  def removeBook(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Remove book")
  }

}
