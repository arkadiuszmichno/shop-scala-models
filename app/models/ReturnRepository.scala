package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ReturnRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, userRepository: UserRepository, bookRepository: BookRepository)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import bookRepository.BookTable
  import dbConfig._
  import profile.api._
  import userRepository.UserTable

  class ReturnTable(tag: Tag) extends Table[Return](tag, "return") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def user = column[Long]("user")

    def user_fk = foreignKey("user_fk", user, usr)(_.id)

    def book = column[Long]("book")

    def book_fk = foreignKey("book_fk", book, bk)(_.id)

    def * = (id, user, book) <> ((Return.apply _).tupled, Return.unapply)
  }

  val rtrn = TableQuery[ReturnTable]
  private val usr = TableQuery[UserTable]
  private val bk = TableQuery[BookTable]


  def create(user: Long, book: Long): Future[Return] = db.run {
    (rtrn.map(b => (b.user, b.book))
      returning rtrn.map(_.id)
      into { case ((user, book), id) => Return(id, user, book) }
      ) += (user, book)
  }

  def list(): Future[Seq[Return]] = db.run {
    rtrn.result
  }

  def getById(id: Long): Future[Return] = db.run {
    rtrn.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Unit] = db.run(rtrn.filter(_.id === id).delete).map(_ => ())

}
