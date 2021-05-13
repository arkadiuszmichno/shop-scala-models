package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class BookReviewRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, userRepository: UserRepository, bookRepository: BookRepository)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import bookRepository.BookTable
  import dbConfig._
  import profile.api._
  import userRepository.UserTable

  class BookReviewTable(tag: Tag) extends Table[BookReview](tag, "bookReview") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def user = column[Long]("user")

    def user_fk = foreignKey("user_fk", user, usr)(_.id)

    def book = column[Long]("book")

    def book_fk = foreignKey("book_fk", book, bk)(_.id)

    def review = column[String]("review")

    def * = (id, user, book, review) <> ((BookReview.apply _).tupled, BookReview.unapply)
  }

  val bkReview = TableQuery[BookReviewTable]
  private val usr = TableQuery[UserTable]
  private val bk = TableQuery[BookTable]


  def create(user: Long, book: Long, review: String): Future[BookReview] = db.run {
    (bkReview.map(b => (b.user, b.book, b.review))
      returning bkReview.map(_.id)
      into { case ((user, book, review), id) => BookReview(id, user, book, review) }
      ) += (user, book, review)
  }

  def list(): Future[Seq[BookReview]] = db.run {
    bkReview.result
  }

  def getById(id: Long): Future[BookReview] = db.run {
    bkReview.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Unit] = db.run(bkReview.filter(_.id === id).delete).map(_ => ())

  def update(id: Long, new_bookReview: BookReview): Future[Unit] = {
    val bookReviewToUpdate: BookReview = new_bookReview.copy(id)
    db.run(bkReview.filter(_.id === id).update(bookReviewToUpdate)).map(_ => ())
  }
}
