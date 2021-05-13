package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CartRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, userRepository: UserRepository, bookRepository: BookRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import bookRepository.BookTable
  import dbConfig._
  import profile.api._
  import userRepository.UserTable

  class CartTable(tag: Tag) extends Table[Cart](tag, "cart") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def user = column[Long]("user")

    def book = column[Long]("book")

    def user_fk = foreignKey("user_fk", user, usr)(_.id)

    def book_fk = foreignKey("book_fk", book, bk)(_.id)

    def * = (id, user, book) <> ((Cart.apply _).tupled, Cart.unapply)
  }

  private val cart = TableQuery[CartTable]
  private val usr = TableQuery[UserTable]
  private val bk = TableQuery[BookTable]


  def list(): Future[Seq[Cart]] = db.run {
    cart.result
  }

  def getByUser(user_id: Long): Future[Seq[Cart]] = db.run {
    cart.filter(_.user === user_id).result
  }

  def create(user: Long, book: Long): Future[Cart] = db.run {
    (cart.map(c => (c.user, c.book))
      returning cart.map(_.id)
      into { case ((user, book), id) => Cart(id, user, book) }
      ) += (user, book)
  }

  def delete(id: Long): Future[Unit] = db.run(cart.filter(_.id === id).delete).map(_ => ())

}