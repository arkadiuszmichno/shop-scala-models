package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class CareersController @Inject()(cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def addCareer(): Action[AnyContent] = Action { implicit request =>
    Ok("Added career")
  }

  def updateCareer(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Updated career")
  }

  def getCareer(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Return career")
  }

  def getCareers: Action[AnyContent] = Action { implicit request =>
    Ok("Return all careers")
  }

  def removeCareer(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Remove career")
  }

}
