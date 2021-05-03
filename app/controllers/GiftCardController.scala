package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class GiftCardController @Inject()(cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def addGiftCard(): Action[AnyContent] = Action { implicit request =>
    Ok("Added gift card")
  }

  def updateGiftCard(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Updated gift card")
  }

  def getGiftCard(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Return gift card")
  }

  def getGiftCards: Action[AnyContent] = Action { implicit request =>
    Ok("Return all gift cards")
  }

  def removeGiftCard(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Remove gift card")
  }

}
