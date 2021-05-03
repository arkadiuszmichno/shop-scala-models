package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class UserController @Inject()(cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def addUser(): Action[AnyContent] = Action { implicit request =>
    Ok("Added user")
  }

  def updateUser(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Updated user")
  }

  def getUser(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Return user")
  }

  def getUsers: Action[AnyContent] = Action { implicit request =>
    Ok("Return all users")
  }

  def removeUser(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Remove user")
  }

}
