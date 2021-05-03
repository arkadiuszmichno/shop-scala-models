package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class BookReviewController @Inject()(cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def addReview(): Action[AnyContent] = Action { implicit request =>
    Ok("Added review")
  }

  def updateReview(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Updated review")
  }

  def getReview(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Return review")
  }

  def getReviews: Action[AnyContent] = Action { implicit request =>
    Ok("Return all reviews")
  }

  def removeReview(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok("Remove review")
  }

}
